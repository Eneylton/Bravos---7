package com.java.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.java.dao.CarroDAO;
import com.java.modelo.Carro;
import com.java.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Carro.class)
public class CarroConverter implements Converter {

	private CarroDAO carroDao;
	
	public CarroConverter() {
		this.carroDao = CDIServiceLocator.getBean(CarroDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		Carro retorno = null;
		
		try {
			if (value != null) {
			retorno = this.carroDao.retornarCarroPorID(new Long(value));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Carro) value).getId();
			String retorno = (codigo == null ? null : codigo.toString());
			return retorno;
		}
		return "";
	}

}