package com.java.controller.instrutor;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.java.modelo.Agenda;
import com.java.modelo.Fabricante;
import com.java.modelo.Instrutor;
import com.java.modelo.Sexo;
import com.java.modelo.TipoVei;
import com.java.service.FabricanteService;
import com.java.service.InstrutorService;
import com.java.service.RelatorioService;
import com.java.service.SexoService;
import com.java.service.TipoVeiService;
import com.java.util.jsf.FacesUtil;

import net.sf.jasperreports.engine.JRException;

@Named
@ViewScoped
public class CadastroInstrutorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private InstrutorService instrutorService;

	@Inject
	private SexoService sexoService;

	@Inject
	private FabricanteService fabricanteService;

	@Inject
	private TipoVeiService tipoVeiService;

	@Inject
	private RelatorioService relatorioService;

	private TipoVei tipoVei = new TipoVei();

	private Instrutor instrutor;

	private Fabricante fabricante;

	private List<Instrutor> listarInstrutores = new ArrayList<>();

	private List<Agenda> listaMapaInstrutores = new ArrayList<>();

	private Map<String, Sexo> listarSexos = new HashMap<String, Sexo>();

	private List<Fabricante> listarFabricantes = new ArrayList<>();

	private List<TipoVei> listarTipoVeiculos = new ArrayList<>();

	@PostConstruct
	public void init() {

		try {

			listarTipoVeiculos = tipoVeiService.listarTodos();

			listarFabricantes = new ArrayList<>();

			preencheListasSelect();

			this.limpar();

		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage("Erro no processamento - Erro: " + ex.getMessage());
		}

	}

	public void popular() {

		try {
			if (tipoVei != null) {

				listarFabricantes = fabricanteService.buscarTipoFabrigante(tipoVei.getId());

			}

		} catch (SQLException e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage(e.getMessage());
		}

	}

	public void salvar() {

		try {

			if (instrutor.getId() == null) {
				instrutorService.incluir(instrutor);
			} else {
				instrutorService.alterar(instrutor);
			}

			FacesUtil.addSuccessMessage("Instrutor salvo com sucesso!");

			this.limpar();

		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	private void preencheListasSelect() throws SQLException {

		listarSexos = new HashMap<String, Sexo>();
		for (Sexo sexo : sexoService.listarTodos()) {
			listarSexos.put(sexo.getDescricao(), sexo);
		}

	}

	public void gerarRelatorio() throws SQLException, JRException {

		Long idInstrutor = instrutor.getId();

		HashMap<String, Object> parametros = new HashMap<String, Object>();
		listaMapaInstrutores = instrutorService.mapaInstrutor(idInstrutor);
		relatorioService.gerarMapaInstrutor(listaMapaInstrutores, parametros, "mapaInstrutor");

	}

	public void limpar() {
		this.instrutor = new Instrutor();
	}

	public TipoVei getTipoVei() {
		return tipoVei;
	}

	public void setTipoVei(TipoVei tipoVei) {
		this.tipoVei = tipoVei;
	}

	public Instrutor getInstrutor() {
		return instrutor;
	}

	public void setInstrutor(Instrutor instrutor) {
		this.instrutor = instrutor;

	}

	public List<Instrutor> getListarInstrutores() {
		return listarInstrutores;
	}

	public void setListarInstrutores(List<Instrutor> listarInstrutores) {
		this.listarInstrutores = listarInstrutores;
	}

	public Map<String, Sexo> getListarSexos() {
		return listarSexos;
	}

	public void setListarSexos(Map<String, Sexo> listarSexos) {
		this.listarSexos = listarSexos;
	}

	public List<Fabricante> getListarFabricantes() {
		return listarFabricantes;
	}

	public void setListarFabricantes(List<Fabricante> listarFabricantes) {
		this.listarFabricantes = listarFabricantes;
	}

	public List<TipoVei> getListarTipoVeiculos() {
		return listarTipoVeiculos;
	}

	public void setListarTipoVeiculos(List<TipoVei> listarTipoVeiculos) {
		this.listarTipoVeiculos = listarTipoVeiculos;
	}

	public List<Agenda> getListaMapaInstrutores() {
		return listaMapaInstrutores;
	}

	public void setListaMapaInstrutores(List<Agenda> listaMapaInstrutores) {
		this.listaMapaInstrutores = listaMapaInstrutores;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

}
