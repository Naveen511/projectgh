package com.niche.ng.web.rest;

import com.niche.ng.ProjectghApp;

import com.niche.ng.domain.Batch;
import com.niche.ng.domain.Damage;
import com.niche.ng.domain.ShadeArea;
import com.niche.ng.domain.NurseryStockDetails;
import com.niche.ng.domain.Nursery;
import com.niche.ng.domain.PickListValue;
import com.niche.ng.domain.PickListValue;
import com.niche.ng.domain.PickListValue;
import com.niche.ng.domain.MotherBed;
import com.niche.ng.domain.FinancialYearSettings;
import com.niche.ng.repository.BatchRepository;
import com.niche.ng.service.BatchService;
import com.niche.ng.service.dto.BatchDTO;
import com.niche.ng.service.mapper.BatchMapper;
import com.niche.ng.web.rest.errors.ExceptionTranslator;
import com.niche.ng.service.dto.BatchCriteria;
import com.niche.ng.service.BatchQueryService;

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
 * Test class for the BatchResource REST controller.
 *
 * @see BatchResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectghApp.class)
public class BatchResourceIntTest {

    private static final String DEFAULT_BATCH_NO = "AAAAAAAAAA";
    private static final String UPDATED_BATCH_NO = "BBBBBBBBBB";

    private static final String DEFAULT_BATCH_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BATCH_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_QUANTITY = 1L;
    private static final Long UPDATED_QUANTITY = 2L;

    private static final Integer DEFAULT_SHOWING_TYPE = 1;
    private static final Integer UPDATED_SHOWING_TYPE = 2;

    private static final LocalDate DEFAULT_SOWING_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SOWING_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_CLOSED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CLOSED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_ROUND = 1;
    private static final Integer UPDATED_ROUND = 2;

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final String DEFAULT_NO_OF_KG = "AAAAAAAAAA";
    private static final String UPDATED_NO_OF_KG = "BBBBBBBBBB";

    @Autowired
    private BatchRepository batchRepository;


    @Autowired
    private BatchMapper batchMapper;
    

    @Autowired
    private BatchService batchService;

    @Autowired
    private BatchQueryService batchQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBatchMockMvc;

    private Batch batch;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BatchResource batchResource = new BatchResource(batchService, batchQueryService);
        this.restBatchMockMvc = MockMvcBuilders.standaloneSetup(batchResource)
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
    public static Batch createEntity(EntityManager em) {
        Batch batch = new Batch()
            .batchNo(DEFAULT_BATCH_NO)
            .batchName(DEFAULT_BATCH_NAME)
            .quantity(DEFAULT_QUANTITY)
            .showingType(DEFAULT_SHOWING_TYPE)
            .sowingDate(DEFAULT_SOWING_DATE)
            .closedDate(DEFAULT_CLOSED_DATE)
            .round(DEFAULT_ROUND)
            .remarks(DEFAULT_REMARKS)
            .status(DEFAULT_STATUS)
            .noOfKg(DEFAULT_NO_OF_KG);
        return batch;
    }

    @Before
    public void initTest() {
        batch = createEntity(em);
    }

