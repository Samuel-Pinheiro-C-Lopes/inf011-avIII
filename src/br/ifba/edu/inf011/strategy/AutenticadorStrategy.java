package br.ifba.edu.inf011.strategy;

import br.ifba.edu.inf011.model.documentos.Documento;

// PARTICIPANT: STRATEGY
public interface AutenticadorStrategy {
	String generateNumber(Documento documento);
}
