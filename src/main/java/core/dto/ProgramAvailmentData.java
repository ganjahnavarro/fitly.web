package core.dto;

import java.math.BigDecimal;
import java.util.Date;

import core.enums.AvailmentType;

public class ProgramAvailmentData extends RecordData {

	private MemberData member;
	private ProgramData availedProgram;
	private Date date;

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public AvailmentType getType() {
		return type;
	}

	public void setType(AvailmentType type) {
		this.type = type;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
