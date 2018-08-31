package core.dto;

import java.math.BigDecimal;

public class CoachData extends PersonData {

	private BigDecimal commissionPercent;

	public BigDecimal getCommissionPercent() {
		return commissionPercent;
	}

	public void setCommissionPercent(BigDecimal commissionPercent) {
		this.commissionPercent = commissionPercent;
	}

}
