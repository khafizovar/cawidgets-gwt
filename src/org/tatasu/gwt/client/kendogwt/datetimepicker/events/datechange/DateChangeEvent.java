package org.tatasu.gwt.client.kendogwt.datetimepicker.events.datechange;

import java.util.Date;

import org.tatasu.gwt.client.kendogwt.datetimepicker.DateTimePicker;

import com.google.gwt.user.client.Event;
/**
 * Cобытие смены даты
 * @author mol4un
 */
public class DateChangeEvent {
	private Date value;
	private DateTimePicker source;
	private Event event;
	
    /**
     * Создание нового события DateChangeEvent
     * @param event		событие пришедшее из jsni
     * @param source	источник события
     * @param value		значение по результату
     */
	public DateChangeEvent(Event event, DateTimePicker source, Date value) {
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
	public DateTimePicker getDateTimePicker() {
		return source;
	}
	
	/**
	 * Получить значение из события
	 */
	public Date getValue() {
		return value;
	}
}

