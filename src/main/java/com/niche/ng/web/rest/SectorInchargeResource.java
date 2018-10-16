/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/10/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs SectorInchargeResource of CRUD Operation
 *
 *******************************************************************************/
package com.niche.ng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.niche.ng.service.SectorInchargeService;
import com.niche.ng.web.rest.errors.BadRequestAlertException;
import com.niche.ng.web.rest.util.HeaderUtil;
import com.niche.ng.web.rest.util.PaginationUtil;
import com.niche.ng.service.dto.SectorInchargeDTO;
import com.niche.ng.service.dto.SectorInchargeCriteria;
import com.niche.ng.service.SectorInchargeQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing SectorIncharge.
 * Used RequestMapping annotation to map the url with the client side.
 * Using service to access the values in the database.
 */
@RestController
@RequestMapping("/api")
public class SectorInchargeResource {

    private final Logger log = LoggerFactory.getLogger(SectorInchargeResource.class);

    private static final String ENTITY_NAME = "sectorIncharge";

    private final SectorInchargeService sectorInchargeService;

    private final SectorInchargeQueryService sectorInchargeQueryService;

    public SectorInchargeResource(SectorInchargeService sectorInchargeService, SectorInchargeQueryService sectorInchargeQueryService) {
        this.sectorInchargeService = sectorInchargeService;
        this.sectorInchargeQueryService = sectorInchargeQueryService;
    }

    /**
     * POST  /sector-incharges : Create a new sectorIncharge.
     *
     * @param sectorInchargeDTO the sectorInchargeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sectorInchargeDTO, or with status 400 (Bad Request) if the sectorIncharge has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sector-incharges")
    @Timed
    public ResponseEntity<SectorInchargeDTO> createSectorIncharge(@Valid @RequestBody SectorInchargeDTO sectorInchargeDTO) throws URISyntaxException {
        log.debug("REST request to save SectorIncharge : {}", sectorInchargeDTO);
        if (sectorInchargeDTO.getId() != null) {
            throw new BadRequestAlertException("A new sectorIncharge cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SectorInchargeDTO result = sectorInchargeService.save(sectorInchargeDTO);
        return ResponseEntity.created(new URI("/api/sector-incharges/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sector-incharges : Updates an existing sectorIncharge.
     *
     * @param sectorInchargeDTO the sectorInchargeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sectorInchargeDTO,
     * or with status 400 (Bad Request) if the sectorInchargeDTO is not valid,
     * or with status 500 (Internal Server Error) if the sectorInchargeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sector-incharges")
    @Timed
    public ResponseEntity<SectorInchargeDTO> updateSectorIncharge(@Valid @RequestBody SectorInchargeDTO sectorInchargeDTO) throws URISyntaxException {
        log.debug("REST request to update SectorIncharge : {}", sectorInchargeDTO);
        if (sectorInchargeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SectorInchargeDTO result = sectorInchargeService.save(sectorInchargeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sectorInchargeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sector-incharges : get all the sectorIncharges.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of sectorIncharges in body
     */
    @GetMapping("/sector-incharges")
    @Timed
    public ResponseEntity<List<SectorInchargeDTO>> getAllSectorIncharges(SectorInchargeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get SectorIncharges by criteria: {}", criteria);
        Page<SectorInchargeDTO> page = sectorInchargeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/sector-incharges");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /sector-incharges/:id : get the "id" sectorIncharge.
     *
     * @param id the id of the sectorInchargeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sectorInchargeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/sector-incharges/{id}")
    @Timed
    public ResponseEntity<SectorInchargeDTO> getSectorIncharge(@PathVariable Long id) {
        log.debug("REST request to get SectorIncharge : {}", id);
        Optional<SectorInchargeDTO> sectorInchargeDTO = sectorInchargeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sectorInchargeDTO);
    }

    /**
     * DELETE  /sector-incharges/:id : delete the "id" sectorIncharge.
     *
     * @param id the id of the sectorInchargeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sector-incharges/{id}")
    @Timed
    public ResponseEntity<Void> deleteSectorIncharge(@PathVariable Long id) {
        log.debug("REST request to delete SectorIncharge : {}", id);
        sectorInchargeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
