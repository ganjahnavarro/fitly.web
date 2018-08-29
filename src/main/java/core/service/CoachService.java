package core.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.model.coach.Coach;
import core.model.coach.CoachRepository;
import core.repository.AbstractRepository;

@Service
@Transactional
public class CoachService extends AbstractService {
	
	@Autowired private CoachRepository repository;

	@SuppressWarnings("rawtypes")
	@Override
	public AbstractRepository getRepository() {
		return repository;
	}
	
	public List<Coach> findFilteredItems(String orderBy, Integer pageSize, Integer pageOffset, String filter) {
		return repository.findFilteredItems(orderBy, pageSize, pageOffset, filter);
	}

}
