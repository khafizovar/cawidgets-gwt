package org.tatasu.gwt.client.kendogwt.grid;

import java.util.ArrayList;
import java.util.HashMap;

import org.tatasu.gwt.client.kendogwt.grid.column.GridColumn;
import org.tatasu.gwt.client.kendogwt.grid.column.ImageColumn;
import org.tatasu.gwt.client.kendogwt.grid.column.ImgTextColumn;
import org.tatasu.gwt.client.kendogwt.grid.events.change.GridChangeCells;
import org.tatasu.gwt.client.kendogwt.grid.events.change.GridChangeEvent;
import org.tatasu.gwt.client.kendogwt.grid.events.change.GridChangeListener;
import org.tatasu.gwt.client.kendogwt.grid.events.columnresize.GridColumnResizeEvent;
import org.tatasu.gwt.client.kendogwt.grid.events.columnresize.GridColumnResizeListener;
import org.tatasu.gwt.client.kendogwt.grid.events.databound.GridDataBoundEvent;
import org.tatasu.gwt.client.kendogwt.grid.events.databound.GridDataBoundListener;
import org.tatasu.gwt.client.kendogwt.grid.options.DataSource;
import org.tatasu.gwt.client.kendogwt.grid.options.GridOptions;
import org.tatasu.gwt.client.kendogwt.grid.options.GridOptionsEnum;
import org.tatasu.gwt.client.kendogwt.grid.utils.DataConverter;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.dom.client.Element;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;

/**
 * Класс Грида
 * 
 * @author HafizovAR
 * 
 */
public class Grid2 extends Widget {

	/** Список колонок */
	//protected ArrayList<GridColumn>					columns;
	/** Локальный источник данных */
	//protected ArrayList<HashMap<String, Object>>	dataProvider;
	protected DataSource 							dataSource;
	/** Опции грида */
	protected GridOptions							gridOptions;

	/** Div элемент который будет выступать родительским элементом */
	private Element									div;
	/** Идентификатор div элемента */
	private String									divElementId;
	/** Массив слушателей событий change */
	private ArrayList<GridChangeListener>			changeListeners			= new ArrayList<GridChangeListener>();
	/** Массив слушателей событий columnResize */
	private ArrayList<GridColumnResizeListener>		columnResizeListeners	= new ArrayList<GridColumnResizeListener>();
	/** Массив слушателей событий dataBound */
	private ArrayList<GridDataBoundListener>		dataBoundListeners		= new ArrayList<GridDataBoundListener>();

	private boolean showEmpty = false;
	public Grid2() {
		super();
		this.divElementId = "kendoGridDiv" + Math.round(Math.random() * 100000);
		div = DOM.createDiv();
		div.setId(divElementId);
		this.setElement(div);
		showEmpty = true;
	}
	public Grid2(GridOptions options, String elementId) {
		super();
		this.divElementId = elementId;
		div = DOM.createDiv();
		div.setId(divElementId);
		this.setElement(div);

		//this.dataProvider = options.getDatasource().getData();
		this.dataSource = options.getDatasource();
		this.gridOptions = options;
	}

	@Override
	protected void onLoad() {
		super.onLoad();
		if(!showEmpty)
			createGrid();
	}

	protected void createGrid() {
		// Родительские опции датагрида
		JSONObject options = new JSONObject();

		// Установка опций дата грида
		options.put(GridOptionsEnum.Option.SELECTABLE.getName(), new JSONString(gridOptions.getSelectable()));
		options.put(GridOptionsEnum.Option.GROUPABLE.getName(), JSONBoolean.getInstance(gridOptions.isGroupable()));
		options.put(GridOptionsEnum.Option.SORTABLE.getName(), JSONBoolean.getInstance(gridOptions.isSortable()));
		options.put(GridOptionsEnum.Option.PAGEABLE.getName(), JSONBoolean.getInstance(gridOptions.isPageable()));
		options.put(GridOptionsEnum.Option.REORDERABLE.getName(), JSONBoolean.getInstance(gridOptions.isReorderable()));
		options.put(GridOptionsEnum.Option.FILTERABLE.getName(), JSONBoolean.getInstance(gridOptions.isFilterable()));
		options.put(GridOptionsEnum.Option.SCROLLABLE.getName(), JSONBoolean.getInstance(gridOptions.isScrollable()));
		options.put(GridOptionsEnum.Option.RESIZABLE.getName(), JSONBoolean.getInstance(gridOptions.isResizable()));
		
		//options.put("editable", JSONBoolean.getInstance(true));

		// Установка колонок
		JSONArray columnsArr = new JSONArray();
		int index = 0;
		for (GridColumn column : gridOptions.getColumnOptions()) {
			JSONObject columnJson = new JSONObject();
			System.out.println(column.getField());
			columnJson.put(GridOptionsEnum.Column.FIELD.getName(), new JSONString(column.getField()));
			columnJson.put(GridOptionsEnum.Column.TITLE.getName(), new JSONString(column.getTitle()));
			columnJson.put(GridOptionsEnum.Column.ENCODED.getName(), JSONBoolean.getInstance(column.isEncoded()));

			if (column instanceof ImageColumn)
				columnJson.put(GridOptionsEnum.Column.TEMPLATE.getName(), new JSONString(((ImageColumn) column).getImageTemplate()));
			if (column instanceof ImgTextColumn) {
				columnJson.put(GridOptionsEnum.Column.TEMPLATE.getName(), new JSONString(((ImgTextColumn) column).getImageTemplate()));
				columnJson.put(GridOptionsEnum.Column.ENCODED.getName(), JSONBoolean.getInstance(false));
			}

			options.put(GridOptionsEnum.Option.COLUMNS.getName(), columnsArr);
			columnsArr.set(index, columnJson);
			index = index + 1;
		}

		
		options.put(GridOptionsEnum.Option.DATASOURCE.getName(), DataConverter.getJSObjectFromArrayHashMap(dataSource.getData(), gridOptions));

		createGrid(this, divElementId, JsonUtils.safeEval(options.toString()));
	}

