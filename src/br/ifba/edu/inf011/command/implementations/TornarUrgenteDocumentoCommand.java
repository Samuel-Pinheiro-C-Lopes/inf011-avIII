package br.ifba.edu.inf011.command.implementations;

import br.ifba.edu.inf011.command.abstractions.IndirectionBaseDocumentoCommand;
import br.ifba.edu.inf011.model.FWDocumentException;
import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;
import br.ifba.edu.inf011.model.documentos.Documento;

// PARTICIPANTE: ConcreteCommand
public class TornarUrgenteDocumentoCommand extends IndirectionBaseDocumentoCommand {
	
	public TornarUrgenteDocumentoCommand(
			GerenciadorDocumentoModel gestorDocumento, 
			Documento documento
	) {
		super(gestorDocumento);
		this.documentoAnterior = documento;
	}

	@Override
	public void executeHook() throws FWDocumentException {
		if (this.numeroDocumentoAnterior != null) 
			documentoAnterior = this.getDocumentoByNumero(numeroDocumentoAnterior);
		else 
			this.numeroDocumentoAnterior = documentoAnterior.getNumero();
		
		this.gestorDocumento.tornarUrgente(documentoAnterior);
	}

	@Override
	protected String getLogHook() {
		return "Tornando urgente <" + this.documentoAnterior.getNumero() + ">";
	}
}
