/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs PickListServiceImplementation
 *
 *******************************************************************************/
package com.niche.ng.service.impl;

import com.niche.ng.service.PickListService;
import com.niche.ng.domain.PickList;
import com.niche.ng.repository.PickListRepository;
import com.niche.ng.service.dto.PickListDTO;
import com.niche.ng.service.mapper.PickListMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
/**
 * Service Implementation for managing PickList.
 *
 * Implementing PickListService with IMPL suffix class as PickListServiceImpl.
 * Using of business logic in the service layer which is present in the service file
 * using impl as a interface to access the repository layer.
 * Once we got the responce from the repository layer, mapper convert the entity
 * object to data transfer object(DTO).
 */
@Service
@Transactional
public class PickListServiceImpl implements PickListService {

    private final Logger log = LoggerFactory.getLogger(PickListServiceImpl.class);

    private final PickListRepository pickListRepository;

    private final PickListMapper pickListMapper;

    public PickListServiceImpl(PickListRepository pickListRepository, PickListMapper pickListMapper) {
        this.pickListRepository = pickListRepository;
        this.pickListMapper = pickListMapper;
    }

    /**
     * Save a pickList.
     *
     * @param pickListDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PickListDTO save(PickListDTO pickListDTO) {
        log.debug("Request to save PickList : {}", pickListDTO);
        PickList pickList = pickListMapper.toEntity(pickListDTO);
        pickList = pickListRepository.save(pickList);
        return pickListMapper.toDto(pickList);
    }

    /**
     * Get all the pickLists.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PickListDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PickLists");
        return pickListRepository.findAll(pageable)
            .map(pickListMapper::toDto);
    }


    /**
     * Get one pickList by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PickListDTO> findOne(Long id) {
        log.debug("Request to get PickList : {}", id);
        return pickListRepository.findById(id)
            .map(pickListMapper::toDto);
    }

    /**
     * Delete the pickList by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PickList : {}", id);
        pickListRepository.deleteById(id);
    }
}
