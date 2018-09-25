package com.niche.ng.web.rest;

import com.niche.ng.ProjectghApp;

import com.niche.ng.domain.MapZonalWithOh;
import com.niche.ng.domain.Zonal;
import com.niche.ng.domain.OperationalHead;
import com.niche.ng.repository.MapZonalWithOhRepository;
import com.niche.ng.service.MapZonalWithOhService;
import com.niche.ng.service.dto.MapZonalWithOhDTO;
import com.niche.ng.service.mapper.MapZonalWithOhMapper;
import com.niche.ng.web.rest.errors.ExceptionTranslator;
import com.niche.ng.service.dto.MapZonalWithOhCriteria;
import com.niche.ng.service.MapZonalWithOhQueryService;

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
 * Test class for the MapZonalWithOhResource REST controller.
 *
 * @see MapZonalWithOhResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectghApp.class)
public class MapZonalWithOhResourceIntTest {

    private static final LocalDate DEFAULT_FROM_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FROM_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_TO_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TO_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    @Autowired
    private MapZonalWithOhRepository mapZonalWithOhRepository;


    @Autowired
    private MapZonalWithOhMapper mapZonalWithOhMapper;
    

    @Autowired
    private MapZonalWithOhService mapZonalWithOhService;

    @Autowired
    private MapZonalWithOhQueryService mapZonalWithOhQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMapZonalWithOhMockMvc;

    private MapZonalWithOh mapZonalWithOh;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MapZonalWithOhResource mapZonalWithOhResource = new MapZonalWithOhResource(mapZonalWithOhService, mapZonalWithOhQueryService);
        this.restMapZonalWithOhMockMvc = MockMvcBuilders.standaloneSetup(mapZonalWithOhResource)
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
    public static MapZonalWithOh createEntity(EntityManager em) {
        MapZonalWithOh mapZonalWithOh = new MapZonalWithOh()
            .fromDate(DEFAULT_FROM_DATE)
            .toDate(DEFAULT_TO_DATE)
            .description(DEFAULT_DESCRIPTION)
            .status(DEFAULT_STATUS);
        return mapZonalWithOh;
    }

    @Before
    public void initTest() {
        mapZonalWithOh = createEntity(em);
    }

