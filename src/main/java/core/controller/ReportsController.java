package core.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import core.Utility;
import core.dto.report.CoachEnrolleesData;
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

	@Autowired
	private ProgramAvailmentService programAvailmentService;
	@Autowired
	private PackageAvailmentService packageAvailmentService;
	@Autowired
	private MembershipService membershipService;
	@Autowired
	private TimeEntryService timeEntryService;

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

	@RequestMapping(value = "/sales/download", method = RequestMethod.GET)
	public void downloadSalesReport(
			@RequestParam(value = "startDate", required = false) String startDateParam,
			@RequestParam(value = "endDate", required = false) String endDateParam,
			HttpServletResponse response) throws IOException {
		List<SalesReportData> salesReports = getSalesReport(startDateParam, endDateParam);

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
	
		int rowIndex = 0;
		createRow(sheet, rowIndex++, "Datse", "Name", "Type", "Amount");
	
		for (SalesReportData salesReport : salesReports) {
			createRow(sheet, rowIndex++, salesReport);
		}
		
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		workbook.write(response.getOutputStream());
		workbook.close();
		response.flushBuffer();
	}

	@RequestMapping(value = "/coach/enrollees", method = RequestMethod.GET)
	public List<CoachEnrolleesData> getCoachEnrollees(
			@RequestParam(value = "startDate", required = false) String startDateParam,
			@RequestParam(value = "endDate", required = false) String endDateParam) {
		Date startDate = Utility.parseDate(startDateParam);
		Date endDate = Utility.parseDate(endDateParam);

		return REPORTS_MAPPER.enrolleesToData(timeEntryService.findCoachEnrollees(startDate, endDate));
	}
	
	private void createRow(HSSFSheet sheet, int rowIndex, SalesReportData salesReport) {
		createRow(sheet, rowIndex, salesReport.getDate(), salesReport.getPerson(),
				salesReport.getDescription(), salesReport.getAmount().toString());
	}
	
	private void createRow(HSSFSheet sheet, int rowIndex, String... values) {
		HSSFRow rowhead = sheet.createRow(rowIndex);
		
		int columnIndex = 0;
		for (String value : values) {
			rowhead.createCell(columnIndex++).setCellValue(value);
		}
	}

}
