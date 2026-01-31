package br.ifba.edu.inf011.command;

import java.util.ArrayList;
import java.util.List;

public class DocumentoCommandManager {
	private DocumentoCommand documentCommand = null;
	private final List<String> logCommands = new ArrayList<String>();
	
	public void executeCommand(DocumentoCommand cmd) {
		if (this.documentCommand == null) {
			this.documentCommand = cmd;
		} else {
			this.documentCommand = this.documentCommand.addChild(cmd);
		}
		logCommands.add(cmd.getLog(Boolean.TRUE));
		cmd.execute();
	}
	
	public void revertCommand() {
		if (this.documentCommand != null) {
			logCommands.add(documentCommand.getLog(Boolean.FALSE));
			this.documentCommand.revert();
			this.documentCommand = this.documentCommand.getParent();
		}
	}
	
	public String getLogs() {
		String logFull = "";
		for(String log : this.logCommands) {
			logFull += log + "\n";
		}
		return logFull;
	}
}
