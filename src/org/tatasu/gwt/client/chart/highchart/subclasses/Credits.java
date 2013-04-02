package org.tatasu.gwt.client.chart.highchart.subclasses;

import java.io.Serializable;


public class Credits implements Serializable {
	
	private static final long	serialVersionUID	= 1L;
	private boolean credits = false;
	
	public Credits() {};
	public boolean isCredits() {
		return credits;
	}
	public void setCredits(boolean credits) {
		this.credits = credits;
	}
}
