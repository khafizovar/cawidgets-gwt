package org.tatasu.gwt.client.cawidgets.datagrid.core;

import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;

public class ResizableDataGrid<T> extends DataGrid<T> {
    public ResizableDataGrid(int i, Resources tableRes) {
		super(i, tableRes);
	}

	public ResizableDataGrid() {
		super();
	}

	public class DataGridResizableHeader extends ResizableHeader<T> {
        public DataGridResizableHeader(String title, Column<T, ?> column) {
            super(title, ResizableDataGrid.this, column);
        }

        @Override
        protected int getTableBodyHeight() {
            return ResizableDataGrid.this.getTableBodyElement().getOffsetHeight();
        }
    }
}
