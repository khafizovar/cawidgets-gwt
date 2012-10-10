package org.tatasu.gwt.client.cawidgets.dropdown;

import com.google.gwt.user.client.Event;

/**
 * Represents a DropDownWidget events.
 */
public class DropDownWidgetEvents {	
		private DropDownWidgetBean value;
		private boolean hasOriginalEvent = true;
		private DropDownWidget ddwidget;
		private Event event;
		
		public DropDownWidgetEvents(Event event, DropDownWidget source) {
			this(event, source, null, true);
		}
	    /**
	     * Создание нового события для виджета.
	     * 
	     * @param event - событие пришедшее через JSNI
	     * @param source - виджет "выстреливший" событие
	     * @param values - массив значений
	     */
		public DropDownWidgetEvents(Event event, DropDownWidget source, DropDownWidgetBean value) {
			this(event, source, value, true);
		}
		
		/**
	     * Создание нового события виджета.
	     * 
	     * @param event - событие пришедшее через JSNI
	     * @param source - виджет "выстреливший" событие
	     * @param values - массив значений
	     * @param hasOriginalEvent - логический, если изменения произошли от не-программные изменения, такие как событие мыши или клавиатуры
	     */
		public DropDownWidgetEvents(Event event, DropDownWidget source, DropDownWidgetBean value, boolean hasOriginalEvent) {
			this.ddwidget = source;
			this.value = value;
			this.hasOriginalEvent = hasOriginalEvent;
		}
		
		/**
		 * Returns the JSNI returned JavaScriptObject event
		 */
		public Event getEvent() {
			return event;
		}
		
		/**
		 * Get the slider of the event
		 */
		public DropDownWidget getSlider() {
			return ddwidget;
		}
		
		/**
		 * get the values from the event
		 */
		public DropDownWidgetBean getValues() {
			return value;
		}
		
		/**
		 * Does this event have an original event
		 */
		public boolean hasOriginalEvent() {
			return hasOriginalEvent;
		}
	}
