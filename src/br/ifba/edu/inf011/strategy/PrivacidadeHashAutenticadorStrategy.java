package br.ifba.edu.inf011.strategy;

import java.time.LocalDateTime;

import br.ifba.edu.inf011.model.documentos.Documento;
import br.ifba.edu.inf011.model.documentos.Privacidade;

//PARTICIPANTE: Concrete Strategy.
//Substitui a condicional para tipo 2.
public class PrivacidadeHashAutenticadorStrategy implements AutenticadorStrategy {
	@Override
	public String generateNumber(Documento documento) {
        if (documento.getPrivacidade() == Privacidade.SIGILOSO) 
            return "SECURE-" + documento.hashCode() + LocalDateTime.now().hashCode();
        
        return "PUB-" + documento.hashCode();
	}

	@Override
	public String toString() {
		return "Exportação";
	}
}
