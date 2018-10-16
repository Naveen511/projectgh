/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/25
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryInventoryServiceImpl
 *
 *******************************************************************************/
package com.niche.ng.service.impl;

import com.niche.ng.service.NurseryInventoryService;
import com.niche.ng.domain.NurseryInventory;
import com.niche.ng.repository.NurseryInventoryRepository;
import com.niche.ng.service.dto.NurseryInventoryDTO;
import com.niche.ng.service.mapper.NurseryInventoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import java.util.Optional;
/**
 * Service Implementation for managing NurseryInventory.
 */
@Service
@Transactional
public class NurseryInventoryServiceImpl implements NurseryInventoryService {

    private final Logger log = LoggerFactory.getLogger(NurseryInventoryServiceImpl.class);

    private final NurseryInventoryRepository nurseryInventoryRepository;

    private final NurseryInventoryMapper nurseryInventoryMapper;

    public NurseryInventoryServiceImpl(NurseryInventoryRepository nurseryInventoryRepository, NurseryInventoryMapper nurseryInventoryMapper) {
        this.nurseryInventoryRepository = nurseryInventoryRepository;
        this.nurseryInventoryMapper = nurseryInventoryMapper;
    }

    /**
     * Save a nurseryInventory.
     *
     * @param nurseryInventoryDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public NurseryInventoryDTO save(NurseryInventoryDTO nurseryInventoryDTO) {
        log.debug("Request to save NurseryInventory : {}", nurseryInventoryDTO);
        NurseryInventory nurseryInventory = nurseryInventoryMapper.toEntity(nurseryInventoryDTO);
        nurseryInventory = nurseryInventoryRepository.save(nurseryInventory);
        return nurseryInventoryMapper.toDto(nurseryInventory);
    }

    /**
     * Get all the nurseryInventories.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<NurseryInventoryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NurseryInventories");
        return nurseryInventoryRepository.findAll(pageable)
            .map(nurseryInventoryMapper::toDto);
    }


    /**
     * Get one nurseryInventory by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NurseryInventoryDTO> findOne(Long id) {
        log.debug("Request to get NurseryInventory : {}", id);
        return nurseryInventoryRepository.findById(id)
            .map(nurseryInventoryMapper::toDto);
    }

    /**
     * Delete the nurseryInventory by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete NurseryInventory : {}", id);
        nurseryInventoryRepository.deleteById(id);
    }

    /**
     * Get all the nursery stock by nurseryId, pickListCategoryId.
     *
     * @param nurseryId the nurseryId of the entity
     * @param pickListCategoryId the pickListCategoryId of the entity
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<NurseryInventoryDTO> findInventory(Long nurserysId, Long pickListCategoryId) {
        log.debug("Request to get Nursery Stock : {}", nurserysId +", "+ pickListCategoryId);
        // List<NurseryStock> stock = nurseryStockRepository.findByNurseryIdAndPickListVarietyIdAndPickListCategoryIdIn(nurseryId, pickListVarietyId, pickListCategoryId);
        // List<NurseryStock> stock = nurseryStockRepository.findByNurseryIdAndPickListVarietyIdInAndPickListCategoryIdIn(nurseryId, pickListVarietyId, pickListCategoryId);
        List<NurseryInventory> inventory = nurseryInventoryRepository.findByNurserysIdAndPickListCategoryId(nurserysId, pickListCategoryId);
        // List<NurseryStock> stock = nurseryStockRepository.findByNurseryIdAndPickListVarietyIdIn(nurseryId, pickListVarietyId);
        return nurseryInventoryMapper.toDto(inventory);
    }

    /**
     * Get all the nursery stock by nurseryId, pickListCategoryId.
     *
     * @param nurseryId the nurseryId of the entity
     * @param status the status of the entity
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<NurseryInventoryDTO> findCoverInventory(Long nurserysId, Integer status) {
        log.debug("Request to get Nursery Cover : {}", nurserysId);
        List<NurseryInventory> inventory = nurseryInventoryRepository.findByNurserysIdAndStatus(nurserysId, status);
        return nurseryInventoryMapper.toDto(inventory);
    }

    /**
     * Get all the damage by status.
     *
     * @param status the status of the entity
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<NurseryInventoryDTO> findParticularStatus(Integer status) {
        log.debug("Request to get seeds : {}", status);
        List<NurseryInventory> inventory = nurseryInventoryRepository.findByStatus(status);
        return nurseryInventoryMapper.toDto(inventory);
    }
}
