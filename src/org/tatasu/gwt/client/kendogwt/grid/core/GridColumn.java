package org.tatasu.gwt.client.kendogwt.grid.core;
/**
 * Класс колонки используемый для создания объектов Колонок
 * @author HafizovAR
 *
 */
public class GridColumn {
	/** Используемое поле */
	protected String field;
	/** Наименование колонки */
	protected String title;
	/** Ширина колонки */
	protected int width = -1;
	/** Видимость колонки */
	protected boolean visible = true;
	
//	private String template;	
//	private String format;
	
	/**
	 * Конструктор
	 * @param field идентификатор поля с данными
	 * @param title отображаемый в заголовке текст
	 * @param width явно заданная ширина колонки 
	 */
	public GridColumn(String field, String title, int width) {
		//super();
		this.field = field.replaceAll(" ", "");
		this.title = title;
		this.width = width;
	}

	/**
	 * Конструктор
	 * @param field идентификатор поля с данными
	 * @param title отображаемый в заголовке текст
	 */
	public GridColumn(String field, String title) {
		//super();
		this.field = field;
		this.title = title;
	}

	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}


	/**
	 * @param field the field to set
	 */
	public void setField(String field) {
		this.field = field;
	}


	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}


	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}


	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}


	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}


	/**
	 * @return the template
	 */
	/*public String getTemplate() {
		return template;
	}*/


	/**
	 * @param template the template to set
	 */
	/*public void setTemplate(String template) {
		this.template = template;
	}*/


	/**
	 * @return the format
	 */
	/*public String getFormat() {
		return format;
	}*/


	/**
	 * @param format the format to set
	 */
	/*public void setFormat(String format) {
		this.format = format;
	}*/
}
