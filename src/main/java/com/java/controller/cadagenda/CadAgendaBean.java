package com.java.controller.cadagenda;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.java.modelo.Agenda;
import com.java.modelo.Aluno;
import com.java.modelo.CadAgenda;
import com.java.modelo.Instrutor;
import com.java.service.AlunoService;
import com.java.service.CadAgendaService;
import com.java.service.InstrutorService;
import com.java.util.FacesMessages;
import com.java.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadAgendaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadAgendaService cadAgendaService;

	@Inject
	private InstrutorService instrutorService;

	@Inject
	private AlunoService alunoService = new AlunoService();

	@Inject
	private FacesMessages messages;

	private List<CadAgenda> listarCadAgendas;

	private List<CadAgenda> pesquisaCadAgendas;
	
	private List<Agenda> listarHorarios;

	private CadAgenda cadAgenda;

	private CadAgenda cadAgendaEdicao = new CadAgenda();

	private CadAgenda cadAgendaSelecionada;

	private Map<String, Instrutor> listarInstrutor = new HashMap<String, Instrutor>();

	private Map<String, Aluno> listarAlunos = new HashMap<String, Aluno>();

	@PostConstruct
	public void init() {

		try {
			
            preencheListasSelect();

			consultar();
			pesquisar();
			
			this.limpar();

		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage("Erro no Processamento - Erro: " + ex.getMessage());
		}

	}

	public void prepararNovoCadastro() {
		cadAgendaEdicao = new CadAgenda();
	}

	private void preencheListasSelect() throws SQLException {

		listarAlunos = new HashMap<String, Aluno>();
		for (Aluno aluno : alunoService.listarTodos()) {
			listarAlunos.put(aluno.getNome(), aluno);
		}

		listarInstrutor = new HashMap<String, Instrutor>();
		for (Instrutor instrutor : instrutorService.listarTodos()) {
			listarInstrutor.put(instrutor.getNome(), instrutor);
		}

	}

	public void salvar() throws SQLException {
		try {

			if (cadAgendaEdicao.getId() == null) {

				cadAgendaService.incluir(cadAgendaEdicao);
				consultar();
				pesquisar();

				messages.info("Cadastro salvo com sucesso!");

				RequestContext.getCurrentInstance().update(Arrays.asList("frm:msgs", "frm:cadAgendaTable"));

			} else {

				cadAgendaService.alterar(cadAgendaEdicao);
				consultar();
				pesquisar();

				messages.info("Cadastro Alterado com sucesso!");

				RequestContext.getCurrentInstance().update(Arrays.asList("frm:msgs", "frm:cadAgendaTable"));

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage("Erro no processamento - Erro: " + ex.getMessage());
		}
	}

	public void excluir() throws SQLException {
		cadAgendaService.excluir(cadAgendaSelecionada);
		cadAgendaSelecionada = null;

		consultar();
		limpar();

		messages.info("Agendamento excluído com sucesso!");
		RequestContext.getCurrentInstance().update(Arrays.asList("frm:msgs", "frm:cadAgendaTable"));
	}

	public void consultar() throws SQLException {

		listarCadAgendas = cadAgendaService.listarTodos();

	}

	public void pesquisar() throws SQLException {

		pesquisaCadAgendas = cadAgendaService.listarTodos();

	}

	public void limpar() {
		this.cadAgenda = new CadAgenda();
	}

	public FacesMessages getMessages() {
		return messages;
	}

	public void setMessages(FacesMessages messages) {
		this.messages = messages;
	}

	public List<CadAgenda> getListarCadAgendas() {
		return listarCadAgendas;
	}

	public void setListarCadAgendas(List<CadAgenda> listarCadAgendas) {
		this.listarCadAgendas = listarCadAgendas;
	}

	public List<CadAgenda> getPesquisaCadAgendas() {
		return pesquisaCadAgendas;
	}

	public void setPesquisaCadAgendas(List<CadAgenda> pesquisaCadAgendas) {
		this.pesquisaCadAgendas = pesquisaCadAgendas;
	}

	public CadAgenda getCadAgenda() {
		return cadAgenda;
	}

	public void setCadAgenda(CadAgenda cadAgenda) {
		this.cadAgenda = cadAgenda;
	}

	public CadAgenda getCadAgendaEdicao() {
		return cadAgendaEdicao;
	}

	public void setCadAgendaEdicao(CadAgenda cadAgendaEdicao) {
		this.cadAgendaEdicao = cadAgendaEdicao;
	}

	public CadAgenda getCadAgendaSelecionada() {
		return cadAgendaSelecionada;
	}

	public void setCadAgendaSelecionada(CadAgenda cadAgendaSelecionada) {
		this.cadAgendaSelecionada = cadAgendaSelecionada;
	}

	public Map<String, Instrutor> getListarInstrutor() {
		return listarInstrutor;
	}

	public void setListarInstrutor(Map<String, Instrutor> listarInstrutor) {
		this.listarInstrutor = listarInstrutor;
	}

	public Map<String, Aluno> getListarAlunos() {
		return listarAlunos;
	}

	public void setListarAlunos(Map<String, Aluno> listarAlunos) {
		this.listarAlunos = listarAlunos;
	}

	public List<Agenda> getListarHorarios() {
		return listarHorarios;
	}

	public void setListarHorarios(List<Agenda> listarHorarios) {
		this.listarHorarios = listarHorarios;
	}
	
	

}
