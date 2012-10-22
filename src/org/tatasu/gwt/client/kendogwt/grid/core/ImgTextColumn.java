package org.tatasu.gwt.client.kendogwt.grid.core;

/**
 * Колонка для отображения текста + иконка. Использовать совместно с ImgTextCell в источнике данных ArrayList<HashMap<String, Object>> 
 * для конкретного значения Object. НЕ использовать совместно с опцией encoded=false;
 * TODO - сделать опциию автоотключаемой при использован6ии этого класса
 *  
 * @author HafizovAR
 *
 */
public class ImgTextColumn extends GridColumn {
	
	//private String imageTemplate= "'#=data."+field+"#'";
	private String imageTemplate= "#=data." + field + "#";
	
	public ImgTextColumn(String field, String title) {
		super(field, title);
	}
	public ImgTextColumn(String field, String title, int width) {
		super(field, title, width);
	}
	public ImgTextColumn(String field, String title, int imageWidtPx, int imageHeightPx) {
		super(field, title);
		//this.imageTemplate = "<img src='#=data."+field+"#' alt='"+ field +"' width='"+imageWidtPx+"' height='"+imageHeightPx+"'/>";
	}

	/**
	 * @return the imageTemplate
	 */
	public String getImageTemplate() {
		return imageTemplate;
	}

}
