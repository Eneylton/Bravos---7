package com.java.controller.cadagenda;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.java.modelo.Agenda;
import com.java.service.CadAgendaService;
import com.java.util.FacesMessages;
import com.java.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PainelAgendaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadAgendaService cadAgendaService;
	
	@Inject
	private FacesMessages messages;

	private List<Agenda> listarHorarios;
	
	private Agenda agendaSelecionada;

	private Agenda agenda;

	@PostConstruct
	public void init() {

		try {
			this.limpar();
			
			

			String idAluno = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
					.get("cadAgenda");
			if (idAluno != null){

			listarHorarios = cadAgendaService.retornoAlunoMapa(Long.parseLong(idAluno));

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage("Erro no Processamento - Erro: " + ex.getMessage());
		}

	}
	
	

	public void limpar() {
		this.agenda = new Agenda();
	}

	public FacesMessages getMessages() {
		return messages;
	}

	public void setMessages(FacesMessages messages) {
		this.messages = messages;
	}


	public List<Agenda> getListarHorarios() {
		return listarHorarios;
	}

	public void setListarHorarios(List<Agenda> listarHorarios) {
		this.listarHorarios = listarHorarios;
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
