package com.niche.ng.service.mapper;

import com.niche.ng.domain.*;
import com.niche.ng.service.dto.OperationalHeadDTO;

import org.mapstruct.*;
import java.util.List;
/**
 * Mapper for the entity OperationalHead and its DTO OperationalHeadDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OperationalHeadMapper extends EntityMapper<OperationalHeadDTO, OperationalHead> {

    @Mapping(target = "zonals", ignore = true)
    @Mapping(target = "mapZonalWithOhs", ignore = true)
    OperationalHead toEntity(OperationalHeadDTO operationalHeadDTO);

    List<OperationalHeadDTO> toDto(List<OperationalHead> operationalHead);

    default OperationalHead fromId(Long id) {
        if (id == null) {
            return null;
        }
        OperationalHead operationalHead = new OperationalHead();
        operationalHead.setId(id);
        return operationalHead;
    }
}
