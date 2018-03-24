package com.java.controller.aluno;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.java.modelo.Aluno;
import com.java.service.AlunoService;
import com.java.util.Session;
import com.java.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaAlunoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AlunoService alunoService;

	private List<Aluno> listaAlunos = new ArrayList<>();

	private List<Aluno> pesquisaAlunos = new ArrayList<>();

	private Aluno aluno;

	private Aluno alunoSelecionado;

	@PostConstruct
	public void inicializar() {

		try {
			Long idUsuario = Session.retornaIdAutoEscola();
			listaAlunos = alunoService.listarTodosPorAutoEscola(idUsuario);
			pesquisaAlunos =  alunoService.listarTodosPorAutoEscola(idUsuario);
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage(ex.getMessage());
		}

	}

	public void excluir() {

		try {
			alunoService.excluir(alunoSelecionado);
			this.listaAlunos.remove(alunoSelecionado);
			FacesUtil.addSuccessMessage("Categoria " + alunoSelecionado.getNome() + " excluída com sucesso.");
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage("O Aluno está vinculado a um registro financeiro, e não pode ser excluido !");
		}

	}

	public List<Aluno> getListaAlunos() {
		return listaAlunos;
	}

	public void setListaAlunos(List<Aluno> listaAlunos) {
		this.listaAlunos = listaAlunos;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Aluno getAlunoSelecionado() {
		return alunoSelecionado;
	}

	public void setAlunoSelecionado(Aluno alunoSelecionado) {
		this.alunoSelecionado = alunoSelecionado;
	}

	public List<Aluno> getPesquisaAlunos() {
		return pesquisaAlunos;
	}

	public void setPesquisaAlunos(List<Aluno> pesquisaAlunos) {
		this.pesquisaAlunos = pesquisaAlunos;
	}

}