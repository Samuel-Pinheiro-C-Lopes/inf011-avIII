package br.ifba.edu.inf011.strategy;

import java.time.LocalDate;

import br.ifba.edu.inf011.model.documentos.Documento;

//PARTICIPANTE: Concrete Strategy.
//Substitui a condicional para tipo 1.
public class DiaProprietarioHashAutenticadorStrategy implements AutenticadorStrategy {
	@Override
	public String generateNumber(Documento documento) {
		return "PES-" + LocalDate.now().getDayOfYear() + "-" + documento.getProprietario().hashCode();
	}
}
