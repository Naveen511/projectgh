package com.niche.ng.web.rest;

import com.niche.ng.ProjectghApp;

import com.niche.ng.domain.MapSectorWithZonal;
import com.niche.ng.domain.Zonal;
import com.niche.ng.domain.Sector;
import com.niche.ng.repository.MapSectorWithZonalRepository;
import com.niche.ng.service.MapSectorWithZonalService;
import com.niche.ng.service.dto.MapSectorWithZonalDTO;
import com.niche.ng.service.mapper.MapSectorWithZonalMapper;
import com.niche.ng.web.rest.errors.ExceptionTranslator;
import com.niche.ng.service.dto.MapSectorWithZonalCriteria;
import com.niche.ng.service.MapSectorWithZonalQueryService;

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
 * Test class for the MapSectorWithZonalResource REST controller.
 *
 * @see MapSectorWithZonalResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectghApp.class)
public class MapSectorWithZonalResourceIntTest {

    private static final LocalDate DEFAULT_FROM_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FROM_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_TO_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TO_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 10;
    private static final Integer UPDATED_STATUS = 9;

    @Autowired
    private MapSectorWithZonalRepository mapSectorWithZonalRepository;


    @Autowired
    private MapSectorWithZonalMapper mapSectorWithZonalMapper;
    

    @Autowired
    private MapSectorWithZonalService mapSectorWithZonalService;

    @Autowired
    private MapSectorWithZonalQueryService mapSectorWithZonalQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMapSectorWithZonalMockMvc;

    private MapSectorWithZonal mapSectorWithZonal;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MapSectorWithZonalResource mapSectorWithZonalResource = new MapSectorWithZonalResource(mapSectorWithZonalService, mapSectorWithZonalQueryService);
        this.restMapSectorWithZonalMockMvc = MockMvcBuilders.standaloneSetup(mapSectorWithZonalResource)
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
    public static MapSectorWithZonal createEntity(EntityManager em) {
        MapSectorWithZonal mapSectorWithZonal = new MapSectorWithZonal()
            .fromDate(DEFAULT_FROM_DATE)
            .toDate(DEFAULT_TO_DATE)
            .description(DEFAULT_DESCRIPTION)
            .status(DEFAULT_STATUS);
        return mapSectorWithZonal;
    }

    @Before
    public void initTest() {
        mapSectorWithZonal = createEntity(em);
    }

