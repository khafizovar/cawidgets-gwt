package org.tatasu.gwt.client.kendogwt.grid.items;

public class Cell {
	/**
	 * Кастомный css набор элемента
	 */
	protected String customCss = null;
	private Object value = null;
	
	public Cell() {
		
	}
	
	public Cell(Object value) {
		this.value = value;
	}
	/**
	 * Установить  css стиль данной ячейки, стиль применяется к div элементу оборочивающего значение ячейки
	 * @param cssString
	 */
	public void setCellCss(String cssString) {
		this.customCss = cssString;
	}
	/**
	 * Получить css ситль div элемента оборачивающего значение ячейки
	 * @return
	 */
	public String getCellCss() {
		return customCss;
	}
	
	@Override
	public String toString() {
		String rez = "<div";
		if (customCss != null) {
			rez += " style=\"" + customCss + "\"";
		}
		rez += ">";
		if (value != null) {
			rez += value.toString();
		}
		rez += "</div>";
		return rez;
	}
}
