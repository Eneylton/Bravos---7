package com.java.controller.professor;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.java.modelo.Professor;
import com.java.service.ProfessorService;
import com.java.util.FacesMessages;
import com.java.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroProfessorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProfessorService professorService;

	@Inject
	private FacesMessages messages;

	private List<Professor> listarProfessores;

	private List<Professor> pesquisafessores;

	private Professor professor;

	private Professor professorEdicao = new Professor();

	private Professor professorSelecionada;

	@PostConstruct
	public void init() {

		try {

			this.limpar();

			consultar();
			pesquisar();

		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage("Erro no Processamento - Erro: " + ex.getMessage());
		}

	}

	public void prepararNovoCadastro() {
		professorEdicao = new Professor();
	}

	public void salvar() throws SQLException {
		try {

			if (professorEdicao.getId() == null) {

				professorService.incluir(professorEdicao);
				consultar();
				pesquisar();

				messages.info("Professor salvo com sucesso!");

				RequestContext.getCurrentInstance().update(Arrays.asList("frm:msgs", "frm:professorTable"));

			} else {

				professorService.alterar(professorEdicao);
				consultar();
				pesquisar();

				messages.info("Professor Alterado com sucesso!");

				RequestContext.getCurrentInstance().update(Arrays.asList("frm:msgs", "frm:professorTable"));

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage("Erro no processamento - Erro: " + ex.getMessage());
		}
	}

	public void excluir() throws SQLException {
		professorService.excluir(professorSelecionada);
		professorSelecionada = null;

		consultar();
		limpar();

		messages.error("Professor excluído com sucesso!");
		RequestContext.getCurrentInstance().update(Arrays.asList("frm:msgs", "frm:professorTable"));
	}

	public void consultar() throws SQLException {

		listarProfessores = professorService.listarTodos();

	}
	
	
	public void pesquisar() throws SQLException {

		listarProfessores = professorService.listarTodos();

	}

	public void limpar() {
		this.professor = new Professor();
	}

	public FacesMessages getMessages() {
		return messages;
	}

	public void setMessages(FacesMessages messages) {
		this.messages = messages;
	}

	public List<Professor> getListarProfessores() {
		return listarProfessores;
	}

	public void setListarProfessores(List<Professor> listarProfessores) {
		this.listarProfessores = listarProfessores;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Professor getProfessorEdicao() {
		return professorEdicao;
	}

	public void setProfessorEdicao(Professor professorEdicao) {
		this.professorEdicao = professorEdicao;
	}

	public Professor getProfessorSelecionada() {
		return professorSelecionada;
	}

	public void setProfessorSelecionada(Professor professorSelecionada) {
		this.professorSelecionada = professorSelecionada;
	}

	public List<Professor> getPesquisafessores() {
		return pesquisafessores;
	}

	public void setPesquisafessores(List<Professor> pesquisafessores) {
		this.pesquisafessores = pesquisafessores;
	}

}
