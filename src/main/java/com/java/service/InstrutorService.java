package com.java.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import com.java.dao.InstrutorDAO;
import com.java.modelo.Agenda;
import com.java.modelo.Instrutor;

public class InstrutorService implements Serializable {

	private static final long serialVersionUID = 1L;

	private InstrutorDAO instrutorDAO = new InstrutorDAO();

	public Instrutor retornarInstrutorPorID(Long id) throws ClassNotFoundException, SQLException {
		return instrutorDAO.retornarInstrutorPorID(id);
	}

	public List<Instrutor> listarTodos() throws SQLException {
		return instrutorDAO.listarTodos();
	}
	
	public List<Agenda> mapaInstrutor(Long id) throws SQLException {
		return instrutorDAO.mapaInstrutor(id);
	}

	public void incluir(Instrutor instrutor) throws SQLException {
		instrutorDAO.incluir(instrutor);
	}

	public void alterar(Instrutor instrutor) throws SQLException {
		instrutorDAO.alterar(instrutor);
	}

	public void excluir(Instrutor instrutor) throws SQLException {
		instrutorDAO.excluir(instrutor);
	}

}