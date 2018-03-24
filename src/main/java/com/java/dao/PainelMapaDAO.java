package com.java.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.conexao.ConnectionFactory;
import com.java.modelo.Agenda;
import com.java.modelo.Aluno;
import com.java.modelo.CadAgenda;
import com.java.modelo.Fabricante;
import com.java.modelo.Instrutor;

public class PainelMapaDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Connection con;

	public Agenda retornarMapaAlunoID(Long id) throws ClassNotFoundException, SQLException {

		String sql = "SELECT  ag.id as id_agenda, ag.inicio AS inicio,ag.fim AS fim,ag.status AS status, "
				+ "cad.id as id_cadastro,al.id as id_aluno,al.nome AS aluno,itr.id as id_instrutor,itr.nome AS instrutor, "
				+ "fab.id as id_fab,fab.marca,fab.modelo " + "FROM " + "cadagenda AS cad " + "INNER JOIN "
				+ "agenda AS ag ON (cad.id = ag.agenda_id) " + "INNER JOIN " + "aluno AS al ON (cad.aluno_id = al.id) "
				+ "INNER JOIN " + "instrutor AS itr ON (cad.instrutor_id = itr.id) " + "INNER JOIN "
				+ "fabricante as fab ON (itr.fabricante_id = fab.id) " + "WHERE " + "al.id = ? order by ag.inicio asc";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, id);

		ResultSet rs = stmt.executeQuery();

		Agenda agenda = null;

		while (rs.next()) {

			agenda = new Agenda();

			agenda.setId(rs.getLong("id_agenda"));
			agenda.setInicio(rs.getDate("inicio"));
			agenda.setFim(rs.getDate("fim"));
			agenda.setStatus(rs.getBoolean("status"));

			CadAgenda cadAgenda = new CadAgenda();

			cadAgenda.setId(rs.getLong("id_cadastro"));

			Aluno al = new Aluno();
			al.setId(rs.getLong("id_aluno"));
			al.setNome(rs.getString("aluno"));
			cadAgenda.setAluno(al);

			Instrutor itr = new Instrutor();
			itr.setId(rs.getLong("id_instrutor"));
			itr.setNome(rs.getString("instrutor"));

			Fabricante fab = new Fabricante();
			fab.setId(rs.getLong("id_fab"));
			fab.setMarca(rs.getString("marca"));
			fab.setModelo(rs.getString("modelo"));
			itr.setFabricante(fab);

			cadAgenda.setInstrutor(itr);

			agenda.setCadAgenda(cadAgenda);
		}

		stmt.close();
		con.close();

		return agenda;

	}

	public List<Agenda> retornoAlunoMapa(Long id) throws SQLException {

		List<Agenda> lista = new ArrayList<Agenda>();

		String sql = "SELECT  ag.id as id_agenda, ag.inicio AS inicio,ag.fim AS fim,ag.status AS status, "
				+ "cad.id as id_cadastro,al.id as id_aluno,al.nome AS aluno,itr.id as id_instrutor,itr.nome AS instrutor, "
				+ "fab.id as id_fab,fab.marca,fab.modelo " + "FROM " + "cadagenda AS cad " + "INNER JOIN "
				+ "agenda AS ag ON (cad.id = ag.agenda_id) " + "INNER JOIN " + "aluno AS al ON (cad.aluno_id = al.id) "
				+ "INNER JOIN " + "instrutor AS itr ON (cad.instrutor_id = itr.id) " + "INNER JOIN "
				+ "fabricante as fab ON (itr.fabricante_id = fab.id) " + "WHERE " + "al.id = ? order by ag.inicio asc";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);

		stmt.setLong(1, id);

		ResultSet rs = stmt.executeQuery();

		CadAgenda cadAgenda = null;

		while (rs.next()) {

			Agenda agenda = new Agenda();
			agenda.setId(rs.getLong("id_agenda"));
			agenda.setInicio(rs.getDate("inicio"));
			agenda.setFim(rs.getDate("fim"));
			agenda.setStatus(rs.getBoolean("status"));

			cadAgenda = new CadAgenda();

			cadAgenda.setId(rs.getLong("id_cadastro"));

			Aluno al = new Aluno();
			al.setId(rs.getLong("id_aluno"));
			al.setNome(rs.getString("aluno"));
			cadAgenda.setAluno(al);

			Instrutor itr = new Instrutor();
			itr.setId(rs.getLong("id_instrutor"));
			itr.setNome(rs.getString("instrutor"));

			Fabricante fab = new Fabricante();
			fab.setId(rs.getLong("id_fab"));
			fab.setMarca(rs.getString("marca"));
			fab.setModelo(rs.getString("modelo"));
			itr.setFabricante(fab);

			cadAgenda.setInstrutor(itr);

			agenda.setCadAgenda(cadAgenda);

			lista.add(agenda);

		}

		stmt.close();
		con.close();

		return lista;

	}

}