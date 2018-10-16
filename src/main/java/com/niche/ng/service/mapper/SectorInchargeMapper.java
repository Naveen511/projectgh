/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/09/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs SectorInchargeMapper
 *
 *******************************************************************************/
package com.niche.ng.service.mapper;

import com.niche.ng.domain.*;
import com.niche.ng.service.dto.SectorInchargeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SectorIncharge and its DTO SectorInchargeDTO.
 * Mapping the parent and child table to fetch the field value.
 * Converting the entity object into data transfer object(DTO).
 */
@Mapper(componentModel = "spring", uses = {SectorMapper.class})
public interface SectorInchargeMapper extends EntityMapper<SectorInchargeDTO, SectorIncharge> {

    @Mapping(source = "sector.id", target = "sectorId")
    @Mapping(source = "sector.sectorName", target = "sectorSectorName")
    SectorInchargeDTO toDto(SectorIncharge sectorIncharge);

    @Mapping(source = "sectorId", target = "sector")
    SectorIncharge toEntity(SectorInchargeDTO sectorInchargeDTO);

    default SectorIncharge fromId(Long id) {
        if (id == null) {
            return null;
        }
        SectorIncharge sectorIncharge = new SectorIncharge();
        sectorIncharge.setId(id);
        return sectorIncharge;
    }
}
