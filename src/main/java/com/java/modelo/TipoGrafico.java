package com.java.modelo;

import java.io.Serializable;

/**
 * @author Desenvolvimento
 *
 */
public class TipoGrafico implements Serializable {

	private static final long serialVersionUID = 1L;

	private String tipo;

	private int total;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}


}