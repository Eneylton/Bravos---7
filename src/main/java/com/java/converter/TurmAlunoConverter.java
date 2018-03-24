package com.java.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.java.dao.TurmAlunoDAO;
import com.java.modelo.TurmAluno;
import com.java.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=TurmAluno.class)
public class TurmAlunoConverter implements Converter {
	

	private TurmAlunoDAO turmAlunoDAO;
	
	public TurmAlunoConverter() {
		this.turmAlunoDAO = CDIServiceLocator.getBean(TurmAlunoDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		TurmAluno retorno = null;
		
		try {
		
			if (value != null) {
				retorno = this.turmAlunoDAO.retornarTurmaAlunoID(new Long(value));
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((TurmAluno) value).getId();
			String retorno = (codigo == null ? null : codigo.toString());
			return retorno;
		}
		
		return "";
	}
}