    @Test
    @Transactional
    public void createMapZonalWithOh() throws Exception {
        int databaseSizeBeforeCreate = mapZonalWithOhRepository.findAll().size();

        // Create the MapZonalWithOh
        MapZonalWithOhDTO mapZonalWithOhDTO = mapZonalWithOhMapper.toDto(mapZonalWithOh);
        restMapZonalWithOhMockMvc.perform(post("/api/map-zonal-with-ohs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mapZonalWithOhDTO)))
            .andExpect(status().isCreated());

        // Validate the MapZonalWithOh in the database
        List<MapZonalWithOh> mapZonalWithOhList = mapZonalWithOhRepository.findAll();
        assertThat(mapZonalWithOhList).hasSize(databaseSizeBeforeCreate + 1);
        MapZonalWithOh testMapZonalWithOh = mapZonalWithOhList.get(mapZonalWithOhList.size() - 1);
        assertThat(testMapZonalWithOh.getFromDate()).isEqualTo(DEFAULT_FROM_DATE);
        assertThat(testMapZonalWithOh.getToDate()).isEqualTo(DEFAULT_TO_DATE);
        assertThat(testMapZonalWithOh.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testMapZonalWithOh.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createMapZonalWithOhWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mapZonalWithOhRepository.findAll().size();

        // Create the MapZonalWithOh with an existing ID
        mapZonalWithOh.setId(1L);
        MapZonalWithOhDTO mapZonalWithOhDTO = mapZonalWithOhMapper.toDto(mapZonalWithOh);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMapZonalWithOhMockMvc.perform(post("/api/map-zonal-with-ohs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mapZonalWithOhDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MapZonalWithOh in the database
        List<MapZonalWithOh> mapZonalWithOhList = mapZonalWithOhRepository.findAll();
        assertThat(mapZonalWithOhList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMapZonalWithOhs() throws Exception {
        // Initialize the database
        mapZonalWithOhRepository.saveAndFlush(mapZonalWithOh);

        // Get all the mapZonalWithOhList
        restMapZonalWithOhMockMvc.perform(get("/api/map-zonal-with-ohs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mapZonalWithOh.getId().intValue())))
            .andExpect(jsonPath("$.[*].fromDate").value(hasItem(DEFAULT_FROM_DATE.toString())))
            .andExpect(jsonPath("$.[*].toDate").value(hasItem(DEFAULT_TO_DATE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    

    @Test
    @Transactional
    public void getMapZonalWithOh() throws Exception {
        // Initialize the database
        mapZonalWithOhRepository.saveAndFlush(mapZonalWithOh);

        // Get the mapZonalWithOh
        restMapZonalWithOhMockMvc.perform(get("/api/map-zonal-with-ohs/{id}", mapZonalWithOh.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mapZonalWithOh.getId().intValue()))
            .andExpect(jsonPath("$.fromDate").value(DEFAULT_FROM_DATE.toString()))
            .andExpect(jsonPath("$.toDate").value(DEFAULT_TO_DATE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getAllMapZonalWithOhsByFromDateIsEqualToSomething() throws Exception {
        // Initialize the database
        mapZonalWithOhRepository.saveAndFlush(mapZonalWithOh);

        // Get all the mapZonalWithOhList where fromDate equals to DEFAULT_FROM_DATE
        defaultMapZonalWithOhShouldBeFound("fromDate.equals=" + DEFAULT_FROM_DATE);

        // Get all the mapZonalWithOhList where fromDate equals to UPDATED_FROM_DATE
        defaultMapZonalWithOhShouldNotBeFound("fromDate.equals=" + UPDATED_FROM_DATE);
    }

    @Test
    @Transactional
    public void getAllMapZonalWithOhsByFromDateIsInShouldWork() throws Exception {
        // Initialize the database
        mapZonalWithOhRepository.saveAndFlush(mapZonalWithOh);

        // Get all the mapZonalWithOhList where fromDate in DEFAULT_FROM_DATE or UPDATED_FROM_DATE
        defaultMapZonalWithOhShouldBeFound("fromDate.in=" + DEFAULT_FROM_DATE + "," + UPDATED_FROM_DATE);

        // Get all the mapZonalWithOhList where fromDate equals to UPDATED_FROM_DATE
        defaultMapZonalWithOhShouldNotBeFound("fromDate.in=" + UPDATED_FROM_DATE);
    }

    @Test
    @Transactional
    public void getAllMapZonalWithOhsByFromDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        mapZonalWithOhRepository.saveAndFlush(mapZonalWithOh);

        // Get all the mapZonalWithOhList where fromDate is not null
        defaultMapZonalWithOhShouldBeFound("fromDate.specified=true");

        // Get all the mapZonalWithOhList where fromDate is null
        defaultMapZonalWithOhShouldNotBeFound("fromDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllMapZonalWithOhsByFromDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mapZonalWithOhRepository.saveAndFlush(mapZonalWithOh);

        // Get all the mapZonalWithOhList where fromDate greater than or equals to DEFAULT_FROM_DATE
        defaultMapZonalWithOhShouldBeFound("fromDate.greaterOrEqualThan=" + DEFAULT_FROM_DATE);

        // Get all the mapZonalWithOhList where fromDate greater than or equals to UPDATED_FROM_DATE
        defaultMapZonalWithOhShouldNotBeFound("fromDate.greaterOrEqualThan=" + UPDATED_FROM_DATE);
    }

    @Test
    @Transactional
    public void getAllMapZonalWithOhsByFromDateIsLessThanSomething() throws Exception {
        // Initialize the database
        mapZonalWithOhRepository.saveAndFlush(mapZonalWithOh);

        // Get all the mapZonalWithOhList where fromDate less than or equals to DEFAULT_FROM_DATE
        defaultMapZonalWithOhShouldNotBeFound("fromDate.lessThan=" + DEFAULT_FROM_DATE);

        // Get all the mapZonalWithOhList where fromDate less than or equals to UPDATED_FROM_DATE
        defaultMapZonalWithOhShouldBeFound("fromDate.lessThan=" + UPDATED_FROM_DATE);
    }


    @Test
    @Transactional
    public void getAllMapZonalWithOhsByToDateIsEqualToSomething() throws Exception {
        // Initialize the database
        mapZonalWithOhRepository.saveAndFlush(mapZonalWithOh);

        // Get all the mapZonalWithOhList where toDate equals to DEFAULT_TO_DATE
        defaultMapZonalWithOhShouldBeFound("toDate.equals=" + DEFAULT_TO_DATE);

        // Get all the mapZonalWithOhList where toDate equals to UPDATED_TO_DATE
        defaultMapZonalWithOhShouldNotBeFound("toDate.equals=" + UPDATED_TO_DATE);
    }

    @Test
    @Transactional
    public void getAllMapZonalWithOhsByToDateIsInShouldWork() throws Exception {
        // Initialize the database
        mapZonalWithOhRepository.saveAndFlush(mapZonalWithOh);

        // Get all the mapZonalWithOhList where toDate in DEFAULT_TO_DATE or UPDATED_TO_DATE
        defaultMapZonalWithOhShouldBeFound("toDate.in=" + DEFAULT_TO_DATE + "," + UPDATED_TO_DATE);

        // Get all the mapZonalWithOhList where toDate equals to UPDATED_TO_DATE
        defaultMapZonalWithOhShouldNotBeFound("toDate.in=" + UPDATED_TO_DATE);
    }

    @Test
    @Transactional
    public void getAllMapZonalWithOhsByToDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        mapZonalWithOhRepository.saveAndFlush(mapZonalWithOh);

        // Get all the mapZonalWithOhList where toDate is not null
        defaultMapZonalWithOhShouldBeFound("toDate.specified=true");

        // Get all the mapZonalWithOhList where toDate is null
        defaultMapZonalWithOhShouldNotBeFound("toDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllMapZonalWithOhsByToDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mapZonalWithOhRepository.saveAndFlush(mapZonalWithOh);

        // Get all the mapZonalWithOhList where toDate greater than or equals to DEFAULT_TO_DATE
        defaultMapZonalWithOhShouldBeFound("toDate.greaterOrEqualThan=" + DEFAULT_TO_DATE);

        // Get all the mapZonalWithOhList where toDate greater than or equals to UPDATED_TO_DATE
        defaultMapZonalWithOhShouldNotBeFound("toDate.greaterOrEqualThan=" + UPDATED_TO_DATE);
    }

    @Test
    @Transactional
    public void getAllMapZonalWithOhsByToDateIsLessThanSomething() throws Exception {
        // Initialize the database
        mapZonalWithOhRepository.saveAndFlush(mapZonalWithOh);

        // Get all the mapZonalWithOhList where toDate less than or equals to DEFAULT_TO_DATE
        defaultMapZonalWithOhShouldNotBeFound("toDate.lessThan=" + DEFAULT_TO_DATE);

        // Get all the mapZonalWithOhList where toDate less than or equals to UPDATED_TO_DATE
        defaultMapZonalWithOhShouldBeFound("toDate.lessThan=" + UPDATED_TO_DATE);
    }


    @Test
    @Transactional
    public void getAllMapZonalWithOhsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        mapZonalWithOhRepository.saveAndFlush(mapZonalWithOh);

        // Get all the mapZonalWithOhList where description equals to DEFAULT_DESCRIPTION
        defaultMapZonalWithOhShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the mapZonalWithOhList where description equals to UPDATED_DESCRIPTION
        defaultMapZonalWithOhShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllMapZonalWithOhsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        mapZonalWithOhRepository.saveAndFlush(mapZonalWithOh);

        // Get all the mapZonalWithOhList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultMapZonalWithOhShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the mapZonalWithOhList where description equals to UPDATED_DESCRIPTION
        defaultMapZonalWithOhShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllMapZonalWithOhsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        mapZonalWithOhRepository.saveAndFlush(mapZonalWithOh);

        // Get all the mapZonalWithOhList where description is not null
        defaultMapZonalWithOhShouldBeFound("description.specified=true");

        // Get all the mapZonalWithOhList where description is null
        defaultMapZonalWithOhShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllMapZonalWithOhsByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        mapZonalWithOhRepository.saveAndFlush(mapZonalWithOh);

        // Get all the mapZonalWithOhList where status equals to DEFAULT_STATUS
        defaultMapZonalWithOhShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the mapZonalWithOhList where status equals to UPDATED_STATUS
        defaultMapZonalWithOhShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllMapZonalWithOhsByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        mapZonalWithOhRepository.saveAndFlush(mapZonalWithOh);

        // Get all the mapZonalWithOhList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultMapZonalWithOhShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the mapZonalWithOhList where status equals to UPDATED_STATUS
        defaultMapZonalWithOhShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllMapZonalWithOhsByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        mapZonalWithOhRepository.saveAndFlush(mapZonalWithOh);

        // Get all the mapZonalWithOhList where status is not null
        defaultMapZonalWithOhShouldBeFound("status.specified=true");

        // Get all the mapZonalWithOhList where status is null
        defaultMapZonalWithOhShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllMapZonalWithOhsByStatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mapZonalWithOhRepository.saveAndFlush(mapZonalWithOh);

        // Get all the mapZonalWithOhList where status greater than or equals to DEFAULT_STATUS
        defaultMapZonalWithOhShouldBeFound("status.greaterOrEqualThan=" + DEFAULT_STATUS);

        // Get all the mapZonalWithOhList where status greater than or equals to UPDATED_STATUS
        defaultMapZonalWithOhShouldNotBeFound("status.greaterOrEqualThan=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllMapZonalWithOhsByStatusIsLessThanSomething() throws Exception {
        // Initialize the database
        mapZonalWithOhRepository.saveAndFlush(mapZonalWithOh);

        // Get all the mapZonalWithOhList where status less than or equals to DEFAULT_STATUS
        defaultMapZonalWithOhShouldNotBeFound("status.lessThan=" + DEFAULT_STATUS);

        // Get all the mapZonalWithOhList where status less than or equals to UPDATED_STATUS
        defaultMapZonalWithOhShouldBeFound("status.lessThan=" + UPDATED_STATUS);
    }


    @Test
    @Transactional
    public void getAllMapZonalWithOhsByZonalIsEqualToSomething() throws Exception {
        // Initialize the database
        Zonal zonal = ZonalResourceIntTest.createEntity(em);
        em.persist(zonal);
        em.flush();
        mapZonalWithOh.setZonal(zonal);
        mapZonalWithOhRepository.saveAndFlush(mapZonalWithOh);
        Long zonalId = zonal.getId();

        // Get all the mapZonalWithOhList where zonal equals to zonalId
        defaultMapZonalWithOhShouldBeFound("zonalId.equals=" + zonalId);

        // Get all the mapZonalWithOhList where zonal equals to zonalId + 1
        defaultMapZonalWithOhShouldNotBeFound("zonalId.equals=" + (zonalId + 1));
    }


    @Test
    @Transactional
    public void getAllMapZonalWithOhsByOperationalHeadIsEqualToSomething() throws Exception {
        // Initialize the database
        OperationalHead operationalHead = OperationalHeadResourceIntTest.createEntity(em);
        em.persist(operationalHead);
        em.flush();
        mapZonalWithOh.setOperationalHead(operationalHead);
        mapZonalWithOhRepository.saveAndFlush(mapZonalWithOh);
        Long operationalHeadId = operationalHead.getId();

        // Get all the mapZonalWithOhList where operationalHead equals to operationalHeadId
        defaultMapZonalWithOhShouldBeFound("operationalHeadId.equals=" + operationalHeadId);

        // Get all the mapZonalWithOhList where operationalHead equals to operationalHeadId + 1
        defaultMapZonalWithOhShouldNotBeFound("operationalHeadId.equals=" + (operationalHeadId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultMapZonalWithOhShouldBeFound(String filter) throws Exception {
        restMapZonalWithOhMockMvc.perform(get("/api/map-zonal-with-ohs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mapZonalWithOh.getId().intValue())))
            .andExpect(jsonPath("$.[*].fromDate").value(hasItem(DEFAULT_FROM_DATE.toString())))
            .andExpect(jsonPath("$.[*].toDate").value(hasItem(DEFAULT_TO_DATE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultMapZonalWithOhShouldNotBeFound(String filter) throws Exception {
        restMapZonalWithOhMockMvc.perform(get("/api/map-zonal-with-ohs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingMapZonalWithOh() throws Exception {
        // Get the mapZonalWithOh
        restMapZonalWithOhMockMvc.perform(get("/api/map-zonal-with-ohs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMapZonalWithOh() throws Exception {
        // Initialize the database
        mapZonalWithOhRepository.saveAndFlush(mapZonalWithOh);

        int databaseSizeBeforeUpdate = mapZonalWithOhRepository.findAll().size();

        // Update the mapZonalWithOh
        MapZonalWithOh updatedMapZonalWithOh = mapZonalWithOhRepository.findById(mapZonalWithOh.getId()).get();
        // Disconnect from session so that the updates on updatedMapZonalWithOh are not directly saved in db
        em.detach(updatedMapZonalWithOh);
        updatedMapZonalWithOh
            .fromDate(UPDATED_FROM_DATE)
            .toDate(UPDATED_TO_DATE)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS);
        MapZonalWithOhDTO mapZonalWithOhDTO = mapZonalWithOhMapper.toDto(updatedMapZonalWithOh);

        restMapZonalWithOhMockMvc.perform(put("/api/map-zonal-with-ohs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mapZonalWithOhDTO)))
            .andExpect(status().isOk());

        // Validate the MapZonalWithOh in the database
        List<MapZonalWithOh> mapZonalWithOhList = mapZonalWithOhRepository.findAll();
        assertThat(mapZonalWithOhList).hasSize(databaseSizeBeforeUpdate);
        MapZonalWithOh testMapZonalWithOh = mapZonalWithOhList.get(mapZonalWithOhList.size() - 1);
        assertThat(testMapZonalWithOh.getFromDate()).isEqualTo(UPDATED_FROM_DATE);
        assertThat(testMapZonalWithOh.getToDate()).isEqualTo(UPDATED_TO_DATE);
        assertThat(testMapZonalWithOh.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testMapZonalWithOh.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingMapZonalWithOh() throws Exception {
        int databaseSizeBeforeUpdate = mapZonalWithOhRepository.findAll().size();

        // Create the MapZonalWithOh
        MapZonalWithOhDTO mapZonalWithOhDTO = mapZonalWithOhMapper.toDto(mapZonalWithOh);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMapZonalWithOhMockMvc.perform(put("/api/map-zonal-with-ohs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mapZonalWithOhDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MapZonalWithOh in the database
        List<MapZonalWithOh> mapZonalWithOhList = mapZonalWithOhRepository.findAll();
        assertThat(mapZonalWithOhList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMapZonalWithOh() throws Exception {
        // Initialize the database
        mapZonalWithOhRepository.saveAndFlush(mapZonalWithOh);

        int databaseSizeBeforeDelete = mapZonalWithOhRepository.findAll().size();

        // Get the mapZonalWithOh
        restMapZonalWithOhMockMvc.perform(delete("/api/map-zonal-with-ohs/{id}", mapZonalWithOh.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MapZonalWithOh> mapZonalWithOhList = mapZonalWithOhRepository.findAll();
        assertThat(mapZonalWithOhList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MapZonalWithOh.class);
        MapZonalWithOh mapZonalWithOh1 = new MapZonalWithOh();
        mapZonalWithOh1.setId(1L);
        MapZonalWithOh mapZonalWithOh2 = new MapZonalWithOh();
        mapZonalWithOh2.setId(mapZonalWithOh1.getId());
        assertThat(mapZonalWithOh1).isEqualTo(mapZonalWithOh2);
        mapZonalWithOh2.setId(2L);
        assertThat(mapZonalWithOh1).isNotEqualTo(mapZonalWithOh2);
        mapZonalWithOh1.setId(null);
        assertThat(mapZonalWithOh1).isNotEqualTo(mapZonalWithOh2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MapZonalWithOhDTO.class);
        MapZonalWithOhDTO mapZonalWithOhDTO1 = new MapZonalWithOhDTO();
        mapZonalWithOhDTO1.setId(1L);
        MapZonalWithOhDTO mapZonalWithOhDTO2 = new MapZonalWithOhDTO();
        assertThat(mapZonalWithOhDTO1).isNotEqualTo(mapZonalWithOhDTO2);
        mapZonalWithOhDTO2.setId(mapZonalWithOhDTO1.getId());
        assertThat(mapZonalWithOhDTO1).isEqualTo(mapZonalWithOhDTO2);
        mapZonalWithOhDTO2.setId(2L);
        assertThat(mapZonalWithOhDTO1).isNotEqualTo(mapZonalWithOhDTO2);
        mapZonalWithOhDTO1.setId(null);
        assertThat(mapZonalWithOhDTO1).isNotEqualTo(mapZonalWithOhDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mapZonalWithOhMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mapZonalWithOhMapper.fromId(null)).isNull();
    }
}
