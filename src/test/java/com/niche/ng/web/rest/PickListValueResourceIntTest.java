package com.niche.ng.web.rest;

import com.niche.ng.ProjectghApp;

import com.niche.ng.domain.PickListValue;
import com.niche.ng.domain.PickListValue;
import com.niche.ng.domain.Batch;
import com.niche.ng.domain.Batch;
import com.niche.ng.domain.NurseryStock;
import com.niche.ng.domain.NurseryStock;
import com.niche.ng.domain.GodownPurchaseDetails;
import com.niche.ng.domain.GodownPurchaseDetails;
import com.niche.ng.domain.GodownPurchaseDetails;
import com.niche.ng.domain.GodownStock;
import com.niche.ng.domain.GodownStock;
import com.niche.ng.domain.GodownStock;
import com.niche.ng.domain.PickList;
import com.niche.ng.domain.PickListValue;
import com.niche.ng.domain.Nursery;
import com.niche.ng.domain.Batch;
import com.niche.ng.domain.NurseryInventory;
import com.niche.ng.domain.NurseryInventory;
import com.niche.ng.domain.NurseryInventory;
import com.niche.ng.domain.NurseryInventoryDetails;
import com.niche.ng.domain.Damage;
import com.niche.ng.domain.NurseryStockDetails;
import com.niche.ng.domain.FinancialYearSettings;
import com.niche.ng.domain.Damage;
import com.niche.ng.domain.PointOfSaleDetails;
import com.niche.ng.domain.PointOfSaleDetails;
import com.niche.ng.domain.CoverFillingDetails;
import com.niche.ng.domain.NurseryInventoryDetails;
import com.niche.ng.domain.CoverFillingDetails;
import com.niche.ng.repository.PickListValueRepository;
import com.niche.ng.service.PickListValueService;
import com.niche.ng.service.dto.PickListValueDTO;
import com.niche.ng.service.mapper.PickListValueMapper;
import com.niche.ng.web.rest.errors.ExceptionTranslator;
import com.niche.ng.service.dto.PickListValueCriteria;
import com.niche.ng.service.PickListValueQueryService;

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
 * Test class for the PickListValueResource REST controller.
 *
 * @see PickListValueResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectghApp.class)
public class PickListValueResourceIntTest {

    private static final String DEFAULT_PICK_LIST_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_PICK_LIST_VALUE = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    @Autowired
    private PickListValueRepository pickListValueRepository;


    @Autowired
    private PickListValueMapper pickListValueMapper;
    

    @Autowired
    private PickListValueService pickListValueService;

    @Autowired
    private PickListValueQueryService pickListValueQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPickListValueMockMvc;

    private PickListValue pickListValue;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PickListValueResource pickListValueResource = new PickListValueResource(pickListValueService, pickListValueQueryService);
        this.restPickListValueMockMvc = MockMvcBuilders.standaloneSetup(pickListValueResource)
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
    public static PickListValue createEntity(EntityManager em) {
        PickListValue pickListValue = new PickListValue()
            .pickListValue(DEFAULT_PICK_LIST_VALUE)
            .status(DEFAULT_STATUS);
        return pickListValue;
    }

    @Before
    public void initTest() {
        pickListValue = createEntity(em);
    }

