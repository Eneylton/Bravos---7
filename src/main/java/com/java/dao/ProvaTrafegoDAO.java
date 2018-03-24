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
import com.java.modelo.ProvaTrafego;

public class ProvaTrafegoDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Connection con;

	public ProvaTrafego retornarProvaTrafegoPorID(Long id) throws ClassNotFoundException, SQLException {

		String sql = "select id,data,hora,aluno_id as aluno,status,aparovado from provatrafego where id = ? ";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, id);

		ResultSet rs = stmt.executeQuery();

		ProvaTrafego provaTrafego = null;

		while (rs.next()) {

			provaTrafego = new ProvaTrafego();

			provaTrafego.setId(rs.getLong("id"));
			provaTrafego.setData(rs.getDate("data"));
			provaTrafego.setHora(rs.getTime("hora"));
			provaTrafego.setStatus(rs.getInt("status"));
			provaTrafego.setAprovado(rs.getInt("aprovado"));
			

			Aluno al = new Aluno();

			al.setId(rs.getLong("aluno"));

			provaTrafego.setAluno(al);

		}

		stmt.close();
		con.close();

		return provaTrafego;

	}

	public List<ProvaTrafego> listarTodos() throws SQLException {

		List<ProvaTrafego> lista = new ArrayList<ProvaTrafego>();

		String sql = "select pr.id,pr.data,pr.hora,al.id as aluno,al.nome as nome,al.whatsapp,al.email,pr.status as status,pr.aprovado, CASE " 
			    + "WHEN DATEDIFF(pr.data, CURDATE()) = 5 THEN 'Faltam apenas 05 dias !' "
				+ "WHEN DATEDIFF(pr.data, CURDATE()) = 4 THEN 'Faltam apenas 04 dias !' "
				+ "WHEN DATEDIFF(pr.data, CURDATE()) = 3 THEN 'Faltam apenas 03 dias !' "
				+ "WHEN DATEDIFF(pr.data, CURDATE()) = 2 THEN 'Faltam apenas 02 dias !' "
				+ "WHEN DATEDIFF(pr.data, CURDATE()) = 1 THEN 'Falta apenas um dia !' "
				+ "WHEN DATEDIFF(pr.data, CURDATE()) = 0 THEN 'Dia da Prova' "
				+ "WHEN DATEDIFF(pr.data, CURDATE()) <= 0 THEN 'Expirou o prazo' ELSE 'Prova Agendada' "
				+ "END AS dia from provatrafego as pr inner join aluno as al on(al.id = pr.aluno_id) order by pr.data ASC";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		ProvaTrafego provaTrafego = null;

		while (rs.next()) {

			provaTrafego = new ProvaTrafego();

			provaTrafego.setId(rs.getLong("id"));
			provaTrafego.setData(rs.getDate("data"));
			provaTrafego.setHora(rs.getTime("hora"));
			provaTrafego.setDia(rs.getString("dia"));
			provaTrafego.setStatus(rs.getInt("status"));
			provaTrafego.setAprovado(rs.getInt("aprovado"));

			Aluno al = new Aluno();

			al.setId(rs.getLong("aluno"));
			al.setNome(rs.getString("nome"));
			al.setEmail(rs.getString("email"));
			al.setWhatsapp(rs.getString("whatsapp"));

			provaTrafego.setAluno(al);

			lista.add(provaTrafego);

		}

		stmt.close();
		con.close();

		return lista;

	}

	public void incluir(ProvaTrafego provaTrafego) throws SQLException {

		con = new ConnectionFactory().getConnection();

		SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		SimpleDateFormat dt2 = new SimpleDateFormat("HH:mm");

		String dataFormatada = dt1.format(provaTrafego.getData());

		String dataFormatada2 = dt2.format(provaTrafego.getHora());

		String sql = "INSERT INTO provatrafego (data,hora,aluno_id,status,aprovado) " + "VALUES (?,?,?,?,?)";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setString(1, dataFormatada);
		stmt.setString(2, dataFormatada2);
		stmt.setLong(3, provaTrafego.getAluno().getId());
		stmt.setInt(4, 2);
		stmt.setInt(5, 1);

		stmt.execute();
		stmt.close();
		con.close();

	}
	
	public void alterarTab(ProvaTrafego provaTrafego) throws SQLException {

		con = new ConnectionFactory().getConnection();

		String sql = "update provatrafego set " + "status=?,aprovado=? where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setInt(1, provaTrafego.getStatus());
		stmt.setInt(2, provaTrafego.getAprovado());

		stmt.setLong(3, provaTrafego.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

	public void alterar(ProvaTrafego provaTrafego) throws SQLException {

		con = new ConnectionFactory().getConnection();

		SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		SimpleDateFormat dt2 = new SimpleDateFormat("HH:mm");

		String dataFormatada = dt1.format(provaTrafego.getData());

		String dataFormatada2 = dt2.format(provaTrafego.getHora());

		String sql = "update provatrafego set " + "data=?,hora=?,aluno_id=?,status=? where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);


		stmt.setString(1, dataFormatada);
		stmt.setString(2, dataFormatada2);
		stmt.setLong(3, provaTrafego.getAluno().getId());
		stmt.setInt(4, provaTrafego.getStatus());
		stmt.setLong(5, provaTrafego.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

	public void excluir(ProvaTrafego provaTrafego) throws SQLException {

		con = new ConnectionFactory().getConnection();

		String sql = "delete from provatrafego where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setLong(1, provaTrafego.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

}
