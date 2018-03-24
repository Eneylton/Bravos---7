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
import com.java.modelo.Professor;
import com.java.modelo.Turma;

public class TurmaDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Connection con;

	public Turma retornarTurmaPorID(Long id) throws ClassNotFoundException, SQLException {

		String sql = "select t.id,t.descricao,t.turno,t.inicio,t.fim,t.professor_id,prf.id,prf.nome as nome "
				+ "from turma as t inner join professor as prf on (t.professor_id = prf.id) where t.id = ? ";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, id);

		ResultSet rs = stmt.executeQuery();

		Turma turma = null;

		while (rs.next()) {

			turma = new Turma();

			turma.setId(rs.getLong("id"));
			turma.setDescricao(rs.getString("descricao"));
			turma.setTurno(rs.getString("turno"));
			turma.setInicio(rs.getDate("inicio"));
			turma.setFim(rs.getDate("fim"));

			Professor prof = new Professor();
			prof.setId(rs.getLong("id"));
			prof.setNome(rs.getString("nome"));
			turma.setProfessor(prof);

			List<Aluno> alunos = this.listarTurmaAlunosRetorno(turma);

			turma.setAlunos(alunos);

		}

		stmt.close();
		con.close();

		return turma;

	}

	private List<Aluno> listarTurmaAlunosRetorno(Turma turma) throws SQLException {

		String sql = "select a.id,a.nome from aluno as a inner join turmaluno as ta on (a.id = ta.aluno_id) WHERE ta.turma_id = ? ";

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, turma.getId());

		ResultSet rs = stmt.executeQuery();

		List<Aluno> lista = new ArrayList<Aluno>();

		while (rs.next()) {

			Aluno aluno = new Aluno();

			aluno.setId(rs.getLong("id"));
			aluno.setNome(rs.getString("nome"));

			lista.add(aluno);

		}

		rs.close();
		stmt.close();

		return lista;

	}

