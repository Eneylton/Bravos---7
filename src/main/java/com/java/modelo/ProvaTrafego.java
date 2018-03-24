package com.java.modelo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Desenvolvimento
 *
 */
public class ProvaTrafego implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Date data;

	private Date hora;

	private Aluno aluno;

	private String dia;

	private int status;

	private int aprovado;

	public int getAprovado() {
		return aprovado;
	}

	public void setAprovado(int aprovado) {
		this.aprovado = aprovado;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
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

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
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
		ProvaTrafego other = (ProvaTrafego) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}