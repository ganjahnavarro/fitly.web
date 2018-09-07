package core.dto.report;

import core.dto.ProgramData;

public class ProgramPurchaseSummaryData {

	private ProgramData program;
	private Long count;

	public ProgramData getProgram() {
		return program;
	}

	public void setProgram(ProgramData program) {
		this.program = program;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

}
