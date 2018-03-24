package com.java.controller.provaTrafego;

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

import com.java.modelo.Aluno;
import com.java.modelo.ProvaTrafego;
import com.java.service.AlunoService;
import com.java.service.ProvaTrafegoService;
import com.java.util.FacesMessages;
import com.java.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ProvaTrafegoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProvaTrafegoService provaTrafegoService;

	@Inject
	private FacesMessages messages;

	@Inject
	private AlunoService alunoService = new AlunoService();

	private List<ProvaTrafego> listarProvaTrafegos;

	private List<ProvaTrafego> pesquisaProvaTrafegos;

	private Map<String, Aluno> listarAlunos = new HashMap<String, Aluno>();

	private ProvaTrafego provaTrafego;

	private ProvaTrafego provaTrafegoEdicao = new ProvaTrafego();

	private ProvaTrafego provaTrafegoSelecionada;

	@PostConstruct
	public void init() {

		try {

			this.limpar();
			consultar();
			preencheListasSelect();

		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage("Erro no Processamento - Erro: " + ex.getMessage());
		}

	}

	private void preencheListasSelect() throws SQLException {

		listarAlunos = new HashMap<String, Aluno>();
		for (Aluno aluno : alunoService.listarTodos()) {
			listarAlunos.put(aluno.getNome(), aluno);
		}

	}

	public void prepararNovoCadastro() {
		provaTrafegoEdicao = new ProvaTrafego();
	}

	public void salvar() throws SQLException {
		try {

			if (provaTrafegoEdicao.getId() == null) {

				provaTrafegoService.incluir(provaTrafegoEdicao);
				consultar();

				messages.info("Prova de Legislação Marcada com Sucesso !");

				RequestContext.getCurrentInstance().update(Arrays.asList("frm:msgs", "frm:provaTrafegoTable"));

			} else {

				provaTrafegoService.alterar(provaTrafegoEdicao);
				consultar();

				messages.info("Prova de Legislação Remacada com sucesso!");

				RequestContext.getCurrentInstance().update(Arrays.asList("frm:msgs", "frm:provaTrafegoTable"));

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage("Erro no processamento - Erro: " + ex.getMessage());
		}
	}

	public void editar(ProvaTrafego provaTrafego) {
		try {
			this.provaTrafegoService.alterarTab(provaTrafego);

			FacesUtil.addSuccessMessage("Prova de Tráfego Realizada com sucesso!");

			consultar();

		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public void excluir() throws SQLException {
		provaTrafegoService.excluir(provaTrafegoSelecionada);
		provaTrafegoSelecionada = null;

		consultar();
		limpar();

		messages.info("Agendamento excluído com sucesso!");
		RequestContext.getCurrentInstance().update(Arrays.asList("frm:msgs", "frm:provaTrafegoTable"));
	}

	public void consultar() throws SQLException {

		listarProvaTrafegos = provaTrafegoService.listarTodos();

		pesquisaProvaTrafegos = provaTrafegoService.listarTodos();
	}

	public void limpar() {
		this.provaTrafego = new ProvaTrafego();
	}

	public FacesMessages getMessages() {
		return messages;
	}

	public void setMessages(FacesMessages messages) {
		this.messages = messages;
	}

	public List<ProvaTrafego> getListarProvaTrafegos() {
		return listarProvaTrafegos;
	}

	public void setListarProvaTrafegos(List<ProvaTrafego> listarProvaTrafegos) {
		this.listarProvaTrafegos = listarProvaTrafegos;
	}

	public List<ProvaTrafego> getPesquisaProvaTrafegos() {
		return pesquisaProvaTrafegos;
	}

	public void setPesquisaProvaTrafegos(List<ProvaTrafego> pesquisaProvaTrafegos) {
		this.pesquisaProvaTrafegos = pesquisaProvaTrafegos;
	}

	public Map<String, Aluno> getListarAlunos() {
		return listarAlunos;
	}

	public void setListarAlunos(Map<String, Aluno> listarAlunos) {
		this.listarAlunos = listarAlunos;
	}

	public ProvaTrafego getProvaTrafego() {
		return provaTrafego;
	}

	public void setProvaTrafego(ProvaTrafego provaTrafego) {
		this.provaTrafego = provaTrafego;
	}

	public ProvaTrafego getProvaTrafegoEdicao() {
		return provaTrafegoEdicao;
	}

	public void setProvaTrafegoEdicao(ProvaTrafego provaTrafegoEdicao) {
		this.provaTrafegoEdicao = provaTrafegoEdicao;
	}

	public ProvaTrafego getProvaTrafegoSelecionada() {
		return provaTrafegoSelecionada;
	}

	public void setProvaTrafegoSelecionada(ProvaTrafego provaTrafegoSelecionada) {
		this.provaTrafegoSelecionada = provaTrafegoSelecionada;
	}

}
