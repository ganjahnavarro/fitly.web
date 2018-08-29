package core.dto;

import java.math.BigDecimal;

import core.enums.Duration;

public class PackageData extends ProductData {

	private Duration duration;
	private Integer durationCount;
	private Integer sessionsCount;
	private BigDecimal price;

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
