/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryStockDetailsMapper
 *
 *******************************************************************************/
package com.niche.ng.service.mapper;

import com.niche.ng.domain.*;
import com.niche.ng.service.dto.NurseryStockDetailsDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity NurseryStockDetails and its DTO NurseryStockDetailsDTO.
 */
@Mapper(componentModel = "spring", uses = {BatchMapper.class, NurseryStockMapper.class, NurseryMapper.class, PickListValueMapper.class, FinancialYearSettingsMapper.class})
public interface NurseryStockDetailsMapper extends EntityMapper<NurseryStockDetailsDTO, NurseryStockDetails> {

    @Mapping(source = "batch.id", target = "batchId")
    @Mapping(source = "batch.batchName", target = "batchBatchName")
    @Mapping(source = "nurseryStock.id", target = "nurseryStockId")
    @Mapping(source = "itNursery.id", target = "itNurseryId")
    @Mapping(source = "itNursery.nurseryName", target = "itNurseryNurseryName")
    @Mapping(source = "saplingDamageArea.id", target = "saplingDamageAreaId")
    @Mapping(source = "saplingDamageArea.pickListValue", target = "saplingDamageAreaPickListValue")
    @Mapping(source = "financialYearStockDetails.id", target = "financialYearStockDetailsId")
    @Mapping(source = "financialYearStockDetails.batchName", target = "financialYearStockDetailsBatchName")
    @Mapping(source = "nurseryStock.pickListVariety.pickListValue", target= "stockVariety")
    @Mapping(source = "nurseryStock.pickListCategory.pickListValue", target= "stockCategory")
    @Mapping(source = "nurseryStock.pickListVariety.id", target= "stockVarietyId")
    @Mapping(source = "nurseryStock.pickListCategory.id", target= "stockCategoryId")
    NurseryStockDetailsDTO toDto(NurseryStockDetails nurseryStockDetails);
    List<NurseryStockDetailsDTO> toDto(List<NurseryStockDetails> nurseryStockDetails);

    @Mapping(source = "batchId", target = "batch")
    @Mapping(source = "nurseryStockId", target = "nurseryStock")
    @Mapping(source = "itNurseryId", target = "itNursery")
    @Mapping(source = "saplingDamageAreaId", target = "saplingDamageArea")
    @Mapping(source = "financialYearStockDetailsId", target = "financialYearStockDetails")
    NurseryStockDetails toEntity(NurseryStockDetailsDTO nurseryStockDetailsDTO);

    default NurseryStockDetails fromId(Long id) {
        if (id == null) {
            return null;
        }
        NurseryStockDetails nurseryStockDetails = new NurseryStockDetails();
        nurseryStockDetails.setId(id);
        return nurseryStockDetails;
    }
}
