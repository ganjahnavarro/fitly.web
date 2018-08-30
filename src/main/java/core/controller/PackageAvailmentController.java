package core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import core.dto.PackageAvailmentData;
import core.mapper.PackageAvailmentMapper;
import core.model.member.Member;
import core.model.pkg.Package;
import core.model.pkg.PackageAvailment;
import core.service.MemberService;
import core.service.PackageService;

@CrossOrigin
@RestController
@RequestMapping("/package/availment")
public class PackageAvailmentController {

	@Autowired private PackageService packageService;
	@Autowired private MemberService memberService;

	private PackageAvailmentMapper MAPPER = PackageAvailmentMapper.INSTANCE;
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public PackageAvailmentData create(@RequestBody PackageAvailmentData packageAvailmentData) {
		PackageAvailment packageAvailment = MAPPER.fromData(packageAvailmentData);
		setDefaultValues(packageAvailment);

		return MAPPER.toData((PackageAvailment) packageService.save(packageAvailment));
	}

	@RequestMapping(value = "/", method = RequestMethod.PATCH)
	public PackageAvailmentData update(@RequestBody PackageAvailmentData packageAvailmentData) {
		PackageAvailment packageAvailment = MAPPER.fromData(packageAvailmentData);
		setDefaultValues(packageAvailment);
		return MAPPER.toData((PackageAvailment) packageService.update(packageAvailment));
	}
	
	private void setDefaultValues(PackageAvailment availment) {
		if (availment.getAvailedPackage() != null) {
			Package availedPackage = (Package) packageService.findById(availment.getAvailedPackage().getId());
			
			availment.setAvailedPackage(availedPackage);
			availment.setDuration(availedPackage.getDuration());
			availment.setDurationCount(availedPackage.getDurationCount());
			availment.setSessionsCount(availedPackage.getSessionsCount());
			availment.setPrice(availedPackage.getPrice());
		}
		
		if (availment.getMember() != null) {
			Member member = (Member) memberService.findById(availment.getMember().getId());
			availment.setMember(member);
		}
	}

}
