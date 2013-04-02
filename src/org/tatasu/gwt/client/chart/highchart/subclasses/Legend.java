package org.tatasu.gwt.client.chart.highchart.subclasses;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Класс легенды
 * 
 * @author HafizovAR
 */
@XmlRootElement(name = "legend")
public class Legend implements Serializable {

	private static final long	serialVersionUID	= 1L;
	private boolean				enabled				= true;
	private String				layout;
	private String				align;
	private String				verticalAlign;
	private Number				x;
	private Number				y;
	private Number				borderWidth;

	public Legend() {}
	
	@XmlElement
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	@XmlElement
	public String getLayout() {
		return layout;
	}
	public void setLayout(String layout) {
		this.layout = layout;
	}
	@XmlElement
	public String getAlign() {
		return align;
	}
	public void setAlign(String align) {
		this.align = align;
	}
	@XmlElement
	public String getVerticalAlign() {
		return verticalAlign;
	}
	public void setVerticalAlign(String verticalAlign) {
		this.verticalAlign = verticalAlign;
	}
	@XmlElement
	public Number getX() {
		return x;
	}
	public void setX(Number x) {
		this.x = x;
	}
	@XmlElement
	public Number getY() {
		return y;
	}
	public void setY(Number y) {
		this.y = y;
	}
	@XmlElement
	public Number getBorderWidth() {
		return borderWidth;
	}
	public void setBorderWidth(Number borderWidth) {
		this.borderWidth = borderWidth;
	}
}
