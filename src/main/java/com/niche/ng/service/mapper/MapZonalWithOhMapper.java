package com.niche.ng.service.mapper;

import com.niche.ng.domain.*;
import com.niche.ng.service.dto.MapZonalWithOhDTO;

import org.mapstruct.*;
import java.util.List;
/**
 * Mapper for the entity MapZonalWithOh and its DTO MapZonalWithOhDTO.
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

    List<MapZonalWithOhDTO> toDto(List<MapZonalWithOh> mapZonalWithOh);

    default MapZonalWithOh fromId(Long id) {
        if (id == null) {
            return null;
        }
        MapZonalWithOh mapZonalWithOh = new MapZonalWithOh();
        mapZonalWithOh.setId(id);
        return mapZonalWithOh;
    }
}
