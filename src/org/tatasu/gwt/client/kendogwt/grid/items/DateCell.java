package org.tatasu.gwt.client.kendogwt.grid.items;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;

public class DateCell extends Cell {
	/** Формат даты */
	private String format = "dd.MM.yyyy HH:mm:ss";
	
	//private String formatKendo = "{0:dd.MM.yyyy HH:mm:ss} ";
	/** Дата в лонг формате */
	private long dateInMilliseconds = -1;
	/**
	 * Конструктор. Дата = текущая дата. Формат по умолчанию = "dd.mm.yyyy HH:MM:ss" 
	 */
	public DateCell() {
		this.dateInMilliseconds = new Date().getTime();
	}
	
	public DateCell(Date date) {
		this.dateInMilliseconds = date.getTime();
	}
	
	public DateCell(Date date, String format) {
		this.dateInMilliseconds = date.getTime();
		this.format = format;
	}
	
	@Override
	public String toString() {		
		DateTimeFormat fmt = DateTimeFormat.getFormat(format);
		// prints Monday, December 17, 2007 in the default locale
		String rez = "<div";
		if (customCss != null) {
			rez += " style=\"" + customCss + "\"";
		}
		rez += ">" + fmt.format(new Date(dateInMilliseconds));
		rez += "</div>";
		return rez;
	}
	
	public Date getDate(){
		return new Date(dateInMilliseconds);
	}
	//public getDateformat: "{0:dd.MM.yyyy}"

	/*public String getFormatKendo() {
		return formatKendo;
	}

	public void setFormatKendo(String formatKendo) {
		this.formatKendo = formatKendo;
	}*/
}
