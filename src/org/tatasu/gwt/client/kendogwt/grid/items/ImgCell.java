package org.tatasu.gwt.client.kendogwt.grid.items;

public class ImgCell {

	private String imageUrl = null;
	private int imageWidth = -1;
	private int imageHeight = -1;
	private String customCss = null;

	// private String cellHtmlTemplate;

	public ImgCell(String imageUrl) {
		this.imageUrl = imageUrl;
		// cellHtmlTemplate ="<div><img src='"+ imageUrl + "'/>&nbsp&nbsp" +
		// text + "</div>";
	}

	public ImgCell(String imageUrl, int imageWidth, int imageHeight) {
		this.imageUrl = imageUrl;
		this.imageWidth = imageWidth;
		this.imageHeight = imageHeight;
		// cellHtmlTemplate ="<div><img src='"+ imageUrl + "' width='" +
		// imageWidth +"' height='" + imageHeight +"'/>&nbsp&nbsp" + text +
		// "</div>";
	}

	public void setCellCss(String cssString) {
		this.customCss = cssString;
	}

	@Override
	public String toString() {
		String rez = "<div";
		if (customCss != null) {
			rez += " style=\"" + customCss + "\"";
		}
		rez += ">";
		if (imageUrl != null) {
			rez += "<img src='" + imageUrl + "'";
			if (imageWidth != -1)
				rez += " width='" + imageWidth + "'";
			if (imageHeight != -1)
				rez += " height='" + imageHeight + "'";
			rez += "/>";
		}

		rez += "</div>";
		return rez;
	}
}
