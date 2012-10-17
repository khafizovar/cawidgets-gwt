package org.tatasu.gwt.client.cawidgets.datagrid.resources;
import com.google.gwt.user.cellview.client.DataGrid;
/**
 * Интерфейс для переопределения стандартных CSS стилей DataGrid 
 * @author HafizovAR
 */
public interface DataGridResource extends DataGrid.Resources {
	  @Source({ DataGrid.Style.DEFAULT_CSS, "DataGridOverride.css" })
	  DataGrid.Style dataGridStyle();
	};