    @Test
    @Transactional
    public void createPickListValue() throws Exception {
        int databaseSizeBeforeCreate = pickListValueRepository.findAll().size();

        // Create the PickListValue
        PickListValueDTO pickListValueDTO = pickListValueMapper.toDto(pickListValue);
        restPickListValueMockMvc.perform(post("/api/pick-list-values")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pickListValueDTO)))
            .andExpect(status().isCreated());

        // Validate the PickListValue in the database
        List<PickListValue> pickListValueList = pickListValueRepository.findAll();
        assertThat(pickListValueList).hasSize(databaseSizeBeforeCreate + 1);
        PickListValue testPickListValue = pickListValueList.get(pickListValueList.size() - 1);
        assertThat(testPickListValue.getPickListValue()).isEqualTo(DEFAULT_PICK_LIST_VALUE);
        assertThat(testPickListValue.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createPickListValueWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pickListValueRepository.findAll().size();

        // Create the PickListValue with an existing ID
        pickListValue.setId(1L);
        PickListValueDTO pickListValueDTO = pickListValueMapper.toDto(pickListValue);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPickListValueMockMvc.perform(post("/api/pick-list-values")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pickListValueDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PickListValue in the database
        List<PickListValue> pickListValueList = pickListValueRepository.findAll();
        assertThat(pickListValueList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkPickListValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = pickListValueRepository.findAll().size();
        // set the field null
        pickListValue.setPickListValue(null);

        // Create the PickListValue, which fails.
        PickListValueDTO pickListValueDTO = pickListValueMapper.toDto(pickListValue);

        restPickListValueMockMvc.perform(post("/api/pick-list-values")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pickListValueDTO)))
            .andExpect(status().isBadRequest());

        List<PickListValue> pickListValueList = pickListValueRepository.findAll();
        assertThat(pickListValueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPickListValues() throws Exception {
        // Initialize the database
        pickListValueRepository.saveAndFlush(pickListValue);

        // Get all the pickListValueList
        restPickListValueMockMvc.perform(get("/api/pick-list-values?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pickListValue.getId().intValue())))
            .andExpect(jsonPath("$.[*].pickListValue").value(hasItem(DEFAULT_PICK_LIST_VALUE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    

    @Test
    @Transactional
    public void getPickListValue() throws Exception {
        // Initialize the database
        pickListValueRepository.saveAndFlush(pickListValue);

        // Get the pickListValue
        restPickListValueMockMvc.perform(get("/api/pick-list-values/{id}", pickListValue.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pickListValue.getId().intValue()))
            .andExpect(jsonPath("$.pickListValue").value(DEFAULT_PICK_LIST_VALUE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getAllPickListValuesByPickListValueIsEqualToSomething() throws Exception {
        // Initialize the database
        pickListValueRepository.saveAndFlush(pickListValue);

        // Get all the pickListValueList where pickListValue equals to DEFAULT_PICK_LIST_VALUE
        defaultPickListValueShouldBeFound("pickListValue.equals=" + DEFAULT_PICK_LIST_VALUE);

        // Get all the pickListValueList where pickListValue equals to UPDATED_PICK_LIST_VALUE
        defaultPickListValueShouldNotBeFound("pickListValue.equals=" + UPDATED_PICK_LIST_VALUE);
    }

    @Test
    @Transactional
    public void getAllPickListValuesByPickListValueIsInShouldWork() throws Exception {
        // Initialize the database
        pickListValueRepository.saveAndFlush(pickListValue);

        // Get all the pickListValueList where pickListValue in DEFAULT_PICK_LIST_VALUE or UPDATED_PICK_LIST_VALUE
        defaultPickListValueShouldBeFound("pickListValue.in=" + DEFAULT_PICK_LIST_VALUE + "," + UPDATED_PICK_LIST_VALUE);

        // Get all the pickListValueList where pickListValue equals to UPDATED_PICK_LIST_VALUE
        defaultPickListValueShouldNotBeFound("pickListValue.in=" + UPDATED_PICK_LIST_VALUE);
    }

    @Test
    @Transactional
    public void getAllPickListValuesByPickListValueIsNullOrNotNull() throws Exception {
        // Initialize the database
        pickListValueRepository.saveAndFlush(pickListValue);

        // Get all the pickListValueList where pickListValue is not null
        defaultPickListValueShouldBeFound("pickListValue.specified=true");

        // Get all the pickListValueList where pickListValue is null
        defaultPickListValueShouldNotBeFound("pickListValue.specified=false");
    }

    @Test
    @Transactional
    public void getAllPickListValuesByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        pickListValueRepository.saveAndFlush(pickListValue);

        // Get all the pickListValueList where status equals to DEFAULT_STATUS
        defaultPickListValueShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the pickListValueList where status equals to UPDATED_STATUS
        defaultPickListValueShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllPickListValuesByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        pickListValueRepository.saveAndFlush(pickListValue);

        // Get all the pickListValueList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultPickListValueShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the pickListValueList where status equals to UPDATED_STATUS
        defaultPickListValueShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllPickListValuesByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        pickListValueRepository.saveAndFlush(pickListValue);

        // Get all the pickListValueList where status is not null
        defaultPickListValueShouldBeFound("status.specified=true");

        // Get all the pickListValueList where status is null
        defaultPickListValueShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllPickListValuesByStatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pickListValueRepository.saveAndFlush(pickListValue);

        // Get all the pickListValueList where status greater than or equals to DEFAULT_STATUS
        defaultPickListValueShouldBeFound("status.greaterOrEqualThan=" + DEFAULT_STATUS);

        // Get all the pickListValueList where status greater than or equals to UPDATED_STATUS
        defaultPickListValueShouldNotBeFound("status.greaterOrEqualThan=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllPickListValuesByStatusIsLessThanSomething() throws Exception {
        // Initialize the database
        pickListValueRepository.saveAndFlush(pickListValue);

        // Get all the pickListValueList where status less than or equals to DEFAULT_STATUS
        defaultPickListValueShouldNotBeFound("status.lessThan=" + DEFAULT_STATUS);

        // Get all the pickListValueList where status less than or equals to UPDATED_STATUS
        defaultPickListValueShouldBeFound("status.lessThan=" + UPDATED_STATUS);
    }


    @Test
    @Transactional
    public void getAllPickListValuesBySelfIdsIsEqualToSomething() throws Exception {
        // Initialize the database
        PickListValue selfIds = PickListValueResourceIntTest.createEntity(em);
        em.persist(selfIds);
        em.flush();
        pickListValue.addSelfIds(selfIds);
        pickListValueRepository.saveAndFlush(pickListValue);
        Long selfIdsId = selfIds.getId();

        // Get all the pickListValueList where selfIds equals to selfIdsId
        defaultPickListValueShouldBeFound("selfIdsId.equals=" + selfIdsId);

        // Get all the pickListValueList where selfIds equals to selfIdsId + 1
        defaultPickListValueShouldNotBeFound("selfIdsId.equals=" + (selfIdsId + 1));
    }


    @Test
    @Transactional
    public void getAllPickListValuesByVarietysIsEqualToSomething() throws Exception {
        // Initialize the database
        Batch varietys = BatchResourceIntTest.createEntity(em);
        em.persist(varietys);
        em.flush();
        pickListValue.addVarietys(varietys);
        pickListValueRepository.saveAndFlush(pickListValue);
        Long varietysId = varietys.getId();

        // Get all the pickListValueList where varietys equals to varietysId
        defaultPickListValueShouldBeFound("varietysId.equals=" + varietysId);

        // Get all the pickListValueList where varietys equals to varietysId + 1
        defaultPickListValueShouldNotBeFound("varietysId.equals=" + (varietysId + 1));
    }


    @Test
    @Transactional
    public void getAllPickListValuesByCategorysIsEqualToSomething() throws Exception {
        // Initialize the database
        Batch categorys = BatchResourceIntTest.createEntity(em);
        em.persist(categorys);
        em.flush();
        pickListValue.addCategorys(categorys);
        pickListValueRepository.saveAndFlush(pickListValue);
        Long categorysId = categorys.getId();

        // Get all the pickListValueList where categorys equals to categorysId
        defaultPickListValueShouldBeFound("categorysId.equals=" + categorysId);

        // Get all the pickListValueList where categorys equals to categorysId + 1
        defaultPickListValueShouldNotBeFound("categorysId.equals=" + (categorysId + 1));
    }


    @Test
    @Transactional
    public void getAllPickListValuesByNurseryStockVarietysIsEqualToSomething() throws Exception {
        // Initialize the database
        NurseryStock nurseryStockVarietys = NurseryStockResourceIntTest.createEntity(em);
        em.persist(nurseryStockVarietys);
        em.flush();
        pickListValue.addNurseryStockVarietys(nurseryStockVarietys);
        pickListValueRepository.saveAndFlush(pickListValue);
        Long nurseryStockVarietysId = nurseryStockVarietys.getId();

        // Get all the pickListValueList where nurseryStockVarietys equals to nurseryStockVarietysId
        defaultPickListValueShouldBeFound("nurseryStockVarietysId.equals=" + nurseryStockVarietysId);

        // Get all the pickListValueList where nurseryStockVarietys equals to nurseryStockVarietysId + 1
        defaultPickListValueShouldNotBeFound("nurseryStockVarietysId.equals=" + (nurseryStockVarietysId + 1));
    }


    @Test
    @Transactional
    public void getAllPickListValuesByNurseryStockCategorysIsEqualToSomething() throws Exception {
        // Initialize the database
        NurseryStock nurseryStockCategorys = NurseryStockResourceIntTest.createEntity(em);
        em.persist(nurseryStockCategorys);
        em.flush();
        pickListValue.addNurseryStockCategorys(nurseryStockCategorys);
        pickListValueRepository.saveAndFlush(pickListValue);
        Long nurseryStockCategorysId = nurseryStockCategorys.getId();

        // Get all the pickListValueList where nurseryStockCategorys equals to nurseryStockCategorysId
        defaultPickListValueShouldBeFound("nurseryStockCategorysId.equals=" + nurseryStockCategorysId);

        // Get all the pickListValueList where nurseryStockCategorys equals to nurseryStockCategorysId + 1
        defaultPickListValueShouldNotBeFound("nurseryStockCategorysId.equals=" + (nurseryStockCategorysId + 1));
    }


    @Test
    @Transactional
    public void getAllPickListValuesByGodownPurchaseVarietysIsEqualToSomething() throws Exception {
        // Initialize the database
        GodownPurchaseDetails godownPurchaseVarietys = GodownPurchaseDetailsResourceIntTest.createEntity(em);
        em.persist(godownPurchaseVarietys);
        em.flush();
        pickListValue.addGodownPurchaseVarietys(godownPurchaseVarietys);
        pickListValueRepository.saveAndFlush(pickListValue);
        Long godownPurchaseVarietysId = godownPurchaseVarietys.getId();

        // Get all the pickListValueList where godownPurchaseVarietys equals to godownPurchaseVarietysId
        defaultPickListValueShouldBeFound("godownPurchaseVarietysId.equals=" + godownPurchaseVarietysId);

        // Get all the pickListValueList where godownPurchaseVarietys equals to godownPurchaseVarietysId + 1
        defaultPickListValueShouldNotBeFound("godownPurchaseVarietysId.equals=" + (godownPurchaseVarietysId + 1));
    }


    @Test
    @Transactional
    public void getAllPickListValuesByGodownPurchaseCategorysIsEqualToSomething() throws Exception {
        // Initialize the database
        GodownPurchaseDetails godownPurchaseCategorys = GodownPurchaseDetailsResourceIntTest.createEntity(em);
        em.persist(godownPurchaseCategorys);
        em.flush();
        pickListValue.addGodownPurchaseCategorys(godownPurchaseCategorys);
        pickListValueRepository.saveAndFlush(pickListValue);
        Long godownPurchaseCategorysId = godownPurchaseCategorys.getId();

        // Get all the pickListValueList where godownPurchaseCategorys equals to godownPurchaseCategorysId
        defaultPickListValueShouldBeFound("godownPurchaseCategorysId.equals=" + godownPurchaseCategorysId);

        // Get all the pickListValueList where godownPurchaseCategorys equals to godownPurchaseCategorysId + 1
        defaultPickListValueShouldNotBeFound("godownPurchaseCategorysId.equals=" + (godownPurchaseCategorysId + 1));
    }


    @Test
    @Transactional
    public void getAllPickListValuesByGodownPurchaseQuantityTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        GodownPurchaseDetails godownPurchaseQuantityType = GodownPurchaseDetailsResourceIntTest.createEntity(em);
        em.persist(godownPurchaseQuantityType);
        em.flush();
        pickListValue.addGodownPurchaseQuantityType(godownPurchaseQuantityType);
        pickListValueRepository.saveAndFlush(pickListValue);
        Long godownPurchaseQuantityTypeId = godownPurchaseQuantityType.getId();

        // Get all the pickListValueList where godownPurchaseQuantityType equals to godownPurchaseQuantityTypeId
        defaultPickListValueShouldBeFound("godownPurchaseQuantityTypeId.equals=" + godownPurchaseQuantityTypeId);

        // Get all the pickListValueList where godownPurchaseQuantityType equals to godownPurchaseQuantityTypeId + 1
        defaultPickListValueShouldNotBeFound("godownPurchaseQuantityTypeId.equals=" + (godownPurchaseQuantityTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllPickListValuesByGodownStockVarietysIsEqualToSomething() throws Exception {
        // Initialize the database
        GodownStock godownStockVarietys = GodownStockResourceIntTest.createEntity(em);
        em.persist(godownStockVarietys);
        em.flush();
        pickListValue.addGodownStockVarietys(godownStockVarietys);
        pickListValueRepository.saveAndFlush(pickListValue);
        Long godownStockVarietysId = godownStockVarietys.getId();

        // Get all the pickListValueList where godownStockVarietys equals to godownStockVarietysId
        defaultPickListValueShouldBeFound("godownStockVarietysId.equals=" + godownStockVarietysId);

        // Get all the pickListValueList where godownStockVarietys equals to godownStockVarietysId + 1
        defaultPickListValueShouldNotBeFound("godownStockVarietysId.equals=" + (godownStockVarietysId + 1));
    }


    @Test
    @Transactional
    public void getAllPickListValuesByGodownStockCategorysIsEqualToSomething() throws Exception {
        // Initialize the database
        GodownStock godownStockCategorys = GodownStockResourceIntTest.createEntity(em);
        em.persist(godownStockCategorys);
        em.flush();
        pickListValue.addGodownStockCategorys(godownStockCategorys);
        pickListValueRepository.saveAndFlush(pickListValue);
        Long godownStockCategorysId = godownStockCategorys.getId();

        // Get all the pickListValueList where godownStockCategorys equals to godownStockCategorysId
        defaultPickListValueShouldBeFound("godownStockCategorysId.equals=" + godownStockCategorysId);

        // Get all the pickListValueList where godownStockCategorys equals to godownStockCategorysId + 1
        defaultPickListValueShouldNotBeFound("godownStockCategorysId.equals=" + (godownStockCategorysId + 1));
    }


    @Test
    @Transactional
    public void getAllPickListValuesByGodownStockQuantityTypesIsEqualToSomething() throws Exception {
        // Initialize the database
        GodownStock godownStockQuantityTypes = GodownStockResourceIntTest.createEntity(em);
        em.persist(godownStockQuantityTypes);
        em.flush();
        pickListValue.addGodownStockQuantityTypes(godownStockQuantityTypes);
        pickListValueRepository.saveAndFlush(pickListValue);
        Long godownStockQuantityTypesId = godownStockQuantityTypes.getId();

        // Get all the pickListValueList where godownStockQuantityTypes equals to godownStockQuantityTypesId
        defaultPickListValueShouldBeFound("godownStockQuantityTypesId.equals=" + godownStockQuantityTypesId);

        // Get all the pickListValueList where godownStockQuantityTypes equals to godownStockQuantityTypesId + 1
        defaultPickListValueShouldNotBeFound("godownStockQuantityTypesId.equals=" + (godownStockQuantityTypesId + 1));
    }


    @Test
    @Transactional
    public void getAllPickListValuesByPickListIsEqualToSomething() throws Exception {
        // Initialize the database
        PickList pickList = PickListResourceIntTest.createEntity(em);
        em.persist(pickList);
        em.flush();
        pickListValue.setPickList(pickList);
        pickListValueRepository.saveAndFlush(pickListValue);
        Long pickListId = pickList.getId();

        // Get all the pickListValueList where pickList equals to pickListId
        defaultPickListValueShouldBeFound("pickListId.equals=" + pickListId);

        // Get all the pickListValueList where pickList equals to pickListId + 1
        defaultPickListValueShouldNotBeFound("pickListId.equals=" + (pickListId + 1));
    }


    @Test
    @Transactional
    public void getAllPickListValuesByPickValueIsEqualToSomething() throws Exception {
        // Initialize the database
        PickListValue pickValue = PickListValueResourceIntTest.createEntity(em);
        em.persist(pickValue);
        em.flush();
        pickListValue.setPickValue(pickValue);
        pickListValueRepository.saveAndFlush(pickListValue);
        Long pickValueId = pickValue.getId();

        // Get all the pickListValueList where pickValue equals to pickValueId
        defaultPickListValueShouldBeFound("pickValueId.equals=" + pickValueId);

        // Get all the pickListValueList where pickValue equals to pickValueId + 1
        defaultPickListValueShouldNotBeFound("pickValueId.equals=" + (pickValueId + 1));
    }


    @Test
    @Transactional
    public void getAllPickListValuesByNurserysIsEqualToSomething() throws Exception {
        // Initialize the database
        Nursery nurserys = NurseryResourceIntTest.createEntity(em);
        em.persist(nurserys);
        em.flush();
        pickListValue.addNurserys(nurserys);
        pickListValueRepository.saveAndFlush(pickListValue);
        Long nurserysId = nurserys.getId();

        // Get all the pickListValueList where nurserys equals to nurserysId
        defaultPickListValueShouldBeFound("nurserysId.equals=" + nurserysId);

        // Get all the pickListValueList where nurserys equals to nurserysId + 1
        defaultPickListValueShouldNotBeFound("nurserysId.equals=" + (nurserysId + 1));
    }


    @Test
    @Transactional
    public void getAllPickListValuesByBatchQuantityTypesIsEqualToSomething() throws Exception {
        // Initialize the database
        Batch batchQuantityTypes = BatchResourceIntTest.createEntity(em);
        em.persist(batchQuantityTypes);
        em.flush();
        pickListValue.addBatchQuantityTypes(batchQuantityTypes);
        pickListValueRepository.saveAndFlush(pickListValue);
        Long batchQuantityTypesId = batchQuantityTypes.getId();

        // Get all the pickListValueList where batchQuantityTypes equals to batchQuantityTypesId
        defaultPickListValueShouldBeFound("batchQuantityTypesId.equals=" + batchQuantityTypesId);

        // Get all the pickListValueList where batchQuantityTypes equals to batchQuantityTypesId + 1
        defaultPickListValueShouldNotBeFound("batchQuantityTypesId.equals=" + (batchQuantityTypesId + 1));
    }


    @Test
    @Transactional
    public void getAllPickListValuesByNurseryInventoryVarietysIsEqualToSomething() throws Exception {
        // Initialize the database
        NurseryInventory nurseryInventoryVarietys = NurseryInventoryResourceIntTest.createEntity(em);
        em.persist(nurseryInventoryVarietys);
        em.flush();
        pickListValue.addNurseryInventoryVarietys(nurseryInventoryVarietys);
        pickListValueRepository.saveAndFlush(pickListValue);
        Long nurseryInventoryVarietysId = nurseryInventoryVarietys.getId();

        // Get all the pickListValueList where nurseryInventoryVarietys equals to nurseryInventoryVarietysId
        defaultPickListValueShouldBeFound("nurseryInventoryVarietysId.equals=" + nurseryInventoryVarietysId);

        // Get all the pickListValueList where nurseryInventoryVarietys equals to nurseryInventoryVarietysId + 1
        defaultPickListValueShouldNotBeFound("nurseryInventoryVarietysId.equals=" + (nurseryInventoryVarietysId + 1));
    }


    @Test
    @Transactional
    public void getAllPickListValuesByNurseryInventoryCategorysIsEqualToSomething() throws Exception {
        // Initialize the database
        NurseryInventory nurseryInventoryCategorys = NurseryInventoryResourceIntTest.createEntity(em);
        em.persist(nurseryInventoryCategorys);
        em.flush();
        pickListValue.addNurseryInventoryCategorys(nurseryInventoryCategorys);
        pickListValueRepository.saveAndFlush(pickListValue);
        Long nurseryInventoryCategorysId = nurseryInventoryCategorys.getId();

        // Get all the pickListValueList where nurseryInventoryCategorys equals to nurseryInventoryCategorysId
        defaultPickListValueShouldBeFound("nurseryInventoryCategorysId.equals=" + nurseryInventoryCategorysId);

        // Get all the pickListValueList where nurseryInventoryCategorys equals to nurseryInventoryCategorysId + 1
        defaultPickListValueShouldNotBeFound("nurseryInventoryCategorysId.equals=" + (nurseryInventoryCategorysId + 1));
    }


    @Test
    @Transactional
    public void getAllPickListValuesByNurseryInventoryQuantityTypesIsEqualToSomething() throws Exception {
        // Initialize the database
        NurseryInventory nurseryInventoryQuantityTypes = NurseryInventoryResourceIntTest.createEntity(em);
        em.persist(nurseryInventoryQuantityTypes);
        em.flush();
        pickListValue.addNurseryInventoryQuantityTypes(nurseryInventoryQuantityTypes);
        pickListValueRepository.saveAndFlush(pickListValue);
        Long nurseryInventoryQuantityTypesId = nurseryInventoryQuantityTypes.getId();

        // Get all the pickListValueList where nurseryInventoryQuantityTypes equals to nurseryInventoryQuantityTypesId
        defaultPickListValueShouldBeFound("nurseryInventoryQuantityTypesId.equals=" + nurseryInventoryQuantityTypesId);

        // Get all the pickListValueList where nurseryInventoryQuantityTypes equals to nurseryInventoryQuantityTypesId + 1
        defaultPickListValueShouldNotBeFound("nurseryInventoryQuantityTypesId.equals=" + (nurseryInventoryQuantityTypesId + 1));
    }


    @Test
    @Transactional
    public void getAllPickListValuesByNurseryInventoryDamageTypesIsEqualToSomething() throws Exception {
        // Initialize the database
        NurseryInventoryDetails nurseryInventoryDamageTypes = NurseryInventoryDetailsResourceIntTest.createEntity(em);
        em.persist(nurseryInventoryDamageTypes);
        em.flush();
        pickListValue.addNurseryInventoryDamageTypes(nurseryInventoryDamageTypes);
        pickListValueRepository.saveAndFlush(pickListValue);
        Long nurseryInventoryDamageTypesId = nurseryInventoryDamageTypes.getId();

        // Get all the pickListValueList where nurseryInventoryDamageTypes equals to nurseryInventoryDamageTypesId
        defaultPickListValueShouldBeFound("nurseryInventoryDamageTypesId.equals=" + nurseryInventoryDamageTypesId);

        // Get all the pickListValueList where nurseryInventoryDamageTypes equals to nurseryInventoryDamageTypesId + 1
        defaultPickListValueShouldNotBeFound("nurseryInventoryDamageTypesId.equals=" + (nurseryInventoryDamageTypesId + 1));
    }


    @Test
    @Transactional
    public void getAllPickListValuesByPickListValueDamageAreaIsEqualToSomething() throws Exception {
        // Initialize the database
        Damage pickListValueDamageArea = DamageResourceIntTest.createEntity(em);
        em.persist(pickListValueDamageArea);
        em.flush();
        pickListValue.addPickListValueDamageArea(pickListValueDamageArea);
        pickListValueRepository.saveAndFlush(pickListValue);
        Long pickListValueDamageAreaId = pickListValueDamageArea.getId();

        // Get all the pickListValueList where pickListValueDamageArea equals to pickListValueDamageAreaId
        defaultPickListValueShouldBeFound("pickListValueDamageAreaId.equals=" + pickListValueDamageAreaId);

        // Get all the pickListValueList where pickListValueDamageArea equals to pickListValueDamageAreaId + 1
        defaultPickListValueShouldNotBeFound("pickListValueDamageAreaId.equals=" + (pickListValueDamageAreaId + 1));
    }


    @Test
    @Transactional
    public void getAllPickListValuesByNurseryStockDamageAreaIsEqualToSomething() throws Exception {
        // Initialize the database
        NurseryStockDetails nurseryStockDamageArea = NurseryStockDetailsResourceIntTest.createEntity(em);
        em.persist(nurseryStockDamageArea);
        em.flush();
        pickListValue.addNurseryStockDamageArea(nurseryStockDamageArea);
        pickListValueRepository.saveAndFlush(pickListValue);
        Long nurseryStockDamageAreaId = nurseryStockDamageArea.getId();

        // Get all the pickListValueList where nurseryStockDamageArea equals to nurseryStockDamageAreaId
        defaultPickListValueShouldBeFound("nurseryStockDamageAreaId.equals=" + nurseryStockDamageAreaId);

        // Get all the pickListValueList where nurseryStockDamageArea equals to nurseryStockDamageAreaId + 1
        defaultPickListValueShouldNotBeFound("nurseryStockDamageAreaId.equals=" + (nurseryStockDamageAreaId + 1));
    }


    @Test
    @Transactional
    public void getAllPickListValuesByFinancialYearNameIsEqualToSomething() throws Exception {
        // Initialize the database
        FinancialYearSettings financialYearName = FinancialYearSettingsResourceIntTest.createEntity(em);
        em.persist(financialYearName);
        em.flush();
        pickListValue.addFinancialYearName(financialYearName);
        pickListValueRepository.saveAndFlush(pickListValue);
        Long financialYearNameId = financialYearName.getId();

        // Get all the pickListValueList where financialYearName equals to financialYearNameId
        defaultPickListValueShouldBeFound("financialYearNameId.equals=" + financialYearNameId);

        // Get all the pickListValueList where financialYearName equals to financialYearNameId + 1
        defaultPickListValueShouldNotBeFound("financialYearNameId.equals=" + (financialYearNameId + 1));
    }


    @Test
    @Transactional
    public void getAllPickListValuesByDamageDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        Damage damageDescription = DamageResourceIntTest.createEntity(em);
        em.persist(damageDescription);
        em.flush();
        pickListValue.addDamageDescription(damageDescription);
        pickListValueRepository.saveAndFlush(pickListValue);
        Long damageDescriptionId = damageDescription.getId();

        // Get all the pickListValueList where damageDescription equals to damageDescriptionId
        defaultPickListValueShouldBeFound("damageDescriptionId.equals=" + damageDescriptionId);

        // Get all the pickListValueList where damageDescription equals to damageDescriptionId + 1
        defaultPickListValueShouldNotBeFound("damageDescriptionId.equals=" + (damageDescriptionId + 1));
    }


    @Test
    @Transactional
    public void getAllPickListValuesByPointOfSaleVarietysIsEqualToSomething() throws Exception {
        // Initialize the database
        PointOfSaleDetails pointOfSaleVarietys = PointOfSaleDetailsResourceIntTest.createEntity(em);
        em.persist(pointOfSaleVarietys);
        em.flush();
        pickListValue.addPointOfSaleVarietys(pointOfSaleVarietys);
        pickListValueRepository.saveAndFlush(pickListValue);
        Long pointOfSaleVarietysId = pointOfSaleVarietys.getId();

        // Get all the pickListValueList where pointOfSaleVarietys equals to pointOfSaleVarietysId
        defaultPickListValueShouldBeFound("pointOfSaleVarietysId.equals=" + pointOfSaleVarietysId);

        // Get all the pickListValueList where pointOfSaleVarietys equals to pointOfSaleVarietysId + 1
        defaultPickListValueShouldNotBeFound("pointOfSaleVarietysId.equals=" + (pointOfSaleVarietysId + 1));
    }


    @Test
    @Transactional
    public void getAllPickListValuesByPointOfSaleCategorysIsEqualToSomething() throws Exception {
        // Initialize the database
        PointOfSaleDetails pointOfSaleCategorys = PointOfSaleDetailsResourceIntTest.createEntity(em);
        em.persist(pointOfSaleCategorys);
        em.flush();
        pickListValue.addPointOfSaleCategorys(pointOfSaleCategorys);
        pickListValueRepository.saveAndFlush(pickListValue);
        Long pointOfSaleCategorysId = pointOfSaleCategorys.getId();

        // Get all the pickListValueList where pointOfSaleCategorys equals to pointOfSaleCategorysId
        defaultPickListValueShouldBeFound("pointOfSaleCategorysId.equals=" + pointOfSaleCategorysId);

        // Get all the pickListValueList where pointOfSaleCategorys equals to pointOfSaleCategorysId + 1
        defaultPickListValueShouldNotBeFound("pointOfSaleCategorysId.equals=" + (pointOfSaleCategorysId + 1));
    }


    @Test
    @Transactional
    public void getAllPickListValuesByCoverFillingDetailsIsEqualToSomething() throws Exception {
        // Initialize the database
        CoverFillingDetails coverFillingDetails = CoverFillingDetailsResourceIntTest.createEntity(em);
        em.persist(coverFillingDetails);
        em.flush();
        pickListValue.addCoverFillingDetails(coverFillingDetails);
        pickListValueRepository.saveAndFlush(pickListValue);
        Long coverFillingDetailsId = coverFillingDetails.getId();

        // Get all the pickListValueList where coverFillingDetails equals to coverFillingDetailsId
        defaultPickListValueShouldBeFound("coverFillingDetailsId.equals=" + coverFillingDetailsId);

        // Get all the pickListValueList where coverFillingDetails equals to coverFillingDetailsId + 1
        defaultPickListValueShouldNotBeFound("coverFillingDetailsId.equals=" + (coverFillingDetailsId + 1));
    }


    @Test
    @Transactional
    public void getAllPickListValuesByNurseryInventoryDamageDescIsEqualToSomething() throws Exception {
        // Initialize the database
        NurseryInventoryDetails nurseryInventoryDamageDesc = NurseryInventoryDetailsResourceIntTest.createEntity(em);
        em.persist(nurseryInventoryDamageDesc);
        em.flush();
        pickListValue.addNurseryInventoryDamageDesc(nurseryInventoryDamageDesc);
        pickListValueRepository.saveAndFlush(pickListValue);
        Long nurseryInventoryDamageDescId = nurseryInventoryDamageDesc.getId();

        // Get all the pickListValueList where nurseryInventoryDamageDesc equals to nurseryInventoryDamageDescId
        defaultPickListValueShouldBeFound("nurseryInventoryDamageDescId.equals=" + nurseryInventoryDamageDescId);

        // Get all the pickListValueList where nurseryInventoryDamageDesc equals to nurseryInventoryDamageDescId + 1
        defaultPickListValueShouldNotBeFound("nurseryInventoryDamageDescId.equals=" + (nurseryInventoryDamageDescId + 1));
    }


    @Test
    @Transactional
    public void getAllPickListValuesByCoverFillingDamageDescIsEqualToSomething() throws Exception {
        // Initialize the database
        CoverFillingDetails coverFillingDamageDesc = CoverFillingDetailsResourceIntTest.createEntity(em);
        em.persist(coverFillingDamageDesc);
        em.flush();
        pickListValue.addCoverFillingDamageDesc(coverFillingDamageDesc);
        pickListValueRepository.saveAndFlush(pickListValue);
        Long coverFillingDamageDescId = coverFillingDamageDesc.getId();

        // Get all the pickListValueList where coverFillingDamageDesc equals to coverFillingDamageDescId
        defaultPickListValueShouldBeFound("coverFillingDamageDescId.equals=" + coverFillingDamageDescId);

        // Get all the pickListValueList where coverFillingDamageDesc equals to coverFillingDamageDescId + 1
        defaultPickListValueShouldNotBeFound("coverFillingDamageDescId.equals=" + (coverFillingDamageDescId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultPickListValueShouldBeFound(String filter) throws Exception {
        restPickListValueMockMvc.perform(get("/api/pick-list-values?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pickListValue.getId().intValue())))
            .andExpect(jsonPath("$.[*].pickListValue").value(hasItem(DEFAULT_PICK_LIST_VALUE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultPickListValueShouldNotBeFound(String filter) throws Exception {
        restPickListValueMockMvc.perform(get("/api/pick-list-values?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingPickListValue() throws Exception {
        // Get the pickListValue
        restPickListValueMockMvc.perform(get("/api/pick-list-values/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePickListValue() throws Exception {
        // Initialize the database
        pickListValueRepository.saveAndFlush(pickListValue);

        int databaseSizeBeforeUpdate = pickListValueRepository.findAll().size();

        // Update the pickListValue
        PickListValue updatedPickListValue = pickListValueRepository.findById(pickListValue.getId()).get();
        // Disconnect from session so that the updates on updatedPickListValue are not directly saved in db
        em.detach(updatedPickListValue);
        updatedPickListValue
            .pickListValue(UPDATED_PICK_LIST_VALUE)
            .status(UPDATED_STATUS);
        PickListValueDTO pickListValueDTO = pickListValueMapper.toDto(updatedPickListValue);

        restPickListValueMockMvc.perform(put("/api/pick-list-values")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pickListValueDTO)))
            .andExpect(status().isOk());

        // Validate the PickListValue in the database
        List<PickListValue> pickListValueList = pickListValueRepository.findAll();
        assertThat(pickListValueList).hasSize(databaseSizeBeforeUpdate);
        PickListValue testPickListValue = pickListValueList.get(pickListValueList.size() - 1);
        assertThat(testPickListValue.getPickListValue()).isEqualTo(UPDATED_PICK_LIST_VALUE);
        assertThat(testPickListValue.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingPickListValue() throws Exception {
        int databaseSizeBeforeUpdate = pickListValueRepository.findAll().size();

        // Create the PickListValue
        PickListValueDTO pickListValueDTO = pickListValueMapper.toDto(pickListValue);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPickListValueMockMvc.perform(put("/api/pick-list-values")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pickListValueDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PickListValue in the database
        List<PickListValue> pickListValueList = pickListValueRepository.findAll();
        assertThat(pickListValueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePickListValue() throws Exception {
        // Initialize the database
        pickListValueRepository.saveAndFlush(pickListValue);

        int databaseSizeBeforeDelete = pickListValueRepository.findAll().size();

        // Get the pickListValue
        restPickListValueMockMvc.perform(delete("/api/pick-list-values/{id}", pickListValue.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PickListValue> pickListValueList = pickListValueRepository.findAll();
        assertThat(pickListValueList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PickListValue.class);
        PickListValue pickListValue1 = new PickListValue();
        pickListValue1.setId(1L);
        PickListValue pickListValue2 = new PickListValue();
        pickListValue2.setId(pickListValue1.getId());
        assertThat(pickListValue1).isEqualTo(pickListValue2);
        pickListValue2.setId(2L);
        assertThat(pickListValue1).isNotEqualTo(pickListValue2);
        pickListValue1.setId(null);
        assertThat(pickListValue1).isNotEqualTo(pickListValue2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PickListValueDTO.class);
        PickListValueDTO pickListValueDTO1 = new PickListValueDTO();
        pickListValueDTO1.setId(1L);
        PickListValueDTO pickListValueDTO2 = new PickListValueDTO();
        assertThat(pickListValueDTO1).isNotEqualTo(pickListValueDTO2);
        pickListValueDTO2.setId(pickListValueDTO1.getId());
        assertThat(pickListValueDTO1).isEqualTo(pickListValueDTO2);
        pickListValueDTO2.setId(2L);
        assertThat(pickListValueDTO1).isNotEqualTo(pickListValueDTO2);
        pickListValueDTO1.setId(null);
        assertThat(pickListValueDTO1).isNotEqualTo(pickListValueDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(pickListValueMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(pickListValueMapper.fromId(null)).isNull();
    }
}
