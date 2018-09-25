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

import com.niche.ng.domain.MotherBed;
import com.niche.ng.domain.*; // for static metamodels
import com.niche.ng.repository.MotherBedRepository;
import com.niche.ng.service.dto.MotherBedCriteria;

import com.niche.ng.service.dto.MotherBedDTO;
import com.niche.ng.service.mapper.MotherBedMapper;

/**
 * Service for executing complex queries for MotherBed entities in the database.
 * The main input is a {@link MotherBedCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MotherBedDTO} or a {@link Page} of {@link MotherBedDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MotherBedQueryService extends QueryService<MotherBed> {

    private final Logger log = LoggerFactory.getLogger(MotherBedQueryService.class);

    private final MotherBedRepository motherBedRepository;

    private final MotherBedMapper motherBedMapper;

    public MotherBedQueryService(MotherBedRepository motherBedRepository, MotherBedMapper motherBedMapper) {
        this.motherBedRepository = motherBedRepository;
        this.motherBedMapper = motherBedMapper;
    }

    /**
     * Return a {@link List} of {@link MotherBedDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MotherBedDTO> findByCriteria(MotherBedCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MotherBed> specification = createSpecification(criteria);
        return motherBedMapper.toDto(motherBedRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MotherBedDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MotherBedDTO> findByCriteria(MotherBedCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MotherBed> specification = createSpecification(criteria);
        return motherBedRepository.findAll(specification, page)
            .map(motherBedMapper::toDto);
    }

    /**
     * Function to convert MotherBedCriteria to a {@link Specification}
     */
    private Specification<MotherBed> createSpecification(MotherBedCriteria criteria) {
        Specification<MotherBed> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MotherBed_.id));
            }
            if (criteria.getValue() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValue(), MotherBed_.value));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), MotherBed_.status));
            }
            if (criteria.getNurseryId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getNurseryId(), MotherBed_.nursery, Nursery_.id));
            }
            if (criteria.getBatchMotherBedId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getBatchMotherBedId(), MotherBed_.batchMotherBeds, Batch_.id));
            }
        }
        return specification;
    }

}
