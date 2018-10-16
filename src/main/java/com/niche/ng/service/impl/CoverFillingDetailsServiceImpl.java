package com.niche.ng.service.impl;

import com.niche.ng.service.CoverFillingDetailsService;
import com.niche.ng.domain.CoverFillingDetails;
import com.niche.ng.repository.CoverFillingDetailsRepository;
import com.niche.ng.service.dto.CoverFillingDetailsDTO;
import com.niche.ng.service.mapper.CoverFillingDetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
import java.util.List;
/**
 * Service Implementation for managing CoverFillingDetails.
 */
@Service
@Transactional
public class CoverFillingDetailsServiceImpl implements CoverFillingDetailsService {

    private final Logger log = LoggerFactory.getLogger(CoverFillingDetailsServiceImpl.class);

    private final CoverFillingDetailsRepository coverFillingDetailsRepository;

    private final CoverFillingDetailsMapper coverFillingDetailsMapper;

    public CoverFillingDetailsServiceImpl(CoverFillingDetailsRepository coverFillingDetailsRepository, CoverFillingDetailsMapper coverFillingDetailsMapper) {
        this.coverFillingDetailsRepository = coverFillingDetailsRepository;
        this.coverFillingDetailsMapper = coverFillingDetailsMapper;
    }

    /**
     * Save a coverFillingDetails.
     *
     * @param coverFillingDetailsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CoverFillingDetailsDTO save(CoverFillingDetailsDTO coverFillingDetailsDTO) {
        log.debug("Request to save CoverFillingDetails : {}", coverFillingDetailsDTO);
        CoverFillingDetails coverFillingDetails = coverFillingDetailsMapper.toEntity(coverFillingDetailsDTO);
        coverFillingDetails = coverFillingDetailsRepository.save(coverFillingDetails);
        return coverFillingDetailsMapper.toDto(coverFillingDetails);
    }

    /**
     * Get all the coverFillingDetails.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CoverFillingDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CoverFillingDetails");
        return coverFillingDetailsRepository.findAll(pageable)
            .map(coverFillingDetailsMapper::toDto);
    }


    /**
     * Get one coverFillingDetails by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CoverFillingDetailsDTO> findOne(Long id) {
        log.debug("Request to get CoverFillingDetails : {}", id);
        return coverFillingDetailsRepository.findById(id)
            .map(coverFillingDetailsMapper::toDto);
    }

    /**
     * Delete the coverFillingDetails by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CoverFillingDetails : {}", id);
        coverFillingDetailsRepository.deleteById(id);
    }

    /**
     * Get all the nurseryInventoryDetails by nurseryInventoryId.
     *
     * @param nurseryInventoryId the nurseryInventoryId of the entity
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<CoverFillingDetailsDTO> findParticularCoverPreparedDetails(Long coverFillingId) {
        log.debug("Request to get coverFillingDetails : {}", coverFillingId);
        List<CoverFillingDetails> coverFillingDetails = coverFillingDetailsRepository.findByCoverFillingId(coverFillingId);
        return coverFillingDetailsMapper.toDto(coverFillingDetails);
    }
}
