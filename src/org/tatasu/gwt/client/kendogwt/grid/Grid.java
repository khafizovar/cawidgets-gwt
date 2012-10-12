package org.tatasu.gwt.client.kendogwt.grid;

import java.util.ArrayList;
import java.util.Date;

import org.tatasu.gwt.client.kendogwt.grid.core.GridBean;
import org.tatasu.gwt.client.kendogwt.grid.core.GridColumn;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;

public class Grid<T extends GridBean> extends Widget{
	/** Список колонок */ 
	ArrayList<GridColumn> columns;
	
	private JSONObject defaultOptions;
	
	public enum Option {		
		SORTABLE("sortable"),
        RESIZABLE("resizable"),
        REORDERABLE("reorderable"),
        PAGEABLE("pageable"),
        DATASOURCE("dataSource"),
        COLUMNS("columns"),	
		GROUPABLE("groupable"),
		FILTERABLE("filterable"),
		SCROLLABLE("scrollable");
		private String name;		
		private Option(String name) {
			this.name = name;
		}		
		public String getName() {
			return this.name;
		}
	}
	
	public enum Column {			
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
		}
	
	public enum DataSource {			
		DATA("data"),
		AUTOBIND("autoBind");
        //TITLE("title"),
        //WIDTH("width"),
		//TEMPLATE("template"),
		//FORMAT("format");
		private String name;		
		private DataSource (String name) {
			this.name = name;
		}		
		public String getName() {
			return this.name;
		}
	}
	

	
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
	
	public Grid(String elementId, ArrayList<GridColumn> columns) {
		super();
		this.columns = columns;
		this.divElementId = elementId;
		div = DOM.createDiv();
		div.setId(elementId);
		this.setElement(div);
		
		
	}

	@Override
	protected void onLoad() {
		//Опции dataGrid
		JSONObject options = new JSONObject();		
		options.put(Option.GROUPABLE.getName(), new JSONString("false"));
		options.put(Option.SORTABLE.getName(), new JSONString("true"));
		options.put(Option.PAGEABLE.getName(), new JSONString("false"));
		
		//Колонки
		JSONArray columnsObj = new JSONArray();
		for (int i = 0; i < columns.size(); i++) {
			JSONObject column = new JSONObject();
			column.put(Column.FIELD.getName(), new JSONString(columns.get(i).getField()));
			column.put(Column.TITLE.getName(), new JSONString(columns.get(i).getTitle()));
			columnsObj.set(i, column);
		}		
		options.put(Option.COLUMNS.getName(), columnsObj);
		
		createGrid(this, divElementId, options.getJavaScriptObject());		
		super.onLoad();
	}

	@Override
	protected void onUnload() {
		//destroyDropDownWidget(getElement().getId());
		super.onUnload();
	}
	
	private native void createGrid(Grid grid, String id, JavaScriptObject options) /*-{
		try {
			$wnd.$("#" + id).kendoGrid(options);
		} catch (error) {
			$wnd.alert(error);
		}
	}-*/;
	
	public void setData(ArrayList<T> data) {
		JSONObject dataSource = new JSONObject();
		JSONObject dataS = new JSONObject();
		JSONArray dataArr = new JSONArray();
		int index = 0;
		for (T t : data) {
			for (String fieldName : t.getBeanFields()) {
				if(t.getValueByFieldname(fieldName) instanceof String) {
					dataS.put(fieldName, new JSONString((String) t.getValueByFieldname(fieldName)));
				} else if(t.getValueByFieldname(fieldName) instanceof Double) {
					dataS.put(fieldName, new JSONNumber((Double) t.getValueByFieldname(fieldName)));
				} else if(t.getValueByFieldname(fieldName) instanceof Integer) {
					dataS.put(fieldName, new JSONNumber((Integer) t.getValueByFieldname(fieldName)));
				} else if(t.getValueByFieldname(fieldName) instanceof Date) {
					//TODO добавить форматтер либо темплейт kendo
					dataS.put(fieldName, new JSONString(((Date)t.getValueByFieldname(fieldName)).toString()));
				}
			}
			dataArr.set(index, dataS);
			index++;
		}
		dataSource.put(DataSource.DATA.getName(), dataArr);
		dataSource.put(DataSource.AUTOBIND.getName(), new JSONString("true"));
		setGridData(divElementId, dataSource.getJavaScriptObject());
	}

	
	private native void setGridData(String id, JavaScriptObject dataSourceOptions) /*-{
		//$wnd.dataSource = new kendo.data.DataSource({
        //                pageSize: 10,
        //                data: createRandomData(500),
        //                change: function() { // subscribe to the CHANGE event of the data source
        //                    // update the max attribute of the "page" input
        //                    $("#page").attr("max", this.totalPages());
//
        //                    $("#people tbody").html(kendo.render(template, this.view()));
         //               }
        //            });

                    // read the data
        //$wnd.dataSource.read();
        
        grid = $( "#" + id ).kendoGrid(dataSourceOptions);
		 
		//function setDataSource() {
		//     $( "#grid" ).data( "kendoGrid" ).dataSource.read();
		//}
	}-*/;
}
