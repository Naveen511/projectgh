package com.niche.ng.service.mapper;

import com.niche.ng.domain.*;
import com.niche.ng.service.dto.MapNurseryWithSectorDTO;

import org.mapstruct.*;
import java.util.List;
/**
 * Mapper for the entity MapNurseryWithSector and its DTO MapNurseryWithSectorDTO.
 */
@Mapper(componentModel = "spring", uses = {NurseryMapper.class, SectorMapper.class})
public interface MapNurseryWithSectorMapper extends EntityMapper<MapNurseryWithSectorDTO, MapNurseryWithSector> {

    @Mapping(source = "nursery.id", target = "nurseryId")
    @Mapping(source = "nursery.nurseryName", target = "nurseryNurseryName")
    @Mapping(source = "sector.id", target = "sectorId")
    @Mapping(source = "sector.sectorName", target = "sectorSectorName")
    MapNurseryWithSectorDTO toDto(MapNurseryWithSector mapNurseryWithSector);

    @Mapping(source = "nurseryId", target = "nursery")
    @Mapping(source = "sectorId", target = "sector")
    MapNurseryWithSector toEntity(MapNurseryWithSectorDTO mapNurseryWithSectorDTO);

    List<MapNurseryWithSectorDTO> toDto(List<MapNurseryWithSector> mapNurseryWithSector);

    default MapNurseryWithSector fromId(Long id) {
        if (id == null) {
            return null;
        }
        MapNurseryWithSector mapNurseryWithSector = new MapNurseryWithSector();
        mapNurseryWithSector.setId(id);
        return mapNurseryWithSector;
    }
}
