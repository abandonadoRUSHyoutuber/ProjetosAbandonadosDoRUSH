package br.upf.ads.gestordebolsas.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class AtividadeRealizada implements Identifiable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AtividadeRealizadaID")
	@SequenceGenerator(name = "AtividadeRealizadaID", sequenceName = "AtividadeRealizadaID", allocationSize = 1)
	private Long id;

	@NotNull(message = "Você deve informar a data da atividade.")
	@Temporal(TemporalType.DATE)
	private Date data;

	@Size(min = 10, max = 100, message = "O título deve ter entre {min} e {max} caracteres.")
	@NotBlank(message = "Você deve informar um título para a atividade.")
	@Column(nullable = false, unique = false, length = 100)
	private String titulo;

	@Size(max = 1000, message = "O texto com as observações deve conter no máximo {max} caracteres.")
	@Column(nullable = true, unique = false, columnDefinition = "text", length = 1000)
	private String observacoes;

	@Min(value = 1, message = "O número de horas realizadas deve ser maior que zero.")
	@NotNull(message = "Você deve informar o número de horas realizadas.")
	@Column(nullable = false, unique = false)
	private Integer horasRealizadas;

	@NotNull(message = "Você deve informar uma equipe.")
	@ManyToOne(optional = false)
	private EquipeAluno equipeAluno;

	@OneToMany(mappedBy = "atividadeRealizada", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<AtividadeAnexo> anexos;

	public AtividadeRealizada() {
		super();
	}

	public AtividadeRealizada(Long id, Date data, String titulo, String observacoes, Integer horasRealizadas, EquipeAluno equipeAluno, List<AtividadeAnexo> anexos) {
		super();
		this.id = id;
		this.data = data;
		this.titulo = titulo;
		this.observacoes = observacoes;
		this.horasRealizadas = horasRealizadas;
		this.equipeAluno = equipeAluno;
		this.anexos = anexos;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getObservacoes() {
		return this.observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Integer getHorasRealizadas() {
		return this.horasRealizadas;
	}

	public void setHorasRealizadas(Integer horasRealizadas) {
		this.horasRealizadas = horasRealizadas;
	}

	public EquipeAluno getEquipeAluno() {
		return equipeAluno;
	}

	public void setEquipeAluno(EquipeAluno equipeAluno) {
		this.equipeAluno = equipeAluno;
	}

	public List<AtividadeAnexo> getAnexos() {
		return anexos;
	}

	public void setAnexos(List<AtividadeAnexo> anexos) {
		this.anexos = anexos;
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
		AtividadeRealizada other = (AtividadeRealizada) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return titulo;
	}

}
