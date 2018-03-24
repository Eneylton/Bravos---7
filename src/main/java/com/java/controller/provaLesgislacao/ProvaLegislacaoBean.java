package com.java.controller.provaLesgislacao;

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
import com.java.modelo.Provalegislacao;
import com.java.service.AlunoService;
import com.java.service.ProvaLegislacaoService;
import com.java.util.FacesMessages;
import com.java.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ProvaLegislacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProvaLegislacaoService provalegislacaoService;

	@Inject
	private FacesMessages messages;

	@Inject
	private AlunoService alunoService = new AlunoService();

	private List<Provalegislacao> listarProvalegislacaos;

	private List<Provalegislacao> pesquisaProvalegislacaos;

	private Map<String, Aluno> listarAlunos = new HashMap<String, Aluno>();

	private Provalegislacao provalegislacao;

	private Provalegislacao provalegislacaoEdicao = new Provalegislacao();

	private Provalegislacao provalegislacaoSelecionada;

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
		provalegislacaoEdicao = new Provalegislacao();
	}

	public void salvar() throws SQLException {
		try {

			if (provalegislacaoEdicao.getId() == null) {

				provalegislacaoService.incluir(provalegislacaoEdicao);
				consultar();

				messages.info("Prova de Legislação Marcada com Sucesso !");

				RequestContext.getCurrentInstance().update(Arrays.asList("frm:msgs", "frm:provaLegislacaoTable"));

			} else {

				provalegislacaoService.alterar(provalegislacaoEdicao);
				consultar();

				messages.info("Prova de Legislação Remacada com sucesso!");

				RequestContext.getCurrentInstance().update(Arrays.asList("frm:msgs", "frm:provaLegislacaoTable"));

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage("Erro no processamento - Erro: " + ex.getMessage());
		}
	}
	
	
	public void editar(Provalegislacao provalegislacao) {
		try {
			this.provalegislacaoService.alterarTab(provalegislacao);

			FacesUtil.addSuccessMessage("Prova de Legislação Realizada com sucesso!");
			
			consultar();

		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	

	public void excluir() throws SQLException {
		provalegislacaoService.excluir(provalegislacaoSelecionada);
		provalegislacaoSelecionada = null;

		consultar();
		limpar();

		messages.info("Agendamento excluído com sucesso!");
		RequestContext.getCurrentInstance().update(Arrays.asList("frm:msgs", "frm:provalegislacaoTable"));
	}

	public void consultar() throws SQLException {

		listarProvalegislacaos = provalegislacaoService.listarTodos();

		pesquisaProvalegislacaos = provalegislacaoService.listarTodos();
	}

	public void limpar() {
		this.provalegislacao = new Provalegislacao();
	}

	public FacesMessages getMessages() {
		return messages;
	}

	public void setMessages(FacesMessages messages) {
		this.messages = messages;
	}

	public List<Provalegislacao> getListarProvalegislacaos() {
		return listarProvalegislacaos;
	}

	public void setListarProvalegislacaos(List<Provalegislacao> listarProvalegislacaos) {
		this.listarProvalegislacaos = listarProvalegislacaos;
	}

	public List<Provalegislacao> getPesquisaProvalegislacaos() {
		return pesquisaProvalegislacaos;
	}

	public void setPesquisaProvalegislacaos(List<Provalegislacao> pesquisaProvalegislacaos) {
		this.pesquisaProvalegislacaos = pesquisaProvalegislacaos;
	}

	public Map<String, Aluno> getListarAlunos() {
		return listarAlunos;
	}

	public void setListarAlunos(Map<String, Aluno> listarAlunos) {
		this.listarAlunos = listarAlunos;
	}

	public Provalegislacao getProvalegislacao() {
		return provalegislacao;
	}

	public void setProvalegislacao(Provalegislacao provalegislacao) {
		this.provalegislacao = provalegislacao;
	}

	public Provalegislacao getProvalegislacaoEdicao() {
		return provalegislacaoEdicao;
	}

	public void setProvalegislacaoEdicao(Provalegislacao provalegislacaoEdicao) {
		this.provalegislacaoEdicao = provalegislacaoEdicao;
	}

	public Provalegislacao getProvalegislacaoSelecionada() {
		return provalegislacaoSelecionada;
	}

	public void setProvalegislacaoSelecionada(Provalegislacao provalegislacaoSelecionada) {
		this.provalegislacaoSelecionada = provalegislacaoSelecionada;
	}

}
