package com.niche.ng.service.mapper;

import com.niche.ng.domain.*;
import com.niche.ng.service.dto.CommonSettingsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CommonSettings and its DTO CommonSettingsDTO.
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
