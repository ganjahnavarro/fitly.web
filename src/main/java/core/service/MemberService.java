package core.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.FitlyConstants;
import core.enums.MemberType;
import core.model.Config;
import core.model.IRecord;
import core.model.member.Member;
import core.model.member.MemberRepository;
import core.model.member.Membership;
import core.repository.AbstractRepository;

@Service
@Transactional
public class MemberService extends AbstractService {
	
	@Autowired private MemberRepository repository;
	@Autowired private ConfigService configService;

	@SuppressWarnings("rawtypes")
	@Override
	public AbstractRepository getRepository() {
		return repository;
	}
	
	@Override
	public IRecord save(IRecord record) {
		Member member = (Member) super.save(record);
		
		if (member.getType().equals(MemberType.REGULAR)) {
			super.save(createMembership(member));
		}

		return member;
	}
	
	public List<Member> findFilteredItems(MemberType type, String filter, Integer pageSize, Integer pageOffset, String orderBy) {
		return repository.findFilteredItems(type, filter, pageSize, pageOffset, orderBy);
	}
	
	private Membership createMembership(Member member) {
		Calendar calendar = Calendar.getInstance();
		Config config = configService.findByName(FitlyConstants.CONFIG_NAME_MEMBERSHIP_AMOUNT);
		
		Membership membership = new Membership();
		membership.setMember(member);
		membership.setAmount(new BigDecimal(config.getValue()));
		
		membership.setStartDate(calendar.getTime());
		
		calendar.add(Calendar.YEAR, 1);
		membership.setEndDate(calendar.getTime());
		
		return membership;
	}
	
	public Member findByAccessCardNo(String accessCardNo) {
		return repository.findByAccessCardNo(accessCardNo);
	}
	
	public Membership findMembershipByMemberId(Long memberId) {
		return repository.findMembershipByMemberId(memberId);
	}
	
	public Long findCount(MemberType type) {
		return repository.findCount(type);
	}

}
