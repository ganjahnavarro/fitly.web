package core.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.model.timeentry.TimeEntry;
import core.model.timeentry.TimeEntryRepository;
import core.repository.AbstractRepository;

@Service
@Transactional
public class TimeEntryService extends AbstractService {
	
	@Autowired private TimeEntryRepository repository;

	@SuppressWarnings("rawtypes")
	@Override
	public AbstractRepository getRepository() {
		return repository;
	}
	
	public List<TimeEntry> findFilteredItems(Integer pageSize, Integer pageOffset, String orderBy) {
		return repository.findFilteredItems(pageSize, pageOffset, orderBy);
	}

}
