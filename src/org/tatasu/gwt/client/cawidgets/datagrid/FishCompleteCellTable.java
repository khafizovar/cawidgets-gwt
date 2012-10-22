package org.tatasu.gwt.client.cawidgets.datagrid;

import java.util.ArrayList;
import java.util.List;

import org.tatasu.gwt.client.cawidgets.datagrid.core.BeanCore;
import org.tatasu.gwt.client.cawidgets.datagrid.core.TableCellResizableHeader;
import org.tatasu.gwt.client.cawidgets.datagrid.resources.DataGridResource;
import org.tatasu.gwt.client.cawidgets.datagrid.resources.WellStateResources;


import com.google.gwt.cell.client.ImageResourceCell;
import com.google.gwt.cell.client.SafeHtmlCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

/**
 * На данный момент не рекомендуется к использованию
 * Класс виджета таблицы с примочками, для нередактируемых данных
 * 
 * @author HafizovAR
 * 
 * @param <T>
 *            Тип Bean класса, расширяющего BeanCore
 */
public class FishCompleteCellTable<T extends BeanCore> extends Composite {
	/** Признак того что колонки были созданы */
	private Boolean columnCreated = false;
	/** Ресурс с иконками состояния */
	protected WellStateResources images = GWT.create(WellStateResources.class);
	/** Дата провайдер с данными */
	protected final ListDataProvider<T> dataProvider;
	/** Сортировщки колонок */
	protected ListHandler<T> columnSortHandler;
	/** Позиции мыши */
	//private int mouseXposition = 0;
	//private int mouseYposition = 0;

	private static CompleteCellTableUiBinder uiBinder = GWT
			.create(CompleteCellTableUiBinder.class);

	@SuppressWarnings("rawtypes")
	interface CompleteCellTableUiBinder extends
			UiBinder<Widget, FishCompleteCellTable> {
	}

	private DataGrid.Resources tableRes = GWT.create(DataGridResource.class);

	@UiField(provided = true)
	DataGrid<T> dataGrid_2;
	@UiField
	VerticalPanel cellTableVp;

	/**
	 * Конструктор
	 */
	public FishCompleteCellTable() {
		dataProvider = new ListDataProvider<T>();
		columnSortHandler = new ListHandler<T>(dataProvider.getList());
		dataGrid_2 = new DataGrid<T>(10, tableRes);
		initWidget(uiBinder.createAndBindUi(this));

		dataProvider.addDataDisplay(dataGrid_2);

		// Трекинг мыши (нужно для хранения x y)
		Event.addNativePreviewHandler(new NativePreviewHandler() {
			public void onPreviewNativeEvent(final NativePreviewEvent event) {
				final int eventType = event.getTypeInt();
				switch (eventType) {
				case Event.ONMOUSEMOVE:
					//mouseXposition = event.getNativeEvent().getClientX();
					//mouseYposition = event.getNativeEvent().getClientY();
					break;
				default:
				}
			}
		});

		// Селекшн модели нет, используем клик хандлер для создания баллон тоол
		// типа
		/*
		 * final NoSelectionModel<T> selectionModel = new NoSelectionModel<T>();
		 * selectionModel .addSelectionChangeHandler(new
		 * SelectionChangeEvent.Handler() {
		 * 
		 * @SuppressWarnings("rawtypes")
		 * 
		 * @Override public void onSelectionChange(SelectionChangeEvent event) {
		 * T eventObject = (T) ((NoSelectionModel)
		 * event.getSource()).getLastSelectedObject();
		 * simplePopup.setHeaderText(eventObject.getToolTipHeader().asString());
		 * simplePopup.setWidget(new
		 * Label(eventObject.getToolTipBody().asString()));
		 * simplePopup.setPopupPosition(mouseXposition, mouseYposition);
		 * simplePopup.show(); } });
		 * dataGrid_2.setSelectionModel(selectionModel);
		 */
	}

	/**
	 * Сеттер установки данных таблицы
	 * 
	 * @param data
	 */
	public void setTableData(List<T> data) {

		if (data.size() > 0) {
			dataProvider.setList(data);
			dataGrid_2.setRowData(data);

			if (!columnCreated) {
				createColumns();
				columnCreated = true;
			}
		} else {
			System.out.println("null can not allowed here");
		}
	}

	/**
	 * Сеттер установки высоты
	 */
	@Override
	public void setHeight(String height) {
		super.setHeight(height);
		cellTableVp.setHeight(height);
		dataGrid_2.setHeight(height);
		dataGrid_2.redraw();
	}

