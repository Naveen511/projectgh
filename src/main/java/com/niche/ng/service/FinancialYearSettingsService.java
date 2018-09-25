package com.niche.ng.service;

import com.niche.ng.service.dto.FinancialYearSettingsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.List;
/**
 * Service Interface for managing FinancialYearSettings.
 */
public interface FinancialYearSettingsService {

    /**
     * Save a financialYearSettings.
     *
     * @param financialYearSettingsDTO the entity to save
     * @return the persisted entity
     */
    FinancialYearSettingsDTO save(FinancialYearSettingsDTO financialYearSettingsDTO);

    /**
     * Get all the financialYearSettings.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<FinancialYearSettingsDTO> findAll(Pageable pageable);


    /**
     * Get the "id" financialYearSettings.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<FinancialYearSettingsDTO> findOne(Long id);

    /**
     * Delete the "id" financialYearSettings.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

     /**
     * softDelete the "id" financialYearSettings
     * 
     * @param id the id of the entity
     */
    void softDelete(Long id);

    /**
     * Get the "status" financialYearSettings.
     *
     * @param status the status of the entity
     * @return the entity
     */
    List<FinancialYearSettingsDTO> findActiveRecord(Integer status);
}
