package com.java.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.conexao.ConnectionFactory;
import com.java.modelo.Veiculo;

public class VeiculoDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Connection con;

	
	public List<Veiculo> listarTodos() throws SQLException {

		List<Veiculo> lista = new ArrayList<Veiculo>();

		String sql = "select id,fabricante,modelo,veiculo from veiculo order by id desc ";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		Veiculo veiculo = null;

		while (rs.next()) {

			veiculo = new Veiculo();

			veiculo.setId(rs.getLong("id"));
			veiculo.setFabricante(rs.getString("fabricante"));
			veiculo.setModelo(rs.getString("modelo"));
			veiculo.setVeiculo(rs.getString("veiculo"));
			
			
			lista.add(veiculo);

		}

		stmt.close();
		con.close();

		return lista;

	}

	

}