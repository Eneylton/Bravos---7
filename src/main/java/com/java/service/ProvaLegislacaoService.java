package com.java.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import com.java.dao.ProvaLegislacaoDAO;
import com.java.modelo.Provalegislacao;

public class ProvaLegislacaoService implements Serializable {
	
	private static final long serialVersionUID = 1L;
		
	@Inject
	private ProvaLegislacaoDAO provalegislacaoDAO;

	public Provalegislacao retornarProvalegislacaoPorID(Long id) throws ClassNotFoundException, SQLException {
		return provalegislacaoDAO.retornarProvalegislacaoPorID(id);
	}

	public List<Provalegislacao> listarTodos() throws SQLException {
		return provalegislacaoDAO.listarTodos();
	}
	
	public void incluir(Provalegislacao provalegislacao) throws SQLException {
		provalegislacaoDAO.incluir(provalegislacao);
	}

	public void alterar(Provalegislacao provalegislacao) throws SQLException {
		provalegislacaoDAO.alterar(provalegislacao);
	}
	
	public void alterarTab(Provalegislacao provalegislacao) throws SQLException {
		provalegislacaoDAO.alterarTab(provalegislacao);
	}

	public void excluir(Provalegislacao Provalegislacao) throws SQLException {
		provalegislacaoDAO.excluir(Provalegislacao);
	}

}
