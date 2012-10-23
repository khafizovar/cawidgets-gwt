package org.tatasu.gwt.client.kendogwt.grid.options;

public class GridOptionsEnum {
	/**
	 * Опции объекта grid
	 * @author HafizovAR
	 */
	public enum Option {
		/**
		 * Сделать отображение постраничным
		 */
        PAGEABLE("pageable"),
        /**
         * Опции для датасурса, см enum DataSource
         */
        DATASOURCE("dataSource"),
        /**
         * Массив с колонками, см enum Column
         */
        COLUMNS("columns"),	
        /**
         * Группируемые
         */
		GROUPABLE("groupable"),
		/**
		 * Использовать фильтр
		 */
		FILTERABLE("filterable"),
		/**
		 * Разрешает/запрещает использование скроллов
		 */
		SCROLLABLE("scrollable"),
		/**
		 * Разрешить сортировку
		 */
		SORTABLE("sortable"),
		/**
		 * Позволяет перетаскиванием изменять порядок столбцов
		 */
		REORDERABLE("reorderable"),
		/**
		 * Включение режима выбора, возможные значения "row" Single row selection. "cell" Single cell selection. "multiple, row" Multiple row selection. "multiple, cell" Multiple cell selection.
		 */
		SELECTABLE("selectable");
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
			/**
			 * Опция идентификатор колонки, значение используется как ключ (для однозначной идентификации поля с данными) из HashMap, все пробелы из значения будут удалены
			 */
			FIELD("field"),
			/**
			 * Наименование столбца соответствуемого данному полю 
			 */
	        TITLE("title"),
	        /**
	         * Ширина колонки
	         */
	        WIDTH("width"),
	        /**
	         * Столбец сортрруемый
	         */
	        SORTABLE("sortable"),
	        /**
	         * Шаблон отображения
	         */
			TEMPLATE("template"),
			/**
			 * Изменяемый размер
			 */
			RESIZABLE("resizable"),
			/**
			 * Колонка "с перетаскиванием"
			 */
	        REORDERABLE("reorderable"),
	        /**
	         * Формат значения
	         */
			FORMAT("format"),
			/**
			 * false - все содержимое будет восприниматься как html, false - данные внутри будут интерпретироваться как текст
			 */
			ENCODED("encoded"),
			/**
			 * Столбец использует фильтры
			 */
			FILTERABLE("filterable");
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
		/**
		 * Массив данных
		 */
		DATA("data"),
		/**
		 * Автоматическая установка данных, если false - необходимо принудительно вызывать .read() для установки данных
		 */
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
