package com.niche.ng.service.mapper;

import com.niche.ng.domain.*;
import com.niche.ng.service.dto.QuantityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Quantity and its DTO QuantityDTO.
 */
@Mapper(componentModel = "spring", uses = {PickListValueMapper.class})
public interface QuantityMapper extends EntityMapper<QuantityDTO, Quantity> {

    @Mapping(source = "pickListVariety.id", target = "pickListVarietyId")
    @Mapping(source = "pickListVariety.pickListValue", target = "pickListVarietyPickListValue")
    @Mapping(source = "pickListCategory.pickListValue", target = "pickListCategoryPickListValue")
    @Mapping(source = "pickListCategory.id", target = "pickListCategoryId")
    QuantityDTO toDto(Quantity quantity);

    @Mapping(source = "pickListVarietyId", target = "pickListVariety")
    @Mapping(source = "pickListCategoryId", target = "pickListCategory")
    Quantity toEntity(QuantityDTO quantityDTO);

    default Quantity fromId(Long id) {
        if (id == null) {
            return null;
        }
        Quantity quantity = new Quantity();
        quantity.setId(id);
        return quantity;
    }
}
