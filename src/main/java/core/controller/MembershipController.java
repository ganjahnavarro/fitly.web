package core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import core.dto.MembershipData;
import core.mapper.MembershipMapper;
import core.model.member.Membership;
import core.service.MemberService;

@CrossOrigin
@RestController
@RequestMapping("/membership")
public class MembershipController {

	@Autowired private MemberService memberService;

	private MembershipMapper MAPPER = MembershipMapper.INSTANCE;

	@RequestMapping(value = "/member/{id}", method = RequestMethod.GET)
	public MembershipData get(@PathVariable Long id) {
		return MAPPER.toData(memberService.findMembershipByMemberId(id));
	}

	@RequestMapping(value = "/", method = RequestMethod.PATCH)
	public MembershipData update(@RequestBody MembershipData membershipData) {
		Membership membership = MAPPER.fromData(membershipData);
		return MAPPER.toData((Membership) memberService.update(membership));
	}

}
