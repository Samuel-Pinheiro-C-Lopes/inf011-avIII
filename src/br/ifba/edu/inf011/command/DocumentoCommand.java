package br.ifba.edu.inf011.command;

import br.ifba.edu.inf011.model.documentos.Documento;

// PARTICIPANTE: Command
public interface DocumentoCommand {
	void execute(Documento documento);
	void revert();
}
