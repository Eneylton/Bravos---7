package com.java.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.conexao.ConnectionFactory;
import com.java.modelo.Carro;
import com.java.modelo.Veiculo;

public class CarroDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Connection con;

	public Carro retornarCarroPorID(Long id) throws ClassNotFoundException, SQLException {

		String sql = "select id,foto,veiculo_id as id_fabricante from carro where id = ? ";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, id);

		ResultSet rs = stmt.executeQuery();

		Carro carro = null;

		while (rs.next()) {

			carro = new Carro();

			carro.setId(rs.getLong("id"));
			carro.setFoto(rs.getString("foto"));
			
			Veiculo veiculo = new Veiculo();
			
			veiculo.setId(rs.getLong("id_fabricante"));
			
			carro.setVeiculo(veiculo);
			
		}

		stmt.close();
		con.close();

		return carro;

	}

	public List<Carro> listarTodos() throws SQLException {

		List<Carro> lista = new ArrayList<Carro>();

		String sql = "SELECT "
					    + "car.id, car.foto,vei.id as id_veiculo,vei.Fabricante as fabricante, vei.modelo as modelo "
					    + "FROM "
					    + "carro AS car "
					        + "INNER JOIN "
 					    + "veiculo AS vei on (car.veiculo_id  = vei.id) "
					+ "ORDER BY id DESC ";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		Carro carro = null;

		while (rs.next()) {

			carro = new Carro();

			carro.setId(rs.getLong("id"));
			carro.setFoto(rs.getString("foto"));
			
			Veiculo vei  = new Veiculo();
			vei.setId(rs.getLong("id_veiculo"));
			vei.setFabricante(rs.getString("fabricante"));
			vei.setModelo(rs.getString("modelo"));
			
			carro.setVeiculo(vei);

			lista.add(carro);

		}

		stmt.close();
		con.close();

		return lista;

	}

	public void incluir(Carro carro) throws SQLException {

		con = new ConnectionFactory().getConnection();

		/*
		 * SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd"); String
		 * dataFormatada = dt1.format(Categoria.getData());
		 */

		String sql = "INSERT INTO carro (foto,veiculo_id) " + "VALUES (?,?)";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setString(1, carro.getFoto());

		stmt.setLong(2, carro.getVeiculo().getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

	public void alterar(Carro carro) throws SQLException {

		con = new ConnectionFactory().getConnection();

		/*
		 * SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd"); String
		 * dataFormatada = dt1.format(Categoria.getData());
		 */

		String sql = "update carro set " + "foto = ?, veiculo_id = ? where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setString(1, carro.getFoto());

		stmt.setLong(2, carro.getVeiculo().getId());
		
		stmt.setLong(3, carro.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

	public void excluir(Carro carro) throws SQLException {

		con = new ConnectionFactory().getConnection();

		String sql = "delete from carro where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setLong(1, carro.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

}