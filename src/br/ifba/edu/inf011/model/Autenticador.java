package br.ifba.edu.inf011.model;

import br.ifba.edu.inf011.model.documentos.Documento;
import br.ifba.edu.inf011.strategy.AutenticadorStrategy;
import br.ifba.edu.inf011.strategy.PadraoAutenticadorStrategy;

public class Autenticador {
	private AutenticadorStrategy strategy;

	public Autenticador() {
		this.strategy = new PadraoAutenticadorStrategy();
	}
	
	public void autenticar(Integer tipo, Documento documento) {
		/*
		String numero;
		if(tipo == 0)
			numero = "CRI-" + LocalDate.now().getYear() + "-" + documento.hashCode();
		else if(tipo == 1)
			numero = "PES-" + LocalDate.now().getDayOfYear() + "-" + documento.getProprietario().hashCode();
		else if (tipo == 2) {
            if (documento.getPrivacidade() == Privacidade.SIGILOSO) {
                numero = "SECURE-" + documento.getNumero().hashCode();
            } else {
                numero = "PUB-" + documento.hashCode();
            }
        }else
			numero = "DOC-" + System.currentTimeMillis(); 
		*/
		documento.setNumero(this.strategy.generateNumber(documento));
	}

}
