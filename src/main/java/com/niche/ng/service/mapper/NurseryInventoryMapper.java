/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/25
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryInventoryMapper
 *
 *******************************************************************************/
package com.niche.ng.service.mapper;

import com.niche.ng.domain.*;
import com.niche.ng.service.dto.NurseryInventoryDTO;

import org.mapstruct.*;
import java.util.List;
/**
 * Mapper for the entity NurseryInventory and its DTO NurseryInventoryDTO.
 */
@Mapper(componentModel = "spring", uses = {NurseryMapper.class, PickListValueMapper.class})
public interface NurseryInventoryMapper extends EntityMapper<NurseryInventoryDTO, NurseryInventory> {

    @Mapping(source = "nurserys.id", target = "nurserysId")
    @Mapping(source = "nurserys.nurseryName", target = "nurserysNurseryName")
    @Mapping(source = "pickListVariety.id", target = "pickListVarietyId")
    @Mapping(source = "pickListVariety.pickListValue", target = "pickListVarietyPickListValue")
    @Mapping(source = "pickListCategory.id", target = "pickListCategoryId")
    @Mapping(source = "pickListCategory.pickListValue", target = "pickListCategoryPickListValue")
    @Mapping(source = "quantityType.id", target = "quantityTypeId")
    @Mapping(source = "quantityType.pickListValue", target = "quantityTypePickListValue")
    NurseryInventoryDTO toDto(NurseryInventory nurseryInventory);

    @Mapping(source = "nurserysId", target = "nurserys")
    @Mapping(source = "pickListVarietyId", target = "pickListVariety")
    @Mapping(source = "pickListCategoryId", target = "pickListCategory")
    @Mapping(target = "nurseryInventoryDetails", ignore = true)
    @Mapping(source = "quantityTypeId", target = "quantityType")
    NurseryInventory toEntity(NurseryInventoryDTO nurseryInventoryDTO);

    List<NurseryInventoryDTO> toDto(List<NurseryInventory> nurseryInventory);

    default NurseryInventory fromId(Long id) {
        if (id == null) {
            return null;
        }
        NurseryInventory nurseryInventory = new NurseryInventory();
        nurseryInventory.setId(id);
        return nurseryInventory;
    }
}
