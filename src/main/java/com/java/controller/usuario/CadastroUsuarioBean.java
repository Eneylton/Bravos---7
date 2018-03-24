package com.java.controller.usuario;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.java.modelo.AutoEscola;
import com.java.modelo.Usuario;
import com.java.modeloEspecializado.AlteraSenhaUsuario;
import com.java.service.AutoEscolaService;
import com.java.service.UsuarioService;
import com.java.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioService usuarioService;

	@Inject
	private AutoEscolaService autoEscolaService;

	private Usuario usuario;

	private AlteraSenhaUsuario alteraSenhaUsuario;

	private String ativo = "A";

	private String inativo = "I";

	private Map<String, AutoEscola> listarAutoEscolas = new HashMap<String, AutoEscola>();

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public AlteraSenhaUsuario getAlteraSenhaUsuario() {
		return alteraSenhaUsuario;
	}

	public void setAlteraSenhaUsuario(AlteraSenhaUsuario alteraSenhaUsuario) {
		this.alteraSenhaUsuario = alteraSenhaUsuario;
	}

	@PostConstruct
	public void init() throws IOException {
		try {
			preencheListasSelect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.limpar();
	}

	private void preencheListasSelect() throws SQLException {

		listarAutoEscolas = new HashMap<String, AutoEscola>();
		for (AutoEscola autescola : autoEscolaService.listarTodos()) {
			listarAutoEscolas.put(autescola.getNome(), autescola);
		}

	}

	public void salvar() {
		try {
			this.usuarioService.salvar(usuario);
			FacesUtil.addSuccessMessage("Usuário salvo com sucesso!");
			this.limpar();
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public void alterarSenhaUsuario() {

		try {

			this.alteraSenhaUsuario = new AlteraSenhaUsuario();

			this.alteraSenhaUsuario.setId(this.usuario.getId());
			this.alteraSenhaUsuario.setLogin(this.usuario.getLogin());
			this.alteraSenhaUsuario.setNovaSenha(this.usuario.getSenha());

			this.usuarioService.alterarSenhaUsuario(this.alteraSenhaUsuario);
			FacesUtil.addSuccessMessage("Senha alterada com sucesso!");
			this.limpar();

		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage(e.getMessage());
		}

	}

	public void limpar() {
		this.usuario = new Usuario();
		this.alteraSenhaUsuario = new AlteraSenhaUsuario();
	}

	public Map<String, AutoEscola> getListarAutoEscolas() {
		return listarAutoEscolas;
	}

	public void setListarAutoEscolas(Map<String, AutoEscola> listarAutoEscolas) {
		this.listarAutoEscolas = listarAutoEscolas;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	public String getInativo() {
		return inativo;
	}

	public void setInativo(String inativo) {
		this.inativo = inativo;
	}

}
