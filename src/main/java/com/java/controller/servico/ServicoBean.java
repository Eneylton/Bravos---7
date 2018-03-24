package com.java.controller.servico;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.java.modelo.Servico;
import com.java.service.ServicoService;
import com.java.util.FacesMessages;
import com.java.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ServicoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ServicoService servicoService;

	@Inject
	private FacesMessages messages;

	private List<Servico> listarServicos;

	private List<Servico> pesquisaServicos;

	private Servico servico;

	private Servico servicoEdicao = new Servico();

	private Servico servicoSelecionada;

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
		servicoEdicao = new Servico();
	}

	public void salvar() throws SQLException {
		try {

			if (servicoEdicao.getId() == null) {

				servicoService.incluir(servicoEdicao);
				consultar();
				pesquisar();

				messages.info("Servico salvo com sucesso!");

				RequestContext.getCurrentInstance().update(Arrays.asList("frm:msgs", "frm:servicoTable"));

			} else {

				servicoService.alterar(servicoEdicao);
				consultar();
				pesquisar();

				messages.info("Servico Alterado com sucesso!");

				RequestContext.getCurrentInstance().update(Arrays.asList("frm:msgs", "frm:servicoTable"));

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage("Erro no processamento - Erro: " + ex.getMessage());
		}
	}

	public void excluir() throws SQLException {
		servicoService.excluir(servicoSelecionada);
		servicoSelecionada = null;

		consultar();
		limpar();

		messages.info("Servico excluído com sucesso!");
		RequestContext.getCurrentInstance().update(Arrays.asList("frm:msgs", "frm:servicoTable"));
	}

	public void consultar() throws SQLException {

		listarServicos = servicoService.listarTodos();

	}

	public void pesquisar() throws SQLException {

		pesquisaServicos = servicoService.listarTodos();

	}

	public void limpar() {
		this.servico = new Servico();
	}

	public FacesMessages getMessages() {
		return messages;
	}

	public void setMessages(FacesMessages messages) {
		this.messages = messages;
	}

	public List<Servico> getListarServicos() {
		return listarServicos;
	}

	public void setListarServicos(List<Servico> listarServicos) {
		this.listarServicos = listarServicos;
	}

	public List<Servico> getPesquisaServicos() {
		return pesquisaServicos;
	}

	public void setPesquisaServicos(List<Servico> pesquisaServicos) {
		this.pesquisaServicos = pesquisaServicos;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public Servico getServicoEdicao() {
		return servicoEdicao;
	}

	public void setServicoEdicao(Servico servicoEdicao) {
		this.servicoEdicao = servicoEdicao;
	}

	public Servico getServicoSelecionada() {
		return servicoSelecionada;
	}

	public void setServicoSelecionada(Servico servicoSelecionada) {
		this.servicoSelecionada = servicoSelecionada;
	}

}
