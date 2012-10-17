package org.tatasu.gwt.client.cawidgets.datagrid.core;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;

public class TableCellResizableHeader<T> extends ResizableHeader<T> {
    private final DataGrid<T> table;

    public TableCellResizableHeader(String title, DataGrid<T> table, Column<T, ?> column) {
        super(title, table, column);
        this.table = table;
    }

    @Override
    protected int getTableBodyHeight() {
        return table.getOffsetHeight();
    }
}
