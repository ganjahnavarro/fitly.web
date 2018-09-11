package core.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.model.pkg.PackageAvailment;
import core.model.pkg.PackageAvailmentRepository;
import core.report.PackagePurchaseSummary;
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
	
	public PackageAvailment findByIdWithDetails(Long id) {
		return repository.findByIdWithDetails(id);
	}
	
	public List<PackageAvailment> findMemberPackageAvailments(Long memberId) {
		return repository.findMemberPackageAvailments(memberId);
	}
	
	public List<PackageAvailment> findAvailablePackageAvailments(Long memberId, Date date) {
		return repository.findAvailablePackageAvailments(memberId, date);
	}
	
	public List<PackagePurchaseSummary> findPackagePurchaseSummaries() {
		return repository.findPackagePurchaseSummaries();
	}

}
