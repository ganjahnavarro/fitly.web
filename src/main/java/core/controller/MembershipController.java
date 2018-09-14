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
import core.model.member.Member;
import core.model.member.Membership;
import core.service.AbstractService;
import core.service.MemberService;
import core.service.MembershipService;

@CrossOrigin
@RestController
@RequestMapping("/membership")
public class MembershipController extends AbstractController {

	@Autowired private MembershipService membershipService;
	@Autowired private MemberService memberService;

	private MembershipMapper MAPPER = MembershipMapper.INSTANCE;

	@RequestMapping(value = "/member/{id}", method = RequestMethod.GET)
	public MembershipData get(@PathVariable Long id) {
		return MAPPER.toData(membershipService.findMembershipByMemberId(id));
	}

	@RequestMapping(value = "/", method = RequestMethod.PATCH)
	public MembershipData update(@RequestBody MembershipData membershipData) {
		Membership membership = MAPPER.fromData(membershipData);
		
		if (membershipData.getAccessCardNo() != null) {
			Member member = memberService.findByAccessCardNo(membershipData.getAccessCardNo());
			
			if (member != null && member.getId() != membership.getMember().getId()) {
				throw new IllegalArgumentException("Access card is already registered to another member.");
			}
		}
		
		return MAPPER.toData((Membership) memberService.update(membership));
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		super.delete(id);
	}
	
	@Override
	protected AbstractService getService() {
		return membershipService;
	}

}
