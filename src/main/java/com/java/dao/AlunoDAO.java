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
import com.java.modelo.AutoEscola;
import com.java.modelo.Servico;
import com.java.modelo.Sexo;

public class AlunoDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Connection con;

	public Aluno retornarAlunoPorID(Long id) throws ClassNotFoundException, SQLException {

		String sql = "SELECT "
							+ "al.id, "
							+ "al.nome, "
							+ "al.usuario_id, "
							+ "al.sexo_id AS sexo, "
							+ "al.servico_id AS servico, "
							+ "ser.descricao AS descriservico, "
							+ "ser.valor AS valor, "
							+ "al.cep, "
							+ "al.endereco, "
							+ "al.bairro, "
							+ "al.cidade, "
							+ "al.estado, "
							+ "al.numero, "
							+ "al.latitude, "
							+ "al.longitude, "
							+ "al.endereco_completo, "
							+ "al.sobrenome, "
							+ "al.pai, "
							+ "al.mae, "
							+ "al.data_nascimento, "
							+ "al.renach, "
							+ "al.rg, "
							+ "al.cpf, "
							+ "al.cnh, "
							+ "al.email, "
							+ "al.fixo, "
							+ "al.whatsapp, "
							+ "al.foto "
							+ "FROM "
							+ "aluno AS al "
							+ "INNER JOIN "
							+ "servico AS ser ON (al.servico_id = ser.id) "
							+ "WHERE "
							+ "al.id = ?";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, id);

		ResultSet rs = stmt.executeQuery();

		Aluno aluno = null;

		while (rs.next()) {

			aluno = new Aluno();

			aluno.setId(rs.getLong("id"));
			aluno.setNome(rs.getString("nome"));
			aluno.setSobreNome(rs.getString("sobrenome"));
			aluno.setPai(rs.getString("pai"));
			aluno.setMae(rs.getString("mae"));
			aluno.setDataNascimento(rs.getDate("data_nascimento"));
			aluno.setCep(rs.getString("cep"));
			aluno.setEndereco(rs.getString("endereco"));
			aluno.setBairro(rs.getString("bairro"));
			aluno.setCidade(rs.getString("cidade"));
			aluno.setEstado(rs.getString("estado"));
			aluno.setNumero(rs.getString("numero"));
			aluno.setEnderecoCompleto(rs.getString("endereco_completo"));
			aluno.setLongitude(rs.getString("longitude"));
			aluno.setLatitude(rs.getString("latitude"));
			aluno.setRenach(rs.getString("renach"));
			aluno.setRg(rs.getString("rg"));
			aluno.setCpf(rs.getString("cpf"));
			aluno.setCnh(rs.getString("cnh"));
			aluno.setEmail(rs.getString("email"));
			aluno.setFixo(rs.getString("fixo"));
			aluno.setWhatsapp(rs.getString("whatsapp"));
			
			aluno.setFoto(rs.getString("foto"));
			
			Sexo sexo = new Sexo();
			sexo.setId(rs.getLong("sexo"));
			aluno.setSexo(sexo);

			Servico serv = new Servico();
			serv.setId(rs.getLong("servico"));
			serv.setDescricao(rs.getString("descriservico"));
			serv.setValor(rs.getDouble("valor"));
			aluno.setServico(serv);
		}

		stmt.close();
		con.close();

		return aluno;

	}

	public List<Aluno> listarTodos() throws SQLException {

		List<Aluno> lista = new ArrayList<Aluno>();

		String sql = "SELECT "
				+ "al.id, "
				+ "al.nome, "
				+ "al.usuario_id, "
				+ "al.sexo_id AS sexo, "
				+ "al.servico_id AS servico, "
				+ "ser.descricao AS descriservico, "
				+ "ser.valor AS valor, "
				+ "al.cep, "
				+ "al.endereco, "
				+ "al.bairro, "
				+ "al.cidade, "
				+ "al.estado, "
				+ "al.numero, "
				+ "al.latitude, "
				+ "al.longitude, "
				+ "al.endereco_completo, "
				+ "al.sobrenome, "
				+ "al.pai, "
				+ "al.mae, "
				+ "al.data_nascimento, "
				+ "al.renach, "
				+ "al.rg, "
				+ "al.cpf, "
				+ "al.cnh, "
				+ "al.email, "
				+ "al.fixo, "
				+ "al.whatsapp, "
				+ "al.foto "
				+ "FROM "
				+ "aluno AS al "
				+ "INNER JOIN "
				+ "servico AS ser ON (al.servico_id = ser.id) order by al.id desc";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		Aluno aluno = null;

		while (rs.next()) {

			aluno = new Aluno();

			aluno.setId(rs.getLong("id"));
			aluno.setNome(rs.getString("nome"));
			aluno.setSobreNome(rs.getString("sobrenome"));
			aluno.setPai(rs.getString("pai"));
			aluno.setMae(rs.getString("mae"));
			aluno.setDataNascimento(rs.getDate("data_nascimento"));
			aluno.setEndereco(rs.getString("endereco"));
			aluno.setBairro(rs.getString("bairro"));
			aluno.setCidade(rs.getString("cidade"));
			aluno.setEstado(rs.getString("estado"));
			aluno.setNumero(rs.getString("numero"));
			aluno.setEnderecoCompleto(rs.getString("endereco_completo"));
			aluno.setLongitude(rs.getString("longitude"));
			aluno.setLatitude(rs.getString("latitude"));
			aluno.setRenach(rs.getString("renach"));
			aluno.setRg(rs.getString("rg"));
			aluno.setCpf(rs.getString("cpf"));
			aluno.setCnh(rs.getString("cnh"));
			aluno.setEmail(rs.getString("email"));
			aluno.setFixo(rs.getString("fixo"));
			aluno.setWhatsapp(rs.getString("whatsapp"));
			
			Sexo sexo = new Sexo();
			sexo.setId(rs.getLong("sexo"));
			aluno.setSexo(sexo);

			Servico serv = new Servico();
			serv.setId(rs.getLong("servico"));
			serv.setDescricao(rs.getString("descriservico"));
			serv.setValor(rs.getDouble("valor"));
			aluno.setServico(serv);
			
			
			
			lista.add(aluno);

		}

		stmt.close();
		con.close();

		return lista;

	}
	
	
	public List<Aluno> listarTodosPorAutoEscola(Long id) throws SQLException {

		List<Aluno> lista = new ArrayList<Aluno>();

		String sql = "SELECT "
								    + "al.id, "
								    + "al.nome, "
								    + "al.usuario_id, "
								    + "al.sexo_id AS sexo, "
								    + "al.servico_id AS servico, "
								    + "ser.descricao AS descriservico, "
								    + "ser.valor AS valor, "
								    + "al.cep, "
								    + "al.endereco, "
								    + "al.bairro, "
								    + "al.cidade, "
								    + "al.estado, "
								    + "al.numero, "
								    + "al.latitude, "
								    + "al.longitude, "
								    + "al.endereco_completo, "
								    + "al.sobrenome, "
								    + "al.pai, "
								    + "al.mae, "
								    + "al.data_nascimento, "
								    + "al.renach, "
								    + "al.rg, "
								    + "al.cpf, "
								    + "al.cnh, "
								    + "al.email, "
								    + "al.fixo, "
								    + "al.whatsapp, "
								    + "al.foto, "
								    + "us.nomeCompleto as nomeCompleto, "
								    + "aut.id as idAutEscola, "
								    + "aut.nome as nomeAutoEscola, "
								    + "aut.cnpj as cnpj, "
								    + "aut.logo as logo "
								    + "FROM "
								    + "aluno AS al "
 								    + "INNER JOIN "
								    + "servico AS ser ON (al.servico_id = ser.id) "
								    + "INNER JOIN "
								    + "usuario AS us ON (us.id = al.usuario_id) "
								    + "INNER JOIN  autoescola as aut ON (aut.id = us.autoescola_id) "
								    + "WHERE aut.id = ? "
								    + "ORDER BY al.id DESC";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, id);

		ResultSet rs = stmt.executeQuery();

		Aluno aluno = null;

		while (rs.next()) {

			aluno = new Aluno();

			aluno.setId(rs.getLong("id"));
			aluno.setNome(rs.getString("nome"));
			aluno.setSobreNome(rs.getString("sobrenome"));
			aluno.setPai(rs.getString("pai"));
			aluno.setMae(rs.getString("mae"));
			aluno.setDataNascimento(rs.getDate("data_nascimento"));
			aluno.setEndereco(rs.getString("endereco"));
			aluno.setBairro(rs.getString("bairro"));
			aluno.setCidade(rs.getString("cidade"));
			aluno.setEstado(rs.getString("estado"));
			aluno.setNumero(rs.getString("numero"));
			aluno.setEnderecoCompleto(rs.getString("endereco_completo"));
			aluno.setLongitude(rs.getString("longitude"));
			aluno.setLatitude(rs.getString("latitude"));
			aluno.setRenach(rs.getString("renach"));
			aluno.setRg(rs.getString("rg"));
			aluno.setCpf(rs.getString("cpf"));
			aluno.setCnh(rs.getString("cnh"));
			aluno.setEmail(rs.getString("email"));
			aluno.setFixo(rs.getString("fixo"));
			aluno.setWhatsapp(rs.getString("whatsapp"));
			
			AutoEscola aut = new AutoEscola();
			
			aut.setId(rs.getLong("idAutEscola"));
			aut.setNome(rs.getString("nomeAutoEscola"));
			aut.setCnpj(rs.getString("cnpj"));
			aut.setLogo(rs.getString("logo"));
			
			Sexo sexo = new Sexo();
			sexo.setId(rs.getLong("sexo"));
			aluno.setSexo(sexo);

			Servico serv = new Servico();
			serv.setId(rs.getLong("servico"));
			serv.setDescricao(rs.getString("descriservico"));
			serv.setValor(rs.getDouble("valor"));
			aluno.setServico(serv);
			
			
			lista.add(aluno);

		}

		stmt.close();
		con.close();

		return lista;

	}
	


	
	
	public List<Aluno> listarAlunosMapa() throws SQLException {

		List<Aluno> lista = new ArrayList<Aluno>();

		String sql = "select id,nome,latitude,longitude,endereco_completo,foto from aluno order by id desc ";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		Aluno aluno = null;

		while (rs.next()) {

			aluno = new Aluno();

			aluno.setId(rs.getLong("id"));
			aluno.setNome(rs.getString("nome"));
			aluno.setLatitude(rs.getString("latitude"));
			aluno.setLongitude(rs.getString("longitude"));
			aluno.setEnderecoCompleto(rs.getString("endereco_completo"));
			aluno.setFoto(rs.getString("foto"));

			lista.add(aluno);

		}

		stmt.close();
		con.close();

		return lista;
	}


	public void incluir(Aluno aluno) throws SQLException {

		con = new ConnectionFactory().getConnection();

		
		SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd"); String
		dataFormatada = dt1.format(aluno.getDataNascimento());
		

		String sql = "INSERT INTO aluno (nome,"
				                        + "usuario_id,"
				                        + "sexo_id,"
				                        + "servico_id,"
				                        + "cep,"
				                        + "endereco,"
				                        + "bairro,"
				                        + "cidade,"
				                        + "estado,"
				                        + "numero,"
				                        + "longitude,"
				                        + "latitude,"
				                        + "endereco_completo,"
				                        + "foto,"
				                        + "sobrenome,"
				                        + "pai,"
				                        + "mae,"
				                        + "data_nascimento,"
				                        + "renach,"
				                        + "rg,"
				                        + "cpf,"
				                        + "cnh,"
				                        + "email,"
				                        + "fixo,"
				                        + "whatsapp) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setString(1, aluno.getNome());
		stmt.setLong(2, aluno.getUsuario().getId());
		stmt.setLong(3, aluno.getSexo().getId());
		stmt.setLong(4, aluno.getServico().getId());
		stmt.setString(5, aluno.getCep());
		stmt.setString(6, aluno.getEndereco());	
		stmt.setString(7, aluno.getBairro());
		stmt.setString(8, aluno.getCidade());
		stmt.setString(9, aluno.getEstado());
		stmt.setString(10, aluno.getNumero());	
		stmt.setString(11, aluno.getLongitude());	
		stmt.setString(12, aluno.getLatitude());	
		stmt.setString(13, aluno.getEnderecoCompleto());
		stmt.setString(14, aluno.getFoto());
		stmt.setString(15, aluno.getSobreNome());
		stmt.setString(16, aluno.getPai());
		stmt.setString(17, aluno.getMae());
		stmt.setString(18, dataFormatada);
		stmt.setString(19, aluno.getRenach());
		stmt.setString(20, aluno.getRg());
		stmt.setString(21, aluno.getCpf());
		stmt.setString(22, aluno.getCnh());
		stmt.setString(23, aluno.getEmail());
		stmt.setString(24, aluno.getFixo());
		stmt.setString(25, aluno.getWhatsapp());	
		stmt.execute();
		stmt.close();
		con.close();

	}

	public void alterar(Aluno aluno) throws SQLException {

		con = new ConnectionFactory().getConnection();

		SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd"); String
		dataFormatada = dt1.format(aluno.getDataNascimento());

						String sql = "update aluno set " 
						        + "nome=?,"
								+ "usuario_id=?,"
								+ "sexo_id=?,"
								+ "servico_id=?,"
								+ "cep=?,"
								+ "endereco=?,"
								+ "bairro=?,"
								+ "cidade=?,"
								+ "estado=?,"
								+ "numero=?,"
								+ "longitude=?,"
								+ "latitude=?,"
								+ "endereco_completo=?,"
								+ "foto=?,"
								+ "sobrenome=?,"
								+ "pai=?,"
								+ "mae=?,"
								+ "data_nascimento=?,"
								+ "renach=?,"
								+ "rg=?,"
								+ "cpf=?,"
								+ "cnh=?,"
								+ "email=?,"
								+ "fixo=?,"
								+ "whatsapp=? "
								+ "where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setString(1, aluno.getNome());
		stmt.setLong(2, aluno.getUsuario().getId());
		stmt.setLong(3, aluno.getSexo().getId());
		stmt.setLong(4, aluno.getServico().getId());
		stmt.setString(5, aluno.getCep());
		stmt.setString(6, aluno.getEndereco());	
		stmt.setString(7, aluno.getBairro());
		stmt.setString(8, aluno.getCidade());
		stmt.setString(9, aluno.getEstado());
		stmt.setString(10, aluno.getNumero());	
		stmt.setString(11, aluno.getLongitude());	
		stmt.setString(12, aluno.getLatitude());	
		stmt.setString(13, aluno.getEnderecoCompleto());
		stmt.setString(14, aluno.getFoto());
		stmt.setString(15, aluno.getSobreNome());
		stmt.setString(16, aluno.getPai());
		stmt.setString(17, aluno.getMae());
		stmt.setString(18, dataFormatada);
		stmt.setString(19, aluno.getRenach());
		stmt.setString(20, aluno.getRg());
		stmt.setString(21, aluno.getCpf());
		stmt.setString(22, aluno.getCnh());
		stmt.setString(23, aluno.getEmail());
		stmt.setString(24, aluno.getFixo());
		stmt.setString(25, aluno.getWhatsapp());	
		stmt.setLong(26, aluno.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

	public void excluir(Aluno aluno) throws SQLException {

		con = new ConnectionFactory().getConnection();

		String sql = "delete from aluno where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setLong(1, aluno.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

}