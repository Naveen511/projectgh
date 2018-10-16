package com.niche.ng.web.rest;

import com.niche.ng.ProjectghApp;

import com.niche.ng.domain.NurseryStock;
import com.niche.ng.domain.NurseryStockDetails;
import com.niche.ng.domain.Nursery;
import com.niche.ng.domain.PickListValue;
import com.niche.ng.domain.PickListValue;
import com.niche.ng.domain.FinancialYearSettings;
import com.niche.ng.domain.PointOfSaleDetails;
import com.niche.ng.repository.NurseryStockRepository;
import com.niche.ng.service.NurseryStockService;
import com.niche.ng.service.dto.NurseryStockDTO;
import com.niche.ng.service.mapper.NurseryStockMapper;
import com.niche.ng.web.rest.errors.ExceptionTranslator;
import com.niche.ng.service.dto.NurseryStockCriteria;
import com.niche.ng.service.NurseryStockQueryService;

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
 * Test class for the NurseryStockResource REST controller.
 *
 * @see NurseryStockResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectghApp.class)
public class NurseryStockResourceIntTest {

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

    private static final Integer DEFAULT_POS_QUANTITY = 1;
    private static final Integer UPDATED_POS_QUANTITY = 2;

    private static final Long DEFAULT_DAMAGE_QUANTITY = 1L;
    private static final Long UPDATED_DAMAGE_QUANTITY = 2L;

    @Autowired
    private NurseryStockRepository nurseryStockRepository;


    @Autowired
    private NurseryStockMapper nurseryStockMapper;
    

    @Autowired
    private NurseryStockService nurseryStockService;

    @Autowired
    private NurseryStockQueryService nurseryStockQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restNurseryStockMockMvc;

    private NurseryStock nurseryStock;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NurseryStockResource nurseryStockResource = new NurseryStockResource(nurseryStockService, nurseryStockQueryService);
        this.restNurseryStockMockMvc = MockMvcBuilders.standaloneSetup(nurseryStockResource)
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
    public static NurseryStock createEntity(EntityManager em) {
        NurseryStock nurseryStock = new NurseryStock()
            .currentQuantity(DEFAULT_CURRENT_QUANTITY)
            .addedQuantity(DEFAULT_ADDED_QUANTITY)
            .consumedQuantity(DEFAULT_CONSUMED_QUANTITY)
            .description(DEFAULT_DESCRIPTION)
            .status(DEFAULT_STATUS)
            .posQuantity(DEFAULT_POS_QUANTITY)
            .damageQuantity(DEFAULT_DAMAGE_QUANTITY);
        return nurseryStock;
    }

    @Before
    public void initTest() {
        nurseryStock = createEntity(em);
    }

