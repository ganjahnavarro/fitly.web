package core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import core.dto.TimeEntryData;
import core.mapper.TimeEntryMapper;
import core.model.coach.Coach;
import core.model.pkg.PackageAvailment;
import core.model.program.ProgramAvailment;
import core.model.timeentry.TimeEntry;
import core.service.CoachService;
import core.service.PackageAvailmentService;
import core.service.ProgramAvailmentService;
import core.service.TimeEntryService;

@CrossOrigin
@RestController
@RequestMapping("/timeentry")
public class TimeEntryController {

	@Autowired private TimeEntryService timeEntryService;
	
	@Autowired private CoachService coachService;
	@Autowired private ProgramAvailmentService programAvailmentService;
	@Autowired private PackageAvailmentService packageAvailmentService;

	private TimeEntryMapper MAPPER = TimeEntryMapper.INSTANCE;

	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public List<TimeEntryData> list(
			@RequestParam(value = "memberId", required = false) Long memberId,
			@RequestParam(value = "coachId", required = false) Long coachId,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "pageOffset", required = false) Integer pageOffset,
			@RequestParam(value = "orderedBy", required = false) String orderedBy) {
		return MAPPER.toData(timeEntryService.findFilteredItems(memberId, coachId,
				pageSize, pageOffset, orderedBy));
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public TimeEntryData create(@RequestBody TimeEntryData timeEntryData) {
		TimeEntry timeEntry = MAPPER.fromData(timeEntryData);
		return MAPPER.toData((TimeEntry) timeEntryService.saveUsingAccessCard(timeEntry));
	}
	
	@RequestMapping(value = "/manually", method = RequestMethod.POST)
	public TimeEntryData createManually(@RequestBody TimeEntryData timeEntryData) {
		TimeEntry timeEntry = MAPPER.fromData(timeEntryData);
		setDefaultValues(timeEntry);
		
		return MAPPER.toData((TimeEntry) timeEntryService.save(timeEntry));
	}
	
	@RequestMapping(value = "/", method = RequestMethod.PATCH)
	public TimeEntryData update(@RequestBody TimeEntryData timeEntryData) {
		TimeEntry timeEntry = MAPPER.fromData(timeEntryData);
		setDefaultValues(timeEntry);
		
		return MAPPER.toData((TimeEntry) timeEntryService.update(timeEntry));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		timeEntryService.deleteRecordById(id);
	}
	
	private void setDefaultValues(TimeEntry timeEntry) {
		if (timeEntry.getCoachAssigned() != null) {
			Coach coachAssigned = (Coach) coachService.findById(timeEntry.getCoachAssigned().getId());
			timeEntry.setCoachAssigned(coachAssigned);
		}
		
		if (timeEntry.getProgramAvailment() != null) {
			ProgramAvailment programAvailment = (ProgramAvailment) programAvailmentService
					.findById(timeEntry.getProgramAvailment().getId());
			timeEntry.setProgramAvailment(programAvailment);
		}
		
		if (timeEntry.getPackageAvailment() != null) {
			PackageAvailment packageAvailment = (PackageAvailment) packageAvailmentService
					.findByIdWithDetails(timeEntry.getPackageAvailment().getId());
			timeEntry.setPackageAvailment(packageAvailment);
		}
	}

}
