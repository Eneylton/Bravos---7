package com.java.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Turma implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String descricao;
	private String turno;
	private Date inicio;
	private Date fim;
	private List<Aluno> alunos;
	private Professor professor;

	private double totalAlunos;

	public double getTotalAlunos() {
		return totalAlunos;
	}

	public void setTotalAlunos(double totalAlunos) {
		this.totalAlunos = totalAlunos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTurno() {
		return turno;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
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
		Turma other = (Turma) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}