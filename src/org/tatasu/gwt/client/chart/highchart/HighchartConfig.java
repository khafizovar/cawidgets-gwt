package org.tatasu.gwt.client.chart.highchart;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.tatasu.gwt.client.chart.highchart.subclasses.Credits;
import org.tatasu.gwt.client.chart.highchart.subclasses.Legend;
import org.tatasu.gwt.client.chart.highchart.subclasses.Series;

@XmlRootElement
public class HighchartConfig {

	/** Тип серии, н-р Series.Type.SPLINE */
	private String			seriesType;
	private Number			marginRight		= 130;
	private Number			marginBottom	= 25;
	private Number			marginTop		= 25;
	private Number			marginLeft		= 25;
	/** Заголовок */
	private String			titleText		= "Title text";
	private String			chartWidth		= "100%";
	private String			chartHeight		= "700px";	
	//private List<Series>	seriesInChart	= null;
	private Legend			legend			= new Legend();
	private Credits			credits			= new Credits();

	public HighchartConfig() {};
	@XmlElement
	public Legend getLegend() {
		return legend;
	}
	public void setLegend(Legend legend) {
		this.legend = legend;
	}
	@XmlElement
	public Credits getCredits() {
		return credits;
	}
	public void setCredits(Credits credits) {
		this.credits = credits;
	}
	@XmlElement
	public String getSeriesType() {
		return seriesType;
	}
	public void setSeriesType(String seriesType) {
		this.seriesType = seriesType;
	}
	@XmlElement
	public Number getMarginRight() {
		return marginRight;
	}
	public void setMarginRight(Number marginRight) {
		this.marginRight = marginRight;
	}
	@XmlElement
	public Number getMarginBottom() {
		return marginBottom;
	}
	public void setMarginBottom(Number marginBottom) {
		this.marginBottom = marginBottom;
	}
	@XmlElement
	public String getTitleText() {
		return titleText;
	}
	public void setTitleText(String titleText) {
		this.titleText = titleText;
	}
	/*@XmlElement
	public List<Series> getSeriesInChart() {
		return seriesInChart;
	}
	public void setSeriesInChart(List<Series> seriesInChart) {
		this.seriesInChart = seriesInChart;
	}*/
	@XmlElement
	public String getChartWidth() {
		return chartWidth;
	}
	public void setChartWidth(String chartWidth) {
		this.chartWidth = chartWidth;
	}
	@XmlElement
	public String getChartHeight() {
		return chartHeight;
	}
	public void setChartHeight(String chartHeight) {
		this.chartHeight = chartHeight;
	}
	@XmlElement
	public Number getMarginTop() {
		return marginTop;
	}
	public void setMarginTop(Number marginTop) {
		this.marginTop = marginTop;
	}
	@XmlElement
	public Number getMarginLeft() {
		return marginLeft;
	}
	public void setMarginLeft(Number marginLeft) {
		this.marginLeft = marginLeft;
	}
}
