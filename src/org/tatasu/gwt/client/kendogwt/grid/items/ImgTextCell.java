package org.tatasu.gwt.client.kendogwt.grid.items;
/**
 * Класс для помещения в ячейку значение формата [Изображение Текст]
 * @author HafizovAR
 *
 */
public class ImgTextCell {
	private String imageUrl = null;
	private String text = null;
	private int imageWidth = -1;
	private int imageHeight = -1;
	private String customCss = null;
	
	//private String cellHtmlTemplate;
	
	public ImgTextCell (String imageUrl, String text) {
		this.imageUrl = imageUrl;
		this.text = text;		
		//cellHtmlTemplate ="<div><img src='"+ imageUrl + "'/>&nbsp&nbsp" + text + "</div>"; 
	}	
	
	public ImgTextCell (String imageUrl, String text, int imageWidth, int imageHeight) {
		this.imageUrl = imageUrl;
		this.text = text;
		this.imageWidth = imageWidth;
		this.imageHeight = imageHeight;
		//cellHtmlTemplate ="<div><img src='"+ imageUrl + "' width='" + imageWidth +"' height='" + imageHeight +"'/>&nbsp&nbsp" + text + "</div>";
	}
		
	public void setCellCss(String cssString) {
		this.customCss = cssString;
	}

	@Override
	public String toString() {
		String rez = "<div";
		if(customCss != null) {
			rez += " style=\"" + customCss + "\"";
		}
		rez += ">";
		if(imageUrl != null && text != null) {
			rez += "<img src='"+ imageUrl + "'";
			if(imageWidth != -1)
				rez += " width='" + imageWidth +"'";
			if(imageHeight != -1)
				rez += " height='" + imageHeight +"'";
			rez += "/>&nbsp&nbsp";
					if(text != null)
						rez += text;
		}
		
		rez += "</div>";
		return rez;
	}
}
