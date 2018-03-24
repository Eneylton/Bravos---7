package com.java.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.java.dao.CategoriaDAO;
import com.java.modelo.Categoria;
import com.java.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Categoria.class)
public class CategoriaConverter implements Converter {

	private CategoriaDAO categoriaDao;
	
	public CategoriaConverter() {
		this.categoriaDao = CDIServiceLocator.getBean(CategoriaDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		Categoria retorno = null;
		
		try {
			if (value != null) {
			retorno = this.categoriaDao.retornarCategoriaPorID(new Long(value));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Categoria) value).getId();
			String retorno = (codigo == null ? null : codigo.toString());
			return retorno;
		}
		return "";
	}

}