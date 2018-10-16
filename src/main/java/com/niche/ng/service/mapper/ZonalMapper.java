/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs ZonalMapper
 *
 *******************************************************************************/
package com.niche.ng.service.mapper;

import com.niche.ng.domain.*;
import com.niche.ng.service.dto.ZonalDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Zonal and its DTO ZonalDTO.
 * Mapping the parent and child table to fetch the field value.
 * Converting the entity object into data transfer object(DTO).
 */
@Mapper(componentModel = "spring", uses = {FinancialYearSettingsMapper.class, OperationalHeadMapper.class})
public interface ZonalMapper extends EntityMapper<ZonalDTO, Zonal> {

    @Mapping(source = "financialYear.id", target = "financialYearId")
    @Mapping(source = "financialYear.batchName", target = "financialYearBatchName")
    @Mapping(source = "operationalHead.id", target = "operationalHeadId")
    @Mapping(source = "operationalHead.name", target = "operationalHeadName")
    ZonalDTO toDto(Zonal zonal);

    @Mapping(target = "sectors", ignore = true)
    @Mapping(source = "financialYearId", target = "financialYear")
    @Mapping(source = "operationalHeadId", target = "operationalHead")
    @Mapping(target = "mapZonalWithOhs", ignore = true)
    @Mapping(target = "zonalIncharges", ignore = true)
    @Mapping(target = "mapSectorWithZonals", ignore = true)
    Zonal toEntity(ZonalDTO zonalDTO);

    default Zonal fromId(Long id) {
        if (id == null) {
            return null;
        }
        Zonal zonal = new Zonal();
        zonal.setId(id);
        return zonal;
    }
}
