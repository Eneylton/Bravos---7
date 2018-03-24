package com.java.controller.categoria;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.java.modelo.Categoria;
import com.java.service.CategoriaService;
import com.java.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaCategoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private CategoriaService categoriaService;

	private List<Categoria> listaCategorias = new ArrayList<>();

	private Categoria categoria;

	private Categoria categoriaSelecionado;

	@PostConstruct
	public void inicializar() {

		try {
			listaCategorias = categoriaService.listarTodos();
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage(ex.getMessage());
		}

	}

	public void excluir() {

		try {
			categoriaService.excluir(categoriaSelecionado);
			this.listaCategorias.remove(categoriaSelecionado);
			FacesUtil.addSuccessMessage("Categoria " + categoriaSelecionado.getDescricao() + " excluída com sucesso.");
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage(ex.getMessage());
		}

	}

	public List<Categoria> getListaCategorias() {
		return listaCategorias;
	}

	public void setListaCategorias(List<Categoria> listaCategorias) {
		this.listaCategorias = listaCategorias;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Categoria getCategoriaSelecionado() {
		return categoriaSelecionado;
	}

	public void setCategoriaSelecionado(Categoria categoriaSelecionado) {
		this.categoriaSelecionado = categoriaSelecionado;
	}

}