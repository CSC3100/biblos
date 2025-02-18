package adas.data;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.math.BigDecimal;

public class PAD extends Signal {
	
	private static final Logger logger = LogManager.getLogger(PAD.class);
	
	private BigDecimal P;
	private BigDecimal A;
	private BigDecimal D;
	
	public PAD(Affect affect) {
		super(affect.getTimeAsNumber());
		labels = PAD_LABELS;
		P = new BigDecimal(0);
		A = new BigDecimal(0);
		D = new BigDecimal(0);
		calculatePAD(affect);
	}
	
	private void calculatePAD(Affect affect) {
		if (affect.isActiveExcitement()) { //++-
			P = P.add(affect.getExcitement());
			A = A.add(affect.getExcitement());
			D = D.subtract(affect.getExcitement());
			normalize();
		}
		if (affect.isActiveEngagement()) { //+++
			P = P.add(affect.getEngagement());
			A = A.add(affect.getEngagement());
			D = D.add(affect.getEngagement());
			normalize();
		}
		if (affect.isActiveStress()) { //-+-
			P = P.subtract(affect.getStress());
			A = A.add(affect.getStress());
			D = D.subtract(affect.getStress());
			normalize();
		}
		if (affect.isActiveFocus()) { //+-+
			P = P.add(affect.getFocus());
			A = A.subtract(affect.getFocus());
			D = D.add(affect.getFocus());
			normalize();
		}
		if (affect.isActiveInterest()) { //++-
			P = P.add(affect.getInterest());
			A = A.add(affect.getInterest());
			D = D.subtract(affect.getInterest());
			normalize();
		}
		if (affect.isActiveRelaxation()) { //+-+
			P = P.add(affect.getRelaxation());
			A = A.subtract(affect.getRelaxation());
			D = D.add(affect.getRelaxation());
			normalize();
		}
	}
	
	private void normalize() {
		double magnitude = Math.sqrt(
			P.doubleValue() * P.doubleValue() +
				A.doubleValue() * A.doubleValue() +
				D.doubleValue() * D.doubleValue()
		);
		if (magnitude > 0) {
			P = P.divide(new BigDecimal(magnitude), 2, BigDecimal.ROUND_HALF_UP);
			A = A.divide(new BigDecimal(magnitude), 2, BigDecimal.ROUND_HALF_UP);
			D = D.divide(new BigDecimal(magnitude), 2, BigDecimal.ROUND_HALF_UP);
		}
		
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
			case "Pleasure":
				result = P.toString();
				break;
			case "Arousal":
				result = A.toString();
				break;
			case "Dominance":
				result = D.toString();
				break;
			default:
				logger.error("Unknown attribute: " + attribute);
				break;
		}
		return result;
	}
	
	public BigDecimal getP() {
		return P;
	}
	
	public BigDecimal getA() {
		return A;
	}
	
	public BigDecimal getD() {
		return D;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("PAD: {")
			.append("P: ").append(P).append(", ")
			.append("A: ").append(A).append(", ")
			.append("D: ").append(D)
			.append("}");
		return sb.toString();
	}
	
	private static final String[] PAD_LABELS = {
		"Time",
		"Timestamp",
		"Pleasure",
		"Arousal",
		"Dominance"
	};
	
}
