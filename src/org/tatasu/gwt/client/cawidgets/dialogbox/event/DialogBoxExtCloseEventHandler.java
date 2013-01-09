package org.tatasu.gwt.client.cawidgets.dialogbox.event;

import com.google.gwt.event.shared.EventHandler;
/**
 * Интерфейс обработчика закрытия диалогового окна
 * @author HafizovAR
 */
public interface DialogBoxExtCloseEventHandler extends EventHandler{
	void onMessageResieved(DialogBoxExtCloseEvent event);
}
