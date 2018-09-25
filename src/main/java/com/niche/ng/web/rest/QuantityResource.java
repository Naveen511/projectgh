package com.niche.ng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.niche.ng.service.QuantityService;
import com.niche.ng.web.rest.errors.BadRequestAlertException;
import com.niche.ng.web.rest.util.HeaderUtil;
import com.niche.ng.web.rest.util.PaginationUtil;
import com.niche.ng.service.dto.QuantityDTO;
import com.niche.ng.service.dto.QuantityCriteria;
import com.niche.ng.service.QuantityQueryService;
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
 * REST controller for managing Quantity.
 */
@RestController
@RequestMapping("/api")
public class QuantityResource {

    private final Logger log = LoggerFactory.getLogger(QuantityResource.class);

    private static final String ENTITY_NAME = "quantity";

    private final QuantityService quantityService;

    private final QuantityQueryService quantityQueryService;

    public QuantityResource(QuantityService quantityService, QuantityQueryService quantityQueryService) {
        this.quantityService = quantityService;
        this.quantityQueryService = quantityQueryService;
    }

    /**
     * POST  /quantities : Create a new quantity.
     *
     * @param quantityDTO the quantityDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new quantityDTO, or with status 400 (Bad Request) if the quantity has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/quantities")
    @Timed
    public ResponseEntity<QuantityDTO> createQuantity(@Valid @RequestBody QuantityDTO quantityDTO) throws URISyntaxException {
        log.debug("REST request to save Quantity : {}", quantityDTO);
        if (quantityDTO.getId() != null) {
            throw new BadRequestAlertException("A new quantity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QuantityDTO result = quantityService.save(quantityDTO);
        return ResponseEntity.created(new URI("/api/quantities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /quantities : Updates an existing quantity.
     *
     * @param quantityDTO the quantityDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated quantityDTO,
     * or with status 400 (Bad Request) if the quantityDTO is not valid,
     * or with status 500 (Internal Server Error) if the quantityDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/quantities")
    @Timed
    public ResponseEntity<QuantityDTO> updateQuantity(@Valid @RequestBody QuantityDTO quantityDTO) throws URISyntaxException {
        log.debug("REST request to update Quantity : {}", quantityDTO);
        if (quantityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        QuantityDTO result = quantityService.save(quantityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, quantityDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /quantities : get all the quantities.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of quantities in body
     */
    @GetMapping("/quantities")
    @Timed
    public ResponseEntity<List<QuantityDTO>> getAllQuantities(QuantityCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Quantities by criteria: {}", criteria);
        Page<QuantityDTO> page = quantityQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/quantities");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /quantities/:id : get the "id" quantity.
     *
     * @param id the id of the quantityDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the quantityDTO, or with status 404 (Not Found)
     */
    @GetMapping("/quantities/{id}")
    @Timed
    public ResponseEntity<QuantityDTO> getQuantity(@PathVariable Long id) {
        log.debug("REST request to get Quantity : {}", id);
        Optional<QuantityDTO> quantityDTO = quantityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(quantityDTO);
    }

    /**
     * DELETE  /quantities/:id : delete the "id" quantity.
     *
     * @param id the id of the quantityDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/quantities/{id}")
    @Timed
    public ResponseEntity<Void> deleteQuantity(@PathVariable Long id) {
        log.debug("REST request to delete Quantity : {}", id);
        quantityService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * DELETE  /quantities/:id : delete the "id" quantity.
     * 
     * @param id the id of the quantityDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @GetMapping("/quantities/soft-delete/{id}")
    @Timed
    public ResponseEntity<Void> softDelete(@PathVariable Long id) {
        log.debug("REST request to delete quantity : {}", id);
        quantityService.softDelete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
