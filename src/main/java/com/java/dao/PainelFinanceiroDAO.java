package com.java.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.java.conexao.ConnectionFactory;

public class PainelFinanceiroDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Connection con;
	
	public double getTotalAtualizado(Long id) throws SQLException {
		con = new ConnectionFactory().getConnection();

		double total = 0;

		String sql = "SELECT "
						+ "SUM(fin.valor) as total "
						+ "FROM "
						+ "financeiro AS fin "
						+ "INNER JOIN "
						+ "categoria AS c ON (fin.categoria_id = c.id) "
						+ "INNER JOIN "
						+ "aluno AS al ON (fin.aluno_id = al.id) "
						+ "INNER JOIN "
						+ "usuario AS us ON (fin.usuario_id = us.id) "
						+ "INNER JOIN "
						+ "autoescola AS aut ON (us.autoescola_id = aut.id) "
						+ "WHERE "
						+ "autoescola_id = ? AND flag='1' AND fin.tipo='1' AND fin.caixa_id='1' "
						+ "ORDER BY fin.data ASC ";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);
		stmt.setLong(1, id);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			if (rs.getDouble("total") != 0) {
				total = rs.getDouble("total");
			} else {
				total = 0;
			}
		}

		rs.close();

		stmt.close();
		con.close();

		return total;
	}
	
	
	public double getTotalDespesa(Long id) throws SQLException {
		con = new ConnectionFactory().getConnection();

		double total = 0;

		String sql = "SELECT "
							+ "SUM(fin.valor) as total "
							+ "FROM "
							+ "financeiro AS fin "
							+ "INNER JOIN "
							+ "categoria AS c ON (fin.categoria_id = c.id) "
							+ "INNER JOIN "
							+ "aluno AS al ON (fin.aluno_id = al.id) "
							+ "INNER JOIN "
							+ "usuario AS us ON (fin.usuario_id = us.id) "
							+ "INNER JOIN "
							+ "autoescola AS aut ON (us.autoescola_id = aut.id) "
							+ "WHERE "
							+ "autoescola_id = ? AND flag='1' AND fin.tipo='2' AND fin.caixa_id='1' "
							+ "ORDER BY fin.data ASC";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);
		stmt.setLong(1, id);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			if (rs.getDouble("total") != 0) {
				total = rs.getDouble("total");
			} else {
				total = 0;
			}
		}

		rs.close();

		stmt.close();
		con.close();

		return total;
	}
	
	
	

	public double getTotalReceita(Long id) throws SQLException {
		con = new ConnectionFactory().getConnection();

		double total = 0;

		String sql = "SELECT "
				+ "SUM(valor) AS total "
				+ "FROM "
				+ "financeiro AS fin "
				+ "INNER JOIN "
				+ "usuario AS us ON (us.id = fin.usuario_id) "
				+ "INNER JOIN "
				+ "autoescola AS aut ON (aut.id = us.autoescola_id) "
				+ "WHERE aut.id = ? AND " 
				+ "flag = '1' AND fin.tipo = '1'";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);
		stmt.setLong(1, id);

		ResultSet rs = stmt.executeQuery();
		

		while (rs.next()) {
			if (rs.getDouble("total") != 0) {
				total = rs.getDouble("total");
			} else {
				total = 0;
			}
		}

		rs.close();

		stmt.close();
		con.close();

		return total;
	}
	
	
	public double getTotalReceitaDIA(Long id) throws SQLException {
		con = new ConnectionFactory().getConnection();

		double total = 0;

		String sql = " SELECT day(fin.data) as dia, sum(fin.valor) as total "
						+ "FROM  financeiro AS fin "
						+ "INNER JOIN "
						+ "categoria AS c ON (fin.categoria_id = c.id) "
						+ "INNER JOIN "
						+ "aluno AS al ON (fin.aluno_id = al.id) "
						+ "INNER JOIN "
						+ "usuario AS us ON (fin.usuario_id = us.id) "
						+ "INNER JOIN "
						+ "autoescola AS aut ON (us.autoescola_id = aut.id) "
						+ "where autoescola_id = ? AND flag='1' AND fin.tipo='1' AND fin.caixa_id='1' AND fin.data = curdate() "
						+ "group by dia order by dia desc limit 1 ";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);
		stmt.setLong(1, id);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			if (rs.getDouble("total") != 0) {
				total = rs.getDouble("total");
			} else {
				total = 0;
			}
		}

		rs.close();

		stmt.close();
		con.close();

		return total;
	}
	
	
	
	public double getTotalDespesaDIA(Long id) throws SQLException {
		con = new ConnectionFactory().getConnection();

		double total = 0;

		String sql = "SELECT day(fin.data) as dia, sum(fin.valor) as total " 
						+ "FROM  financeiro AS fin "
						+ "INNER JOIN "
						+ "categoria AS c ON (fin.categoria_id = c.id) "
						+ "INNER JOIN "
						+ "aluno AS al ON (fin.aluno_id = al.id) "
						+ "INNER JOIN "
						+ "usuario AS us ON (fin.usuario_id = us.id) "
						+ "INNER JOIN "
						+ "autoescola AS aut ON (us.autoescola_id = aut.id) "
						+ "where autoescola_id = ? AND flag='1' AND fin.tipo='2' AND fin.caixa_id='1' AND fin.data = curdate() "
						+ "group by dia order by dia desc limit 1";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);
		stmt.setLong(1, id);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			if (rs.getDouble("total") != 0) {
				total = rs.getDouble("total");
			} else {
				total = 0;
			}
		}

		rs.close();

		stmt.close();
		con.close();

		return total;
	}
	
		
	
	
	
	public double getTotalBancoReceita(Long id) throws SQLException {
		con = new ConnectionFactory().getConnection();

		double total = 0;

		String sql = "SELECT "
						+ "SUM(valor) AS total "
						+ "FROM "
						+ "financeiro AS fin "
						+ "INNER JOIN "
						+ "usuario AS us ON (us.id = fin.usuario_id) "
						+ "INNER JOIN "
						+ "autoescola AS aut ON (us.autoescola_id = aut.id) "
						+ "WHERE "
						+ "flag='1' AND fin.tipo='1' AND fin.caixa_id='2' AND aut.id= ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);
		stmt.setLong(1, id);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			if (rs.getDouble("total") != 0) {
				total = rs.getDouble("total");
			} else {
				total = 0;
			}
		}

		rs.close();

		stmt.close();
		con.close();

		return total;
	}


	public double getTotalBancoDespesa(Long id) throws SQLException {
		con = new ConnectionFactory().getConnection();

		double total = 0;

		String sql = "SELECT "
						+ "SUM(valor) AS total "
						+ "FROM "
						+ "financeiro AS fin "
						+ "INNER JOIN "
						+ "usuario AS us ON (us.id = fin.usuario_id) "
						+ "INNER JOIN "
						+ "autoescola AS aut ON (us.autoescola_id = aut.id) "
						+ "WHERE "
						+ "flag='1' AND fin.tipo='2' AND fin.caixa_id='2' AND aut.id= ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);
		stmt.setLong(1, id);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			if (rs.getDouble("total") != 0) {
				total = rs.getDouble("total");
			} else {
				total = 0;
			}
		}

		rs.close();

		stmt.close();
		con.close();

		return total;
	}





