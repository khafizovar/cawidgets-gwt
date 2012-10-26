package org.tatasu.gwt.client.kendogwt.grid.options;

import java.util.ArrayList;

import org.tatasu.gwt.client.kendogwt.grid.core.GridColumn;

public class GridOptions {
	
	public static final String SELECTABLE_NONE = "none";
	public static final String SELECTABLE_ROW = "row";
	public static final String SELECTABLE_CELL = "cell";
	public static final String SELECTABLE_ROW_MULTIPLE = "multiple, row";
	public static final String SELECTABLE_CELL_MULTIPLE = "multiple, cell";
	
	/**
	 * Сделать отображение постраничным, по умочланию false
	 */
    private boolean pageable  = false;
    /**
     * Опции для датасурса, см enum DataSource, по умолчанию см опции по умолчиню объекта DataSourceOptions
     */
    private DataSourceOptions datasource = new DataSourceOptions();
    /**
     * Массив с колонками, см enum Column
     */
    private ArrayList<GridColumn> columnOptions = null;	
    /**
     * Группируемые, поумолчанию false
     */
	private boolean groupable = false;
	/**
	 * Использовать фильтр, по умолчанию false
	 */
	private boolean filterable = false; 
	/**
	 * Разрешает/запрещает использование скроллов, по умолчиню false
	 */
	private boolean scrollable = false; 
	/**
	 * Разрешить сортировку, по умолчанию true
	 */
	private boolean sortable = true;
	/**
	 * Позволяет перетаскиванием изменять порядок столбцов, по умолчанию false
	 */
	private boolean reorderable = false;
	/**
	 * Включение режима выбора, возможные значения "row" Single row selection. "cell" Single cell selection. "multiple, row" Multiple row selection. "multiple, cell" Multiple cell selection.
	 */
	private String selectable = "none";
	/**
	 * Включение/выключение изменение размера колонок 
	 */
	private boolean resizable = true;
	
	public GridOptions() { }
	
	public GridOptions(boolean pageable, DataSourceOptions datasource,
			ArrayList<GridColumn> columnOptions, boolean groupable,
			boolean filterable, boolean scrollable, boolean sortable,
			boolean reorderable, String selectable) {
		super();
		this.pageable = pageable;
		this.datasource = datasource;
		this.columnOptions = columnOptions;
		this.groupable = groupable;
		this.filterable = filterable;
		this.scrollable = scrollable;
		this.sortable = sortable;
		this.reorderable = reorderable;
		this.selectable = selectable;
	}





	/**
	 * @return the pageable
	 */
	public boolean isPageable() {
		return pageable;
	}
	/**
	 * @param pageable the pageable to set
	 */
	public void setPageable(boolean pageable) {
		this.pageable = pageable;
	}
	/**
	 * @return the datasource
	 */
	public DataSourceOptions getDatasource() {
		return datasource;
	}
	/**
	 * @param datasource the datasource to set
	 */
	public void setDatasource(DataSourceOptions datasource) {
		this.datasource = datasource;
	}
	/**
	 * @return the columnOptions
	 */
	public ArrayList<GridColumn> getColumnOptions() {
		return columnOptions;
	}
	/**
	 * @param columnOptions the columnOptions to set
	 */
	public void setColumnOptions(ArrayList<GridColumn> columnOptions) {
		this.columnOptions = columnOptions;
	}
	/**
	 * @return the groupable
	 */
	public boolean isGroupable() {
		return groupable;
	}
	/**
	 * @param groupable the groupable to set
	 */
	public void setGroupable(boolean groupable) {
		this.groupable = groupable;
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
	/**
	 * @return the scrollable
	 */
	public boolean isScrollable() {
		return scrollable;
	}
	/**
	 * @param scrollable the scrollable to set
	 */
	public void setScrollable(boolean scrollable) {
		this.scrollable = scrollable;
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
	 * @return the selectable
	 */
	public String getSelectable() {
		return selectable;
	}
	/**
	 * @param selectable the selectable to set
	 */
	public void setSelectable(String selectable) {
		this.selectable = selectable;
	}
	
	public ArrayList<String> getArrayFields() {
		if(columnOptions != null) {
			ArrayList<String> rez = new ArrayList<String>();
			for (GridColumn string : columnOptions) {
				rez.add(string.getField());
			}			
			return rez;
		} else return null;
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
}
