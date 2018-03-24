package com.java.service;

import java.io.Serializable;
import java.sql.SQLException;

import com.java.dao.PainelFinanceiroDAO;

public class PainelFinanceiroService implements Serializable {

	private static final long serialVersionUID = 1L;

	private PainelFinanceiroDAO painelFinanceiroDAO = new PainelFinanceiroDAO();
	
	
	public double getTotalAtualizado(Long id) throws SQLException {
		return painelFinanceiroDAO.getTotalAtualizado(id);
	}
	
	
	public double getTotalReceita(Long id) throws SQLException {
		return painelFinanceiroDAO.getTotalReceita(id);
	}
	
	
	public double getTotalDespesa(Long id) throws SQLException {
		return painelFinanceiroDAO.getTotalDespesa(id);
	}
	
	
	public double getTotalBancoReceita(Long id) throws SQLException {
		return painelFinanceiroDAO.getTotalBancoReceita(id);
	
	}
	
	public double getTotalDespesaMovimentada(Long id) throws SQLException {
		return painelFinanceiroDAO.getTotalDespesaMovimentada(id);
	}

	public double getTotalBancoDespesa(Long id) throws SQLException {
		return painelFinanceiroDAO.getTotalBancoDespesa(id);
	}
	
	
	public double getPagar(Long id) throws SQLException {
		return painelFinanceiroDAO.getPagar(id);
	}
	
	public double getReceber(Long id) throws SQLException {
		return painelFinanceiroDAO.getReceber(id);
	}
	
	public double getRecebido(Long id) throws SQLException {
		return painelFinanceiroDAO.getRecebido(id);
	}
	
	
	public double getTotalAtrasado(Long id) throws SQLException {
		return painelFinanceiroDAO.getTotalAtrasado(id);
	}
	
	public double getTotalReceitaDIA(Long id) throws SQLException {
		return painelFinanceiroDAO.getTotalReceitaDIA(id);
	}
	
	public double getTotalDespesaDIA(Long id) throws SQLException {
		return painelFinanceiroDAO.getTotalDespesaDIA(id);
	}


}
