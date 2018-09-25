package com.niche.ng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.niche.ng.service.FinancialYearSettingsService;
import com.niche.ng.web.rest.errors.BadRequestAlertException;
import com.niche.ng.web.rest.util.HeaderUtil;
import com.niche.ng.web.rest.util.PaginationUtil;
import com.niche.ng.service.dto.FinancialYearSettingsDTO;
import com.niche.ng.service.dto.FinancialYearSettingsCriteria;
import com.niche.ng.service.FinancialYearSettingsQueryService;
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
 * REST controller for managing FinancialYearSettings.
 */
@RestController
@RequestMapping("/api")
public class FinancialYearSettingsResource {

    private final Logger log = LoggerFactory.getLogger(FinancialYearSettingsResource.class);

    private static final String ENTITY_NAME = "financialYearSettings";

    private final FinancialYearSettingsService financialYearSettingsService;

    private final FinancialYearSettingsQueryService financialYearSettingsQueryService;

    public FinancialYearSettingsResource(FinancialYearSettingsService financialYearSettingsService, FinancialYearSettingsQueryService financialYearSettingsQueryService) {
        this.financialYearSettingsService = financialYearSettingsService;
        this.financialYearSettingsQueryService = financialYearSettingsQueryService;
    }

    /**
     * POST  /financial-year-settings : Create a new financialYearSettings.
     *
     * @param financialYearSettingsDTO the financialYearSettingsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new financialYearSettingsDTO, or with status 400 (Bad Request) if the financialYearSettings has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/financial-year-settings")
    @Timed
    public ResponseEntity<FinancialYearSettingsDTO> createFinancialYearSettings(@Valid @RequestBody FinancialYearSettingsDTO financialYearSettingsDTO) throws URISyntaxException {
        log.debug("REST request to save FinancialYearSettings : {}", financialYearSettingsDTO);
        if (financialYearSettingsDTO.getId() != null) {
            throw new BadRequestAlertException("A new financialYearSettings cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FinancialYearSettingsDTO result = financialYearSettingsService.save(financialYearSettingsDTO);
        return ResponseEntity.created(new URI("/api/financial-year-settings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /financial-year-settings : Updates an existing financialYearSettings.
     *
     * @param financialYearSettingsDTO the financialYearSettingsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated financialYearSettingsDTO,
     * or with status 400 (Bad Request) if the financialYearSettingsDTO is not valid,
     * or with status 500 (Internal Server Error) if the financialYearSettingsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/financial-year-settings")
    @Timed
    public ResponseEntity<FinancialYearSettingsDTO> updateFinancialYearSettings(@Valid @RequestBody FinancialYearSettingsDTO financialYearSettingsDTO) throws URISyntaxException {
        log.debug("REST request to update FinancialYearSettings : {}", financialYearSettingsDTO);
        if (financialYearSettingsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FinancialYearSettingsDTO result = financialYearSettingsService.save(financialYearSettingsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, financialYearSettingsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /financial-year-settings : get all the financialYearSettings.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of financialYearSettings in body
     */
    @GetMapping("/financial-year-settings")
    @Timed
    public ResponseEntity<List<FinancialYearSettingsDTO>> getAllFinancialYearSettings(FinancialYearSettingsCriteria criteria, Pageable pageable) {
        log.debug("REST request to get FinancialYearSettings by criteria: {}", criteria);
        Page<FinancialYearSettingsDTO> page = financialYearSettingsQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/financial-year-settings");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /financial-year-settings/:id : get the "id" financialYearSettings.
     *
     * @param id the id of the financialYearSettingsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the financialYearSettingsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/financial-year-settings/{id}")
    @Timed
    public ResponseEntity<FinancialYearSettingsDTO> getFinancialYearSettings(@PathVariable Long id) {
        log.debug("REST request to get FinancialYearSettings : {}", id);
        Optional<FinancialYearSettingsDTO> financialYearSettingsDTO = financialYearSettingsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(financialYearSettingsDTO);
    }

    /**
     * DELETE  /financial-year-settings/:id : delete the "id" financialYearSettings.
     *
     * @param id the id of the financialYearSettingsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/financial-year-settings/{id}")
    @Timed
    public ResponseEntity<Void> deleteFinancialYearSettings(@PathVariable Long id) {
        log.debug("REST request to delete FinancialYearSettings : {}", id);
        financialYearSettingsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * DELETE  /financial-year-settings/:id : delete the "id" FinancialYearSettings.
     * 
     * @param id the id of the financialYearSettingsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @GetMapping("/financial-year-settings/soft-delete/{id}")
    @Timed
    public ResponseEntity<Void> softDelete(@PathVariable Long id) {
        log.debug("REST request to delete FinancialYearSettings : {}", id);
        financialYearSettingsService.softDelete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * GET  /financial-year-settings/active-record:status : get the "status" financialYearSettings.
     *
     * @param status the status of the financialYearSettingsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the financialYearSettingsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/financial-year-settings/active-record/{status}")
    @Timed
    public ResponseEntity<List<FinancialYearSettingsDTO>> getParticularStatusRecord(@PathVariable Integer status) {
        log.debug("REST request to get a list of particular status FinancialYearSettings");
        List<FinancialYearSettingsDTO> financialYearSettingsDTO = financialYearSettingsService.findActiveRecord(status);
        return ResponseEntity.ok().body(financialYearSettingsDTO);
    }
}
