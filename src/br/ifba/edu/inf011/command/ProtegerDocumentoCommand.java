package br.ifba.edu.inf011.command;

import br.ifba.edu.inf011.model.FWDocumentException;
import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;
import br.ifba.edu.inf011.model.documentos.Documento;

// PARTICIPANTE: ConcreteCommand
public class ProtegerDocumentoCommand extends IndirecaoBaseDocumentoCommand {

	protected ProtegerDocumentoCommand(GerenciadorDocumentoModel gestorDocumento) {
		super(gestorDocumento);
	}

	@Override
	public void execute(Documento documento) {
		try {
			this.gestorDocumento.protegerDocumento(documento);
		} catch (FWDocumentException e) {
			e.printStackTrace();
		}
	}

}
