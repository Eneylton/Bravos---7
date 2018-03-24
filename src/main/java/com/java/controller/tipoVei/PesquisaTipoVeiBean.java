package com.java.controller.tipoVei;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.java.modelo.TipoVei;
import com.java.service.TipoVeiService;
import com.java.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaTipoVeiBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private TipoVeiService TipoVeiService;

	private List<TipoVei> listaTipoVeis = new ArrayList<>();

	private TipoVei tipoVei;

	private TipoVei tipoVeiSelecionado;

	@PostConstruct
	public void inicializar() {

		try {
			listaTipoVeis = TipoVeiService.listarTodos();
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage(ex.getMessage());
		}

	}

	public void excluir() {

		try {
			TipoVeiService.excluir(tipoVeiSelecionado);
			this.listaTipoVeis.remove(tipoVeiSelecionado);
			FacesUtil.addSuccessMessage("Categoria " + tipoVeiSelecionado.getDescricao() + " excluída com sucesso.");
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage(ex.getMessage());
		}

	}

	public List<TipoVei> getListaTipoVeis() {
		return listaTipoVeis;
	}

	public void setListaTipoVeis(List<TipoVei> listaTipoVeis) {
		this.listaTipoVeis = listaTipoVeis;
	}

	public TipoVei getTipoVei() {
		return tipoVei;
	}

	public void setTipoVei(TipoVei tipoVei) {
		this.tipoVei = tipoVei;
	}

	public TipoVei getTipoVeiSelecionado() {
		return tipoVeiSelecionado;
	}

	public void setTipoVeiSelecionado(TipoVei tipoVeiSelecionado) {
		this.tipoVeiSelecionado = tipoVeiSelecionado;
	}

}