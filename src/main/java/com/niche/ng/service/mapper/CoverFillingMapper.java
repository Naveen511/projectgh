/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/17
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs CoverFillingMapper
 *
 *******************************************************************************/
package com.niche.ng.service.mapper;

import com.niche.ng.domain.*;
import com.niche.ng.service.dto.CoverFillingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CoverFilling and its DTO CoverFillingDTO.
 * Mapping the parent and child table to fetch the field value.
 * Converting the entity object into data transfer object(DTO).
 */
@Mapper(componentModel = "spring", uses = {})
public interface CoverFillingMapper extends EntityMapper<CoverFillingDTO, CoverFilling> {


    @Mapping(target = "coverFillingDetails", ignore = true)
    CoverFilling toEntity(CoverFillingDTO coverFillingDTO);

    default CoverFilling fromId(Long id) {
        if (id == null) {
            return null;
        }
        CoverFilling coverFilling = new CoverFilling();
        coverFilling.setId(id);
        return coverFilling;
    }
}