	@Override
	protected void onUnload() {
		super.onUnload();
	}

	/**
	 * Нативный метод создания kendo grid
	 * 
	 * @param grid
	 * @param id
	 * @param options
	 */
	private native void createGrid(Grid2 grid, String id, JavaScriptObject options) /*-{
		try {
			
			//options.columns[2].editor = function (container, options) {
				//$wnd.alert(JSON.stringify(options));
             //       $wnd.$('<div id="calendar" style="width: 100%;">  <input id="datetimepicker" value="'+ options.model.datesample2 + '" style="width:200px;" /><div>').appendTo(container).kendoDateTimePicker({
                    	//timeFormat: "HH:mm", //24 hours format
             //       	format: "dd.MM.yyyy hh:mm:ss",
    		//			parseFormats: ["dd.MM.yyyy hh:mm:ss"] //format also will be added to parseFormats
             //       });
                    	
                      //  .appendTo(container)
                        //.kendoDropDownList({
                          //  autoBind: false,
                            //dataSource: {
                              //  type: "odata",
                                //transport: {
                                  //  read: "http://demos.kendoui.com/service/Northwind.svc/Categories"
                                //}
                            //}
                        //});
              //  };
			options.dataBound = function(event) {
				grid.@org.tatasu.gwt.client.kendogwt.grid.Grid2::fireDataBoundEvent(Lcom/google/gwt/user/client/Event;)(event);
			};
			options.columnResize = function(event) {
				grid.@org.tatasu.gwt.client.kendogwt.grid.Grid2::fireColumnResizeEvent(Lcom/google/gwt/user/client/Event;IILjava/lang/String;Ljava/lang/String;)(event,event.oldWidth, event.newWidth, event.column.field, event.column.title);
			};
			options.change = function(event) {
				//Возвращает массив выбора                
				var selected = new Array();
				for ( var i = 0; i < this.select().length; i++) {
					var idx = $wnd.$("#" + id).data("kendoGrid").cellIndex(
							this.select()[i]);
					var columnName = this.columns[idx].field;
					var cellvalue = $wnd.$(this.select()[i]).text();
					selected.push({
						'value' : cellvalue,
						'columnIndex' : idx,
						"columnField" : columnName
					});
				}
				grid.@org.tatasu.gwt.client.kendogwt.grid.Grid2::fireOnChangeEvent(Lcom/google/gwt/user/client/Event;Lcom/google/gwt/core/client/JavaScriptObject;)(event, selected);
			};
			$wnd.$("#" + id).kendoGrid(options);
		} catch (error) {
			$wnd.alert(error);
		}
	}-*/;

	/**
	 * Установка данных для grid
	 * 
	 * @param data
	 */
	@Deprecated
	public void setData(ArrayList<HashMap<String, Object>> data) {
		// Уничтожаем существующий датасурс
		destroyDataSource();
		// Подменяем текущие данные
		//dataProvider = data;
		dataSource.setData(data);
		// Вызываем метод создания грида				
		createGrid();
	}
	/**
	 * Установка нового DataSource
	 * @param data	Новый объект ДатаСоурс
	 */
	public void setDataSource(DataSource data) {
		destroyDataSource();
		//dataProvider = data.getData();
		dataSource = data;
		createGrid();
	}
	
	/**
	 * Уничтожение текущего датасурса грида
	 */
	public void destroyDataSource() {
		destroyDataSourceNative(divElementId);
		dataSource = new DataSource();
	}

