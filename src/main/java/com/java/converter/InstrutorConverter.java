package com.java.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.java.dao.InstrutorDAO;
import com.java.modelo.Instrutor;
import com.java.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Instrutor.class)
public class InstrutorConverter implements Converter {

	private InstrutorDAO instrutorDao;

	public InstrutorConverter() {
		this.instrutorDao = CDIServiceLocator.getBean(InstrutorDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		Instrutor retorno = null;

		try {
			if (value != null) {
				retorno = this.instrutorDao.retornarInstrutorPorID(new Long(value));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Instrutor) value).getId();
			String retorno = (codigo == null ? null : codigo.toString());
			return retorno;
		}
		return "";
	}

}