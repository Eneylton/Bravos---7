package com.java.controller.tipoVei;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.java.dao.TipoVeiDAO;
import com.java.modelo.TipoVei;
import com.java.util.jsf.FacesUtil;

@ManagedBean
@ViewScoped
public class TipoVeiculoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private TipoVeiDAO tipoDAO = new TipoVeiDAO();

	private TipoVei tipoVei = new TipoVei();

	private List<TipoVei> listarTipoVeis = new ArrayList<>();

	@PostConstruct
	public void init() {

		try {
			
			listarTipoVeis = tipoDAO.listarTodos();

		} catch (SQLException e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public TipoVei getTipoVei() {
		return tipoVei;
	}

	public void setTipoVei(TipoVei tipoVei) {
		this.tipoVei = tipoVei;
	}

	public List<TipoVei> getListarTipoVeis() {
		return listarTipoVeis;
	}

	public void setListarTipoVeis(List<TipoVei> listarTipoVeis) {
		this.listarTipoVeis = listarTipoVeis;
	}

}