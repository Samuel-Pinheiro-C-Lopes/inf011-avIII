package br.ifba.edu.inf011.command.implementations;

import br.ifba.edu.inf011.command.abstractions.BaseDocumentoCommand;
import br.ifba.edu.inf011.model.FWDocumentException;
import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;
import br.ifba.edu.inf011.model.documentos.Privacidade;
import br.ifba.edu.inf011.strategy.AutenticadorStrategy;

// PARTICIPANTE: ConcreteCommand
public class CriarDocumentoCommand extends BaseDocumentoCommand {
	private final Privacidade privacidade;
	private String numeroDocumento;
	private final AutenticadorStrategy strategy;
	
	public CriarDocumentoCommand(
		final GerenciadorDocumentoModel gestorDocumento,
		final AutenticadorStrategy strategy,
		Privacidade privacidade
	) {
		super(gestorDocumento);
		this.privacidade = privacidade;
		this.strategy = strategy;
		this.numeroDocumento = null;
	}
	
	@Override
	public void executeHook() throws FWDocumentException {
			this.numeroDocumento = this.gestorDocumento.criarDocumento(this.strategy, this.privacidade).getNumero();
	}

	@Override
	public void revertHook() {
		if (this.numeroDocumento == null) return;
		
		this.gestorDocumento.getRepositorio().removeIf(d -> this.numeroDocumento.equals(d.getNumero()));
	}

	@Override
	protected String getLogHook(Boolean isExecute) {
		// TODO Auto-generated method stub
		return null;
	}

}
