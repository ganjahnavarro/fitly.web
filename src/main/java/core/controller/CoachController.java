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

import core.Validator;
import core.dto.CoachData;
import core.mapper.CoachMapper;
import core.model.coach.Coach;
import core.service.CoachService;

@CrossOrigin
@RestController
@RequestMapping("/coach")
public class CoachController {

	@Autowired private CoachService coachService;
	@Autowired private Validator validator;

	private CoachMapper MAPPER = CoachMapper.INSTANCE;

	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public List<CoachData> list(@RequestParam(value = "filter", required = false) String filter,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "pageOffset", required = false) Integer pageOffset,
			@RequestParam(value = "orderedBy", required = false) String orderedBy) {
		return MAPPER.toData(coachService.findFilteredItems(filter, pageSize, pageOffset, orderedBy));
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public CoachData create(@RequestBody CoachData coachData) {
		validateCoachData(coachData);
		Coach coach = MAPPER.fromData(coachData);
		return MAPPER.toData((Coach) coachService.save(coach));
	}

	@RequestMapping(value = "/", method = RequestMethod.PATCH)
	public CoachData update(@RequestBody CoachData coachData) {
		validateCoachData(coachData);
		Coach coach = MAPPER.fromData(coachData);
		return MAPPER.toData((Coach) coachService.update(coach));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		coachService.deleteRecordById(id);
	}
	
	private void validateCoachData(CoachData coachData) {
		validator.validateEmailAddress(coachData.getEmail());
		validator.validateDate(coachData.getBirthDate(), "Birth date format should be MM/dd/yyyy.");
	}

}
