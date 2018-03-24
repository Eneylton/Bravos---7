package com.java.controller.financeiro;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.java.modelo.Financeiro;
import com.java.service.FinanceiroService;
import com.java.service.RelatorioService;
import com.java.util.jsf.FacesUtil;

@Named
@ViewScoped
public class FinanceiroCrossBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FinanceiroService financeiroService;
	
	@Inject
	private RelatorioService relatorioService;

	private Financeiro financeiro;

	private List<Financeiro> listaFinanceiros = new ArrayList<>();

	private Date data_inicio;

	private Date data_fim;

	private double valorTotal;

	@PostConstruct
	public void init() {

		try {

			this.limpar();

		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage("Erro no processamento - Erro: " + ex.getMessage());
		}

	}

	public void consultarPriodo() throws SQLException {

		this.data_inicio = financeiro.getDataInicio();

		this.data_fim = financeiro.getDataFim();

		listaFinanceiros = financeiroService.listarTodosPorData(data_inicio, data_fim);
		
		this.valorTotal = financeiroService.getTotalData(data_inicio, data_fim);

	}
	
	
	public void gerarRelatorioPeriodo() {

		try {

			this.data_inicio = financeiro.getDataInicio();

			this.data_fim = financeiro.getDataFim();
			
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			listaFinanceiros = financeiroService.listarTodosPorData(data_inicio, data_fim);
			relatorioService.gerarRelatorioPerido(listaFinanceiros, parametros, "periodo");

		

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	public void limpar() {
		this.financeiro = new Financeiro();
	}

	public Financeiro getFinanceiro() {
		return financeiro;
	}

	public void setFinanceiro(Financeiro financeiro) {
		this.financeiro = financeiro;
	}

	public Date getData_inicio() {
		return data_inicio;
	}

	public void setData_inicio(Date data_inicio) {
		this.data_inicio = data_inicio;
	}

	public Date getData_fim() {
		return data_fim;
	}

	public void setData_fim(Date data_fim) {
		this.data_fim = data_fim;
	}

	public List<Financeiro> getListaFinanceiros() {
		return listaFinanceiros;
	}

	public void setListaFinanceiros(List<Financeiro> listaFinanceiros) {
		this.listaFinanceiros = listaFinanceiros;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

}
