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

import com.niche.ng.domain.Zonal;
import com.niche.ng.domain.*; // for static metamodels
import com.niche.ng.repository.ZonalRepository;
import com.niche.ng.service.dto.ZonalCriteria;

import com.niche.ng.service.dto.ZonalDTO;
import com.niche.ng.service.mapper.ZonalMapper;

/**
 * Service for executing complex queries for Zonal entities in the database.
 * The main input is a {@link ZonalCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ZonalDTO} or a {@link Page} of {@link ZonalDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ZonalQueryService extends QueryService<Zonal> {

    private final Logger log = LoggerFactory.getLogger(ZonalQueryService.class);

    private final ZonalRepository zonalRepository;

    private final ZonalMapper zonalMapper;

    public ZonalQueryService(ZonalRepository zonalRepository, ZonalMapper zonalMapper) {
        this.zonalRepository = zonalRepository;
        this.zonalMapper = zonalMapper;
    }

    /**
     * Return a {@link List} of {@link ZonalDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ZonalDTO> findByCriteria(ZonalCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Zonal> specification = createSpecification(criteria);
        return zonalMapper.toDto(zonalRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ZonalDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ZonalDTO> findByCriteria(ZonalCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Zonal> specification = createSpecification(criteria);
        return zonalRepository.findAll(specification, page)
            .map(zonalMapper::toDto);
    }

    /**
     * Function to convert ZonalCriteria to a {@link Specification}
     */
    private Specification<Zonal> createSpecification(ZonalCriteria criteria) {
        Specification<Zonal> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Zonal_.id));
            }
            if (criteria.getZoneName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getZoneName(), Zonal_.zoneName));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), Zonal_.status));
            }
            if (criteria.getSectorsId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getSectorsId(), Zonal_.sectors, Sector_.id));
            }
            if (criteria.getFinancialYearId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getFinancialYearId(), Zonal_.financialYear, FinancialYearSettings_.id));
            }
            if (criteria.getOperationalHeadId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getOperationalHeadId(), Zonal_.operationalHead, OperationalHead_.id));
            }
            if (criteria.getMapZonalWithOhId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getMapZonalWithOhId(), Zonal_.mapZonalWithOhs, MapZonalWithOh_.id));
            }
            if (criteria.getZonalInchargeId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getZonalInchargeId(), Zonal_.zonalIncharges, ZonalIncharge_.id));
            }
            if (criteria.getMapSectorWithZonalId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getMapSectorWithZonalId(), Zonal_.mapSectorWithZonals, MapSectorWithZonal_.id));
            }
        }
        return specification;
    }

}
