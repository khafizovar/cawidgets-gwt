package org.tatasu.gwt.client.kendogwt.grid;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import org.tatasu.gwt.client.kendogwt.grid.core.GridColumn;
import org.tatasu.gwt.client.kendogwt.grid.core.ImageColumn;
import org.tatasu.gwt.client.kendogwt.grid.core.ImgTextColumn;
import org.tatasu.gwt.client.kendogwt.grid.events.change.GridChangeCells;
import org.tatasu.gwt.client.kendogwt.grid.events.change.GridChangeEvent;
import org.tatasu.gwt.client.kendogwt.grid.events.change.GridChangeListener;
import org.tatasu.gwt.client.kendogwt.grid.events.columnresize.GridColumnResizeEvent;
import org.tatasu.gwt.client.kendogwt.grid.events.columnresize.GridColumnResizeListener;
import org.tatasu.gwt.client.kendogwt.grid.events.databound.GridDataBoundEvent;
import org.tatasu.gwt.client.kendogwt.grid.events.databound.GridDataBoundListener;
import org.tatasu.gwt.client.kendogwt.grid.options.GridOptions;
import org.tatasu.gwt.client.kendogwt.grid.options.GridOptionsEnum;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.dom.client.Element;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNumber;
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
 *         TODO 1. Remove Item 2. Remove Item Event
 * 
 */
public class Grid2 extends Widget {

	/** Список колонок */
	protected ArrayList<GridColumn>					columns;
	/** Локальный источник данных */
	protected ArrayList<HashMap<String, Object>>	localData;
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

	public Grid2(GridOptions options, String elementId) {
		super();
		this.divElementId = elementId;
		div = DOM.createDiv();
		div.setId(elementId);
		this.setElement(div);

		this.localData = options.getDatasource().getData();
		this.gridOptions = options;
	}

