package com.java.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import com.java.dao.ProvaTrafegoDAO;
import com.java.modelo.ProvaTrafego;

public class ProvaTrafegoService implements Serializable {
	
	private static final long serialVersionUID = 1L;
		
	@Inject
	private ProvaTrafegoDAO provaTrafegoDAO;

	public ProvaTrafego retornarProvaTrafegoPorID(Long id) throws ClassNotFoundException, SQLException {
		return provaTrafegoDAO.retornarProvaTrafegoPorID(id);
	}

	public List<ProvaTrafego> listarTodos() throws SQLException {
		return provaTrafegoDAO.listarTodos();
	}
	
	public void incluir(ProvaTrafego provaTrafego) throws SQLException {
		provaTrafegoDAO.incluir(provaTrafego);
	}

	public void alterar(ProvaTrafego provaTrafego) throws SQLException {
		provaTrafegoDAO.alterar(provaTrafego);
	}
	
	public void alterarTab(ProvaTrafego provaTrafego) throws SQLException {
		provaTrafegoDAO.alterarTab(provaTrafego);
	}

	public void excluir(ProvaTrafego provaTrafego) throws SQLException {
		provaTrafegoDAO.excluir(provaTrafego);
	}

}
