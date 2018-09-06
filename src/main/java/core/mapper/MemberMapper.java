package core.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import core.dto.MemberData;
import core.model.member.Member;

@Mapper(uses = { CoachMapper.class })
public interface MemberMapper {
	
	MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

	@Mapping(target = "modifiedDate", source = "modifiedDate", dateFormat = "MM/dd/yyyy HH:mm")
	@Mapping(target = "birthDate", source = "birthDate", dateFormat = "MM/dd/yyyy")
	MemberData toData(Member member);
	
	@IterableMapping(dateFormat = "MM/dd/yyyy HH:mm")
	List<MemberData> toData(List<Member> members);
	
	@InheritInverseConfiguration
	Member fromData(MemberData memberData);

}
