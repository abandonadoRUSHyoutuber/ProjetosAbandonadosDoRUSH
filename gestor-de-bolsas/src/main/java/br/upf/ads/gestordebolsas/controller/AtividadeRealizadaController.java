package br.upf.ads.gestordebolsas.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.model.UploadedFile;

import br.upf.ads.gestordebolsas.entity.AtividadeAnexo;
import br.upf.ads.gestordebolsas.entity.AtividadeRealizada;
import br.upf.ads.gestordebolsas.entity.EquipeAluno;
import br.upf.ads.gestordebolsas.entity.Identifiable;
import br.upf.ads.gestordebolsas.entity.Projeto;
import br.upf.ads.gestordebolsas.util.JPAUtil;
import br.upf.ads.gestordebolsas.util.JSFUtil;

@ManagedBean
@ViewScoped
@SuppressWarnings("unchecked")
public class AtividadeRealizadaController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String entityPackage = "br.upf.ads.gestordebolsas.entity.";

	public Boolean editando;
	public List<AtividadeRealizada> lista;
	public AtividadeRealizada selecionado;

	// Controle das atividades realizadas do aluno no projeto
	public String id;
	public boolean aluno;
	public Projeto projeto;

	public AtividadeRealizadaController() {
		anexoSelecionado = new AtividadeAnexo();
		editando = false;
	}

	public void init(String id, boolean aluno, Projeto projeto) {
		try {
			this.id = id;
			this.aluno = aluno;
			this.projeto = projeto;
			this.anexoSelecionado = new AtividadeAnexo();
			carregarLista();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public Boolean getEditando() {
		return editando;
	}

	public void setEditando(Boolean editando) {
		this.editando = editando;
	}

	public List<AtividadeRealizada> getLista() {
		return lista;
	}

	public void setLista(List<AtividadeRealizada> lista) {
		this.lista = lista;
	}

	public AtividadeRealizada getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(AtividadeRealizada selecionado) {
		this.selecionado = selecionado;
	}

	public void carregarLista() {
		EntityManager em = JPAUtil.getEntityManager();
		if (aluno) {
			EquipeAluno ea = getEquipeAluno();
			lista = em.createQuery("from AtividadeRealizada where equipealuno_id = :id").setParameter("id", ea.getId()).getResultList();
		} else {
			lista = em.createQuery("from AtividadeRealizada").getResultList();
		}
		em.close();
	}

	public List<Identifiable> getSelect(String source, String entity) throws Exception {

		if (source.equals("Table")) {
			EntityManager em = JPAUtil.getEntityManager();
			List<Identifiable> selectItems = em.createQuery("from " + entity).getResultList();
			em.close();
			return selectItems;
		}

		if (source.equals("Enum")) {
			Class<?> clazz = Class.forName(entityPackage + entity);
			List<Identifiable> selectItems = (List<Identifiable>) Arrays.asList(clazz.getEnumConstants());
			return selectItems;
		}

		return null;
	}

	public void incluir() {
		try {
			editando = true;
			selecionado = new AtividadeRealizada();
			anexoSelecionado = new AtividadeAnexo();
			if (aluno) {
				selecionado.setEquipeAluno(getEquipeAluno());
			}
		} catch (Throwable e) {
			e.printStackTrace();
			JSFUtil.mensagemDeErro("Erro ao incluir o novo registro.");
		}
	}

	public void alterar() {
		editando = true;
	}

	public void salvar() {
		try {
			editando = false;
			EntityManager em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			if (aluno) {
				selecionado.setEquipeAluno(getEquipeAluno());
			}
			em.merge(selecionado);
			em.getTransaction().commit();
			em.close();
			carregarLista();
			JSFUtil.mensagemDeSucesso("Informações salvas com sucesso.");
		} catch (Throwable e) {
			e.printStackTrace();
			JSFUtil.mensagemDeErro("Erro ao tentar salvar as informações.");
		}
	}

	public void excluir() {
		try {
			editando = false;
			EntityManager em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			em.remove(em.merge(selecionado));
			em.getTransaction().commit();
			em.close();
			carregarLista();
			JSFUtil.mensagemDeSucesso("Registro apagado com sucesso.");
		} catch (Throwable e) {
			e.printStackTrace();
			JSFUtil.mensagemDeErro("Erro ao tentar apagar o registro.");
		}
	}

	public void cancelar() {
		try {
			editando = false;
			selecionado = new AtividadeRealizada();
		} catch (Throwable e) {
			e.printStackTrace();
			JSFUtil.mensagemDeErro("Erro ao tentar cancelar a ação.");
		}
	}

	public boolean isSelected() {
		try {
			return new AtividadeRealizada().equals(selecionado);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isNewInstance() {
		try {
			Long id = ((Identifiable) selecionado).getId();
			return id == null;
		} catch (Throwable e) {
			e.printStackTrace();
			return false;
		}
	}

	public EquipeAluno getEquipeAluno() {
		try {
			EntityManager em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			String equipeAlunoId = em.createNativeQuery("SELECT ea.id "
					                                  + "FROM EquipeAluno ea INNER JOIN EquipeProjeto ep ON ea.id = ep.id "
					                                  + "WHERE ep.projeto_id = "+projeto.getId()+" AND ea.aluno_id = "+id).getSingleResult().toString();
			EquipeAluno equipeAluno = em.find(EquipeAluno.class, Long.valueOf(equipeAlunoId));
			em.getTransaction().commit();
			em.close();
			return equipeAluno;
		} catch (Throwable e) {
			e.printStackTrace();
			JSFUtil.mensagemDeErro("Erro ao tentar buscar os dados do aluno no projeto.");
		}
		return null;
	}

	/*
	 * 
	 * Sistema de anexos das atividades
	 * 
	 */

	// Controle dos arquivos anexados
	public UploadedFile file;
	public AtividadeAnexo anexoSelecionado;

	public void incluirAnexo() {
		anexoSelecionado = new AtividadeAnexo();
	}

	public void salvarAnexo() {
		if (file != null) {
			anexoSelecionado.setAtividadeRealizada(selecionado);
			anexoSelecionado.setArquivo(file.getFileName());
			anexoSelecionado.setArquivoTipo(file.getContentType());
			anexoSelecionado.setBytes(file.getContents());
			if (selecionado.getAnexos() == null) {
				selecionado.setAnexos(new ArrayList<AtividadeAnexo>());
			}
			selecionado.getAnexos().add(anexoSelecionado);
			anexoSelecionado = new AtividadeAnexo();
		}
	}

	public void cancelarAnexo() {
		anexoSelecionado = new AtividadeAnexo();
	}

	public void excluirAnexo(Integer linha) {
		AtividadeAnexo anexoExcluido = selecionado.getAnexos().get(linha);
		selecionado.getAnexos().remove(anexoExcluido);
	}

	public void downloadAnexo(Integer linha) throws IOException {
		AtividadeAnexo anexo = selecionado.getAnexos().get(linha);
		byte[] b = anexo.getBytes();
		HttpServletResponse res = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		res.setContentType(anexo.getArquivoTipo());
		res.setHeader("Content-disposition", "inline;filename=" + anexo.getArquivo()); // abre no navegador
		res.getOutputStream().write(b);
		FacesContext.getCurrentInstance().responseComplete();
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public AtividadeAnexo getAnexoSelecionado() {
		return anexoSelecionado;
	}

	public void setAnexoSelecionado(AtividadeAnexo anexoSelecionado) {
		this.anexoSelecionado = anexoSelecionado;
	}

}
