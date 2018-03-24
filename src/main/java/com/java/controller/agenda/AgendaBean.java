package com.java.controller.agenda;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import com.java.modelo.Agenda;
import com.java.modelo.CadAgenda;
import com.java.service.AgendaService;
import com.java.service.CadAgendaService;
import com.java.service.RelatorioService;
import com.java.util.jsf.FacesUtil;

import net.sf.jasperreports.engine.JRException;

@Named
@ViewScoped
public class AgendaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AgendaService agendaService;

	@Inject
	private CadAgendaService cadAgendaService;

	@Inject
	private RelatorioService relatorioService;

	private List<Agenda> listarHorarios;

	private List<Agenda> listarAgendas = new ArrayList<>();

	private ScheduleModel agendaCalendario;

	private Agenda agenda;

	private CadAgenda cadAgenda = new CadAgenda();

	private Agenda agendaSelecionada;

	@PostConstruct
	public void inicializar() throws ParseException {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT-3"));

		agendaCalendario = new DefaultScheduleModel();

		try {
			listarAgendas = agendaService.listarTodos();
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage(ex.getMessage());
		}

		for (Agenda ev1 : listarAgendas) {

			DefaultScheduleEvent evt = new DefaultScheduleEvent();
			evt.setData(ev1.getId());
			evt.setTitle(ev1.getCadAgenda().getAluno().getNome());
			evt.setStartDate(ev1.getInicio());
			evt.setEndDate(ev1.getFim());

			evt.setDescription(ev1.getDescricao());
			evt.setEditable(true);

			if (ev1.isStatus() == true) {
				evt.setStyleClass("cor1");
			} else if (ev1.isStatus() == false) {
				evt.setStyleClass("cor2");
			}

			agendaCalendario.addEvent(evt);

		}

	}

	public void quandoSelecionado(SelectEvent selectEvent) {
		ScheduleEvent event = (ScheduleEvent) selectEvent.getObject();

		for (Agenda ev2 : listarAgendas) {
			if (ev2.getId() == (Long) event.getData()) {
				agenda = ev2;
				break;
			}
		}
	}

	public void quandoNovo(SelectEvent selectEvent) {
		ScheduleEvent event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(),
				(Date) selectEvent.getObject());
		agenda = new Agenda();
		agenda.setInicio(new java.sql.Date(event.getStartDate().getTime()));
		agenda.setFim(new java.sql.Date(event.getEndDate().getTime()));
	}

	public void quandoMovido(ScheduleEntryMoveEvent movi) {
		for (Agenda ag : listarAgendas) {

			if (ag.getId() == (Long) movi.getScheduleEvent().getData()) {

				agenda = ag;

				try {

					agendaService.alterar(agenda);
					inicializar();

				} catch (Exception ex) {
					ex.printStackTrace();
					FacesUtil.addErrorMessage(ex.getMessage());
				}
				break;

			}

		}
	}

	public void quandoRedimencionado(ScheduleEntryResizeEvent movi) {

		for (Agenda ag : listarAgendas) {

			if (ag.getId() == (Long) movi.getScheduleEvent().getData()) {

				agenda = ag;

				try {

					agendaService.alterar(agenda);
					inicializar();

				} catch (Exception ex) {
					ex.printStackTrace();
					FacesUtil.addErrorMessage(ex.getMessage());
				}
				break;

			}

		}

	}

	public void salvar() {
		if (agenda.getId() == null) {

			if (agenda.getInicio().getTime() <= agenda.getFim().getTime()) {

				try {

					Long selctIdAluno = cadAgenda.getId();

					agenda.setCadAgenda(cadAgendaService.retornarCadAgendaPorID(selctIdAluno));

					agendaService.incluir(agenda);
					inicializar();
					FacesUtil.addSuccessMessage("Horário reservado com sucesso !");

				} catch (Exception ex) {
					ex.printStackTrace();
					FacesUtil.addErrorMessage(ex.getMessage());
				}

				agenda = new Agenda();

			} else {
				FacesUtil.addSuccessMessage("Horário reservado com sucesso !");
			}

		} else {

			try {

				agendaService.alterar(agenda);
				inicializar();
			} catch (Exception ex) {
				ex.printStackTrace();
				FacesUtil.addErrorMessage(ex.getMessage());
			}
		}
	}

	public void excluir() {

		try {
			agendaService.excluir(agendaSelecionada);
			this.listarAgendas.remove(agendaSelecionada);
			FacesUtil.addSuccessMessage("Evento " + agendaSelecionada.getDescricao() + " Excluída com sucesso.");
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage(ex.getMessage());
		}

	}

	private final Date now = new Date();

	public Date getNow() {
		return now;
	}

	public void gerarRelatorio() throws SQLException, JRException {

		Long selctIdAluno = cadAgenda.getId();

		HashMap<String, Object> parametros = new HashMap<String, Object>();
		listarHorarios = cadAgendaService.retornoAlunoMapa(selctIdAluno);
		relatorioService.gerarMapaAluno(listarHorarios, parametros, "mapaAluno");

	}

	public List<Agenda> getListarAgendas() {
		return listarAgendas;
	}

	public void setListarAgendas(List<Agenda> listarAgendas) {
		this.listarAgendas = listarAgendas;
	}

	public ScheduleModel getAgendaCalendario() {
		return agendaCalendario;
	}

	public void setAgendaCalendario(ScheduleModel agendaCalendario) {
		this.agendaCalendario = agendaCalendario;
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

	public CadAgenda getCadAgenda() {
		return cadAgenda;
	}

	public void setCadAgenda(CadAgenda cadAgenda) {
		this.cadAgenda = cadAgenda;
	}

	public List<Agenda> getListarHorarios() {
		return listarHorarios;
	}

	public void setListarHorarios(List<Agenda> listarHorarios) {
		this.listarHorarios = listarHorarios;
	}

}
