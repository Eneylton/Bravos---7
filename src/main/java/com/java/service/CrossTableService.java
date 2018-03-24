package com.java.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import com.java.dao.CrossTableDAO;
import com.java.modelo.CrossTable;


public class CrossTableService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CrossTableDAO crossTableDAO;
	
	
	
	public List<CrossTable> listarReceita() throws SQLException {
		return crossTableDAO.listarReceita();
	}
	
	public List<CrossTable> listarReceitaBanco() throws SQLException {
		return crossTableDAO.listarReceitaBanco();
	}
	
	public List<CrossTable> listarDespesasBanco() throws SQLException {
		return crossTableDAO.listarDespesasBanco();
	}
	
	public List<CrossTable> listarDespesa() throws SQLException {
		return crossTableDAO.listarDespesa();
	}
	
	
	
	
		
}
