package core.dto;

import java.math.BigDecimal;

public class ProgramData extends RecordData {

	private String name;
	private String description;

	private BigDecimal guestPrice;
	private BigDecimal memberPrice;
	private BigDecimal monthlyPrice;

	private Boolean hasCoach;
	private BigDecimal coachPrice;

	private BigDecimal commission;
	private Boolean active;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getGuestPrice() {
		return guestPrice;
	}

	public void setGuestPrice(BigDecimal guestPrice) {
		this.guestPrice = guestPrice;
	}

	public BigDecimal getMemberPrice() {
		return memberPrice;
	}

	public void setMemberPrice(BigDecimal memberPrice) {
		this.memberPrice = memberPrice;
	}

	public BigDecimal getMonthlyPrice() {
		return monthlyPrice;
	}

	public void setMonthlyPrice(BigDecimal monthlyPrice) {
		this.monthlyPrice = monthlyPrice;
	}

	public Boolean getHasCoach() {
		return hasCoach;
	}

	public void setHasCoach(Boolean hasCoach) {
		this.hasCoach = hasCoach;
	}

	public BigDecimal getCoachPrice() {
		return coachPrice;
	}

	public void setCoachPrice(BigDecimal coachPrice) {
		this.coachPrice = coachPrice;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public BigDecimal getCommission() {
		return commission;
	}

	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}

}
