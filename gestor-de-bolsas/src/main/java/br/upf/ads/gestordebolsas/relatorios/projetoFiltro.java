package br.upf.ads.gestordebolsas.relatorios;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.upf.ads.gestordebolsas.entity.Identifiable;
import br.upf.ads.gestordebolsas.entity.Professor;
import br.upf.ads.gestordebolsas.util.JPAUtil;
import br.upf.ads.gestordebolsas.util.JSFUtil;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class projetoFiltro implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String entityPackage = "br.upf.ads.gestordebolsas.entity.";

	private Professor professor;

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public void gerar(Long id, boolean ehProfessor, boolean filtrar) {
		try {
			HashMap parameters = new HashMap();

			if (filtrar) {
				if (ehProfessor) {
					parameters.put("filtro", "where professor.id = " + id);
				} else {
					parameters.put("filtro", "where professor.id = " + professor.getId());
				}
			}

			RelatorioUtil.rodarRelatorioPDF("WEB-INF/Relatorios/Projetos/ProjetosRel.jasper", parameters);
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.mensagemDeErro("Erro ao criar relatório");
			FacesContext.getCurrentInstance().addMessage("Erro", new FacesMessage(e.getMessage()));
		}
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
}
