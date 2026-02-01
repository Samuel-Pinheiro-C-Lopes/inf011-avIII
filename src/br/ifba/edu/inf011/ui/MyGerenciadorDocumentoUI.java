package br.ifba.edu.inf011.ui;

import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.JOptionPane;

import br.ifba.edu.inf011.af.DocumentOperatorFactory;
import br.ifba.edu.inf011.command.DocumentoCommandManager;
import br.ifba.edu.inf011.command.implementations.AssinarDocumentoCommand;
import br.ifba.edu.inf011.command.implementations.CriarDocumentoCommand;
import br.ifba.edu.inf011.command.implementations.ProtegerDocumentoCommand;
import br.ifba.edu.inf011.command.implementations.SalvarDocumentoCommand;
import br.ifba.edu.inf011.command.implementations.TornarUrgenteDocumentoCommand;
import br.ifba.edu.inf011.model.documentos.Privacidade;
import br.ifba.edu.inf011.strategy.AutenticadorStrategy;

public class MyGerenciadorDocumentoUI extends AbstractGerenciadorDocumentosUI{
	private final DocumentoCommandManager commandManager = new DocumentoCommandManager();
	
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
		comandos.addOperacao("↩️ Desfazer", e->this.desfazer());
		comandos.addOperacao("↪️ Refazer", e->this.refazer());
		comandos.addOperacao("💾✍️ Alterar e Assinar", e->this.alterarEAssinar());
		comandos.addOperacao("💾⏰ Priorizar", e->this.priorizar());
		comandos.addOperacao("✅ Consolidar", e->this.consolidar());
		return comandos;
	 }
	
	protected void consolidar() {
		this.commandManager.clear();
		JOptionPane.showMessageDialog(this, "Mudanças consolidadas!");
	}

	protected void alterarEAssinar() {
		try {
			this.commandManager.execute(
				new SalvarDocumentoCommand(
						controller, 
						this.areaEdicao.getConteudo(), 
						this.atual
				).addSubCommand(
					new AssinarDocumentoCommand(controller, this.atual)
				)
			);
			
			this.atual = controller.getDocumentoAtual();
			this.refreshUI();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erro ao Salvar: " + e.getMessage());
		}
	}
	
	protected void priorizar() {
		try {
			this.commandManager.execute(
				new SalvarDocumentoCommand(
						controller, 
						this.areaEdicao.getConteudo(), 
						this.atual
				).addSubCommand(
					new TornarUrgenteDocumentoCommand(controller, this.atual)
				)
			);
			
			this.atual = controller.getDocumentoAtual();
			this.refreshUI();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erro ao Salvar: " + e.getMessage());
		}
	}
	
	protected void criarDocumentoPublico() {
		this.criarDocumento(Privacidade.PUBLICO);
	}
	
	protected void criarDocumentoPrivado() {
		this.criarDocumento(Privacidade.SIGILOSO);
	}
	
	protected void salvarConteudo() {
        try {
        	if (this.atual == null) {
        		JOptionPane.showMessageDialog(this, "Nenhum documento selecionado!");
        		return;
        	}
       
        	this.commandManager.execute(new SalvarDocumentoCommand(controller, this.areaEdicao.getConteudo(), this.atual));
			
        	JOptionPane.showMessageDialog(this, "Documento salvo com sucesso!");
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
			
			this.commandManager.execute(new ProtegerDocumentoCommand(controller, this.atual));

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
			
			this.commandManager.execute(new AssinarDocumentoCommand(controller, this.atual));
			
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
			
			this.commandManager.execute(new TornarUrgenteDocumentoCommand(controller, this.atual));

			this.atual = controller.getDocumentoAtual();
			this.refreshUI();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erro ao tornar urgente: " + e.getMessage());
		}		
	}	

	private void criarDocumento(Privacidade privacidade) {
		try {
			AutenticadorStrategy strategy = this.barraSuperior.getSelected();
			
			this.commandManager.execute(new CriarDocumentoCommand(controller, strategy, privacidade));
			
			if (!controller.getRepositorio().isEmpty()) {
				this.atual = controller.getRepositorio().get(controller.getRepositorio().size() - 1);
				this.barraDocs.addDoc("[" + atual.getNumero() + "]");
				this.refreshUI();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erro ao criar documento: " + e.getMessage());
		}
    }
	
	private void desfazer() {
		try {
			this.commandManager.undo();
			
			this.atual = controller.getDocumentoAtual();
			this.refreshUI();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erro ao desfazer: " + e.getMessage());
		}
	}
	
	private void refazer() {
		try {
			this.commandManager.redo();
			
			this.atual = controller.getDocumentoAtual();
			this.refreshUI();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erro ao refazer: " + e.getMessage());
		}
	}
	
}
