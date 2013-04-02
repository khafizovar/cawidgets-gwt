package org.tatasu.gwt.client.chart.treemapchart.events;

import org.tatasu.gwt.client.chart.treemapchart.TreeMapChart;

import com.google.gwt.user.client.Event;

public class TreeMapChartEvent {

	private Event			event;
	private TreeMapChart	source;
	private String			eventType;
	private Integer			rowIndex	= null;
	private Integer			mouseXPosition;
	private Integer			mouseYPosition;

	public TreeMapChartEvent(Event event, TreeMapChart source, String eventType, Integer rowIndex, Integer mouseXPosition, Integer mouseYPosition) {
		this.event = event;
		this.source = source;
		this.eventType = eventType;
		this.rowIndex = rowIndex;
		this.mouseXPosition = mouseXPosition;
		this.mouseYPosition = mouseYPosition;
	}
	public Event getEvent() {
		return event;
	}
	public TreeMapChart getSource() {
		return source;
	}
	public String getEventType() {
		return eventType;
	}
	public Integer getRowIndex() {
		return rowIndex;
	}
	public Integer getMouseXPosition() {
		return mouseXPosition;
	}
	public Integer getMouseYPosition() {
		return mouseYPosition;
	}
}