	@Override
	protected void onLoad() {
		super.onLoad();
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

		// Установка колонок
		// Получаем все наименования полей
		// ArrayList<String> columnNamesFromHashMap =
		// GridHashMapParser.getKeysName(localData);
		JSONArray columnsArr = new JSONArray();
		int index = 0;
		for (GridColumn column : gridOptions.getColumnOptions()) {
			JSONObject columnJson = new JSONObject();
			System.out.println(column.getField());
			columnJson.put(GridOptionsEnum.Column.FIELD.getName(), new JSONString(column.getField()));
			columnJson.put(GridOptionsEnum.Column.TITLE.getName(), new JSONString(column.getTitle()));
			columnJson.put(GridOptionsEnum.Column.ENCODED.getName(), JSONBoolean.getInstance(column.isEncoded()));
			// columnJson.put(GridOptionsEnum.Column.FILTERABLE.getName(),
			// JSONBoolean.getInstance(column.isFilterable()));
			// if(column.getFormat() != null)
			// columnJson.put(GridOptionsEnum.Column.FORMAT.getName(), new
			// JSONString(column.getFormat()));

			// columnJson.put(GridOptionsEnum.Column.REORDERABLE.getName(),
			// JSONBoolean.getInstance(column.isReorderable()));

			// columnJson.put(GridOptionsEnum.Column.RESIZABLE.getName(),
			// JSONBoolean.getInstance(column.isReorderable()));

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

		// / Инициализация модели данных
		JSONObject dataSource = new JSONObject();
		JSONObject dataS = new JSONObject();
		JSONArray dataArr = new JSONArray();
		int index2 = 0;
		// Обход массива данных
		for (HashMap<String, Object> t : localData) {
			dataS = new JSONObject();
			for (String fieldName : gridOptions.getArrayFields()) {
				if (t.get(fieldName) instanceof String) {
					dataS.put(fieldName, new JSONString(t.get(fieldName).toString()));
				} else if (t.get(fieldName) instanceof Long) {
					dataS.put(fieldName, new JSONNumber((Long) t.get(fieldName)));
				} else if (t.get(fieldName) instanceof Double) {
					dataS.put(fieldName, new JSONNumber((Double) t.get(fieldName)));
				} else if (t.get(fieldName) instanceof Integer) {
					dataS.put(fieldName, new JSONNumber((Integer) t.get(fieldName)));
				} else if (t.get(fieldName) instanceof Date) {
					DateTimeFormat fmt = DateTimeFormat.getFormat("dd.MM.yyyy HH:mm:ss");
					dataS.put(fieldName, new JSONString(fmt.format(((Date) t.get(fieldName)))));
					// } else if (t.get(fieldName) instanceof DateCell) {

					// } else if (t.get(fieldName) instanceof ImgTextCell) {
					// dataS.put(fieldName, new
					// JSONString(t.get(fieldName).toString()));
				} else {
					if(t.get(fieldName) != null)
						dataS.put(fieldName, new JSONString(t.get(fieldName).toString()));
					else 
						dataS.put(fieldName, new JSONString(""));
				}
			}
			dataArr.set(index2, dataS);
			index2 = index2 + 1;
		}
		dataSource.put(GridOptionsEnum.DataSource.DATA.getName(), dataArr);
		// dataSource.put(DataSource.AUTOBIND.getName(), new
		// JSONString("true"));
		// Конец инициализации модели данных
		options.put(GridOptionsEnum.Option.DATASOURCE.getName(), dataSource);

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
			//options.start = function(event, ui) {
			//	slider.@org.tatasu.modules.slider.Slider::fireOnStartEvent(Lcom/google/gwt/user/client/Event;Lcom/google/gwt/core/client/JsArrayInteger;)(event, ui.values);
			//};
			//options.slide = function(event, ui) {
			//	return slider.@org.tatasu.modules.slider.Slider::fireOnSlideEvent(Lcom/google/gwt/user/client/Event;Lcom/google/gwt/core/client/JsArrayInteger;)(event, ui.values);
			//};
			//options.change = function(event, ui) {
			//	var hasChange = event.originalEvent ? true : false;
			//	slider.@org.tatasu.modules.slider.Slider::fireOnChangeEvent(Lcom/google/gwt/user/client/Event;Lcom/google/gwt/core/client/JsArrayInteger;Z)(event, ui.values, hasChange);
			//};
			//options.stop = function(event, ui) {
			//	slider.@org.tatasu.modules.slider.Slider::fireOnStopEvent(Lcom/google/gwt/user/client/Event;Lcom/google/gwt/core/client/JsArrayInteger;)(event, ui.values);
			//};
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
	public void setData(ArrayList<HashMap<String, Object>> data) {
		// Уничтожаем существующий датасурс
		destroyDataSource();
		// Подменяем текущие данные
		localData = data;
		// Вызываем метод создания грида				
		createGrid();
		// TODO добавить метод установки данных
		/*
		JSONArray dataArr = new JSONArray();
		int index2 = 0;
		// Обход массива данных
		for (HashMap<String, Object> t : localData) {
			JSONObject dataS = new JSONObject();
			for (String fieldName : gridOptions.getArrayFields()) {
				if (t.get(fieldName) instanceof String) {
					dataS.put(fieldName, new JSONString(t.get(fieldName).toString()));
				} else if (t.get(fieldName) instanceof Long) {
					dataS.put(fieldName, new JSONNumber((Long) t.get(fieldName)));
				} else if (t.get(fieldName) instanceof Double) {
					dataS.put(fieldName, new JSONNumber((Double) t.get(fieldName)));
				} else if (t.get(fieldName) instanceof Integer) {
					dataS.put(fieldName, new JSONNumber((Integer) t.get(fieldName)));
				} else if (t.get(fieldName) instanceof Date) {
					DateTimeFormat fmt = DateTimeFormat.getFormat("dd.MM.yyyy HH:mm:ss");
					dataS.put(fieldName, new JSONString(fmt.format(((Date) t.get(fieldName)))));
					// } else if (t.get(fieldName) instanceof DateCell) {

					// } else if (t.get(fieldName) instanceof ImgTextCell) {
					// dataS.put(fieldName, new
					// JSONString(t.get(fieldName).toString()));
				} else {
					if(t.get(fieldName) != null)
						dataS.put(fieldName, new JSONString(t.get(fieldName).toString()));
					else 
						dataS.put(fieldName, new JSONString(""));
				}
			}
			dataArr.set(index2, dataS);
			index2 = index2 + 1;
		}
		
		JSONObject dataSource = new JSONObject();
		dataSource.put(GridOptionsEnum.DataSource.DATA.getName(), dataArr);
		setGridData(divElementId, dataSource.getJavaScriptObject());*/
	}
	
	/**
	 * Уничтожение текущего датасурса грида
	 */
	public void destroyDataSource() {
		destroyDataSourceNative(divElementId);
		localData = new ArrayList<HashMap<String,Object>>();
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
	 * Добавление объекта в kendo grid, привязка объекта к гриду осуществляется по ключу String, т.е. String = filedName
	 * Добавление нового объекта приводит к срабатыванию события dataBound
	 * @param row добавляемый объект
	 */
	public void addRow(HashMap<String, Object> row) { 
		JSONObject dataS = new JSONObject();
		for (String fieldName : gridOptions.getArrayFields()) {
			if (row.get(fieldName) instanceof String) {
				dataS.put(fieldName, new JSONString(row.get(fieldName).toString()));
			} else if (row.get(fieldName) instanceof Long) {
				dataS.put(fieldName, new JSONNumber((Long) row.get(fieldName)));
			} else if (row.get(fieldName) instanceof Double) {
				dataS.put(fieldName, new JSONNumber((Double) row.get(fieldName)));
			} else if (row.get(fieldName) instanceof Integer) {
				dataS.put(fieldName, new JSONNumber((Integer) row.get(fieldName)));
			} else if (row.get(fieldName) instanceof Date) {
				DateTimeFormat fmt = DateTimeFormat.getFormat("dd.MM.yyyy HH:mm:ss");
				dataS.put(fieldName, new JSONString(fmt.format(((Date) row.get(fieldName)))));
			} else {
				if(row.get(fieldName) != null)
					dataS.put(fieldName, new JSONString(row.get(fieldName).toString()));
				else 
					dataS.put(fieldName, new JSONString(""));
			}
		}
		//Добавляем в локальный источник данных
		localData.add(row);
		addRowNative(divElementId, dataS.getJavaScriptObject());
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
	 * Удаление объекта по объекту !ВНИМАНИЕ будут удалены все строки значения
	 * которых совпадают с указанными значениями
	 * 
	 * @param bean
	 */
	/*
	 * public void removeItem(T bean) {
	 * 
	 * }
	 */
	/**
	 * Удаление объекта по индексу в источнике данных
	 * 
	 * @param index
	 */
	public void removeItem(int index) {

	}

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
				//$wnd.alert('q44:' + $wnd.$('#' + elId).height() + " " + $wnd.$('#' + elId).width());			   
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
		// 'value' :cellvalue, 'columnIndex':idx, "columnField"
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
