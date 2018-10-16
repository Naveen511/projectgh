/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/07
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryStockResource of CRUD Operation
 *
 *******************************************************************************/
package com.niche.ng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.niche.ng.service.NurseryStockService;
import com.niche.ng.web.rest.errors.BadRequestAlertException;
import com.niche.ng.web.rest.util.HeaderUtil;
import com.niche.ng.web.rest.util.PaginationUtil;
import com.niche.ng.service.dto.NurseryStockDTO;
import com.niche.ng.service.dto.NurseryStockCriteria;
import com.niche.ng.service.NurseryStockQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing NurseryStock.
 * Used RequestMapping annotation to map the url with the client side.
 * Using service to access the values in the database.
 */
@RestController
@RequestMapping("/api")
public class NurseryStockResource {

    private final Logger log = LoggerFactory.getLogger(NurseryStockResource.class);

    private static final String ENTITY_NAME = "nurseryStock";

    private final NurseryStockService nurseryStockService;

    private final NurseryStockQueryService nurseryStockQueryService;

    public NurseryStockResource(NurseryStockService nurseryStockService, NurseryStockQueryService nurseryStockQueryService) {
        this.nurseryStockService = nurseryStockService;
        this.nurseryStockQueryService = nurseryStockQueryService;
    }

    /**
     * POST  /nursery-stocks : Create a new nurseryStock.
     *
     * @param nurseryStockDTO the nurseryStockDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new nurseryStockDTO, or with status 400 (Bad Request) if the nurseryStock has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/nursery-stocks")
    @Timed
    public ResponseEntity<NurseryStockDTO> createNurseryStock(@RequestBody NurseryStockDTO nurseryStockDTO) throws URISyntaxException {
        log.debug("REST request to save NurseryStock : {}", nurseryStockDTO);
        if (nurseryStockDTO.getId() != null) {
            throw new BadRequestAlertException("A new nurseryStock cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NurseryStockDTO result = nurseryStockService.save(nurseryStockDTO);
        return ResponseEntity.created(new URI("/api/nursery-stocks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /nursery-stocks : Updates an existing nurseryStock.
     *
     * @param nurseryStockDTO the nurseryStockDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated nurseryStockDTO,
     * or with status 400 (Bad Request) if the nurseryStockDTO is not valid,
     * or with status 500 (Internal Server Error) if the nurseryStockDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/nursery-stocks")
    @Timed
    public ResponseEntity<NurseryStockDTO> updateNurseryStock(@RequestBody NurseryStockDTO nurseryStockDTO) throws URISyntaxException {
        log.debug("REST request to update NurseryStock : {}", nurseryStockDTO);
        if (nurseryStockDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NurseryStockDTO result = nurseryStockService.save(nurseryStockDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, nurseryStockDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /nursery-stocks : get all the nurseryStocks.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of nurseryStocks in body
     */
    @GetMapping("/nursery-stocks")
    @Timed
    public ResponseEntity<List<NurseryStockDTO>> getAllNurseryStocks(NurseryStockCriteria criteria, Pageable pageable) {
        log.debug("REST request to get NurseryStocks by criteria: {}", criteria);
        Page<NurseryStockDTO> page = nurseryStockQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/nursery-stocks");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /nursery-stocks/:id : get the "id" nurseryStock.
     *
     * @param id the id of the nurseryStockDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the nurseryStockDTO, or with status 404 (Not Found)
     */
    @GetMapping("/nursery-stocks/{id}")
    @Timed
    public ResponseEntity<NurseryStockDTO> getNurseryStock(@PathVariable Long id) {
        log.debug("REST request to get NurseryStock : {}", id);
        Optional<NurseryStockDTO> nurseryStockDTO = nurseryStockService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nurseryStockDTO);
    }

    /**
     * DELETE  /nursery-stocks/:id : delete the "id" nurseryStock.
     *
     * @param id the id of the nurseryStockDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/nursery-stocks/{id}")
    @Timed
    public ResponseEntity<Void> deleteNurseryStock(@PathVariable Long id) {
        log.debug("REST request to delete NurseryStock : {}", id);
        nurseryStockService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
