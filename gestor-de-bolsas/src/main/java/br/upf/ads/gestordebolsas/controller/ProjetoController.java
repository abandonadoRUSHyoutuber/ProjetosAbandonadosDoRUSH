package br.upf.ads.gestordebolsas.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;

import org.primefaces.PrimeFaces;

import br.upf.ads.gestordebolsas.entity.Identifiable;
import br.upf.ads.gestordebolsas.entity.Projeto;
import br.upf.ads.gestordebolsas.util.JPAUtil;
import br.upf.ads.gestordebolsas.util.JSFUtil;

@ManagedBean
@SessionScoped
@SuppressWarnings("unchecked")
public class ProjetoController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String entityPackage = "br.upf.ads.gestordebolsas.entity.";

	public Boolean editando;
	public List<Projeto> lista;
	public Projeto selecionado;

	public ProjetoController() {
		editando = false;
	}

	public void init(String entidade) {
		try {
			this.selecionado = new Projeto();
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

	public List<Projeto> getLista() {
		return lista;
	}

	public void setLista(List<Projeto> lista) {
		this.lista = lista;
	}

	public Projeto getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(Projeto selecionado) {
		this.selecionado = selecionado;
	}

	public void carregarLista() {
		EntityManager em = JPAUtil.getEntityManager();
		lista = em.createQuery("from Projeto").getResultList();
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
			selecionado = new Projeto();
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
			selecionado = new Projeto();
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
			selecionado = new Projeto();
		} catch (Throwable e) {
			e.printStackTrace();
			JSFUtil.mensagemDeErro("Erro ao tentar cancelar a ação.");
		}
	}

	public boolean isSelected() {
		try {
			return new Projeto().equals(selecionado);
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
	
	public void abrirPopup(String url, int largura, int altura, boolean modal, Projeto projeto) {
		this.selecionado = projeto;
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", modal);
		options.put("width", largura);
		options.put("height", altura);
		options.put("contentWidth", "100%");
		options.put("contentHeight", "100%");
		options.put("headerElement", "customheader");
		options.put("resizable", false);
		options.put("minimizable", true);
		options.put("maximizable", true);
		PrimeFaces.current().dialog().openDynamic(url, options, null);
	}

}
