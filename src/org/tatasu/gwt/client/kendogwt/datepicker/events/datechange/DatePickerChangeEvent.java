package org.tatasu.gwt.client.kendogwt.datepicker.events.datechange;

import java.util.Date;

import org.tatasu.gwt.client.kendogwt.datepicker.DatePicker;
import com.google.gwt.user.client.Event;
/**
 * Событие смены даты
 * @author mol4un
 */
public class DatePickerChangeEvent {
	
	private Date value;
	private DatePicker source;
	private Event event;
	
    /**
     * Создание нового события DateChangeEvent
     * @param event		событие пришедшее из jsni
     * @param source	источник события
     * @param value		значение по результату
     */
	public DatePickerChangeEvent(Event event, DatePicker source, Date value) {
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
	public DatePicker getDateTimePicker() {
		return source;
	}
	
	/**
	 * Получить значение из события
	 */
	public Date getValue() {
		return value;
	}
}

