package org.tatasu.gwt.client.cawidgets.dropdown;
/**
 * Бин класс принимаемый в качестве данных для установки виджетом DropDownWidget
 * @author HafizovAR
 */
public class DropDownWidgetBean {
	
	/** Ссылка на изображение */
	private String title;
	/** Текст на строке выпадающего списка */
	private String innerText;
	/** Значение которое будет содержаться в поле value */
	private String value;
	
	public DropDownWidgetBean() {}
	
	public DropDownWidgetBean(String title, String innerText, String value) {
		super();
		this.title = title;
		this.innerText = innerText;
		this.value = value;
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
	 * @return the innerText
	 */
	public String getInnerText() {
		return innerText;
	}
	/**
	 * @param innerText the innerText to set
	 */
	public void setInnerText(String innerText) {
		this.innerText = innerText;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
