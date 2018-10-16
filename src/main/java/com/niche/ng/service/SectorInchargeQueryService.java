/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/10
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs SectorInchargeQueryService
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

import com.niche.ng.domain.SectorIncharge;
import com.niche.ng.domain.*; // for static metamodels
import com.niche.ng.repository.SectorInchargeRepository;
import com.niche.ng.service.dto.SectorInchargeCriteria;

import com.niche.ng.service.dto.SectorInchargeDTO;
import com.niche.ng.service.mapper.SectorInchargeMapper;

/**
 * Service for executing complex queries for SectorIncharge entities in the database.
 * The main input is a {@link SectorInchargeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SectorInchargeDTO} or a {@link Page} of {@link SectorInchargeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SectorInchargeQueryService extends QueryService<SectorIncharge> {

    private final Logger log = LoggerFactory.getLogger(SectorInchargeQueryService.class);

    private final SectorInchargeRepository sectorInchargeRepository;

    private final SectorInchargeMapper sectorInchargeMapper;

    public SectorInchargeQueryService(SectorInchargeRepository sectorInchargeRepository, SectorInchargeMapper sectorInchargeMapper) {
        this.sectorInchargeRepository = sectorInchargeRepository;
        this.sectorInchargeMapper = sectorInchargeMapper;
    }

    /**
     * Return a {@link List} of {@link SectorInchargeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SectorInchargeDTO> findByCriteria(SectorInchargeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SectorIncharge> specification = createSpecification(criteria);
        return sectorInchargeMapper.toDto(sectorInchargeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SectorInchargeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SectorInchargeDTO> findByCriteria(SectorInchargeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<SectorIncharge> specification = createSpecification(criteria);
        return sectorInchargeRepository.findAll(specification, page)
            .map(sectorInchargeMapper::toDto);
    }

    /**
     * Function to convert SectorInchargeCriteria to a {@link Specification}
     */
    private Specification<SectorIncharge> createSpecification(SectorInchargeCriteria criteria) {
        Specification<SectorIncharge> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), SectorIncharge_.id));
            }
            if (criteria.getFromDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFromDate(), SectorIncharge_.fromDate));
            }
            if (criteria.getToDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getToDate(), SectorIncharge_.toDate));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), SectorIncharge_.description));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), SectorIncharge_.status));
            }
            if (criteria.getSectorId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getSectorId(), SectorIncharge_.sector, Sector_.id));
            }
        }
        return specification;
    }

}
