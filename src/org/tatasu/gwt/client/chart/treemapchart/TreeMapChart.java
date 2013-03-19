package org.tatasu.gwt.client.chart.treemapchart;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.tatasu.gwt.client.chart.treemapchart.events.TreeMapChartEvent;
import org.tatasu.gwt.client.chart.treemapchart.events.TreeMapChartEventTypes;
import org.tatasu.gwt.client.chart.treemapchart.events.TreeMapChartOnMouseOutListener;
import org.tatasu.gwt.client.chart.treemapchart.events.TreeMapChartOnMouseOverListener;
import org.tatasu.gwt.client.chart.treemapchart.events.TreeMapChartReadyListener;
import org.tatasu.gwt.client.chart.treemapchart.events.TreeMapChartRollupListener;
import org.tatasu.gwt.client.chart.treemapchart.events.TreeMapChartSelectListener;
import org.tatasu.gwt.client.chart.treemapchart.options.TreeMapChartOptions;
import org.tatasu.gwt.client.chart.treemapchart.options.TreeMapChartOptionsEnum;

import com.google.gwt.core.client.JavaScriptObject;

import com.google.gwt.dom.client.Element;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;

/**
 * GWT обертка для компоненты {@link https
 * ://developers.google.com/chart/interactive/docs/gallery/treemap} <br/>
 * 
 * @author HafizovAR
 * 
 */
public class TreeMapChart extends Widget {

	/** Div элемент который будет выступать родительским элементом */
	private Element										div;
	/** Идентификатор div элемента */
	private String										divElementId;
	/** Локальные данные для отображения на графике */
	private List<TreeMapChartData>						data;
	/** Опции графика */
	private TreeMapChartOptions							options;
	/** Наше дерево */
	private JavaScriptObject							tree;
	/** Список слушателей события onMouseOut */
	private ArrayList<TreeMapChartOnMouseOutListener>	onMouseOutListeners		= new ArrayList<TreeMapChartOnMouseOutListener>();
	/** Список слушателей события onMouseOver */
	private ArrayList<TreeMapChartOnMouseOverListener>	onMouseOverListeners	= new ArrayList<TreeMapChartOnMouseOverListener>();
	/** Список слушателей события ready */
	private ArrayList<TreeMapChartReadyListener>		readyListeners			= new ArrayList<TreeMapChartReadyListener>();
	/** Список слушателей события rollup */
	private ArrayList<TreeMapChartRollupListener>		rollUpListeners			= new ArrayList<TreeMapChartRollupListener>();
	/** Список слушателей события select */
	private ArrayList<TreeMapChartSelectListener>		selectListeners			= new ArrayList<TreeMapChartSelectListener>();

