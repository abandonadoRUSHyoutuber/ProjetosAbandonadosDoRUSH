package br.upf.ads.gestordebolsas.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class AtividadeAnexo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AtividadeAnexoID")
	@SequenceGenerator(name = "AtividadeAnexoID", sequenceName = "AtividadeAnexoID", allocationSize = 1)
	private Long id;

	@Size(min = 2, max = 100, message = "A descrição do anexo deve ter entre {min} e {max} caracteres.")
	@NotBlank(message = "Informe a descrição do arquivo")
	@Column(length = 100, nullable = false)
	private String descricao;

	@Lob
	private String arquivo;

	@Column(length = 100)
	private String arquivoTipo;

	@Lob
	private byte[] bytes;

	@NotNull(message = "Você deve informar a atividade.")
	@ManyToOne(optional = false)
	private AtividadeRealizada atividadeRealizada;

	public AtividadeAnexo() {
		super();
	}

	public AtividadeAnexo(Long id, String descricao, String arquivo, String arquivoTipo, byte[] bytes, AtividadeRealizada atividadeRealizada) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.arquivo = arquivo;
		this.arquivoTipo = arquivoTipo;
		this.bytes = bytes;
		this.atividadeRealizada = atividadeRealizada;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	public String getArquivoTipo() {
		return arquivoTipo;
	}

	public void setArquivoTipo(String arquivoTipo) {
		this.arquivoTipo = arquivoTipo;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public AtividadeRealizada getAtividadeRealizada() {
		return atividadeRealizada;
	}

	public void setAtividadeRealizada(AtividadeRealizada atividadeRealizada) {
		this.atividadeRealizada = atividadeRealizada;
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
		AtividadeAnexo other = (AtividadeAnexo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return descricao;
	}

}
