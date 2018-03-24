package com.java.controller.usuario;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.java.modelo.Usuario;
import com.java.util.Session;
import com.java.util.jsfsecurity.SecurityUtil;

@Named
@ViewScoped
public class LogoutBean  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Usuario usuario;
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@PostConstruct
	public void inicializar() {
		
		 
		try {
			
			SecurityUtil.logOut();
			Session.getSession().setAttribute("usuarioLogado", null);
			Session.getSession().setAttribute("nomeCompletoUsuarioLogado", null);
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			context.redirect("../login/Login.xhtml");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
