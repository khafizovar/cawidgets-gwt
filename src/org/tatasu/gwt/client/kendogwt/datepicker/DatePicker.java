package org.tatasu.gwt.client.kendogwt.datepicker;

import java.util.ArrayList;
import java.util.Date;

import org.tatasu.gwt.client.kendogwt.datepicker.events.datechange.DatePickerChangeEvent;
import org.tatasu.gwt.client.kendogwt.datepicker.events.datechange.DatePickerChangeListener;
import org.tatasu.gwt.client.kendogwt.datepicker.events.datefield.DatePickerCloseListener;
import org.tatasu.gwt.client.kendogwt.datepicker.events.datefield.DatePickerOpenCloseEvent;
import org.tatasu.gwt.client.kendogwt.datepicker.events.datefield.DatePickerOpenListener;
import org.tatasu.gwt.client.kendogwt.datepicker.options.DatePickerOptions;
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

/**
 * TODO Нет реализации template
 */
/**
 * GWT обертка для виджета DatePicker kendo ui
 * @author HafizovAR
 *
 */
public class DatePicker extends Widget {
	/** Div элемент который будет выступать родительским элементом */
	private Element					div;
	/** Идентификатор div элемента */
	private String					divElementId;
	/** Input элемент необходимый для kendo элемента */
	private Element					input;
	/** Идентификатор input элемента */
	private String 					inputElementId;
	/** Локальная переменная опции виджета DateTimePicker */ 
	private DatePickerOptions 	options;
	/** Список слушателей смены даты */
	private ArrayList<DatePickerChangeListener> changeListener = new ArrayList<DatePickerChangeListener>();
	/** Список слушателей открытия поля даты */
	private ArrayList<DatePickerOpenListener> openListener = new ArrayList<DatePickerOpenListener>();
	/** Список слушателей закрытия поля даты */
	private ArrayList<DatePickerCloseListener> closeListener = new ArrayList<DatePickerCloseListener>();
	
	/**
	 * Конструктор
	 * @param options	опции виджета
	 */
	public DatePicker(DatePickerOptions options) {
		super();
		this.options = options;
		this.divElementId = "kendoDatePickerDiv" + new Date().getTime() +  Math.round(Math.random() * 100000);
		this.inputElementId = "kendoDateInputId" + new Date().getTime() + Math.round(Math.random() * 100000);
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
		createDatePicker();
	}
	/**
	 * Метод создания виджета
	 */
	protected void createDatePicker() {
				// Родительские опции DateTimePicker
				JSONObject optionsJs = new JSONObject(); 
				
				// Установка опций дата грида
				if(options.getMaximum() != null) 
					optionsJs.put(DateTimePickerOptionsEnum.Options.MAXIMUM.getName(), new JSONNumber(options.getMaximum().getTime()));
				
				if(options.getMinimum() != null )
					optionsJs.put(DateTimePickerOptionsEnum.Options.MINIMUM.getName(), new JSONNumber(options.getMinimum().getTime()));
				
				if(options.getFormat() != null )
					optionsJs.put(DateTimePickerOptionsEnum.Options.FORMAT.getName(), new JSONString(options.getFormat()));
						
				if(options.getDepth() != null)
					optionsJs.put(DateTimePickerOptionsEnum.Options.DEPTH.getName(), new JSONString(options.getDepth()));
				
				if(options.getStart() != null)
					optionsJs.put(DateTimePickerOptionsEnum.Options.START.getName(), new JSONString(options.getStart()));
				
				createDatePickerJS(this, divElementId, inputElementId, optionsJs.getJavaScriptObject());	
				
				if(options.getValue() != null )
					setValueJS(JsDate.create((options.getValue().getTime())), inputElementId);
	}
	