    @Test
    @Transactional
    public void createBatch() throws Exception {
        int databaseSizeBeforeCreate = batchRepository.findAll().size();

        // Create the Batch
        BatchDTO batchDTO = batchMapper.toDto(batch);
        restBatchMockMvc.perform(post("/api/batches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(batchDTO)))
            .andExpect(status().isCreated());

        // Validate the Batch in the database
        List<Batch> batchList = batchRepository.findAll();
        assertThat(batchList).hasSize(databaseSizeBeforeCreate + 1);
        Batch testBatch = batchList.get(batchList.size() - 1);
        assertThat(testBatch.getBatchNo()).isEqualTo(DEFAULT_BATCH_NO);
        assertThat(testBatch.getBatchName()).isEqualTo(DEFAULT_BATCH_NAME);
        assertThat(testBatch.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testBatch.getShowingType()).isEqualTo(DEFAULT_SHOWING_TYPE);
        assertThat(testBatch.getSowingDate()).isEqualTo(DEFAULT_SOWING_DATE);
        assertThat(testBatch.getClosedDate()).isEqualTo(DEFAULT_CLOSED_DATE);
        assertThat(testBatch.getRound()).isEqualTo(DEFAULT_ROUND);
        assertThat(testBatch.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testBatch.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testBatch.getNoOfKg()).isEqualTo(DEFAULT_NO_OF_KG);
    }

    @Test
    @Transactional
    public void createBatchWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = batchRepository.findAll().size();

        // Create the Batch with an existing ID
        batch.setId(1L);
        BatchDTO batchDTO = batchMapper.toDto(batch);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBatchMockMvc.perform(post("/api/batches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(batchDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Batch in the database
        List<Batch> batchList = batchRepository.findAll();
        assertThat(batchList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkBatchNoIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchRepository.findAll().size();
        // set the field null
        batch.setBatchNo(null);

        // Create the Batch, which fails.
        BatchDTO batchDTO = batchMapper.toDto(batch);

        restBatchMockMvc.perform(post("/api/batches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(batchDTO)))
            .andExpect(status().isBadRequest());

        List<Batch> batchList = batchRepository.findAll();
        assertThat(batchList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBatchNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchRepository.findAll().size();
        // set the field null
        batch.setBatchName(null);

        // Create the Batch, which fails.
        BatchDTO batchDTO = batchMapper.toDto(batch);

        restBatchMockMvc.perform(post("/api/batches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(batchDTO)))
            .andExpect(status().isBadRequest());

        List<Batch> batchList = batchRepository.findAll();
        assertThat(batchList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQuantityIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchRepository.findAll().size();
        // set the field null
        batch.setQuantity(null);

        // Create the Batch, which fails.
        BatchDTO batchDTO = batchMapper.toDto(batch);

        restBatchMockMvc.perform(post("/api/batches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(batchDTO)))
            .andExpect(status().isBadRequest());

        List<Batch> batchList = batchRepository.findAll();
        assertThat(batchList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkShowingTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchRepository.findAll().size();
        // set the field null
        batch.setShowingType(null);

        // Create the Batch, which fails.
        BatchDTO batchDTO = batchMapper.toDto(batch);

        restBatchMockMvc.perform(post("/api/batches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(batchDTO)))
            .andExpect(status().isBadRequest());

        List<Batch> batchList = batchRepository.findAll();
        assertThat(batchList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSowingDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchRepository.findAll().size();
        // set the field null
        batch.setSowingDate(null);

        // Create the Batch, which fails.
        BatchDTO batchDTO = batchMapper.toDto(batch);

        restBatchMockMvc.perform(post("/api/batches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(batchDTO)))
            .andExpect(status().isBadRequest());

        List<Batch> batchList = batchRepository.findAll();
        assertThat(batchList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBatches() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get all the batchList
        restBatchMockMvc.perform(get("/api/batches?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(batch.getId().intValue())))
            .andExpect(jsonPath("$.[*].batchNo").value(hasItem(DEFAULT_BATCH_NO.toString())))
            .andExpect(jsonPath("$.[*].batchName").value(hasItem(DEFAULT_BATCH_NAME.toString())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].showingType").value(hasItem(DEFAULT_SHOWING_TYPE)))
            .andExpect(jsonPath("$.[*].sowingDate").value(hasItem(DEFAULT_SOWING_DATE.toString())))
            .andExpect(jsonPath("$.[*].closedDate").value(hasItem(DEFAULT_CLOSED_DATE.toString())))
            .andExpect(jsonPath("$.[*].round").value(hasItem(DEFAULT_ROUND)))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].noOfKg").value(hasItem(DEFAULT_NO_OF_KG.toString())));
    }
    

    @Test
    @Transactional
    public void getBatch() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get the batch
        restBatchMockMvc.perform(get("/api/batches/{id}", batch.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(batch.getId().intValue()))
            .andExpect(jsonPath("$.batchNo").value(DEFAULT_BATCH_NO.toString()))
            .andExpect(jsonPath("$.batchName").value(DEFAULT_BATCH_NAME.toString()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY.intValue()))
            .andExpect(jsonPath("$.showingType").value(DEFAULT_SHOWING_TYPE))
            .andExpect(jsonPath("$.sowingDate").value(DEFAULT_SOWING_DATE.toString()))
            .andExpect(jsonPath("$.closedDate").value(DEFAULT_CLOSED_DATE.toString()))
            .andExpect(jsonPath("$.round").value(DEFAULT_ROUND))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.noOfKg").value(DEFAULT_NO_OF_KG.toString()));
    }

    @Test
    @Transactional
    public void getAllBatchesByBatchNoIsEqualToSomething() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get all the batchList where batchNo equals to DEFAULT_BATCH_NO
        defaultBatchShouldBeFound("batchNo.equals=" + DEFAULT_BATCH_NO);

        // Get all the batchList where batchNo equals to UPDATED_BATCH_NO
        defaultBatchShouldNotBeFound("batchNo.equals=" + UPDATED_BATCH_NO);
    }

    @Test
    @Transactional
    public void getAllBatchesByBatchNoIsInShouldWork() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get all the batchList where batchNo in DEFAULT_BATCH_NO or UPDATED_BATCH_NO
        defaultBatchShouldBeFound("batchNo.in=" + DEFAULT_BATCH_NO + "," + UPDATED_BATCH_NO);

        // Get all the batchList where batchNo equals to UPDATED_BATCH_NO
        defaultBatchShouldNotBeFound("batchNo.in=" + UPDATED_BATCH_NO);
    }

    @Test
    @Transactional
    public void getAllBatchesByBatchNoIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get all the batchList where batchNo is not null
        defaultBatchShouldBeFound("batchNo.specified=true");

        // Get all the batchList where batchNo is null
        defaultBatchShouldNotBeFound("batchNo.specified=false");
    }

    @Test
    @Transactional
    public void getAllBatchesByBatchNameIsEqualToSomething() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get all the batchList where batchName equals to DEFAULT_BATCH_NAME
        defaultBatchShouldBeFound("batchName.equals=" + DEFAULT_BATCH_NAME);

        // Get all the batchList where batchName equals to UPDATED_BATCH_NAME
        defaultBatchShouldNotBeFound("batchName.equals=" + UPDATED_BATCH_NAME);
    }

    @Test
    @Transactional
    public void getAllBatchesByBatchNameIsInShouldWork() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get all the batchList where batchName in DEFAULT_BATCH_NAME or UPDATED_BATCH_NAME
        defaultBatchShouldBeFound("batchName.in=" + DEFAULT_BATCH_NAME + "," + UPDATED_BATCH_NAME);

        // Get all the batchList where batchName equals to UPDATED_BATCH_NAME
        defaultBatchShouldNotBeFound("batchName.in=" + UPDATED_BATCH_NAME);
    }

    @Test
    @Transactional
    public void getAllBatchesByBatchNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get all the batchList where batchName is not null
        defaultBatchShouldBeFound("batchName.specified=true");

        // Get all the batchList where batchName is null
        defaultBatchShouldNotBeFound("batchName.specified=false");
    }

    @Test
    @Transactional
    public void getAllBatchesByQuantityIsEqualToSomething() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get all the batchList where quantity equals to DEFAULT_QUANTITY
        defaultBatchShouldBeFound("quantity.equals=" + DEFAULT_QUANTITY);

        // Get all the batchList where quantity equals to UPDATED_QUANTITY
        defaultBatchShouldNotBeFound("quantity.equals=" + UPDATED_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllBatchesByQuantityIsInShouldWork() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get all the batchList where quantity in DEFAULT_QUANTITY or UPDATED_QUANTITY
        defaultBatchShouldBeFound("quantity.in=" + DEFAULT_QUANTITY + "," + UPDATED_QUANTITY);

        // Get all the batchList where quantity equals to UPDATED_QUANTITY
        defaultBatchShouldNotBeFound("quantity.in=" + UPDATED_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllBatchesByQuantityIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get all the batchList where quantity is not null
        defaultBatchShouldBeFound("quantity.specified=true");

        // Get all the batchList where quantity is null
        defaultBatchShouldNotBeFound("quantity.specified=false");
    }

    @Test
    @Transactional
    public void getAllBatchesByQuantityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get all the batchList where quantity greater than or equals to DEFAULT_QUANTITY
        defaultBatchShouldBeFound("quantity.greaterOrEqualThan=" + DEFAULT_QUANTITY);

        // Get all the batchList where quantity greater than or equals to UPDATED_QUANTITY
        defaultBatchShouldNotBeFound("quantity.greaterOrEqualThan=" + UPDATED_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllBatchesByQuantityIsLessThanSomething() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get all the batchList where quantity less than or equals to DEFAULT_QUANTITY
        defaultBatchShouldNotBeFound("quantity.lessThan=" + DEFAULT_QUANTITY);

        // Get all the batchList where quantity less than or equals to UPDATED_QUANTITY
        defaultBatchShouldBeFound("quantity.lessThan=" + UPDATED_QUANTITY);
    }


    @Test
    @Transactional
    public void getAllBatchesByShowingTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get all the batchList where showingType equals to DEFAULT_SHOWING_TYPE
        defaultBatchShouldBeFound("showingType.equals=" + DEFAULT_SHOWING_TYPE);

        // Get all the batchList where showingType equals to UPDATED_SHOWING_TYPE
        defaultBatchShouldNotBeFound("showingType.equals=" + UPDATED_SHOWING_TYPE);
    }

    @Test
    @Transactional
    public void getAllBatchesByShowingTypeIsInShouldWork() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get all the batchList where showingType in DEFAULT_SHOWING_TYPE or UPDATED_SHOWING_TYPE
        defaultBatchShouldBeFound("showingType.in=" + DEFAULT_SHOWING_TYPE + "," + UPDATED_SHOWING_TYPE);

        // Get all the batchList where showingType equals to UPDATED_SHOWING_TYPE
        defaultBatchShouldNotBeFound("showingType.in=" + UPDATED_SHOWING_TYPE);
    }

    @Test
    @Transactional
    public void getAllBatchesByShowingTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get all the batchList where showingType is not null
        defaultBatchShouldBeFound("showingType.specified=true");

        // Get all the batchList where showingType is null
        defaultBatchShouldNotBeFound("showingType.specified=false");
    }

    @Test
    @Transactional
    public void getAllBatchesByShowingTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get all the batchList where showingType greater than or equals to DEFAULT_SHOWING_TYPE
        defaultBatchShouldBeFound("showingType.greaterOrEqualThan=" + DEFAULT_SHOWING_TYPE);

        // Get all the batchList where showingType greater than or equals to UPDATED_SHOWING_TYPE
        defaultBatchShouldNotBeFound("showingType.greaterOrEqualThan=" + UPDATED_SHOWING_TYPE);
    }

    @Test
    @Transactional
    public void getAllBatchesByShowingTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get all the batchList where showingType less than or equals to DEFAULT_SHOWING_TYPE
        defaultBatchShouldNotBeFound("showingType.lessThan=" + DEFAULT_SHOWING_TYPE);

        // Get all the batchList where showingType less than or equals to UPDATED_SHOWING_TYPE
        defaultBatchShouldBeFound("showingType.lessThan=" + UPDATED_SHOWING_TYPE);
    }


    @Test
    @Transactional
    public void getAllBatchesBySowingDateIsEqualToSomething() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get all the batchList where sowingDate equals to DEFAULT_SOWING_DATE
        defaultBatchShouldBeFound("sowingDate.equals=" + DEFAULT_SOWING_DATE);

        // Get all the batchList where sowingDate equals to UPDATED_SOWING_DATE
        defaultBatchShouldNotBeFound("sowingDate.equals=" + UPDATED_SOWING_DATE);
    }

    @Test
    @Transactional
    public void getAllBatchesBySowingDateIsInShouldWork() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get all the batchList where sowingDate in DEFAULT_SOWING_DATE or UPDATED_SOWING_DATE
        defaultBatchShouldBeFound("sowingDate.in=" + DEFAULT_SOWING_DATE + "," + UPDATED_SOWING_DATE);

        // Get all the batchList where sowingDate equals to UPDATED_SOWING_DATE
        defaultBatchShouldNotBeFound("sowingDate.in=" + UPDATED_SOWING_DATE);
    }

    @Test
    @Transactional
    public void getAllBatchesBySowingDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get all the batchList where sowingDate is not null
        defaultBatchShouldBeFound("sowingDate.specified=true");

        // Get all the batchList where sowingDate is null
        defaultBatchShouldNotBeFound("sowingDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllBatchesBySowingDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get all the batchList where sowingDate greater than or equals to DEFAULT_SOWING_DATE
        defaultBatchShouldBeFound("sowingDate.greaterOrEqualThan=" + DEFAULT_SOWING_DATE);

        // Get all the batchList where sowingDate greater than or equals to UPDATED_SOWING_DATE
        defaultBatchShouldNotBeFound("sowingDate.greaterOrEqualThan=" + UPDATED_SOWING_DATE);
    }

    @Test
    @Transactional
    public void getAllBatchesBySowingDateIsLessThanSomething() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get all the batchList where sowingDate less than or equals to DEFAULT_SOWING_DATE
        defaultBatchShouldNotBeFound("sowingDate.lessThan=" + DEFAULT_SOWING_DATE);

        // Get all the batchList where sowingDate less than or equals to UPDATED_SOWING_DATE
        defaultBatchShouldBeFound("sowingDate.lessThan=" + UPDATED_SOWING_DATE);
    }


    @Test
    @Transactional
    public void getAllBatchesByClosedDateIsEqualToSomething() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get all the batchList where closedDate equals to DEFAULT_CLOSED_DATE
        defaultBatchShouldBeFound("closedDate.equals=" + DEFAULT_CLOSED_DATE);

        // Get all the batchList where closedDate equals to UPDATED_CLOSED_DATE
        defaultBatchShouldNotBeFound("closedDate.equals=" + UPDATED_CLOSED_DATE);
    }

    @Test
    @Transactional
    public void getAllBatchesByClosedDateIsInShouldWork() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get all the batchList where closedDate in DEFAULT_CLOSED_DATE or UPDATED_CLOSED_DATE
        defaultBatchShouldBeFound("closedDate.in=" + DEFAULT_CLOSED_DATE + "," + UPDATED_CLOSED_DATE);

        // Get all the batchList where closedDate equals to UPDATED_CLOSED_DATE
        defaultBatchShouldNotBeFound("closedDate.in=" + UPDATED_CLOSED_DATE);
    }

    @Test
    @Transactional
    public void getAllBatchesByClosedDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get all the batchList where closedDate is not null
        defaultBatchShouldBeFound("closedDate.specified=true");

        // Get all the batchList where closedDate is null
        defaultBatchShouldNotBeFound("closedDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllBatchesByClosedDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get all the batchList where closedDate greater than or equals to DEFAULT_CLOSED_DATE
        defaultBatchShouldBeFound("closedDate.greaterOrEqualThan=" + DEFAULT_CLOSED_DATE);

        // Get all the batchList where closedDate greater than or equals to UPDATED_CLOSED_DATE
        defaultBatchShouldNotBeFound("closedDate.greaterOrEqualThan=" + UPDATED_CLOSED_DATE);
    }

    @Test
    @Transactional
    public void getAllBatchesByClosedDateIsLessThanSomething() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get all the batchList where closedDate less than or equals to DEFAULT_CLOSED_DATE
        defaultBatchShouldNotBeFound("closedDate.lessThan=" + DEFAULT_CLOSED_DATE);

        // Get all the batchList where closedDate less than or equals to UPDATED_CLOSED_DATE
        defaultBatchShouldBeFound("closedDate.lessThan=" + UPDATED_CLOSED_DATE);
    }


    @Test
    @Transactional
    public void getAllBatchesByRoundIsEqualToSomething() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get all the batchList where round equals to DEFAULT_ROUND
        defaultBatchShouldBeFound("round.equals=" + DEFAULT_ROUND);

        // Get all the batchList where round equals to UPDATED_ROUND
        defaultBatchShouldNotBeFound("round.equals=" + UPDATED_ROUND);
    }

    @Test
    @Transactional
    public void getAllBatchesByRoundIsInShouldWork() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get all the batchList where round in DEFAULT_ROUND or UPDATED_ROUND
        defaultBatchShouldBeFound("round.in=" + DEFAULT_ROUND + "," + UPDATED_ROUND);

        // Get all the batchList where round equals to UPDATED_ROUND
        defaultBatchShouldNotBeFound("round.in=" + UPDATED_ROUND);
    }

    @Test
    @Transactional
    public void getAllBatchesByRoundIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get all the batchList where round is not null
        defaultBatchShouldBeFound("round.specified=true");

        // Get all the batchList where round is null
        defaultBatchShouldNotBeFound("round.specified=false");
    }

    @Test
    @Transactional
    public void getAllBatchesByRoundIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get all the batchList where round greater than or equals to DEFAULT_ROUND
        defaultBatchShouldBeFound("round.greaterOrEqualThan=" + DEFAULT_ROUND);

        // Get all the batchList where round greater than or equals to UPDATED_ROUND
        defaultBatchShouldNotBeFound("round.greaterOrEqualThan=" + UPDATED_ROUND);
    }

    @Test
    @Transactional
    public void getAllBatchesByRoundIsLessThanSomething() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get all the batchList where round less than or equals to DEFAULT_ROUND
        defaultBatchShouldNotBeFound("round.lessThan=" + DEFAULT_ROUND);

        // Get all the batchList where round less than or equals to UPDATED_ROUND
        defaultBatchShouldBeFound("round.lessThan=" + UPDATED_ROUND);
    }


