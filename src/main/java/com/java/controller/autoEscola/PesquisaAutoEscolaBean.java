package com.java.controller.autoEscola;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.java.modelo.AutoEscola;
import com.java.service.AutoEscolaService;
import com.java.service.Relatorio2Service;
import com.java.util.Session;
import com.java.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaAutoEscolaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private AutoEscolaService autoEscolaService;
	
	@Inject
	private Relatorio2Service relatorioService;

	private List<AutoEscola> listaAutoEscolas = new ArrayList<>();
	private List<AutoEscola> pesquisarAutoEscolas = new ArrayList<>();

	private AutoEscola autoEscola;

	private AutoEscola autoEscolaSelecionado;

	@PostConstruct
	public void inicializar() {

		try {
			listaAutoEscolas = autoEscolaService.listarTodos();
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage(ex.getMessage());
		}

	}

	public void excluir() {

		try {
			autoEscolaService.excluir(autoEscolaSelecionado);
			this.listaAutoEscolas.remove(autoEscolaSelecionado);
			FacesUtil.addSuccessMessage("Auto escola " + autoEscolaSelecionado.getNome() + " excluída com sucesso.");
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage(ex.getMessage());
		}

	}
	
	public void gerarRelatorio() {

		try {
			Long idAuto = Session.retornaIdAutoEscola();
		
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			listaAutoEscolas = autoEscolaService.listarUsuariosPorAutoEscola(idAuto);
			relatorioService.gerarRelatorioUsuarioAutoEscola(listaAutoEscolas, parametros, "autoEscolaUsuario");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<AutoEscola> getListaAutoEscolas() {
		return listaAutoEscolas;
	}

	public void setListaAutoEscolas(List<AutoEscola> listaAutoEscolas) {
		this.listaAutoEscolas = listaAutoEscolas;
	}

	public AutoEscola getAutoEscola() {
		return autoEscola;
	}

	public void setAutoEscola(AutoEscola autoEscola) {
		this.autoEscola = autoEscola;
	}

	public AutoEscola getAutoEscolaSelecionado() {
		return autoEscolaSelecionado;
	}

	public void setAutoEscolaSelecionado(AutoEscola autoEscolaSelecionado) {
		this.autoEscolaSelecionado = autoEscolaSelecionado;
	}

	public List<AutoEscola> getPesquisarAutoEscolas() {
		return pesquisarAutoEscolas;
	}

	public void setPesquisarAutoEscolas(List<AutoEscola> pesquisarAutoEscolas) {
		this.pesquisarAutoEscolas = pesquisarAutoEscolas;
	}

	

}