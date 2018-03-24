package com.java.controller.seguraca;

import java.io.Serializable;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.java.dao.UsuarioDAO;
import com.java.modelo.Usuario;
import com.java.util.Session;

@Named
@ViewScoped
public class SegurancaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioDAO usuarioDAO;

	private Usuario usuario;

	@PostConstruct
	public void init() {

	}

	public boolean isPermitido() throws SQLException {

		Usuario usuario = usuarioDAO.retornarUsuarioPorID(Session.retornaIdUsuarioLogado());

		if (usuario == null) {
			return false;
		} else if (usuario.getAdmin().equals("S")) {
			return false;
		} else {
			return true;
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
