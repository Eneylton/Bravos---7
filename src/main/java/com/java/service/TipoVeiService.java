package com.java.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import com.java.dao.TipoVeiDAO;
import com.java.modelo.TipoVei;

public class TipoVeiService implements Serializable {
	
	private static final long serialVersionUID = 1L;
		
	private TipoVeiDAO tipoVeiDAO = new TipoVeiDAO();
	
	
	public TipoVei retornarTipoVeiPorID(Long id) throws ClassNotFoundException, SQLException {
		return tipoVeiDAO.retornarTipoVeiPorID(id);
	}
	

	public List<TipoVei> listarTodos() throws SQLException {
		return tipoVeiDAO.listarTodos();
	}
	
	public void incluir(TipoVei tipoVei) throws SQLException {
		tipoVeiDAO.incluir(tipoVei);
	}

	public void alterar(TipoVei tipoVei) throws SQLException {
		tipoVeiDAO.alterar(tipoVei);
	}

	public void excluir(TipoVei tipoVei) throws SQLException {
		tipoVeiDAO.excluir(tipoVei);
	}
		
}