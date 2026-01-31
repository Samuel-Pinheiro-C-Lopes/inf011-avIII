package br.ifba.edu.inf011.command;

import br.ifba.edu.inf011.composite.Component;
import br.ifba.edu.inf011.model.FWDocumentException;
import br.ifba.edu.inf011.model.documentos.Documento;

// PARTICIPANTE: Command (Command) e Component (Composite)
public interface DocumentoCommand /*extends Component */{
	void execute();
	void revert();
	DocumentoCommand getChild();
	DocumentoCommand getParent();
	String getLog(Boolean isExecute);
	void setParent(DocumentoCommand parent);
	DocumentoCommand addChild(DocumentoCommand child);
}