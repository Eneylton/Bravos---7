package com.java.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.conexao.ConnectionFactory;
import com.java.modelo.Servico;

public class ServicoDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Connection con;

	public Servico retornarServicoPorID(Long id) throws ClassNotFoundException, SQLException {

		String sql = "select id,descricao,valor from servico where id = ? ";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, id);

		ResultSet rs = stmt.executeQuery();

		Servico servico = null;

		while (rs.next()) {

			servico = new Servico();

			servico.setId(rs.getLong("id"));
			servico.setDescricao(rs.getString("descricao"));
			servico.setValor(rs.getDouble("valor"));

		}

		stmt.close();
		con.close();

		return servico;

	}

	public List<Servico> listarTodos() throws SQLException {

		List<Servico> lista = new ArrayList<Servico>();

		String sql = "select id,descricao,valor from servico order by descricao ASC ";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		Servico servico = null;

		while (rs.next()) {

			servico = new Servico();

			servico.setId(rs.getLong("id"));
			servico.setDescricao(rs.getString("descricao"));
			servico.setValor(rs.getDouble("valor"));

			lista.add(servico);

		}

		stmt.close();
		con.close();

		return lista;

	}

	public void incluir(Servico servico) throws SQLException {

		con = new ConnectionFactory().getConnection();

		String sql = "INSERT INTO servico (descricao,valor) " + "VALUES (?,?)";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setString(1, servico.getDescricao());
		stmt.setDouble(2, servico.getValor());

		stmt.execute();
		stmt.close();
		con.close();

	}
	
	

	public void alterar(Servico servico) throws SQLException {

		con = new ConnectionFactory().getConnection();

	

		String sql = "update servico set " + "descricao = ?,valor=? where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setString(1, servico.getDescricao());
		stmt.setDouble(2, servico.getValor());
		stmt.setLong(3, servico.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

	public void excluir(Servico Servico) throws SQLException {

		con = new ConnectionFactory().getConnection();

		String sql = "delete from servico where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setLong(1, Servico.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

}
