package br.ifba.edu.inf011.command.interfaces;

// PARTICIPANTE: Command (Command) e Component (Composite)
public interface DocumentoCommand {
	void execute() throws Exception;
	void undo() throws Exception;
	DocumentoCommand getChild();
	DocumentoCommand getParent();
	DocumentoCommand addChild(DocumentoCommand child);
	DocumentoCommand addSubCommand(DocumentoCommand subCommand);
	void setParent(DocumentoCommand parent);
}