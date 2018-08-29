package core.dto;

import java.math.BigDecimal;

import core.model.member.Member;

public class MembershipData extends RecordData {

	private Member member;

	private String startDate;
	private String endDate;

	private String accessCardNo;
	private BigDecimal amount;

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
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

	public String getAccessCardNo() {
		return accessCardNo;
	}

	public void setAccessCardNo(String accessCardNo) {
		this.accessCardNo = accessCardNo;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
