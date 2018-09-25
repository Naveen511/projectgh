package com.niche.ng.service.impl;

import com.niche.ng.service.QuantityService;
import com.niche.ng.domain.Quantity;
import com.niche.ng.repository.QuantityRepository;
import com.niche.ng.service.dto.QuantityDTO;
import com.niche.ng.service.mapper.QuantityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing Quantity.
 */
@Service
@Transactional
public class QuantityServiceImpl implements QuantityService {

    private final Logger log = LoggerFactory.getLogger(QuantityServiceImpl.class);

    private final QuantityRepository quantityRepository;

    private final QuantityMapper quantityMapper;

    public QuantityServiceImpl(QuantityRepository quantityRepository, QuantityMapper quantityMapper) {
        this.quantityRepository = quantityRepository;
        this.quantityMapper = quantityMapper;
    }

    /**
     * Save a quantity.
     *
     * @param quantityDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public QuantityDTO save(QuantityDTO quantityDTO) {
        log.debug("Request to save Quantity : {}", quantityDTO);
        Quantity quantity = quantityMapper.toEntity(quantityDTO);
        quantity = quantityRepository.save(quantity);
        return quantityMapper.toDto(quantity);
    }

    /**
     * Get all the quantities.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<QuantityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Quantities");
        return quantityRepository.findAll(pageable)
            .map(quantityMapper::toDto);
    }


    /**
     * Get one quantity by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<QuantityDTO> findOne(Long id) {
        log.debug("Request to get Quantity : {}", id);
        return quantityRepository.findById(id)
            .map(quantityMapper::toDto);
    }

    /**
     * Delete the quantity by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Quantity : {}", id);
        quantityRepository.deleteById(id);
    }

    /**
     * softDelete the quantity by id
     * 
     * @param id the id of the entity
     */
    @Override
    public void softDelete(Long id) {
        log.debug("Request to delete quantity : {}", id);
        quantityRepository.findById(id);
    }
}