	/**
	 * Метод создания js дататаймпикера 
	 * @param parent			класс родителя
	 * @param divId				идентификатор div контейнера
	 * @param inputElementId	идентификатор input 
	 * @param options			объект опций
	 */
	private native void createDatePickerJS(DatePicker parent, String divId, String inputElementId, JavaScriptObject options) /*-{
				try {	
					//Приведение типа long к типу Date 
					var nullValue = null;
					var dateView = "date";
					var timeView = "time";
					if(options.max != nullValue)
						options.max = new $wnd.Date(options.max);
					if(options.min != nullValue) 
						options.min = new $wnd.Date(options.min); 
					
					//js обработчик смены установленной даты
					options.change = function(event) {
						try {
							parent.@org.tatasu.gwt.client.kendogwt.datepicker.DatePicker::fireDateChangeEvent(Lcom/google/gwt/user/client/Event;D)(event, this.value().getTime());
						} catch (error) {
							$wnd.alert(error);
						}
					};
					//js обработчик открытия виджетов поля даты и времени
					options.open = function(event) {
						try {
							parent.@org.tatasu.gwt.client.kendogwt.datepicker.DatePicker::fireDateCloseEvent(Lcom/google/gwt/user/client/Event;)(event);
						} catch (error) {
							$wnd.alert(error);
						}
					};
					//js обработчик закрытия виджетов поля даты и времени
					options.close = function(event) {
						try {
							parent.@org.tatasu.gwt.client.kendogwt.datepicker.DatePicker::fireDateOpenEvent(Lcom/google/gwt/user/client/Event;)(event);							
						} catch (error) {
							$wnd.alert(error);
						}
					};
					
					$wnd.$("#" + inputElementId).kendoDatePicker(options);
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
		return $wnd.$("#" + inputElementId).data("kendoDatePicker").value().getTime();
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
			$wnd.$("#" + inputElementId).data("kendoDatePicker").value(new $wnd.Date(longValue));
		} catch (error) {
			$wnd.alert(error);
		}
	}-*/; 
	
	
	/****************************************** События регистрации/отписки ***************************************/
	/**
	 * Регистрация слушателя смены даты
	 * @param listener	слушатель смены даты
	 */
	public void addDateChangeEventListener(DatePickerChangeListener listener) {
		changeListener.add(listener);
	}
	
	/**
	 * Разрегистрация слушателя смены даты 
	 * @param listener слушатель смены даты которого необходимо разрегистрировать
	 */
	public void removeDateChangeEventListener(DatePickerChangeListener listener) {
		changeListener.remove(listener);
	}
	
	/**
	 * Регистрация слушателей открытия поля даты 
	 * @param listener	слушатель
	 */
	public void addOpenEventListener(DatePickerOpenListener listener) {
		openListener.add(listener);
	}
	
	/**
	 * Разрегистрация слушателя открытия поля даты
	 * @param listener слушатель
	 */
	public void removeOpenEventListener(DatePickerOpenListener listener) {
		openListener.remove(listener);
	}
	
	/**
	 * Регистрация сулашетля закрытия поля со временем
	 * @param listener	слушатель
	 */
	public void addDateCloseListener(DatePickerCloseListener listener) {
		closeListener.add(listener);
	}
	
	/**
	 * Метод разрегистрации слушателя события закрытия поля с датой
	 * @param listener слушатель
	 */
	public void removeDateCloseListener(DatePickerCloseListener listener) {
		closeListener.remove(listener);
	}
	
	/****************************************** Методы "выстрела" событий ********************************/
	/**
	 * Генерируем событие смены даты
	 * @param event		Событие
	 * @param value		Новое значение установелнной даты
	 */
	protected void fireDateChangeEvent(Event event, double value) {
		DatePickerChangeEvent eventJ = new DatePickerChangeEvent(event, this, new Date(new Double(value).longValue()));

		for (DatePickerChangeListener listener : changeListener) {
			listener.onDateChange(eventJ);
		}
	}
	/**
	 * Генерируем событие открытия поля даты
	 * @param event событие
	 */
	protected void fireDateOpenEvent(Event event) {
		DatePickerOpenCloseEvent eventJ = new DatePickerOpenCloseEvent(event, this);
		
		for(DatePickerOpenListener listener : openListener) {
			listener.onDatePickerOpen(eventJ);
		}
	}
	/**
	 * Генерируем событие закрытия поля даты
	 * @param event
	 */
	protected void fireDateCloseEvent(Event event) {
		DatePickerOpenCloseEvent eventJ = new DatePickerOpenCloseEvent(event, this);
		
		for(DatePickerCloseListener listener : closeListener) {
			listener.onDatePickerClose(eventJ);
		}
	}
}
