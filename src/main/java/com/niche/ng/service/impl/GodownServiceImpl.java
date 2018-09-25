/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs GodownServiceImpl
 *
 *******************************************************************************/
package com.niche.ng.service.impl;

import com.niche.ng.service.GodownService;
import com.niche.ng.domain.Godown;
import com.niche.ng.repository.GodownRepository;
import com.niche.ng.service.dto.GodownDTO;
import com.niche.ng.service.mapper.GodownMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing Godown.
 */
@Service
@Transactional
public class GodownServiceImpl implements GodownService {

    private final Logger log = LoggerFactory.getLogger(GodownServiceImpl.class);

    private final GodownRepository godownRepository;

    private final GodownMapper godownMapper;

    public GodownServiceImpl(GodownRepository godownRepository, GodownMapper godownMapper) {
        this.godownRepository = godownRepository;
        this.godownMapper = godownMapper;
    }

    /**
     * Save a godown.
     *
     * @param godownDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public GodownDTO save(GodownDTO godownDTO) {
        log.debug("Request to save Godown : {}", godownDTO);
        Godown godown = godownMapper.toEntity(godownDTO);
        godown = godownRepository.save(godown);
        return godownMapper.toDto(godown);
    }

    /**
     * Get all the godowns.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GodownDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Godowns");
        return godownRepository.findAll(pageable)
            .map(godownMapper::toDto);
    }


    /**
     * Get one godown by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<GodownDTO> findOne(Long id) {
        log.debug("Request to get Godown : {}", id);
        return godownRepository.findById(id)
            .map(godownMapper::toDto);
    }

    /**
     * Delete the godown by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Godown : {}", id);
        godownRepository.deleteById(id);
    }

    /**
     * softDelete the Zonal by id
     * 
     * @param id the id of the entity
     */
    @Override
    public void softDelete(Long id) {
        log.debug("Request to delete Zonal : {}", id);
        godownRepository.findById(id);
    }
}
