package com.java.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.conexao.ConnectionFactory;
import com.java.modelo.Mapa;

public class MapaDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Connection con;

	

	public List<Mapa> listarTodos() throws SQLException {

		List<Mapa> lista = new ArrayList<Mapa>();

		String sql = "SELECT id,nome,latitude,longitude FROM mapa";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		Mapa mapa = null;

		while (rs.next()) {

			mapa = new Mapa();

			mapa.setId(rs.getLong("id"));
			mapa.setNome(rs.getString("nome"));
			mapa.setLatitude(rs.getString("latitude"));
			mapa.setLongitude(rs.getString("longitude"));
			
			
			lista.add(mapa);

		}

		stmt.close();
		con.close();

		return lista;

	}

	
}