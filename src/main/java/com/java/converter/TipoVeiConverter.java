package com.java.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.java.dao.TipoVeiDAO;
import com.java.modelo.TipoVei;
import com.java.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=TipoVei.class)
public class TipoVeiConverter implements Converter {

	private TipoVeiDAO tipoVeiDao;
	
	public TipoVeiConverter() {
		this.tipoVeiDao = CDIServiceLocator.getBean(TipoVeiDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		TipoVei retorno = null;
		
		try {
			if (value != null) {
			retorno = this.tipoVeiDao.retornarTipoVeiPorID(new Long(value));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((TipoVei) value).getId();
			String retorno = (codigo == null ? null : codigo.toString());
			return retorno;
		}
		return "";
	}

}