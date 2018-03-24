package com.java.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Data {
	
	public static Boolean eValida(Date data) {
		
		DateFormat df = new SimpleDateFormat ("yyyy-MM-dd");  
		df.setLenient (false);  
		try {  
		    df.parse (data.toString());  
		    return true;
		} catch (ParseException ex) {  
		   return false;
		}  
		
	}
	
	public static String retornaDaraHoraAtual() {
		
		Locale locale = new Locale("pt","BR"); 
		GregorianCalendar calendar = new GregorianCalendar(); 
		SimpleDateFormat formatador = new SimpleDateFormat("dd' de 'MMMMM' de 'yyyy' - 'HH':'mm'h'",locale); 
		return formatador.format(calendar.getTime()); 
		
	} 
	
	public static String retornaDaraHoraAtual_yyyyMMdd_ssmmHH() {
		
		Locale locale = new Locale("pt","BR"); 
		GregorianCalendar calendar = new GregorianCalendar(); 
		SimpleDateFormat formatador = new SimpleDateFormat("yyyyMMdd_ssmmHH",locale); 
		return formatador.format(calendar.getTime()); 
		
	} 
	
	public static String formataDataPara_ddMMyyyy (Date data) throws ParseException {
		
		Locale locale = new Locale("pt","BR"); 
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy",locale);
		return formatador.format(data);
		
	}
	
public static String formataDataPara_MMM_dd_yyyy (Date data) throws ParseException {
		
		Locale locale = new Locale("pt","BR"); 
		SimpleDateFormat formatador = new SimpleDateFormat("MMM dd, yyyy",locale);
		return formatador.format(data);
		
	}
	
}
