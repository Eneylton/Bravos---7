package com.java.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.java.modelo.Usuario;



public class Session {
	
	public static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
    }

	public static HttpSession getSession() {
        return (HttpSession) getFacesContext().getExternalContext().getSession(false);
    }
	
	public static Long retornaIdUsuarioLogado() {
		
		Usuario usuario = (Usuario) getSession().getAttribute("usuarioLogado");
		
		if (usuario != null) {
			return usuario.getId();
		}else {
			return null;
		}
	}
	
	
public static Long retornaIdAutoEscola() {
		
		Usuario usuario = (Usuario) getSession().getAttribute("usuarioLogado");
		
		if (usuario != null) {
			return usuario.getAutoescola().getId();
		}else {
			return null;
		}
	}
	
	
	public static String retornaNomeCompletoUsuarioLogado() {
		
		Usuario usuario = (Usuario) getSession().getAttribute("usuarioLogado");
		
		if (usuario != null) {
			return usuario.getNomeCompleto();
		}else {
			return null;
		}
	}
	
	
public static String retornaIdAutoEscolaImagem() {
		
		Usuario usuario = (Usuario) getSession().getAttribute("usuarioLogado");
		
		if (usuario != null) {
			return usuario.getAutoescola().getLogo();
		}else {
			return null;
		}
	}
	
	
public static String retornaAutoEscolaLog() {
		
		Usuario usuario = (Usuario) getSession().getAttribute("usuarioLogado");
		
		if (usuario != null) {
			return usuario.getAutoescola().getNome();
		}else {
			return null;
		}
	}
	
public static String retornaLoginUsuarioLogado() {
		
		Usuario usuario = (Usuario) getSession().getAttribute("usuarioLogado");
		
		if (usuario != null) {
			return usuario.getLogin();
		}else {
			return null;
		}
	}
	
}
