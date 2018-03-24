package com.java.controller.turma;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.java.modelo.Aluno;
import com.java.modelo.Professor;
import com.java.modelo.Turma;
import com.java.service.ProfessorService;
import com.java.service.TurmaService;
import com.java.util.FacesMessages;
import com.java.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroTurmaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private TurmaService turmaService;

	@Inject
	private ProfessorService professorService;

	@Inject
	private FacesMessages messages;

	private List<Turma> listarTurmas;
	private List<Turma> pesquisaTurmas;
	private List<Aluno> listarAlunosPorTurma = new ArrayList<>();

	private Map<String, Professor> listarProfessores = new HashMap<String, Professor>();

	private Turma turma;

	private Turma turmaEdicao = new Turma();

	private Turma turmaSelecionada;

	@PostConstruct
	public void init() {

		try {

		    consultar();
			preencheListasSelect();
			limpar();

		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage("Erro no Processamento - Erro: " + ex.getMessage());
		}

	}

	public void prepararNovoCadastro() {
		turmaEdicao = new Turma();
	}

	public void salvar() throws SQLException {
		try {

			if (turmaEdicao.getId() == null) {

				turmaService.incluir(turmaEdicao);
				consultar();

				messages.info("Turma salvo com sucesso!");

				RequestContext.getCurrentInstance().update(Arrays.asList("frm:msgs", "frm:turmaTable"));

			} else {

				turmaService.alterar(turmaEdicao);
				consultar();

				messages.info("Turma Alterado com sucesso!");

				RequestContext.getCurrentInstance().update(Arrays.asList("frm:msgs", "frm:turmaTable"));

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage("Erro no processamento - Erro: " + ex.getMessage());
		}
	}

	public void excluir() throws SQLException {
		turmaService.excluir(turmaSelecionada);
		turmaSelecionada = null;

		consultar();


		messages.info("Turma excluído com sucesso!");
		RequestContext.getCurrentInstance().update(Arrays.asList("frm:msgs", "frm:turmaTable"));
	}

	public void consultar() throws SQLException {

		listarTurmas = turmaService.listarTodos();
		pesquisaTurmas = turmaService.listarTodos();

	}

	
	public void buscarListaAluno() throws Exception {

		Long id = turmaSelecionada.getId();

		turmaSelecionada = (Turma) turmaService.buscarAlunosTurmas(id);

	}

	private void preencheListasSelect() throws SQLException {

		listarProfessores = new TreeMap<String, Professor>();
		for (Professor professor : professorService.listarTodos()) {
			listarProfessores.put(professor.getNome(), professor);
		}

	}

	public void limpar() {
		this.turma = new Turma();
	}

	public FacesMessages getMessages() {
		return messages;
	}

	public void setMessages(FacesMessages messages) {
		this.messages = messages;
	}

	public List<Turma> getListarTurmas() {
		return listarTurmas;
	}

	public void setListarTurmas(List<Turma> listarTurmas) {
		this.listarTurmas = listarTurmas;
	}

	public List<Turma> getPesquisaTurmas() {
		return pesquisaTurmas;
	}

	public void setPesquisaTurmas(List<Turma> pesquisaTurmas) {
		this.pesquisaTurmas = pesquisaTurmas;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Turma getTurmaEdicao() {
		return turmaEdicao;
	}

	public void setTurmaEdicao(Turma turmaEdicao) {
		this.turmaEdicao = turmaEdicao;
	}

	public Turma getTurmaSelecionada() {
		return turmaSelecionada;
	}

	public void setTurmaSelecionada(Turma turmaSelecionada) {
		this.turmaSelecionada = turmaSelecionada;
	}

	public List<Aluno> getListarAlunosPorTurma() {
		return listarAlunosPorTurma;
	}

	public void setListarAlunosPorTurma(List<Aluno> listarAlunosPorTurma) {
		this.listarAlunosPorTurma = listarAlunosPorTurma;
	}

	public Map<String, Professor> getListarProfessores() {
		return listarProfessores;
	}

	public void setListarProfessores(Map<String, Professor> listarProfessores) {
		this.listarProfessores = listarProfessores;
	}

}
