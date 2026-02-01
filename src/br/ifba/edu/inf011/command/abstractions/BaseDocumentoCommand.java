package br.ifba.edu.inf011.command.abstractions;

import br.ifba.edu.inf011.command.interfaces.DocumentoCommand;
import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;
import br.ifba.edu.inf011.model.documentos.Documento;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Instant;

// PARTICIPANT: AbstractClass (Template Method) e Composite (Composite)
public abstract class BaseDocumentoCommand implements DocumentoCommand {
	protected final GerenciadorDocumentoModel gestorDocumento;
	private DocumentoCommand child = null;
	private DocumentoCommand parent = null;
	
	protected BaseDocumentoCommand(final GerenciadorDocumentoModel gestorDocumento) {
		this.gestorDocumento = gestorDocumento;
	}
	
	// Template Method execute
	@Override
	public final void execute() throws Exception {
		this.executeHook();
		this.registerLog(getLogHook(), Boolean.TRUE);
	}	
	
	// Template Method revert
	@Override
	public final void undo() throws Exception {
		this.revertHook();
		this.registerLog(getLogHook(), Boolean.FALSE);
	}
	
	@Override
	public DocumentoCommand getChild() {
		return this.child;
	}
	
	@Override
	public DocumentoCommand addChild(DocumentoCommand command) {
		this.child = command;
		this.child.setParent(this);
		return this.child;
	}
	
	@Override
	public DocumentoCommand getParent() {
		return this.parent;
	}
	
	@Override
	public void setParent(DocumentoCommand parent) {
		this.parent = parent;
	}
	
	// registering log
	private void registerLog(String log, Boolean isExecuting) {
		final String operacao = "[" + (isExecuting ? "EXECUTANDO" : "REVERTENDO") + "] ";
		final String timeStamp = " [" + Instant.now() + "]";
		
		try {			
			Files.writeString(
				Path.of("logs.txt"), 
				operacao + log + timeStamp + "\n", 
				StandardOpenOption.CREATE, 
				StandardOpenOption.APPEND
			);
		} catch (IOException ex) {
			
		}
		
	}
	
	// Mandatory Primitive Operations
	protected abstract void executeHook() throws Exception;
	protected abstract void revertHook() throws Exception;
	protected abstract String getLogHook();
	
	// helper
	protected Documento getDocumentoByNumero(final String numero ) {
		return this.gestorDocumento.getRepositorio().stream().filter(d -> d.getNumero().equals(numero)).findFirst().orElse(null);
	}
}
