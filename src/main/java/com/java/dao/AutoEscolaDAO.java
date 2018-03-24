package com.java.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.conexao.ConnectionFactory;
import com.java.modelo.AutoEscola;
import com.java.modelo.Usuario;

public class AutoEscolaDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Connection con;

	public AutoEscola retornarAutoEscolaPorID(Long id) throws ClassNotFoundException, SQLException {

		String sql = "select al.id, al.nome,al.razao,al.nome,al.cnpj,al.logo "
				+ "from autoescola as al "
				+ "where al.id = ?";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, id);

		ResultSet rs = stmt.executeQuery();

		AutoEscola autoEscola = null;

		while (rs.next()) {

			autoEscola = new AutoEscola();

			autoEscola.setId(rs.getLong("id"));
			autoEscola.setRazao(rs.getString("razao"));
			autoEscola.setNome(rs.getString("nome"));
			autoEscola.setCnpj(rs.getString("cnpj"));
			autoEscola.setLogo(rs.getString("logo"));

		}

		stmt.close();
		con.close();

		return autoEscola;

	}

	public List<AutoEscola> listarTodos() throws SQLException {

		List<AutoEscola> lista = new ArrayList<AutoEscola>();

			String sql = "select al.id, al.nome,al.razao,al.cnpj,al.logo "
					+ "from autoescola as al "
					+ "order by id desc ";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		AutoEscola autoEscola = null;

		while (rs.next()) {

			autoEscola = new AutoEscola();

			autoEscola.setId(rs.getLong("id"));
			autoEscola.setNome(rs.getString("nome"));
			autoEscola.setRazao(rs.getString("razao"));
			autoEscola.setCnpj(rs.getString("cnpj"));
			autoEscola.setLogo(rs.getString("logo"));

			lista.add(autoEscola);

		}

		stmt.close();
		con.close();

		return lista;

	}
	
	
	
	public List<AutoEscola> listarUsuarioPorAutoEscola() throws SQLException {

		List<AutoEscola> lista = new ArrayList<AutoEscola>();

			String sql = "SELECT "
							+ "aut.nome, "
							+ "count(us.id) as total "
							+ "FROM "
							+ "autoescola AS aut "
							+ "INNER JOIN "
							+ "usuario us ON (aut.id = us.autoescola_id) group by aut.nome";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		AutoEscola autoEscola = null;

		while (rs.next()) {

			autoEscola = new AutoEscola();

			
			autoEscola.setNome(rs.getString("nome"));
			autoEscola.setTotal(rs.getInt("total"));
			
			lista.add(autoEscola);

		}

		stmt.close();
		con.close();

		return lista;

	}
	
	
	
	public List<AutoEscola> listarUsuariosPorAutoEscola(Long id) throws SQLException {

		List<AutoEscola> lista = new ArrayList<AutoEscola>();

			String sql = "SELECT "
							+ "aut.id, "
							+ "aut.nome AS nomeAutoEscola, "
							+ "aut.logo, "
							+ "aut.razao, "
							+ "aut.cnpj, "
							+ "us.id AS idUsuario, "
							+ "us.nomeCompleto, "
							+ "us.login "
							+ "FROM "
							+ "autoescola AS aut "
							+ "INNER JOIN "
							+ "usuario us ON (aut.id = us.autoescola_id) "
							+ "WHERE "
							+ "us.autoescola_id = ?";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, id);

		ResultSet rs = stmt.executeQuery();

		AutoEscola autoEscola = null;

		while (rs.next()) {

			autoEscola = new AutoEscola();

			autoEscola.setId(rs.getLong("id"));
			autoEscola.setNome(rs.getString("nomeAutoEscola"));
			autoEscola.setRazao(rs.getString("razao"));
			autoEscola.setCnpj(rs.getString("cnpj"));
			autoEscola.setLogo(rs.getString("logo"));
			
			Usuario usu = new Usuario();
			usu.setId(rs.getLong("idUsuario"));
			usu.setLogin(rs.getString("login"));
			usu.setNomeCompleto(rs.getString("nomeCompleto"));
			
			autoEscola.setUsuario(usu);
			
			lista.add(autoEscola);

		}

		stmt.close();
		con.close();

		return lista;

	}

	

	public void incluir(AutoEscola autoEscola) throws SQLException {

		con = new ConnectionFactory().getConnection();

		/*
		 * SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd"); String
		 * dataFormatada = dt1.format(Categoria.getData());
		 */

		String sql = "INSERT INTO autoEscola (razao,nome,cnpj,logo) " + " VALUES (?,?,?,?)";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setString(1, autoEscola.getRazao());
		stmt.setString(2, autoEscola.getNome());
		stmt.setString(3, autoEscola.getCnpj());
		stmt.setString(4, autoEscola.getLogo());

		stmt.execute();
		stmt.close();
		con.close();

	}

	public void alterar(AutoEscola autoEscola) throws SQLException {

		con = new ConnectionFactory().getConnection();

		/*
		 * SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd"); String
		 * dataFormatada = dt1.format(Categoria.getData());
		 */

		String sql = "update autoEscola set " + "razao=?,nome=?,cnpj=?,logo=? where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setString(1, autoEscola.getRazao());
		stmt.setString(2, autoEscola.getNome());
		stmt.setString(3, autoEscola.getCnpj());
		stmt.setString(4, autoEscola.getLogo());

		stmt.setLong(5, autoEscola.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

	public void excluir(AutoEscola autoEscola) throws SQLException {

		con = new ConnectionFactory().getConnection();

		String sql = "delete from autoEscola where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setLong(1, autoEscola.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

}