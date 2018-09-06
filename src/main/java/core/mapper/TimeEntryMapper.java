package core.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import core.dto.TimeEntryData;
import core.model.timeentry.TimeEntry;

@Mapper(uses = { MemberMapper.class, CoachMapper.class, ProgramAvailmentMapper.class, PackageAvailmentMapper.class })
public interface TimeEntryMapper {
	
	TimeEntryMapper INSTANCE = Mappers.getMapper(TimeEntryMapper.class);

	@Mapping(target = "modifiedDate", source = "modifiedDate", dateFormat = "MM/dd/yyyy HH:mm")
	@Mapping(target = "date", source = "date", dateFormat = "MM/dd/yyyy")
	TimeEntryData toData(TimeEntry timeEntry);
	
	@IterableMapping(dateFormat = "MM/dd/yyyy HH:mm")
	List<TimeEntryData> toData(List<TimeEntry> timeEntrys);
	
	@InheritInverseConfiguration
	TimeEntry fromData(TimeEntryData timeEntryData);

}
