/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryMapper
 *
 *******************************************************************************/
package com.niche.ng.service.mapper;

import com.niche.ng.domain.*;
import com.niche.ng.service.dto.NurseryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Nursery and its DTO NurseryDTO.
 * Mapping the parent and child table to fetch the field value.
 * Converting the entity object into data transfer object(DTO).
 */
@Mapper(componentModel = "spring", uses = {SectorMapper.class, PickListValueMapper.class, FinancialYearSettingsMapper.class})
public interface NurseryMapper extends EntityMapper<NurseryDTO, Nursery> {

    @Mapping(source = "sector.id", target = "sectorId")
    @Mapping(source = "sector.sectorName", target = "sectorSectorName")
    @Mapping(source = "sector.zonal.zoneName", target = "zonalName")
    @Mapping(source = "nurseryType.id", target = "nurseryTypeId")
    @Mapping(source = "nurseryType.pickListValue", target = "nurseryTypePickListValue")
    @Mapping(source = "financialYearNursery.id", target = "financialYearNurseryId")
    @Mapping(source = "financialYearNursery.batchName", target = "financialYearNurseryBatchName")
    NurseryDTO toDto(Nursery nursery);

    @Mapping(target = "batchs", ignore = true)
    @Mapping(target = "nurseryStocks", ignore = true)
    @Mapping(source = "sectorId", target = "sector")
    @Mapping(source = "nurseryTypeId", target = "nurseryType")
    @Mapping(target = "motherBeds", ignore = true)
    @Mapping(target = "nurseryInventorys", ignore = true)
    @Mapping(target = "nurseryStockDetails", ignore = true)
    @Mapping(source = "financialYearNurseryId", target = "financialYearNursery")
    @Mapping(target = "incharges", ignore = true)
    @Mapping(target = "mapNurseryWithSectors", ignore = true)
    @Mapping(target = "fromNurseryStockDetails", ignore = true)
    Nursery toEntity(NurseryDTO nurseryDTO);

    default Nursery fromId(Long id) {
        if (id == null) {
            return null;
        }
        Nursery nursery = new Nursery();
        nursery.setId(id);
        return nursery;
    }
}
