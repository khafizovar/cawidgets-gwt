package org.tatasu.gwt.client.chart.treemapchart;

import java.io.Serializable;

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
	private String				Name;
	/** Родитель объекта (Будут объединены по родителю) */
	private String				Parent;
	/** Размер на полотне графика */
	private double				size;
	/** Цвет на полотне графика */
	private double				color;
	private String				popUpString			= "";

	public TreeMapChartData() {}
	public TreeMapChartData(String name, String parent, double size, double color) {
		super();
		Name = name;
		Parent = parent;
		this.size = size;
		this.color = color;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getParent() {
		return Parent;
	}
	public void setParent(String parent) {
		Parent = parent;
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
