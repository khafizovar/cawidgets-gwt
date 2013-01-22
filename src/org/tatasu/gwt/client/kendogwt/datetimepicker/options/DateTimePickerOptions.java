package org.tatasu.gwt.client.kendogwt.datetimepicker.options;

import java.util.Date;
/**
 * Класс опций для компоненты DateTimePicker
 * @author mol4un
 *
 */
public class DateTimePickerOptions {

	/**  shows the days from the month */
	public static final String START_MONTH = "month";
	/** shows the months of the year */
	public static final String START_YEAR = "year";
	/** shows the years from the decade */
	public static final String START_DECADE = "decade";
	/** shows the decades from the century */
	public static final String START_CENTURY = "century";
	
	/**
	 * Максимальное позволенное значение даты
	 */
	private Date maximum = null;
	/**
	 * Минимальное позволенное значение даты
	 */
	private Date minimum = null;
	/**
	 * Значение для инициализации даты
	 */
	private Date value = null;	
	/**
	 * Example "MM/dd/yyyy hh:mm:ss tt" 
	 */
	private String format = "dd.MM.yyyy HH:mm:ss";
	
	/**
	 * Example "hh:mm:ss" 
	 */
	private String timeFormat = "HH:mm";
	
	private String start = null;
	
	private String depth = null;
	
	private Integer interval = 5;
	
	public DateTimePickerOptions() { }
	
	public DateTimePickerOptions(Date maximum, Date minimum, Date value,
			String format, String timeFormat, String start, String depth,
			Integer interval) {
		this.maximum = maximum;
		this.minimum = minimum;
		this.value = value;
		this.format = format;
		this.timeFormat = timeFormat;
		this.start = start;
		this.depth = depth;
		this.interval = interval;
	}
	
	public Date getMaximum() {
		return maximum;
	}

	public void setMaximum(Date maximum) {
		this.maximum = maximum;
	}

	public Date getMinimum() {
		return minimum;
	}

	public void setMinimum(Date minimum) {
		this.minimum = minimum;
	}

	public Date getValue() {
		return value;
	}

	public void setValue(Date value) {
		this.value = value;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getTimeFormat() {
		return timeFormat;
	}

	public void setTimeFormat(String timeFormat) {
		this.timeFormat = timeFormat;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getDepth() {
		return depth;
	}

	public void setDepth(String depth) {
		this.depth = depth;
	}

	public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}
	
}
