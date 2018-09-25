package com.niche.ng.web.rest;

import com.niche.ng.ProjectghApp;

import com.niche.ng.domain.GodownStock;
import com.niche.ng.domain.GodownStockDetails;
import com.niche.ng.domain.PickListValue;
import com.niche.ng.domain.PickListValue;
import com.niche.ng.domain.PickListValue;
import com.niche.ng.domain.Godown;
import com.niche.ng.domain.FinancialYearSettings;
import com.niche.ng.repository.GodownStockRepository;
import com.niche.ng.service.GodownStockService;
import com.niche.ng.service.dto.GodownStockDTO;
import com.niche.ng.service.mapper.GodownStockMapper;
import com.niche.ng.web.rest.errors.ExceptionTranslator;
import com.niche.ng.service.dto.GodownStockCriteria;
import com.niche.ng.service.GodownStockQueryService;

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
 * Test class for the GodownStockResource REST controller.
 *
 * @see GodownStockResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectghApp.class)
public class GodownStockResourceIntTest {

    private static final Long DEFAULT_CURRENT_QUANTITY = 1L;
    private static final Long UPDATED_CURRENT_QUANTITY = 2L;

    private static final Long DEFAULT_ADDED_QUANTITY = 1L;
    private static final Long UPDATED_ADDED_QUANTITY = 2L;

    private static final Long DEFAULT_CONSUMED_QUANTITY = 1L;
    private static final Long UPDATED_CONSUMED_QUANTITY = 2L;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    @Autowired
    private GodownStockRepository godownStockRepository;


    @Autowired
    private GodownStockMapper godownStockMapper;
    

    @Autowired
    private GodownStockService godownStockService;

    @Autowired
    private GodownStockQueryService godownStockQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restGodownStockMockMvc;

    private GodownStock godownStock;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GodownStockResource godownStockResource = new GodownStockResource(godownStockService, godownStockQueryService);
        this.restGodownStockMockMvc = MockMvcBuilders.standaloneSetup(godownStockResource)
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
    public static GodownStock createEntity(EntityManager em) {
        GodownStock godownStock = new GodownStock()
            .currentQuantity(DEFAULT_CURRENT_QUANTITY)
            .addedQuantity(DEFAULT_ADDED_QUANTITY)
            .consumedQuantity(DEFAULT_CONSUMED_QUANTITY)
            .description(DEFAULT_DESCRIPTION)
            .status(DEFAULT_STATUS);
        return godownStock;
    }

    @Before
    public void initTest() {
        godownStock = createEntity(em);
    }

