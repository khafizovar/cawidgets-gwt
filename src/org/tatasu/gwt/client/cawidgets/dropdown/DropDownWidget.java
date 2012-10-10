package org.tatasu.gwt.client.cawidgets.dropdown;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Widget;
/**
 * DropDown виджет на базе jquery плагина http://www.marghoobsuleman.com/jquery-image-dropdown.
 * Виджет использует jquery-1.8.2. 
 * @author HafizovAR
 *
 */
public class DropDownWidget extends Widget{	
	/** Слушатели событий */
	private List<DropDownWidgetListener> listeners = new ArrayList<DropDownWidgetListener>();
	/** Данные */
	private List<DropDownWidgetBean> data;
	/** Ширина дд виджета */
	private String width = "500px";
	/** Высота дд виджета */
	private String height = "25px";
	/** идентификатор созданного select элемента */
	private String selectId;
	/** идентификатор созданного div элемента (родитель select) */
	private String divId;
	/** количество строк в выпадающем меню */
	private int visRows = 15;
	/** высота строки */
	private int rowHeight = 25;
	
	//private String uniqueStringId = "variable" + new Date().getTime() + "" + Math.round(Math.random() * 100); 
	Element div;
	Element select;	
	/**
     * Slider with the specified ID
     * @param id - id of the element to create
     * @param options - JSONObject of any possible option, can be null for defaults
     */
	public DropDownWidget(String DivElementId, String SelectElementId,List<DropDownWidgetBean> data, String width, String height, int visibleRows, int rowHeight) {		
		super();
		this.data = data;
		div = DOM.createDiv();
		this.setElement(div);
    	div.setId(DivElementId);	
    	this.selectId = SelectElementId;
    	this.divId = DivElementId;
		this.width = width;
		this.height = height;
		this.visRows = visibleRows + 1; //+ 1 - исправление костылей плагина
		this.rowHeight = rowHeight;
		
		select = DOM.createSelect();
		if(data != null)
			for(int i = 0; i<data.size(); i++) {
				Element option = DOM.createOption();
				option.setAttribute("title", data.get(i).getTitle());
				option.setInnerText(data.get(i).getInnerText());
				option.setAttribute("value", data.get(i).getValue());
				select.appendChild(option);
			}
		select.setId(SelectElementId);
		select.setAttribute("width", "200px");
		div.appendChild(select);		
	}

	@Override
	protected void onLoad() {
		createDropDownWidget(this, select.getId(), visRows, rowHeight,  width, height);
		super.onLoad();
	}

	@Override
	protected void onUnload() {
		destroyDropDownWidget(getElement().getId());
		super.onUnload();
	}
	
	@Override
	public void setWidth(String width) {		
		this.width = width;  
		this.changeWidth(selectId, width);
		super.setWidth(width);
	}
	
	@Override
	public void setHeight(String height) {
		this.height = height;
		this.changeHeight(selectId, height);
		super.setHeight(height);
	}

	
	private void fireOnChangeEvent(Event event, String newTitleText, String newValue, String newInnerText) {
		DropDownWidgetEvents ddEventEvent = new DropDownWidgetEvents(event, this, new DropDownWidgetBean(newTitleText, newInnerText, newValue), false);		
		for (DropDownWidgetListener listener : listeners) {
			listener.onChange(ddEventEvent);
		}
	}
	
	private native void createDropDownWidget(DropDownWidget ddlist, String id, int _visibleRows, int _rowHeight, String wdth, String hght) /*-{	
  		$wnd.$("#" + id).width(wdth);
  		$wnd.$("#" + id).height(hght);
  		$wnd.$("#" + id).msDropDown({visibleRows:_visibleRows, rowHeight:_rowHeight});
  		$wnd.$('#'+id).change(function(event) {	
  			var newVal = $wnd.$(this).val();
  			ddlist.@org.tatasu.gwt.client.cawidgets.dropdown.DropDownWidget::fireOnChangeEvent(Lcom/google/gwt/user/client/Event;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)(event, "", newVal, "");
  		});
	}-*/;

	private native void destroyDropDownWidget(String id) /*-{
		$wnd.$("#" + id).remove();
	}-*/;
	
	private native void changeWidth(String id, String value) /*-{
		$wnd.$("#" + id).width(value);
	}-*/;
	
	private native void changeHeight(String id, String value) /*-{
		$wnd.$("#" + id).height(value);
	}-*/;
	/**
	 * Открыть список
	 */
	public void open() {
		openNative(selectId);		
	}	
	
	private native void openNative(String id) /*-{
		$wnd.$("#"+ id).msDropDown().data('dd').open()
	}-*/;
	
	/**
	 * Закрыть список
	 */
	public void close() {
		closeNative(selectId);
	}
	
	private native void closeNative(String id) /*-{
		$wnd.$("#"+ id).msDropDown().data('dd').open()
	}-*/;
	
	/**
	 * Добавить новый элемент
	 * @param newItem добавляемый элемент
	 */
	public void addItem(DropDownWidgetBean newItem) {
		addItemNative(selectId, newItem.getInnerText(), newItem.getValue(), newItem.getTitle(), visRows, rowHeight);
	}
	
	private native void addItemNative(String selectId, String itemText, String val, String titl, int visRows, int rowHght) /*-{
		$wnd.$('#' + selectId).msDropDown({visibleRows: visRows, rowHeight: rowHght}).data("dd").add({text:itemText, value:val, title:titl}); //will add icon
	}-*/;
	/**
	 * Удалить элемент по индексу
	 * @param index	Индекс удаляемого элемента
	 */
	public void removeItem(int index) {
		removeItemNative(selectId, index + "", visRows, rowHeight );
	}
	
