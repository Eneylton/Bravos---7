package com.java.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.inject.Inject;

import com.java.dao.Agenda2DAO;
import com.java.modelo.Agenda2;


public class Agenda2Service implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Agenda2DAO agenda2DAO;
	
	public Agenda2 retornarAgenda2PorID(Long id) throws ClassNotFoundException, SQLException {
		return agenda2DAO.retornarAgenda2PorID(id);
	}
	
	public List<Agenda2> listarTodos() throws SQLException, ParseException {
		return agenda2DAO.listarTodos();
	}
	
	public void incluir(Agenda2 agenda2) throws SQLException {
		agenda2DAO.incluir(agenda2);
	}
	
	public void alterar(Agenda2 agenda2) throws SQLException {
		agenda2DAO.alterar(agenda2);
	}
	
	public void excluir(Agenda2 agenda2) throws SQLException {
		agenda2DAO.excluir(agenda2);
	}
	
}
