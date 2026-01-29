package br.ifba.edu.inf011.command;

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
	public void execute() {
		try {
			this.gestorDocumento.protegerDocumento(this.documento);
		} catch (FWDocumentException e) {
			e.printStackTrace();
		}
	}

}
