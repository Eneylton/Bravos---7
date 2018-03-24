package com.java.modelo;

import java.io.Serializable;

/**
 * @author Desenvolvimento
 *
 */
public class TotalCadastroDia implements Serializable {

	private static final long serialVersionUID = 1L;

	private String dia;
	private int total;



	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}