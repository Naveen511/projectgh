package com.niche.ng.service;

import com.niche.ng.service.dto.CommonSettingsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing CommonSettings.
 */
public interface CommonSettingsService {

    /**
     * Save a commonSettings.
     *
     * @param commonSettingsDTO the entity to save
     * @return the persisted entity
     */
    CommonSettingsDTO save(CommonSettingsDTO commonSettingsDTO);

    /**
     * Get all the commonSettings.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CommonSettingsDTO> findAll(Pageable pageable);


    /**
     * Get the "id" commonSettings.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CommonSettingsDTO> findOne(Long id);

    /**
     * Delete the "id" commonSettings.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
