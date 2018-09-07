package core.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import core.dto.ProgramAvailmentData;
import core.model.program.ProgramAvailment;

@Mapper(uses = { MemberMapper.class, ProgramMapper.class })
public interface ProgramAvailmentMapper {
	
	ProgramAvailmentMapper INSTANCE = Mappers.getMapper(ProgramAvailmentMapper.class);

	@Mapping(target = "modifiedDate", source = "modifiedDate", dateFormat = "MM/dd/yyyy HH:mm")
	@Mapping(target = "startDate", source = "startDate", dateFormat = "MM/dd/yyyy")
	@Mapping(target = "endDate", source = "endDate", dateFormat = "MM/dd/yyyy")
	ProgramAvailmentData toData(ProgramAvailment programAvailment);
	
	@IterableMapping(dateFormat = "MM/dd/yyyy HH:mm")
	List<ProgramAvailmentData> toData(List<ProgramAvailment> programAvailments);
	
	@InheritInverseConfiguration
	ProgramAvailment fromData(ProgramAvailmentData programAvailmentData);
	
}