	/**
	 * Очистка dataSource элемента grid для его повторной установки
	 * 
	 * @param id
	 *            идентификатор div элемента
	 */
	private native void destroyDataSourceNative(String id) /*-{
		if ($wnd.$("#" + id).length > 0 && $wnd.$("#" + id).data().kendoGrid) {
			var thisKendoGrid = $wnd.$("#" + id).data().kendoGrid;

			if (thisKendoGrid.dataSource && thisKendoGrid._refreshHandler) {
				$wnd.$("#" + id).removeData('kendoGrid');
				$wnd.$("#" + id).empty();
			}
		}
	}-*/;
	
	/**
	 * Уничтожение грида
	 * Данный метод не вырезает элемент div контейнера из модели dom
	 * @param id
	 */
	private native void destroyGrid(String id) /*-{
		$wnd.$("#" + id).data("kendoGrid").destroy();
	}-*/;
	/**
	 * Добавление объекта в kendo grid, привязка объекта к гриду осуществляется по ключу String, т.е. String = filedName
	 * Добавление нового объекта приводит к срабатыванию события dataBound
	 * @param row добавляемый объект
	 */
	public void addRow(HashMap<String, Object> row) { 
		//Добавляем в локальный источник данных
		dataSource.getData().add(row);
		addRowNative(divElementId, DataConverter.getJSObjectFromHashMap(row, gridOptions).getJavaScriptObject());//dataS.getJavaScriptObject());
	}
	/**
	 * нативный метод добавления объекта в грид
	 * @param id	идентификатор div контейнера
	 * @param obj	Добавляемый JavaScript объект
	 */
	private native void addRowNative(String id, JavaScriptObject obj) /*-{
		try {
			$wnd.$("#" + id).data('kendoGrid').dataSource.add(obj);
		} catch (error) {
			$wnd.alert(error);
		}

	}-*/;
	/**
	 * Удаление объекта по индексу строки данных
	 * <b>Удаление происходит по текущему индексу ОТОБРАЖАЕМОЙ строки в таблице, т.е. например при постраничном отображении, на странице 2 нумерация будет начинаться также с 0</b>
	 * Строки группировки игнорируются
	 * 
	 * @param index
	 * TODO нет синхронизации с локальным dataSource
	 */
	/*public void removeVisibleItem(int index) {
		removeVisibleItemNative(divElementId, index);
	}*/
	/**
	 * нативный метод удаления отображаемой строки
	 * @param id 	идентификатор элемента
	 * @param rowIndex	индекс отображаемой строки
	 */
	/*public native void removeVisibleItemNative(String id, int rowIndex) /*-{
	    var grid = $wnd.$("#" + id).data("kendoGrid"),
            row = grid.tbody.find(">tr:not(.k-grouping-row)").eq(rowIndex);
			grid.removeRow(row);
	}-*/
	/**
	 * Удаление элемента по индексу в dataSource, после удаления датагриду в js передается новый объект с данными для установки их в грид
	 * @param index
	 */
	public void removeItem(int index) {		
		this.dataSource.getData().remove(index);
		setNewDataSource(divElementId, DataConverter.getJSObjectFromArrayHashMap(this.dataSource.getData(), gridOptions).getJavaScriptObject());
	}
	
	public native void setNewDataSource(String id, JavaScriptObject dataSource)/*-{
			var grid = $wnd.$("#" + id).data("kendoGrid");
			grid.dataSource.data(dataSource);
			grid.refresh();
	}-*/;
	
	/**
	 * Нативный метод удаления выделенных объектов
	 * @param id
	 */
	public native void removeSelecteItemsNative(String id) /*-{
         $wnd.$("#" + id).data("kendoGrid").select().each(function() {
               $wnd.$("#" + id).data("kendoGrid").removeRow($wnd.$(this)); 
         });
	}-*/;
	
	@Override
	public void setSize(String width, String height) {
		super.setSize(width, height);
		div.setAttribute("width", width);
		div.setAttribute("height", height);
		setSizeNative(width, height, divElementId);
	}

	/**
	 * Нативный метод установки размеров виджета. После установки размеров
	 * контейнера вызывается метод обновления грида без которого новые размеры
	 * не применяются
	 * 
	 * @param width
	 *            ширина, примеры значений: "70%" , "300px" и т.д.
	 * @param height
	 *            высота, может принимать зачения аналогичные ширине
	 * @param elId
	 *            идентификатор div элемента-контейнера
	 */
	private native void setSizeNative(String width, String height, String elId) /*-{
		$wnd.$('#' + elId).height(height);
		$wnd.$('#' + elId).width(width);
		if ($wnd.$('#' + elId).data("kendoGrid") != 'undefined'
				&& $wnd.$('#' + elId).data("kendoGrid") != undefined)
			$wnd.$('#' + elId).data("kendoGrid").refresh();
		else {
			$wnd.$(document).ready(function() {		   
				$wnd.$('#' + elId).height(height);
				$wnd.$('#' + elId).width(width);
			});
		}
	}-*/;

