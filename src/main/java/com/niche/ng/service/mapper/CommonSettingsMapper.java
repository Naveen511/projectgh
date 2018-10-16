/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/09/12
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs CommonSettingsMapper
 *
 *******************************************************************************/
package com.niche.ng.service.mapper;

import com.niche.ng.domain.*;
import com.niche.ng.service.dto.CommonSettingsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CommonSettings and its DTO CommonSettingsDTO.
 * Mapping the parent and child table to fetch the field value.
 * Converting the entity object into data transfer object(DTO).
 */
@Mapper(componentModel = "spring", uses = {})
public interface CommonSettingsMapper extends EntityMapper<CommonSettingsDTO, CommonSettings> {



    default CommonSettings fromId(Long id) {
        if (id == null) {
            return null;
        }
        CommonSettings commonSettings = new CommonSettings();
        commonSettings.setId(id);
        return commonSettings;
    }
}
