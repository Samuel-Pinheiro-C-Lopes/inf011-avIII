package br.ifba.edu.inf011.strategy;

import java.time.LocalDate;

import br.ifba.edu.inf011.model.documentos.Documento;

// PARTICIPANTE: Concrete Strategy.
// Substitui a condicional para tipo 0.
public class HashYearAutenticadorStrategy implements AutenticadorStrategy {
	@Override
	public String generateNumber(Documento documento) {
		return "CRI-" + LocalDate.now().getYear() + "-" + documento.hashCode();
	}
	
	@Override
	public String toString() {
		return "Criminal";
	}
}
