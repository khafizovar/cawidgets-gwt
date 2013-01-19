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
				// Родительские опции датагрида
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
					optionsJs.put(DateTimePickerOptionsEnum.Options.TIMEFORMAT.getName(), new JSONString(options.getStart()));
		
				createDateTimePickerJS(this, divElementId, inputElementId, null);	
				
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
				//$wnd.alert(options);
				$wnd.$("#" + inputElementId).kendoDateTimePicker(options);				
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
	
	public void setValue(Date date) {
		setValueJS(JsDate.create((date.getTime())), inputElementId);
	}
	
	private native void setValueJS(JsDate longValue, String inputElementId) /*-{
		try  {
		//var sendingDate = longValue;
		//$wnd.alert(sendingDate);
		$wnd.$("#" + inputElementId).data("kendoDateTimePicker").value(new Date());
		} catch (error) {
			$wnd.alert(error);
		}
	}-*/; 
	
}
