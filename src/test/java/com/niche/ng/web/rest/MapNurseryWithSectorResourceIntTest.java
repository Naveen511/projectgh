package com.niche.ng.web.rest;

import com.niche.ng.ProjectghApp;

import com.niche.ng.domain.MapNurseryWithSector;
import com.niche.ng.domain.Nursery;
import com.niche.ng.domain.Sector;
import com.niche.ng.repository.MapNurseryWithSectorRepository;
import com.niche.ng.service.MapNurseryWithSectorService;
import com.niche.ng.service.dto.MapNurseryWithSectorDTO;
import com.niche.ng.service.mapper.MapNurseryWithSectorMapper;
import com.niche.ng.web.rest.errors.ExceptionTranslator;
import com.niche.ng.service.dto.MapNurseryWithSectorCriteria;
import com.niche.ng.service.MapNurseryWithSectorQueryService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


import static com.niche.ng.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the MapNurseryWithSectorResource REST controller.
 *
 * @see MapNurseryWithSectorResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectghApp.class)
public class MapNurseryWithSectorResourceIntTest {

    private static final LocalDate DEFAULT_FROM_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FROM_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_TO_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TO_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    @Autowired
    private MapNurseryWithSectorRepository mapNurseryWithSectorRepository;


    @Autowired
    private MapNurseryWithSectorMapper mapNurseryWithSectorMapper;
    

    @Autowired
    private MapNurseryWithSectorService mapNurseryWithSectorService;

    @Autowired
    private MapNurseryWithSectorQueryService mapNurseryWithSectorQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMapNurseryWithSectorMockMvc;

