package org.tatasu.gwt.client.cawidgets.dialogbox.event;


import com.google.gwt.event.shared.GwtEvent;

public class DialogBoxExtCloseEvent extends GwtEvent<DialogBoxExtCloseEventHandler> {

	public static final Type<DialogBoxExtCloseEventHandler> TYPE = new Type<DialogBoxExtCloseEventHandler>();

	
	public DialogBoxExtCloseEvent() {

    }
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<DialogBoxExtCloseEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(DialogBoxExtCloseEventHandler handler) {
		handler.onMessageResieved(this);
	}
	
}