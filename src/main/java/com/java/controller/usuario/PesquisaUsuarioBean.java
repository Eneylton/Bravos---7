package com.java.controller.usuario;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.java.modelo.Usuario;
import com.java.service.UsuarioService;
import com.java.util.Session;
import com.java.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	UsuarioService usuarioService;

	private List<Usuario> usuarios = new ArrayList<>();
	private List<Usuario> pesquisaUsuario = new ArrayList<>();

	private Usuario usuarioSelecionado;

	@PostConstruct
	public void inicializar() {
		try {
			carregarLista();
		} catch (SQLException e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public void carregarLista() throws SQLException {

		Long idUsuario = Session.retornaIdAutoEscola();

		usuarios = usuarioService.listarTodos(idUsuario);
		pesquisaUsuario = usuarioService.listarTodos(idUsuario);
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public void excluir() {
		try {
			usuarioService.excluir(usuarioSelecionado);
			this.usuarios.remove(usuarioSelecionado);
			FacesUtil.addSuccessMessage("Usuário " + usuarioSelecionado.getNomeCompleto() + " excluído com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public List<Usuario> getPesquisaUsuario() {
		return pesquisaUsuario;
	}

	public void setPesquisaUsuario(List<Usuario> pesquisaUsuario) {
		this.pesquisaUsuario = pesquisaUsuario;
	}

}
