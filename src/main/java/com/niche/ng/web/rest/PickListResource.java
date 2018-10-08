/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/07
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs PickListResource of CRUD Operation
 *
 *******************************************************************************/
package com.niche.ng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.niche.ng.service.PickListService;
import com.niche.ng.web.rest.errors.BadRequestAlertException;
import com.niche.ng.web.rest.util.HeaderUtil;
import com.niche.ng.web.rest.util.PaginationUtil;
import com.niche.ng.service.dto.PickListDTO;
import com.niche.ng.service.dto.PickListCriteria;
import com.niche.ng.service.PickListQueryService;
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
 * REST controller for managing PickList.
 */
@RestController
@RequestMapping("/api")
public class PickListResource {

    private final Logger log = LoggerFactory.getLogger(PickListResource.class);

    private static final String ENTITY_NAME = "pickList";

    private final PickListService pickListService;

    private final PickListQueryService pickListQueryService;

    public PickListResource(PickListService pickListService, PickListQueryService pickListQueryService) {
        this.pickListService = pickListService;
        this.pickListQueryService = pickListQueryService;
    }

    /**
     * POST  /pick-lists : Create a new pickList.
     *
     * @param pickListDTO the pickListDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new pickListDTO, or with status 400 (Bad Request) if the pickList has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pick-lists")
    @Timed
    public ResponseEntity<PickListDTO> createPickList(@Valid @RequestBody PickListDTO pickListDTO) throws URISyntaxException {
        log.debug("REST request to save PickList : {}", pickListDTO);
        if (pickListDTO.getId() != null) {
            throw new BadRequestAlertException("A new pickList cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PickListDTO result = pickListService.save(pickListDTO);
        return ResponseEntity.created(new URI("/api/pick-lists/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pick-lists : Updates an existing pickList.
     *
     * @param pickListDTO the pickListDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated pickListDTO,
     * or with status 400 (Bad Request) if the pickListDTO is not valid,
     * or with status 500 (Internal Server Error) if the pickListDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pick-lists")
    @Timed
    public ResponseEntity<PickListDTO> updatePickList(@Valid @RequestBody PickListDTO pickListDTO) throws URISyntaxException {
        log.debug("REST request to update PickList : {}", pickListDTO);
        if (pickListDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PickListDTO result = pickListService.save(pickListDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, pickListDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pick-lists : get all the pickLists.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of pickLists in body
     */
    @GetMapping("/pick-lists")
    @Timed
    public ResponseEntity<List<PickListDTO>> getAllPickLists(PickListCriteria criteria, Pageable pageable) {
        log.debug("REST request to get PickLists by criteria: {}", criteria);
        Page<PickListDTO> page = pickListQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pick-lists");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /pick-lists/:id : get the "id" pickList.
     *
     * @param id the id of the pickListDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the pickListDTO, or with status 404 (Not Found)
     */
    @GetMapping("/pick-lists/{id}")
    @Timed
    public ResponseEntity<PickListDTO> getPickList(@PathVariable Long id) {
        log.debug("REST request to get PickList : {}", id);
        Optional<PickListDTO> pickListDTO = pickListService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pickListDTO);
    }

    /**
     * DELETE  /pick-lists/:id : delete the "id" pickList.
     *
     * @param id the id of the pickListDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pick-lists/{id}")
    @Timed
    public ResponseEntity<Void> deletePickList(@PathVariable Long id) {
        log.debug("REST request to delete PickList : {}", id);
        pickListService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