    @Test
    @Transactional
    public void getAllBatchesByRemarksIsEqualToSomething() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get all the batchList where remarks equals to DEFAULT_REMARKS
        defaultBatchShouldBeFound("remarks.equals=" + DEFAULT_REMARKS);

        // Get all the batchList where remarks equals to UPDATED_REMARKS
        defaultBatchShouldNotBeFound("remarks.equals=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllBatchesByRemarksIsInShouldWork() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get all the batchList where remarks in DEFAULT_REMARKS or UPDATED_REMARKS
        defaultBatchShouldBeFound("remarks.in=" + DEFAULT_REMARKS + "," + UPDATED_REMARKS);

        // Get all the batchList where remarks equals to UPDATED_REMARKS
        defaultBatchShouldNotBeFound("remarks.in=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllBatchesByRemarksIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get all the batchList where remarks is not null
        defaultBatchShouldBeFound("remarks.specified=true");

        // Get all the batchList where remarks is null
        defaultBatchShouldNotBeFound("remarks.specified=false");
    }

    @Test
    @Transactional
    public void getAllBatchesByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get all the batchList where status equals to DEFAULT_STATUS
        defaultBatchShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the batchList where status equals to UPDATED_STATUS
        defaultBatchShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllBatchesByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get all the batchList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultBatchShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the batchList where status equals to UPDATED_STATUS
        defaultBatchShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllBatchesByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get all the batchList where status is not null
        defaultBatchShouldBeFound("status.specified=true");

        // Get all the batchList where status is null
        defaultBatchShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllBatchesByStatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get all the batchList where status greater than or equals to DEFAULT_STATUS
        defaultBatchShouldBeFound("status.greaterOrEqualThan=" + DEFAULT_STATUS);

        // Get all the batchList where status greater than or equals to UPDATED_STATUS
        defaultBatchShouldNotBeFound("status.greaterOrEqualThan=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllBatchesByStatusIsLessThanSomething() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get all the batchList where status less than or equals to DEFAULT_STATUS
        defaultBatchShouldNotBeFound("status.lessThan=" + DEFAULT_STATUS);

        // Get all the batchList where status less than or equals to UPDATED_STATUS
        defaultBatchShouldBeFound("status.lessThan=" + UPDATED_STATUS);
    }


    @Test
    @Transactional
    public void getAllBatchesByNoOfKgIsEqualToSomething() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get all the batchList where noOfKg equals to DEFAULT_NO_OF_KG
        defaultBatchShouldBeFound("noOfKg.equals=" + DEFAULT_NO_OF_KG);

        // Get all the batchList where noOfKg equals to UPDATED_NO_OF_KG
        defaultBatchShouldNotBeFound("noOfKg.equals=" + UPDATED_NO_OF_KG);
    }

