package org.tatasu.gwt.client.kendogwt.grid.events.change;


public interface GridChangeListener {
	/**
	 * Fires when select row or cell is changed
	 * Срабатывает когда выбор строки или ячейки меняется
	 * @param event
	 */
	public void change(GridChangeEvent event);
}
