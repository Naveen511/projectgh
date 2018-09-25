/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs PickListValueMapper
 *
 *******************************************************************************/
package com.niche.ng.service.mapper;

import com.niche.ng.domain.*;
import com.niche.ng.service.dto.PickListValueDTO;

import org.mapstruct.*;
import java.util.List;
/**
 * Mapper for the entity PickListValue and its DTO PickListValueDTO.
 */
@Mapper(componentModel = "spring", uses = {PickListMapper.class})
public interface PickListValueMapper extends EntityMapper<PickListValueDTO, PickListValue> {

    @Mapping(source = "pickList.id", target = "pickListId")
    @Mapping(source = "pickList.pickListName", target = "pickListPickListName")
    @Mapping(source = "pickValue.id", target = "pickValueId")
    @Mapping(source = "pickValue.pickListValue", target = "pickValuePickListValue")
    PickListValueDTO toDto(PickListValue pickListValue);

    @Mapping(target = "selfIds", ignore = true)
    @Mapping(target = "varietys", ignore = true)
    @Mapping(target = "categorys", ignore = true)
    @Mapping(target = "nurseryStockVarietys", ignore = true)
    @Mapping(target = "nurseryStockCategorys", ignore = true)
    @Mapping(target = "godownPurchaseVarietys", ignore = true)
    @Mapping(target = "godownPurchaseCategorys", ignore = true)
    @Mapping(target = "godownPurchaseQuantityTypes", ignore = true)
    @Mapping(target = "godownStockVarietys", ignore = true)
    @Mapping(target = "godownStockCategorys", ignore = true)
    @Mapping(target = "godownStockQuantityTypes", ignore = true)
    @Mapping(source = "pickListId", target = "pickList")
    @Mapping(source = "pickValueId", target = "pickValue")
    @Mapping(target = "nurserys", ignore = true)
    @Mapping(target = "batchQuantityTypes", ignore = true)
    @Mapping(target = "nurseryInventoryVarietys", ignore = true)
    @Mapping(target = "nurseryInventoryCategorys", ignore = true)
    @Mapping(target = "nurseryInventoryQuantityTypes", ignore = true)
    @Mapping(target = "nurseryInventoryDamageTypes", ignore = true)
    @Mapping(target = "pickListValueDamageAreas", ignore = true)
    @Mapping(target = "nurseryStockDamageAreas", ignore = true)
    @Mapping(target = "financialYearNames", ignore = true)
    @Mapping(target = "damageDescriptions", ignore = true)
    @Mapping(target = "quantitysVarieties", ignore = true)
    @Mapping(target = "quantitysCategories", ignore = true)
    @Mapping(target = "pointOfSaleVarietys", ignore = true)
    @Mapping(target = "pointOfSaleCategorys", ignore = true)

    @Mapping(target = "coverFillingDetails", ignore = true)
    @Mapping(target = "nurseryInventoryDamageDescs", ignore = true)
    @Mapping(target = "coverFillingDamageDescs", ignore = true)
    PickListValue toEntity(PickListValueDTO pickListValueDTO);

    List<PickListValueDTO> toDto(List<PickListValue> pickListValue);

    default PickListValue fromId(Long id) {
        if (id == null) {
            return null;
        }
        PickListValue pickListValue = new PickListValue();
        pickListValue.setId(id);
        return pickListValue;
    }
}
