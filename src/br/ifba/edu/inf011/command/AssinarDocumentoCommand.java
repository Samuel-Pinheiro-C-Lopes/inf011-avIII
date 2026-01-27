package br.ifba.edu.inf011.command;

import br.ifba.edu.inf011.model.FWDocumentException;
import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;
import br.ifba.edu.inf011.model.documentos.Documento;

// PARTICIPANTE: ConcreteCommand
public class AssinarDocumentoCommand extends IndirecaoBaseDocumentoCommand {
	
	public AssinarDocumentoCommand(final GerenciadorDocumentoModel gestorDocumento) {
		super(gestorDocumento);
	}
	
	@Override
	public void execute(final Documento documento) {
		try {
			this.documentoAnterior = documento;
			this.gestorDocumento.assinarDocumento(documento);
		} catch (FWDocumentException e) {
			e.printStackTrace();
		}
	}
}
