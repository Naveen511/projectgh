package com.niche.ng.service.mapper;

import com.niche.ng.domain.*;
import com.niche.ng.service.dto.NurseryInchargeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity NurseryIncharge and its DTO NurseryInchargeDTO.
 */
@Mapper(componentModel = "spring", uses = {NurseryMapper.class})
public interface NurseryInchargeMapper extends EntityMapper<NurseryInchargeDTO, NurseryIncharge> {

    @Mapping(source = "nursery.id", target = "nurseryId")
    @Mapping(source = "nursery.nurseryName", target = "nurseryNurseryName")
    NurseryInchargeDTO toDto(NurseryIncharge nurseryIncharge);

    @Mapping(source = "nurseryId", target = "nursery")
    NurseryIncharge toEntity(NurseryInchargeDTO nurseryInchargeDTO);

    default NurseryIncharge fromId(Long id) {
        if (id == null) {
            return null;
        }
        NurseryIncharge nurseryIncharge = new NurseryIncharge();
        nurseryIncharge.setId(id);
        return nurseryIncharge;
    }
}
