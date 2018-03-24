package com.java.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.java.dao.ProvaLegislacaoDAO;
import com.java.modelo.Provalegislacao;
import com.java.modelo.Servico;
import com.java.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=ProvaLegislacaoDAO.class)
public class ProvaLegislacaoConverter implements Converter {

	private ProvaLegislacaoDAO provaLegislacaoDAO;
	
	public ProvaLegislacaoConverter() {
		this.provaLegislacaoDAO = CDIServiceLocator.getBean(ProvaLegislacaoDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		Provalegislacao retorno = null;
		
		try {
			if (value != null) {
			retorno = this.provaLegislacaoDAO.retornarProvalegislacaoPorID(new Long(value));
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