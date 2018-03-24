package com.java.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.primefaces.context.RequestContext;

import com.java.modelo.Agenda;
import com.java.modelo.Aluno;
import com.java.modelo.AutoEscola;
import com.java.modelo.CrossTable;
import com.java.modelo.Financeiro;
import com.java.util.Constantes;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioService implements Serializable {

	private static final long serialVersionUID = 1L;

	public void gerarRelatorio(List<Financeiro> consulta, HashMap<String, Object> parametros, String relatorio)
			throws JRException {

		String nomeArquivo = "financeiro" + ".pdf";

		String caminhoFisicoReport = Constantes.CAMINHO_FISICO_BASE_WEBAPP + "resources/relatorios/" + relatorio
				+ ".jrxml";

		String caminhoExpPDF = Constantes.CAMINHO_FISICO_BASE_WEBAPP + "tmp/relatorio/" + nomeArquivo;

		String urlExpPDF = Constantes.URL_PDF + "relatorio/" + nomeArquivo;
		
		

		JasperReport report = JasperCompileManager.compileReport(caminhoFisicoReport);

		JasperPrint print = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(consulta));

		JasperExportManager.exportReportToPdfFile(print, caminhoExpPDF);

		RequestContext.getCurrentInstance().execute("window.open('" + urlExpPDF + "');");

	}
	
	
	public void gerarRelatorioCaixa(List<Financeiro> consulta, HashMap<String, Object> parametros, String relatorio)
			throws JRException {

		String nomeArquivo = "caixa" + ".pdf";

		String caminhoFisicoReport = Constantes.CAMINHO_FISICO_BASE_WEBAPP + "resources/relatorios/" + relatorio
				+ ".jrxml";

		String caminhoExpPDF = Constantes.CAMINHO_FISICO_BASE_WEBAPP + "tmp/relatorio/" + nomeArquivo;

		String urlExpPDF = Constantes.URL_PDF + "relatorio/" + nomeArquivo;

		JasperReport report = JasperCompileManager.compileReport(caminhoFisicoReport);

		JasperPrint print = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(consulta));

		JasperExportManager.exportReportToPdfFile(print, caminhoExpPDF);

		RequestContext.getCurrentInstance().execute("window.open('" + urlExpPDF + "');");

	}
	
	
	public void gerarRecibo(List<Financeiro> consulta, HashMap<String, Object> parametros, String relatorio)
			throws JRException {

		String nomeArquivo = "recibo" + ".pdf";

		String caminhoFisicoReport = Constantes.CAMINHO_FISICO_BASE_WEBAPP + "resources/relatorios/" + relatorio
				+ ".jrxml";

		String caminhoExpPDF = Constantes.CAMINHO_FISICO_BASE_WEBAPP + "tmp/relatorio/" + nomeArquivo;

		String urlExpPDF = Constantes.URL_PDF + "relatorio/" + nomeArquivo;

		JasperReport report = JasperCompileManager.compileReport(caminhoFisicoReport);

		JasperPrint print = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(consulta));

		JasperExportManager.exportReportToPdfFile(print, caminhoExpPDF);

		RequestContext.getCurrentInstance().execute("window.open('" + urlExpPDF + "');");

	}
	
	
	
	

	public void gerarRelatorioPerido(List<Financeiro> consulta, HashMap<String, Object> parametros, String relatorio)
			throws JRException {

		String nomeArquivo = "periodo" + ".pdf";

		String caminhoFisicoReport = Constantes.CAMINHO_FISICO_BASE_WEBAPP + "resources/relatorios/" + relatorio
				+ ".jrxml";

		String caminhoExpPDF = Constantes.CAMINHO_FISICO_BASE_WEBAPP + "tmp/relatorio/" + nomeArquivo;

		String urlExpPDF = Constantes.URL_PDF + "relatorio/" + nomeArquivo;

		JasperReport report = JasperCompileManager.compileReport(caminhoFisicoReport);

		JasperPrint print = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(consulta));

		JasperExportManager.exportReportToPdfFile(print, caminhoExpPDF);

		RequestContext.getCurrentInstance().execute("window.open('" + urlExpPDF + "');");

	}
	
	public void gerarReciboMov(List<Financeiro> consulta, HashMap<String, Object> parametros, String relatorio)
			throws JRException {

		String nomeArquivo = "reciboMov" + ".pdf";

		String caminhoFisicoReport = Constantes.CAMINHO_FISICO_BASE_WEBAPP + "resources/relatorios/" + relatorio
				+ ".jrxml";

		String caminhoExpPDF = Constantes.CAMINHO_FISICO_BASE_WEBAPP + "tmp/relatorio/" + nomeArquivo;

		String urlExpPDF = Constantes.URL_PDF + "relatorio/" + nomeArquivo;

		JasperReport report = JasperCompileManager.compileReport(caminhoFisicoReport);

		JasperPrint print = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(consulta));

		JasperExportManager.exportReportToPdfFile(print, caminhoExpPDF);

		RequestContext.getCurrentInstance().execute("window.open('" + urlExpPDF + "');");

	}
	

	public void gerarRelatorioReceita(List<CrossTable> consulta, HashMap<String, Object> parametros, String relatorio)
			throws JRException {

		String nomeArquivo = "receita" + ".pdf";

		String caminhoFisicoReport = Constantes.CAMINHO_FISICO_BASE_WEBAPP + "resources/relatorios/" + relatorio
				+ ".jrxml";

		String caminhoExpPDF = Constantes.CAMINHO_FISICO_BASE_WEBAPP + "tmp/relatorio/" + nomeArquivo;

		String urlExpPDF = Constantes.URL_PDF + "relatorio/" + nomeArquivo;

		JasperReport report = JasperCompileManager.compileReport(caminhoFisicoReport);

		JasperPrint print = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(consulta));

		JasperExportManager.exportReportToPdfFile(print, caminhoExpPDF);

		RequestContext.getCurrentInstance().execute("window.open('" + urlExpPDF + "');");

	}
	
	
	public void gerarRelatorioDespesa(List<CrossTable> consulta, HashMap<String, Object> parametros, String relatorio)
			throws JRException {

		String nomeArquivo = "despesa" + ".pdf";

		String caminhoFisicoReport = Constantes.CAMINHO_FISICO_BASE_WEBAPP + "resources/relatorios/" + relatorio
				+ ".jrxml";

		String caminhoExpPDF = Constantes.CAMINHO_FISICO_BASE_WEBAPP + "tmp/relatorio/" + nomeArquivo;

		String urlExpPDF = Constantes.URL_PDF + "relatorio/" + nomeArquivo;

		JasperReport report = JasperCompileManager.compileReport(caminhoFisicoReport);

		JasperPrint print = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(consulta));

		JasperExportManager.exportReportToPdfFile(print, caminhoExpPDF);

		RequestContext.getCurrentInstance().execute("window.open('" + urlExpPDF + "');");

	}
	
	public void gerarRelatorioReceitaBanco(List<CrossTable> consulta, HashMap<String, Object> parametros, String relatorio)
			throws JRException {

		String nomeArquivo = "receitaBanco" + ".pdf";

		String caminhoFisicoReport = Constantes.CAMINHO_FISICO_BASE_WEBAPP + "resources/relatorios/" + relatorio
				+ ".jrxml";

		String caminhoExpPDF = Constantes.CAMINHO_FISICO_BASE_WEBAPP + "tmp/relatorio/" + nomeArquivo;

		String urlExpPDF = Constantes.URL_PDF + "relatorio/" + nomeArquivo;

		JasperReport report = JasperCompileManager.compileReport(caminhoFisicoReport);

		JasperPrint print = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(consulta));

		JasperExportManager.exportReportToPdfFile(print, caminhoExpPDF);

		RequestContext.getCurrentInstance().execute("window.open('" + urlExpPDF + "');");

	}
	
	public void gerarRelatorioDespesaBanco(List<CrossTable> consulta, HashMap<String, Object> parametros, String relatorio)
			throws JRException {

		String nomeArquivo = "despesaBanco" + ".pdf";

		String caminhoFisicoReport = Constantes.CAMINHO_FISICO_BASE_WEBAPP + "resources/relatorios/" + relatorio
				+ ".jrxml";

		String caminhoExpPDF = Constantes.CAMINHO_FISICO_BASE_WEBAPP + "tmp/relatorio/" + nomeArquivo;

		String urlExpPDF = Constantes.URL_PDF + "relatorio/" + nomeArquivo;

		JasperReport report = JasperCompileManager.compileReport(caminhoFisicoReport);

		JasperPrint print = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(consulta));

		JasperExportManager.exportReportToPdfFile(print, caminhoExpPDF);

		RequestContext.getCurrentInstance().execute("window.open('" + urlExpPDF + "');");

	}
	
	
	public void gerarRelatorioAreceber(List<Financeiro> consulta, HashMap<String, Object> parametros, String relatorio)
			throws JRException {

		String nomeArquivo = "receber" + ".pdf";

		String caminhoFisicoReport = Constantes.CAMINHO_FISICO_BASE_WEBAPP + "resources/relatorios/" + relatorio
				+ ".jrxml";

		String caminhoExpPDF = Constantes.CAMINHO_FISICO_BASE_WEBAPP + "tmp/relatorio/" + nomeArquivo;

		String urlExpPDF = Constantes.URL_PDF + "relatorio/" + nomeArquivo;

		JasperReport report = JasperCompileManager.compileReport(caminhoFisicoReport);

		JasperPrint print = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(consulta));

		JasperExportManager.exportReportToPdfFile(print, caminhoExpPDF);

		RequestContext.getCurrentInstance().execute("window.open('" + urlExpPDF + "');");

	}
	
	
	public void gerarRelatorioRecebido(List<Financeiro> consulta, HashMap<String, Object> parametros, String relatorio)
			throws JRException {

		String nomeArquivo = "recebido" + ".pdf";

		String caminhoFisicoReport = Constantes.CAMINHO_FISICO_BASE_WEBAPP + "resources/relatorios/" + relatorio
				+ ".jrxml";

		String caminhoExpPDF = Constantes.CAMINHO_FISICO_BASE_WEBAPP + "tmp/relatorio/" + nomeArquivo;

		String urlExpPDF = Constantes.URL_PDF + "relatorio/" + nomeArquivo;

		JasperReport report = JasperCompileManager.compileReport(caminhoFisicoReport);

		JasperPrint print = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(consulta));

		JasperExportManager.exportReportToPdfFile(print, caminhoExpPDF);

		RequestContext.getCurrentInstance().execute("window.open('" + urlExpPDF + "');");

	}

	public void gerarRelatorioApagar(List<Financeiro> consulta, HashMap<String, Object> parametros, String relatorio)
			throws JRException {

		String nomeArquivo = "apagar" + ".pdf";

		String caminhoFisicoReport = Constantes.CAMINHO_FISICO_BASE_WEBAPP + "resources/relatorios/" + relatorio
				+ ".jrxml";

		String caminhoExpPDF = Constantes.CAMINHO_FISICO_BASE_WEBAPP + "tmp/relatorio/" + nomeArquivo;

		String urlExpPDF = Constantes.URL_PDF + "relatorio/" + nomeArquivo;

		JasperReport report = JasperCompileManager.compileReport(caminhoFisicoReport);

		JasperPrint print = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(consulta));

		JasperExportManager.exportReportToPdfFile(print, caminhoExpPDF);

		RequestContext.getCurrentInstance().execute("window.open('" + urlExpPDF + "');");

	}

	
	public void gerarRelatorioPagas(List<Financeiro> consulta, HashMap<String, Object> parametros, String relatorio)
			throws JRException {

		String nomeArquivo = "pagas" + ".pdf";

		String caminhoFisicoReport = Constantes.CAMINHO_FISICO_BASE_WEBAPP + "resources/relatorios/" + relatorio
				+ ".jrxml";

		String caminhoExpPDF = Constantes.CAMINHO_FISICO_BASE_WEBAPP + "tmp/relatorio/" + nomeArquivo;

		String urlExpPDF = Constantes.URL_PDF + "relatorio/" + nomeArquivo;

		JasperReport report = JasperCompileManager.compileReport(caminhoFisicoReport);

		JasperPrint print = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(consulta));

		JasperExportManager.exportReportToPdfFile(print, caminhoExpPDF);

		RequestContext.getCurrentInstance().execute("window.open('" + urlExpPDF + "');");

	}
	
	public void gerarRelatorioAtrasado(List<Financeiro> consulta, HashMap<String, Object> parametros, String relatorio)
			throws JRException {

		String nomeArquivo = "atrasado" + ".pdf";

		String caminhoFisicoReport = Constantes.CAMINHO_FISICO_BASE_WEBAPP + "resources/relatorios/" + relatorio
				+ ".jrxml";

		String caminhoExpPDF = Constantes.CAMINHO_FISICO_BASE_WEBAPP + "tmp/relatorio/" + nomeArquivo;

		String urlExpPDF = Constantes.URL_PDF + "relatorio/" + nomeArquivo;

		JasperReport report = JasperCompileManager.compileReport(caminhoFisicoReport);

		JasperPrint print = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(consulta));

		JasperExportManager.exportReportToPdfFile(print, caminhoExpPDF);

		RequestContext.getCurrentInstance().execute("window.open('" + urlExpPDF + "');");

	}
	
	
	public void gerarRelatorioReceitaMov(List<Financeiro> consulta, HashMap<String, Object> parametros, String relatorio)
			throws JRException {

		String nomeArquivo = "receitaMov" + ".pdf";

		String caminhoFisicoReport = Constantes.CAMINHO_FISICO_BASE_WEBAPP + "resources/relatorios/" + relatorio
				+ ".jrxml";

		String caminhoExpPDF = Constantes.CAMINHO_FISICO_BASE_WEBAPP + "tmp/relatorio/" + nomeArquivo;

		String urlExpPDF = Constantes.URL_PDF + "relatorio/" + nomeArquivo;

		JasperReport report = JasperCompileManager.compileReport(caminhoFisicoReport);

		JasperPrint print = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(consulta));

		JasperExportManager.exportReportToPdfFile(print, caminhoExpPDF);

		RequestContext.getCurrentInstance().execute("window.open('" + urlExpPDF + "');");

	}
	
	
	public void gerarMapaAluno(List<Agenda> consulta, HashMap<String, Object> parametros, String relatorio)
			throws JRException {

		String nomeArquivo = "mapaAluno" + ".pdf";

		String caminhoFisicoReport = Constantes.CAMINHO_FISICO_BASE_WEBAPP + "resources/relatorios/" + relatorio
				+ ".jrxml";

		String caminhoExpPDF = Constantes.CAMINHO_FISICO_BASE_WEBAPP + "tmp/relatorio/" + nomeArquivo;

		String urlExpPDF = Constantes.URL_PDF + "relatorio/" + nomeArquivo;

		JasperReport report = JasperCompileManager.compileReport(caminhoFisicoReport);

		JasperPrint print = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(consulta));

		JasperExportManager.exportReportToPdfFile(print, caminhoExpPDF);

		RequestContext.getCurrentInstance().execute("window.open('" + urlExpPDF + "');");

	}
	
	public void gerarMapaInstrutor(List<Agenda> consulta, HashMap<String, Object> parametros, String relatorio)
			throws JRException {

		String nomeArquivo = "mapaInstrutor" + ".pdf";

		String caminhoFisicoReport = Constantes.CAMINHO_FISICO_BASE_WEBAPP + "resources/relatorios/" + relatorio
				+ ".jrxml";

		String caminhoExpPDF = Constantes.CAMINHO_FISICO_BASE_WEBAPP + "tmp/relatorio/" + nomeArquivo;

		String urlExpPDF = Constantes.URL_PDF + "relatorio/" + nomeArquivo;

		JasperReport report = JasperCompileManager.compileReport(caminhoFisicoReport);

		JasperPrint print = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(consulta));

		JasperExportManager.exportReportToPdfFile(print, caminhoExpPDF);

		RequestContext.getCurrentInstance().execute("window.open('" + urlExpPDF + "');");

	}
	
	
	public void gerarListaPresenca(List<Aluno> consulta, HashMap<String, Object> parametros, String relatorio) throws JRException{
		
		String nomeArquivo = "presenca" +  ".pdf";
		
		String caminhoFisicoReport = Constantes.CAMINHO_FISICO_BASE_WEBAPP +  "resources/relatorios/" + relatorio + ".jrxml";
		
		String caminhoExpPDF = Constantes.CAMINHO_FISICO_BASE_WEBAPP + "tmp/relatorio/" + nomeArquivo;
		
		String urlExpPDF = Constantes.URL_PDF + "relatorio/" + nomeArquivo;
		
		JasperReport report = JasperCompileManager.compileReport(caminhoFisicoReport);
		
		JasperPrint print = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(consulta));
		
		JasperExportManager.exportReportToPdfFile(print, caminhoExpPDF);
		
		RequestContext.getCurrentInstance().execute("window.open('" + urlExpPDF + "');");
		
	}


	public void gerarDespesas(List<Financeiro> consulta, HashMap<String, Object> parametros, String relatorio)
			throws JRException {

		String nomeArquivo = "despesas" + ".pdf";

		String caminhoFisicoReport = Constantes.CAMINHO_FISICO_BASE_WEBAPP + "resources/relatorios/" + relatorio
				+ ".jrxml";

		String caminhoExpPDF = Constantes.CAMINHO_FISICO_BASE_WEBAPP + "tmp/relatorio/" + nomeArquivo;

		String urlExpPDF = Constantes.URL_PDF + "relatorio/" + nomeArquivo;

		JasperReport report = JasperCompileManager.compileReport(caminhoFisicoReport);

		JasperPrint print = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(consulta));

		JasperExportManager.exportReportToPdfFile(print, caminhoExpPDF);

		RequestContext.getCurrentInstance().execute("window.open('" + urlExpPDF + "');");

	}
	
	
	public void gerarRelatorioAutoEscola(List<AutoEscola> consulta, HashMap<String, Object> parametros, String relatorio)
			throws JRException {

		String nomeArquivo = "autoEscola" + ".pdf";

		String caminhoFisicoReport = Constantes.CAMINHO_FISICO_BASE_WEBAPP + "resources/relatorios/" + relatorio
				+ ".jrxml";

		String caminhoExpPDF = Constantes.CAMINHO_FISICO_BASE_WEBAPP + "tmp/relatorio/" + nomeArquivo;

		String urlExpPDF = Constantes.URL_PDF + "relatorio/" + nomeArquivo;

		JasperReport report = JasperCompileManager.compileReport(caminhoFisicoReport);

		JasperPrint print = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(consulta));

		JasperExportManager.exportReportToPdfFile(print, caminhoExpPDF);

		RequestContext.getCurrentInstance().execute("window.open('" + urlExpPDF + "');");

	}
	
	

}
