package com.gnjb.fitly.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gnjb.fitly.model.product.Program;
import com.gnjb.fitly.model.product.ProgramRepository;

import core.repository.AbstractRepository;
import core.service.AbstractService;

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

}
