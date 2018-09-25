package com.niche.ng.web.rest;

import com.niche.ng.ProjectghApp;

import com.niche.ng.domain.NurseryInventory;
import com.niche.ng.domain.Nursery;
import com.niche.ng.domain.PickListValue;
import com.niche.ng.domain.PickListValue;
import com.niche.ng.domain.NurseryInventoryDetails;
import com.niche.ng.domain.PickListValue;
import com.niche.ng.repository.NurseryInventoryRepository;
import com.niche.ng.service.NurseryInventoryService;
import com.niche.ng.service.dto.NurseryInventoryDTO;
import com.niche.ng.service.mapper.NurseryInventoryMapper;
import com.niche.ng.web.rest.errors.ExceptionTranslator;
import com.niche.ng.service.dto.NurseryInventoryCriteria;
import com.niche.ng.service.NurseryInventoryQueryService;

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
 * Test class for the NurseryInventoryResource REST controller.
 *
 * @see NurseryInventoryResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectghApp.class)
public class NurseryInventoryResourceIntTest {

    private static final Integer DEFAULT_CURRENT_QUANTITY = 1;
    private static final Integer UPDATED_CURRENT_QUANTITY = 2;

    private static final Integer DEFAULT_ADDED_QUANTITY = 1;
    private static final Integer UPDATED_ADDED_QUANTITY = 2;

    private static final Integer DEFAULT_CONSUMED_QUANTITY = 1;
    private static final Integer UPDATED_CONSUMED_QUANTITY = 2;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final Integer DEFAULT_DAMAGE_QUANTITY = 1;
    private static final Integer UPDATED_DAMAGE_QUANTITY = 2;

    @Autowired
    private NurseryInventoryRepository nurseryInventoryRepository;


    @Autowired
    private NurseryInventoryMapper nurseryInventoryMapper;
    

    @Autowired
    private NurseryInventoryService nurseryInventoryService;

    @Autowired
    private NurseryInventoryQueryService nurseryInventoryQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restNurseryInventoryMockMvc;

    private NurseryInventory nurseryInventory;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NurseryInventoryResource nurseryInventoryResource = new NurseryInventoryResource(nurseryInventoryService, nurseryInventoryQueryService);
        this.restNurseryInventoryMockMvc = MockMvcBuilders.standaloneSetup(nurseryInventoryResource)
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
    public static NurseryInventory createEntity(EntityManager em) {
        NurseryInventory nurseryInventory = new NurseryInventory()
            .currentQuantity(DEFAULT_CURRENT_QUANTITY)
            .addedQuantity(DEFAULT_ADDED_QUANTITY)
            .consumedQuantity(DEFAULT_CONSUMED_QUANTITY)
            .description(DEFAULT_DESCRIPTION)
            .status(DEFAULT_STATUS)
            .damageQuantity(DEFAULT_DAMAGE_QUANTITY);
        return nurseryInventory;
    }

    @Before
    public void initTest() {
        nurseryInventory = createEntity(em);
    }

