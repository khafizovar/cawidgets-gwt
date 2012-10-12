package org.tatasu.gwt.client.kendogwt.grid;

import java.util.ArrayList;
import java.util.Date;

import org.tatasu.gwt.client.kendogwt.grid.core.GridBean;
import org.tatasu.gwt.client.kendogwt.grid.core.GridColumn;
import org.tatasu.gwt.client.kendogwt.grid.core.GridOptionsEnum;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;
//import com.google.gwt.core.client.JavaScriptObject;

public class Grid<T extends GridBean> extends Widget{
	/** Список колонок */ 
	protected ArrayList<GridColumn> columns;	
	//protected JSONObject defaultOptions;
	/** Локальный источник данных */
	protected ArrayList<T> dataModel; 


	
	public enum Templates {
		DATETEMPLATE("#= kendo.toString(BirthDate,\"dd MMMM yyyy\") #");
		
		private String name;		
			private Templates(String name) {
				this.name = name;
			}		
			public String getName() {
				return this.name;
			}
	}
	
	/** Div элемент который будет выступать родительским элементом */
	private Element div;
	private String divElementId;
	
	private String width = "100%";
	private String height = "300px";
	
	public Grid(String elementId, ArrayList<GridColumn> columns, ArrayList<T> data) {
		super();
		this.columns = columns;
		this.divElementId = elementId;
		div = DOM.createDiv();
		div.setId(elementId);
		this.setElement(div);
		
		this.dataModel = data;
	}

	@Override
	protected void onLoad() {
		createGridModwlWithData();
		super.onLoad();
	}

	protected void createGridModwlWithData(){
		//Опции dataGrid
		JSONObject options = new JSONObject();		
		//options.put(Option.GROUPABLE.getName(), new JSONString("false"));
		options.put(GridOptionsEnum.Option.GROUPABLE.getName(), getPsevdoJsFalse());
		options.put(GridOptionsEnum.Option.SORTABLE.getName(), getPsevdoJsFalse());
		options.put(GridOptionsEnum.Option.PAGEABLE.getName(), getPsevdoJsFalse());
		
		//Колонки
		JSONArray columnsObj = new JSONArray();
		for (int i = 0; i < columns.size(); i++) {
			JSONObject column = new JSONObject();
			column.put(GridOptionsEnum.Column.FIELD.getName(), new JSONString(columns.get(i).getField()));
			column.put(GridOptionsEnum.Column.TITLE.getName(), new JSONString(columns.get(i).getTitle()));
			columnsObj.set(i, column);
		}		
		options.put(GridOptionsEnum.Option.COLUMNS.getName(), columnsObj);
		/// Инициализация модели данных
		JSONObject dataSource = new JSONObject();
		JSONObject dataS = new JSONObject();
		JSONArray dataArr = new JSONArray();
		int index = 0;
		for (T t : dataModel) {
			for (String fieldName : t.getBeanFields()) {
				if(t.getValueByFieldName(fieldName) instanceof String) {
					dataS.put(fieldName, new JSONString((String) t.getValueByFieldName(fieldName)));
				} else if(t.getValueByFieldName(fieldName) instanceof Double) {
					dataS.put(fieldName, new JSONNumber((Double) t.getValueByFieldName(fieldName)));
				} else if(t.getValueByFieldName(fieldName) instanceof Integer) {
					dataS.put(fieldName, new JSONNumber((Integer) t.getValueByFieldName(fieldName)));
				} else if(t.getValueByFieldName(fieldName) instanceof Date) {
					//TODO добавить форматтер либо темплейт kendo
					dataS.put(fieldName, new JSONString(((Date)t.getValueByFieldName(fieldName)).toString()));
				}
			}
			dataArr.set(index, dataS);
			index++;
		}
		dataSource.put(GridOptionsEnum.DataSource.DATA.getName(), dataArr);
		//dataSource.put(DataSource.AUTOBIND.getName(), new JSONString("true"));
		//Конец инициализации модели данных
		options.put(GridOptionsEnum.Option.DATASOURCE.getName(), dataSource);
		
		createGrid(this, divElementId, options.getJavaScriptObject());	
	}
	
