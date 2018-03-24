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
import com.java.modelo.Rank;
import com.java.util.FacesMessages;
import com.java.util.jsf.FacesUtil;

@Named
@ViewScoped
public class GraficoPieBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private GraficoDAO graficoDAO;

	@Inject
	private FacesMessages messages;

	private List<Rank> listarRank = new ArrayList<>();

	private Rank rank;

	private PieChartModel pieModelTipo;

	@PostConstruct
	public void init() {

		try {
			
			listarRank = graficoDAO.totalRank();

			createPieModelTipo();

		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage("Erro no processamento - Erro: " + ex.getMessage());
		}

	}

	private void createPieModelTipo() throws SQLException {
		pieModelTipo = new PieChartModel();

		for (Rank rank : listarRank) {

			pieModelTipo.set(rank.getNomeCompleto() + " Total (" + rank.getTotal() + ")", rank.getTotal());

		}

		pieModelTipo.setLegendPosition("w");
	
		pieModelTipo.setFill(true);

		pieModelTipo.setShowDataLabels(true);
		pieModelTipo.setDiameter(200);
		pieModelTipo.setMouseoverHighlight(true);
		pieModelTipo.setShadow(true);
		pieModelTipo.setSeriesColors("FF7256,FFA54F,FFD700,9ACD32,B4CDCD,00C5CD,63B8FF,C1CDCD,00FF7F,B0C4DE,FFA500,87CEEB,FA8072");

	}

	public PieChartModel getPieModelTipo() {
		return pieModelTipo;
	}

	public void setPieModelTipo(PieChartModel pieModelTipo) {
		this.pieModelTipo = pieModelTipo;
	}

	public FacesMessages getMessages() {
		return messages;
	}

	public void setMessages(FacesMessages messages) {
		this.messages = messages;
	}

	public List<Rank> getListarRank() {
		return listarRank;
	}

	public void setListarRank(List<Rank> listarRank) {
		this.listarRank = listarRank;
	}

	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

}
