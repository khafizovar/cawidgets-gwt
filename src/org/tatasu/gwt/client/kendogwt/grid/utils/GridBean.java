package org.tatasu.gwt.client.kendogwt.grid.utils;

import java.util.ArrayList;

public interface GridBean {
	/**
	 * Возвращает наименование всех полей, используемых в последствии для устанвоки наименовании столбцов
	 * @return Лист со списком полей
	 */
	public  ArrayList<String> getBeanFields();
	
	/**
	 * Возвращает значение поля по его имени
	 * @param fieldName	Наименованеи поля.
	 * @return
	 */
	public Object getValueByFieldName(String fieldName);
}
