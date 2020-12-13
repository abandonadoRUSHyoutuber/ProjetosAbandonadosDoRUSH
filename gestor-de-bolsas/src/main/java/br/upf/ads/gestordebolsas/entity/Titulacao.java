package br.upf.ads.gestordebolsas.entity;

public enum Titulacao implements Identifiable {

	ESPECIALISTA("Especialista"), 
	MESTRE("Mestre"), 
	DOUTOR("Doutor"), 
	POS_DOUTOR("Pós-Doutor");

	private String descricao;

	Titulacao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return this.descricao;
	}

	@Override
	public Long getId() {
		return Long.valueOf(this.ordinal());
	}

}
