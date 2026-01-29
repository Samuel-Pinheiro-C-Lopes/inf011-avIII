package br.ifba.edu.inf011.ui;

import javax.swing.JOptionPane;

import br.ifba.edu.inf011.af.DocumentOperatorFactory;
import br.ifba.edu.inf011.command.AssinarDocumentoCommand;
import br.ifba.edu.inf011.command.CriarDocumentoCommand;
import br.ifba.edu.inf011.command.DocumentoCommand;
import br.ifba.edu.inf011.command.ProtegerDocumentoCommand;
import br.ifba.edu.inf011.command.SalvarDocumentoCommand;
import br.ifba.edu.inf011.command.TornarUrgenteDocumentoCommand;
import br.ifba.edu.inf011.model.FWDocumentException;
import br.ifba.edu.inf011.model.documentos.Privacidade;
import br.ifba.edu.inf011.strategy.AutenticadorStrategy;

public class MyGerenciadorDocumentoUI extends AbstractGerenciadorDocumentosUI{
	private DocumentoCommand documentCommand;
	
	 public MyGerenciadorDocumentoUI(DocumentOperatorFactory factory) {
		super(factory);
	}

	protected JPanelOperacoes montarMenuOperacoes() {
		JPanelOperacoes comandos = new JPanelOperacoes();
		comandos.addOperacao("➕ Criar Publico", e -> this.criarDocumentoPublico());
		comandos.addOperacao("➕ Criar Privado", e -> this.criarDocumentoPrivado());
		comandos.addOperacao("💾 Salvar", e-> this.salvarConteudo());
		comandos.addOperacao("🔑 Proteger", e->this.protegerDocumento());
		comandos.addOperacao("✍️ Assinar", e->this.assinarDocumento());
		comandos.addOperacao("⏰ Urgente", e->this.tornarUrgente());
		comandos.addOperacao("🔄 Desfazer", e->this.desfazer());
		comandos.addOperacao("🔄 Refazer", e->this.refazer());
		return comandos;
	 }
	
	protected void criarDocumentoPublico() {
		this.criarDocumento(Privacidade.PUBLICO);
		//this.criarDocumento(Privacidade.PUBLICO);
	}
	
	protected void criarDocumentoPrivado() {
		this.criarDocumento(Privacidade.SIGILOSO);
		//this.criarDocumento(Privacidade.SIGILOSO);
	}
	
	protected void salvarConteudo() {
        try {
        	this.documentCommand = this.documentCommand.addChild(new SalvarDocumentoCommand(controller, this.areaEdicao.getConteudo(), this.atual));
            //this.controller.salvarDocumento(this.atual, this.areaEdicao.getConteudo());
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(this, "Erro ao Salvar: " + e.getMessage());
        }
    }	
	
	protected void protegerDocumento() {
		//try {
		this.documentCommand = this.documentCommand.addChild(new ProtegerDocumentoCommand(controller, this.atual));
			//this.controller.protegerDocumento(this.atual);
		this.refreshUI();
		//} catch (FWDocumentException e) {
		//	JOptionPane.showMessageDialog(this, "Erro ao proteger: " + e.getMessage());
		//}
	}

	protected void assinarDocumento() {
		//try {
			// this.controller.assinarDocumento(this.atual);
			this.documentCommand = this.documentCommand.addChild(new AssinarDocumentoCommand(controller, this.atual));
			this.refreshUI();
		//} catch (FWDocumentException e) {
		//	JOptionPane.showMessageDialog(this, "Erro ao assinar: " + e.getMessage());
		//}		
	}
	
	protected void tornarUrgente() {
		//try {
			//this.controller.tornarUrgente(this.atual);
			this.documentCommand = this.documentCommand.addChild(new TornarUrgenteDocumentoCommand(controller, this.atual));
			this.refreshUI();
		//} catch (FWDocumentException e) {
		//	JOptionPane.showMessageDialog(this, "Erro ao tornar urgente: " + e.getMessage());
		//}		
	}	

	
	private void criarDocumento(Privacidade privacidade) {
		AutenticadorStrategy strategy = this.barraSuperior.getSelected();
		this.documentCommand = this.documentCommand.addChild(new CriarDocumentoCommand(this.atual, controller, strategy, privacidade));
		this.barraDocs.addDoc("[" + atual.getNumero() + "]");
		this.refreshUI();
        //try {
        //    AutenticadorStrategy strategy = this.barraSuperior.getSelected();
         //   this.atual = this.controller.criarDocumento(strategy, privacidade);
         //   this.barraDocs.addDoc("[" + atual.getNumero() + "]");
        //    this.refreshUI();
       // } catch (FWDocumentException e) {
        //    JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
      //  }
    }
	
	private void desfazer() {
		this.documentCommand.revert();
		this.documentCommand = this.documentCommand.getParent();
	}
	
	private void refazer() {
		this.documentCommand.execute();
		this.documentCommand = this.documentCommand.getChild();
	}
	
}
