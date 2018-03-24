package com.java.modelo;

import java.io.Serializable;
import java.util.Date;

public class Aluno implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Sexo sexo;

	private Servico servico;

	private Usuario usuario;

	private String nome;

	private int qtd;

	private int idFormaPagamento;

	private int idFormaPagamento2;

	private int idCaixa;

	private int idCaixa2;

	private String qtdParcela;

	private double valor;

	private double entrada;

	private Date data;

	private Turma turma;

	private String cep;

	private String endereco;

	private String bairro;

	private String cidade;

	private String estado;

	private String numero;

	private String enderecoCompleto;

	private String longitude;

	private String latitude;

	private String foto;

	private String sobreNome;

	private String pai;

	private String mae;

	private Date dataNascimento;

	private String renach;

	private String cpf;

	private String rg;

	private String cnh;

	private String email;

	private String fixo;

	private String whatsapp;

	private AutoEscola autoEscola;

	public AutoEscola getAutoEscola() {
		return autoEscola;
	}

	public void setAutoEscola(AutoEscola autoEscola) {
		this.autoEscola = autoEscola;
	}

	public String getSobreNome() {
		return sobreNome;
	}

	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}

	public String getPai() {
		return pai;
	}

	public void setPai(String pai) {
		this.pai = pai;
	}

	public String getMae() {
		return mae;
	}

	public void setMae(String mae) {
		this.mae = mae;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getRenach() {
		return renach;
	}

	public void setRenach(String renach) {
		this.renach = renach;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCnh() {
		return cnh;
	}

	public void setCnh(String cnh) {
		this.cnh = cnh;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFixo() {
		return fixo;
	}

	public void setFixo(String fixo) {
		this.fixo = fixo;
	}

	public String getWhatsapp() {
		return whatsapp;
	}

	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getEnderecoCompleto() {
		return enderecoCompleto;
	}

	public void setEnderecoCompleto(String enderecoCompleto) {
		this.enderecoCompleto = enderecoCompleto;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	private boolean selecionado;

	public boolean isSelecionado() {
		return selecionado;
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}

	public int getIdCaixa2() {
		return idCaixa2;
	}

	public void setIdCaixa2(int idCaixa2) {
		this.idCaixa2 = idCaixa2;
	}

	public int getIdFormaPagamento2() {
		return idFormaPagamento2;
	}

	public void setIdFormaPagamento2(int idFormaPagamento2) {
		this.idFormaPagamento2 = idFormaPagamento2;
	}

	public int getIdFormaPagamento() {
		return idFormaPagamento;
	}

	public void setIdFormaPagamento(int idFormaPagamento) {
		this.idFormaPagamento = idFormaPagamento;
	}

	public int getIdCaixa() {
		return idCaixa;
	}

	public void setIdCaixa(int idCaixa) {
		this.idCaixa = idCaixa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
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
		Aluno other = (Aluno) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}