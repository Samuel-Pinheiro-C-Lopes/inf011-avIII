package br.ifba.edu.inf011.command;

import br.ifba.edu.inf011.command.interfaces.DocumentoCommand;

public class DocumentoCommandManager {
	private DocumentoCommand currentCommand = null;
	private Boolean firstCommand = Boolean.FALSE;
	
	// do: cc = cd1, cc = ed1,  cc = ad1, cc = ud1, cc = cd2, cc = ed2, cc = ad2, cc = ud2
	// undo: 
	// criar (d1), escrever (d1), assinar(d1), urgente(d1), criar (d2), escrever (d2), assinar (d2), urgente (d2) 
	// tirar urgente (d2), tirar assinatura (d2), tirar escrever (d2), tirar criar (d2)
	
	public void execute(DocumentoCommand cmd) throws Exception {
		if (this.currentCommand == null) {
			this.currentCommand = cmd;
			this.firstCommand = Boolean.TRUE;
		} else {
			this.currentCommand = this.currentCommand.addChild(cmd);
			this.firstCommand = Boolean.FALSE;
		}

		cmd.execute();
	}
	
	public void undo() throws Exception {
		if (this.currentCommand == null || this.firstCommand == Boolean.TRUE) 
			return;
		
		this.currentCommand.undo();
		
		if (this.currentCommand.getParent() != null) {
			this.currentCommand = this.currentCommand.getParent();
		} else {
			this.firstCommand = Boolean.TRUE;
		}
	}
	
	public void revert() throws Exception {
		if (this.currentCommand == null)
			return;

		if (this.firstCommand == Boolean.FALSE) {
			if (this.currentCommand.getChild() == null)
				return;
			
			this.currentCommand = this.currentCommand.getChild();
		} else {			
			this.firstCommand = Boolean.FALSE;
		}
		
		this.currentCommand.execute();
	}
}
