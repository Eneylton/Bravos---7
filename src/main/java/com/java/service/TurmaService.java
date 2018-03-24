package com.java.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import com.java.dao.TurmaDAO;
import com.java.modelo.Aluno;
import com.java.modelo.Turma;
import com.java.util.NegocioException;

public class TurmaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private TurmaDAO turmaDAO;

	public Turma retornarTurmaPorID(Long id) throws ClassNotFoundException, SQLException {
		return turmaDAO.retornarTurmaPorID(id);
	}

	public List<Turma> listarTodos() throws SQLException {
		return turmaDAO.listarTodos();
	}

	@SuppressWarnings("unused")
	private List<Aluno> listarAlunoPorTurma2(Turma turma) throws Exception {
		if (turma == null) {
			throw new NegocioException("O Aluno deve ser informada.");
		}
		return turmaDAO.listarAlunoPorTurma2(turma);
	}

	public void incluir(Turma turma) throws SQLException {
		turmaDAO.incluir(turma);
	}

	public void alterar(Turma turma) throws SQLException {
		turmaDAO.alterar(turma);
	}

	public void excluir(Turma turma) throws SQLException {
		turmaDAO.excluir(turma);
	}

	public void adicionarListaDeAlunos(Turma turma, List<Aluno> listarAlunos) throws SQLException, NegocioException {

		if (turma.getId() == null || turma.getId() == 0) {
			throw new NegocioException("Aluno inválido.");
		}

		if (listarAlunos == null || listarAlunos.size() == 0) {
			throw new NegocioException("Selecione pelo menos um Aluno para Turma.");
		}

		if (listarAlunos.size() > 10) {
			throw new NegocioException("Número Máximo de 10 Alunos por Turma.");
		}

		turmaDAO.adicionarListaDeAlunos(turma, listarAlunos);
	}

	public List<Aluno> buscarAlunosTurmas(Long turma_id) throws Exception {
		return turmaDAO.buscarAlunosTurmas(turma_id);
	}

}
