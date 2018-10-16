/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryInventoryQueryService
 *
 *******************************************************************************/
package com.niche.ng.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.niche.ng.domain.NurseryInventory;
import com.niche.ng.domain.*; // for static metamodels
import com.niche.ng.repository.NurseryInventoryRepository;
import com.niche.ng.service.dto.NurseryInventoryCriteria;

import com.niche.ng.service.dto.NurseryInventoryDTO;
import com.niche.ng.service.mapper.NurseryInventoryMapper;

/**
 * Service for executing complex queries for NurseryInventory entities in the database.
 * The main input is a {@link NurseryInventoryCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link NurseryInventoryDTO} or a {@link Page} of {@link NurseryInventoryDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class NurseryInventoryQueryService extends QueryService<NurseryInventory> {

    private final Logger log = LoggerFactory.getLogger(NurseryInventoryQueryService.class);

    private final NurseryInventoryRepository nurseryInventoryRepository;

    private final NurseryInventoryMapper nurseryInventoryMapper;

    public NurseryInventoryQueryService(NurseryInventoryRepository nurseryInventoryRepository, NurseryInventoryMapper nurseryInventoryMapper) {
        this.nurseryInventoryRepository = nurseryInventoryRepository;
        this.nurseryInventoryMapper = nurseryInventoryMapper;
    }

    /**
     * Return a {@link List} of {@link NurseryInventoryDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<NurseryInventoryDTO> findByCriteria(NurseryInventoryCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<NurseryInventory> specification = createSpecification(criteria);
        return nurseryInventoryMapper.toDto(nurseryInventoryRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link NurseryInventoryDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<NurseryInventoryDTO> findByCriteria(NurseryInventoryCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<NurseryInventory> specification = createSpecification(criteria);
        return nurseryInventoryRepository.findAll(specification, page)
            .map(nurseryInventoryMapper::toDto);
    }

    /**
     * Function to convert NurseryInventoryCriteria to a {@link Specification}
     */
    private Specification<NurseryInventory> createSpecification(NurseryInventoryCriteria criteria) {
        Specification<NurseryInventory> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), NurseryInventory_.id));
            }
            if (criteria.getCurrentQuantity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCurrentQuantity(), NurseryInventory_.currentQuantity));
            }
            if (criteria.getAddedQuantity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAddedQuantity(), NurseryInventory_.addedQuantity));
            }
            if (criteria.getConsumedQuantity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConsumedQuantity(), NurseryInventory_.consumedQuantity));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), NurseryInventory_.description));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), NurseryInventory_.status));
            }
            if (criteria.getDamageQuantity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDamageQuantity(), NurseryInventory_.damageQuantity));
            }
            if (criteria.getNurserysId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getNurserysId(), NurseryInventory_.nurserys, Nursery_.id));
            }
            if (criteria.getPickListVarietyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getPickListVarietyId(), NurseryInventory_.pickListVariety, PickListValue_.id));
            }
            if (criteria.getPickListCategoryId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getPickListCategoryId(), NurseryInventory_.pickListCategory, PickListValue_.id));
            }
            if (criteria.getNurseryInventoryDetailsId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getNurseryInventoryDetailsId(), NurseryInventory_.nurseryInventoryDetails, NurseryInventoryDetails_.id));
            }
            if (criteria.getQuantityTypeId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getQuantityTypeId(), NurseryInventory_.quantityType, PickListValue_.id));
            }
        }
        return specification;
    }

}
