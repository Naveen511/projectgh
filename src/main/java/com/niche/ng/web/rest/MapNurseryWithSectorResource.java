/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/22
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs MapNurseryWithSectorResource of CRUD Operation
 *
 *******************************************************************************/
package com.niche.ng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.niche.ng.service.MapNurseryWithSectorService;
import com.niche.ng.web.rest.errors.BadRequestAlertException;
import com.niche.ng.web.rest.util.HeaderUtil;
import com.niche.ng.web.rest.util.PaginationUtil;
import com.niche.ng.service.dto.MapNurseryWithSectorDTO;
import com.niche.ng.service.dto.MapNurseryWithSectorCriteria;
import com.niche.ng.service.MapNurseryWithSectorQueryService;
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
 * REST controller for managing MapNurseryWithSector.
 * Used RequestMapping annotation to map the url with the client side.
 * Using service to access the values in the database.
 */
@RestController
@RequestMapping("/api")
public class MapNurseryWithSectorResource {

    private final Logger log = LoggerFactory.getLogger(MapNurseryWithSectorResource.class);

    private static final String ENTITY_NAME = "mapNurseryWithSector";

    private final MapNurseryWithSectorService mapNurseryWithSectorService;

    private final MapNurseryWithSectorQueryService mapNurseryWithSectorQueryService;

    public MapNurseryWithSectorResource(MapNurseryWithSectorService mapNurseryWithSectorService, MapNurseryWithSectorQueryService mapNurseryWithSectorQueryService) {
        this.mapNurseryWithSectorService = mapNurseryWithSectorService;
        this.mapNurseryWithSectorQueryService = mapNurseryWithSectorQueryService;
    }

    /**
     * POST  /map-nursery-with-sectors : Create a new mapNurseryWithSector.
     *
     * @param mapNurseryWithSectorDTO the mapNurseryWithSectorDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new mapNurseryWithSectorDTO, or with status 400 (Bad Request) if the mapNurseryWithSector has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/map-nursery-with-sectors")
    @Timed
    public ResponseEntity<MapNurseryWithSectorDTO> createMapNurseryWithSector(@Valid @RequestBody MapNurseryWithSectorDTO mapNurseryWithSectorDTO) throws URISyntaxException {
        log.debug("REST request to save MapNurseryWithSector : {}", mapNurseryWithSectorDTO);
        if (mapNurseryWithSectorDTO.getId() != null) {
            throw new BadRequestAlertException("A new mapNurseryWithSector cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MapNurseryWithSectorDTO result = mapNurseryWithSectorService.save(mapNurseryWithSectorDTO);
        return ResponseEntity.created(new URI("/api/map-nursery-with-sectors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /map-nursery-with-sectors : Updates an existing mapNurseryWithSector.
     *
     * @param mapNurseryWithSectorDTO the mapNurseryWithSectorDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated mapNurseryWithSectorDTO,
     * or with status 400 (Bad Request) if the mapNurseryWithSectorDTO is not valid,
     * or with status 500 (Internal Server Error) if the mapNurseryWithSectorDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/map-nursery-with-sectors")
    @Timed
    public ResponseEntity<MapNurseryWithSectorDTO> updateMapNurseryWithSector(@Valid @RequestBody MapNurseryWithSectorDTO mapNurseryWithSectorDTO) throws URISyntaxException {
        log.debug("REST request to update MapNurseryWithSector : {}", mapNurseryWithSectorDTO);
        if (mapNurseryWithSectorDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MapNurseryWithSectorDTO result = mapNurseryWithSectorService.save(mapNurseryWithSectorDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, mapNurseryWithSectorDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /map-nursery-with-sectors : get all the mapNurseryWithSectors.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of mapNurseryWithSectors in body
     */
    @GetMapping("/map-nursery-with-sectors")
    @Timed
    public ResponseEntity<List<MapNurseryWithSectorDTO>> getAllMapNurseryWithSectors(MapNurseryWithSectorCriteria criteria, Pageable pageable) {
        log.debug("REST request to get MapNurseryWithSectors by criteria: {}", criteria);
        Page<MapNurseryWithSectorDTO> page = mapNurseryWithSectorQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/map-nursery-with-sectors");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /map-nursery-with-sectors/:id : get the "id" mapNurseryWithSector.
     *
     * @param id the id of the mapNurseryWithSectorDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the mapNurseryWithSectorDTO, or with status 404 (Not Found)
     */
    @GetMapping("/map-nursery-with-sectors/{id}")
    @Timed
    public ResponseEntity<MapNurseryWithSectorDTO> getMapNurseryWithSector(@PathVariable Long id) {
        log.debug("REST request to get MapNurseryWithSector : {}", id);
        Optional<MapNurseryWithSectorDTO> mapNurseryWithSectorDTO = mapNurseryWithSectorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mapNurseryWithSectorDTO);
    }

    /**
     * DELETE  /map-nursery-with-sectors/:id : delete the "id" mapNurseryWithSector.
     *
     * @param id the id of the mapNurseryWithSectorDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/map-nursery-with-sectors/{id}")
    @Timed
    public ResponseEntity<Void> deleteMapNurseryWithSector(@PathVariable Long id) {
        log.debug("REST request to delete MapNurseryWithSector : {}", id);
        mapNurseryWithSectorService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
