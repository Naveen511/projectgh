/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs SectorServiceImpl
 *
 *******************************************************************************/
package com.niche.ng.service.impl;

import com.niche.ng.service.SectorService;
import com.niche.ng.domain.Sector;
import com.niche.ng.repository.SectorRepository;
import com.niche.ng.service.dto.SectorDTO;
import com.niche.ng.service.mapper.SectorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
import java.util.List;
/**
 * Service Implementation for managing Sector.
 */
@Service
@Transactional
public class SectorServiceImpl implements SectorService {

    private final Logger log = LoggerFactory.getLogger(SectorServiceImpl.class);

    private final SectorRepository sectorRepository;

    private final SectorMapper sectorMapper;

    public SectorServiceImpl(SectorRepository sectorRepository, SectorMapper sectorMapper) {
        this.sectorRepository = sectorRepository;
        this.sectorMapper = sectorMapper;
    }

    /**
     * Save a sector.
     *
     * @param sectorDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SectorDTO save(SectorDTO sectorDTO) {
        log.debug("Request to save Sector : {}", sectorDTO);
        Sector sector = sectorMapper.toEntity(sectorDTO);
        sector = sectorRepository.save(sector);
        return sectorMapper.toDto(sector);
    }

    /**
     * Get all the sectors.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SectorDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Sectors");
        return sectorRepository.findAll(pageable)
            .map(sectorMapper::toDto);
    }


    /**
     * Get one sector by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SectorDTO> findOne(Long id) {
        log.debug("Request to get Sector : {}", id);
        return sectorRepository.findById(id)
            .map(sectorMapper::toDto);
    }

    /**
     * Delete the sector by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Sector : {}", id);
        sectorRepository.deleteById(id);
    }
    
    /**
     * Get all the sectors by zonalId.
     *
     * @param zonalId the zonalId of the entity
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SectorDTO> findZonalSectors(Long zonalId) {
        log.debug("Request to get Sectors : {}", zonalId);
        List<Sector> sector = sectorRepository.findByZonalId(zonalId);
        return sectorMapper.toDto(sector);
    }

    @Override
    public Long findActiveCount(Integer status) {
        return sectorRepository.countByStatus(status);
    }
}
