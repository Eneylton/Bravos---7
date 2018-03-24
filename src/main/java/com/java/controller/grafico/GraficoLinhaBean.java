package com.java.controller.grafico;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import com.java.dao.GraficoDAO;
import com.java.modelo.Financeiro;
import com.java.modelo.TotalCadastroDia;
import com.java.util.FacesMessages;
import com.java.util.Session;
import com.java.util.jsf.FacesUtil;

@Named
@ViewScoped
public class GraficoLinhaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private GraficoDAO graficoDAO;

	@Inject
	private FacesMessages messages;

	private List<TotalCadastroDia> listarFinanceiro = new ArrayList<>();

	private Financeiro financeiro;

	private LineChartModel graficoLinha;

	private LineChartModel graficoLinha2;

	@PostConstruct
	public void init() {

		try {

			createAnimatedModels();

		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage("Erro no processamento - Erro: " + ex.getMessage());
		}

	}

	private void createAnimatedModels() throws SQLException {

		graficoLinha = initLinearModel();
	

		graficoLinha.setAnimate(true);
		graficoLinha.setLegendPosition("ne");
		
		Axis yAxis = graficoLinha.getAxis(AxisType.Y);
		
		yAxis.setMin(0);
		yAxis.setMax(50);
		yAxis.setLabel("Qdt Atendimento");
		yAxis.setTickFormat("%d");
		
		
		Axis xAxis = graficoLinha.getAxis(AxisType.X);
		xAxis.setMin(0);
		xAxis.setMax(31);
		xAxis.setTickCount(31);
		xAxis.setLabel("Dia");
		xAxis.setTickFormat("%d");
	}

	private LineChartModel initLinearModel() throws SQLException {

		LineChartModel model = new LineChartModel();
		model.setShowPointLabels(true);

		List<TotalCadastroDia> totalCategoria = graficoDAO.getTotalCadastroDia();

		LineChartSeries series1 = new LineChartSeries();

		LineChartSeries series2 = new LineChartSeries();

		for (TotalCadastroDia inscritosDia : totalCategoria) {
			
			series1.setFill(true);
			series1.setLabel("Total Geral");

			series1.set(inscritosDia.getDia(), inscritosDia.getTotal());

		}

		Long idUsuario = Session.retornaIdUsuarioLogado();

		List<TotalCadastroDia> totalCategoria2 = graficoDAO.getTotalCadastroDiaUser(idUsuario);

		for (TotalCadastroDia inscritosDia : totalCategoria2) {
			series2.setFill(true);
			series2.setLabel("Atendente");

			series2.set(inscritosDia.getDia(), inscritosDia.getTotal());

		}

		model.addSeries(series1);
		model.addSeries(series2);

		return model;

	}

	public FacesMessages getMessages() {
		return messages;
	}

	public List<TotalCadastroDia> getListarFinanceiro() {
		return listarFinanceiro;
	}

	public void setListarFinanceiro(List<TotalCadastroDia> listarFinanceiro) {
		this.listarFinanceiro = listarFinanceiro;
	}

	public Financeiro getFinanceiro() {
		return financeiro;
	}

	public void setFinanceiro(Financeiro financeiro) {
		this.financeiro = financeiro;
	}

	public LineChartModel getGraficoLinha() {
		return graficoLinha;
	}

	public void setGraficoLinha(LineChartModel graficoLinha) {
		this.graficoLinha = graficoLinha;
	}

	public void setMessages(FacesMessages messages) {
		this.messages = messages;
	}

	public LineChartModel getGraficoLinha2() {
		return graficoLinha2;
	}

	public void setGraficoLinha2(LineChartModel graficoLinha2) {
		this.graficoLinha2 = graficoLinha2;
	}

}
