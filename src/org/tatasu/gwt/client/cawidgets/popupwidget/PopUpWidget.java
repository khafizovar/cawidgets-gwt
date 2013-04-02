package org.tatasu.gwt.client.cawidgets.popupwidget;

import java.util.ArrayList;
import org.tatasu.gwt.client.cawidgets.popupwidget.event.CloseEventListener;
import org.tatasu.gwt.client.cawidgets.popupwidget.event.MinimizeEventListener;
import org.tatasu.gwt.client.cawidgets.popupwidget.event.SimpleEvent;
import org.tatasu.gwt.client.cawidgets.popupwidget.pbutton.PButton;
import org.tatasu.gwt.client.cawidgets.popupwidget.resizepanel.PanelResizeListener;
import org.tatasu.gwt.client.cawidgets.popupwidget.resizepanel.ResizePanel;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * 
 * @author HafizovAR
 *
 */
public class PopUpWidget extends DialogBox {

	//private final WellStateResources						resources	= GWT.create(WellStateResources.class);
	
	private VerticalPanel	vp = new VerticalPanel();
	/** Виджет "свернуть панель" */
	private final Button minimizeButton = new Button("_"); 
	/** Заголовок панели */
	private final HorizontalPanel captionPanel = new HorizontalPanel();
	/** Кнопка закрыть окно */
	private final Button							closeButton				= new Button("x");

	private final ResizePanel						absPanel;
	/** Связанная с этой панелью кнопка на панели задач */
	private final PButton							panelButton				= new PButton();

	private final HTML								title					= new HTML();
	/** Слушатели нажатия кнопки закрыть */
	private final ArrayList<CloseEventListener>		closeListener			= new ArrayList<CloseEventListener>();
	/** Слушатели кнопки "минимизировать" */
	private final ArrayList<MinimizeEventListener>	minimizeEventListeners = new ArrayList<MinimizeEventListener>();
	
	/**
	 * Конструктор
	 * 
	 * @param dropDownData
	 *            данные для графика
	 */
	public PopUpWidget() {
		super(false, false);
		
		setHTML("&nbsp;");

		absPanel = new ResizePanel();
		absPanel.add(vp, 0, 0);
		
		super.add(absPanel);
		//absPanel.setSize(vp.getOffsetWidth() + "px", vp.getOffsetHeight() + "px");
		//this.setSize((absPanel.getOffsetWidth() + 15) + "px", (absPanel.getOffsetHeight() + 15) + "px");
		//this.setSize(temporarySize, temporarySize);		
		//this.add(absPanel);	
	}

	/**
	 * Регистрация слушателя о просьбе закрытия диалогового окна
	 * @param listener
	 *            слушатель
	 */
	public void addCloseEventListener(CloseEventListener listener) {
		closeListener.add(listener);
	}

	/**
	 * Разрегистрация слушателя о просьбе закрытия диалогового окна
	 * 
	 * @param listener
	 *            слушатель
	 */
	public void removeCloseEventListener(CloseEventListener listener) {
		closeListener.remove(listener);
	}

	/**
	 * Регистрация сулаштеля минимизации
	 * @param listener
	 */
	public void addMinimizeEventListener(MinimizeEventListener listener) {
		minimizeEventListeners.add(listener);
	}
	
	/**
	 * Разрегистрация слушателя минимизации 
	 * @param listener
	 */
	public void removeMinimizeListener(MinimizeEventListener listener) {
		minimizeEventListeners.remove(listener);
	}

	/************************************************************** Пушки ********************************************************/
	
	private void fireOnCloseEvent() {
		this.hideWithRemoveAllChilds();
		SimpleEvent event = new SimpleEvent(this);

		for (CloseEventListener listener : closeListener) {
			listener.onCloseEvent(event);
		}
	}
	
	private void fireOnMinimize() {
		SimpleEvent event = new SimpleEvent(this);
		
		for (MinimizeEventListener listener : minimizeEventListeners) {
			listener.onMinimize(event);
		}
	}
	/**************************************************** Аксессоры ***********************************************/
	
	/**
	 * @return the panelButton
	 */
	public PButton getPanelButton() {
		return panelButton;
	}
	
