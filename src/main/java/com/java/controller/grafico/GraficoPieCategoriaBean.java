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
import com.java.modelo.Rank2;
import com.java.util.FacesMessages;
import com.java.util.jsf.FacesUtil;

@Named
@ViewScoped
public class GraficoPieCategoriaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private GraficoDAO graficoDAO;

	@Inject
	private FacesMessages messages;

	private List<Rank2> listarRank2 = new ArrayList<>();

	private Rank2 rank2;

	private PieChartModel pieModelTipo;

	@PostConstruct
	public void init() {

		try {

			listarRank2 = graficoDAO.totalRank2();

			createPieModelTipo();

		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage("Erro no processamento - Erro: " + ex.getMessage());
		}

	}

	private void createPieModelTipo() throws SQLException {
		pieModelTipo = new PieChartModel();

		for (Rank2 Rank2 : listarRank2) {

			pieModelTipo.set(Rank2.getCat() + " Total (" + Rank2.getTotal() + ")", Rank2.getTotal());

		}

		pieModelTipo.setLegendPosition("w");
		pieModelTipo.setFill(true);

		pieModelTipo.setShowDataLabels(true);
		pieModelTipo.setDiameter(200);
		pieModelTipo.setMouseoverHighlight(true);
		pieModelTipo.setShadow(true);
		pieModelTipo.setSeriesColors("B4CDCD,00C5CD,63B8FF,C1CDCD,FF7256,FFA54F,FFD700,9ACD32,00FF7F,B0C4DE,FFA500,87CEEB,FA8072");

	}

	public FacesMessages getMessages() {
		return messages;
	}

	public void setMessages(FacesMessages messages) {
		this.messages = messages;
	}

	public List<Rank2> getListarRank2() {
		return listarRank2;
	}

	public void setListarRank2(List<Rank2> listarRank2) {
		this.listarRank2 = listarRank2;
	}

	public Rank2 getRank2() {
		return rank2;
	}

	public void setRank2(Rank2 rank2) {
		this.rank2 = rank2;
	}

	public PieChartModel getPieModelTipo() {
		return pieModelTipo;
	}

	public void setPieModelTipo(PieChartModel pieModelTipo) {
		this.pieModelTipo = pieModelTipo;
	}

}
