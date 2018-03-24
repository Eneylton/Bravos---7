package com.java.controller.usuario;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.java.modelo.Usuario;
import com.java.modeloEspecializado.AlteraSenhaUsuario;
import com.java.service.UsuarioService;
import com.java.util.jsf.FacesUtil;

@Named
@ViewScoped
public class AlterarSenhaUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioService usuarioService;
	
	private Usuario usuario;
	
	private AlteraSenhaUsuario alteraSenhaUsuario;
	
	private Map<String, String> listaAdmin = new HashMap<String, String>();
	private Map<String, String> listaSituacao = new HashMap<String, String>();

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

	public Map<String, String> getListaAdmin() {
		return listaAdmin;
	}

	public void setListaAdmin(Map<String, String> listaAdmin) {
		this.listaAdmin = listaAdmin;
	}

	public Map<String, String> getListaSituacao() {
		return listaSituacao;
	}

	public void setListaSituacao(Map<String, String> listaSituacao) {
		this.listaSituacao = listaSituacao;
	}

	@PostConstruct
	public void init() {
		this.limpar();
		this.limparAlterarSenhaUsuario();
		this.preenchaListaRadio();
	}
	
	public void salvar() {
		try {
			this.usuarioService.alterarSenhaUsuario(alteraSenhaUsuario);
			FacesUtil.addSuccessMessage("Senha alterada com sucesso!");
			this.limpar();
			this.limparAlterarSenhaUsuario();
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage(e.getMessage());
		} 
	}
	
	public void limpar() {
		this.usuario = new Usuario();
	}
	
	public void limparAlterarSenhaUsuario(){
		this.alteraSenhaUsuario = new AlteraSenhaUsuario();
	}
	
	public void preenchaListaRadio(){
		
		listaAdmin = new HashMap<String, String>();
		listaAdmin.put("Sim", "S");
		listaAdmin.put("Não", "N");
	
		listaSituacao = new HashMap<String, String>();
		listaSituacao.put("Ativo", "A");
		listaSituacao.put("Inativo", "I");
		
	}
	
}
