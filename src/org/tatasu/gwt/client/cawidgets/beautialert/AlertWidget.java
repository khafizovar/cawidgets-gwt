package org.tatasu.gwt.client.cawidgets.beautialert;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class AlertWidget {
	private static final String CONST_CLOSE = "Закрыть";
	public static DialogBox alertWidget(final String header, final String content) {
	    final DialogBox box = new DialogBox(false, true);
	    final VerticalPanel panel = new VerticalPanel();
	    panel.setWidth("");
	    final Label contentLabel = new Label(content);
	    contentLabel.setWordWrap(true);
	    box.setText(header);
	    panel.add(contentLabel);
	    final Button buttonClose = new Button(CONST_CLOSE, new ClickHandler() {			
			@Override
			public void onClick(final ClickEvent event) {
			      box.hide();
			}
		}); 

	    final Label emptyLabel = new Label("");
	    emptyLabel.setSize("auto","25px");    
	    panel.add(emptyLabel);
	    panel.add(emptyLabel);
	    buttonClose.setWidth("90px");
	    panel.add(buttonClose);
	    panel.setCellHorizontalAlignment(buttonClose, HasAlignment.ALIGN_RIGHT);
	    box.add(panel);
	    return box;
	}
}