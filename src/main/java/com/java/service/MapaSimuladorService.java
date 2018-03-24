package com.java.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import com.java.dao.MapaSimuladorDAO;
import com.java.modelo.Agenda2;

public class MapaSimuladorService implements Serializable {
	
	private static final long serialVersionUID = 1L;
		
	private MapaSimuladorDAO mapaSimuladorDAO = new MapaSimuladorDAO();
	
	
	public List<Agenda2> retornoAlunoMapa(Long id) throws SQLException {
		return mapaSimuladorDAO.retornoAlunoMapa(id);
	}
	

		
}