package org.tatasu.gwt.client.kendogwt.datetimepicker.events.datefield;

import org.tatasu.gwt.client.kendogwt.datetimepicker.DateTimePicker;

import com.google.gwt.user.client.Event;
/**
 * Событие открытия виджета DateTimePicker, поле даты
 * @author mol4un
 */
public class DateOpenCloseEvent {
	
	private DateTimePicker source;
	private Event event;
	/**
	 * Конструктор
	 * @param event		Событие
	 * @param source	Источник события
	 */
	public DateOpenCloseEvent(Event event, DateTimePicker source) {
		this.event = event;
		this.source = source;
	}

	/**
	 * Returns the JSNI returned JavaScriptObject event
	 */
	public Event getEvent() {
		return event;
	}
	
	/**
	 * Получить источник события
	 */
	public DateTimePicker getDateTimePicker() {
		return source;
	}
}
