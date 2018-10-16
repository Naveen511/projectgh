/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/09/12
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs MapSectorWithZonalMapper
 *
 *******************************************************************************/
package com.niche.ng.service.mapper;

import com.niche.ng.domain.*;
import com.niche.ng.service.dto.MapSectorWithZonalDTO;

import org.mapstruct.*;
/**
 * Mapper for the entity MapSectorWithZonal and its DTO MapSectorWithZonalDTO.
 * Mapping the parent and child table to fetch the field value.
 * Converting the entity object into data transfer object(DTO).
 */
@Mapper(componentModel = "spring", uses = {ZonalMapper.class, SectorMapper.class})
public interface MapSectorWithZonalMapper extends EntityMapper<MapSectorWithZonalDTO, MapSectorWithZonal> {

    @Mapping(source = "zonal.id", target = "zonalId")
    @Mapping(source = "zonal.zoneName", target = "zonalZoneName")
    @Mapping(source = "sector.id", target = "sectorId")
    @Mapping(source = "sector.sectorName", target = "sectorSectorName")
    MapSectorWithZonalDTO toDto(MapSectorWithZonal mapSectorWithZonal);

    @Mapping(source = "zonalId", target = "zonal")
    @Mapping(source = "sectorId", target = "sector")
    MapSectorWithZonal toEntity(MapSectorWithZonalDTO mapSectorWithZonalDTO);

    default MapSectorWithZonal fromId(Long id) {
        if (id == null) {
            return null;
        }
        MapSectorWithZonal mapSectorWithZonal = new MapSectorWithZonal();
        mapSectorWithZonal.setId(id);
        return mapSectorWithZonal;
    }
}
