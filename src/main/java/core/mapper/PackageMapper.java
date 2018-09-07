package core.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import core.dto.PackageData;
import core.dto.report.PackagePurchaseSummaryData;
import core.model.pkg.Package;
import core.model.pkg.PackagePurchaseSummary;

@Mapper
public interface PackageMapper {
	
	PackageMapper INSTANCE = Mappers.getMapper(PackageMapper.class);

	@Mapping(target = "modifiedDate", source = "modifiedDate", dateFormat = "MM/dd/yyyy HH:mm")
	PackageData toData(Package pkg);
	
	@IterableMapping(dateFormat = "MM/dd/yyyy HH:mm")
	List<PackageData> toData(List<Package> packages);
	
	@InheritInverseConfiguration
	Package fromData(PackageData packageData);
	
	PackagePurchaseSummaryData summaryToData(PackagePurchaseSummary summary);

	List<PackagePurchaseSummaryData> summariesToData(List<PackagePurchaseSummary> summaries);

}
