package br.ifba.edu.inf011.command;

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
	public void execute() {
		try {
			this.conteudoAntigo = this.documento.getConteudo();
			this.gestorDocumento.salvarDocumento(documento, this.conteudo);
			this.documentoAlterado = documento;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void revert() {
		if (this.documentoAlterado == null) return;
		
		try {
			this.gestorDocumento.salvarDocumento(documentoAlterado, this.conteudoAntigo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
