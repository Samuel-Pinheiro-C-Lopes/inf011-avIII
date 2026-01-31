package br.ifba.edu.inf011.command.implementations;

import br.ifba.edu.inf011.command.abstractions.BaseDocumentoCommand;
import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;
import br.ifba.edu.inf011.model.documentos.Documento;

// PARTICIPANTE: ConcreteCommand
public class SalvarDocumentoCommand extends BaseDocumentoCommand {
	private final String conteudo;
	private String conteudoAntigo;
	private Documento documentoAlterado;
	private final Documento documento;
	
	public SalvarDocumentoCommand(
			GerenciadorDocumentoModel gestorDocumento,
			String conteudo,
			Documento documento
	) {
		super(gestorDocumento);
		this.conteudo = conteudo;
		this.documento = documento;
	}

	@Override
	public void executeHook() throws Exception {
			this.conteudoAntigo = this.documento.getConteudo();
			this.gestorDocumento.salvarDocumento(documento, this.conteudo);
			this.documentoAlterado = documento;
	}

	@Override
	public void revertHook() throws Exception {
		if (this.documentoAlterado == null) return;
		
		this.gestorDocumento.salvarDocumento(documentoAlterado, this.conteudoAntigo);

	}

	@Override
	protected String getLogHook(Boolean isExecute) {
		// TODO Auto-generated method stub
		return null;
	}

}
