package com.java.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.java.dao.AutoEscolaDAO;
import com.java.modelo.AutoEscola;
import com.java.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=AutoEscola.class)
public class AutoEscolaConverter implements Converter {

	private AutoEscolaDAO autoEscolaDao;
	
	public AutoEscolaConverter() {
		this.autoEscolaDao = CDIServiceLocator.getBean(AutoEscolaDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		AutoEscola retorno = null;
		
		try {
			if (value != null) {
			retorno = this.autoEscolaDao.retornarAutoEscolaPorID(new Long(value));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((AutoEscola) value).getId();
			String retorno = (codigo == null ? null : codigo.toString());
			return retorno;
		}
		return "";
	}

}