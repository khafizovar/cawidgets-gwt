package org.tatasu.gwt.client.chart.treemapchart.events;

import org.tatasu.gwt.client.chart.treemapchart.TreeMapChart;

import com.google.gwt.user.client.Event;

public class TreeMapChartEvent {

	private Event			event;
	private TreeMapChart	source;
	private String			eventType;

	public TreeMapChartEvent(Event event, TreeMapChart source, String eventType) {
		this.event = event;
		this.source = source;
		this.eventType = eventType;
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
}
