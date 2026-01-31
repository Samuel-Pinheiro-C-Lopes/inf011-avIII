package br.ifba.edu.inf011.command.implementations;

import br.ifba.edu.inf011.command.abstractions.IndirecaoBaseDocumentoCommand;
import br.ifba.edu.inf011.model.FWDocumentException;
import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;
import br.ifba.edu.inf011.model.documentos.Documento;

// PARTICIPANTE: ConcreteCommand
public class ProtegerDocumentoCommand extends IndirecaoBaseDocumentoCommand {

	private final Documento documento;
	public ProtegerDocumentoCommand(GerenciadorDocumentoModel gestorDocumento,Documento documento) {
		super(gestorDocumento);
		this.documento = documento;
	}

	@Override
	public void executeHook() throws FWDocumentException {
			this.documentoAnterior = this.documento;
			this.gestorDocumento.protegerDocumento(this.documento);
	}

	@Override
	protected String getLogHook(Boolean isExecute) {
		// TODO Auto-generated method stub
		return null;
	}

}