public double getTotalDespesaMovimentada(Long id) throws SQLException {
	con = new ConnectionFactory().getConnection();

	double total = 0;

	String sql = "SELECT "
			+ "SUM(valor) AS total "
			+ "FROM "
			+ "financeiro AS fin "
			+ "INNER JOIN "
			+ "usuario AS us ON (us.id = fin.usuario_id) "
			+ "INNER JOIN "
			+ "autoescola AS aut ON (aut.id = us.autoescola_id) "
			+ "WHERE aut.id = ? AND " 
			+ "flag = '1' AND fin.tipo = '2'";

	PreparedStatement stmt;

	stmt = con.prepareStatement(sql);
	stmt.setLong(1, id);

	ResultSet rs = stmt.executeQuery();

	while (rs.next()) {
		if (rs.getDouble("total") != 0) {
			total = rs.getDouble("total");
		} else {
			total = 0;
		}
	}

	rs.close();

	stmt.close();
	con.close();

	return total;
}




public double getPagar(Long id) throws SQLException {
	con = new ConnectionFactory().getConnection();

	double total = 0;

	String sql = "SELECT " 
					+ "SUM(valor) AS total "
					+ "FROM "
					+ "financeiro AS fin "
					+ "INNER JOIN "
					+ "usuario AS us ON (us.id = fin.usuario_id) "
					+ "INNER JOIN "
					+ "autoescola AS aut ON (us.autoescola_id = aut.id) "
					+ "WHERE "
					+ "fin.data > CURDATE() AND fin.tipo = '2' "
					+ "AND fin.flag = '2' AND aut.id= ?";

	PreparedStatement stmt;

	stmt = con.prepareStatement(sql);
	stmt.setLong(1, id);

	ResultSet rs = stmt.executeQuery();

	while (rs.next()) {
		if (rs.getDouble("total") != 0) {
			total = rs.getDouble("total");
		} else {
			total = 0;
		}
	}

	rs.close();

	stmt.close();
	con.close();

	return total;
}



