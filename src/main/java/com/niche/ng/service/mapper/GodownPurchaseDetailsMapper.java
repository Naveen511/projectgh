/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs GodownPurchaseDetailsMapper
 *
 *******************************************************************************/
package com.niche.ng.service.mapper;

import com.niche.ng.domain.*;
import com.niche.ng.service.dto.GodownPurchaseDetailsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity GodownPurchaseDetails and its DTO GodownPurchaseDetailsDTO.
 * Mapping the parent and child table to fetch the field value.
 * Converting the entity object into data transfer object(DTO).
 */
@Mapper(componentModel = "spring", uses = {PickListValueMapper.class, GodownMapper.class, FinancialYearSettingsMapper.class})
public interface GodownPurchaseDetailsMapper extends EntityMapper<GodownPurchaseDetailsDTO, GodownPurchaseDetails> {

    @Mapping(source = "pickListVariety.id", target = "pickListVarietyId")
    @Mapping(source = "pickListVariety.pickListValue", target = "pickListVarietyPickListValue")
    @Mapping(source = "pickListCategory.id", target = "pickListCategoryId")
    @Mapping(source = "pickListCategory.pickListValue", target = "pickListCategoryPickListValue")
    @Mapping(source = "pickListQuantityType.id", target = "pickListQuantityTypeId")
    @Mapping(source = "pickListQuantityType.pickListValue", target = "pickListQuantityTypePickListValue")
    @Mapping(source = "godown.id", target = "godownId")
    @Mapping(source = "godown.name", target = "godownName")
    @Mapping(source = "financialYearGodownPurchase.id", target = "financialYearGodownPurchaseId")
    @Mapping(source = "financialYearGodownPurchase.batchName", target = "financialYearGodownPurchaseBatchName")
    GodownPurchaseDetailsDTO toDto(GodownPurchaseDetails godownPurchaseDetails);

    @Mapping(source = "pickListVarietyId", target = "pickListVariety")
    @Mapping(source = "pickListCategoryId", target = "pickListCategory")
    @Mapping(source = "pickListQuantityTypeId", target = "pickListQuantityType")
    @Mapping(source = "godownId", target = "godown")
    @Mapping(source = "financialYearGodownPurchaseId", target = "financialYearGodownPurchase")
    GodownPurchaseDetails toEntity(GodownPurchaseDetailsDTO godownPurchaseDetailsDTO);

    default GodownPurchaseDetails fromId(Long id) {
        if (id == null) {
            return null;
        }
        GodownPurchaseDetails godownPurchaseDetails = new GodownPurchaseDetails();
        godownPurchaseDetails.setId(id);
        return godownPurchaseDetails;
    }
}
