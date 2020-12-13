package br.upf.ads.gestordebolsas.entity;

public enum Turno implements Identifiable {

	MANHA("Manhã"), 
	TARDE("Tarde"), 
	NOITE("Noite");

	private String descricao;

	Turno(String descricao) {
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