public double getReceber(Long id) throws SQLException {
	con = new ConnectionFactory().getConnection();

	double total = 0;

	String sql = "SELECT "
					+ "SUM(valor) AS total "
					+ "FROM "
					+ "financeiro AS fin "
					+ "INNER JOIN "
					+ "usuario AS us ON (us.id = fin.usuario_id) "
					+ "INNER JOIN "
					+ "autoescola AS aut ON (aut.id = us.autoescola_id) "
					+ "WHERE "
					+ "data > CURDATE() AND tipo = '1' "
					+ "AND flag = '2' AND aut.id= ? ";

	PreparedStatement stmt;

	stmt = con.prepareStatement(sql);
	stmt.setLong(1, id);

	ResultSet rs = stmt.executeQuery();

	while (rs.next()) {
		if (rs.getDouble("total") != 0) {
			total = rs.getDouble("total");
		} else {
			total = 0;
		}
	}

	rs.close();

	stmt.close();
	con.close();

	return total;
}




public double getRecebido(Long id) throws SQLException {
	con = new ConnectionFactory().getConnection();

	double total = 0;

	String sql = "SELECT "
					+ "SUM(valor) AS total "
					+ "FROM "
					+ "financeiro AS fin "
					+ "INNER JOIN "
					+ "usuario AS us ON (us.id = fin.usuario_id) "
					+ "INNER JOIN "
					+ "autoescola AS aut ON (us.autoescola_id = aut.id) "
					+ "WHERE "
					+ "fin.tipo='1' "
					+ "AND fin.flag = '1' AND aut.id= ?";

	PreparedStatement stmt;

	stmt = con.prepareStatement(sql);
	stmt.setLong(1, id);

	ResultSet rs = stmt.executeQuery();

	while (rs.next()) {
		if (rs.getDouble("total") != 0) {
			total = rs.getDouble("total");
		} else {
			total = 0;
		}
	}

	rs.close();

	stmt.close();
	con.close();

	return total;
}



public double getTotalAtrasado(Long id) throws SQLException {
	con = new ConnectionFactory().getConnection();

	double total = 0;

	
	String sql = "SELECT "
					+ "SUM(valor) AS total "
					+ "FROM "
					+ "financeiro AS fin "
					+ "INNER JOIN "
					+ "usuario AS us ON (us.id = fin.usuario_id) "
					+ "INNER JOIN "
					+ "autoescola AS aut ON (us.autoescola_id = aut.id) "
					+ "WHERE "
					+ "data < curdate() AND flag ='2' AND aut.id= ?";

	PreparedStatement stmt;

	stmt = con.prepareStatement(sql);
	stmt.setLong(1, id);

	ResultSet rs = stmt.executeQuery();

	while (rs.next()) {
		if (rs.getDouble("total") != 0) {
			total = rs.getDouble("total");
		} else {
			total = 0;
		}
	}

	rs.close();

	stmt.close();
	con.close();

	return total;
}

}


