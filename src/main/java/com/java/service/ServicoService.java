package com.java.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import com.java.dao.ServicoDAO;
import com.java.modelo.Servico;

public class ServicoService implements Serializable {
	
	private static final long serialVersionUID = 1L;
		
	@Inject
	private ServicoDAO servicoDAO;

	public Servico retornarServicoPorID(Long id) throws ClassNotFoundException, SQLException {
		return servicoDAO.retornarServicoPorID(id);
	}

	public List<Servico> listarTodos() throws SQLException {
		return servicoDAO.listarTodos();
	}
	
	public void incluir(Servico servico) throws SQLException {
		servicoDAO.incluir(servico);
	}

	public void alterar(Servico servico) throws SQLException {
		servicoDAO.alterar(servico);
	}

	public void excluir(Servico servico) throws SQLException {
		servicoDAO.excluir(servico);
	}

}
