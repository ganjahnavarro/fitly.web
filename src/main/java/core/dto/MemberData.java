package core.dto;

import core.enums.MemberType;

public class MemberData extends PersonData {

	private MemberType type;

	public MemberType getType() {
		return type;
	}

	public void setType(MemberType type) {
		this.type = type;
	}

}
