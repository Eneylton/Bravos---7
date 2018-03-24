package com.java.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import com.java.dao.Mapa2DAO;
import com.java.modelo.Mapa2;

public class Mapa2Service implements Serializable {

	private static final long serialVersionUID = 1L;

	private Mapa2DAO mapa2DAO = new Mapa2DAO();

	public Mapa2 retornarMapa2PorID(Long id) throws ClassNotFoundException, SQLException {
		return mapa2DAO.retornarMapa2PorID(id);
	}

	public List<Mapa2> listarTodos() throws SQLException {
		return mapa2DAO.listarTodos();
	}
	
	public void incluir(Mapa2 mapa2) throws SQLException {
		mapa2DAO.incluir(mapa2);
	}

	public void alterar(Mapa2 mapa2) throws SQLException {
		mapa2DAO.alterar(mapa2);
	}

	public void excluir(Mapa2 mapa2) throws SQLException {
		mapa2DAO.excluir(mapa2);
	}

}