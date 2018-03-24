package com.java.modelo;

import java.io.Serializable;

public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String admin;
	private String login;
	private String nomeCompleto;
	private String senha;
	private String situacao;
	private AutoEscola autoescola;
	

	public AutoEscola getAutoescola() {
		return autoescola;
	}

	public void setAutoescola(AutoEscola autoescola) {
		this.autoescola = autoescola;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

}