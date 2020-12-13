package br.upf.ads.gestordebolsas.controller;

import java.io.Serializable;
import java.util.HashMap;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.upf.ads.gestordebolsas.relatorios.RelatorioUtil;
import br.upf.ads.gestordebolsas.util.JSFUtil;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes" })
public class RelatoriosController implements Serializable {

	private static final long serialVersionUID = 1L;

	public void relAlunos() {
		try {
			HashMap parameters = new HashMap();
			RelatorioUtil.rodarRelatorioPDF("WEB-INF/Relatorios/Alunos/AlunosRel.jasper", parameters);
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.mensagemDeErro("Erro ao criar relatório");
			FacesContext.getCurrentInstance().addMessage("Erro", new FacesMessage(e.getMessage()));
		}
	}

	public void relProfessores() {
		try {
			HashMap parameters = new HashMap();
			RelatorioUtil.rodarRelatorioPDF("WEB-INF/Relatorios/Professores/ProfessoresRel.jasper", parameters);
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.mensagemDeErro("Erro ao criar relatório");
			FacesContext.getCurrentInstance().addMessage("Erro", new FacesMessage(e.getMessage()));
		}
	}
}
