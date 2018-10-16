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

import com.niche.ng.domain.ShadeArea;
import com.niche.ng.domain.*; // for static metamodels
import com.niche.ng.repository.ShadeAreaRepository;
import com.niche.ng.service.dto.ShadeAreaCriteria;

import com.niche.ng.service.dto.ShadeAreaDTO;
import com.niche.ng.service.mapper.ShadeAreaMapper;

/**
 * Service for executing complex queries for ShadeArea entities in the database.
 * The main input is a {@link ShadeAreaCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ShadeAreaDTO} or a {@link Page} of {@link ShadeAreaDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ShadeAreaQueryService extends QueryService<ShadeArea> {

    private final Logger log = LoggerFactory.getLogger(ShadeAreaQueryService.class);

    private final ShadeAreaRepository shadeAreaRepository;

    private final ShadeAreaMapper shadeAreaMapper;

    public ShadeAreaQueryService(ShadeAreaRepository shadeAreaRepository, ShadeAreaMapper shadeAreaMapper) {
        this.shadeAreaRepository = shadeAreaRepository;
        this.shadeAreaMapper = shadeAreaMapper;
    }

    /**
     * Return a {@link List} of {@link ShadeAreaDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ShadeAreaDTO> findByCriteria(ShadeAreaCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ShadeArea> specification = createSpecification(criteria);
        return shadeAreaMapper.toDto(shadeAreaRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ShadeAreaDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ShadeAreaDTO> findByCriteria(ShadeAreaCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ShadeArea> specification = createSpecification(criteria);
        return shadeAreaRepository.findAll(specification, page)
            .map(shadeAreaMapper::toDto);
    }

    /**
     * Function to convert ShadeAreaCriteria to a {@link Specification}
     */
    private Specification<ShadeArea> createSpecification(ShadeAreaCriteria criteria) {
        Specification<ShadeArea> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ShadeArea_.id));
            }
            if (criteria.getNoOfSeedlings() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNoOfSeedlings(), ShadeArea_.noOfSeedlings));
            }
            if (criteria.getDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDate(), ShadeArea_.date));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), ShadeArea_.status));
            }
            if (criteria.getDamage() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDamage(), ShadeArea_.damage));
            }
            if (criteria.getSaplings() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSaplings(), ShadeArea_.saplings));
            }
            if (criteria.getBatchId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getBatchId(), ShadeArea_.batch, Batch_.id));
            }
            if (criteria.getFinancialYearShadeAreaId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getFinancialYearShadeAreaId(), ShadeArea_.financialYearShadeArea, FinancialYearSettings_.id));
            }
        }
        return specification;
    }

}
