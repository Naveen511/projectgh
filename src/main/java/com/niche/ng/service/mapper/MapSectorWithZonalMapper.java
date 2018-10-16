package com.niche.ng.service.mapper;

import com.niche.ng.domain.*;
import com.niche.ng.service.dto.MapSectorWithZonalDTO;

import org.mapstruct.*;
import java.util.List;
/**
 * Mapper for the entity MapSectorWithZonal and its DTO MapSectorWithZonalDTO.
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

    List<MapSectorWithZonalDTO> toDto(List<MapSectorWithZonal> mapSectorWithZonal);

    default MapSectorWithZonal fromId(Long id) {
        if (id == null) {
            return null;
        }
        MapSectorWithZonal mapSectorWithZonal = new MapSectorWithZonal();
        mapSectorWithZonal.setId(id);
        return mapSectorWithZonal;
    }
}
