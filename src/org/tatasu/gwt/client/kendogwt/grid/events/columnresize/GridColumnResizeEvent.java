package org.tatasu.gwt.client.kendogwt.grid.events.columnresize;

import org.tatasu.gwt.client.kendogwt.grid.Grid2;

import com.google.gwt.user.client.Event;


public class GridColumnResizeEvent {
	private Event event;
	private int oldValue;
	private int newValue;
	private String columnField;
	private String columnTitle;
	private Grid2 grid;
	
	public GridColumnResizeEvent(Event event, int oldValue, int newValue, String columnField, String columnTitle, Grid2 grid) {
		super();
		this.event = event;
		this.oldValue = oldValue;
		this.newValue = newValue;
		this.columnField = columnField;
		this.columnTitle = columnTitle;
	}
	
	/**
	 * @return the event
	 */
	public Event getEvent() {
		return event;
	}
	
	/**
	 * @return the oldValue
	 */
	public int getOldValue() {
		return oldValue;
	}
	
	/**
	 * @return the newValue
	 */
	public int getNewValue() {
		return newValue;
	}
	
	/**
	 * @return the columnField
	 */
	public String getColumnField() {
		return columnField;
	}
	
	/**
	 * @return the columnTitle
	 */
	public String getColumnTitle() {
		return columnTitle;
	}

	
	/**
	 * @return the grid
	 */
	public Grid2 getGrid() {
		return grid;
	}	
}
