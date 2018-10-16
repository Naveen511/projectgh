/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs GodownStockServiceImpl
 *
 *******************************************************************************/
package com.niche.ng.service.impl;

import com.niche.ng.service.GodownStockService;
import com.niche.ng.domain.GodownStock;
import com.niche.ng.repository.GodownStockRepository;
import com.niche.ng.service.dto.GodownStockDTO;
import com.niche.ng.service.mapper.GodownStockMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

/**
 * Service Implementation for managing GodownStock.
 *
 * Implementing GodownStockService with IMPL suffix class as GodownStockServiceImpl.
 * Using of business logic in the service layer which is present in the service file
 * using impl as a interface to access the repository layer.
 * Once we got the responce from the repository layer, mapper convert the entity
 * object to data transfer object(DTO).
 */
@Service
@Transactional
public class GodownStockServiceImpl implements GodownStockService {

    private final Logger log = LoggerFactory.getLogger(GodownStockServiceImpl.class);

    private final GodownStockRepository godownStockRepository;

    private final GodownStockMapper godownStockMapper;

    public GodownStockServiceImpl(GodownStockRepository godownStockRepository, GodownStockMapper godownStockMapper) {
        this.godownStockRepository = godownStockRepository;
        this.godownStockMapper = godownStockMapper;
    }

    /**
     * Save a godownStock.
     *
     * @param godownStockDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public GodownStockDTO save(GodownStockDTO godownStockDTO) {
        log.debug("Request to save GodownStock : {}", godownStockDTO);
        GodownStock godownStock = godownStockMapper.toEntity(godownStockDTO);
        godownStock = godownStockRepository.save(godownStock);
        return godownStockMapper.toDto(godownStock);
    }

    /**
     * Get all the godownStocks.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GodownStockDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GodownStocks");
        return godownStockRepository.findAll(pageable)
            .map(godownStockMapper::toDto);
    }


    /**
     * Get one godownStock by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<GodownStockDTO> findOne(Long id) {
        log.debug("Request to get GodownStock : {}", id);
        return godownStockRepository.findById(id)
            .map(godownStockMapper::toDto);
    }

    /**
     * Delete the godownStock by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete GodownStock : {}", id);
        godownStockRepository.deleteById(id);
    }
}
