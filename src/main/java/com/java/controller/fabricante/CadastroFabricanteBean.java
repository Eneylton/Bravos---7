package com.java.controller.fabricante;

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

import com.java.modelo.Fabricante;
import com.java.modelo.TipoVei;
import com.java.service.FabricanteService;
import com.java.service.TipoVeiService;
import com.java.util.FileUploadCopiarArquivo;
import com.java.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroFabricanteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FabricanteService fabricanteService;

	@Inject
	private TipoVeiService tipoVeiService;

	private TipoVei tipoVei = new TipoVei();

	private Fabricante fabricante;

	private Map<String, TipoVei> listarTipoVeiculos = new HashMap<String, TipoVei>();

	private UploadedFile arquivoUP;

	@PostConstruct
	public void init() {

		try {

			this.limpar();

			preencheListasSelect();

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
				fabricante.setFoto(nomeArquivo);
			} else {
				fabricante.setFoto("");
			}

			if (fabricante.getId() == null) {
				fabricanteService.incluir(fabricante);
			} else {
				fabricanteService.alterar(fabricante);
			}

			FacesUtil.addSuccessMessage("Fabricante salva com sucesso!");

			this.limpar();

		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}


	private void preencheListasSelect() throws SQLException {

		listarTipoVeiculos = new HashMap<String, TipoVei>();
		for (TipoVei tipovei : tipoVeiService.listarTodos()) {
			listarTipoVeiculos.put(tipovei.getDescricao(), tipovei);
		}

	}
	
	
	public void handleFileUpload(FileUploadEvent event) {
		this.setArquivoUP(event.getFile());
	}


	public void limpar() {
		this.fabricante = new Fabricante();
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public TipoVei getTipoVei() {
		return tipoVei;
	}

	public void setTipoVei(TipoVei tipoVei) {
		this.tipoVei = tipoVei;
	}

	public Map<String, TipoVei> getListarTipoVeiculos() {
		return listarTipoVeiculos;
	}

	public void setListarTipoVeiculos(Map<String, TipoVei> listarTipoVeiculos) {
		this.listarTipoVeiculos = listarTipoVeiculos;
	}

	public UploadedFile getArquivoUP() {
		return arquivoUP;
	}

	public void setArquivoUP(UploadedFile arquivoUP) {
		this.arquivoUP = arquivoUP;
	}

}
