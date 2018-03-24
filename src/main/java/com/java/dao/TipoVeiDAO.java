package com.java.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.conexao.ConnectionFactory;
import com.java.modelo.TipoVei;

public class TipoVeiDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Connection con;

	public TipoVei retornarTipoVeiPorID(Long id) throws ClassNotFoundException, SQLException {

		String sql = "select id,descricao from tipovei where id = ? ";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, id);

		ResultSet rs = stmt.executeQuery();

		TipoVei tipoVei = null;

		while (rs.next()) {

			tipoVei = new TipoVei();

			tipoVei.setId(rs.getLong("id"));
			tipoVei.setDescricao(rs.getString("descricao"));
			
		}

		stmt.close();
		con.close();

		return tipoVei;

	}

	public List<TipoVei> listarTodos() throws SQLException {

		List<TipoVei> lista = new ArrayList<TipoVei>();

		String sql = "select id,descricao from tipovei order by id desc ";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		TipoVei tipoVei = null;

		while (rs.next()) {

			tipoVei = new TipoVei();

			tipoVei.setId(rs.getLong("id"));
			tipoVei.setDescricao(rs.getString("descricao"));
			
			
			lista.add(tipoVei);

		}

		stmt.close();
		con.close();

		return lista;

	}

	public void incluir(TipoVei tipoVei) throws SQLException {

		con = new ConnectionFactory().getConnection();
		
		/*SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
		String dataFormatada = dt1.format(Categoria.getData());*/

		String sql = "insert into tipovei (descricao) " + "values (?)";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setString(1, tipoVei.getDescricao());
		
		stmt.execute();
		stmt.close();
		con.close();

	}

	public void alterar(TipoVei tipoVei) throws SQLException {

		con = new ConnectionFactory().getConnection();
		
		/*SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
		String dataFormatada = dt1.format(Categoria.getData());*/

		String sql = "update tipovei set " + "descricao = ? where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setString(1, tipoVei.getDescricao());

		stmt.setLong(2, tipoVei.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

	public void excluir(TipoVei tipoVei) throws SQLException {

		con = new ConnectionFactory().getConnection();

		String sql = "delete from tipovei where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setLong(1, tipoVei.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

}