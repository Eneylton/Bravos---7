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
import com.java.modelo.CrossTable;

public class CrossTableDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Connection con;

	public List<CrossTable> listarReceita() throws SQLException {

		List<CrossTable> lista = new ArrayList<CrossTable>();

		String sql = "SELECT fin.id,COALESCE(cat.descricao, 'SUBTOTAL') AS categoria, "
				+ "SUM(IF(MONTH(fin.data) = 1, valor, 0)) AS 'Janeiro', "
				+ "SUM(IF(MONTH(fin.data) = 2, valor, 0)) AS 'Fevereiro', "
				+ "SUM(IF(MONTH(fin.data) = 3, valor, 0)) AS 'Marco', "
				+ "SUM(IF(MONTH(fin.data) = 4, valor, 0)) AS 'Abril', "
				+ "SUM(IF(MONTH(fin.data) = 5, valor, 0)) AS 'Maio', "
				+ "SUM(IF(MONTH(fin.data) = 6, valor, 0)) AS 'Junho', "
				+ "SUM(IF(MONTH(fin.data) = 7, valor, 0)) AS 'Julho', "
				+ "SUM(IF(MONTH(fin.data) = 8, valor, 0)) AS 'Agosto', "
				+ "SUM(IF(MONTH(fin.data) = 9, valor, 0)) AS 'Setembro',"
				+ "SUM(IF(MONTH(fin.data) = 10, valor, 0)) AS 'Outubro', "
				+ "SUM(IF(MONTH(fin.data) = 11, valor, 0)) AS 'Novembro', "
				+ "SUM(IF(MONTH(fin.data) = 12, valor, 0)) AS 'Dezembro', " 
				+ "SUM(fin.valor) AS total_produto "
				+ "FROM financeiro as fin "
				+ "inner join categoria as cat on (fin.categoria_id = cat.id)  Where fin.tipo='1' and fin.flag='1'  group by cat.descricao With rollup";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		CrossTable cross = null;

		while (rs.next()) {

			cross = new CrossTable();

			cross.setId(rs.getLong("id"));
			cross.setJaneiro(rs.getDouble("Janeiro"));
			cross.setFevereiro(rs.getDouble("Fevereiro"));
			cross.setMarco(rs.getDouble("Marco"));
			cross.setAbril(rs.getDouble("Abril"));
			cross.setMaio(rs.getDouble("Maio"));
			cross.setJunho(rs.getDouble("Junho"));
			cross.setJulho(rs.getDouble("Julho"));
			cross.setAgosto(rs.getDouble("Agosto"));
			cross.setSetembro(rs.getDouble("Setembro"));
			cross.setOutubro(rs.getDouble("Outubro"));
			cross.setNovembro(rs.getDouble("Novembro"));
			cross.setDezembro(rs.getDouble("Dezembro"));

			cross.setTotalProduto(rs.getDouble("total_produto"));

			Categoria cate = new Categoria();
			cate.setDescricao(rs.getString("categoria"));

			cross.setCategoria(cate);

			lista.add(cross);

		}

		stmt.close();
		con.close();

		return lista;

	}
	
	
	public List<CrossTable> listarDespesa() throws SQLException {

		List<CrossTable> lista = new ArrayList<CrossTable>();

		String sql = "SELECT fin.id,COALESCE(cat.descricao, 'SUBTOTAL') AS categoria, "
				+ "SUM(IF(MONTH(fin.data) = 1, valor, 0)) AS 'Janeiro', "
				+ "SUM(IF(MONTH(fin.data) = 2, valor, 0)) AS 'Fevereiro', "
				+ "SUM(IF(MONTH(fin.data) = 3, valor, 0)) AS 'Marco', "
				+ "SUM(IF(MONTH(fin.data) = 4, valor, 0)) AS 'Abril', "
				+ "SUM(IF(MONTH(fin.data) = 5, valor, 0)) AS 'Maio', "
				+ "SUM(IF(MONTH(fin.data) = 6, valor, 0)) AS 'Junho', "
				+ "SUM(IF(MONTH(fin.data) = 7, valor, 0)) AS 'Julho', "
				+ "SUM(IF(MONTH(fin.data) = 8, valor, 0)) AS 'Agosto', "
				+ "SUM(IF(MONTH(fin.data) = 9, valor, 0)) AS 'Setembro',"
				+ "SUM(IF(MONTH(fin.data) = 10, valor, 0)) AS 'Outubro', "
				+ "SUM(IF(MONTH(fin.data) = 11, valor, 0)) AS 'Novembro', "
				+ "SUM(IF(MONTH(fin.data) = 12, valor, 0)) AS 'Dezembro', " 
				+ "SUM(fin.valor) AS total_produto "
				+ "FROM financeiro as fin "
				+ "inner join categoria as cat on (fin.categoria_id = cat.id)  Where fin.tipo='2' and fin.flag='1'  group by cat.descricao With rollup";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		CrossTable cross = null;

		while (rs.next()) {

			cross = new CrossTable();

			cross.setId(rs.getLong("id"));
			cross.setJaneiro(rs.getDouble("Janeiro"));
			cross.setFevereiro(rs.getDouble("Fevereiro"));
			cross.setMarco(rs.getDouble("Marco"));
			cross.setAbril(rs.getDouble("Abril"));
			cross.setMaio(rs.getDouble("Maio"));
			cross.setJunho(rs.getDouble("Junho"));
			cross.setJulho(rs.getDouble("Julho"));
			cross.setAgosto(rs.getDouble("Agosto"));
			cross.setSetembro(rs.getDouble("Setembro"));
			cross.setOutubro(rs.getDouble("Outubro"));
			cross.setNovembro(rs.getDouble("Novembro"));
			cross.setDezembro(rs.getDouble("Dezembro"));

			cross.setTotalProduto(rs.getDouble("total_produto"));

			Categoria cate = new Categoria();
			cate.setDescricao(rs.getString("categoria"));

			cross.setCategoria(cate);

			lista.add(cross);

		}

		stmt.close();
		con.close();

		return lista;

	}

	
	public List<CrossTable> listarReceitaBanco() throws SQLException {

		List<CrossTable> lista = new ArrayList<CrossTable>();

		String sql = "SELECT fin.id,COALESCE(cat.descricao, 'SUBTOTAL') AS categoria, "
				+ "SUM(IF(MONTH(fin.data) = 1, valor, 0)) AS 'Janeiro', "
				+ "SUM(IF(MONTH(fin.data) = 2, valor, 0)) AS 'Fevereiro', "
				+ "SUM(IF(MONTH(fin.data) = 3, valor, 0)) AS 'Marco', "
				+ "SUM(IF(MONTH(fin.data) = 4, valor, 0)) AS 'Abril', "
				+ "SUM(IF(MONTH(fin.data) = 5, valor, 0)) AS 'Maio', "
				+ "SUM(IF(MONTH(fin.data) = 6, valor, 0)) AS 'Junho', "
				+ "SUM(IF(MONTH(fin.data) = 7, valor, 0)) AS 'Julho', "
				+ "SUM(IF(MONTH(fin.data) = 8, valor, 0)) AS 'Agosto', "
				+ "SUM(IF(MONTH(fin.data) = 9, valor, 0)) AS 'Setembro',"
				+ "SUM(IF(MONTH(fin.data) = 10, valor, 0)) AS 'Outubro', "
				+ "SUM(IF(MONTH(fin.data) = 11, valor, 0)) AS 'Novembro', "
				+ "SUM(IF(MONTH(fin.data) = 12, valor, 0)) AS 'Dezembro', " 
				+ "SUM(fin.valor) AS total_produto "
				+ "FROM financeiro as fin "
				+ "inner join categoria as cat on (fin.categoria_id = cat.id)  Where fin.tipo='1' and fin.caixa_id='2' and fin.flag='1'  group by cat.descricao With rollup";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		CrossTable cross = null;

		while (rs.next()) {

			cross = new CrossTable();

			cross.setId(rs.getLong("id"));
			cross.setJaneiro(rs.getDouble("Janeiro"));
			cross.setFevereiro(rs.getDouble("Fevereiro"));
			cross.setMarco(rs.getDouble("Marco"));
			cross.setAbril(rs.getDouble("Abril"));
			cross.setMaio(rs.getDouble("Maio"));
			cross.setJunho(rs.getDouble("Junho"));
			cross.setJulho(rs.getDouble("Julho"));
			cross.setAgosto(rs.getDouble("Agosto"));
			cross.setSetembro(rs.getDouble("Setembro"));
			cross.setOutubro(rs.getDouble("Outubro"));
			cross.setNovembro(rs.getDouble("Novembro"));
			cross.setDezembro(rs.getDouble("Dezembro"));

			cross.setTotalProduto(rs.getDouble("total_produto"));

			Categoria cate = new Categoria();
			cate.setDescricao(rs.getString("categoria"));

			cross.setCategoria(cate);

			lista.add(cross);

		}

		stmt.close();
		con.close();

		return lista;

	}
	
	
	public List<CrossTable> listarDespesasBanco() throws SQLException {

		List<CrossTable> lista = new ArrayList<CrossTable>();

		String sql = "SELECT fin.id,COALESCE(cat.descricao, 'SUBTOTAL') AS categoria, "
				+ "SUM(IF(MONTH(fin.data) = 1, valor, 0)) AS 'Janeiro', "
				+ "SUM(IF(MONTH(fin.data) = 2, valor, 0)) AS 'Fevereiro', "
				+ "SUM(IF(MONTH(fin.data) = 3, valor, 0)) AS 'Marco', "
				+ "SUM(IF(MONTH(fin.data) = 4, valor, 0)) AS 'Abril', "
				+ "SUM(IF(MONTH(fin.data) = 5, valor, 0)) AS 'Maio', "
				+ "SUM(IF(MONTH(fin.data) = 6, valor, 0)) AS 'Junho', "
				+ "SUM(IF(MONTH(fin.data) = 7, valor, 0)) AS 'Julho', "
				+ "SUM(IF(MONTH(fin.data) = 8, valor, 0)) AS 'Agosto', "
				+ "SUM(IF(MONTH(fin.data) = 9, valor, 0)) AS 'Setembro',"
				+ "SUM(IF(MONTH(fin.data) = 10, valor, 0)) AS 'Outubro', "
				+ "SUM(IF(MONTH(fin.data) = 11, valor, 0)) AS 'Novembro', "
				+ "SUM(IF(MONTH(fin.data) = 12, valor, 0)) AS 'Dezembro', " 
				+ "SUM(fin.valor) AS total_produto "
				+ "FROM financeiro as fin "
				+ "inner join categoria as cat on (fin.categoria_id = cat.id)  Where fin.tipo='2' and fin.caixa_id='2' and fin.flag='1' group by cat.descricao With rollup";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		CrossTable cross = null;

		while (rs.next()) {

			cross = new CrossTable();

			cross.setId(rs.getLong("id"));
			cross.setJaneiro(rs.getDouble("Janeiro"));
			cross.setFevereiro(rs.getDouble("Fevereiro"));
			cross.setMarco(rs.getDouble("Marco"));
			cross.setAbril(rs.getDouble("Abril"));
			cross.setMaio(rs.getDouble("Maio"));
			cross.setJunho(rs.getDouble("Junho"));
			cross.setJulho(rs.getDouble("Julho"));
			cross.setAgosto(rs.getDouble("Agosto"));
			cross.setSetembro(rs.getDouble("Setembro"));
			cross.setOutubro(rs.getDouble("Outubro"));
			cross.setNovembro(rs.getDouble("Novembro"));
			cross.setDezembro(rs.getDouble("Dezembro"));

			cross.setTotalProduto(rs.getDouble("total_produto"));

			Categoria cate = new Categoria();
			cate.setDescricao(rs.getString("categoria"));

			cross.setCategoria(cate);

			lista.add(cross);

		}

		stmt.close();
		con.close();

		return lista;

	}
	

}
