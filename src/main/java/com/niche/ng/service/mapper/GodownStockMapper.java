/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs GodownStockMapper
 *
 *******************************************************************************/
package com.niche.ng.service.mapper;

import com.niche.ng.domain.*;
import com.niche.ng.service.dto.GodownStockDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity GodownStock and its DTO GodownStockDTO.
 * Mapping the parent and child table to fetch the field value.
 * Converting the entity object into data transfer object(DTO).
 */
@Mapper(componentModel = "spring", uses = {PickListValueMapper.class, GodownMapper.class, FinancialYearSettingsMapper.class})
public interface GodownStockMapper extends EntityMapper<GodownStockDTO, GodownStock> {

    @Mapping(source = "pickListVariety.id", target = "pickListVarietyId")
    @Mapping(source = "pickListVariety.pickListValue", target = "pickListVarietyPickListValue")
    @Mapping(source = "pickListCategory.id", target = "pickListCategoryId")
    @Mapping(source = "pickListCategory.pickListValue", target = "pickListCategoryPickListValue")
    @Mapping(source = "pickListQuantityType.id", target = "pickListQuantityTypeId")
    @Mapping(source = "pickListQuantityType.pickListValue", target = "pickListQuantityTypePickListValue")
    @Mapping(source = "godown.id", target = "godownId")
    @Mapping(source = "godown.name", target = "godownName")
    @Mapping(source = "financialYearGodownStock.id", target = "financialYearGodownStockId")
    @Mapping(source = "financialYearGodownStock.batchName", target = "financialYearGodownStockBatchName")
    GodownStockDTO toDto(GodownStock godownStock);

    @Mapping(target = "godownStockDetails", ignore = true)
    @Mapping(source = "pickListVarietyId", target = "pickListVariety")
    @Mapping(source = "pickListCategoryId", target = "pickListCategory")
    @Mapping(source = "pickListQuantityTypeId", target = "pickListQuantityType")
    @Mapping(source = "godownId", target = "godown")
    @Mapping(source = "financialYearGodownStockId", target = "financialYearGodownStock")
    GodownStock toEntity(GodownStockDTO godownStockDTO);

    default GodownStock fromId(Long id) {
        if (id == null) {
            return null;
        }
        GodownStock godownStock = new GodownStock();
        godownStock.setId(id);
        return godownStock;
    }
}
