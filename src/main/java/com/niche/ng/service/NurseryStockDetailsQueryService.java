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

import com.niche.ng.domain.NurseryStockDetails;
import com.niche.ng.domain.*; // for static metamodels
import com.niche.ng.repository.NurseryStockDetailsRepository;
import com.niche.ng.service.dto.NurseryStockDetailsCriteria;

import com.niche.ng.service.dto.NurseryStockDetailsDTO;
import com.niche.ng.service.mapper.NurseryStockDetailsMapper;

/**
 * Service for executing complex queries for NurseryStockDetails entities in the database.
 * The main input is a {@link NurseryStockDetailsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link NurseryStockDetailsDTO} or a {@link Page} of {@link NurseryStockDetailsDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class NurseryStockDetailsQueryService extends QueryService<NurseryStockDetails> {

    private final Logger log = LoggerFactory.getLogger(NurseryStockDetailsQueryService.class);

    private final NurseryStockDetailsRepository nurseryStockDetailsRepository;

    private final NurseryStockDetailsMapper nurseryStockDetailsMapper;

    public NurseryStockDetailsQueryService(NurseryStockDetailsRepository nurseryStockDetailsRepository, NurseryStockDetailsMapper nurseryStockDetailsMapper) {
        this.nurseryStockDetailsRepository = nurseryStockDetailsRepository;
        this.nurseryStockDetailsMapper = nurseryStockDetailsMapper;
    }

    /**
     * Return a {@link List} of {@link NurseryStockDetailsDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<NurseryStockDetailsDTO> findByCriteria(NurseryStockDetailsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<NurseryStockDetails> specification = createSpecification(criteria);
        return nurseryStockDetailsMapper.toDto(nurseryStockDetailsRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link NurseryStockDetailsDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<NurseryStockDetailsDTO> findByCriteria(NurseryStockDetailsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<NurseryStockDetails> specification = createSpecification(criteria);
        return nurseryStockDetailsRepository.findAll(specification, page)
            .map(nurseryStockDetailsMapper::toDto);
    }

    /**
     * Function to convert NurseryStockDetailsCriteria to a {@link Specification}
     */
    private Specification<NurseryStockDetails> createSpecification(NurseryStockDetailsCriteria criteria) {
        Specification<NurseryStockDetails> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), NurseryStockDetails_.id));
            }
            if (criteria.getDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDate(), NurseryStockDetails_.date));
            }
            if (criteria.getQuantity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQuantity(), NurseryStockDetails_.quantity));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), NurseryStockDetails_.description));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), NurseryStockDetails_.status));
            }
            if (criteria.getBatchId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getBatchId(), NurseryStockDetails_.batch, Batch_.id));
            }
            if (criteria.getNurseryStockId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getNurseryStockId(), NurseryStockDetails_.nurseryStock, NurseryStock_.id));
            }
            if (criteria.getItNurseryId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getItNurseryId(), NurseryStockDetails_.itNursery, Nursery_.id));
            }
            if (criteria.getSaplingDamageAreaId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getSaplingDamageAreaId(), NurseryStockDetails_.saplingDamageArea, PickListValue_.id));
            }
            if (criteria.getFinancialYearStockDetailsId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getFinancialYearStockDetailsId(), NurseryStockDetails_.financialYearStockDetails, FinancialYearSettings_.id));
            }
        }
        return specification;
    }

}
