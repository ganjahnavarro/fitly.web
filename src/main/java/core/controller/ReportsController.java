package core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import core.dto.report.PackagePurchaseSummaryData;
import core.dto.report.ProgramPurchaseSummaryData;
import core.mapper.PackageMapper;
import core.mapper.ProgramMapper;
import core.service.PackageAvailmentService;
import core.service.ProgramAvailmentService;

@CrossOrigin
@RestController
@RequestMapping("/report")
public class ReportsController {
	
	@Autowired private ProgramAvailmentService programAvailmentService;
	@Autowired private PackageAvailmentService packageAvailmentService;
	
	private ProgramMapper PROGRAM_MAPPER = ProgramMapper.INSTANCE;
	private PackageMapper PACKAGE_MAPPER = PackageMapper.INSTANCE;
	
	@RequestMapping(value = "/program/summary", method = RequestMethod.GET)
	public List<ProgramPurchaseSummaryData> getProgramPurchaseSummaries() {
		return PROGRAM_MAPPER.summariesToData(programAvailmentService.findProgramPurchaseSummaries());
	}
	
	@RequestMapping(value = "/package/summary", method = RequestMethod.GET)
	public List<PackagePurchaseSummaryData> getPackagePurchaseSummaries() {
		return PACKAGE_MAPPER.summariesToData(packageAvailmentService.findPackagePurchaseSummaries());
	}

}
