package org.tatasu.gwt.client.cawidgets.popupwidget.event;

import org.tatasu.gwt.client.cawidgets.popupwidget.PopUpWidget;



public class SimpleEvent {
	private final PopUpWidget source;
	public SimpleEvent(PopUpWidget source) {
		this.source = source;
	}
	
	/**
	 * @return the source
	 */
	public PopUpWidget getSource() {
		return source;
	}
}
