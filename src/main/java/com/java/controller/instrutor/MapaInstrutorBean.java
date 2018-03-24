package com.java.controller.instrutor;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.java.modelo.Agenda;
import com.java.modelo.ExcelOptions;
import com.java.modelo.Instrutor;
import com.java.modelo.PDFOptions;
import com.java.service.InstrutorService;
import com.java.util.jsf.FacesUtil;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
@Named
@ViewScoped
public class MapaInstrutorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private InstrutorService instrutorService;

	private List<Agenda> listaInstrutores = new ArrayList<>();

	private List<Agenda> pesquisaInstrutores = new ArrayList<>();

	private Instrutor instrutor;

	private Instrutor instrutorSelecionado;
	
	private ExcelOptions excelOpt;

	private PDFOptions pdfOpt;

	@PostConstruct
	public void inicializar() {

		try {

			String idInstrutor = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
					.get("instrutor");

			listaInstrutores = instrutorService.mapaInstrutor(Long.parseLong(idInstrutor));
			
			pesquisaInstrutores = instrutorService.mapaInstrutor(Long.parseLong(idInstrutor));
			

		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage(ex.getMessage());
		}

	}
	
	
	 public void customizationOptions() {
	        excelOpt = new ExcelOptions();
	        excelOpt.setFacetBgColor("#8c2f4d");
	        excelOpt.setFacetFontSize("14");
	        excelOpt.setFacetFontColor("#ffcb39");
	        excelOpt.setFacetFontStyle("BOLD");
	        excelOpt.setCellFontColor("#0000ff");
	        excelOpt.setCellFontSize("14");
	         
	        pdfOpt = new PDFOptions();
	        pdfOpt.setFacetBgColor("#FF0000");
	        pdfOpt.setFacetFontColor("#e1e1e2");
	        pdfOpt.setFacetFontStyle("BOLD");
	        pdfOpt.setCellFontSize("18");
	    }
	 
	 
		public void custumizadoXLS(Object document) {
			HSSFWorkbook wb = (HSSFWorkbook) document;
			HSSFSheet sheet = wb.getSheetAt(0);
			HSSFRow header = sheet.getRow(0);

			HSSFCellStyle cellStyle = wb.createCellStyle();
			cellStyle.setFillForegroundColor(HSSFColor.CORAL.index);
			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellStyle.setUserStyleName("Alunos");

			for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
				HSSFCell cell = header.getCell(i);

				cell.setCellStyle(cellStyle);
			}
		}
		
		
		 public void custumizandoPDF(Object document) throws IOException, BadElementException, DocumentException {
		        
		        Document pdf = (Document) document;        
		        pdf.setMargins(2f, 2f, 2f, 2f);
		        pdf.setPageSize(PageSize.A4);
		        pdf.addTitle("Gerar Fatura");
		        pdf.open();
		       
		        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		        String logo = servletContext.getRealPath("") + File.separator + "resources/img" + File.separator + "pdf.png";
		        //cria a imagem e passando a url
		        Image image = Image.getInstance(logo);
		        //alinha ao centro
		        image.setAlignment(Image.ALIGN_CENTER);
		        //adciona a img ao pdf
		        pdf.add(image);
		        //adiciona um paragrafo ao pdf, alinha também ao centro
		        Paragraph p = new Paragraph("__________ Relatório _________");
		        Paragraph b = new Paragraph(" ");
		        p.setAlignment("center");
		        b.setAlignment("center");
		        pdf.add(p);
		        pdf.add(b);
		    }
		
		
	
	

	public List<Agenda> getListaInstrutores() {
		return listaInstrutores;
	}

	public void setListaInstrutores(List<Agenda> listaInstrutores) {
		this.listaInstrutores = listaInstrutores;
	}

	public Instrutor getInstrutor() {
		return instrutor;
	}

	public void setInstrutor(Instrutor instrutor) {
		this.instrutor = instrutor;
	}

	public Instrutor getInstrutorSelecionado() {
		return instrutorSelecionado;
	}

	public void setInstrutorSelecionado(Instrutor instrutorSelecionado) {
		this.instrutorSelecionado = instrutorSelecionado;
	}

	public List<Agenda> getPesquisaInstrutores() {
		return pesquisaInstrutores;
	}

	public void setPesquisaInstrutores(List<Agenda> pesquisaInstrutores) {
		this.pesquisaInstrutores = pesquisaInstrutores;
	}

}
