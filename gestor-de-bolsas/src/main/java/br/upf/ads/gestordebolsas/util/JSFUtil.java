package br.upf.ads.gestordebolsas.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public final class JSFUtil {

	public static void mensagem(String mensagem, Severity tipo) {
		FacesMessage msg = new FacesMessage(tipo, mensagem, "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public static void mensagemDeErro(String mensagem) {
		mensagem(mensagem, FacesMessage.SEVERITY_ERROR);
	}

	public static void mensagemDeSucesso(String mensagem) {
		mensagem(mensagem, FacesMessage.SEVERITY_INFO);
	}

	public static void mensagemDeAviso(String mensagem) {
		mensagem(mensagem, FacesMessage.SEVERITY_WARN);
	}

	public static void mensagemDeFatalidade(String mensagem) {
		mensagem(mensagem, FacesMessage.SEVERITY_FATAL);
	}

}