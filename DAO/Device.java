package adas.data;

import adas.model.Blackboard;
import adas.util.NumberHandler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONArray;

import java.math.BigDecimal;
import java.util.Arrays;

public class Device extends Signal {
	
	private static final Logger logger = LogManager.getLogger(Device.class);
	
	private Integer batteryLevel;
	private Integer batteryPercent;
	private BigDecimal wirelessSignal;
	private BigDecimal qualityOverall;
	private BigDecimal[] qualityPerSensor;
	
	public Device(BigDecimal time, int sensors) {
		super(time);
		qualityPerSensor = new BigDecimal[sensors];
		if (sensors == 14) {
			labels = EPOC_LABELS;
		} else if (sensors == 5) {
			labels = INSIGHT_LABELS;
		} else if (sensors == 2) {
			labels = MN8_LABELS;
		} else {
			labels = new String[0];
			logger.error("Unknown number of sensors: " + sensors);
		}
	}
	
	public Device addBatteryLevel(Object o) {
		batteryLevel = (Integer) o;
		return this;
	}
	
	public Device addBatteryPercent(Object o) {
		batteryPercent = (o != null) ? (Integer) o : Integer.valueOf(-1);
		return this;
	}
	
	public Device addWirelessSignal(Object o) {
		wirelessSignal = NumberHandler.safeAssignment(o, new BigDecimal(-1));
		return this;
	}
	
	public Device addQuality(Object o) {
		JSONArray array = (JSONArray) o;
		if (array.length() - 1 != Blackboard.SENSORS) {
			logger.error("Expected " + Blackboard.SENSORS + " sensors, but received " + array.length());
		} else {
			for (int i = 0; i < array.length() - 1; i++) {
				this.qualityPerSensor[i] = array.getBigDecimal(i);
			}
		}
		this.qualityOverall = array.getBigDecimal(array.length() - 1);
		return this;
	}
	
	@Override
	public String get(String attribute) {
		String result = "";
		switch (attribute) {
			case "Time":
				result = getTimeAsNumber().toString();
				break;
			case "Timestamp":
				result = getTimeAsString();
				break;
			case "Battery Level":
				result = batteryLevel.toString();
				break;
			case "Battery Percent":
				result = batteryPercent.toString();
				break;
			case "Wireless Signal":
				result = wirelessSignal.toString();
				break;
			case "Overall Quality":
				result = qualityOverall.toString();
				break;
			default:
				if (attribute.startsWith("Quality Sensor ")) {
					int index = Integer.parseInt(attribute.replace("Quality Sensor ", ""));
					if (index >= 0 && index < qualityPerSensor.length) {
						result = qualityPerSensor[index].toString();
					}
				} else {
					logger.error("Unknown attribute: " + attribute);
				}
				break;
		}
		return result;
	}
	
	@Override
	public String toString() {
		return "Device{" +
			"time=" + getTimeAsString() +
			", batteryLevel=" + batteryLevel +
			", batteryPercent=" + batteryPercent +
			", wirelessSignal=" + wirelessSignal +
			", qualityOverall=" + qualityOverall +
			", qualityPerSensor=" + Arrays.toString(qualityPerSensor) +
			'}';
	}
	
	private static final String[] EPOC_LABELS = {
		"Time",
		"Timestamp",
		"Battery Level",
		"Battery Percent",
		"Wireless Signal",
		"Overall Quality",
		"Quality Sensor 0",
		"Quality Sensor 1",
		"Quality Sensor 2",
		"Quality Sensor 3",
		"Quality Sensor 4",
		"Quality Sensor 5",
		"Quality Sensor 6",
		"Quality Sensor 7",
		"Quality Sensor 8",
		"Quality Sensor 9",
		"Quality Sensor 10",
		"Quality Sensor 11",
		"Quality Sensor 12",
		"Quality Sensor 13"
	};
	
	private static final String[] INSIGHT_LABELS = {
		"Time",
		"Timestamp",
		"Battery Level",
		"Battery Percent",
		"Wireless Signal",
		"Overall Quality",
		"Quality Sensor 0",
		"Quality Sensor 1",
		"Quality Sensor 2",
		"Quality Sensor 3",
		"Quality Sensor 4"
	};
	
	private static final String[] MN8_LABELS = {
		"Time",
		"Timestamp",
		"Battery Level",
		"Battery Percent",
		"Wireless Signal",
		"Overall Quality",
		"Quality Sensor 0",
		"Quality Sensor 1"
	};
	
}
