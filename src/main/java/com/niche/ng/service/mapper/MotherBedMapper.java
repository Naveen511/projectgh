/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/22
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs MotherBedMapper
 *
 *******************************************************************************/
package com.niche.ng.service.mapper;

import com.niche.ng.domain.*;
import com.niche.ng.service.dto.MotherBedDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity MotherBed and its DTO MotherBedDTO.
 */
@Mapper(componentModel = "spring", uses = {NurseryMapper.class})
public interface MotherBedMapper extends EntityMapper<MotherBedDTO, MotherBed> {

    @Mapping(source = "nursery.id", target = "nurseryId")
    @Mapping(source = "nursery.nurseryName", target = "nurseryNurseryName")
    MotherBedDTO toDto(MotherBed motherBed);

    @Mapping(source = "nurseryId", target = "nursery")
    @Mapping(target = "batchMotherBeds", ignore = true)
    MotherBed toEntity(MotherBedDTO motherBedDTO);

    List<MotherBedDTO> toDto(List<MotherBed> nursery);

    default MotherBed fromId(Long id) {
        if (id == null) {
            return null;
        }
        MotherBed motherBed = new MotherBed();
        motherBed.setId(id);
        return motherBed;
    }
}
