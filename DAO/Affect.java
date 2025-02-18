package adas.data;

import adas.util.NumberHandler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.math.BigDecimal;

/**
 * A wrapper for affective state metrics from Emotive headset.
 *
 * @author javiergs
 * @version 2.0, 2024-07-28
 */
public class Affect extends Signal {
	
	private static final Logger logger = LogManager.getLogger(Affect.class);
	
	private BigDecimal excitement;
	private BigDecimal engagement;
	private BigDecimal stress;
	private BigDecimal relaxation;
	private BigDecimal interest;
	private BigDecimal focus;
	private boolean activeExcitement;
	private boolean activeEngagement;
	private boolean activeStress;
	private boolean activeRelaxation;
	private boolean activeInterest;
	private boolean activeFocus;
	
	public Affect(BigDecimal time) {
		super(time);
		labels = AFFECT_LABELS;
	}
	
	public Affect addEngagement(Object o) {
		engagement = NumberHandler.safeAssignment(o, new BigDecimal(-1));
		return this;
	}
	
	public Affect addExcitement(Object o) {
		excitement = NumberHandler.safeAssignment(o, new BigDecimal(-1));
		return this;
	}
	
	public Affect addStress(Object o) {
		stress = NumberHandler.safeAssignment(o, new BigDecimal(-1));
		return this;
	}
	
	public Affect addRelaxation(Object o) {
		relaxation = NumberHandler.safeAssignment(o, new BigDecimal(-1));
		return this;
	}
	
	public Affect addInterest(Object o) {
		interest = NumberHandler.safeAssignment(o, new BigDecimal(-1));
		return this;
	}
	
	public Affect addFocus(Object o) {
		focus = NumberHandler.safeAssignment(o, new BigDecimal(-1));
		return this;
	}
	
	public Affect addActiveEngagement(Object o) {
		activeEngagement = (boolean) o;
		return this;
	}
	
	public Affect addActiveExcitement(Object o) {
		activeExcitement = (boolean) o;
		return this;
	}
	
	public Affect addActiveStress(Object o) {
		activeStress = (boolean) o;
		return this;
	}
	
	public Affect addActiveRelaxation(Object o) {
		activeRelaxation = (boolean) o;
		return this;
	}
	
	public Affect addActiveInterest(Object o) {
		activeInterest = (boolean) o;
		return this;
	}
	
	public Affect addActiveFocus(Object o) {
		activeFocus = (boolean) o;
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
			case "Active Attention or Focus":
				result = String.valueOf(activeFocus);
				break;
			case "Attention or Focus":
				result = (focus != null) ? focus.toString() : "";
				break;
			case "Active Engagement":
				result = String.valueOf(activeEngagement);
				break;
			case "Engagement":
				result = (engagement != null) ? engagement.toString() : "";
				break;
			case "Active Excitement":
				result = String.valueOf(activeExcitement);
				break;
			case "Excitement":
				result = (excitement != null) ? excitement.toString() : "";
				break;
			case "Active Interest":
				result = String.valueOf(activeInterest);
				break;
			case "Interest":
				result = (interest != null) ? interest.toString() : "";
				break;
			case "Active Relaxation":
				result = String.valueOf(activeRelaxation);
				break;
			case "Relaxation":
				result = (relaxation != null) ? relaxation.toString() : "";
				break;
			case "Active Stress":
				result = String.valueOf(activeStress);
				break;
			case "Stress":
				result = (stress != null) ? stress.toString() : "";
				break;
			default:
				logger.error("Unknown attribute: " + attribute);
				result = "";
				break;
		}
		return result;
	}
	
	public boolean isActiveRelaxation() {
		return activeRelaxation;
	}
	
	public boolean isActiveInterest() {
		return activeInterest;
	}
	
	public boolean isActiveFocus() {
		return activeFocus;
	}
	
	public boolean isActiveStress() {
		return activeStress;
	}
	
	public boolean isActiveExcitement() {
		return activeExcitement;
	}
	
	public boolean isActiveEngagement() {
		return activeEngagement;
	}
	
	public BigDecimal getEngagement() {
		return engagement;
	}
	
	public BigDecimal getExcitement() {
		return excitement;
	}
	
	public BigDecimal getStress() {
		return stress;
	}
	
	public BigDecimal getRelaxation() {
		return relaxation;
	}
	
	public BigDecimal getInterest() {
		return interest;
	}
	
	public BigDecimal getFocus() {
		return focus;
	}
	
	@Override
	public String toString() {
		String line = "Affect: {";
		for (String attribute : AFFECT_LABELS) {
			line = line + attribute + ": " + get(attribute) + ",";
		}
		line = line.substring(0, line.length() - 1);
		return line + "}";
	}
	
	public static final String[] AFFECT_LABELS = {
		"Time",
		"Timestamp",
		"Active Attention or Focus",
		"Attention or Focus",
		"Active Engagement",
		"Engagement",
		"Active Excitement",
		"Excitement",
		"Active Interest",
		"Interest",
		"Active Relaxation",
		"Relaxation",
		"Active Stress",
		"Stress",
	};
	
}