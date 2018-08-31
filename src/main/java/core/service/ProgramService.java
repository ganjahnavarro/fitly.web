package core.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.model.program.Program;
import core.model.program.ProgramAvailment;
import core.model.program.ProgramRepository;
import core.repository.AbstractRepository;

@Service
@Transactional
public class ProgramService extends AbstractService {
	
	@Autowired private ProgramRepository repository;

	@SuppressWarnings("rawtypes")
	@Override
	public AbstractRepository getRepository() {
		return repository;
	}
	
	public List<Program> findFilteredItems(String orderBy, Integer pageSize, Integer pageOffset, String filter) {
		return repository.findFilteredItems(orderBy, pageSize, pageOffset, filter);
	}
	
	public List<ProgramAvailment> findMemberProgramAvailments(Long memberId) {
		return repository.findMemberProgramAvailments(memberId);
	}

}
