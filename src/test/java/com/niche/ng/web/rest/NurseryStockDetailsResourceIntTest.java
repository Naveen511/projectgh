package com.niche.ng.web.rest;

import com.niche.ng.ProjectghApp;

import com.niche.ng.domain.NurseryStockDetails;
import com.niche.ng.domain.Batch;
import com.niche.ng.domain.NurseryStock;
import com.niche.ng.domain.Nursery;
import com.niche.ng.domain.PickListValue;
import com.niche.ng.domain.FinancialYearSettings;
import com.niche.ng.repository.NurseryStockDetailsRepository;
import com.niche.ng.service.NurseryStockDetailsService;
import com.niche.ng.service.dto.NurseryStockDetailsDTO;
import com.niche.ng.service.mapper.NurseryStockDetailsMapper;
import com.niche.ng.web.rest.errors.ExceptionTranslator;
import com.niche.ng.service.dto.NurseryStockDetailsCriteria;
import com.niche.ng.service.NurseryStockDetailsQueryService;

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
 * Test class for the NurseryStockDetailsResource REST controller.
 *
 * @see NurseryStockDetailsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectghApp.class)
public class NurseryStockDetailsResourceIntTest {

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_QUANTITY = 1L;
    private static final Long UPDATED_QUANTITY = 2L;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    @Autowired
    private NurseryStockDetailsRepository nurseryStockDetailsRepository;


    @Autowired
    private NurseryStockDetailsMapper nurseryStockDetailsMapper;
    

    @Autowired
    private NurseryStockDetailsService nurseryStockDetailsService;

    @Autowired
    private NurseryStockDetailsQueryService nurseryStockDetailsQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restNurseryStockDetailsMockMvc;

    private NurseryStockDetails nurseryStockDetails;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NurseryStockDetailsResource nurseryStockDetailsResource = new NurseryStockDetailsResource(nurseryStockDetailsService, nurseryStockDetailsQueryService);
        this.restNurseryStockDetailsMockMvc = MockMvcBuilders.standaloneSetup(nurseryStockDetailsResource)
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
    public static NurseryStockDetails createEntity(EntityManager em) {
        NurseryStockDetails nurseryStockDetails = new NurseryStockDetails()
            .date(DEFAULT_DATE)
            .quantity(DEFAULT_QUANTITY)
            .description(DEFAULT_DESCRIPTION)
            .status(DEFAULT_STATUS);
        return nurseryStockDetails;
    }

