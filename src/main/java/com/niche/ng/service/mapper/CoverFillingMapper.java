package com.niche.ng.service.mapper;

import com.niche.ng.domain.*;
import com.niche.ng.service.dto.CoverFillingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CoverFilling and its DTO CoverFillingDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CoverFillingMapper extends EntityMapper<CoverFillingDTO, CoverFilling> {


    @Mapping(target = "coverFillingDetails", ignore = true)
    CoverFilling toEntity(CoverFillingDTO coverFillingDTO);

    default CoverFilling fromId(Long id) {
        if (id == null) {
            return null;
        }
        CoverFilling coverFilling = new CoverFilling();
        coverFilling.setId(id);
        return coverFilling;
    }
}
