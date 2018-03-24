package com.java.conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//return DriverManager.getConnection("jdbc:mysql://db-sitesindauma.mysql.uhserver.com/db_sitesindauma?autoreconect=true&useUnicode=yes&characterEncoding=Cp1252", "sitesindauma", "s1n2d3m4@");
			//return DriverManager.getConnection("jdbc:mysql://node143218-bravos.jelasticlw.com.br/db_bravos4", "root", "QNTovs44248");
			//return DriverManager.getConnection("jdbc:mysql://localhost/db_sitesindauma?autoreconect=true&useUnicode=yes&characterEncoding=Cp1252", "db_cadastroselect", "ti1234");
			return DriverManager.getConnection("jdbc:mysql://localhost/db_bravos80?autoReconnect=true", "root", "ti1234");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
        
    }
}