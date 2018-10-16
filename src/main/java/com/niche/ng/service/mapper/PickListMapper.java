/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs PickListMapper
 *
 *******************************************************************************/
package com.niche.ng.service.mapper;

import com.niche.ng.domain.*;
import com.niche.ng.service.dto.PickListDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PickList and its DTO PickListDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PickListMapper extends EntityMapper<PickListDTO, PickList> {


    @Mapping(target = "pickListValues", ignore = true)
    PickList toEntity(PickListDTO pickListDTO);

    default PickList fromId(Long id) {
        if (id == null) {
            return null;
        }
        PickList pickList = new PickList();
        pickList.setId(id);
        return pickList;
    }
}
