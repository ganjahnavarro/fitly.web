package core.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import core.dto.report.PackagePurchaseSummaryData;
import core.dto.report.ProgramPurchaseSummaryData;
import core.dto.report.SalesReportData;
import core.report.PackagePurchaseSummary;
import core.report.ProgramPurchaseSummary;
import core.report.SalesReport;

@Mapper(uses = { ProgramMapper.class, PackageMapper.class, PersonMapper.class })
public interface ReportsMapper {

	ReportsMapper INSTANCE = Mappers.getMapper(ReportsMapper.class);

	ProgramPurchaseSummaryData programPurchaseSummaryToData(ProgramPurchaseSummary summary);
	List<ProgramPurchaseSummaryData> programPurchaseSummariesToData(List<ProgramPurchaseSummary> summaries);
	
	PackagePurchaseSummaryData packagePurchaseSummaryToData(PackagePurchaseSummary summary);
	List<PackagePurchaseSummaryData> packagePurchaseSummariesToData(List<PackagePurchaseSummary> summaries);
	
	SalesReportData toSalesReportData(SalesReport salesReport);
	List<SalesReportData> toSalesReportsData(List<SalesReport> salesReports);
	
}
