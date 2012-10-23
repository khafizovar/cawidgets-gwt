package org.tatasu.gwt.client.kendogwt.grid.options;

import java.util.ArrayList;
import java.util.HashMap;

public class DataSourceOptions {
	/**
	 * Массив данных
	 */
	private ArrayList<HashMap<String, Object>> data;
	/**
	 * Автоматическая установка данных, если false - необходимо принудительно вызывать .read() для установки данных
	 */
	private boolean autoBind = true;
	/**
	 * @return the data
	 */
	public ArrayList<HashMap<String, Object>> getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(ArrayList<HashMap<String, Object>> data) {
		this.data = data;
	}
	/**
	 * @return the autoBind
	 */
	public boolean isAutoBind() {
		return autoBind;
	}
	/**
	 * @param autoBind the autoBind to set
	 */
	public void setAutoBind(boolean autoBind) {
		this.autoBind = autoBind;
	}
	public DataSourceOptions(ArrayList<HashMap<String, Object>> data,
			boolean autoBind) {
		super();
		this.data = data;
		this.autoBind = autoBind;
	}
	public DataSourceOptions() {	}
	
}
