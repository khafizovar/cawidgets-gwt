package org.tatasu.gwt.client.cawidgets.popupwidget.resizepanel;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

public class ResizePanel extends AbsolutePanel {
	private boolean bDragDrop = false;
	private boolean move = false ;
	//private final Image								angleR					= new Image("/WelltechParameters-portlet/images/angle.png");	
	private final Image								angleR					= new Image("/images/angle.png");
	private final List<PanelResizeListener> panelResizedListeners = new ArrayList<PanelResizeListener>();
	private final ResizePanel widget = this;
	
	private final int minWidht = 469;
	private final int minHeight = 400;
	
	public ResizePanel() {
	        super();
	        
	        //listen to mouse-events
	        DOM.sinkEvents(this.getElement(),
	        		Event.ONMOUSEDOWN |
	        		Event.ONMOUSEMOVE | 
	                Event.ONMOUSEUP |
	                Event.ONMOUSEOVER
	        );
	        this.add(angleR, this.getOffsetWidth() - angleR.getOffsetWidth(), this.getOffsetHeight() - angleR.getOffsetHeight());
	}
	
	/**
	 * processes the mouse-events to show cursor or change states
	 *  - mouseover
	 *  - mousedown
	 *  - mouseup
	 *  - mousemove
	 */
	@Override
	public void onBrowserEvent(Event event) {
        final int eventType = DOM.eventGetType(event);
        if (Event.ONMOUSEOVER == eventType) {
        	//show different cursors
            if (isCursorResize(event)) {
                DOM.setStyleAttribute(this.getElement(), "cursor", "se-resize");
            } else if(isCursorMove(event)){
                DOM.setStyleAttribute(this.getElement(),"cursor", "se-resize");
            }else {
                DOM.setStyleAttribute(this.getElement(), "cursor", "default");
            }
        }
        if (Event.ONMOUSEDOWN == eventType) {
            if (isCursorResize(event)) {
            	//enable/disable resize
                if (bDragDrop == false) {
                	bDragDrop = true;

                    DOM.setCapture(this.getElement());
                }
            }else if(isCursorMove(event)){
                DOM.setCapture(this.getElement());
                move = true;
            }
        } else if (Event.ONMOUSEMOVE == eventType) {
        	//reset cursor-type
            if(!isCursorResize(event)&&!isCursorMove(event)){
                DOM.setStyleAttribute(this.getElement(), "cursor", "default");
            }
            
            //calculate and set the new size
            if (bDragDrop == true) {
            	int absX = DOM.eventGetClientX(event);
                int absY = DOM.eventGetClientY(event);
                int originalX = DOM.getAbsoluteLeft(this.getElement());
                int originalY = DOM.getAbsoluteTop(this.getElement());
                
                //do not allow mirror-functionality
                if(absY>originalY && absX>originalX){
                    Integer height = absY-originalY+2;
                    if(height >= minHeight)
                    	this.setHeight(height + "px");
                    
                    Integer width = absX-originalX+2;
                    if(width >= minWidht)
                    	this.setWidth(width + "px");
                    notifyPanelResizedListeners(((width >= minWidht) ? width : minWidht), ((height >= minHeight) ? height : minHeight));
                    //this.getWidget(this.getWidgetIndex(angleR))
                    this.setWidgetPosition(angleR, this.getOffsetWidth() - angleR.getOffsetWidth(), this.getOffsetHeight() - angleR.getOffsetHeight());                    
                }
            }else if(move == true){
                RootPanel.get().setWidgetPosition(this, DOM.eventGetClientX(event),DOM.eventGetClientY(event));
            }
        } else if (Event.ONMOUSEUP == eventType) {
        	//reset states
            if(move == true){
                move = false;
                DOM.releaseCapture(this.getElement());
            }
            if (bDragDrop == true) {
            	bDragDrop = false;
                DOM.releaseCapture(this.getElement());
            }
        }
	}

	/**
	 * returns if mousepointer is in region to show cursor-resize
	 * @param event
	 * @return true if in region
	 */
	protected boolean isCursorResize(Event event) {
        int cursorY  = DOM.eventGetClientY(event);
        int initialY = this.getAbsoluteTop();
        int height   = this.getOffsetHeight();
        
        int cursorX  = DOM.eventGetClientX(event);
        int initialX = this.getAbsoluteLeft();
        int width    = this.getOffsetWidth();

        //only in bottom right corner (area of 10 pixels in square) 
        if (((initialX + width - 10) < cursorX && cursorX <= (initialX + width)) &&
        	((initialY + height - 10) < cursorY && cursorY <= (initialY + height)))
            return true;
        else
            return false;
	}

	/**
	 * sets the element in panel
	 * @param movingPanelElement
	 */
	/*public void setMovingPanelElement(Element movingPanelElement) {
        this.movingPanelElement = movingPanelElement;
	}*/
	
	/**
	 * is cursor in moving state?
	 * @param event event to process
	 * @return true if cursor is in movement
	 */
	protected boolean isCursorMove(Event event){
		//if(movingPanelElement!=null){
		if(angleR.getElement() != null) {
			
			widget.setWidgetPosition(angleR, widget.getOffsetWidth() - angleR.getOffsetWidth(), widget.getOffsetHeight() - angleR.getOffsetHeight());
			
	        int cursorY = DOM.eventGetClientY(event);
	        int initialY = angleR.getElement().getAbsoluteTop();
	        int cursorX = DOM.eventGetClientX(event);
	        int initialX = angleR.getElement().getAbsoluteLeft();

	        if(initialY <= cursorY && initialX <= cursorX)	        		
	        	return true;
	 		else
	 			return false;
		} else 
			return false;
	}
	
    /**
     * Добавление слушателя события ресайз
     * @param listener
     */
	public void addPanelResizedListener(PanelResizeListener listener) {
        panelResizedListeners.add(listener);
    }
	/**
	 * Удаление слушателя события ресайз
	 * @param listener
	 */
	public void removeResizePanelListeners(PanelResizeListener listener) {
		panelResizedListeners.remove(listener);
	}

	/**
     * Interface function to emit signal
     */
	private void notifyPanelResizedListeners(Integer width,Integer height) {
        /*for (Iterator i = panelResizedListeners.iterator(); i.hasNext(); ) {
            ((PanelResizeListener) i.next()).onResized(width,height);
        }*/
		for ( PanelResizeListener  listener : panelResizedListeners) {
			listener.onResized(width, height);
		}
    }
}