    @Test
    @Transactional
    public void getAllBatchesByNoOfKgIsInShouldWork() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get all the batchList where noOfKg in DEFAULT_NO_OF_KG or UPDATED_NO_OF_KG
        defaultBatchShouldBeFound("noOfKg.in=" + DEFAULT_NO_OF_KG + "," + UPDATED_NO_OF_KG);

        // Get all the batchList where noOfKg equals to UPDATED_NO_OF_KG
        defaultBatchShouldNotBeFound("noOfKg.in=" + UPDATED_NO_OF_KG);
    }

    @Test
    @Transactional
    public void getAllBatchesByNoOfKgIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        // Get all the batchList where noOfKg is not null
        defaultBatchShouldBeFound("noOfKg.specified=true");

        // Get all the batchList where noOfKg is null
        defaultBatchShouldNotBeFound("noOfKg.specified=false");
    }

    @Test
    @Transactional
    public void getAllBatchesByDamagesIsEqualToSomething() throws Exception {
        // Initialize the database
        Damage damages = DamageResourceIntTest.createEntity(em);
        em.persist(damages);
        em.flush();
        batch.addDamages(damages);
        batchRepository.saveAndFlush(batch);
        Long damagesId = damages.getId();

        // Get all the batchList where damages equals to damagesId
        defaultBatchShouldBeFound("damagesId.equals=" + damagesId);

        // Get all the batchList where damages equals to damagesId + 1
        defaultBatchShouldNotBeFound("damagesId.equals=" + (damagesId + 1));
    }


    @Test
    @Transactional
    public void getAllBatchesByShadeAreasIsEqualToSomething() throws Exception {
        // Initialize the database
        ShadeArea shadeAreas = ShadeAreaResourceIntTest.createEntity(em);
        em.persist(shadeAreas);
        em.flush();
        batch.addShadeAreas(shadeAreas);
        batchRepository.saveAndFlush(batch);
        Long shadeAreasId = shadeAreas.getId();

        // Get all the batchList where shadeAreas equals to shadeAreasId
        defaultBatchShouldBeFound("shadeAreasId.equals=" + shadeAreasId);

        // Get all the batchList where shadeAreas equals to shadeAreasId + 1
        defaultBatchShouldNotBeFound("shadeAreasId.equals=" + (shadeAreasId + 1));
    }


    @Test
    @Transactional
    public void getAllBatchesByNurseryStockDetailsIsEqualToSomething() throws Exception {
        // Initialize the database
        NurseryStockDetails nurseryStockDetails = NurseryStockDetailsResourceIntTest.createEntity(em);
        em.persist(nurseryStockDetails);
        em.flush();
        batch.addNurseryStockDetails(nurseryStockDetails);
        batchRepository.saveAndFlush(batch);
        Long nurseryStockDetailsId = nurseryStockDetails.getId();

        // Get all the batchList where nurseryStockDetails equals to nurseryStockDetailsId
        defaultBatchShouldBeFound("nurseryStockDetailsId.equals=" + nurseryStockDetailsId);

        // Get all the batchList where nurseryStockDetails equals to nurseryStockDetailsId + 1
        defaultBatchShouldNotBeFound("nurseryStockDetailsId.equals=" + (nurseryStockDetailsId + 1));
    }


    @Test
    @Transactional
    public void getAllBatchesByNurseryIsEqualToSomething() throws Exception {
        // Initialize the database
        Nursery nursery = NurseryResourceIntTest.createEntity(em);
        em.persist(nursery);
        em.flush();
        batch.setNursery(nursery);
        batchRepository.saveAndFlush(batch);
        Long nurseryId = nursery.getId();

        // Get all the batchList where nursery equals to nurseryId
        defaultBatchShouldBeFound("nurseryId.equals=" + nurseryId);

        // Get all the batchList where nursery equals to nurseryId + 1
        defaultBatchShouldNotBeFound("nurseryId.equals=" + (nurseryId + 1));
    }


    @Test
    @Transactional
    public void getAllBatchesByPickListVarietyIsEqualToSomething() throws Exception {
        // Initialize the database
        PickListValue pickListVariety = PickListValueResourceIntTest.createEntity(em);
        em.persist(pickListVariety);
        em.flush();
        batch.setPickListVariety(pickListVariety);
        batchRepository.saveAndFlush(batch);
        Long pickListVarietyId = pickListVariety.getId();

        // Get all the batchList where pickListVariety equals to pickListVarietyId
        defaultBatchShouldBeFound("pickListVarietyId.equals=" + pickListVarietyId);

        // Get all the batchList where pickListVariety equals to pickListVarietyId + 1
        defaultBatchShouldNotBeFound("pickListVarietyId.equals=" + (pickListVarietyId + 1));
    }


    @Test
    @Transactional
    public void getAllBatchesByPickListCategoryIsEqualToSomething() throws Exception {
        // Initialize the database
        PickListValue pickListCategory = PickListValueResourceIntTest.createEntity(em);
        em.persist(pickListCategory);
        em.flush();
        batch.setPickListCategory(pickListCategory);
        batchRepository.saveAndFlush(batch);
        Long pickListCategoryId = pickListCategory.getId();

        // Get all the batchList where pickListCategory equals to pickListCategoryId
        defaultBatchShouldBeFound("pickListCategoryId.equals=" + pickListCategoryId);

        // Get all the batchList where pickListCategory equals to pickListCategoryId + 1
        defaultBatchShouldNotBeFound("pickListCategoryId.equals=" + (pickListCategoryId + 1));
    }


    @Test
    @Transactional
    public void getAllBatchesByQuantityTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        PickListValue quantityType = PickListValueResourceIntTest.createEntity(em);
        em.persist(quantityType);
        em.flush();
        batch.setQuantityType(quantityType);
        batchRepository.saveAndFlush(batch);
        Long quantityTypeId = quantityType.getId();

        // Get all the batchList where quantityType equals to quantityTypeId
        defaultBatchShouldBeFound("quantityTypeId.equals=" + quantityTypeId);

        // Get all the batchList where quantityType equals to quantityTypeId + 1
        defaultBatchShouldNotBeFound("quantityTypeId.equals=" + (quantityTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllBatchesByMotherBedIsEqualToSomething() throws Exception {
        // Initialize the database
        MotherBed motherBed = MotherBedResourceIntTest.createEntity(em);
        em.persist(motherBed);
        em.flush();
        batch.setMotherBed(motherBed);
        batchRepository.saveAndFlush(batch);
        Long motherBedId = motherBed.getId();

        // Get all the batchList where motherBed equals to motherBedId
        defaultBatchShouldBeFound("motherBedId.equals=" + motherBedId);

        // Get all the batchList where motherBed equals to motherBedId + 1
        defaultBatchShouldNotBeFound("motherBedId.equals=" + (motherBedId + 1));
    }


    @Test
    @Transactional
    public void getAllBatchesByFinancialYearBatchIsEqualToSomething() throws Exception {
        // Initialize the database
        FinancialYearSettings financialYearBatch = FinancialYearSettingsResourceIntTest.createEntity(em);
        em.persist(financialYearBatch);
        em.flush();
        batch.setFinancialYearBatch(financialYearBatch);
        batchRepository.saveAndFlush(batch);
        Long financialYearBatchId = financialYearBatch.getId();

        // Get all the batchList where financialYearBatch equals to financialYearBatchId
        defaultBatchShouldBeFound("financialYearBatchId.equals=" + financialYearBatchId);

        // Get all the batchList where financialYearBatch equals to financialYearBatchId + 1
        defaultBatchShouldNotBeFound("financialYearBatchId.equals=" + (financialYearBatchId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultBatchShouldBeFound(String filter) throws Exception {
        restBatchMockMvc.perform(get("/api/batches?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(batch.getId().intValue())))
            .andExpect(jsonPath("$.[*].batchNo").value(hasItem(DEFAULT_BATCH_NO.toString())))
            .andExpect(jsonPath("$.[*].batchName").value(hasItem(DEFAULT_BATCH_NAME.toString())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].showingType").value(hasItem(DEFAULT_SHOWING_TYPE)))
            .andExpect(jsonPath("$.[*].sowingDate").value(hasItem(DEFAULT_SOWING_DATE.toString())))
            .andExpect(jsonPath("$.[*].closedDate").value(hasItem(DEFAULT_CLOSED_DATE.toString())))
            .andExpect(jsonPath("$.[*].round").value(hasItem(DEFAULT_ROUND)))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].noOfKg").value(hasItem(DEFAULT_NO_OF_KG.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultBatchShouldNotBeFound(String filter) throws Exception {
        restBatchMockMvc.perform(get("/api/batches?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingBatch() throws Exception {
        // Get the batch
        restBatchMockMvc.perform(get("/api/batches/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBatch() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        int databaseSizeBeforeUpdate = batchRepository.findAll().size();

        // Update the batch
        Batch updatedBatch = batchRepository.findById(batch.getId()).get();
        // Disconnect from session so that the updates on updatedBatch are not directly saved in db
        em.detach(updatedBatch);
        updatedBatch
            .batchNo(UPDATED_BATCH_NO)
            .batchName(UPDATED_BATCH_NAME)
            .quantity(UPDATED_QUANTITY)
            .showingType(UPDATED_SHOWING_TYPE)
            .sowingDate(UPDATED_SOWING_DATE)
            .closedDate(UPDATED_CLOSED_DATE)
            .round(UPDATED_ROUND)
            .remarks(UPDATED_REMARKS)
            .status(UPDATED_STATUS)
            .noOfKg(UPDATED_NO_OF_KG);
        BatchDTO batchDTO = batchMapper.toDto(updatedBatch);

        restBatchMockMvc.perform(put("/api/batches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(batchDTO)))
            .andExpect(status().isOk());

        // Validate the Batch in the database
        List<Batch> batchList = batchRepository.findAll();
        assertThat(batchList).hasSize(databaseSizeBeforeUpdate);
        Batch testBatch = batchList.get(batchList.size() - 1);
        assertThat(testBatch.getBatchNo()).isEqualTo(UPDATED_BATCH_NO);
        assertThat(testBatch.getBatchName()).isEqualTo(UPDATED_BATCH_NAME);
        assertThat(testBatch.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testBatch.getShowingType()).isEqualTo(UPDATED_SHOWING_TYPE);
        assertThat(testBatch.getSowingDate()).isEqualTo(UPDATED_SOWING_DATE);
        assertThat(testBatch.getClosedDate()).isEqualTo(UPDATED_CLOSED_DATE);
        assertThat(testBatch.getRound()).isEqualTo(UPDATED_ROUND);
        assertThat(testBatch.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testBatch.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testBatch.getNoOfKg()).isEqualTo(UPDATED_NO_OF_KG);
    }

    @Test
    @Transactional
    public void updateNonExistingBatch() throws Exception {
        int databaseSizeBeforeUpdate = batchRepository.findAll().size();

        // Create the Batch
        BatchDTO batchDTO = batchMapper.toDto(batch);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restBatchMockMvc.perform(put("/api/batches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(batchDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Batch in the database
        List<Batch> batchList = batchRepository.findAll();
        assertThat(batchList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBatch() throws Exception {
        // Initialize the database
        batchRepository.saveAndFlush(batch);

        int databaseSizeBeforeDelete = batchRepository.findAll().size();

        // Get the batch
        restBatchMockMvc.perform(delete("/api/batches/{id}", batch.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Batch> batchList = batchRepository.findAll();
        assertThat(batchList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Batch.class);
        Batch batch1 = new Batch();
        batch1.setId(1L);
        Batch batch2 = new Batch();
        batch2.setId(batch1.getId());
        assertThat(batch1).isEqualTo(batch2);
        batch2.setId(2L);
        assertThat(batch1).isNotEqualTo(batch2);
        batch1.setId(null);
        assertThat(batch1).isNotEqualTo(batch2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BatchDTO.class);
        BatchDTO batchDTO1 = new BatchDTO();
        batchDTO1.setId(1L);
        BatchDTO batchDTO2 = new BatchDTO();
        assertThat(batchDTO1).isNotEqualTo(batchDTO2);
        batchDTO2.setId(batchDTO1.getId());
        assertThat(batchDTO1).isEqualTo(batchDTO2);
        batchDTO2.setId(2L);
        assertThat(batchDTO1).isNotEqualTo(batchDTO2);
        batchDTO1.setId(null);
        assertThat(batchDTO1).isNotEqualTo(batchDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(batchMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(batchMapper.fromId(null)).isNull();
    }
}
