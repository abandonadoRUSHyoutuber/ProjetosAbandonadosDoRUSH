package br.upf.ads.gestordebolsas.entity;

public enum DiaDaSemana implements Identifiable {

	SEGUNDA("Segunda"),
	TERCA("Terça"),
	QUARTA("Quarta"),
	QUINTA("Quinta"),
	SEXTA("Sexta"),
	SABADO("Sábado");
	
	private String descricao;

	DiaDaSemana(String descricao) {
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
