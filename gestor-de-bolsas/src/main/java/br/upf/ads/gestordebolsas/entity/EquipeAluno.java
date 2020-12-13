package br.upf.ads.gestordebolsas.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.upf.ads.gestordebolsas.annotation.EnumValidate;

@Entity
public class EquipeAluno extends EquipeProjeto implements Identifiable {

	private static final long serialVersionUID = 1L;

	@EnumValidate(enumClass = TipoAluno.class, message = "O tipo do aluno deve ser um bolsista ou voluntário!")
	@NotNull(message = "Você deve informar se o aluno é bolsista ou voluntario.")
	@Column(nullable = false, unique = false)
	private Character bolsaOuVoluntario;

	@Min(value = 0, message = "O valor da bolsa deve ser maior ou igual a zero.")
	@NotNull(message = "Você deve informar o valor da bolsa.")
	@Column(nullable = false, unique = false)
	private Float valorBolsa;

	@Size(min = 2, max = 30, message = "O tipo da bolsa deve ter entre {min} e {max} caracteres.")
	@NotBlank(message = "Você deve informar o tipo da bolsa.")
	@Column(nullable = false, unique = false, length = 30)
	private String tipoBolsa;

	@Column(nullable = false, unique = false, updatable = false)
	private Integer horasSemanais = 0;

	@Column(nullable = false, unique = false, updatable = false)
	private Integer horasRealizadas = 0;

	@NotNull(message = "Você deve informar o professor orientador.")
	@ManyToOne(optional = false)
	private Professor orientador;

	@NotNull(message = "Você deve informar o aluno.")
	@ManyToOne(optional = false)
	private Aluno aluno;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "equipeAluno")
	@Fetch(FetchMode.SUBSELECT)
	private List<HorarioAluno> horariosAluno;

	public EquipeAluno() {
		super();
	}

	public EquipeAluno(Long id, Date dataEntrada, Date dataSaida, Projeto projeto, Character bolsaOuVoluntario, Float valorBolsa, String tipoBolsa, Integer horasSemanais, Integer horasRealizadas, Professor orientador, Aluno aluno, List<HorarioAluno> horariosAluno) {
		super(id, dataEntrada, dataSaida, projeto);
		this.bolsaOuVoluntario = bolsaOuVoluntario;
		this.valorBolsa = valorBolsa;
		this.tipoBolsa = tipoBolsa;
		this.horasSemanais = horasSemanais;
		this.horasRealizadas = horasRealizadas;
		this.orientador = orientador;
		this.aluno = aluno;
		this.horariosAluno = horariosAluno;
	}

	public Character getBolsaOuVoluntario() {
		return bolsaOuVoluntario;
	}

	public void setBolsaOuVoluntario(Character bolsaOuVoluntario) {
		this.bolsaOuVoluntario = bolsaOuVoluntario;
	}

	public Float getValorBolsa() {
		return valorBolsa;
	}

	public void setValorBolsa(Float valorBolsa) {
		this.valorBolsa = valorBolsa;
	}

	public String getTipoBolsa() {
		return tipoBolsa;
	}

	public void setTipoBolsa(String tipoBolsa) {
		this.tipoBolsa = tipoBolsa;
	}

	public Integer getHorasSemanais() {
		return horasSemanais;
	}

	public void setHorasSemanais(Integer horasSemanais) {
		this.horasSemanais = horasSemanais;
	}

	public Integer getHorasRealizadas() {
		return horasRealizadas;
	}

	public void setHorasRealizadas(Integer horasRealizadas) {
		this.horasRealizadas = horasRealizadas;
	}

	public Professor getOrientador() {
		return orientador;
	}

	public void setOrientador(Professor orientador) {
		this.orientador = orientador;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<HorarioAluno> getHorariosAluno() {
		return horariosAluno;
	}

	public void setHorariosAluno(List<HorarioAluno> horariosAluno) {
		this.horariosAluno = horariosAluno;
	}

	@Override
	public String toString() {
		return aluno.toString();
	}

}
