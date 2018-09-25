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

import com.niche.ng.domain.MapZonalWithOh;
import com.niche.ng.domain.*; // for static metamodels
import com.niche.ng.repository.MapZonalWithOhRepository;
import com.niche.ng.service.dto.MapZonalWithOhCriteria;

import com.niche.ng.service.dto.MapZonalWithOhDTO;
import com.niche.ng.service.mapper.MapZonalWithOhMapper;

/**
 * Service for executing complex queries for MapZonalWithOh entities in the database.
 * The main input is a {@link MapZonalWithOhCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MapZonalWithOhDTO} or a {@link Page} of {@link MapZonalWithOhDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MapZonalWithOhQueryService extends QueryService<MapZonalWithOh> {

    private final Logger log = LoggerFactory.getLogger(MapZonalWithOhQueryService.class);

    private final MapZonalWithOhRepository mapZonalWithOhRepository;

    private final MapZonalWithOhMapper mapZonalWithOhMapper;

    public MapZonalWithOhQueryService(MapZonalWithOhRepository mapZonalWithOhRepository, MapZonalWithOhMapper mapZonalWithOhMapper) {
        this.mapZonalWithOhRepository = mapZonalWithOhRepository;
        this.mapZonalWithOhMapper = mapZonalWithOhMapper;
    }

    /**
     * Return a {@link List} of {@link MapZonalWithOhDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MapZonalWithOhDTO> findByCriteria(MapZonalWithOhCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MapZonalWithOh> specification = createSpecification(criteria);
        return mapZonalWithOhMapper.toDto(mapZonalWithOhRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MapZonalWithOhDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MapZonalWithOhDTO> findByCriteria(MapZonalWithOhCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MapZonalWithOh> specification = createSpecification(criteria);
        return mapZonalWithOhRepository.findAll(specification, page)
            .map(mapZonalWithOhMapper::toDto);
    }

    /**
     * Function to convert MapZonalWithOhCriteria to a {@link Specification}
     */
    private Specification<MapZonalWithOh> createSpecification(MapZonalWithOhCriteria criteria) {
        Specification<MapZonalWithOh> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MapZonalWithOh_.id));
            }
            if (criteria.getFromDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFromDate(), MapZonalWithOh_.fromDate));
            }
            if (criteria.getToDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getToDate(), MapZonalWithOh_.toDate));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), MapZonalWithOh_.description));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), MapZonalWithOh_.status));
            }
            if (criteria.getZonalId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getZonalId(), MapZonalWithOh_.zonal, Zonal_.id));
            }
            if (criteria.getOperationalHeadId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getOperationalHeadId(), MapZonalWithOh_.operationalHead, OperationalHead_.id));
            }
        }
        return specification;
    }

}
