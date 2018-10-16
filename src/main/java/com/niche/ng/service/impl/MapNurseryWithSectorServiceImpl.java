/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/09/16
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs MapNurseryWithSectorServiceImpl
 *
 *******************************************************************************/
package com.niche.ng.service.impl;

import com.niche.ng.service.MapNurseryWithSectorService;
import com.niche.ng.domain.MapNurseryWithSector;
import com.niche.ng.repository.MapNurseryWithSectorRepository;
import com.niche.ng.service.dto.MapNurseryWithSectorDTO;
import com.niche.ng.service.mapper.MapNurseryWithSectorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
/**
 * Service Implementation for managing MapNurseryWithSector.
 *
 * Implementing MapNurseryWithSectorService with IMPL suffix class
 * as MapNurseryWithSectorServiceImpl.
 * Using of business logic in the service layer which is present in the service file
 * using impl as a interface to access the repository layer.
 * Once we got the responce from the repository layer, mapper convert the entity
 * object to data transfer object(DTO).
 */
@Service
@Transactional
public class MapNurseryWithSectorServiceImpl implements MapNurseryWithSectorService {

    private final Logger log = LoggerFactory.getLogger(MapNurseryWithSectorServiceImpl.class);

    private final MapNurseryWithSectorRepository mapNurseryWithSectorRepository;

    private final MapNurseryWithSectorMapper mapNurseryWithSectorMapper;

    public MapNurseryWithSectorServiceImpl(MapNurseryWithSectorRepository mapNurseryWithSectorRepository, MapNurseryWithSectorMapper mapNurseryWithSectorMapper) {
        this.mapNurseryWithSectorRepository = mapNurseryWithSectorRepository;
        this.mapNurseryWithSectorMapper = mapNurseryWithSectorMapper;
    }

    /**
     * Save a mapNurseryWithSector.
     *
     * @param mapNurseryWithSectorDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MapNurseryWithSectorDTO save(MapNurseryWithSectorDTO mapNurseryWithSectorDTO) {
        log.debug("Request to save MapNurseryWithSector : {}", mapNurseryWithSectorDTO);
        MapNurseryWithSector mapNurseryWithSector = mapNurseryWithSectorMapper.toEntity(mapNurseryWithSectorDTO);
        mapNurseryWithSector = mapNurseryWithSectorRepository.save(mapNurseryWithSector);
        return mapNurseryWithSectorMapper.toDto(mapNurseryWithSector);
    }

    /**
     * Get all the mapNurseryWithSectors.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MapNurseryWithSectorDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MapNurseryWithSectors");
        return mapNurseryWithSectorRepository.findAll(pageable)
            .map(mapNurseryWithSectorMapper::toDto);
    }


    /**
     * Get one mapNurseryWithSector by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MapNurseryWithSectorDTO> findOne(Long id) {
        log.debug("Request to get MapNurseryWithSector : {}", id);
        return mapNurseryWithSectorRepository.findById(id)
            .map(mapNurseryWithSectorMapper::toDto);
    }

    /**
     * Delete the mapNurseryWithSector by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MapNurseryWithSector : {}", id);
        mapNurseryWithSectorRepository.deleteById(id);
    }
}
