package br.ifba.edu.inf011.command;

import br.ifba.edu.inf011.model.FWDocumentException;
import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;
import br.ifba.edu.inf011.model.documentos.Documento;

// PARTICIPANTE: ConcreteCommand
public class TornarUrgenteDocumentoCommand extends IndirecaoBaseDocumentoCommand {
	
	protected TornarUrgenteDocumentoCommand(GerenciadorDocumentoModel gestorDocumento) {
		super(gestorDocumento);
	}

	@Override
	public void execute(Documento documento) {
		try {
			this.documentoAnterior = documento;
			this.gestorDocumento.tornarUrgente(documento);
		} catch (FWDocumentException e) {
			e.printStackTrace();
		}
	}

}
