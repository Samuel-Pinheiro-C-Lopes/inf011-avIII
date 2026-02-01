package br.ifba.edu.inf011.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.ifba.edu.inf011.af.DocumentOperatorFactory;
import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;
import br.ifba.edu.inf011.model.documentos.Documento;
import br.ifba.edu.inf011.strategy.AutenticadorStrategy;
import br.ifba.edu.inf011.strategy.DiaProprietarioHashAutenticadorStrategy;
import br.ifba.edu.inf011.strategy.HashYearAutenticadorStrategy;
import br.ifba.edu.inf011.strategy.PadraoAutenticadorStrategy;
import br.ifba.edu.inf011.strategy.PrivacidadeHashAutenticadorStrategy;

public abstract class AbstractGerenciadorDocumentosUI extends JFrame implements ListSelectionListener {

	protected GerenciadorDocumentoModel controller;
	protected JPanelBarraSuperior<AutenticadorStrategy> barraSuperior;
	protected JPanelAreaEdicao areaEdicao;
	protected JPanelListaDocumentos<String> barraDocs;

	protected AutenticadorStrategy[] tipos = { 
			new DiaProprietarioHashAutenticadorStrategy(),
			new HashYearAutenticadorStrategy(), 
			new PadraoAutenticadorStrategy(),
			new PrivacidadeHashAutenticadorStrategy() 
	};
	protected Documento atual;
	protected DefaultListModel<String> listDocs;

	public AbstractGerenciadorDocumentosUI(DocumentOperatorFactory factory) {
		this.controller = new GerenciadorDocumentoModel(factory);
		this.listDocs = new DefaultListModel<String>();
		this.barraSuperior = new JPanelBarraSuperior<AutenticadorStrategy>(tipos);
		this.areaEdicao = new JPanelAreaEdicao();
		this.barraDocs = new JPanelListaDocumentos<String>(this.listDocs, this);
		this.montarAparencia();
	}

	protected abstract JPanelOperacoes montarMenuOperacoes();

	public void montarAparencia() {
		// Configuração da Janela
		this.setTitle("Sistema de Gestão de Documentos - INF011");
		this.setSize(800, 500);

		JLabel subtitle = new JLabel("Logs located in: " + "logs.txt");
		subtitle.setHorizontalAlignment(SwingConstants.CENTER); 
		subtitle.setFont(new Font("Dialog", Font.ITALIC, 12)); 
		subtitle.setForeground(Color.GRAY); 
		subtitle.setBorder(new EmptyBorder(5, 10, 5, 10)); 

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		// Cria um painel para a barra superior e o subtitle
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.add(this.barraSuperior, BorderLayout.CENTER);
		topPanel.add(subtitle, BorderLayout.SOUTH);

		// Layout
		this.add(topPanel, BorderLayout.NORTH);
		this.add(this.areaEdicao, BorderLayout.CENTER);
		this.add(this.barraDocs, BorderLayout.WEST);
		this.add(this.montarMenuOperacoes(), BorderLayout.EAST);
	}

	protected void refreshUI() {
		try {
			if (this.atual != null) {
				this.areaEdicao.atualizar(this.atual.getConteudo());
			}
			
			this.listDocs.clear();
			this.listDocs.addAll(this.controller.getRepositorio().stream().map(d -> d.getNumero()).toList());
		} catch (Exception e) {
			this.areaEdicao.atualizar("");
			JOptionPane.showMessageDialog(this, "Erro ao Carregar : " + e.getMessage());
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			int index = this.barraDocs.getIndiceDocSelecionado();
			if (index != -1) {
				this.atual = controller.getRepositorio().get(index);
				this.refreshUI();
			}
		}
	}

}