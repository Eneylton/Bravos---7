package com.java.controller.turmaluno;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.java.modelo.Aluno;
import com.java.modelo.Turma;
import com.java.service.AlunoService;
import com.java.service.TurmaService;
import com.java.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroTurmAlunoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private TurmaService turmaService;

	@Inject
	private AlunoService alunoService;

	private List<Aluno> alunos = new ArrayList<>();

	private List<Aluno> pesquisAlunos = new ArrayList<>();

	private List<Aluno> alunosSelecionados = new ArrayList<>();

	private Aluno alunoSelecionado;

	private Turma turma = new Turma();

	@PostConstruct
	public void init() {

		try {

			alunos = alunoService.listarTodos();

			for (Aluno ac : alunos) {
				ac.setSelecionado(false);
			}
			
			pesquisAlunos = alunoService.listarTodos();

			for (Aluno ac : pesquisAlunos) {
				ac.setSelecionado(false);
			}

			String idTurma = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
					.get("turma");

			Turma turmaSelecionado = turmaService.retornarTurmaPorID(Long.parseLong(idTurma));

			for (Aluno ac : turmaSelecionado.getAlunos()) {
				this.adicionarAlunoSelecionado(ac);

				for (int i = 0; i < alunos.size(); i++) {

					if (alunos.get(i).getId() == ac.getId()) {
						alunos.get(i).setSelecionado(true);
						break;
					}
				}

			}

			this.limpar();

		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage("Erro no processamento - Erro: " + ex.getMessage());
		}

	}

	public void salvar() {

		try {

			turmaService.adicionarListaDeAlunos(turma, alunosSelecionados);

			FacesUtil.addSuccessMessage("Aluno Adicionado com sucesso!");

			this.limpar();

		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public void limpar() {
		this.turma = new Turma();
	}

	private void adicionarAlunoSelecionado(Aluno Aluno) {
		this.alunosSelecionados.add(Aluno);
	}

	private void removerAlunoSelecionado(Aluno Aluno) {
		this.alunosSelecionados.remove(Aluno);
	}

	public void onRowSelect(SelectEvent event) {
		System.out.println("Select Event");
		adicionarAlunoSelecionado(((Aluno) event.getObject()));
	}

	public void onRowUnSelect(UnselectEvent event) {
		System.out.println("UnSelect Event");
		removerAlunoSelecionado(((Aluno) event.getObject()));
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public List<Aluno> getAlunosSelecionados() {
		return alunosSelecionados;
	}

	public void setAlunosSelecionados(List<Aluno> alunosSelecionados) {
		this.alunosSelecionados = alunosSelecionados;
	}

	public Aluno getAlunoSelecionado() {
		return alunoSelecionado;
	}

	public void setAlunoSelecionado(Aluno alunoSelecionado) {
		this.alunoSelecionado = alunoSelecionado;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public List<Aluno> getPesquisAlunos() {
		return pesquisAlunos;
	}

	public void setPesquisAlunos(List<Aluno> pesquisAlunos) {
		this.pesquisAlunos = pesquisAlunos;
	}

}
