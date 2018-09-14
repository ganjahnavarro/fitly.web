package core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import core.model.pkg.Package;
import core.dto.PackageData;
import core.enums.Duration;
import core.mapper.PackageMapper;
import core.service.AbstractService;
import core.service.PackageService;

@CrossOrigin
@RestController
@RequestMapping("/package")
public class PackageController extends AbstractController {

	@Autowired private PackageService packageService;

	private PackageMapper MAPPER = PackageMapper.INSTANCE;

	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public List<PackageData> list(@RequestParam(value = "filter", required = false) String filter,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "pageOffset", required = false) Integer pageOffset,
			@RequestParam(value = "orderedBy", required = false) String orderedBy) {
		return MAPPER.toData(packageService.findFilteredItems(filter, pageSize, pageOffset, orderedBy));
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public PackageData create(@RequestBody PackageData packageData) {
		setDefaultValues(packageData);

		Package pkg = MAPPER.fromData(packageData);
		return MAPPER.toData((Package) packageService.save(pkg));
	}

	@RequestMapping(value = "/", method = RequestMethod.PATCH)
	public PackageData update(@RequestBody PackageData packageData) {
		setDefaultValues(packageData);

		Package pkg = MAPPER.fromData(packageData);
		return MAPPER.toData((Package) packageService.update(pkg));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		packageService.deleteRecordById(id);
	}
	
	private void setDefaultValues(PackageData packageData) {
		if (packageData.getDuration() == null) {
			packageData.setDuration(Duration.ENDLESS);
		}

		// If endless duration set any non-null value to duration count for model validation
		if (Duration.ENDLESS.equals(packageData.getDuration())) {
			packageData.setDurationCount(-1);
		}
	}
	
	@Override
	protected AbstractService getService() {
		return packageService;
	}

}
