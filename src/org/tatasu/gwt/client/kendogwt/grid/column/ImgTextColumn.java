package org.tatasu.gwt.client.kendogwt.grid.column;

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
	/**
	 * Конструктор
	 * @param field Идентификатор поля
	 * @param title Отображаемый в заголовке текст
	 */
	public ImgTextColumn(String field, String title) {
		super(field, title);
	}
	/**
	 * Конструктор
	 * @param field Идентификатор поля
	 * @param title Отображаемый в заголовке текст
	 * @param width Явная ширина колонки 
	 */
	public ImgTextColumn(String field, String title, int width) {
		super(field, title, width);
	}
	/**
	 * Конструктор
	 * @param field Идентифкатор поля
	 * @param title	Отображаемый в звголовке текст
	 * @param imageWidtPx	Ширина изображения в пикселях
	 * @param imageHeightPx	Высота изображения в пикселях	
	 */
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
