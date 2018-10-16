/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/16
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs SectorInchargeServiceImpl
 *
 *******************************************************************************/
package com.niche.ng.service.impl;

import com.niche.ng.service.SectorInchargeService;
import com.niche.ng.domain.SectorIncharge;
import com.niche.ng.repository.SectorInchargeRepository;
import com.niche.ng.service.dto.SectorInchargeDTO;
import com.niche.ng.service.mapper.SectorInchargeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing SectorIncharge.
 *
 * Implementing SectorInchargeService with IMPL suffix class
 * as SectorInchargeServiceImpl.
 * Using of business logic in the service layer which is present in the service file
 * using impl as a interface to access the repository layer.
 * Once we got the responce from the repository layer, mapper convert the entity
 * object to data transfer object(DTO).
 */
@Service
@Transactional
public class SectorInchargeServiceImpl implements SectorInchargeService {

    private final Logger log = LoggerFactory.getLogger(SectorInchargeServiceImpl.class);

    private final SectorInchargeRepository sectorInchargeRepository;

    private final SectorInchargeMapper sectorInchargeMapper;

    public SectorInchargeServiceImpl(SectorInchargeRepository sectorInchargeRepository, SectorInchargeMapper sectorInchargeMapper) {
        this.sectorInchargeRepository = sectorInchargeRepository;
        this.sectorInchargeMapper = sectorInchargeMapper;
    }

    /**
     * Save a sectorIncharge.
     *
     * @param sectorInchargeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SectorInchargeDTO save(SectorInchargeDTO sectorInchargeDTO) {
        log.debug("Request to save SectorIncharge : {}", sectorInchargeDTO);
        SectorIncharge sectorIncharge = sectorInchargeMapper.toEntity(sectorInchargeDTO);
        sectorIncharge = sectorInchargeRepository.save(sectorIncharge);
        return sectorInchargeMapper.toDto(sectorIncharge);
    }

    /**
     * Get all the sectorIncharges.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SectorInchargeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SectorIncharges");
        return sectorInchargeRepository.findAll(pageable)
            .map(sectorInchargeMapper::toDto);
    }


    /**
     * Get one sectorIncharge by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SectorInchargeDTO> findOne(Long id) {
        log.debug("Request to get SectorIncharge : {}", id);
        return sectorInchargeRepository.findById(id)
            .map(sectorInchargeMapper::toDto);
    }

    /**
     * Delete the sectorIncharge by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SectorIncharge : {}", id);
        sectorInchargeRepository.deleteById(id);
    }
}
