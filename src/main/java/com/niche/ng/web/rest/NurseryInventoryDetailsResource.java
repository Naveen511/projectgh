/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/09/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryInventoryDetailsResource of CRUD Operation
 *
 *******************************************************************************/
package com.niche.ng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.niche.ng.service.NurseryInventoryDetailsService;
import com.niche.ng.web.rest.errors.BadRequestAlertException;
import com.niche.ng.web.rest.util.HeaderUtil;
import com.niche.ng.web.rest.util.PaginationUtil;
import com.niche.ng.service.dto.NurseryInventoryDetailsDTO;
import com.niche.ng.service.dto.NurseryInventoryDetailsCriteria;
import com.niche.ng.service.NurseryInventoryDetailsQueryService;
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
 * REST controller for managing NurseryInventoryDetails.
 * Used RequestMapping annotation to map the url with the client side.
 * Using service to access the values in the database.
 */
@RestController
@RequestMapping("/api")
public class NurseryInventoryDetailsResource {

    private final Logger log = LoggerFactory.getLogger(NurseryInventoryDetailsResource.class);

    private static final String ENTITY_NAME = "nurseryInventoryDetails";

    private final NurseryInventoryDetailsService nurseryInventoryDetailsService;

    private final NurseryInventoryDetailsQueryService nurseryInventoryDetailsQueryService;

    public NurseryInventoryDetailsResource(NurseryInventoryDetailsService nurseryInventoryDetailsService, NurseryInventoryDetailsQueryService nurseryInventoryDetailsQueryService) {
        this.nurseryInventoryDetailsService = nurseryInventoryDetailsService;
        this.nurseryInventoryDetailsQueryService = nurseryInventoryDetailsQueryService;
    }

    /**
     * POST  /nursery-inventory-details : Create a new nurseryInventoryDetails.
     *
     * @param nurseryInventoryDetailsDTO the nurseryInventoryDetailsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new nurseryInventoryDetailsDTO, or with status 400 (Bad Request) if the nurseryInventoryDetails has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/nursery-inventory-details")
    @Timed
    public ResponseEntity<NurseryInventoryDetailsDTO> createNurseryInventoryDetails(@Valid @RequestBody NurseryInventoryDetailsDTO nurseryInventoryDetailsDTO) throws URISyntaxException {
        log.debug("REST request to save NurseryInventoryDetails : {}", nurseryInventoryDetailsDTO);
        if (nurseryInventoryDetailsDTO.getId() != null) {
            throw new BadRequestAlertException("A new nurseryInventoryDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NurseryInventoryDetailsDTO result = nurseryInventoryDetailsService.save(nurseryInventoryDetailsDTO);
        return ResponseEntity.created(new URI("/api/nursery-inventory-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /nursery-inventory-details : Updates an existing nurseryInventoryDetails.
     *
     * @param nurseryInventoryDetailsDTO the nurseryInventoryDetailsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated nurseryInventoryDetailsDTO,
     * or with status 400 (Bad Request) if the nurseryInventoryDetailsDTO is not valid,
     * or with status 500 (Internal Server Error) if the nurseryInventoryDetailsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/nursery-inventory-details")
    @Timed
    public ResponseEntity<NurseryInventoryDetailsDTO> updateNurseryInventoryDetails(@Valid @RequestBody NurseryInventoryDetailsDTO nurseryInventoryDetailsDTO) throws URISyntaxException {
        log.debug("REST request to update NurseryInventoryDetails : {}", nurseryInventoryDetailsDTO);
        if (nurseryInventoryDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NurseryInventoryDetailsDTO result = nurseryInventoryDetailsService.save(nurseryInventoryDetailsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, nurseryInventoryDetailsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /nursery-inventory-details : get all the nurseryInventoryDetails.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of nurseryInventoryDetails in body
     */
    @GetMapping("/nursery-inventory-details")
    @Timed
    public ResponseEntity<List<NurseryInventoryDetailsDTO>> getAllNurseryInventoryDetails(NurseryInventoryDetailsCriteria criteria, Pageable pageable) {
        log.debug("REST request to get NurseryInventoryDetails by criteria: {}", criteria);
        Page<NurseryInventoryDetailsDTO> page = nurseryInventoryDetailsQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/nursery-inventory-details");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /nursery-inventory-details/:id : get the "id" nurseryInventoryDetails.
     *
     * @param id the id of the nurseryInventoryDetailsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the nurseryInventoryDetailsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/nursery-inventory-details/{id}")
    @Timed
    public ResponseEntity<NurseryInventoryDetailsDTO> getNurseryInventoryDetails(@PathVariable Long id) {
        log.debug("REST request to get NurseryInventoryDetails : {}", id);
        Optional<NurseryInventoryDetailsDTO> nurseryInventoryDetailsDTO = nurseryInventoryDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nurseryInventoryDetailsDTO);
    }

    /**
     * DELETE  /nursery-inventory-details/:id : delete the "id" nurseryInventoryDetails.
     *
     * @param id the id of the nurseryInventoryDetailsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/nursery-inventory-details/{id}")
    @Timed
    public ResponseEntity<Void> deleteNurseryInventoryDetails(@PathVariable Long id) {
        log.debug("REST request to delete NurseryInventoryDetails : {}", id);
        nurseryInventoryDetailsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
