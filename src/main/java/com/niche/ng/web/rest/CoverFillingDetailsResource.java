/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/07
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs CoverFillingDetailsResource of CRUD Operation
 *
 *******************************************************************************/
package com.niche.ng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.niche.ng.service.CoverFillingDetailsService;
import com.niche.ng.web.rest.errors.BadRequestAlertException;
import com.niche.ng.web.rest.util.HeaderUtil;
import com.niche.ng.web.rest.util.PaginationUtil;
import com.niche.ng.service.dto.CoverFillingDetailsDTO;
import com.niche.ng.service.dto.CoverFillingDetailsCriteria;
import com.niche.ng.service.CoverFillingDetailsQueryService;
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
 * REST controller for managing CoverFillingDetails.
 * Used RequestMapping annotation to map the url with the client side.
 * Using service to access the values in the database.
 */
@RestController
@RequestMapping("/api")
public class CoverFillingDetailsResource {

    private final Logger log = LoggerFactory.getLogger(CoverFillingDetailsResource.class);

    private static final String ENTITY_NAME = "coverFillingDetails";

    private final CoverFillingDetailsService coverFillingDetailsService;

    private final CoverFillingDetailsQueryService coverFillingDetailsQueryService;

    public CoverFillingDetailsResource(CoverFillingDetailsService coverFillingDetailsService, CoverFillingDetailsQueryService coverFillingDetailsQueryService) {
        this.coverFillingDetailsService = coverFillingDetailsService;
        this.coverFillingDetailsQueryService = coverFillingDetailsQueryService;
    }

    /**
     * POST  /cover-filling-details : Create a new coverFillingDetails.
     *
     * @param coverFillingDetailsDTO the coverFillingDetailsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new coverFillingDetailsDTO, or with status 400 (Bad Request) if the coverFillingDetails has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/cover-filling-details")
    @Timed
    public ResponseEntity<CoverFillingDetailsDTO> createCoverFillingDetails(@Valid @RequestBody CoverFillingDetailsDTO coverFillingDetailsDTO) throws URISyntaxException {
        log.debug("REST request to save CoverFillingDetails : {}", coverFillingDetailsDTO);
        if (coverFillingDetailsDTO.getId() != null) {
            throw new BadRequestAlertException("A new coverFillingDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CoverFillingDetailsDTO result = coverFillingDetailsService.save(coverFillingDetailsDTO);
        return ResponseEntity.created(new URI("/api/cover-filling-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /cover-filling-details : Updates an existing coverFillingDetails.
     *
     * @param coverFillingDetailsDTO the coverFillingDetailsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated coverFillingDetailsDTO,
     * or with status 400 (Bad Request) if the coverFillingDetailsDTO is not valid,
     * or with status 500 (Internal Server Error) if the coverFillingDetailsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/cover-filling-details")
    @Timed
    public ResponseEntity<CoverFillingDetailsDTO> updateCoverFillingDetails(@Valid @RequestBody CoverFillingDetailsDTO coverFillingDetailsDTO) throws URISyntaxException {
        log.debug("REST request to update CoverFillingDetails : {}", coverFillingDetailsDTO);
        if (coverFillingDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CoverFillingDetailsDTO result = coverFillingDetailsService.save(coverFillingDetailsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, coverFillingDetailsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /cover-filling-details : get all the coverFillingDetails.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of coverFillingDetails in body
     */
    @GetMapping("/cover-filling-details")
    @Timed
    public ResponseEntity<List<CoverFillingDetailsDTO>> getAllCoverFillingDetails(CoverFillingDetailsCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CoverFillingDetails by criteria: {}", criteria);
        Page<CoverFillingDetailsDTO> page = coverFillingDetailsQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/cover-filling-details");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /cover-filling-details/:id : get the "id" coverFillingDetails.
     *
     * @param id the id of the coverFillingDetailsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the coverFillingDetailsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/cover-filling-details/{id}")
    @Timed
    public ResponseEntity<CoverFillingDetailsDTO> getCoverFillingDetails(@PathVariable Long id) {
        log.debug("REST request to get CoverFillingDetails : {}", id);
        Optional<CoverFillingDetailsDTO> coverFillingDetailsDTO = coverFillingDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(coverFillingDetailsDTO);
    }

    /**
     * DELETE  /cover-filling-details/:id : delete the "id" coverFillingDetails.
     *
     * @param id the id of the coverFillingDetailsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/cover-filling-details/{id}")
    @Timed
    public ResponseEntity<Void> deleteCoverFillingDetails(@PathVariable Long id) {
        log.debug("REST request to delete CoverFillingDetails : {}", id);
        coverFillingDetailsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
