package org.tatasu.gwt.client.kendogwt.calendar.events.navigateevent;

import org.tatasu.gwt.client.kendogwt.calendar.Calendar;

import com.google.gwt.user.client.Event;

/**
 * Событие навигации виджета Календарь
 * @author HafizovAR
 *
 */
public class CalendarNavigateEvent {
	private Calendar source;
	private Event event;
	
    /**
     * Создание нового события CalendarChangeEvent
     * @param event		событие пришедшее из jsni
     * @param source	источник события
     * @param value		значение по результату
     */
	public CalendarNavigateEvent(Event event, Calendar source) {
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
	public Calendar getDateTimePicker() {
		return source;
	}
}
