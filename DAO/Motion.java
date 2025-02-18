package adas.data;

import adas.util.NumberHandler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.math.BigDecimal;

/**
 * A wrapper for the motion data as received from the Emotiv WebSocket server.
 *
 * @author javiergs
 * @version 2.0, 2024-07-28
 */
public class Motion extends Signal {
	
	private static final Logger logger = LogManager.getLogger(Motion.class);
	
	private Integer counter;
	private boolean interpolated;
	private BigDecimal accelerometerX;
	private BigDecimal accelerometerY;
	private BigDecimal accelerometerZ;
	private BigDecimal magnetometerX;
	private BigDecimal magnetometerY;
	private BigDecimal magnetometerZ;
	private BigDecimal quaternion0;
	private BigDecimal quaternion1;
	private BigDecimal quaternion2;
	private BigDecimal quaternion3;
	
	public Motion(BigDecimal time) {
		super(time);
		labels = MOTION_LABELS;
	}
	
	public Motion addCounter(Object o) {
		counter = (o != JSONObject.NULL && o instanceof Number) ? (Integer) o : Integer.valueOf(-1);
		return this;
	}
	
	public Motion addInterpolated(Object o) {
		interpolated = (boolean) o;
		return this;
	}
	
	public Motion addAccelerometerX(Object o) {
		accelerometerX = NumberHandler.safeAssignment(o, new BigDecimal(-1));
		return this;
	}
	
	public Motion addAccelerometerY(Object o) {
		accelerometerY = NumberHandler.safeAssignment(o, new BigDecimal(-1));
		return this;
	}
	
	public Motion addAccelerometerZ(Object o) {
		accelerometerZ = NumberHandler.safeAssignment(o, new BigDecimal(-1));
		return this;
	}
	
	public Motion addMagnetometerX(Object o) {
		magnetometerX = NumberHandler.safeAssignment(o, new BigDecimal(-1));
		return this;
	}
	
	public Motion addMagnetometerY(Object o) {
		magnetometerY = NumberHandler.safeAssignment(o, new BigDecimal(-1));
		return this;
	}
	
	public Motion addMagnetometerZ(Object o) {
		magnetometerZ = NumberHandler.safeAssignment(o, new BigDecimal(-1));
		return this;
	}
	
	public Motion addQuaternion0(Object o) {
		quaternion0 = NumberHandler.safeAssignment(o, new BigDecimal(-1));
		return this;
	}
	
	public Motion addQuaternion1(Object o) {
		quaternion1 = NumberHandler.safeAssignment(o, new BigDecimal(-1));
		return this;
	}
	
	public Motion addQuaternion2(Object o) {
		quaternion2 = NumberHandler.safeAssignment(o, new BigDecimal(-1));
		return this;
	}
	
	public Motion addQuaternion3(Object o) {
		quaternion3 = NumberHandler.safeAssignment(o, new BigDecimal(-1));
		return this;
	}
	
	public BigDecimal getAccelerometerX() {
		return accelerometerX;
	}
	
	public BigDecimal getAccelerometerY() {
		return accelerometerY;
	}
	
	public BigDecimal getAccelerometerZ() {
		return accelerometerZ;
	}
	
	public BigDecimal getMagnetometerX() {
		return magnetometerX;
	}
	
	public BigDecimal getMagnetometerY() {
		return magnetometerY;
	}
	
	public BigDecimal getMagnetometerZ() {
		return magnetometerZ;
	}
	
	public BigDecimal getQuaternion0() {
		return quaternion0;
	}
	
	public BigDecimal getQuaternion1() {
		return quaternion1;
	}
	
	public BigDecimal getQuaternion2() {
		return quaternion2;
	}
	
	public BigDecimal getQuaternion3() {
		return quaternion3;
	}
	
	public Integer getCounter() {
		return counter;
	}
	
	public boolean isInterpolated() {
		return interpolated;
	}
	
	@Override
	public String get(String attribute) {
		String result;
		if (attribute.equals("Timestamp")) {
			result = getTimeAsString();
		} else if (attribute.equals("Time")) {
			result = getTimeAsNumber().toString();
		} else if (attribute.equals("Counter")) {
			result = String.valueOf(counter);
		} else if (attribute.equals("Interpolated")) {
			result = String.valueOf(interpolated);
		} else if (attribute.equals("Accelerometer X")) {
			result = accelerometerX.toString();
		} else if (attribute.equals("Accelerometer Y")) {
			result = accelerometerY.toString();
		} else if (attribute.equals("Accelerometer Z")) {
			result = accelerometerZ.toString();
		} else if (attribute.equals("Magnetometer X")) {
			result = magnetometerX.toString();
		} else if (attribute.equals("Magnetometer Y")) {
			result = magnetometerY.toString();
		} else if (attribute.equals("Magnetometer Z")) {
			result = magnetometerZ.toString();
		} else if (attribute.equals("Quaternion 0")) {
			result = quaternion0.toString();
		} else if (attribute.equals("Quaternion 1")) {
			result = quaternion1.toString();
		} else if (attribute.equals("Quaternion 2")) {
			result = quaternion2.toString();
		} else if (attribute.equals("Quaternion 3")) {
			result = quaternion3.toString();
		} else {
			logger.error("Unknown attribute: " + attribute);
			result = "";
		}
		return result;
	}
	
	@Override
	public String toString() {
		return "Motion{" +
			"time=" + getTimeAsString() +
			", counter=" + counter +
			", interpolated=" + interpolated +
			", accelerometerX=" + accelerometerX +
			", accelerometerY=" + accelerometerY +
			", accelerometerZ=" + accelerometerZ +
			", magnetometerX=" + magnetometerX +
			", magnetometerY=" + magnetometerY +
			", magnetometerZ=" + magnetometerZ +
			", quaternion0=" + quaternion0 +
			", quaternion1=" + quaternion1 +
			", quaternion2=" + quaternion2 +
			", quaternion3=" + quaternion3 +
			'}';
	}
	
	public static String[] MOTION_LABELS = {
		"Time",
		"Timestamp",
		"Counter",
		"Interpolated",
		"Accelerometer X",
		"Accelerometer Y",
		"Accelerometer Z",
		"Magnetometer X",
		"Magnetometer Y",
		"Magnetometer Z",
		"Quaternion 0",
		"Quaternion 1",
		"Quaternion 2",
		"Quaternion 3"
	};
	
}