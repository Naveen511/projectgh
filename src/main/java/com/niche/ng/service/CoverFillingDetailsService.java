package com.niche.ng.service;

import com.niche.ng.service.dto.CoverFillingDetailsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.List;

/**
 * Service Interface for managing CoverFillingDetails.
 */
public interface CoverFillingDetailsService {

    /**
     * Save a coverFillingDetails.
     *
     * @param coverFillingDetailsDTO the entity to save
     * @return the persisted entity
     */
    CoverFillingDetailsDTO save(CoverFillingDetailsDTO coverFillingDetailsDTO);

    /**
     * Get all the coverFillingDetails.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CoverFillingDetailsDTO> findAll(Pageable pageable);


    /**
     * Get the "id" coverFillingDetails.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CoverFillingDetailsDTO> findOne(Long id);

    /**
     * Delete the "id" coverFillingDetails.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Get the "coverFillingId" NurseryInventoryDetails.
     *
     * @param coverFillingId the coverFillingId of the entity
     * @return the list of entity
     */
    List<CoverFillingDetailsDTO> findParticularCoverPreparedDetails(Long coverFillingId);
}
