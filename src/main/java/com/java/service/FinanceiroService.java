package com.java.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import com.java.dao.FinanceiroDAO;
import com.java.modelo.Financeiro;

public class FinanceiroService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FinanceiroDAO financeiroDAO;

	public Financeiro retornarFinanceiroPorID(Long id) throws ClassNotFoundException, SQLException {
		return financeiroDAO.retornarFinanceiroPorID(id);
	}
	

	public List<Financeiro> listarTodos() throws SQLException {
		return financeiroDAO.listarTodos();
	}
	
	public List<Financeiro> listarTodosPorData(Date data_inicio, Date data_fim) throws SQLException {
		return financeiroDAO.listarTodosPorData(data_inicio, data_fim);
	}
	
	public List<Financeiro> listarTodosUsuariosPorAutoEscola(Long id) throws SQLException {
		return financeiroDAO.listarTodosUsuariosPorAutoEscola(id);
		
	}
	
	public List<Financeiro> listarTodosUsuariosPorAutoEscola2(Long id) throws SQLException {
		return financeiroDAO.listarTodosUsuariosPorAutoEscola2(id);
	}
	
	public List<Financeiro> retorDespesas(Long id) throws SQLException {
		return financeiroDAO.retorDespesas(id);
	}
	
	
	public List<Financeiro> retornoIdRecibo(Long id) throws SQLException {
		return financeiroDAO.retornoIdRecibo(id);
	}
	
	public double getTotalData(Date data_inicio, Date data_fim) throws SQLException {
		return financeiroDAO.getTotalData(data_inicio, data_fim);
	}
	
	public void incluir(Financeiro financeiro) throws SQLException {
		financeiroDAO.incluir(financeiro);
	}
	
	public void incluirFinanceiro(Financeiro financeiro) throws SQLException {
		financeiroDAO.incluirFinanceiro(financeiro);
	}

	public void alterar(Financeiro financeiro) throws SQLException {
		financeiroDAO.alterar(financeiro);
	}

	public void excluir(Financeiro financeiro) throws SQLException {
		financeiroDAO.excluir(financeiro);
	}

}