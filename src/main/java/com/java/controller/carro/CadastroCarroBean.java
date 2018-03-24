package com.java.controller.carro;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.java.dao.VeiculoDAO;
import com.java.modelo.Carro;
import com.java.modelo.Veiculo;
import com.java.service.CarroService;
import com.java.util.FileUploadCopiarArquivo;
import com.java.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroCarroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CarroService carroService;

	@Inject
	private VeiculoDAO veiculoDAO;

	private Carro carro;

	private UploadedFile arquivoUP;

	private Map<String, Veiculo> listarVeiculos = new HashMap<String, Veiculo>();

	@PostConstruct
	public void init() {

		try {

			preencheListasSelect();

			this.limpar();

		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage("Erro no processamento - Erro: " + ex.getMessage());
		}

	}

	public void salvar() {

		try {

			String nomeArquivo = "";
			if (arquivoUP != null) {
				FileUploadCopiarArquivo.iniciarCopia(arquivoUP);
				nomeArquivo = arquivoUP.getFileName();
				carro.setFoto(nomeArquivo);
			} else {
				carro.setFoto("");
			}

			if (carro.getId() == null) {
				carroService.incluir(carro);
			} else {
				carroService.alterar(carro);
			}

			FacesUtil.addSuccessMessage("Arquivo salva com sucesso!");

			this.limpar();

		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	private void preencheListasSelect() throws SQLException {

		listarVeiculos = new HashMap<String, Veiculo>();
		for (Veiculo veiculo : veiculoDAO.listarTodos()) {
			listarVeiculos.put(veiculo.getModelo(), veiculo);
		}

	}
	
	public void handleFileUpload(FileUploadEvent event) {
		this.setArquivoUP(event.getFile());
	}

	public void limpar() {
		this.carro = new Carro();
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public Map<String, Veiculo> getListarVeiculos() {
		return listarVeiculos;
	}

	public void setListarVeiculos(Map<String, Veiculo> listarVeiculos) {
		this.listarVeiculos = listarVeiculos;
	}

	public UploadedFile getArquivoUP() {
		return arquivoUP;
	}

	public void setArquivoUP(UploadedFile arquivoUP) {
		this.arquivoUP = arquivoUP;
	}

}
