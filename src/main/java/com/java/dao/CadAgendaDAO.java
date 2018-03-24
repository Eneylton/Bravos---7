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

public class CadAgendaDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Connection con;

	public CadAgenda retornarCadAgendaPorID(Long id) throws ClassNotFoundException, SQLException {

		String sql = "SELECT " + "cad.id as id,itr.id as id_instrutor,al.id as id_aluno, "
				+ "itr.nome as instrutor,al.nome as aluno,fab.id as id_fab,fab.modelo as modelo, "
				+ "fab.marca as marca,fab.foto as foto " + "FROM " + "cadagenda AS cad " + "INNER JOIN "
				+ "instrutor AS itr ON (cad.instrutor_id = itr.id) " + "INNER JOIN "
				+ "aluno AS al ON (al.id = cad.aluno_id) " + "INNER JOIN "
				+ "fabricante as fab ON (fab.id = itr.fabricante_id) "

				+ "WHERE cad.id = ? " + "order by cad.id desc ";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, id);

		ResultSet rs = stmt.executeQuery();

		CadAgenda cadAgenda = null;

		while (rs.next()) {

			cadAgenda = new CadAgenda();

			cadAgenda.setId(rs.getLong("id"));

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
			fab.setFoto(rs.getString("foto"));

			itr.setFabricante(fab);
			cadAgenda.setInstrutor(itr);
		}

		stmt.close();
		con.close();

		return cadAgenda;

	}
	
	
	public List<Agenda> retornoAlunoMapa(Long id) throws SQLException {

		List<Agenda> lista = new ArrayList<Agenda>();

		String sql = "SELECT  ag.id as id_agenda, ag.inicio AS inicio,ag.fim AS fim,ag.status AS status, "
				   + "cad.id as id_cadastro,al.id as id_aluno,al.nome AS aluno,itr.id as id_instrutor,itr.nome AS instrutor, "
				   + "fab.id as id_fab,fab.marca,fab.modelo,CASE WHEN ag.status = '0' THEN 'Sim' "
				   + "WHEN ag.status = '1' THEN 'Não'  END AS idDescricao "
				   + "FROM "
					    + "cadagenda AS cad "
					        + "INNER JOIN "
					    + "agenda AS ag ON (cad.id = ag.agenda_id) "
					        + "INNER JOIN "
					    + "aluno AS al ON (cad.aluno_id = al.id) "
 					        + "INNER JOIN "
					    + "instrutor AS itr ON (cad.instrutor_id = itr.id) "
					        + "INNER JOIN "
					    + "fabricante as fab ON (itr.fabricante_id = fab.id) "
					+ "WHERE "
					    + "cad.id = ? order by ag.inicio asc"; 

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setLong(1, id);

		ResultSet rs = stmt.executeQuery();

		CadAgenda cadAgenda = null;

		while (rs.next()) {

		    Agenda agenda = new Agenda();
		    agenda.setId(rs.getLong("id_agenda"));
			agenda.setInicio(rs.getTimestamp("inicio"));
			agenda.setFim(rs.getTimestamp("fim"));
			agenda.setStatus(rs.getBoolean("status"));
			agenda.setIdStatus(rs.getString("idDescricao"));
		
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

	public List<CadAgenda> listarTodos() throws SQLException {

		List<CadAgenda> lista = new ArrayList<CadAgenda>();

		String sql = "SELECT " + "cad.id,itr.id as id_instrutor,al.id as id_aluno,"
				+ "itr.nome as instrutor,al.nome as aluno " + "FROM " + "cadagenda AS cad " + "INNER JOIN "
				+ "instrutor AS itr ON (cad.instrutor_id = itr.id) " + "INNER JOIN "
				+ "aluno AS al ON (al.id = cad.aluno_id) " + "order by cad.id desc ";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		CadAgenda cadAgenda = null;

		while (rs.next()) {

			cadAgenda = new CadAgenda();

			cadAgenda.setId(rs.getLong("id"));

			Aluno al = new Aluno();
			al.setId(rs.getLong("id_aluno"));
			al.setNome(rs.getString("aluno"));
			cadAgenda.setAluno(al);

			Instrutor itr = new Instrutor();
			itr.setId(rs.getLong("id_instrutor"));
			itr.setNome(rs.getString("instrutor"));
			cadAgenda.setInstrutor(itr);

			lista.add(cadAgenda);

		}

		stmt.close();
		con.close();

		return lista;

	}

	public void incluir(CadAgenda cadAgenda) throws SQLException {

		con = new ConnectionFactory().getConnection();

		/*
		 * SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd"); String
		 * dataFormatada = dt1.format(Categoria.getData());
		 */

		String sql = "INSERT INTO cadagenda (instrutor_id,aluno_id) " + " VALUES (?,?)";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setLong(1, cadAgenda.getInstrutor().getId());
		stmt.setLong(2, cadAgenda.getAluno().getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

	public void alterar(CadAgenda cadAgenda) throws SQLException {

		con = new ConnectionFactory().getConnection();

		/*
		 * SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd"); String
		 * dataFormatada = dt1.format(Categoria.getData());
		 */

		String sql = "update cadagenda set " + "instrutor_id = ?,aluno_id=? where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setLong(1, cadAgenda.getInstrutor().getId());
		stmt.setLong(2, cadAgenda.getAluno().getId());

		stmt.setLong(3, cadAgenda.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

	public void excluir(CadAgenda cadAgenda) throws SQLException {

		con = new ConnectionFactory().getConnection();

		String sql = "delete from cadagenda where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setLong(1, cadAgenda.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

}