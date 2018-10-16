/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/07
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description : This file performs GodownPurchaseDetailsResource of CRUD Operation
 *
 *******************************************************************************/
package com.niche.ng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.niche.ng.service.GodownPurchaseDetailsService;
import com.niche.ng.web.rest.errors.BadRequestAlertException;
import com.niche.ng.web.rest.util.HeaderUtil;
import com.niche.ng.web.rest.util.PaginationUtil;
import com.niche.ng.service.dto.GodownPurchaseDetailsDTO;
import com.niche.ng.service.dto.GodownPurchaseDetailsCriteria;
import com.niche.ng.service.GodownPurchaseDetailsQueryService;
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
 * REST controller for managing GodownPurchaseDetails.
 */
@RestController
@RequestMapping("/api")
public class GodownPurchaseDetailsResource {

    private final Logger log = LoggerFactory.getLogger(GodownPurchaseDetailsResource.class);

    private static final String ENTITY_NAME = "godownPurchaseDetails";

    private final GodownPurchaseDetailsService godownPurchaseDetailsService;

    private final GodownPurchaseDetailsQueryService godownPurchaseDetailsQueryService;

    public GodownPurchaseDetailsResource(GodownPurchaseDetailsService godownPurchaseDetailsService, GodownPurchaseDetailsQueryService godownPurchaseDetailsQueryService) {
        this.godownPurchaseDetailsService = godownPurchaseDetailsService;
        this.godownPurchaseDetailsQueryService = godownPurchaseDetailsQueryService;
    }

    /**
     * POST  /godown-purchase-details : Create a new godownPurchaseDetails.
     *
     * @param godownPurchaseDetailsDTO the godownPurchaseDetailsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new godownPurchaseDetailsDTO, or with status 400 (Bad Request) if the godownPurchaseDetails has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/godown-purchase-details")
    @Timed
    public ResponseEntity<GodownPurchaseDetailsDTO> createGodownPurchaseDetails(@Valid @RequestBody GodownPurchaseDetailsDTO godownPurchaseDetailsDTO) throws URISyntaxException {
        log.debug("REST request to save GodownPurchaseDetails : {}", godownPurchaseDetailsDTO);
        if (godownPurchaseDetailsDTO.getId() != null) {
            throw new BadRequestAlertException("A new godownPurchaseDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GodownPurchaseDetailsDTO result = godownPurchaseDetailsService.save(godownPurchaseDetailsDTO);
        return ResponseEntity.created(new URI("/api/godown-purchase-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /godown-purchase-details : Updates an existing godownPurchaseDetails.
     *
     * @param godownPurchaseDetailsDTO the godownPurchaseDetailsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated godownPurchaseDetailsDTO,
     * or with status 400 (Bad Request) if the godownPurchaseDetailsDTO is not valid,
     * or with status 500 (Internal Server Error) if the godownPurchaseDetailsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/godown-purchase-details")
    @Timed
    public ResponseEntity<GodownPurchaseDetailsDTO> updateGodownPurchaseDetails(@Valid @RequestBody GodownPurchaseDetailsDTO godownPurchaseDetailsDTO) throws URISyntaxException {
        log.debug("REST request to update GodownPurchaseDetails : {}", godownPurchaseDetailsDTO);
        if (godownPurchaseDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GodownPurchaseDetailsDTO result = godownPurchaseDetailsService.save(godownPurchaseDetailsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, godownPurchaseDetailsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /godown-purchase-details : get all the godownPurchaseDetails.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of godownPurchaseDetails in body
     */
    @GetMapping("/godown-purchase-details")
    @Timed
    public ResponseEntity<List<GodownPurchaseDetailsDTO>> getAllGodownPurchaseDetails(GodownPurchaseDetailsCriteria criteria, Pageable pageable) {
        log.debug("REST request to get GodownPurchaseDetails by criteria: {}", criteria);
        Page<GodownPurchaseDetailsDTO> page = godownPurchaseDetailsQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/godown-purchase-details");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /godown-purchase-details/:id : get the "id" godownPurchaseDetails.
     *
     * @param id the id of the godownPurchaseDetailsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the godownPurchaseDetailsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/godown-purchase-details/{id}")
    @Timed
    public ResponseEntity<GodownPurchaseDetailsDTO> getGodownPurchaseDetails(@PathVariable Long id) {
        log.debug("REST request to get GodownPurchaseDetails : {}", id);
        Optional<GodownPurchaseDetailsDTO> godownPurchaseDetailsDTO = godownPurchaseDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(godownPurchaseDetailsDTO);
    }

    /**
     * DELETE  /godown-purchase-details/:id : delete the "id" godownPurchaseDetails.
     *
     * @param id the id of the godownPurchaseDetailsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/godown-purchase-details/{id}")
    @Timed
    public ResponseEntity<Void> deleteGodownPurchaseDetails(@PathVariable Long id) {
        log.debug("REST request to delete GodownPurchaseDetails : {}", id);
        godownPurchaseDetailsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
