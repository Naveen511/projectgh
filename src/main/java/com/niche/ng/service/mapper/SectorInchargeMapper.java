package com.niche.ng.service.mapper;

import com.niche.ng.domain.*;
import com.niche.ng.service.dto.SectorInchargeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SectorIncharge and its DTO SectorInchargeDTO.
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
