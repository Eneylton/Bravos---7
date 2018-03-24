package com.java.modelo;

import java.io.Serializable;

/**
 * @author Desenvolvimento
 *
 */
public class CrossTable implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Categoria categoria;

	private double janeiro;

	private double fevereiro;

	private double marco;

	private double abril;

	private double maio;

	private double junho;

	private double julho;

	private double agosto;

	private double setembro;

	private double outubro;

	private double novembro;

	private double dezembro;

	private double totalProduto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getJaneiro() {
		return janeiro;
	}

	public void setJaneiro(double janeiro) {
		this.janeiro = janeiro;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public double getTotalProduto() {
		return totalProduto;
	}

	public void setTotalProduto(double totalProduto) {
		this.totalProduto = totalProduto;
	}

	public double getFevereiro() {
		return fevereiro;
	}

	public void setFevereiro(double fevereiro) {
		this.fevereiro = fevereiro;
	}

	public double getMarco() {
		return marco;
	}

	public void setMarco(double marco) {
		this.marco = marco;
	}

	public double getAbril() {
		return abril;
	}

	public void setAbril(double abril) {
		this.abril = abril;
	}

	public double getMaio() {
		return maio;
	}

	public void setMaio(double maio) {
		this.maio = maio;
	}

	public double getJunho() {
		return junho;
	}

	public void setJunho(double junho) {
		this.junho = junho;
	}

	public double getJulho() {
		return julho;
	}

	public void setJulho(double julho) {
		this.julho = julho;
	}

	public double getAgosto() {
		return agosto;
	}

	public void setAgosto(double agosto) {
		this.agosto = agosto;
	}

	public double getSetembro() {
		return setembro;
	}

	public void setSetembro(double setembro) {
		this.setembro = setembro;
	}

	public double getOutubro() {
		return outubro;
	}

	public void setOutubro(double outubro) {
		this.outubro = outubro;
	}

	public double getNovembro() {
		return novembro;
	}

	public void setNovembro(double novembro) {
		this.novembro = novembro;
	}

	public double getDezembro() {
		return dezembro;
	}

	public void setDezembro(double dezembro) {
		this.dezembro = dezembro;
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
		CrossTable other = (CrossTable) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}