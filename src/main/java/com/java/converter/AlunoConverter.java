package com.java.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.java.dao.AlunoDAO;
import com.java.modelo.Aluno;
import com.java.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Aluno.class)
public class AlunoConverter implements Converter {

	private AlunoDAO alunoDao;

	public AlunoConverter() {
		this.alunoDao = CDIServiceLocator.getBean(AlunoDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		Aluno retorno = null;

		try {
			if (value != null) {
				retorno = this.alunoDao.retornarAlunoPorID(new Long(value));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Aluno) value).getId();
			String retorno = (codigo == null ? null : codigo.toString());
			return retorno;
		}
		return "";
	}

}