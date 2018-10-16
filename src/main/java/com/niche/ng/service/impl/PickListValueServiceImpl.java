/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs PickListValueServiceImplementation
 *
 *******************************************************************************/
package com.niche.ng.service.impl;

import com.niche.ng.service.PickListValueService;
import com.niche.ng.domain.PickListValue;
import com.niche.ng.repository.PickListValueRepository;
import com.niche.ng.service.dto.PickListValueDTO;
import com.niche.ng.service.mapper.PickListValueMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing PickListValue.
 *
 * Implementing PickListValueService with IMPL suffix class as PickListValueServiceImpl.
 * Using of business logic in the service layer which is present in the service file
 * using impl as a interface to access the repository layer.
 * Once we got the responce from the repository layer, mapper convert the entity
 * object to data transfer object(DTO).
 */
@Service
@Transactional
public class PickListValueServiceImpl implements PickListValueService {

    private final Logger log = LoggerFactory.getLogger(PickListValueServiceImpl.class);

    private final PickListValueRepository pickListValueRepository;

    private final PickListValueMapper pickListValueMapper;

    public PickListValueServiceImpl(PickListValueRepository pickListValueRepository, PickListValueMapper pickListValueMapper) {
        this.pickListValueRepository = pickListValueRepository;
        this.pickListValueMapper = pickListValueMapper;
    }

    /**
     * Save a pickListValue.
     *
     * @param pickListValueDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PickListValueDTO save(PickListValueDTO pickListValueDTO) {
        log.debug("Request to save PickListValue : {}", pickListValueDTO);
        PickListValue pickListValue = pickListValueMapper.toEntity(pickListValueDTO);
        pickListValue = pickListValueRepository.save(pickListValue);
        return pickListValueMapper.toDto(pickListValue);
    }

    /**
     * Get all the pickListValues.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PickListValueDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PickListValues");
        return pickListValueRepository.findAll(pageable)
            .map(pickListValueMapper::toDto);
    }


    /**
     * Get one pickListValue by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PickListValueDTO> findOne(Long id) {
        log.debug("Request to get PickListValue : {}", id);
        return pickListValueRepository.findById(id)
            .map(pickListValueMapper::toDto);
    }

    /**
     * Delete the pickListValue by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PickListValue : {}", id);
        pickListValueRepository.deleteById(id);
    }
}
