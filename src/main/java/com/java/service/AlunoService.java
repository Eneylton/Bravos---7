package com.java.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import com.java.dao.AlunoDAO;
import com.java.modelo.Aluno;
import com.java.util.NegocioException;

public class AlunoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioService usuarioService;

	private AlunoDAO alunoDAO = new AlunoDAO();

	public Aluno retornarAlunoPorID(Long id) throws ClassNotFoundException, SQLException {
		return alunoDAO.retornarAlunoPorID(id);
	}
	
	public List<Aluno> listarAlunosMapa() throws SQLException {
		return alunoDAO.listarAlunosMapa();
	}

	public List<Aluno> listarTodos() throws SQLException {
		return alunoDAO.listarTodos();
	}
	

	
	public List<Aluno> listarTodosPorAutoEscola(Long id) throws SQLException {
		return alunoDAO.listarTodosPorAutoEscola(id);
	}

	public void incluir(Aluno aluno) throws SQLException, NegocioException {

		if (usuarioService.testaSeUsuarioLogadoEhAdmin() == false) {
			throw new NegocioException("Usuário não tem permissão.");
		}

		alunoDAO.incluir(aluno);
	}

	public void alterar(Aluno aluno) throws SQLException, NegocioException {

		if (usuarioService.testaSeUsuarioLogadoEhAdmin() == false) {
			throw new NegocioException("Usuário não tem permissão.");
		}

		alunoDAO.alterar(aluno);
	}

	public void excluir(Aluno aluno) throws SQLException, NegocioException {

		if (usuarioService.testaSeUsuarioLogadoEhAdmin() == false) {
			throw new NegocioException("Usuário não tem permissão.");
		}

		alunoDAO.excluir(aluno);
	}

}