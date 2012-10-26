package org.tatasu.gwt.client.kendogwt.grid.events.databound;

import org.tatasu.gwt.client.kendogwt.grid.Grid2;

/**
 * Событие dataBound
 * @author HafizovAR
 */
public class GridDataBoundEvent {
	private Grid2 grid;
	
	public GridDataBoundEvent(Grid2 grid){
		this.grid = grid;
	}

	/**
	 * @return the grid
	 */
	public Grid2 getGrid() {
		return grid;
	}
}
