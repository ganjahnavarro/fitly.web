package core.dto.report;

import core.dto.CoachData;

public class CoachEnrolleesData {

	private CoachData coach;
	private Long count;

	public CoachData getCoach() {
		return coach;
	}

	public void setCoach(CoachData coach) {
		this.coach = coach;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

}
