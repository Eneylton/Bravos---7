package com.java.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import com.java.dao.PainelMapaDAO;
import com.java.modelo.Agenda;

public class PainelMapaService implements Serializable {
	
	private static final long serialVersionUID = 1L;
		
	private PainelMapaDAO painelMapaDAO = new PainelMapaDAO();
	
	
	public Agenda retornarMapaAlunoID(Long id) throws ClassNotFoundException, SQLException {
		return painelMapaDAO.retornarMapaAlunoID(id);
	}
	

	public List<Agenda> retornoAlunoMapa(Long id) throws SQLException {
		return painelMapaDAO.retornoAlunoMapa(id);
	}
}