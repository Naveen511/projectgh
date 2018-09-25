/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs GodownMapper
 *
 *******************************************************************************/
package com.niche.ng.service.mapper;

import com.niche.ng.domain.*;
import com.niche.ng.service.dto.GodownDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Godown and its DTO GodownDTO.
 */
@Mapper(componentModel = "spring", uses = {FinancialYearSettingsMapper.class})
public interface GodownMapper extends EntityMapper<GodownDTO, Godown> {

    @Mapping(source = "financialYearGodown.id", target = "financialYearGodownId")
    @Mapping(source = "financialYearGodown.batchName", target = "financialYearGodownBatchName")
    GodownDTO toDto(Godown godown);

    @Mapping(target = "godownPurchaseDetails", ignore = true)
    @Mapping(target = "godownStocks", ignore = true)
    @Mapping(source = "financialYearGodownId", target = "financialYearGodown")
    Godown toEntity(GodownDTO godownDTO);

    default Godown fromId(Long id) {
        if (id == null) {
            return null;
        }
        Godown godown = new Godown();
        godown.setId(id);
        return godown;
    }
}
