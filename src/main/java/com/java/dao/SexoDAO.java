package com.java.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.conexao.ConnectionFactory;
import com.java.modelo.Sexo;

public class SexoDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Connection con;

	public Sexo retornarSexoPorID(Long id) throws ClassNotFoundException, SQLException {

		String sql = "select id,descricao from sexo where id = ? ";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, id);

		ResultSet rs = stmt.executeQuery();

		Sexo sexo = null;

		while (rs.next()) {

			sexo = new Sexo();

			sexo.setId(rs.getLong("id"));
			sexo.setDescricao(rs.getString("descricao"));
			
		}

		stmt.close();
		con.close();

		return sexo;

	}

	public List<Sexo> listarTodos() throws SQLException {

		List<Sexo> lista = new ArrayList<Sexo>();

		String sql = "select id,descricao from sexo order by id desc ";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		Sexo sexo = null;

		while (rs.next()) {

			sexo = new Sexo();

			sexo.setId(rs.getLong("id"));
			sexo.setDescricao(rs.getString("descricao"));
			
			
			lista.add(sexo);

		}

		stmt.close();
		con.close();

		return lista;

	}

	public void incluir(Sexo sexo) throws SQLException {

		con = new ConnectionFactory().getConnection();
		
		/*SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
		String dataFormatada = dt1.format(Categoria.getData());*/

		String sql = "INSERT INTO sexo (descricao) " + "VALUES (?)";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setString(1, sexo.getDescricao());
		
		stmt.execute();
		stmt.close();
		con.close();

	}

	public void alterar(Sexo sexo) throws SQLException {

		con = new ConnectionFactory().getConnection();
		
		/*SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
		String dataFormatada = dt1.format(Categoria.getData());*/

		String sql = "update sexo set " + "descricao = ? where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setString(1, sexo.getDescricao());

		stmt.setLong(2, sexo.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

	public void excluir(Sexo sexo) throws SQLException {

		con = new ConnectionFactory().getConnection();

		String sql = "delete from sexo where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setLong(1, sexo.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

}