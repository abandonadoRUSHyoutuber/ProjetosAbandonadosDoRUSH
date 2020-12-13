package br.upf.ads.gestordebolsas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.upf.ads.gestordebolsas.annotation.EnumValidate;

@Entity
public class HorarioAluno implements Identifiable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HorarioAlunoID")
	@SequenceGenerator(name = "HorarioAlunoID", sequenceName = "HorarioAlunoID", allocationSize = 1)
	private Long id;

	@EnumValidate(enumClass = DiaDaSemana.class, message = "O dia da semana deve ser Segunda, Terça, Quarta, Quinta, Sexta ou Sábado.")
	@NotBlank(message = "Você deve informar o dia da semana.")
	@Column(nullable = false, unique = false, length = 20)
	private String diaDaSemana;

	@EnumValidate(enumClass = Turno.class, message = "O turno deve ser Manhã, Tarde ou Noite.")
	@NotBlank(message = "Você deve informar o turno.")
	@Column(nullable = false, unique = false, length = 10)
	private String turno;

	@Min(value = 1, message = "O turno deve ser de no mínimo 1 hora e no máximo 6 horas.")
	@Max(value = 6, message = "O turno deve ser de no mínimo 1 hora e no máximo 6 horas.")
	@NotNull(message = "Você deve informar a número de horas do turno.")
	@Column(nullable = false)
	private Integer horasNoTurno;

	@Size(max = 100, message = "A descrição deve ser conter no máximo {max} caracteres.")
	@Column(unique = false, length = 100)
	private String horarios;

	@NotNull(message = "Deve informar o aluno")
	@ManyToOne(optional = false)
	private EquipeAluno equipeAluno;

	public HorarioAluno() {
		super();
	}

	public HorarioAluno(Long id, String diaDaSemana, String turno, Integer horasNoTurno, String horarios, EquipeAluno equipeAluno) {
		super();
		this.id = id;
		this.diaDaSemana = diaDaSemana;
		this.turno = turno;
		this.horasNoTurno = horasNoTurno;
		this.horarios = horarios;
		this.equipeAluno = equipeAluno;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDiaDaSemana() {
		return diaDaSemana;
	}

	public void setDiaDaSemana(String diaDaSemana) {
		this.diaDaSemana = diaDaSemana;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public Integer getHorasNoTurno() {
		return horasNoTurno;
	}

	public void setHorasNoTurno(Integer horasNoTurno) {
		this.horasNoTurno = horasNoTurno;
	}

	public String getHorarios() {
		return horarios;
	}

	public void setHorarios(String horarios) {
		this.horarios = horarios;
	}

	public EquipeAluno getEquipeAluno() {
		return equipeAluno;
	}

	public void setEquipeAluno(EquipeAluno equipeAluno) {
		this.equipeAluno = equipeAluno;
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
		HorarioAluno other = (HorarioAluno) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
