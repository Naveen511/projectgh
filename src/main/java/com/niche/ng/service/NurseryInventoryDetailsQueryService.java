/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryINventoryDetailsService
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

import com.niche.ng.domain.NurseryInventoryDetails;
import com.niche.ng.domain.*; // for static metamodels
import com.niche.ng.repository.NurseryInventoryDetailsRepository;
import com.niche.ng.service.dto.NurseryInventoryDetailsCriteria;

import com.niche.ng.service.dto.NurseryInventoryDetailsDTO;
import com.niche.ng.service.mapper.NurseryInventoryDetailsMapper;

/**
 * Service for executing complex queries for NurseryInventoryDetails entities in the database.
 * The main input is a {@link NurseryInventoryDetailsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link NurseryInventoryDetailsDTO} or a {@link Page} of {@link NurseryInventoryDetailsDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class NurseryInventoryDetailsQueryService extends QueryService<NurseryInventoryDetails> {

    private final Logger log = LoggerFactory.getLogger(NurseryInventoryDetailsQueryService.class);

    private final NurseryInventoryDetailsRepository nurseryInventoryDetailsRepository;

    private final NurseryInventoryDetailsMapper nurseryInventoryDetailsMapper;

    public NurseryInventoryDetailsQueryService(NurseryInventoryDetailsRepository nurseryInventoryDetailsRepository, NurseryInventoryDetailsMapper nurseryInventoryDetailsMapper) {
        this.nurseryInventoryDetailsRepository = nurseryInventoryDetailsRepository;
        this.nurseryInventoryDetailsMapper = nurseryInventoryDetailsMapper;
    }

    /**
     * Return a {@link List} of {@link NurseryInventoryDetailsDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<NurseryInventoryDetailsDTO> findByCriteria(NurseryInventoryDetailsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<NurseryInventoryDetails> specification = createSpecification(criteria);
        return nurseryInventoryDetailsMapper.toDto(nurseryInventoryDetailsRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link NurseryInventoryDetailsDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<NurseryInventoryDetailsDTO> findByCriteria(NurseryInventoryDetailsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<NurseryInventoryDetails> specification = createSpecification(criteria);
        return nurseryInventoryDetailsRepository.findAll(specification, page)
            .map(nurseryInventoryDetailsMapper::toDto);
    }

    /**
     * Function to convert NurseryInventoryDetailsCriteria to a {@link Specification}
     */
    private Specification<NurseryInventoryDetails> createSpecification(NurseryInventoryDetailsCriteria criteria) {
        Specification<NurseryInventoryDetails> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), NurseryInventoryDetails_.id));
            }
            if (criteria.getDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDate(), NurseryInventoryDetails_.date));
            }
            if (criteria.getQuantity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQuantity(), NurseryInventoryDetails_.quantity));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), NurseryInventoryDetails_.status));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), NurseryInventoryDetails_.description));
            }
            if (criteria.getNurseryInventoryId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getNurseryInventoryId(), NurseryInventoryDetails_.nurseryInventory, NurseryInventory_.id));
            }
            if (criteria.getDamageTypeId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getDamageTypeId(), NurseryInventoryDetails_.damageType, PickListValue_.id));
            }
        }
        return specification;
    }

}
