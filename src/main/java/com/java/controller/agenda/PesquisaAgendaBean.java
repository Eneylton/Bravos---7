package com.java.controller.agenda;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.java.modelo.Agenda;
import com.java.service.AgendaService;
import com.java.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaAgendaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AgendaService agendaService;

	private List<Agenda> agendas = new ArrayList<>();

	private Agenda agenda;

	private Agenda agendaSelecionada;

	@PostConstruct
	public void inicializar() {

		try {
			agendas = agendaService.listarTodos();
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage(ex.getMessage());
		}

	}

	public void excluir() {

		try {
			agendaService.excluir(agendaSelecionada);
			this.agendas.remove(agendaSelecionada);
			FacesUtil.addSuccessMessage("Agenda " + agendaSelecionada.getDescricao() + " excluída com sucesso.");
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage(ex.getMessage());
		}

	}

	public void filtrar() throws SQLException, ParseException {
		agendas = agendaService.listarTodos();
	}

	public List<Agenda> getAgendas() {
		return agendas;
	}

	public void setAgendas(List<Agenda> agendas) {
		this.agendas = agendas;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public Agenda getAgendaSelecionada() {
		return agendaSelecionada;
	}

	public void setAgendaSelecionada(Agenda agendaSelecionada) {
		this.agendaSelecionada = agendaSelecionada;
	}

}
