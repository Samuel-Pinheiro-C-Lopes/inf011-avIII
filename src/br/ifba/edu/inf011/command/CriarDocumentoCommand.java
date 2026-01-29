package br.ifba.edu.inf011.command;

import br.ifba.edu.inf011.model.FWDocumentException;
import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;
import br.ifba.edu.inf011.model.documentos.Documento;
import br.ifba.edu.inf011.model.documentos.Privacidade;
import br.ifba.edu.inf011.strategy.AutenticadorStrategy;

// PARTICIPANTE: ConcreteCommand
public class CriarDocumentoCommand extends BaseDocumentoCommand {
	private final Documento documento;
	private final Privacidade privacidade;
	private String numeroDocumento;
	private final AutenticadorStrategy strategy;
	
	
	public CriarDocumentoCommand(
		final Documento documento,
		final GerenciadorDocumentoModel gestorDocumento,
		final AutenticadorStrategy strategy,
		Privacidade privacidade
	) {
		super(gestorDocumento);
		this.documento = documento;
		this.privacidade = privacidade;
		this.strategy = strategy;
		this.numeroDocumento = null;
	}
	
	@Override
	public void execute() {
		try {
			this.numeroDocumento = this.gestorDocumento.criarDocumento(this.strategy, this.privacidade).getNumero();
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
