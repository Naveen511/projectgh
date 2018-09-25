package com.niche.ng.web.rest;

import com.niche.ng.ProjectghApp;

import com.niche.ng.domain.FinancialYearSettings;
import com.niche.ng.domain.PickListValue;
import com.niche.ng.domain.Zonal;
import com.niche.ng.domain.Sector;
import com.niche.ng.domain.Nursery;
import com.niche.ng.domain.Batch;
import com.niche.ng.domain.Damage;
import com.niche.ng.domain.ShadeArea;
import com.niche.ng.domain.NurseryStock;
import com.niche.ng.domain.NurseryStockDetails;
import com.niche.ng.domain.Godown;
import com.niche.ng.domain.GodownStock;
import com.niche.ng.domain.GodownStockDetails;
import com.niche.ng.domain.GodownPurchaseDetails;
import com.niche.ng.repository.FinancialYearSettingsRepository;
import com.niche.ng.service.FinancialYearSettingsService;
import com.niche.ng.service.dto.FinancialYearSettingsDTO;
import com.niche.ng.service.mapper.FinancialYearSettingsMapper;
import com.niche.ng.web.rest.errors.ExceptionTranslator;
import com.niche.ng.service.dto.FinancialYearSettingsCriteria;
import com.niche.ng.service.FinancialYearSettingsQueryService;

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
 * Test class for the FinancialYearSettingsResource REST controller.
 *
 * @see FinancialYearSettingsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectghApp.class)
public class FinancialYearSettingsResourceIntTest {

    private static final String DEFAULT_BATCH_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BATCH_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    @Autowired
    private FinancialYearSettingsRepository financialYearSettingsRepository;


    @Autowired
    private FinancialYearSettingsMapper financialYearSettingsMapper;
    

    @Autowired
    private FinancialYearSettingsService financialYearSettingsService;

    @Autowired
    private FinancialYearSettingsQueryService financialYearSettingsQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFinancialYearSettingsMockMvc;

    private FinancialYearSettings financialYearSettings;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FinancialYearSettingsResource financialYearSettingsResource = new FinancialYearSettingsResource(financialYearSettingsService, financialYearSettingsQueryService);
        this.restFinancialYearSettingsMockMvc = MockMvcBuilders.standaloneSetup(financialYearSettingsResource)
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
    public static FinancialYearSettings createEntity(EntityManager em) {
        FinancialYearSettings financialYearSettings = new FinancialYearSettings()
            .batchName(DEFAULT_BATCH_NAME)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .status(DEFAULT_STATUS);
        return financialYearSettings;
    }

    @Before
    public void initTest() {
        financialYearSettings = createEntity(em);
    }