	/**
	 * Сеттер установки ширины
	 */
	@Override
	public void setWidth(String width) {
		super.setWidth(width);
		cellTableVp.setWidth(width);
		dataGrid_2.setWidth(width);
		dataGrid_2.redraw();
	}

	/**
	 * Метод создания колонок
	 */
	private void createColumns() {
		ArrayList<String> fields = dataProvider.getList().get(0)
				.getFieldsNameArray();

		for (final String field : fields) {
			if (!field.equals("state")) {

				Column<T, SafeHtml> objectNameColumn = new Column<T, SafeHtml>(
						new SafeHtmlCell()) {

					@Override
					public SafeHtml getValue(T object) {
						try {
							Object val = object.getValueByFieldName(field);
							
							SafeHtmlBuilder builder = new SafeHtmlBuilder();
							String cellBgColorStyle = null;
							if(object.getRGBAStringForCell(field) != null) {
								cellBgColorStyle= "background: "+ object.getRGBAStringForCell(field) +"; border:solid; border-width:0px; -moz-border-radius: 5px;  -webkit-border-radius: 5px;  border-radius: 5px;";
							}
							
							if(object.checkingForLice(field)) {
								//Без тега img
								return builder.appendHtmlConstant("<div style=\"cursor: help; " +  cellBgColorStyle + "\" title=\"" + object.getToolTipHtmlContext(field) + "\">&nbsp&nbsp"+ val + "</div>").toSafeHtml();
							} else {
								//С тегом img и иконкой восклицательного знака
								return builder.appendHtmlConstant("<div style=\"cursor: help; " +  cellBgColorStyle + "\" title=\"" + object.getToolTipHtmlContext(field) + "\"><img style=\"width:14px; height:14px;\" src=\"" + images.exmark().getSafeUri().asString() + "\"/>&nbsp&nbsp"+ val + "</div>").toSafeHtml();
							}
						} catch (Throwable thr) {
							thr.printStackTrace();
						}
						return null;
					}
					/*
					 * @Override public String getCellStyleNames(Context
					 * context, Type object) { return object.getState() == 1 ?
					 * "cellTableCell green_style_name " :
					 * "cellTableCell red_style_name"; }
					 */
				};

				dataGrid_2
						.addColumn(
								objectNameColumn,
								new TableCellResizableHeader<T>(
										dataProvider
												.getList()
												.get(0)
												.getColumnNameByFieldName(field) == null ? field
												: dataProvider
														.getList()
														.get(0)
														.getColumnNameByFieldName(
																field),
										dataGrid_2, objectNameColumn));

				// StarRatingColumn<Object> objectNameColumn = new
				// StarRatingColumn<Object>() {
				// @Override
				// public StarRating getValue(Object object) {
				// return new StarRating(Integer.parseInt(object.toString()),
				// 10, false);
				// }
				// };
				// dataGrid_2.addColumn(objectNameColumn, new
				// TableCellResizableHeader<T>(dataProvider.getList().get(0).getColumnNameByFieldName(field)==
				// null ? field :
				// dataProvider.getList().get(0).getColumnNameByFieldName(field),
				// dataGrid_2, objectNameColumn));

				// Update view
				/*
				 * ratingColumn.setFieldUpdater(new
				 * FieldUpdater<ThingIWantToRate, StarRating>() { public void
				 * update(int index, ThingIWantToRate object, StarRating value)
				 * { //Set cell's value object.setRating(value.getRating());
				 * 
				 * //TODO: Update database here
				 * 
				 * //Redraw table cellTable.redraw(); } });
				 */

			} else {
				// Колонка со статусом обрабатывается отдельно
				// TODO Придумать как унифицировать установку колонок c рисунками
				Column<T, ImageResource> status = new Column<T, ImageResource>(
						new ImageResourceCell()) {
					@Override
					public ImageResource getValue(T object) {
						return object.getValueByFieldName(field).equals(1) ? images
								.green() : images.red();
					}
				};
				dataGrid_2
						.addColumn(
								status,
								new TableCellResizableHeader<T>(
										dataProvider
												.getList()
												.get(0)
												.getColumnNameByFieldName(field) == null ? field
												: dataProvider
														.getList()
														.get(0)
														.getColumnNameByFieldName(
																field),
										dataGrid_2, status));
				dataGrid_2.setColumnWidth(status, 60, Unit.PX);
			}
		}
	}
}
