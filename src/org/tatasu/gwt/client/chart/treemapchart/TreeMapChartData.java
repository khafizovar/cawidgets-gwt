package org.tatasu.gwt.client.chart.treemapchart;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.internal.txw2.annotation.XmlElement;

/**
 * Единица данных для графика TreeMapChart
 * 
 * @author HafizovAR
 * 
 */
public class TreeMapChartData implements Serializable {

	/**	 */
	private static final long	serialVersionUID	= 1L;
	/** Наименование объекта */
	private String				name;
	/** Родитель объекта (Будут объединены по родителю) */
	private String				parent;
	/** Размер на полотне графика */
	private double				size;
	/** Цвет на полотне графика */
	private double				color;
	private String				popUpString			= "";

	public TreeMapChartData() {}
	public TreeMapChartData(String name, String parent, double size, double color) {
		super();
		this.name = name;
		this.parent = parent;
		this.size = size;
		this.color = color;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		this.size = size;
	}
	public double getColor() {
		return color;
	}
	public void setColor(double color) {
		this.color = color;
	}
	public String getPopUpString() {
		return popUpString;
	}
	public void setPopUpString(String popUpString) {
		this.popUpString = popUpString;
	}
}
