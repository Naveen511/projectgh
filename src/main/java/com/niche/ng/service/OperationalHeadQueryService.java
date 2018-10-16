/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/09/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs OperationalHeadQueryService
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

import com.niche.ng.domain.OperationalHead;
import com.niche.ng.domain.*; // for static metamodels
import com.niche.ng.repository.OperationalHeadRepository;
import com.niche.ng.service.dto.OperationalHeadCriteria;

import com.niche.ng.service.dto.OperationalHeadDTO;
import com.niche.ng.service.mapper.OperationalHeadMapper;

/**
 * Service for executing complex queries for OperationalHead entities in the database.
 * The main input is a {@link OperationalHeadCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link OperationalHeadDTO} or a {@link Page} of {@link OperationalHeadDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class OperationalHeadQueryService extends QueryService<OperationalHead> {

    private final Logger log = LoggerFactory.getLogger(OperationalHeadQueryService.class);

    private final OperationalHeadRepository operationalHeadRepository;

    private final OperationalHeadMapper operationalHeadMapper;

    public OperationalHeadQueryService(OperationalHeadRepository operationalHeadRepository, OperationalHeadMapper operationalHeadMapper) {
        this.operationalHeadRepository = operationalHeadRepository;
        this.operationalHeadMapper = operationalHeadMapper;
    }

    /**
     * Return a {@link List} of {@link OperationalHeadDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<OperationalHeadDTO> findByCriteria(OperationalHeadCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<OperationalHead> specification = createSpecification(criteria);
        return operationalHeadMapper.toDto(operationalHeadRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link OperationalHeadDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<OperationalHeadDTO> findByCriteria(OperationalHeadCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<OperationalHead> specification = createSpecification(criteria);
        return operationalHeadRepository.findAll(specification, page)
            .map(operationalHeadMapper::toDto);
    }

    /**
     * Function to convert OperationalHeadCriteria to a {@link Specification}
     */
    private Specification<OperationalHead> createSpecification(OperationalHeadCriteria criteria) {
        Specification<OperationalHead> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), OperationalHead_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), OperationalHead_.name));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), OperationalHead_.description));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), OperationalHead_.status));
            }
            if (criteria.getZonalId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getZonalId(), OperationalHead_.zonals, Zonal_.id));
            }
            if (criteria.getMapZonalWithOhId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getMapZonalWithOhId(), OperationalHead_.mapZonalWithOhs, MapZonalWithOh_.id));
            }
        }
        return specification;
    }

}
