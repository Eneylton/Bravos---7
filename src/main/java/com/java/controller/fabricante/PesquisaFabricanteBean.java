package com.java.controller.fabricante;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.java.modelo.Fabricante;
import com.java.service.FabricanteService;
import com.java.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaFabricanteBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private FabricanteService fabricanteService;

	private List<Fabricante> listaFabricantes = new ArrayList<>();

	private Fabricante fabricante;

	private Fabricante fabricanteSelecionado;

	@PostConstruct
	public void inicializar() {

		try {
			listaFabricantes = fabricanteService.listarTodos();
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage(ex.getMessage());
		}

	}

	public void excluir() {

		try {
			fabricanteService.excluir(fabricanteSelecionado);
			this.listaFabricantes.remove(fabricanteSelecionado);
			FacesUtil.addSuccessMessage("Fabricante " + fabricanteSelecionado.getMarca() + " excluída com sucesso.");
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage(ex.getMessage());
		}

	}

	public List<Fabricante> getListaFabricantes() {
		return listaFabricantes;
	}

	public void setListaFabricantes(List<Fabricante> listaFabricantes) {
		this.listaFabricantes = listaFabricantes;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante Fabricante) {
		this.fabricante = Fabricante;
	}

	public Fabricante getFabricanteSelecionado() {
		return fabricanteSelecionado;
	}

	public void setFabricanteSelecionado(Fabricante FabricanteSelecionado) {
		this.fabricanteSelecionado = FabricanteSelecionado;
	}

}