package br.upf.ads.gestordebolsas.entity;

import javax.persistence.Entity;

@Entity
public class Administrador extends Pessoa implements Identifiable {

	private static final long serialVersionUID = 1L;

	public Administrador() {
		super();
	}

	public Administrador(Long id, String matricula, String nome, String cpf, String rg, String email, String senha,	String celular, String observacoes) {
		super(id, matricula, nome, cpf, rg, email, senha, celular, observacoes);
	}

}
