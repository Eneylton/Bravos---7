package com.java.controller.turmaluno;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.java.service.RelatorioService;
import com.java.service.TurmaService;
import com.java.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroBuscarAlunoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private TurmaService turmaService;
	
	@Inject
	private RelatorioService relatorioService;

	private List<Aluno> alunos = new ArrayList<>();

	private List<Aluno> alunosSelecionados = new ArrayList<>();

	private Aluno alunoSelecionado;

	private Turma turma;

	@PostConstruct
	public void init() {

		try {

				for (Aluno ac : alunos) {
				ac.setSelecionado(false);
			}

			String idTurma = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
					.get("turma");

			Turma turmaSelecionado = turmaService.retornarTurmaPorID(Long.parseLong(idTurma));
			alunos = turmaService.buscarAlunosTurmas(Long.parseLong(idTurma));

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
	
	
	public void gerarListaPresenca() throws NumberFormatException, ClassNotFoundException, SQLException{
		
	
		try {
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			
		    Long id = turma.getId();
		    
		    String idTurma = String.valueOf( new Long( id) );

			parametros.put(idTurma, idTurma);

			alunos = turmaService.buscarAlunosTurmas(Long.parseLong(idTurma));
			relatorioService.gerarListaPresenca(alunos, parametros, "presenca");
			

		} catch (Exception e) {
			e.printStackTrace();
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

}
