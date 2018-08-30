package core.dto;

import java.math.BigDecimal;

import core.enums.Duration;

public class PackageAvailmentData extends RecordData {

	private MemberData member;
	private PackageData availedPackage;
	private String date;

	private Duration duration;
	private Integer durationCount;
	private Integer sessionsCount;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
