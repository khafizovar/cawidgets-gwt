package org.tatasu.gwt.client.kendogwt.datetimepicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.tatasu.gwt.client.kendogwt.datetimepicker.events.datechange.DateChangeEvent;
import org.tatasu.gwt.client.kendogwt.datetimepicker.events.datechange.DateChangeListener;
import org.tatasu.gwt.client.kendogwt.datetimepicker.events.dateopen.DateOpenEvent;
import org.tatasu.gwt.client.kendogwt.datetimepicker.events.dateopen.DateOpenListener;
import org.tatasu.gwt.client.kendogwt.datetimepicker.events.timeopen.TimeOpenEvent;
import org.tatasu.gwt.client.kendogwt.datetimepicker.events.timeopen.TimeOpenListener;
import org.tatasu.gwt.client.kendogwt.datetimepicker.options.DateTimePickerOptions;
import org.tatasu.gwt.client.kendogwt.datetimepicker.options.DateTimePickerOptionsEnum;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsDate;
import com.google.gwt.dom.client.Element;
import com.google.gwt.i18n.client.TimeZone;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.CalendarUtil;
/**
 * GWT обертка web виджета kendo ui DateTimePicker 
 * @author mol4un
 *
 */
public class DateTimePicker extends Widget {
	
	/** Div элемент который будет выступать родительским элементом */
	private Element					div;
	/** Идентификатор div элемента */
	private String					divElementId;
	/** Input элемент необходимый для kendo элемента */
	private Element					input;
	/** Идентификатор input элемента */
	private String 					inputElementId;
	/** Локальная переменная опции виджета DateTimePicker */ 
	private DateTimePickerOptions 	options;
	/** Список слушателей события смены даты */
	ArrayList<DateChangeListener> dateChangeListeners = new ArrayList<DateChangeListener>();
	/** Список слушателей события открытия поля даты */
	ArrayList<DateOpenListener> dateOpenListeners = new ArrayList<DateOpenListener>();
	/** Список слушателей события открытия поля времени */
	ArrayList<TimeOpenListener> timeOpenListeners = new ArrayList<TimeOpenListener>();
	
	public DateTimePicker(DateTimePickerOptions options) {
		super();
		this.options = options;
		this.divElementId = "kendoDateTimePickerDiv" + new Date().getTime() +  Math.round(Math.random() * 100000);
		this.inputElementId = "kendoDateTimePickerInoutId" + new Date().getTime() + Math.round(Math.random() * 100000);
		div = DOM.createDiv();
		div.setId(divElementId);
		input = DOM.createInputText();
		input.setId(inputElementId);
		div.appendChild(input);
		this.setElement(div);		
	}
	
	@Override
	protected void onLoad() {
		super.onLoad();
		createDateTimePicker();
	}
	
	private void createDateTimePicker() {
				// Родительские опции DateTimePicker
				JSONObject optionsJs = new JSONObject(); 
				
				// Установка опций дата грида
				if(options.getMaximum() != null) 
					optionsJs.put(DateTimePickerOptionsEnum.Options.MAXIMUM.getName(), new JSONNumber(options.getMaximum().getTime()));
				
				if(options.getMinimum() != null )
					optionsJs.put(DateTimePickerOptionsEnum.Options.MINIMUM.getName(), new JSONNumber(options.getMinimum().getTime()));
				
				if(options.getFormat() != null )
					optionsJs.put(DateTimePickerOptionsEnum.Options.FORMAT.getName(), new JSONString(options.getFormat()));
				
				if(options.getInterval() != null )
					optionsJs.put(DateTimePickerOptionsEnum.Options.INTERVAL.getName(), new JSONNumber(options.getInterval()));
				
				if(options.getDepth() != null)
					optionsJs.put(DateTimePickerOptionsEnum.Options.DEPTH.getName(), new JSONString(options.getDepth()));
				
				if(options.getStart() != null)
					optionsJs.put(DateTimePickerOptionsEnum.Options.START.getName(), new JSONString(options.getStart()));
				
				if(options.getTimeFormat() != null)
					optionsJs.put(DateTimePickerOptionsEnum.Options.TIMEFORMAT.getName(), new JSONString(options.getTimeFormat()));
		
				createDateTimePickerJS(this, divElementId, inputElementId, optionsJs.getJavaScriptObject());	
				if(options.getValue() != null )
					setValueJS(JsDate.create((options.getValue().getTime())), inputElementId);
					//optionsJs.put(DateTimePickerOptionsEnum.Options.VALUE.getName(), new JSONNumber(options.getValue().getTime()));
	}
	/**
	 * Метод создания js дататаймпикера 
	 * @param parent			класс родителя
	 * @param divId				идентификатор div контейнера
	 * @param inputElementId	идентификатор input 
	 * @param options			объект опций
	 */
	private native void createDateTimePickerJS(DateTimePicker parent, String divId, String inputElementId, JavaScriptObject options) /*-{
				try {	
					//Приведение типа long к типу Date 
					var nullValue = null;
					var dateView = "date";
					var timeView = "time";
					if(options.max != nullValue)
						options.max = new $wnd.Date(options.max);
					if(options.min != nullValue) 
						options.min = new $wnd.Date(options.min); 
						
					options.change = function(event) {
						try {
							//$wnd.alert(this.value() + " " + this.value().getTime() + " " + this.value().getTimezoneOffset());
							parent.@org.tatasu.gwt.client.kendogwt.datetimepicker.DateTimePicker::fireDateChangeEvent(Lcom/google/gwt/user/client/Event;D)(event, this.value().getTime());
						} catch (error) {
							$wnd.alert(error);
						}
					};
					
					options.open = function(event) {
						try {
							//$wnd.alert(this.value() + " " + this.value().getTime() + " " + this.value().getTimezoneOffset());
							if(event.view === dateView) {	//генерируем событие смены Даты
								parent.@org.tatasu.gwt.client.kendogwt.datetimepicker.DateTimePicker::fireDateOpenEvent(Lcom/google/gwt/user/client/Event;)(event);
							} else if (event.view === timeView) {
								parent.@org.tatasu.gwt.client.kendogwt.datetimepicker.DateTimePicker::fireTimeOpenEvent(Lcom/google/gwt/user/client/Event;)(event);
							}
								
						} catch (error) {
							$wnd.alert(error);
						}
					};
					
					$wnd.$("#" + inputElementId).kendoDateTimePicker(options);
				} catch (error) {
					$wnd.alert(error);
				}
	}-*/;
	
