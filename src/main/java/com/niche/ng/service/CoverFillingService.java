package com.niche.ng.service;

import com.niche.ng.service.dto.CoverFillingDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing CoverFilling.
 */
public interface CoverFillingService {

    /**
     * Save a coverFilling.
     *
     * @param coverFillingDTO the entity to save
     * @return the persisted entity
     */
    CoverFillingDTO save(CoverFillingDTO coverFillingDTO);

    /**
     * Get all the coverFillings.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CoverFillingDTO> findAll(Pageable pageable);


    /**
     * Get the "id" coverFilling.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CoverFillingDTO> findOne(Long id);

    /**
     * Delete the "id" coverFilling.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
