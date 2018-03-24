package com.java.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.java.conexao.ConnectionFactory;
import com.java.modelo.Aluno;
import com.java.modelo.Provalegislacao;

public class ProvaLegislacaoDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Connection con;

	public Provalegislacao retornarProvalegislacaoPorID(Long id) throws ClassNotFoundException, SQLException {

		String sql = "select id,data,hora,aluno_id as aluno,status,aparovado from provalegislacao where id = ? ";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, id);

		ResultSet rs = stmt.executeQuery();

		Provalegislacao provalegislacao = null;

		while (rs.next()) {

			provalegislacao = new Provalegislacao();

			provalegislacao.setId(rs.getLong("id"));
			provalegislacao.setData(rs.getDate("data"));
			provalegislacao.setHora(rs.getTime("hora"));
			provalegislacao.setStatus(rs.getInt("status"));
			provalegislacao.setAprovado(rs.getInt("aprovado"));
			

			Aluno al = new Aluno();

			al.setId(rs.getLong("aluno"));

			provalegislacao.setAluno(al);

		}

		stmt.close();
		con.close();

		return provalegislacao;

	}

	public List<Provalegislacao> listarTodos() throws SQLException {

		List<Provalegislacao> lista = new ArrayList<Provalegislacao>();

		String sql = "select pr.id,pr.data,pr.hora,al.id as aluno,al.nome as nome,al.whatsapp,al.email, pr.status as status,pr.aprovado, CASE " 
			    + "WHEN DATEDIFF(pr.data, CURDATE()) = 5 THEN 'Faltam apenas 05 dias !' "
				+ "WHEN DATEDIFF(pr.data, CURDATE()) = 4 THEN 'Faltam apenas 04 dias !' "
				+ "WHEN DATEDIFF(pr.data, CURDATE()) = 3 THEN 'Faltam apenas 03 dias !' "
				+ "WHEN DATEDIFF(pr.data, CURDATE()) = 2 THEN 'Faltam apenas 02 dias !' "
				+ "WHEN DATEDIFF(pr.data, CURDATE()) = 1 THEN 'Falta apenas um dia !' "
				+ "WHEN DATEDIFF(pr.data, CURDATE()) = 0 THEN 'Dia da Prova' "
				+ "WHEN DATEDIFF(pr.data, CURDATE()) <= 0 THEN 'Expirou o prazo' ELSE 'Prova Agendada' "
				+ "END AS dia from provalegislacao as pr inner join aluno as al on(al.id = pr.aluno_id) order by pr.data ASC";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		Provalegislacao provalegislacao = null;

		while (rs.next()) {

			provalegislacao = new Provalegislacao();

			provalegislacao.setId(rs.getLong("id"));
			provalegislacao.setData(rs.getDate("data"));
			provalegislacao.setHora(rs.getTime("hora"));
			provalegislacao.setDia(rs.getString("dia"));
			provalegislacao.setStatus(rs.getInt("status"));
			provalegislacao.setAprovado(rs.getInt("aprovado"));

			Aluno al = new Aluno();

			al.setId(rs.getLong("aluno"));
			al.setNome(rs.getString("nome"));
			al.setEmail(rs.getString("email"));
			al.setWhatsapp(rs.getString("whatsapp"));

			provalegislacao.setAluno(al);

			lista.add(provalegislacao);

		}

		stmt.close();
		con.close();

		return lista;

	}

	public void incluir(Provalegislacao provalegislacao) throws SQLException {

		con = new ConnectionFactory().getConnection();

		SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		SimpleDateFormat dt2 = new SimpleDateFormat("HH:mm");

		String dataFormatada = dt1.format(provalegislacao.getData());

		String dataFormatada2 = dt2.format(provalegislacao.getHora());

		String sql = "INSERT INTO provalegislacao (data,hora,aluno_id,status,aprovado) " + "VALUES (?,?,?,?,?)";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setString(1, dataFormatada);
		stmt.setString(2, dataFormatada2);
		stmt.setLong(3, provalegislacao.getAluno().getId());
		stmt.setInt(4, 2);
		stmt.setInt(5, 1);

		stmt.execute();
		stmt.close();
		con.close();

	}
	
	public void alterarTab(Provalegislacao provalegislacao) throws SQLException {

		con = new ConnectionFactory().getConnection();

		String sql = "update provalegislacao  set " + "status=?,aprovado=? where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setInt(1, provalegislacao.getStatus());
		stmt.setInt(2, provalegislacao.getAprovado());

		stmt.setLong(3, provalegislacao.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

	public void alterar(Provalegislacao provalegislacao) throws SQLException {

		con = new ConnectionFactory().getConnection();

		SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		SimpleDateFormat dt2 = new SimpleDateFormat("HH:mm");

		String dataFormatada = dt1.format(provalegislacao.getData());

		String dataFormatada2 = dt2.format(provalegislacao.getHora());

		String sql = "update provalegislacao set " + "data=?,hora=?,aluno_id=?,status=? where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);


		stmt.setString(1, dataFormatada);
		stmt.setString(2, dataFormatada2);
		stmt.setLong(3, provalegislacao.getAluno().getId());
		stmt.setInt(4, provalegislacao.getStatus());
		stmt.setLong(5, provalegislacao.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

	public void excluir(Provalegislacao provalegislacao) throws SQLException {

		con = new ConnectionFactory().getConnection();

		String sql = "delete from provalegislacao where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setLong(1, provalegislacao.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

}
