package br.upf.ads.gestordebolsas.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.upf.ads.gestordebolsas.entity.Identifiable;
import br.upf.ads.gestordebolsas.util.JPAUtil;
import br.upf.ads.gestordebolsas.util.JSFUtil;

@ManagedBean
@ViewScoped
@SuppressWarnings("unchecked")
public class EntityController<E> implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String entityPackage = "br.upf.ads.gestordebolsas.entity.";

	private Class<E> classe;
	private String classeNome;

	public Boolean editando;
	public List<E> lista;
	public E selecionado;

	public EntityController() {
		editando = false;
	}

	public void init(String entidade) {
		try {
			this.classe = (Class<E>) Class.forName(entityPackage + entidade);
			this.classeNome = classe.getSimpleName();
			this.selecionado = classe.newInstance();
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

	public List<E> getLista() {
		return lista;
	}

	public void setLista(List<E> lista) {
		this.lista = lista;
	}

	public E getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(E selecionado) {
		this.selecionado = selecionado;
	}

	public void carregarLista() {
		EntityManager em = JPAUtil.getEntityManager();
		lista = em.createQuery("from " + classeNome).getResultList();
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
			selecionado = classe.newInstance();
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
			selecionado = classe.newInstance();
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
			selecionado = classe.newInstance();
		} catch (Throwable e) {
			e.printStackTrace();
			JSFUtil.mensagemDeErro("Erro ao tentar cancelar a ação.");
		}
	}

	public boolean isSelected() {
		try {
			return classe.newInstance().equals(selecionado);
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
