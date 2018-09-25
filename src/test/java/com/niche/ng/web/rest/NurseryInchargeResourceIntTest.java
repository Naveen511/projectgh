package com.niche.ng.web.rest;

import com.niche.ng.ProjectghApp;

import com.niche.ng.domain.NurseryIncharge;
import com.niche.ng.domain.Nursery;
import com.niche.ng.repository.NurseryInchargeRepository;
import com.niche.ng.service.NurseryInchargeService;
import com.niche.ng.service.dto.NurseryInchargeDTO;
import com.niche.ng.service.mapper.NurseryInchargeMapper;
import com.niche.ng.web.rest.errors.ExceptionTranslator;
import com.niche.ng.service.dto.NurseryInchargeCriteria;
import com.niche.ng.service.NurseryInchargeQueryService;

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
 * Test class for the NurseryInchargeResource REST controller.
 *
 * @see NurseryInchargeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectghApp.class)
public class NurseryInchargeResourceIntTest {

    private static final LocalDate DEFAULT_FROM_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FROM_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_TO_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TO_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 10;
    private static final Integer UPDATED_STATUS = 9;

    @Autowired
    private NurseryInchargeRepository nurseryInchargeRepository;


    @Autowired
    private NurseryInchargeMapper nurseryInchargeMapper;
    

    @Autowired
    private NurseryInchargeService nurseryInchargeService;

    @Autowired
    private NurseryInchargeQueryService nurseryInchargeQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restNurseryInchargeMockMvc;

    private NurseryIncharge nurseryIncharge;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NurseryInchargeResource nurseryInchargeResource = new NurseryInchargeResource(nurseryInchargeService, nurseryInchargeQueryService);
        this.restNurseryInchargeMockMvc = MockMvcBuilders.standaloneSetup(nurseryInchargeResource)
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
    public static NurseryIncharge createEntity(EntityManager em) {
        NurseryIncharge nurseryIncharge = new NurseryIncharge()
            .fromDate(DEFAULT_FROM_DATE)
            .toDate(DEFAULT_TO_DATE)
            .description(DEFAULT_DESCRIPTION)
            .status(DEFAULT_STATUS);
        return nurseryIncharge;
    }

    @Before
    public void initTest() {
        nurseryIncharge = createEntity(em);
    }

