package com.java.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.conexao.ConnectionFactory;
import com.java.modelo.Aluno;
import com.java.modelo.AutoEscola;
import com.java.modelo.Caixa;
import com.java.modelo.Categoria;
import com.java.modelo.Financeiro;
import com.java.modelo.Servico;
import com.java.modelo.Usuario;

public class ListasDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Connection con;

	public List<Financeiro> retornoDespesa(Long id) throws SQLException {

		List<Financeiro> lista = new ArrayList<Financeiro>();

		String sql = "SELECT " + "fin.id, " + "al.nome as idNome," + "sum(fin.valor) as total, " + "fin.data, "
				+ "fin.valor, " + "fin.flag, " + "fin.qtd, " + "fin.tipo as idTipo, " + "fin.servico_id AS servico, "
				+ "fin.aluno_id AS idAluno, " + "fin.descricao, " + "fin.categoria_id AS idCat, "
				+ "fin.caixa_id AS caixa, " + "fin.pagamento_id AS pagamento, " + "CASE "
				+ " WHEN DATEDIFF(fin.data, CURDATE()) = 5 THEN '05 DIAS PARA O PAGAMENTO' "
				+ " WHEN DATEDIFF(fin.data, CURDATE()) = 4 THEN '04 DIAS PARA O PAGAMENTO' "
				+ " WHEN DATEDIFF(fin.data, CURDATE()) = 3 THEN '03 DIAS PARA O PAGAMENTO' "
				+ " WHEN DATEDIFF(fin.data, CURDATE()) = 2 THEN '02 DIAS PARA O PAGAMENTO' "
				+ " WHEN DATEDIFF(fin.data, CURDATE()) = 1 THEN 'FALTA UM DIA PARA O PAGAMENTO' "
				+ " WHEN DATEDIFF(fin.data, CURDATE()) = 0 THEN 'DIA DO PAGAMENTO' "
				+ " WHEN DATEDIFF(fin.data, CURDATE()) <= 0 THEN 'ATRASADO' " + " ELSE 'AQUARDANDO PAGAMENTO' "
				+ " END AS dia, " + "c.descricao AS categoria, CASE " + " WHEN fin.tipo = '1' THEN 'Receita' "
				+ " WHEN fin.tipo = '2' THEN 'Despesa' " + " END " + " AS idDescricao, CASE "
				+ " WHEN fin.flag = '1' THEN 'Pago' " + " WHEN fin.flag = '2' THEN 'Em Aberto' " + " END "
				+ " AS FlagDesc " + "FROM " + " financeiro AS fin INNER JOIN "
				+ " usuario as us ON (fin.usuario_id =  us.id) " + " INNER JOIN "
				+ " autoescola as aut ON (us.autoescola_id = aut.id) INNER JOIN "
				+ " categoria AS c ON (fin.categoria_id = c.id) INNER JOIN "
				+ " aluno AS al ON (fin.aluno_id = al.id)  WHERE  aut.id=? AND fin.flag ='1' AND fin.tipo='2' "
				+ " group by fin.id ORDER BY fin.data ASC";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, id);

		ResultSet rs = stmt.executeQuery();

		Financeiro financeiro = null;

		while (rs.next()) {

			financeiro = new Financeiro();

			financeiro.setId(rs.getLong("id"));
			financeiro.setData(rs.getDate("data"));
			financeiro.setValor(rs.getDouble("valor"));
			financeiro.setFlag(rs.getInt("flag"));
			financeiro.setQtd(rs.getInt("qtd"));
			financeiro.setTipo(rs.getInt("idTipo"));
			financeiro.setDescricao(rs.getString("descricao"));
			financeiro.setDia(rs.getString("dia"));
			financeiro.setIdTipo(rs.getString("idDescricao"));
			financeiro.setIdFlag(rs.getString("FlagDesc"));
			financeiro.setValorTotal(rs.getDouble("total"));
			financeiro.setIdFormaPagamento(rs.getInt("pagamento"));

			Categoria cat = new Categoria();

			cat.setId(rs.getLong("idCat"));
			cat.setDescricao(rs.getString("categoria"));
			financeiro.setCategoria(cat);

			Aluno al = new Aluno();

			al.setId(rs.getLong("idAluno"));
			al.setNome(rs.getString("idNome"));
			financeiro.setAluno(al);

			Caixa cx = new Caixa();
			cx.setId(rs.getLong("caixa"));
			financeiro.setCaixa(cx);

			lista.add(financeiro);

		}

		stmt.close();
		con.close();

		return lista;

	}

	public double getTotalDespesa(Long id) throws SQLException {
		con = new ConnectionFactory().getConnection();

		double total = 0;

		String sql = "SELECT " + "SUM(valor) AS total " + "FROM " + "financeiro AS fin " + "INNER JOIN "
				+ "usuario AS us ON (us.id = fin.usuario_id) " + "INNER JOIN "
				+ "autoescola AS aut ON (aut.id = us.autoescola_id) " + "WHERE aut.id = ? AND "
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

	public List<Financeiro> contasAreceber(Long id) throws SQLException {

		List<Financeiro> lista = new ArrayList<Financeiro>();

		String sql = "SELECT " + "fin.id, " + "al.nome as idNome," + "sum(fin.valor) as total, " + "fin.data, "
				+ "fin.valor, " + "fin.flag, " + "fin.qtd, " + "fin.tipo as idTipo, " + "fin.servico_id AS servico, "
				+ "fin.aluno_id AS idAluno, " + "fin.descricao, " + "fin.categoria_id AS idCat, "
				+ "fin.caixa_id AS caixa, " + "fin.pagamento_id AS pagamento, " + "CASE "
				+ " WHEN DATEDIFF(fin.data, CURDATE()) = 5 THEN '05 DIAS PARA O PAGAMENTO' "
				+ " WHEN DATEDIFF(fin.data, CURDATE()) = 4 THEN '04 DIAS PARA O PAGAMENTO' "
				+ " WHEN DATEDIFF(fin.data, CURDATE()) = 3 THEN '03 DIAS PARA O PAGAMENTO' "
				+ " WHEN DATEDIFF(fin.data, CURDATE()) = 2 THEN '02 DIAS PARA O PAGAMENTO' "
				+ " WHEN DATEDIFF(fin.data, CURDATE()) = 1 THEN 'FALTA UM DIA PARA O PAGAMENTO' "
				+ " WHEN DATEDIFF(fin.data, CURDATE()) = 0 THEN 'DIA DO PAGAMENTO' "
				+ " WHEN DATEDIFF(fin.data, CURDATE()) <= 0 THEN 'ATRASADO' " + " ELSE 'AQUARDANDO PAGAMENTO' "
				+ " END AS dia, " + "c.descricao AS categoria, CASE " + " WHEN fin.tipo = '1' THEN 'Receita' "
				+ " WHEN fin.tipo = '2' THEN 'Despesa' " + " END " + " AS idDescricao, CASE "
				+ " WHEN fin.flag = '1' THEN 'Pago' " + " WHEN fin.flag = '2' THEN 'Em Aberto' " + " END "
				+ " AS FlagDesc " + "FROM financeiro AS fin INNER JOIN " + "usuario AS us ON (us.id = fin.usuario_id) "
				+ "INNER JOIN " + "categoria AS c ON (fin.categoria_id = c.id) " + "INNER JOIN "
				+ "autoescola AS aut ON (us.autoescola_id = aut.id) " + "INNER JOIN "
				+ "aluno AS al ON (fin.aluno_id = al.id) " + "WHERE "
				+ "fin.data > CURDATE() AND tipo = '1' AND aut.id= ? " + "GROUP BY fin.id " + "ORDER BY fin.data ASC";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, id);

		ResultSet rs = stmt.executeQuery();

		Financeiro financeiro = null;

		while (rs.next()) {

			financeiro = new Financeiro();

			financeiro.setId(rs.getLong("id"));
			financeiro.setData(rs.getDate("data"));
			financeiro.setValor(rs.getDouble("valor"));
			financeiro.setFlag(rs.getInt("flag"));
			financeiro.setQtd(rs.getInt("qtd"));
			financeiro.setTipo(rs.getInt("idTipo"));
			financeiro.setDescricao(rs.getString("descricao"));
			financeiro.setDia(rs.getString("dia"));
			financeiro.setIdTipo(rs.getString("idDescricao"));
			financeiro.setIdFlag(rs.getString("FlagDesc"));
			financeiro.setValorTotal(rs.getDouble("total"));
			financeiro.setIdFormaPagamento(rs.getInt("pagamento"));

			Categoria cat = new Categoria();

			cat.setId(rs.getLong("idCat"));
			cat.setDescricao(rs.getString("categoria"));
			financeiro.setCategoria(cat);

			Aluno al = new Aluno();

			al.setId(rs.getLong("idAluno"));
			al.setNome(rs.getString("idNome"));
			financeiro.setAluno(al);

			Caixa cx = new Caixa();
			cx.setId(rs.getLong("caixa"));
			financeiro.setCaixa(cx);

			lista.add(financeiro);

		}

		stmt.close();
		con.close();

		return lista;

	}

	public List<Financeiro> retornoAreceber(Long id) throws SQLException {

		List<Financeiro> lista = new ArrayList<Financeiro>();

		String sql = "SELECT " + "(a.c) - (b.d) as totalGeral, " + "financeiro.id AS id, "
				+ "financeiro.valor AS valor, " + "financeiro.data AS data, " + "CASE "
				+ "WHEN DATEDIFF(financeiro.data, CURDATE()) = 5 THEN '05 DIAS PARA O PAGAMENTO' "
				+ "WHEN DATEDIFF(financeiro.data, CURDATE()) = 4 THEN '04 DIAS PARA O PAGAMENTO' "
				+ "WHEN DATEDIFF(financeiro.data, CURDATE()) = 3 THEN '03 DIAS PARA O PAGAMENTO' "
				+ "WHEN DATEDIFF(financeiro.data, CURDATE()) = 2 THEN '02 DIAS PARA O PAGAMENTO' "
				+ "WHEN DATEDIFF(financeiro.data, CURDATE()) = 1 THEN 'FALTA UM DIA PARA O PAGAMENTO' "
				+ "WHEN DATEDIFF(financeiro.data, CURDATE()) = 0 THEN 'DIA DO PAGAMENTO' "
				+ "WHEN DATEDIFF(financeiro.data, CURDATE()) <= 0 THEN 'ATRASADO' " + "ELSE 'AQUARDANDO PAGAMENTO' "
				+ "END AS dia, " + "financeiro.valorExtenso AS valorExtenso, "
				+ "autoescola.cnpj AS `usuario.autoescola.cnpj`, " + "autoescola.logo AS `usuario.autoescola.logo`, "
				+ "autoescola.nome AS `usuario.autoescola.nome`, " + "usuario.nomeCompleto AS `usuario.nomeCompleto`, "
				+ "servico.descricao AS `aluno.servico.descricao`, " + "aluno.nome AS `aluno.nome`, "
				+ "categoria.descricao AS `categoria.descricao` " + "FROM (SELECT "
				+ "IFNULL(SUM(REPLACE(fin5.valor, ',', '.')), 0) AS c " + "FROM " + "financeiro AS fin5 "
				+ "INNER JOIN " + "usuario AS us5 ON (us5.id = fin5.usuario_id) "
				+ "Inner Join autoescola AS aut5 ON (us5.autoescola_id = aut5.id) " + "WHERE "
				+ "fin5.flag = '1' AND fin5.tipo = '1' AND us5.autoescola_id = ?) as a, " + "(SELECT "
				+ "IFNULL(SUM(REPLACE(fin4.valor, ',', '.')), 0) AS d " + "FROM " + "financeiro AS fin4 "
				+ "INNER JOIN " + "usuario AS us4 ON (us4.id = fin4.usuario_id) "
				+ "Inner Join autoescola AS aut4 ON (us4.autoescola_id = aut4.id) " + "WHERE "
				+ "fin4.flag = '1' AND fin4.tipo = '2' AND us4.autoescola_id = ?) as b "

				+ "INNER JOIN " + "financeiro AS financeiro " + "INNER JOIN "
				+ "usuario AS usuario ON (usuario.id = financeiro.usuario_id) " + "INNER JOIN "
				+ "autoescola AS autoescola ON (usuario.autoescola_id = autoescola.id) " + "INNER JOIN "
				+ "aluno AS aluno ON (financeiro.aluno_id = aluno.id) " + "INNER JOIN "
				+ "categoria AS categoria ON (financeiro.categoria_id = categoria.id) " + "LEFT JOIN "
				+ "servico AS servico ON (servico.id = aluno.servico_id) " + "WHERE "
				+ "usuario.autoescola_id = ? AND financeiro.data > CURDATE() AND financeiro.tipo = '1' AND financeiro.flag = '2' ";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, id);
		stmt.setLong(2, id);
		stmt.setLong(3, id);

		ResultSet rs = stmt.executeQuery();

		Financeiro financeiro = null;

		while (rs.next()) {

			financeiro = new Financeiro();

			financeiro.setId(rs.getLong("id"));
			financeiro.setTotalGeral(rs.getDouble("totalGeral"));
			financeiro.setData(rs.getDate("data"));
			financeiro.setValor(rs.getDouble("valor"));
			financeiro.setDia(rs.getString("dia"));

			Categoria cat = new Categoria();

			cat.setDescricao(rs.getString("categoria.descricao"));
			financeiro.setCategoria(cat);

			Aluno al = new Aluno();

			al.setNome(rs.getString("aluno.nome"));

			Servico serv = new Servico();

			serv.setDescricao(rs.getString("aluno.servico.descricao"));
			al.setServico(serv);

			financeiro.setAluno(al);

			Usuario us = new Usuario();

			us.setNomeCompleto(rs.getString("usuario.nomeCompleto"));

			AutoEscola aut = new AutoEscola();

			aut.setNome(rs.getString("usuario.autoescola.nome"));
			aut.setLogo(rs.getString("usuario.autoescola.logo"));
			aut.setCnpj(rs.getString("usuario.autoescola.cnpj"));

			us.setAutoescola(aut);

			financeiro.setUsuario(us);

			lista.add(financeiro);

		}

		stmt.close();
		con.close();

		return lista;
	}

	public double getTotalAreceber(Long id) throws SQLException {
		con = new ConnectionFactory().getConnection();

		double total = 0;

		String sql = "SELECT " + "SUM(valor) AS total " + "FROM " + "financeiro AS fin " + "INNER JOIN "
				+ "usuario AS us ON (us.id = fin.usuario_id) " + "INNER JOIN "
				+ "autoescola AS aut ON (aut.id = us.autoescola_id) " + "WHERE "
				+ "data > CURDATE() AND tipo = '1' AND flag = '2' AND  aut.id= ?";

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

	public List<Financeiro> contasApagar(Long id) throws SQLException {

		List<Financeiro> lista = new ArrayList<Financeiro>();

		String sql = "SELECT " + "fin.id, " + "al.nome as idNome," + "sum(fin.valor) as total, " + "fin.data, "
				+ "fin.valor, " + "fin.flag, " + "fin.qtd, " + "fin.tipo as idTipo, " + "fin.servico_id AS servico, "
				+ "fin.aluno_id AS idAluno, " + "fin.descricao, " + "fin.categoria_id AS idCat, "
				+ "fin.caixa_id AS caixa, " + "fin.pagamento_id AS pagamento, " + "CASE "
				+ " WHEN DATEDIFF(fin.data, CURDATE()) = 5 THEN '05 DIAS PARA O PAGAMENTO' "
				+ " WHEN DATEDIFF(fin.data, CURDATE()) = 4 THEN '04 DIAS PARA O PAGAMENTO' "
				+ " WHEN DATEDIFF(fin.data, CURDATE()) = 3 THEN '03 DIAS PARA O PAGAMENTO' "
				+ " WHEN DATEDIFF(fin.data, CURDATE()) = 2 THEN '02 DIAS PARA O PAGAMENTO' "
				+ " WHEN DATEDIFF(fin.data, CURDATE()) = 1 THEN 'FALTA UM DIA PARA O PAGAMENTO' "
				+ " WHEN DATEDIFF(fin.data, CURDATE()) = 0 THEN 'DIA DO PAGAMENTO' "
				+ " WHEN DATEDIFF(fin.data, CURDATE()) <= 0 THEN 'ATRASADO' " + " ELSE 'AQUARDANDO PAGAMENTO' "
				+ " END AS dia, " + "c.descricao AS categoria, CASE " + " WHEN fin.tipo = '1' THEN 'Receita' "
				+ " WHEN fin.tipo = '2' THEN 'Despesa' " + " END " + " AS idDescricao, CASE "
				+ " WHEN fin.flag = '1' THEN 'Pago' " + " WHEN fin.flag = '2' THEN 'Em Aberto' " + " END "
				+ " AS FlagDesc " + "FROM  financeiro AS fin " + "INNER JOIN "
				+ "categoria AS c ON (fin.categoria_id = c.id) " + "INNER JOIN "
				+ "aluno AS al ON (fin.aluno_id = al.id) " + "INNER JOIN "
				+ "usuario AS us ON (us.id = fin.usuario_id) " + "INNER JOIN "
				+ "autoescola AS aut ON (us.autoescola_id = aut.id) "
				+ "WHERE aut.id=? AND fin.data > curdate() AND fin.tipo = '2' AND fin.tipo = '2' "
				+ "group by fin.id ORDER BY fin.data ASC";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, id);

		ResultSet rs = stmt.executeQuery();

		Financeiro financeiro = null;

		while (rs.next()) {

			financeiro = new Financeiro();

			financeiro.setId(rs.getLong("id"));
			financeiro.setData(rs.getDate("data"));
			financeiro.setValor(rs.getDouble("valor"));
			financeiro.setFlag(rs.getInt("flag"));
			financeiro.setQtd(rs.getInt("qtd"));
			financeiro.setTipo(rs.getInt("idTipo"));
			financeiro.setDescricao(rs.getString("descricao"));
			financeiro.setDia(rs.getString("dia"));
			financeiro.setIdTipo(rs.getString("idDescricao"));
			financeiro.setIdFlag(rs.getString("FlagDesc"));
			financeiro.setValorTotal(rs.getDouble("total"));
			financeiro.setIdFormaPagamento(rs.getInt("pagamento"));

			Categoria cat = new Categoria();

			cat.setId(rs.getLong("idCat"));
			cat.setDescricao(rs.getString("categoria"));
			financeiro.setCategoria(cat);

			Aluno al = new Aluno();

			al.setId(rs.getLong("idAluno"));
			al.setNome(rs.getString("idNome"));
			financeiro.setAluno(al);

			Caixa cx = new Caixa();
			cx.setId(rs.getLong("caixa"));
			financeiro.setCaixa(cx);

			lista.add(financeiro);

		}

		stmt.close();
		con.close();

		return lista;

	}
	
	public List<Financeiro> retornoApagar(Long id) throws SQLException {

		List<Financeiro> lista = new ArrayList<Financeiro>();

		String sql = "SELECT "
				+ "(a.c) - (b.d) as totalGeral, "
				+ "financeiro.id AS id, "
				+ "financeiro.valor AS valor, "
				+ "financeiro.data AS data, "
				+ "CASE "
				+ "WHEN DATEDIFF(financeiro.data, CURDATE()) = 5 THEN '05 DIAS PARA O PAGAMENTO' "
				+ "WHEN DATEDIFF(financeiro.data, CURDATE()) = 4 THEN '04 DIAS PARA O PAGAMENTO' "
				+ "WHEN DATEDIFF(financeiro.data, CURDATE()) = 3 THEN '03 DIAS PARA O PAGAMENTO' "
				+ "WHEN DATEDIFF(financeiro.data, CURDATE()) = 2 THEN '02 DIAS PARA O PAGAMENTO' "
				+ "WHEN DATEDIFF(financeiro.data, CURDATE()) = 1 THEN 'FALTA UM DIA PARA O PAGAMENTO' "
				+ "WHEN DATEDIFF(financeiro.data, CURDATE()) = 0 THEN 'DIA DO PAGAMENTO' "
				+ "WHEN DATEDIFF(financeiro.data, CURDATE()) <= 0 THEN 'ATRASADO' "
				+ "ELSE 'AQUARDANDO PAGAMENTO' "
				+ "END AS dia, "
				+ "financeiro.valorExtenso AS valorExtenso, "
				+ "autoescola.cnpj AS `usuario.autoescola.cnpj`, "
				+ "autoescola.logo AS `usuario.autoescola.logo`, "
				+ "autoescola.nome AS `usuario.autoescola.nome`, "
				+ "usuario.nomeCompleto AS `usuario.nomeCompleto`, "
				+ "servico.descricao AS `aluno.servico.descricao`, "
				+ "aluno.nome AS `aluno.nome`, "
				+ "categoria.descricao AS `categoria.descricao` "
				+ "FROM (SELECT "
						+ "IFNULL(SUM(REPLACE(fin5.valor, ',', '.')), 0) AS c "
						+ "FROM "
						+ "financeiro AS fin5 "
						+ "INNER JOIN "
						+ "usuario AS us5 ON (us5.id = fin5.usuario_id) "
						+ "Inner Join autoescola AS aut5 ON (us5.autoescola_id = aut5.id) "
						+ "WHERE "
						+ "fin5.flag = '1' AND fin5.tipo = '1' AND us5.autoescola_id = ?) as a, "
				+ "(SELECT "
						+ "IFNULL(SUM(REPLACE(fin4.valor, ',', '.')), 0) AS d "
						+ "FROM "
						+ "financeiro AS fin4 "
						+ "INNER JOIN "
						+ "usuario AS us4 ON (us4.id = fin4.usuario_id) "
						+ "Inner Join autoescola AS aut4 ON (us4.autoescola_id = aut4.id) "
						+ "WHERE "
						+ "fin4.flag = '1' AND fin4.tipo = '2' AND us4.autoescola_id = ?) as b "

				+ "INNER JOIN "
				+ "financeiro AS financeiro "
				+ "INNER JOIN "
				+ "usuario AS usuario ON (usuario.id = financeiro.usuario_id) "
				+ "INNER JOIN "
				+ "autoescola AS autoescola ON (usuario.autoescola_id = autoescola.id) "
				+ "INNER JOIN "
				+ "aluno AS aluno ON (financeiro.aluno_id = aluno.id) "
				+ "INNER JOIN "
				+ "categoria AS categoria ON (financeiro.categoria_id = categoria.id) "
				+ "LEFT JOIN "
				+ "servico AS servico ON (servico.id = aluno.servico_id) "
				+ "WHERE "
				+ "usuario.autoescola_id = ?  AND financeiro.data > CURDATE() AND financeiro.tipo = '2' "
				+ "AND financeiro.flag = '2'";

	con = new ConnectionFactory().getConnection();

	PreparedStatement stmt = con.prepareStatement(sql);
	stmt.setLong(1, id);
	stmt.setLong(2, id);
	stmt.setLong(3, id);

	ResultSet rs = stmt.executeQuery();

	Financeiro financeiro = null;

	while (rs.next()) {

		financeiro = new Financeiro();

		financeiro.setId(rs.getLong("id"));
		financeiro.setTotalGeral(rs.getDouble("totalGeral"));
		financeiro.setData(rs.getDate("data"));
		financeiro.setValor(rs.getDouble("valor"));
		financeiro.setDia(rs.getString("dia"));

		Categoria cat = new Categoria();

		
		cat.setDescricao(rs.getString("categoria.descricao"));
		financeiro.setCategoria(cat);

		Aluno al = new Aluno();

		
		al.setNome(rs.getString("aluno.nome"));
		
		Servico serv = new Servico();
		
		serv.setDescricao(rs.getString("aluno.servico.descricao"));
		al.setServico(serv);
		
		financeiro.setAluno(al);
		
	
		
		Usuario us = new Usuario();
		
		
		us.setNomeCompleto(rs.getString("usuario.nomeCompleto"));
		
		AutoEscola aut = new AutoEscola();
		
	
		aut.setNome(rs.getString("usuario.autoescola.nome"));
		aut.setLogo(rs.getString("usuario.autoescola.logo"));
		aut.setCnpj(rs.getString("usuario.autoescola.cnpj"));
		
		us.setAutoescola(aut);
		
		financeiro.setUsuario(us);

		lista.add(financeiro);

	}

	stmt.close();
	con.close();

	return lista;

}



	public double getTotalApagar(Long id) throws SQLException {
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

}
