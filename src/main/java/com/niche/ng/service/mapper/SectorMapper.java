/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs SectorMapper
 *
 *******************************************************************************/
package com.niche.ng.service.mapper;

import com.niche.ng.domain.*;
import com.niche.ng.service.dto.SectorDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Sector and its DTO SectorDTO.
 * Mapping the parent and child table to fetch the field value.
 * Converting the entity object into data transfer object(DTO).
 */
@Mapper(componentModel = "spring", uses = {ZonalMapper.class, FinancialYearSettingsMapper.class})
public interface SectorMapper extends EntityMapper<SectorDTO, Sector> {

    @Mapping(source = "zonal.id", target = "zonalId")
    @Mapping(source = "zonal.zoneName", target = "zonalZoneName")
    @Mapping(source = "financialYearSector.id", target = "financialYearSectorId")
    @Mapping(source = "financialYearSector.batchName", target = "financialYearSectorBatchName")
    SectorDTO toDto(Sector sector);

    @Mapping(target = "nurserys", ignore = true)
    @Mapping(source = "zonalId", target = "zonal")
    @Mapping(source = "financialYearSectorId", target = "financialYearSector")
    @Mapping(target = "incharges", ignore = true)
    @Mapping(target = "mapSectorWithZonals", ignore = true)
    @Mapping(target = "mapNurseryWithSectors", ignore = true)
    Sector toEntity(SectorDTO sectorDTO);

    default Sector fromId(Long id) {
        if (id == null) {
            return null;
        }
        Sector sector = new Sector();
        sector.setId(id);
        return sector;
    }
}
