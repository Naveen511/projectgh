package com.niche.ng.web.rest;

import com.niche.ng.ProjectghApp;

import com.niche.ng.domain.Nursery;
import com.niche.ng.domain.Batch;
import com.niche.ng.domain.NurseryStock;
import com.niche.ng.domain.Sector;
import com.niche.ng.domain.PickListValue;
import com.niche.ng.domain.MotherBed;
import com.niche.ng.domain.NurseryInventory;
import com.niche.ng.domain.NurseryStockDetails;
import com.niche.ng.domain.FinancialYearSettings;
import com.niche.ng.domain.NurseryIncharge;
import com.niche.ng.domain.MapNurseryWithSector;
import com.niche.ng.repository.NurseryRepository;
import com.niche.ng.service.NurseryService;
import com.niche.ng.service.dto.NurseryDTO;
import com.niche.ng.service.mapper.NurseryMapper;
import com.niche.ng.web.rest.errors.ExceptionTranslator;
import com.niche.ng.service.dto.NurseryCriteria;
import com.niche.ng.service.NurseryQueryService;

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
 * Test class for the NurseryResource REST controller.
 *
 * @see NurseryResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectghApp.class)
public class NurseryResourceIntTest {

    private static final String DEFAULT_NURSERY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NURSERY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_NURSERY_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_NURSERY_ADDRESS = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    @Autowired
    private NurseryRepository nurseryRepository;


    @Autowired
    private NurseryMapper nurseryMapper;
    

    @Autowired
    private NurseryService nurseryService;

    @Autowired
    private NurseryQueryService nurseryQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restNurseryMockMvc;

    private Nursery nursery;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NurseryResource nurseryResource = new NurseryResource(nurseryService, nurseryQueryService);
        this.restNurseryMockMvc = MockMvcBuilders.standaloneSetup(nurseryResource)
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
    public static Nursery createEntity(EntityManager em) {
        Nursery nursery = new Nursery()
            .nurseryName(DEFAULT_NURSERY_NAME)
            .nurseryAddress(DEFAULT_NURSERY_ADDRESS)
            .status(DEFAULT_STATUS)
            .code(DEFAULT_CODE);
        return nursery;
    }

    @Before
    public void initTest() {
        nursery = createEntity(em);
    }

