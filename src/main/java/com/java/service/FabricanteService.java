package com.java.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import com.java.dao.FabricanteDAO;
import com.java.modelo.Fabricante;

public class FabricanteService implements Serializable {

	private static final long serialVersionUID = 1L;

	private FabricanteDAO fabricanteDAO = new FabricanteDAO();

	public Fabricante retornarFabricantePorID(Long id) throws ClassNotFoundException, SQLException {
		return fabricanteDAO.retornarFabricantePorID(id);
	}

	public List<Fabricante> buscarTipoFabrigante(Long id) throws SQLException {
		return fabricanteDAO.buscarTipoFabrigante(id);

	}

	public List<Fabricante> listarTodos() throws SQLException {
		return fabricanteDAO.listarTodos();
	}

	public void incluir(Fabricante fabricante) throws SQLException {
		fabricanteDAO.incluir(fabricante);
	}

	public void alterar(Fabricante fabricante) throws SQLException {
		fabricanteDAO.alterar(fabricante);
	}

	public void excluir(Fabricante fabricante) throws SQLException {
		fabricanteDAO.excluir(fabricante);
	}

}