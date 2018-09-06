package core.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import core.dto.PackageAvailmentData;
import core.mapper.PackageAvailmentMapper;
import core.model.member.Member;
import core.model.pkg.Package;
import core.model.pkg.PackageAvailment;
import core.service.AbstractService;
import core.service.MemberService;
import core.service.PackageAvailmentService;
import core.service.PackageService;

@CrossOrigin
@RestController
@RequestMapping("/package/availment")
public class PackageAvailmentController extends AbstractController {

	@Autowired private PackageAvailmentService packageAvailmentService;
	
	@Autowired private PackageService packageService;
	@Autowired private MemberService memberService;

	private PackageAvailmentMapper MAPPER = PackageAvailmentMapper.INSTANCE;
	
	@RequestMapping(value = "/{memberId}", method = RequestMethod.GET)
	public List<PackageAvailmentData> get(@PathVariable Long memberId) {
		return MAPPER.toData(packageAvailmentService.findMemberPackageAvailments(memberId));
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public PackageAvailmentData create(@RequestBody PackageAvailmentData packageAvailmentData) {
		PackageAvailment packageAvailment = MAPPER.fromData(packageAvailmentData);
		setDefaultValues(packageAvailment);

		return MAPPER.toData((PackageAvailment) packageAvailmentService.save(packageAvailment));
	}

	@RequestMapping(value = "/", method = RequestMethod.PATCH)
	public PackageAvailmentData update(@RequestBody PackageAvailmentData packageAvailmentData) {
		PackageAvailment packageAvailment = MAPPER.fromData(packageAvailmentData);
		setDefaultValues(packageAvailment);
		return MAPPER.toData((PackageAvailment) packageAvailmentService.update(packageAvailment));
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		super.delete(id);
	}
	
	private void setDefaultValues(PackageAvailment availment) {
		if (availment.getAvailedPackage() != null) {
			Package availedPackage = (Package) packageService.findById(availment.getAvailedPackage().getId());
			
			availment.setAvailedPackage(availedPackage);
			availment.setDuration(availedPackage.getDuration());
			availment.setDurationCount(availedPackage.getDurationCount());
			availment.setSessionsCount(availedPackage.getSessionsCount());
			availment.setSessionsRemaining(availment.getSessionsCount());
			availment.setPrice(availedPackage.getPrice());
			
			Date startDate = availment.getStartDate();
			Date endDate = availment.getDuration().computeEndDate(startDate, availment.getDurationCount());
			availment.setEndDate(endDate);
		}
		
		if (availment.getMember() != null) {
			Member member = (Member) memberService.findById(availment.getMember().getId());
			availment.setMember(member);
		}
	}
	
	@Override
	protected AbstractService getService() {
		return packageAvailmentService;
	}

}
