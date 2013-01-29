package org.tatasu.gwt.client.kendogwt.calendar;

import java.util.ArrayList;
import java.util.Date;

import org.tatasu.gwt.client.kendogwt.calendar.events.changeevent.CalendarChangeEvent;
import org.tatasu.gwt.client.kendogwt.calendar.events.changeevent.CalendarChangeListener;
import org.tatasu.gwt.client.kendogwt.calendar.events.navigateevent.CalendarNavigateEvent;
import org.tatasu.gwt.client.kendogwt.calendar.events.navigateevent.CalendarNavigateListener;
import org.tatasu.gwt.client.kendogwt.calendar.options.CalendarOptions;
import org.tatasu.gwt.client.kendogwt.datetimepicker.options.DateTimePickerOptionsEnum;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsDate;
import com.google.gwt.dom.client.Element;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Widget;

public class Calendar extends Widget {

	/** Div элемент который будет выступать родительским элементом */
	private Element			div;
	/** Идентификатор div элемента */
	private String			divElementId;
	/** Опции виджета Calendar */
	private CalendarOptions	options;
	/** Список слушателей смены выбранной даты */
	private ArrayList<CalendarChangeListener> changeListeners = new ArrayList<CalendarChangeListener>();
	/** Список слушателей события навигаци */
	private ArrayList<CalendarNavigateListener> navigateListeners = new ArrayList<CalendarNavigateListener>();

	public Calendar(CalendarOptions options) {
		super();
		this.options = options;
		this.divElementId = "kendoCalendarDiv" + new Date().getTime() + Math.round(Math.random() * 100000);
		div = DOM.createDiv();
		div.setId(divElementId);
		this.setElement(div);
	}

	@Override
	protected void onLoad() {
		super.onLoad();
		createCalendar();
	}

	private void createCalendar() {
		// Родительские опции DateTimePicker
		JSONObject optionsJs = new JSONObject(); 
		
		// Установка опций 
		if(options.getMaximum() != null) 
			optionsJs.put(DateTimePickerOptionsEnum.Options.MAXIMUM.getName(), new JSONNumber(options.getMaximum().getTime()));
		
		if(options.getMinimum() != null )
			optionsJs.put(DateTimePickerOptionsEnum.Options.MINIMUM.getName(), new JSONNumber(options.getMinimum().getTime()));
				
		if(options.getDepth() != null)
			optionsJs.put(DateTimePickerOptionsEnum.Options.DEPTH.getName(), new JSONString(options.getDepth()));
		
		if(options.getStart() != null)
			optionsJs.put(DateTimePickerOptionsEnum.Options.START.getName(), new JSONString(options.getStart()));
		
		createCalendarJs(this, divElementId, optionsJs.getJavaScriptObject());	
		
		if(options.getValue() != null )
			setValueJS(JsDate.create((options.getValue().getTime())), divElementId);
	}


	/**
	 * Получить текущее значение даты
	 * @return
	 */
	public Date getValue() {
		return new Date(new Double(getValueJs(divElementId)).longValue());
	}
	/**
	 * Получить текущее значение из JS
	 * @param divElementId	идентификатор div элемента виджета
	 * @return
	 */
	private native double getValueJs(String divElementId) /*-{
		return $wnd.$("#" + divElementId).data("kendoCalendar").value().getTime();
	}-*/;
	/**
	 * Установка значения виджета
	 * @param date	значение даты для установки
	 */
	public void setValue(Date date) {
		setValueJS(JsDate.create((date.getTime())), divElementId);
	}
	/**
	 * Нативный метод установки даты
	 * @param longValue			Передача даты осуществляется значением в миллисекундах
	 * @param inputElementId	Идентификатор элемента input выступающего базой для виджета
	 */
	private native void setValueJS(JsDate longValue, String divElementId) /*-{
		try  {
			$wnd.$("#" + inputElementId).data("kendoCalendar").value(new $wnd.Date(longValue));
		} catch (error) {
			$wnd.alert(error);
		}
	}-*/; 
	/**
	 * Метод создания нативного календаря
	 * @param parent	родительский GWT объекта
	 * @param divId		идентификатор div контейнера
	 * @param options	опции календаря
	 */
	private native void createCalendarJs(Calendar parent, String divId, JavaScriptObject options) /*-{
		if(options.max != nullValue)
			options.max = new $wnd.Date(options.max);
		if(options.min != nullValue) 
			options.min = new $wnd.Date(options.min); 
			//js обработчик смены установленной даты
			options.change = function(event) {
				try {
					parent.@org.tatasu.gwt.client.kendogwt.calendar.Calendar::fireDateChangeEvent(Lcom/google/gwt/user/client/Event;D)(event, this.value().getTime());
				} catch (error) {
					$wnd.alert(error);
				}
			};
			//js обработчик навигации
			options.navigate = function(event) {
				try {
					parent.@org.tatasu.gwt.client.kendogwt.calendar.Calendar::fireCalendarNavigationEvent(Lcom/google/gwt/user/client/Event;)(event);
				} catch(error) {
					$wnd.alert(error);
				}
			}
		 $wnd.$("#" + divId).kendoCalendar(options);
	}-*/;
	
	public void navigateUp() {
		
	}
	
	private native void navigateUpJs (String divId) /*-{
		$wnd.$("#" + divId).data("kendoCalendar").navigateUp();
	}-*/;
	
	public void navigateDown() {
		
	}
	
	public void navigateToFuture() {
		
	}
	
	public void navigateToPast() {
		
	}
	
	/****************************************** Методы регистрации слушателей ****************************/
	/**
	 * Регистрация слушателя смены даты
	 * @param listener слушатель
	 */
	protected void addCalendarChangeListener(CalendarChangeListener listener) {
		changeListeners.add(listener);
	}
	/**
	 * Разрегистрация слушателя смены даты
	 * @param listener слушатель
	 */
	protected void removeCalendarChangeListener(CalendarChangeListener listener) {
		changeListeners.remove(listener);
	}
	/**
	 * Регистрация слушателя навигации
	 * @param listener слушатель
	 */
	protected void addCalendarNavigateListener(CalendarNavigateListener listener) {
		navigateListeners.add(listener);
	}
	/**
	 * Разрегистрация слушателя навигации
	 * @param listener слушатель
	 */
	protected void removecalendarNavigateListener(CalendarNavigateListener listener) {
		navigateListeners.remove(listener);
	}
	/****************************************** Методы "выстрела" событий ********************************/
	/**
	 * Генерируем событие смены даты
	 * @param event		Событие
	 * @param value		Новое значение установелнной даты
	 */
	protected void fireDateChangeEvent(Event event, double value) {
		if(changeListeners.size() > 0) {			
			CalendarChangeEvent eventJ = new CalendarChangeEvent(event, this, new Date(new Double(value).longValue()));
	
			for (CalendarChangeListener listener : changeListeners) {
				listener.onCalendarChange(eventJ);
			}
		}
	}
	/**
	 * генерируемсобытие навигации виджета Календарь
	 * @param event
	 */
	protected void fireCalendarNavigationEvent(Event event) {
		if(navigateListeners.size() > 0) {
			CalendarNavigateEvent eventJ = new CalendarNavigateEvent(event, this);
			
			for (CalendarNavigateListener listener : navigateListeners) {
				listener.onCalendarNavigateEvent(eventJ);
			}
		}
	}
}
