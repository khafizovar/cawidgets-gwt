package org.tatasu.gwt.client.kendogwt.grid;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.zip.DataFormatException;

import org.tatasu.gwt.client.kendogwt.grid.core.GridColumn;
import org.tatasu.gwt.client.kendogwt.grid.core.ImageColumn;
import org.tatasu.gwt.client.kendogwt.grid.core.ImgTextColumn;
import org.tatasu.gwt.client.kendogwt.grid.events.GridListener;
import org.tatasu.gwt.client.kendogwt.grid.events.change.GridChangeCells;
import org.tatasu.gwt.client.kendogwt.grid.events.change.GridChangeEvent;
import org.tatasu.gwt.client.kendogwt.grid.events.change.GridChangeListener;
import org.tatasu.gwt.client.kendogwt.grid.items.ImgTextCell;
import org.tatasu.gwt.client.kendogwt.grid.options.GridOptions;
import org.tatasu.gwt.client.kendogwt.grid.options.GridOptionsEnum;
import org.tatasu.gwt.client.kendogwt.grid.utils.GridHashMapParser;

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
	/** Массив слушателей событий */
	private ArrayList<GridChangeListener> listeners = new ArrayList<GridChangeListener>();

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
				//} else if (t.get(fieldName) instanceof DateCell) {
					
				//} else if (t.get(fieldName) instanceof ImgTextCell) {
				//	dataS.put(fieldName, new JSONString(t.get(fieldName).toString()));
				} else {
					dataS.put(fieldName, new JSONString(t.get(fieldName).toString()));
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
			options.change = function(event) {
				//Возвращает массив выбора
				//var selected =  $wnd.$.map(this.select(), function(item) {
						//var textI = $wnd.$(item).text();
						//var indexI = $wnd.$(item).index();
						//var columnName = this.columns[3]
                       // var locArr = {'value' :textI, 'columnIndex':indexI};                        
                        //return  $wnd.$(item).text();
                       // return locArr;
                //});
                
                var selected = new Array();
                for(var i = 0; i<this.select().length; i++) {
						var idx =  $wnd.$("#" + id).data("kendoGrid").cellIndex(this.select()[i]);
						var columnName = this.columns[idx].field;
						var cellvalue = $wnd.$(this.select()[i]).text();
						selected.push({'value' :cellvalue, 'columnIndex':idx, "columnField":columnName});
					}
			
                
				//var index = $(event.item).index();
				//var text = $(event.item).text();
				//console.log('selected item contains text: ',text,' and its index is: ',index);
                //kendoConsole.log("Selected: " + selected.length + " item(s), [" + selected.join(", ") + "]");
				//grid.@org.tatasu.gwt.client.kendogwt.grid.Grid2::fireEvent(Lcom/google/gwt/user/client/Event;)(event,selected)//:fireOnChangeEvent(Lcom/google/gwt/user/client/Event;)(event)
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
		// Вызываем метод создания грида
		// createGridModwlWithData();
		// TODO добавить метод установки данных
	}

	private native void setGridData(String id, JavaScriptObject dataSourceOptions) /*-{
		try {
			$wnd.temporaryDataSource = new $wnd.kendo.data.DataSource(
					dataSourceOptions);
			$wnd.$("#" + id).kendoGrid($wnd.temporaryDataSource);
			$wnd.$("#" + id).data("kendoGrid").dataSource.read();
		} catch (error) {
			$wnd.alert(error);
		}
	}-*/;

	public void destroyDataSource() {
		destroy(divElementId);
	}

	/**
	 * Очистка dataSource элемента grid для его повторной установки
	 * 
	 * @param id
	 *            идентификатор div элемента
	 */
	private native void destroy(String id) /*-{
		if ($wnd.$("#" + id).length > 0 && $wnd.$("#" + id).data().kendoGrid) {
			var thisKendoGrid = $wnd.$("#" + id).data().kendoGrid;

			if (thisKendoGrid.dataSource && thisKendoGrid._refreshHandler) {
				$wnd.$("#" + id).removeData('kendoGrid');
				$wnd.$("#" + id).empty();
			}
		}
	}-*/;

	/**
	 * Добавление строки в kendo grid
	 * 
	 * @param bean
	 */
	/*
	 * public void addRow(HashMap row) { dataModel.add(bean); JSONObject dataS =
	 * new JSONObject(); for (String fieldName : bean.getBeanFields()) {
	 * if(bean.getValueByFieldName(fieldName) instanceof String) {
	 * dataS.put(fieldName, new JSONString((String)
	 * bean.getValueByFieldName(fieldName))); } else
	 * if(bean.getValueByFieldName(fieldName) instanceof Double) {
	 * dataS.put(fieldName, new JSONNumber((Double)
	 * bean.getValueByFieldName(fieldName))); } else
	 * if(bean.getValueByFieldName(fieldName) instanceof Integer) {
	 * dataS.put(fieldName, new JSONNumber((Integer)
	 * bean.getValueByFieldName(fieldName))); } else
	 * if(bean.getValueByFieldName(fieldName) instanceof Date) { //TODO добавить
	 * форматтер либо темплейт kendo dataS.put(fieldName, new
	 * JSONString(((Date)bean.getValueByFieldName(fieldName)).toString())); } }
	 * addRowNative(divElementId, dataS.getJavaScriptObject()); }
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
	
	private void fireOnChangeEvent(Event event, JavaScriptObject selectedvalues) {
		//'value' :cellvalue, 'columnIndex':idx, "columnField"
		final String CONST_VALUE = "value";
		final String CONST_COLUMN_INDEX = "columnIndex";
		final String CONST_COLUMN_FIELD = "columnField";
		//Массив с данными выбранных ячеек 
		ArrayList<GridChangeCells> cells = new ArrayList<GridChangeCells>();
		//ОБъект который фактически является массивом, но приходит с ключами "0", "1", "2" и т.д.
		JSONObject jsonValue = new JSONObject(selectedvalues);
		//System.out.println(jsonValue);
		for (int i = 0;; i++){
			if(jsonValue.containsKey(i + "")) {
				JSONValue jsValue = jsonValue.get(i+"");
				JSONObject cellData = jsValue.isObject();
				cells.add(new GridChangeCells(cellData.get(CONST_VALUE).toString(), Integer.parseInt(cellData.get(CONST_COLUMN_INDEX).toString()), cellData.get(CONST_COLUMN_FIELD).toString()));
			} else {
				break;
			}
		}		
		//JSONArray aArr = new JSONArray(selectedvalues);//jsonValue.isArray(); //JSONArray
		//ArrayList<GridChangeCells> cells = new ArrayList<GridChangeCells>();
		//for(int i=0; i< aArr.size(); i++) {
		//	JSONValue cellItem = aArr.get(i);
			//cells.add(new GridChangeCells(cellData, columnIndex, columnFieldName))
		//}
		//int[] vals = convertToIntArray(values);
		//SliderEvent sliderEvent = new SliderEvent(event, this, vals, hasOriginalEvent);
		
		GridChangeEvent eventJ = new GridChangeEvent(event, this, cells);
		
		for (GridChangeListener listener : listeners) {
			//listener.onChange(sliderEvent);
			listener.change(eventJ);
		}
	}
	
	
	/**************************************** Methods for grid listener *******************************/
	
	/**
	 * Add a listener for GridListener
	 */
	public void addListener(GridChangeListener listener) {
		listeners.add(listener);
	}
	
	/**
	 * remove a listener from GridListener
	 */
	public void removeListener(GridChangeListener listener) {
		listeners.remove(listener);
	}
}
