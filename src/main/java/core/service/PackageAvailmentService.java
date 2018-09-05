package core.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.model.pkg.PackageAvailment;
import core.model.pkg.PackageAvailmentRepository;
import core.repository.AbstractRepository;

@Service
@Transactional
public class PackageAvailmentService extends AbstractService {
	
	@Autowired private PackageAvailmentRepository repository;

	@SuppressWarnings("rawtypes")
	@Override
	public AbstractRepository getRepository() {
		return repository;
	}
	
	public List<PackageAvailment> findMemberPackageAvailments(Long memberId) {
		return repository.findMemberPackageAvailments(memberId);
	}

}
