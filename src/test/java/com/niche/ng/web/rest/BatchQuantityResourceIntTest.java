package com.niche.ng.web.rest;

import com.niche.ng.ProjectghApp;

import com.niche.ng.domain.BatchQuantity;
import com.niche.ng.domain.Batch;
import com.niche.ng.repository.BatchQuantityRepository;
import com.niche.ng.service.BatchQuantityService;
import com.niche.ng.service.dto.BatchQuantityDTO;
import com.niche.ng.service.mapper.BatchQuantityMapper;
import com.niche.ng.web.rest.errors.ExceptionTranslator;
import com.niche.ng.service.dto.BatchQuantityCriteria;
import com.niche.ng.service.BatchQuantityQueryService;

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
 * Test class for the BatchQuantityResource REST controller.
 *
 * @see BatchQuantityResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectghApp.class)
public class BatchQuantityResourceIntTest {

    private static final Integer DEFAULT_QUANTITY = 1;
    private static final Integer UPDATED_QUANTITY = 2;

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    @Autowired
    private BatchQuantityRepository batchQuantityRepository;


    @Autowired
    private BatchQuantityMapper batchQuantityMapper;
    

    @Autowired
    private BatchQuantityService batchQuantityService;

    @Autowired
    private BatchQuantityQueryService batchQuantityQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBatchQuantityMockMvc;

    private BatchQuantity batchQuantity;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BatchQuantityResource batchQuantityResource = new BatchQuantityResource(batchQuantityService, batchQuantityQueryService);
        this.restBatchQuantityMockMvc = MockMvcBuilders.standaloneSetup(batchQuantityResource)
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
    public static BatchQuantity createEntity(EntityManager em) {
        BatchQuantity batchQuantity = new BatchQuantity()
            .quantity(DEFAULT_QUANTITY)
            .date(DEFAULT_DATE)
            .remarks(DEFAULT_REMARKS)
            .status(DEFAULT_STATUS);
        return batchQuantity;
    }

    @Before
    public void initTest() {
        batchQuantity = createEntity(em);
    }

