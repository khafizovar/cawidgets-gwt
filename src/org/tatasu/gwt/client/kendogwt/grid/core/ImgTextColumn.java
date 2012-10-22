package org.tatasu.gwt.client.kendogwt.grid.core;

public class ImgTextColumn extends GridColumn {
	
	private String imageTemplate= "<img src='#=data."+field+"#' alt='"+ field +"'/>";
	
	public ImgTextColumn(String field, String title) {
		super(field, title);
	}
	public ImgTextColumn(String field, String title, int width) {
		super(field, title, width);
	}
	public ImgTextColumn(String field, String title, int imageWidtPx, int imageHeightPx) {
		super(field, title);
		this.imageTemplate = "<img src='#=data."+field+"#' alt='"+ field +"' width='"+imageWidtPx+"' height='"+imageHeightPx+"'/>";
	}

	/**
	 * @return the imageTemplate
	 */
	public String getImageTemplate() {
		return imageTemplate;
	}

}
