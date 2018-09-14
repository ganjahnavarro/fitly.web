package core.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.model.member.Membership;
import core.model.member.MembershipRepository;
import core.report.SalesReport;
import core.repository.AbstractRepository;

@Service
@Transactional
public class MembershipService extends AbstractService {
	
	@Autowired private MembershipRepository repository;

	@SuppressWarnings("rawtypes")
	@Override
	public AbstractRepository getRepository() {
		return repository;
	}
	
	public Membership findMembershipByMemberId(Long memberId) {
		return repository.findMembershipByMemberId(memberId);
	}
	
	public List<SalesReport> findSalesReport(Date startDate, Date endDate) {
		return repository.findSalesReport(startDate, endDate);
	}

}
