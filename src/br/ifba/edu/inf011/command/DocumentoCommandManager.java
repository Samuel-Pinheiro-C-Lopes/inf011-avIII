package br.ifba.edu.inf011.command;

import br.ifba.edu.inf011.command.interfaces.DocumentoCommand;

public class DocumentoCommandManager {
	private DocumentoCommand currentCommand = null;
	private Boolean firstCommand = Boolean.FALSE;

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
	
	public void redo() throws Exception {
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
	
	public void clear() {
		this.currentCommand = null;
	}
}
