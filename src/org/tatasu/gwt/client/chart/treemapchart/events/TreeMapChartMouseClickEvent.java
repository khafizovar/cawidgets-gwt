package org.tatasu.gwt.client.chart.treemapchart.events;

import org.tatasu.gwt.client.chart.treemapchart.TreeMapChart;

import com.google.gwt.user.client.Event;

public class TreeMapChartMouseClickEvent {

	/** Оригинальное событие */
	private Event			event;
	/** Источник события */
	private TreeMapChart	source;
	/** Тип события */
	private String			eventType;
	/** Индекс события */
	private Integer			rowIndex	= null;
	/** X координата мыши */
	private Integer			mouseXPosition;
	/** Y координата мыши */
	private Integer			mouseYPosition;
	/** Номер клавиши */
	private Integer			buttonNumber;
	/** Зажата ли кнопка alt */
	private boolean			altKey		= false;
	/** Зажата ли кнопка shift */
	private boolean			shiftKey	= false;

	public TreeMapChartMouseClickEvent(Event event, TreeMapChart source, String eventType, Integer rowIndex, Integer mouseXPosition, Integer mouseYPosition, Integer buttonNumber, boolean altkey, boolean shiftKey) {
		this.event = event;
		this.source = source;
		this.eventType = eventType;
		this.rowIndex = rowIndex;
		this.mouseXPosition = mouseXPosition;
		this.mouseYPosition = mouseYPosition;
		this.buttonNumber = buttonNumber;
		this.altKey = altkey;
		this.shiftKey = shiftKey;
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
	public Integer getButtonNumber() {
		return buttonNumber;
	}
	public boolean isAltKey() {
		return altKey;
	}
	public boolean isShiftKey() {
		return shiftKey;
	}
}
