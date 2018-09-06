package core.dto;

import java.math.BigDecimal;

import core.enums.MemberType;

public class MemberData extends PersonData {

	private MemberType type;

	private BigDecimal width;
	private BigDecimal height;

	private CoachData defaultCoach;

	public MemberType getType() {
		return type;
	}

	public void setType(MemberType type) {
		this.type = type;
	}

	public BigDecimal getWidth() {
		return width;
	}

	public void setWidth(BigDecimal width) {
		this.width = width;
	}

	public BigDecimal getHeight() {
		return height;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
	}

	public CoachData getDefaultCoach() {
		return defaultCoach;
	}

	public void setDefaultCoach(CoachData defaultCoach) {
		this.defaultCoach = defaultCoach;
	}

}
