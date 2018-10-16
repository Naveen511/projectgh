/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/25
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs QuantityMapper
 *
 *******************************************************************************/
package com.niche.ng.service.mapper;

import com.niche.ng.domain.*;
import com.niche.ng.service.dto.QuantityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Quantity and its DTO QuantityDTO.
 * Mapping the parent and child table to fetch the field value.
 * Converting the entity object into data transfer object(DTO).
 */
@Mapper(componentModel = "spring", uses = {PickListValueMapper.class})
public interface QuantityMapper extends EntityMapper<QuantityDTO, Quantity> {

    @Mapping(source = "pickListVariety.id", target = "pickListVarietyId")
    @Mapping(source = "pickListVariety.pickListValue", target = "pickListVarietyPickListValue")
    @Mapping(source = "pickListCategory.id", target = "pickListCategoryId")
    @Mapping(source = "pickListCategory.pickListValue", target = "pickListCategoryPickListValue")
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