    @Test
    @Transactional
    public void createBatchQuantity() throws Exception {
        int databaseSizeBeforeCreate = batchQuantityRepository.findAll().size();

        // Create the BatchQuantity
        BatchQuantityDTO batchQuantityDTO = batchQuantityMapper.toDto(batchQuantity);
        restBatchQuantityMockMvc.perform(post("/api/batch-quantities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(batchQuantityDTO)))
            .andExpect(status().isCreated());

        // Validate the BatchQuantity in the database
        List<BatchQuantity> batchQuantityList = batchQuantityRepository.findAll();
        assertThat(batchQuantityList).hasSize(databaseSizeBeforeCreate + 1);
        BatchQuantity testBatchQuantity = batchQuantityList.get(batchQuantityList.size() - 1);
        assertThat(testBatchQuantity.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testBatchQuantity.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testBatchQuantity.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testBatchQuantity.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createBatchQuantityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = batchQuantityRepository.findAll().size();

        // Create the BatchQuantity with an existing ID
        batchQuantity.setId(1L);
        BatchQuantityDTO batchQuantityDTO = batchQuantityMapper.toDto(batchQuantity);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBatchQuantityMockMvc.perform(post("/api/batch-quantities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(batchQuantityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BatchQuantity in the database
        List<BatchQuantity> batchQuantityList = batchQuantityRepository.findAll();
        assertThat(batchQuantityList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkQuantityIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchQuantityRepository.findAll().size();
        // set the field null
        batchQuantity.setQuantity(null);

        // Create the BatchQuantity, which fails.
        BatchQuantityDTO batchQuantityDTO = batchQuantityMapper.toDto(batchQuantity);

        restBatchQuantityMockMvc.perform(post("/api/batch-quantities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(batchQuantityDTO)))
            .andExpect(status().isBadRequest());

        List<BatchQuantity> batchQuantityList = batchQuantityRepository.findAll();
        assertThat(batchQuantityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBatchQuantities() throws Exception {
        // Initialize the database
        batchQuantityRepository.saveAndFlush(batchQuantity);

        // Get all the batchQuantityList
        restBatchQuantityMockMvc.perform(get("/api/batch-quantities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(batchQuantity.getId().intValue())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    

    @Test
    @Transactional
    public void getBatchQuantity() throws Exception {
        // Initialize the database
        batchQuantityRepository.saveAndFlush(batchQuantity);

        // Get the batchQuantity
        restBatchQuantityMockMvc.perform(get("/api/batch-quantities/{id}", batchQuantity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(batchQuantity.getId().intValue()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getAllBatchQuantitiesByQuantityIsEqualToSomething() throws Exception {
        // Initialize the database
        batchQuantityRepository.saveAndFlush(batchQuantity);

        // Get all the batchQuantityList where quantity equals to DEFAULT_QUANTITY
        defaultBatchQuantityShouldBeFound("quantity.equals=" + DEFAULT_QUANTITY);

        // Get all the batchQuantityList where quantity equals to UPDATED_QUANTITY
        defaultBatchQuantityShouldNotBeFound("quantity.equals=" + UPDATED_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllBatchQuantitiesByQuantityIsInShouldWork() throws Exception {
        // Initialize the database
        batchQuantityRepository.saveAndFlush(batchQuantity);

        // Get all the batchQuantityList where quantity in DEFAULT_QUANTITY or UPDATED_QUANTITY
        defaultBatchQuantityShouldBeFound("quantity.in=" + DEFAULT_QUANTITY + "," + UPDATED_QUANTITY);

        // Get all the batchQuantityList where quantity equals to UPDATED_QUANTITY
        defaultBatchQuantityShouldNotBeFound("quantity.in=" + UPDATED_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllBatchQuantitiesByQuantityIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchQuantityRepository.saveAndFlush(batchQuantity);

        // Get all the batchQuantityList where quantity is not null
        defaultBatchQuantityShouldBeFound("quantity.specified=true");

        // Get all the batchQuantityList where quantity is null
        defaultBatchQuantityShouldNotBeFound("quantity.specified=false");
    }

    @Test
    @Transactional
    public void getAllBatchQuantitiesByQuantityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchQuantityRepository.saveAndFlush(batchQuantity);

        // Get all the batchQuantityList where quantity greater than or equals to DEFAULT_QUANTITY
        defaultBatchQuantityShouldBeFound("quantity.greaterOrEqualThan=" + DEFAULT_QUANTITY);

        // Get all the batchQuantityList where quantity greater than or equals to UPDATED_QUANTITY
        defaultBatchQuantityShouldNotBeFound("quantity.greaterOrEqualThan=" + UPDATED_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllBatchQuantitiesByQuantityIsLessThanSomething() throws Exception {
        // Initialize the database
        batchQuantityRepository.saveAndFlush(batchQuantity);

        // Get all the batchQuantityList where quantity less than or equals to DEFAULT_QUANTITY
        defaultBatchQuantityShouldNotBeFound("quantity.lessThan=" + DEFAULT_QUANTITY);

        // Get all the batchQuantityList where quantity less than or equals to UPDATED_QUANTITY
        defaultBatchQuantityShouldBeFound("quantity.lessThan=" + UPDATED_QUANTITY);
    }


    @Test
    @Transactional
    public void getAllBatchQuantitiesByDateIsEqualToSomething() throws Exception {
        // Initialize the database
        batchQuantityRepository.saveAndFlush(batchQuantity);

        // Get all the batchQuantityList where date equals to DEFAULT_DATE
        defaultBatchQuantityShouldBeFound("date.equals=" + DEFAULT_DATE);

        // Get all the batchQuantityList where date equals to UPDATED_DATE
        defaultBatchQuantityShouldNotBeFound("date.equals=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    public void getAllBatchQuantitiesByDateIsInShouldWork() throws Exception {
        // Initialize the database
        batchQuantityRepository.saveAndFlush(batchQuantity);

        // Get all the batchQuantityList where date in DEFAULT_DATE or UPDATED_DATE
        defaultBatchQuantityShouldBeFound("date.in=" + DEFAULT_DATE + "," + UPDATED_DATE);

        // Get all the batchQuantityList where date equals to UPDATED_DATE
        defaultBatchQuantityShouldNotBeFound("date.in=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    public void getAllBatchQuantitiesByDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchQuantityRepository.saveAndFlush(batchQuantity);

        // Get all the batchQuantityList where date is not null
        defaultBatchQuantityShouldBeFound("date.specified=true");

        // Get all the batchQuantityList where date is null
        defaultBatchQuantityShouldNotBeFound("date.specified=false");
    }

    @Test
    @Transactional
    public void getAllBatchQuantitiesByDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchQuantityRepository.saveAndFlush(batchQuantity);

        // Get all the batchQuantityList where date greater than or equals to DEFAULT_DATE
        defaultBatchQuantityShouldBeFound("date.greaterOrEqualThan=" + DEFAULT_DATE);

        // Get all the batchQuantityList where date greater than or equals to UPDATED_DATE
        defaultBatchQuantityShouldNotBeFound("date.greaterOrEqualThan=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    public void getAllBatchQuantitiesByDateIsLessThanSomething() throws Exception {
        // Initialize the database
        batchQuantityRepository.saveAndFlush(batchQuantity);

        // Get all the batchQuantityList where date less than or equals to DEFAULT_DATE
        defaultBatchQuantityShouldNotBeFound("date.lessThan=" + DEFAULT_DATE);

        // Get all the batchQuantityList where date less than or equals to UPDATED_DATE
        defaultBatchQuantityShouldBeFound("date.lessThan=" + UPDATED_DATE);
    }


    @Test
    @Transactional
    public void getAllBatchQuantitiesByRemarksIsEqualToSomething() throws Exception {
        // Initialize the database
        batchQuantityRepository.saveAndFlush(batchQuantity);

        // Get all the batchQuantityList where remarks equals to DEFAULT_REMARKS
        defaultBatchQuantityShouldBeFound("remarks.equals=" + DEFAULT_REMARKS);

        // Get all the batchQuantityList where remarks equals to UPDATED_REMARKS
        defaultBatchQuantityShouldNotBeFound("remarks.equals=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllBatchQuantitiesByRemarksIsInShouldWork() throws Exception {
        // Initialize the database
        batchQuantityRepository.saveAndFlush(batchQuantity);

        // Get all the batchQuantityList where remarks in DEFAULT_REMARKS or UPDATED_REMARKS
        defaultBatchQuantityShouldBeFound("remarks.in=" + DEFAULT_REMARKS + "," + UPDATED_REMARKS);

        // Get all the batchQuantityList where remarks equals to UPDATED_REMARKS
        defaultBatchQuantityShouldNotBeFound("remarks.in=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllBatchQuantitiesByRemarksIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchQuantityRepository.saveAndFlush(batchQuantity);

        // Get all the batchQuantityList where remarks is not null
        defaultBatchQuantityShouldBeFound("remarks.specified=true");

        // Get all the batchQuantityList where remarks is null
        defaultBatchQuantityShouldNotBeFound("remarks.specified=false");
    }

    @Test
    @Transactional
    public void getAllBatchQuantitiesByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        batchQuantityRepository.saveAndFlush(batchQuantity);

        // Get all the batchQuantityList where status equals to DEFAULT_STATUS
        defaultBatchQuantityShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the batchQuantityList where status equals to UPDATED_STATUS
        defaultBatchQuantityShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllBatchQuantitiesByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        batchQuantityRepository.saveAndFlush(batchQuantity);

        // Get all the batchQuantityList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultBatchQuantityShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the batchQuantityList where status equals to UPDATED_STATUS
        defaultBatchQuantityShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllBatchQuantitiesByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchQuantityRepository.saveAndFlush(batchQuantity);

        // Get all the batchQuantityList where status is not null
        defaultBatchQuantityShouldBeFound("status.specified=true");

        // Get all the batchQuantityList where status is null
        defaultBatchQuantityShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllBatchQuantitiesByStatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchQuantityRepository.saveAndFlush(batchQuantity);

        // Get all the batchQuantityList where status greater than or equals to DEFAULT_STATUS
        defaultBatchQuantityShouldBeFound("status.greaterOrEqualThan=" + DEFAULT_STATUS);

        // Get all the batchQuantityList where status greater than or equals to UPDATED_STATUS
        defaultBatchQuantityShouldNotBeFound("status.greaterOrEqualThan=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllBatchQuantitiesByStatusIsLessThanSomething() throws Exception {
        // Initialize the database
        batchQuantityRepository.saveAndFlush(batchQuantity);

        // Get all the batchQuantityList where status less than or equals to DEFAULT_STATUS
        defaultBatchQuantityShouldNotBeFound("status.lessThan=" + DEFAULT_STATUS);

        // Get all the batchQuantityList where status less than or equals to UPDATED_STATUS
        defaultBatchQuantityShouldBeFound("status.lessThan=" + UPDATED_STATUS);
    }


    @Test
    @Transactional
    public void getAllBatchQuantitiesByBatchIsEqualToSomething() throws Exception {
        // Initialize the database
        Batch batch = BatchResourceIntTest.createEntity(em);
        em.persist(batch);
        em.flush();
        batchQuantity.setBatch(batch);
        batchQuantityRepository.saveAndFlush(batchQuantity);
        Long batchId = batch.getId();

        // Get all the batchQuantityList where batch equals to batchId
        defaultBatchQuantityShouldBeFound("batchId.equals=" + batchId);

        // Get all the batchQuantityList where batch equals to batchId + 1
        defaultBatchQuantityShouldNotBeFound("batchId.equals=" + (batchId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultBatchQuantityShouldBeFound(String filter) throws Exception {
        restBatchQuantityMockMvc.perform(get("/api/batch-quantities?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(batchQuantity.getId().intValue())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultBatchQuantityShouldNotBeFound(String filter) throws Exception {
        restBatchQuantityMockMvc.perform(get("/api/batch-quantities?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingBatchQuantity() throws Exception {
        // Get the batchQuantity
        restBatchQuantityMockMvc.perform(get("/api/batch-quantities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBatchQuantity() throws Exception {
        // Initialize the database
        batchQuantityRepository.saveAndFlush(batchQuantity);

        int databaseSizeBeforeUpdate = batchQuantityRepository.findAll().size();

        // Update the batchQuantity
        BatchQuantity updatedBatchQuantity = batchQuantityRepository.findById(batchQuantity.getId()).get();
        // Disconnect from session so that the updates on updatedBatchQuantity are not directly saved in db
        em.detach(updatedBatchQuantity);
        updatedBatchQuantity
            .quantity(UPDATED_QUANTITY)
            .date(UPDATED_DATE)
            .remarks(UPDATED_REMARKS)
            .status(UPDATED_STATUS);
        BatchQuantityDTO batchQuantityDTO = batchQuantityMapper.toDto(updatedBatchQuantity);

        restBatchQuantityMockMvc.perform(put("/api/batch-quantities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(batchQuantityDTO)))
            .andExpect(status().isOk());

        // Validate the BatchQuantity in the database
        List<BatchQuantity> batchQuantityList = batchQuantityRepository.findAll();
        assertThat(batchQuantityList).hasSize(databaseSizeBeforeUpdate);
        BatchQuantity testBatchQuantity = batchQuantityList.get(batchQuantityList.size() - 1);
        assertThat(testBatchQuantity.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testBatchQuantity.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testBatchQuantity.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testBatchQuantity.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingBatchQuantity() throws Exception {
        int databaseSizeBeforeUpdate = batchQuantityRepository.findAll().size();

        // Create the BatchQuantity
        BatchQuantityDTO batchQuantityDTO = batchQuantityMapper.toDto(batchQuantity);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restBatchQuantityMockMvc.perform(put("/api/batch-quantities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(batchQuantityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BatchQuantity in the database
        List<BatchQuantity> batchQuantityList = batchQuantityRepository.findAll();
        assertThat(batchQuantityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBatchQuantity() throws Exception {
        // Initialize the database
        batchQuantityRepository.saveAndFlush(batchQuantity);

        int databaseSizeBeforeDelete = batchQuantityRepository.findAll().size();

        // Get the batchQuantity
        restBatchQuantityMockMvc.perform(delete("/api/batch-quantities/{id}", batchQuantity.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<BatchQuantity> batchQuantityList = batchQuantityRepository.findAll();
        assertThat(batchQuantityList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BatchQuantity.class);
        BatchQuantity batchQuantity1 = new BatchQuantity();
        batchQuantity1.setId(1L);
        BatchQuantity batchQuantity2 = new BatchQuantity();
        batchQuantity2.setId(batchQuantity1.getId());
        assertThat(batchQuantity1).isEqualTo(batchQuantity2);
        batchQuantity2.setId(2L);
        assertThat(batchQuantity1).isNotEqualTo(batchQuantity2);
        batchQuantity1.setId(null);
        assertThat(batchQuantity1).isNotEqualTo(batchQuantity2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BatchQuantityDTO.class);
        BatchQuantityDTO batchQuantityDTO1 = new BatchQuantityDTO();
        batchQuantityDTO1.setId(1L);
        BatchQuantityDTO batchQuantityDTO2 = new BatchQuantityDTO();
        assertThat(batchQuantityDTO1).isNotEqualTo(batchQuantityDTO2);
        batchQuantityDTO2.setId(batchQuantityDTO1.getId());
        assertThat(batchQuantityDTO1).isEqualTo(batchQuantityDTO2);
        batchQuantityDTO2.setId(2L);
        assertThat(batchQuantityDTO1).isNotEqualTo(batchQuantityDTO2);
        batchQuantityDTO1.setId(null);
        assertThat(batchQuantityDTO1).isNotEqualTo(batchQuantityDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(batchQuantityMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(batchQuantityMapper.fromId(null)).isNull();
    }
}
