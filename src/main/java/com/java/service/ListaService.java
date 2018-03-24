package com.java.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import com.java.dao.ListasDAO;
import com.java.modelo.Financeiro;

public class ListaService implements Serializable {

	private static final long serialVersionUID = 1L;

	private ListasDAO listaDAO = new ListasDAO();

	public List<Financeiro> retornoDespesa(Long id) throws SQLException {
		return listaDAO.retornoDespesa(id);
	}

	public List<Financeiro> contasAreceber(Long id) throws SQLException {
		return listaDAO.contasAreceber(id);
	}
	
	public List<Financeiro> retornoAreceber(Long id) throws SQLException {
		return listaDAO.retornoAreceber(id);
	}

	public List<Financeiro> contasApagar(Long id) throws SQLException {
		return listaDAO.contasApagar(id);
	}
	
	public List<Financeiro> retornoApagar(Long id) throws SQLException {
		return listaDAO.retornoApagar(id);
	}

	public double getTotalDespesa(Long id) throws SQLException {
		return listaDAO.getTotalDespesa(id);

	}

	public double getTotalAreceber(Long id) throws SQLException {

		return listaDAO.getTotalAreceber(id);
	}

	public double getTotalApagar(Long id) throws SQLException {

		return listaDAO.getTotalApagar(id);
	}

}
