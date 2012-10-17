/**
 * 
 */
package org.tatasu.gwt.client.cawidgets.datagrid.resources;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/** Класс с ресурсами изображений для отображения состояний сигнала по скважине
 * @author HafizovAR
 */
public interface WellStateResources extends ClientBundle {

	@Source("org/tatasu/gwt/client/cawidgets/datagrid/resources/images/green.png")
	ImageResource green();

	@Source("org/tatasu/gwt/client/cawidgets/datagrid/resources/images/red.png")
	ImageResource red();
	
	@Source("org/tatasu/gwt/client/cawidgets/datagrid/resources/images/exmark.png")
	ImageResource exmark();

}
