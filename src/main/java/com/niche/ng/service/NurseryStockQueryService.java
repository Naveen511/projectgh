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

import com.niche.ng.domain.NurseryStock;
import com.niche.ng.domain.*; // for static metamodels
import com.niche.ng.repository.NurseryStockRepository;
import com.niche.ng.service.dto.NurseryStockCriteria;

import com.niche.ng.service.dto.NurseryStockDTO;
import com.niche.ng.service.mapper.NurseryStockMapper;

/**
 * Service for executing complex queries for NurseryStock entities in the database.
 * The main input is a {@link NurseryStockCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link NurseryStockDTO} or a {@link Page} of {@link NurseryStockDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class NurseryStockQueryService extends QueryService<NurseryStock> {

    private final Logger log = LoggerFactory.getLogger(NurseryStockQueryService.class);

    private final NurseryStockRepository nurseryStockRepository;

    private final NurseryStockMapper nurseryStockMapper;

    public NurseryStockQueryService(NurseryStockRepository nurseryStockRepository, NurseryStockMapper nurseryStockMapper) {
        this.nurseryStockRepository = nurseryStockRepository;
        this.nurseryStockMapper = nurseryStockMapper;
    }

    /**
     * Return a {@link List} of {@link NurseryStockDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<NurseryStockDTO> findByCriteria(NurseryStockCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<NurseryStock> specification = createSpecification(criteria);
        return nurseryStockMapper.toDto(nurseryStockRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link NurseryStockDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<NurseryStockDTO> findByCriteria(NurseryStockCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<NurseryStock> specification = createSpecification(criteria);
        return nurseryStockRepository.findAll(specification, page)
            .map(nurseryStockMapper::toDto);
    }

    /**
     * Function to convert NurseryStockCriteria to a {@link Specification}
     */
    private Specification<NurseryStock> createSpecification(NurseryStockCriteria criteria) {
        Specification<NurseryStock> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), NurseryStock_.id));
            }
            if (criteria.getCurrentQuantity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCurrentQuantity(), NurseryStock_.currentQuantity));
            }
            if (criteria.getAddedQuantity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAddedQuantity(), NurseryStock_.addedQuantity));
            }
            if (criteria.getConsumedQuantity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConsumedQuantity(), NurseryStock_.consumedQuantity));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), NurseryStock_.description));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), NurseryStock_.status));
            }
            if (criteria.getPosQuantity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPosQuantity(), NurseryStock_.posQuantity));
            }
            if (criteria.getNurseryStockDetailsId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getNurseryStockDetailsId(), NurseryStock_.nurseryStockDetails, NurseryStockDetails_.id));
            }
            if (criteria.getNurseryId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getNurseryId(), NurseryStock_.nursery, Nursery_.id));
            }
            if (criteria.getPickListVarietyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getPickListVarietyId(), NurseryStock_.pickListVariety, PickListValue_.id));
            }
            if (criteria.getPickListCategoryId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getPickListCategoryId(), NurseryStock_.pickListCategory, PickListValue_.id));
            }
            if (criteria.getFinancialYearNurseryStockId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getFinancialYearNurseryStockId(), NurseryStock_.financialYearNurseryStock, FinancialYearSettings_.id));
            }
            if (criteria.getPointOfSaleDetailsId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getPointOfSaleDetailsId(), NurseryStock_.pointOfSaleDetails, PointOfSaleDetails_.id));
            }
        }
        return specification;
    }

}
