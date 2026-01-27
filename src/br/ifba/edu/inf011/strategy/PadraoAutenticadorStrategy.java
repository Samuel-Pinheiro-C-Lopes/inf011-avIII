package br.ifba.edu.inf011.strategy;

import br.ifba.edu.inf011.model.documentos.Documento;

//PARTICIPANTE: Concrete Strategy.
//Substitui a condicional genérica (o else).
public class PadraoAutenticadorStrategy implements AutenticadorStrategy {
	@Override
	public String generateNumber(Documento documento) {
		return "DOC-" + System.currentTimeMillis();
	}
}
