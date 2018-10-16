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
import com.niche.ng.service.BatchQuantityService;
import com.niche.ng.web.rest.errors.BadRequestAlertException;
import com.niche.ng.web.rest.util.HeaderUtil;
import com.niche.ng.web.rest.util.PaginationUtil;
import com.niche.ng.service.dto.BatchQuantityDTO;
import com.niche.ng.service.dto.BatchQuantityCriteria;
import com.niche.ng.service.BatchQuantityQueryService;
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
 * REST controller for managing BatchQuantity.
 * Used RequestMapping annotation to map the url with the client side.
 * Using service to access the values in the database.
 */
@RestController
@RequestMapping("/api")
public class BatchQuantityResource {

    private final Logger log = LoggerFactory.getLogger(BatchQuantityResource.class);

    private static final String ENTITY_NAME = "batchQuantity";

    private final BatchQuantityService batchQuantityService;

    private final BatchQuantityQueryService batchQuantityQueryService;

    public BatchQuantityResource(BatchQuantityService batchQuantityService, BatchQuantityQueryService batchQuantityQueryService) {
        this.batchQuantityService = batchQuantityService;
        this.batchQuantityQueryService = batchQuantityQueryService;
    }

    /**
     * POST  /batch-quantities : Create a new batchQuantity.
     *
     * @param batchQuantityDTO the batchQuantityDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new batchQuantityDTO, or with status 400 (Bad Request) if the batchQuantity has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/batch-quantities")
    @Timed
    public ResponseEntity<BatchQuantityDTO> createBatchQuantity(@Valid @RequestBody BatchQuantityDTO batchQuantityDTO) throws URISyntaxException {
        log.debug("REST request to save BatchQuantity : {}", batchQuantityDTO);
        if (batchQuantityDTO.getId() != null) {
            throw new BadRequestAlertException("A new batchQuantity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BatchQuantityDTO result = batchQuantityService.save(batchQuantityDTO);
        return ResponseEntity.created(new URI("/api/batch-quantities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /batch-quantities : Updates an existing batchQuantity.
     *
     * @param batchQuantityDTO the batchQuantityDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated batchQuantityDTO,
     * or with status 400 (Bad Request) if the batchQuantityDTO is not valid,
     * or with status 500 (Internal Server Error) if the batchQuantityDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/batch-quantities")
    @Timed
    public ResponseEntity<BatchQuantityDTO> updateBatchQuantity(@Valid @RequestBody BatchQuantityDTO batchQuantityDTO) throws URISyntaxException {
        log.debug("REST request to update BatchQuantity : {}", batchQuantityDTO);
        if (batchQuantityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BatchQuantityDTO result = batchQuantityService.save(batchQuantityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, batchQuantityDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /batch-quantities : get all the batchQuantities.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of batchQuantities in body
     */
    @GetMapping("/batch-quantities")
    @Timed
    public ResponseEntity<List<BatchQuantityDTO>> getAllBatchQuantities(BatchQuantityCriteria criteria, Pageable pageable) {
        log.debug("REST request to get BatchQuantities by criteria: {}", criteria);
        Page<BatchQuantityDTO> page = batchQuantityQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/batch-quantities");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /batch-quantities/:id : get the "id" batchQuantity.
     *
     * @param id the id of the batchQuantityDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the batchQuantityDTO, or with status 404 (Not Found)
     */
    @GetMapping("/batch-quantities/{id}")
    @Timed
    public ResponseEntity<BatchQuantityDTO> getBatchQuantity(@PathVariable Long id) {
        log.debug("REST request to get BatchQuantity : {}", id);
        Optional<BatchQuantityDTO> batchQuantityDTO = batchQuantityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(batchQuantityDTO);
    }

    /**
     * DELETE  /batch-quantities/:id : delete the "id" batchQuantity.
     *
     * @param id the id of the batchQuantityDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/batch-quantities/{id}")
    @Timed
    public ResponseEntity<Void> deleteBatchQuantity(@PathVariable Long id) {
        log.debug("REST request to delete BatchQuantity : {}", id);
        batchQuantityService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
