package core.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import core.dto.ProgramData;
import core.dto.report.ProgramPurchaseSummaryData;
import core.model.program.Program;
import core.model.program.ProgramPurchaseSummary;

@Mapper
public interface ProgramMapper {
	
	ProgramMapper INSTANCE = Mappers.getMapper(ProgramMapper.class);

	@Mapping(target = "modifiedDate", source = "modifiedDate", dateFormat = "MM/dd/yyyy HH:mm")
	ProgramData toData(Program program);
	
	@IterableMapping(dateFormat = "MM/dd/yyyy HH:mm")
	List<ProgramData> toData(List<Program> programs);
	
	@InheritInverseConfiguration
	Program fromData(ProgramData programData);
	
	ProgramPurchaseSummaryData summaryToData(ProgramPurchaseSummary summary);

	List<ProgramPurchaseSummaryData> summariesToData(List<ProgramPurchaseSummary> summaries);
	
}
