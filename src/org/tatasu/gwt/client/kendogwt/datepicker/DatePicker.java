package org.tatasu.gwt.client.kendogwt.datepicker;

import java.util.Date;

import org.tatasu.gwt.client.kendogwt.datepicker.options.DatePickerOptions;
import org.tatasu.gwt.client.kendogwt.datetimepicker.DateTimePicker;
import org.tatasu.gwt.client.kendogwt.datetimepicker.options.DateTimePickerOptions;
import org.tatasu.gwt.client.kendogwt.datetimepicker.options.DateTimePickerOptionsEnum;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsDate;
import com.google.gwt.dom.client.Element;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;

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
				
				//if(options.getValue() != null )
				//	setValueJS(JsDate.create((options.getValue().getTime())), inputElementId);
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
							//$wnd.alert(this.value() + " " + this.value().getTime() + " " + this.value().getTimezoneOffset());
							parent.@org.tatasu.gwt.client.kendogwt.datetimepicker.DateTimePicker::fireDateChangeEvent(Lcom/google/gwt/user/client/Event;D)(event, this.value().getTime());
						} catch (error) {
							$wnd.alert(error);
						}
					};
					//js обработчик открытия виджетов поля даты и времени
					options.open = function(event) {
						try {
							//$wnd.alert(this.value() + " " + this.value().getTime() + " " + this.value().getTimezoneOffset());
							//parent.@org.tatasu.gwt.client.kendogwt.datetimepicker.DateTimePicker::fireDateCloseEvent(Lcom/google/gwt/user/client/Event;)(event);
						} catch (error) {
							$wnd.alert(error);
						}
					};
					//js обработчик закрытия виджетов поля даты и времени
					options.close = function(event) {
						try {
								parent.@org.tatasu.gwt.client.kendogwt.datetimepicker.DateTimePicker::fireDateOpenEvent(Lcom/google/gwt/user/client/Event;)(event);							
						} catch (error) {
							$wnd.alert(error);
						}
					};
					
					$wnd.$("#" + inputElementId).kendoDatePicker(options);
				} catch (error) {
					$wnd.alert(error);
				}
	}-*/;
}
