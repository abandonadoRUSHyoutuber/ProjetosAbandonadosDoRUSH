package br.upf.ads.gestordebolsas.entity;

import static javax.persistence.InheritanceType.JOINED;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import br.upf.ads.gestordebolsas.util.Senha;

@Entity
@Inheritance(strategy = JOINED)
public abstract class Pessoa implements Identifiable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PessoaID")
	@SequenceGenerator(name = "PessoaID", sequenceName = "PessoaID", allocationSize = 1)
	private Long id;

	@Size(min = 1, max = 10, message = "A matricula deve ter entre {min} e {max} caracteres.")
	@NotBlank(message = "Você deve informar a matricula.")
	@Column(nullable = false, unique = true, length = 10)
	private String matricula;

	@Size(min = 2, max = 60, message = "O nome deve ter entre {min} e {max} caracteres.")
	@NotBlank(message = "Você deve informar o nome.")
	@Column(nullable = false, unique = false, length = 60)
	private String nome;

	@CPF(message = "O número do CPF esta incorreto.")
	@Size(min = 14, max = 14, message = "O número do CPF esta incorreto.")
	@NotBlank(message = "Você deve informar número o CPF.")
	@Column(nullable = false, unique = true, length = 14)
	private String cpf;

	@Size(min = 6, max = 11, message = "O número do RG ter entre {min} e {max} caracteres.")
	@NotBlank(message = "Você deve informar o número do RG.")
	@Column(nullable = false, unique = true, length = 11)
	private String rg;

	@Email(message = "O endereço de email deve ser válido.")
	@Size(min = 7, max = 100, message = "O endereço de email deve ser válido.")
	@NotBlank(message = "Você deve informar o endereço de email.")
	@Column(nullable = false, unique = true, length = 100)
	private String email;

	@Size(min = 6, max = 100, message = "A sua senha deve ter entre {min} e {max} caracteres.")
	@NotBlank(message = "Você deve informar uma senha de no mínimo 6 caracteres.")
	@Column(nullable = false, unique = false, length = 100)
	private String senha;

	@Size(min = 13, max = 15, message = "O número do celular deve ter 11 digitos incluindo o DDD.")
	@Column(nullable = true, unique = false, length = 15)
	private String celular;

	@Size(max = 1000, message = "O texto com as observações deve conter no máximo {max} caracteres.")
	@Column(nullable = true, unique = false, columnDefinition = "text")
	private String observacoes;

	public Pessoa() {
		super();
	}

	public Pessoa(Long id, String matricula, String nome, String cpf, String rg, String email, String senha, String celular, String observacoes) {
		super();
		this.id = id;
		this.matricula = matricula;
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.email = email;
		this.senha = Senha.Criptografar(senha);
		this.celular = celular;
		this.observacoes = observacoes;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatricula() {
		return this.matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return this.rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = Senha.Criptografar(senha);
	}

	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getObservacoes() {
		return this.observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
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
		Pessoa other = (Pessoa) obj;
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
