package com.java.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.conexao.ConnectionFactory;
import com.java.modelo.Agenda2;

public class MapaSimuladorDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Connection con;

	

	public List<Agenda2> retornoAlunoMapa(Long id) throws SQLException {

		List<Agenda2> lista = new ArrayList<Agenda2>();

		String sql = "SELECT " 
						+ "s.id as id_simulador, "
						+ "s.status, "
						+ "s.inicio, "
						+ "s.fim "
		
						+ "FROM "
						+ "Simulador AS s "
						+ "INNER JOIN "
						+ "aluno as al ON (al.id = s.aluno_id) WHERE al.id = ? order by al.id ";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);

		stmt.setLong(1, id);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {

			Agenda2 agenda2 = new Agenda2();
			agenda2.setId(rs.getLong("id_simulador"));
			agenda2.setInicio(rs.getDate("inicio"));
			agenda2.setFim(rs.getDate("fim"));
			agenda2.setStatus(rs.getBoolean("status"));

			lista.add(agenda2);

		}

		stmt.close();
		con.close();

		return lista;

	}

}