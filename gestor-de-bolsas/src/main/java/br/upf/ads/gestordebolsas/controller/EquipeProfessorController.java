package br.upf.ads.gestordebolsas.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.upf.ads.gestordebolsas.entity.EquipeProfessor;
import br.upf.ads.gestordebolsas.entity.Identifiable;
import br.upf.ads.gestordebolsas.entity.Projeto;
import br.upf.ads.gestordebolsas.util.JPAUtil;
import br.upf.ads.gestordebolsas.util.JSFUtil;

@ManagedBean
@ViewScoped
@SuppressWarnings("unchecked")
public class EquipeProfessorController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String entityPackage = "br.upf.ads.gestordebolsas.entity.";

	public Boolean editando;
	public List<EquipeProfessor> lista;
	public EquipeProfessor selecionado;
	
	public Projeto projeto;
	
	public EquipeProfessorController() {
		editando = false;
	}

	public void init(Projeto projeto) {
		try {
			this.projeto = projeto;
			this.selecionado = new EquipeProfessor();
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

	public List<EquipeProfessor> getLista() {
		return lista;
	}

	public void setLista(List<EquipeProfessor> lista) {
		this.lista = lista;
	}

	public EquipeProfessor getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(EquipeProfessor selecionado) {
		this.selecionado = selecionado;
	}

	public void carregarLista() {
		EntityManager em = JPAUtil.getEntityManager();
		lista = em.createQuery("from EquipeProfessor o where o.projeto.id = " + projeto.getId()).getResultList();
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
			selecionado = new EquipeProfessor();
			selecionado.setProjeto(projeto);
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
			selecionado.setProjeto(projeto);
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
			selecionado = new EquipeProfessor();
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
			selecionado = new EquipeProfessor();
		} catch (Throwable e) {
			e.printStackTrace();
			JSFUtil.mensagemDeErro("Erro ao tentar cancelar a ação.");
		}
	}

	public void remover() {
		selecionado.setDataSaida(new Date());
		salvar();
	}

	public boolean isSelected() {
		try {
			return new EquipeProfessor().equals(selecionado);
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
	
}
