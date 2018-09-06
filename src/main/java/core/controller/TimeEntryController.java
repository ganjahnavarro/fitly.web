package core.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import core.Utility;
import core.dto.TimeEntryData;
import core.mapper.TimeEntryMapper;
import core.model.member.Member;
import core.model.pkg.PackageAvailment;
import core.model.program.ProgramAvailment;
import core.model.timeentry.TimeEntry;
import core.service.MemberService;
import core.service.PackageAvailmentService;
import core.service.ProgramAvailmentService;
import core.service.TimeEntryService;

@CrossOrigin
@RestController
@RequestMapping("/timeentry")
public class TimeEntryController {

	@Autowired private TimeEntryService timeEntryService;

	@Autowired private MemberService memberService;
	@Autowired private ProgramAvailmentService programAvailmentService;
	@Autowired private PackageAvailmentService packageAvailmentService;

	private TimeEntryMapper MAPPER = TimeEntryMapper.INSTANCE;

	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public List<TimeEntryData> list(@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "pageOffset", required = false) Integer pageOffset,
			@RequestParam(value = "orderedBy", required = false) String orderedBy) {
		return MAPPER.toData(timeEntryService.findFilteredItems(pageSize, pageOffset, orderedBy));
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public TimeEntryData create(@RequestBody TimeEntryData timeEntryData) {
		TimeEntry timeEntry = MAPPER.fromData(timeEntryData);

		timeEntry.setDate(new Date());

		Member member = memberService.findByAccessCardNo(timeEntryData.getAccessCardNoUsed());
		timeEntry.setMember(member);
		timeEntry.setCoachAssigned(member.getDefaultCoach());

		Availments availments = getAvailmentsToUse(member);
		ProgramAvailment programAvailment = availments.programAvailment;
		PackageAvailment packageAvailment = availments.packageAvailment;

		timeEntry.setProgramAvailment(programAvailment);
		timeEntry.setPackageAvailment(packageAvailment);
		
		BigDecimal commission = programAvailment != null ? programAvailment.getAvailedProgram().getCommission()
				: packageAvailment.getAvailedPackage().getCommission();
		timeEntry.setCommission(commission);

		return MAPPER.toData((TimeEntry) timeEntryService.save(timeEntry));
	}
	
	private Availments getAvailmentsToUse(Member member) {
		Availments availments = new Availments();
		Date today = Utility.getCurrentDateWithoutTime();

		List<ProgramAvailment> availableProgramAvailments = programAvailmentService
				.findAvailableProgramAvailments(member.getId(), today);
		List<PackageAvailment> availablePackageAvailments = packageAvailmentService
				.findAvailablePackageAvailments(member.getId(), today);
		
		ProgramAvailment programAvCandidate = availableProgramAvailments.get(0);
		PackageAvailment packageAvCandidate = availablePackageAvailments.get(0);

		if (programAvCandidate == null) {
			availments.packageAvailment = packageAvCandidate;
		} else if (packageAvCandidate == null) {
			availments.programAvailment = programAvCandidate;
		} else {
			boolean packageBeforeProgram = packageAvCandidate.getStartDate()
					.before(programAvCandidate.getStartDate());
			availments.programAvailment = !packageBeforeProgram ? programAvCandidate : null;
			availments.packageAvailment = packageBeforeProgram ? packageAvCandidate : null;
		}

		return availments;
	}

	@RequestMapping(value = "/", method = RequestMethod.PATCH)
	public TimeEntryData update(@RequestBody TimeEntryData timeEntryData) {
		TimeEntry timeEntry = MAPPER.fromData(timeEntryData);
		return MAPPER.toData((TimeEntry) timeEntryService.update(timeEntry));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		timeEntryService.deleteRecordById(id);
	}

	private class Availments {

		ProgramAvailment programAvailment;
		PackageAvailment packageAvailment;

	}

}
