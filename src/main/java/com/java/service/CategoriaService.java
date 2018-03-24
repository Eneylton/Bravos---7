package com.java.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import com.java.dao.CategoriaDAO;
import com.java.modelo.Categoria;

public class CategoriaService implements Serializable {
	
	private static final long serialVersionUID = 1L;
		
	private CategoriaDAO categoriaDAO = new CategoriaDAO();
	
	
	public Categoria retornarCategoriaPorID(Long id) throws ClassNotFoundException, SQLException {
		return categoriaDAO.retornarCategoriaPorID(id);
	}
	

	public List<Categoria> listarTodos() throws SQLException {
		return categoriaDAO.listarTodos();
	}
	
	public void incluir(Categoria categoria) throws SQLException {
		categoriaDAO.incluir(categoria);
	}

	public void alterar(Categoria categoria) throws SQLException {
		categoriaDAO.alterar(categoria);
	}

	public void excluir(Categoria categoria) throws SQLException {
		categoriaDAO.excluir(categoria);
	}
		
}