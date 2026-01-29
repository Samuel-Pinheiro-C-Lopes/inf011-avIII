package br.ifba.edu.inf011.command;

import br.ifba.edu.inf011.model.FWDocumentException;
import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;
import br.ifba.edu.inf011.model.documentos.Documento;

// PARTICIPANTE: ConcreteCommand
public class AssinarDocumentoCommand extends IndirecaoBaseDocumentoCommand {
	private final Documento documento;
	
	public AssinarDocumentoCommand(final GerenciadorDocumentoModel gestorDocumento, final Documento documento) {
		super(gestorDocumento);
		this.documento = documento;
	}
	
	@Override
	public void execute() {
		try {
			this.documentoAnterior = this.documento;
			this.gestorDocumento.assinarDocumento(documento);
		} catch (FWDocumentException e) {
			e.printStackTrace();
		}
	}
}
