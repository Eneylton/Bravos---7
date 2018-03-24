package com.java.controller.sexo;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.java.modelo.Sexo;
import com.java.service.SexoService;
import com.java.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroSexoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private SexoService sexoService;

	private Sexo sexo;

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

			if (sexo.getId() == null) {
				sexoService.incluir(sexo);
			} else {
				sexoService.alterar(sexo);
			}

			FacesUtil.addSuccessMessage("Categoria salvo com sucesso!");

			this.limpar();

		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public void limpar() {
		this.sexo = new Sexo();
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

}
