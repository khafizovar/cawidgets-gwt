package org.tatasu.gwt.client.kendogwt.datetimepicker.options;
/**
 * Класс 
 * @author mol4un
 *
 */
public class DateTimePickerOptionsEnum {
	/**  shows the days from the month */
	//public static final String START_MONTH = "month";
	/** shows the months of the year */
	//public static final String START_YEAR = "year";
	/** shows the years from the decade */
	//public static final String START_DECADE = "decade";
	/** shows the decades from the century */
	//public static final String START_CENTURY = "century";
	
	
	/**
	 * Опции JS объекта DateTimePicker Kendo
	 * @author mol4un
	 */
	public enum Options {
		/**
		 * Минимальное ограничение времени
		 */
		MINIMUM("min"),
		/**
		 * Максимальное ограничение времени
		 */
		MAXIMUM("max"),
		/**
		 * Значение для инициализации
		 */
		VALUE("value"),
		/**
		 * Формат даты
		 */
		FORMAT("format"),
		/**
		 * Формат времени
		 */
		TIMEFORMAT("timeFormat"),
		/**
		 * TODO проверить данную опцию
		 * Значение которое будет считаться началом инициализации ??
		 */
		START("start"),
		/**
		 * Глубина отображения. 
		 * Create a DateTimePicker for selecting a month
		*	$("#dateTimePicker").kendoDateTimePicker({
		*	    start: "year",
		*	    depth: "year"
		*	});
		 */
		DEPTH("depth"),
		/**
		 * Define the interval (in minutes) between values in the time drop-down list
		 * Интервал (в минутах) между значениями в дроп листе времени
		 */
		INTERVAL("interval");
		private String name;		
		private Options(String name) {
			this.name = name;
		}		
		public String getName() {
			return this.name;
		}
	}
}
