package org.tatasu.gwt.client.kendogwt.grid.core;

/**
 * Колонка с изображением, изображение формируется на базе темплейта, поразумевается что ссылка на изображение 
 * содержится в самом поле. НЕ использовать encoded = false
 * TODO сделать эту опцию автоотключаемой при использовании этого класса.
 * @author HafizovAR
 */
public class ImageColumn extends GridColumn {
	
	private String imageTemplate= "<img src='#=data."+field+"#' alt='"+ field +"'/>";
	
	public ImageColumn(String field, String title) {
		super(field, title);
	}
	public ImageColumn(String field, String title, int width) {
		super(field, title, width);
	}
	public ImageColumn(String field, String title, int imageWidtPx, int imageHeightPx) {
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
