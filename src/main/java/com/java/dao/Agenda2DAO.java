package com.java.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.java.conexao.ConnectionFactory;
import com.java.modelo.Agenda2;
import com.java.modelo.Aluno;

public class Agenda2DAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Connection con;

	public Agenda2 retornarAgenda2PorID(Long id) throws ClassNotFoundException, SQLException {

		String sql = "select id,titulo,inicio,fim,descricao,status from Simulador where id = ? ";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, id);

		ResultSet rs = stmt.executeQuery();

		Agenda2 agenda2 = null;

		while (rs.next()) {

			agenda2 = new Agenda2();

			agenda2.setId(rs.getLong("id"));
			agenda2.setTitulo(rs.getString("titulo"));
			agenda2.setInicio(rs.getTimestamp("inicio"));
			agenda2.setFim(rs.getTimestamp("fim"));
			agenda2.setDescricao(rs.getString("descricao"));
			agenda2.setStatus(rs.getBoolean("status"));
		}

		stmt.close();
		con.close();

		return agenda2;

	}

	public List<Agenda2> listarTodos() throws SQLException {

		List<Agenda2> lista = new ArrayList<Agenda2>();

		String sql = "SELECT "
						+ "s.id as id_simulador, "
						+ "s.titulo, " 
						+ "s.status, "
						+ "s.inicio, "
						+ "s.fim, "
						+ "s.descricao, "
						+ "al.id as id_aluno, "
						+ "al.nome as nome "
						+ "FROM "
						+ "Simulador AS s "
						+ "INNER JOIN "
						+ "aluno as al ON (al.id = s.aluno_id) order by al.id";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);
		
	

		ResultSet rs = stmt.executeQuery();

		Agenda2 agenda2 = null;

		while (rs.next()) {

			agenda2 = new Agenda2();

			agenda2.setId(rs.getLong("id_simulador"));
			agenda2.setTitulo(rs.getString("titulo"));
			agenda2.setMostrarDataInicio(rs.getString("inicio"));
			agenda2.setInicio(rs.getTimestamp("inicio"));
			agenda2.setMostrarDataFim(rs.getString("fim"));
			agenda2.setFim(rs.getTimestamp("fim"));
			agenda2.setDescricao(rs.getString("descricao"));
			agenda2.setStatus(rs.getBoolean("status"));
			
			
			Aluno  al = new Aluno();
			al.setId(rs.getLong("id_aluno"));
			al.setNome(rs.getString("nome"));
			agenda2.setAluno(al);
			

			lista.add(agenda2);

		}

		stmt.close();
		con.close();

		return lista;

	}

	public void incluir(Agenda2 agenda2) throws SQLException {

		con = new ConnectionFactory().getConnection();

		SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String dataFormatada = dt1.format(agenda2.getInicio());

		String dataFormatada2 = dt1.format(agenda2.getFim());

		String sql = "INSERT INTO Simulador (titulo,inicio,fim,descricao,status,aluno_id) " + "VALUES (?,?,?,?,?,?)";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setString(1, agenda2.getTitulo());
		stmt.setString(2, dataFormatada);
		stmt.setString(3, dataFormatada2);
		stmt.setString(4, agenda2.getDescricao());
		stmt.setBoolean(5, agenda2.isStatus());
		stmt.setLong(6, agenda2.getAluno().getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

	public void alterar(Agenda2 agenda2) throws SQLException {

		con = new ConnectionFactory().getConnection();

		SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String dataFormatada1 = dt1.format(agenda2.getInicio());

		String dataFormatada2 = dt1.format(agenda2.getFim());

		String sql = "update Simulador set " + "titulo = ?, inicio = ?,fim = ?, "
				+ "descricao = ?,status = ?  where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setString(1, agenda2.getTitulo());
		stmt.setString(2, dataFormatada1);
		stmt.setString(3, dataFormatada2);
		stmt.setString(4, agenda2.getDescricao());
		stmt.setBoolean(5, agenda2.isStatus());
		stmt.setLong(6, agenda2.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

	public void excluir(Agenda2 agenda2) throws SQLException {

		con = new ConnectionFactory().getConnection();

		String sql = "delete from Simulador where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setLong(1, agenda2.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

}
