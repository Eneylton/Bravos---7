package com.java.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import com.java.dao.FinanceiroAlunoDAO;
import com.java.modelo.FinanceiroAluno;

public class FinanceiroAlunoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FinanceiroAlunoDAO financeiroAlunoDAO;

	public FinanceiroAluno retornarFinanceiroAlunoPorID(Long id) throws ClassNotFoundException, SQLException {
		return financeiroAlunoDAO.retornarFinanceiroAlunoPorID(id);
	}

	public List<FinanceiroAluno> listarTodos() throws SQLException {
		return financeiroAlunoDAO.listarTodos();
	}

	public double getTotal() throws SQLException {
		return financeiroAlunoDAO.getTotal();
	}

	public double getTotalPago() throws SQLException {
		return financeiroAlunoDAO.getTotalPago();
	}

	public double getTotalAberto() throws SQLException {
		return financeiroAlunoDAO.getTotalAberto();
	}

	public void incluir(FinanceiroAluno financeiroAluno) throws SQLException {
		financeiroAlunoDAO.incluir(financeiroAluno);
	}

	public void alterar(FinanceiroAluno financeiroAluno) throws SQLException {
		financeiroAlunoDAO.alterar(financeiroAluno);
	}

	public void excluir(FinanceiroAluno financeiroAluno) throws SQLException {
		financeiroAlunoDAO.excluir(financeiroAluno);
	}

}
