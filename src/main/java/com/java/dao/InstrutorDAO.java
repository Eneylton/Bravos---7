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
import com.java.modelo.Sexo;
import com.java.modelo.TipoVei;

public class InstrutorDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Connection con;

	public Instrutor retornarInstrutorPorID(Long id) throws ClassNotFoundException, SQLException {

		String sql = "SELECT "
						+ "i.id, "
						+ "i.nome, "
						+ "i.sexo_id AS id_sexo, "
						+ "i.fabricante_id AS id_fab, "
						+ "fab.id AS idFab, "
						+ "fab.modelo AS modelo, "
						+ "fab.marca AS marca, "
						+ "fab.foto AS foto, "
						+ "tv.id as tipvei, "
					    + "tv.descricao as veiculo "
						+ "FROM "
						+ "instrutor AS i "
						+ "INNER JOIN "
						+ "fabricante AS fab ON (i.fabricante_id = fab.id) "
						+ "Inner join "
						+ "tipovei as tv ON (fab.tipovei_id = tv.id) "
						+ "WHERE "
						+ "i.id = ?";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, id);

		ResultSet rs = stmt.executeQuery();

		Instrutor instrutor = null;

		while (rs.next()) {

			instrutor = new Instrutor();

			instrutor.setId(rs.getLong("id"));
			instrutor.setNome(rs.getString("nome"));
			
			Sexo sexo = new Sexo();
			sexo.setId(rs.getLong("id_sexo"));
			
			instrutor.setSexo(sexo);
			
			
			Fabricante fab = new Fabricante();
			fab.setId(rs.getLong("id_fab"));
			fab.setModelo(rs.getString("modelo"));
			fab.setMarca(rs.getString("marca"));
			fab.setFoto(rs.getString("foto"));
			
			TipoVei vei = new TipoVei();
			vei.setId(rs.getLong("tipvei"));
			vei.setDescricao(rs.getString("veiculo"));
			fab.setTipoVei(vei);
			instrutor.setFabricante(fab);
			
	
		}

		stmt.close();
		con.close();

		return instrutor;

	}

	public List<Instrutor> listarTodos() throws SQLException {

		List<Instrutor> lista = new ArrayList<Instrutor>();

		String sql = "SELECT "
				   + "i.id, i.nome, i.sexo_id AS id_sexo, i.fabricante_id AS id_fab,  "
				    + "fab.id as idFab,fab.modelo as modelo,fab.marca as marca,fab.foto as foto "
				    + "FROM instrutor as i inner join fabricante as fab ON (i.fabricante_id = fab.id) order by id DESC";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		Instrutor instrutor = null;

		while (rs.next()) {
			
			instrutor = new Instrutor();

			instrutor.setId(rs.getLong("id"));
			instrutor.setNome(rs.getString("nome"));
			
			Sexo sexo = new Sexo();
			sexo.setId(rs.getLong("id_sexo"));
			
			instrutor.setSexo(sexo);
			
			
			Fabricante fab = new Fabricante();
			fab.setId(rs.getLong("id_fab"));
			fab.setModelo(rs.getString("modelo"));
			fab.setMarca(rs.getString("marca"));
			fab.setFoto(rs.getString("foto"));
			
			instrutor.setFabricante(fab);
		
			
			lista.add(instrutor);

		}

		stmt.close();
		con.close();

		return lista;

	}
	
	
	public List<Agenda> mapaInstrutor(Long id) throws SQLException {

		List<Agenda> lista = new ArrayList<Agenda>();

						String sql = "SELECT itr.id as id_instrutor, itr.nome as instrutor,cad.id as id_cadastro, "
								   + "ag.id as id_agenda,ag.inicio as inicio,ag.fim as fim,al.id as id_aluno,"
								   + "al.nome as aluno,CASE  WHEN ag.status = '0' THEN 'NÃO' "
			                       + "WHEN ag.status = '1' THEN 'SIM' END AS idStatus,fab.marca,fab.modelo,fab.foto "
				                   + "FROM "
				                   + "agenda AS ag "
				                   + "INNER JOIN "
				                   + "cadagenda AS cad ON (ag.agenda_id = cad.id) "
				                   + "INNER JOIN "
				                   + "instrutor AS itr ON (cad.instrutor_id = itr.id) "
				                   + "INNER JOIN "
				                   + "aluno AS al ON (cad.aluno_id = al.id) "
				                   + "INNER JOIN "
				                   + "fabricante AS fab ON (itr.fabricante_id = fab.id) "
				                   + "WHERE "
				                   + "itr.id = ? "
				                   + "ORDER BY ag.inicio ASC";

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
			agenda.setIdStatus(rs.getString("idStatus"));
		
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
		    fab.setMarca(rs.getString("marca"));
		    fab.setModelo(rs.getString("modelo"));
		    fab.setFoto(rs.getString("foto"));
		    
		    itr.setFabricante(fab);
		    
		    cadAgenda.setInstrutor(itr);
		    
		    agenda.setCadAgenda(cadAgenda);
		
			lista.add(agenda);

		}

		stmt.close();
		con.close();

		return lista;

	}


	public void incluir(Instrutor instrutor) throws SQLException {

		con = new ConnectionFactory().getConnection();

		/*
		 * SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd"); String
		 * dataFormatada = dt1.format(Categoria.getData());
		 */

		String sql = "INSERT INTO instrutor (nome,sexo_id,fabricante_id) " + "VALUES (?,?,?)";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setString(1, instrutor.getNome());
		
		stmt.setLong(2, instrutor.getSexo().getId());
		
		stmt.setLong(3, instrutor.getFabricante().getId());
	

		stmt.execute();
		stmt.close();
		con.close();

	}

	public void alterar(Instrutor instrutor) throws SQLException {

		con = new ConnectionFactory().getConnection();

		/*
		 * SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd"); String
		 * dataFormatada = dt1.format(Categoria.getData());
		 */

		String sql = "update instrutor set " + "nome=?,sexo_id=?,fabricante_id =? where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);
		stmt = con.prepareStatement(sql);

		stmt.setString(1, instrutor.getNome());
		
		stmt.setLong(2, instrutor.getSexo().getId());
		
		stmt.setLong(3, instrutor.getFabricante().getId());
		stmt.setLong(4, instrutor.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

	public void excluir(Instrutor instrutor) throws SQLException {

		con = new ConnectionFactory().getConnection();

		String sql = "delete from instrutor where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setLong(1, instrutor.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

}