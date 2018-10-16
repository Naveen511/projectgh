/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/07
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs ShadeAreaResource of CRUD Operation
 *
 *******************************************************************************/
package com.niche.ng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.niche.ng.service.ShadeAreaService;
import com.niche.ng.web.rest.errors.BadRequestAlertException;
import com.niche.ng.web.rest.util.HeaderUtil;
import com.niche.ng.web.rest.util.PaginationUtil;
import com.niche.ng.service.dto.ShadeAreaDTO;
import com.niche.ng.service.dto.ShadeAreaCriteria;
import com.niche.ng.service.ShadeAreaQueryService;
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
 * REST controller for managing ShadeArea.
 * Used RequestMapping annotation to map the url with the client side.
 * Using service to access the values in the database.
 */
@RestController
@RequestMapping("/api")
public class ShadeAreaResource {

    private final Logger log = LoggerFactory.getLogger(ShadeAreaResource.class);

    private static final String ENTITY_NAME = "shadeArea";

    private final ShadeAreaService shadeAreaService;

    private final ShadeAreaQueryService shadeAreaQueryService;

    public ShadeAreaResource(ShadeAreaService shadeAreaService, ShadeAreaQueryService shadeAreaQueryService) {
        this.shadeAreaService = shadeAreaService;
        this.shadeAreaQueryService = shadeAreaQueryService;
    }

    /**
     * POST  /shade-areas : Create a new shadeArea.
     *
     * @param shadeAreaDTO the shadeAreaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new shadeAreaDTO, or with status 400 (Bad Request) if the shadeArea has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/shade-areas")
    @Timed
    public ResponseEntity<ShadeAreaDTO> createShadeArea(@Valid @RequestBody ShadeAreaDTO shadeAreaDTO) throws URISyntaxException {
        log.debug("REST request to save ShadeArea : {}", shadeAreaDTO);
        if (shadeAreaDTO.getId() != null) {
            throw new BadRequestAlertException("A new shadeArea cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ShadeAreaDTO result = shadeAreaService.save(shadeAreaDTO);
        return ResponseEntity.created(new URI("/api/shade-areas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /shade-areas : Updates an existing shadeArea.
     *
     * @param shadeAreaDTO the shadeAreaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated shadeAreaDTO,
     * or with status 400 (Bad Request) if the shadeAreaDTO is not valid,
     * or with status 500 (Internal Server Error) if the shadeAreaDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/shade-areas")
    @Timed
    public ResponseEntity<ShadeAreaDTO> updateShadeArea(@Valid @RequestBody ShadeAreaDTO shadeAreaDTO) throws URISyntaxException {
        log.debug("REST request to update ShadeArea : {}", shadeAreaDTO);
        if (shadeAreaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ShadeAreaDTO result = shadeAreaService.save(shadeAreaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, shadeAreaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /shade-areas : get all the shadeAreas.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of shadeAreas in body
     */
    @GetMapping("/shade-areas")
    @Timed
    public ResponseEntity<List<ShadeAreaDTO>> getAllShadeAreas(ShadeAreaCriteria criteria, Pageable pageable) {
        log.debug("REST request to get ShadeAreas by criteria: {}", criteria);
        Page<ShadeAreaDTO> page = shadeAreaQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/shade-areas");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /shade-areas/:id : get the "id" shadeArea.
     *
     * @param id the id of the shadeAreaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the shadeAreaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/shade-areas/{id}")
    @Timed
    public ResponseEntity<ShadeAreaDTO> getShadeArea(@PathVariable Long id) {
        log.debug("REST request to get ShadeArea : {}", id);
        Optional<ShadeAreaDTO> shadeAreaDTO = shadeAreaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(shadeAreaDTO);
    }

    /**
     * DELETE  /shade-areas/:id : delete the "id" shadeArea.
     *
     * @param id the id of the shadeAreaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/shade-areas/{id}")
    @Timed
    public ResponseEntity<Void> deleteShadeArea(@PathVariable Long id) {
        log.debug("REST request to delete ShadeArea : {}", id);
        shadeAreaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * GET  /shade-areas/count:batchId : get the "batchId" shade area records.
     *
     * @param batchId the batchId of the ShadeAreaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the shade count, 
     *         or with status 404 (Not Found)
     */
    @GetMapping("/shade-areas/count/{batchId}")
    @Timed
    public String getShadeCount(@PathVariable Long batchId) {
        return shadeAreaService.getShadeAreaCount(batchId);
    }
}
