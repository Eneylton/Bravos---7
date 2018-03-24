package com.java.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.java.dao.AgendaDAO;
import com.java.modelo.Agenda;
import com.java.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Agenda.class)
public class AgendaConverter implements Converter {

	private AgendaDAO agendaDAO;
	
	public AgendaConverter() {
		this.agendaDAO = CDIServiceLocator.getBean(AgendaDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		Agenda retorno = null;
		
		try {
		
			if (value != null) {
				retorno = this.agendaDAO.retornarAgendaPorID(new Long(value));
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Agenda) value).getId();
			String retorno = (codigo == null ? null : codigo.toString());
			return retorno;
		}
		
		return "";
	}

}