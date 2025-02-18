package adas.data;

import adas.util.NumberHandler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Arrays;

/**
 * A wrapper for the EEG signal as received from the Emotiv WebSocket server.
 *
 * @author javiergs
 * @version 2.0, 2024-07-28
 */
public class EEG extends Signal {
	
	private static final Logger logger = LogManager.getLogger(EEG.class);
	public static String[] sensorNames;
	
	private Integer counter;
	private boolean interpolated;
	private LinkedList<BigDecimal> sensors;
	private BigDecimal contactQuality;
	
	public EEG(BigDecimal time) {
		super(time);
		sensors = new LinkedList<>();
		labels = EEG_LABELS;
		int sizeA = EEG_LABELS.length;
		int sizeB = sensorNames.length;
		labels = new String[sizeA + sizeB];
		System.arraycopy(EEG_LABELS, 0, labels, 0, sizeA);
		System.arraycopy(sensorNames, 0, labels, sizeA, sizeB);
	}
	
	public EEG addCounter(Object o) {
		counter = (o != JSONObject.NULL && o instanceof Number) ? (Integer) o : Integer.valueOf(-1);
		return this;
	}
	
	public EEG addInterpolated(Object o) {
		interpolated = (boolean) o;
		return this;
	}
	
	public EEG addSensors(Object o) {
		BigDecimal value = NumberHandler.safeAssignment(o, new BigDecimal(-1));
		sensors.add(value);
		return this;
	}
	
	public EEG addContactQuality(Object o) {
		contactQuality = NumberHandler.safeAssignment(o, new BigDecimal(-1));
		return this;
	}
	
	public Integer getCounter() {
		return counter;
	}
	
	public boolean isInterpolated() {
		return interpolated;
	}
	
	public BigDecimal getContactQuality() {
		return contactQuality;
	}
	
	public BigDecimal getSensorValueAt(int i) {
		return sensors.get(i);
	}
	
	public static String[] getSensorNames() {
		return sensorNames;
	}
	
	public String get(String attribute) {
		String result ="";
		if (attribute.equals("Timestamp")) {
			result = getTimeAsString();
		} else if (attribute.equals("Time")) {
			result = getTimeAsNumber().toString();
		} else if (attribute.equals("Counter")) {
			result = String.valueOf(counter);
		} else if (attribute.equals("Interpolated")) {
			result = String.valueOf(interpolated);
		} else if (attribute.equals("Sensors")) {
			result = String.valueOf(sensors);
		} else if (attribute.equals("Contact Quality")) {
			result = contactQuality.toString();
			// there is a method to search a value on an array
		} else if (Arrays.asList(sensorNames).contains(attribute)) {
			int index = Arrays.asList(sensorNames).indexOf(attribute);
			result = sensors.get(index).toString();
		} else {
			LogManager.getLogger(EEG.class).error("Unknown attribute: " + attribute);
		}
		return result;
	}
	
	@Override
	public String toString() {
		return "EEG{" +
			"time=" + getTimeAsString() +
			", counter=" + counter +
			", interpolated=" + interpolated +
			", sensors=" + sensors +
			", contactQuality=" + contactQuality +
			'}';
	}
	
	public static String[] EEG_LABELS = {
		"Time",
		"Timestamp",
		"Counter",
		"Interpolated",
		"Contact Quality"
	};
	
	public static void setSensorNames(JSONArray array) {
		sensorNames = new String[array.length()];
		for (int i = 0; i < array.length(); i++) {
			sensorNames[i] = array.getString(i);
		}
	}

}