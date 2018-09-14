package core.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import core.Utility;
import core.dto.report.PackagePurchaseSummaryData;
import core.dto.report.ProgramPurchaseSummaryData;
import core.dto.report.SalesReportData;
import core.mapper.ReportsMapper;
import core.report.SalesReport;
import core.service.MembershipService;
import core.service.PackageAvailmentService;
import core.service.ProgramAvailmentService;
import core.service.TimeEntryService;

@CrossOrigin
@RestController
@RequestMapping("/report")
public class ReportsController extends AbstractController {
	
	@Autowired private ProgramAvailmentService programAvailmentService;
	@Autowired private PackageAvailmentService packageAvailmentService;
	@Autowired private MembershipService membershipService;
	@Autowired private TimeEntryService timeEntryService;
	
	private ReportsMapper REPORTS_MAPPER = ReportsMapper.INSTANCE;
	
	@RequestMapping(value = "/program/summary", method = RequestMethod.GET)
	public List<ProgramPurchaseSummaryData> getProgramPurchaseSummaries() {
		return REPORTS_MAPPER.programPurchaseSummariesToData(programAvailmentService.findProgramPurchaseSummaries());
	}
	
	@RequestMapping(value = "/package/summary", method = RequestMethod.GET)
	public List<PackagePurchaseSummaryData> getPackagePurchaseSummaries() {
		return REPORTS_MAPPER.packagePurchaseSummariesToData(packageAvailmentService.findPackagePurchaseSummaries());
	}
	
	@RequestMapping(value = "/sales", method = RequestMethod.GET)
	public List<SalesReportData> getSalesReport(
			@RequestParam(value = "startDate", required = false) String startDateParam,
			@RequestParam(value = "endDate", required = false) String endDateParam) {
		Date startDate = Utility.parseDate(startDateParam);
		Date endDate = Utility.parseDate(endDateParam);
		
		List<SalesReport> salesReports = new ArrayList<>();
		salesReports.addAll(membershipService.findSalesReport(startDate, endDate));
		salesReports.addAll(programAvailmentService.findSalesReport(startDate, endDate));
		salesReports.addAll(packageAvailmentService.findSalesReport(startDate, endDate));
		salesReports.addAll(timeEntryService.findSalesReport(startDate, endDate));

		salesReports.sort(Comparator.comparing(SalesReport::getDate));
		return REPORTS_MAPPER.toSalesReportsData(salesReports);
	}
	
}
