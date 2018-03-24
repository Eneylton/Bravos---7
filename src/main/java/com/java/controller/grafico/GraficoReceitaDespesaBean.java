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
public class GraficoReceitaDespesaBean implements Serializable {
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
		graficoBarra.setLegendPosition("ne");

		Axis yAxis = graficoBarra.getAxis(AxisType.Y);
		yAxis.setMin(0);
		yAxis.setMax(30000);
		yAxis.setTickFormat("R$ %.2f");

		Axis xAxis = graficoBarra.getAxis(AxisType.X);
		xAxis.setMin(1);
		xAxis.setMax(31);
		xAxis.setTickFormat("R$ %.2f");
		xAxis.setTickCount(31);
		xAxis.setTickAngle(-50);
		graficoBarra.setExtender("chartExtender");
		graficoBarra.setSeriesColors("1592d0,d00c03");

	}

	private BarChartModel initBarModel() throws SQLException {
		BarChartModel model = new BarChartModel();
		ChartSeries receita = new ChartSeries();
		
		ChartSeries despesa = new ChartSeries();
			
		
		List<Financeiro> totalReceita = graficoDAO.getReceitaMes();
       
		for (Financeiro finan : totalReceita) {
			receita.setLabel("Receitas");
			receita.set(finan.getMes(), finan.getTotal());

		}
		
		List<Financeiro> totalDespesas = graficoDAO.getDespesaMes();

		for (Financeiro finan : totalDespesas) {
			despesa.setLabel("Despesas");	
			despesa.set(finan.getMes(), finan.getTotal());

		}
		
		model.addSeries(receita);
		model.addSeries(despesa);

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
