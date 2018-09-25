package com.niche.ng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.niche.ng.service.MapZonalWithOhService;
import com.niche.ng.web.rest.errors.BadRequestAlertException;
import com.niche.ng.web.rest.util.HeaderUtil;
import com.niche.ng.web.rest.util.PaginationUtil;
import com.niche.ng.service.dto.MapZonalWithOhDTO;
import com.niche.ng.service.dto.MapZonalWithOhCriteria;
import com.niche.ng.service.MapZonalWithOhQueryService;
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
 * REST controller for managing MapZonalWithOh.
 */
@RestController
@RequestMapping("/api")
public class MapZonalWithOhResource {

    private final Logger log = LoggerFactory.getLogger(MapZonalWithOhResource.class);

    private static final String ENTITY_NAME = "mapZonalWithOh";

    private final MapZonalWithOhService mapZonalWithOhService;

    private final MapZonalWithOhQueryService mapZonalWithOhQueryService;

    public MapZonalWithOhResource(MapZonalWithOhService mapZonalWithOhService, MapZonalWithOhQueryService mapZonalWithOhQueryService) {
        this.mapZonalWithOhService = mapZonalWithOhService;
        this.mapZonalWithOhQueryService = mapZonalWithOhQueryService;
    }

    /**
     * POST  /map-zonal-with-ohs : Create a new mapZonalWithOh.
     *
     * @param mapZonalWithOhDTO the mapZonalWithOhDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new mapZonalWithOhDTO, or with status 400 (Bad Request) if the mapZonalWithOh has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/map-zonal-with-ohs")
    @Timed
    public ResponseEntity<MapZonalWithOhDTO> createMapZonalWithOh(@RequestBody MapZonalWithOhDTO mapZonalWithOhDTO) throws URISyntaxException {
        log.debug("REST request to save MapZonalWithOh : {}", mapZonalWithOhDTO);
        if (mapZonalWithOhDTO.getId() != null) {
            throw new BadRequestAlertException("A new mapZonalWithOh cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MapZonalWithOhDTO result = mapZonalWithOhService.save(mapZonalWithOhDTO);
        return ResponseEntity.created(new URI("/api/map-zonal-with-ohs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /map-zonal-with-ohs : Updates an existing mapZonalWithOh.
     *
     * @param mapZonalWithOhDTO the mapZonalWithOhDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated mapZonalWithOhDTO,
     * or with status 400 (Bad Request) if the mapZonalWithOhDTO is not valid,
     * or with status 500 (Internal Server Error) if the mapZonalWithOhDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/map-zonal-with-ohs")
    @Timed
    public ResponseEntity<MapZonalWithOhDTO> updateMapZonalWithOh(@RequestBody MapZonalWithOhDTO mapZonalWithOhDTO) throws URISyntaxException {
        log.debug("REST request to update MapZonalWithOh : {}", mapZonalWithOhDTO);
        if (mapZonalWithOhDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MapZonalWithOhDTO result = mapZonalWithOhService.save(mapZonalWithOhDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, mapZonalWithOhDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /map-zonal-with-ohs : get all the mapZonalWithOhs.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of mapZonalWithOhs in body
     */
    @GetMapping("/map-zonal-with-ohs")
    @Timed
    public ResponseEntity<List<MapZonalWithOhDTO>> getAllMapZonalWithOhs(MapZonalWithOhCriteria criteria, Pageable pageable) {
        log.debug("REST request to get MapZonalWithOhs by criteria: {}", criteria);
        Page<MapZonalWithOhDTO> page = mapZonalWithOhQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/map-zonal-with-ohs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /map-zonal-with-ohs/:id : get the "id" mapZonalWithOh.
     *
     * @param id the id of the mapZonalWithOhDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the mapZonalWithOhDTO, or with status 404 (Not Found)
     */
    @GetMapping("/map-zonal-with-ohs/{id}")
    @Timed
    public ResponseEntity<MapZonalWithOhDTO> getMapZonalWithOh(@PathVariable Long id) {
        log.debug("REST request to get MapZonalWithOh : {}", id);
        Optional<MapZonalWithOhDTO> mapZonalWithOhDTO = mapZonalWithOhService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mapZonalWithOhDTO);
    }

    /**
     * DELETE  /map-zonal-with-ohs/:id : delete the "id" mapZonalWithOh.
     *
     * @param id the id of the mapZonalWithOhDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/map-zonal-with-ohs/{id}")
    @Timed
    public ResponseEntity<Void> deleteMapZonalWithOh(@PathVariable Long id) {
        log.debug("REST request to delete MapZonalWithOh : {}", id);
        mapZonalWithOhService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * GET  /map-zonal-with-ohs/zoanl/:zonalId/:status : get all the sectors of particular zonalId.
     *
     * @param zonalId the zonalId of the MapZonalWithOhDTO to retrieve
     * @param status the status of the MapZonalWithOhDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and the list of sectors in body
     */
    @GetMapping("/map-zonal-with-ohs/zonal/{zonalId}/{status}")
    @Timed
    public ResponseEntity<List<MapZonalWithOhDTO>> getParticularZonalActiveRecord(@PathVariable Long zonalId, @PathVariable Integer status) {
        log.debug("REST request to get a list of particular zonal active record");
        List<MapZonalWithOhDTO> list = mapZonalWithOhService.findParticularZonalActiveRecord(zonalId, status);
        return ResponseEntity.ok().body(list);
    }

    /**
     * GET  /map-zonal-with-ohs/zoanl/:zonalId : get all the sectors of particular zonalId.
     *
     * @param zonalId the zonalId of the MapZonalWithOhDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and the list of sectors in body
     */
    @GetMapping("/map-zonal-with-ohs/zonal/{zonalId}")
    @Timed
    public ResponseEntity<List<MapZonalWithOhDTO>> getParticularZonalRecord(@PathVariable Long zonalId) {
        log.debug("REST request to get a list of particular zonal record");
        List<MapZonalWithOhDTO> list = mapZonalWithOhService.findParticularZonalRecord(zonalId);
        return ResponseEntity.ok().body(list);
    }
}