    @Test
    @Transactional
    public void createNurseryInventory() throws Exception {
        int databaseSizeBeforeCreate = nurseryInventoryRepository.findAll().size();

        // Create the NurseryInventory
        NurseryInventoryDTO nurseryInventoryDTO = nurseryInventoryMapper.toDto(nurseryInventory);
        restNurseryInventoryMockMvc.perform(post("/api/nursery-inventories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryInventoryDTO)))
            .andExpect(status().isCreated());

        // Validate the NurseryInventory in the database
        List<NurseryInventory> nurseryInventoryList = nurseryInventoryRepository.findAll();
        assertThat(nurseryInventoryList).hasSize(databaseSizeBeforeCreate + 1);
        NurseryInventory testNurseryInventory = nurseryInventoryList.get(nurseryInventoryList.size() - 1);
        assertThat(testNurseryInventory.getCurrentQuantity()).isEqualTo(DEFAULT_CURRENT_QUANTITY);
        assertThat(testNurseryInventory.getAddedQuantity()).isEqualTo(DEFAULT_ADDED_QUANTITY);
        assertThat(testNurseryInventory.getConsumedQuantity()).isEqualTo(DEFAULT_CONSUMED_QUANTITY);
        assertThat(testNurseryInventory.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testNurseryInventory.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testNurseryInventory.getDamageQuantity()).isEqualTo(DEFAULT_DAMAGE_QUANTITY);
    }

    @Test
    @Transactional
    public void createNurseryInventoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nurseryInventoryRepository.findAll().size();

        // Create the NurseryInventory with an existing ID
        nurseryInventory.setId(1L);
        NurseryInventoryDTO nurseryInventoryDTO = nurseryInventoryMapper.toDto(nurseryInventory);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNurseryInventoryMockMvc.perform(post("/api/nursery-inventories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryInventoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NurseryInventory in the database
        List<NurseryInventory> nurseryInventoryList = nurseryInventoryRepository.findAll();
        assertThat(nurseryInventoryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllNurseryInventories() throws Exception {
        // Initialize the database
        nurseryInventoryRepository.saveAndFlush(nurseryInventory);

        // Get all the nurseryInventoryList
        restNurseryInventoryMockMvc.perform(get("/api/nursery-inventories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nurseryInventory.getId().intValue())))
            .andExpect(jsonPath("$.[*].currentQuantity").value(hasItem(DEFAULT_CURRENT_QUANTITY)))
            .andExpect(jsonPath("$.[*].addedQuantity").value(hasItem(DEFAULT_ADDED_QUANTITY)))
            .andExpect(jsonPath("$.[*].consumedQuantity").value(hasItem(DEFAULT_CONSUMED_QUANTITY)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].damageQuantity").value(hasItem(DEFAULT_DAMAGE_QUANTITY)));
    }
    

    @Test
    @Transactional
    public void getNurseryInventory() throws Exception {
        // Initialize the database
        nurseryInventoryRepository.saveAndFlush(nurseryInventory);

        // Get the nurseryInventory
        restNurseryInventoryMockMvc.perform(get("/api/nursery-inventories/{id}", nurseryInventory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(nurseryInventory.getId().intValue()))
            .andExpect(jsonPath("$.currentQuantity").value(DEFAULT_CURRENT_QUANTITY))
            .andExpect(jsonPath("$.addedQuantity").value(DEFAULT_ADDED_QUANTITY))
            .andExpect(jsonPath("$.consumedQuantity").value(DEFAULT_CONSUMED_QUANTITY))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.damageQuantity").value(DEFAULT_DAMAGE_QUANTITY));
    }

    @Test
    @Transactional
    public void getAllNurseryInventoriesByCurrentQuantityIsEqualToSomething() throws Exception {
        // Initialize the database
        nurseryInventoryRepository.saveAndFlush(nurseryInventory);

        // Get all the nurseryInventoryList where currentQuantity equals to DEFAULT_CURRENT_QUANTITY
        defaultNurseryInventoryShouldBeFound("currentQuantity.equals=" + DEFAULT_CURRENT_QUANTITY);

        // Get all the nurseryInventoryList where currentQuantity equals to UPDATED_CURRENT_QUANTITY
        defaultNurseryInventoryShouldNotBeFound("currentQuantity.equals=" + UPDATED_CURRENT_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllNurseryInventoriesByCurrentQuantityIsInShouldWork() throws Exception {
        // Initialize the database
        nurseryInventoryRepository.saveAndFlush(nurseryInventory);

        // Get all the nurseryInventoryList where currentQuantity in DEFAULT_CURRENT_QUANTITY or UPDATED_CURRENT_QUANTITY
        defaultNurseryInventoryShouldBeFound("currentQuantity.in=" + DEFAULT_CURRENT_QUANTITY + "," + UPDATED_CURRENT_QUANTITY);

        // Get all the nurseryInventoryList where currentQuantity equals to UPDATED_CURRENT_QUANTITY
        defaultNurseryInventoryShouldNotBeFound("currentQuantity.in=" + UPDATED_CURRENT_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllNurseryInventoriesByCurrentQuantityIsNullOrNotNull() throws Exception {
        // Initialize the database
        nurseryInventoryRepository.saveAndFlush(nurseryInventory);

        // Get all the nurseryInventoryList where currentQuantity is not null
        defaultNurseryInventoryShouldBeFound("currentQuantity.specified=true");

        // Get all the nurseryInventoryList where currentQuantity is null
        defaultNurseryInventoryShouldNotBeFound("currentQuantity.specified=false");
    }

    @Test
    @Transactional
    public void getAllNurseryInventoriesByCurrentQuantityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        nurseryInventoryRepository.saveAndFlush(nurseryInventory);

        // Get all the nurseryInventoryList where currentQuantity greater than or equals to DEFAULT_CURRENT_QUANTITY
        defaultNurseryInventoryShouldBeFound("currentQuantity.greaterOrEqualThan=" + DEFAULT_CURRENT_QUANTITY);

        // Get all the nurseryInventoryList where currentQuantity greater than or equals to UPDATED_CURRENT_QUANTITY
        defaultNurseryInventoryShouldNotBeFound("currentQuantity.greaterOrEqualThan=" + UPDATED_CURRENT_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllNurseryInventoriesByCurrentQuantityIsLessThanSomething() throws Exception {
        // Initialize the database
        nurseryInventoryRepository.saveAndFlush(nurseryInventory);

        // Get all the nurseryInventoryList where currentQuantity less than or equals to DEFAULT_CURRENT_QUANTITY
        defaultNurseryInventoryShouldNotBeFound("currentQuantity.lessThan=" + DEFAULT_CURRENT_QUANTITY);

        // Get all the nurseryInventoryList where currentQuantity less than or equals to UPDATED_CURRENT_QUANTITY
        defaultNurseryInventoryShouldBeFound("currentQuantity.lessThan=" + UPDATED_CURRENT_QUANTITY);
    }


    @Test
    @Transactional
    public void getAllNurseryInventoriesByAddedQuantityIsEqualToSomething() throws Exception {
        // Initialize the database
        nurseryInventoryRepository.saveAndFlush(nurseryInventory);

        // Get all the nurseryInventoryList where addedQuantity equals to DEFAULT_ADDED_QUANTITY
        defaultNurseryInventoryShouldBeFound("addedQuantity.equals=" + DEFAULT_ADDED_QUANTITY);

        // Get all the nurseryInventoryList where addedQuantity equals to UPDATED_ADDED_QUANTITY
        defaultNurseryInventoryShouldNotBeFound("addedQuantity.equals=" + UPDATED_ADDED_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllNurseryInventoriesByAddedQuantityIsInShouldWork() throws Exception {
        // Initialize the database
        nurseryInventoryRepository.saveAndFlush(nurseryInventory);

        // Get all the nurseryInventoryList where addedQuantity in DEFAULT_ADDED_QUANTITY or UPDATED_ADDED_QUANTITY
        defaultNurseryInventoryShouldBeFound("addedQuantity.in=" + DEFAULT_ADDED_QUANTITY + "," + UPDATED_ADDED_QUANTITY);

        // Get all the nurseryInventoryList where addedQuantity equals to UPDATED_ADDED_QUANTITY
        defaultNurseryInventoryShouldNotBeFound("addedQuantity.in=" + UPDATED_ADDED_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllNurseryInventoriesByAddedQuantityIsNullOrNotNull() throws Exception {
        // Initialize the database
        nurseryInventoryRepository.saveAndFlush(nurseryInventory);

        // Get all the nurseryInventoryList where addedQuantity is not null
        defaultNurseryInventoryShouldBeFound("addedQuantity.specified=true");

        // Get all the nurseryInventoryList where addedQuantity is null
        defaultNurseryInventoryShouldNotBeFound("addedQuantity.specified=false");
    }

    @Test
    @Transactional
    public void getAllNurseryInventoriesByAddedQuantityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        nurseryInventoryRepository.saveAndFlush(nurseryInventory);

        // Get all the nurseryInventoryList where addedQuantity greater than or equals to DEFAULT_ADDED_QUANTITY
        defaultNurseryInventoryShouldBeFound("addedQuantity.greaterOrEqualThan=" + DEFAULT_ADDED_QUANTITY);

        // Get all the nurseryInventoryList where addedQuantity greater than or equals to UPDATED_ADDED_QUANTITY
        defaultNurseryInventoryShouldNotBeFound("addedQuantity.greaterOrEqualThan=" + UPDATED_ADDED_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllNurseryInventoriesByAddedQuantityIsLessThanSomething() throws Exception {
        // Initialize the database
        nurseryInventoryRepository.saveAndFlush(nurseryInventory);

        // Get all the nurseryInventoryList where addedQuantity less than or equals to DEFAULT_ADDED_QUANTITY
        defaultNurseryInventoryShouldNotBeFound("addedQuantity.lessThan=" + DEFAULT_ADDED_QUANTITY);

        // Get all the nurseryInventoryList where addedQuantity less than or equals to UPDATED_ADDED_QUANTITY
        defaultNurseryInventoryShouldBeFound("addedQuantity.lessThan=" + UPDATED_ADDED_QUANTITY);
    }


    @Test
    @Transactional
    public void getAllNurseryInventoriesByConsumedQuantityIsEqualToSomething() throws Exception {
        // Initialize the database
        nurseryInventoryRepository.saveAndFlush(nurseryInventory);

        // Get all the nurseryInventoryList where consumedQuantity equals to DEFAULT_CONSUMED_QUANTITY
        defaultNurseryInventoryShouldBeFound("consumedQuantity.equals=" + DEFAULT_CONSUMED_QUANTITY);

        // Get all the nurseryInventoryList where consumedQuantity equals to UPDATED_CONSUMED_QUANTITY
        defaultNurseryInventoryShouldNotBeFound("consumedQuantity.equals=" + UPDATED_CONSUMED_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllNurseryInventoriesByConsumedQuantityIsInShouldWork() throws Exception {
        // Initialize the database
        nurseryInventoryRepository.saveAndFlush(nurseryInventory);

        // Get all the nurseryInventoryList where consumedQuantity in DEFAULT_CONSUMED_QUANTITY or UPDATED_CONSUMED_QUANTITY
        defaultNurseryInventoryShouldBeFound("consumedQuantity.in=" + DEFAULT_CONSUMED_QUANTITY + "," + UPDATED_CONSUMED_QUANTITY);

        // Get all the nurseryInventoryList where consumedQuantity equals to UPDATED_CONSUMED_QUANTITY
        defaultNurseryInventoryShouldNotBeFound("consumedQuantity.in=" + UPDATED_CONSUMED_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllNurseryInventoriesByConsumedQuantityIsNullOrNotNull() throws Exception {
        // Initialize the database
        nurseryInventoryRepository.saveAndFlush(nurseryInventory);

        // Get all the nurseryInventoryList where consumedQuantity is not null
        defaultNurseryInventoryShouldBeFound("consumedQuantity.specified=true");

        // Get all the nurseryInventoryList where consumedQuantity is null
        defaultNurseryInventoryShouldNotBeFound("consumedQuantity.specified=false");
    }

    @Test
    @Transactional
    public void getAllNurseryInventoriesByConsumedQuantityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        nurseryInventoryRepository.saveAndFlush(nurseryInventory);

        // Get all the nurseryInventoryList where consumedQuantity greater than or equals to DEFAULT_CONSUMED_QUANTITY
        defaultNurseryInventoryShouldBeFound("consumedQuantity.greaterOrEqualThan=" + DEFAULT_CONSUMED_QUANTITY);

        // Get all the nurseryInventoryList where consumedQuantity greater than or equals to UPDATED_CONSUMED_QUANTITY
        defaultNurseryInventoryShouldNotBeFound("consumedQuantity.greaterOrEqualThan=" + UPDATED_CONSUMED_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllNurseryInventoriesByConsumedQuantityIsLessThanSomething() throws Exception {
        // Initialize the database
        nurseryInventoryRepository.saveAndFlush(nurseryInventory);

        // Get all the nurseryInventoryList where consumedQuantity less than or equals to DEFAULT_CONSUMED_QUANTITY
        defaultNurseryInventoryShouldNotBeFound("consumedQuantity.lessThan=" + DEFAULT_CONSUMED_QUANTITY);

        // Get all the nurseryInventoryList where consumedQuantity less than or equals to UPDATED_CONSUMED_QUANTITY
        defaultNurseryInventoryShouldBeFound("consumedQuantity.lessThan=" + UPDATED_CONSUMED_QUANTITY);
    }


    @Test
    @Transactional
    public void getAllNurseryInventoriesByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        nurseryInventoryRepository.saveAndFlush(nurseryInventory);

        // Get all the nurseryInventoryList where description equals to DEFAULT_DESCRIPTION
        defaultNurseryInventoryShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the nurseryInventoryList where description equals to UPDATED_DESCRIPTION
        defaultNurseryInventoryShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllNurseryInventoriesByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        nurseryInventoryRepository.saveAndFlush(nurseryInventory);

        // Get all the nurseryInventoryList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultNurseryInventoryShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the nurseryInventoryList where description equals to UPDATED_DESCRIPTION
        defaultNurseryInventoryShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllNurseryInventoriesByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        nurseryInventoryRepository.saveAndFlush(nurseryInventory);

        // Get all the nurseryInventoryList where description is not null
        defaultNurseryInventoryShouldBeFound("description.specified=true");

        // Get all the nurseryInventoryList where description is null
        defaultNurseryInventoryShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllNurseryInventoriesByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        nurseryInventoryRepository.saveAndFlush(nurseryInventory);

        // Get all the nurseryInventoryList where status equals to DEFAULT_STATUS
        defaultNurseryInventoryShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the nurseryInventoryList where status equals to UPDATED_STATUS
        defaultNurseryInventoryShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllNurseryInventoriesByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        nurseryInventoryRepository.saveAndFlush(nurseryInventory);

        // Get all the nurseryInventoryList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultNurseryInventoryShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the nurseryInventoryList where status equals to UPDATED_STATUS
        defaultNurseryInventoryShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllNurseryInventoriesByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        nurseryInventoryRepository.saveAndFlush(nurseryInventory);

        // Get all the nurseryInventoryList where status is not null
        defaultNurseryInventoryShouldBeFound("status.specified=true");

        // Get all the nurseryInventoryList where status is null
        defaultNurseryInventoryShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllNurseryInventoriesByStatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        nurseryInventoryRepository.saveAndFlush(nurseryInventory);

        // Get all the nurseryInventoryList where status greater than or equals to DEFAULT_STATUS
        defaultNurseryInventoryShouldBeFound("status.greaterOrEqualThan=" + DEFAULT_STATUS);

        // Get all the nurseryInventoryList where status greater than or equals to UPDATED_STATUS
        defaultNurseryInventoryShouldNotBeFound("status.greaterOrEqualThan=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllNurseryInventoriesByStatusIsLessThanSomething() throws Exception {
        // Initialize the database
        nurseryInventoryRepository.saveAndFlush(nurseryInventory);

        // Get all the nurseryInventoryList where status less than or equals to DEFAULT_STATUS
        defaultNurseryInventoryShouldNotBeFound("status.lessThan=" + DEFAULT_STATUS);

        // Get all the nurseryInventoryList where status less than or equals to UPDATED_STATUS
        defaultNurseryInventoryShouldBeFound("status.lessThan=" + UPDATED_STATUS);
    }


    @Test
    @Transactional
    public void getAllNurseryInventoriesByDamageQuantityIsEqualToSomething() throws Exception {
        // Initialize the database
        nurseryInventoryRepository.saveAndFlush(nurseryInventory);

        // Get all the nurseryInventoryList where damageQuantity equals to DEFAULT_DAMAGE_QUANTITY
        defaultNurseryInventoryShouldBeFound("damageQuantity.equals=" + DEFAULT_DAMAGE_QUANTITY);

        // Get all the nurseryInventoryList where damageQuantity equals to UPDATED_DAMAGE_QUANTITY
        defaultNurseryInventoryShouldNotBeFound("damageQuantity.equals=" + UPDATED_DAMAGE_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllNurseryInventoriesByDamageQuantityIsInShouldWork() throws Exception {
        // Initialize the database
        nurseryInventoryRepository.saveAndFlush(nurseryInventory);

        // Get all the nurseryInventoryList where damageQuantity in DEFAULT_DAMAGE_QUANTITY or UPDATED_DAMAGE_QUANTITY
        defaultNurseryInventoryShouldBeFound("damageQuantity.in=" + DEFAULT_DAMAGE_QUANTITY + "," + UPDATED_DAMAGE_QUANTITY);

        // Get all the nurseryInventoryList where damageQuantity equals to UPDATED_DAMAGE_QUANTITY
        defaultNurseryInventoryShouldNotBeFound("damageQuantity.in=" + UPDATED_DAMAGE_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllNurseryInventoriesByDamageQuantityIsNullOrNotNull() throws Exception {
        // Initialize the database
        nurseryInventoryRepository.saveAndFlush(nurseryInventory);

        // Get all the nurseryInventoryList where damageQuantity is not null
        defaultNurseryInventoryShouldBeFound("damageQuantity.specified=true");

        // Get all the nurseryInventoryList where damageQuantity is null
        defaultNurseryInventoryShouldNotBeFound("damageQuantity.specified=false");
    }

    @Test
    @Transactional
    public void getAllNurseryInventoriesByDamageQuantityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        nurseryInventoryRepository.saveAndFlush(nurseryInventory);

        // Get all the nurseryInventoryList where damageQuantity greater than or equals to DEFAULT_DAMAGE_QUANTITY
        defaultNurseryInventoryShouldBeFound("damageQuantity.greaterOrEqualThan=" + DEFAULT_DAMAGE_QUANTITY);

        // Get all the nurseryInventoryList where damageQuantity greater than or equals to UPDATED_DAMAGE_QUANTITY
        defaultNurseryInventoryShouldNotBeFound("damageQuantity.greaterOrEqualThan=" + UPDATED_DAMAGE_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllNurseryInventoriesByDamageQuantityIsLessThanSomething() throws Exception {
        // Initialize the database
        nurseryInventoryRepository.saveAndFlush(nurseryInventory);

        // Get all the nurseryInventoryList where damageQuantity less than or equals to DEFAULT_DAMAGE_QUANTITY
        defaultNurseryInventoryShouldNotBeFound("damageQuantity.lessThan=" + DEFAULT_DAMAGE_QUANTITY);

        // Get all the nurseryInventoryList where damageQuantity less than or equals to UPDATED_DAMAGE_QUANTITY
        defaultNurseryInventoryShouldBeFound("damageQuantity.lessThan=" + UPDATED_DAMAGE_QUANTITY);
    }


    @Test
    @Transactional
    public void getAllNurseryInventoriesByNurserysIsEqualToSomething() throws Exception {
        // Initialize the database
        Nursery nurserys = NurseryResourceIntTest.createEntity(em);
        em.persist(nurserys);
        em.flush();
        nurseryInventory.setNurserys(nurserys);
        nurseryInventoryRepository.saveAndFlush(nurseryInventory);
        Long nurserysId = nurserys.getId();

        // Get all the nurseryInventoryList where nurserys equals to nurserysId
        defaultNurseryInventoryShouldBeFound("nurserysId.equals=" + nurserysId);

        // Get all the nurseryInventoryList where nurserys equals to nurserysId + 1
        defaultNurseryInventoryShouldNotBeFound("nurserysId.equals=" + (nurserysId + 1));
    }


    @Test
    @Transactional
    public void getAllNurseryInventoriesByPickListVarietyIsEqualToSomething() throws Exception {
        // Initialize the database
        PickListValue pickListVariety = PickListValueResourceIntTest.createEntity(em);
        em.persist(pickListVariety);
        em.flush();
        nurseryInventory.setPickListVariety(pickListVariety);
        nurseryInventoryRepository.saveAndFlush(nurseryInventory);
        Long pickListVarietyId = pickListVariety.getId();

        // Get all the nurseryInventoryList where pickListVariety equals to pickListVarietyId
        defaultNurseryInventoryShouldBeFound("pickListVarietyId.equals=" + pickListVarietyId);

        // Get all the nurseryInventoryList where pickListVariety equals to pickListVarietyId + 1
        defaultNurseryInventoryShouldNotBeFound("pickListVarietyId.equals=" + (pickListVarietyId + 1));
    }


    @Test
    @Transactional
    public void getAllNurseryInventoriesByPickListCategoryIsEqualToSomething() throws Exception {
        // Initialize the database
        PickListValue pickListCategory = PickListValueResourceIntTest.createEntity(em);
        em.persist(pickListCategory);
        em.flush();
        nurseryInventory.setPickListCategory(pickListCategory);
        nurseryInventoryRepository.saveAndFlush(nurseryInventory);
        Long pickListCategoryId = pickListCategory.getId();

        // Get all the nurseryInventoryList where pickListCategory equals to pickListCategoryId
        defaultNurseryInventoryShouldBeFound("pickListCategoryId.equals=" + pickListCategoryId);

        // Get all the nurseryInventoryList where pickListCategory equals to pickListCategoryId + 1
        defaultNurseryInventoryShouldNotBeFound("pickListCategoryId.equals=" + (pickListCategoryId + 1));
    }


    @Test
    @Transactional
    public void getAllNurseryInventoriesByNurseryInventoryDetailsIsEqualToSomething() throws Exception {
        // Initialize the database
        NurseryInventoryDetails nurseryInventoryDetails = NurseryInventoryDetailsResourceIntTest.createEntity(em);
        em.persist(nurseryInventoryDetails);
        em.flush();
        nurseryInventory.addNurseryInventoryDetails(nurseryInventoryDetails);
        nurseryInventoryRepository.saveAndFlush(nurseryInventory);
        Long nurseryInventoryDetailsId = nurseryInventoryDetails.getId();

        // Get all the nurseryInventoryList where nurseryInventoryDetails equals to nurseryInventoryDetailsId
        defaultNurseryInventoryShouldBeFound("nurseryInventoryDetailsId.equals=" + nurseryInventoryDetailsId);

        // Get all the nurseryInventoryList where nurseryInventoryDetails equals to nurseryInventoryDetailsId + 1
        defaultNurseryInventoryShouldNotBeFound("nurseryInventoryDetailsId.equals=" + (nurseryInventoryDetailsId + 1));
    }


    @Test
    @Transactional
    public void getAllNurseryInventoriesByQuantityTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        PickListValue quantityType = PickListValueResourceIntTest.createEntity(em);
        em.persist(quantityType);
        em.flush();
        nurseryInventory.setQuantityType(quantityType);
        nurseryInventoryRepository.saveAndFlush(nurseryInventory);
        Long quantityTypeId = quantityType.getId();

        // Get all the nurseryInventoryList where quantityType equals to quantityTypeId
        defaultNurseryInventoryShouldBeFound("quantityTypeId.equals=" + quantityTypeId);

        // Get all the nurseryInventoryList where quantityType equals to quantityTypeId + 1
        defaultNurseryInventoryShouldNotBeFound("quantityTypeId.equals=" + (quantityTypeId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultNurseryInventoryShouldBeFound(String filter) throws Exception {
        restNurseryInventoryMockMvc.perform(get("/api/nursery-inventories?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nurseryInventory.getId().intValue())))
            .andExpect(jsonPath("$.[*].currentQuantity").value(hasItem(DEFAULT_CURRENT_QUANTITY)))
            .andExpect(jsonPath("$.[*].addedQuantity").value(hasItem(DEFAULT_ADDED_QUANTITY)))
            .andExpect(jsonPath("$.[*].consumedQuantity").value(hasItem(DEFAULT_CONSUMED_QUANTITY)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].damageQuantity").value(hasItem(DEFAULT_DAMAGE_QUANTITY)));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultNurseryInventoryShouldNotBeFound(String filter) throws Exception {
        restNurseryInventoryMockMvc.perform(get("/api/nursery-inventories?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingNurseryInventory() throws Exception {
        // Get the nurseryInventory
        restNurseryInventoryMockMvc.perform(get("/api/nursery-inventories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNurseryInventory() throws Exception {
        // Initialize the database
        nurseryInventoryRepository.saveAndFlush(nurseryInventory);

        int databaseSizeBeforeUpdate = nurseryInventoryRepository.findAll().size();

        // Update the nurseryInventory
        NurseryInventory updatedNurseryInventory = nurseryInventoryRepository.findById(nurseryInventory.getId()).get();
        // Disconnect from session so that the updates on updatedNurseryInventory are not directly saved in db
        em.detach(updatedNurseryInventory);
        updatedNurseryInventory
            .currentQuantity(UPDATED_CURRENT_QUANTITY)
            .addedQuantity(UPDATED_ADDED_QUANTITY)
            .consumedQuantity(UPDATED_CONSUMED_QUANTITY)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS)
            .damageQuantity(UPDATED_DAMAGE_QUANTITY);
        NurseryInventoryDTO nurseryInventoryDTO = nurseryInventoryMapper.toDto(updatedNurseryInventory);

        restNurseryInventoryMockMvc.perform(put("/api/nursery-inventories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryInventoryDTO)))
            .andExpect(status().isOk());

        // Validate the NurseryInventory in the database
        List<NurseryInventory> nurseryInventoryList = nurseryInventoryRepository.findAll();
        assertThat(nurseryInventoryList).hasSize(databaseSizeBeforeUpdate);
        NurseryInventory testNurseryInventory = nurseryInventoryList.get(nurseryInventoryList.size() - 1);
        assertThat(testNurseryInventory.getCurrentQuantity()).isEqualTo(UPDATED_CURRENT_QUANTITY);
        assertThat(testNurseryInventory.getAddedQuantity()).isEqualTo(UPDATED_ADDED_QUANTITY);
        assertThat(testNurseryInventory.getConsumedQuantity()).isEqualTo(UPDATED_CONSUMED_QUANTITY);
        assertThat(testNurseryInventory.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testNurseryInventory.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testNurseryInventory.getDamageQuantity()).isEqualTo(UPDATED_DAMAGE_QUANTITY);
    }

    @Test
    @Transactional
    public void updateNonExistingNurseryInventory() throws Exception {
        int databaseSizeBeforeUpdate = nurseryInventoryRepository.findAll().size();

        // Create the NurseryInventory
        NurseryInventoryDTO nurseryInventoryDTO = nurseryInventoryMapper.toDto(nurseryInventory);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restNurseryInventoryMockMvc.perform(put("/api/nursery-inventories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryInventoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NurseryInventory in the database
        List<NurseryInventory> nurseryInventoryList = nurseryInventoryRepository.findAll();
        assertThat(nurseryInventoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNurseryInventory() throws Exception {
        // Initialize the database
        nurseryInventoryRepository.saveAndFlush(nurseryInventory);

        int databaseSizeBeforeDelete = nurseryInventoryRepository.findAll().size();

        // Get the nurseryInventory
        restNurseryInventoryMockMvc.perform(delete("/api/nursery-inventories/{id}", nurseryInventory.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<NurseryInventory> nurseryInventoryList = nurseryInventoryRepository.findAll();
        assertThat(nurseryInventoryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NurseryInventory.class);
        NurseryInventory nurseryInventory1 = new NurseryInventory();
        nurseryInventory1.setId(1L);
        NurseryInventory nurseryInventory2 = new NurseryInventory();
        nurseryInventory2.setId(nurseryInventory1.getId());
        assertThat(nurseryInventory1).isEqualTo(nurseryInventory2);
        nurseryInventory2.setId(2L);
        assertThat(nurseryInventory1).isNotEqualTo(nurseryInventory2);
        nurseryInventory1.setId(null);
        assertThat(nurseryInventory1).isNotEqualTo(nurseryInventory2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NurseryInventoryDTO.class);
        NurseryInventoryDTO nurseryInventoryDTO1 = new NurseryInventoryDTO();
        nurseryInventoryDTO1.setId(1L);
        NurseryInventoryDTO nurseryInventoryDTO2 = new NurseryInventoryDTO();
        assertThat(nurseryInventoryDTO1).isNotEqualTo(nurseryInventoryDTO2);
        nurseryInventoryDTO2.setId(nurseryInventoryDTO1.getId());
        assertThat(nurseryInventoryDTO1).isEqualTo(nurseryInventoryDTO2);
        nurseryInventoryDTO2.setId(2L);
        assertThat(nurseryInventoryDTO1).isNotEqualTo(nurseryInventoryDTO2);
        nurseryInventoryDTO1.setId(null);
        assertThat(nurseryInventoryDTO1).isNotEqualTo(nurseryInventoryDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(nurseryInventoryMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(nurseryInventoryMapper.fromId(null)).isNull();
    }
}
