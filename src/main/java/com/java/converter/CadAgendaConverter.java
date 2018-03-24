package com.java.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.java.dao.CadAgendaDAO;
import com.java.modelo.CadAgenda;
import com.java.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=CadAgenda.class)
public class CadAgendaConverter implements Converter {

	private CadAgendaDAO cadAgendaDao;
	
	public CadAgendaConverter() {
		this.cadAgendaDao = CDIServiceLocator.getBean(CadAgendaDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		CadAgenda retorno = null;
		
		try {
			if (value != null) {
			retorno = this.cadAgendaDao.retornarCadAgendaPorID(new Long(value));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((CadAgenda) value).getId();
			String retorno = (codigo == null ? null : codigo.toString());
			return retorno;
		}
		return "";
	}

}