package com.java.modelo;

import java.io.Serializable;
import java.util.Date;

public class Financeiro implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Date data;

	private double valor;

	private int flag;

	private int qtd;

	private int tipo;

	private Servico servico;

	private Aluno aluno;

	private String descricao;

	private Categoria categoria;

	private Caixa caixa;

	private int idFormaPagamento;

	private String dia;

	private double valorTotal;

	private String valorExtenso;

	private Usuario usuario;

	private AutoEscola autoEscola;

	private String idTipo;

	private String idFlag;

	private Date dataInicio;

	private Date dataFim;

	private String mes;

	private String nomeCompleto;

	private double total;

	private double totalRelatorio;

	private double totalGeral;

	public double getTotalGeral() {
		return totalGeral;
	}

	public void setTotalGeral(double totalGeral) {
		this.totalGeral = totalGeral;
	}

	public AutoEscola getAutoEscola() {
		return autoEscola;
	}

	public void setAutoEscola(AutoEscola autoEscola) {
		this.autoEscola = autoEscola;
	}

	public double getTotalRelatorio() {
		return totalRelatorio;
	}

	public void setTotalRelatorio(double totalRelatorio) {
		this.totalRelatorio = totalRelatorio;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public String getValorExtenso() {
		return valorExtenso;
	}

	public void setValorExtenso(String valorExtenso) {
		this.valorExtenso = valorExtenso;
	}

	public String getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(String idTipo) {
		this.idTipo = idTipo;
	}

	public String getIdFlag() {
		return idFlag;
	}

	public void setIdFlag(String idFlag) {
		this.idFlag = idFlag;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}

	public int getIdFormaPagamento() {
		return idFormaPagamento;
	}

	public void setIdFormaPagamento(int idFormaPagamento) {
		this.idFormaPagamento = idFormaPagamento;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Financeiro other = (Financeiro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}