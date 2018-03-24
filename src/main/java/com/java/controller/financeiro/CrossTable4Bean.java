package com.java.controller.financeiro;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.java.modelo.CrossTable;
import com.java.service.CrossTableService;
import com.java.service.RelatorioService;
import com.java.util.FacesMessages;
import com.java.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CrossTable4Bean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CrossTableService crossTableService;

	@Inject
	private FacesMessages messages;
	
	@Inject
	private RelatorioService relatorioService;

	private List<CrossTable> listarCrossTable;

	private CrossTable crossTable;

	@PostConstruct
	public void init() {

		try {

			this.limpar();

			consultar();

		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage("Erro no Processamento - Erro: " + ex.getMessage());
		}

	}

	public void consultar() throws SQLException {

		listarCrossTable = crossTableService.listarDespesasBanco();

	}
	
	public void gerarRelatorioDespesaBanco() {

		try {
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			listarCrossTable = crossTableService.listarDespesasBanco();
			relatorioService.gerarRelatorioDespesaBanco(listarCrossTable, parametros, "despesaBanco");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public void limpar() {
		this.crossTable = new CrossTable();
	}

	public FacesMessages getMessages() {
		return messages;
	}

	public void setMessages(FacesMessages messages) {
		this.messages = messages;
	}

	public List<CrossTable> getListarCrossTable() {
		return listarCrossTable;
	}

	public void setListarCrossTable(List<CrossTable> listarCrossTable) {
		this.listarCrossTable = listarCrossTable;
	}

	public CrossTable getCrossTable() {
		return crossTable;
	}

	public void setCrossTable(CrossTable crossTable) {
		this.crossTable = crossTable;
	}

}
