package core.dto;

import java.math.BigDecimal;

public class TimeEntryData extends RecordData {

	private String date;

	private MemberData member;
	private String accessCardNoUsed;

	private CoachData coachAssigned;
	private BigDecimal commission;

	private ProgramAvailmentData programaAvailment;
	private PackageAvailmentData packageAvailment;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public MemberData getMember() {
		return member;
	}

	public String getAccessCardNoUsed() {
		return accessCardNoUsed;
	}

	public void setAccessCardNoUsed(String accessCardNoUsed) {
		this.accessCardNoUsed = accessCardNoUsed;
	}

	public void setMember(MemberData member) {
		this.member = member;
	}

	public CoachData getCoachAssigned() {
		return coachAssigned;
	}

	public void setCoachAssigned(CoachData coachAssigned) {
		this.coachAssigned = coachAssigned;
	}

	public BigDecimal getCommission() {
		return commission;
	}

	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}

	public ProgramAvailmentData getProgramaAvailment() {
		return programaAvailment;
	}

	public void setProgramaAvailment(ProgramAvailmentData programaAvailment) {
		this.programaAvailment = programaAvailment;
	}

	public PackageAvailmentData getPackageAvailment() {
		return packageAvailment;
	}

	public void setPackageAvailment(PackageAvailmentData packageAvailment) {
		this.packageAvailment = packageAvailment;
	}

}
