package com.java.modelo;

import java.io.Serializable;
import java.util.Date;

public class Agenda implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String titulo;
	private Date inicio;
	private Date Fim;
	private String idStatus;
	private String mostrarDataInicio;
	private String mostrarDataFim;
    private CadAgenda cadAgenda;

	
	public String getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(String idStatus) {
		this.idStatus = idStatus;
	}

	public CadAgenda getCadAgenda() {
		return cadAgenda;
	}

	public void setCadAgenda(CadAgenda cadAgenda) {
		this.cadAgenda = cadAgenda;
	}

	private boolean status;

	private String descricao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFim() {
		return Fim;
	}

	public void setFim(Date fim) {
		Fim = fim;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMostrarDataInicio() {
		return mostrarDataInicio;
	}

	public void setMostrarDataInicio(String mostrarDataInicio) {
		this.mostrarDataInicio = mostrarDataInicio;
	}

	public String getMostrarDataFim() {
		return mostrarDataFim;
	}

	public void setMostrarDataFim(String mostrarDataFim) {
		this.mostrarDataFim = mostrarDataFim;
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
		Agenda other = (Agenda) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}