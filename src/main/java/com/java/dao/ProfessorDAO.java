package com.java.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.conexao.ConnectionFactory;
import com.java.modelo.Professor;

public class ProfessorDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Connection con;

	public Professor retornarProfessorPorID(Long id) throws ClassNotFoundException, SQLException {

		String sql = "select id,nome from professor where id = ? ";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, id);

		ResultSet rs = stmt.executeQuery();

		Professor professor = null;

		while (rs.next()) {

			professor = new Professor();

			professor.setId(rs.getLong("id"));
			professor.setNome(rs.getString("nome"));
		

		}

		stmt.close();
		con.close();

		return professor;

	}

	public List<Professor> listarTodos() throws SQLException {

		List<Professor> lista = new ArrayList<Professor>();

		String sql = "select id,nome from professor order by id desc ";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		Professor professor = null;

		while (rs.next()) {

			professor = new Professor();

			professor.setId(rs.getLong("id"));
			professor.setNome(rs.getString("nome"));
		

			lista.add(professor);

		}

		stmt.close();
		con.close();

		return lista;

	}

	public void incluir(Professor professor) throws SQLException {

		con = new ConnectionFactory().getConnection();

		/*SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
		String dataFormatada = dt1.format(professor.getData());*/

		String sql = "INSERT INTO professor (nome) " + "VALUES (?)";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setString(1, professor.getNome());
	

		stmt.execute();
		stmt.close();
		con.close();

	}

	public void alterar(Professor professor) throws SQLException {

		con = new ConnectionFactory().getConnection();

		/*SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
		String dataFormatada = dt1.format(professor.getData());*/

		String sql = "update professor set " + "nome = ? where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setString(1, professor.getNome());

		stmt.setLong(2, professor.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

	public void excluir(Professor professor) throws SQLException {

		con = new ConnectionFactory().getConnection();

		String sql = "delete from professor where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setLong(1, professor.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

}
