package br.ifba.edu.inf011.command;

import br.ifba.edu.inf011.model.FWDocumentException;
import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;
import br.ifba.edu.inf011.model.documentos.Documento;
import br.ifba.edu.inf011.model.documentos.Privacidade;

// PARTICIPANTE: ConcreteCommand
public class CriarDocumentoCommand extends BaseDocumentoCommand {
	private final int tipoAutenticadorIndex;
	private final Privacidade privacidade;
	private String numeroDocumento;
	
	
	public CriarDocumentoCommand(
		final GerenciadorDocumentoModel gestorDocumento,
		int tipoAutenticadorIndex,
		Privacidade privacidade
	) {
		super(gestorDocumento);
		this.tipoAutenticadorIndex = tipoAutenticadorIndex;
		this.privacidade = privacidade;
		this.numeroDocumento = null;
	}
	
	@Override
	public void execute(Documento documento) {
		try {
			this.numeroDocumento = this.gestorDocumento.criarDocumento(
				this.tipoAutenticadorIndex, 
				this.privacidade
			).getNumero();
		} catch (FWDocumentException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void revert() {
		if (this.numeroDocumento == null) return;
		
		this.gestorDocumento.getRepositorio().removeIf(d -> this.numeroDocumento.equals(d.getNumero()));
	}

}
