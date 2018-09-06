package core.dto;

import java.math.BigDecimal;

import core.enums.AvailmentType;

public class ProgramAvailmentData extends RecordData {

	private MemberData member;
	private ProgramData availedProgram;

	private String startDate;
	private String endDate;

	private AvailmentType type;
	private BigDecimal price;

	public MemberData getMember() {
		return member;
	}

	public void setMember(MemberData member) {
		this.member = member;
	}

	public ProgramData getAvailedProgram() {
		return availedProgram;
	}

	public void setAvailedProgram(ProgramData availedProgram) {
		this.availedProgram = availedProgram;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setType(AvailmentType type) {
		this.type = type;
	}

	public AvailmentType getType() {
		return type;
	}

}
