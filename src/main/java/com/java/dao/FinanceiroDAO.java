package com.java.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.java.conexao.ConnectionFactory;
import com.java.modelo.Aluno;
import com.java.modelo.AutoEscola;
import com.java.modelo.Caixa;
import com.java.modelo.Categoria;
import com.java.modelo.Financeiro;
import com.java.modelo.Servico;
import com.java.modelo.Usuario;
import com.java.util.Extenso;

public class FinanceiroDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Connection con;

	public Financeiro retornarFinanceiroPorID(Long id) throws ClassNotFoundException, SQLException {

		String sql = "select id,"
				+ "data,"
				+ "valor,"
				+ "flag,"
				+ "tipo,"
				+ "descricao,"
				+ "categoria_id as cat,"
				+ "caixa_id as caixa,"
				+ "pagamento_id as pagamento,valorExtenso "
				+ "from financeiro where id = ? ";

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
			financeiro.setDescricao(rs.getString("descricao"));
			
			Categoria cat = new Categoria();
			cat.setId(rs.getLong("cat"));
			financeiro.setCategoria(cat);
			
			Caixa cx = new Caixa();
			cx.setId(rs.getLong("caixa"));
			financeiro.setCaixa(cx);
			financeiro.setIdFormaPagamento(rs.getInt("pagamento"));

		}

		stmt.close();
		con.close();

		return financeiro;

	}
	
	
	public List<Financeiro> listarTodos() throws SQLException {

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
				+ " AS FlagDesc " + "FROM " + " financeiro AS fin " + "INNER JOIN "
				+ " categoria AS c ON (fin.categoria_id = c.id) INNER JOIN "
				+ " aluno AS al ON (fin.aluno_id = al.id)  " + " group by fin.id ORDER BY fin.data ASC";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);

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
			
			double receita = this.getTotalReceita();
			
			double despesa = this.getTotalDespesa();
			
			double valorRelatorio = receita - despesa ; 
			
			financeiro.setTotalRelatorio(valorRelatorio);

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
	
	
	public List<Financeiro> listarTodosUsuariosPorAutoEscola(Long id) throws SQLException {

		List<Financeiro> lista = new ArrayList<Financeiro>();

		String sql = "SELECT fin.id, al.nome as idNome, sum(fin.valor) as total, fin.data, "
						+ "fin.valor, fin.flag, fin.qtd, fin.tipo as idTipo, fin.servico_id AS servico, "
						+ "fin.aluno_id AS idAluno, fin.descricao, fin.categoria_id AS idCat, "
						+ "fin.caixa_id AS caixa, fin.pagamento_id AS pagamento, CASE "
						+ "WHEN DATEDIFF(fin.data, CURDATE()) = 5 THEN '05 DIAS PARA O PAGAMENTO' "
						+ "WHEN DATEDIFF(fin.data, CURDATE()) = 4 THEN '04 DIAS PARA O PAGAMENTO' "
				    	+ "WHEN DATEDIFF(fin.data, CURDATE()) = 3 THEN '03 DIAS PARA O PAGAMENTO' "
						+ "WHEN DATEDIFF(fin.data, CURDATE()) = 2 THEN '02 DIAS PARA O PAGAMENTO' "
						+ "WHEN DATEDIFF(fin.data, CURDATE()) = 1 THEN 'FALTA UM DIA PARA O PAGAMENTO' "
						+ "WHEN DATEDIFF(fin.data, CURDATE()) = 0 THEN 'DIA DO PAGAMENTO' "
						+ "WHEN DATEDIFF(fin.data, CURDATE()) <= 0 THEN 'ATRASADO'  ELSE 'AQUARDANDO PAGAMENTO' "
						+ "END AS dia, c.descricao AS categoria, CASE  WHEN fin.tipo = '1' THEN 'Receita' "
						+ "WHEN fin.tipo = '2' THEN 'Despesa'  END  AS idDescricao, CASE "
						+ "WHEN fin.flag = '1' THEN 'Pago'  WHEN fin.flag = '2' THEN 'Em Aberto'  END "
						+ "AS FlagDesc,aut.id as idAuto,aut.nome as nomeAut,aut.logo,"
						+ "aut.cnpj,us.id as idUser, us.nomeCompleto as nomeCompleto,fin.valorExtenso  "
						+ "FROM  financeiro AS fin INNER JOIN "
						+ "categoria AS c ON (fin.categoria_id = c.id) INNER JOIN "
						+ "aluno AS al ON (fin.aluno_id = al.id) INNER Join usuario AS us ON (fin.usuario_id = us.id) "
						+ "INNER JOIN "
						+ "autoescola AS aut ON (us.autoescola_id = aut.id) Where autoescola_id =?  group by fin.id ORDER BY fin.data ASC";

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
			
			double receita = this.getTotalReceita();
			
			double despesa = this.getTotalDespesa();
			
			double valorRelatorio = receita - despesa ; 
			
			financeiro.setTotalRelatorio(valorRelatorio);

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
			
			Usuario us = new Usuario();
			
			us.setId(rs.getLong("idUser"));
			us.setNomeCompleto(rs.getString("nomeCompleto"));
			
			AutoEscola aut = new AutoEscola();
			
			aut.setId(rs.getLong("idAuto"));
			aut.setNome(rs.getString("nomeAut"));
			aut.setLogo(rs.getString("logo"));
			aut.setCnpj(rs.getString("cnpj"));
			
			us.setAutoescola(aut);
			
			financeiro.setUsuario(us);

			lista.add(financeiro);

		}

		stmt.close();
		con.close();

		return lista;

	}
	
	
	public List<Financeiro> listarTodosUsuariosPorAutoEscola2(Long id) throws SQLException {

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
					+ "usuario.autoescola_id = ?";

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
	
	
	public List<Financeiro> retorDespesas(Long id) throws SQLException {

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
					+ "usuario.autoescola_id = ?  AND financeiro.flag = '1' AND financeiro.tipo='2'";

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
	
	
	
	
	public double getTotalReceita() throws SQLException {
		con = new ConnectionFactory().getConnection();

		double total = 0;

		String sql = "SELECT sum(valor) as total FROM financeiro as fin where flag='1' AND fin.tipo='1' ";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

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
	
	
	public double getTotalDespesa() throws SQLException {
		con = new ConnectionFactory().getConnection();

		double total = 0;

		String sql = "SELECT sum(valor) as total FROM financeiro as fin where flag='1' AND fin.tipo='2' AND fin.caixa_id='1'";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

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
	
	
	
	
	public List<Financeiro> retornoIdRecibo(Long id) throws SQLException {

		List<Financeiro> lista = new ArrayList<Financeiro>();

		String sql = "select fin.id as id,"
				             + "fin.valor,"
				             + "fin.data,"
				             + "fin.valorExtenso,"
				             + "aut.razao,"
				             + "aut.cnpj,"
				             + "aut.logo,"
				             + "aut.nome as autoEscola,"
				             + "us.nomeCompleto as nomeCompleto, "
				             + "al.nome as nomeAluno, "
				             + "serv.descricao as descricaoServico, "
				             + "cat.descricao as cateDescricao "
				             + "from financeiro as fin "
				    + "inner join usuario as us ON (us.id = fin.usuario_id) "
				    + "inner join autoescola as aut ON (us.autoescola_id = aut.id) "
                    + "inner join aluno as al ON (fin.aluno_id = al.id) "
                    + "inner join categoria as cat ON (fin.categoria_id = cat.id) "
                    + "INNER JOIN servico as serv ON (serv.id = al.servico_id) "
                    + "WHERE fin.id = ?";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);

		stmt.setLong(1, id);

		ResultSet rs = stmt.executeQuery();

		Financeiro financeiro = null;

		while (rs.next()) {

			financeiro = new Financeiro();
			
			financeiro.setId(rs.getLong("id"));
			financeiro.setValor(rs.getDouble("valor"));
			financeiro.setValorExtenso(rs.getString("valorExtenso"));
			financeiro.setData(rs.getDate("data"));
			
			Categoria cat = new Categoria();

			cat.setDescricao(rs.getString("cateDescricao"));
			financeiro.setCategoria(cat);
			
			Aluno al = new Aluno();

			al.setNome(rs.getString("NomeAluno"));
			financeiro.setAluno(al);
			
			Servico serv = new Servico();
			
			serv.setDescricao(rs.getString("descricaoServico"));
			financeiro.setServico(serv);

			Usuario use = new Usuario();
			
			use.setNomeCompleto(rs.getString("nomeCompleto"));
			
			AutoEscola aut = new AutoEscola();
			aut.setNome(rs.getString("autoEscola"));
			aut.setRazao(rs.getString("razao"));
			aut.setCnpj(rs.getString("cnpj"));
			aut.setLogo(rs.getString("logo"));
			use.setAutoescola(aut);
			
			financeiro.setUsuario(use);
			
			lista.add(financeiro);

		}

		stmt.close();
		con.close();

		return lista;

	}
	
	
	public List<Financeiro> listarTodosPorData(Date data_inicio, Date data_fim) throws SQLException {

		List<Financeiro> lista = new ArrayList<Financeiro>();

		String sql = "SELECT " + "fin.id, " + "fin.valorExtenso," + "al.nome as idNome," + "sum(fin.valor) as total, " + "fin.data, "
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
				+ " AS FlagDesc " + "FROM " + " financeiro AS fin " + "INNER JOIN "
				+ " categoria AS c ON (fin.categoria_id = c.id) INNER JOIN "
				+ " aluno AS al ON (fin.aluno_id = al.id) WHERE fin.data BETWEEN ? AND ? "
				+ " group by fin.id ORDER BY fin.data ASC";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);

		stmt.setDate(1, new java.sql.Date(data_inicio.getTime()));
		stmt.setDate(2, new java.sql.Date(data_fim.getTime()));

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

			Categoria cat = new Categoria();

			cat.setId(rs.getLong("idCat"));
			cat.setDescricao(rs.getString("categoria"));
			financeiro.setCategoria(cat);

			Aluno al = new Aluno();

			al.setId(rs.getLong("idAluno"));
			al.setNome(rs.getString("idNome"));
			financeiro.setAluno(al);

			lista.add(financeiro);

		}

		stmt.close();
		con.close();

		return lista;

	}


	
	public void incluirFinanceiro(Financeiro financeiro) throws SQLException {
		

		con = new ConnectionFactory().getConnection();
		
		double vl = financeiro.getValor();
		String valorExtenso = new Extenso(vl).toString(); 

		SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
		String dataFormatada = dt1.format(financeiro.getData());

		String sql = "INSERT INTO financeiro (data,"
						+ "valor,"
						+ "flag,"
						+ "qtd,"
						+ "tipo,"
						+ "descricao,"
						+ "categoria_id,"
						+ "caixa_id,"
						+ "pagamento_id,"
						+ "usuario_id,"
						+ "aluno_id,"
						+ "valorExtenso,servico_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setString(1, dataFormatada);
		stmt.setDouble(2, financeiro.getValor());
		stmt.setInt(3, financeiro.getFlag());
		stmt.setInt(4, financeiro.getQtd());
		stmt.setInt(5, 2);
		stmt.setString(6, financeiro.getDescricao());
		stmt.setLong(7, financeiro.getCategoria().getId());
		stmt.setLong(8, financeiro.getCaixa().getId());
		stmt.setInt(9, financeiro.getIdFormaPagamento());
		stmt.setLong(10, financeiro.getUsuario().getId());
		stmt.setLong(11, 1L);
		stmt.setString(12, valorExtenso);
		stmt.setLong(13, 26L);

		stmt.execute();
		stmt.close();
		con.close();

	}
	
	public double getTotalData(Date data_inicio, Date data_fim) throws SQLException {
		con = new ConnectionFactory().getConnection();

		double total = 0;

		String sql = "SELECT sum(valor) as total FROM financeiro  WHERE data BETWEEN ? AND ?";

		PreparedStatement stmt2;

		stmt2 = con.prepareStatement(sql);

		stmt2.setDate(1, new java.sql.Date(data_inicio.getTime()));
		stmt2.setDate(2, new java.sql.Date(data_fim.getTime()));

		ResultSet rs = stmt2.executeQuery();

		while (rs.next()) {
			if (rs.getDouble("total") != 0) {
				total = rs.getDouble("total");
			} else {
				total = 0;
			}
		}

		rs.close();

		stmt2.close();
		con.close();

		return total;
	}
	
	
	public void incluir(Financeiro financeiro) throws SQLException {

		con = new ConnectionFactory().getConnection();
		
		double valorExtenso = financeiro.getValor();

		String ve = new Extenso(valorExtenso).toString();

		SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
		String dataFormatada = dt1.format(financeiro.getData());

		String sql = "INSERT INTO financeiro (data,valor,flag,qtd,tipo,servico_id,"
				+ "aluno_id,descricao,categoria_id,caixa_id,pagamento_id,usuario_id,valorExtenso) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setString(1, dataFormatada);
		stmt.setDouble(2, financeiro.getValor());
		stmt.setInt(3, financeiro.getFlag());
		stmt.setInt(4, financeiro.getQtd());
		stmt.setInt(5, 2);
		stmt.setLong(6, financeiro.getServico().getId());
		stmt.setLong(7, financeiro.getAluno().getId());
		stmt.setString(8, financeiro.getDescricao());
		stmt.setLong(9, financeiro.getCategoria().getId());
		stmt.setLong(10, financeiro.getCaixa().getId());
		stmt.setInt(11, financeiro.getIdFormaPagamento());
		stmt.setLong(12, financeiro.getUsuario().getId());
		stmt.setString(13, ve);

		stmt.execute();
		stmt.close();
		con.close();

	}


	public void alterar(Financeiro financeiro) throws SQLException {

		con = new ConnectionFactory().getConnection();
		
		double vl = financeiro.getValor();
		String valorExtenso = new Extenso(vl).toString(); 


		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");


		String sql = "update financeiro set "
				                            + "data=?,"
				                            + "valor=?,"
				                            + "flag=?,"
				                            + "tipo=?,"
				                            + "descricao=?,"
				                            + "categoria_id=?,"
				                            + "caixa_id=?,"
				                            + "pagamento_id=?,"
				                            + "usuario_id=?,valorExtenso=? where id = ?";

											PreparedStatement stmt;
									
									
											stmt = con.prepareStatement(sql);
									
											stmt.setString(1, formatador.format( data ));
											stmt.setDouble(2, financeiro.getValor());
											stmt.setInt(3, financeiro.getFlag());
											stmt.setInt(4, financeiro.getTipo());
											stmt.setString(5, financeiro.getDescricao());
											stmt.setLong(6, financeiro.getCategoria().getId());
											stmt.setLong(7, financeiro.getCaixa().getId());
											stmt.setInt(8, financeiro.getIdFormaPagamento());
											stmt.setLong(9, financeiro.getUsuario().getId());
											stmt.setString(10, valorExtenso);
											stmt.setLong(11,financeiro.getId());
									
									
											stmt.execute();
											stmt.close();
											con.close();

	}

	public void excluir(Financeiro financeiro) throws SQLException {

		con = new ConnectionFactory().getConnection();

		String sql = "delete from financeiro where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setLong(1, financeiro.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

}

