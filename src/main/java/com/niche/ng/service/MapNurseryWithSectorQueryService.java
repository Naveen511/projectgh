/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/09/07
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs MapNurseryWithSectorQueryService
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

import com.niche.ng.domain.MapNurseryWithSector;
import com.niche.ng.domain.*; // for static metamodels
import com.niche.ng.repository.MapNurseryWithSectorRepository;
import com.niche.ng.service.dto.MapNurseryWithSectorCriteria;

import com.niche.ng.service.dto.MapNurseryWithSectorDTO;
import com.niche.ng.service.mapper.MapNurseryWithSectorMapper;

/**
 * Service for executing complex queries for MapNurseryWithSector entities in the database.
 * The main input is a {@link MapNurseryWithSectorCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MapNurseryWithSectorDTO} or a {@link Page} of {@link MapNurseryWithSectorDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MapNurseryWithSectorQueryService extends QueryService<MapNurseryWithSector> {

    private final Logger log = LoggerFactory.getLogger(MapNurseryWithSectorQueryService.class);

    private final MapNurseryWithSectorRepository mapNurseryWithSectorRepository;

    private final MapNurseryWithSectorMapper mapNurseryWithSectorMapper;

    public MapNurseryWithSectorQueryService(MapNurseryWithSectorRepository mapNurseryWithSectorRepository, MapNurseryWithSectorMapper mapNurseryWithSectorMapper) {
        this.mapNurseryWithSectorRepository = mapNurseryWithSectorRepository;
        this.mapNurseryWithSectorMapper = mapNurseryWithSectorMapper;
    }

    /**
     * Return a {@link List} of {@link MapNurseryWithSectorDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MapNurseryWithSectorDTO> findByCriteria(MapNurseryWithSectorCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MapNurseryWithSector> specification = createSpecification(criteria);
        return mapNurseryWithSectorMapper.toDto(mapNurseryWithSectorRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MapNurseryWithSectorDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MapNurseryWithSectorDTO> findByCriteria(MapNurseryWithSectorCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MapNurseryWithSector> specification = createSpecification(criteria);
        return mapNurseryWithSectorRepository.findAll(specification, page)
            .map(mapNurseryWithSectorMapper::toDto);
    }

    /**
     * Function to convert MapNurseryWithSectorCriteria to a {@link Specification}
     */
    private Specification<MapNurseryWithSector> createSpecification(MapNurseryWithSectorCriteria criteria) {
        Specification<MapNurseryWithSector> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MapNurseryWithSector_.id));
            }
            if (criteria.getFromDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFromDate(), MapNurseryWithSector_.fromDate));
            }
            if (criteria.getToDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getToDate(), MapNurseryWithSector_.toDate));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), MapNurseryWithSector_.description));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), MapNurseryWithSector_.status));
            }
            if (criteria.getNurseryId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getNurseryId(), MapNurseryWithSector_.nursery, Nursery_.id));
            }
            if (criteria.getSectorId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getSectorId(), MapNurseryWithSector_.sector, Sector_.id));
            }
        }
        return specification;
    }

}
