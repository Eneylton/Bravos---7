package com.java.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import com.java.dao.AutoEscolaDAO;
import com.java.modelo.AutoEscola;

public class AutoEscolaService implements Serializable {
	
	private static final long serialVersionUID = 1L;
		
	private AutoEscolaDAO autoEscolaDAO = new AutoEscolaDAO();
	
	
	public AutoEscola retornarAutoEscolaPorID(Long id) throws ClassNotFoundException, SQLException {
		return autoEscolaDAO.retornarAutoEscolaPorID(id);
	}
	
	public List<AutoEscola> listarUsuariosPorAutoEscola(Long id) throws SQLException {
		return autoEscolaDAO.listarUsuariosPorAutoEscola(id);
	}
	
	public List<AutoEscola> listarUsuarioPorAutoEscola() throws SQLException {
		return autoEscolaDAO.listarUsuarioPorAutoEscola();
	}

	public List<AutoEscola> listarTodos() throws SQLException {
		return autoEscolaDAO.listarTodos();
	}
	
	public void incluir(AutoEscola autoEscola) throws SQLException {
		autoEscolaDAO.incluir(autoEscola);
	}

	public void alterar(AutoEscola autoEscola) throws SQLException {
		autoEscolaDAO.alterar(autoEscola);
	}

	public void excluir(AutoEscola autoEscola) throws SQLException {
		autoEscolaDAO.excluir(autoEscola);
	}
		
}