package com.niche.ng.service.mapper;

import com.niche.ng.domain.*;
import com.niche.ng.service.dto.CoverFillingDetailsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CoverFillingDetails and its DTO CoverFillingDetailsDTO.
 */
@Mapper(componentModel = "spring", uses = {CoverFillingMapper.class, PickListValueMapper.class})
public interface CoverFillingDetailsMapper extends EntityMapper<CoverFillingDetailsDTO, CoverFillingDetails> {

    @Mapping(source = "coverFilling.id", target = "coverFillingId")
    @Mapping(source = "damageType.id", target = "damageTypeId")
    @Mapping(source = "damageType.pickListValue", target = "damageTypePickListValue")
    @Mapping(source = "coverFillingDamageDescription.id", target = "coverFillingDamageDescriptionId")
    @Mapping(source = "coverFillingDamageDescription.pickListValue", target = "coverFillingDamageDescriptionPickListValue")
    CoverFillingDetailsDTO toDto(CoverFillingDetails coverFillingDetails);

    @Mapping(source = "coverFillingId", target = "coverFilling")
    @Mapping(source = "damageTypeId", target = "damageType")
    @Mapping(source = "coverFillingDamageDescriptionId", target = "coverFillingDamageDescription")
    CoverFillingDetails toEntity(CoverFillingDetailsDTO coverFillingDetailsDTO);

    default CoverFillingDetails fromId(Long id) {
        if (id == null) {
            return null;
        }
        CoverFillingDetails coverFillingDetails = new CoverFillingDetails();
        coverFillingDetails.setId(id);
        return coverFillingDetails;
    }
}
