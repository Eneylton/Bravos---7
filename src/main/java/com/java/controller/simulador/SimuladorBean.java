package com.java.controller.simulador;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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

import com.java.modelo.Agenda2;
import com.java.modelo.Aluno;
import com.java.service.Agenda2Service;
import com.java.service.AlunoService;
import com.java.util.jsf.FacesUtil;

import net.sf.jasperreports.engine.JRException;

@Named
@ViewScoped
public class SimuladorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Agenda2Service agenda2Service;

	@Inject
	private AlunoService alunoService;

	private List<Agenda2> listarAgenda2s = new ArrayList<>();

	private ScheduleModel agenda2Calendario;

	private Agenda2 agenda2 = new Agenda2();

	private Aluno aluno = new Aluno();

	private Agenda2 agenda2Selecionada;

	@PostConstruct
	public void inicializar() throws ParseException {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT-3"));

		agenda2Calendario = new DefaultScheduleModel();

		try {
			listarAgenda2s = agenda2Service.listarTodos();
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage(ex.getMessage());
		}
		
		
		
		for (Agenda2 ev1 : listarAgenda2s) {

			DefaultScheduleEvent evt = new DefaultScheduleEvent();
			evt.setData(ev1.getId());
			evt.setTitle(ev1.getAluno().getNome());
			evt.setStartDate(ev1.getInicio());
			evt.setEndDate(ev1.getFim());

			evt.setDescription(ev1.getDescricao());
			evt.setEditable(true);

			if (ev1.isStatus() == true) {
				evt.setStyleClass("cor1");
			} else if (ev1.isStatus() == false) {
				evt.setStyleClass("cor2");
			}

			agenda2Calendario.addEvent(evt);

		}

	}

	public void quandoSelecionado(SelectEvent selectEvent) {
		ScheduleEvent event = (ScheduleEvent) selectEvent.getObject();

		for (Agenda2 ev2 : listarAgenda2s) {
			if (ev2.getId() == (Long) event.getData()) {
				agenda2 = ev2;
				break;
			}
		}
	}

	public void quandoNovo(SelectEvent selectEvent) {
		ScheduleEvent event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(),
				(Date) selectEvent.getObject());
		agenda2 = new Agenda2();
		agenda2.setInicio(new java.sql.Date(event.getStartDate().getTime()));
		agenda2.setFim(new java.sql.Date(event.getEndDate().getTime()));
	}

	public void quandoMovido(ScheduleEntryMoveEvent movi) {
		for (Agenda2 ag : listarAgenda2s) {

			if (ag.getId() == (Long) movi.getScheduleEvent().getData()) {

				agenda2 = ag;

				try {

					agenda2Service.alterar(agenda2);
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

		for (Agenda2 ag : listarAgenda2s) {

			if (ag.getId() == (Long) movi.getScheduleEvent().getData()) {

				agenda2 = ag;

				try {

					agenda2Service.alterar(agenda2);
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
		if (agenda2.getId() == null) {

			if (agenda2.getInicio().getTime() <= agenda2.getFim().getTime()) {

				try {

					Long selctIdAluno = aluno.getId();

					agenda2.setAluno(alunoService.retornarAlunoPorID(selctIdAluno));

					agenda2Service.incluir(agenda2);
					inicializar();
					FacesUtil.addSuccessMessage("Aluno Agendado com sucesso!");

				} catch (Exception ex) {
					ex.printStackTrace();
					FacesUtil.addErrorMessage(ex.getMessage());
				}

				agenda2 = new Agenda2();

			} else {
				FacesUtil.addSuccessMessage("Agenda2 salvo com sucesso!");
			}

		} else {

			try {

				agenda2Service.alterar(agenda2);
				inicializar();
			} catch (Exception ex) {
				ex.printStackTrace();
				FacesUtil.addErrorMessage(ex.getMessage());
			}
		}
	}

	public void excluir() {

		try {
			agenda2Service.excluir(agenda2Selecionada);
			this.listarAgenda2s.remove(agenda2Selecionada);
			FacesUtil.addSuccessMessage("Evento " + agenda2Selecionada.getDescricao() + " excluída com sucesso.");
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

		

	}

	public AlunoService getAlunoService() {
		return alunoService;
	}

	public void setAlunoService(AlunoService alunoService) {
		this.alunoService = alunoService;
	}

	public List<Agenda2> getListarAgenda2s() {
		return listarAgenda2s;
	}

	public void setListarAgenda2s(List<Agenda2> listarAgenda2s) {
		this.listarAgenda2s = listarAgenda2s;
	}

	public ScheduleModel getAgenda2Calendario() {
		return agenda2Calendario;
	}

	public void setAgenda2Calendario(ScheduleModel agenda2Calendario) {
		this.agenda2Calendario = agenda2Calendario;
	}

	public Agenda2 getAgenda2() {
		return agenda2;
	}

	public void setAgenda2(Agenda2 agenda2) {
		this.agenda2 = agenda2;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Agenda2 getAgenda2Selecionada() {
		return agenda2Selecionada;
	}

	public void setAgenda2Selecionada(Agenda2 agenda2Selecionada) {
		this.agenda2Selecionada = agenda2Selecionada;
	}

	
}
