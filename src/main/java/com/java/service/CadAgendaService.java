package com.java.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import com.java.dao.CadAgendaDAO;
import com.java.modelo.Agenda;
import com.java.modelo.CadAgenda;

public class CadAgendaService implements Serializable {
	
	private static final long serialVersionUID = 1L;
		
	private CadAgendaDAO cadAgendaDAO = new CadAgendaDAO();
	
	
	public CadAgenda retornarCadAgendaPorID(Long id) throws ClassNotFoundException, SQLException {
		return cadAgendaDAO.retornarCadAgendaPorID(id);
	}
	

	public List<CadAgenda> listarTodos() throws SQLException {
		return cadAgendaDAO.listarTodos();
	}
	
	public List<Agenda> retornoAlunoMapa(Long id) throws SQLException {
		return cadAgendaDAO.retornoAlunoMapa(id);
	}
	
	public void incluir(CadAgenda cadAgenda) throws SQLException {
		cadAgendaDAO.incluir(cadAgenda);
	}

	public void alterar(CadAgenda cadAgenda) throws SQLException {
		cadAgendaDAO.alterar(cadAgenda);
	}

	public void excluir(CadAgenda cadAgenda) throws SQLException {
		cadAgendaDAO.excluir(cadAgenda);
	}
		
}