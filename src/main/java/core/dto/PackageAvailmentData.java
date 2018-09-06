package core.dto;

import java.math.BigDecimal;

import core.enums.Duration;

public class PackageAvailmentData extends RecordData {

	private MemberData member;
	private PackageData availedPackage;

	private String startDate;
	private String endDate;

	private Duration duration;
	private Integer durationCount;
	
	private Integer sessionsCount;
	private Integer sessionsRemaining;
	
	private BigDecimal price;

	public MemberData getMember() {
		return member;
	}

	public void setMember(MemberData member) {
		this.member = member;
	}

	public PackageData getAvailedPackage() {
		return availedPackage;
	}

	public void setAvailedPackage(PackageData availedPackage) {
		this.availedPackage = availedPackage;
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

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	public Integer getDurationCount() {
		return durationCount;
	}

	public void setDurationCount(Integer durationCount) {
		this.durationCount = durationCount;
	}

	public Integer getSessionsCount() {
		return sessionsCount;
	}

	public void setSessionsCount(Integer sessionsCount) {
		this.sessionsCount = sessionsCount;
	}
	
	public Integer getSessionsRemaining() {
		return sessionsRemaining;
	}

	public void setSessionsRemaining(Integer sessionsRemaining) {
		this.sessionsRemaining = sessionsRemaining;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
