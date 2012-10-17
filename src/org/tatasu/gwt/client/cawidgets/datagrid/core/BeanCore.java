package org.tatasu.gwt.client.cawidgets.datagrid.core;

import java.util.ArrayList;
/**
 * Интерфейс  расширения для всех bean классов используемых компонентой таблица
 * @author HafizovAR
 *
 */
public interface BeanCore {
	/**
	 * Метод реализующий возврат всех полей бин класса
	 * Пример кода
	 *  {@code
	 *  	@Override
	 *		public ArrayList<String> getFieldsNameArray() {		
	 *			ArrayList<String> arr =new ArrayList<String>();
	 *			arr.add("id");
	 *			arr.add("well");
	 *			arr.add("pgOutput");
	 *			arr.add("settingDepth");		
	 *			return arr;
	 *		}
	 *	}
	 *	TODO не совсем корректно реализовывать этот метод для всех экземпляров бин класса, 
	 *	но другого решения я пока не нашел. Java reflection GWT на клиенте не держит
	 *  @return Массив с наименованиями всех полей бин класса
	 */
	public ArrayList<String> getFieldsNameArray();
	
	/**
	 * Метод возврата значения по наименованию поля 
	 * @param fieldName наименование поля bean класса
	 * @return значение поля класса 
	 */
	public Object getValueByFieldName(String fieldName);
	
	/**
	 * Получить наименование колонки по наименованию поля
	 * @param fieldName Наименование поля
	 * @return	наименование колонки
	 */
	public String getColumnNameByFieldName (String fieldName);
	
	/**
	 * Получить хедер всплывающего окна для данного бина
	 * @return Заголовок всплывающего окна
	 */
	/*public SafeHtml getToolTipHeader();*/
	
	/**
	 * Получить тело всплывающего окна для данного экземпляра бина
	 * @return Телов сплывающего окна
	 */
	/*public SafeHtml getToolTipBody();*/
	
	/**
	 * Проверка значения на вшивость,
	 * Если проверка не предвидется, просто возвращаем true
	 * @param fieldname если false - вшивое значение ставим восклицательный знак, true - значение в норме
	 * @return
	 */
	public boolean checkingForLice(String fieldname);
	
	/**
	 * Выборка tooltip контента для данного конкретного экземпляра бина
	 * @param fieldname	Наименование колонки
	 * @return
	 */
	public String getToolTipHtmlContext(String fieldname);
	/**
	 * Выборка CSS параметра rgba для данной ячейки, 
	 * @param fieldName
	 * @return	Строка вида rgba(255,255,255, 0.5), данная строка будет добавлена к стилю данной ячейки, либо можно просто вернуть null, без ;
	 */
	public String getRGBAStringForCell(String fieldName);
	
}
