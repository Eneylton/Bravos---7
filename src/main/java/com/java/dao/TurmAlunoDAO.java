package com.java.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.conexao.ConnectionFactory;
import com.java.modelo.TurmAluno;

public class TurmAlunoDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Connection con;

	public TurmAluno retornarTurmaAlunoID(Long id) throws ClassNotFoundException, SQLException {

		String sql = "SELECT aluno_id,turma_id FROM turmaluno where id = ? ";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, id);

		ResultSet rs = stmt.executeQuery();

		TurmAluno turmAluno = null;

		while (rs.next()) {

			turmAluno = new TurmAluno();
			turmAluno.setId(rs.getLong("id"));
			
		

		}

		stmt.close();
		con.close();

		return turmAluno;

	}

	public List<TurmAluno> listarTodos() throws SQLException {

		List<TurmAluno> lista = new ArrayList<TurmAluno>();

		String sql = "select id,aluno_id,turma_id from turmaluno order by id desc ";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		TurmAluno turmAluno = null;

		while (rs.next()) {

			turmAluno = new TurmAluno();
			turmAluno.setId(rs.getLong("id"));
			lista.add(turmAluno);

		}

		stmt.close();
		con.close();

		return lista;

	}

	public void incluir(TurmAluno turmaluno) throws SQLException {

		con = new ConnectionFactory().getConnection();

		String sql = "INSERT INTO turmaluno (aluno_id,turma_id) " + "VALUES (?,?)";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setLong(1, turmaluno.getAluno().getId());
		stmt.setLong(2, turmaluno.getTurma().getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

	public void alterar(TurmAluno turmaluno) throws SQLException {

		con = new ConnectionFactory().getConnection();

		/*
		 * SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd"); String
		 * dataFormatada = dt1.format(CarroAcessorio.getData());
		 */

		String sql = "update turmaluno set " + "aluno_id = ?, turma_id = ?  where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setLong(1, turmaluno.getAluno().getId());
		stmt.setLong(2, turmaluno.getTurma().getId());
        stmt.setLong(3, turmaluno.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

	public void excluir(TurmAluno turmaluno) throws SQLException {

		con = new ConnectionFactory().getConnection();

		String sql = "delete from turmaluno where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setLong(1, turmaluno.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

}
