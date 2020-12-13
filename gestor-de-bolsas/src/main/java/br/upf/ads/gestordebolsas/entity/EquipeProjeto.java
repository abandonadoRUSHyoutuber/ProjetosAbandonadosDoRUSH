package br.upf.ads.gestordebolsas.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class EquipeProjeto implements Identifiable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EquipeProjetoID")
	@SequenceGenerator(name = "EquipeProjetoID", sequenceName = "EquipeProjetoID", allocationSize = 1)
	private Long id;

	@PastOrPresent(message = "A data de entrada não pode ser uma data futura.")
	@NotNull(message = "Você deve informar a data de entrada do integrante.")
	@Temporal(TemporalType.DATE)
	private Date dataEntrada = new Date();

	@PastOrPresent(message = "A data de saída não pode ser uma data futura.")
	@Temporal(TemporalType.DATE)
	private Date dataSaida;

	@NotNull(message = "Você deve informar qual o projeto.")
	@ManyToOne(optional = false)
	private Projeto projeto;

	public EquipeProjeto() {
		super();
	}

	public EquipeProjeto(Long id, Date dataEntrada, Date dataSaida, Projeto projeto) {
		super();
		this.id = id;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.projeto = projeto;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataEntrada() {
		return this.dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Date getDataSaida() {
		return this.dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
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
		EquipeProjeto other = (EquipeProjeto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
