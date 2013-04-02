package org.tatasu.gwt.client.cawidgets.popupwidget.pbutton;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;

/**
 * Композитный элемент для добавления на панель
 * @author HafizovAR
 */
public class PButton extends Composite  {
	
	private final String closeButtonLabel = "x"; 
	private final String HEIGHT_SHOW_BUTTON = "24px";
	
	private final HorizontalPanel hp = new HorizontalPanel();
	private final HorizontalPanel subHp = new HorizontalPanel();
	private final Button showPanelButton = new Button();
	//private final Hyperlink  showPanelButton = new Hyperlink ();
	private final Button closePanelButton = new Button(closeButtonLabel);
	
	public PButton() {
		initWidget(hp);		
		showPanelButton.setHeight(HEIGHT_SHOW_BUTTON);
		closePanelButton.setHeight(HEIGHT_SHOW_BUTTON);
		subHp.add(showPanelButton);
		subHp.add(closePanelButton);
		hp.add(subHp);
		//hp.setBorderWidth(1);
	}
	//Установка текста на кнопке разворачивания 
	public void setText(String text) {
		showPanelButton.setHTML("<NOBR>" + text + "</NOBR>");
		showPanelButton.setTitle(text);
	}
	/**
	 * Добавление обработчика к кнопке панели
	 * @param showPanelClickHandler
	 */
	public void addShowPanelClickHandler(ClickHandler showPanelClickHandler) {
		showPanelButton.addClickHandler(showPanelClickHandler);
	}
	/**
	 * Добавления обработчика к кнопке закрытия
	 * @param closePanelClickhandler
	 */
	public void addClosePanelClickHandler(ClickHandler closePanelClickhandler) {
		closePanelButton.addClickHandler(closePanelClickhandler);
	}
	
	public void show() {
		hp.setVisible(true);
	}
	
	public void hide() {
		hp.setVisible(false);
	}
}
