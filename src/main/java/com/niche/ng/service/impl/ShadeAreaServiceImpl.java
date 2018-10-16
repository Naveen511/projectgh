/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs ShadeAreaServiceImpl
 *
 *******************************************************************************/
package com.niche.ng.service.impl;

import com.niche.ng.service.ShadeAreaService;
import com.niche.ng.domain.ShadeArea;
import com.niche.ng.repository.ShadeAreaRepository;
import com.niche.ng.service.dto.ShadeAreaDTO;
import com.niche.ng.service.mapper.ShadeAreaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
import java.util.List;
/**
 * Service Implementation for managing ShadeArea.
 */
@Service
@Transactional
public class ShadeAreaServiceImpl implements ShadeAreaService {

    private final Logger log = LoggerFactory.getLogger(ShadeAreaServiceImpl.class);

    private final ShadeAreaRepository shadeAreaRepository;

    private final ShadeAreaMapper shadeAreaMapper;

    public ShadeAreaServiceImpl(ShadeAreaRepository shadeAreaRepository, ShadeAreaMapper shadeAreaMapper) {
        this.shadeAreaRepository = shadeAreaRepository;
        this.shadeAreaMapper = shadeAreaMapper;
    }

    /**
     * Save a shadeArea.
     *
     * @param shadeAreaDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ShadeAreaDTO save(ShadeAreaDTO shadeAreaDTO) {
        log.debug("Request to save ShadeArea : {}", shadeAreaDTO);
        ShadeArea shadeArea = shadeAreaMapper.toEntity(shadeAreaDTO);
        shadeArea = shadeAreaRepository.save(shadeArea);
        return shadeAreaMapper.toDto(shadeArea);
    }

    /**
     * Get all the shadeAreas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ShadeAreaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ShadeAreas");
        return shadeAreaRepository.findAll(pageable)
            .map(shadeAreaMapper::toDto);
    }


    /**
     * Get one shadeArea by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ShadeAreaDTO> findOne(Long id) {
        // log.debug("Request to get ShadeArea : {}", id);
        return shadeAreaRepository.findById(id)
            .map(shadeAreaMapper::toDto);
    }

    /**
     * Delete the shadeArea by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        /// log.debug("Request to delete ShadeArea : {}", id);
        shadeAreaRepository.deleteById(id);
    }

    /**
     * Get all the shadeArea by batchId.
     *
     * @param batchId the batchId of the entity
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ShadeAreaDTO> findParticularBatch(Long batchId) {
        // log.debug("Request to get shade : {}", batchId);
        List<ShadeArea> shade = shadeAreaRepository.findByBatchId(batchId);
        return shadeAreaMapper.toDto(shade);
    }

    /**
     * Get the count of shade area of particular batch
     *
     * @param batchId the batchId of the entity
     * @return string value of shade area count
     */
    public String getShadeAreaCount(Long batchId) {
        return shadeAreaRepository.getCountByBatchId(batchId);
    }
}
