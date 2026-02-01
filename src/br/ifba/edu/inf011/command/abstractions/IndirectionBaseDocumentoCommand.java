package br.ifba.edu.inf011.command.abstractions;

import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;
import br.ifba.edu.inf011.model.documentos.Documento;

public abstract class IndirectionBaseDocumentoCommand extends BaseDocumentoCommand {
	protected Documento documentoAnterior;
	protected String numeroDocumentoAnterior;
	
	protected IndirectionBaseDocumentoCommand(GerenciadorDocumentoModel gestorDocumento) {
		super(gestorDocumento);
		this.documentoAnterior = null;
		this.numeroDocumentoAnterior = null;
	}
	
	@Override
	protected void revertHook() throws Exception {
		if (this.documentoAnterior == null)
			return;
		
		Documento docAtual = this.getDocumentoByNumero(this.numeroDocumentoAnterior);
		this.gestorDocumento.atualizarRepositorio(docAtual, documentoAnterior);
		this.gestorDocumento.setDocumentoAtual(documentoAnterior);
	}
}
