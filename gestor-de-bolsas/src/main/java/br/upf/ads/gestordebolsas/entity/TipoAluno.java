package br.upf.ads.gestordebolsas.entity;

public enum TipoAluno implements Identifiable {

	BOLSISTA("B"), 
	VOLUNTARIO("V");

	private String descricao;

	TipoAluno(String descricao) {
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
