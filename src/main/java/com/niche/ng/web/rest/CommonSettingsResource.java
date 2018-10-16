/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/07
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs CommonSettingsResource of CRUD Operation
 *
 *******************************************************************************/
package com.niche.ng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.niche.ng.service.CommonSettingsService;
import com.niche.ng.web.rest.errors.BadRequestAlertException;
import com.niche.ng.web.rest.util.HeaderUtil;
import com.niche.ng.web.rest.util.PaginationUtil;
import com.niche.ng.service.dto.CommonSettingsDTO;
import com.niche.ng.service.dto.CommonSettingsCriteria;
import com.niche.ng.service.CommonSettingsQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing CommonSettings.
 * Used RequestMapping annotation to map the url with the client side.
 * Using service to access the values in the database.
 */
@RestController
@RequestMapping("/api")
public class CommonSettingsResource {

    private final Logger log = LoggerFactory.getLogger(CommonSettingsResource.class);

    private static final String ENTITY_NAME = "commonSettings";

    private final CommonSettingsService commonSettingsService;

    private final CommonSettingsQueryService commonSettingsQueryService;

    public CommonSettingsResource(CommonSettingsService commonSettingsService, CommonSettingsQueryService commonSettingsQueryService) {
        this.commonSettingsService = commonSettingsService;
        this.commonSettingsQueryService = commonSettingsQueryService;
    }

    /**
     * POST  /common-settings : Create a new commonSettings.
     *
     * @param commonSettingsDTO the commonSettingsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new commonSettingsDTO, or with status 400 (Bad Request) if the commonSettings has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/common-settings")
    @Timed
    public ResponseEntity<CommonSettingsDTO> createCommonSettings(@RequestBody CommonSettingsDTO commonSettingsDTO) throws URISyntaxException {
        log.debug("REST request to save CommonSettings : {}", commonSettingsDTO);
        if (commonSettingsDTO.getId() != null) {
            throw new BadRequestAlertException("A new commonSettings cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CommonSettingsDTO result = commonSettingsService.save(commonSettingsDTO);
        return ResponseEntity.created(new URI("/api/common-settings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /common-settings : Updates an existing commonSettings.
     *
     * @param commonSettingsDTO the commonSettingsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated commonSettingsDTO,
     * or with status 400 (Bad Request) if the commonSettingsDTO is not valid,
     * or with status 500 (Internal Server Error) if the commonSettingsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/common-settings")
    @Timed
    public ResponseEntity<CommonSettingsDTO> updateCommonSettings(@RequestBody CommonSettingsDTO commonSettingsDTO) throws URISyntaxException {
        log.debug("REST request to update CommonSettings : {}", commonSettingsDTO);
        if (commonSettingsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CommonSettingsDTO result = commonSettingsService.save(commonSettingsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, commonSettingsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /common-settings : get all the commonSettings.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of commonSettings in body
     */
    @GetMapping("/common-settings")
    @Timed
    public ResponseEntity<List<CommonSettingsDTO>> getAllCommonSettings(CommonSettingsCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CommonSettings by criteria: {}", criteria);
        Page<CommonSettingsDTO> page = commonSettingsQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/common-settings");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /common-settings/:id : get the "id" commonSettings.
     *
     * @param id the id of the commonSettingsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the commonSettingsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/common-settings/{id}")
    @Timed
    public ResponseEntity<CommonSettingsDTO> getCommonSettings(@PathVariable Long id) {
        log.debug("REST request to get CommonSettings : {}", id);
        Optional<CommonSettingsDTO> commonSettingsDTO = commonSettingsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(commonSettingsDTO);
    }

    /**
     * DELETE  /common-settings/:id : delete the "id" commonSettings.
     *
     * @param id the id of the commonSettingsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/common-settings/{id}")
    @Timed
    public ResponseEntity<Void> deleteCommonSettings(@PathVariable Long id) {
        log.debug("REST request to delete CommonSettings : {}", id);
        commonSettingsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
