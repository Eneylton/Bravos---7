package com.java.service;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.context.RequestContext;

import com.java.modelo.AutoEscola;
import com.java.modelo.Financeiro;
import com.java.modelo.Mapa2;
import com.java.util.Constantes;
import com.java.util.jsf.FacesUtil;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class Relatorio2Service implements Serializable {

	private static final long serialVersionUID = 1L;

	private HttpServletResponse response;
	private FacesContext context;
	private Connection con;

	public Relatorio2Service() {
		this.context = FacesContext.getCurrentInstance();
		this.response = (HttpServletResponse) this.context.getExternalContext().getResponse();
	}

	public void gerarRelatorio(List<Mapa2> consulta, HashMap<String, Object> parametros, String relatorio)
			throws JRException {

		String caminhoFisicoReport = Constantes.CAMINHO_FISICO_BASE_WEBAPP + "resources/relatorios/" + relatorio
				+ ".jrxml";

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		/*
		 * JRBeanCollectionDataSource datasrc = new
		 * JRBeanCollectionDataSource(lista);
		 */

		JasperReport report = JasperCompileManager.compileReport(caminhoFisicoReport);

		JasperPrint print = JasperFillManager.fillReport(report, parametros, getConexao());

		JasperExportManager.exportReportToPdfStream(print, baos);

		try {
			response.reset();

			response.setContentType("application/pdf");

			response.setContentLength(baos.size());

			response.setHeader("Content-disposition", "inline; filename=rel04.pdf");

			response.getOutputStream().write(baos.toByteArray());

			response.getOutputStream().flush();

			response.getOutputStream().close();

			context.renderResponse();

			context.responseComplete();

			closeConnection();

		} catch (Exception e) {
			FacesUtil.addErrorMessage("Erro no processamento - Erro: " + e.getMessage());
		}

	}

	public void gerarRelatorioAutoEscola(List<AutoEscola> consulta, HashMap<String, Object> parametros,
			String relatorio) throws JRException {

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

	public void gerarRelatorioMapa(List<Mapa2> consulta, HashMap<String, Object> parametros, String relatorio)
			throws JRException {

		String caminhoFisicoReport = Constantes.CAMINHO_FISICO_BASE_WEBAPP + "resources/relatorios/" + relatorio
				+ ".jrxml";

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		/*
		 * JRBeanCollectionDataSource datasrc = new
		 * JRBeanCollectionDataSource(lista);
		 */

		JasperReport report = JasperCompileManager.compileReport(caminhoFisicoReport);

		JasperPrint print = JasperFillManager.fillReport(report, parametros, getConexao());

		JasperExportManager.exportReportToPdfStream(print, baos);

		try {
			response.reset();

			response.setContentType("application/pdf");

			response.setContentLength(baos.size());

			response.setHeader("Content-disposition", "inline; filename=relatorio.pdf");

			response.getOutputStream().write(baos.toByteArray());

			response.getOutputStream().flush();

			response.getOutputStream().close();

			context.renderResponse();

			context.responseComplete();

			closeConnection();

		} catch (Exception e) {
			FacesUtil.addErrorMessage("Erro no processamento - Erro: " + e.getMessage());
		}

	}

	public void gerarRelatorioUsuarioAutoEscola(List<AutoEscola> consulta, HashMap<String, Object> parametros,
			String relatorio) throws JRException {

		String caminhoFisicoReport = Constantes.CAMINHO_FISICO_BASE_WEBAPP + "resources/relatorios/" + relatorio
				+ ".jrxml";

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		/*
		 * JRBeanCollectionDataSource datasrc = new
		 * JRBeanCollectionDataSource(lista);
		 */

		JasperReport report = JasperCompileManager.compileReport(caminhoFisicoReport);

		JasperPrint print = JasperFillManager.fillReport(report, parametros, getConexao());

		JasperExportManager.exportReportToPdfStream(print, baos);

		try {
			response.reset();

			response.setContentType("application/pdf");

			response.setContentLength(baos.size());

			response.setHeader("Content-disposition", "inline; filename=relatorio.pdf");

			response.getOutputStream().write(baos.toByteArray());

			response.getOutputStream().flush();

			response.getOutputStream().close();

			context.renderResponse();

			context.responseComplete();

			closeConnection();

		} catch (Exception e) {
			FacesUtil.addErrorMessage("Erro no processamento - Erro: " + e.getMessage());
		}

	}

	public void gerarEstatisticaAutoEscola(List<AutoEscola> consulta, HashMap<String, Object> parametros,
			String relatorio) throws JRException {

		String caminhoFisicoReport = Constantes.CAMINHO_FISICO_BASE_WEBAPP + "resources/relatorios/" + relatorio
				+ ".jrxml";

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		/*
		 * JRBeanCollectionDataSource datasrc = new
		 * JRBeanCollectionDataSource(lista);
		 */

		JasperReport report = JasperCompileManager.compileReport(caminhoFisicoReport);

		JasperPrint print = JasperFillManager.fillReport(report, parametros, getConexao());

		JasperExportManager.exportReportToPdfStream(print, baos);

		try {
			response.reset();

			response.setContentType("application/pdf");

			response.setContentLength(baos.size());

			response.setHeader("Content-disposition", "inline; filename=relatorio.pdf");

			response.getOutputStream().write(baos.toByteArray());

			response.getOutputStream().flush();

			response.getOutputStream().close();

			context.renderResponse();

			context.responseComplete();

			closeConnection();

		} catch (Exception e) {
			FacesUtil.addErrorMessage("Erro no processamento - Erro: " + e.getMessage());
		}

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

	

	public void gerarReciboBancoDedados(List<Financeiro> consulta, HashMap<String, Object> parametros, String relatorio)
			throws JRException {

		String caminhoFisicoReport = Constantes.CAMINHO_FISICO_BASE_WEBAPP + "resources/relatorios/" + relatorio
				+ ".jrxml";

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		/*
		 * JRBeanCollectionDataSource datasrc = new
		 * JRBeanCollectionDataSource(lista);
		 */

		JasperReport report = JasperCompileManager.compileReport(caminhoFisicoReport);

		JasperPrint print = JasperFillManager.fillReport(report, parametros, getConexao());

		JasperExportManager.exportReportToPdfStream(print, baos);

		try {
			response.reset();

			response.setContentType("application/pdf");

			response.setContentLength(baos.size());

			response.setHeader("Content-disposition", "inline; filename=rel04.pdf");

			response.getOutputStream().write(baos.toByteArray());

			response.getOutputStream().flush();

			response.getOutputStream().close();

			context.renderResponse();

			context.responseComplete();

			closeConnection();

		} catch (Exception e) {
			FacesUtil.addErrorMessage("Erro no processamento - Erro: " + e.getMessage());
		}

	}

	
	
	
	public void gerarRelatorio5(List<Financeiro> consulta, HashMap<String, Object> parametros, String relatorio)
			throws JRException {

		String caminhoFisicoReport = Constantes.CAMINHO_FISICO_BASE_WEBAPP + "resources/relatorios/" + relatorio
				+ ".jrxml";

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		/*
		 * JRBeanCollectionDataSource datasrc = new
		 * JRBeanCollectionDataSource(lista);
		 */

		JasperReport report = JasperCompileManager.compileReport(caminhoFisicoReport);

		JasperPrint print = JasperFillManager.fillReport(report, parametros, getConexao());

		JasperExportManager.exportReportToPdfStream(print, baos);

		try {
			response.reset();

			response.setContentType("application/pdf");

			response.setContentLength(baos.size());

			response.setHeader("Content-disposition", "inline; filename=rel04.pdf");

			response.getOutputStream().write(baos.toByteArray());

			response.getOutputStream().flush();

			response.getOutputStream().close();

			context.renderResponse();

			context.responseComplete();

			closeConnection();

		} catch (Exception e) {
			FacesUtil.addErrorMessage("Erro no processamento - Erro: " + e.getMessage());
		}

	}

	private Connection getConexao() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/db_bravos80?autoReconnect=true", "root",
					"ti1234");
			return con;

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}

	private void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
