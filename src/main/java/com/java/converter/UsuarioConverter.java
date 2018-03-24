package com.java.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.java.dao.UsuarioDAO;
import com.java.modelo.Usuario;
import com.java.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Usuario.class)
public class UsuarioConverter implements Converter {

	private UsuarioDAO usuarioDAO;
	
	public UsuarioConverter() {
		this.usuarioDAO = CDIServiceLocator.getBean(UsuarioDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		Usuario retorno = null;
		
		try {
			if (value != null) {
			retorno = this.usuarioDAO.retornarUsuarioPorID(new Long(value));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Usuario) value).getId();
			String retorno = (codigo == null ? null : codigo.toString());
			return retorno;
		}
		return "";
	}

}