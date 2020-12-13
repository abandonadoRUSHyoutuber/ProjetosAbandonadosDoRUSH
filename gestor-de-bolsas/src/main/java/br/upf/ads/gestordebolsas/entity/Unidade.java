package br.upf.ads.gestordebolsas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Unidade implements Identifiable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UnidadeID")
	@SequenceGenerator(name = "UnidadeID", sequenceName = "UnidadeID", allocationSize = 1)
	private Long id;

	@Size(min = 2, max = 60, message = "O nome da unidade deve ter entre {min} e {max} caracteres.")
	@NotBlank(message = "Você deve informar o nome da unidade.")
	@Column(nullable = false, unique = true, length = 60)
	private String nome;

	@Size(min = 2, max = 10, message = "A sigla da unidade deve ter entre {min} e {max} caracteres.")
	@NotBlank(message = "Você deve informar a sigla da unidade.")
	@Column(nullable = false, unique = true, length = 10)
	private String sigla;

	public Unidade() {
		super();
	}

	public Unidade(Long id, String nome, String sigla) {
		super();
		this.id = id;
		this.nome = nome;
		this.sigla = sigla;
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

	public String getSigla() {
		return this.sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
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
		Unidade other = (Unidade) obj;
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
