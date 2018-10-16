/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/07
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs GodownStockDetailsResource of CRUD Operation
 *
 *******************************************************************************/
package com.niche.ng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.niche.ng.service.GodownStockDetailsService;
import com.niche.ng.web.rest.errors.BadRequestAlertException;
import com.niche.ng.web.rest.util.HeaderUtil;
import com.niche.ng.web.rest.util.PaginationUtil;
import com.niche.ng.service.dto.GodownStockDetailsDTO;
import com.niche.ng.service.dto.GodownStockDetailsCriteria;
import com.niche.ng.service.GodownStockDetailsQueryService;
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
 * REST controller for managing GodownStockDetails.
 */
@RestController
@RequestMapping("/api")
public class GodownStockDetailsResource {

    private final Logger log = LoggerFactory.getLogger(GodownStockDetailsResource.class);

    private static final String ENTITY_NAME = "godownStockDetails";

    private final GodownStockDetailsService godownStockDetailsService;

    private final GodownStockDetailsQueryService godownStockDetailsQueryService;

    public GodownStockDetailsResource(GodownStockDetailsService godownStockDetailsService, GodownStockDetailsQueryService godownStockDetailsQueryService) {
        this.godownStockDetailsService = godownStockDetailsService;
        this.godownStockDetailsQueryService = godownStockDetailsQueryService;
    }

    /**
     * POST  /godown-stock-details : Create a new godownStockDetails.
     *
     * @param godownStockDetailsDTO the godownStockDetailsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new godownStockDetailsDTO, or with status 400 (Bad Request) if the godownStockDetails has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/godown-stock-details")
    @Timed
    public ResponseEntity<GodownStockDetailsDTO> createGodownStockDetails(@Valid @RequestBody GodownStockDetailsDTO godownStockDetailsDTO) throws URISyntaxException {
        log.debug("REST request to save GodownStockDetails : {}", godownStockDetailsDTO);
        if (godownStockDetailsDTO.getId() != null) {
            throw new BadRequestAlertException("A new godownStockDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GodownStockDetailsDTO result = godownStockDetailsService.save(godownStockDetailsDTO);
        return ResponseEntity.created(new URI("/api/godown-stock-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /godown-stock-details : Updates an existing godownStockDetails.
     *
     * @param godownStockDetailsDTO the godownStockDetailsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated godownStockDetailsDTO,
     * or with status 400 (Bad Request) if the godownStockDetailsDTO is not valid,
     * or with status 500 (Internal Server Error) if the godownStockDetailsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/godown-stock-details")
    @Timed
    public ResponseEntity<GodownStockDetailsDTO> updateGodownStockDetails(@Valid @RequestBody GodownStockDetailsDTO godownStockDetailsDTO) throws URISyntaxException {
        log.debug("REST request to update GodownStockDetails : {}", godownStockDetailsDTO);
        if (godownStockDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GodownStockDetailsDTO result = godownStockDetailsService.save(godownStockDetailsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, godownStockDetailsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /godown-stock-details : get all the godownStockDetails.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of godownStockDetails in body
     */
    @GetMapping("/godown-stock-details")
    @Timed
    public ResponseEntity<List<GodownStockDetailsDTO>> getAllGodownStockDetails(GodownStockDetailsCriteria criteria, Pageable pageable) {
        log.debug("REST request to get GodownStockDetails by criteria: {}", criteria);
        Page<GodownStockDetailsDTO> page = godownStockDetailsQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/godown-stock-details");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /godown-stock-details/:id : get the "id" godownStockDetails.
     *
     * @param id the id of the godownStockDetailsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the godownStockDetailsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/godown-stock-details/{id}")
    @Timed
    public ResponseEntity<GodownStockDetailsDTO> getGodownStockDetails(@PathVariable Long id) {
        log.debug("REST request to get GodownStockDetails : {}", id);
        Optional<GodownStockDetailsDTO> godownStockDetailsDTO = godownStockDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(godownStockDetailsDTO);
    }

    /**
     * DELETE  /godown-stock-details/:id : delete the "id" godownStockDetails.
     *
     * @param id the id of the godownStockDetailsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/godown-stock-details/{id}")
    @Timed
    public ResponseEntity<Void> deleteGodownStockDetails(@PathVariable Long id) {
        log.debug("REST request to delete GodownStockDetails : {}", id);
        godownStockDetailsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * GET  /godown-stock-details/stock-list/:godownStockId : get the "godownStockId" godownStockDetails.
     *
     * @param godownStockId the godownStockId of the GodownStockDetailsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and the list of stocks in body
     */
    @GetMapping("/godown-stock-details/stock-list/{godownStockId}")
    @Timed
    public ResponseEntity<List<GodownStockDetailsDTO>> getStockListDetails(@PathVariable Long godownStockId) {
        log.debug("REST request to get a list of particular zonal Sectors");
        List<GodownStockDetailsDTO> stockList = godownStockDetailsService.findStockList(godownStockId);
        return ResponseEntity.ok().body(stockList);
    }
}
