package com.java.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import com.java.dao.MapaDAO;
import com.java.modelo.Mapa;

public class MapaService implements Serializable {

	private static final long serialVersionUID = 1L;

	private MapaDAO mapaDAO = new MapaDAO();

	public List<Mapa> listarTodos() throws SQLException {
		return mapaDAO.listarTodos();
	}

}