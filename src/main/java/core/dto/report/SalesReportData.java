package core.dto.report;

import java.math.BigDecimal;
import java.util.Date;

import core.dto.PersonData;

public class SalesReportData {

	private Date date;
	private PersonData person;
	private String description;
	private BigDecimal amount;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public PersonData getPerson() {
		return person;
	}

	public void setPerson(PersonData person) {
		this.person = person;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
