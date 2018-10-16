/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/07
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs DamageResource of CRUD Operation
 *
 *******************************************************************************/
package com.niche.ng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.niche.ng.service.DamageService;
import com.niche.ng.web.rest.errors.BadRequestAlertException;
import com.niche.ng.web.rest.util.HeaderUtil;
import com.niche.ng.web.rest.util.PaginationUtil;
import com.niche.ng.service.dto.DamageDTO;
import com.niche.ng.service.dto.DamageCriteria;
import com.niche.ng.service.DamageQueryService;
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
 * REST controller for managing Damage.
 * Used RequestMapping annotation to map the url with the client side.
 * Using service to access the values in the database.
 */
@RestController
@RequestMapping("/api")
public class DamageResource {

    private final Logger log = LoggerFactory.getLogger(DamageResource.class);

    private static final String ENTITY_NAME = "damage";

    private final DamageService damageService;

    private final DamageQueryService damageQueryService;

    public DamageResource(DamageService damageService, DamageQueryService damageQueryService) {
        this.damageService = damageService;
        this.damageQueryService = damageQueryService;
    }

    /**
     * POST  /damages : Create a new damage.
     *
     * @param damageDTO the damageDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new damageDTO, or with status 400 (Bad Request) if the damage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/damages")
    @Timed
    public ResponseEntity<DamageDTO> createDamage(@Valid @RequestBody DamageDTO damageDTO) throws URISyntaxException {
        log.debug("REST request to save Damage : {}", damageDTO);
        if (damageDTO.getId() != null) {
            throw new BadRequestAlertException("A new damage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DamageDTO result = damageService.save(damageDTO);
        return ResponseEntity.created(new URI("/api/damages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /damages : Updates an existing damage.
     *
     * @param damageDTO the damageDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated damageDTO,
     * or with status 400 (Bad Request) if the damageDTO is not valid,
     * or with status 500 (Internal Server Error) if the damageDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/damages")
    @Timed
    public ResponseEntity<DamageDTO> updateDamage(@Valid @RequestBody DamageDTO damageDTO) throws URISyntaxException {
        log.debug("REST request to update Damage : {}", damageDTO);
        if (damageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DamageDTO result = damageService.save(damageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, damageDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /damages : get all the damages.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of damages in body
     */
    @GetMapping("/damages")
    @Timed
    public ResponseEntity<List<DamageDTO>> getAllDamages(DamageCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Damages by criteria: {}", criteria);
        Page<DamageDTO> page = damageQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/damages");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /damages/:id : get the "id" damage.
     *
     * @param id the id of the damageDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the damageDTO, or with status 404 (Not Found)
     */
    @GetMapping("/damages/{id}")
    @Timed
    public ResponseEntity<DamageDTO> getDamage(@PathVariable Long id) {
        log.debug("REST request to get Damage : {}", id);
        Optional<DamageDTO> damageDTO = damageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(damageDTO);
    }

    /**
     * DELETE  /damages/:id : delete the "id" damage.
     *
     * @param id the id of the damageDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/damages/{id}")
    @Timed
    public ResponseEntity<Void> deleteDamage(@PathVariable Long id) {
        log.debug("REST request to delete Damage : {}", id);
        damageService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * GET  /damages/count:batchId : get the count of damage based on particular status.
     *
     * @param batchId the batchId of the damageDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the damage count, 
     *         or with status 404 (Not Found)
     */
    @GetMapping("/damages/count/{batchId}")
    @Timed
    public String getDamageCount(@PathVariable Long batchId) {
        // log.debug("REST request to get Damage : {}", batchId);
        // log.debug("Damage Count : ", damageService.getDamageCount(batchId));
        return damageService.getDamageCount(batchId);
        // log.debug("Damage Count : ", damageService.getDamageCount(batchId));
        // return ResponseUtil.wrapOrNotFound(damageDTO);
    }
}