    @Before
    public void initTest() {
        nurseryStockDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createNurseryStockDetails() throws Exception {
        int databaseSizeBeforeCreate = nurseryStockDetailsRepository.findAll().size();

        // Create the NurseryStockDetails
        NurseryStockDetailsDTO nurseryStockDetailsDTO = nurseryStockDetailsMapper.toDto(nurseryStockDetails);
        restNurseryStockDetailsMockMvc.perform(post("/api/nursery-stock-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryStockDetailsDTO)))
            .andExpect(status().isCreated());

        // Validate the NurseryStockDetails in the database
        List<NurseryStockDetails> nurseryStockDetailsList = nurseryStockDetailsRepository.findAll();
        assertThat(nurseryStockDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        NurseryStockDetails testNurseryStockDetails = nurseryStockDetailsList.get(nurseryStockDetailsList.size() - 1);
        assertThat(testNurseryStockDetails.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testNurseryStockDetails.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testNurseryStockDetails.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testNurseryStockDetails.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createNurseryStockDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nurseryStockDetailsRepository.findAll().size();

        // Create the NurseryStockDetails with an existing ID
        nurseryStockDetails.setId(1L);
        NurseryStockDetailsDTO nurseryStockDetailsDTO = nurseryStockDetailsMapper.toDto(nurseryStockDetails);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNurseryStockDetailsMockMvc.perform(post("/api/nursery-stock-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryStockDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NurseryStockDetails in the database
        List<NurseryStockDetails> nurseryStockDetailsList = nurseryStockDetailsRepository.findAll();
        assertThat(nurseryStockDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = nurseryStockDetailsRepository.findAll().size();
        // set the field null
        nurseryStockDetails.setDate(null);

        // Create the NurseryStockDetails, which fails.
        NurseryStockDetailsDTO nurseryStockDetailsDTO = nurseryStockDetailsMapper.toDto(nurseryStockDetails);

        restNurseryStockDetailsMockMvc.perform(post("/api/nursery-stock-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryStockDetailsDTO)))
            .andExpect(status().isBadRequest());

        List<NurseryStockDetails> nurseryStockDetailsList = nurseryStockDetailsRepository.findAll();
        assertThat(nurseryStockDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQuantityIsRequired() throws Exception {
        int databaseSizeBeforeTest = nurseryStockDetailsRepository.findAll().size();
        // set the field null
        nurseryStockDetails.setQuantity(null);

        // Create the NurseryStockDetails, which fails.
        NurseryStockDetailsDTO nurseryStockDetailsDTO = nurseryStockDetailsMapper.toDto(nurseryStockDetails);

        restNurseryStockDetailsMockMvc.perform(post("/api/nursery-stock-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryStockDetailsDTO)))
            .andExpect(status().isBadRequest());

        List<NurseryStockDetails> nurseryStockDetailsList = nurseryStockDetailsRepository.findAll();
        assertThat(nurseryStockDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNurseryStockDetails() throws Exception {
        // Initialize the database
        nurseryStockDetailsRepository.saveAndFlush(nurseryStockDetails);

        // Get all the nurseryStockDetailsList
        restNurseryStockDetailsMockMvc.perform(get("/api/nursery-stock-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nurseryStockDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    

    @Test
    @Transactional
    public void getNurseryStockDetails() throws Exception {
        // Initialize the database
        nurseryStockDetailsRepository.saveAndFlush(nurseryStockDetails);

        // Get the nurseryStockDetails
        restNurseryStockDetailsMockMvc.perform(get("/api/nursery-stock-details/{id}", nurseryStockDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(nurseryStockDetails.getId().intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY.intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getAllNurseryStockDetailsByDateIsEqualToSomething() throws Exception {
        // Initialize the database
        nurseryStockDetailsRepository.saveAndFlush(nurseryStockDetails);

        // Get all the nurseryStockDetailsList where date equals to DEFAULT_DATE
        defaultNurseryStockDetailsShouldBeFound("date.equals=" + DEFAULT_DATE);

        // Get all the nurseryStockDetailsList where date equals to UPDATED_DATE
        defaultNurseryStockDetailsShouldNotBeFound("date.equals=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    public void getAllNurseryStockDetailsByDateIsInShouldWork() throws Exception {
        // Initialize the database
        nurseryStockDetailsRepository.saveAndFlush(nurseryStockDetails);

        // Get all the nurseryStockDetailsList where date in DEFAULT_DATE or UPDATED_DATE
        defaultNurseryStockDetailsShouldBeFound("date.in=" + DEFAULT_DATE + "," + UPDATED_DATE);

        // Get all the nurseryStockDetailsList where date equals to UPDATED_DATE
        defaultNurseryStockDetailsShouldNotBeFound("date.in=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    public void getAllNurseryStockDetailsByDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        nurseryStockDetailsRepository.saveAndFlush(nurseryStockDetails);

        // Get all the nurseryStockDetailsList where date is not null
        defaultNurseryStockDetailsShouldBeFound("date.specified=true");

        // Get all the nurseryStockDetailsList where date is null
        defaultNurseryStockDetailsShouldNotBeFound("date.specified=false");
    }

    @Test
    @Transactional
    public void getAllNurseryStockDetailsByDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        nurseryStockDetailsRepository.saveAndFlush(nurseryStockDetails);

        // Get all the nurseryStockDetailsList where date greater than or equals to DEFAULT_DATE
        defaultNurseryStockDetailsShouldBeFound("date.greaterOrEqualThan=" + DEFAULT_DATE);

        // Get all the nurseryStockDetailsList where date greater than or equals to UPDATED_DATE
        defaultNurseryStockDetailsShouldNotBeFound("date.greaterOrEqualThan=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    public void getAllNurseryStockDetailsByDateIsLessThanSomething() throws Exception {
        // Initialize the database
        nurseryStockDetailsRepository.saveAndFlush(nurseryStockDetails);

        // Get all the nurseryStockDetailsList where date less than or equals to DEFAULT_DATE
        defaultNurseryStockDetailsShouldNotBeFound("date.lessThan=" + DEFAULT_DATE);

        // Get all the nurseryStockDetailsList where date less than or equals to UPDATED_DATE
        defaultNurseryStockDetailsShouldBeFound("date.lessThan=" + UPDATED_DATE);
    }


    @Test
    @Transactional
    public void getAllNurseryStockDetailsByQuantityIsEqualToSomething() throws Exception {
        // Initialize the database
        nurseryStockDetailsRepository.saveAndFlush(nurseryStockDetails);

        // Get all the nurseryStockDetailsList where quantity equals to DEFAULT_QUANTITY
        defaultNurseryStockDetailsShouldBeFound("quantity.equals=" + DEFAULT_QUANTITY);

        // Get all the nurseryStockDetailsList where quantity equals to UPDATED_QUANTITY
        defaultNurseryStockDetailsShouldNotBeFound("quantity.equals=" + UPDATED_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllNurseryStockDetailsByQuantityIsInShouldWork() throws Exception {
        // Initialize the database
        nurseryStockDetailsRepository.saveAndFlush(nurseryStockDetails);

        // Get all the nurseryStockDetailsList where quantity in DEFAULT_QUANTITY or UPDATED_QUANTITY
        defaultNurseryStockDetailsShouldBeFound("quantity.in=" + DEFAULT_QUANTITY + "," + UPDATED_QUANTITY);

        // Get all the nurseryStockDetailsList where quantity equals to UPDATED_QUANTITY
        defaultNurseryStockDetailsShouldNotBeFound("quantity.in=" + UPDATED_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllNurseryStockDetailsByQuantityIsNullOrNotNull() throws Exception {
        // Initialize the database
        nurseryStockDetailsRepository.saveAndFlush(nurseryStockDetails);

        // Get all the nurseryStockDetailsList where quantity is not null
        defaultNurseryStockDetailsShouldBeFound("quantity.specified=true");

        // Get all the nurseryStockDetailsList where quantity is null
        defaultNurseryStockDetailsShouldNotBeFound("quantity.specified=false");
    }

    @Test
    @Transactional
    public void getAllNurseryStockDetailsByQuantityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        nurseryStockDetailsRepository.saveAndFlush(nurseryStockDetails);

        // Get all the nurseryStockDetailsList where quantity greater than or equals to DEFAULT_QUANTITY
        defaultNurseryStockDetailsShouldBeFound("quantity.greaterOrEqualThan=" + DEFAULT_QUANTITY);

        // Get all the nurseryStockDetailsList where quantity greater than or equals to UPDATED_QUANTITY
        defaultNurseryStockDetailsShouldNotBeFound("quantity.greaterOrEqualThan=" + UPDATED_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllNurseryStockDetailsByQuantityIsLessThanSomething() throws Exception {
        // Initialize the database
        nurseryStockDetailsRepository.saveAndFlush(nurseryStockDetails);

        // Get all the nurseryStockDetailsList where quantity less than or equals to DEFAULT_QUANTITY
        defaultNurseryStockDetailsShouldNotBeFound("quantity.lessThan=" + DEFAULT_QUANTITY);

        // Get all the nurseryStockDetailsList where quantity less than or equals to UPDATED_QUANTITY
        defaultNurseryStockDetailsShouldBeFound("quantity.lessThan=" + UPDATED_QUANTITY);
    }


    @Test
    @Transactional
    public void getAllNurseryStockDetailsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        nurseryStockDetailsRepository.saveAndFlush(nurseryStockDetails);

        // Get all the nurseryStockDetailsList where description equals to DEFAULT_DESCRIPTION
        defaultNurseryStockDetailsShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the nurseryStockDetailsList where description equals to UPDATED_DESCRIPTION
        defaultNurseryStockDetailsShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllNurseryStockDetailsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        nurseryStockDetailsRepository.saveAndFlush(nurseryStockDetails);

        // Get all the nurseryStockDetailsList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultNurseryStockDetailsShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the nurseryStockDetailsList where description equals to UPDATED_DESCRIPTION
        defaultNurseryStockDetailsShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllNurseryStockDetailsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        nurseryStockDetailsRepository.saveAndFlush(nurseryStockDetails);

        // Get all the nurseryStockDetailsList where description is not null
        defaultNurseryStockDetailsShouldBeFound("description.specified=true");

        // Get all the nurseryStockDetailsList where description is null
        defaultNurseryStockDetailsShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllNurseryStockDetailsByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        nurseryStockDetailsRepository.saveAndFlush(nurseryStockDetails);

        // Get all the nurseryStockDetailsList where status equals to DEFAULT_STATUS
        defaultNurseryStockDetailsShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the nurseryStockDetailsList where status equals to UPDATED_STATUS
        defaultNurseryStockDetailsShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllNurseryStockDetailsByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        nurseryStockDetailsRepository.saveAndFlush(nurseryStockDetails);

        // Get all the nurseryStockDetailsList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultNurseryStockDetailsShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the nurseryStockDetailsList where status equals to UPDATED_STATUS
        defaultNurseryStockDetailsShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllNurseryStockDetailsByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        nurseryStockDetailsRepository.saveAndFlush(nurseryStockDetails);

        // Get all the nurseryStockDetailsList where status is not null
        defaultNurseryStockDetailsShouldBeFound("status.specified=true");

        // Get all the nurseryStockDetailsList where status is null
        defaultNurseryStockDetailsShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllNurseryStockDetailsByStatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        nurseryStockDetailsRepository.saveAndFlush(nurseryStockDetails);

        // Get all the nurseryStockDetailsList where status greater than or equals to DEFAULT_STATUS
        defaultNurseryStockDetailsShouldBeFound("status.greaterOrEqualThan=" + DEFAULT_STATUS);

        // Get all the nurseryStockDetailsList where status greater than or equals to UPDATED_STATUS
        defaultNurseryStockDetailsShouldNotBeFound("status.greaterOrEqualThan=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllNurseryStockDetailsByStatusIsLessThanSomething() throws Exception {
        // Initialize the database
        nurseryStockDetailsRepository.saveAndFlush(nurseryStockDetails);

        // Get all the nurseryStockDetailsList where status less than or equals to DEFAULT_STATUS
        defaultNurseryStockDetailsShouldNotBeFound("status.lessThan=" + DEFAULT_STATUS);

        // Get all the nurseryStockDetailsList where status less than or equals to UPDATED_STATUS
        defaultNurseryStockDetailsShouldBeFound("status.lessThan=" + UPDATED_STATUS);
    }


    @Test
    @Transactional
    public void getAllNurseryStockDetailsByBatchIsEqualToSomething() throws Exception {
        // Initialize the database
        Batch batch = BatchResourceIntTest.createEntity(em);
        em.persist(batch);
        em.flush();
        nurseryStockDetails.setBatch(batch);
        nurseryStockDetailsRepository.saveAndFlush(nurseryStockDetails);
        Long batchId = batch.getId();

        // Get all the nurseryStockDetailsList where batch equals to batchId
        defaultNurseryStockDetailsShouldBeFound("batchId.equals=" + batchId);

        // Get all the nurseryStockDetailsList where batch equals to batchId + 1
        defaultNurseryStockDetailsShouldNotBeFound("batchId.equals=" + (batchId + 1));
    }


    @Test
    @Transactional
    public void getAllNurseryStockDetailsByNurseryStockIsEqualToSomething() throws Exception {
        // Initialize the database
        NurseryStock nurseryStock = NurseryStockResourceIntTest.createEntity(em);
        em.persist(nurseryStock);
        em.flush();
        nurseryStockDetails.setNurseryStock(nurseryStock);
        nurseryStockDetailsRepository.saveAndFlush(nurseryStockDetails);
        Long nurseryStockId = nurseryStock.getId();

        // Get all the nurseryStockDetailsList where nurseryStock equals to nurseryStockId
        defaultNurseryStockDetailsShouldBeFound("nurseryStockId.equals=" + nurseryStockId);

        // Get all the nurseryStockDetailsList where nurseryStock equals to nurseryStockId + 1
        defaultNurseryStockDetailsShouldNotBeFound("nurseryStockId.equals=" + (nurseryStockId + 1));
    }


    @Test
    @Transactional
    public void getAllNurseryStockDetailsByItNurseryIsEqualToSomething() throws Exception {
        // Initialize the database
        Nursery itNursery = NurseryResourceIntTest.createEntity(em);
        em.persist(itNursery);
        em.flush();
        nurseryStockDetails.setItNursery(itNursery);
        nurseryStockDetailsRepository.saveAndFlush(nurseryStockDetails);
        Long itNurseryId = itNursery.getId();

        // Get all the nurseryStockDetailsList where itNursery equals to itNurseryId
        defaultNurseryStockDetailsShouldBeFound("itNurseryId.equals=" + itNurseryId);

        // Get all the nurseryStockDetailsList where itNursery equals to itNurseryId + 1
        defaultNurseryStockDetailsShouldNotBeFound("itNurseryId.equals=" + (itNurseryId + 1));
    }


    @Test
    @Transactional
    public void getAllNurseryStockDetailsBySaplingDamageAreaIsEqualToSomething() throws Exception {
        // Initialize the database
        PickListValue saplingDamageArea = PickListValueResourceIntTest.createEntity(em);
        em.persist(saplingDamageArea);
        em.flush();
        nurseryStockDetails.setSaplingDamageArea(saplingDamageArea);
        nurseryStockDetailsRepository.saveAndFlush(nurseryStockDetails);
        Long saplingDamageAreaId = saplingDamageArea.getId();

        // Get all the nurseryStockDetailsList where saplingDamageArea equals to saplingDamageAreaId
        defaultNurseryStockDetailsShouldBeFound("saplingDamageAreaId.equals=" + saplingDamageAreaId);

        // Get all the nurseryStockDetailsList where saplingDamageArea equals to saplingDamageAreaId + 1
        defaultNurseryStockDetailsShouldNotBeFound("saplingDamageAreaId.equals=" + (saplingDamageAreaId + 1));
    }


    @Test
    @Transactional
    public void getAllNurseryStockDetailsByFinancialYearStockDetailsIsEqualToSomething() throws Exception {
        // Initialize the database
        FinancialYearSettings financialYearStockDetails = FinancialYearSettingsResourceIntTest.createEntity(em);
        em.persist(financialYearStockDetails);
        em.flush();
        nurseryStockDetails.setFinancialYearStockDetails(financialYearStockDetails);
        nurseryStockDetailsRepository.saveAndFlush(nurseryStockDetails);
        Long financialYearStockDetailsId = financialYearStockDetails.getId();

        // Get all the nurseryStockDetailsList where financialYearStockDetails equals to financialYearStockDetailsId
        defaultNurseryStockDetailsShouldBeFound("financialYearStockDetailsId.equals=" + financialYearStockDetailsId);

        // Get all the nurseryStockDetailsList where financialYearStockDetails equals to financialYearStockDetailsId + 1
        defaultNurseryStockDetailsShouldNotBeFound("financialYearStockDetailsId.equals=" + (financialYearStockDetailsId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultNurseryStockDetailsShouldBeFound(String filter) throws Exception {
        restNurseryStockDetailsMockMvc.perform(get("/api/nursery-stock-details?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nurseryStockDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultNurseryStockDetailsShouldNotBeFound(String filter) throws Exception {
        restNurseryStockDetailsMockMvc.perform(get("/api/nursery-stock-details?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingNurseryStockDetails() throws Exception {
        // Get the nurseryStockDetails
        restNurseryStockDetailsMockMvc.perform(get("/api/nursery-stock-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNurseryStockDetails() throws Exception {
        // Initialize the database
        nurseryStockDetailsRepository.saveAndFlush(nurseryStockDetails);

        int databaseSizeBeforeUpdate = nurseryStockDetailsRepository.findAll().size();

        // Update the nurseryStockDetails
        NurseryStockDetails updatedNurseryStockDetails = nurseryStockDetailsRepository.findById(nurseryStockDetails.getId()).get();
        // Disconnect from session so that the updates on updatedNurseryStockDetails are not directly saved in db
        em.detach(updatedNurseryStockDetails);
        updatedNurseryStockDetails
            .date(UPDATED_DATE)
            .quantity(UPDATED_QUANTITY)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS);
        NurseryStockDetailsDTO nurseryStockDetailsDTO = nurseryStockDetailsMapper.toDto(updatedNurseryStockDetails);

        restNurseryStockDetailsMockMvc.perform(put("/api/nursery-stock-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryStockDetailsDTO)))
            .andExpect(status().isOk());

        // Validate the NurseryStockDetails in the database
        List<NurseryStockDetails> nurseryStockDetailsList = nurseryStockDetailsRepository.findAll();
        assertThat(nurseryStockDetailsList).hasSize(databaseSizeBeforeUpdate);
        NurseryStockDetails testNurseryStockDetails = nurseryStockDetailsList.get(nurseryStockDetailsList.size() - 1);
        assertThat(testNurseryStockDetails.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testNurseryStockDetails.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testNurseryStockDetails.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testNurseryStockDetails.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingNurseryStockDetails() throws Exception {
        int databaseSizeBeforeUpdate = nurseryStockDetailsRepository.findAll().size();

        // Create the NurseryStockDetails
        NurseryStockDetailsDTO nurseryStockDetailsDTO = nurseryStockDetailsMapper.toDto(nurseryStockDetails);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restNurseryStockDetailsMockMvc.perform(put("/api/nursery-stock-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryStockDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NurseryStockDetails in the database
        List<NurseryStockDetails> nurseryStockDetailsList = nurseryStockDetailsRepository.findAll();
        assertThat(nurseryStockDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNurseryStockDetails() throws Exception {
        // Initialize the database
        nurseryStockDetailsRepository.saveAndFlush(nurseryStockDetails);

        int databaseSizeBeforeDelete = nurseryStockDetailsRepository.findAll().size();

        // Get the nurseryStockDetails
        restNurseryStockDetailsMockMvc.perform(delete("/api/nursery-stock-details/{id}", nurseryStockDetails.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<NurseryStockDetails> nurseryStockDetailsList = nurseryStockDetailsRepository.findAll();
        assertThat(nurseryStockDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NurseryStockDetails.class);
        NurseryStockDetails nurseryStockDetails1 = new NurseryStockDetails();
        nurseryStockDetails1.setId(1L);
        NurseryStockDetails nurseryStockDetails2 = new NurseryStockDetails();
        nurseryStockDetails2.setId(nurseryStockDetails1.getId());
        assertThat(nurseryStockDetails1).isEqualTo(nurseryStockDetails2);
        nurseryStockDetails2.setId(2L);
        assertThat(nurseryStockDetails1).isNotEqualTo(nurseryStockDetails2);
        nurseryStockDetails1.setId(null);
        assertThat(nurseryStockDetails1).isNotEqualTo(nurseryStockDetails2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NurseryStockDetailsDTO.class);
        NurseryStockDetailsDTO nurseryStockDetailsDTO1 = new NurseryStockDetailsDTO();
        nurseryStockDetailsDTO1.setId(1L);
        NurseryStockDetailsDTO nurseryStockDetailsDTO2 = new NurseryStockDetailsDTO();
        assertThat(nurseryStockDetailsDTO1).isNotEqualTo(nurseryStockDetailsDTO2);
        nurseryStockDetailsDTO2.setId(nurseryStockDetailsDTO1.getId());
        assertThat(nurseryStockDetailsDTO1).isEqualTo(nurseryStockDetailsDTO2);
        nurseryStockDetailsDTO2.setId(2L);
        assertThat(nurseryStockDetailsDTO1).isNotEqualTo(nurseryStockDetailsDTO2);
        nurseryStockDetailsDTO1.setId(null);
        assertThat(nurseryStockDetailsDTO1).isNotEqualTo(nurseryStockDetailsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(nurseryStockDetailsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(nurseryStockDetailsMapper.fromId(null)).isNull();
    }
}
