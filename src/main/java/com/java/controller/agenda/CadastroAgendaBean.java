package com.java.controller.agenda;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.java.modelo.Agenda;
import com.java.service.AgendaService;
import com.java.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroAgendaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AgendaService agendaService;

	private Agenda agenda;

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

			if (agenda.getId() == null) {

				agendaService.incluir(agenda);
			} else {
				agendaService.alterar(agenda);
			}

			FacesUtil.addSuccessMessage("Agenda salvo com sucesso!");

			this.limpar();

		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public void limpar() {
		this.agenda = new Agenda();
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda Agenda) {
		this.agenda = Agenda;
	}

}
