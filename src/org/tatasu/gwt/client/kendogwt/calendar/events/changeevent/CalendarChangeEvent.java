package org.tatasu.gwt.client.kendogwt.calendar.events.changeevent;

import java.util.Date;

import org.tatasu.gwt.client.kendogwt.calendar.Calendar;

import com.google.gwt.user.client.Event;


public class CalendarChangeEvent {
	
	private Date value;
	private Calendar source;
	private Event event;
	
    /**
     * Создание нового события CalendarChangeEvent
     * @param event		событие пришедшее из jsni
     * @param source	источник события
     * @param value		значение по результату
     */
	public CalendarChangeEvent(Event event, Calendar source, Date value) {
		this.event = event;
		this.source = source;
		this.value = value;
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
	public Calendar getDateTimePicker() {
		return source;
	}
	
	/**
	 * Получить значение из события
	 */
	public Date getValue() {
		return value;
	}
}

