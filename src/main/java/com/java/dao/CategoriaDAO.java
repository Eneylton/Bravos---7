package com.java.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.conexao.ConnectionFactory;
import com.java.modelo.Categoria;

public class CategoriaDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Connection con;

	public Categoria retornarCategoriaPorID(Long id) throws ClassNotFoundException, SQLException {

		String sql = "select id,descricao from categoria where id = ? ";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, id);

		ResultSet rs = stmt.executeQuery();

		Categoria Categoria = null;

		while (rs.next()) {

			Categoria = new Categoria();

			Categoria.setId(rs.getLong("id"));
			Categoria.setDescricao(rs.getString("descricao"));
			
		}

		stmt.close();
		con.close();

		return Categoria;

	}

	public List<Categoria> listarTodos() throws SQLException {

		List<Categoria> lista = new ArrayList<Categoria>();

		String sql = "select id,descricao from categoria order by id desc ";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		Categoria categoria = null;

		while (rs.next()) {

			categoria = new Categoria();

			categoria.setId(rs.getLong("id"));
			categoria.setDescricao(rs.getString("descricao"));
			
			
			lista.add(categoria);

		}

		stmt.close();
		con.close();

		return lista;

	}

	public void incluir(Categoria categoria) throws SQLException {

		con = new ConnectionFactory().getConnection();
		
		/*SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
		String dataFormatada = dt1.format(Categoria.getData());*/

		String sql = "INSERT INTO categoria (descricao) " + "VALUES (?)";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setString(1, categoria.getDescricao());
		
		stmt.execute();
		stmt.close();
		con.close();

	}

	public void alterar(Categoria categoria) throws SQLException {

		con = new ConnectionFactory().getConnection();
		
		/*SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
		String dataFormatada = dt1.format(Categoria.getData());*/

		String sql = "update categoria set " + "descricao = ? where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setString(1, categoria.getDescricao());

		stmt.setLong(2, categoria.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

	public void excluir(Categoria categoria) throws SQLException {

		con = new ConnectionFactory().getConnection();

		String sql = "delete from categoria where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setLong(1, categoria.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

}