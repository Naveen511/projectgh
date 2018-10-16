/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/07
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs GodownStockResource of CRUD Operation
 *
 *******************************************************************************/
package com.niche.ng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.niche.ng.service.GodownStockService;
import com.niche.ng.web.rest.errors.BadRequestAlertException;
import com.niche.ng.web.rest.util.HeaderUtil;
import com.niche.ng.web.rest.util.PaginationUtil;
import com.niche.ng.service.dto.GodownStockDTO;
import com.niche.ng.service.dto.GodownStockCriteria;
import com.niche.ng.service.GodownStockQueryService;
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
 * REST controller for managing GodownStock.
 */
@RestController
@RequestMapping("/api")
public class GodownStockResource {

    private final Logger log = LoggerFactory.getLogger(GodownStockResource.class);

    private static final String ENTITY_NAME = "godownStock";

    private final GodownStockService godownStockService;

    private final GodownStockQueryService godownStockQueryService;

    public GodownStockResource(GodownStockService godownStockService, GodownStockQueryService godownStockQueryService) {
        this.godownStockService = godownStockService;
        this.godownStockQueryService = godownStockQueryService;
    }

    /**
     * POST  /godown-stocks : Create a new godownStock.
     *
     * @param godownStockDTO the godownStockDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new godownStockDTO, or with status 400 (Bad Request) if the godownStock has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/godown-stocks")
    @Timed
    public ResponseEntity<GodownStockDTO> createGodownStock(@RequestBody GodownStockDTO godownStockDTO) throws URISyntaxException {
        log.debug("REST request to save GodownStock : {}", godownStockDTO);
        if (godownStockDTO.getId() != null) {
            throw new BadRequestAlertException("A new godownStock cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GodownStockDTO result = godownStockService.save(godownStockDTO);
        return ResponseEntity.created(new URI("/api/godown-stocks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /godown-stocks : Updates an existing godownStock.
     *
     * @param godownStockDTO the godownStockDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated godownStockDTO,
     * or with status 400 (Bad Request) if the godownStockDTO is not valid,
     * or with status 500 (Internal Server Error) if the godownStockDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/godown-stocks")
    @Timed
    public ResponseEntity<GodownStockDTO> updateGodownStock(@RequestBody GodownStockDTO godownStockDTO) throws URISyntaxException {
        log.debug("REST request to update GodownStock : {}", godownStockDTO);
        if (godownStockDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GodownStockDTO result = godownStockService.save(godownStockDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, godownStockDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /godown-stocks : get all the godownStocks.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of godownStocks in body
     */
    @GetMapping("/godown-stocks")
    @Timed
    public ResponseEntity<List<GodownStockDTO>> getAllGodownStocks(GodownStockCriteria criteria, Pageable pageable) {
        log.debug("REST request to get GodownStocks by criteria: {}", criteria);
        Page<GodownStockDTO> page = godownStockQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/godown-stocks");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /godown-stocks/:id : get the "id" godownStock.
     *
     * @param id the id of the godownStockDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the godownStockDTO, or with status 404 (Not Found)
     */
    @GetMapping("/godown-stocks/{id}")
    @Timed
    public ResponseEntity<GodownStockDTO> getGodownStock(@PathVariable Long id) {
        log.debug("REST request to get GodownStock : {}", id);
        Optional<GodownStockDTO> godownStockDTO = godownStockService.findOne(id);
        return ResponseUtil.wrapOrNotFound(godownStockDTO);
    }

    /**
     * DELETE  /godown-stocks/:id : delete the "id" godownStock.
     *
     * @param id the id of the godownStockDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/godown-stocks/{id}")
    @Timed
    public ResponseEntity<Void> deleteGodownStock(@PathVariable Long id) {
        log.debug("REST request to delete GodownStock : {}", id);
        godownStockService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * GET  /godown-stocks/godownId/pickListCategoryId : get all the values of same Id.
     *
     * @param godownId the godownId of the sectorDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and the list of sectors in body
     */
    @GetMapping("/godown-stocks/{godownId}/{pickListCategoryId}")
    @Timed
    public ResponseEntity<List<GodownStockDTO>> getGodownCategoryStock(@PathVariable Long godownId, @PathVariable Long pickListCategoryId) {
        log.debug("REST request to get a list of particular category stock");
        List<GodownStockDTO> list = godownStockService.findStock(godownId, pickListCategoryId);
        return ResponseEntity.ok().body(list);
    }
}
