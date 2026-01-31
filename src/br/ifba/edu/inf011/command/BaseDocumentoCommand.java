package br.ifba.edu.inf011.command;

import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;

public abstract class BaseDocumentoCommand implements DocumentoCommand {
	protected final GerenciadorDocumentoModel gestorDocumento;
	private DocumentoCommand child = null;
	private DocumentoCommand parent = null;
	
	protected BaseDocumentoCommand(final GerenciadorDocumentoModel gestorDocumento) {
		this.gestorDocumento = gestorDocumento;
	}
	
	@Override
	public DocumentoCommand getChild() {
		return this.child;
	}
	
	@Override
	public String getLog(Boolean isExecute) {
		String commandName = this.toString();
		if(!isExecute) {
			return "Revertendo " + commandName;
		}
		return "Executando " + commandName;
	}
	
	@Override
	public DocumentoCommand addChild(DocumentoCommand command) {
		this.child = command;
		this.child.setParent(this);
		return this.child;
	}
	
	@Override
	public DocumentoCommand getParent() {
		return  this.parent;
	}
	
	@Override
	public void setParent(DocumentoCommand parent) {
		this.parent = parent;
	}
}
