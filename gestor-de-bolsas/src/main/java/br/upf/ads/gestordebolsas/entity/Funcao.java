package br.upf.ads.gestordebolsas.entity;

public enum Funcao implements Identifiable {

	COORDENADOR("Coodernador"), 
	COLABORADOR("Colaborador");

	private String descricao;

	Funcao(String descricao) {
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
