/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/25
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryInventoryDetailsMapper
 *
 *******************************************************************************/
package com.niche.ng.service.mapper;

import com.niche.ng.domain.*;
import com.niche.ng.service.dto.NurseryInventoryDetailsDTO;

import org.mapstruct.*;
/**
 * Mapper for the entity NurseryInventoryDetails and its DTO NurseryInventoryDetailsDTO.
 * Mapping the parent and child table to fetch the field value.
 * Converting the entity object into data transfer object(DTO).
 */
@Mapper(componentModel = "spring", uses = {NurseryInventoryMapper.class, PickListValueMapper.class})
public interface NurseryInventoryDetailsMapper extends EntityMapper<NurseryInventoryDetailsDTO, NurseryInventoryDetails> {

    @Mapping(source = "nurseryInventory.id", target = "nurseryInventoryId")
    @Mapping(source = "damageType.id", target = "damageTypeId")
    @Mapping(source = "damageType.pickListValue", target = "damageTypePickListValue")
    @Mapping(source = "inventoryDamageDescription.id", target = "inventoryDamageDescriptionId")
    @Mapping(source = "inventoryDamageDescription.pickListValue", target = "inventoryDamageDescriptionPickListValue")

    NurseryInventoryDetailsDTO toDto(NurseryInventoryDetails nurseryInventoryDetails);

    @Mapping(source = "nurseryInventoryId", target = "nurseryInventory")
    @Mapping(source = "damageTypeId", target = "damageType")
    @Mapping(source = "inventoryDamageDescriptionId", target = "inventoryDamageDescription")
    NurseryInventoryDetails toEntity(NurseryInventoryDetailsDTO nurseryInventoryDetailsDTO);

    default NurseryInventoryDetails fromId(Long id) {
        if (id == null) {
            return null;
        }
        NurseryInventoryDetails nurseryInventoryDetails = new NurseryInventoryDetails();
        nurseryInventoryDetails.setId(id);
        return nurseryInventoryDetails;
    }
}
