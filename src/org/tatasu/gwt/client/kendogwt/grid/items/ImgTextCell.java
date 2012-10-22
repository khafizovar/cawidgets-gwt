package org.tatasu.gwt.client.kendogwt.grid.items;
/**
 * Класс для помещения в ячейку формата [Изображение Текст]
 * @author HafizovAR
 *
 */
public class ImgTextCell {
	//private String imageUrl;
	//private String text;
	
	private String cellHtmlTemplate;
	
	public ImgTextCell (String imageUrl, String text) {
		//this.imageUrl = imageUrl;
		//this.text = text;		
		cellHtmlTemplate ="<img src='"+ imageUrl + "'/>&nbsp&nbsp" + text + ""; 
	}	
	
	public ImgTextCell (String imageUrl, String text, int imageWidth, int imageHeight) {
		//this.imageUrl = imageUrl;
		//this.text = text;		
		cellHtmlTemplate ="<img src='"+ imageUrl + "' width='" + imageWidth +"' height='" + imageHeight +"'/>&nbsp&nbsp" + text + "";
	}
	
	
	@Override
	public String toString() {		
		return cellHtmlTemplate;
	}
}