    @Test
    @Transactional
    public void createNurseryIncharge() throws Exception {
        int databaseSizeBeforeCreate = nurseryInchargeRepository.findAll().size();

        // Create the NurseryIncharge
        NurseryInchargeDTO nurseryInchargeDTO = nurseryInchargeMapper.toDto(nurseryIncharge);
        restNurseryInchargeMockMvc.perform(post("/api/nursery-incharges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryInchargeDTO)))
            .andExpect(status().isCreated());

        // Validate the NurseryIncharge in the database
        List<NurseryIncharge> nurseryInchargeList = nurseryInchargeRepository.findAll();
        assertThat(nurseryInchargeList).hasSize(databaseSizeBeforeCreate + 1);
        NurseryIncharge testNurseryIncharge = nurseryInchargeList.get(nurseryInchargeList.size() - 1);
        assertThat(testNurseryIncharge.getFromDate()).isEqualTo(DEFAULT_FROM_DATE);
        assertThat(testNurseryIncharge.getToDate()).isEqualTo(DEFAULT_TO_DATE);
        assertThat(testNurseryIncharge.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testNurseryIncharge.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createNurseryInchargeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nurseryInchargeRepository.findAll().size();

        // Create the NurseryIncharge with an existing ID
        nurseryIncharge.setId(1L);
        NurseryInchargeDTO nurseryInchargeDTO = nurseryInchargeMapper.toDto(nurseryIncharge);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNurseryInchargeMockMvc.perform(post("/api/nursery-incharges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryInchargeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NurseryIncharge in the database
        List<NurseryIncharge> nurseryInchargeList = nurseryInchargeRepository.findAll();
        assertThat(nurseryInchargeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkFromDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = nurseryInchargeRepository.findAll().size();
        // set the field null
        nurseryIncharge.setFromDate(null);

        // Create the NurseryIncharge, which fails.
        NurseryInchargeDTO nurseryInchargeDTO = nurseryInchargeMapper.toDto(nurseryIncharge);

        restNurseryInchargeMockMvc.perform(post("/api/nursery-incharges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryInchargeDTO)))
            .andExpect(status().isBadRequest());

        List<NurseryIncharge> nurseryInchargeList = nurseryInchargeRepository.findAll();
        assertThat(nurseryInchargeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNurseryIncharges() throws Exception {
        // Initialize the database
        nurseryInchargeRepository.saveAndFlush(nurseryIncharge);

        // Get all the nurseryInchargeList
        restNurseryInchargeMockMvc.perform(get("/api/nursery-incharges?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nurseryIncharge.getId().intValue())))
            .andExpect(jsonPath("$.[*].fromDate").value(hasItem(DEFAULT_FROM_DATE.toString())))
            .andExpect(jsonPath("$.[*].toDate").value(hasItem(DEFAULT_TO_DATE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    

    @Test
    @Transactional
    public void getNurseryIncharge() throws Exception {
        // Initialize the database
        nurseryInchargeRepository.saveAndFlush(nurseryIncharge);

        // Get the nurseryIncharge
        restNurseryInchargeMockMvc.perform(get("/api/nursery-incharges/{id}", nurseryIncharge.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(nurseryIncharge.getId().intValue()))
            .andExpect(jsonPath("$.fromDate").value(DEFAULT_FROM_DATE.toString()))
            .andExpect(jsonPath("$.toDate").value(DEFAULT_TO_DATE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getAllNurseryInchargesByFromDateIsEqualToSomething() throws Exception {
        // Initialize the database
        nurseryInchargeRepository.saveAndFlush(nurseryIncharge);

        // Get all the nurseryInchargeList where fromDate equals to DEFAULT_FROM_DATE
        defaultNurseryInchargeShouldBeFound("fromDate.equals=" + DEFAULT_FROM_DATE);

        // Get all the nurseryInchargeList where fromDate equals to UPDATED_FROM_DATE
        defaultNurseryInchargeShouldNotBeFound("fromDate.equals=" + UPDATED_FROM_DATE);
    }

    @Test
    @Transactional
    public void getAllNurseryInchargesByFromDateIsInShouldWork() throws Exception {
        // Initialize the database
        nurseryInchargeRepository.saveAndFlush(nurseryIncharge);

        // Get all the nurseryInchargeList where fromDate in DEFAULT_FROM_DATE or UPDATED_FROM_DATE
        defaultNurseryInchargeShouldBeFound("fromDate.in=" + DEFAULT_FROM_DATE + "," + UPDATED_FROM_DATE);

        // Get all the nurseryInchargeList where fromDate equals to UPDATED_FROM_DATE
        defaultNurseryInchargeShouldNotBeFound("fromDate.in=" + UPDATED_FROM_DATE);
    }

    @Test
    @Transactional
    public void getAllNurseryInchargesByFromDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        nurseryInchargeRepository.saveAndFlush(nurseryIncharge);

        // Get all the nurseryInchargeList where fromDate is not null
        defaultNurseryInchargeShouldBeFound("fromDate.specified=true");

        // Get all the nurseryInchargeList where fromDate is null
        defaultNurseryInchargeShouldNotBeFound("fromDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllNurseryInchargesByFromDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        nurseryInchargeRepository.saveAndFlush(nurseryIncharge);

        // Get all the nurseryInchargeList where fromDate greater than or equals to DEFAULT_FROM_DATE
        defaultNurseryInchargeShouldBeFound("fromDate.greaterOrEqualThan=" + DEFAULT_FROM_DATE);

        // Get all the nurseryInchargeList where fromDate greater than or equals to UPDATED_FROM_DATE
        defaultNurseryInchargeShouldNotBeFound("fromDate.greaterOrEqualThan=" + UPDATED_FROM_DATE);
    }

    @Test
    @Transactional
    public void getAllNurseryInchargesByFromDateIsLessThanSomething() throws Exception {
        // Initialize the database
        nurseryInchargeRepository.saveAndFlush(nurseryIncharge);

        // Get all the nurseryInchargeList where fromDate less than or equals to DEFAULT_FROM_DATE
        defaultNurseryInchargeShouldNotBeFound("fromDate.lessThan=" + DEFAULT_FROM_DATE);

        // Get all the nurseryInchargeList where fromDate less than or equals to UPDATED_FROM_DATE
        defaultNurseryInchargeShouldBeFound("fromDate.lessThan=" + UPDATED_FROM_DATE);
    }


    @Test
    @Transactional
    public void getAllNurseryInchargesByToDateIsEqualToSomething() throws Exception {
        // Initialize the database
        nurseryInchargeRepository.saveAndFlush(nurseryIncharge);

        // Get all the nurseryInchargeList where toDate equals to DEFAULT_TO_DATE
        defaultNurseryInchargeShouldBeFound("toDate.equals=" + DEFAULT_TO_DATE);

        // Get all the nurseryInchargeList where toDate equals to UPDATED_TO_DATE
        defaultNurseryInchargeShouldNotBeFound("toDate.equals=" + UPDATED_TO_DATE);
    }

    @Test
    @Transactional
    public void getAllNurseryInchargesByToDateIsInShouldWork() throws Exception {
        // Initialize the database
        nurseryInchargeRepository.saveAndFlush(nurseryIncharge);

        // Get all the nurseryInchargeList where toDate in DEFAULT_TO_DATE or UPDATED_TO_DATE
        defaultNurseryInchargeShouldBeFound("toDate.in=" + DEFAULT_TO_DATE + "," + UPDATED_TO_DATE);

        // Get all the nurseryInchargeList where toDate equals to UPDATED_TO_DATE
        defaultNurseryInchargeShouldNotBeFound("toDate.in=" + UPDATED_TO_DATE);
    }

    @Test
    @Transactional
    public void getAllNurseryInchargesByToDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        nurseryInchargeRepository.saveAndFlush(nurseryIncharge);

        // Get all the nurseryInchargeList where toDate is not null
        defaultNurseryInchargeShouldBeFound("toDate.specified=true");

        // Get all the nurseryInchargeList where toDate is null
        defaultNurseryInchargeShouldNotBeFound("toDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllNurseryInchargesByToDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        nurseryInchargeRepository.saveAndFlush(nurseryIncharge);

        // Get all the nurseryInchargeList where toDate greater than or equals to DEFAULT_TO_DATE
        defaultNurseryInchargeShouldBeFound("toDate.greaterOrEqualThan=" + DEFAULT_TO_DATE);

        // Get all the nurseryInchargeList where toDate greater than or equals to UPDATED_TO_DATE
        defaultNurseryInchargeShouldNotBeFound("toDate.greaterOrEqualThan=" + UPDATED_TO_DATE);
    }

    @Test
    @Transactional
    public void getAllNurseryInchargesByToDateIsLessThanSomething() throws Exception {
        // Initialize the database
        nurseryInchargeRepository.saveAndFlush(nurseryIncharge);

        // Get all the nurseryInchargeList where toDate less than or equals to DEFAULT_TO_DATE
        defaultNurseryInchargeShouldNotBeFound("toDate.lessThan=" + DEFAULT_TO_DATE);

        // Get all the nurseryInchargeList where toDate less than or equals to UPDATED_TO_DATE
        defaultNurseryInchargeShouldBeFound("toDate.lessThan=" + UPDATED_TO_DATE);
    }


    @Test
    @Transactional
    public void getAllNurseryInchargesByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        nurseryInchargeRepository.saveAndFlush(nurseryIncharge);

        // Get all the nurseryInchargeList where description equals to DEFAULT_DESCRIPTION
        defaultNurseryInchargeShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the nurseryInchargeList where description equals to UPDATED_DESCRIPTION
        defaultNurseryInchargeShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllNurseryInchargesByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        nurseryInchargeRepository.saveAndFlush(nurseryIncharge);

        // Get all the nurseryInchargeList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultNurseryInchargeShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the nurseryInchargeList where description equals to UPDATED_DESCRIPTION
        defaultNurseryInchargeShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllNurseryInchargesByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        nurseryInchargeRepository.saveAndFlush(nurseryIncharge);

        // Get all the nurseryInchargeList where description is not null
        defaultNurseryInchargeShouldBeFound("description.specified=true");

        // Get all the nurseryInchargeList where description is null
        defaultNurseryInchargeShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllNurseryInchargesByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        nurseryInchargeRepository.saveAndFlush(nurseryIncharge);

        // Get all the nurseryInchargeList where status equals to DEFAULT_STATUS
        defaultNurseryInchargeShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the nurseryInchargeList where status equals to UPDATED_STATUS
        defaultNurseryInchargeShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllNurseryInchargesByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        nurseryInchargeRepository.saveAndFlush(nurseryIncharge);

        // Get all the nurseryInchargeList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultNurseryInchargeShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the nurseryInchargeList where status equals to UPDATED_STATUS
        defaultNurseryInchargeShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllNurseryInchargesByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        nurseryInchargeRepository.saveAndFlush(nurseryIncharge);

        // Get all the nurseryInchargeList where status is not null
        defaultNurseryInchargeShouldBeFound("status.specified=true");

        // Get all the nurseryInchargeList where status is null
        defaultNurseryInchargeShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllNurseryInchargesByStatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        nurseryInchargeRepository.saveAndFlush(nurseryIncharge);

        // Get all the nurseryInchargeList where status greater than or equals to DEFAULT_STATUS
        defaultNurseryInchargeShouldBeFound("status.greaterOrEqualThan=" + DEFAULT_STATUS);

        // Get all the nurseryInchargeList where status greater than or equals to (DEFAULT_STATUS + 1)
        defaultNurseryInchargeShouldNotBeFound("status.greaterOrEqualThan=" + (DEFAULT_STATUS + 1));
    }

    @Test
    @Transactional
    public void getAllNurseryInchargesByStatusIsLessThanSomething() throws Exception {
        // Initialize the database
        nurseryInchargeRepository.saveAndFlush(nurseryIncharge);

        // Get all the nurseryInchargeList where status less than or equals to DEFAULT_STATUS
        defaultNurseryInchargeShouldNotBeFound("status.lessThan=" + DEFAULT_STATUS);

        // Get all the nurseryInchargeList where status less than or equals to (DEFAULT_STATUS + 1)
        defaultNurseryInchargeShouldBeFound("status.lessThan=" + (DEFAULT_STATUS + 1));
    }


    @Test
    @Transactional
    public void getAllNurseryInchargesByNurseryIsEqualToSomething() throws Exception {
        // Initialize the database
        Nursery nursery = NurseryResourceIntTest.createEntity(em);
        em.persist(nursery);
        em.flush();
        nurseryIncharge.setNursery(nursery);
        nurseryInchargeRepository.saveAndFlush(nurseryIncharge);
        Long nurseryId = nursery.getId();

        // Get all the nurseryInchargeList where nursery equals to nurseryId
        defaultNurseryInchargeShouldBeFound("nurseryId.equals=" + nurseryId);

        // Get all the nurseryInchargeList where nursery equals to nurseryId + 1
        defaultNurseryInchargeShouldNotBeFound("nurseryId.equals=" + (nurseryId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultNurseryInchargeShouldBeFound(String filter) throws Exception {
        restNurseryInchargeMockMvc.perform(get("/api/nursery-incharges?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nurseryIncharge.getId().intValue())))
            .andExpect(jsonPath("$.[*].fromDate").value(hasItem(DEFAULT_FROM_DATE.toString())))
            .andExpect(jsonPath("$.[*].toDate").value(hasItem(DEFAULT_TO_DATE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultNurseryInchargeShouldNotBeFound(String filter) throws Exception {
        restNurseryInchargeMockMvc.perform(get("/api/nursery-incharges?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingNurseryIncharge() throws Exception {
        // Get the nurseryIncharge
        restNurseryInchargeMockMvc.perform(get("/api/nursery-incharges/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNurseryIncharge() throws Exception {
        // Initialize the database
        nurseryInchargeRepository.saveAndFlush(nurseryIncharge);

        int databaseSizeBeforeUpdate = nurseryInchargeRepository.findAll().size();

        // Update the nurseryIncharge
        NurseryIncharge updatedNurseryIncharge = nurseryInchargeRepository.findById(nurseryIncharge.getId()).get();
        // Disconnect from session so that the updates on updatedNurseryIncharge are not directly saved in db
        em.detach(updatedNurseryIncharge);
        updatedNurseryIncharge
            .fromDate(UPDATED_FROM_DATE)
            .toDate(UPDATED_TO_DATE)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS);
        NurseryInchargeDTO nurseryInchargeDTO = nurseryInchargeMapper.toDto(updatedNurseryIncharge);

        restNurseryInchargeMockMvc.perform(put("/api/nursery-incharges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryInchargeDTO)))
            .andExpect(status().isOk());

        // Validate the NurseryIncharge in the database
        List<NurseryIncharge> nurseryInchargeList = nurseryInchargeRepository.findAll();
        assertThat(nurseryInchargeList).hasSize(databaseSizeBeforeUpdate);
        NurseryIncharge testNurseryIncharge = nurseryInchargeList.get(nurseryInchargeList.size() - 1);
        assertThat(testNurseryIncharge.getFromDate()).isEqualTo(UPDATED_FROM_DATE);
        assertThat(testNurseryIncharge.getToDate()).isEqualTo(UPDATED_TO_DATE);
        assertThat(testNurseryIncharge.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testNurseryIncharge.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingNurseryIncharge() throws Exception {
        int databaseSizeBeforeUpdate = nurseryInchargeRepository.findAll().size();

        // Create the NurseryIncharge
        NurseryInchargeDTO nurseryInchargeDTO = nurseryInchargeMapper.toDto(nurseryIncharge);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restNurseryInchargeMockMvc.perform(put("/api/nursery-incharges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryInchargeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NurseryIncharge in the database
        List<NurseryIncharge> nurseryInchargeList = nurseryInchargeRepository.findAll();
        assertThat(nurseryInchargeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNurseryIncharge() throws Exception {
        // Initialize the database
        nurseryInchargeRepository.saveAndFlush(nurseryIncharge);

        int databaseSizeBeforeDelete = nurseryInchargeRepository.findAll().size();

        // Get the nurseryIncharge
        restNurseryInchargeMockMvc.perform(delete("/api/nursery-incharges/{id}", nurseryIncharge.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<NurseryIncharge> nurseryInchargeList = nurseryInchargeRepository.findAll();
        assertThat(nurseryInchargeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NurseryIncharge.class);
        NurseryIncharge nurseryIncharge1 = new NurseryIncharge();
        nurseryIncharge1.setId(1L);
        NurseryIncharge nurseryIncharge2 = new NurseryIncharge();
        nurseryIncharge2.setId(nurseryIncharge1.getId());
        assertThat(nurseryIncharge1).isEqualTo(nurseryIncharge2);
        nurseryIncharge2.setId(2L);
        assertThat(nurseryIncharge1).isNotEqualTo(nurseryIncharge2);
        nurseryIncharge1.setId(null);
        assertThat(nurseryIncharge1).isNotEqualTo(nurseryIncharge2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NurseryInchargeDTO.class);
        NurseryInchargeDTO nurseryInchargeDTO1 = new NurseryInchargeDTO();
        nurseryInchargeDTO1.setId(1L);
        NurseryInchargeDTO nurseryInchargeDTO2 = new NurseryInchargeDTO();
        assertThat(nurseryInchargeDTO1).isNotEqualTo(nurseryInchargeDTO2);
        nurseryInchargeDTO2.setId(nurseryInchargeDTO1.getId());
        assertThat(nurseryInchargeDTO1).isEqualTo(nurseryInchargeDTO2);
        nurseryInchargeDTO2.setId(2L);
        assertThat(nurseryInchargeDTO1).isNotEqualTo(nurseryInchargeDTO2);
        nurseryInchargeDTO1.setId(null);
        assertThat(nurseryInchargeDTO1).isNotEqualTo(nurseryInchargeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(nurseryInchargeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(nurseryInchargeMapper.fromId(null)).isNull();
    }
}
