package org.tatasu.gwt.client.kendogwt.grid.core;

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
	
	
	public GridColumn(String field, String title, int width) {
		//super();
		this.field = field.replaceAll(" ", "");
		this.title = title;
		this.width = width;
	}


	public GridColumn(String field, String title) {
		//super();
		this.field = field;
		this.title = title;
	}


	public GridColumn( String title, String field, Integer width) {
		/*public enum Column {			
			FIELD("field"),
	        TITLE("title"),
	        WIDTH("width"),
			TEMPLATE("template"),
			FORMAT("format");
			private String name;		
			private Column(String name) {
				this.name = name;
			}		
			public String getName() {
				return this.name;
			}
		}*/		
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
