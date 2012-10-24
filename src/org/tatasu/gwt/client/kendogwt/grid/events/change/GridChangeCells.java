package org.tatasu.gwt.client.kendogwt.grid.events.change;


public class GridChangeCells {
	/** Данные ячейки, все данные приходят типа String */
	private String cellData;
	/** Текущий (!) индекс колонки в котором находится ячейка */
	private int columnIndex;
	/** Наименование колонки в которой находится ячейка */
	private String columnFieldName;
	
	public GridChangeCells(String cellData, int columnIndex, String columnFieldName) {
		super();
		this.cellData = cellData;
		this.columnIndex = columnIndex;
		this.columnFieldName = columnFieldName;
	}

	
	/**
	 * @return the cellData
	 */
	public String getCellData() {
		return cellData;
	}

	
	/**
	 * @return the columnIndex
	 */
	public int getColumnIndex() {
		return columnIndex;
	}

	
	/**
	 * @return the columnFieldName
	 */
	public String getColumnFieldName() {
		return columnFieldName;
	}
}
