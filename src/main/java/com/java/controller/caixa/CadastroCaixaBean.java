package com.java.controller.caixa;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.java.modelo.Caixa;
import com.java.service.CaixaService;
import com.java.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroCaixaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CaixaService caixaService;

	private Caixa caixa;

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

			if (caixa.getId() == null) {
				caixaService.incluir(caixa);
			} else {
				caixaService.alterar(caixa);
			}

			FacesUtil.addSuccessMessage("Categoria salvo com sucesso!");

			this.limpar();

		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public void limpar() {
		this.caixa = new Caixa();
	}

	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}

}
