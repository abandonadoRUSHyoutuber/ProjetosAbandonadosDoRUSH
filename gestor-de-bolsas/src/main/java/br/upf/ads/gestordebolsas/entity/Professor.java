package br.upf.ads.gestordebolsas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.upf.ads.gestordebolsas.annotation.EnumValidate;

@Entity
public class Professor extends Pessoa implements Identifiable {

	private static final long serialVersionUID = 1L;

	@EnumValidate(enumClass = Titulacao.class, message = "Você deve informar uma titulação válida.")
	@NotBlank(message = "Você deve informar a titulação do professor.")
	@Column(nullable = false, unique = false, length = 20)
	private String titulacao;

	@NotNull(message = "Você deve informar a unidade.")
	@ManyToOne(optional = false)
	private Unidade unidade;

	public Professor() {
		super();
	}

	public Professor(Long id, String matricula, String nome, String cpf, String rg, String email, String senha, String celular, String observacoes, String titulacao, Unidade unidade) {
		super(id, matricula, nome, cpf, rg, email, senha, celular, observacoes);
		this.titulacao = titulacao;
		this.unidade = unidade;
	}

	public String getTitulacao() {
		return this.titulacao;
	}

	public void setTitulacao(String titulacao) {
		this.titulacao = titulacao;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

}
