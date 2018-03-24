package com.java.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.java.dao.ServicoDAO;
import com.java.modelo.Servico;
import com.java.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Servico.class)
public class ServicoConverter implements Converter {

	private ServicoDAO servicoDAO;
	
	public ServicoConverter() {
		this.servicoDAO = CDIServiceLocator.getBean(ServicoDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		Servico retorno = null;
		
		try {
			if (value != null) {
			retorno = this.servicoDAO.retornarServicoPorID(new Long(value));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Servico) value).getId();
			String retorno = (codigo == null ? null : codigo.toString());
			return retorno;
		}
		return "";
	}

}