    private MapNurseryWithSector mapNurseryWithSector;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MapNurseryWithSectorResource mapNurseryWithSectorResource = new MapNurseryWithSectorResource(mapNurseryWithSectorService, mapNurseryWithSectorQueryService);
        this.restMapNurseryWithSectorMockMvc = MockMvcBuilders.standaloneSetup(mapNurseryWithSectorResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MapNurseryWithSector createEntity(EntityManager em) {
        MapNurseryWithSector mapNurseryWithSector = new MapNurseryWithSector()
            .fromDate(DEFAULT_FROM_DATE)
            .toDate(DEFAULT_TO_DATE)
            .description(DEFAULT_DESCRIPTION)
            .status(DEFAULT_STATUS);
        return mapNurseryWithSector;
    }

    @Before
    public void initTest() {
        mapNurseryWithSector = createEntity(em);
    }

    @Test
    @Transactional
    public void createMapNurseryWithSector() throws Exception {
        int databaseSizeBeforeCreate = mapNurseryWithSectorRepository.findAll().size();

        // Create the MapNurseryWithSector
        MapNurseryWithSectorDTO mapNurseryWithSectorDTO = mapNurseryWithSectorMapper.toDto(mapNurseryWithSector);
        restMapNurseryWithSectorMockMvc.perform(post("/api/map-nursery-with-sectors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mapNurseryWithSectorDTO)))
            .andExpect(status().isCreated());

        // Validate the MapNurseryWithSector in the database
        List<MapNurseryWithSector> mapNurseryWithSectorList = mapNurseryWithSectorRepository.findAll();
        assertThat(mapNurseryWithSectorList).hasSize(databaseSizeBeforeCreate + 1);
        MapNurseryWithSector testMapNurseryWithSector = mapNurseryWithSectorList.get(mapNurseryWithSectorList.size() - 1);
        assertThat(testMapNurseryWithSector.getFromDate()).isEqualTo(DEFAULT_FROM_DATE);
        assertThat(testMapNurseryWithSector.getToDate()).isEqualTo(DEFAULT_TO_DATE);
        assertThat(testMapNurseryWithSector.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testMapNurseryWithSector.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createMapNurseryWithSectorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mapNurseryWithSectorRepository.findAll().size();

        // Create the MapNurseryWithSector with an existing ID
        mapNurseryWithSector.setId(1L);
        MapNurseryWithSectorDTO mapNurseryWithSectorDTO = mapNurseryWithSectorMapper.toDto(mapNurseryWithSector);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMapNurseryWithSectorMockMvc.perform(post("/api/map-nursery-with-sectors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mapNurseryWithSectorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MapNurseryWithSector in the database
        List<MapNurseryWithSector> mapNurseryWithSectorList = mapNurseryWithSectorRepository.findAll();
        assertThat(mapNurseryWithSectorList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkFromDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = mapNurseryWithSectorRepository.findAll().size();
        // set the field null
        mapNurseryWithSector.setFromDate(null);

        // Create the MapNurseryWithSector, which fails.
        MapNurseryWithSectorDTO mapNurseryWithSectorDTO = mapNurseryWithSectorMapper.toDto(mapNurseryWithSector);

        restMapNurseryWithSectorMockMvc.perform(post("/api/map-nursery-with-sectors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mapNurseryWithSectorDTO)))
            .andExpect(status().isBadRequest());

        List<MapNurseryWithSector> mapNurseryWithSectorList = mapNurseryWithSectorRepository.findAll();
        assertThat(mapNurseryWithSectorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMapNurseryWithSectors() throws Exception {
        // Initialize the database
        mapNurseryWithSectorRepository.saveAndFlush(mapNurseryWithSector);

        // Get all the mapNurseryWithSectorList
        restMapNurseryWithSectorMockMvc.perform(get("/api/map-nursery-with-sectors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mapNurseryWithSector.getId().intValue())))
            .andExpect(jsonPath("$.[*].fromDate").value(hasItem(DEFAULT_FROM_DATE.toString())))
            .andExpect(jsonPath("$.[*].toDate").value(hasItem(DEFAULT_TO_DATE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    

    @Test
    @Transactional
    public void getMapNurseryWithSector() throws Exception {
        // Initialize the database
        mapNurseryWithSectorRepository.saveAndFlush(mapNurseryWithSector);

        // Get the mapNurseryWithSector
        restMapNurseryWithSectorMockMvc.perform(get("/api/map-nursery-with-sectors/{id}", mapNurseryWithSector.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mapNurseryWithSector.getId().intValue()))
            .andExpect(jsonPath("$.fromDate").value(DEFAULT_FROM_DATE.toString()))
            .andExpect(jsonPath("$.toDate").value(DEFAULT_TO_DATE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getAllMapNurseryWithSectorsByFromDateIsEqualToSomething() throws Exception {
        // Initialize the database
        mapNurseryWithSectorRepository.saveAndFlush(mapNurseryWithSector);

        // Get all the mapNurseryWithSectorList where fromDate equals to DEFAULT_FROM_DATE
        defaultMapNurseryWithSectorShouldBeFound("fromDate.equals=" + DEFAULT_FROM_DATE);

        // Get all the mapNurseryWithSectorList where fromDate equals to UPDATED_FROM_DATE
        defaultMapNurseryWithSectorShouldNotBeFound("fromDate.equals=" + UPDATED_FROM_DATE);
    }

    @Test
    @Transactional
    public void getAllMapNurseryWithSectorsByFromDateIsInShouldWork() throws Exception {
        // Initialize the database
        mapNurseryWithSectorRepository.saveAndFlush(mapNurseryWithSector);

        // Get all the mapNurseryWithSectorList where fromDate in DEFAULT_FROM_DATE or UPDATED_FROM_DATE
        defaultMapNurseryWithSectorShouldBeFound("fromDate.in=" + DEFAULT_FROM_DATE + "," + UPDATED_FROM_DATE);

        // Get all the mapNurseryWithSectorList where fromDate equals to UPDATED_FROM_DATE
        defaultMapNurseryWithSectorShouldNotBeFound("fromDate.in=" + UPDATED_FROM_DATE);
    }

    @Test
    @Transactional
    public void getAllMapNurseryWithSectorsByFromDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        mapNurseryWithSectorRepository.saveAndFlush(mapNurseryWithSector);

        // Get all the mapNurseryWithSectorList where fromDate is not null
        defaultMapNurseryWithSectorShouldBeFound("fromDate.specified=true");

        // Get all the mapNurseryWithSectorList where fromDate is null
        defaultMapNurseryWithSectorShouldNotBeFound("fromDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllMapNurseryWithSectorsByFromDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mapNurseryWithSectorRepository.saveAndFlush(mapNurseryWithSector);

        // Get all the mapNurseryWithSectorList where fromDate greater than or equals to DEFAULT_FROM_DATE
        defaultMapNurseryWithSectorShouldBeFound("fromDate.greaterOrEqualThan=" + DEFAULT_FROM_DATE);

        // Get all the mapNurseryWithSectorList where fromDate greater than or equals to UPDATED_FROM_DATE
        defaultMapNurseryWithSectorShouldNotBeFound("fromDate.greaterOrEqualThan=" + UPDATED_FROM_DATE);
    }

    @Test
    @Transactional
    public void getAllMapNurseryWithSectorsByFromDateIsLessThanSomething() throws Exception {
        // Initialize the database
        mapNurseryWithSectorRepository.saveAndFlush(mapNurseryWithSector);

        // Get all the mapNurseryWithSectorList where fromDate less than or equals to DEFAULT_FROM_DATE
        defaultMapNurseryWithSectorShouldNotBeFound("fromDate.lessThan=" + DEFAULT_FROM_DATE);

        // Get all the mapNurseryWithSectorList where fromDate less than or equals to UPDATED_FROM_DATE
        defaultMapNurseryWithSectorShouldBeFound("fromDate.lessThan=" + UPDATED_FROM_DATE);
    }


    @Test
    @Transactional
    public void getAllMapNurseryWithSectorsByToDateIsEqualToSomething() throws Exception {
        // Initialize the database
        mapNurseryWithSectorRepository.saveAndFlush(mapNurseryWithSector);

        // Get all the mapNurseryWithSectorList where toDate equals to DEFAULT_TO_DATE
        defaultMapNurseryWithSectorShouldBeFound("toDate.equals=" + DEFAULT_TO_DATE);

        // Get all the mapNurseryWithSectorList where toDate equals to UPDATED_TO_DATE
        defaultMapNurseryWithSectorShouldNotBeFound("toDate.equals=" + UPDATED_TO_DATE);
    }

    @Test
    @Transactional
    public void getAllMapNurseryWithSectorsByToDateIsInShouldWork() throws Exception {
        // Initialize the database
        mapNurseryWithSectorRepository.saveAndFlush(mapNurseryWithSector);

        // Get all the mapNurseryWithSectorList where toDate in DEFAULT_TO_DATE or UPDATED_TO_DATE
        defaultMapNurseryWithSectorShouldBeFound("toDate.in=" + DEFAULT_TO_DATE + "," + UPDATED_TO_DATE);

        // Get all the mapNurseryWithSectorList where toDate equals to UPDATED_TO_DATE
        defaultMapNurseryWithSectorShouldNotBeFound("toDate.in=" + UPDATED_TO_DATE);
    }

    @Test
    @Transactional
    public void getAllMapNurseryWithSectorsByToDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        mapNurseryWithSectorRepository.saveAndFlush(mapNurseryWithSector);

        // Get all the mapNurseryWithSectorList where toDate is not null
        defaultMapNurseryWithSectorShouldBeFound("toDate.specified=true");

        // Get all the mapNurseryWithSectorList where toDate is null
        defaultMapNurseryWithSectorShouldNotBeFound("toDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllMapNurseryWithSectorsByToDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mapNurseryWithSectorRepository.saveAndFlush(mapNurseryWithSector);

        // Get all the mapNurseryWithSectorList where toDate greater than or equals to DEFAULT_TO_DATE
        defaultMapNurseryWithSectorShouldBeFound("toDate.greaterOrEqualThan=" + DEFAULT_TO_DATE);

        // Get all the mapNurseryWithSectorList where toDate greater than or equals to UPDATED_TO_DATE
        defaultMapNurseryWithSectorShouldNotBeFound("toDate.greaterOrEqualThan=" + UPDATED_TO_DATE);
    }

    @Test
    @Transactional
    public void getAllMapNurseryWithSectorsByToDateIsLessThanSomething() throws Exception {
        // Initialize the database
        mapNurseryWithSectorRepository.saveAndFlush(mapNurseryWithSector);

        // Get all the mapNurseryWithSectorList where toDate less than or equals to DEFAULT_TO_DATE
        defaultMapNurseryWithSectorShouldNotBeFound("toDate.lessThan=" + DEFAULT_TO_DATE);

        // Get all the mapNurseryWithSectorList where toDate less than or equals to UPDATED_TO_DATE
        defaultMapNurseryWithSectorShouldBeFound("toDate.lessThan=" + UPDATED_TO_DATE);
    }


    @Test
    @Transactional
    public void getAllMapNurseryWithSectorsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        mapNurseryWithSectorRepository.saveAndFlush(mapNurseryWithSector);

        // Get all the mapNurseryWithSectorList where description equals to DEFAULT_DESCRIPTION
        defaultMapNurseryWithSectorShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the mapNurseryWithSectorList where description equals to UPDATED_DESCRIPTION
        defaultMapNurseryWithSectorShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllMapNurseryWithSectorsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        mapNurseryWithSectorRepository.saveAndFlush(mapNurseryWithSector);

        // Get all the mapNurseryWithSectorList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultMapNurseryWithSectorShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the mapNurseryWithSectorList where description equals to UPDATED_DESCRIPTION
        defaultMapNurseryWithSectorShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllMapNurseryWithSectorsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        mapNurseryWithSectorRepository.saveAndFlush(mapNurseryWithSector);

        // Get all the mapNurseryWithSectorList where description is not null
        defaultMapNurseryWithSectorShouldBeFound("description.specified=true");

        // Get all the mapNurseryWithSectorList where description is null
        defaultMapNurseryWithSectorShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllMapNurseryWithSectorsByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        mapNurseryWithSectorRepository.saveAndFlush(mapNurseryWithSector);

        // Get all the mapNurseryWithSectorList where status equals to DEFAULT_STATUS
        defaultMapNurseryWithSectorShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the mapNurseryWithSectorList where status equals to UPDATED_STATUS
        defaultMapNurseryWithSectorShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllMapNurseryWithSectorsByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        mapNurseryWithSectorRepository.saveAndFlush(mapNurseryWithSector);

        // Get all the mapNurseryWithSectorList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultMapNurseryWithSectorShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the mapNurseryWithSectorList where status equals to UPDATED_STATUS
        defaultMapNurseryWithSectorShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllMapNurseryWithSectorsByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        mapNurseryWithSectorRepository.saveAndFlush(mapNurseryWithSector);

        // Get all the mapNurseryWithSectorList where status is not null
        defaultMapNurseryWithSectorShouldBeFound("status.specified=true");

        // Get all the mapNurseryWithSectorList where status is null
        defaultMapNurseryWithSectorShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllMapNurseryWithSectorsByStatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mapNurseryWithSectorRepository.saveAndFlush(mapNurseryWithSector);

        // Get all the mapNurseryWithSectorList where status greater than or equals to DEFAULT_STATUS
        defaultMapNurseryWithSectorShouldBeFound("status.greaterOrEqualThan=" + DEFAULT_STATUS);

        // Get all the mapNurseryWithSectorList where status greater than or equals to UPDATED_STATUS
        defaultMapNurseryWithSectorShouldNotBeFound("status.greaterOrEqualThan=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllMapNurseryWithSectorsByStatusIsLessThanSomething() throws Exception {
        // Initialize the database
        mapNurseryWithSectorRepository.saveAndFlush(mapNurseryWithSector);

        // Get all the mapNurseryWithSectorList where status less than or equals to DEFAULT_STATUS
        defaultMapNurseryWithSectorShouldNotBeFound("status.lessThan=" + DEFAULT_STATUS);

        // Get all the mapNurseryWithSectorList where status less than or equals to UPDATED_STATUS
        defaultMapNurseryWithSectorShouldBeFound("status.lessThan=" + UPDATED_STATUS);
    }


    @Test
    @Transactional
    public void getAllMapNurseryWithSectorsByNurseryIsEqualToSomething() throws Exception {
        // Initialize the database
        Nursery nursery = NurseryResourceIntTest.createEntity(em);
        em.persist(nursery);
        em.flush();
        mapNurseryWithSector.setNursery(nursery);
        mapNurseryWithSectorRepository.saveAndFlush(mapNurseryWithSector);
        Long nurseryId = nursery.getId();

        // Get all the mapNurseryWithSectorList where nursery equals to nurseryId
        defaultMapNurseryWithSectorShouldBeFound("nurseryId.equals=" + nurseryId);

        // Get all the mapNurseryWithSectorList where nursery equals to nurseryId + 1
        defaultMapNurseryWithSectorShouldNotBeFound("nurseryId.equals=" + (nurseryId + 1));
    }


    @Test
    @Transactional
    public void getAllMapNurseryWithSectorsBySectorIsEqualToSomething() throws Exception {
        // Initialize the database
        Sector sector = SectorResourceIntTest.createEntity(em);
        em.persist(sector);
        em.flush();
        mapNurseryWithSector.setSector(sector);
        mapNurseryWithSectorRepository.saveAndFlush(mapNurseryWithSector);
        Long sectorId = sector.getId();

        // Get all the mapNurseryWithSectorList where sector equals to sectorId
        defaultMapNurseryWithSectorShouldBeFound("sectorId.equals=" + sectorId);

        // Get all the mapNurseryWithSectorList where sector equals to sectorId + 1
        defaultMapNurseryWithSectorShouldNotBeFound("sectorId.equals=" + (sectorId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultMapNurseryWithSectorShouldBeFound(String filter) throws Exception {
        restMapNurseryWithSectorMockMvc.perform(get("/api/map-nursery-with-sectors?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mapNurseryWithSector.getId().intValue())))
            .andExpect(jsonPath("$.[*].fromDate").value(hasItem(DEFAULT_FROM_DATE.toString())))
            .andExpect(jsonPath("$.[*].toDate").value(hasItem(DEFAULT_TO_DATE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultMapNurseryWithSectorShouldNotBeFound(String filter) throws Exception {
        restMapNurseryWithSectorMockMvc.perform(get("/api/map-nursery-with-sectors?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingMapNurseryWithSector() throws Exception {
        // Get the mapNurseryWithSector
        restMapNurseryWithSectorMockMvc.perform(get("/api/map-nursery-with-sectors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMapNurseryWithSector() throws Exception {
        // Initialize the database
        mapNurseryWithSectorRepository.saveAndFlush(mapNurseryWithSector);

        int databaseSizeBeforeUpdate = mapNurseryWithSectorRepository.findAll().size();

        // Update the mapNurseryWithSector
        MapNurseryWithSector updatedMapNurseryWithSector = mapNurseryWithSectorRepository.findById(mapNurseryWithSector.getId()).get();
        // Disconnect from session so that the updates on updatedMapNurseryWithSector are not directly saved in db
        em.detach(updatedMapNurseryWithSector);
        updatedMapNurseryWithSector
            .fromDate(UPDATED_FROM_DATE)
            .toDate(UPDATED_TO_DATE)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS);
        MapNurseryWithSectorDTO mapNurseryWithSectorDTO = mapNurseryWithSectorMapper.toDto(updatedMapNurseryWithSector);

        restMapNurseryWithSectorMockMvc.perform(put("/api/map-nursery-with-sectors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mapNurseryWithSectorDTO)))
            .andExpect(status().isOk());

        // Validate the MapNurseryWithSector in the database
        List<MapNurseryWithSector> mapNurseryWithSectorList = mapNurseryWithSectorRepository.findAll();
        assertThat(mapNurseryWithSectorList).hasSize(databaseSizeBeforeUpdate);
        MapNurseryWithSector testMapNurseryWithSector = mapNurseryWithSectorList.get(mapNurseryWithSectorList.size() - 1);
        assertThat(testMapNurseryWithSector.getFromDate()).isEqualTo(UPDATED_FROM_DATE);
        assertThat(testMapNurseryWithSector.getToDate()).isEqualTo(UPDATED_TO_DATE);
        assertThat(testMapNurseryWithSector.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testMapNurseryWithSector.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingMapNurseryWithSector() throws Exception {
        int databaseSizeBeforeUpdate = mapNurseryWithSectorRepository.findAll().size();

        // Create the MapNurseryWithSector
        MapNurseryWithSectorDTO mapNurseryWithSectorDTO = mapNurseryWithSectorMapper.toDto(mapNurseryWithSector);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMapNurseryWithSectorMockMvc.perform(put("/api/map-nursery-with-sectors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mapNurseryWithSectorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MapNurseryWithSector in the database
        List<MapNurseryWithSector> mapNurseryWithSectorList = mapNurseryWithSectorRepository.findAll();
        assertThat(mapNurseryWithSectorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMapNurseryWithSector() throws Exception {
        // Initialize the database
        mapNurseryWithSectorRepository.saveAndFlush(mapNurseryWithSector);

        int databaseSizeBeforeDelete = mapNurseryWithSectorRepository.findAll().size();

        // Get the mapNurseryWithSector
        restMapNurseryWithSectorMockMvc.perform(delete("/api/map-nursery-with-sectors/{id}", mapNurseryWithSector.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MapNurseryWithSector> mapNurseryWithSectorList = mapNurseryWithSectorRepository.findAll();
        assertThat(mapNurseryWithSectorList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MapNurseryWithSector.class);
        MapNurseryWithSector mapNurseryWithSector1 = new MapNurseryWithSector();
        mapNurseryWithSector1.setId(1L);
        MapNurseryWithSector mapNurseryWithSector2 = new MapNurseryWithSector();
        mapNurseryWithSector2.setId(mapNurseryWithSector1.getId());
        assertThat(mapNurseryWithSector1).isEqualTo(mapNurseryWithSector2);
        mapNurseryWithSector2.setId(2L);
        assertThat(mapNurseryWithSector1).isNotEqualTo(mapNurseryWithSector2);
        mapNurseryWithSector1.setId(null);
        assertThat(mapNurseryWithSector1).isNotEqualTo(mapNurseryWithSector2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MapNurseryWithSectorDTO.class);
        MapNurseryWithSectorDTO mapNurseryWithSectorDTO1 = new MapNurseryWithSectorDTO();
        mapNurseryWithSectorDTO1.setId(1L);
        MapNurseryWithSectorDTO mapNurseryWithSectorDTO2 = new MapNurseryWithSectorDTO();
        assertThat(mapNurseryWithSectorDTO1).isNotEqualTo(mapNurseryWithSectorDTO2);
        mapNurseryWithSectorDTO2.setId(mapNurseryWithSectorDTO1.getId());
        assertThat(mapNurseryWithSectorDTO1).isEqualTo(mapNurseryWithSectorDTO2);
        mapNurseryWithSectorDTO2.setId(2L);
        assertThat(mapNurseryWithSectorDTO1).isNotEqualTo(mapNurseryWithSectorDTO2);
        mapNurseryWithSectorDTO1.setId(null);
        assertThat(mapNurseryWithSectorDTO1).isNotEqualTo(mapNurseryWithSectorDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mapNurseryWithSectorMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mapNurseryWithSectorMapper.fromId(null)).isNull();
    }
}
