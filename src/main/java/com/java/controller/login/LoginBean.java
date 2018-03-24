package com.java.controller.login;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.java.modelo.Usuario;
import com.java.service.UsuarioService;
import com.java.util.jsf.FacesUtil;
import com.java.util.jsfsecurity.SecurityUtil;

@Named
@ViewScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	UsuarioService usuarioService;
	
    @SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(LoginBean.class.getName());

    private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@PostConstruct
	public void init() {
		
		try{
					
		this.limpar();
		
		if( this.isLoggedIn()){			
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			context.redirect("../Usuario/PesquisaUsuario.xhtml");
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}

    public String authenticate() {

        String viewId = "Login";
        
        if (usuarioService.validaUsuarioPorLoginSenha(usuario)) {
        	viewId = SecurityUtil.logIn("/login/Login");
        } else {
        	FacesUtil.addErrorMessage("Usuário ou senha inválidos.");
			this.limpar();
        }
        
        return viewId;
    }

    public boolean isLoggedIn() {
        return SecurityUtil.isLoggedIn();
    }

    public String logout() {
        SecurityUtil.logOut();
        return "/Login.xhtml";
    }
    
    public void limpar() {
		this.usuario = new Usuario();
	}
        
}