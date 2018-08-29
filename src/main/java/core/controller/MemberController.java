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
import core.dto.MemberData;
import core.mapper.MemberMapper;
import core.model.member.Member;
import core.service.MemberService;

@CrossOrigin
@RestController
@RequestMapping("/member")
public class MemberController {

	@Autowired private MemberService memberService;
	@Autowired private Validator validator;

	private MemberMapper MAPPER = MemberMapper.INSTANCE;

	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public List<MemberData> list(@RequestParam(value = "filter", required = false) String filter,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "pageOffset", required = false) Integer pageOffset,
			@RequestParam(value = "orderedBy", required = false) String orderedBy) {
		return MAPPER.toData(memberService.findFilteredItems(filter, pageSize, pageOffset, orderedBy));
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public MemberData create(@RequestBody MemberData memberData) {
		validate(memberData);

		Member member = MAPPER.fromData(memberData);
		return MAPPER.toData((Member) memberService.save(member));
	}

	@RequestMapping(value = "/", method = RequestMethod.PATCH)
	public MemberData update(@RequestBody MemberData memberData) {
		validate(memberData);

		Member member = MAPPER.fromData(memberData);
		return MAPPER.toData((Member) memberService.update(member));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		memberService.deleteRecordById(id);
	}
	
	private void validate(MemberData memberData) {
		Runnable validateEmail = () -> validator.validateEmailAddress(memberData.getEmail());
		Runnable validateBirthDate = () -> validator.validateDate(memberData.getBirthDate(), "Invalid birth date format.");

		validator.aggregate(validateEmail, validateBirthDate);
	}

}
