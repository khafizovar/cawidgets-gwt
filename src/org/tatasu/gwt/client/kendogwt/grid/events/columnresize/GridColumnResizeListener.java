package org.tatasu.gwt.client.kendogwt.grid.events.columnresize;

import org.tatasu.gwt.client.kendogwt.grid.events.GridEvent;


public interface GridColumnResizeListener {
	/**
	 * Fires when the user resizes a column.
	 * @param event
	 */
	public void columnResize(GridColumnResizeEvent event);
}
