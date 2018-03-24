package com.java.controller.caixa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.java.modelo.Caixa;
import com.java.service.CaixaService;
import com.java.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaCaixaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private CaixaService caixaService;

	private List<Caixa> listaCaixas = new ArrayList<>();

	private Caixa caixa;

	private Caixa caixaSelecionado;

	@PostConstruct
	public void inicializar() {

		try {
			listaCaixas = caixaService.listarTodos();
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage(ex.getMessage());
		}

	}

	public void excluir() {

		try {
			caixaService.excluir(caixaSelecionado);
			this.listaCaixas.remove(caixaSelecionado);
			FacesUtil.addSuccessMessage("Categoria " + caixaSelecionado.getDescricao() + " excluída com sucesso.");
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage(ex.getMessage());
		}

	}

	public List<Caixa> getListaCaixas() {
		return listaCaixas;
	}

	public void setListaCaixas(List<Caixa> listaCaixas) {
		this.listaCaixas = listaCaixas;
	}

	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}

	public Caixa getCaixaSelecionado() {
		return caixaSelecionado;
	}

	public void setCaixaSelecionado(Caixa caixaSelecionado) {
		this.caixaSelecionado = caixaSelecionado;
	}

}