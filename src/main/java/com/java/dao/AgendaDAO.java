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
import com.java.modelo.Agenda;
import com.java.modelo.Aluno;
import com.java.modelo.CadAgenda;
import com.java.modelo.Instrutor;

public class AgendaDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Connection con;

	public Agenda retornarAgendaPorID(Long id) throws ClassNotFoundException, SQLException {

		String sql = "select id,titulo,inicio,fim,descricao,status from agenda where id = ? ";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, id);

		ResultSet rs = stmt.executeQuery();

		Agenda agenda = null;

		while (rs.next()) {

			agenda = new Agenda();

			agenda.setId(rs.getLong("id"));
			agenda.setTitulo(rs.getString("titulo"));
			agenda.setInicio(rs.getTimestamp("inicio"));
			agenda.setFim(rs.getTimestamp("fim"));
			agenda.setDescricao(rs.getString("descricao"));
			agenda.setStatus(rs.getBoolean("status"));
		}

		stmt.close();
		con.close();

		return agenda;

	}

	public List<Agenda> listarTodos() throws SQLException {

		List<Agenda> lista = new ArrayList<Agenda>();

		String sql = "SELECT "
			    + "ag.id AS id_agenda, "
			    + "ag.titulo, "
			    + "ag.inicio, "
			    + "ag.fim, "
			    + "ag.descricao, "
			    + "ag.status, "
			    + "al.id AS id_aluno, "
			    + "al.nome AS aluno, "
			    + "itr.id AS id_instrutor, "
			    + "itr.nome AS instrutor, cad.id as id_cadastro "
			    + "FROM "
			    + "agenda AS ag "
			        + "INNER JOIN "
			    + "cadagenda AS cad ON (ag.agenda_id = cad.id) "
			        + "INNER JOIN "
			    + "aluno AS al ON (cad.aluno_id = al.id) "
			        + "INNER JOIN "
			    + "instrutor AS itr ON (cad.instrutor_id = itr.id)";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		Agenda agenda = null;

		while (rs.next()) {

			agenda = new Agenda();

			agenda.setId(rs.getLong("id_agenda"));
			agenda.setTitulo(rs.getString("titulo"));
			agenda.setMostrarDataInicio(rs.getString("inicio"));
			agenda.setInicio(rs.getTimestamp("inicio"));
			agenda.setMostrarDataFim(rs.getString("fim"));
			agenda.setFim(rs.getTimestamp("fim"));
			agenda.setDescricao(rs.getString("descricao"));
			agenda.setStatus(rs.getBoolean("status"));
			
			CadAgenda cad = new CadAgenda();
			cad.setId(rs.getLong("id_cadastro"));
			
			Aluno  al = new Aluno();
			al.setId(rs.getLong("id_aluno"));
			al.setNome(rs.getString("aluno"));
			cad.setAluno(al);
			
			Instrutor itr = new Instrutor();
			itr.setId(rs.getLong("id_instrutor"));
			itr.setNome(rs.getString("instrutor"));
			cad.setInstrutor(itr);
			
			agenda.setCadAgenda(cad);

			lista.add(agenda);

		}

		stmt.close();
		con.close();

		return lista;

	}

	public void incluir(Agenda agenda) throws SQLException {

		con = new ConnectionFactory().getConnection();

		SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String dataFormatada = dt1.format(agenda.getInicio());

		String dataFormatada2 = dt1.format(agenda.getFim());

		String sql = "INSERT INTO agenda (titulo,inicio,fim,descricao,status,agenda_id) " + "VALUES (?,?,?,?,?,?)";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setString(1, agenda.getTitulo());
		stmt.setString(2, dataFormatada);
		stmt.setString(3, dataFormatada2);
		stmt.setString(4, agenda.getDescricao());
		stmt.setBoolean(5, agenda.isStatus());
		stmt.setLong(6, agenda.getCadAgenda().getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

	public void alterar(Agenda agenda) throws SQLException {

		con = new ConnectionFactory().getConnection();

		SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String dataFormatada1 = dt1.format(agenda.getInicio());

		String dataFormatada2 = dt1.format(agenda.getFim());

		String sql = "update agenda set " + "titulo = ?, inicio = ?,fim = ?, "
				+ "descricao = ?,status = ?  where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setString(1, agenda.getTitulo());
		stmt.setString(2, dataFormatada1);
		stmt.setString(3, dataFormatada2);
		stmt.setString(4, agenda.getDescricao());
		stmt.setBoolean(5, agenda.isStatus());
		stmt.setLong(6, agenda.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

	public void excluir(Agenda agenda) throws SQLException {

		con = new ConnectionFactory().getConnection();

		String sql = "delete from agenda where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setLong(1, agenda.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

}
