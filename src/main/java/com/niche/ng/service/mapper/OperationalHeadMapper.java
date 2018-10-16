/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/09/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs OperationalHeadMapper
 *
 *******************************************************************************/
package com.niche.ng.service.mapper;

import com.niche.ng.domain.*;
import com.niche.ng.service.dto.OperationalHeadDTO;

import org.mapstruct.*;
/**
 * Mapper for the entity OperationalHead and its DTO OperationalHeadDTO.
 * Mapping the parent and child table to fetch the field value.
 * Converting the entity object into data transfer object(DTO).
 */
@Mapper(componentModel = "spring", uses = {})
public interface OperationalHeadMapper extends EntityMapper<OperationalHeadDTO, OperationalHead> {

    @Mapping(target = "zonals", ignore = true)
    @Mapping(target = "mapZonalWithOhs", ignore = true)
    OperationalHead toEntity(OperationalHeadDTO operationalHeadDTO);

    default OperationalHead fromId(Long id) {
        if (id == null) {
            return null;
        }
        OperationalHead operationalHead = new OperationalHead();
        operationalHead.setId(id);
        return operationalHead;
    }
}
