package br.ifba.edu.inf011.command.abstractions;

import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;
import br.ifba.edu.inf011.model.documentos.Documento;

public abstract class IndirecaoBaseDocumentoCommand extends BaseDocumentoCommand {
	protected Documento documentoAnterior;
	
	protected IndirecaoBaseDocumentoCommand(final GerenciadorDocumentoModel gestorDocumento) {
		super(gestorDocumento);
	}
	
	@Override
	public void revertHook() {
		if (this.documentoAnterior == null) return;
		
		for (Documento docAtual : this.gestorDocumento.getRepositorio()) {
			if (
				docAtual != null && docAtual.getNumero() != null && 
			    this.documentoAnterior.getNumero() != null &&
			    docAtual.getNumero().equals(this.documentoAnterior.getNumero())
		    ) {
				this.gestorDocumento.atualizarRepositorio(docAtual, this.documentoAnterior);
				break;
			}
		}
	}
}
