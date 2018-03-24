package com.java.controller.autoEscola;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.java.modelo.AutoEscola;
import com.java.service.AutoEscolaService;
import com.java.service.Relatorio2Service;
import com.java.util.FacesMessages;
import com.java.util.FileUploadCopiarArquivo;
import com.java.util.Session;
import com.java.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroAutoEscolaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private AutoEscolaService autoEscolaService;

	@Inject
	private FacesMessages messages;
	
	@Inject
	private Relatorio2Service relatorioService;

	private UploadedFile arquivoUP;

	private List<AutoEscola> listarAutoEscolas;

	private List<AutoEscola> pesquisaAltoEscola;

	private AutoEscola autoEscola;

	private AutoEscola autoEscolaEdicao = new AutoEscola();

	private AutoEscola autoEscolaSelecionada;
	
	

	@PostConstruct
	public void init() {

		try {

			this.limpar();

			consultar();
			pesquisar();

		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage("Erro no Processamento - Erro: " + ex.getMessage());
		}

	}

	public void prepararNovoCadastro() {
		autoEscolaEdicao = new AutoEscola();
	}

	public void salvar() throws SQLException {
		try {
			

			String nomeArquivo = "";
			if (arquivoUP != null) {
				FileUploadCopiarArquivo.iniciarCopia(arquivoUP);
				nomeArquivo = arquivoUP.getFileName();
				autoEscolaEdicao.setLogo(nomeArquivo);
			} else {
				autoEscolaEdicao.setLogo("");
			}

			if (autoEscolaEdicao.getId() == null) {

				autoEscolaService.incluir(autoEscolaEdicao);
				consultar();
				pesquisar();

				messages.info("Auto Escola Cadastrada com sucesso!");

				RequestContext.getCurrentInstance().update(Arrays.asList("frm:msgs", "frm:autoEscolaTable"));

			} else {

				autoEscolaService.alterar(autoEscolaEdicao);
				consultar();
				pesquisar();

				messages.info("Registro Alterado com sucesso!");

				RequestContext.getCurrentInstance().update(Arrays.asList("frm:msgs", "frm:autoEscolaTable"));

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage("Erro no processamento - Erro: " + ex.getMessage());
		}
	}

	public void excluir() throws SQLException {
		autoEscolaService.excluir(autoEscolaSelecionada);
		autoEscolaSelecionada = null;

		consultar();
		limpar();

		messages.info("Auto Escola excluída com sucesso!");
		RequestContext.getCurrentInstance().update(Arrays.asList("frm:msgs", "frm:autoEscolaTable"));
	}

	public void consultar() throws SQLException {

		listarAutoEscolas = autoEscolaService.listarTodos();

	}

	public void pesquisar() throws SQLException {

		listarAutoEscolas = autoEscolaService.listarTodos();

	}
	
	
	public void gerarRelatorio() {

		try {
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			listarAutoEscolas = autoEscolaService.listarTodos();
			relatorioService.gerarRelatorioAutoEscola(listarAutoEscolas, parametros, "autoEscola");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void gerarRelatorio2() {

		try {
			Long idAuto = Session.retornaIdAutoEscola();
		
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			listarAutoEscolas = autoEscolaService.listarUsuariosPorAutoEscola(idAuto);
			relatorioService.gerarRelatorioUsuarioAutoEscola(listarAutoEscolas, parametros, "autoEscolaUsuario");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void gerarRelatorio3() {

		try {
			
		
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			listarAutoEscolas = autoEscolaService.listarUsuarioPorAutoEscola();
			relatorioService.gerarEstatisticaAutoEscola(listarAutoEscolas, parametros, "graficoUsuarioAutoescola");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void handleFileUpload(FileUploadEvent event) {
		this.setArquivoUP(event.getFile());
	}


	public void limpar() {
		this.autoEscola = new AutoEscola();
	}

	public FacesMessages getMessages() {
		return messages;
	}

	public void setMessages(FacesMessages messages) {
		this.messages = messages;
	}

	public List<AutoEscola> getListarAutoEscolas() {
		return listarAutoEscolas;
	}

	public void setListarAutoEscolas(List<AutoEscola> listarAutoEscolas) {
		this.listarAutoEscolas = listarAutoEscolas;
	}

	public List<AutoEscola> getPesquisafessores() {
		return pesquisaAltoEscola;
	}

	public void setPesquisafessores(List<AutoEscola> pesquisafessores) {
		this.pesquisaAltoEscola = pesquisafessores;
	}

	public AutoEscola getAutoEscola() {
		return autoEscola;
	}

	public void setAutoEscola(AutoEscola autoEscola) {
		this.autoEscola = autoEscola;
	}

	public AutoEscola getAutoEscolaEdicao() {
		return autoEscolaEdicao;
	}

	public void setAutoEscolaEdicao(AutoEscola autoEscolaEdicao) {
		this.autoEscolaEdicao = autoEscolaEdicao;
	}

	public AutoEscola getAutoEscolaSelecionada() {
		return autoEscolaSelecionada;
	}

	public void setAutoEscolaSelecionada(AutoEscola autoEscolaSelecionada) {
		this.autoEscolaSelecionada = autoEscolaSelecionada;
	}

	public List<AutoEscola> getPesquisaAltoEscola() {
		return pesquisaAltoEscola;
	}

	public void setPesquisaAltoEscola(List<AutoEscola> pesquisaAltoEscola) {
		this.pesquisaAltoEscola = pesquisaAltoEscola;
	}

	public UploadedFile getArquivoUP() {
		return arquivoUP;
	}

	public void setArquivoUP(UploadedFile arquivoUP) {
		this.arquivoUP = arquivoUP;
	}

}
