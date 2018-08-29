package core.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.Constants;
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
		super.save(createMembership(member));
		return member;
	}
	
	public List<Member> findFilteredItems(String orderBy, Integer pageSize, Integer pageOffset, String filter) {
		return repository.findFilteredItems(orderBy, pageSize, pageOffset, filter);
	}
	
	private Membership createMembership(Member member) {
		Calendar calendar = Calendar.getInstance();
		Config config = configService.findByName(Constants.CONFIG_NAME_MEMBERSHIP_AMOUNT);
		
		Membership membership = new Membership();
		membership.setMember(member);
		membership.setAmount(new BigDecimal(config.getValue()));
		
		membership.setStartDate(calendar.getTime());
		
		calendar.add(Calendar.YEAR, 1);
		membership.setEndDate(calendar.getTime());
		
		return membership;
	}
	
	public Membership findMembershipByMemberId(Long memberId) {
		return repository.findMembershipByMemberId(memberId);
	}

}
