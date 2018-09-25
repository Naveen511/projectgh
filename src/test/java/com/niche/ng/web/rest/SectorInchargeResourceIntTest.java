package com.niche.ng.web.rest;

import com.niche.ng.ProjectghApp;

import com.niche.ng.domain.SectorIncharge;
import com.niche.ng.domain.Sector;
import com.niche.ng.repository.SectorInchargeRepository;
import com.niche.ng.service.SectorInchargeService;
import com.niche.ng.service.dto.SectorInchargeDTO;
import com.niche.ng.service.mapper.SectorInchargeMapper;
import com.niche.ng.web.rest.errors.ExceptionTranslator;
import com.niche.ng.service.dto.SectorInchargeCriteria;
import com.niche.ng.service.SectorInchargeQueryService;

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
 * Test class for the SectorInchargeResource REST controller.
 *
 * @see SectorInchargeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectghApp.class)
public class SectorInchargeResourceIntTest {

    private static final LocalDate DEFAULT_FROM_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FROM_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_TO_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TO_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 10;
    private static final Integer UPDATED_STATUS = 9;

    @Autowired
    private SectorInchargeRepository sectorInchargeRepository;


    @Autowired
    private SectorInchargeMapper sectorInchargeMapper;
    

    @Autowired
    private SectorInchargeService sectorInchargeService;

    @Autowired
    private SectorInchargeQueryService sectorInchargeQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSectorInchargeMockMvc;

    private SectorIncharge sectorIncharge;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SectorInchargeResource sectorInchargeResource = new SectorInchargeResource(sectorInchargeService, sectorInchargeQueryService);
        this.restSectorInchargeMockMvc = MockMvcBuilders.standaloneSetup(sectorInchargeResource)
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
    public static SectorIncharge createEntity(EntityManager em) {
        SectorIncharge sectorIncharge = new SectorIncharge()
            .fromDate(DEFAULT_FROM_DATE)
            .toDate(DEFAULT_TO_DATE)
            .description(DEFAULT_DESCRIPTION)
            .status(DEFAULT_STATUS);
        return sectorIncharge;
    }

    @Before
    public void initTest() {
        sectorIncharge = createEntity(em);
    }