public List<Turma> listarTodos() throws SQLException {

	List<Turma> lista = new ArrayList<Turma>();

	String sql = "select "
    + "t.id, "
    + "t.descricao as turmaalunos, " 
    + "t.turno, "
    + "t.inicio, "
    + "t.fim, "
    + "t.professor_id, " 
    + "pr.id as idprof, "
    + "pr.nome as nome, "
    + "count(ta.aluno_id) as total " 
    + "from "
    + "turma as t "
    + "inner join "
    + "professor as pr on (t.professor_id = pr.id) " 
    + "left join "
    + "turmaluno as ta on (t.id = ta.turma_id) "
    + "group by turmaalunos "
    + "order by t.id desc";

	con = new ConnectionFactory().getConnection();

	PreparedStatement stmt = con.prepareStatement(sql);

	ResultSet rs = stmt.executeQuery();

	Turma turma = null;

	while (rs.next()) {

		turma = new Turma();

		turma.setId(rs.getLong("id"));
		turma.setDescricao(rs.getString("turmaalunos"));
		turma.setTurno(rs.getString("turno"));
		turma.setInicio(rs.getDate("inicio"));
		turma.setFim(rs.getDate("fim"));
		turma.setTotalAlunos(rs.getInt("total") * 10 );

		Professor prof = new Professor();
		prof.setId(rs.getLong("idprof"));
		prof.setNome(rs.getString("nome"));

		turma.setProfessor(prof);

		lista.add(turma);

	}

	stmt.close();
	con.close();

	return lista;

}

	public List<Aluno> listarAlunosTurmas(Long aluno_id) throws Exception {

		String sql = "select * from turmaluno where aluno_id = ? ";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, aluno_id);

		ResultSet rs = stmt.executeQuery();

		List<Aluno> lista = new ArrayList<Aluno>();

		while (rs.next()) {

			Aluno aluno = new Aluno();

			aluno.setId(rs.getLong("id"));
			aluno.setNome(rs.getString("nome"));

			lista.add(aluno);

		}

		rs.close();
		stmt.close();
		con.close();

		return lista;
	}

	public List<Aluno> listarAlunoPorTurma2(Turma turma) throws Exception {
		return this.listarAlunosTurmas(turma.getId());

	}

	public List<Aluno> buscarAlunosTurmas(Long turma_id) throws Exception {

		String sql = "select  a.id, a.nome,t.descricao as descricao,t.turno as turno,t.inicio,t.fim,prf.id ,prf.nome as prof from aluno as a inner join turmaluno as ta on (a.id = ta.aluno_id) "
				+ "inner join turma as t on (t.id = ta.turma_id) inner join professor as prf on (t.professor_id = prf.id) "
				+ "where ta.turma_id = ?";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, turma_id);

		ResultSet rs = stmt.executeQuery();

		List<Aluno> lista = new ArrayList<Aluno>();

		while (rs.next()) {

			Aluno aluno = new Aluno();

			aluno.setId(rs.getLong("id"));
			aluno.setNome(rs.getString("nome"));

			Turma turma = new Turma();
			turma.setId(rs.getLong("id"));
			turma.setDescricao(rs.getString("descricao"));
			turma.setTurno(rs.getString("turno"));
			turma.setInicio(rs.getDate("inicio"));
			turma.setFim(rs.getDate("fim"));

			Professor prof = new Professor();
			prof.setId(rs.getLong("id"));
			prof.setNome(rs.getString("prof"));

			turma.setProfessor(prof);
			aluno.setTurma(turma);

			lista.add(aluno);

		}

		rs.close();
		stmt.close();
		con.close();

		return lista;
	}

	public void adicionarListaDeAlunos(Turma turma, List<Aluno> listarAlunos) throws SQLException {

		con = new ConnectionFactory().getConnection();

		removerAlunoPorTurma(turma);

		for (Aluno aluno : listarAlunos) {
			incluirAlunoPorTurma(turma, aluno);
		}

		con.close();

	}

	private void incluirAlunoPorTurma(Turma turma, Aluno aluno) throws SQLException {

		String sql = "INSERT INTO turmaluno (aluno_id, turma_id,flag) " + "VALUES (?,?,?)";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setLong(1, aluno.getId());
		stmt.setLong(2, turma.getId());
		stmt.setInt(3, 1);

		stmt.execute();
		stmt.close();

	}

	private void removerAlunoPorTurma(Turma turma) throws SQLException {

		String sql = "DELETE FROM turmaluno WHERE turma_id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setLong(1, turma.getId());

		stmt.execute();
		stmt.close();

	}

	public void incluir(Turma turma) throws SQLException {

		con = new ConnectionFactory().getConnection();

		SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
		String dataInicio = dt1.format(turma.getInicio());
		String dataFim = dt1.format(turma.getFim());

		String sql = "INSERT INTO turma (turno,descricao,inicio,fim,professor_id) " + "VALUES (?,?,?,?,?)";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setString(1, turma.getTurno());
		stmt.setString(2, turma.getDescricao());
		stmt.setString(3, dataInicio);
		stmt.setString(4, dataFim);
		stmt.setLong(5, turma.getProfessor().getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

	public void alterar(Turma turma) throws SQLException {

		con = new ConnectionFactory().getConnection();

		SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
		String dataInicio = dt1.format(turma.getInicio());
		String dataFim = dt1.format(turma.getFim());

		String sql = "update turma set " + " turno =?, descricao =?, inicio =?, fim =?, professor_id = ? where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setString(1, turma.getTurno());
		stmt.setString(2, turma.getDescricao());
		stmt.setString(3, dataInicio);
		stmt.setString(4, dataFim);
		stmt.setLong(5, turma.getProfessor().getId());
		stmt.setLong(6, turma.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

	public void excluir(Turma turma) throws SQLException {

		con = new ConnectionFactory().getConnection();

		String sql = "delete from turma where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setLong(1, turma.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

}
