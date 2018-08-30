package core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import core.dto.ProgramAvailmentData;
import core.mapper.ProgramAvailmentMapper;
import core.model.member.Member;
import core.model.program.Program;
import core.model.program.ProgramAvailment;
import core.service.MemberService;
import core.service.ProgramService;

@CrossOrigin
@RestController
@RequestMapping("/program/availment")
public class ProgramAvailmentController {

	@Autowired private ProgramService programService;
	@Autowired private MemberService memberService;

	private ProgramAvailmentMapper MAPPER = ProgramAvailmentMapper.INSTANCE;
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ProgramAvailmentData create(@RequestBody ProgramAvailmentData programAvailmentData) {
		ProgramAvailment programAvailment = MAPPER.fromData(programAvailmentData);
		setDefaultValues(programAvailment);

		return MAPPER.toData((ProgramAvailment) programService.save(programAvailment));
	}

	@RequestMapping(value = "/", method = RequestMethod.PATCH)
	public ProgramAvailmentData update(@RequestBody ProgramAvailmentData programAvailmentData) {
		ProgramAvailment programAvailment = MAPPER.fromData(programAvailmentData);
		setDefaultValues(programAvailment);
		return MAPPER.toData((ProgramAvailment) programService.update(programAvailment));
	}
	
	private void setDefaultValues(ProgramAvailment availment) {
		if (availment.getAvailedProgram() != null) {
			Program availedProgram = (Program) programService.findById(availment.getAvailedProgram().getId());
			availment.setPrice(availedProgram.getPrice(availment.getType()));
			availment.setAvailedProgram(availedProgram);
		}
		
		if (availment.getMember() != null) {
			Member member = (Member) memberService.findById(availment.getMember().getId());
			availment.setMember(member);
		}
	}

}
