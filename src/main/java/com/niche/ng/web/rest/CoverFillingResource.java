package com.niche.ng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.niche.ng.service.CoverFillingService;
import com.niche.ng.web.rest.errors.BadRequestAlertException;
import com.niche.ng.web.rest.util.HeaderUtil;
import com.niche.ng.web.rest.util.PaginationUtil;
import com.niche.ng.service.dto.CoverFillingDTO;
import com.niche.ng.service.dto.CoverFillingCriteria;
import com.niche.ng.service.CoverFillingQueryService;
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
 * REST controller for managing CoverFilling.
 */
@RestController
@RequestMapping("/api")
public class CoverFillingResource {

    private final Logger log = LoggerFactory.getLogger(CoverFillingResource.class);

    private static final String ENTITY_NAME = "coverFilling";

    private final CoverFillingService coverFillingService;

    private final CoverFillingQueryService coverFillingQueryService;

    public CoverFillingResource(CoverFillingService coverFillingService, CoverFillingQueryService coverFillingQueryService) {
        this.coverFillingService = coverFillingService;
        this.coverFillingQueryService = coverFillingQueryService;
    }

    /**
     * POST  /cover-fillings : Create a new coverFilling.
     *
     * @param coverFillingDTO the coverFillingDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new coverFillingDTO, or with status 400 (Bad Request) if the coverFilling has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/cover-fillings")
    @Timed
    public ResponseEntity<CoverFillingDTO> createCoverFilling(@Valid @RequestBody CoverFillingDTO coverFillingDTO) throws URISyntaxException {
        log.debug("REST request to save CoverFilling : {}", coverFillingDTO);
        if (coverFillingDTO.getId() != null) {
            throw new BadRequestAlertException("A new coverFilling cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CoverFillingDTO result = coverFillingService.save(coverFillingDTO);
        return ResponseEntity.created(new URI("/api/cover-fillings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /cover-fillings : Updates an existing coverFilling.
     *
     * @param coverFillingDTO the coverFillingDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated coverFillingDTO,
     * or with status 400 (Bad Request) if the coverFillingDTO is not valid,
     * or with status 500 (Internal Server Error) if the coverFillingDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/cover-fillings")
    @Timed
    public ResponseEntity<CoverFillingDTO> updateCoverFilling(@Valid @RequestBody CoverFillingDTO coverFillingDTO) throws URISyntaxException {
        log.debug("REST request to update CoverFilling : {}", coverFillingDTO);
        if (coverFillingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CoverFillingDTO result = coverFillingService.save(coverFillingDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, coverFillingDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /cover-fillings : get all the coverFillings.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of coverFillings in body
     */
    @GetMapping("/cover-fillings")
    @Timed
    public ResponseEntity<List<CoverFillingDTO>> getAllCoverFillings(CoverFillingCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CoverFillings by criteria: {}", criteria);
        Page<CoverFillingDTO> page = coverFillingQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/cover-fillings");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /cover-fillings/:id : get the "id" coverFilling.
     *
     * @param id the id of the coverFillingDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the coverFillingDTO, or with status 404 (Not Found)
     */
    @GetMapping("/cover-fillings/{id}")
    @Timed
    public ResponseEntity<CoverFillingDTO> getCoverFilling(@PathVariable Long id) {
        log.debug("REST request to get CoverFilling : {}", id);
        Optional<CoverFillingDTO> coverFillingDTO = coverFillingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(coverFillingDTO);
    }

    /**
     * DELETE  /cover-fillings/:id : delete the "id" coverFilling.
     *
     * @param id the id of the coverFillingDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/cover-fillings/{id}")
    @Timed
    public ResponseEntity<Void> deleteCoverFilling(@PathVariable Long id) {
        log.debug("REST request to delete CoverFilling : {}", id);
        coverFillingService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
