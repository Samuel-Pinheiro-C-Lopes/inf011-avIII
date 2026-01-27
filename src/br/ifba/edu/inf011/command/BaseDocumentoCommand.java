package br.ifba.edu.inf011.command;

import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;

public abstract class BaseDocumentoCommand implements DocumentoCommand {
	protected final GerenciadorDocumentoModel gestorDocumento;
	
	protected BaseDocumentoCommand(final GerenciadorDocumentoModel gestorDocumento) {
		this.gestorDocumento = gestorDocumento;
	}
}
