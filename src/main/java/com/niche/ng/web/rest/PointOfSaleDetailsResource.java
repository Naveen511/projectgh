package com.niche.ng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.niche.ng.service.PointOfSaleDetailsService;
import com.niche.ng.web.rest.errors.BadRequestAlertException;
import com.niche.ng.web.rest.util.HeaderUtil;
import com.niche.ng.web.rest.util.PaginationUtil;
import com.niche.ng.service.dto.PointOfSaleDetailsDTO;
import com.niche.ng.service.dto.PointOfSaleDetailsCriteria;
import com.niche.ng.service.PointOfSaleDetailsQueryService;
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
 * REST controller for managing PointOfSaleDetails.
 */
@RestController
@RequestMapping("/api")
public class PointOfSaleDetailsResource {

    private final Logger log = LoggerFactory.getLogger(PointOfSaleDetailsResource.class);

    private static final String ENTITY_NAME = "pointOfSaleDetails";

    private final PointOfSaleDetailsService pointOfSaleDetailsService;

    private final PointOfSaleDetailsQueryService pointOfSaleDetailsQueryService;

    public PointOfSaleDetailsResource(PointOfSaleDetailsService pointOfSaleDetailsService, PointOfSaleDetailsQueryService pointOfSaleDetailsQueryService) {
        this.pointOfSaleDetailsService = pointOfSaleDetailsService;
        this.pointOfSaleDetailsQueryService = pointOfSaleDetailsQueryService;
    }

    /**
     * POST  /point-of-sale-details : Create a new pointOfSaleDetails.
     *
     * @param pointOfSaleDetailsDTO the pointOfSaleDetailsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new pointOfSaleDetailsDTO, or with status 400 (Bad Request) if the pointOfSaleDetails has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/point-of-sale-details")
    @Timed
    public ResponseEntity<PointOfSaleDetailsDTO> createPointOfSaleDetails(@RequestBody PointOfSaleDetailsDTO pointOfSaleDetailsDTO) throws URISyntaxException {
        log.debug("REST request to save PointOfSaleDetails : {}", pointOfSaleDetailsDTO);
        if (pointOfSaleDetailsDTO.getId() != null) {
            throw new BadRequestAlertException("A new pointOfSaleDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PointOfSaleDetailsDTO result = pointOfSaleDetailsService.save(pointOfSaleDetailsDTO);
        return ResponseEntity.created(new URI("/api/point-of-sale-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /point-of-sale-details : Updates an existing pointOfSaleDetails.
     *
     * @param pointOfSaleDetailsDTO the pointOfSaleDetailsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated pointOfSaleDetailsDTO,
     * or with status 400 (Bad Request) if the pointOfSaleDetailsDTO is not valid,
     * or with status 500 (Internal Server Error) if the pointOfSaleDetailsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/point-of-sale-details")
    @Timed
    public ResponseEntity<PointOfSaleDetailsDTO> updatePointOfSaleDetails(@RequestBody PointOfSaleDetailsDTO pointOfSaleDetailsDTO) throws URISyntaxException {
        log.debug("REST request to update PointOfSaleDetails : {}", pointOfSaleDetailsDTO);
        if (pointOfSaleDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PointOfSaleDetailsDTO result = pointOfSaleDetailsService.save(pointOfSaleDetailsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, pointOfSaleDetailsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /point-of-sale-details : get all the pointOfSaleDetails.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of pointOfSaleDetails in body
     */
    @GetMapping("/point-of-sale-details")
    @Timed
    public ResponseEntity<List<PointOfSaleDetailsDTO>> getAllPointOfSaleDetails(PointOfSaleDetailsCriteria criteria, Pageable pageable) {
        log.debug("REST request to get PointOfSaleDetails by criteria: {}", criteria);
        Page<PointOfSaleDetailsDTO> page = pointOfSaleDetailsQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/point-of-sale-details");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /point-of-sale-details/:id : get the "id" pointOfSaleDetails.
     *
     * @param id the id of the pointOfSaleDetailsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the pointOfSaleDetailsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/point-of-sale-details/{id}")
    @Timed
    public ResponseEntity<PointOfSaleDetailsDTO> getPointOfSaleDetails(@PathVariable Long id) {
        log.debug("REST request to get PointOfSaleDetails : {}", id);
        Optional<PointOfSaleDetailsDTO> pointOfSaleDetailsDTO = pointOfSaleDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pointOfSaleDetailsDTO);
    }

    /**
     * DELETE  /point-of-sale-details/:id : delete the "id" pointOfSaleDetails.
     *
     * @param id the id of the pointOfSaleDetailsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/point-of-sale-details/{id}")
    @Timed
    public ResponseEntity<Void> deletePointOfSaleDetails(@PathVariable Long id) {
        log.debug("REST request to delete PointOfSaleDetails : {}", id);
        pointOfSaleDetailsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
