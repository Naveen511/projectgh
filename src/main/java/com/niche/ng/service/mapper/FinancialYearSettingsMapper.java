/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs FinancialYearSettingMapper
 *
 *******************************************************************************/
package com.niche.ng.service.mapper;

import com.niche.ng.domain.*;
import com.niche.ng.service.dto.FinancialYearSettingsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity FinancialYearSettings and its DTO FinancialYearSettingsDTO.
 * Mapping the parent and child table to fetch the field value.
 * Converting the entity object into data transfer object(DTO).
 */
@Mapper(componentModel = "spring", uses = {PickListValueMapper.class})
public interface FinancialYearSettingsMapper extends EntityMapper<FinancialYearSettingsDTO, FinancialYearSettings> {

    @Mapping(source = "financialYear.id", target = "financialYearId")
    @Mapping(source = "financialYear.pickListValue", target = "financialYearPickListValue")
    FinancialYearSettingsDTO toDto(FinancialYearSettings financialYearSettings);

    @Mapping(source = "financialYearId", target = "financialYear")
    @Mapping(target = "zonals", ignore = true)
    @Mapping(target = "sectors", ignore = true)
    @Mapping(target = "nurseries", ignore = true)
    @Mapping(target = "batches", ignore = true)
    @Mapping(target = "damages", ignore = true)
    @Mapping(target = "shadeAreas", ignore = true)
    @Mapping(target = "nurseryStocks", ignore = true)
    @Mapping(target = "nurseryStockDetails", ignore = true)
    @Mapping(target = "godowns", ignore = true)
    @Mapping(target = "godownStocks", ignore = true)
    @Mapping(target = "godownStockDetails", ignore = true)
    @Mapping(target = "godownPurchaseDetails", ignore = true)
    FinancialYearSettings toEntity(FinancialYearSettingsDTO financialYearSettingsDTO);

    default FinancialYearSettings fromId(Long id) {
        if (id == null) {
            return null;
        }
        FinancialYearSettings financialYearSettings = new FinancialYearSettings();
        financialYearSettings.setId(id);
        return financialYearSettings;
    }
}
