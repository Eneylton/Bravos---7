package com.java.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import com.java.dao.ProfessorDAO;
import com.java.modelo.Professor;


public class ProfessorService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ProfessorDAO professorDAO;
	
	public Professor retornarProfessorPorID(Long id) throws ClassNotFoundException, SQLException {
		return professorDAO.retornarProfessorPorID(id);
	}
	
	public List<Professor> listarTodos() throws SQLException {
		return professorDAO.listarTodos();
	}
	
	public void incluir(Professor professor) throws SQLException {
		professorDAO.incluir(professor);
	}
	
	public void alterar(Professor professor) throws SQLException {
		professorDAO.alterar(professor);
	}
	
	public void excluir(Professor professor) throws SQLException {
		professorDAO.excluir(professor);
	}
	
}
