package com.java.modelo;

import java.io.Serializable;

/**
 * @author Desenvolvimento
 *
 */
/**
 * @author Desenvolvimento
 *
 */
public class StatusGrafico implements Serializable {

	private static final long serialVersionUID = 1L;

	private String status;

	private int total;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}