    @Test
    @Transactional
    public void createNurseryStock() throws Exception {
        int databaseSizeBeforeCreate = nurseryStockRepository.findAll().size();

        // Create the NurseryStock
        NurseryStockDTO nurseryStockDTO = nurseryStockMapper.toDto(nurseryStock);
        restNurseryStockMockMvc.perform(post("/api/nursery-stocks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryStockDTO)))
            .andExpect(status().isCreated());

        // Validate the NurseryStock in the database
        List<NurseryStock> nurseryStockList = nurseryStockRepository.findAll();
        assertThat(nurseryStockList).hasSize(databaseSizeBeforeCreate + 1);
        NurseryStock testNurseryStock = nurseryStockList.get(nurseryStockList.size() - 1);
        assertThat(testNurseryStock.getCurrentQuantity()).isEqualTo(DEFAULT_CURRENT_QUANTITY);
        assertThat(testNurseryStock.getAddedQuantity()).isEqualTo(DEFAULT_ADDED_QUANTITY);
        assertThat(testNurseryStock.getConsumedQuantity()).isEqualTo(DEFAULT_CONSUMED_QUANTITY);
        assertThat(testNurseryStock.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testNurseryStock.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testNurseryStock.getPosQuantity()).isEqualTo(DEFAULT_POS_QUANTITY);
        assertThat(testNurseryStock.getDamageQuantity()).isEqualTo(DEFAULT_DAMAGE_QUANTITY);
    }

    @Test
    @Transactional
    public void createNurseryStockWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nurseryStockRepository.findAll().size();

        // Create the NurseryStock with an existing ID
        nurseryStock.setId(1L);
        NurseryStockDTO nurseryStockDTO = nurseryStockMapper.toDto(nurseryStock);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNurseryStockMockMvc.perform(post("/api/nursery-stocks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryStockDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NurseryStock in the database
        List<NurseryStock> nurseryStockList = nurseryStockRepository.findAll();
        assertThat(nurseryStockList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllNurseryStocks() throws Exception {
        // Initialize the database
        nurseryStockRepository.saveAndFlush(nurseryStock);

        // Get all the nurseryStockList
        restNurseryStockMockMvc.perform(get("/api/nursery-stocks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nurseryStock.getId().intValue())))
            .andExpect(jsonPath("$.[*].currentQuantity").value(hasItem(DEFAULT_CURRENT_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].addedQuantity").value(hasItem(DEFAULT_ADDED_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].consumedQuantity").value(hasItem(DEFAULT_CONSUMED_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].posQuantity").value(hasItem(DEFAULT_POS_QUANTITY)))
            .andExpect(jsonPath("$.[*].damageQuantity").value(hasItem(DEFAULT_DAMAGE_QUANTITY.intValue())));
    }
    

    @Test
    @Transactional
    public void getNurseryStock() throws Exception {
        // Initialize the database
        nurseryStockRepository.saveAndFlush(nurseryStock);

        // Get the nurseryStock
        restNurseryStockMockMvc.perform(get("/api/nursery-stocks/{id}", nurseryStock.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(nurseryStock.getId().intValue()))
            .andExpect(jsonPath("$.currentQuantity").value(DEFAULT_CURRENT_QUANTITY.intValue()))
            .andExpect(jsonPath("$.addedQuantity").value(DEFAULT_ADDED_QUANTITY.intValue()))
            .andExpect(jsonPath("$.consumedQuantity").value(DEFAULT_CONSUMED_QUANTITY.intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.posQuantity").value(DEFAULT_POS_QUANTITY))
            .andExpect(jsonPath("$.damageQuantity").value(DEFAULT_DAMAGE_QUANTITY.intValue()));
    }

    @Test
    @Transactional
    public void getAllNurseryStocksByCurrentQuantityIsEqualToSomething() throws Exception {
        // Initialize the database
        nurseryStockRepository.saveAndFlush(nurseryStock);

        // Get all the nurseryStockList where currentQuantity equals to DEFAULT_CURRENT_QUANTITY
        defaultNurseryStockShouldBeFound("currentQuantity.equals=" + DEFAULT_CURRENT_QUANTITY);

        // Get all the nurseryStockList where currentQuantity equals to UPDATED_CURRENT_QUANTITY
        defaultNurseryStockShouldNotBeFound("currentQuantity.equals=" + UPDATED_CURRENT_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllNurseryStocksByCurrentQuantityIsInShouldWork() throws Exception {
        // Initialize the database
        nurseryStockRepository.saveAndFlush(nurseryStock);

        // Get all the nurseryStockList where currentQuantity in DEFAULT_CURRENT_QUANTITY or UPDATED_CURRENT_QUANTITY
        defaultNurseryStockShouldBeFound("currentQuantity.in=" + DEFAULT_CURRENT_QUANTITY + "," + UPDATED_CURRENT_QUANTITY);

        // Get all the nurseryStockList where currentQuantity equals to UPDATED_CURRENT_QUANTITY
        defaultNurseryStockShouldNotBeFound("currentQuantity.in=" + UPDATED_CURRENT_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllNurseryStocksByCurrentQuantityIsNullOrNotNull() throws Exception {
        // Initialize the database
        nurseryStockRepository.saveAndFlush(nurseryStock);

        // Get all the nurseryStockList where currentQuantity is not null
        defaultNurseryStockShouldBeFound("currentQuantity.specified=true");

        // Get all the nurseryStockList where currentQuantity is null
        defaultNurseryStockShouldNotBeFound("currentQuantity.specified=false");
    }

    @Test
    @Transactional
    public void getAllNurseryStocksByCurrentQuantityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        nurseryStockRepository.saveAndFlush(nurseryStock);

        // Get all the nurseryStockList where currentQuantity greater than or equals to DEFAULT_CURRENT_QUANTITY
        defaultNurseryStockShouldBeFound("currentQuantity.greaterOrEqualThan=" + DEFAULT_CURRENT_QUANTITY);

        // Get all the nurseryStockList where currentQuantity greater than or equals to UPDATED_CURRENT_QUANTITY
        defaultNurseryStockShouldNotBeFound("currentQuantity.greaterOrEqualThan=" + UPDATED_CURRENT_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllNurseryStocksByCurrentQuantityIsLessThanSomething() throws Exception {
        // Initialize the database
        nurseryStockRepository.saveAndFlush(nurseryStock);

        // Get all the nurseryStockList where currentQuantity less than or equals to DEFAULT_CURRENT_QUANTITY
        defaultNurseryStockShouldNotBeFound("currentQuantity.lessThan=" + DEFAULT_CURRENT_QUANTITY);

        // Get all the nurseryStockList where currentQuantity less than or equals to UPDATED_CURRENT_QUANTITY
        defaultNurseryStockShouldBeFound("currentQuantity.lessThan=" + UPDATED_CURRENT_QUANTITY);
    }


    @Test
    @Transactional
    public void getAllNurseryStocksByAddedQuantityIsEqualToSomething() throws Exception {
        // Initialize the database
        nurseryStockRepository.saveAndFlush(nurseryStock);

        // Get all the nurseryStockList where addedQuantity equals to DEFAULT_ADDED_QUANTITY
        defaultNurseryStockShouldBeFound("addedQuantity.equals=" + DEFAULT_ADDED_QUANTITY);

        // Get all the nurseryStockList where addedQuantity equals to UPDATED_ADDED_QUANTITY
        defaultNurseryStockShouldNotBeFound("addedQuantity.equals=" + UPDATED_ADDED_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllNurseryStocksByAddedQuantityIsInShouldWork() throws Exception {
        // Initialize the database
        nurseryStockRepository.saveAndFlush(nurseryStock);

        // Get all the nurseryStockList where addedQuantity in DEFAULT_ADDED_QUANTITY or UPDATED_ADDED_QUANTITY
        defaultNurseryStockShouldBeFound("addedQuantity.in=" + DEFAULT_ADDED_QUANTITY + "," + UPDATED_ADDED_QUANTITY);

        // Get all the nurseryStockList where addedQuantity equals to UPDATED_ADDED_QUANTITY
        defaultNurseryStockShouldNotBeFound("addedQuantity.in=" + UPDATED_ADDED_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllNurseryStocksByAddedQuantityIsNullOrNotNull() throws Exception {
        // Initialize the database
        nurseryStockRepository.saveAndFlush(nurseryStock);

        // Get all the nurseryStockList where addedQuantity is not null
        defaultNurseryStockShouldBeFound("addedQuantity.specified=true");

        // Get all the nurseryStockList where addedQuantity is null
        defaultNurseryStockShouldNotBeFound("addedQuantity.specified=false");
    }

    @Test
    @Transactional
    public void getAllNurseryStocksByAddedQuantityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        nurseryStockRepository.saveAndFlush(nurseryStock);

        // Get all the nurseryStockList where addedQuantity greater than or equals to DEFAULT_ADDED_QUANTITY
        defaultNurseryStockShouldBeFound("addedQuantity.greaterOrEqualThan=" + DEFAULT_ADDED_QUANTITY);

        // Get all the nurseryStockList where addedQuantity greater than or equals to UPDATED_ADDED_QUANTITY
        defaultNurseryStockShouldNotBeFound("addedQuantity.greaterOrEqualThan=" + UPDATED_ADDED_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllNurseryStocksByAddedQuantityIsLessThanSomething() throws Exception {
        // Initialize the database
        nurseryStockRepository.saveAndFlush(nurseryStock);

        // Get all the nurseryStockList where addedQuantity less than or equals to DEFAULT_ADDED_QUANTITY
        defaultNurseryStockShouldNotBeFound("addedQuantity.lessThan=" + DEFAULT_ADDED_QUANTITY);

        // Get all the nurseryStockList where addedQuantity less than or equals to UPDATED_ADDED_QUANTITY
        defaultNurseryStockShouldBeFound("addedQuantity.lessThan=" + UPDATED_ADDED_QUANTITY);
    }


    @Test
    @Transactional
    public void getAllNurseryStocksByConsumedQuantityIsEqualToSomething() throws Exception {
        // Initialize the database
        nurseryStockRepository.saveAndFlush(nurseryStock);

        // Get all the nurseryStockList where consumedQuantity equals to DEFAULT_CONSUMED_QUANTITY
        defaultNurseryStockShouldBeFound("consumedQuantity.equals=" + DEFAULT_CONSUMED_QUANTITY);

        // Get all the nurseryStockList where consumedQuantity equals to UPDATED_CONSUMED_QUANTITY
        defaultNurseryStockShouldNotBeFound("consumedQuantity.equals=" + UPDATED_CONSUMED_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllNurseryStocksByConsumedQuantityIsInShouldWork() throws Exception {
        // Initialize the database
        nurseryStockRepository.saveAndFlush(nurseryStock);

        // Get all the nurseryStockList where consumedQuantity in DEFAULT_CONSUMED_QUANTITY or UPDATED_CONSUMED_QUANTITY
        defaultNurseryStockShouldBeFound("consumedQuantity.in=" + DEFAULT_CONSUMED_QUANTITY + "," + UPDATED_CONSUMED_QUANTITY);

        // Get all the nurseryStockList where consumedQuantity equals to UPDATED_CONSUMED_QUANTITY
        defaultNurseryStockShouldNotBeFound("consumedQuantity.in=" + UPDATED_CONSUMED_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllNurseryStocksByConsumedQuantityIsNullOrNotNull() throws Exception {
        // Initialize the database
        nurseryStockRepository.saveAndFlush(nurseryStock);

        // Get all the nurseryStockList where consumedQuantity is not null
        defaultNurseryStockShouldBeFound("consumedQuantity.specified=true");

        // Get all the nurseryStockList where consumedQuantity is null
        defaultNurseryStockShouldNotBeFound("consumedQuantity.specified=false");
    }

    @Test
    @Transactional
    public void getAllNurseryStocksByConsumedQuantityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        nurseryStockRepository.saveAndFlush(nurseryStock);

        // Get all the nurseryStockList where consumedQuantity greater than or equals to DEFAULT_CONSUMED_QUANTITY
        defaultNurseryStockShouldBeFound("consumedQuantity.greaterOrEqualThan=" + DEFAULT_CONSUMED_QUANTITY);

        // Get all the nurseryStockList where consumedQuantity greater than or equals to UPDATED_CONSUMED_QUANTITY
        defaultNurseryStockShouldNotBeFound("consumedQuantity.greaterOrEqualThan=" + UPDATED_CONSUMED_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllNurseryStocksByConsumedQuantityIsLessThanSomething() throws Exception {
        // Initialize the database
        nurseryStockRepository.saveAndFlush(nurseryStock);

        // Get all the nurseryStockList where consumedQuantity less than or equals to DEFAULT_CONSUMED_QUANTITY
        defaultNurseryStockShouldNotBeFound("consumedQuantity.lessThan=" + DEFAULT_CONSUMED_QUANTITY);

        // Get all the nurseryStockList where consumedQuantity less than or equals to UPDATED_CONSUMED_QUANTITY
        defaultNurseryStockShouldBeFound("consumedQuantity.lessThan=" + UPDATED_CONSUMED_QUANTITY);
    }


    @Test
    @Transactional
    public void getAllNurseryStocksByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        nurseryStockRepository.saveAndFlush(nurseryStock);

        // Get all the nurseryStockList where description equals to DEFAULT_DESCRIPTION
        defaultNurseryStockShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the nurseryStockList where description equals to UPDATED_DESCRIPTION
        defaultNurseryStockShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllNurseryStocksByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        nurseryStockRepository.saveAndFlush(nurseryStock);

        // Get all the nurseryStockList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultNurseryStockShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the nurseryStockList where description equals to UPDATED_DESCRIPTION
        defaultNurseryStockShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllNurseryStocksByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        nurseryStockRepository.saveAndFlush(nurseryStock);

        // Get all the nurseryStockList where description is not null
        defaultNurseryStockShouldBeFound("description.specified=true");

        // Get all the nurseryStockList where description is null
        defaultNurseryStockShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllNurseryStocksByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        nurseryStockRepository.saveAndFlush(nurseryStock);

        // Get all the nurseryStockList where status equals to DEFAULT_STATUS
        defaultNurseryStockShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the nurseryStockList where status equals to UPDATED_STATUS
        defaultNurseryStockShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllNurseryStocksByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        nurseryStockRepository.saveAndFlush(nurseryStock);

        // Get all the nurseryStockList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultNurseryStockShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the nurseryStockList where status equals to UPDATED_STATUS
        defaultNurseryStockShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllNurseryStocksByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        nurseryStockRepository.saveAndFlush(nurseryStock);

        // Get all the nurseryStockList where status is not null
        defaultNurseryStockShouldBeFound("status.specified=true");

        // Get all the nurseryStockList where status is null
        defaultNurseryStockShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllNurseryStocksByStatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        nurseryStockRepository.saveAndFlush(nurseryStock);

        // Get all the nurseryStockList where status greater than or equals to DEFAULT_STATUS
        defaultNurseryStockShouldBeFound("status.greaterOrEqualThan=" + DEFAULT_STATUS);

        // Get all the nurseryStockList where status greater than or equals to UPDATED_STATUS
        defaultNurseryStockShouldNotBeFound("status.greaterOrEqualThan=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllNurseryStocksByStatusIsLessThanSomething() throws Exception {
        // Initialize the database
        nurseryStockRepository.saveAndFlush(nurseryStock);

        // Get all the nurseryStockList where status less than or equals to DEFAULT_STATUS
        defaultNurseryStockShouldNotBeFound("status.lessThan=" + DEFAULT_STATUS);

        // Get all the nurseryStockList where status less than or equals to UPDATED_STATUS
        defaultNurseryStockShouldBeFound("status.lessThan=" + UPDATED_STATUS);
    }


    @Test
    @Transactional
    public void getAllNurseryStocksByPosQuantityIsEqualToSomething() throws Exception {
        // Initialize the database
        nurseryStockRepository.saveAndFlush(nurseryStock);

        // Get all the nurseryStockList where posQuantity equals to DEFAULT_POS_QUANTITY
        defaultNurseryStockShouldBeFound("posQuantity.equals=" + DEFAULT_POS_QUANTITY);

        // Get all the nurseryStockList where posQuantity equals to UPDATED_POS_QUANTITY
        defaultNurseryStockShouldNotBeFound("posQuantity.equals=" + UPDATED_POS_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllNurseryStocksByPosQuantityIsInShouldWork() throws Exception {
        // Initialize the database
        nurseryStockRepository.saveAndFlush(nurseryStock);

        // Get all the nurseryStockList where posQuantity in DEFAULT_POS_QUANTITY or UPDATED_POS_QUANTITY
        defaultNurseryStockShouldBeFound("posQuantity.in=" + DEFAULT_POS_QUANTITY + "," + UPDATED_POS_QUANTITY);

        // Get all the nurseryStockList where posQuantity equals to UPDATED_POS_QUANTITY
        defaultNurseryStockShouldNotBeFound("posQuantity.in=" + UPDATED_POS_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllNurseryStocksByPosQuantityIsNullOrNotNull() throws Exception {
        // Initialize the database
        nurseryStockRepository.saveAndFlush(nurseryStock);

        // Get all the nurseryStockList where posQuantity is not null
        defaultNurseryStockShouldBeFound("posQuantity.specified=true");

        // Get all the nurseryStockList where posQuantity is null
        defaultNurseryStockShouldNotBeFound("posQuantity.specified=false");
    }

    @Test
    @Transactional
    public void getAllNurseryStocksByPosQuantityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        nurseryStockRepository.saveAndFlush(nurseryStock);

        // Get all the nurseryStockList where posQuantity greater than or equals to DEFAULT_POS_QUANTITY
        defaultNurseryStockShouldBeFound("posQuantity.greaterOrEqualThan=" + DEFAULT_POS_QUANTITY);

        // Get all the nurseryStockList where posQuantity greater than or equals to UPDATED_POS_QUANTITY
        defaultNurseryStockShouldNotBeFound("posQuantity.greaterOrEqualThan=" + UPDATED_POS_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllNurseryStocksByPosQuantityIsLessThanSomething() throws Exception {
        // Initialize the database
        nurseryStockRepository.saveAndFlush(nurseryStock);

        // Get all the nurseryStockList where posQuantity less than or equals to DEFAULT_POS_QUANTITY
        defaultNurseryStockShouldNotBeFound("posQuantity.lessThan=" + DEFAULT_POS_QUANTITY);

        // Get all the nurseryStockList where posQuantity less than or equals to UPDATED_POS_QUANTITY
        defaultNurseryStockShouldBeFound("posQuantity.lessThan=" + UPDATED_POS_QUANTITY);
    }


    @Test
    @Transactional
    public void getAllNurseryStocksByDamageQuantityIsEqualToSomething() throws Exception {
        // Initialize the database
        nurseryStockRepository.saveAndFlush(nurseryStock);

        // Get all the nurseryStockList where damageQuantity equals to DEFAULT_DAMAGE_QUANTITY
        defaultNurseryStockShouldBeFound("damageQuantity.equals=" + DEFAULT_DAMAGE_QUANTITY);

        // Get all the nurseryStockList where damageQuantity equals to UPDATED_DAMAGE_QUANTITY
        defaultNurseryStockShouldNotBeFound("damageQuantity.equals=" + UPDATED_DAMAGE_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllNurseryStocksByDamageQuantityIsInShouldWork() throws Exception {
        // Initialize the database
        nurseryStockRepository.saveAndFlush(nurseryStock);

        // Get all the nurseryStockList where damageQuantity in DEFAULT_DAMAGE_QUANTITY or UPDATED_DAMAGE_QUANTITY
        defaultNurseryStockShouldBeFound("damageQuantity.in=" + DEFAULT_DAMAGE_QUANTITY + "," + UPDATED_DAMAGE_QUANTITY);

        // Get all the nurseryStockList where damageQuantity equals to UPDATED_DAMAGE_QUANTITY
        defaultNurseryStockShouldNotBeFound("damageQuantity.in=" + UPDATED_DAMAGE_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllNurseryStocksByDamageQuantityIsNullOrNotNull() throws Exception {
        // Initialize the database
        nurseryStockRepository.saveAndFlush(nurseryStock);

        // Get all the nurseryStockList where damageQuantity is not null
        defaultNurseryStockShouldBeFound("damageQuantity.specified=true");

        // Get all the nurseryStockList where damageQuantity is null
        defaultNurseryStockShouldNotBeFound("damageQuantity.specified=false");
    }

    @Test
    @Transactional
    public void getAllNurseryStocksByDamageQuantityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        nurseryStockRepository.saveAndFlush(nurseryStock);

        // Get all the nurseryStockList where damageQuantity greater than or equals to DEFAULT_DAMAGE_QUANTITY
        defaultNurseryStockShouldBeFound("damageQuantity.greaterOrEqualThan=" + DEFAULT_DAMAGE_QUANTITY);

        // Get all the nurseryStockList where damageQuantity greater than or equals to UPDATED_DAMAGE_QUANTITY
        defaultNurseryStockShouldNotBeFound("damageQuantity.greaterOrEqualThan=" + UPDATED_DAMAGE_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllNurseryStocksByDamageQuantityIsLessThanSomething() throws Exception {
        // Initialize the database
        nurseryStockRepository.saveAndFlush(nurseryStock);

        // Get all the nurseryStockList where damageQuantity less than or equals to DEFAULT_DAMAGE_QUANTITY
        defaultNurseryStockShouldNotBeFound("damageQuantity.lessThan=" + DEFAULT_DAMAGE_QUANTITY);

        // Get all the nurseryStockList where damageQuantity less than or equals to UPDATED_DAMAGE_QUANTITY
        defaultNurseryStockShouldBeFound("damageQuantity.lessThan=" + UPDATED_DAMAGE_QUANTITY);
    }


    @Test
    @Transactional
    public void getAllNurseryStocksByNurseryStockDetailsIsEqualToSomething() throws Exception {
        // Initialize the database
        NurseryStockDetails nurseryStockDetails = NurseryStockDetailsResourceIntTest.createEntity(em);
        em.persist(nurseryStockDetails);
        em.flush();
        nurseryStock.addNurseryStockDetails(nurseryStockDetails);
        nurseryStockRepository.saveAndFlush(nurseryStock);
        Long nurseryStockDetailsId = nurseryStockDetails.getId();

        // Get all the nurseryStockList where nurseryStockDetails equals to nurseryStockDetailsId
        defaultNurseryStockShouldBeFound("nurseryStockDetailsId.equals=" + nurseryStockDetailsId);

        // Get all the nurseryStockList where nurseryStockDetails equals to nurseryStockDetailsId + 1
        defaultNurseryStockShouldNotBeFound("nurseryStockDetailsId.equals=" + (nurseryStockDetailsId + 1));
    }


    @Test
    @Transactional
    public void getAllNurseryStocksByNurseryIsEqualToSomething() throws Exception {
        // Initialize the database
        Nursery nursery = NurseryResourceIntTest.createEntity(em);
        em.persist(nursery);
        em.flush();
        nurseryStock.setNursery(nursery);
        nurseryStockRepository.saveAndFlush(nurseryStock);
        Long nurseryId = nursery.getId();

        // Get all the nurseryStockList where nursery equals to nurseryId
        defaultNurseryStockShouldBeFound("nurseryId.equals=" + nurseryId);

        // Get all the nurseryStockList where nursery equals to nurseryId + 1
        defaultNurseryStockShouldNotBeFound("nurseryId.equals=" + (nurseryId + 1));
    }


    @Test
    @Transactional
    public void getAllNurseryStocksByPickListVarietyIsEqualToSomething() throws Exception {
        // Initialize the database
        PickListValue pickListVariety = PickListValueResourceIntTest.createEntity(em);
        em.persist(pickListVariety);
        em.flush();
        nurseryStock.setPickListVariety(pickListVariety);
        nurseryStockRepository.saveAndFlush(nurseryStock);
        Long pickListVarietyId = pickListVariety.getId();

        // Get all the nurseryStockList where pickListVariety equals to pickListVarietyId
        defaultNurseryStockShouldBeFound("pickListVarietyId.equals=" + pickListVarietyId);

        // Get all the nurseryStockList where pickListVariety equals to pickListVarietyId + 1
        defaultNurseryStockShouldNotBeFound("pickListVarietyId.equals=" + (pickListVarietyId + 1));
    }


    @Test
    @Transactional
    public void getAllNurseryStocksByPickListCategoryIsEqualToSomething() throws Exception {
        // Initialize the database
        PickListValue pickListCategory = PickListValueResourceIntTest.createEntity(em);
        em.persist(pickListCategory);
        em.flush();
        nurseryStock.setPickListCategory(pickListCategory);
        nurseryStockRepository.saveAndFlush(nurseryStock);
        Long pickListCategoryId = pickListCategory.getId();

        // Get all the nurseryStockList where pickListCategory equals to pickListCategoryId
        defaultNurseryStockShouldBeFound("pickListCategoryId.equals=" + pickListCategoryId);

        // Get all the nurseryStockList where pickListCategory equals to pickListCategoryId + 1
        defaultNurseryStockShouldNotBeFound("pickListCategoryId.equals=" + (pickListCategoryId + 1));
    }


    @Test
    @Transactional
    public void getAllNurseryStocksByFinancialYearNurseryStockIsEqualToSomething() throws Exception {
        // Initialize the database
        FinancialYearSettings financialYearNurseryStock = FinancialYearSettingsResourceIntTest.createEntity(em);
        em.persist(financialYearNurseryStock);
        em.flush();
        nurseryStock.setFinancialYearNurseryStock(financialYearNurseryStock);
        nurseryStockRepository.saveAndFlush(nurseryStock);
        Long financialYearNurseryStockId = financialYearNurseryStock.getId();

        // Get all the nurseryStockList where financialYearNurseryStock equals to financialYearNurseryStockId
        defaultNurseryStockShouldBeFound("financialYearNurseryStockId.equals=" + financialYearNurseryStockId);

        // Get all the nurseryStockList where financialYearNurseryStock equals to financialYearNurseryStockId + 1
        defaultNurseryStockShouldNotBeFound("financialYearNurseryStockId.equals=" + (financialYearNurseryStockId + 1));
    }


    @Test
    @Transactional
    public void getAllNurseryStocksByPointOfSaleDetailsIsEqualToSomething() throws Exception {
        // Initialize the database
        PointOfSaleDetails pointOfSaleDetails = PointOfSaleDetailsResourceIntTest.createEntity(em);
        em.persist(pointOfSaleDetails);
        em.flush();
        nurseryStock.addPointOfSaleDetails(pointOfSaleDetails);
        nurseryStockRepository.saveAndFlush(nurseryStock);
        Long pointOfSaleDetailsId = pointOfSaleDetails.getId();

        // Get all the nurseryStockList where pointOfSaleDetails equals to pointOfSaleDetailsId
        defaultNurseryStockShouldBeFound("pointOfSaleDetailsId.equals=" + pointOfSaleDetailsId);

        // Get all the nurseryStockList where pointOfSaleDetails equals to pointOfSaleDetailsId + 1
        defaultNurseryStockShouldNotBeFound("pointOfSaleDetailsId.equals=" + (pointOfSaleDetailsId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultNurseryStockShouldBeFound(String filter) throws Exception {
        restNurseryStockMockMvc.perform(get("/api/nursery-stocks?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nurseryStock.getId().intValue())))
            .andExpect(jsonPath("$.[*].currentQuantity").value(hasItem(DEFAULT_CURRENT_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].addedQuantity").value(hasItem(DEFAULT_ADDED_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].consumedQuantity").value(hasItem(DEFAULT_CONSUMED_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].posQuantity").value(hasItem(DEFAULT_POS_QUANTITY)))
            .andExpect(jsonPath("$.[*].damageQuantity").value(hasItem(DEFAULT_DAMAGE_QUANTITY.intValue())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultNurseryStockShouldNotBeFound(String filter) throws Exception {
        restNurseryStockMockMvc.perform(get("/api/nursery-stocks?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingNurseryStock() throws Exception {
        // Get the nurseryStock
        restNurseryStockMockMvc.perform(get("/api/nursery-stocks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNurseryStock() throws Exception {
        // Initialize the database
        nurseryStockRepository.saveAndFlush(nurseryStock);

        int databaseSizeBeforeUpdate = nurseryStockRepository.findAll().size();

        // Update the nurseryStock
        NurseryStock updatedNurseryStock = nurseryStockRepository.findById(nurseryStock.getId()).get();
        // Disconnect from session so that the updates on updatedNurseryStock are not directly saved in db
        em.detach(updatedNurseryStock);
        updatedNurseryStock
            .currentQuantity(UPDATED_CURRENT_QUANTITY)
            .addedQuantity(UPDATED_ADDED_QUANTITY)
            .consumedQuantity(UPDATED_CONSUMED_QUANTITY)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS)
            .posQuantity(UPDATED_POS_QUANTITY)
            .damageQuantity(UPDATED_DAMAGE_QUANTITY);
        NurseryStockDTO nurseryStockDTO = nurseryStockMapper.toDto(updatedNurseryStock);

        restNurseryStockMockMvc.perform(put("/api/nursery-stocks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryStockDTO)))
            .andExpect(status().isOk());

        // Validate the NurseryStock in the database
        List<NurseryStock> nurseryStockList = nurseryStockRepository.findAll();
        assertThat(nurseryStockList).hasSize(databaseSizeBeforeUpdate);
        NurseryStock testNurseryStock = nurseryStockList.get(nurseryStockList.size() - 1);
        assertThat(testNurseryStock.getCurrentQuantity()).isEqualTo(UPDATED_CURRENT_QUANTITY);
        assertThat(testNurseryStock.getAddedQuantity()).isEqualTo(UPDATED_ADDED_QUANTITY);
        assertThat(testNurseryStock.getConsumedQuantity()).isEqualTo(UPDATED_CONSUMED_QUANTITY);
        assertThat(testNurseryStock.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testNurseryStock.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testNurseryStock.getPosQuantity()).isEqualTo(UPDATED_POS_QUANTITY);
        assertThat(testNurseryStock.getDamageQuantity()).isEqualTo(UPDATED_DAMAGE_QUANTITY);
    }

    @Test
    @Transactional
    public void updateNonExistingNurseryStock() throws Exception {
        int databaseSizeBeforeUpdate = nurseryStockRepository.findAll().size();

        // Create the NurseryStock
        NurseryStockDTO nurseryStockDTO = nurseryStockMapper.toDto(nurseryStock);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restNurseryStockMockMvc.perform(put("/api/nursery-stocks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryStockDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NurseryStock in the database
        List<NurseryStock> nurseryStockList = nurseryStockRepository.findAll();
        assertThat(nurseryStockList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNurseryStock() throws Exception {
        // Initialize the database
        nurseryStockRepository.saveAndFlush(nurseryStock);

        int databaseSizeBeforeDelete = nurseryStockRepository.findAll().size();

        // Get the nurseryStock
        restNurseryStockMockMvc.perform(delete("/api/nursery-stocks/{id}", nurseryStock.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<NurseryStock> nurseryStockList = nurseryStockRepository.findAll();
        assertThat(nurseryStockList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NurseryStock.class);
        NurseryStock nurseryStock1 = new NurseryStock();
        nurseryStock1.setId(1L);
        NurseryStock nurseryStock2 = new NurseryStock();
        nurseryStock2.setId(nurseryStock1.getId());
        assertThat(nurseryStock1).isEqualTo(nurseryStock2);
        nurseryStock2.setId(2L);
        assertThat(nurseryStock1).isNotEqualTo(nurseryStock2);
        nurseryStock1.setId(null);
        assertThat(nurseryStock1).isNotEqualTo(nurseryStock2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NurseryStockDTO.class);
        NurseryStockDTO nurseryStockDTO1 = new NurseryStockDTO();
        nurseryStockDTO1.setId(1L);
        NurseryStockDTO nurseryStockDTO2 = new NurseryStockDTO();
        assertThat(nurseryStockDTO1).isNotEqualTo(nurseryStockDTO2);
        nurseryStockDTO2.setId(nurseryStockDTO1.getId());
        assertThat(nurseryStockDTO1).isEqualTo(nurseryStockDTO2);
        nurseryStockDTO2.setId(2L);
        assertThat(nurseryStockDTO1).isNotEqualTo(nurseryStockDTO2);
        nurseryStockDTO1.setId(null);
        assertThat(nurseryStockDTO1).isNotEqualTo(nurseryStockDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(nurseryStockMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(nurseryStockMapper.fromId(null)).isNull();
    }
}
