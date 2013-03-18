package org.tatasu.gwt.client.chart.treemapchart.options;


public class TreeMapChartOptionsEnum {
	/**
	 * Опции JS объекта DateTimePicker Kendo
	 * @author mol4un
	 */
	public enum Options {
		HEADER_COLOR("headerColor"),
		HEADER_HEIGHT("headerHeight"),
		HEADER_HIGHLIGHT_COLOR("headerHighlightColor"),
		MAX_COLOR("maxColor"),
		MAX_DEPTH("maxDepth"),
		MAX_HIGHLIGHT_COLOR("maxHighlightColor"),
		MAX_POST_DEPTH("maxPostDepth"),
		MAX_COLOR_VALUE("maxColorValue"),
		MID_COLOR("midColor"),
		MID_HIGHLIGHT_COLOR("midHighlightColor"),
		MIN_COLOR("minColor"),
		MIN_HIGHLIGHT_COLOR("minHighlightColor"),
		MIN_COLOR_VALUE("minColorValue"),
		NO_COLOR("noColor"),
		NO_HIGHLIGHT_COLOR("noHighlightColor"),
		SHOW_SCALE("showScale"),
		SHOW_TOOLTIPS("showTooltips"),
		FONT_COLOR("fontColor"),
		FONT_FAMILY("fontFamily"),
		FONT_SIZE("fontSize");
		private String name;		
		private Options(String name) {
			this.name = name;
		}		
		public String getName() {
			return this.name;
		}
	}
}
