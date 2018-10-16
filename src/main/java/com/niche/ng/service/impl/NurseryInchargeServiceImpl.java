/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/16
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryInchargeServiceImpl
 *
 *******************************************************************************/
package com.niche.ng.service.impl;

import com.niche.ng.service.NurseryInchargeService;
import com.niche.ng.domain.NurseryIncharge;
import com.niche.ng.repository.NurseryInchargeRepository;
import com.niche.ng.service.dto.NurseryInchargeDTO;
import com.niche.ng.service.mapper.NurseryInchargeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing NurseryIncharge.
 *
 * Implementing NurseryInchargeService with IMPL suffix class
 * as NurseryInchargeServiceImpl.
 * Using of business logic in the service layer which is present in the service file
 * using impl as a interface to access the repository layer.
 * Once we got the responce from the repository layer, mapper convert the entity
 * object to data transfer object(DTO).
 */
@Service
@Transactional
public class NurseryInchargeServiceImpl implements NurseryInchargeService {

    private final Logger log = LoggerFactory.getLogger(NurseryInchargeServiceImpl.class);

    private final NurseryInchargeRepository nurseryInchargeRepository;

    private final NurseryInchargeMapper nurseryInchargeMapper;

    public NurseryInchargeServiceImpl(NurseryInchargeRepository nurseryInchargeRepository, NurseryInchargeMapper nurseryInchargeMapper) {
        this.nurseryInchargeRepository = nurseryInchargeRepository;
        this.nurseryInchargeMapper = nurseryInchargeMapper;
    }

    /**
     * Save a nurseryIncharge.
     *
     * @param nurseryInchargeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public NurseryInchargeDTO save(NurseryInchargeDTO nurseryInchargeDTO) {
        log.debug("Request to save NurseryIncharge : {}", nurseryInchargeDTO);
        NurseryIncharge nurseryIncharge = nurseryInchargeMapper.toEntity(nurseryInchargeDTO);
        nurseryIncharge = nurseryInchargeRepository.save(nurseryIncharge);
        return nurseryInchargeMapper.toDto(nurseryIncharge);
    }

    /**
     * Get all the nurseryIncharges.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<NurseryInchargeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NurseryIncharges");
        return nurseryInchargeRepository.findAll(pageable)
            .map(nurseryInchargeMapper::toDto);
    }


    /**
     * Get one nurseryIncharge by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NurseryInchargeDTO> findOne(Long id) {
        log.debug("Request to get NurseryIncharge : {}", id);
        return nurseryInchargeRepository.findById(id)
            .map(nurseryInchargeMapper::toDto);
    }

    /**
     * Delete the nurseryIncharge by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete NurseryIncharge : {}", id);
        nurseryInchargeRepository.deleteById(id);
    }
}
