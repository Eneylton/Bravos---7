package com.java.controller.mapa;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.java.modelo.Mapa2;
import com.java.service.Mapa2Service;
import com.java.service.Relatorio2Service;
import com.java.util.FacesMessages;
import com.java.util.jsf.FacesUtil;

@Named
@ViewScoped
public class Mapa2Bean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private Mapa2Service mapa2Service;

	@Inject
	private FacesMessages messages;

	@Inject
	private Relatorio2Service relatorioService;

	private List<Mapa2> listarMapas;

	private List<Mapa2> pesquisarMapas;

	private Mapa2 mapa2;

	private Mapa2 mapa2Edicao = new Mapa2();

	private Mapa2 mapa2Selecionada;

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
		mapa2Edicao = new Mapa2();
	}

	public void salvar() throws SQLException {
		try {

			if (mapa2Edicao.getId() == null) {

				mapa2Service.incluir(mapa2Edicao);
				consultar();
				pesquisar();

				messages.info("Auto Escola Cadastrada com sucesso!");

				RequestContext.getCurrentInstance().update(Arrays.asList("frm:msgs", "frm:mapa2Table"));

			} else {

				mapa2Service.alterar(mapa2Edicao);
				consultar();
				pesquisar();

				messages.info("Registro Alterado com sucesso!");

				RequestContext.getCurrentInstance().update(Arrays.asList("frm:msgs", "frm:mapa2Table"));

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage("Erro no processamento - Erro: " + ex.getMessage());
		}
	}

	public void excluir() throws SQLException {
		mapa2Service.excluir(mapa2Selecionada);
		mapa2Selecionada = null;

		consultar();
		limpar();

		messages.info("Auto Escola excluída com sucesso!");
		RequestContext.getCurrentInstance().update(Arrays.asList("frm:msgs", "frm:mapa2Table"));
	}

	public void consultar() throws SQLException {

		listarMapas = mapa2Service.listarTodos();

	}

	public void pesquisar() throws SQLException {

		listarMapas = mapa2Service.listarTodos();

	}
	
	
	
	public void gerarRelatorio() {

		try {
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			listarMapas = mapa2Service.listarTodos();
			relatorioService.gerarRelatorio(listarMapas, parametros, "rel02");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public void gerarMapa() {

		try {
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			listarMapas = mapa2Service.listarTodos();
			relatorioService.gerarRelatorioMapa(listarMapas, parametros, "mapaRegiao");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public void limpar() {
		this.mapa2 = new Mapa2();
	}

	public FacesMessages getMessages() {
		return messages;
	}

	public void setMessages(FacesMessages messages) {
		this.messages = messages;
	}

	public List<Mapa2> getListarMapas() {
		return listarMapas;
	}

	public void setListarMapas(List<Mapa2> listarMapas) {
		this.listarMapas = listarMapas;
	}

	public List<Mapa2> getPesquisarMapas() {
		return pesquisarMapas;
	}

	public void setPesquisarMapas(List<Mapa2> pesquisarMapas) {
		this.pesquisarMapas = pesquisarMapas;
	}

	public Mapa2 getMapa2() {
		return mapa2;
	}

	public void setMapa2(Mapa2 mapa2) {
		this.mapa2 = mapa2;
	}

	public Mapa2 getMapa2Edicao() {
		return mapa2Edicao;
	}

	public void setMapa2Edicao(Mapa2 mapa2Edicao) {
		this.mapa2Edicao = mapa2Edicao;
	}

	public Mapa2 getMapa2Selecionada() {
		return mapa2Selecionada;
	}

	public void setMapa2Selecionada(Mapa2 mapa2Selecionada) {
		this.mapa2Selecionada = mapa2Selecionada;
	}

}
