package org.tatasu.gwt.client.cawidgets.loader.resources;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface LoaderWidgetResources extends ClientBundle {

	@Source("org/tatasu/gwt/client/cawidgets/loader/resources/images/ajax-loader.gif")
	ImageResource getLoaderImage();

}
