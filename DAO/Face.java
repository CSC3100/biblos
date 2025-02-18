package adas.data;

import adas.util.NumberHandler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.math.BigDecimal;

public class Face extends Signal {
	
	private static final Logger logger = LogManager.getLogger(Face.class);
	
	private String actionEye;
	private String actionUpperFace;
	private String actionLowerFace;
	private BigDecimal powerUpperFace;
	private BigDecimal powerLowerFace;
	
	public Face(BigDecimal time) {
		super(time);
		labels = FACE_LABELS;
	}
	
	public Face addActionEye(Object o) {
		actionEye = (String) o;
		return this;
	}
	
	public Face addActionUpperFace(Object o) {
		actionUpperFace = (String) o;
		return this;
	}
	
	public Face addActionLowerFace(Object o) {
		actionLowerFace = (String) o;
		return this;
	}
	
	public Face addActionUpperFacePower(Object o) {
		powerUpperFace = NumberHandler.safeAssignment(o, new BigDecimal(-1));
		return this;
	}
	
	public Face addActionLowerFacePower(Object o) {
		powerLowerFace = NumberHandler.safeAssignment(o, new BigDecimal(-1));
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
			case "Action Eye":
				result = getActionEye();
				break;
			case "Action Upper Face":
				result = getActionUpperFace();
				break;
			case "Power Upper Face":
				result = getPowerUpperFace().toString();
				break;
			case "Action Lower Face":
				result = getActionLowerFace();
				break;
			case "Power Lower Face":
				result = getPowerLowerFace().toString();
				break;
			default:
				logger.error("Unknown attribute: " + attribute);
		}
		return result;
	}
	
	private String getActionEye() {
		if (actionEye != null)
			return actionEye;
		else
			return "none";
	}
	
	private String getActionUpperFace() {
		if (actionUpperFace != null)
			return actionUpperFace;
		else return "none";
	}
	
	private String getActionLowerFace() {
		if (actionLowerFace != null)
			return actionLowerFace;
		else return "none";
	}
	
	private BigDecimal getPowerUpperFace() {
		if (powerUpperFace != null)
			return powerUpperFace;
		else return new BigDecimal(-1);
	}
	
	private BigDecimal getPowerLowerFace() {
		if (powerLowerFace != null)
			return powerLowerFace;
		else return new BigDecimal(-1);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Face: {")
			.append("eye:").append(actionEye).append(", ")
			.append("upperFaceAction:").append(actionUpperFace).append(", ")
			.append("upperFacePower:").append(powerUpperFace).append(", ")
			.append("lowerFaceAction:").append(actionLowerFace).append(", ")
			.append("lowerFacePower:").append(powerLowerFace)
			.append("}");
		return sb.toString();
	}
	
	private static final String[] FACE_LABELS = {
		"Time",
		"Timestamp",
		"Action Eye",
		"Action Upper Face",
		"Power Upper Face",
		"Action Lower Face",
		"Power Lower Face"
	};
	
}
