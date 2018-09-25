package com.niche.ng.web.rest;

import com.niche.ng.ProjectghApp;

import com.niche.ng.domain.MotherBed;
import com.niche.ng.domain.Nursery;
import com.niche.ng.domain.Batch;
import com.niche.ng.repository.MotherBedRepository;
import com.niche.ng.service.MotherBedService;
import com.niche.ng.service.dto.MotherBedDTO;
import com.niche.ng.service.mapper.MotherBedMapper;
import com.niche.ng.web.rest.errors.ExceptionTranslator;
import com.niche.ng.service.dto.MotherBedCriteria;
import com.niche.ng.service.MotherBedQueryService;

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
import java.util.List;


import static com.niche.ng.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the MotherBedResource REST controller.
 *
 * @see MotherBedResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectghApp.class)
public class MotherBedResourceIntTest {

    private static final Integer DEFAULT_VALUE = 1;
    private static final Integer UPDATED_VALUE = 2;

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    @Autowired
    private MotherBedRepository motherBedRepository;


    @Autowired
    private MotherBedMapper motherBedMapper;
    

    @Autowired
    private MotherBedService motherBedService;

    @Autowired
    private MotherBedQueryService motherBedQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMotherBedMockMvc;

    private MotherBed motherBed;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MotherBedResource motherBedResource = new MotherBedResource(motherBedService, motherBedQueryService);
        this.restMotherBedMockMvc = MockMvcBuilders.standaloneSetup(motherBedResource)
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
    public static MotherBed createEntity(EntityManager em) {
        MotherBed motherBed = new MotherBed()
            .value(DEFAULT_VALUE)
            .status(DEFAULT_STATUS);
        // Add required entity
        Nursery nursery = NurseryResourceIntTest.createEntity(em);
        em.persist(nursery);
        em.flush();
        motherBed.setNursery(nursery);
        return motherBed;
    }

    @Before
    public void initTest() {
        motherBed = createEntity(em);
    }

