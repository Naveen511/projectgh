package com.niche.ng.service.impl;

import com.niche.ng.service.SectorInchargeService;
import com.niche.ng.domain.SectorIncharge;
import com.niche.ng.repository.SectorInchargeRepository;
import com.niche.ng.service.dto.SectorInchargeDTO;
import com.niche.ng.service.mapper.SectorInchargeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing SectorIncharge.
 */
@Service
@Transactional
public class SectorInchargeServiceImpl implements SectorInchargeService {

    private final Logger log = LoggerFactory.getLogger(SectorInchargeServiceImpl.class);

    private final SectorInchargeRepository sectorInchargeRepository;

    private final SectorInchargeMapper sectorInchargeMapper;

    public SectorInchargeServiceImpl(SectorInchargeRepository sectorInchargeRepository, SectorInchargeMapper sectorInchargeMapper) {
        this.sectorInchargeRepository = sectorInchargeRepository;
        this.sectorInchargeMapper = sectorInchargeMapper;
    }

    /**
     * Save a sectorIncharge.
     *
     * @param sectorInchargeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SectorInchargeDTO save(SectorInchargeDTO sectorInchargeDTO) {
        log.debug("Request to save SectorIncharge : {}", sectorInchargeDTO);
        SectorIncharge sectorIncharge = sectorInchargeMapper.toEntity(sectorInchargeDTO);
        sectorIncharge = sectorInchargeRepository.save(sectorIncharge);
        return sectorInchargeMapper.toDto(sectorIncharge);
    }

    /**
     * Get all the sectorIncharges.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SectorInchargeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SectorIncharges");
        return sectorInchargeRepository.findAll(pageable)
            .map(sectorInchargeMapper::toDto);
    }


    /**
     * Get one sectorIncharge by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SectorInchargeDTO> findOne(Long id) {
        log.debug("Request to get SectorIncharge : {}", id);
        return sectorInchargeRepository.findById(id)
            .map(sectorInchargeMapper::toDto);
    }

    /**
     * Delete the sectorIncharge by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SectorIncharge : {}", id);
        sectorInchargeRepository.deleteById(id);
    }
}