	/**
	 * Получить текущее значение даты
	 * @return
	 */
	public Date getValue() {
		return new Date(new Double(getValueJs(inputElementId)).longValue());
	}
	/**
	 * Получить текущее значение из JS
	 * @param inputElementId
	 * @return
	 */
	private native double getValueJs(String inputElementId) /*-{
		return $wnd.$("#" + inputElementId).data("kendoDateTimePicker").value().getTime();
	}-*/;
	/**
	 * Установка значения виджета
	 * @param date	значение даты для установки
	 */
	public void setValue(Date date) {
		setValueJS(JsDate.create((date.getTime())), inputElementId);
	}
	/**
	 * Нативный метод установки даты
	 * @param longValue			Передача даты осуществляется значением в миллисекундах
	 * @param inputElementId	Идентификатор элемента input выступающего базой для виджета
	 */
	private native void setValueJS(JsDate longValue, String inputElementId) /*-{
		try  {
			$wnd.$("#" + inputElementId).data("kendoDateTimePicker").value(new $wnd.Date(longValue));
		} catch (error) {
			$wnd.alert(error);
		}
	}-*/; 
	
	
	/****************************************** События регистрации/отписки ***************************************/
	/**
	 * Регистрация слушателя смены даты
	 * @param listener	слушатель смены даты
	 */
	public void addDateChangeEventListener(DateChangeListener listener) {
		dateChangeListeners.add(listener);
	}
	/**
	 * Разрегистрация слушателя смены даты 
	 * @param listener слушатель смены даты которого необходимо разрегистрировать
	 */
	public void removeDateChangeEventListener(DateChangeListener listener) {
		dateChangeListeners.remove(listener);
	}
	
	/**
	 * Регистрация слушателей открытия поля даты 
	 * @param listener	слушатель
	 */
	public void addOpenEventListener(DateOpenListener listener) {
		dateOpenListeners.add(listener);
	}
	/**
	 * Разрегистрация слушателя открытия поля даты
	 * @param listener слушатель
	 */
	public void removeOpenEventListener(DateOpenListener listener) {
		dateOpenListeners.remove(listener);
	}
	/**
	 * Регистрация слушателя события открытия поля времени 
	 * @param listener слушатель
	 */
	public void addTimeOpenEventListener(TimeOpenListener listener) {
		timeOpenListeners.add(listener);
	}
	/**
	 * Разрегистрация слушателя события открытия поля времени
	 * @param listener слушатель
	 */
	public void removeTimeOpenListener(TimeOpenListener listener) {
		timeOpenListeners.remove(listener);
	}
	/****************************************** Методы "выстрела" событий ********************************/
	/**
	 * Генерируем событие смены даты
	 * @param event		Событие
	 * @param value		Новое значение установелнной даты
	 */
	private void fireDateChangeEvent(Event event, double value) {
		//TimeZone timeZone = TimeZone.createTimeZone(new Date().getTimezoneOffset()); 
		//Window.alert(timeZone.getLongName(new Date()));
		//Window.alert(value + " " + new Double(value).longValue() + new Date(new Double(value).longValue()) + new Date(new Double(value).longValue()).getTimezoneOffset());
		//Window.alert(value + " " + new Double(value).longValue() + new Date(new Double(value).longValue()) + " " + new Date(new Double(value).longValue()).getTimezoneOffset() + " ");
		DateChangeEvent eventJ = new DateChangeEvent(event, this, new Date(new Double(value).longValue()));

		for (DateChangeListener listener : dateChangeListeners) {
			listener.onDateChange(eventJ);
		}
		//Window.alert(value + "");
	}
	/**
	 * Генерируем событие открытия поля даты
	 * @param event событие
	 */
	private void fireDateOpenEvent(Event event) {
		DateOpenEvent eventJ = new DateOpenEvent(event, this);
		
		for(DateOpenListener listener : dateOpenListeners) {
			listener.onDateOpen(eventJ);
		}
	}
	/**
	 * Генерируем событие открытия поля времени
	 * @param event событие
	 */
	private void fireTimeOpenEvent(Event event) {
		TimeOpenEvent eventJ = new TimeOpenEvent(event, this);
		
		for(TimeOpenListener listener : timeOpenListeners) {
			listener.onTimeOnOpen(eventJ);
		}
	}
}
