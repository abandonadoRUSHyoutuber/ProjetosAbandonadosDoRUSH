package br.upf.ads.gestordebolsas.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.upf.ads.gestordebolsas.entity.Administrador;
import br.upf.ads.gestordebolsas.entity.Aluno;
import br.upf.ads.gestordebolsas.entity.Pessoa;
import br.upf.ads.gestordebolsas.entity.PessoaRecuperacao;
import br.upf.ads.gestordebolsas.entity.Professor;
import br.upf.ads.gestordebolsas.util.Email;
import br.upf.ads.gestordebolsas.util.JPAUtil;
import br.upf.ads.gestordebolsas.util.JSFUtil;
import br.upf.ads.gestordebolsas.util.Senha;

@ManagedBean
@SessionScoped
@SuppressWarnings("unchecked")
public class LoginController implements Serializable {

	private static final long serialVersionUID = 1L;

	public String email;
	public String senha;
	public String senhaAtual;
	public String novaSenha;
	public String codigo;
	public Boolean verifica = false;
	/**
	 * Atributo para controle do usuário logado. É inicializado quando informados
	 * login e senha válidos. Setado para null quando o usuário sair do sistema.
	 */
	public Pessoa usuarioLogado = null;
	public String tipoUsuario = null;

	public LoginController() {
	}

