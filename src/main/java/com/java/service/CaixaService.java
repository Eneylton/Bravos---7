package com.java.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import com.java.dao.CaixaDAO;
import com.java.modelo.Caixa;

public class CaixaService implements Serializable {
	
	private static final long serialVersionUID = 1L;
		
	private CaixaDAO caixaDAO = new CaixaDAO();
	
	
	public Caixa retornarCaixaPorID(Long id) throws ClassNotFoundException, SQLException {
		return caixaDAO.retornarCaixaPorID(id);
	}
	

	public List<Caixa> listarTodos() throws SQLException {
		return caixaDAO.listarTodos();
	}
	
	public void incluir(Caixa caixa) throws SQLException {
		caixaDAO.incluir(caixa);
	}

	public void alterar(Caixa caixa) throws SQLException {
		caixaDAO.alterar(caixa);
	}

	public void excluir(Caixa caixa) throws SQLException {
		caixaDAO.excluir(caixa);
	}
		
}