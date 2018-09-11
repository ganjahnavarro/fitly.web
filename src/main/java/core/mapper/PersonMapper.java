package core.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import core.dto.CoachData;
import core.dto.MemberData;
import core.dto.PersonData;
import core.model.coach.Coach;
import core.model.member.Member;
import core.model.person.Person;

@Mapper
public interface PersonMapper {

	PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

	default PersonData toData(Person person) {
		if (person instanceof Member)
			return toMemberData((Member) person);
		return toCoachData((Coach) person);
	}

	default Person fromData(PersonData personData) {
		if (personData instanceof MemberData)
			return fromMemberData((MemberData) personData);
		return fromCoachData((CoachData) personData);
	}

	@Mapping(target = "modifiedDate", source = "modifiedDate", dateFormat = "MM/dd/yyyy HH:mm")
	@Mapping(target = "birthDate", source = "birthDate", dateFormat = "MM/dd/yyyy")
	CoachData toCoachData(Coach coach);

	@InheritInverseConfiguration
	Coach fromCoachData(CoachData coachData);

	@Mapping(target = "modifiedDate", source = "modifiedDate", dateFormat = "MM/dd/yyyy HH:mm")
	@Mapping(target = "birthDate", source = "birthDate", dateFormat = "MM/dd/yyyy")
	MemberData toMemberData(Member member);

	@InheritInverseConfiguration
	Member fromMemberData(MemberData memberData);

}
