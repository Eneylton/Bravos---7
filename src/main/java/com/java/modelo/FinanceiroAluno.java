package com.java.modelo;

import java.io.Serializable;
import java.util.Date;

public class FinanceiroAluno implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Long idAluno;

	private Long idServico;

	private Long idCategoria;

	private Long idFormaPagamento;

	private Long idCaixa;

	private int qtd;

	private int flag;

	private int tipo;

	private String descricao;

	private String qtdParcela;

	private double valor;

	private double entrada;

	private Date data;

	private String status;

	private Usuario usuario;

	private String valorExtenso;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getIdFormaPagamento() {
		return idFormaPagamento;
	}

	public void setIdFormaPagamento(Long idFormaPagamento) {
		this.idFormaPagamento = idFormaPagamento;
	}

	public Long getIdCaixa() {
		return idCaixa;
	}

	public void setIdCaixa(Long idCaixa) {
		this.idCaixa = idCaixa;
	}

	public Long getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Long idAluno) {
		this.idAluno = idAluno;
	}

	public Long getIdServico() {
		return idServico;
	}

	public void setIdServico(Long idServico) {
		this.idServico = idServico;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getEntrada() {
		return entrada;
	}

	public void setEntrada(double entrada) {
		this.entrada = entrada;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public String getQtdParcela() {
		return qtdParcela;
	}

	public void setQtdParcela(String qtdParcela) {
		this.qtdParcela = qtdParcela;
	}

	public String getValorExtenso() {
		return valorExtenso;
	}

	public void setValorExtenso(String valorExtenso) {
		this.valorExtenso = valorExtenso;
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
		FinanceiroAluno other = (FinanceiroAluno) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
