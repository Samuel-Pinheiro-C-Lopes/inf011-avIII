package br.ifba.edu.inf011.ui;

import javax.swing.JOptionPane;

import br.ifba.edu.inf011.af.DocumentOperatorFactory;
import br.ifba.edu.inf011.command.AssinarDocumentoCommand;
import br.ifba.edu.inf011.command.CriarDocumentoCommand;
import br.ifba.edu.inf011.command.DocumentoCommand;
import br.ifba.edu.inf011.command.DocumentoCommandManager;
import br.ifba.edu.inf011.command.ProtegerDocumentoCommand;
import br.ifba.edu.inf011.command.SalvarDocumentoCommand;
import br.ifba.edu.inf011.command.TornarUrgenteDocumentoCommand;
import br.ifba.edu.inf011.model.documentos.Privacidade;
import br.ifba.edu.inf011.strategy.AutenticadorStrategy;

public class MyGerenciadorDocumentoUI extends AbstractGerenciadorDocumentosUI{
	private DocumentoCommand documentCommand;
	private DocumentoCommandManager commandManager = new DocumentoCommandManager();
	
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
        	if (this.atual == null) {
        		JOptionPane.showMessageDialog(this, "Nenhum documento selecionado!");
        		return;
        	}
        	SalvarDocumentoCommand cmd = new SalvarDocumentoCommand(controller, this.areaEdicao.getConteudo(), this.atual);
        	if (this.documentCommand == null) {
        		this.documentCommand = cmd;
        	} else {
        		this.documentCommand = this.documentCommand.addChild(cmd);
        	}
        	cmd.execute();
			JOptionPane.showMessageDialog(this, "Documento salvo com sucesso!");
            //this.controller.salvarDocumento(this.atual, this.areaEdicao.getConteudo());
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(this, "Erro ao Salvar: " + e.getMessage());
        }
    }	
	
	protected void protegerDocumento() {
		try {
			if (this.atual == null) {
				JOptionPane.showMessageDialog(this, "Nenhum documento selecionado!");
				return;
			}
			ProtegerDocumentoCommand cmd = new ProtegerDocumentoCommand(controller, this.atual);
			if (this.documentCommand == null) {
				this.documentCommand = cmd;
			} else {
				this.documentCommand = this.documentCommand.addChild(cmd);
			}
			cmd.execute();
			// Atualiza o documento atual para o novo documento protegido
			this.atual = controller.getDocumentoAtual();
			this.refreshUI();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erro ao proteger: " + e.getMessage());
		}
	}

	protected void assinarDocumento() {
		try {
			if (this.atual == null) {
				JOptionPane.showMessageDialog(this, "Nenhum documento selecionado!");
				return;
			}
			AssinarDocumentoCommand cmd = new AssinarDocumentoCommand(controller, this.atual);
			if (this.documentCommand == null) {
				this.documentCommand = cmd;
			} else {
				this.documentCommand = this.documentCommand.addChild(cmd);
			}
			cmd.execute();
			// Atualiza o documento atual para o novo documento assinado
			this.atual = controller.getDocumentoAtual();
			this.refreshUI();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erro ao assinar: " + e.getMessage());
		}		
	}
	
	protected void tornarUrgente() {
		try {
			if (this.atual == null) {
				JOptionPane.showMessageDialog(this, "Nenhum documento selecionado!");
				return;
			}
			TornarUrgenteDocumentoCommand cmd = new TornarUrgenteDocumentoCommand(controller, this.atual);
			if (this.documentCommand == null) {
				this.documentCommand = cmd;
			} else {
				this.documentCommand = this.documentCommand.addChild(cmd);
			}
			cmd.execute();
			// Atualiza o documento atual para o novo documento urgente
			this.atual = controller.getDocumentoAtual();
			this.refreshUI();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erro ao tornar urgente: " + e.getMessage());
		}		
	}	

	// classe abstraata command que já temn isso pronto, eu acho. professor mesm,o sugeriu isso
	private void criarDocumento(Privacidade privacidade) {
		try {
			AutenticadorStrategy strategy = this.barraSuperior.getSelected();
			
			CriarDocumentoCommand cmd = new CriarDocumentoCommand(this.atual, controller, strategy, privacidade);
			commandManager .executeCommand(cmd);
			if (!controller.getRepositorio().isEmpty()) {
				this.atual = controller.getRepositorio().get(controller.getRepositorio().size() - 1);
				this.barraDocs.addDoc("[" + atual.getNumero() + "]");
				this.refreshUI();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erro ao criar documento: " + e.getMessage());
		}
        //try {
        //    AutenticadorStrategy strategy = this.barraSuperior.getSelected();
         //   this.atual = this.controller.criarDocumento(strategy, privacidade);
         //   this.barraDocs.addDoc("[" + atual.getNumero() + "]");
        //    this.refreshUI();
       // } catch (FWDocumentException e) {
        //    JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
      //  }
    }
	
	
	private void mostrarLogs() {
		
	}
	
	private void desfazer() {

	}
	
	private void refazer() {
		if (this.documentCommand != null && this.documentCommand.getChild() != null) {
			this.documentCommand = this.documentCommand.getChild();
			this.documentCommand.execute();
			this.refreshUI();
		}
	}
	
}
