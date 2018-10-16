package com.niche.ng.service.impl;

import com.niche.ng.service.ZonalInchargeService;
import com.niche.ng.domain.ZonalIncharge;
import com.niche.ng.repository.ZonalInchargeRepository;
import com.niche.ng.service.dto.ZonalInchargeDTO;
import com.niche.ng.service.mapper.ZonalInchargeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing ZonalIncharge.
 */
@Service
@Transactional
public class ZonalInchargeServiceImpl implements ZonalInchargeService {

    private final Logger log = LoggerFactory.getLogger(ZonalInchargeServiceImpl.class);

    private final ZonalInchargeRepository zonalInchargeRepository;

    private final ZonalInchargeMapper zonalInchargeMapper;

    public ZonalInchargeServiceImpl(ZonalInchargeRepository zonalInchargeRepository, ZonalInchargeMapper zonalInchargeMapper) {
        this.zonalInchargeRepository = zonalInchargeRepository;
        this.zonalInchargeMapper = zonalInchargeMapper;
    }

    /**
     * Save a zonalIncharge.
     *
     * @param zonalInchargeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ZonalInchargeDTO save(ZonalInchargeDTO zonalInchargeDTO) {
        log.debug("Request to save ZonalIncharge : {}", zonalInchargeDTO);
        ZonalIncharge zonalIncharge = zonalInchargeMapper.toEntity(zonalInchargeDTO);
        zonalIncharge = zonalInchargeRepository.save(zonalIncharge);
        return zonalInchargeMapper.toDto(zonalIncharge);
    }

    /**
     * Get all the zonalIncharges.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ZonalInchargeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ZonalIncharges");
        return zonalInchargeRepository.findAll(pageable)
            .map(zonalInchargeMapper::toDto);
    }


    /**
     * Get one zonalIncharge by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ZonalInchargeDTO> findOne(Long id) {
        log.debug("Request to get ZonalIncharge : {}", id);
        return zonalInchargeRepository.findById(id)
            .map(zonalInchargeMapper::toDto);
    }

    /**
     * Delete the zonalIncharge by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ZonalIncharge : {}", id);
        zonalInchargeRepository.deleteById(id);
    }
}
