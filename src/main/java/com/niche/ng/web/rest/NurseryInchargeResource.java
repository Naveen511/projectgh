/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/09/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryInchargeResource of CRUD Operation
 *
 *******************************************************************************/
package com.niche.ng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.niche.ng.service.NurseryInchargeService;
import com.niche.ng.web.rest.errors.BadRequestAlertException;
import com.niche.ng.web.rest.util.HeaderUtil;
import com.niche.ng.web.rest.util.PaginationUtil;
import com.niche.ng.service.dto.NurseryInchargeDTO;
import com.niche.ng.service.dto.NurseryInchargeCriteria;
import com.niche.ng.service.NurseryInchargeQueryService;
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
 * REST controller for managing NurseryIncharge.
 * Used RequestMapping annotation to map the url with the client side.
 * Using service to access the values in the database.
 */
@RestController
@RequestMapping("/api")
public class NurseryInchargeResource {

    private final Logger log = LoggerFactory.getLogger(NurseryInchargeResource.class);

    private static final String ENTITY_NAME = "nurseryIncharge";

    private final NurseryInchargeService nurseryInchargeService;

    private final NurseryInchargeQueryService nurseryInchargeQueryService;

    public NurseryInchargeResource(NurseryInchargeService nurseryInchargeService, NurseryInchargeQueryService nurseryInchargeQueryService) {
        this.nurseryInchargeService = nurseryInchargeService;
        this.nurseryInchargeQueryService = nurseryInchargeQueryService;
    }

    /**
     * POST  /nursery-incharges : Create a new nurseryIncharge.
     *
     * @param nurseryInchargeDTO the nurseryInchargeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new nurseryInchargeDTO, or with status 400 (Bad Request) if the nurseryIncharge has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/nursery-incharges")
    @Timed
    public ResponseEntity<NurseryInchargeDTO> createNurseryIncharge(@Valid @RequestBody NurseryInchargeDTO nurseryInchargeDTO) throws URISyntaxException {
        log.debug("REST request to save NurseryIncharge : {}", nurseryInchargeDTO);
        if (nurseryInchargeDTO.getId() != null) {
            throw new BadRequestAlertException("A new nurseryIncharge cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NurseryInchargeDTO result = nurseryInchargeService.save(nurseryInchargeDTO);
        return ResponseEntity.created(new URI("/api/nursery-incharges/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /nursery-incharges : Updates an existing nurseryIncharge.
     *
     * @param nurseryInchargeDTO the nurseryInchargeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated nurseryInchargeDTO,
     * or with status 400 (Bad Request) if the nurseryInchargeDTO is not valid,
     * or with status 500 (Internal Server Error) if the nurseryInchargeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/nursery-incharges")
    @Timed
    public ResponseEntity<NurseryInchargeDTO> updateNurseryIncharge(@Valid @RequestBody NurseryInchargeDTO nurseryInchargeDTO) throws URISyntaxException {
        log.debug("REST request to update NurseryIncharge : {}", nurseryInchargeDTO);
        if (nurseryInchargeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NurseryInchargeDTO result = nurseryInchargeService.save(nurseryInchargeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, nurseryInchargeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /nursery-incharges : get all the nurseryIncharges.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of nurseryIncharges in body
     */
    @GetMapping("/nursery-incharges")
    @Timed
    public ResponseEntity<List<NurseryInchargeDTO>> getAllNurseryIncharges(NurseryInchargeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get NurseryIncharges by criteria: {}", criteria);
        Page<NurseryInchargeDTO> page = nurseryInchargeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/nursery-incharges");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /nursery-incharges/:id : get the "id" nurseryIncharge.
     *
     * @param id the id of the nurseryInchargeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the nurseryInchargeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/nursery-incharges/{id}")
    @Timed
    public ResponseEntity<NurseryInchargeDTO> getNurseryIncharge(@PathVariable Long id) {
        log.debug("REST request to get NurseryIncharge : {}", id);
        Optional<NurseryInchargeDTO> nurseryInchargeDTO = nurseryInchargeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nurseryInchargeDTO);
    }

    /**
     * DELETE  /nursery-incharges/:id : delete the "id" nurseryIncharge.
     *
     * @param id the id of the nurseryInchargeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/nursery-incharges/{id}")
    @Timed
    public ResponseEntity<Void> deleteNurseryIncharge(@PathVariable Long id) {
        log.debug("REST request to delete NurseryIncharge : {}", id);
        nurseryInchargeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
