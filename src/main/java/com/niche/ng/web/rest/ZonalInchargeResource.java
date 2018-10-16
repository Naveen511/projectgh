package com.niche.ng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.niche.ng.service.ZonalInchargeService;
import com.niche.ng.web.rest.errors.BadRequestAlertException;
import com.niche.ng.web.rest.util.HeaderUtil;
import com.niche.ng.web.rest.util.PaginationUtil;
import com.niche.ng.service.dto.ZonalInchargeDTO;
import com.niche.ng.service.dto.ZonalInchargeCriteria;
import com.niche.ng.service.ZonalInchargeQueryService;
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
 * REST controller for managing ZonalIncharge.
 */
@RestController
@RequestMapping("/api")
public class ZonalInchargeResource {

    private final Logger log = LoggerFactory.getLogger(ZonalInchargeResource.class);

    private static final String ENTITY_NAME = "zonalIncharge";

    private final ZonalInchargeService zonalInchargeService;

    private final ZonalInchargeQueryService zonalInchargeQueryService;

    public ZonalInchargeResource(ZonalInchargeService zonalInchargeService, ZonalInchargeQueryService zonalInchargeQueryService) {
        this.zonalInchargeService = zonalInchargeService;
        this.zonalInchargeQueryService = zonalInchargeQueryService;
    }

    /**
     * POST  /zonal-incharges : Create a new zonalIncharge.
     *
     * @param zonalInchargeDTO the zonalInchargeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new zonalInchargeDTO, or with status 400 (Bad Request) if the zonalIncharge has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/zonal-incharges")
    @Timed
    public ResponseEntity<ZonalInchargeDTO> createZonalIncharge(@RequestBody ZonalInchargeDTO zonalInchargeDTO) throws URISyntaxException {
        log.debug("REST request to save ZonalIncharge : {}", zonalInchargeDTO);
        if (zonalInchargeDTO.getId() != null) {
            throw new BadRequestAlertException("A new zonalIncharge cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ZonalInchargeDTO result = zonalInchargeService.save(zonalInchargeDTO);
        return ResponseEntity.created(new URI("/api/zonal-incharges/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /zonal-incharges : Updates an existing zonalIncharge.
     *
     * @param zonalInchargeDTO the zonalInchargeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated zonalInchargeDTO,
     * or with status 400 (Bad Request) if the zonalInchargeDTO is not valid,
     * or with status 500 (Internal Server Error) if the zonalInchargeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/zonal-incharges")
    @Timed
    public ResponseEntity<ZonalInchargeDTO> updateZonalIncharge(@RequestBody ZonalInchargeDTO zonalInchargeDTO) throws URISyntaxException {
        log.debug("REST request to update ZonalIncharge : {}", zonalInchargeDTO);
        if (zonalInchargeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ZonalInchargeDTO result = zonalInchargeService.save(zonalInchargeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, zonalInchargeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /zonal-incharges : get all the zonalIncharges.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of zonalIncharges in body
     */
    @GetMapping("/zonal-incharges")
    @Timed
    public ResponseEntity<List<ZonalInchargeDTO>> getAllZonalIncharges(ZonalInchargeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get ZonalIncharges by criteria: {}", criteria);
        Page<ZonalInchargeDTO> page = zonalInchargeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/zonal-incharges");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /zonal-incharges/:id : get the "id" zonalIncharge.
     *
     * @param id the id of the zonalInchargeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the zonalInchargeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/zonal-incharges/{id}")
    @Timed
    public ResponseEntity<ZonalInchargeDTO> getZonalIncharge(@PathVariable Long id) {
        log.debug("REST request to get ZonalIncharge : {}", id);
        Optional<ZonalInchargeDTO> zonalInchargeDTO = zonalInchargeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(zonalInchargeDTO);
    }

    /**
     * DELETE  /zonal-incharges/:id : delete the "id" zonalIncharge.
     *
     * @param id the id of the zonalInchargeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/zonal-incharges/{id}")
    @Timed
    public ResponseEntity<Void> deleteZonalIncharge(@PathVariable Long id) {
        log.debug("REST request to delete ZonalIncharge : {}", id);
        zonalInchargeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
