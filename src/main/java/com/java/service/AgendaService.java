package com.java.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.inject.Inject;

import com.java.dao.AgendaDAO;
import com.java.modelo.Agenda;


public class AgendaService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private AgendaDAO agendaDAO;
	
	public Agenda retornarAgendaPorID(Long id) throws ClassNotFoundException, SQLException {
		return agendaDAO.retornarAgendaPorID(id);
	}
	
	public List<Agenda> listarTodos() throws SQLException, ParseException {
		return agendaDAO.listarTodos();
	}
	
	public void incluir(Agenda agenda) throws SQLException {
		agendaDAO.incluir(agenda);
	}
	
	public void alterar(Agenda agenda) throws SQLException {
		agendaDAO.alterar(agenda);
	}
	
	public void excluir(Agenda agenda) throws SQLException {
		agendaDAO.excluir(agenda);
	}
	
}
