package com.niche.ng.service.mapper;

import com.niche.ng.domain.*;
import com.niche.ng.service.dto.PointOfSaleDetailsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PointOfSaleDetails and its DTO PointOfSaleDetailsDTO.
 */
@Mapper(componentModel = "spring", uses = {PickListValueMapper.class, NurseryStockMapper.class})
public interface PointOfSaleDetailsMapper extends EntityMapper<PointOfSaleDetailsDTO, PointOfSaleDetails> {

    @Mapping(source = "pickListVariety.id", target = "pickListVarietyId")
    @Mapping(source = "pickListVariety.pickListValue", target = "pickListVarietyPickListValue")
    @Mapping(source = "pickListCategory.id", target = "pickListCategoryId")
    @Mapping(source = "pickListCategory.pickListValue", target = "pickListCategoryPickListValue")
    @Mapping(source = "nurseryStock.id", target = "nurseryStockId")
    PointOfSaleDetailsDTO toDto(PointOfSaleDetails pointOfSaleDetails);

    @Mapping(source = "pickListVarietyId", target = "pickListVariety")
    @Mapping(source = "pickListCategoryId", target = "pickListCategory")
    @Mapping(source = "nurseryStockId", target = "nurseryStock")
    PointOfSaleDetails toEntity(PointOfSaleDetailsDTO pointOfSaleDetailsDTO);

    default PointOfSaleDetails fromId(Long id) {
        if (id == null) {
            return null;
        }
        PointOfSaleDetails pointOfSaleDetails = new PointOfSaleDetails();
        pointOfSaleDetails.setId(id);
        return pointOfSaleDetails;
    }
}