    @Test
    @Transactional
    public void createSectorIncharge() throws Exception {
        int databaseSizeBeforeCreate = sectorInchargeRepository.findAll().size();

        // Create the SectorIncharge
        SectorInchargeDTO sectorInchargeDTO = sectorInchargeMapper.toDto(sectorIncharge);
        restSectorInchargeMockMvc.perform(post("/api/sector-incharges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sectorInchargeDTO)))
            .andExpect(status().isCreated());

        // Validate the SectorIncharge in the database
        List<SectorIncharge> sectorInchargeList = sectorInchargeRepository.findAll();
        assertThat(sectorInchargeList).hasSize(databaseSizeBeforeCreate + 1);
        SectorIncharge testSectorIncharge = sectorInchargeList.get(sectorInchargeList.size() - 1);
        assertThat(testSectorIncharge.getFromDate()).isEqualTo(DEFAULT_FROM_DATE);
        assertThat(testSectorIncharge.getToDate()).isEqualTo(DEFAULT_TO_DATE);
        assertThat(testSectorIncharge.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testSectorIncharge.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createSectorInchargeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sectorInchargeRepository.findAll().size();

        // Create the SectorIncharge with an existing ID
        sectorIncharge.setId(1L);
        SectorInchargeDTO sectorInchargeDTO = sectorInchargeMapper.toDto(sectorIncharge);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSectorInchargeMockMvc.perform(post("/api/sector-incharges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sectorInchargeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SectorIncharge in the database
        List<SectorIncharge> sectorInchargeList = sectorInchargeRepository.findAll();
        assertThat(sectorInchargeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkFromDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = sectorInchargeRepository.findAll().size();
        // set the field null
        sectorIncharge.setFromDate(null);

        // Create the SectorIncharge, which fails.
        SectorInchargeDTO sectorInchargeDTO = sectorInchargeMapper.toDto(sectorIncharge);

        restSectorInchargeMockMvc.perform(post("/api/sector-incharges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sectorInchargeDTO)))
            .andExpect(status().isBadRequest());

        List<SectorIncharge> sectorInchargeList = sectorInchargeRepository.findAll();
        assertThat(sectorInchargeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSectorIncharges() throws Exception {
        // Initialize the database
        sectorInchargeRepository.saveAndFlush(sectorIncharge);

        // Get all the sectorInchargeList
        restSectorInchargeMockMvc.perform(get("/api/sector-incharges?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sectorIncharge.getId().intValue())))
            .andExpect(jsonPath("$.[*].fromDate").value(hasItem(DEFAULT_FROM_DATE.toString())))
            .andExpect(jsonPath("$.[*].toDate").value(hasItem(DEFAULT_TO_DATE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    

    @Test
    @Transactional
    public void getSectorIncharge() throws Exception {
        // Initialize the database
        sectorInchargeRepository.saveAndFlush(sectorIncharge);

        // Get the sectorIncharge
        restSectorInchargeMockMvc.perform(get("/api/sector-incharges/{id}", sectorIncharge.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sectorIncharge.getId().intValue()))
            .andExpect(jsonPath("$.fromDate").value(DEFAULT_FROM_DATE.toString()))
            .andExpect(jsonPath("$.toDate").value(DEFAULT_TO_DATE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getAllSectorInchargesByFromDateIsEqualToSomething() throws Exception {
        // Initialize the database
        sectorInchargeRepository.saveAndFlush(sectorIncharge);

        // Get all the sectorInchargeList where fromDate equals to DEFAULT_FROM_DATE
        defaultSectorInchargeShouldBeFound("fromDate.equals=" + DEFAULT_FROM_DATE);

        // Get all the sectorInchargeList where fromDate equals to UPDATED_FROM_DATE
        defaultSectorInchargeShouldNotBeFound("fromDate.equals=" + UPDATED_FROM_DATE);
    }

    @Test
    @Transactional
    public void getAllSectorInchargesByFromDateIsInShouldWork() throws Exception {
        // Initialize the database
        sectorInchargeRepository.saveAndFlush(sectorIncharge);

        // Get all the sectorInchargeList where fromDate in DEFAULT_FROM_DATE or UPDATED_FROM_DATE
        defaultSectorInchargeShouldBeFound("fromDate.in=" + DEFAULT_FROM_DATE + "," + UPDATED_FROM_DATE);

        // Get all the sectorInchargeList where fromDate equals to UPDATED_FROM_DATE
        defaultSectorInchargeShouldNotBeFound("fromDate.in=" + UPDATED_FROM_DATE);
    }

    @Test
    @Transactional
    public void getAllSectorInchargesByFromDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        sectorInchargeRepository.saveAndFlush(sectorIncharge);

        // Get all the sectorInchargeList where fromDate is not null
        defaultSectorInchargeShouldBeFound("fromDate.specified=true");

        // Get all the sectorInchargeList where fromDate is null
        defaultSectorInchargeShouldNotBeFound("fromDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllSectorInchargesByFromDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sectorInchargeRepository.saveAndFlush(sectorIncharge);

        // Get all the sectorInchargeList where fromDate greater than or equals to DEFAULT_FROM_DATE
        defaultSectorInchargeShouldBeFound("fromDate.greaterOrEqualThan=" + DEFAULT_FROM_DATE);

        // Get all the sectorInchargeList where fromDate greater than or equals to UPDATED_FROM_DATE
        defaultSectorInchargeShouldNotBeFound("fromDate.greaterOrEqualThan=" + UPDATED_FROM_DATE);
    }

    @Test
    @Transactional
    public void getAllSectorInchargesByFromDateIsLessThanSomething() throws Exception {
        // Initialize the database
        sectorInchargeRepository.saveAndFlush(sectorIncharge);

        // Get all the sectorInchargeList where fromDate less than or equals to DEFAULT_FROM_DATE
        defaultSectorInchargeShouldNotBeFound("fromDate.lessThan=" + DEFAULT_FROM_DATE);

        // Get all the sectorInchargeList where fromDate less than or equals to UPDATED_FROM_DATE
        defaultSectorInchargeShouldBeFound("fromDate.lessThan=" + UPDATED_FROM_DATE);
    }


    @Test
    @Transactional
    public void getAllSectorInchargesByToDateIsEqualToSomething() throws Exception {
        // Initialize the database
        sectorInchargeRepository.saveAndFlush(sectorIncharge);

        // Get all the sectorInchargeList where toDate equals to DEFAULT_TO_DATE
        defaultSectorInchargeShouldBeFound("toDate.equals=" + DEFAULT_TO_DATE);

        // Get all the sectorInchargeList where toDate equals to UPDATED_TO_DATE
        defaultSectorInchargeShouldNotBeFound("toDate.equals=" + UPDATED_TO_DATE);
    }

    @Test
    @Transactional
    public void getAllSectorInchargesByToDateIsInShouldWork() throws Exception {
        // Initialize the database
        sectorInchargeRepository.saveAndFlush(sectorIncharge);

        // Get all the sectorInchargeList where toDate in DEFAULT_TO_DATE or UPDATED_TO_DATE
        defaultSectorInchargeShouldBeFound("toDate.in=" + DEFAULT_TO_DATE + "," + UPDATED_TO_DATE);

        // Get all the sectorInchargeList where toDate equals to UPDATED_TO_DATE
        defaultSectorInchargeShouldNotBeFound("toDate.in=" + UPDATED_TO_DATE);
    }

    @Test
    @Transactional
    public void getAllSectorInchargesByToDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        sectorInchargeRepository.saveAndFlush(sectorIncharge);

        // Get all the sectorInchargeList where toDate is not null
        defaultSectorInchargeShouldBeFound("toDate.specified=true");

        // Get all the sectorInchargeList where toDate is null
        defaultSectorInchargeShouldNotBeFound("toDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllSectorInchargesByToDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sectorInchargeRepository.saveAndFlush(sectorIncharge);

        // Get all the sectorInchargeList where toDate greater than or equals to DEFAULT_TO_DATE
        defaultSectorInchargeShouldBeFound("toDate.greaterOrEqualThan=" + DEFAULT_TO_DATE);

        // Get all the sectorInchargeList where toDate greater than or equals to UPDATED_TO_DATE
        defaultSectorInchargeShouldNotBeFound("toDate.greaterOrEqualThan=" + UPDATED_TO_DATE);
    }

    @Test
    @Transactional
    public void getAllSectorInchargesByToDateIsLessThanSomething() throws Exception {
        // Initialize the database
        sectorInchargeRepository.saveAndFlush(sectorIncharge);

        // Get all the sectorInchargeList where toDate less than or equals to DEFAULT_TO_DATE
        defaultSectorInchargeShouldNotBeFound("toDate.lessThan=" + DEFAULT_TO_DATE);

        // Get all the sectorInchargeList where toDate less than or equals to UPDATED_TO_DATE
        defaultSectorInchargeShouldBeFound("toDate.lessThan=" + UPDATED_TO_DATE);
    }


    @Test
    @Transactional
    public void getAllSectorInchargesByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        sectorInchargeRepository.saveAndFlush(sectorIncharge);

        // Get all the sectorInchargeList where description equals to DEFAULT_DESCRIPTION
        defaultSectorInchargeShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the sectorInchargeList where description equals to UPDATED_DESCRIPTION
        defaultSectorInchargeShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllSectorInchargesByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        sectorInchargeRepository.saveAndFlush(sectorIncharge);

        // Get all the sectorInchargeList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultSectorInchargeShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the sectorInchargeList where description equals to UPDATED_DESCRIPTION
        defaultSectorInchargeShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllSectorInchargesByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        sectorInchargeRepository.saveAndFlush(sectorIncharge);

        // Get all the sectorInchargeList where description is not null
        defaultSectorInchargeShouldBeFound("description.specified=true");

        // Get all the sectorInchargeList where description is null
        defaultSectorInchargeShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllSectorInchargesByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        sectorInchargeRepository.saveAndFlush(sectorIncharge);

        // Get all the sectorInchargeList where status equals to DEFAULT_STATUS
        defaultSectorInchargeShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the sectorInchargeList where status equals to UPDATED_STATUS
        defaultSectorInchargeShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllSectorInchargesByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        sectorInchargeRepository.saveAndFlush(sectorIncharge);

        // Get all the sectorInchargeList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultSectorInchargeShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the sectorInchargeList where status equals to UPDATED_STATUS
        defaultSectorInchargeShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllSectorInchargesByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        sectorInchargeRepository.saveAndFlush(sectorIncharge);

        // Get all the sectorInchargeList where status is not null
        defaultSectorInchargeShouldBeFound("status.specified=true");

        // Get all the sectorInchargeList where status is null
        defaultSectorInchargeShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllSectorInchargesByStatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sectorInchargeRepository.saveAndFlush(sectorIncharge);

        // Get all the sectorInchargeList where status greater than or equals to DEFAULT_STATUS
        defaultSectorInchargeShouldBeFound("status.greaterOrEqualThan=" + DEFAULT_STATUS);

        // Get all the sectorInchargeList where status greater than or equals to (DEFAULT_STATUS + 1)
        defaultSectorInchargeShouldNotBeFound("status.greaterOrEqualThan=" + (DEFAULT_STATUS + 1));
    }

    @Test
    @Transactional
    public void getAllSectorInchargesByStatusIsLessThanSomething() throws Exception {
        // Initialize the database
        sectorInchargeRepository.saveAndFlush(sectorIncharge);

        // Get all the sectorInchargeList where status less than or equals to DEFAULT_STATUS
        defaultSectorInchargeShouldNotBeFound("status.lessThan=" + DEFAULT_STATUS);

        // Get all the sectorInchargeList where status less than or equals to (DEFAULT_STATUS + 1)
        defaultSectorInchargeShouldBeFound("status.lessThan=" + (DEFAULT_STATUS + 1));
    }


    @Test
    @Transactional
    public void getAllSectorInchargesBySectorIsEqualToSomething() throws Exception {
        // Initialize the database
        Sector sector = SectorResourceIntTest.createEntity(em);
        em.persist(sector);
        em.flush();
        sectorIncharge.setSector(sector);
        sectorInchargeRepository.saveAndFlush(sectorIncharge);
        Long sectorId = sector.getId();

        // Get all the sectorInchargeList where sector equals to sectorId
        defaultSectorInchargeShouldBeFound("sectorId.equals=" + sectorId);

        // Get all the sectorInchargeList where sector equals to sectorId + 1
        defaultSectorInchargeShouldNotBeFound("sectorId.equals=" + (sectorId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultSectorInchargeShouldBeFound(String filter) throws Exception {
        restSectorInchargeMockMvc.perform(get("/api/sector-incharges?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sectorIncharge.getId().intValue())))
            .andExpect(jsonPath("$.[*].fromDate").value(hasItem(DEFAULT_FROM_DATE.toString())))
            .andExpect(jsonPath("$.[*].toDate").value(hasItem(DEFAULT_TO_DATE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultSectorInchargeShouldNotBeFound(String filter) throws Exception {
        restSectorInchargeMockMvc.perform(get("/api/sector-incharges?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingSectorIncharge() throws Exception {
        // Get the sectorIncharge
        restSectorInchargeMockMvc.perform(get("/api/sector-incharges/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSectorIncharge() throws Exception {
        // Initialize the database
        sectorInchargeRepository.saveAndFlush(sectorIncharge);

        int databaseSizeBeforeUpdate = sectorInchargeRepository.findAll().size();

        // Update the sectorIncharge
        SectorIncharge updatedSectorIncharge = sectorInchargeRepository.findById(sectorIncharge.getId()).get();
        // Disconnect from session so that the updates on updatedSectorIncharge are not directly saved in db
        em.detach(updatedSectorIncharge);
        updatedSectorIncharge
            .fromDate(UPDATED_FROM_DATE)
            .toDate(UPDATED_TO_DATE)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS);
        SectorInchargeDTO sectorInchargeDTO = sectorInchargeMapper.toDto(updatedSectorIncharge);

        restSectorInchargeMockMvc.perform(put("/api/sector-incharges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sectorInchargeDTO)))
            .andExpect(status().isOk());

        // Validate the SectorIncharge in the database
        List<SectorIncharge> sectorInchargeList = sectorInchargeRepository.findAll();
        assertThat(sectorInchargeList).hasSize(databaseSizeBeforeUpdate);
        SectorIncharge testSectorIncharge = sectorInchargeList.get(sectorInchargeList.size() - 1);
        assertThat(testSectorIncharge.getFromDate()).isEqualTo(UPDATED_FROM_DATE);
        assertThat(testSectorIncharge.getToDate()).isEqualTo(UPDATED_TO_DATE);
        assertThat(testSectorIncharge.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testSectorIncharge.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingSectorIncharge() throws Exception {
        int databaseSizeBeforeUpdate = sectorInchargeRepository.findAll().size();

        // Create the SectorIncharge
        SectorInchargeDTO sectorInchargeDTO = sectorInchargeMapper.toDto(sectorIncharge);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSectorInchargeMockMvc.perform(put("/api/sector-incharges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sectorInchargeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SectorIncharge in the database
        List<SectorIncharge> sectorInchargeList = sectorInchargeRepository.findAll();
        assertThat(sectorInchargeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSectorIncharge() throws Exception {
        // Initialize the database
        sectorInchargeRepository.saveAndFlush(sectorIncharge);

        int databaseSizeBeforeDelete = sectorInchargeRepository.findAll().size();

        // Get the sectorIncharge
        restSectorInchargeMockMvc.perform(delete("/api/sector-incharges/{id}", sectorIncharge.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SectorIncharge> sectorInchargeList = sectorInchargeRepository.findAll();
        assertThat(sectorInchargeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SectorIncharge.class);
        SectorIncharge sectorIncharge1 = new SectorIncharge();
        sectorIncharge1.setId(1L);
        SectorIncharge sectorIncharge2 = new SectorIncharge();
        sectorIncharge2.setId(sectorIncharge1.getId());
        assertThat(sectorIncharge1).isEqualTo(sectorIncharge2);
        sectorIncharge2.setId(2L);
        assertThat(sectorIncharge1).isNotEqualTo(sectorIncharge2);
        sectorIncharge1.setId(null);
        assertThat(sectorIncharge1).isNotEqualTo(sectorIncharge2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SectorInchargeDTO.class);
        SectorInchargeDTO sectorInchargeDTO1 = new SectorInchargeDTO();
        sectorInchargeDTO1.setId(1L);
        SectorInchargeDTO sectorInchargeDTO2 = new SectorInchargeDTO();
        assertThat(sectorInchargeDTO1).isNotEqualTo(sectorInchargeDTO2);
        sectorInchargeDTO2.setId(sectorInchargeDTO1.getId());
        assertThat(sectorInchargeDTO1).isEqualTo(sectorInchargeDTO2);
        sectorInchargeDTO2.setId(2L);
        assertThat(sectorInchargeDTO1).isNotEqualTo(sectorInchargeDTO2);
        sectorInchargeDTO1.setId(null);
        assertThat(sectorInchargeDTO1).isNotEqualTo(sectorInchargeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(sectorInchargeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(sectorInchargeMapper.fromId(null)).isNull();
    }
}
