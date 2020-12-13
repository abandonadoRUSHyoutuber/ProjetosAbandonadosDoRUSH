package br.upf.ads.gestordebolsas.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.upf.ads.gestordebolsas.entity.EquipeAluno;
import br.upf.ads.gestordebolsas.entity.HorarioAluno;
import br.upf.ads.gestordebolsas.entity.Identifiable;
import br.upf.ads.gestordebolsas.entity.Projeto;
import br.upf.ads.gestordebolsas.util.JPAUtil;
import br.upf.ads.gestordebolsas.util.JSFUtil;

@ManagedBean
@ViewScoped
@SuppressWarnings("unchecked")
public class EquipeAlunoController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String entityPackage = "br.upf.ads.gestordebolsas.entity.";
		
	public Boolean editando;
	public List<EquipeAluno> lista;
	public EquipeAluno selecionado;
	public HorarioAluno horario;
	
	public Projeto projeto;
	
	public EquipeAlunoController() {
		horario = new HorarioAluno();
		editando = false;
	}
	
	public void init(Projeto projeto) {
		try {
			this.projeto = projeto;
			this.selecionado = new EquipeAluno();
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

	public List<EquipeAluno> getLista() {
		return lista;
	}

	public void setLista(List<EquipeAluno> lista) {
		this.lista = lista;
	}

	public EquipeAluno getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(EquipeAluno selecionado) {
		this.selecionado = selecionado;
	}

	public HorarioAluno getHorario() {
		return horario;
	}

	public void setHorario(HorarioAluno horario) {
		this.horario = horario;
	}
	
	public void carregarLista() {
		EntityManager em = JPAUtil.getEntityManager();
		lista = em.createQuery("from EquipeAluno o where o.projeto.id = " + projeto.getId()).getResultList();
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
			selecionado = new EquipeAluno();
			selecionado.setProjeto(projeto);
			horario = new HorarioAluno();
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
			selecionado = new EquipeAluno();	
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
		    selecionado = new EquipeAluno();	
		    horario = new HorarioAluno();
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
			return new EquipeAluno().equals(selecionado);
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
	
	public void novoHorario() {
		horario = new HorarioAluno();
	}
	
	public void removerHorario() {
		selecionado.getHorariosAluno().remove(horario);
	}
	
	public void salvarHorario() {
		try {
			if (selecionado.getHorariosAluno() == null) {
				selecionado.setHorariosAluno(new ArrayList<>());
			}
			horario.setEquipeAluno(selecionado);
			selecionado.getHorariosAluno().add(horario);	
		} catch (Throwable e) {
			JSFUtil.mensagemDeErro("Erro ao salvar horário.");
			e.printStackTrace();
		}
	}

}
