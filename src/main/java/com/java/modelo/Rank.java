package com.java.modelo;

import java.io.Serializable;

/**
 * @author Desenvolvimento
 *
 */
public class Rank implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nomeCompleto;

	private int total;

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}