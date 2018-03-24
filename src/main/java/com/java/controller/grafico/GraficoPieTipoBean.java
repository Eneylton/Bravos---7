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
import com.java.modelo.TipoGrafico;
import com.java.util.FacesMessages;
import com.java.util.jsf.FacesUtil;

@Named
@ViewScoped
public class GraficoPieTipoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private GraficoDAO graficoDAO;

	@Inject
	private FacesMessages messages;

	private List<TipoGrafico> listarTipoGrafico = new ArrayList<>();

	private TipoGrafico tipoGrafico;

	private PieChartModel pieModelTipo;

	@PostConstruct
	public void init() {

		try {

			listarTipoGrafico = graficoDAO.totalTipo();

			createPieModelTipo();

		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage("Erro no processamento - Erro: " + ex.getMessage());
		}

	}

	private void createPieModelTipo() throws SQLException {
		pieModelTipo = new PieChartModel();

		for (TipoGrafico tipoGrafico : listarTipoGrafico) {

			pieModelTipo.set(tipoGrafico.getTipo() + " - (" + tipoGrafico.getTotal() + ")", tipoGrafico.getTotal());

		}

		pieModelTipo.setLegendPosition("w");
		pieModelTipo.setTitle("Fluxo de Caixa");
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

	public List<TipoGrafico> getListarTipoGrafico() {
		return listarTipoGrafico;
	}

	public void setListarTipoGrafico(List<TipoGrafico> listarTipoGrafico) {
		this.listarTipoGrafico = listarTipoGrafico;
	}

	public TipoGrafico getTipoGrafico() {
		return tipoGrafico;
	}

	public void setTipoGrafico(TipoGrafico tipoGrafico) {
		this.tipoGrafico = tipoGrafico;
	}

	public PieChartModel getPieModelTipo() {
		return pieModelTipo;
	}

	public void setPieModelTipo(PieChartModel pieModelTipo) {
		this.pieModelTipo = pieModelTipo;
	}

}
