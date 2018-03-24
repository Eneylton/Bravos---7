package com.java.controller.sexo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.java.modelo.Sexo;
import com.java.service.SexoService;
import com.java.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaSexoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private SexoService sexoService;

	private List<Sexo> listaSexos = new ArrayList<>();

	private Sexo sexo;

	private Sexo sexoSelecionado;

	@PostConstruct
	public void inicializar() {

		try {
			listaSexos = sexoService.listarTodos();
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage(ex.getMessage());
		}

	}

	public void excluir() {

		try {
			sexoService.excluir(sexoSelecionado);
			this.listaSexos.remove(sexoSelecionado);
			FacesUtil.addSuccessMessage("Categoria " + sexoSelecionado.getDescricao() + " excluída com sucesso.");
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage(ex.getMessage());
		}

	}

	public List<Sexo> getListaSexos() {
		return listaSexos;
	}

	public void setListaSexos(List<Sexo> listaSexos) {
		this.listaSexos = listaSexos;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Sexo getSexoSelecionado() {
		return sexoSelecionado;
	}

	public void setSexoSelecionado(Sexo sexoSelecionado) {
		this.sexoSelecionado = sexoSelecionado;
	}

}