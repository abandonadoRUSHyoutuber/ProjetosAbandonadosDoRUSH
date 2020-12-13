package br.upf.ads.gestordebolsas.relatorios;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.upf.ads.gestordebolsas.entity.Aluno;
import br.upf.ads.gestordebolsas.entity.Identifiable;
import br.upf.ads.gestordebolsas.util.JPAUtil;
import br.upf.ads.gestordebolsas.util.JSFUtil;

@ManagedBean
@RequestScoped
public class horarioAlunoFiltro implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String entityPackage = "br.upf.ads.gestordebolsas.entity.";

	private Aluno aluno;

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public void gerar(boolean filtrar) {
		try {
			HashMap parameters = new HashMap();

			if (filtrar) {
				parameters.put("filtro", "where pessoa.id = " + aluno.getId());
			}

			RelatorioUtil.rodarRelatorioPDF("WEB-INF/Relatorios/Alunos/HorariosRel.jasper", parameters);
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
