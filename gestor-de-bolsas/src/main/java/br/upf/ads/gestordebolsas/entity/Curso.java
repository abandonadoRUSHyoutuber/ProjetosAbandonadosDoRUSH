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
public class Curso implements Identifiable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CursoID")
	@SequenceGenerator(name = "CursoID", sequenceName = "CursoID", allocationSize = 1)
	private Long id;

	@Size(min = 2, max = 60, message = "O nome do curso deve ter entre {min} e {max} caracteres.")
	@NotBlank(message = "Você deve informar o nome do curso.")
	@Column(nullable = false, unique = true, length = 60)
	private String nome;

	@NotNull(message = "Você deve informar a unidade.")
	@ManyToOne(optional = false)
	private Unidade unidade;

	public Curso() {
		super();
	}

	public Curso(Long id, String nome, Unidade unidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.unidade = unidade;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Unidade getUnidade() {
		return this.unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
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
		Curso other = (Curso) obj;
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