    @Test
    @Transactional
    public void createGodownStock() throws Exception {
        int databaseSizeBeforeCreate = godownStockRepository.findAll().size();

        // Create the GodownStock
        GodownStockDTO godownStockDTO = godownStockMapper.toDto(godownStock);
        restGodownStockMockMvc.perform(post("/api/godown-stocks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(godownStockDTO)))
            .andExpect(status().isCreated());

        // Validate the GodownStock in the database
        List<GodownStock> godownStockList = godownStockRepository.findAll();
        assertThat(godownStockList).hasSize(databaseSizeBeforeCreate + 1);
        GodownStock testGodownStock = godownStockList.get(godownStockList.size() - 1);
        assertThat(testGodownStock.getCurrentQuantity()).isEqualTo(DEFAULT_CURRENT_QUANTITY);
        assertThat(testGodownStock.getAddedQuantity()).isEqualTo(DEFAULT_ADDED_QUANTITY);
        assertThat(testGodownStock.getConsumedQuantity()).isEqualTo(DEFAULT_CONSUMED_QUANTITY);
        assertThat(testGodownStock.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testGodownStock.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createGodownStockWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = godownStockRepository.findAll().size();

        // Create the GodownStock with an existing ID
        godownStock.setId(1L);
        GodownStockDTO godownStockDTO = godownStockMapper.toDto(godownStock);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGodownStockMockMvc.perform(post("/api/godown-stocks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(godownStockDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GodownStock in the database
        List<GodownStock> godownStockList = godownStockRepository.findAll();
        assertThat(godownStockList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllGodownStocks() throws Exception {
        // Initialize the database
        godownStockRepository.saveAndFlush(godownStock);

        // Get all the godownStockList
        restGodownStockMockMvc.perform(get("/api/godown-stocks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(godownStock.getId().intValue())))
            .andExpect(jsonPath("$.[*].currentQuantity").value(hasItem(DEFAULT_CURRENT_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].addedQuantity").value(hasItem(DEFAULT_ADDED_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].consumedQuantity").value(hasItem(DEFAULT_CONSUMED_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    

    @Test
    @Transactional
    public void getGodownStock() throws Exception {
        // Initialize the database
        godownStockRepository.saveAndFlush(godownStock);

        // Get the godownStock
        restGodownStockMockMvc.perform(get("/api/godown-stocks/{id}", godownStock.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(godownStock.getId().intValue()))
            .andExpect(jsonPath("$.currentQuantity").value(DEFAULT_CURRENT_QUANTITY.intValue()))
            .andExpect(jsonPath("$.addedQuantity").value(DEFAULT_ADDED_QUANTITY.intValue()))
            .andExpect(jsonPath("$.consumedQuantity").value(DEFAULT_CONSUMED_QUANTITY.intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getAllGodownStocksByCurrentQuantityIsEqualToSomething() throws Exception {
        // Initialize the database
        godownStockRepository.saveAndFlush(godownStock);

        // Get all the godownStockList where currentQuantity equals to DEFAULT_CURRENT_QUANTITY
        defaultGodownStockShouldBeFound("currentQuantity.equals=" + DEFAULT_CURRENT_QUANTITY);

        // Get all the godownStockList where currentQuantity equals to UPDATED_CURRENT_QUANTITY
        defaultGodownStockShouldNotBeFound("currentQuantity.equals=" + UPDATED_CURRENT_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllGodownStocksByCurrentQuantityIsInShouldWork() throws Exception {
        // Initialize the database
        godownStockRepository.saveAndFlush(godownStock);

        // Get all the godownStockList where currentQuantity in DEFAULT_CURRENT_QUANTITY or UPDATED_CURRENT_QUANTITY
        defaultGodownStockShouldBeFound("currentQuantity.in=" + DEFAULT_CURRENT_QUANTITY + "," + UPDATED_CURRENT_QUANTITY);

        // Get all the godownStockList where currentQuantity equals to UPDATED_CURRENT_QUANTITY
        defaultGodownStockShouldNotBeFound("currentQuantity.in=" + UPDATED_CURRENT_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllGodownStocksByCurrentQuantityIsNullOrNotNull() throws Exception {
        // Initialize the database
        godownStockRepository.saveAndFlush(godownStock);

        // Get all the godownStockList where currentQuantity is not null
        defaultGodownStockShouldBeFound("currentQuantity.specified=true");

        // Get all the godownStockList where currentQuantity is null
        defaultGodownStockShouldNotBeFound("currentQuantity.specified=false");
    }

    @Test
    @Transactional
    public void getAllGodownStocksByCurrentQuantityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        godownStockRepository.saveAndFlush(godownStock);

        // Get all the godownStockList where currentQuantity greater than or equals to DEFAULT_CURRENT_QUANTITY
        defaultGodownStockShouldBeFound("currentQuantity.greaterOrEqualThan=" + DEFAULT_CURRENT_QUANTITY);

        // Get all the godownStockList where currentQuantity greater than or equals to UPDATED_CURRENT_QUANTITY
        defaultGodownStockShouldNotBeFound("currentQuantity.greaterOrEqualThan=" + UPDATED_CURRENT_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllGodownStocksByCurrentQuantityIsLessThanSomething() throws Exception {
        // Initialize the database
        godownStockRepository.saveAndFlush(godownStock);

        // Get all the godownStockList where currentQuantity less than or equals to DEFAULT_CURRENT_QUANTITY
        defaultGodownStockShouldNotBeFound("currentQuantity.lessThan=" + DEFAULT_CURRENT_QUANTITY);

        // Get all the godownStockList where currentQuantity less than or equals to UPDATED_CURRENT_QUANTITY
        defaultGodownStockShouldBeFound("currentQuantity.lessThan=" + UPDATED_CURRENT_QUANTITY);
    }


    @Test
    @Transactional
    public void getAllGodownStocksByAddedQuantityIsEqualToSomething() throws Exception {
        // Initialize the database
        godownStockRepository.saveAndFlush(godownStock);

        // Get all the godownStockList where addedQuantity equals to DEFAULT_ADDED_QUANTITY
        defaultGodownStockShouldBeFound("addedQuantity.equals=" + DEFAULT_ADDED_QUANTITY);

        // Get all the godownStockList where addedQuantity equals to UPDATED_ADDED_QUANTITY
        defaultGodownStockShouldNotBeFound("addedQuantity.equals=" + UPDATED_ADDED_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllGodownStocksByAddedQuantityIsInShouldWork() throws Exception {
        // Initialize the database
        godownStockRepository.saveAndFlush(godownStock);

        // Get all the godownStockList where addedQuantity in DEFAULT_ADDED_QUANTITY or UPDATED_ADDED_QUANTITY
        defaultGodownStockShouldBeFound("addedQuantity.in=" + DEFAULT_ADDED_QUANTITY + "," + UPDATED_ADDED_QUANTITY);

        // Get all the godownStockList where addedQuantity equals to UPDATED_ADDED_QUANTITY
        defaultGodownStockShouldNotBeFound("addedQuantity.in=" + UPDATED_ADDED_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllGodownStocksByAddedQuantityIsNullOrNotNull() throws Exception {
        // Initialize the database
        godownStockRepository.saveAndFlush(godownStock);

        // Get all the godownStockList where addedQuantity is not null
        defaultGodownStockShouldBeFound("addedQuantity.specified=true");

        // Get all the godownStockList where addedQuantity is null
        defaultGodownStockShouldNotBeFound("addedQuantity.specified=false");
    }

    @Test
    @Transactional
    public void getAllGodownStocksByAddedQuantityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        godownStockRepository.saveAndFlush(godownStock);

        // Get all the godownStockList where addedQuantity greater than or equals to DEFAULT_ADDED_QUANTITY
        defaultGodownStockShouldBeFound("addedQuantity.greaterOrEqualThan=" + DEFAULT_ADDED_QUANTITY);

        // Get all the godownStockList where addedQuantity greater than or equals to UPDATED_ADDED_QUANTITY
        defaultGodownStockShouldNotBeFound("addedQuantity.greaterOrEqualThan=" + UPDATED_ADDED_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllGodownStocksByAddedQuantityIsLessThanSomething() throws Exception {
        // Initialize the database
        godownStockRepository.saveAndFlush(godownStock);

        // Get all the godownStockList where addedQuantity less than or equals to DEFAULT_ADDED_QUANTITY
        defaultGodownStockShouldNotBeFound("addedQuantity.lessThan=" + DEFAULT_ADDED_QUANTITY);

        // Get all the godownStockList where addedQuantity less than or equals to UPDATED_ADDED_QUANTITY
        defaultGodownStockShouldBeFound("addedQuantity.lessThan=" + UPDATED_ADDED_QUANTITY);
    }


    @Test
    @Transactional
    public void getAllGodownStocksByConsumedQuantityIsEqualToSomething() throws Exception {
        // Initialize the database
        godownStockRepository.saveAndFlush(godownStock);

        // Get all the godownStockList where consumedQuantity equals to DEFAULT_CONSUMED_QUANTITY
        defaultGodownStockShouldBeFound("consumedQuantity.equals=" + DEFAULT_CONSUMED_QUANTITY);

        // Get all the godownStockList where consumedQuantity equals to UPDATED_CONSUMED_QUANTITY
        defaultGodownStockShouldNotBeFound("consumedQuantity.equals=" + UPDATED_CONSUMED_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllGodownStocksByConsumedQuantityIsInShouldWork() throws Exception {
        // Initialize the database
        godownStockRepository.saveAndFlush(godownStock);

        // Get all the godownStockList where consumedQuantity in DEFAULT_CONSUMED_QUANTITY or UPDATED_CONSUMED_QUANTITY
        defaultGodownStockShouldBeFound("consumedQuantity.in=" + DEFAULT_CONSUMED_QUANTITY + "," + UPDATED_CONSUMED_QUANTITY);

        // Get all the godownStockList where consumedQuantity equals to UPDATED_CONSUMED_QUANTITY
        defaultGodownStockShouldNotBeFound("consumedQuantity.in=" + UPDATED_CONSUMED_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllGodownStocksByConsumedQuantityIsNullOrNotNull() throws Exception {
        // Initialize the database
        godownStockRepository.saveAndFlush(godownStock);

        // Get all the godownStockList where consumedQuantity is not null
        defaultGodownStockShouldBeFound("consumedQuantity.specified=true");

        // Get all the godownStockList where consumedQuantity is null
        defaultGodownStockShouldNotBeFound("consumedQuantity.specified=false");
    }

    @Test
    @Transactional
    public void getAllGodownStocksByConsumedQuantityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        godownStockRepository.saveAndFlush(godownStock);

        // Get all the godownStockList where consumedQuantity greater than or equals to DEFAULT_CONSUMED_QUANTITY
        defaultGodownStockShouldBeFound("consumedQuantity.greaterOrEqualThan=" + DEFAULT_CONSUMED_QUANTITY);

        // Get all the godownStockList where consumedQuantity greater than or equals to UPDATED_CONSUMED_QUANTITY
        defaultGodownStockShouldNotBeFound("consumedQuantity.greaterOrEqualThan=" + UPDATED_CONSUMED_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllGodownStocksByConsumedQuantityIsLessThanSomething() throws Exception {
        // Initialize the database
        godownStockRepository.saveAndFlush(godownStock);

        // Get all the godownStockList where consumedQuantity less than or equals to DEFAULT_CONSUMED_QUANTITY
        defaultGodownStockShouldNotBeFound("consumedQuantity.lessThan=" + DEFAULT_CONSUMED_QUANTITY);

        // Get all the godownStockList where consumedQuantity less than or equals to UPDATED_CONSUMED_QUANTITY
        defaultGodownStockShouldBeFound("consumedQuantity.lessThan=" + UPDATED_CONSUMED_QUANTITY);
    }


    @Test
    @Transactional
    public void getAllGodownStocksByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        godownStockRepository.saveAndFlush(godownStock);

        // Get all the godownStockList where description equals to DEFAULT_DESCRIPTION
        defaultGodownStockShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the godownStockList where description equals to UPDATED_DESCRIPTION
        defaultGodownStockShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllGodownStocksByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        godownStockRepository.saveAndFlush(godownStock);

        // Get all the godownStockList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultGodownStockShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the godownStockList where description equals to UPDATED_DESCRIPTION
        defaultGodownStockShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllGodownStocksByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        godownStockRepository.saveAndFlush(godownStock);

        // Get all the godownStockList where description is not null
        defaultGodownStockShouldBeFound("description.specified=true");

        // Get all the godownStockList where description is null
        defaultGodownStockShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllGodownStocksByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        godownStockRepository.saveAndFlush(godownStock);

        // Get all the godownStockList where status equals to DEFAULT_STATUS
        defaultGodownStockShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the godownStockList where status equals to UPDATED_STATUS
        defaultGodownStockShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllGodownStocksByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        godownStockRepository.saveAndFlush(godownStock);

        // Get all the godownStockList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultGodownStockShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the godownStockList where status equals to UPDATED_STATUS
        defaultGodownStockShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllGodownStocksByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        godownStockRepository.saveAndFlush(godownStock);

        // Get all the godownStockList where status is not null
        defaultGodownStockShouldBeFound("status.specified=true");

        // Get all the godownStockList where status is null
        defaultGodownStockShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllGodownStocksByStatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        godownStockRepository.saveAndFlush(godownStock);

        // Get all the godownStockList where status greater than or equals to DEFAULT_STATUS
        defaultGodownStockShouldBeFound("status.greaterOrEqualThan=" + DEFAULT_STATUS);

        // Get all the godownStockList where status greater than or equals to UPDATED_STATUS
        defaultGodownStockShouldNotBeFound("status.greaterOrEqualThan=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllGodownStocksByStatusIsLessThanSomething() throws Exception {
        // Initialize the database
        godownStockRepository.saveAndFlush(godownStock);

        // Get all the godownStockList where status less than or equals to DEFAULT_STATUS
        defaultGodownStockShouldNotBeFound("status.lessThan=" + DEFAULT_STATUS);

        // Get all the godownStockList where status less than or equals to UPDATED_STATUS
        defaultGodownStockShouldBeFound("status.lessThan=" + UPDATED_STATUS);
    }


    @Test
    @Transactional
    public void getAllGodownStocksByGodownStockDetailsIsEqualToSomething() throws Exception {
        // Initialize the database
        GodownStockDetails godownStockDetails = GodownStockDetailsResourceIntTest.createEntity(em);
        em.persist(godownStockDetails);
        em.flush();
        godownStock.addGodownStockDetails(godownStockDetails);
        godownStockRepository.saveAndFlush(godownStock);
        Long godownStockDetailsId = godownStockDetails.getId();

        // Get all the godownStockList where godownStockDetails equals to godownStockDetailsId
        defaultGodownStockShouldBeFound("godownStockDetailsId.equals=" + godownStockDetailsId);

        // Get all the godownStockList where godownStockDetails equals to godownStockDetailsId + 1
        defaultGodownStockShouldNotBeFound("godownStockDetailsId.equals=" + (godownStockDetailsId + 1));
    }


    @Test
    @Transactional
    public void getAllGodownStocksByPickListVarietyIsEqualToSomething() throws Exception {
        // Initialize the database
        PickListValue pickListVariety = PickListValueResourceIntTest.createEntity(em);
        em.persist(pickListVariety);
        em.flush();
        godownStock.setPickListVariety(pickListVariety);
        godownStockRepository.saveAndFlush(godownStock);
        Long pickListVarietyId = pickListVariety.getId();

        // Get all the godownStockList where pickListVariety equals to pickListVarietyId
        defaultGodownStockShouldBeFound("pickListVarietyId.equals=" + pickListVarietyId);

        // Get all the godownStockList where pickListVariety equals to pickListVarietyId + 1
        defaultGodownStockShouldNotBeFound("pickListVarietyId.equals=" + (pickListVarietyId + 1));
    }


    @Test
    @Transactional
    public void getAllGodownStocksByPickListCategoryIsEqualToSomething() throws Exception {
        // Initialize the database
        PickListValue pickListCategory = PickListValueResourceIntTest.createEntity(em);
        em.persist(pickListCategory);
        em.flush();
        godownStock.setPickListCategory(pickListCategory);
        godownStockRepository.saveAndFlush(godownStock);
        Long pickListCategoryId = pickListCategory.getId();

        // Get all the godownStockList where pickListCategory equals to pickListCategoryId
        defaultGodownStockShouldBeFound("pickListCategoryId.equals=" + pickListCategoryId);

        // Get all the godownStockList where pickListCategory equals to pickListCategoryId + 1
        defaultGodownStockShouldNotBeFound("pickListCategoryId.equals=" + (pickListCategoryId + 1));
    }


    @Test
    @Transactional
    public void getAllGodownStocksByPickListQuantityTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        PickListValue pickListQuantityType = PickListValueResourceIntTest.createEntity(em);
        em.persist(pickListQuantityType);
        em.flush();
        godownStock.setPickListQuantityType(pickListQuantityType);
        godownStockRepository.saveAndFlush(godownStock);
        Long pickListQuantityTypeId = pickListQuantityType.getId();

        // Get all the godownStockList where pickListQuantityType equals to pickListQuantityTypeId
        defaultGodownStockShouldBeFound("pickListQuantityTypeId.equals=" + pickListQuantityTypeId);

        // Get all the godownStockList where pickListQuantityType equals to pickListQuantityTypeId + 1
        defaultGodownStockShouldNotBeFound("pickListQuantityTypeId.equals=" + (pickListQuantityTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllGodownStocksByGodownIsEqualToSomething() throws Exception {
        // Initialize the database
        Godown godown = GodownResourceIntTest.createEntity(em);
        em.persist(godown);
        em.flush();
        godownStock.setGodown(godown);
        godownStockRepository.saveAndFlush(godownStock);
        Long godownId = godown.getId();

        // Get all the godownStockList where godown equals to godownId
        defaultGodownStockShouldBeFound("godownId.equals=" + godownId);

        // Get all the godownStockList where godown equals to godownId + 1
        defaultGodownStockShouldNotBeFound("godownId.equals=" + (godownId + 1));
    }


    @Test
    @Transactional
    public void getAllGodownStocksByFinancialYearGodownStockIsEqualToSomething() throws Exception {
        // Initialize the database
        FinancialYearSettings financialYearGodownStock = FinancialYearSettingsResourceIntTest.createEntity(em);
        em.persist(financialYearGodownStock);
        em.flush();
        godownStock.setFinancialYearGodownStock(financialYearGodownStock);
        godownStockRepository.saveAndFlush(godownStock);
        Long financialYearGodownStockId = financialYearGodownStock.getId();

        // Get all the godownStockList where financialYearGodownStock equals to financialYearGodownStockId
        defaultGodownStockShouldBeFound("financialYearGodownStockId.equals=" + financialYearGodownStockId);

        // Get all the godownStockList where financialYearGodownStock equals to financialYearGodownStockId + 1
        defaultGodownStockShouldNotBeFound("financialYearGodownStockId.equals=" + (financialYearGodownStockId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultGodownStockShouldBeFound(String filter) throws Exception {
        restGodownStockMockMvc.perform(get("/api/godown-stocks?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(godownStock.getId().intValue())))
            .andExpect(jsonPath("$.[*].currentQuantity").value(hasItem(DEFAULT_CURRENT_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].addedQuantity").value(hasItem(DEFAULT_ADDED_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].consumedQuantity").value(hasItem(DEFAULT_CONSUMED_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultGodownStockShouldNotBeFound(String filter) throws Exception {
        restGodownStockMockMvc.perform(get("/api/godown-stocks?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingGodownStock() throws Exception {
        // Get the godownStock
        restGodownStockMockMvc.perform(get("/api/godown-stocks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGodownStock() throws Exception {
        // Initialize the database
        godownStockRepository.saveAndFlush(godownStock);

        int databaseSizeBeforeUpdate = godownStockRepository.findAll().size();

        // Update the godownStock
        GodownStock updatedGodownStock = godownStockRepository.findById(godownStock.getId()).get();
        // Disconnect from session so that the updates on updatedGodownStock are not directly saved in db
        em.detach(updatedGodownStock);
        updatedGodownStock
            .currentQuantity(UPDATED_CURRENT_QUANTITY)
            .addedQuantity(UPDATED_ADDED_QUANTITY)
            .consumedQuantity(UPDATED_CONSUMED_QUANTITY)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS);
        GodownStockDTO godownStockDTO = godownStockMapper.toDto(updatedGodownStock);

        restGodownStockMockMvc.perform(put("/api/godown-stocks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(godownStockDTO)))
            .andExpect(status().isOk());

        // Validate the GodownStock in the database
        List<GodownStock> godownStockList = godownStockRepository.findAll();
        assertThat(godownStockList).hasSize(databaseSizeBeforeUpdate);
        GodownStock testGodownStock = godownStockList.get(godownStockList.size() - 1);
        assertThat(testGodownStock.getCurrentQuantity()).isEqualTo(UPDATED_CURRENT_QUANTITY);
        assertThat(testGodownStock.getAddedQuantity()).isEqualTo(UPDATED_ADDED_QUANTITY);
        assertThat(testGodownStock.getConsumedQuantity()).isEqualTo(UPDATED_CONSUMED_QUANTITY);
        assertThat(testGodownStock.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testGodownStock.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingGodownStock() throws Exception {
        int databaseSizeBeforeUpdate = godownStockRepository.findAll().size();

        // Create the GodownStock
        GodownStockDTO godownStockDTO = godownStockMapper.toDto(godownStock);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restGodownStockMockMvc.perform(put("/api/godown-stocks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(godownStockDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GodownStock in the database
        List<GodownStock> godownStockList = godownStockRepository.findAll();
        assertThat(godownStockList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGodownStock() throws Exception {
        // Initialize the database
        godownStockRepository.saveAndFlush(godownStock);

        int databaseSizeBeforeDelete = godownStockRepository.findAll().size();

        // Get the godownStock
        restGodownStockMockMvc.perform(delete("/api/godown-stocks/{id}", godownStock.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<GodownStock> godownStockList = godownStockRepository.findAll();
        assertThat(godownStockList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GodownStock.class);
        GodownStock godownStock1 = new GodownStock();
        godownStock1.setId(1L);
        GodownStock godownStock2 = new GodownStock();
        godownStock2.setId(godownStock1.getId());
        assertThat(godownStock1).isEqualTo(godownStock2);
        godownStock2.setId(2L);
        assertThat(godownStock1).isNotEqualTo(godownStock2);
        godownStock1.setId(null);
        assertThat(godownStock1).isNotEqualTo(godownStock2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GodownStockDTO.class);
        GodownStockDTO godownStockDTO1 = new GodownStockDTO();
        godownStockDTO1.setId(1L);
        GodownStockDTO godownStockDTO2 = new GodownStockDTO();
        assertThat(godownStockDTO1).isNotEqualTo(godownStockDTO2);
        godownStockDTO2.setId(godownStockDTO1.getId());
        assertThat(godownStockDTO1).isEqualTo(godownStockDTO2);
        godownStockDTO2.setId(2L);
        assertThat(godownStockDTO1).isNotEqualTo(godownStockDTO2);
        godownStockDTO1.setId(null);
        assertThat(godownStockDTO1).isNotEqualTo(godownStockDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(godownStockMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(godownStockMapper.fromId(null)).isNull();
    }
}
