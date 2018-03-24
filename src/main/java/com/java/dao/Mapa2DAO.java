package com.java.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.conexao.ConnectionFactory;
import com.java.modelo.Mapa2;

public class Mapa2DAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Connection con;
	
	
	public Mapa2 retornarMapa2PorID(Long id) throws ClassNotFoundException, SQLException {

		String sql = "SELECT id,nome,latitude,longitude FROM Mapa where id = ? ";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		Mapa2 mapa2 = null;

		while (rs.next()) {

			mapa2 = new Mapa2();

			mapa2.setId(rs.getLong("id"));
			mapa2.setNome(rs.getString("nome"));
			mapa2.setLatitude(rs.getFloat("latitude"));
			mapa2.setLongitude(rs.getFloat("longitude"));
			
		}

		stmt.close();
		con.close();

		return mapa2;

	}


	

	public List<Mapa2> listarTodos() throws SQLException {

		List<Mapa2> lista = new ArrayList<Mapa2>();

		String sql = "SELECT id,nome,latitude,longitude FROM Mapa order by id desc";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		Mapa2 mapa2 = null;

		while (rs.next()) {

			mapa2 = new Mapa2();

			mapa2.setId(rs.getLong("id"));
			mapa2.setNome(rs.getString("nome"));
			mapa2.setLatitude(rs.getFloat("latitude"));
			mapa2.setLongitude(rs.getFloat("longitude"));
			
			
			lista.add(mapa2);

		}

		stmt.close();
		con.close();

		return lista;

	}
	
	public void incluir(Mapa2 mapa2) throws SQLException {

		con = new ConnectionFactory().getConnection();

		String sql = "INSERT INTO Mapa (nome,latitude,longitude) " + "VALUES (?,?,?)";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setString(1, mapa2.getNome());
		stmt.setFloat(2, mapa2.getLatitude());
		stmt.setFloat(3, mapa2.getLongitude());

		stmt.execute();
		stmt.close();
		con.close();

	}
	
	

	public void alterar(Mapa2 mapa2) throws SQLException {

		con = new ConnectionFactory().getConnection();

	

		String sql = "update Mapa set " + "nome=?,latitude=?,longitude=? where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setString(1, mapa2.getNome());
		stmt.setFloat(2, mapa2.getLatitude());
		stmt.setFloat(3, mapa2.getLongitude());
		stmt.setLong(4, mapa2.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

	public void excluir(Mapa2 mapa2) throws SQLException {

		con = new ConnectionFactory().getConnection();

		String sql = "delete from Mapa where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setLong(1, mapa2.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}


	
}