	@Override
    public void setHTML(String html) {
            if (closeButton != null) {
                    setCaption(html, closeButton, minimizeButton);
            } else {
                    super.setHTML(html);
            }
    }

    @Override
    public void setHTML(SafeHtml html) {
            if (closeButton != null) {
                    setCaption(html.asString(), closeButton, minimizeButton);
            } else {
                    super.setHTML(html);
            }
    }

    /**
     * Makes a new caption and replace the old one.
     * 
     * @param txt
     * @param w
     */
    private void setCaption(String txt, Widget w, Widget w2) {
            captionPanel.setWidth("100%");
            title.setHTML(txt);
            captionPanel.add(title);
            captionPanel.add(w2);
            captionPanel.add(w);
            captionPanel.setCellHorizontalAlignment(w,
                            HasHorizontalAlignment.ALIGN_RIGHT);
            
            captionPanel.setCellHorizontalAlignment(w2,
                    HasHorizontalAlignment.ALIGN_RIGHT);
            // make sure that only when you click on this icon the widget will be 
            // closed!, don't make the field too width
            captionPanel.setCellWidth(w, "1%");
            captionPanel.setCellWidth(w2, "1%");
            captionPanel.addStyleName("Caption");

            // Get the cell element that holds the caption
            Element td = getCellElement(0, 1);

            // Remove the old caption
            td.setInnerHTML("");

            // append our horizontal panel
            td.appendChild(captionPanel.getElement());
    }
    
    /**
     * Function checks if the browser event is was inside the caption region
     * 
     * @param event
     *            browser event
     * @return true if event inside the caption panel (DialogBox header)
     */
    protected boolean isHeaderCloseControlEvent(NativeEvent event) {
            // return isWidgetEvent(event, captionPanel.getWidget(1));
            return isWidgetEvent(event, closeButton);
    }
    
    /**
     * Function checks if the browser event is was inside the caption region
     * 
     * @param event
     *            browser event
     * @return true if event inside the caption panel (DialogBox header)
     */
    protected boolean isHeaderMinimizeControlEvent(NativeEvent event) {
            // return isWidgetEvent(event, captionPanel.getWidget(1));
            return isWidgetEvent(event, minimizeButton);
    }
    
    /**
     * Функция проверяет принадлежит ли событие виджету
     * 
     * @param event - текущее событие
     * @param w - проверяемый виджет
     * @return - true если событие принадлежит виджету
     */
    protected boolean isWidgetEvent(NativeEvent event, Widget w) {
            EventTarget target = event.getEventTarget();

            if (Element.is(target)) {
                    boolean t = w.getElement().isOrHasChild(Element.as(target));
                    return t;
            }
            return false;
    }
    
    /**
     * переопределяет событие браузера из DialogBox
     */
    @Override
    public void onBrowserEvent(Event event) {
    		//Сперва проверяем закрываеющий виджет
            if (isHeaderCloseControlEvent(event)) {

                    switch (event.getTypeInt()) {
                    case Event.ONMOUSEUP:
                    case Event.ONCLICK:
                    		fireOnCloseEvent();
                            break;
                    case Event.ONMOUSEOVER:
                            break;
                    case Event.ONMOUSEOUT:
                            break;
                    }

                    return;
            }
            //Потом проверяем сворачивающий виджет
            if(isHeaderMinimizeControlEvent(event)) {
            	 switch (event.getTypeInt()) {
                     case Event.ONMOUSEUP:
                     case Event.ONCLICK:
                    	 	 fireOnMinimize();
                             break;
                     case Event.ONMOUSEOVER:
                             break;
                     case Event.ONMOUSEOUT:
                             break;
                     }

                     return;
            }
            super.onBrowserEvent(event);
    }
    
    @Override
    public void setSize(String width, String height) {
    	absPanel.setSize(width, height);
    	vp.setSize(width, height);
    }
    
    @Override
    public void add(Widget widget) {
    	vp.add(widget);
    }
    
    public void hideWithRemoveAllChilds() {
    	vp.clear();
    	this.hide();
    }

    public void addResizeListeners(PanelResizeListener listener) {    	
		absPanel.addPanelResizedListener(listener);
    }
    
    public void removeResizeListeners(PanelResizeListener listener) {
    	absPanel.removeResizePanelListeners(listener);
    }
}
