package org.tatasu.gwt.client.kendogwt.grid.core;

public class GridOptionsEnum {
	/**
	 * Опции объекта grid
	 * @author HafizovAR
	 */
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
	/**
	 * Опции объекта Column
	 * @author HafizovAR
	 */
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
	/**
	 * Опции объекта DataSource
	 * @author HafizovAR
	 */
	public enum DataSource {			
		DATA("data"),
		AUTOBIND("autoBind");
		private String name;		
		private DataSource (String name) {
			this.name = name;
		}
		public String getName() {
			return this.name;
		}
	}
	
}
