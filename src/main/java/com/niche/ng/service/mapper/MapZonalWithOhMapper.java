/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/09/12
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs MapZonalWithOhMapper
 *
 *******************************************************************************/
package com.niche.ng.service.mapper;

import com.niche.ng.domain.*;
import com.niche.ng.service.dto.MapZonalWithOhDTO;

import org.mapstruct.*;
/**
 * Mapper for the entity MapZonalWithOh and its DTO MapZonalWithOhDTO.
 * Mapping the parent and child table to fetch the field value.
 * Converting the entity object into data transfer object(DTO).
 */
@Mapper(componentModel = "spring", uses = {ZonalMapper.class, OperationalHeadMapper.class})
public interface MapZonalWithOhMapper extends EntityMapper<MapZonalWithOhDTO, MapZonalWithOh> {

    @Mapping(source = "zonal.id", target = "zonalId")
    @Mapping(source = "zonal.zoneName", target = "zonalZoneName")
    @Mapping(source = "operationalHead.id", target = "operationalHeadId")
    @Mapping(source = "operationalHead.name", target = "operationalHeadName")
    MapZonalWithOhDTO toDto(MapZonalWithOh mapZonalWithOh);

    @Mapping(source = "zonalId", target = "zonal")
    @Mapping(source = "operationalHeadId", target = "operationalHead")
    MapZonalWithOh toEntity(MapZonalWithOhDTO mapZonalWithOhDTO);

    default MapZonalWithOh fromId(Long id) {
        if (id == null) {
            return null;
        }
        MapZonalWithOh mapZonalWithOh = new MapZonalWithOh();
        mapZonalWithOh.setId(id);
        return mapZonalWithOh;
    }
}
