package core.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import core.dto.MembershipData;
import core.model.member.Membership;

@Mapper
public interface MembershipMapper {
	
	MembershipMapper INSTANCE = Mappers.getMapper(MembershipMapper.class);

	@Mapping(target = "modifiedDate", source = "modifiedDate", dateFormat = "MM/dd/yyyy HH:mm")
	@Mapping(target = "startDate", source = "startDate", dateFormat = "MM/dd/yyyy")
	@Mapping(target = "endDate", source = "endDate", dateFormat = "MM/dd/yyyy")
	MembershipData toData(Membership membership);
	
	@IterableMapping(dateFormat = "MM/dd/yyyy HH:mm")
	List<MembershipData> toData(List<Membership> memberships);
	
	@InheritInverseConfiguration
	Membership fromData(MembershipData memberData);

}
