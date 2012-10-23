package org.tatasu.gwt.client.kendogwt.grid.options;

import com.google.gwt.json.client.JSONObject;

public class ColumnsOptions {
	/**
	 * Опция идентификатор колонки, значение используется как ключ (для однозначной идентификации поля с данными) из HashMap, все пробелы из значения будут удалены
	 */
	private String field = null;
	/**
	 * Наименование столбца соответствуемого данному полю 
	 */
    private String title = null;
    /**
     * Ширина колонки, в пикселях
     */
    private int width = -1;
    /**
     * Столбец сортрруемый
     */
    private boolean sortable = true; 
    /**
     * Шаблон отображения
     */
	private String template = null;
	public ColumnsOptions() {}
	
	
	public ColumnsOptions(String field, String title, int width,
			boolean sortable, String template, boolean resizable,
			boolean reorderable, String format, boolean encoded,
			boolean filterable) {
		super();
		this.field = field;
		this.title = title;
		this.width = width;
		this.sortable = sortable;
		this.template = template;
		this.resizable = resizable;
		this.reorderable = reorderable;
		this.format = format;
		this.encoded = encoded;
		this.filterable = filterable;
	}


	/**
	 * Изменяемый размер
	 */
	private boolean resizable = true;
	/**
	 * Колонка "с перетаскиванием"
	 */
    private boolean reorderable = false;
    /**
     * Формат значения
     */
	private String format = null;
	/**
	 * false - все содержимое будет восприниматься как html, false - данные внутри будут интерпретироваться как текст
	 */
	private boolean encoded = true;
	/**
	 * Столбец использует фильтры
	 */
	private boolean filterable = false;
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
	 * @return the sortable
	 */
	public boolean isSortable() {
		return sortable;
	}
	/**
	 * @param sortable the sortable to set
	 */
	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}
	/**
	 * @return the template
	 */
	public String getTemplate() {
		return template;
	}
	/**
	 * @param template the template to set
	 */
	public void setTemplate(String template) {
		this.template = template;
	}
	/**
	 * @return the resizable
	 */
	public boolean isResizable() {
		return resizable;
	}
	/**
	 * @param resizable the resizable to set
	 */
	public void setResizable(boolean resizable) {
		this.resizable = resizable;
	}
	/**
	 * @return the reorderable
	 */
	public boolean isReorderable() {
		return reorderable;
	}
	/**
	 * @param reorderable the reorderable to set
	 */
	public void setReorderable(boolean reorderable) {
		this.reorderable = reorderable;
	}
	/**
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}
	/**
	 * @param format the format to set
	 */
	public void setFormat(String format) {
		this.format = format;
	}
	/**
	 * @return the encoded
	 */
	public boolean isEncoded() {
		return encoded;
	}
	/**
	 * @param encoded the encoded to set
	 */
	public void setEncoded(boolean encoded) {
		this.encoded = encoded;
	}
	/**
	 * @return the filterable
	 */
	public boolean isFilterable() {
		return filterable;
	}
	/**
	 * @param filterable the filterable to set
	 */
	public void setFilterable(boolean filterable) {
		this.filterable = filterable;
	}
	
	
	public JSONObject getJSONObject() {
		return null;
	}
}
