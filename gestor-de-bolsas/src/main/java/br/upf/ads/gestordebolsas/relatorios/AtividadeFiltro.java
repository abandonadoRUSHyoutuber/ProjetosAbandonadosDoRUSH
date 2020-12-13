package br.upf.ads.gestordebolsas.relatorios;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
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
@SuppressWarnings({ "unchecked", "rawtypes" })
public class AtividadeFiltro implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String entityPackage = "br.upf.ads.gestordebolsas.entity.";

	private Aluno aluno;
	private Date dataInicio;
	private Date dataFim;

	public void gerar(Long id, boolean ehAluno, boolean filtrar) {
		try {
			HashMap parameters = new HashMap();

			if (filtrar) {

				if (dataInicio == null || dataFim == null) {
					JSFUtil.mensagemDeErro("Você deve informar a data inicial e a data final!");
					return;
				}

				SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
				String dataI = formatador.format(dataInicio);
				String dataF = formatador.format(dataFim);

				if (ehAluno) {
					parameters.put("filtro", "WHERE (atividaderealizada.data BETWEEN '" + dataI + "' AND '" + dataF + "') AND pessoa.id = " + id);
				} else {
					parameters.put("filtro", "WHERE (atividaderealizada.data BETWEEN '" + dataI + "' AND '" + dataF + "') AND pessoa.id = " + aluno.getId());
				}
			} else if (ehAluno) {
				parameters.put("filtro", "WHERE pessoa.id = " + id);
			}
			RelatorioUtil.rodarRelatorioPDF("WEB-INF/Relatorios/Alunos/AtividadeRel.jasper", parameters);
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

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

}
