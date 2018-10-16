/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/09/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs ZonalInchargeMapper
 *
 *******************************************************************************/
package com.niche.ng.service.mapper;

import com.niche.ng.domain.*;
import com.niche.ng.service.dto.ZonalInchargeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ZonalIncharge and its DTO ZonalInchargeDTO.
 * Mapping the parent and child table to fetch the field value.
 * Converting the entity object into data transfer object(DTO).
 */
@Mapper(componentModel = "spring", uses = {ZonalMapper.class})
public interface ZonalInchargeMapper extends EntityMapper<ZonalInchargeDTO, ZonalIncharge> {

    @Mapping(source = "zonal.id", target = "zonalId")
    @Mapping(source = "zonal.zoneName", target = "zonalZoneName")
    ZonalInchargeDTO toDto(ZonalIncharge zonalIncharge);

    @Mapping(source = "zonalId", target = "zonal")
    ZonalIncharge toEntity(ZonalInchargeDTO zonalInchargeDTO);

    default ZonalIncharge fromId(Long id) {
        if (id == null) {
            return null;
        }
        ZonalIncharge zonalIncharge = new ZonalIncharge();
        zonalIncharge.setId(id);
        return zonalIncharge;
    }
}
