package core.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import core.dto.report.CoachEnrolleesData;
import core.dto.report.PackagePurchaseSummaryData;
import core.dto.report.ProgramPurchaseSummaryData;
import core.dto.report.SalesReportData;
import core.model.coach.CoachEnrollees;
import core.report.PackagePurchaseSummary;
import core.report.ProgramPurchaseSummary;
import core.report.SalesReport;

@Mapper(uses = { ProgramMapper.class, PackageMapper.class, CoachMapper.class })
public interface ReportsMapper {

	ReportsMapper INSTANCE = Mappers.getMapper(ReportsMapper.class);

	ProgramPurchaseSummaryData programPurchaseSummaryToData(ProgramPurchaseSummary summary);
	List<ProgramPurchaseSummaryData> programPurchaseSummariesToData(List<ProgramPurchaseSummary> summaries);
	
	PackagePurchaseSummaryData packagePurchaseSummaryToData(PackagePurchaseSummary summary);
	List<PackagePurchaseSummaryData> packagePurchaseSummariesToData(List<PackagePurchaseSummary> summaries);
	
	SalesReportData toSalesReportData(SalesReport salesReport);

	@IterableMapping(dateFormat = "MM/dd/yyyy HH:mm")
	List<SalesReportData> toSalesReportsData(List<SalesReport> salesReports);
	
	CoachEnrolleesData enrolleesToData(CoachEnrollees coachEnrollees);
	List<CoachEnrolleesData> enrolleesToData(List<CoachEnrollees> coachEnrollees);
	
}
