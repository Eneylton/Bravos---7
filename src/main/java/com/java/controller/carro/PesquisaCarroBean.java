package com.java.controller.carro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.java.modelo.Carro;
import com.java.service.CarroService;
import com.java.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaCarroBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private CarroService CarroService;

	private List<Carro> listaCarros = new ArrayList<>();

	private Carro carro;

	private Carro carroSelecionado;

	@PostConstruct
	public void inicializar() {

		try {
			listaCarros = CarroService.listarTodos();
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage(ex.getMessage());
		}

	}

	public void excluir() {

		try {
			CarroService.excluir(carroSelecionado);
			this.listaCarros.remove(carroSelecionado);
			FacesUtil.addSuccessMessage("Veiculo excluída com sucesso.");
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage(ex.getMessage());
		}

	}

	public List<Carro> getListaCarros() {
		return listaCarros;
	}

	public void setListaCarros(List<Carro> listaCarros) {
		this.listaCarros = listaCarros;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public Carro getCarroSelecionado() {
		return carroSelecionado;
	}

	public void setCarroSelecionado(Carro carroSelecionado) {
		this.carroSelecionado = carroSelecionado;
	}

}