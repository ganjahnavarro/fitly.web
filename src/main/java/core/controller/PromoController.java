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

import core.dto.PromoData;
import core.mapper.PromoMapper;
import core.model.promo.Promo;
import core.service.AbstractService;
import core.service.PromoService;

@CrossOrigin
@RestController
@RequestMapping("/promo")
public class PromoController extends AbstractController {

	@Autowired private PromoService promoService;

	private PromoMapper MAPPER = PromoMapper.INSTANCE;

	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public List<PromoData> list(@RequestParam(value = "filter", required = false) String filter,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "pageOffset", required = false) Integer pageOffset,
			@RequestParam(value = "orderedBy", required = false) String orderedBy) {
		return MAPPER.toData(promoService.findFilteredItems(filter, pageSize, pageOffset, orderedBy));
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public PromoData create(@RequestBody PromoData promoData) {
		Promo promo = MAPPER.fromData(promoData);
		return MAPPER.toData((Promo) promoService.save(promo));
	}

	@RequestMapping(value = "/", method = RequestMethod.PATCH)
	public PromoData update(@RequestBody PromoData promoData) {
		Promo promo = MAPPER.fromData(promoData);
		return MAPPER.toData((Promo) promoService.update(promo));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		super.delete(id);
	}
	
	@Override
	protected AbstractService getService() {
		return promoService;
	}
	
}
