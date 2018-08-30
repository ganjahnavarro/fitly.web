package core.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import core.dto.PackageAvailmentData;
import core.model.pkg.PackageAvailment;

@Mapper(uses = { MemberMapper.class, PackageMapper.class })
public interface PackageAvailmentMapper {
	
	PackageAvailmentMapper INSTANCE = Mappers.getMapper(PackageAvailmentMapper.class);

	@Mapping(target = "modifiedDate", source = "modifiedDate", dateFormat = "MM/dd/yyyy HH:mm")
	@Mapping(target = "date", source = "date", dateFormat = "MM/dd/yyyy HH:mm")
	PackageAvailmentData toData(PackageAvailment packageAvailment);
	
	@IterableMapping(dateFormat = "MM/dd/yyyy HH:mm")
	List<PackageAvailmentData> toData(List<PackageAvailment> packageAvailments);
	
	@InheritInverseConfiguration
	PackageAvailment fromData(PackageAvailmentData packageAvailmentData);

}
