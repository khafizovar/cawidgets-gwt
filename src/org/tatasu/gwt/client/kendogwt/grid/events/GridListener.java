package org.tatasu.gwt.client.kendogwt.grid.events;

import org.tatasu.gwt.client.kendogwt.grid.events.change.GridChangeEvent;


public interface GridListener {
	/**
	 * Fires when the grid has received data from the data source.
	 * @param event
	 */
	public void dataBoud(GridEvent event);
	
	/**
	 * Fires when the user resizes a column.
	 * @param event
	 */
	public void columnResize(GridEvent event);
	
	/**
	 * Fires before the grid item is removed. 
	 * @param event
	 */
	public void remove(GridEvent event);
}
