package com.java.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.java.dao.FinanceiroDAO;
import com.java.modelo.Financeiro;
import com.java.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Financeiro.class)
public class FinanceiroConverter implements Converter {

	private FinanceiroDAO financeiroDao;
	
	public FinanceiroConverter() {
		this.financeiroDao = CDIServiceLocator.getBean(FinanceiroDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		Financeiro retorno = null;
		
		try {
			if (value != null) {
			retorno = this.financeiroDao.retornarFinanceiroPorID(new Long(value));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Financeiro) value).getId();
			String retorno = (codigo == null ? null : codigo.toString());
			return retorno;
		}
		return "";
	}

}