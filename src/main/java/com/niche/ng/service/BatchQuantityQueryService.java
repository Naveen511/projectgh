/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/10/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs BatchQuantityQueryService
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

import com.niche.ng.domain.BatchQuantity;
import com.niche.ng.domain.*; // for static metamodels
import com.niche.ng.repository.BatchQuantityRepository;
import com.niche.ng.service.dto.BatchQuantityCriteria;

import com.niche.ng.service.dto.BatchQuantityDTO;
import com.niche.ng.service.mapper.BatchQuantityMapper;

/**
 * Service for executing complex queries for BatchQuantity entities in the database.
 * The main input is a {@link BatchQuantityCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link BatchQuantityDTO} or a {@link Page} of {@link BatchQuantityDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class BatchQuantityQueryService extends QueryService<BatchQuantity> {

    private final Logger log = LoggerFactory.getLogger(BatchQuantityQueryService.class);

    private final BatchQuantityRepository batchQuantityRepository;

    private final BatchQuantityMapper batchQuantityMapper;

    public BatchQuantityQueryService(BatchQuantityRepository batchQuantityRepository, BatchQuantityMapper batchQuantityMapper) {
        this.batchQuantityRepository = batchQuantityRepository;
        this.batchQuantityMapper = batchQuantityMapper;
    }

    /**
     * Return a {@link List} of {@link BatchQuantityDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<BatchQuantityDTO> findByCriteria(BatchQuantityCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<BatchQuantity> specification = createSpecification(criteria);
        return batchQuantityMapper.toDto(batchQuantityRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link BatchQuantityDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<BatchQuantityDTO> findByCriteria(BatchQuantityCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<BatchQuantity> specification = createSpecification(criteria);
        return batchQuantityRepository.findAll(specification, page)
            .map(batchQuantityMapper::toDto);
    }

    /**
     * Function to convert BatchQuantityCriteria to a {@link Specification}
     */
    private Specification<BatchQuantity> createSpecification(BatchQuantityCriteria criteria) {
        Specification<BatchQuantity> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), BatchQuantity_.id));
            }
            if (criteria.getQuantity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQuantity(), BatchQuantity_.quantity));
            }
            if (criteria.getDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDate(), BatchQuantity_.date));
            }
            if (criteria.getRemarks() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRemarks(), BatchQuantity_.remarks));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), BatchQuantity_.status));
            }
            if (criteria.getBatchId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getBatchId(), BatchQuantity_.batch, Batch_.id));
            }
        }
        return specification;
    }

}
