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

import core.dto.ProgramData;
import core.mapper.ProgramMapper;
import core.model.program.Program;
import core.service.ProgramService;

@CrossOrigin
@RestController
@RequestMapping("/program")
public class ProgramController {

	@Autowired private ProgramService programService;

	private ProgramMapper MAPPER = ProgramMapper.INSTANCE;

	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public List<ProgramData> list(@RequestParam(value = "filter", required = false) String filter,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "pageOffset", required = false) Integer pageOffset,
			@RequestParam(value = "orderedBy", required = false) String orderedBy) {
		return MAPPER.toData(programService.findFilteredItems(filter, pageSize, pageOffset, orderedBy));
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ProgramData create(@RequestBody ProgramData programData) {
		Program program = MAPPER.fromData(programData);
		return MAPPER.toData((Program) programService.save(program));
	}

	@RequestMapping(value = "/", method = RequestMethod.PATCH)
	public ProgramData update(@RequestBody ProgramData programData) {
		Program program = MAPPER.fromData(programData);
		return MAPPER.toData((Program) programService.update(program));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		programService.deleteRecordById(id);
	}

}
