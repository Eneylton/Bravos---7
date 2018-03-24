package com.java.controller.grafico;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.PieChartModel;

import com.java.dao.GraficoDAO;
import com.java.modelo.StatusGrafico;
import com.java.util.FacesMessages;
import com.java.util.jsf.FacesUtil;

@Named
@ViewScoped
public class GraficoPieStatusBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private GraficoDAO graficoDAO;

	@Inject
	private FacesMessages messages;

	private List<StatusGrafico> listarStatusGrafico = new ArrayList<>();

	private StatusGrafico statusGrafico;

	private PieChartModel pieModelTipo;

	@PostConstruct
	public void init() {

		try {

			listarStatusGrafico = graficoDAO.totalStatus();

			createPieModelTipo();

		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage("Erro no processamento - Erro: " + ex.getMessage());
		}

	}

	private void createPieModelTipo() throws SQLException {
		pieModelTipo = new PieChartModel();

		for (StatusGrafico StatusGrafico : listarStatusGrafico) {

			pieModelTipo.set(StatusGrafico.getStatus() + " - (" + StatusGrafico.getTotal() + ")",
					StatusGrafico.getTotal());

		}

		pieModelTipo.setLegendPosition("w");
		pieModelTipo.setTitle("Total de Contas");
		pieModelTipo.setFill(true);

		pieModelTipo.setShowDataLabels(true);
		pieModelTipo.setDiameter(150);
		pieModelTipo.setMouseoverHighlight(true);
		pieModelTipo.setShadow(true);
		pieModelTipo.setSeriesColors("b7d3ea,d7ec23");

	}

	public FacesMessages getMessages() {
		return messages;
	}

	public void setMessages(FacesMessages messages) {
		this.messages = messages;
	}

	public List<StatusGrafico> getListarStatusGrafico() {
		return listarStatusGrafico;
	}

	public void setListarStatusGrafico(List<StatusGrafico> listarStatusGrafico) {
		this.listarStatusGrafico = listarStatusGrafico;
	}

	public StatusGrafico getStatusGrafico() {
		return statusGrafico;
	}

	public void setStatusGrafico(StatusGrafico statusGrafico) {
		this.statusGrafico = statusGrafico;
	}

	public PieChartModel getPieModelTipo() {
		return pieModelTipo;
	}

	public void setPieModelTipo(PieChartModel pieModelTipo) {
		this.pieModelTipo = pieModelTipo;
	}

}
