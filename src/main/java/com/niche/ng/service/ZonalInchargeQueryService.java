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

import com.niche.ng.domain.ZonalIncharge;
import com.niche.ng.domain.*; // for static metamodels
import com.niche.ng.repository.ZonalInchargeRepository;
import com.niche.ng.service.dto.ZonalInchargeCriteria;

import com.niche.ng.service.dto.ZonalInchargeDTO;
import com.niche.ng.service.mapper.ZonalInchargeMapper;

/**
 * Service for executing complex queries for ZonalIncharge entities in the database.
 * The main input is a {@link ZonalInchargeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ZonalInchargeDTO} or a {@link Page} of {@link ZonalInchargeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ZonalInchargeQueryService extends QueryService<ZonalIncharge> {

    private final Logger log = LoggerFactory.getLogger(ZonalInchargeQueryService.class);

    private final ZonalInchargeRepository zonalInchargeRepository;

    private final ZonalInchargeMapper zonalInchargeMapper;

    public ZonalInchargeQueryService(ZonalInchargeRepository zonalInchargeRepository, ZonalInchargeMapper zonalInchargeMapper) {
        this.zonalInchargeRepository = zonalInchargeRepository;
        this.zonalInchargeMapper = zonalInchargeMapper;
    }

    /**
     * Return a {@link List} of {@link ZonalInchargeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ZonalInchargeDTO> findByCriteria(ZonalInchargeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ZonalIncharge> specification = createSpecification(criteria);
        return zonalInchargeMapper.toDto(zonalInchargeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ZonalInchargeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ZonalInchargeDTO> findByCriteria(ZonalInchargeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ZonalIncharge> specification = createSpecification(criteria);
        return zonalInchargeRepository.findAll(specification, page)
            .map(zonalInchargeMapper::toDto);
    }

    /**
     * Function to convert ZonalInchargeCriteria to a {@link Specification}
     */
    private Specification<ZonalIncharge> createSpecification(ZonalInchargeCriteria criteria) {
        Specification<ZonalIncharge> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ZonalIncharge_.id));
            }
            if (criteria.getFromDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFromDate(), ZonalIncharge_.fromDate));
            }
            if (criteria.getToDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getToDate(), ZonalIncharge_.toDate));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ZonalIncharge_.description));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), ZonalIncharge_.status));
            }
            if (criteria.getZonalId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getZonalId(), ZonalIncharge_.zonal, Zonal_.id));
            }
        }
        return specification;
    }

}
