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
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.java.dao.GraficoDAO;
import com.java.modelo.Financeiro;
import com.java.util.FacesMessages;
import com.java.util.jsf.FacesUtil;

@Named
@ViewScoped
public class GraficoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private GraficoDAO graficoDAO;

	@Inject
	private FacesMessages messages;

	private List<Financeiro> listarFinanceiro = new ArrayList<>();

	private Financeiro financeiro;

	private BarChartModel graficoBarra;

	@PostConstruct
	public void init() {

		try {
			
			listarFinanceiro = graficoDAO.getTotalMes();
			createAnimatedModels();

		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage("Erro no processamento - Erro: " + ex.getMessage());
		}

	}

	private void createAnimatedModels() throws SQLException {

		graficoBarra = initBarModel();
		graficoBarra.setAnimate(true);
		graficoBarra.setShowPointLabels(true);

		Axis yAxis = graficoBarra.getAxis(AxisType.Y);
		yAxis.setMin(0);
		yAxis.setMax(50000);
		yAxis.setTickFormat("R$ %.2f");

		Axis xAxis = graficoBarra.getAxis(AxisType.X);
		xAxis.setMin(1);
		xAxis.setMax(31);
		xAxis.setTickFormat("R$ %.2f");
		xAxis.setTickCount(31);
		xAxis.setTickAngle(-50);
		graficoBarra.setSeriesColors("1592d0,bbd9e8,FFA500,48D1CC,7CFC00,ADD8E6,48D1CC,708090,4876FF,00F5FF,00FF00,FF8C69");
		
		graficoBarra.setExtender("chartExtender");

	}

	private BarChartModel initBarModel() throws SQLException {
		BarChartModel model = new BarChartModel();
		ChartSeries barra = new ChartSeries();
		
		List<Financeiro> totalCategoria = graficoDAO.getTotalMes();

		for (Financeiro finan : totalCategoria) {

			barra.set(finan.getMes(), finan.getTotal());

		}

		model.addSeries(barra);

		return model;

	}

	public FacesMessages getMessages() {
		return messages;
	}

	public void setMessages(FacesMessages messages) {
		this.messages = messages;
	}

	public List<Financeiro> getListarFinanceiro() {
		return listarFinanceiro;
	}

	public void setListarFinanceiro(List<Financeiro> listarFinanceiro) {
		this.listarFinanceiro = listarFinanceiro;
	}

	public Financeiro getFinanceiro() {
		return financeiro;
	}

	public void setFinanceiro(Financeiro financeiro) {
		this.financeiro = financeiro;
	}

	public BarChartModel getGraficoBarra() {
		return graficoBarra;
	}

	public void setGraficoBarra(BarChartModel graficoBarra) {
		this.graficoBarra = graficoBarra;
	}

}
