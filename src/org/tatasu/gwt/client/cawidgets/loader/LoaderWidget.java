package org.tatasu.gwt.client.cawidgets.loader;

import org.tatasu.gwt.client.cawidgets.loader.resources.LoaderWidgetResources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

/**
 * Класс обслуживания Loading Box'a
 * 
 * @author HafizovAR
 */
public class LoaderWidget {

	Timer timer1;
	Label labelSec = new Label();
	private DecoratedPopupPanel loadBox;
	private LoaderWidgetResources loaderImage = GWT.create(LoaderWidgetResources.class);

	/**
	 * Метод создания LoadingBox'а
	 */
	public void createLodBox() {
		loadBox = new DecoratedPopupPanel(false);// new DialogBox();
		loadBox.setModal(true);
		loadBox.setGlassEnabled(true);
		Image img = new Image(loaderImage.getLoaderImage());

		HorizontalPanel vp = new HorizontalPanel();
		vp.add(img);
		loadBox.add(vp);
	}

	/**
	 * Метод отображения LoadingBox'а
	 */
	public void showLoading() {
		if (loadBox == null) {
			createLodBox();
		}
		loadBox.center();
		loadBox.show();
	}

	/**
	 * Метод скрытия LoadingBox'а
	 */
	public void hideLoading() {
		if (loadBox != null) {
			loadBox.hide();
		}
	}
}