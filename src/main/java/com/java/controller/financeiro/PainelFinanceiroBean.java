package com.java.controller.financeiro;

import java.io.Serializable;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.java.service.PainelFinanceiroService;
import com.java.util.Session;
import com.java.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PainelFinanceiroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PainelFinanceiroService painelFinanceiroService;

	private double receita;

	private double receitaDia;

	private double despesaDia;

	private double totalDia;

	private double receitaBanco;

	private double despesa;

	private double despesaBanco;

	private double movimentado;

	private double movimentadoBanco;

	private double movimentadoDespesa;

	private double pagar;

	private double receber;

	private double recebido;

	private double atrasado;

	@PostConstruct
	public void init() {

		try {

			carregarValor();

		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage("Erro no processamento - Erro: " + ex.getMessage());
		}

	}

	private void carregarValor() throws SQLException {

		Long idAuto = Session.retornaIdAutoEscola();

		receita = painelFinanceiroService.getTotalReceita(idAuto);

		despesa = painelFinanceiroService.getTotalDespesa(idAuto);
		
		movimentadoDespesa = painelFinanceiroService.getTotalDespesaMovimentada(idAuto);
		
		receber = painelFinanceiroService.getReceber(idAuto);

		pagar = painelFinanceiroService.getPagar(idAuto);

		recebido = painelFinanceiroService.getRecebido(idAuto);

		atrasado = painelFinanceiroService.getTotalAtrasado(idAuto);

		receitaBanco = painelFinanceiroService.getTotalBancoReceita(idAuto);

		despesaBanco = painelFinanceiroService.getTotalBancoDespesa(idAuto);

		movimentado = receita - despesa;

		movimentadoBanco = receitaBanco - despesaBanco;

		receitaDia = painelFinanceiroService.getTotalReceitaDIA(idAuto);
		despesaDia = painelFinanceiroService.getTotalDespesaDIA(idAuto);
		
		totalDia = receitaDia - despesaDia;

	}

	public double getReceita() {
		return receita;
	}

	public void setReceita(double receita) {
		this.receita = receita;
	}

	public double getDespesa() {
		return despesa;
	}

	public void setDespesa(double despesa) {
		this.despesa = despesa;
	}

	public double getMovimentado() {
		return movimentado;
	}

	public void setMovimentado(double movimentado) {
		this.movimentado = movimentado;
	}

	public double getReceitaBanco() {
		return receitaBanco;
	}

	public void setReceitaBanco(double receitaBanco) {
		this.receitaBanco = receitaBanco;
	}

	public double getDespesaBanco() {
		return despesaBanco;
	}

	public void setDespesaBanco(double despesaBanco) {
		this.despesaBanco = despesaBanco;
	}

	public double getMovimentadoBanco() {
		return movimentadoBanco;
	}

	public void setMovimentadoBanco(double movimentadoBanco) {
		this.movimentadoBanco = movimentadoBanco;
	}

	public double getMovimentadoDespesa() {
		return movimentadoDespesa;
	}

	public void setMovimentadoDespesa(double movimentadoDespesa) {
		this.movimentadoDespesa = movimentadoDespesa;
	}

	public double getPagar() {
		return pagar;
	}

	public void setPagar(double pagar) {
		this.pagar = pagar;
	}

	public double getReceber() {
		return receber;
	}

	public void setReceber(double receber) {
		this.receber = receber;
	}

	public double getRecebido() {
		return recebido;
	}

	public void setRecebido(double recebido) {
		this.recebido = recebido;
	}

	public double getAtrasado() {
		return atrasado;
	}

	public void setAtrasado(double atrasado) {
		this.atrasado = atrasado;
	}

	public double getReceitaDia() {
		return receitaDia;
	}

	public void setReceitaDia(double receitaDia) {
		this.receitaDia = receitaDia;
	}

	public double getDespesaDia() {
		return despesaDia;
	}

	public void setDespesaDia(double despesaDia) {
		this.despesaDia = despesaDia;
	}

	public double getTotalDia() {
		return totalDia;
	}

	public void setTotalDia(double totalDia) {
		this.totalDia = totalDia;
	}

}
