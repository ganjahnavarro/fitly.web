package core.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import core.dto.PromoData;
import core.model.promo.Promo;

@Mapper
public interface PromoMapper {
	
	PromoMapper INSTANCE = Mappers.getMapper(PromoMapper.class);

	@Mapping(target = "modifiedDate", source = "modifiedDate", dateFormat = "MM/dd/yyyy HH:mm")
	PromoData toData(Promo promo);
	
	@IterableMapping(dateFormat = "MM/dd/yyyy HH:mm")
	List<PromoData> toData(List<Promo> promos);
	
	@InheritInverseConfiguration
	Promo fromData(PromoData promoData);

}
