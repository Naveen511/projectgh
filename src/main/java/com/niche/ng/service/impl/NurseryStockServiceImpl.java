/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryStockServiceImplementation
 *
 *******************************************************************************/
package com.niche.ng.service.impl;

import java.util.List;
import com.niche.ng.service.NurseryStockService;
import com.niche.ng.domain.NurseryStock;
import com.niche.ng.repository.NurseryStockRepository;
import com.niche.ng.service.dto.NurseryStockDTO;
import com.niche.ng.service.mapper.NurseryStockMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing NurseryStock.
 *
 * Implementing NurseryStockService with IMPL suffix class as NurseryStockServiceImpl.
 * Using of business logic in the service layer which is present in the service file
 * using impl as a interface to access the repository layer.
 * Once we got the responce from the repository layer, mapper convert the entity
 * object to data transfer object(DTO).
 */
@Service
@Transactional
public class NurseryStockServiceImpl implements NurseryStockService {

    private final Logger log = LoggerFactory.getLogger(NurseryStockServiceImpl.class);

    private final NurseryStockRepository nurseryStockRepository;

    private final NurseryStockMapper nurseryStockMapper;

    public NurseryStockServiceImpl(NurseryStockRepository nurseryStockRepository, NurseryStockMapper nurseryStockMapper) {
        this.nurseryStockRepository = nurseryStockRepository;
        this.nurseryStockMapper = nurseryStockMapper;
    }

    /**
     * Save a nurseryStock.
     *
     * @param nurseryStockDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public NurseryStockDTO save(NurseryStockDTO nurseryStockDTO) {
        log.debug("Request to save NurseryStock : {}", nurseryStockDTO);
        NurseryStock nurseryStock = nurseryStockMapper.toEntity(nurseryStockDTO);
        nurseryStock = nurseryStockRepository.save(nurseryStock);
        return nurseryStockMapper.toDto(nurseryStock);
    }

    /**
     * Get all the nurseryStocks.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<NurseryStockDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NurseryStocks");
        return nurseryStockRepository.findAll(pageable)
            .map(nurseryStockMapper::toDto);
    }


    /**
     * Get one nurseryStock by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NurseryStockDTO> findOne(Long id) {
        log.debug("Request to get NurseryStock : {}", id);
        return nurseryStockRepository.findById(id)
            .map(nurseryStockMapper::toDto);
    }

    /**
     * Delete the nurseryStock by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete NurseryStock : {}", id);
        nurseryStockRepository.deleteById(id);
    }
}