    @Test
    @Transactional
    public void createMotherBed() throws Exception {
        int databaseSizeBeforeCreate = motherBedRepository.findAll().size();

        // Create the MotherBed
        MotherBedDTO motherBedDTO = motherBedMapper.toDto(motherBed);
        restMotherBedMockMvc.perform(post("/api/mother-beds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(motherBedDTO)))
            .andExpect(status().isCreated());

        // Validate the MotherBed in the database
        List<MotherBed> motherBedList = motherBedRepository.findAll();
        assertThat(motherBedList).hasSize(databaseSizeBeforeCreate + 1);
        MotherBed testMotherBed = motherBedList.get(motherBedList.size() - 1);
        assertThat(testMotherBed.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testMotherBed.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createMotherBedWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = motherBedRepository.findAll().size();

        // Create the MotherBed with an existing ID
        motherBed.setId(1L);
        MotherBedDTO motherBedDTO = motherBedMapper.toDto(motherBed);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMotherBedMockMvc.perform(post("/api/mother-beds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(motherBedDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MotherBed in the database
        List<MotherBed> motherBedList = motherBedRepository.findAll();
        assertThat(motherBedList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = motherBedRepository.findAll().size();
        // set the field null
        motherBed.setValue(null);

        // Create the MotherBed, which fails.
        MotherBedDTO motherBedDTO = motherBedMapper.toDto(motherBed);

        restMotherBedMockMvc.perform(post("/api/mother-beds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(motherBedDTO)))
            .andExpect(status().isBadRequest());

        List<MotherBed> motherBedList = motherBedRepository.findAll();
        assertThat(motherBedList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMotherBeds() throws Exception {
        // Initialize the database
        motherBedRepository.saveAndFlush(motherBed);

        // Get all the motherBedList
        restMotherBedMockMvc.perform(get("/api/mother-beds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(motherBed.getId().intValue())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    

    @Test
    @Transactional
    public void getMotherBed() throws Exception {
        // Initialize the database
        motherBedRepository.saveAndFlush(motherBed);

        // Get the motherBed
        restMotherBedMockMvc.perform(get("/api/mother-beds/{id}", motherBed.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(motherBed.getId().intValue()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getAllMotherBedsByValueIsEqualToSomething() throws Exception {
        // Initialize the database
        motherBedRepository.saveAndFlush(motherBed);

        // Get all the motherBedList where value equals to DEFAULT_VALUE
        defaultMotherBedShouldBeFound("value.equals=" + DEFAULT_VALUE);

        // Get all the motherBedList where value equals to UPDATED_VALUE
        defaultMotherBedShouldNotBeFound("value.equals=" + UPDATED_VALUE);
    }

    @Test
    @Transactional
    public void getAllMotherBedsByValueIsInShouldWork() throws Exception {
        // Initialize the database
        motherBedRepository.saveAndFlush(motherBed);

        // Get all the motherBedList where value in DEFAULT_VALUE or UPDATED_VALUE
        defaultMotherBedShouldBeFound("value.in=" + DEFAULT_VALUE + "," + UPDATED_VALUE);

        // Get all the motherBedList where value equals to UPDATED_VALUE
        defaultMotherBedShouldNotBeFound("value.in=" + UPDATED_VALUE);
    }

    @Test
    @Transactional
    public void getAllMotherBedsByValueIsNullOrNotNull() throws Exception {
        // Initialize the database
        motherBedRepository.saveAndFlush(motherBed);

        // Get all the motherBedList where value is not null
        defaultMotherBedShouldBeFound("value.specified=true");

        // Get all the motherBedList where value is null
        defaultMotherBedShouldNotBeFound("value.specified=false");
    }

    @Test
    @Transactional
    public void getAllMotherBedsByValueIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        motherBedRepository.saveAndFlush(motherBed);

        // Get all the motherBedList where value greater than or equals to DEFAULT_VALUE
        defaultMotherBedShouldBeFound("value.greaterOrEqualThan=" + DEFAULT_VALUE);

        // Get all the motherBedList where value greater than or equals to UPDATED_VALUE
        defaultMotherBedShouldNotBeFound("value.greaterOrEqualThan=" + UPDATED_VALUE);
    }

    @Test
    @Transactional
    public void getAllMotherBedsByValueIsLessThanSomething() throws Exception {
        // Initialize the database
        motherBedRepository.saveAndFlush(motherBed);

        // Get all the motherBedList where value less than or equals to DEFAULT_VALUE
        defaultMotherBedShouldNotBeFound("value.lessThan=" + DEFAULT_VALUE);

        // Get all the motherBedList where value less than or equals to UPDATED_VALUE
        defaultMotherBedShouldBeFound("value.lessThan=" + UPDATED_VALUE);
    }


    @Test
    @Transactional
    public void getAllMotherBedsByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        motherBedRepository.saveAndFlush(motherBed);

        // Get all the motherBedList where status equals to DEFAULT_STATUS
        defaultMotherBedShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the motherBedList where status equals to UPDATED_STATUS
        defaultMotherBedShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllMotherBedsByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        motherBedRepository.saveAndFlush(motherBed);

        // Get all the motherBedList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultMotherBedShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the motherBedList where status equals to UPDATED_STATUS
        defaultMotherBedShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllMotherBedsByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        motherBedRepository.saveAndFlush(motherBed);

        // Get all the motherBedList where status is not null
        defaultMotherBedShouldBeFound("status.specified=true");

        // Get all the motherBedList where status is null
        defaultMotherBedShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllMotherBedsByStatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        motherBedRepository.saveAndFlush(motherBed);

        // Get all the motherBedList where status greater than or equals to DEFAULT_STATUS
        defaultMotherBedShouldBeFound("status.greaterOrEqualThan=" + DEFAULT_STATUS);

        // Get all the motherBedList where status greater than or equals to UPDATED_STATUS
        defaultMotherBedShouldNotBeFound("status.greaterOrEqualThan=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllMotherBedsByStatusIsLessThanSomething() throws Exception {
        // Initialize the database
        motherBedRepository.saveAndFlush(motherBed);

        // Get all the motherBedList where status less than or equals to DEFAULT_STATUS
        defaultMotherBedShouldNotBeFound("status.lessThan=" + DEFAULT_STATUS);

        // Get all the motherBedList where status less than or equals to UPDATED_STATUS
        defaultMotherBedShouldBeFound("status.lessThan=" + UPDATED_STATUS);
    }


    @Test
    @Transactional
    public void getAllMotherBedsByNurseryIsEqualToSomething() throws Exception {
        // Initialize the database
        Nursery nursery = NurseryResourceIntTest.createEntity(em);
        em.persist(nursery);
        em.flush();
        motherBed.setNursery(nursery);
        motherBedRepository.saveAndFlush(motherBed);
        Long nurseryId = nursery.getId();

        // Get all the motherBedList where nursery equals to nurseryId
        defaultMotherBedShouldBeFound("nurseryId.equals=" + nurseryId);

        // Get all the motherBedList where nursery equals to nurseryId + 1
        defaultMotherBedShouldNotBeFound("nurseryId.equals=" + (nurseryId + 1));
    }


    @Test
    @Transactional
    public void getAllMotherBedsByBatchMotherBedIsEqualToSomething() throws Exception {
        // Initialize the database
        Batch batchMotherBed = BatchResourceIntTest.createEntity(em);
        em.persist(batchMotherBed);
        em.flush();
        motherBed.addBatchMotherBed(batchMotherBed);
        motherBedRepository.saveAndFlush(motherBed);
        Long batchMotherBedId = batchMotherBed.getId();

        // Get all the motherBedList where batchMotherBed equals to batchMotherBedId
        defaultMotherBedShouldBeFound("batchMotherBedId.equals=" + batchMotherBedId);

        // Get all the motherBedList where batchMotherBed equals to batchMotherBedId + 1
        defaultMotherBedShouldNotBeFound("batchMotherBedId.equals=" + (batchMotherBedId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultMotherBedShouldBeFound(String filter) throws Exception {
        restMotherBedMockMvc.perform(get("/api/mother-beds?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(motherBed.getId().intValue())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultMotherBedShouldNotBeFound(String filter) throws Exception {
        restMotherBedMockMvc.perform(get("/api/mother-beds?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingMotherBed() throws Exception {
        // Get the motherBed
        restMotherBedMockMvc.perform(get("/api/mother-beds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMotherBed() throws Exception {
        // Initialize the database
        motherBedRepository.saveAndFlush(motherBed);

        int databaseSizeBeforeUpdate = motherBedRepository.findAll().size();

        // Update the motherBed
        MotherBed updatedMotherBed = motherBedRepository.findById(motherBed.getId()).get();
        // Disconnect from session so that the updates on updatedMotherBed are not directly saved in db
        em.detach(updatedMotherBed);
        updatedMotherBed
            .value(UPDATED_VALUE)
            .status(UPDATED_STATUS);
        MotherBedDTO motherBedDTO = motherBedMapper.toDto(updatedMotherBed);

        restMotherBedMockMvc.perform(put("/api/mother-beds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(motherBedDTO)))
            .andExpect(status().isOk());

        // Validate the MotherBed in the database
        List<MotherBed> motherBedList = motherBedRepository.findAll();
        assertThat(motherBedList).hasSize(databaseSizeBeforeUpdate);
        MotherBed testMotherBed = motherBedList.get(motherBedList.size() - 1);
        assertThat(testMotherBed.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testMotherBed.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingMotherBed() throws Exception {
        int databaseSizeBeforeUpdate = motherBedRepository.findAll().size();

        // Create the MotherBed
        MotherBedDTO motherBedDTO = motherBedMapper.toDto(motherBed);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMotherBedMockMvc.perform(put("/api/mother-beds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(motherBedDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MotherBed in the database
        List<MotherBed> motherBedList = motherBedRepository.findAll();
        assertThat(motherBedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMotherBed() throws Exception {
        // Initialize the database
        motherBedRepository.saveAndFlush(motherBed);

        int databaseSizeBeforeDelete = motherBedRepository.findAll().size();

        // Get the motherBed
        restMotherBedMockMvc.perform(delete("/api/mother-beds/{id}", motherBed.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MotherBed> motherBedList = motherBedRepository.findAll();
        assertThat(motherBedList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MotherBed.class);
        MotherBed motherBed1 = new MotherBed();
        motherBed1.setId(1L);
        MotherBed motherBed2 = new MotherBed();
        motherBed2.setId(motherBed1.getId());
        assertThat(motherBed1).isEqualTo(motherBed2);
        motherBed2.setId(2L);
        assertThat(motherBed1).isNotEqualTo(motherBed2);
        motherBed1.setId(null);
        assertThat(motherBed1).isNotEqualTo(motherBed2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MotherBedDTO.class);
        MotherBedDTO motherBedDTO1 = new MotherBedDTO();
        motherBedDTO1.setId(1L);
        MotherBedDTO motherBedDTO2 = new MotherBedDTO();
        assertThat(motherBedDTO1).isNotEqualTo(motherBedDTO2);
        motherBedDTO2.setId(motherBedDTO1.getId());
        assertThat(motherBedDTO1).isEqualTo(motherBedDTO2);
        motherBedDTO2.setId(2L);
        assertThat(motherBedDTO1).isNotEqualTo(motherBedDTO2);
        motherBedDTO1.setId(null);
        assertThat(motherBedDTO1).isNotEqualTo(motherBedDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(motherBedMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(motherBedMapper.fromId(null)).isNull();
    }
}
