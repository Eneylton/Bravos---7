package com.java.controller.simulador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.java.modelo.Aluno;
import com.java.service.AlunoService;
import com.java.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaSimuladorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AlunoService alunoService;

	private List<Aluno> listarAlunos = new ArrayList<>();

	private List<Aluno> pesquisaAlunos = new ArrayList<>();

	private Aluno aluno = new Aluno();

	private Aluno alunoSelecionado;

	@PostConstruct
	public void inicializar() {

		try {
			listarAlunos = alunoService.listarTodos();

			pesquisaAlunos = alunoService.listarTodos();

		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage(ex.getMessage());
		}

	}

	public List<Aluno> getListarAlunos() {
		return listarAlunos;
	}

	public void setListarAlunos(List<Aluno> listarAlunos) {
		this.listarAlunos = listarAlunos;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<Aluno> getPesquisaAlunos() {
		return pesquisaAlunos;
	}

	public void setPesquisaAlunos(List<Aluno> pesquisaAlunos) {
		this.pesquisaAlunos = pesquisaAlunos;
	}

	public Aluno getAlunoSelecionado() {
		return alunoSelecionado;
	}

	public void setAlunoSelecionado(Aluno alunoSelecionado) {
		this.alunoSelecionado = alunoSelecionado;
	}

}
