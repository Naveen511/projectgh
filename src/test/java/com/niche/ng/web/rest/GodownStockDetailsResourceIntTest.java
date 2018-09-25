package com.niche.ng.web.rest;

import com.niche.ng.ProjectghApp;

import com.niche.ng.domain.GodownStockDetails;
import com.niche.ng.domain.GodownStock;
import com.niche.ng.domain.FinancialYearSettings;
import com.niche.ng.repository.GodownStockDetailsRepository;
import com.niche.ng.service.GodownStockDetailsService;
import com.niche.ng.service.dto.GodownStockDetailsDTO;
import com.niche.ng.service.mapper.GodownStockDetailsMapper;
import com.niche.ng.web.rest.errors.ExceptionTranslator;
import com.niche.ng.service.dto.GodownStockDetailsCriteria;
import com.niche.ng.service.GodownStockDetailsQueryService;

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
 * Test class for the GodownStockDetailsResource REST controller.
 *
 * @see GodownStockDetailsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectghApp.class)
public class GodownStockDetailsResourceIntTest {

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_QUANTITY = 1L;
    private static final Long UPDATED_QUANTITY = 2L;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final Float DEFAULT_PRICE = 1F;
    private static final Float UPDATED_PRICE = 2F;

    @Autowired
    private GodownStockDetailsRepository godownStockDetailsRepository;


    @Autowired
    private GodownStockDetailsMapper godownStockDetailsMapper;
    

    @Autowired
    private GodownStockDetailsService godownStockDetailsService;

    @Autowired
    private GodownStockDetailsQueryService godownStockDetailsQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restGodownStockDetailsMockMvc;

    private GodownStockDetails godownStockDetails;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GodownStockDetailsResource godownStockDetailsResource = new GodownStockDetailsResource(godownStockDetailsService, godownStockDetailsQueryService);
        this.restGodownStockDetailsMockMvc = MockMvcBuilders.standaloneSetup(godownStockDetailsResource)
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
    public static GodownStockDetails createEntity(EntityManager em) {
        GodownStockDetails godownStockDetails = new GodownStockDetails()
            .date(DEFAULT_DATE)
            .quantity(DEFAULT_QUANTITY)
            .description(DEFAULT_DESCRIPTION)
            .status(DEFAULT_STATUS)
            .price(DEFAULT_PRICE);
        return godownStockDetails;
    }

