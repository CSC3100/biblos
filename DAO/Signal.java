package adas.data;

import adas.util.TimeHandler;

import java.math.BigDecimal;

/**
 * A wrapper for received data
 *
 * @author javiergs
 * @version 2.0, 2024-07-28
 */
public abstract class Signal {
	
	protected String[] labels;
	protected BigDecimal time;
	
	public Signal(BigDecimal t) {
		time = t;
	}
	
	public String getTimeAsString() {
		return TimeHandler.timeToString(time);
	}
	
	public BigDecimal getTimeAsNumber() {
		return time;
	}
	
	public String getHeaderForFile() {
		String line = "";
		for (String attribute : labels) {
			line = line + attribute + ",";
		}
		line = line.substring(0, line.length() - 1);
		return line;
	}
	
	public abstract String get(String attribute);
	
	public String getRowForFile() {
		String line = "";
		for (String attribute : labels) {
			line = line + get(attribute) + ",";
		}
		line = line.substring(0, line.length() - 1);
		return line;
	}
	
}