package org.tatasu.gwt.client.kendogwt.grid.items;
/**
 * Класс для помещения в ячейку 
 * @author HafizovAR
 *
 */
public class ImgTextCell {
	private String imageUrl;
	private String text;
	
	public ImgTextCell (String imageUrl, String text) {
		this.imageUrl = imageUrl;
		this.text = text;		
	}
	
	@Override
	public String toString() {		
		return "<img src='"+ imageUrl + "'/>" + text + "";
	}
}