	/**
	 * Конструктор
	 * 
	 * @param options
	 *            Объект опций
	 * @param data
	 *            Объект данных. В параметрах данных есть один неявный момент -
	 *            должен присутствовать родительский элемент. Т.е. это элемент
	 *            наименование которого является родителем для остальных
	 *            объектов, а его родительский элемент должен быть равен null,
	 *            после чего можно вкладывать дочерние узлы и группы
	 */
	public TreeMapChart(TreeMapChartOptions options, List<TreeMapChartData> data) {
		super();
		this.options = options;
		this.divElementId = "chart_div" + new Date().getTime() + Math.round(Math.random() * 100000);
		div = DOM.createDiv();
		div.setId(divElementId);
		div.setClassName("treemapchart");
		this.setElement(div);
		this.data = data;
	}
	@Override
	protected void onLoad() {
		super.onLoad();
		createTreeMapChart();
	}
	/**
	 * Метод построения графика, вызывается по onLoad();
	 */
	private void createTreeMapChart() {
		// Формируем данные
		/** Строка данных для eval (двумерный массив) */
		String dataStr = "[['-','-',0,0]";
		/** Строка title для eval (одномерный массив) */
		//String popUpArray = "['',";
		for (TreeMapChartData element : data) {
			// Формирование строки данны для eval 
			if (element.getName() != null)
				dataStr += ",['" + element.getName() + "',";
			else
				dataStr += ",[null, ";
			if (element.getParent() != null)
				dataStr += "'" + element.getParent() + "'," + element.getSize() + "," + element.getColor() + "]";
			else
				dataStr += "null," + element.getSize() + "," + element.getColor() + "]";
			
			//Формирование строки popUp для eval
			//popUpArray += ",'" + element.getPopUpString() + "'";
		}
		dataStr += "]";
		//popUpArray += "]";
		this.tree = createTreeMapChartJS(this, divElementId, getChartOptions(), dataStr);
	}
	/**
	 * Нативный метод построения графика, вызывается из createTreemapChart()
	 * 
	 * @param parent
	 *            текущий объект
	 * @param divElementId2
	 *            идентификатор используемого div элемента
	 * @param javaScriptObject
	 *            Объект опций для графика
	 * @param value
	 *            Значения в формате string для данны. Внимание! - идет
	 *            преобразование в двумерный массив через eval()
	 * @return Созданный объект treeMapChart
	 */
	private native JavaScriptObject createTreeMapChartJS(TreeMapChart parent, String divElementId2, JavaScriptObject javaScriptObject, String value) /*-{		
			var data = $wnd.google.visualization.arrayToDataTable(eval(value));
			var tree = new $wnd.google.visualization.TreeMap($wnd.document.getElementById(divElementId2));
			//Подписка на события готовности графика			
			$wnd.google.visualization.events
					.addListener(
							tree,
							'ready',
							function() {
								$wnd.console.log("ready");
								parent.@org.tatasu.gwt.client.chart.treemapchart.TreeMapChart::fireEvent(Lcom/google/gwt/user/client/Event;Ljava/lang/String;)(event, 'ready');
							});
			//Подписка на событие навигации по уровню вверх
			$wnd.google.visualization.events
					.addListener(
							tree,
							'rollup',
							function() {
								$wnd.console.log("rollup");
								parent.@org.tatasu.gwt.client.chart.treemapchart.TreeMapChart::fireEvent(Lcom/google/gwt/user/client/Event;Ljava/lang/String;)(event, 'rollup');
							});
			$wnd.google.visualization.events
					.addListener(
							tree,
							'onmouseover',
							function() {
								$wnd.console.log("onmouseover");
								parent.@org.tatasu.gwt.client.chart.treemapchart.TreeMapChart::fireEvent(Lcom/google/gwt/user/client/Event;Ljava/lang/String;)(event, 'onmouseover');
							});
			$wnd.google.visualization.events
					.addListener(
							tree,
							'onmouseout',
							function() {
								$wnd.console.log("onmouseout");
								parent.@org.tatasu.gwt.client.chart.treemapchart.TreeMapChart::fireEvent(Lcom/google/gwt/user/client/Event;Ljava/lang/String;)(event, 'onmouseout');
							});
			$wnd.google.visualization.events
					.addListener(
							tree,
							'select',
							function() {
								$wnd.console.log("select");
								parent.@org.tatasu.gwt.client.chart.treemapchart.TreeMapChart::fireEvent(Lcom/google/gwt/user/client/Event;Ljava/lang/String;)(event, 'select');
							});

			tree.draw(data, javaScriptObject);
			return tree;
	}-*/;
	/**
	 * Установка новых данных и перерисовка графика
	 * 
	 * @param data
	 */
	public void setDataAndRedraw(List<TreeMapChartData> data) {
		this.data = data;
		// Формируем данные
		String dataStr = "[['-','-',0,0]";
		for (TreeMapChartData element : data) {
			if (element.getName() != null)
				dataStr += ",['" + element.getName() + "',";
			else
				dataStr += ",[null, ";
			if (element.getParent() != null)
				dataStr += "'" + element.getParent() + "'," + element.getSize() + "," + element.getColor() + "]";
			else
				dataStr += "null," + element.getSize() + "," + element.getColor() + "]";
		}
		dataStr += "]";
		setDataAndRedraw(getChartOptions(), dataStr, tree);
	}
	/**
	 * Установка данных, нативный метод
	 * 
	 * @param javaScriptObject
	 *            Нативный метод
	 * @param value
	 * @param tree
	 */
	private native void setDataAndRedraw(JavaScriptObject javaScriptObject, String value, JavaScriptObject tree) /*-{
			$wnd.console.log(value);
			var data = $wnd.google.visualization.arrayToDataTable(eval(value));			
			tree.draw(data, javaScriptObject);
			$wnd.alert(error);
	}-*/;
	/**
	 * Преобразует тип установленные данные TreeMapChartOptions для данного
	 * графика в javaScriptOptions
	 * 
	 * @return
	 */
	private JavaScriptObject getChartOptions() {
		// Создаем javaScriptObject из опций
		JSONObject optionsJs = new JSONObject();
		// Установка опций
		if (options.getHeaderColor() != null)
			optionsJs.put(TreeMapChartOptionsEnum.Options.HEADER_COLOR.getName(), new JSONString(options.getHeaderColor()));
		if (options.getHeaderHeight() != null)
			optionsJs.put(TreeMapChartOptionsEnum.Options.HEADER_HEIGHT.getName(), new JSONNumber(options.getHeaderHeight()));
		if (options.getHeaderHighlightColor() != null)
			optionsJs.put(TreeMapChartOptionsEnum.Options.HEADER_HIGHLIGHT_COLOR.getName(), new JSONString(options.getHeaderHighlightColor()));
		if (options.getMaxColor() != null)
			optionsJs.put(TreeMapChartOptionsEnum.Options.MAX_COLOR.getName(), new JSONString(options.getMaxColor()));
		if (options.getMaxDepth() != null)
			optionsJs.put(TreeMapChartOptionsEnum.Options.MAX_DEPTH.getName(), new JSONNumber(options.getMaxDepth()));
		if (options.getMaxHighlightColor() != null)
			optionsJs.put(TreeMapChartOptionsEnum.Options.MAX_HIGHLIGHT_COLOR.getName(), new JSONString(options.getMaxHighlightColor()));
		if (options.getMaxPostDepth() != null)
			optionsJs.put(TreeMapChartOptionsEnum.Options.MAX_POST_DEPTH.getName(), new JSONNumber(options.getMaxPostDepth()));
		if (options.getMaxColorValue() != null)
			optionsJs.put(TreeMapChartOptionsEnum.Options.MAX_COLOR_VALUE.getName(), new JSONNumber(options.getMaxColorValue()));
		if (options.getMidColor() != null)
			optionsJs.put(TreeMapChartOptionsEnum.Options.MID_COLOR.getName(), new JSONString(options.getMidColor()));
		if (options.getMidHighlightColor() != null)
			optionsJs.put(TreeMapChartOptionsEnum.Options.MID_HIGHLIGHT_COLOR.getName(), new JSONString(options.getMidHighlightColor()));
		if (options.getMinColor() != null)
			optionsJs.put(TreeMapChartOptionsEnum.Options.MIN_COLOR.getName(), new JSONString(options.getMinColor()));
		if (options.getMinHighlightColor() != null)
			optionsJs.put(TreeMapChartOptionsEnum.Options.MIN_HIGHLIGHT_COLOR.getName(), new JSONString(options.getMinHighlightColor()));
		if (options.getMinColorValue() != null)
			optionsJs.put(TreeMapChartOptionsEnum.Options.MIN_COLOR_VALUE.getName(), new JSONNumber(options.getMinColorValue()));
		if (options.getNoColor() != null)
			optionsJs.put(TreeMapChartOptionsEnum.Options.NO_COLOR.getName(), new JSONString(options.getNoColor()));
		if (options.getNoHighlightColor() != null)
			optionsJs.put(TreeMapChartOptionsEnum.Options.NO_COLOR.getName(), new JSONString(options.getNoHighlightColor()));
		optionsJs.put(TreeMapChartOptionsEnum.Options.SHOW_SCALE.getName(), JSONBoolean.getInstance(options.isShowScale()));
		optionsJs.put(TreeMapChartOptionsEnum.Options.SHOW_TOOLTIPS.getName(), JSONBoolean.getInstance(options.isShowTooltips()));
		if (options.getFontColor() != null)
			optionsJs.put(TreeMapChartOptionsEnum.Options.FONT_COLOR.getName(), new JSONString(options.getFontColor()));
		if (options.getFontFamily() != null)
			optionsJs.put(TreeMapChartOptionsEnum.Options.FONT_FAMILY.getName(), new JSONString(options.getFontFamily()));
		if (options.getFontSize() != null)
			optionsJs.put(TreeMapChartOptionsEnum.Options.FONT_SIZE.getName(), new JSONNumber(options.getFontSize()));
		return optionsJs.getJavaScriptObject();
	}
	/**
	 * Очистка графика
	 */
	public void clearChart() {
		clearChartJS(tree);
	}
	/**
	 * Очистка графика, нативный метод
	 * 
	 * @param tree
	 *            элемент графика
	 */
	private native void clearChartJS(JavaScriptObject tree) /*-{
			tree.clearChart();
	}-*/;
	/**
	 * Выборка индекса элемента выбранного пользователем
	 * 
	 * @return индекс в списке элементов
	 */
	public int getSelection() {
		JSONObject js = new JSONObject(getSelectionJs(tree));
		Window.alert(Integer.parseInt(((JSONObject) js.get("0")).get("row").toString()) + "");
		return Integer.parseInt(((JSONObject) js.get("0")).get("row").toString());
	}
	/**
	 * Нативный метод получения выбранного идентификатора
	 * 
	 * @param tree
	 *            Объект treemapChart
	 * @return
	 */
	private native JavaScriptObject getSelectionJs(JavaScriptObject tree) /*-{
		$wnd.console.log("selected tree value: "
				+ JSON.stringify(tree.getSelection()));
		return tree.getSelection();
	}-*/;
	/**
	 * Выборка глубины вложенности
	 * 
	 * @return количество вложений
	 */
	public int getMaxPossibleDepth() {
		return Integer.parseInt(getMaxPossibleDepthJS(tree));
	}
	/**
	 * Нативный метод выборки глубины вложенности
	 * 
	 * @param tree
	 *            Объект TreeMapChart
	 * @return
	 */
	private native String getMaxPossibleDepthJS(JavaScriptObject tree) /*-{
		$wnd.console.log(JSON.stringify(tree.getMaxPossibleDepth()));
		return JSON.stringify(tree.getMaxPossibleDepth());
	}-*/;
	/**
	 * Метод "на уровень вверх" с последующей перерисовкой
	 */
	public void goUpAndDraw() {
		goUpAndDraw(tree);
	}
	/**
	 * Нативный метод "на уровень вверх с последующей перерисовкой"
	 * 
	 * @param tree
	 *            Объект дерева
	 */
	private native void goUpAndDraw(JavaScriptObject tree) /*-{
		tree.goUpAndDraw();
	}-*/;
	/**
	 * Метод перерисовки графика
	 */
	public void reDraw() {
		setDataAndRedraw(data);
	}
	/************* Методы регистрации/разрегистрации слушателей *****************/
	/**
	 * Регистрация слушателя события "onMouseOver"
	 * 
	 * @param listener
	 *            слушатель для регистрации
	 */
	public void addOnMouseOverListener(TreeMapChartOnMouseOverListener listener) {
		onMouseOverListeners.add(listener);
	}
	/**
	 * Разрегистрация слушателя события "onMouseOver"
	 * 
	 * @param listener
	 *            слушатель для разрегистрации
	 */
	public void removeDateChangeEventListener(TreeMapChartOnMouseOverListener listener) {
		onMouseOverListeners.remove(listener);
	}
	/**
	 * Регистра слушателя соыбтия "onMouseOutListener"
	 * 
	 * @param listener
	 */
	public void addOnMouseOutListener(TreeMapChartOnMouseOutListener listener) {
		onMouseOutListeners.add(listener);
	}
	/**
	 * Разрегистра слушателя соыбтия "onMouseOutListener"
	 * 
	 * @param listener
	 *            Слушатель
	 */
	public void removeOnMouseOutListener(TreeMapChartOnMouseOutListener listener) {
		onMouseOutListeners.remove(listener);
	}
	/**
	 * Регистрация слушателей события ready
	 * 
	 * @param listener
	 *            Слушатель
	 */
	public void addOnReadyListener(TreeMapChartReadyListener listener) {
		readyListeners.add(listener);
	}
	/**
	 * Разрегистрация слушателей события ready
	 * 
	 * @param listener
	 *            Слушатель
	 */
	public void removeOnReadyListener(TreeMapChartReadyListener listener) {
		readyListeners.remove(listener);
	}
	/**
	 * Регистрация слушателя события rollup
	 * 
	 * @param listener
	 *            Слушатель
	 */
	public void addRollUpListener(TreeMapChartRollupListener listener) {
		rollUpListeners.add(listener);
	}
	/**
	 * Разрегистрация события слуаштеля rollup
	 * 
	 * @param listener
	 *            Слушатель
	 */
	public void removeRollUpListener(TreeMapChartRollupListener listener) {
		rollUpListeners.remove(listener);
	}
	/**
	 * Регистрация слушателя события select
	 * 
	 * @param listener
	 *            Слушатель
	 */
	public void addSelectListener(TreeMapChartSelectListener listener) {
		selectListeners.add(listener);
	}
	/**
	 * Разрегистрация слушателя события select
	 * 
	 * @param listener
	 */
	public void removeSelectListener(TreeMapChartSelectListener listener) {
		selectListeners.add(listener);
	}
	/********************* Метод генерации событий ***************************/
	/**
	 * Метод генерации всех типов событий
	 * 
	 * @param event
	 *            оргинальное событие
	 * @param eventType
	 *            Тип события {@link TreeMapChartEventTypes}
	 */
	protected void fireEvent(Event event, String eventType) {
		TreeMapChartEvent eventJ = new TreeMapChartEvent(event, this, eventType);
		if (eventType.equals(TreeMapChartEventTypes.ON_MOUSE_OUT)) {
			for (TreeMapChartOnMouseOutListener listener : onMouseOutListeners) {
				listener.onMouseOut(eventJ);
			}
		} else if (eventType.equals(TreeMapChartEventTypes.ON_MOUSE_OVER)) {
			for (TreeMapChartOnMouseOverListener listener : onMouseOverListeners) {
				listener.onMouseOver(eventJ);
			}
		} else if (eventType.equals(TreeMapChartEventTypes.READY)) {
			for (TreeMapChartReadyListener listener : readyListeners) {
				listener.onReady(eventJ);
			}
		} else if (eventType.equals(TreeMapChartEventTypes.ROLLUP)) {
			for (TreeMapChartRollupListener listener : rollUpListeners) {
				listener.rollUp(eventJ);
			}
		} else if (eventType.equals(TreeMapChartEventTypes.SELECT)) {
			for (TreeMapChartSelectListener listener : selectListeners) {
				listener.onSelect(eventJ);
			}
		}
	}
}
