package com.java.controller.categoria;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.java.modelo.Categoria;
import com.java.service.CategoriaService;
import com.java.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroCategoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CategoriaService categoriaService;

	private Categoria categoria;

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

			if (categoria.getId() == null) {
				categoriaService.incluir(categoria);
			} else {
				categoriaService.alterar(categoria);
			}

			FacesUtil.addSuccessMessage("Categoria salvo com sucesso!");

			this.limpar();

		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public void limpar() {
		this.categoria = new Categoria();
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
