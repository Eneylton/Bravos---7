package com.java.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import com.java.dao.GraficoDAO;
import com.java.modelo.Financeiro;

public class GraficoService implements Serializable {
	
	private static final long serialVersionUID = 1L;
		
	private GraficoDAO graficoDAO = new GraficoDAO();
	
	
	public List<Financeiro> getTotalMes() throws SQLException {
		return graficoDAO.getTotalMes();
	}
}