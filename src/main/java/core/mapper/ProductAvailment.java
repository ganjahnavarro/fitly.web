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
public interface ProductAvailment {

	ProductAvailment INSTANCE = Mappers.getMapper(ProductAvailment.class);

	@Mapping(target = "modifiedDate", source = "modifiedDate", dateFormat = "MM/dd/yyyy HH:mm")
	@Mapping(target = "date", source = "date", dateFormat = "MM/dd/yyyy HH:mm")
	ProgramAvailmentData toData(ProgramAvailment programAvailment);

	@IterableMapping(dateFormat = "MM/dd/yyyy HH:mm")
	List<ProgramAvailmentData> toData(List<ProgramAvailment> programAvailments);

	@InheritInverseConfiguration
	ProgramAvailment fromData(ProgramAvailmentData programAvailmentData);

}
