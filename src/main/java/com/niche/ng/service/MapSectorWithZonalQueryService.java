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

import com.niche.ng.domain.MapSectorWithZonal;
import com.niche.ng.domain.*; // for static metamodels
import com.niche.ng.repository.MapSectorWithZonalRepository;
import com.niche.ng.service.dto.MapSectorWithZonalCriteria;

import com.niche.ng.service.dto.MapSectorWithZonalDTO;
import com.niche.ng.service.mapper.MapSectorWithZonalMapper;

/**
 * Service for executing complex queries for MapSectorWithZonal entities in the database.
 * The main input is a {@link MapSectorWithZonalCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MapSectorWithZonalDTO} or a {@link Page} of {@link MapSectorWithZonalDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MapSectorWithZonalQueryService extends QueryService<MapSectorWithZonal> {

    private final Logger log = LoggerFactory.getLogger(MapSectorWithZonalQueryService.class);

    private final MapSectorWithZonalRepository mapSectorWithZonalRepository;

    private final MapSectorWithZonalMapper mapSectorWithZonalMapper;

    public MapSectorWithZonalQueryService(MapSectorWithZonalRepository mapSectorWithZonalRepository, MapSectorWithZonalMapper mapSectorWithZonalMapper) {
        this.mapSectorWithZonalRepository = mapSectorWithZonalRepository;
        this.mapSectorWithZonalMapper = mapSectorWithZonalMapper;
    }

    /**
     * Return a {@link List} of {@link MapSectorWithZonalDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MapSectorWithZonalDTO> findByCriteria(MapSectorWithZonalCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MapSectorWithZonal> specification = createSpecification(criteria);
        return mapSectorWithZonalMapper.toDto(mapSectorWithZonalRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MapSectorWithZonalDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MapSectorWithZonalDTO> findByCriteria(MapSectorWithZonalCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MapSectorWithZonal> specification = createSpecification(criteria);
        return mapSectorWithZonalRepository.findAll(specification, page)
            .map(mapSectorWithZonalMapper::toDto);
    }

    /**
     * Function to convert MapSectorWithZonalCriteria to a {@link Specification}
     */
    private Specification<MapSectorWithZonal> createSpecification(MapSectorWithZonalCriteria criteria) {
        Specification<MapSectorWithZonal> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MapSectorWithZonal_.id));
            }
            if (criteria.getFromDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFromDate(), MapSectorWithZonal_.fromDate));
            }
            if (criteria.getToDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getToDate(), MapSectorWithZonal_.toDate));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), MapSectorWithZonal_.description));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), MapSectorWithZonal_.status));
            }
            if (criteria.getZonalId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getZonalId(), MapSectorWithZonal_.zonal, Zonal_.id));
            }
            if (criteria.getSectorId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getSectorId(), MapSectorWithZonal_.sector, Sector_.id));
            }
        }
        return specification;
    }

}
