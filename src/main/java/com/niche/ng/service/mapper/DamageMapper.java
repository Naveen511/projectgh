/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs DamageMapper
 *
 *******************************************************************************/
package com.niche.ng.service.mapper;

import com.niche.ng.domain.*;
import com.niche.ng.service.dto.DamageDTO;

import org.mapstruct.*;
import java.util.List;
/**
 * Mapper for the entity Damage and its DTO DamageDTO.
 */
@Mapper(componentModel = "spring", uses = {BatchMapper.class, PickListValueMapper.class, FinancialYearSettingsMapper.class})
public interface DamageMapper extends EntityMapper<DamageDTO, Damage> {

    @Mapping(source = "batch.id", target = "batchId")
    @Mapping(source = "batch.batchName", target = "batchBatchName")
    @Mapping(source = "description.id", target = "descriptionId")
    @Mapping(source = "description.pickListValue", target = "descriptionPickListValue")
    @Mapping(source = "damageArea.id", target = "damageAreaId")
    @Mapping(source = "damageArea.pickListValue", target = "damageAreaPickListValue")
    @Mapping(source = "financialYearDamage.id", target = "financialYearDamageId")
    @Mapping(source = "financialYearDamage.batchName", target = "financialYearDamageBatchName")
    DamageDTO toDto(Damage damage);

    @Mapping(source = "batchId", target = "batch")
    @Mapping(source = "descriptionId", target = "description")
    @Mapping(source = "damageAreaId", target = "damageArea")
    @Mapping(source = "financialYearDamageId", target = "financialYearDamage")
    Damage toEntity(DamageDTO damageDTO);

    List<DamageDTO> toDto(List<Damage> damage);

    default Damage fromId(Long id) {
        if (id == null) {
            return null;
        }
        Damage damage = new Damage();
        damage.setId(id);
        return damage;
    }
}
