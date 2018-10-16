/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/10/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs BatchQuantityResource of CRUD Operation
 *
 *******************************************************************************/
package com.niche.ng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.niche.ng.service.OperationalHeadService;
import com.niche.ng.web.rest.errors.BadRequestAlertException;
import com.niche.ng.web.rest.util.HeaderUtil;
import com.niche.ng.web.rest.util.PaginationUtil;
import com.niche.ng.service.dto.OperationalHeadDTO;
import com.niche.ng.service.dto.OperationalHeadCriteria;
import com.niche.ng.service.OperationalHeadQueryService;
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
 * REST controller for managing OperationalHead.
 * Used RequestMapping annotation to map the url with the client side.
 * Using service to access the values in the database.
 */
@RestController
@RequestMapping("/api")
public class OperationalHeadResource {

    private final Logger log = LoggerFactory.getLogger(OperationalHeadResource.class);

    private static final String ENTITY_NAME = "operationalHead";

    private final OperationalHeadService operationalHeadService;

    private final OperationalHeadQueryService operationalHeadQueryService;

    public OperationalHeadResource(OperationalHeadService operationalHeadService, OperationalHeadQueryService operationalHeadQueryService) {
        this.operationalHeadService = operationalHeadService;
        this.operationalHeadQueryService = operationalHeadQueryService;
    }

    /**
     * POST  /operational-heads : Create a new operationalHead.
     *
     * @param operationalHeadDTO the operationalHeadDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new operationalHeadDTO, or with status 400 (Bad Request) if the operationalHead has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/operational-heads")
    @Timed
    public ResponseEntity<OperationalHeadDTO> createOperationalHead(@Valid @RequestBody OperationalHeadDTO operationalHeadDTO) throws URISyntaxException {
        log.debug("REST request to save OperationalHead : {}", operationalHeadDTO);
        if (operationalHeadDTO.getId() != null) {
            throw new BadRequestAlertException("A new operationalHead cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OperationalHeadDTO result = operationalHeadService.save(operationalHeadDTO);
        return ResponseEntity.created(new URI("/api/operational-heads/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /operational-heads : Updates an existing operationalHead.
     *
     * @param operationalHeadDTO the operationalHeadDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated operationalHeadDTO,
     * or with status 400 (Bad Request) if the operationalHeadDTO is not valid,
     * or with status 500 (Internal Server Error) if the operationalHeadDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/operational-heads")
    @Timed
    public ResponseEntity<OperationalHeadDTO> updateOperationalHead(@Valid @RequestBody OperationalHeadDTO operationalHeadDTO) throws URISyntaxException {
        log.debug("REST request to update OperationalHead : {}", operationalHeadDTO);
        if (operationalHeadDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OperationalHeadDTO result = operationalHeadService.save(operationalHeadDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, operationalHeadDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /operational-heads : get all the operationalHeads.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of operationalHeads in body
     */
    @GetMapping("/operational-heads")
    @Timed
    public ResponseEntity<List<OperationalHeadDTO>> getAllOperationalHeads(OperationalHeadCriteria criteria, Pageable pageable) {
        log.debug("REST request to get OperationalHeads by criteria: {}", criteria);
        Page<OperationalHeadDTO> page = operationalHeadQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/operational-heads");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /operational-heads/:id : get the "id" operationalHead.
     *
     * @param id the id of the operationalHeadDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the operationalHeadDTO, or with status 404 (Not Found)
     */
    @GetMapping("/operational-heads/{id}")
    @Timed
    public ResponseEntity<OperationalHeadDTO> getOperationalHead(@PathVariable Long id) {
        log.debug("REST request to get OperationalHead : {}", id);
        Optional<OperationalHeadDTO> operationalHeadDTO = operationalHeadService.findOne(id);
        return ResponseUtil.wrapOrNotFound(operationalHeadDTO);
    }

    /**
     * DELETE  /operational-heads/:id : delete the "id" operationalHead.
     *
     * @param id the id of the operationalHeadDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/operational-heads/{id}")
    @Timed
    public ResponseEntity<Void> deleteOperationalHead(@PathVariable Long id) {
        log.debug("REST request to delete OperationalHead : {}", id);
        operationalHeadService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * GET  /operational-heads/active-record:status : get the particular "status" operationalHead.
     *
     * @param status the status of the operationalHeadDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the operationalHeadDTO, or with status 404 (Not Found)
     */
    @GetMapping("/operational-heads/count/{status}")
    @Timed
    public Long getOperationHeadCount(@PathVariable Integer status) {
        log.debug("REST request to get a list of particular status of operationalHeadService");
        return operationalHeadService.findActiveCount(status);
    }
}
