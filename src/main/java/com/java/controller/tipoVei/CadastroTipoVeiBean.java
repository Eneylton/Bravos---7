package com.java.controller.tipoVei;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.java.modelo.TipoVei;
import com.java.service.TipoVeiService;
import com.java.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroTipoVeiBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private TipoVeiService tipoVeiService;

	private TipoVei tipoVei;

	@PostConstruct
	public void init() {

		try {

			this.limpar();

		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage("Erro no processamento - Erro: " + ex.getMessage());
		}

	}

	public void salvar() {

		try {

			if (tipoVei.getId() == null) {
				tipoVeiService.incluir(tipoVei);
			} else {
				tipoVeiService.alterar(tipoVei);
			}

			FacesUtil.addSuccessMessage("Categoria salvo com sucesso!");

			this.limpar();

		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public void limpar() {
		this.tipoVei = new TipoVei();
	}

	public TipoVei getTipoVei() {
		return tipoVei;
	}

	public void setTipoVei(TipoVei tipoVei) {
		this.tipoVei = tipoVei;
	}

 

}
