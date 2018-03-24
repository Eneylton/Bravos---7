package com.java.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.java.dao.SexoDAO;
import com.java.modelo.Sexo;
import com.java.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Sexo.class)
public class SexoConverter implements Converter {

	private SexoDAO sexoDao;
	
	public SexoConverter() {
		this.sexoDao = CDIServiceLocator.getBean(SexoDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		Sexo retorno = null;
		
		try {
			if (value != null) {
			retorno = this.sexoDao.retornarSexoPorID(new Long(value));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Sexo) value).getId();
			String retorno = (codigo == null ? null : codigo.toString());
			return retorno;
		}
		return "";
	}

}