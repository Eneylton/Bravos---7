package com.java.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.java.dao.ProvaTrafegoDAO;
import com.java.modelo.ProvaTrafego;
import com.java.modelo.Servico;
import com.java.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=ProvaTrafegoDAO.class)
public class ProvaTrafegoConverter implements Converter {

	private ProvaTrafegoDAO provaTrafegoDAO;
	
	public ProvaTrafegoConverter() {
		this.provaTrafegoDAO = CDIServiceLocator.getBean(ProvaTrafegoDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		ProvaTrafego retorno = null;
		
		try {
			if (value != null) {
			retorno = this.provaTrafegoDAO.retornarProvaTrafegoPorID(new Long(value));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Servico) value).getId();
			String retorno = (codigo == null ? null : codigo.toString());
			return retorno;
		}
		return "";
	}

}