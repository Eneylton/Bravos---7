package com.java.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.java.dao.ProfessorDAO;
import com.java.modelo.Professor;
import com.java.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Professor.class)
public class ProfessorConverter implements Converter {

	private ProfessorDAO professorDAO;
	
	public ProfessorConverter() {
		this.professorDAO = CDIServiceLocator.getBean(ProfessorDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		Professor retorno = null;
		
		try {
		
			if (value != null) {
				retorno = this.professorDAO.retornarProfessorPorID(new Long(value));
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Professor) value).getId();
			String retorno = (codigo == null ? null : codigo.toString());
			return retorno;
		}
		
		return "";
	}

}