	public void debug() {
		Window.alert(div.getAttribute("width") + " " + div.getAttribute("height"));
	}

	/***************************************** Events Fire *********************************************/
	/**
	 * "Выстрел" события смены выбора
	 * 
	 * @param event
	 * @param selectedvalues
	 */
	private void fireOnChangeEvent(Event event, JavaScriptObject selectedvalues) {
		final String CONST_VALUE = "value";
		final String CONST_COLUMN_INDEX = "columnIndex";
		final String CONST_COLUMN_FIELD = "columnField";
		// Массив с данными выбранных ячеек
		ArrayList<GridChangeCells> cells = new ArrayList<GridChangeCells>();
		// ОБъект который фактически является массивом, но приходит с ключами
		// "0", "1", "2" и т.д.
		JSONObject jsonValue = new JSONObject(selectedvalues);
		for (int i = 0;; i++) {
			if (jsonValue.containsKey(i + "")) {
				JSONValue jsValue = jsonValue.get(i + "");
				JSONObject cellData = jsValue.isObject();
				cells.add(new GridChangeCells(cellData.get(CONST_VALUE).toString(), Integer.parseInt(cellData.get(CONST_COLUMN_INDEX).toString()),
						cellData.get(CONST_COLUMN_FIELD).toString()));
			} else {
				break;
			}
		}
		GridChangeEvent eventJ = new GridChangeEvent(event, this, cells);

		for (GridChangeListener listener : changeListeners) {
			// listener.onChange(sliderEvent);
			listener.change(eventJ);
		}
	}

	/**
	 * "Выстрел" события изменения размера колонки
	 * 
	 * @param event
	 *            оригинал события
	 * @param oldValue
	 *            старое значение ширины колонки
	 * @param newValue
	 *            новое значение ширины колонки
	 * @param columnField
	 *            Наименование колонки (поле field)
	 * @param columnTitle
	 *            Заголовок колонки (поле title)
	 */
	private void fireColumnResizeEvent(Event event, int oldValue, int newValue, String columnField, String columnTitle) {
		GridColumnResizeEvent eventJ = new GridColumnResizeEvent(event, oldValue, newValue, columnField, columnTitle, this);

		for (GridColumnResizeListener listener : columnResizeListeners) {
			listener.columnResize(eventJ);
		}
	}

	/**
	 * "Выстрел" события установки данных в гриде
	 * 
	 * @param event
	 *            оригинальное событие
	 */
	private void fireDataBoundEvent(Event event) {
		GridDataBoundEvent eventJ = new GridDataBoundEvent(this);

		for (GridDataBoundListener listener : dataBoundListeners) {
			listener.dataBoud(eventJ);
		}
	}

	/**************************************** Methods for grid listener *******************************/

	/**
	 * Добавление слушателя смены выбора в гриде
	 * 
	 * @param listener
	 *            регистрируемый слушатель
	 */
	public void addChangeListener(GridChangeListener listener) {
		changeListeners.add(listener);
	}

	/**
	 * Удаление слушателя смены выбора в гриде
	 * 
	 * @param listener
	 *            зарегистрированный слушатель
	 */
	public void removeChangeListener(GridChangeListener listener) {
		changeListeners.remove(listener);
	}

	/**
	 * Добавление слушателя событий "ресайза" колонки
	 * 
	 * @param listener
	 *            регистрируемый слушатель
	 */
	public void addColumnResizeListener(GridColumnResizeListener listener) {
		columnResizeListeners.add(listener);
	}

	/**
	 * Удаление слушателя события "ресайза" колонки
	 * 
	 * @param listener
	 *            зарегистрированный слушатель
	 */
	public void removeColumnResizeListener(GridColumnResizeListener listener) {
		columnResizeListeners.remove(listener);
	}

	/**
	 * Добавление слушателя событий установки данных грида. Событие
	 * "выстреливает" когда данные из dataSource будут установлены в гриде (т.е.
	 * отображены визуально)
	 * 
	 * @param listener
	 *            регистрируемый слушатель
	 */
	public void addDataBoundListener(GridDataBoundListener listener) {
		dataBoundListeners.add(listener);
	}

	/**
	 * Удаление слушателя события установки данных грида.
	 * 
	 * @param listener
	 *            зарегистрированный слушатель
	 */
	public void removeDataBoundListener(GridDataBoundListener listener) {
		dataBoundListeners.remove(listener);
	}
}
