package org.tatasu.gwt.client.cawidgets.datagrid.core.cell;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;

public class BeautifulLabel extends Widget {
	/** Элемент контейнер */
	private Element div;
	private String width;
	private String height;
	private String divId = "BeautifulLabel" + Math.round((Math.random() * 10));
	public BeautifulLabel() {
		super();
		div = DOM.createDiv();
		div.setId(divId);
		this.setElement(div);
	}
	
	@Override
	protected void onLoad() {
		//createBeautifulLabel(this, divId, width, height);
		super.onLoad();
	}
	
	private void createBeautifulLabel() {
		
	}	
}
