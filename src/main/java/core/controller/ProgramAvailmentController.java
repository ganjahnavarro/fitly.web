package core.controller;

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
import core.dto.ProgramAvailmentData;
import core.mapper.ProgramAvailmentMapper;
import core.model.member.Member;
import core.model.program.Program;
import core.model.program.ProgramAvailment;
import core.service.AbstractService;
import core.service.MemberService;
import core.service.ProgramAvailmentService;
import core.service.ProgramService;

@CrossOrigin
@RestController
@RequestMapping("/program/availment")
public class ProgramAvailmentController extends AbstractController {

	@Autowired private ProgramAvailmentService programAvailmentService;
	@Autowired private ProgramService programService;
	@Autowired private MemberService memberService;

	private ProgramAvailmentMapper MAPPER = ProgramAvailmentMapper.INSTANCE;
	
	@RequestMapping(value = "/{memberId}/all", method = RequestMethod.GET)
	public List<ProgramAvailmentData> getAllForMember(@PathVariable Long memberId) {
		return MAPPER.toData(programAvailmentService.findMemberProgramAvailments(memberId));
	}
	
	@RequestMapping(value = "/{memberId}/available", method = RequestMethod.GET)
	public List<ProgramAvailmentData> getAvailableForMember(@PathVariable Long memberId,
			@RequestParam(value = "date", required = false) String dateParam) {
		Date today = Utility.getCurrentDateWithoutTime();
		Date date = dateParam != null ? Utility.parseDate(dateParam) : today;
		return MAPPER.toData(programAvailmentService.findAvailableProgramAvailments(memberId, date));
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ProgramAvailmentData create(@RequestBody ProgramAvailmentData programAvailmentData) {
		ProgramAvailment programAvailment = MAPPER.fromData(programAvailmentData);
		setDefaultValues(programAvailment);

		return MAPPER.toData((ProgramAvailment) programAvailmentService.save(programAvailment));
	}

	@RequestMapping(value = "/", method = RequestMethod.PATCH)
	public ProgramAvailmentData update(@RequestBody ProgramAvailmentData programAvailmentData) {
		ProgramAvailment programAvailment = MAPPER.fromData(programAvailmentData);
		setDefaultValues(programAvailment);
		return MAPPER.toData((ProgramAvailment) programAvailmentService.update(programAvailment));
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		super.delete(id);
	}
	
	private void setDefaultValues(ProgramAvailment availment) {
		if (availment.getAvailedProgram() != null) {
			Program availedProgram = (Program) programService.findById(availment.getAvailedProgram().getId());
			availment.setAvailedProgram(availedProgram);

			Date startDate = availment.getStartDate();
			availment.setEndDate(availment.getType().getEndDate(startDate));
			availment.setPrice(availment.getType().getProgramPrice(availedProgram));
		}
		
		if (availment.getMember() != null) {
			Member member = (Member) memberService.findById(availment.getMember().getId());
			availment.setMember(member);
		}
	}
	
	@Override
	protected AbstractService getService() {
		return programAvailmentService;
	}

}
