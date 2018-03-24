package com.java.controller.aluno;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.map.GeocodeEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.GeocodeResult;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import com.java.dao.UsuarioDAO;
import com.java.modelo.Aluno;
import com.java.modelo.FinanceiroAluno;
import com.java.modelo.Servico;
import com.java.modelo.Sexo;
import com.java.service.AlunoService;
import com.java.service.FinanceiroAlunoService;
import com.java.service.ServicoService;
import com.java.service.SexoService;
import com.java.util.Extenso;
import com.java.util.FileUploadCopiarArquivo;
import com.java.util.Session;
import com.java.util.WebServiceCep;
import com.java.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroAlunoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioDAO usuarioDAO;

	@Inject
	private AlunoService alunoService;

	@Inject
	private ServicoService servicoService;

	@Inject
	private SexoService sexoService;

	@Inject
	private FinanceiroAluno financeiroAluno;

	@Inject
	private FinanceiroAlunoService financeiroAlunoService;

	private UploadedFile arquivoUP;

	private int qtd;

	private String num;

	private double valorParcial;

	private double entrada;

	private double valor;

	private String valorExtenso;

	private Long formaPagamento;

	private Long formaPagamento2;

	private Long caixa;

	private Long caixa2;

	private double valorParcela;

	private double valorEntrada;

	private Date dataVencimento;

	private Aluno aluno;

	private String cep;

	private MapModel geoModel = new DefaultMapModel();

	private String centerGeoMap = "-2.5357198, -44.21076360000001";

	private String enderecoCompleto;

	private MapModel advancedModel;

	private String lt1;

	private String lt2;

	private double latitude;

	private double longitude;

	private List<FinanceiroAluno> listaFinanceiroAlunos = new ArrayList<>();

	private Map<String, Servico> listarServicos = new HashMap<String, Servico>();

	private Map<String, Sexo> listarSexos = new HashMap<String, Sexo>();

	@PostConstruct
	public void init() {

		try {

			preencheListasSelect();
			this.limpar();

		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage("Erro no processamento - Erro: " + ex.getMessage());
		}

	}

	public void onGeocode(GeocodeEvent event) throws SQLException {
		List<GeocodeResult> results = event.getResults();

		if (results != null && !results.isEmpty()) {
			LatLng center = results.get(0).getLatLng();
			centerGeoMap = center.getLat() + "," + center.getLng();

			for (int i = 0; i < results.size(); i++) {
				GeocodeResult result = results.get(i);
				geoModel.addOverlay(new Marker(result.getLatLng(), result.getAddress(), result.getLatLng().getLat()));

				this.enderecoCompleto = result.getAddress();
				this.latitude = result.getLatLng().getLat();
				this.longitude = result.getLatLng().getLng();
				this.num = aluno.getNumero();

				this.lt1 = Double.toString(this.latitude);

				this.lt2 = Double.toString(this.longitude);

				aluno.setLatitude(this.lt1);
				aluno.setLongitude(this.lt2);
				aluno.setEnderecoCompleto(this.enderecoCompleto);

			}

		}
	}

	public void cep() {

		try {

			this.cep = aluno.getCep();

			WebServiceCep webServiceCep = WebServiceCep.searchCep(this.cep);

			if (webServiceCep.wasSuccessful()) {

				aluno.setEndereco(webServiceCep.getLogradouroFull());
				aluno.setBairro(webServiceCep.getBairro());
				aluno.setCidade(webServiceCep.getCidade());
				aluno.setEstado(webServiceCep.getUf());

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage("Erro no processamento - Erro: " + ex.getMessage());
		}

	}

	public void calcular() throws SQLException {

		calcularParcela();

	}

	public void calcularParcela() throws SQLException {

		this.formaPagamento = (long) aluno.getIdFormaPagamento();

		this.caixa = (long) aluno.getIdCaixa();

		this.qtd = aluno.getQtd();

		this.valor = aluno.getServico().getValor();

		this.entrada = aluno.getEntrada();

		this.valorParcela = (valor - entrada);

		this.valorEntrada = valorParcela / qtd;

		GregorianCalendar gc = new GregorianCalendar();

		this.dataVencimento = aluno.getData();

		for (int i = 0; i <= qtd; i++) {

			gc.setTime(this.dataVencimento);
			gc.roll(GregorianCalendar.MONTH, i);

			Calendar c = Calendar.getInstance();

			Date d = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			c.setTime(this.dataVencimento);
			c.set(Calendar.MONTH, c.get(Calendar.MONTH) + i);

			FinanceiroAluno parc = new FinanceiroAluno();

			if (i == 0) {

				parc.setQtd(i);
				parc.setQtdParcela(" Entrada (+) ");
				parc.setValor(entrada);
				parc.setData(cal.getTime());
				parc.setStatus("PAGO");
				parc.setQtd(i);
				parc.setFlag(1);
				parc.setTipo(1);
				parc.setIdAluno(aluno.getId());
				parc.setIdServico(24L);
				parc.setIdFormaPagamento(this.formaPagamento);
				parc.setIdCaixa(this.caixa);
				parc.setIdCategoria(1L);
				parc.setUsuario(usuarioDAO.retornarUsuarioPorID(Session.retornaIdUsuarioLogado()));
				this.valorExtenso = new Extenso(entrada).toString();
				parc.setValorExtenso(valorExtenso);
				listaFinanceiroAlunos.add(parc);

				financeiroAlunoService.incluir(parc);
				FacesUtil.addSuccessMessage("Entrada (+) Salva com Sucesso !!! ");

			} else {

				parc.setQtd(i);
				parc.setQtdParcela(i + " de " + qtd);
				parc.setValor(this.valorEntrada);
				parc.setData(c.getTime());
				parc.setStatus("EM ABERTO");
				parc.setQtd(i);
				parc.setFlag(2);
				parc.setTipo(1);
				parc.setIdAluno(aluno.getId());
				parc.setIdServico(24L);
				parc.setIdFormaPagamento(3L);
				parc.setIdCaixa(2L);
				parc.setIdCategoria(1L);
				parc.setUsuario(usuarioDAO.retornarUsuarioPorID(Session.retornaIdUsuarioLogado()));
				this.valorExtenso = new Extenso(this.valorEntrada).toString();
				parc.setValorExtenso(valorExtenso);
				listaFinanceiroAlunos.add(parc);

				financeiroAlunoService.incluir(parc);
				FacesUtil.addSuccessMessage("Parcela Nº " + i + " (-) Salva com Sucesso !!! ");

			}

		}
	}

	public void pagamentoAvista() throws SQLException {

		this.formaPagamento2 = (long) aluno.getIdFormaPagamento2();

		this.caixa2 = (long) aluno.getIdCaixa2();

		this.qtd = 1;

		this.valor = aluno.getServico().getValor();

		this.valorExtenso = new Extenso(valor).toString();

		this.valorEntrada = valor / qtd;

		for (int i = 1; i <= qtd; i++) {

			Date d2 = new Date();
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(d2);

			FinanceiroAluno parc2 = new FinanceiroAluno();

			if (i == 1) {
				parc2.setQtd(i);
				parc2.setQtdParcela(" Entrada (+) ");
				parc2.setValor(this.valorEntrada);
				parc2.setData(cal2.getTime());
				parc2.setStatus("PAGO");
				parc2.setQtd(i);
				parc2.setFlag(1);
				parc2.setTipo(1);
				parc2.setIdAluno(aluno.getId());
				parc2.setIdServico(24L);
				parc2.setIdFormaPagamento(this.formaPagamento2);
				parc2.setIdCaixa(this.caixa2);
				parc2.setIdCategoria(1L);
				parc2.setUsuario(usuarioDAO.retornarUsuarioPorID(Session.retornaIdUsuarioLogado()));

				this.valorExtenso = new Extenso(this.valorEntrada).toString();
				parc2.setValorExtenso(valorExtenso);
				listaFinanceiroAlunos.add(parc2);

				financeiroAlunoService.incluir(parc2);
				FacesUtil.addSuccessMessage("Pagamento Realizado com Sucesso !!! ");

			}
		}

	}

	private void preencheListasSelect() throws SQLException {

		listarSexos = new HashMap<String, Sexo>();
		for (Sexo sexo : sexoService.listarTodos()) {
			listarSexos.put(sexo.getDescricao(), sexo);
		}

		listarServicos = new HashMap<String, Servico>();
		for (Servico servico : servicoService.listarTodos()) {
			listarServicos.put(servico.getDescricao(), servico);

		}

	}

	public void limpar() {
		this.aluno = new Aluno();
	}

	public void salvar() {

		try {

			String nomeArquivo = "";
			if (arquivoUP != null) {
				FileUploadCopiarArquivo.iniciarCopia(arquivoUP);
				nomeArquivo = arquivoUP.getFileName();
				aluno.setFoto(nomeArquivo);
			} else {
				aluno.setFoto("");
			}

			if (aluno.getId() == null) {
				aluno.setUsuario(usuarioDAO.retornarUsuarioPorID(Session.retornaIdUsuarioLogado()));
				alunoService.incluir(aluno);
			} else {
				aluno.setUsuario(usuarioDAO.retornarUsuarioPorID(Session.retornaIdUsuarioLogado()));
				alunoService.alterar(aluno);
			}

			FacesUtil.addSuccessMessage("Alteração realizada com sucesso !");

			this.limpar();

		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public void handleFileUpload(FileUploadEvent event) {
		this.setArquivoUP(event.getFile());
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Map<String, Servico> getListarServicos() {
		return listarServicos;
	}

	public void setListarServicos(Map<String, Servico> listarServicos) {
		this.listarServicos = listarServicos;
	}

	public Map<String, Sexo> getListarSexos() {
		return listarSexos;
	}

	public void setListarSexos(Map<String, Sexo> listarSexos) {
		this.listarSexos = listarSexos;
	}

	public FinanceiroAluno getFinanceiroAluno() {
		return financeiroAluno;
	}

	public void setFinanceiroAluno(FinanceiroAluno financeiroAluno) {
		this.financeiroAluno = financeiroAluno;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public double getValorParcial() {
		return valorParcial;
	}

	public void setValorParcial(double valorParcial) {
		this.valorParcial = valorParcial;
	}

	public double getEntrada() {
		return entrada;
	}

	public void setEntrada(double entrada) {
		this.entrada = entrada;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public List<FinanceiroAluno> getListaFinanceiroAlunos() {
		return listaFinanceiroAlunos;
	}

	public void setListaFinanceiroAlunos(List<FinanceiroAluno> listaFinanceiroAlunos) {
		this.listaFinanceiroAlunos = listaFinanceiroAlunos;
	}

	public double getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(double valorParcela) {
		this.valorParcela = valorParcela;
	}

	public double getValorEntrada() {
		return valorEntrada;
	}

	public void setValorEntrada(double valorEntrada) {
		this.valorEntrada = valorEntrada;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Long getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(Long formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Long getCaixa() {
		return caixa;
	}

	public void setCaixa(Long caixa) {
		this.caixa = caixa;
	}

	public Long getFormaPagamento2() {
		return formaPagamento2;
	}

	public void setFormaPagamento2(Long formaPagamento2) {
		this.formaPagamento2 = formaPagamento2;
	}

	public Long getCaixa2() {
		return caixa2;
	}

	public void setCaixa2(Long caixa2) {
		this.caixa2 = caixa2;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public MapModel getGeoModel() {
		return geoModel;
	}

	public void setGeoModel(MapModel geoModel) {
		this.geoModel = geoModel;
	}

	public String getCenterGeoMap() {
		return centerGeoMap;
	}

	public void setCenterGeoMap(String centerGeoMap) {
		this.centerGeoMap = centerGeoMap;
	}

	public String getEnderecoCompleto() {
		return enderecoCompleto;
	}

	public void setEnderecoCompleto(String enderecoCompleto) {
		this.enderecoCompleto = enderecoCompleto;
	}

	public MapModel getAdvancedModel() {
		return advancedModel;
	}

	public void setAdvancedModel(MapModel advancedModel) {
		this.advancedModel = advancedModel;
	}

	public String getLt1() {
		return lt1;
	}

	public void setLt1(String lt1) {
		this.lt1 = lt1;
	}

	public String getLt2() {
		return lt2;
	}

	public void setLt2(String lt2) {
		this.lt2 = lt2;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public UploadedFile getArquivoUP() {
		return arquivoUP;
	}

	public void setArquivoUP(UploadedFile arquivoUP) {
		this.arquivoUP = arquivoUP;
	}

	public String getValorExtenso() {
		return valorExtenso;
	}

	public void setValorExtenso(String valorExtenso) {
		this.valorExtenso = valorExtenso;
	}

}
