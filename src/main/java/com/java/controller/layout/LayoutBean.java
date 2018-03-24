package com.java.controller.layout;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import com.java.util.Session;

@Named
@ViewScoped
public class LayoutBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String usuarioLogado;

	private String autoEscolaLog;

	private String autoEscolaImg;

	private Long idAutoEscola;

	public String getUsuarioLogado() {
		return usuarioLogado;
	}

	@PostConstruct
	public void init() {
		this.usuarioLogado = Session.retornaNomeCompletoUsuarioLogado();
		this.autoEscolaLog = Session.retornaAutoEscolaLog();
		this.idAutoEscola = Session.retornaIdAutoEscola();
		this.autoEscolaImg = Session.retornaIdAutoEscolaImagem();
	}

	public void setUsuarioLogado(String usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public String getAutoEscolaLog() {
		return autoEscolaLog;
	}

	public void setAutoEscolaLog(String autoEscolaLog) {
		this.autoEscolaLog = autoEscolaLog;
	}

	public Long getIdAutoEscola() {
		return idAutoEscola;
	}

	public void setIdAutoEscola(Long idAutoEscola) {
		this.idAutoEscola = idAutoEscola;
	}

	public String getAutoEscolaImg() {
		return autoEscolaImg;
	}

	public void setAutoEscolaImg(String autoEscolaImg) {
		this.autoEscolaImg = autoEscolaImg;
	}

}
