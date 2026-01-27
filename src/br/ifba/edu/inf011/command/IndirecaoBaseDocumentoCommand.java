package br.ifba.edu.inf011.command;

import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;
import br.ifba.edu.inf011.model.documentos.Documento;

public abstract class IndirecaoBaseDocumentoCommand extends BaseDocumentoCommand {
	protected Documento documentoAnterior;
	
	protected IndirecaoBaseDocumentoCommand(final GerenciadorDocumentoModel gestorDocumento) {
		super(gestorDocumento);
	}
	
	@Override
	public void revert() {
		if (this.documentoAnterior == null) return;
		
		this.gestorDocumento.atualizarRepositorio(documentoAnterior, documentoAnterior);
		
		/*
		 * opsie, não vi que gestor documento já tinha um método para update
		Documento documentoAtual = null;
		
		for (int idx = 0; idx < this.gestorDocumento.getRepositorio().size(); idx++) {
			documentoAtual = this.gestorDocumento.getRepositorio().get(idx);
			
			if (!this.documentoAnterior.getNumero().equals(documentoAtual.getNumero()))
				continue;
			
			documentoAnterior = null;
			this.gestorDocumento.getRepositorio().set(idx, this.documentoAnterior);
		}
		*/
	}
}