    @Before
    public void initTest() {
        godownStockDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createGodownStockDetails() throws Exception {
        int databaseSizeBeforeCreate = godownStockDetailsRepository.findAll().size();

        // Create the GodownStockDetails
        GodownStockDetailsDTO godownStockDetailsDTO = godownStockDetailsMapper.toDto(godownStockDetails);
        restGodownStockDetailsMockMvc.perform(post("/api/godown-stock-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(godownStockDetailsDTO)))
            .andExpect(status().isCreated());

        // Validate the GodownStockDetails in the database
        List<GodownStockDetails> godownStockDetailsList = godownStockDetailsRepository.findAll();
        assertThat(godownStockDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        GodownStockDetails testGodownStockDetails = godownStockDetailsList.get(godownStockDetailsList.size() - 1);
        assertThat(testGodownStockDetails.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testGodownStockDetails.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testGodownStockDetails.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testGodownStockDetails.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testGodownStockDetails.getPrice()).isEqualTo(DEFAULT_PRICE);
    }

    @Test
    @Transactional
    public void createGodownStockDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = godownStockDetailsRepository.findAll().size();

        // Create the GodownStockDetails with an existing ID
        godownStockDetails.setId(1L);
        GodownStockDetailsDTO godownStockDetailsDTO = godownStockDetailsMapper.toDto(godownStockDetails);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGodownStockDetailsMockMvc.perform(post("/api/godown-stock-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(godownStockDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GodownStockDetails in the database
        List<GodownStockDetails> godownStockDetailsList = godownStockDetailsRepository.findAll();
        assertThat(godownStockDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = godownStockDetailsRepository.findAll().size();
        // set the field null
        godownStockDetails.setDate(null);

        // Create the GodownStockDetails, which fails.
        GodownStockDetailsDTO godownStockDetailsDTO = godownStockDetailsMapper.toDto(godownStockDetails);

        restGodownStockDetailsMockMvc.perform(post("/api/godown-stock-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(godownStockDetailsDTO)))
            .andExpect(status().isBadRequest());

        List<GodownStockDetails> godownStockDetailsList = godownStockDetailsRepository.findAll();
        assertThat(godownStockDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQuantityIsRequired() throws Exception {
        int databaseSizeBeforeTest = godownStockDetailsRepository.findAll().size();
        // set the field null
        godownStockDetails.setQuantity(null);

        // Create the GodownStockDetails, which fails.
        GodownStockDetailsDTO godownStockDetailsDTO = godownStockDetailsMapper.toDto(godownStockDetails);

        restGodownStockDetailsMockMvc.perform(post("/api/godown-stock-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(godownStockDetailsDTO)))
            .andExpect(status().isBadRequest());

        List<GodownStockDetails> godownStockDetailsList = godownStockDetailsRepository.findAll();
        assertThat(godownStockDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllGodownStockDetails() throws Exception {
        // Initialize the database
        godownStockDetailsRepository.saveAndFlush(godownStockDetails);

        // Get all the godownStockDetailsList
        restGodownStockDetailsMockMvc.perform(get("/api/godown-stock-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(godownStockDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.doubleValue())));
    }
    

    @Test
    @Transactional
    public void getGodownStockDetails() throws Exception {
        // Initialize the database
        godownStockDetailsRepository.saveAndFlush(godownStockDetails);

        // Get the godownStockDetails
        restGodownStockDetailsMockMvc.perform(get("/api/godown-stock-details/{id}", godownStockDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(godownStockDetails.getId().intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY.intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.doubleValue()));
    }

    @Test
    @Transactional
    public void getAllGodownStockDetailsByDateIsEqualToSomething() throws Exception {
        // Initialize the database
        godownStockDetailsRepository.saveAndFlush(godownStockDetails);

        // Get all the godownStockDetailsList where date equals to DEFAULT_DATE
        defaultGodownStockDetailsShouldBeFound("date.equals=" + DEFAULT_DATE);

        // Get all the godownStockDetailsList where date equals to UPDATED_DATE
        defaultGodownStockDetailsShouldNotBeFound("date.equals=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    public void getAllGodownStockDetailsByDateIsInShouldWork() throws Exception {
        // Initialize the database
        godownStockDetailsRepository.saveAndFlush(godownStockDetails);

        // Get all the godownStockDetailsList where date in DEFAULT_DATE or UPDATED_DATE
        defaultGodownStockDetailsShouldBeFound("date.in=" + DEFAULT_DATE + "," + UPDATED_DATE);

        // Get all the godownStockDetailsList where date equals to UPDATED_DATE
        defaultGodownStockDetailsShouldNotBeFound("date.in=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    public void getAllGodownStockDetailsByDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        godownStockDetailsRepository.saveAndFlush(godownStockDetails);

        // Get all the godownStockDetailsList where date is not null
        defaultGodownStockDetailsShouldBeFound("date.specified=true");

        // Get all the godownStockDetailsList where date is null
        defaultGodownStockDetailsShouldNotBeFound("date.specified=false");
    }

    @Test
    @Transactional
    public void getAllGodownStockDetailsByDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        godownStockDetailsRepository.saveAndFlush(godownStockDetails);

        // Get all the godownStockDetailsList where date greater than or equals to DEFAULT_DATE
        defaultGodownStockDetailsShouldBeFound("date.greaterOrEqualThan=" + DEFAULT_DATE);

        // Get all the godownStockDetailsList where date greater than or equals to UPDATED_DATE
        defaultGodownStockDetailsShouldNotBeFound("date.greaterOrEqualThan=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    public void getAllGodownStockDetailsByDateIsLessThanSomething() throws Exception {
        // Initialize the database
        godownStockDetailsRepository.saveAndFlush(godownStockDetails);

        // Get all the godownStockDetailsList where date less than or equals to DEFAULT_DATE
        defaultGodownStockDetailsShouldNotBeFound("date.lessThan=" + DEFAULT_DATE);

        // Get all the godownStockDetailsList where date less than or equals to UPDATED_DATE
        defaultGodownStockDetailsShouldBeFound("date.lessThan=" + UPDATED_DATE);
    }


    @Test
    @Transactional
    public void getAllGodownStockDetailsByQuantityIsEqualToSomething() throws Exception {
        // Initialize the database
        godownStockDetailsRepository.saveAndFlush(godownStockDetails);

        // Get all the godownStockDetailsList where quantity equals to DEFAULT_QUANTITY
        defaultGodownStockDetailsShouldBeFound("quantity.equals=" + DEFAULT_QUANTITY);

        // Get all the godownStockDetailsList where quantity equals to UPDATED_QUANTITY
        defaultGodownStockDetailsShouldNotBeFound("quantity.equals=" + UPDATED_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllGodownStockDetailsByQuantityIsInShouldWork() throws Exception {
        // Initialize the database
        godownStockDetailsRepository.saveAndFlush(godownStockDetails);

        // Get all the godownStockDetailsList where quantity in DEFAULT_QUANTITY or UPDATED_QUANTITY
        defaultGodownStockDetailsShouldBeFound("quantity.in=" + DEFAULT_QUANTITY + "," + UPDATED_QUANTITY);

        // Get all the godownStockDetailsList where quantity equals to UPDATED_QUANTITY
        defaultGodownStockDetailsShouldNotBeFound("quantity.in=" + UPDATED_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllGodownStockDetailsByQuantityIsNullOrNotNull() throws Exception {
        // Initialize the database
        godownStockDetailsRepository.saveAndFlush(godownStockDetails);

        // Get all the godownStockDetailsList where quantity is not null
        defaultGodownStockDetailsShouldBeFound("quantity.specified=true");

        // Get all the godownStockDetailsList where quantity is null
        defaultGodownStockDetailsShouldNotBeFound("quantity.specified=false");
    }

    @Test
    @Transactional
    public void getAllGodownStockDetailsByQuantityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        godownStockDetailsRepository.saveAndFlush(godownStockDetails);

        // Get all the godownStockDetailsList where quantity greater than or equals to DEFAULT_QUANTITY
        defaultGodownStockDetailsShouldBeFound("quantity.greaterOrEqualThan=" + DEFAULT_QUANTITY);

        // Get all the godownStockDetailsList where quantity greater than or equals to UPDATED_QUANTITY
        defaultGodownStockDetailsShouldNotBeFound("quantity.greaterOrEqualThan=" + UPDATED_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllGodownStockDetailsByQuantityIsLessThanSomething() throws Exception {
        // Initialize the database
        godownStockDetailsRepository.saveAndFlush(godownStockDetails);

        // Get all the godownStockDetailsList where quantity less than or equals to DEFAULT_QUANTITY
        defaultGodownStockDetailsShouldNotBeFound("quantity.lessThan=" + DEFAULT_QUANTITY);

        // Get all the godownStockDetailsList where quantity less than or equals to UPDATED_QUANTITY
        defaultGodownStockDetailsShouldBeFound("quantity.lessThan=" + UPDATED_QUANTITY);
    }


    @Test
    @Transactional
    public void getAllGodownStockDetailsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        godownStockDetailsRepository.saveAndFlush(godownStockDetails);

        // Get all the godownStockDetailsList where description equals to DEFAULT_DESCRIPTION
        defaultGodownStockDetailsShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the godownStockDetailsList where description equals to UPDATED_DESCRIPTION
        defaultGodownStockDetailsShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllGodownStockDetailsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        godownStockDetailsRepository.saveAndFlush(godownStockDetails);

        // Get all the godownStockDetailsList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultGodownStockDetailsShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the godownStockDetailsList where description equals to UPDATED_DESCRIPTION
        defaultGodownStockDetailsShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllGodownStockDetailsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        godownStockDetailsRepository.saveAndFlush(godownStockDetails);

        // Get all the godownStockDetailsList where description is not null
        defaultGodownStockDetailsShouldBeFound("description.specified=true");

        // Get all the godownStockDetailsList where description is null
        defaultGodownStockDetailsShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllGodownStockDetailsByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        godownStockDetailsRepository.saveAndFlush(godownStockDetails);

        // Get all the godownStockDetailsList where status equals to DEFAULT_STATUS
        defaultGodownStockDetailsShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the godownStockDetailsList where status equals to UPDATED_STATUS
        defaultGodownStockDetailsShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllGodownStockDetailsByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        godownStockDetailsRepository.saveAndFlush(godownStockDetails);

        // Get all the godownStockDetailsList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultGodownStockDetailsShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the godownStockDetailsList where status equals to UPDATED_STATUS
        defaultGodownStockDetailsShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllGodownStockDetailsByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        godownStockDetailsRepository.saveAndFlush(godownStockDetails);

        // Get all the godownStockDetailsList where status is not null
        defaultGodownStockDetailsShouldBeFound("status.specified=true");

        // Get all the godownStockDetailsList where status is null
        defaultGodownStockDetailsShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllGodownStockDetailsByStatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        godownStockDetailsRepository.saveAndFlush(godownStockDetails);

        // Get all the godownStockDetailsList where status greater than or equals to DEFAULT_STATUS
        defaultGodownStockDetailsShouldBeFound("status.greaterOrEqualThan=" + DEFAULT_STATUS);

        // Get all the godownStockDetailsList where status greater than or equals to UPDATED_STATUS
        defaultGodownStockDetailsShouldNotBeFound("status.greaterOrEqualThan=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllGodownStockDetailsByStatusIsLessThanSomething() throws Exception {
        // Initialize the database
        godownStockDetailsRepository.saveAndFlush(godownStockDetails);

        // Get all the godownStockDetailsList where status less than or equals to DEFAULT_STATUS
        defaultGodownStockDetailsShouldNotBeFound("status.lessThan=" + DEFAULT_STATUS);

        // Get all the godownStockDetailsList where status less than or equals to UPDATED_STATUS
        defaultGodownStockDetailsShouldBeFound("status.lessThan=" + UPDATED_STATUS);
    }


    @Test
    @Transactional
    public void getAllGodownStockDetailsByPriceIsEqualToSomething() throws Exception {
        // Initialize the database
        godownStockDetailsRepository.saveAndFlush(godownStockDetails);

        // Get all the godownStockDetailsList where price equals to DEFAULT_PRICE
        defaultGodownStockDetailsShouldBeFound("price.equals=" + DEFAULT_PRICE);

        // Get all the godownStockDetailsList where price equals to UPDATED_PRICE
        defaultGodownStockDetailsShouldNotBeFound("price.equals=" + UPDATED_PRICE);
    }

    @Test
    @Transactional
    public void getAllGodownStockDetailsByPriceIsInShouldWork() throws Exception {
        // Initialize the database
        godownStockDetailsRepository.saveAndFlush(godownStockDetails);

        // Get all the godownStockDetailsList where price in DEFAULT_PRICE or UPDATED_PRICE
        defaultGodownStockDetailsShouldBeFound("price.in=" + DEFAULT_PRICE + "," + UPDATED_PRICE);

        // Get all the godownStockDetailsList where price equals to UPDATED_PRICE
        defaultGodownStockDetailsShouldNotBeFound("price.in=" + UPDATED_PRICE);
    }

    @Test
    @Transactional
    public void getAllGodownStockDetailsByPriceIsNullOrNotNull() throws Exception {
        // Initialize the database
        godownStockDetailsRepository.saveAndFlush(godownStockDetails);

        // Get all the godownStockDetailsList where price is not null
        defaultGodownStockDetailsShouldBeFound("price.specified=true");

        // Get all the godownStockDetailsList where price is null
        defaultGodownStockDetailsShouldNotBeFound("price.specified=false");
    }

    @Test
    @Transactional
    public void getAllGodownStockDetailsByGodownStockIsEqualToSomething() throws Exception {
        // Initialize the database
        GodownStock godownStock = GodownStockResourceIntTest.createEntity(em);
        em.persist(godownStock);
        em.flush();
        godownStockDetails.setGodownStock(godownStock);
        godownStockDetailsRepository.saveAndFlush(godownStockDetails);
        Long godownStockId = godownStock.getId();

        // Get all the godownStockDetailsList where godownStock equals to godownStockId
        defaultGodownStockDetailsShouldBeFound("godownStockId.equals=" + godownStockId);

        // Get all the godownStockDetailsList where godownStock equals to godownStockId + 1
        defaultGodownStockDetailsShouldNotBeFound("godownStockId.equals=" + (godownStockId + 1));
    }


    @Test
    @Transactional
    public void getAllGodownStockDetailsByFinancialYearGodownStockDetailsIsEqualToSomething() throws Exception {
        // Initialize the database
        FinancialYearSettings financialYearGodownStockDetails = FinancialYearSettingsResourceIntTest.createEntity(em);
        em.persist(financialYearGodownStockDetails);
        em.flush();
        godownStockDetails.setFinancialYearGodownStockDetails(financialYearGodownStockDetails);
        godownStockDetailsRepository.saveAndFlush(godownStockDetails);
        Long financialYearGodownStockDetailsId = financialYearGodownStockDetails.getId();

        // Get all the godownStockDetailsList where financialYearGodownStockDetails equals to financialYearGodownStockDetailsId
        defaultGodownStockDetailsShouldBeFound("financialYearGodownStockDetailsId.equals=" + financialYearGodownStockDetailsId);

        // Get all the godownStockDetailsList where financialYearGodownStockDetails equals to financialYearGodownStockDetailsId + 1
        defaultGodownStockDetailsShouldNotBeFound("financialYearGodownStockDetailsId.equals=" + (financialYearGodownStockDetailsId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultGodownStockDetailsShouldBeFound(String filter) throws Exception {
        restGodownStockDetailsMockMvc.perform(get("/api/godown-stock-details?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(godownStockDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.doubleValue())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultGodownStockDetailsShouldNotBeFound(String filter) throws Exception {
        restGodownStockDetailsMockMvc.perform(get("/api/godown-stock-details?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingGodownStockDetails() throws Exception {
        // Get the godownStockDetails
        restGodownStockDetailsMockMvc.perform(get("/api/godown-stock-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGodownStockDetails() throws Exception {
        // Initialize the database
        godownStockDetailsRepository.saveAndFlush(godownStockDetails);

        int databaseSizeBeforeUpdate = godownStockDetailsRepository.findAll().size();

        // Update the godownStockDetails
        GodownStockDetails updatedGodownStockDetails = godownStockDetailsRepository.findById(godownStockDetails.getId()).get();
        // Disconnect from session so that the updates on updatedGodownStockDetails are not directly saved in db
        em.detach(updatedGodownStockDetails);
        updatedGodownStockDetails
            .date(UPDATED_DATE)
            .quantity(UPDATED_QUANTITY)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS)
            .price(UPDATED_PRICE);
        GodownStockDetailsDTO godownStockDetailsDTO = godownStockDetailsMapper.toDto(updatedGodownStockDetails);

        restGodownStockDetailsMockMvc.perform(put("/api/godown-stock-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(godownStockDetailsDTO)))
            .andExpect(status().isOk());

        // Validate the GodownStockDetails in the database
        List<GodownStockDetails> godownStockDetailsList = godownStockDetailsRepository.findAll();
        assertThat(godownStockDetailsList).hasSize(databaseSizeBeforeUpdate);
        GodownStockDetails testGodownStockDetails = godownStockDetailsList.get(godownStockDetailsList.size() - 1);
        assertThat(testGodownStockDetails.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testGodownStockDetails.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testGodownStockDetails.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testGodownStockDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testGodownStockDetails.getPrice()).isEqualTo(UPDATED_PRICE);
    }

    @Test
    @Transactional
    public void updateNonExistingGodownStockDetails() throws Exception {
        int databaseSizeBeforeUpdate = godownStockDetailsRepository.findAll().size();

        // Create the GodownStockDetails
        GodownStockDetailsDTO godownStockDetailsDTO = godownStockDetailsMapper.toDto(godownStockDetails);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restGodownStockDetailsMockMvc.perform(put("/api/godown-stock-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(godownStockDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GodownStockDetails in the database
        List<GodownStockDetails> godownStockDetailsList = godownStockDetailsRepository.findAll();
        assertThat(godownStockDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGodownStockDetails() throws Exception {
        // Initialize the database
        godownStockDetailsRepository.saveAndFlush(godownStockDetails);

        int databaseSizeBeforeDelete = godownStockDetailsRepository.findAll().size();

        // Get the godownStockDetails
        restGodownStockDetailsMockMvc.perform(delete("/api/godown-stock-details/{id}", godownStockDetails.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<GodownStockDetails> godownStockDetailsList = godownStockDetailsRepository.findAll();
        assertThat(godownStockDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GodownStockDetails.class);
        GodownStockDetails godownStockDetails1 = new GodownStockDetails();
        godownStockDetails1.setId(1L);
        GodownStockDetails godownStockDetails2 = new GodownStockDetails();
        godownStockDetails2.setId(godownStockDetails1.getId());
        assertThat(godownStockDetails1).isEqualTo(godownStockDetails2);
        godownStockDetails2.setId(2L);
        assertThat(godownStockDetails1).isNotEqualTo(godownStockDetails2);
        godownStockDetails1.setId(null);
        assertThat(godownStockDetails1).isNotEqualTo(godownStockDetails2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GodownStockDetailsDTO.class);
        GodownStockDetailsDTO godownStockDetailsDTO1 = new GodownStockDetailsDTO();
        godownStockDetailsDTO1.setId(1L);
        GodownStockDetailsDTO godownStockDetailsDTO2 = new GodownStockDetailsDTO();
        assertThat(godownStockDetailsDTO1).isNotEqualTo(godownStockDetailsDTO2);
        godownStockDetailsDTO2.setId(godownStockDetailsDTO1.getId());
        assertThat(godownStockDetailsDTO1).isEqualTo(godownStockDetailsDTO2);
        godownStockDetailsDTO2.setId(2L);
        assertThat(godownStockDetailsDTO1).isNotEqualTo(godownStockDetailsDTO2);
        godownStockDetailsDTO1.setId(null);
        assertThat(godownStockDetailsDTO1).isNotEqualTo(godownStockDetailsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(godownStockDetailsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(godownStockDetailsMapper.fromId(null)).isNull();
    }
}
