package com.java.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.java.dao.TurmaDAO;
import com.java.modelo.Turma;
import com.java.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Turma.class)
public class TurmaConverter implements Converter {

	private TurmaDAO turmaDAO;
	
	public TurmaConverter() {
		this.turmaDAO = CDIServiceLocator.getBean(TurmaDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		Turma retorno = null;
		
		try {
		
			if (value != null) {
				retorno = this.turmaDAO.retornarTurmaPorID(new Long(value));
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Turma) value).getId();
			String retorno = (codigo == null ? null : codigo.toString());
			return retorno;
		}
		
		return "";
	}

}