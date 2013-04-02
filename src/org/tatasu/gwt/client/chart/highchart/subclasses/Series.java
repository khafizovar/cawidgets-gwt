package org.tatasu.gwt.client.chart.highchart.subclasses;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * Класс серий
 * @author HafizovAR
 */
@XmlRootElement(name = "series")
public class Series implements Serializable {

	private static final long	serialVersionUID	= 1L;
	/** Наименование серии */
	private String		name;
	/** Точки серии */
	private Number[]	points;
	
	public Series() {}
	@XmlElement
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@XmlElement
	public Number[] getPoints() {
		return points;
	}
	public void setPoints(Number[] points) {
		this.points = points;
	}
}
