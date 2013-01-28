package org.tatasu.gwt.client.kendogwt.datepicker.events.datefield;



import org.tatasu.gwt.client.kendogwt.datepicker.DatePicker;

import com.google.gwt.user.client.Event;

public class DatePickerOpenCloseEvent {
	
	private DatePicker source;
	private Event event;
	/**
	 * Конструктор
	 * @param event		Событие
	 * @param source	Источник события
	 */
	public DatePickerOpenCloseEvent(Event event, DatePicker source) {
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
	public DatePicker getDateTimePicker() {
		return source;
	}
}