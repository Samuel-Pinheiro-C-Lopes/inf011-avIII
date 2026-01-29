package br.ifba.edu.inf011.command;

import br.ifba.edu.inf011.model.FWDocumentException;
import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;
import br.ifba.edu.inf011.model.documentos.Documento;

// PARTICIPANTE: ConcreteCommand
public class TornarUrgenteDocumentoCommand extends IndirecaoBaseDocumentoCommand {
	private final Documento documento;
	public TornarUrgenteDocumentoCommand(GerenciadorDocumentoModel gestorDocumento, Documento documento) {
		super(gestorDocumento);
		this.documento = documento;
	}

	@Override
	public void execute() {
		try {
			this.documentoAnterior = this.documento;
			this.gestorDocumento.tornarUrgente(this.documento);
		} catch (FWDocumentException e) {
			e.printStackTrace();
		}
	}

}
