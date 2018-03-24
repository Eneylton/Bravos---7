package com.java.controller.aluno;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.java.modelo.Mapa;
import com.java.service.MapaService;
import com.java.util.Constantes;
import com.java.util.jsf.FacesUtil;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

@Named
@ViewScoped
public class PesquisaAlunoMapaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private MapaService mapaService;

	private List<Mapa> listaMapas = new ArrayList<>();

	private Mapa mapa;

	@PostConstruct
	public void inicializar() {

		try {
			listaMapas = mapaService.listarTodos();
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage(ex.getMessage());
		}

	}
	
	
	public void gerarRelatorio() {

		try {
			
			
			
			listaMapas = mapaService.listarTodos();
			
			String caminho = Constantes.CAMINHO_FISICO_BASE_WEBAPP + "resources/relatorios/rel02.jrxml";
			JasperReport relatorio = JasperCompileManager.compileReport(caminho);
			JRBeanCollectionDataSource dados = new JRBeanCollectionDataSource(listaMapas, false);
			JasperPrint print = JasperFillManager.fillReport(relatorio, null, dados);
			
			JasperViewer view = new JasperViewer(print, false);
			
			view.setVisible(true);
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<Mapa> getListaMapas() {
		
	
		
		return listaMapas;
	}

	public void setListaMapas(List<Mapa> listaMapas) {
		this.listaMapas = listaMapas;
	}

	public Mapa getMapa() {
		return mapa;
	}

	public void setMapa(Mapa mapa) {
		this.mapa = mapa;
	}

}