package com.java.controller.simulador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.java.modelo.Agenda2;
import com.java.service.MapaSimuladorService;
import com.java.util.jsf.FacesUtil;

@Named
@ViewScoped
public class MapaSimuladorBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private MapaSimuladorService mapaSimuladorService;

	private List<Agenda2> listarAgendas = new ArrayList<>();

	@PostConstruct
	public void inicializar() {

		try {
			
			String idAluno = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
					.get("aluno");
			
			listarAgendas = mapaSimuladorService.retornoAlunoMapa(Long.parseLong(idAluno));
			
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage(ex.getMessage());
		}

	}

	public List<Agenda2> getListarAgendas() {
		return listarAgendas;
	}

	public void setListarAgendas(List<Agenda2> listarAgendas) {
		this.listarAgendas = listarAgendas;
	}

}
