package br.ifba.edu.inf011.command.interfaces;

// PARTICIPANTE: Command (Command) e Component (Composite)
public interface DocumentoCommand /*extends Component */{
	void execute() throws Exception;
	void undo() throws Exception;
	DocumentoCommand getChild();
	DocumentoCommand getParent();
	DocumentoCommand addChild(DocumentoCommand child);
	void setParent(DocumentoCommand parent);
}