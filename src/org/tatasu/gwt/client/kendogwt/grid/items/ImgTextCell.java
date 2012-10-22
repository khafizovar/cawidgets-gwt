package org.tatasu.gwt.client.kendogwt.grid.items;

public class ImgTextCell {
	private String imageUrl;
	private String text;
	
	public ImgTextCell (String imageUrl, String text) {
		this.imageUrl = imageUrl;
		this.text = text;		
	}
	
	public String  getHtmlTemplate() {
		return "<div><img src='"+ imageUrl + "'>" + text + "</div>";
	}
}
