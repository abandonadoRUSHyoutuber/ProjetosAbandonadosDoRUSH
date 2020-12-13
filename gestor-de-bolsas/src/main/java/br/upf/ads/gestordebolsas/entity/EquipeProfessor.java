package br.upf.ads.gestordebolsas.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.upf.ads.gestordebolsas.annotation.EnumValidate;

@Entity
public class EquipeProfessor extends EquipeProjeto implements Identifiable {

	private static final long serialVersionUID = 1L;

	@EnumValidate(enumClass = Funcao.class, message = "Você deve informar uma função válida.")
	@NotBlank(message = "Você deve informar a função do professor.")
	@Column(nullable = false, unique = false, length = 60)
	private String funcao;

	@Min(value = 0, message = "O número de horas semanais deve ser maior ou igual a zero.")
	@NotNull(message = "Você deve informar o número de horas semanais.")
	@Column(nullable = false, unique = false)
	private Integer horasSemanais;

	@NotNull(message = "Você deve informar o orientador do projeto.")
	@ManyToOne(optional = false)
	private Professor professor;

	public EquipeProfessor() {
		super();
	}

	public EquipeProfessor(Long id, Date dataEntrada, Date dataSaida, Projeto projeto, String funcao, Integer horasSemanais, Professor professor) {
		super(id, dataEntrada, dataSaida, projeto);
		this.funcao = funcao;
		this.horasSemanais = horasSemanais;
		this.professor = professor;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public Integer getHorasSemanais() {
		return horasSemanais;
	}

	public void setHorasSemanais(Integer horasSemanais) {
		this.horasSemanais = horasSemanais;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

}
