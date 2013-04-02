package org.tatasu.gwt.server.treemapchart;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.tatasu.gwt.client.chart.treemapchart.TreeMapChartData;

/**
 * Расширение ArrayList c типизацией по TreemapChart для маршаллинга списка
 * данных графика treemapChart
 * 
 * @author HafizovAR
 * 
 */
@XmlRootElement
@XmlSeeAlso({ TreeMapChartData.class })
public class TreeMapChartDataList extends ArrayList<TreeMapChartData> {

	private static final long	serialVersionUID	= 1L;
	public TreeMapChartDataList() {}
	
	@XmlElement
	public List<TreeMapChartData> getData() {
		return this;
	}
	
	public void setData(List<TreeMapChartData> data) {
		this.addAll(data);
	}
	
}
