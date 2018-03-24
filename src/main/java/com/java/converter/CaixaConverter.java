package com.java.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.java.dao.CaixaDAO;
import com.java.modelo.Caixa;
import com.java.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Caixa.class)
public class CaixaConverter implements Converter {

	private CaixaDAO caixaDao;
	
	public CaixaConverter() {
		this.caixaDao = CDIServiceLocator.getBean(CaixaDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		Caixa retorno = null;
		
		try {
			if (value != null) {
			retorno = this.caixaDao.retornarCaixaPorID(new Long(value));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Caixa) value).getId();
			String retorno = (codigo == null ? null : codigo.toString());
			return retorno;
		}
		return "";
	}

}