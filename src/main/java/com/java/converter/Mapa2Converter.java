package com.java.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.java.dao.Mapa2DAO;
import com.java.modelo.Mapa2;
import com.java.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Mapa2.class)
public class Mapa2Converter implements Converter {

	private Mapa2DAO mapa2Dao;
	
	public Mapa2Converter() {
		this.mapa2Dao = CDIServiceLocator.getBean(Mapa2DAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		Mapa2 retorno = null;
		
		try {
			if (value != null) {
			retorno = this.mapa2Dao.retornarMapa2PorID(new Long(value));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Mapa2) value).getId();
			String retorno = (codigo == null ? null : codigo.toString());
			return retorno;
		}
		return "";
	}

}