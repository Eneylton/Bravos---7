package com.java.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import com.java.dao.CarroDAO;
import com.java.modelo.Carro;

public class CarroService implements Serializable {
	
	private static final long serialVersionUID = 1L;
		
	private CarroDAO carroDAO = new CarroDAO();
	
	
	public Carro retornarCarroPorID(Long id) throws ClassNotFoundException, SQLException {
		return carroDAO.retornarCarroPorID(id);
	}
	

	public List<Carro> listarTodos() throws SQLException {
		return carroDAO.listarTodos();
	}
	
	public void incluir(Carro carro) throws SQLException {
		carroDAO.incluir(carro);
	}

	public void alterar(Carro carro) throws SQLException {
		carroDAO.alterar(carro);
	}

	public void excluir(Carro carro) throws SQLException {
		carroDAO.excluir(carro);
	}
		
}