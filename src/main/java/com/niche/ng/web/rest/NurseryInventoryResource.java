package com.niche.ng.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.niche.ng.service.NurseryInventoryService;
import com.niche.ng.web.rest.errors.BadRequestAlertException;
import com.niche.ng.web.rest.util.HeaderUtil;
import com.niche.ng.web.rest.util.PaginationUtil;
import com.niche.ng.service.dto.NurseryInventoryDTO;
import com.niche.ng.service.dto.NurseryInventoryCriteria;
import com.niche.ng.service.NurseryInventoryQueryService;
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
 * REST controller for managing NurseryInventory.
 */
@RestController
@RequestMapping("/api")
public class NurseryInventoryResource {

    private final Logger log = LoggerFactory.getLogger(NurseryInventoryResource.class);

    private static final String ENTITY_NAME = "nurseryInventory";

    private final NurseryInventoryService nurseryInventoryService;

    private final NurseryInventoryQueryService nurseryInventoryQueryService;

    public NurseryInventoryResource(NurseryInventoryService nurseryInventoryService, NurseryInventoryQueryService nurseryInventoryQueryService) {
        this.nurseryInventoryService = nurseryInventoryService;
        this.nurseryInventoryQueryService = nurseryInventoryQueryService;
    }

    /**
     * POST  /nursery-inventories : Create a new nurseryInventory.
     *
     * @param nurseryInventoryDTO the nurseryInventoryDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new nurseryInventoryDTO, or with status 400 (Bad Request) if the nurseryInventory has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/nursery-inventories")
    @Timed
    public ResponseEntity<NurseryInventoryDTO> createNurseryInventory(@Valid @RequestBody NurseryInventoryDTO nurseryInventoryDTO) throws URISyntaxException {
        log.debug("REST request to save NurseryInventory : {}", nurseryInventoryDTO);
        if (nurseryInventoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new nurseryInventory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NurseryInventoryDTO result = nurseryInventoryService.save(nurseryInventoryDTO);
        return ResponseEntity.created(new URI("/api/nursery-inventories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /nursery-inventories : Updates an existing nurseryInventory.
     *
     * @param nurseryInventoryDTO the nurseryInventoryDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated nurseryInventoryDTO,
     * or with status 400 (Bad Request) if the nurseryInventoryDTO is not valid,
     * or with status 500 (Internal Server Error) if the nurseryInventoryDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/nursery-inventories")
    @Timed
    public ResponseEntity<NurseryInventoryDTO> updateNurseryInventory(@Valid @RequestBody NurseryInventoryDTO nurseryInventoryDTO) throws URISyntaxException {
        log.debug("REST request to update NurseryInventory : {}", nurseryInventoryDTO);
        if (nurseryInventoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NurseryInventoryDTO result = nurseryInventoryService.save(nurseryInventoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, nurseryInventoryDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /nursery-inventories : get all the nurseryInventories.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of nurseryInventories in body
     */
    @GetMapping("/nursery-inventories")
    @Timed
    public ResponseEntity<List<NurseryInventoryDTO>> getAllNurseryInventories(NurseryInventoryCriteria criteria, Pageable pageable) {
        log.debug("REST request to get NurseryInventories by criteria: {}", criteria);
        Page<NurseryInventoryDTO> page = nurseryInventoryQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/nursery-inventories");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /nursery-inventories/:id : get the "id" nurseryInventory.
     *
     * @param id the id of the nurseryInventoryDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the nurseryInventoryDTO, or with status 404 (Not Found)
     */
    @GetMapping("/nursery-inventories/{id}")
    @Timed
    public ResponseEntity<NurseryInventoryDTO> getNurseryInventory(@PathVariable Long id) {
        log.debug("REST request to get NurseryInventory : {}", id);
        Optional<NurseryInventoryDTO> nurseryInventoryDTO = nurseryInventoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nurseryInventoryDTO);
    }

    /**
     * DELETE  /nursery-inventories/:id : delete the "id" nurseryInventory.
     *
     * @param id the id of the nurseryInventoryDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/nursery-inventories/{id}")
    @Timed
    public ResponseEntity<Void> deleteNurseryInventory(@PathVariable Long id) {
        log.debug("REST request to delete NurseryInventory : {}", id);
        nurseryInventoryService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * GET  /nursery-inventories/inventory/:nurseryId/:pickListCategoryId : get all the sectors of particular zonalId.
     *
     * @param nurseryId the zonalId of the sectorDTO to retrieve
     * @param pickListCategoryId the zonalId of the sectorDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and the list of sectors in body
     */
    @GetMapping("/nursery-inventories/inventory/{nurseryId}/{pickListCategoryId}")
    @Timed
    public ResponseEntity<List<NurseryInventoryDTO>> getNurseryCategoryInventory(@PathVariable Long nurseryId, @PathVariable Long pickListCategoryId) {
        log.debug("REST request to get a list of particular category inventory");
        List<NurseryInventoryDTO> list = nurseryInventoryService.findInventory(nurseryId, pickListCategoryId);
        return ResponseEntity.ok().body(list);
    }

    /**
     * GET  /nursery-inventories/inventory/ : {nurseryId} : get all the sectors of particular zonalId.
     *
     * @param nurseryId the zonalId of the sectorDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and the list of sectors in body
     */
    @GetMapping("/nursery-inventories/inventory/cover/{nurseryId}/{status}")
    @Timed
    public ResponseEntity<List<NurseryInventoryDTO>> getCoverInventory(@PathVariable Long nurseryId, @PathVariable Integer status) {
        log.debug("REST request to get a list of particular category inventory");
        List<NurseryInventoryDTO> list = nurseryInventoryService.findCoverInventory(nurseryId, status);
        return ResponseEntity.ok().body(list);
    }

    /**
     * GET /nursery-inventories/inventory/:status : To get all the seeds from inventory table
     * @param status status of the Seeds : NurseryInvetoryDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and the list of inventory in body
     */
     @GetMapping("/nursery-inventories/inventory/seeds/{status}")
     @Timed
     public ResponseEntity<List<NurseryInventoryDTO>> getSeedsStatusRecord(@PathVariable Integer status) {
        log.debug("REST request to get a list of particular seeds from the inventory");
        List<NurseryInventoryDTO> inventoryList = nurseryInventoryService.findParticularStatus(status);
        return ResponseEntity.ok().body(inventoryList);
     }
}
