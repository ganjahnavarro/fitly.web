package core.dto;

import java.math.BigDecimal;

public class PromoData extends RecordData {

	private String code;
	private String description;
	private BigDecimal lessAmount;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getLessAmount() {
		return lessAmount;
	}

	public void setLessAmount(BigDecimal lessAmount) {
		this.lessAmount = lessAmount;
	}

}
