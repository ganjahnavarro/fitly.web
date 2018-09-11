package core.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import core.dto.CoachData;
import core.dto.report.CoachEnrolleesData;
import core.model.coach.Coach;
import core.model.coach.CoachEnrollees;

@Mapper
public interface CoachMapper {
	
	CoachMapper INSTANCE = Mappers.getMapper(CoachMapper.class);

	@Mapping(target = "modifiedDate", source = "modifiedDate", dateFormat = "MM/dd/yyyy HH:mm")
	@Mapping(target = "birthDate", source = "birthDate", dateFormat = "MM/dd/yyyy")
	CoachData toData(Coach coach);
	
	@IterableMapping(dateFormat = "MM/dd/yyyy HH:mm")
	List<CoachData> toData(List<Coach> coaches);
	
	@InheritInverseConfiguration
	Coach fromData(CoachData coachData);
	
	CoachEnrolleesData enrolleesToData(CoachEnrollees coachEnrollees);

	List<CoachEnrolleesData> enrolleesToData(List<CoachEnrollees> coachEnrollees);

}
