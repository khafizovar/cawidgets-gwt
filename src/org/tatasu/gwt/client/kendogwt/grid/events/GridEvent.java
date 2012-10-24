package org.tatasu.gwt.client.kendogwt.grid.events;

import org.tatasu.gwt.client.kendogwt.grid.Grid2;

import com.google.gwt.user.client.Event;

public class GridEvent {
	private Object value;
	private boolean hasOriginalEvent;
	private Grid2 grid;
	private Event event;
	
	public GridEvent(Event event, Grid2 source, Object values) {
		this(event, source, values, true );
	}
	
	public GridEvent(Event event, Grid2 source, Object value, boolean hasOriginalEvent) {
		this.value = value;
		this.event = event;
		this.grid = source;
		this.hasOriginalEvent = hasOriginalEvent;
	}

	
	/**
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}
	
	/**
	 * @return the hasOriginalEvent
	 */
	public boolean isHasOriginalEvent() {
		return hasOriginalEvent;
	}
	
	/**
	 * @return the grid
	 */
	public Grid2 getGrid() {
		return grid;
	}
	
	/**
	 * @return the event
	 */
	public Event getEvent() {
		return event;
	}
}
