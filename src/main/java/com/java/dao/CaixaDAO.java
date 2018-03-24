package com.java.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.conexao.ConnectionFactory;
import com.java.modelo.Caixa;

public class CaixaDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Connection con;

	public Caixa retornarCaixaPorID(Long id) throws ClassNotFoundException, SQLException {

		String sql = "select id,descricao from caixa where id = ? ";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, id);

		ResultSet rs = stmt.executeQuery();

		Caixa caixa = null;

		while (rs.next()) {

			caixa = new Caixa();

			caixa.setId(rs.getLong("id"));
			caixa.setDescricao(rs.getString("descricao"));
			
		}

		stmt.close();
		con.close();

		return caixa;

	}

	public List<Caixa> listarTodos() throws SQLException {

		List<Caixa> lista = new ArrayList<Caixa>();

		String sql = "select id,descricao from caixa order by id desc ";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		Caixa caixa = null;

		while (rs.next()) {

			caixa = new Caixa();

			caixa.setId(rs.getLong("id"));
			caixa.setDescricao(rs.getString("descricao"));
			
			
			lista.add(caixa);

		}

		stmt.close();
		con.close();

		return lista;

	}

	public void incluir(Caixa caixa) throws SQLException {

		con = new ConnectionFactory().getConnection();
		
		/*SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
		String dataFormatada = dt1.format(Categoria.getData());*/

		String sql = "INSERT INTO caixa (descricao) " + "VALUES (?)";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setString(1, caixa.getDescricao());
		
		stmt.execute();
		stmt.close();
		con.close();

	}

	public void alterar(Caixa caixa) throws SQLException {

		con = new ConnectionFactory().getConnection();
		
		/*SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
		String dataFormatada = dt1.format(Categoria.getData());*/

		String sql = "update caixa set " + "descricao = ? where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setString(1, caixa.getDescricao());

		stmt.setLong(2, caixa.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

	public void excluir(Caixa caixa) throws SQLException {

		con = new ConnectionFactory().getConnection();

		String sql = "delete from caixa where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setLong(1, caixa.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

}