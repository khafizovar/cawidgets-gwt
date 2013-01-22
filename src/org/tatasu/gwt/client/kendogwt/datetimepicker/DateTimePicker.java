package org.tatasu.gwt.client.kendogwt.datetimepicker;

import java.util.Date;

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

public class DateTimePicker extends Widget {
	
	/** Div элемент который будет выступать родительским элементом */
	private Element									div;
	/** Идентификатор div элемента */
	private String									divElementId;
	/** Input элемент необходимый для kendo элемента */
	private Element									input;
	/** Идентификатор input элемента */
	private String 									inputElementId;
	
	private DateTimePickerOptions options;
	
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
	 * Метод создания js дататаймпикера sdfsdfsdfddwdsdsfsSDFFDSF	'sgd;ldf;lmg;sdalkfj;dslSdfsdSDfsdfsdf
	 * @param parent			класс родителя
	 * @param divId				идентификатор div контейнера
	 * @param inputElementId	идентификатор input 
	 * @param options			объект опций
	 */
	private native void createDateTimePickerJS(DateTimePicker parent, String divId, String inputElementId, JavaScriptObject options) /*-{
				try {	
					var nativeOptions = Object();
					var nullValue = null;
					
					if(options.max != nullValue) 
						nativeOptions.max = new $wnd.Date(options.max);
					if(options.min != nullValue) 
						nativeOptions.min = new $wnd.Date(options.min);
					if(options.format != nullValue )
						nativeOptions.min = new $wnd.Date(options.min);
					if(options.interval != nullValue )
						nativeOptions.interval = options.interval;
					if(options.depth != nullValue)
						nativeOptions.depth = options.depth;
					if(options.start != nullValue)
						nativeOptions.start = options.start;
					if(options.timeFormat != nullValue)
						nativeOptions.timeFormat = options.timeFormat;
					
					$wnd.$("#" + inputElementId).kendoDateTimePicker(nativeOptions);
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
 * 
	 * @param date
	 */
	public void setValue(Date date) {
		setValueJS(JsDate.create((date.getTime())), inputElementId);
	}
	
	private native void setValueJS(JsDate longValue, String inputElementId) /*-{
		try  {
			$wnd.$("#" + inputElementId).data("kendoDateTimePicker").value(new $wnd.Date(longValue));
		} catch (error) {
			$wnd.alert(error);
		}
	}-*/; 
	
}
