package org.tatasu.gwt.client.kendogwt.grid.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.tatasu.gwt.client.kendogwt.grid.options.GridOptions;
import org.tatasu.gwt.client.kendogwt.grid.options.GridOptionsEnum;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

public class DataConverter {
	
	public static final String defaultDataForamt = "dd.MM.yyyy HH:mm:ss";
	/*public static JavaScriptObject getJSObjectFromDataSource(DataSource) {
		JSONObject ok = new JSONObject();
		return ok.getJavaScriptObject();
	}*/
	/**
	 * Метод парсинга ArrayList<HashMap<String, Object>> в JSONObject, для нужд kendo grid, Поиск значений происходит по полям указанным в gridOptions
	 * @param data	Данные из которых необходимо получить javaScriptObject
	 * @param gridOptions необходим для получения списка полей датагрида
	 * @return
	 */
	public static JSONArray getJSObjectFromArrayHashMap(ArrayList<HashMap<String, Object>> data, GridOptions gridOptions) {
		// / Инициализация модели данных
		JSONObject dataS = new JSONObject();
		JSONArray dataArr = new JSONArray();
		int index2 = 0;
		// Обход массива данных
		//for (HashMap<String, Object> t : dataProvider) {
		for (HashMap<String, Object> t : data) {
			/*dataS = new JSONObject();
			for (String fieldName : gridOptions.getArrayFields()) {
				if (t.get(fieldName) instanceof String) {
					dataS.put(fieldName, new JSONString(t.get(fieldName).toString()));
				} else if (t.get(fieldName) instanceof Long) {
					dataS.put(fieldName, new JSONNumber((Long) t.get(fieldName)));
				} else if (t.get(fieldName) instanceof Double) {
					dataS.put(fieldName, new JSONNumber((Double) t.get(fieldName)));
				} else if (t.get(fieldName) instanceof Integer) {
					dataS.put(fieldName, new JSONNumber((Integer) t.get(fieldName)));
				} else if (t.get(fieldName) instanceof Date) {
					DateTimeFormat fmt = DateTimeFormat.getFormat("dd.MM.yyyy HH:mm:ss");
					dataS.put(fieldName, new JSONString(fmt.format(((Date) t.get(fieldName)))));
				} else {
					if(t.get(fieldName) != null)
						dataS.put(fieldName, new JSONString(t.get(fieldName).toString()));
					else 
						dataS.put(fieldName, new JSONString(""));
				}
			}
			dataArr.set(index2, dataS);*/
			dataArr.set(index2, getJSObjectFromHashMap(t, gridOptions));
			index2 = index2 + 1;
		}
		return dataArr;
	}
	/**
	 * Метод парсинга одиночного значения в JSONObject для нужд DataGrid. Поиск значений происходит по полям указанным в gridOptions
	 * @param t
	 * @param gridOptions
	 * @return
	 */
	public static JSONObject getJSObjectFromHashMap(HashMap<String, Object> t, GridOptions gridOptions) {
		JSONObject dataS = new JSONObject();
		
		for (String fieldName : gridOptions.getArrayFields()) {
			if (t.get(fieldName) instanceof String) {
				dataS.put(fieldName, new JSONString(t.get(fieldName).toString()));
			} else if (t.get(fieldName) instanceof Long) {
				dataS.put(fieldName, new JSONNumber((Long) t.get(fieldName)));
			} else if (t.get(fieldName) instanceof Double) {
				dataS.put(fieldName, new JSONNumber((Double) t.get(fieldName)));
			} else if (t.get(fieldName) instanceof Integer) {
				dataS.put(fieldName, new JSONNumber((Integer) t.get(fieldName)));
			} else if (t.get(fieldName) instanceof Date) {
				DateTimeFormat fmt = DateTimeFormat.getFormat(defaultDataForamt);
				dataS.put(fieldName, new JSONString(fmt.format(((Date) t.get(fieldName)))));
			} else if(t.get(fieldName) instanceof Boolean) {
				dataS.put(fieldName, JSONBoolean.getInstance((Boolean)t.get(fieldName)));
			} else {
				if(t.get(fieldName) != null)
					dataS.put(fieldName, new JSONString(t.get(fieldName).toString()));
				else 
					dataS.put(fieldName, new JSONString(""));
			}
		}
		
		return dataS;
	}
}