    @Test
    @Transactional
    public void createFinancialYearSettings() throws Exception {
        int databaseSizeBeforeCreate = financialYearSettingsRepository.findAll().size();

        // Create the FinancialYearSettings
        FinancialYearSettingsDTO financialYearSettingsDTO = financialYearSettingsMapper.toDto(financialYearSettings);
        restFinancialYearSettingsMockMvc.perform(post("/api/financial-year-settings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(financialYearSettingsDTO)))
            .andExpect(status().isCreated());

        // Validate the FinancialYearSettings in the database
        List<FinancialYearSettings> financialYearSettingsList = financialYearSettingsRepository.findAll();
        assertThat(financialYearSettingsList).hasSize(databaseSizeBeforeCreate + 1);
        FinancialYearSettings testFinancialYearSettings = financialYearSettingsList.get(financialYearSettingsList.size() - 1);
        assertThat(testFinancialYearSettings.getBatchName()).isEqualTo(DEFAULT_BATCH_NAME);
        assertThat(testFinancialYearSettings.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testFinancialYearSettings.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testFinancialYearSettings.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createFinancialYearSettingsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = financialYearSettingsRepository.findAll().size();

        // Create the FinancialYearSettings with an existing ID
        financialYearSettings.setId(1L);
        FinancialYearSettingsDTO financialYearSettingsDTO = financialYearSettingsMapper.toDto(financialYearSettings);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFinancialYearSettingsMockMvc.perform(post("/api/financial-year-settings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(financialYearSettingsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FinancialYearSettings in the database
        List<FinancialYearSettings> financialYearSettingsList = financialYearSettingsRepository.findAll();
        assertThat(financialYearSettingsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkBatchNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = financialYearSettingsRepository.findAll().size();
        // set the field null
        financialYearSettings.setBatchName(null);

        // Create the FinancialYearSettings, which fails.
        FinancialYearSettingsDTO financialYearSettingsDTO = financialYearSettingsMapper.toDto(financialYearSettings);

        restFinancialYearSettingsMockMvc.perform(post("/api/financial-year-settings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(financialYearSettingsDTO)))
            .andExpect(status().isBadRequest());

        List<FinancialYearSettings> financialYearSettingsList = financialYearSettingsRepository.findAll();
        assertThat(financialYearSettingsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFinancialYearSettings() throws Exception {
        // Initialize the database
        financialYearSettingsRepository.saveAndFlush(financialYearSettings);

        // Get all the financialYearSettingsList
        restFinancialYearSettingsMockMvc.perform(get("/api/financial-year-settings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(financialYearSettings.getId().intValue())))
            .andExpect(jsonPath("$.[*].batchName").value(hasItem(DEFAULT_BATCH_NAME.toString())))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    

    @Test
    @Transactional
    public void getFinancialYearSettings() throws Exception {
        // Initialize the database
        financialYearSettingsRepository.saveAndFlush(financialYearSettings);

        // Get the financialYearSettings
        restFinancialYearSettingsMockMvc.perform(get("/api/financial-year-settings/{id}", financialYearSettings.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(financialYearSettings.getId().intValue()))
            .andExpect(jsonPath("$.batchName").value(DEFAULT_BATCH_NAME.toString()))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getAllFinancialYearSettingsByBatchNameIsEqualToSomething() throws Exception {
        // Initialize the database
        financialYearSettingsRepository.saveAndFlush(financialYearSettings);

        // Get all the financialYearSettingsList where batchName equals to DEFAULT_BATCH_NAME
        defaultFinancialYearSettingsShouldBeFound("batchName.equals=" + DEFAULT_BATCH_NAME);

        // Get all the financialYearSettingsList where batchName equals to UPDATED_BATCH_NAME
        defaultFinancialYearSettingsShouldNotBeFound("batchName.equals=" + UPDATED_BATCH_NAME);
    }

    @Test
    @Transactional
    public void getAllFinancialYearSettingsByBatchNameIsInShouldWork() throws Exception {
        // Initialize the database
        financialYearSettingsRepository.saveAndFlush(financialYearSettings);

        // Get all the financialYearSettingsList where batchName in DEFAULT_BATCH_NAME or UPDATED_BATCH_NAME
        defaultFinancialYearSettingsShouldBeFound("batchName.in=" + DEFAULT_BATCH_NAME + "," + UPDATED_BATCH_NAME);

        // Get all the financialYearSettingsList where batchName equals to UPDATED_BATCH_NAME
        defaultFinancialYearSettingsShouldNotBeFound("batchName.in=" + UPDATED_BATCH_NAME);
    }

    @Test
    @Transactional
    public void getAllFinancialYearSettingsByBatchNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        financialYearSettingsRepository.saveAndFlush(financialYearSettings);

        // Get all the financialYearSettingsList where batchName is not null
        defaultFinancialYearSettingsShouldBeFound("batchName.specified=true");

        // Get all the financialYearSettingsList where batchName is null
        defaultFinancialYearSettingsShouldNotBeFound("batchName.specified=false");
    }

    @Test
    @Transactional
    public void getAllFinancialYearSettingsByStartDateIsEqualToSomething() throws Exception {
        // Initialize the database
        financialYearSettingsRepository.saveAndFlush(financialYearSettings);

        // Get all the financialYearSettingsList where startDate equals to DEFAULT_START_DATE
        defaultFinancialYearSettingsShouldBeFound("startDate.equals=" + DEFAULT_START_DATE);

        // Get all the financialYearSettingsList where startDate equals to UPDATED_START_DATE
        defaultFinancialYearSettingsShouldNotBeFound("startDate.equals=" + UPDATED_START_DATE);
    }

    @Test
    @Transactional
    public void getAllFinancialYearSettingsByStartDateIsInShouldWork() throws Exception {
        // Initialize the database
        financialYearSettingsRepository.saveAndFlush(financialYearSettings);

        // Get all the financialYearSettingsList where startDate in DEFAULT_START_DATE or UPDATED_START_DATE
        defaultFinancialYearSettingsShouldBeFound("startDate.in=" + DEFAULT_START_DATE + "," + UPDATED_START_DATE);

        // Get all the financialYearSettingsList where startDate equals to UPDATED_START_DATE
        defaultFinancialYearSettingsShouldNotBeFound("startDate.in=" + UPDATED_START_DATE);
    }

    @Test
    @Transactional
    public void getAllFinancialYearSettingsByStartDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        financialYearSettingsRepository.saveAndFlush(financialYearSettings);

        // Get all the financialYearSettingsList where startDate is not null
        defaultFinancialYearSettingsShouldBeFound("startDate.specified=true");

        // Get all the financialYearSettingsList where startDate is null
        defaultFinancialYearSettingsShouldNotBeFound("startDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllFinancialYearSettingsByStartDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        financialYearSettingsRepository.saveAndFlush(financialYearSettings);

        // Get all the financialYearSettingsList where startDate greater than or equals to DEFAULT_START_DATE
        defaultFinancialYearSettingsShouldBeFound("startDate.greaterOrEqualThan=" + DEFAULT_START_DATE);

        // Get all the financialYearSettingsList where startDate greater than or equals to UPDATED_START_DATE
        defaultFinancialYearSettingsShouldNotBeFound("startDate.greaterOrEqualThan=" + UPDATED_START_DATE);
    }

    @Test
    @Transactional
    public void getAllFinancialYearSettingsByStartDateIsLessThanSomething() throws Exception {
        // Initialize the database
        financialYearSettingsRepository.saveAndFlush(financialYearSettings);

        // Get all the financialYearSettingsList where startDate less than or equals to DEFAULT_START_DATE
        defaultFinancialYearSettingsShouldNotBeFound("startDate.lessThan=" + DEFAULT_START_DATE);

        // Get all the financialYearSettingsList where startDate less than or equals to UPDATED_START_DATE
        defaultFinancialYearSettingsShouldBeFound("startDate.lessThan=" + UPDATED_START_DATE);
    }


    @Test
    @Transactional
    public void getAllFinancialYearSettingsByEndDateIsEqualToSomething() throws Exception {
        // Initialize the database
        financialYearSettingsRepository.saveAndFlush(financialYearSettings);

        // Get all the financialYearSettingsList where endDate equals to DEFAULT_END_DATE
        defaultFinancialYearSettingsShouldBeFound("endDate.equals=" + DEFAULT_END_DATE);

        // Get all the financialYearSettingsList where endDate equals to UPDATED_END_DATE
        defaultFinancialYearSettingsShouldNotBeFound("endDate.equals=" + UPDATED_END_DATE);
    }

    @Test
    @Transactional
    public void getAllFinancialYearSettingsByEndDateIsInShouldWork() throws Exception {
        // Initialize the database
        financialYearSettingsRepository.saveAndFlush(financialYearSettings);

        // Get all the financialYearSettingsList where endDate in DEFAULT_END_DATE or UPDATED_END_DATE
        defaultFinancialYearSettingsShouldBeFound("endDate.in=" + DEFAULT_END_DATE + "," + UPDATED_END_DATE);

        // Get all the financialYearSettingsList where endDate equals to UPDATED_END_DATE
        defaultFinancialYearSettingsShouldNotBeFound("endDate.in=" + UPDATED_END_DATE);
    }

    @Test
    @Transactional
    public void getAllFinancialYearSettingsByEndDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        financialYearSettingsRepository.saveAndFlush(financialYearSettings);

        // Get all the financialYearSettingsList where endDate is not null
        defaultFinancialYearSettingsShouldBeFound("endDate.specified=true");

        // Get all the financialYearSettingsList where endDate is null
        defaultFinancialYearSettingsShouldNotBeFound("endDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllFinancialYearSettingsByEndDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        financialYearSettingsRepository.saveAndFlush(financialYearSettings);

        // Get all the financialYearSettingsList where endDate greater than or equals to DEFAULT_END_DATE
        defaultFinancialYearSettingsShouldBeFound("endDate.greaterOrEqualThan=" + DEFAULT_END_DATE);

        // Get all the financialYearSettingsList where endDate greater than or equals to UPDATED_END_DATE
        defaultFinancialYearSettingsShouldNotBeFound("endDate.greaterOrEqualThan=" + UPDATED_END_DATE);
    }

    @Test
    @Transactional
    public void getAllFinancialYearSettingsByEndDateIsLessThanSomething() throws Exception {
        // Initialize the database
        financialYearSettingsRepository.saveAndFlush(financialYearSettings);

        // Get all the financialYearSettingsList where endDate less than or equals to DEFAULT_END_DATE
        defaultFinancialYearSettingsShouldNotBeFound("endDate.lessThan=" + DEFAULT_END_DATE);

        // Get all the financialYearSettingsList where endDate less than or equals to UPDATED_END_DATE
        defaultFinancialYearSettingsShouldBeFound("endDate.lessThan=" + UPDATED_END_DATE);
    }


    @Test
    @Transactional
    public void getAllFinancialYearSettingsByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        financialYearSettingsRepository.saveAndFlush(financialYearSettings);

        // Get all the financialYearSettingsList where status equals to DEFAULT_STATUS
        defaultFinancialYearSettingsShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the financialYearSettingsList where status equals to UPDATED_STATUS
        defaultFinancialYearSettingsShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllFinancialYearSettingsByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        financialYearSettingsRepository.saveAndFlush(financialYearSettings);

        // Get all the financialYearSettingsList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultFinancialYearSettingsShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the financialYearSettingsList where status equals to UPDATED_STATUS
        defaultFinancialYearSettingsShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllFinancialYearSettingsByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        financialYearSettingsRepository.saveAndFlush(financialYearSettings);

        // Get all the financialYearSettingsList where status is not null
        defaultFinancialYearSettingsShouldBeFound("status.specified=true");

        // Get all the financialYearSettingsList where status is null
        defaultFinancialYearSettingsShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllFinancialYearSettingsByStatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        financialYearSettingsRepository.saveAndFlush(financialYearSettings);

        // Get all the financialYearSettingsList where status greater than or equals to DEFAULT_STATUS
        defaultFinancialYearSettingsShouldBeFound("status.greaterOrEqualThan=" + DEFAULT_STATUS);

        // Get all the financialYearSettingsList where status greater than or equals to UPDATED_STATUS
        defaultFinancialYearSettingsShouldNotBeFound("status.greaterOrEqualThan=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllFinancialYearSettingsByStatusIsLessThanSomething() throws Exception {
        // Initialize the database
        financialYearSettingsRepository.saveAndFlush(financialYearSettings);

        // Get all the financialYearSettingsList where status less than or equals to DEFAULT_STATUS
        defaultFinancialYearSettingsShouldNotBeFound("status.lessThan=" + DEFAULT_STATUS);

        // Get all the financialYearSettingsList where status less than or equals to UPDATED_STATUS
        defaultFinancialYearSettingsShouldBeFound("status.lessThan=" + UPDATED_STATUS);
    }


    @Test
    @Transactional
    public void getAllFinancialYearSettingsByFinancialYearIsEqualToSomething() throws Exception {
        // Initialize the database
        PickListValue financialYear = PickListValueResourceIntTest.createEntity(em);
        em.persist(financialYear);
        em.flush();
        financialYearSettings.setFinancialYear(financialYear);
        financialYearSettingsRepository.saveAndFlush(financialYearSettings);
        Long financialYearId = financialYear.getId();

        // Get all the financialYearSettingsList where financialYear equals to financialYearId
        defaultFinancialYearSettingsShouldBeFound("financialYearId.equals=" + financialYearId);

        // Get all the financialYearSettingsList where financialYear equals to financialYearId + 1
        defaultFinancialYearSettingsShouldNotBeFound("financialYearId.equals=" + (financialYearId + 1));
    }


    @Test
    @Transactional
    public void getAllFinancialYearSettingsByZonalIsEqualToSomething() throws Exception {
        // Initialize the database
        Zonal zonal = ZonalResourceIntTest.createEntity(em);
        em.persist(zonal);
        em.flush();
        financialYearSettings.addZonal(zonal);
        financialYearSettingsRepository.saveAndFlush(financialYearSettings);
        Long zonalId = zonal.getId();

        // Get all the financialYearSettingsList where zonal equals to zonalId
        defaultFinancialYearSettingsShouldBeFound("zonalId.equals=" + zonalId);

        // Get all the financialYearSettingsList where zonal equals to zonalId + 1
        defaultFinancialYearSettingsShouldNotBeFound("zonalId.equals=" + (zonalId + 1));
    }


    @Test
    @Transactional
    public void getAllFinancialYearSettingsBySectorIsEqualToSomething() throws Exception {
        // Initialize the database
        Sector sector = SectorResourceIntTest.createEntity(em);
        em.persist(sector);
        em.flush();
        financialYearSettings.addSector(sector);
        financialYearSettingsRepository.saveAndFlush(financialYearSettings);
        Long sectorId = sector.getId();

        // Get all the financialYearSettingsList where sector equals to sectorId
        defaultFinancialYearSettingsShouldBeFound("sectorId.equals=" + sectorId);

        // Get all the financialYearSettingsList where sector equals to sectorId + 1
        defaultFinancialYearSettingsShouldNotBeFound("sectorId.equals=" + (sectorId + 1));
    }


    @Test
    @Transactional
    public void getAllFinancialYearSettingsByNurseryIsEqualToSomething() throws Exception {
        // Initialize the database
        Nursery nursery = NurseryResourceIntTest.createEntity(em);
        em.persist(nursery);
        em.flush();
        financialYearSettings.addNursery(nursery);
        financialYearSettingsRepository.saveAndFlush(financialYearSettings);
        Long nurseryId = nursery.getId();

        // Get all the financialYearSettingsList where nursery equals to nurseryId
        defaultFinancialYearSettingsShouldBeFound("nurseryId.equals=" + nurseryId);

        // Get all the financialYearSettingsList where nursery equals to nurseryId + 1
        defaultFinancialYearSettingsShouldNotBeFound("nurseryId.equals=" + (nurseryId + 1));
    }


    @Test
    @Transactional
    public void getAllFinancialYearSettingsByBatchIsEqualToSomething() throws Exception {
        // Initialize the database
        Batch batch = BatchResourceIntTest.createEntity(em);
        em.persist(batch);
        em.flush();
        financialYearSettings.addBatch(batch);
        financialYearSettingsRepository.saveAndFlush(financialYearSettings);
        Long batchId = batch.getId();

        // Get all the financialYearSettingsList where batch equals to batchId
        defaultFinancialYearSettingsShouldBeFound("batchId.equals=" + batchId);

        // Get all the financialYearSettingsList where batch equals to batchId + 1
        defaultFinancialYearSettingsShouldNotBeFound("batchId.equals=" + (batchId + 1));
    }


    @Test
    @Transactional
    public void getAllFinancialYearSettingsByDamageIsEqualToSomething() throws Exception {
        // Initialize the database
        Damage damage = DamageResourceIntTest.createEntity(em);
        em.persist(damage);
        em.flush();
        financialYearSettings.addDamage(damage);
        financialYearSettingsRepository.saveAndFlush(financialYearSettings);
        Long damageId = damage.getId();

        // Get all the financialYearSettingsList where damage equals to damageId
        defaultFinancialYearSettingsShouldBeFound("damageId.equals=" + damageId);

        // Get all the financialYearSettingsList where damage equals to damageId + 1
        defaultFinancialYearSettingsShouldNotBeFound("damageId.equals=" + (damageId + 1));
    }


    @Test
    @Transactional
    public void getAllFinancialYearSettingsByShadeAreaIsEqualToSomething() throws Exception {
        // Initialize the database
        ShadeArea shadeArea = ShadeAreaResourceIntTest.createEntity(em);
        em.persist(shadeArea);
        em.flush();
        financialYearSettings.addShadeArea(shadeArea);
        financialYearSettingsRepository.saveAndFlush(financialYearSettings);
        Long shadeAreaId = shadeArea.getId();

        // Get all the financialYearSettingsList where shadeArea equals to shadeAreaId
        defaultFinancialYearSettingsShouldBeFound("shadeAreaId.equals=" + shadeAreaId);

        // Get all the financialYearSettingsList where shadeArea equals to shadeAreaId + 1
        defaultFinancialYearSettingsShouldNotBeFound("shadeAreaId.equals=" + (shadeAreaId + 1));
    }


    @Test
    @Transactional
    public void getAllFinancialYearSettingsByNurseryStockIsEqualToSomething() throws Exception {
        // Initialize the database
        NurseryStock nurseryStock = NurseryStockResourceIntTest.createEntity(em);
        em.persist(nurseryStock);
        em.flush();
        financialYearSettings.addNurseryStock(nurseryStock);
        financialYearSettingsRepository.saveAndFlush(financialYearSettings);
        Long nurseryStockId = nurseryStock.getId();

        // Get all the financialYearSettingsList where nurseryStock equals to nurseryStockId
        defaultFinancialYearSettingsShouldBeFound("nurseryStockId.equals=" + nurseryStockId);

        // Get all the financialYearSettingsList where nurseryStock equals to nurseryStockId + 1
        defaultFinancialYearSettingsShouldNotBeFound("nurseryStockId.equals=" + (nurseryStockId + 1));
    }


    @Test
    @Transactional
    public void getAllFinancialYearSettingsByNurseryStockDetailsIsEqualToSomething() throws Exception {
        // Initialize the database
        NurseryStockDetails nurseryStockDetails = NurseryStockDetailsResourceIntTest.createEntity(em);
        em.persist(nurseryStockDetails);
        em.flush();
        financialYearSettings.addNurseryStockDetails(nurseryStockDetails);
        financialYearSettingsRepository.saveAndFlush(financialYearSettings);
        Long nurseryStockDetailsId = nurseryStockDetails.getId();

        // Get all the financialYearSettingsList where nurseryStockDetails equals to nurseryStockDetailsId
        defaultFinancialYearSettingsShouldBeFound("nurseryStockDetailsId.equals=" + nurseryStockDetailsId);

        // Get all the financialYearSettingsList where nurseryStockDetails equals to nurseryStockDetailsId + 1
        defaultFinancialYearSettingsShouldNotBeFound("nurseryStockDetailsId.equals=" + (nurseryStockDetailsId + 1));
    }


    @Test
    @Transactional
    public void getAllFinancialYearSettingsByGodownIsEqualToSomething() throws Exception {
        // Initialize the database
        Godown godown = GodownResourceIntTest.createEntity(em);
        em.persist(godown);
        em.flush();
        financialYearSettings.addGodown(godown);
        financialYearSettingsRepository.saveAndFlush(financialYearSettings);
        Long godownId = godown.getId();

        // Get all the financialYearSettingsList where godown equals to godownId
        defaultFinancialYearSettingsShouldBeFound("godownId.equals=" + godownId);

        // Get all the financialYearSettingsList where godown equals to godownId + 1
        defaultFinancialYearSettingsShouldNotBeFound("godownId.equals=" + (godownId + 1));
    }


    @Test
    @Transactional
    public void getAllFinancialYearSettingsByGodownStockIsEqualToSomething() throws Exception {
        // Initialize the database
        GodownStock godownStock = GodownStockResourceIntTest.createEntity(em);
        em.persist(godownStock);
        em.flush();
        financialYearSettings.addGodownStock(godownStock);
        financialYearSettingsRepository.saveAndFlush(financialYearSettings);
        Long godownStockId = godownStock.getId();

        // Get all the financialYearSettingsList where godownStock equals to godownStockId
        defaultFinancialYearSettingsShouldBeFound("godownStockId.equals=" + godownStockId);

        // Get all the financialYearSettingsList where godownStock equals to godownStockId + 1
        defaultFinancialYearSettingsShouldNotBeFound("godownStockId.equals=" + (godownStockId + 1));
    }


    @Test
    @Transactional
    public void getAllFinancialYearSettingsByGodownStockDetailsIsEqualToSomething() throws Exception {
        // Initialize the database
        GodownStockDetails godownStockDetails = GodownStockDetailsResourceIntTest.createEntity(em);
        em.persist(godownStockDetails);
        em.flush();
        financialYearSettings.addGodownStockDetails(godownStockDetails);
        financialYearSettingsRepository.saveAndFlush(financialYearSettings);
        Long godownStockDetailsId = godownStockDetails.getId();

        // Get all the financialYearSettingsList where godownStockDetails equals to godownStockDetailsId
        defaultFinancialYearSettingsShouldBeFound("godownStockDetailsId.equals=" + godownStockDetailsId);

        // Get all the financialYearSettingsList where godownStockDetails equals to godownStockDetailsId + 1
        defaultFinancialYearSettingsShouldNotBeFound("godownStockDetailsId.equals=" + (godownStockDetailsId + 1));
    }


    @Test
    @Transactional
    public void getAllFinancialYearSettingsByGodownPurchaseDetailsIsEqualToSomething() throws Exception {
        // Initialize the database
        GodownPurchaseDetails godownPurchaseDetails = GodownPurchaseDetailsResourceIntTest.createEntity(em);
        em.persist(godownPurchaseDetails);
        em.flush();
        financialYearSettings.addGodownPurchaseDetails(godownPurchaseDetails);
        financialYearSettingsRepository.saveAndFlush(financialYearSettings);
        Long godownPurchaseDetailsId = godownPurchaseDetails.getId();

        // Get all the financialYearSettingsList where godownPurchaseDetails equals to godownPurchaseDetailsId
        defaultFinancialYearSettingsShouldBeFound("godownPurchaseDetailsId.equals=" + godownPurchaseDetailsId);

        // Get all the financialYearSettingsList where godownPurchaseDetails equals to godownPurchaseDetailsId + 1
        defaultFinancialYearSettingsShouldNotBeFound("godownPurchaseDetailsId.equals=" + (godownPurchaseDetailsId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultFinancialYearSettingsShouldBeFound(String filter) throws Exception {
        restFinancialYearSettingsMockMvc.perform(get("/api/financial-year-settings?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(financialYearSettings.getId().intValue())))
            .andExpect(jsonPath("$.[*].batchName").value(hasItem(DEFAULT_BATCH_NAME.toString())))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultFinancialYearSettingsShouldNotBeFound(String filter) throws Exception {
        restFinancialYearSettingsMockMvc.perform(get("/api/financial-year-settings?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingFinancialYearSettings() throws Exception {
        // Get the financialYearSettings
        restFinancialYearSettingsMockMvc.perform(get("/api/financial-year-settings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFinancialYearSettings() throws Exception {
        // Initialize the database
        financialYearSettingsRepository.saveAndFlush(financialYearSettings);

        int databaseSizeBeforeUpdate = financialYearSettingsRepository.findAll().size();

        // Update the financialYearSettings
        FinancialYearSettings updatedFinancialYearSettings = financialYearSettingsRepository.findById(financialYearSettings.getId()).get();
        // Disconnect from session so that the updates on updatedFinancialYearSettings are not directly saved in db
        em.detach(updatedFinancialYearSettings);
        updatedFinancialYearSettings
            .batchName(UPDATED_BATCH_NAME)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .status(UPDATED_STATUS);
        FinancialYearSettingsDTO financialYearSettingsDTO = financialYearSettingsMapper.toDto(updatedFinancialYearSettings);

        restFinancialYearSettingsMockMvc.perform(put("/api/financial-year-settings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(financialYearSettingsDTO)))
            .andExpect(status().isOk());

        // Validate the FinancialYearSettings in the database
        List<FinancialYearSettings> financialYearSettingsList = financialYearSettingsRepository.findAll();
        assertThat(financialYearSettingsList).hasSize(databaseSizeBeforeUpdate);
        FinancialYearSettings testFinancialYearSettings = financialYearSettingsList.get(financialYearSettingsList.size() - 1);
        assertThat(testFinancialYearSettings.getBatchName()).isEqualTo(UPDATED_BATCH_NAME);
        assertThat(testFinancialYearSettings.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testFinancialYearSettings.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testFinancialYearSettings.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingFinancialYearSettings() throws Exception {
        int databaseSizeBeforeUpdate = financialYearSettingsRepository.findAll().size();

        // Create the FinancialYearSettings
        FinancialYearSettingsDTO financialYearSettingsDTO = financialYearSettingsMapper.toDto(financialYearSettings);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFinancialYearSettingsMockMvc.perform(put("/api/financial-year-settings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(financialYearSettingsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FinancialYearSettings in the database
        List<FinancialYearSettings> financialYearSettingsList = financialYearSettingsRepository.findAll();
        assertThat(financialYearSettingsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFinancialYearSettings() throws Exception {
        // Initialize the database
        financialYearSettingsRepository.saveAndFlush(financialYearSettings);

        int databaseSizeBeforeDelete = financialYearSettingsRepository.findAll().size();

        // Get the financialYearSettings
        restFinancialYearSettingsMockMvc.perform(delete("/api/financial-year-settings/{id}", financialYearSettings.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<FinancialYearSettings> financialYearSettingsList = financialYearSettingsRepository.findAll();
        assertThat(financialYearSettingsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FinancialYearSettings.class);
        FinancialYearSettings financialYearSettings1 = new FinancialYearSettings();
        financialYearSettings1.setId(1L);
        FinancialYearSettings financialYearSettings2 = new FinancialYearSettings();
        financialYearSettings2.setId(financialYearSettings1.getId());
        assertThat(financialYearSettings1).isEqualTo(financialYearSettings2);
        financialYearSettings2.setId(2L);
        assertThat(financialYearSettings1).isNotEqualTo(financialYearSettings2);
        financialYearSettings1.setId(null);
        assertThat(financialYearSettings1).isNotEqualTo(financialYearSettings2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FinancialYearSettingsDTO.class);
        FinancialYearSettingsDTO financialYearSettingsDTO1 = new FinancialYearSettingsDTO();
        financialYearSettingsDTO1.setId(1L);
        FinancialYearSettingsDTO financialYearSettingsDTO2 = new FinancialYearSettingsDTO();
        assertThat(financialYearSettingsDTO1).isNotEqualTo(financialYearSettingsDTO2);
        financialYearSettingsDTO2.setId(financialYearSettingsDTO1.getId());
        assertThat(financialYearSettingsDTO1).isEqualTo(financialYearSettingsDTO2);
        financialYearSettingsDTO2.setId(2L);
        assertThat(financialYearSettingsDTO1).isNotEqualTo(financialYearSettingsDTO2);
        financialYearSettingsDTO1.setId(null);
        assertThat(financialYearSettingsDTO1).isNotEqualTo(financialYearSettingsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(financialYearSettingsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(financialYearSettingsMapper.fromId(null)).isNull();
    }
}
