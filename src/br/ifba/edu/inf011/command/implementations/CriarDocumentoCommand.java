package br.ifba.edu.inf011.command.implementations;

import java.util.List;

import br.ifba.edu.inf011.command.abstractions.BaseDocumentoCommand;
import br.ifba.edu.inf011.model.FWDocumentException;
import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;
import br.ifba.edu.inf011.model.documentos.Documento;
import br.ifba.edu.inf011.model.documentos.Privacidade;
import br.ifba.edu.inf011.strategy.AutenticadorStrategy;

// PARTICIPANTE: ConcreteCommand
public class CriarDocumentoCommand extends BaseDocumentoCommand {
	private final Privacidade privacidade;
	private Documento documentoCriado;
	private final AutenticadorStrategy strategy;
	
	public CriarDocumentoCommand(
		final GerenciadorDocumentoModel gestorDocumento,
		final AutenticadorStrategy strategy,
		Privacidade privacidade
	) {
		super(gestorDocumento);
		this.privacidade = privacidade;
		this.strategy = strategy;
		this.documentoCriado = null;
	}
	
	@Override
	public void executeHook() throws FWDocumentException {
			if (documentoCriado != null) {
				this.gestorDocumento.getRepositorio().add(documentoCriado);
				this.gestorDocumento.setDocumentoAtual(documentoCriado);
				return;
			}
			
			final String numeroDocumento = this.gestorDocumento.criarDocumento(this.strategy, this.privacidade).getNumero();
			this.documentoCriado = this.gestorDocumento.getRepositorio().stream().filter(d -> d.getNumero().equals(numeroDocumento)).findFirst().orElse(null);
	}

	@Override
	public void revertHook() {
		if (this.documentoCriado == null) 
			return;
		
		this.gestorDocumento.getRepositorio().removeIf(d -> this.documentoCriado.getNumero().equals(d.getNumero()));
		this.gestorDocumento.setDocumentoAtual(null);
	}

	@Override
	protected String getLogHook() {
		return "<" + "Criando documento " + this.strategy.toString() + ">";
	}

}
