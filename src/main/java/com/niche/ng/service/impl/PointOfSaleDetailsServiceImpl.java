package com.niche.ng.service.impl;

import com.niche.ng.service.PointOfSaleDetailsService;
import com.niche.ng.domain.PointOfSaleDetails;
import com.niche.ng.repository.PointOfSaleDetailsRepository;
import com.niche.ng.service.dto.PointOfSaleDetailsDTO;
import com.niche.ng.service.mapper.PointOfSaleDetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing PointOfSaleDetails.
 */
@Service
@Transactional
public class PointOfSaleDetailsServiceImpl implements PointOfSaleDetailsService {

    private final Logger log = LoggerFactory.getLogger(PointOfSaleDetailsServiceImpl.class);

    private final PointOfSaleDetailsRepository pointOfSaleDetailsRepository;

    private final PointOfSaleDetailsMapper pointOfSaleDetailsMapper;

    public PointOfSaleDetailsServiceImpl(PointOfSaleDetailsRepository pointOfSaleDetailsRepository, PointOfSaleDetailsMapper pointOfSaleDetailsMapper) {
        this.pointOfSaleDetailsRepository = pointOfSaleDetailsRepository;
        this.pointOfSaleDetailsMapper = pointOfSaleDetailsMapper;
    }

    /**
     * Save a pointOfSaleDetails.
     *
     * @param pointOfSaleDetailsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PointOfSaleDetailsDTO save(PointOfSaleDetailsDTO pointOfSaleDetailsDTO) {
        log.debug("Request to save PointOfSaleDetails : {}", pointOfSaleDetailsDTO);
        PointOfSaleDetails pointOfSaleDetails = pointOfSaleDetailsMapper.toEntity(pointOfSaleDetailsDTO);
        pointOfSaleDetails = pointOfSaleDetailsRepository.save(pointOfSaleDetails);
        return pointOfSaleDetailsMapper.toDto(pointOfSaleDetails);
    }

    /**
     * Get all the pointOfSaleDetails.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PointOfSaleDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PointOfSaleDetails");
        return pointOfSaleDetailsRepository.findAll(pageable)
            .map(pointOfSaleDetailsMapper::toDto);
    }


    /**
     * Get one pointOfSaleDetails by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PointOfSaleDetailsDTO> findOne(Long id) {
        log.debug("Request to get PointOfSaleDetails : {}", id);
        return pointOfSaleDetailsRepository.findById(id)
            .map(pointOfSaleDetailsMapper::toDto);
    }

    /**
     * Delete the pointOfSaleDetails by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PointOfSaleDetails : {}", id);
        pointOfSaleDetailsRepository.deleteById(id);
    }
}
