package com.java.modelo;

public class ExcelOptions implements ExporterOptions {
    
	 private String facetFontStyle;
	    
	    private String facetFontColor;
	    
	    private String facetBgColor;
	    
	    private String facetFontSize;

	    private String cellFontStyle;
	    
	    private String cellFontColor;
	    
	    private String cellFontSize;
	    
	    public ExcelOptions() {}
	    
	    public ExcelOptions(String facetFontStyle, String facetFontColor, String facetBgColor, String facetFontSize) {
	        this.facetFontStyle = facetFontStyle;
	        this.facetFontColor = facetFontColor;
	        this.facetBgColor = facetBgColor;
	        this.facetFontSize = facetFontSize;
	    }
	    
	    public ExcelOptions(String cellFontStyle, String cellFontColor, String cellFontSize) {
	        this.cellFontStyle = cellFontStyle;
	        this.cellFontColor = cellFontColor;
	        this.cellFontSize = cellFontSize;
	    }
	    
	    public ExcelOptions(String facetFontStyle, String facetFontColor, String facetBgColor, String facetFontSize, String cellFontStyle, String cellFontColor, String cellFontSize) {
	        this(facetFontStyle, facetFontColor, facetBgColor, facetFontSize);
	        this.cellFontStyle = cellFontStyle;
	        this.cellFontColor = cellFontColor;
	        this.cellFontSize = cellFontSize;
	    }

	    public String getFacetFontStyle() {
	        return facetFontStyle;
	    }

	    public void setFacetFontStyle(String facetFontStyle) {
	        this.facetFontStyle = facetFontStyle;
	    }

	    public String getFacetFontColor() {
	        return facetFontColor;
	    }

	    public void setFacetFontColor(String facetFontColor) {
	        this.facetFontColor = facetFontColor;
	    }

	    public String getFacetBgColor() {
	        return facetBgColor;
	    }

	    public void setFacetBgColor(String facetBgColor) {
	        this.facetBgColor = facetBgColor;
	    }

	    public String getFacetFontSize() {
	        return facetFontSize;
	    }

	    public void setFacetFontSize(String facetFontSize) {
	        this.facetFontSize = facetFontSize;
	    }

	    public String getCellFontStyle() {
	        return cellFontStyle;
	    }

	    public void setCellFontStyle(String cellFontStyle) {
	        this.cellFontStyle = cellFontStyle;
	    }

	    public String getCellFontColor() {
	        return cellFontColor;
	    }

	    public void setCellFontColor(String cellFontColor) {
	        this.cellFontColor = cellFontColor;
	    }

	    public String getCellFontSize() {
	        return cellFontSize;
	    }

	    public void setCellFontSize(String cellFontSize) {
	        this.cellFontSize = cellFontSize;
	    }
	 
	}