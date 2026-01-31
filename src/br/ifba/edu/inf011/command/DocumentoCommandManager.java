package br.ifba.edu.inf011.command;

import br.ifba.edu.inf011.command.interfaces.DocumentoCommand;

public class DocumentoCommandManager {
	private DocumentoCommand currentCommand = null;
	
	public void execute(DocumentoCommand cmd) throws Exception {
		if (this.currentCommand == null) {
			this.currentCommand = cmd;
		} else {
			this.currentCommand = this.currentCommand.addChild(cmd);
		}

		cmd.execute();
	}
	
	public void undo() throws Exception {
		if (this.currentCommand == null) 
			return;
		
		this.currentCommand.undo();
		this.currentCommand = this.currentCommand.getParent();
	}
	
	public void revert() throws Exception {
		if (this.currentCommand.getChild() == null)
			return;
		
		this.currentCommand = this.currentCommand.getChild();
		this.currentCommand.execute();
	}
}