	/**
	 * Método responsável por valodar o login e senha do usuário. Se for válido
	 * inicializa o usuário logado com a instancia do usuário respectivo ao login e
	 * senha informados e redireciona para a tela principal da aplicação.
	 * 
	 * @throws Exception
	 */
	public String entrar() {
		EntityManager em = JPAUtil.getEntityManager();
		Query qry = em.createQuery("from Pessoa where email = :email and senha = :senha");
		qry.setParameter("email", email);
		qry.setParameter("senha", Senha.Criptografar(senha));
		List<Pessoa> list = qry.getResultList();
		em.close();
		if (list.size() <= 0) {
			usuarioLogado = null;
			FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login ou senha inválida!", "");
			FacesContext.getCurrentInstance().addMessage(null, mensagem);
			tipoUsuario = null;
			return "";
		} else {
			usuarioLogado = list.get(0);
			this.defineTipoUsuario();
			HttpServletResponse res = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
			HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			String contextPath = req.getContextPath();
			try {
				res.sendRedirect(contextPath + "/faces/index.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FacesContext.getCurrentInstance().responseComplete();
			return contextPath;
		}	
	}

	public void defineTipoUsuario() {
		if (usuarioLogado.getClass().equals(Aluno.class))
			tipoUsuario = "Aluno";
		if (usuarioLogado.getClass().equals(Professor.class))
			tipoUsuario = "Professor";
		if (usuarioLogado.getClass().equals(Administrador.class))
			tipoUsuario = "Administrador";
	}

	/**
	 * Método responsável por desconectar o usuário e abrir a página de login
	 * 
	 * @throws Exception
	 */
	public String sair() {
		setUsuarioLogado(null);
		FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário Desconectado!", "");
		FacesContext.getCurrentInstance().addMessage(null, mensagem);
		return "/faces/Login.xhtml";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Pessoa getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Pessoa usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Boolean getVerifica() {
		return verifica;
	}

	public void setVerifica(Boolean verifica) {
		this.verifica = verifica;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	/**
	 * Método que gera e envia para o e-mail informado, um código para recuperação
	 */
	public String recuperarSenha() {
		setVerifica(false);
		try {
			String codigoRecuperacao = String.format("%06d", (new Random().nextInt(999999)));
			EntityManager em = JPAUtil.getEntityManager();
			Pessoa pessoa = findPessoaByEmail(email);
			if (pessoa == null) {
				JSFUtil.mensagemDeErro("Nenhuma pessoa cadastrada com este email!");
				return "";
			}
			PessoaRecuperacao pRec = new PessoaRecuperacao();
			pRec.setEmail(email);
			PessoaRecuperacao antigo = findPessoaRecuperacaoByEmail(email);
			if (antigo != null) {
				pRec = antigo;
			}
			pRec.setCodigo(codigoRecuperacao);
			em.getTransaction().begin();
			em.merge(pRec);
			Email.send(email, "🔐 Recuperação de Senha",
					"Sistema Gestor de Bolsas\nUsuário: " + pessoa.getNome() + "\nSeu código é " + codigoRecuperacao);
			em.getTransaction().commit();
			em.close();
			JSFUtil.mensagemDeSucesso("Código gerado com sucesso! Verifique seu e-mail");
			return "ConfirmarCodigo.xhtml";
		} catch (Throwable e) {
			e.printStackTrace();
			JSFUtil.mensagemDeErro("Não foi possível redefinir sua senha!");
		}
		return "";
	}

	/**
	 * Método para verificar se o código informado está cadastado na base de dados
	 * para o determinado usuário
	 */
	public String verificarCodigo() {
		try {
			PessoaRecuperacao p = findPessoaRecuperacaoByEmail(email);
			if (p.getCodigo().equals(codigo)) {
				JSFUtil.mensagemDeSucesso("Código verificado! Insira sua nova senha");
				setVerifica(true);
				return "DefinirNovaSenha.xhtml";
			} else {
				setVerifica(false);
				JSFUtil.mensagemDeErro("Código incorreto!");
			}
		} catch (Throwable e) {
			e.printStackTrace();
			JSFUtil.mensagemDeErro("Não foi possível verificar seu código de recuperação!");
		}
		return "";
	}

	/**
	 * Método que irá cadastrar nova senha e limpar dados de recuperação do usuário
	 * em questão
	 */
	public String definirNovaSenha() throws IOException {
		if (verifica) {
			EntityManager em = JPAUtil.getEntityManager();
			Pessoa p = findPessoaByEmail(email);
			PessoaRecuperacao pr = findPessoaRecuperacaoByEmail(email);
			if (pr.getCodigo().equals(codigo)) {
				p.setSenha(novaSenha);
				em.getTransaction().begin();
				em.merge(p);
				em.remove(em.merge(pr));
				em.getTransaction().commit();
				em.close();
				JSFUtil.mensagemDeSucesso("Senha alterada com sucesso!");
			} else {
				JSFUtil.mensagemDeErro("Erro ao alterar a senha. Código inválido!");
			}
		} else {
			JSFUtil.mensagemDeErro("Acesso negado. Gere novo código de recuperação!");
		}
		setVerifica(false);
		return "../LoginForm.xhtml";
	}

	/**
	 * Método que irá alterar a senha do usuário logado.
	 */
	public void alterarSenha() throws IOException {
		if (Senha.Validar(usuarioLogado.getSenha(), senhaAtual)) {
			if (novaSenha.length() >= 6 && novaSenha.length() <= 100) {
				usuarioLogado.setSenha(novaSenha);
				EntityManager em = JPAUtil.getEntityManager();
				em.getTransaction().begin();
				em.merge(usuarioLogado);
				em.getTransaction().commit();
				em.close();
				JSFUtil.mensagemDeSucesso("Senha alterada com sucesso!");
				return;
			} else {
				JSFUtil.mensagemDeErro("A nova sua senha deve ter entre 6 e 100 caracteres.");
				return;
			}
		} else {
			JSFUtil.mensagemDeErro("Senha atual inválida!");
			return;
		}
	}

	/**
	 * Método que recebe um e-mail e retorna os dados da pessoa na base de dados
	 */
	public Pessoa findPessoaByEmail(String value) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			Query q = em.createQuery("FROM Pessoa o WHERE o.email = '" + value + "'");
			return (Pessoa) q.getSingleResult();
		} catch (Throwable e) {
			return null;
		} finally {
			em.close();
		}
	}

	/**
	 * Método que recebe um e-mail e verifica se possui código de recuperação
	 * cadastrado. Se afirmativo, retorna o código
	 */
	public PessoaRecuperacao findPessoaRecuperacaoByEmail(String value) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			Query q = em.createQuery("FROM PessoaRecuperacao o WHERE o.email = '" + value + "'");
			return (PessoaRecuperacao) q.getSingleResult();
		} catch (Throwable e) {
			return null;
		} finally {
			em.close();
		}
	}

}