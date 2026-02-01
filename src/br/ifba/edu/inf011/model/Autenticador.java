package br.ifba.edu.inf011.model;

import br.ifba.edu.inf011.model.documentos.Documento;
import br.ifba.edu.inf011.strategy.AutenticadorStrategy;
import br.ifba.edu.inf011.strategy.PadraoAutenticadorStrategy;

// PARTICIPANT: Context (Strategy)
public class Autenticador {
	private AutenticadorStrategy strategy;

	public Autenticador() {
		this.strategy = new PadraoAutenticadorStrategy();
	}
	
	public void autenticar(AutenticadorStrategy strategy, Documento documento) {
		this.strategy = strategy;
		
		this.autenticar(documento);
	}
	
	public void autenticar(Documento documento) {
		documento.setNumero(this.strategy.generateNumber(documento));
	}

}
