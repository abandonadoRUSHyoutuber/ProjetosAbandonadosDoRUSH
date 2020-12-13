package br.upf.ads.gestordebolsas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Projeto implements Identifiable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ProjetoID")
	@SequenceGenerator(name = "ProjetoID", sequenceName = "ProjetoID", allocationSize = 1)
	private Long id;

	@Size(min = 2, max = 60, message = "O nome do projeto deve ter entre {min} e {max} caracteres.")
	@NotBlank(message = "Você deve informar o nome do projeto.")
	@Column(nullable = false, unique = false, length = 60)
	private String nome;

	@NotNull(message = "Você deve informar a unidade do projeto.")
	@ManyToOne(optional = false)
	private Unidade unidade;

	@NotNull(message = "Você deve informar o professor responsável pelo projeto.")
	@ManyToOne(optional = false)
	private Professor professor;

	public Projeto() {
		super();
	}

	public Projeto(Long id, String nome, Unidade unidade, Professor professor) {
		super();
		this.id = id;
		this.nome = nome;
		this.unidade = unidade;
		this.professor = professor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Projeto other = (Projeto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nome;
	}

}
