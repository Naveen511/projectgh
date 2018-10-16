/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/22
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs MapSectorWithZonalResource of CRUD Operation
 *
 *******************************************************************************/
package com.niche.ng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.niche.ng.service.MapSectorWithZonalService;
import com.niche.ng.web.rest.errors.BadRequestAlertException;
import com.niche.ng.web.rest.util.HeaderUtil;
import com.niche.ng.web.rest.util.PaginationUtil;
import com.niche.ng.service.dto.MapSectorWithZonalDTO;
import com.niche.ng.service.dto.MapSectorWithZonalCriteria;
import com.niche.ng.service.MapSectorWithZonalQueryService;
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
 * REST controller for managing MapSectorWithZonal.
 * Used RequestMapping annotation to map the url with the client side.
 * Using service to access the values in the database.
 */
@RestController
@RequestMapping("/api")
public class MapSectorWithZonalResource {

    private final Logger log = LoggerFactory.getLogger(MapSectorWithZonalResource.class);

    private static final String ENTITY_NAME = "mapSectorWithZonal";

    private final MapSectorWithZonalService mapSectorWithZonalService;

    private final MapSectorWithZonalQueryService mapSectorWithZonalQueryService;

    public MapSectorWithZonalResource(MapSectorWithZonalService mapSectorWithZonalService, MapSectorWithZonalQueryService mapSectorWithZonalQueryService) {
        this.mapSectorWithZonalService = mapSectorWithZonalService;
        this.mapSectorWithZonalQueryService = mapSectorWithZonalQueryService;
    }

    /**
     * POST  /map-sector-with-zonals : Create a new mapSectorWithZonal.
     *
     * @param mapSectorWithZonalDTO the mapSectorWithZonalDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new mapSectorWithZonalDTO, or with status 400 (Bad Request) if the mapSectorWithZonal has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/map-sector-with-zonals")
    @Timed
    public ResponseEntity<MapSectorWithZonalDTO> createMapSectorWithZonal(@Valid @RequestBody MapSectorWithZonalDTO mapSectorWithZonalDTO) throws URISyntaxException {
        log.debug("REST request to save MapSectorWithZonal : {}", mapSectorWithZonalDTO);
        if (mapSectorWithZonalDTO.getId() != null) {
            throw new BadRequestAlertException("A new mapSectorWithZonal cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MapSectorWithZonalDTO result = mapSectorWithZonalService.save(mapSectorWithZonalDTO);
        return ResponseEntity.created(new URI("/api/map-sector-with-zonals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /map-sector-with-zonals : Updates an existing mapSectorWithZonal.
     *
     * @param mapSectorWithZonalDTO the mapSectorWithZonalDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated mapSectorWithZonalDTO,
     * or with status 400 (Bad Request) if the mapSectorWithZonalDTO is not valid,
     * or with status 500 (Internal Server Error) if the mapSectorWithZonalDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/map-sector-with-zonals")
    @Timed
    public ResponseEntity<MapSectorWithZonalDTO> updateMapSectorWithZonal(@Valid @RequestBody MapSectorWithZonalDTO mapSectorWithZonalDTO) throws URISyntaxException {
        log.debug("REST request to update MapSectorWithZonal : {}", mapSectorWithZonalDTO);
        if (mapSectorWithZonalDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MapSectorWithZonalDTO result = mapSectorWithZonalService.save(mapSectorWithZonalDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, mapSectorWithZonalDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /map-sector-with-zonals : get all the mapSectorWithZonals.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of mapSectorWithZonals in body
     */
    @GetMapping("/map-sector-with-zonals")
    @Timed
    public ResponseEntity<List<MapSectorWithZonalDTO>> getAllMapSectorWithZonals(MapSectorWithZonalCriteria criteria, Pageable pageable) {
        log.debug("REST request to get MapSectorWithZonals by criteria: {}", criteria);
        Page<MapSectorWithZonalDTO> page = mapSectorWithZonalQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/map-sector-with-zonals");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /map-sector-with-zonals/:id : get the "id" mapSectorWithZonal.
     *
     * @param id the id of the mapSectorWithZonalDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the mapSectorWithZonalDTO, or with status 404 (Not Found)
     */
    @GetMapping("/map-sector-with-zonals/{id}")
    @Timed
    public ResponseEntity<MapSectorWithZonalDTO> getMapSectorWithZonal(@PathVariable Long id) {
        log.debug("REST request to get MapSectorWithZonal : {}", id);
        Optional<MapSectorWithZonalDTO> mapSectorWithZonalDTO = mapSectorWithZonalService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mapSectorWithZonalDTO);
    }

    /**
     * DELETE  /map-sector-with-zonals/:id : delete the "id" mapSectorWithZonal.
     *
     * @param id the id of the mapSectorWithZonalDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/map-sector-with-zonals/{id}")
    @Timed
    public ResponseEntity<Void> deleteMapSectorWithZonal(@PathVariable Long id) {
        log.debug("REST request to delete MapSectorWithZonal : {}", id);
        mapSectorWithZonalService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
