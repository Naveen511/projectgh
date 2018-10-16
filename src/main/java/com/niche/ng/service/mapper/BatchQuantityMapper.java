/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/10/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs BatchQuantityMapper
 *
 *******************************************************************************/
package com.niche.ng.service.mapper;

import com.niche.ng.domain.*;
import com.niche.ng.service.dto.BatchQuantityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity BatchQuantity and its DTO BatchQuantityDTO.
 * Mapping the parent and child table to fetch the field value.
 * Converting the entity object into data transfer object(DTO).
 */
@Mapper(componentModel = "spring", uses = {BatchMapper.class})
public interface BatchQuantityMapper extends EntityMapper<BatchQuantityDTO, BatchQuantity> {

    @Mapping(source = "batch.id", target = "batchId")
    @Mapping(source = "batch.batchName", target = "batchBatchName")
    BatchQuantityDTO toDto(BatchQuantity batchQuantity);

    @Mapping(source = "batchId", target = "batch")
    BatchQuantity toEntity(BatchQuantityDTO batchQuantityDTO);

    default BatchQuantity fromId(Long id) {
        if (id == null) {
            return null;
        }
        BatchQuantity batchQuantity = new BatchQuantity();
        batchQuantity.setId(id);
        return batchQuantity;
    }
}
