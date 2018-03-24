package com.java.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import com.java.dao.SexoDAO;
import com.java.modelo.Sexo;

public class SexoService implements Serializable {
	
	private static final long serialVersionUID = 1L;
		
	private SexoDAO sexoDAO = new SexoDAO();
	
	
	public Sexo retornarSexoPorID(Long id) throws ClassNotFoundException, SQLException {
		return sexoDAO.retornarSexoPorID(id);
	}
	

	public List<Sexo> listarTodos() throws SQLException {
		return sexoDAO.listarTodos();
	}
	
	public void incluir(Sexo sexo) throws SQLException {
		sexoDAO.incluir(sexo);
	}

	public void alterar(Sexo sexo) throws SQLException {
		sexoDAO.alterar(sexo);
	}

	public void excluir(Sexo sexo) throws SQLException {
		sexoDAO.excluir(sexo);
	}
		
}