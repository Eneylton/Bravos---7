package com.java.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.conexao.ConnectionFactory;
import com.java.modelo.Fabricante;
import com.java.modelo.TipoVei;

public class FabricanteDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Connection con;

	public Fabricante retornarFabricantePorID(Long id) throws ClassNotFoundException, SQLException {

		String sql = "select id,marca,modelo,tipovei_id as id_tipo,foto from fabricante where id = ? ";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, id);

		ResultSet rs = stmt.executeQuery();

		Fabricante fabricante = null;

		while (rs.next()) {

			fabricante = new Fabricante();

			fabricante.setId(rs.getLong("id"));
			fabricante.setMarca(rs.getString("marca"));
			fabricante.setModelo(rs.getString("modelo"));
			fabricante.setFoto(rs.getString("foto"));
			
			
			TipoVei vei = new TipoVei();
			vei.setId(rs.getLong("id_tipo"));
			fabricante.setTipoVei(vei);
			
		}

		stmt.close();
		con.close();

		return fabricante;

	}

	public List<Fabricante> listarTodos() throws SQLException {

		List<Fabricante> lista = new ArrayList<Fabricante>();

		String sql = "select id,marca,modelo,foto from fabricante order by id desc ";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		Fabricante fabricante = null;

		while (rs.next()) {

			fabricante = new Fabricante();

			fabricante.setId(rs.getLong("id"));
			fabricante.setMarca(rs.getString("marca"));
			fabricante.setModelo(rs.getString("modelo"));
			fabricante.setFoto(rs.getString("foto"));
	
			lista.add(fabricante);

		}

		stmt.close();
		con.close();

		return lista;

	}
	
	
	public List<Fabricante> buscarTipoFabrigante(Long id) throws SQLException {

		List<Fabricante> lista = new ArrayList<Fabricante>();

		String sql = "SELECT * FROM fabricante as fab inner join tipovei as tvi on (fab.tipovei_id = tvi.id) WHERE tvi.id = ?";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, id);

		ResultSet rs = stmt.executeQuery();

		Fabricante fab = null;

		while (rs.next()) {

			fab = new Fabricante();

			fab.setId(rs.getLong("id"));
			fab.setModelo(rs.getString("modelo"));

			lista.add(fab);

		}

		stmt.close();
		con.close();

		return lista;

	}


	public void incluir(Fabricante fabricante) throws SQLException {

		con = new ConnectionFactory().getConnection();
		
		/*SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
		String dataFormatada = dt1.format(Categoria.getData());*/

		String sql = "INSERT INTO fabricante (marca,modelo,tipovei_id,foto) " + "VALUES (?,?,?,?)";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setString(1, fabricante.getMarca());
		stmt.setString(2, fabricante.getModelo());
		stmt.setLong(3, fabricante.getTipoVei().getId());
		stmt.setString(4, fabricante.getFoto());
		stmt.execute();
		stmt.close();
		con.close();

	}

	public void alterar(Fabricante fabricante) throws SQLException {

		con = new ConnectionFactory().getConnection();
		
		/*SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
		String dataFormatada = dt1.format(Categoria.getData());*/

		String sql = "update fabricante set " + "marca = ?,modelo = ?,tipovei_id = ?,foto=? where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setString(1, fabricante.getMarca());
		stmt.setString(2, fabricante.getModelo());
		stmt.setLong(3, fabricante.getTipoVei().getId());
		stmt.setString(4, fabricante.getFoto());
		stmt.setLong(5, fabricante.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

	public void excluir(Fabricante fabricante) throws SQLException {

		con = new ConnectionFactory().getConnection();

		String sql = "delete from fabricante where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setLong(1, fabricante.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

}