    @Test
    @Transactional
    public void createNursery() throws Exception {
        int databaseSizeBeforeCreate = nurseryRepository.findAll().size();

        // Create the Nursery
        NurseryDTO nurseryDTO = nurseryMapper.toDto(nursery);
        restNurseryMockMvc.perform(post("/api/nurseries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryDTO)))
            .andExpect(status().isCreated());

        // Validate the Nursery in the database
        List<Nursery> nurseryList = nurseryRepository.findAll();
        assertThat(nurseryList).hasSize(databaseSizeBeforeCreate + 1);
        Nursery testNursery = nurseryList.get(nurseryList.size() - 1);
        assertThat(testNursery.getNurseryName()).isEqualTo(DEFAULT_NURSERY_NAME);
        assertThat(testNursery.getNurseryAddress()).isEqualTo(DEFAULT_NURSERY_ADDRESS);
        assertThat(testNursery.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testNursery.getCode()).isEqualTo(DEFAULT_CODE);
    }

    @Test
    @Transactional
    public void createNurseryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nurseryRepository.findAll().size();

        // Create the Nursery with an existing ID
        nursery.setId(1L);
        NurseryDTO nurseryDTO = nurseryMapper.toDto(nursery);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNurseryMockMvc.perform(post("/api/nurseries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Nursery in the database
        List<Nursery> nurseryList = nurseryRepository.findAll();
        assertThat(nurseryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNurseryNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = nurseryRepository.findAll().size();
        // set the field null
        nursery.setNurseryName(null);

        // Create the Nursery, which fails.
        NurseryDTO nurseryDTO = nurseryMapper.toDto(nursery);

        restNurseryMockMvc.perform(post("/api/nurseries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryDTO)))
            .andExpect(status().isBadRequest());

        List<Nursery> nurseryList = nurseryRepository.findAll();
        assertThat(nurseryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNurseries() throws Exception {
        // Initialize the database
        nurseryRepository.saveAndFlush(nursery);

        // Get all the nurseryList
        restNurseryMockMvc.perform(get("/api/nurseries?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nursery.getId().intValue())))
            .andExpect(jsonPath("$.[*].nurseryName").value(hasItem(DEFAULT_NURSERY_NAME.toString())))
            .andExpect(jsonPath("$.[*].nurseryAddress").value(hasItem(DEFAULT_NURSERY_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())));
    }
    

    @Test
    @Transactional
    public void getNursery() throws Exception {
        // Initialize the database
        nurseryRepository.saveAndFlush(nursery);

        // Get the nursery
        restNurseryMockMvc.perform(get("/api/nurseries/{id}", nursery.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(nursery.getId().intValue()))
            .andExpect(jsonPath("$.nurseryName").value(DEFAULT_NURSERY_NAME.toString()))
            .andExpect(jsonPath("$.nurseryAddress").value(DEFAULT_NURSERY_ADDRESS.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()));
    }

    @Test
    @Transactional
    public void getAllNurseriesByNurseryNameIsEqualToSomething() throws Exception {
        // Initialize the database
        nurseryRepository.saveAndFlush(nursery);

        // Get all the nurseryList where nurseryName equals to DEFAULT_NURSERY_NAME
        defaultNurseryShouldBeFound("nurseryName.equals=" + DEFAULT_NURSERY_NAME);

        // Get all the nurseryList where nurseryName equals to UPDATED_NURSERY_NAME
        defaultNurseryShouldNotBeFound("nurseryName.equals=" + UPDATED_NURSERY_NAME);
    }

    @Test
    @Transactional
    public void getAllNurseriesByNurseryNameIsInShouldWork() throws Exception {
        // Initialize the database
        nurseryRepository.saveAndFlush(nursery);

        // Get all the nurseryList where nurseryName in DEFAULT_NURSERY_NAME or UPDATED_NURSERY_NAME
        defaultNurseryShouldBeFound("nurseryName.in=" + DEFAULT_NURSERY_NAME + "," + UPDATED_NURSERY_NAME);

        // Get all the nurseryList where nurseryName equals to UPDATED_NURSERY_NAME
        defaultNurseryShouldNotBeFound("nurseryName.in=" + UPDATED_NURSERY_NAME);
    }

    @Test
    @Transactional
    public void getAllNurseriesByNurseryNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        nurseryRepository.saveAndFlush(nursery);

        // Get all the nurseryList where nurseryName is not null
        defaultNurseryShouldBeFound("nurseryName.specified=true");

        // Get all the nurseryList where nurseryName is null
        defaultNurseryShouldNotBeFound("nurseryName.specified=false");
    }

    @Test
    @Transactional
    public void getAllNurseriesByNurseryAddressIsEqualToSomething() throws Exception {
        // Initialize the database
        nurseryRepository.saveAndFlush(nursery);

        // Get all the nurseryList where nurseryAddress equals to DEFAULT_NURSERY_ADDRESS
        defaultNurseryShouldBeFound("nurseryAddress.equals=" + DEFAULT_NURSERY_ADDRESS);

        // Get all the nurseryList where nurseryAddress equals to UPDATED_NURSERY_ADDRESS
        defaultNurseryShouldNotBeFound("nurseryAddress.equals=" + UPDATED_NURSERY_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllNurseriesByNurseryAddressIsInShouldWork() throws Exception {
        // Initialize the database
        nurseryRepository.saveAndFlush(nursery);

        // Get all the nurseryList where nurseryAddress in DEFAULT_NURSERY_ADDRESS or UPDATED_NURSERY_ADDRESS
        defaultNurseryShouldBeFound("nurseryAddress.in=" + DEFAULT_NURSERY_ADDRESS + "," + UPDATED_NURSERY_ADDRESS);

        // Get all the nurseryList where nurseryAddress equals to UPDATED_NURSERY_ADDRESS
        defaultNurseryShouldNotBeFound("nurseryAddress.in=" + UPDATED_NURSERY_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllNurseriesByNurseryAddressIsNullOrNotNull() throws Exception {
        // Initialize the database
        nurseryRepository.saveAndFlush(nursery);

        // Get all the nurseryList where nurseryAddress is not null
        defaultNurseryShouldBeFound("nurseryAddress.specified=true");

        // Get all the nurseryList where nurseryAddress is null
        defaultNurseryShouldNotBeFound("nurseryAddress.specified=false");
    }

    @Test
    @Transactional
    public void getAllNurseriesByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        nurseryRepository.saveAndFlush(nursery);

        // Get all the nurseryList where status equals to DEFAULT_STATUS
        defaultNurseryShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the nurseryList where status equals to UPDATED_STATUS
        defaultNurseryShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllNurseriesByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        nurseryRepository.saveAndFlush(nursery);

        // Get all the nurseryList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultNurseryShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the nurseryList where status equals to UPDATED_STATUS
        defaultNurseryShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllNurseriesByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        nurseryRepository.saveAndFlush(nursery);

        // Get all the nurseryList where status is not null
        defaultNurseryShouldBeFound("status.specified=true");

        // Get all the nurseryList where status is null
        defaultNurseryShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllNurseriesByStatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        nurseryRepository.saveAndFlush(nursery);

        // Get all the nurseryList where status greater than or equals to DEFAULT_STATUS
        defaultNurseryShouldBeFound("status.greaterOrEqualThan=" + DEFAULT_STATUS);

        // Get all the nurseryList where status greater than or equals to UPDATED_STATUS
        defaultNurseryShouldNotBeFound("status.greaterOrEqualThan=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllNurseriesByStatusIsLessThanSomething() throws Exception {
        // Initialize the database
        nurseryRepository.saveAndFlush(nursery);

        // Get all the nurseryList where status less than or equals to DEFAULT_STATUS
        defaultNurseryShouldNotBeFound("status.lessThan=" + DEFAULT_STATUS);

        // Get all the nurseryList where status less than or equals to UPDATED_STATUS
        defaultNurseryShouldBeFound("status.lessThan=" + UPDATED_STATUS);
    }


    @Test
    @Transactional
    public void getAllNurseriesByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        nurseryRepository.saveAndFlush(nursery);

        // Get all the nurseryList where code equals to DEFAULT_CODE
        defaultNurseryShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the nurseryList where code equals to UPDATED_CODE
        defaultNurseryShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllNurseriesByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        nurseryRepository.saveAndFlush(nursery);

        // Get all the nurseryList where code in DEFAULT_CODE or UPDATED_CODE
        defaultNurseryShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the nurseryList where code equals to UPDATED_CODE
        defaultNurseryShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllNurseriesByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        nurseryRepository.saveAndFlush(nursery);

        // Get all the nurseryList where code is not null
        defaultNurseryShouldBeFound("code.specified=true");

        // Get all the nurseryList where code is null
        defaultNurseryShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllNurseriesByBatchsIsEqualToSomething() throws Exception {
        // Initialize the database
        Batch batchs = BatchResourceIntTest.createEntity(em);
        em.persist(batchs);
        em.flush();
        nursery.addBatchs(batchs);
        nurseryRepository.saveAndFlush(nursery);
        Long batchsId = batchs.getId();

        // Get all the nurseryList where batchs equals to batchsId
        defaultNurseryShouldBeFound("batchsId.equals=" + batchsId);

        // Get all the nurseryList where batchs equals to batchsId + 1
        defaultNurseryShouldNotBeFound("batchsId.equals=" + (batchsId + 1));
    }


    @Test
    @Transactional
    public void getAllNurseriesByNurseryStocksIsEqualToSomething() throws Exception {
        // Initialize the database
        NurseryStock nurseryStocks = NurseryStockResourceIntTest.createEntity(em);
        em.persist(nurseryStocks);
        em.flush();
        nursery.addNurseryStocks(nurseryStocks);
        nurseryRepository.saveAndFlush(nursery);
        Long nurseryStocksId = nurseryStocks.getId();

        // Get all the nurseryList where nurseryStocks equals to nurseryStocksId
        defaultNurseryShouldBeFound("nurseryStocksId.equals=" + nurseryStocksId);

        // Get all the nurseryList where nurseryStocks equals to nurseryStocksId + 1
        defaultNurseryShouldNotBeFound("nurseryStocksId.equals=" + (nurseryStocksId + 1));
    }


    @Test
    @Transactional
    public void getAllNurseriesBySectorIsEqualToSomething() throws Exception {
        // Initialize the database
        Sector sector = SectorResourceIntTest.createEntity(em);
        em.persist(sector);
        em.flush();
        nursery.setSector(sector);
        nurseryRepository.saveAndFlush(nursery);
        Long sectorId = sector.getId();

        // Get all the nurseryList where sector equals to sectorId
        defaultNurseryShouldBeFound("sectorId.equals=" + sectorId);

        // Get all the nurseryList where sector equals to sectorId + 1
        defaultNurseryShouldNotBeFound("sectorId.equals=" + (sectorId + 1));
    }


    @Test
    @Transactional
    public void getAllNurseriesByNurseryTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        PickListValue nurseryType = PickListValueResourceIntTest.createEntity(em);
        em.persist(nurseryType);
        em.flush();
        nursery.setNurseryType(nurseryType);
        nurseryRepository.saveAndFlush(nursery);
        Long nurseryTypeId = nurseryType.getId();

        // Get all the nurseryList where nurseryType equals to nurseryTypeId
        defaultNurseryShouldBeFound("nurseryTypeId.equals=" + nurseryTypeId);

        // Get all the nurseryList where nurseryType equals to nurseryTypeId + 1
        defaultNurseryShouldNotBeFound("nurseryTypeId.equals=" + (nurseryTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllNurseriesByMotherBedsIsEqualToSomething() throws Exception {
        // Initialize the database
        MotherBed motherBeds = MotherBedResourceIntTest.createEntity(em);
        em.persist(motherBeds);
        em.flush();
        nursery.addMotherBeds(motherBeds);
        nurseryRepository.saveAndFlush(nursery);
        Long motherBedsId = motherBeds.getId();

        // Get all the nurseryList where motherBeds equals to motherBedsId
        defaultNurseryShouldBeFound("motherBedsId.equals=" + motherBedsId);

        // Get all the nurseryList where motherBeds equals to motherBedsId + 1
        defaultNurseryShouldNotBeFound("motherBedsId.equals=" + (motherBedsId + 1));
    }


    @Test
    @Transactional
    public void getAllNurseriesByNurseryInventorysIsEqualToSomething() throws Exception {
        // Initialize the database
        NurseryInventory nurseryInventorys = NurseryInventoryResourceIntTest.createEntity(em);
        em.persist(nurseryInventorys);
        em.flush();
        nursery.addNurseryInventorys(nurseryInventorys);
        nurseryRepository.saveAndFlush(nursery);
        Long nurseryInventorysId = nurseryInventorys.getId();

        // Get all the nurseryList where nurseryInventorys equals to nurseryInventorysId
        defaultNurseryShouldBeFound("nurseryInventorysId.equals=" + nurseryInventorysId);

        // Get all the nurseryList where nurseryInventorys equals to nurseryInventorysId + 1
        defaultNurseryShouldNotBeFound("nurseryInventorysId.equals=" + (nurseryInventorysId + 1));
    }


    @Test
    @Transactional
    public void getAllNurseriesByNurseryStockDetailsIsEqualToSomething() throws Exception {
        // Initialize the database
        NurseryStockDetails nurseryStockDetails = NurseryStockDetailsResourceIntTest.createEntity(em);
        em.persist(nurseryStockDetails);
        em.flush();
        nursery.addNurseryStockDetails(nurseryStockDetails);
        nurseryRepository.saveAndFlush(nursery);
        Long nurseryStockDetailsId = nurseryStockDetails.getId();

        // Get all the nurseryList where nurseryStockDetails equals to nurseryStockDetailsId
        defaultNurseryShouldBeFound("nurseryStockDetailsId.equals=" + nurseryStockDetailsId);

        // Get all the nurseryList where nurseryStockDetails equals to nurseryStockDetailsId + 1
        defaultNurseryShouldNotBeFound("nurseryStockDetailsId.equals=" + (nurseryStockDetailsId + 1));
    }


    @Test
    @Transactional
    public void getAllNurseriesByFinancialYearNurseryIsEqualToSomething() throws Exception {
        // Initialize the database
        FinancialYearSettings financialYearNursery = FinancialYearSettingsResourceIntTest.createEntity(em);
        em.persist(financialYearNursery);
        em.flush();
        nursery.setFinancialYearNursery(financialYearNursery);
        nurseryRepository.saveAndFlush(nursery);
        Long financialYearNurseryId = financialYearNursery.getId();

        // Get all the nurseryList where financialYearNursery equals to financialYearNurseryId
        defaultNurseryShouldBeFound("financialYearNurseryId.equals=" + financialYearNurseryId);

        // Get all the nurseryList where financialYearNursery equals to financialYearNurseryId + 1
        defaultNurseryShouldNotBeFound("financialYearNurseryId.equals=" + (financialYearNurseryId + 1));
    }


    @Test
    @Transactional
    public void getAllNurseriesByInchargeIsEqualToSomething() throws Exception {
        // Initialize the database
        NurseryIncharge incharge = NurseryInchargeResourceIntTest.createEntity(em);
        em.persist(incharge);
        em.flush();
        nursery.addIncharge(incharge);
        nurseryRepository.saveAndFlush(nursery);
        Long inchargeId = incharge.getId();

        // Get all the nurseryList where incharge equals to inchargeId
        defaultNurseryShouldBeFound("inchargeId.equals=" + inchargeId);

        // Get all the nurseryList where incharge equals to inchargeId + 1
        defaultNurseryShouldNotBeFound("inchargeId.equals=" + (inchargeId + 1));
    }


    @Test
    @Transactional
    public void getAllNurseriesByMapNurseryWithSectorIsEqualToSomething() throws Exception {
        // Initialize the database
        MapNurseryWithSector mapNurseryWithSector = MapNurseryWithSectorResourceIntTest.createEntity(em);
        em.persist(mapNurseryWithSector);
        em.flush();
        nursery.addMapNurseryWithSector(mapNurseryWithSector);
        nurseryRepository.saveAndFlush(nursery);
        Long mapNurseryWithSectorId = mapNurseryWithSector.getId();

        // Get all the nurseryList where mapNurseryWithSector equals to mapNurseryWithSectorId
        defaultNurseryShouldBeFound("mapNurseryWithSectorId.equals=" + mapNurseryWithSectorId);

        // Get all the nurseryList where mapNurseryWithSector equals to mapNurseryWithSectorId + 1
        defaultNurseryShouldNotBeFound("mapNurseryWithSectorId.equals=" + (mapNurseryWithSectorId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultNurseryShouldBeFound(String filter) throws Exception {
        restNurseryMockMvc.perform(get("/api/nurseries?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nursery.getId().intValue())))
            .andExpect(jsonPath("$.[*].nurseryName").value(hasItem(DEFAULT_NURSERY_NAME.toString())))
            .andExpect(jsonPath("$.[*].nurseryAddress").value(hasItem(DEFAULT_NURSERY_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultNurseryShouldNotBeFound(String filter) throws Exception {
        restNurseryMockMvc.perform(get("/api/nurseries?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingNursery() throws Exception {
        // Get the nursery
        restNurseryMockMvc.perform(get("/api/nurseries/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNursery() throws Exception {
        // Initialize the database
        nurseryRepository.saveAndFlush(nursery);

        int databaseSizeBeforeUpdate = nurseryRepository.findAll().size();

        // Update the nursery
        Nursery updatedNursery = nurseryRepository.findById(nursery.getId()).get();
        // Disconnect from session so that the updates on updatedNursery are not directly saved in db
        em.detach(updatedNursery);
        updatedNursery
            .nurseryName(UPDATED_NURSERY_NAME)
            .nurseryAddress(UPDATED_NURSERY_ADDRESS)
            .status(UPDATED_STATUS)
            .code(UPDATED_CODE);
        NurseryDTO nurseryDTO = nurseryMapper.toDto(updatedNursery);

        restNurseryMockMvc.perform(put("/api/nurseries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryDTO)))
            .andExpect(status().isOk());

        // Validate the Nursery in the database
        List<Nursery> nurseryList = nurseryRepository.findAll();
        assertThat(nurseryList).hasSize(databaseSizeBeforeUpdate);
        Nursery testNursery = nurseryList.get(nurseryList.size() - 1);
        assertThat(testNursery.getNurseryName()).isEqualTo(UPDATED_NURSERY_NAME);
        assertThat(testNursery.getNurseryAddress()).isEqualTo(UPDATED_NURSERY_ADDRESS);
        assertThat(testNursery.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testNursery.getCode()).isEqualTo(UPDATED_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingNursery() throws Exception {
        int databaseSizeBeforeUpdate = nurseryRepository.findAll().size();

        // Create the Nursery
        NurseryDTO nurseryDTO = nurseryMapper.toDto(nursery);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restNurseryMockMvc.perform(put("/api/nurseries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Nursery in the database
        List<Nursery> nurseryList = nurseryRepository.findAll();
        assertThat(nurseryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNursery() throws Exception {
        // Initialize the database
        nurseryRepository.saveAndFlush(nursery);

        int databaseSizeBeforeDelete = nurseryRepository.findAll().size();

        // Get the nursery
        restNurseryMockMvc.perform(delete("/api/nurseries/{id}", nursery.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Nursery> nurseryList = nurseryRepository.findAll();
        assertThat(nurseryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Nursery.class);
        Nursery nursery1 = new Nursery();
        nursery1.setId(1L);
        Nursery nursery2 = new Nursery();
        nursery2.setId(nursery1.getId());
        assertThat(nursery1).isEqualTo(nursery2);
        nursery2.setId(2L);
        assertThat(nursery1).isNotEqualTo(nursery2);
        nursery1.setId(null);
        assertThat(nursery1).isNotEqualTo(nursery2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NurseryDTO.class);
        NurseryDTO nurseryDTO1 = new NurseryDTO();
        nurseryDTO1.setId(1L);
        NurseryDTO nurseryDTO2 = new NurseryDTO();
        assertThat(nurseryDTO1).isNotEqualTo(nurseryDTO2);
        nurseryDTO2.setId(nurseryDTO1.getId());
        assertThat(nurseryDTO1).isEqualTo(nurseryDTO2);
        nurseryDTO2.setId(2L);
        assertThat(nurseryDTO1).isNotEqualTo(nurseryDTO2);
        nurseryDTO1.setId(null);
        assertThat(nurseryDTO1).isNotEqualTo(nurseryDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(nurseryMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(nurseryMapper.fromId(null)).isNull();
    }
}
