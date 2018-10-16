/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryStockMapper
 *
 *******************************************************************************/
package com.niche.ng.service.mapper;

import com.niche.ng.domain.*;
import com.niche.ng.service.dto.NurseryStockDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity NurseryStock and its DTO NurseryStockDTO.
 */
@Mapper(componentModel = "spring", uses = {NurseryMapper.class, PickListValueMapper.class, FinancialYearSettingsMapper.class})
public interface NurseryStockMapper extends EntityMapper<NurseryStockDTO, NurseryStock> {

    @Mapping(source = "nursery.id", target = "nurseryId")
    @Mapping(source = "nursery.nurseryName", target = "nurseryNurseryName")
    @Mapping(source = "pickListVariety.id", target = "pickListVarietyId")
    @Mapping(source = "pickListVariety.pickListValue", target = "pickListVarietyPickListValue")
    @Mapping(source = "pickListCategory.id", target = "pickListCategoryId")
    @Mapping(source = "pickListCategory.pickListValue", target = "pickListCategoryPickListValue")
    @Mapping(source = "financialYearNurseryStock.id", target = "financialYearNurseryStockId")
    @Mapping(source = "financialYearNurseryStock.batchName", target = "financialYearNurseryStockBatchName")
    NurseryStockDTO toDto(NurseryStock nurseryStock);

    @Mapping(target = "nurseryStockDetails", ignore = true)
    @Mapping(source = "nurseryId", target = "nursery")
    @Mapping(source = "pickListVarietyId", target = "pickListVariety")
    @Mapping(source = "pickListCategoryId", target = "pickListCategory")
    @Mapping(source = "financialYearNurseryStockId", target = "financialYearNurseryStock")
    @Mapping(target = "pointOfSaleDetails", ignore = true)
    NurseryStock toEntity(NurseryStockDTO nurseryStockDTO);

    List<NurseryStockDTO> toDto(List<NurseryStock> nurseryStock);

    default NurseryStock fromId(Long id) {
        if (id == null) {
            return null;
        }
        NurseryStock nurseryStock = new NurseryStock();
        nurseryStock.setId(id);
        return nurseryStock;
    }
}