	private native void removeItemNative(String selectedId, String index, int visRow, int rowHght) /*-{
		$wnd.$('#' + selectedId).msDropDown({visibleRows: visRows, rowHeight: rowHght}).data("dd").remove(index);
	}-*/;
	
	/**
	 * Установка свойств, доступные свойства
	 * 	showIcon - показать/скрыть иконки, возможные значения "true", "false";
	 *  visibleRows - количество видимых строк
	 *  rowheight - высота строки, в пикселях
	 * @param selectedId
	 * @param propert
	 * @param val
	 */
	//public native void setProperty(String selectedId, String propert, String val) /*-{
	//	$wnd.$('#' + selectedId).msDropDown().data("dd").set(propert, val);	
	//}-*/;
	/**
	 * Выборка элемента по индексу
	 * @param i	индекс элемента
	 * @return возвращаемый элемент
	 */
	public DropDownWidgetBean getItem(int i){	
		return new DropDownWidgetBean(getItemTitle(selectId, i + "", visRows, rowHeight), 
										getItemText(selectId, i + "", visRows, rowHeight), 
										getItemValue(selectId, i + "", visRows, rowHeight));
	}

	public native String getItemText(String selectId, String index, int visRow, int rowHght) /*-{
		return $wnd.$('#' + selectId).msDropDown({visibleRows: visRows, rowHeight: rowHght}).data("dd").item(index).text;
	}-*/;
	public native String getItemValue(String selectId, String index, int visRow, int rowHght) /*-{
		return $wnd.$('#' + selectId).msDropDown({visibleRows: visRows, rowHeight: rowHght}).data("dd").item(index).value;
	}-*/;
	public native String getItemTitle(String selectId, String index, int visRow, int rowHght) /*-{
		return $wnd.$('#' + selectId).msDropDown({visibleRows: visRows, rowHeight: rowHght}).data("dd").item(index).title;
	}-*/;
	
	public DropDownWidgetBean getSelectedItem() {
		return new DropDownWidgetBean(getSelectedItemTitle(selectId, visRows, rowHeight), 
									  getSelectedItemText(selectId, visRows, rowHeight), 
									  getSelectedItemValue(selectId, visRows, rowHeight));
	}
			
	public native String getSelectedItemText(String selectId, int visRow, int rowHght) /*-{
		return $wnd.$('#' + selectId).msDropDown({visibleRows: visRows, rowHeight: rowHght}).data("dd").item($wnd.document.getElementById(selectId).selectedIndex).text;
	}-*/;
	public native String getSelectedItemValue(String selectId, int visRow, int rowHght) /*-{
		return $wnd.$('#' + selectId).msDropDown({visibleRows: visRows, rowHeight: rowHght}).data("dd").item($wnd.document.getElementById(selectId).selectedIndex).value;
	}-*/;
	public native String getSelectedItemTitle(String selectId, int visRow, int rowHght) /*-{
		return $wnd.$('#' + selectId).msDropDown({visibleRows: visRows, rowHeight: rowHght}).data("dd").item($wnd.document.getElementById(selectId).selectedIndex).title;
	}-*/;

	/**
	 * Возвращает id элемента select
	 * @return the selectId
	 */
	public String getSelectId() {
		return selectId;
	}

	/**
	 * Возвращает id элемента div
	 * @return the select
	 */
	/*public Element getSelect() {
		return select;
	}*/
	
	/**
	 * Добавление листенера событий
	 */
	public void addListener(DropDownWidgetListener listener) {
		listeners.add(listener);
	}
	
	/**
	 * Удаление листенера событий
	 */
	public void removeListener(DropDownWidgetListener listener) {
		listeners.remove(listener);
	}
	/**
	 * disable виджет
	 */
	public void disable() {
		disableNative(selectId);
	}
	
	private native void disableNative(String id) /*-{
		//$wnd.$('#' + id).msDropDown().data("dd").disabled(true);
	}-*/;
	/**
	 * enable виджет
	 */
	public void enable() {
		enableNative(selectId, visRows, rowHeight);
	}
	
	private native void enableNative(String id, int visRow, int rowHght) /*-{
		$wnd.$('#' + id).msDropDown({visibleRows: visRows, rowHeight: rowHght}).data("dd").disabled(false);
	}-*/;
	/**
	 * Скрыть элемент
	 */
	public void hide() {
		hideNative(selectId, visRows, rowHeight);
	}
	
	private native void hideNative(String id,  int visRow, int rowHght) /*-{
		$wnd.$('#' + id).msDropDown({visibleRows: visRows, rowHeight: rowHght}).data("dd").visible(false);
	}-*/;
	/**
	 * Показать виджет (если ранее он был скрыт)
	 */
	public void show() {
		showNative(selectId, visRows, rowHeight);
	}
	private native void showNative(String id,  int visRow, int rowHght) /*-{
		$wnd.$('#' + id).msDropDown({visibleRows: visRows, rowHeight: rowHght}).data("dd").visible(true);
	}-*/;
	
	/*public void setVisibleRowCount(int n) {
		setVisibleRowCountNative(selectId, n + "");
	}
	
	private native void setVisibleRowCountNative(String id, String n) /*-{
		$wnd.$('#' + id).msDropDown().data("dd").visible(true);
	}-/;*/
}
