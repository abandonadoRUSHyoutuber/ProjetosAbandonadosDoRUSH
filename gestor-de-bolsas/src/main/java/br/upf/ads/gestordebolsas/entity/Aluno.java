package br.upf.ads.gestordebolsas.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Aluno extends Pessoa implements Identifiable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "Você deve informar a unidade.")
	@ManyToOne(optional = false)
	private Curso curso;

	public Aluno() {
		super();
	}

	public Aluno(Long id, String matricula, String nome, String cpf, String rg, String email, String senha, String celular, String observacoes, Curso curso) {
		super(id, matricula, nome, cpf, rg, email, senha, celular, observacoes);
		this.curso = curso;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

}