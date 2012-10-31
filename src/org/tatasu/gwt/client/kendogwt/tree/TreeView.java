package org.tatasu.gwt.client.kendogwt.tree;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;


/**
 * Tree kendo ui
 * @author HafizovAR
 *
 */
public class TreeView extends Widget {
	
	private Element									div;
	protected String divId = "";
	
	public TreeView(String divId) {
		this.divId = divId;
		div = DOM.createDiv();
		div.setId(divId);
	}
	
	@Override
	protected void onLoad() {
		// TODO Auto-generated method stub
		super.onLoad();
		createTreeView(divId);
	}
	
	private native void createTreeView(String divId) /*-{
		$("#" + divId).kendoTreeView({
                checkboxes: {
                    checkChildren: true
                },

                dataSource: [{
                    id: 1, text: "My Documents", expanded: true, spriteCssClass: "rootfolder", items: [
                        {
                            id: 2, text: "Kendo UI Project", expanded: true, spriteCssClass: "folder", items: [
                                { id: 3, text: "about.html", spriteCssClass: "html" },
                                { id: 4, text: "index.html", spriteCssClass: "html" },
                                { id: 5, text: "logo.png", spriteCssClass: "image" }
                            ]
                        },
                        {
                            id: 6, text: "New Web Site", expanded: true, spriteCssClass: "folder", items: [
                                { id: 7, text: "mockup.jpg", spriteCssClass: "image" },
                                { id: 8, text: "Research.pdf", spriteCssClass: "pdf" },
                            ]
                        },
                        {
                            id: 9, text: "Reports", expanded: true, spriteCssClass: "folder", items: [
                                { id: 10, text: "February.pdf", spriteCssClass: "pdf" },
                                { id: 11, text: "March.pdf", spriteCssClass: "pdf" },
                                { id: 12, text: "April.pdf", spriteCssClass: "pdf" }
                            ]
                        }
                    ]
                }]
            });
	}-*/;
}
