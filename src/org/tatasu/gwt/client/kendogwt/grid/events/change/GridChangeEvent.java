package org.tatasu.gwt.client.kendogwt.grid.events.change;

import java.util.ArrayList;

import org.tatasu.gwt.client.kendogwt.grid.Grid2;

import com.google.gwt.user.client.Event;

/**
 * Событие смены выбранных строк, ячеек в kendo ui гриде
 * @author HafizovAR
 *
 */
public class GridChangeEvent {
	/** Данные ячейки, все данные приходят типа String */
	//private String cellData;
	/** Текущий (!) индекс колонки в котором находится ячейка */
	//private int columnIndex;
	/** Наименование колонки в которой находится ячейка */
	//private String columnFieldName;
	/** Источник события */
	private Grid2 grid;
	/** Событие, на данный момент jsobject? */
	private Event event;
	/** Выбранные объекты */
	private ArrayList<GridChangeCells> cells = new ArrayList<GridChangeCells>();
	
	public GridChangeEvent(Event event, Grid2 source, ArrayList<GridChangeCells> cells) {
		this.cells = cells;
		this.event = event;
		this.grid = source;
	}

	
	/**
	 * Источник события 
	 * @return the grid
	 */
	public Grid2 getGrid() {
		return grid;
	}

	
	/**
	 * Событие, на данный момент jsobject?
	 * @return the event
	 */
	public Event getEvent() {
		return event;
	}
	
	/**
	 * Выбранные объекты 
	 * @return the cells
	 */
	public ArrayList<GridChangeCells> getCells() {
		return cells;
	}
	
}