	@Override
	protected void onUnload() {
		super.onUnload();
	}
	/**
	 * Нативный метод создания kendo grid  
	 * @param grid
	 * @param id
	 * @param options
	 */
	private native void createGrid(Grid<T> grid, String id, JavaScriptObject options) /*-{
		try {
			$wnd.$("#" + id).kendoGrid(options);
		} catch (error) {
			$wnd.alert(error);
		}
	}-*/;
	/**
	 * Установка данных для grid
	 * @param data
	 */
	public void setData(ArrayList<T> data) {
		//Уничтожаем существующий датасурс
		destroyDataSource();
		//Устанавливаем текущие данные 
		dataModel = data;
		//Вызываем метод создания грида
		createGridModwlWithData();		
	}

	
	private native void setGridData(String id, JavaScriptObject dataSourceOptions) /*-{
        try {
	        $wnd.temporaryDataSource = new $wnd.kendo.data.DataSource(dataSourceOptions);
	       	$wnd.$( "#" + id ).kendoGrid($wnd.temporaryDataSource);
	       	$wnd.$( "#" + id ).data("kendoGrid").dataSource.read();
        } catch(error) {
        	$wnd.alert(error);
        }		
	}-*/;
	
	public void destroyDataSource() {
		destroy(divElementId);		
	}
	/**
	 * Очистка dataSource элемента grid для его повторной установки
	 * @param id идентификатор div элемента
	 */
	private native void destroy(String id) /*-{
		if ($wnd.$( "#" + id ).length > 0 && $wnd.$( "#" + id ).data().kendoGrid) {
           var thisKendoGrid = $wnd.$( "#" + id ).data().kendoGrid;
 
           if (thisKendoGrid.dataSource && thisKendoGrid._refreshHandler) {
               $wnd.$( "#" + id ).removeData('kendoGrid');
               $wnd.$( "#" + id ).empty();
           }
       }
	}-*/;
	/**
	 * Добавление строки в kendo grid
	 * @param bean
	 */
	public void addRow(T bean) {
		dataModel.add(bean);		
		JSONObject dataS = new JSONObject();		
		for (String fieldName : bean.getBeanFields()) {
			if(bean.getValueByFieldName(fieldName) instanceof String) {
				dataS.put(fieldName, new JSONString((String) bean.getValueByFieldName(fieldName)));
			} else if(bean.getValueByFieldName(fieldName) instanceof Double) {
				dataS.put(fieldName, new JSONNumber((Double) bean.getValueByFieldName(fieldName)));
			} else if(bean.getValueByFieldName(fieldName) instanceof Integer) {
				dataS.put(fieldName, new JSONNumber((Integer) bean.getValueByFieldName(fieldName)));
			} else if(bean.getValueByFieldName(fieldName) instanceof Date) {
				//TODO добавить форматтер либо темплейт kendo
				dataS.put(fieldName, new JSONString(((Date)bean.getValueByFieldName(fieldName)).toString()));
			}
		}
		addRowNative(divElementId, dataS.getJavaScriptObject());
	}
	
	private native void addRowNative(String id, JavaScriptObject obj) /*-{
		try {
			$wnd.$("#" + id).data('kendoGrid').dataSource.add(obj);
		} catch (error) {
			$wnd.alert(error);
		}
		
	}-*/;
	/**
	 * Удаление объекта по объекту
	 * !ВНИМАНИЕ будут удалены все строки значения которых совпадают с указанными значениями
	 * @param bean
	 */
	public void removeItem(T bean) {
		
	}
	/**
	 * Удаление объекта по индексу в источнике данных
	 * @param index
	 */
	public void removeItem(int index) {

	}
	
	private JSONObject getPsevdoJsFalse() {
		return null;
	};
	
	private JSONString getPsevdoJsTrue() {
		return new JSONString("true");
	};
}
