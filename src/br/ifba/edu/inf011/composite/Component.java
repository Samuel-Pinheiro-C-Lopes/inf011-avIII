package br.ifba.edu.inf011.composite;

//Component em Composite
public interface Component {
	Component getChild();
	void addChild(Component child);
}
