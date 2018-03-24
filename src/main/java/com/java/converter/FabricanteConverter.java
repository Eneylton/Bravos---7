package com.java.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.java.dao.FabricanteDAO;
import com.java.modelo.Fabricante;
import com.java.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Fabricante.class)
public class FabricanteConverter implements Converter {

	private FabricanteDAO fabricanteDao;
	
	public FabricanteConverter() {
		this.fabricanteDao = CDIServiceLocator.getBean(FabricanteDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		Fabricante retorno = null;
		
		try {
			if (value != null) {
			retorno = this.fabricanteDao.retornarFabricantePorID(new Long(value));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Fabricante) value).getId();
			String retorno = (codigo == null ? null : codigo.toString());
			return retorno;
		}
		return "";
	}

}