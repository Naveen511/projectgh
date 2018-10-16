/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/09/16
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs MapZonalWithOhServiceImpl
 *
 *******************************************************************************/
package com.niche.ng.service.impl;

import com.niche.ng.service.MapZonalWithOhService;
import com.niche.ng.domain.MapZonalWithOh;
import com.niche.ng.repository.MapZonalWithOhRepository;
import com.niche.ng.service.dto.MapZonalWithOhDTO;
import com.niche.ng.service.mapper.MapZonalWithOhMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing MapZonalWithOh.
 *
 * Implementing MapZonalWithOhService with IMPL suffix class
 * as MapZonalWithOhServiceImpl.
 * Using of business logic in the service layer which is present in the service file
 * using impl as a interface to access the repository layer.
 * Once we got the responce from the repository layer, mapper convert the entity
 * object to data transfer object(DTO).
 */
@Service
@Transactional
public class MapZonalWithOhServiceImpl implements MapZonalWithOhService {

    private final Logger log = LoggerFactory.getLogger(MapZonalWithOhServiceImpl.class);

    private final MapZonalWithOhRepository mapZonalWithOhRepository;

    private final MapZonalWithOhMapper mapZonalWithOhMapper;

    public MapZonalWithOhServiceImpl(MapZonalWithOhRepository mapZonalWithOhRepository, MapZonalWithOhMapper mapZonalWithOhMapper) {
        this.mapZonalWithOhRepository = mapZonalWithOhRepository;
        this.mapZonalWithOhMapper = mapZonalWithOhMapper;
    }

    /**
     * Save a mapZonalWithOh.
     *
     * @param mapZonalWithOhDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MapZonalWithOhDTO save(MapZonalWithOhDTO mapZonalWithOhDTO) {
        log.debug("Request to save MapZonalWithOh : {}", mapZonalWithOhDTO);
        MapZonalWithOh mapZonalWithOh = mapZonalWithOhMapper.toEntity(mapZonalWithOhDTO);
        mapZonalWithOh = mapZonalWithOhRepository.save(mapZonalWithOh);
        return mapZonalWithOhMapper.toDto(mapZonalWithOh);
    }

    /**
     * Get all the mapZonalWithOhs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MapZonalWithOhDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MapZonalWithOhs");
        return mapZonalWithOhRepository.findAll(pageable)
            .map(mapZonalWithOhMapper::toDto);
    }


    /**
     * Get one mapZonalWithOh by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MapZonalWithOhDTO> findOne(Long id) {
        log.debug("Request to get MapZonalWithOh : {}", id);
        return mapZonalWithOhRepository.findById(id)
            .map(mapZonalWithOhMapper::toDto);
    }

    /**
     * Delete the mapZonalWithOh by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MapZonalWithOh : {}", id);
        mapZonalWithOhRepository.deleteById(id);
    }
}