    @Test
    @Transactional
    public void createMapSectorWithZonal() throws Exception {
        int databaseSizeBeforeCreate = mapSectorWithZonalRepository.findAll().size();

        // Create the MapSectorWithZonal
        MapSectorWithZonalDTO mapSectorWithZonalDTO = mapSectorWithZonalMapper.toDto(mapSectorWithZonal);
        restMapSectorWithZonalMockMvc.perform(post("/api/map-sector-with-zonals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mapSectorWithZonalDTO)))
            .andExpect(status().isCreated());

        // Validate the MapSectorWithZonal in the database
        List<MapSectorWithZonal> mapSectorWithZonalList = mapSectorWithZonalRepository.findAll();
        assertThat(mapSectorWithZonalList).hasSize(databaseSizeBeforeCreate + 1);
        MapSectorWithZonal testMapSectorWithZonal = mapSectorWithZonalList.get(mapSectorWithZonalList.size() - 1);
        assertThat(testMapSectorWithZonal.getFromDate()).isEqualTo(DEFAULT_FROM_DATE);
        assertThat(testMapSectorWithZonal.getToDate()).isEqualTo(DEFAULT_TO_DATE);
        assertThat(testMapSectorWithZonal.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testMapSectorWithZonal.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createMapSectorWithZonalWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mapSectorWithZonalRepository.findAll().size();

        // Create the MapSectorWithZonal with an existing ID
        mapSectorWithZonal.setId(1L);
        MapSectorWithZonalDTO mapSectorWithZonalDTO = mapSectorWithZonalMapper.toDto(mapSectorWithZonal);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMapSectorWithZonalMockMvc.perform(post("/api/map-sector-with-zonals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mapSectorWithZonalDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MapSectorWithZonal in the database
        List<MapSectorWithZonal> mapSectorWithZonalList = mapSectorWithZonalRepository.findAll();
        assertThat(mapSectorWithZonalList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkFromDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = mapSectorWithZonalRepository.findAll().size();
        // set the field null
        mapSectorWithZonal.setFromDate(null);

        // Create the MapSectorWithZonal, which fails.
        MapSectorWithZonalDTO mapSectorWithZonalDTO = mapSectorWithZonalMapper.toDto(mapSectorWithZonal);

        restMapSectorWithZonalMockMvc.perform(post("/api/map-sector-with-zonals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mapSectorWithZonalDTO)))
            .andExpect(status().isBadRequest());

        List<MapSectorWithZonal> mapSectorWithZonalList = mapSectorWithZonalRepository.findAll();
        assertThat(mapSectorWithZonalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMapSectorWithZonals() throws Exception {
        // Initialize the database
        mapSectorWithZonalRepository.saveAndFlush(mapSectorWithZonal);

        // Get all the mapSectorWithZonalList
        restMapSectorWithZonalMockMvc.perform(get("/api/map-sector-with-zonals?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mapSectorWithZonal.getId().intValue())))
            .andExpect(jsonPath("$.[*].fromDate").value(hasItem(DEFAULT_FROM_DATE.toString())))
            .andExpect(jsonPath("$.[*].toDate").value(hasItem(DEFAULT_TO_DATE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    

    @Test
    @Transactional
    public void getMapSectorWithZonal() throws Exception {
        // Initialize the database
        mapSectorWithZonalRepository.saveAndFlush(mapSectorWithZonal);

        // Get the mapSectorWithZonal
        restMapSectorWithZonalMockMvc.perform(get("/api/map-sector-with-zonals/{id}", mapSectorWithZonal.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mapSectorWithZonal.getId().intValue()))
            .andExpect(jsonPath("$.fromDate").value(DEFAULT_FROM_DATE.toString()))
            .andExpect(jsonPath("$.toDate").value(DEFAULT_TO_DATE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getAllMapSectorWithZonalsByFromDateIsEqualToSomething() throws Exception {
        // Initialize the database
        mapSectorWithZonalRepository.saveAndFlush(mapSectorWithZonal);

        // Get all the mapSectorWithZonalList where fromDate equals to DEFAULT_FROM_DATE
        defaultMapSectorWithZonalShouldBeFound("fromDate.equals=" + DEFAULT_FROM_DATE);

        // Get all the mapSectorWithZonalList where fromDate equals to UPDATED_FROM_DATE
        defaultMapSectorWithZonalShouldNotBeFound("fromDate.equals=" + UPDATED_FROM_DATE);
    }

    @Test
    @Transactional
    public void getAllMapSectorWithZonalsByFromDateIsInShouldWork() throws Exception {
        // Initialize the database
        mapSectorWithZonalRepository.saveAndFlush(mapSectorWithZonal);

        // Get all the mapSectorWithZonalList where fromDate in DEFAULT_FROM_DATE or UPDATED_FROM_DATE
        defaultMapSectorWithZonalShouldBeFound("fromDate.in=" + DEFAULT_FROM_DATE + "," + UPDATED_FROM_DATE);

        // Get all the mapSectorWithZonalList where fromDate equals to UPDATED_FROM_DATE
        defaultMapSectorWithZonalShouldNotBeFound("fromDate.in=" + UPDATED_FROM_DATE);
    }

    @Test
    @Transactional
    public void getAllMapSectorWithZonalsByFromDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        mapSectorWithZonalRepository.saveAndFlush(mapSectorWithZonal);

        // Get all the mapSectorWithZonalList where fromDate is not null
        defaultMapSectorWithZonalShouldBeFound("fromDate.specified=true");

        // Get all the mapSectorWithZonalList where fromDate is null
        defaultMapSectorWithZonalShouldNotBeFound("fromDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllMapSectorWithZonalsByFromDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mapSectorWithZonalRepository.saveAndFlush(mapSectorWithZonal);

        // Get all the mapSectorWithZonalList where fromDate greater than or equals to DEFAULT_FROM_DATE
        defaultMapSectorWithZonalShouldBeFound("fromDate.greaterOrEqualThan=" + DEFAULT_FROM_DATE);

        // Get all the mapSectorWithZonalList where fromDate greater than or equals to UPDATED_FROM_DATE
        defaultMapSectorWithZonalShouldNotBeFound("fromDate.greaterOrEqualThan=" + UPDATED_FROM_DATE);
    }

    @Test
    @Transactional
    public void getAllMapSectorWithZonalsByFromDateIsLessThanSomething() throws Exception {
        // Initialize the database
        mapSectorWithZonalRepository.saveAndFlush(mapSectorWithZonal);

        // Get all the mapSectorWithZonalList where fromDate less than or equals to DEFAULT_FROM_DATE
        defaultMapSectorWithZonalShouldNotBeFound("fromDate.lessThan=" + DEFAULT_FROM_DATE);

        // Get all the mapSectorWithZonalList where fromDate less than or equals to UPDATED_FROM_DATE
        defaultMapSectorWithZonalShouldBeFound("fromDate.lessThan=" + UPDATED_FROM_DATE);
    }


    @Test
    @Transactional
    public void getAllMapSectorWithZonalsByToDateIsEqualToSomething() throws Exception {
        // Initialize the database
        mapSectorWithZonalRepository.saveAndFlush(mapSectorWithZonal);

        // Get all the mapSectorWithZonalList where toDate equals to DEFAULT_TO_DATE
        defaultMapSectorWithZonalShouldBeFound("toDate.equals=" + DEFAULT_TO_DATE);

        // Get all the mapSectorWithZonalList where toDate equals to UPDATED_TO_DATE
        defaultMapSectorWithZonalShouldNotBeFound("toDate.equals=" + UPDATED_TO_DATE);
    }

    @Test
    @Transactional
    public void getAllMapSectorWithZonalsByToDateIsInShouldWork() throws Exception {
        // Initialize the database
        mapSectorWithZonalRepository.saveAndFlush(mapSectorWithZonal);

        // Get all the mapSectorWithZonalList where toDate in DEFAULT_TO_DATE or UPDATED_TO_DATE
        defaultMapSectorWithZonalShouldBeFound("toDate.in=" + DEFAULT_TO_DATE + "," + UPDATED_TO_DATE);

        // Get all the mapSectorWithZonalList where toDate equals to UPDATED_TO_DATE
        defaultMapSectorWithZonalShouldNotBeFound("toDate.in=" + UPDATED_TO_DATE);
    }

    @Test
    @Transactional
    public void getAllMapSectorWithZonalsByToDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        mapSectorWithZonalRepository.saveAndFlush(mapSectorWithZonal);

        // Get all the mapSectorWithZonalList where toDate is not null
        defaultMapSectorWithZonalShouldBeFound("toDate.specified=true");

        // Get all the mapSectorWithZonalList where toDate is null
        defaultMapSectorWithZonalShouldNotBeFound("toDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllMapSectorWithZonalsByToDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mapSectorWithZonalRepository.saveAndFlush(mapSectorWithZonal);

        // Get all the mapSectorWithZonalList where toDate greater than or equals to DEFAULT_TO_DATE
        defaultMapSectorWithZonalShouldBeFound("toDate.greaterOrEqualThan=" + DEFAULT_TO_DATE);

        // Get all the mapSectorWithZonalList where toDate greater than or equals to UPDATED_TO_DATE
        defaultMapSectorWithZonalShouldNotBeFound("toDate.greaterOrEqualThan=" + UPDATED_TO_DATE);
    }

    @Test
    @Transactional
    public void getAllMapSectorWithZonalsByToDateIsLessThanSomething() throws Exception {
        // Initialize the database
        mapSectorWithZonalRepository.saveAndFlush(mapSectorWithZonal);

        // Get all the mapSectorWithZonalList where toDate less than or equals to DEFAULT_TO_DATE
        defaultMapSectorWithZonalShouldNotBeFound("toDate.lessThan=" + DEFAULT_TO_DATE);

        // Get all the mapSectorWithZonalList where toDate less than or equals to UPDATED_TO_DATE
        defaultMapSectorWithZonalShouldBeFound("toDate.lessThan=" + UPDATED_TO_DATE);
    }


    @Test
    @Transactional
    public void getAllMapSectorWithZonalsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        mapSectorWithZonalRepository.saveAndFlush(mapSectorWithZonal);

        // Get all the mapSectorWithZonalList where description equals to DEFAULT_DESCRIPTION
        defaultMapSectorWithZonalShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the mapSectorWithZonalList where description equals to UPDATED_DESCRIPTION
        defaultMapSectorWithZonalShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllMapSectorWithZonalsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        mapSectorWithZonalRepository.saveAndFlush(mapSectorWithZonal);

        // Get all the mapSectorWithZonalList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultMapSectorWithZonalShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the mapSectorWithZonalList where description equals to UPDATED_DESCRIPTION
        defaultMapSectorWithZonalShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllMapSectorWithZonalsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        mapSectorWithZonalRepository.saveAndFlush(mapSectorWithZonal);

        // Get all the mapSectorWithZonalList where description is not null
        defaultMapSectorWithZonalShouldBeFound("description.specified=true");

        // Get all the mapSectorWithZonalList where description is null
        defaultMapSectorWithZonalShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllMapSectorWithZonalsByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        mapSectorWithZonalRepository.saveAndFlush(mapSectorWithZonal);

        // Get all the mapSectorWithZonalList where status equals to DEFAULT_STATUS
        defaultMapSectorWithZonalShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the mapSectorWithZonalList where status equals to UPDATED_STATUS
        defaultMapSectorWithZonalShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllMapSectorWithZonalsByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        mapSectorWithZonalRepository.saveAndFlush(mapSectorWithZonal);

        // Get all the mapSectorWithZonalList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultMapSectorWithZonalShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the mapSectorWithZonalList where status equals to UPDATED_STATUS
        defaultMapSectorWithZonalShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllMapSectorWithZonalsByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        mapSectorWithZonalRepository.saveAndFlush(mapSectorWithZonal);

        // Get all the mapSectorWithZonalList where status is not null
        defaultMapSectorWithZonalShouldBeFound("status.specified=true");

        // Get all the mapSectorWithZonalList where status is null
        defaultMapSectorWithZonalShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllMapSectorWithZonalsByStatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mapSectorWithZonalRepository.saveAndFlush(mapSectorWithZonal);

        // Get all the mapSectorWithZonalList where status greater than or equals to DEFAULT_STATUS
        defaultMapSectorWithZonalShouldBeFound("status.greaterOrEqualThan=" + DEFAULT_STATUS);

        // Get all the mapSectorWithZonalList where status greater than or equals to (DEFAULT_STATUS + 1)
        defaultMapSectorWithZonalShouldNotBeFound("status.greaterOrEqualThan=" + (DEFAULT_STATUS + 1));
    }

    @Test
    @Transactional
    public void getAllMapSectorWithZonalsByStatusIsLessThanSomething() throws Exception {
        // Initialize the database
        mapSectorWithZonalRepository.saveAndFlush(mapSectorWithZonal);

        // Get all the mapSectorWithZonalList where status less than or equals to DEFAULT_STATUS
        defaultMapSectorWithZonalShouldNotBeFound("status.lessThan=" + DEFAULT_STATUS);

        // Get all the mapSectorWithZonalList where status less than or equals to (DEFAULT_STATUS + 1)
        defaultMapSectorWithZonalShouldBeFound("status.lessThan=" + (DEFAULT_STATUS + 1));
    }


    @Test
    @Transactional
    public void getAllMapSectorWithZonalsByZonalIsEqualToSomething() throws Exception {
        // Initialize the database
        Zonal zonal = ZonalResourceIntTest.createEntity(em);
        em.persist(zonal);
        em.flush();
        mapSectorWithZonal.setZonal(zonal);
        mapSectorWithZonalRepository.saveAndFlush(mapSectorWithZonal);
        Long zonalId = zonal.getId();

        // Get all the mapSectorWithZonalList where zonal equals to zonalId
        defaultMapSectorWithZonalShouldBeFound("zonalId.equals=" + zonalId);

        // Get all the mapSectorWithZonalList where zonal equals to zonalId + 1
        defaultMapSectorWithZonalShouldNotBeFound("zonalId.equals=" + (zonalId + 1));
    }


    @Test
    @Transactional
    public void getAllMapSectorWithZonalsBySectorIsEqualToSomething() throws Exception {
        // Initialize the database
        Sector sector = SectorResourceIntTest.createEntity(em);
        em.persist(sector);
        em.flush();
        mapSectorWithZonal.setSector(sector);
        mapSectorWithZonalRepository.saveAndFlush(mapSectorWithZonal);
        Long sectorId = sector.getId();

        // Get all the mapSectorWithZonalList where sector equals to sectorId
        defaultMapSectorWithZonalShouldBeFound("sectorId.equals=" + sectorId);

        // Get all the mapSectorWithZonalList where sector equals to sectorId + 1
        defaultMapSectorWithZonalShouldNotBeFound("sectorId.equals=" + (sectorId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultMapSectorWithZonalShouldBeFound(String filter) throws Exception {
        restMapSectorWithZonalMockMvc.perform(get("/api/map-sector-with-zonals?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mapSectorWithZonal.getId().intValue())))
            .andExpect(jsonPath("$.[*].fromDate").value(hasItem(DEFAULT_FROM_DATE.toString())))
            .andExpect(jsonPath("$.[*].toDate").value(hasItem(DEFAULT_TO_DATE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultMapSectorWithZonalShouldNotBeFound(String filter) throws Exception {
        restMapSectorWithZonalMockMvc.perform(get("/api/map-sector-with-zonals?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingMapSectorWithZonal() throws Exception {
        // Get the mapSectorWithZonal
        restMapSectorWithZonalMockMvc.perform(get("/api/map-sector-with-zonals/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMapSectorWithZonal() throws Exception {
        // Initialize the database
        mapSectorWithZonalRepository.saveAndFlush(mapSectorWithZonal);

        int databaseSizeBeforeUpdate = mapSectorWithZonalRepository.findAll().size();

        // Update the mapSectorWithZonal
        MapSectorWithZonal updatedMapSectorWithZonal = mapSectorWithZonalRepository.findById(mapSectorWithZonal.getId()).get();
        // Disconnect from session so that the updates on updatedMapSectorWithZonal are not directly saved in db
        em.detach(updatedMapSectorWithZonal);
        updatedMapSectorWithZonal
            .fromDate(UPDATED_FROM_DATE)
            .toDate(UPDATED_TO_DATE)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS);
        MapSectorWithZonalDTO mapSectorWithZonalDTO = mapSectorWithZonalMapper.toDto(updatedMapSectorWithZonal);

        restMapSectorWithZonalMockMvc.perform(put("/api/map-sector-with-zonals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mapSectorWithZonalDTO)))
            .andExpect(status().isOk());

        // Validate the MapSectorWithZonal in the database
        List<MapSectorWithZonal> mapSectorWithZonalList = mapSectorWithZonalRepository.findAll();
        assertThat(mapSectorWithZonalList).hasSize(databaseSizeBeforeUpdate);
        MapSectorWithZonal testMapSectorWithZonal = mapSectorWithZonalList.get(mapSectorWithZonalList.size() - 1);
        assertThat(testMapSectorWithZonal.getFromDate()).isEqualTo(UPDATED_FROM_DATE);
        assertThat(testMapSectorWithZonal.getToDate()).isEqualTo(UPDATED_TO_DATE);
        assertThat(testMapSectorWithZonal.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testMapSectorWithZonal.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingMapSectorWithZonal() throws Exception {
        int databaseSizeBeforeUpdate = mapSectorWithZonalRepository.findAll().size();

        // Create the MapSectorWithZonal
        MapSectorWithZonalDTO mapSectorWithZonalDTO = mapSectorWithZonalMapper.toDto(mapSectorWithZonal);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMapSectorWithZonalMockMvc.perform(put("/api/map-sector-with-zonals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mapSectorWithZonalDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MapSectorWithZonal in the database
        List<MapSectorWithZonal> mapSectorWithZonalList = mapSectorWithZonalRepository.findAll();
        assertThat(mapSectorWithZonalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMapSectorWithZonal() throws Exception {
        // Initialize the database
        mapSectorWithZonalRepository.saveAndFlush(mapSectorWithZonal);

        int databaseSizeBeforeDelete = mapSectorWithZonalRepository.findAll().size();

        // Get the mapSectorWithZonal
        restMapSectorWithZonalMockMvc.perform(delete("/api/map-sector-with-zonals/{id}", mapSectorWithZonal.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MapSectorWithZonal> mapSectorWithZonalList = mapSectorWithZonalRepository.findAll();
        assertThat(mapSectorWithZonalList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MapSectorWithZonal.class);
        MapSectorWithZonal mapSectorWithZonal1 = new MapSectorWithZonal();
        mapSectorWithZonal1.setId(1L);
        MapSectorWithZonal mapSectorWithZonal2 = new MapSectorWithZonal();
        mapSectorWithZonal2.setId(mapSectorWithZonal1.getId());
        assertThat(mapSectorWithZonal1).isEqualTo(mapSectorWithZonal2);
        mapSectorWithZonal2.setId(2L);
        assertThat(mapSectorWithZonal1).isNotEqualTo(mapSectorWithZonal2);
        mapSectorWithZonal1.setId(null);
        assertThat(mapSectorWithZonal1).isNotEqualTo(mapSectorWithZonal2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MapSectorWithZonalDTO.class);
        MapSectorWithZonalDTO mapSectorWithZonalDTO1 = new MapSectorWithZonalDTO();
        mapSectorWithZonalDTO1.setId(1L);
        MapSectorWithZonalDTO mapSectorWithZonalDTO2 = new MapSectorWithZonalDTO();
        assertThat(mapSectorWithZonalDTO1).isNotEqualTo(mapSectorWithZonalDTO2);
        mapSectorWithZonalDTO2.setId(mapSectorWithZonalDTO1.getId());
        assertThat(mapSectorWithZonalDTO1).isEqualTo(mapSectorWithZonalDTO2);
        mapSectorWithZonalDTO2.setId(2L);
        assertThat(mapSectorWithZonalDTO1).isNotEqualTo(mapSectorWithZonalDTO2);
        mapSectorWithZonalDTO1.setId(null);
        assertThat(mapSectorWithZonalDTO1).isNotEqualTo(mapSectorWithZonalDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mapSectorWithZonalMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mapSectorWithZonalMapper.fromId(null)).isNull();
    }
}
