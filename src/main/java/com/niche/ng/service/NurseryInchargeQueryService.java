/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/07
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryInchargeQueryService
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

import com.niche.ng.domain.NurseryIncharge;
import com.niche.ng.domain.*; // for static metamodels
import com.niche.ng.repository.NurseryInchargeRepository;
import com.niche.ng.service.dto.NurseryInchargeCriteria;

import com.niche.ng.service.dto.NurseryInchargeDTO;
import com.niche.ng.service.mapper.NurseryInchargeMapper;

/**
 * Service for executing complex queries for NurseryIncharge entities in the database.
 * The main input is a {@link NurseryInchargeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link NurseryInchargeDTO} or a {@link Page} of {@link NurseryInchargeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class NurseryInchargeQueryService extends QueryService<NurseryIncharge> {

    private final Logger log = LoggerFactory.getLogger(NurseryInchargeQueryService.class);

    private final NurseryInchargeRepository nurseryInchargeRepository;

    private final NurseryInchargeMapper nurseryInchargeMapper;

    public NurseryInchargeQueryService(NurseryInchargeRepository nurseryInchargeRepository, NurseryInchargeMapper nurseryInchargeMapper) {
        this.nurseryInchargeRepository = nurseryInchargeRepository;
        this.nurseryInchargeMapper = nurseryInchargeMapper;
    }

    /**
     * Return a {@link List} of {@link NurseryInchargeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<NurseryInchargeDTO> findByCriteria(NurseryInchargeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<NurseryIncharge> specification = createSpecification(criteria);
        return nurseryInchargeMapper.toDto(nurseryInchargeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link NurseryInchargeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<NurseryInchargeDTO> findByCriteria(NurseryInchargeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<NurseryIncharge> specification = createSpecification(criteria);
        return nurseryInchargeRepository.findAll(specification, page)
            .map(nurseryInchargeMapper::toDto);
    }

    /**
     * Function to convert NurseryInchargeCriteria to a {@link Specification}
     */
    private Specification<NurseryIncharge> createSpecification(NurseryInchargeCriteria criteria) {
        Specification<NurseryIncharge> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), NurseryIncharge_.id));
            }
            if (criteria.getFromDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFromDate(), NurseryIncharge_.fromDate));
            }
            if (criteria.getToDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getToDate(), NurseryIncharge_.toDate));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), NurseryIncharge_.description));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), NurseryIncharge_.status));
            }
            if (criteria.getNurseryId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getNurseryId(), NurseryIncharge_.nursery, Nursery_.id));
            }
        }
        return specification;
    }

}
