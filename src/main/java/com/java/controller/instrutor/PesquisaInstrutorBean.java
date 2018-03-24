package com.java.controller.instrutor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.java.modelo.Instrutor;
import com.java.service.InstrutorService;
import com.java.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaInstrutorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private InstrutorService instrutorService;

	private List<Instrutor> listaInstrutors = new ArrayList<>();

	private Instrutor instrutor;

	private Instrutor instrutorSelecionado;

	@PostConstruct
	public void inicializar() {

		try {
			listaInstrutors = instrutorService.listarTodos();
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage(ex.getMessage());
		}

	}

	public void excluir() {

		try {
			instrutorService.excluir(instrutorSelecionado);
			this.listaInstrutors.remove(instrutorSelecionado);
			FacesUtil.addSuccessMessage("Categoria " + instrutorSelecionado.getNome() + " excluída com sucesso.");
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage("Instrutor vinculado a um aluno não pode ser excluido !");
		}

	}

	public List<Instrutor> getListaInstrutors() {
		return listaInstrutors;
	}

	public void setListaInstrutors(List<Instrutor> listaInstrutors) {
		this.listaInstrutors = listaInstrutors;
	}

	public Instrutor getInstrutor() {
		return instrutor;
	}

	public void setInstrutor(Instrutor instrutor) {
		this.instrutor = instrutor;
	}

	public Instrutor getInstrutorSelecionado() {
		return instrutorSelecionado;
	}

	public void setInstrutorSelecionado(Instrutor instrutorSelecionado) {
		this.instrutorSelecionado = instrutorSelecionado;
	}

}