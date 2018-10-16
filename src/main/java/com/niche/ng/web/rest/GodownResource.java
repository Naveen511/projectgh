/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/07
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs GodownResource of CRUD Operation
 *
 *******************************************************************************/
package com.niche.ng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.niche.ng.service.GodownService;
import com.niche.ng.web.rest.errors.BadRequestAlertException;
import com.niche.ng.web.rest.util.HeaderUtil;
import com.niche.ng.web.rest.util.PaginationUtil;
import com.niche.ng.service.dto.GodownDTO;
import com.niche.ng.service.dto.GodownCriteria;
import com.niche.ng.service.GodownQueryService;
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
 * REST controller for managing Godown.
 * Used RequestMapping annotation to map the url with the client side.
 * Using service to access the values in the database.
 */
@RestController
@RequestMapping("/api")
public class GodownResource {

    private final Logger log = LoggerFactory.getLogger(GodownResource.class);

    private static final String ENTITY_NAME = "godown";

    private final GodownService godownService;

    private final GodownQueryService godownQueryService;

    public GodownResource(GodownService godownService, GodownQueryService godownQueryService) {
        this.godownService = godownService;
        this.godownQueryService = godownQueryService;
    }

    /**
     * POST  /godowns : Create a new godown.
     *
     * @param godownDTO the godownDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new godownDTO, or with status 400 (Bad Request) if the godown has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/godowns")
    @Timed
    public ResponseEntity<GodownDTO> createGodown(@Valid @RequestBody GodownDTO godownDTO) throws URISyntaxException {
        log.debug("REST request to save Godown : {}", godownDTO);
        if (godownDTO.getId() != null) {
            throw new BadRequestAlertException("A new godown cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GodownDTO result = godownService.save(godownDTO);
        return ResponseEntity.created(new URI("/api/godowns/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /godowns : Updates an existing godown.
     *
     * @param godownDTO the godownDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated godownDTO,
     * or with status 400 (Bad Request) if the godownDTO is not valid,
     * or with status 500 (Internal Server Error) if the godownDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/godowns")
    @Timed
    public ResponseEntity<GodownDTO> updateGodown(@Valid @RequestBody GodownDTO godownDTO) throws URISyntaxException {
        log.debug("REST request to update Godown : {}", godownDTO);
        if (godownDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GodownDTO result = godownService.save(godownDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, godownDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /godowns : get all the godowns.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of godowns in body
     */
    @GetMapping("/godowns")
    @Timed
    public ResponseEntity<List<GodownDTO>> getAllGodowns(GodownCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Godowns by criteria: {}", criteria);
        Page<GodownDTO> page = godownQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/godowns");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /godowns/:id : get the "id" godown.
     *
     * @param id the id of the godownDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the godownDTO, or with status 404 (Not Found)
     */
    @GetMapping("/godowns/{id}")
    @Timed
    public ResponseEntity<GodownDTO> getGodown(@PathVariable Long id) {
        log.debug("REST request to get Godown : {}", id);
        Optional<GodownDTO> godownDTO = godownService.findOne(id);
        return ResponseUtil.wrapOrNotFound(godownDTO);
    }

    /**
     * DELETE  /godowns/:id : delete the "id" godown.
     *
     * @param id the id of the godownDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/godowns/{id}")
    @Timed
    public ResponseEntity<Void> deleteGodown(@PathVariable Long id) {
        log.debug("REST request to delete Godown : {}", id);
        godownService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
