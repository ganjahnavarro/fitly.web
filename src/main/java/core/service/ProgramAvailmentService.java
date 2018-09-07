package core.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.model.program.ProgramAvailment;
import core.model.program.ProgramAvailmentRepository;
import core.model.program.ProgramPurchaseSummary;
import core.repository.AbstractRepository;

@Service
@Transactional
public class ProgramAvailmentService extends AbstractService {
	
	@Autowired private ProgramAvailmentRepository repository;

	@SuppressWarnings("rawtypes")
	@Override
	public AbstractRepository getRepository() {
		return repository;
	}
	
	public List<ProgramAvailment> findMemberProgramAvailments(Long memberId) {
		return repository.findMemberProgramAvailments(memberId);
	}
	
	public List<ProgramAvailment> findAvailableProgramAvailments(Long memberId, Date date) {
		return repository.findAvailableProgramAvailments(memberId, date);
	}
	
	public List<ProgramPurchaseSummary> findProgramPurchaseSummaries() {
		return repository.findProgramPurchaseSummaries();
	}
	
}
