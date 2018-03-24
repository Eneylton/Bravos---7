package com.java.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.conexao.ConnectionFactory;
import com.java.modelo.Financeiro;
import com.java.modelo.Rank;
import com.java.modelo.Rank2;
import com.java.modelo.StatusGrafico;
import com.java.modelo.TipoGrafico;
import com.java.modelo.TotalCadastroDia;

public class GraficoDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Connection con;

	public List<Financeiro> getTotalMes() throws SQLException {
		this.con = new ConnectionFactory().getConnection();

		List<Financeiro> financeiros = new ArrayList<Financeiro>();

		Financeiro financeiro;

		String sql = "SELECT " + "fin.id, " + "cat.descricao, " + "(CASE MONTH(fin.data) " + " WHEN 1 THEN 'Janeiro' "
				+ " WHEN 2 THEN 'Fevereiro' " + " WHEN 3 THEN 'Março' " + " WHEN 4 THEN 'Abril' "
				+ "WHEN 5 THEN 'Maio' " + "WHEN 6 THEN 'Junho' " + " WHEN 7 THEN 'Julho' " + "WHEN 8 THEN 'Agosto' "
				+ "WHEN 9 THEN 'Setembro' " + " WHEN 10 THEN 'Outubro' " + " WHEN 11 THEN 'Novembro' "
				+ " WHEN 12 THEN 'Dezembro' " + " END) AS mes, " + " SUM(fin.valor) AS total " + " FROM "
				+ " financeiro AS fin " + " INNER JOIN " + "categoria AS cat ON (fin.categoria_id = cat.id) "
				+ " GROUP BY mes " + "ORDER BY fin.data ASC ";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			financeiro = new Financeiro();

			financeiro.setId(rs.getLong("id"));
			financeiro.setMes(rs.getString("mes"));
			financeiro.setTotal(rs.getDouble("total"));

			financeiros.add(financeiro);
		}

		rs.close();

		stmt.close();
		con.close();

		return financeiros;
	}

	public List<TotalCadastroDia> getTotalCadastroDia() throws SQLException {
		this.con = new ConnectionFactory().getConnection();

		List<TotalCadastroDia> TotalCadastroDia = new ArrayList<TotalCadastroDia>();

		TotalCadastroDia diaInscritos;

		String sql = "SELECT day(fin.data) as dia, count(fin.id) as total FROM financeiro fin "
				+ "group by dia order by dia";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			diaInscritos = new TotalCadastroDia();
			diaInscritos.setDia(rs.getString("dia") + "");
			diaInscritos.setTotal(rs.getInt("total"));

			TotalCadastroDia.add(diaInscritos);
		}

		rs.close();

		stmt.close();
		con.close();

		return TotalCadastroDia;
	}
	
	
	public List<TotalCadastroDia> getTotalCadastroDiaUser(Long id) throws SQLException {
	
		List<TotalCadastroDia> TotalCadastroDia = new ArrayList<TotalCadastroDia>();

		TotalCadastroDia diaInscritos;

		String sql = "SELECT "
						+ "DAY(fin.data) AS dia, COUNT(fin.id) AS total "
						+ "FROM "
						+ "financeiro fin "
						+ "INNER JOIN "
						+ "usuario AS us ON (fin.usuario_id = us.id) "
						+ "WHERE "
						+ "us.id = ? "
								+ "GROUP BY dia "
								+ "ORDER BY dia";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);

		stmt.setLong(1, id);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			diaInscritos = new TotalCadastroDia();
			diaInscritos.setDia(rs.getString("dia") + "");
			diaInscritos.setTotal(rs.getInt("total"));

			TotalCadastroDia.add(diaInscritos);
		}

		rs.close();

		stmt.close();
		con.close();

		return TotalCadastroDia;
	}


	public List<Rank> totalRank() throws SQLException {
		this.con = new ConnectionFactory().getConnection();

		List<Rank> listarRank = new ArrayList<Rank>();

		Rank rank;

		String sql = "SELECT " + "us.nomeCompleto as nome, COUNT(fin.aluno_id) AS total " + "FROM "
				+ " financeiro AS fin " + "INNER JOIN " + " usuario AS us ON (fin.usuario_id = us.id) "
				+ " GROUP BY us.nomeCompleto";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			rank = new Rank();
			rank.setNomeCompleto(rs.getString("nome") + "");
			rank.setTotal(rs.getInt("total"));

			listarRank.add(rank);
		}

		rs.close();

		stmt.close();
		con.close();

		return listarRank;
	}

	public List<Rank2> totalRank2() throws SQLException {
		this.con = new ConnectionFactory().getConnection();

		List<Rank2> listarRank2 = new ArrayList<Rank2>();

		Rank2 rank2;

		String sql = "SELECT cat.descricao as cat, count(cat.descricao) as total "
				   + "FROM financeiro as fin inner join categoria as cat on (fin.categoria_id = cat.id) "
				   + "group by cat.descricao;";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {

			rank2 = new Rank2();
			rank2.setCat(rs.getString("cat") + "");
			rank2.setTotal(rs.getInt("total"));

			listarRank2.add(rank2);
		}

		rs.close();

		stmt.close();
		con.close();

		return listarRank2;
	}

	public List<TipoGrafico> totalTipo() throws SQLException {
		this.con = new ConnectionFactory().getConnection();

		List<TipoGrafico> listarRank = new ArrayList<TipoGrafico>();

		TipoGrafico tipo;

		String sql = "select tp.descricao as tipo,count(fin.tipo) as total from financeiro as fin inner join tipo as tp on (fin.tipo = tp.id) group by tipo";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			tipo = new TipoGrafico();
			tipo.setTipo(rs.getString("tipo") + "");
			tipo.setTotal(rs.getInt("total"));

			listarRank.add(tipo);
		}

		rs.close();

		stmt.close();
		con.close();

		return listarRank;
	}

	public List<StatusGrafico> totalStatus() throws SQLException {
		this.con = new ConnectionFactory().getConnection();

		List<StatusGrafico> listarSatus = new ArrayList<StatusGrafico>();

		StatusGrafico status;

		String sql = "select fl.descricao as status,count(fin.flag) as total  from financeiro as fin inner join flag as fl on (fin.flag = fl.id) group by fl.descricao";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			status = new StatusGrafico();
			status.setStatus(rs.getString("status") + "");
			status.setTotal(rs.getInt("total"));

			listarSatus.add(status);
		}

		rs.close();

		stmt.close();
		con.close();

		return listarSatus;
	}
	
	
	public List<Financeiro> getReceitaMes() throws SQLException {
		this.con = new ConnectionFactory().getConnection();

		List<Financeiro> financeiros = new ArrayList<Financeiro>();

		Financeiro financeiro;

		String sql = "SELECT fin.id, fin.tipo, (CASE MONTH(fin.data)  WHEN 1 THEN 'Janeiro' "
						+ "WHEN 2 THEN 'Fevereiro' WHEN 3 THEN 'Março' WHEN 4 THEN 'Abril' "
						+ "WHEN 5 THEN 'Maio' WHEN 6 THEN 'Junho' WHEN 7 THEN 'Julho' WHEN 8 THEN 'Agosto' " 
						+ "WHEN 9 THEN 'Setembro' WHEN 10 THEN 'Outubro' WHEN 11 THEN 'Novembro' "
						+ "WHEN 12 THEN 'Dezembro' END) AS mes, SUM(fin.valor) AS total FROM "
		+ "financeiro AS fin WHERE fin.tipo = '1' AND fin.flag='1' "
		+ "GROUP BY mes ORDER BY fin.data ASC ";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			financeiro = new Financeiro();

			financeiro.setId(rs.getLong("id"));
			financeiro.setMes(rs.getString("mes"));
			financeiro.setTotal(rs.getDouble("total"));

			financeiros.add(financeiro);
		}

		rs.close();

		stmt.close();
		con.close();

		return financeiros;
	}
	
	
	public List<Financeiro> getDespesaMes() throws SQLException {
		this.con = new ConnectionFactory().getConnection();

		List<Financeiro> financeiros = new ArrayList<Financeiro>();

		Financeiro financeiro;

		String sql = "SELECT fin.id, fin.tipo, (CASE MONTH(fin.data)  WHEN 1 THEN 'Janeiro' "
						+ "WHEN 2 THEN 'Fevereiro' WHEN 3 THEN 'Março' WHEN 4 THEN 'Abril' "
						+ "WHEN 5 THEN 'Maio' WHEN 6 THEN 'Junho' WHEN 7 THEN 'Julho' WHEN 8 THEN 'Agosto' " 
						+ "WHEN 9 THEN 'Setembro' WHEN 10 THEN 'Outubro' WHEN 11 THEN 'Novembro' "
						+ "WHEN 12 THEN 'Dezembro' END) AS mes, SUM(fin.valor) AS total FROM "
		+ "financeiro AS fin WHERE fin.tipo = '2' AND flag='1'"
		+ "GROUP BY mes ORDER BY fin.data ASC ";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			financeiro = new Financeiro();

			financeiro.setId(rs.getLong("id"));
			financeiro.setMes(rs.getString("mes"));
			financeiro.setTotal(rs.getDouble("total"));

			financeiros.add(financeiro);
		}

		rs.close();

		stmt.close();
		con.close();

		return financeiros;
	}


}
