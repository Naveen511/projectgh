package com.niche.ng.web.rest;

import com.niche.ng.ProjectghApp;

import com.niche.ng.domain.ShadeArea;
import com.niche.ng.domain.Batch;
import com.niche.ng.domain.FinancialYearSettings;
import com.niche.ng.repository.ShadeAreaRepository;
import com.niche.ng.service.ShadeAreaService;
import com.niche.ng.service.dto.ShadeAreaDTO;
import com.niche.ng.service.mapper.ShadeAreaMapper;
import com.niche.ng.web.rest.errors.ExceptionTranslator;
import com.niche.ng.service.dto.ShadeAreaCriteria;
import com.niche.ng.service.ShadeAreaQueryService;

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
 * Test class for the ShadeAreaResource REST controller.
 *
 * @see ShadeAreaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectghApp.class)
public class ShadeAreaResourceIntTest {

    private static final Long DEFAULT_NO_OF_SEEDLINGS = 1L;
    private static final Long UPDATED_NO_OF_SEEDLINGS = 2L;

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final Integer DEFAULT_DAMAGE = 1;
    private static final Integer UPDATED_DAMAGE = 2;

    private static final Integer DEFAULT_SAPLINGS = 1;
    private static final Integer UPDATED_SAPLINGS = 2;

    @Autowired
    private ShadeAreaRepository shadeAreaRepository;


    @Autowired
    private ShadeAreaMapper shadeAreaMapper;
    

    @Autowired
    private ShadeAreaService shadeAreaService;

    @Autowired
    private ShadeAreaQueryService shadeAreaQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restShadeAreaMockMvc;

    private ShadeArea shadeArea;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ShadeAreaResource shadeAreaResource = new ShadeAreaResource(shadeAreaService, shadeAreaQueryService);
        this.restShadeAreaMockMvc = MockMvcBuilders.standaloneSetup(shadeAreaResource)
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
    public static ShadeArea createEntity(EntityManager em) {
        ShadeArea shadeArea = new ShadeArea()
            .noOfSeedlings(DEFAULT_NO_OF_SEEDLINGS)
            .date(DEFAULT_DATE)
            .status(DEFAULT_STATUS)
            .damage(DEFAULT_DAMAGE)
            .saplings(DEFAULT_SAPLINGS);
        return shadeArea;
    }

    @Before
    public void initTest() {
        shadeArea = createEntity(em);
    }

    @Test
    @Transactional
    public void createShadeArea() throws Exception {
        int databaseSizeBeforeCreate = shadeAreaRepository.findAll().size();

        // Create the ShadeArea
        ShadeAreaDTO shadeAreaDTO = shadeAreaMapper.toDto(shadeArea);
        restShadeAreaMockMvc.perform(post("/api/shade-areas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shadeAreaDTO)))
            .andExpect(status().isCreated());

        // Validate the ShadeArea in the database
        List<ShadeArea> shadeAreaList = shadeAreaRepository.findAll();
        assertThat(shadeAreaList).hasSize(databaseSizeBeforeCreate + 1);
        ShadeArea testShadeArea = shadeAreaList.get(shadeAreaList.size() - 1);
        assertThat(testShadeArea.getNoOfSeedlings()).isEqualTo(DEFAULT_NO_OF_SEEDLINGS);
        assertThat(testShadeArea.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testShadeArea.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testShadeArea.getDamage()).isEqualTo(DEFAULT_DAMAGE);
        assertThat(testShadeArea.getSaplings()).isEqualTo(DEFAULT_SAPLINGS);
    }

    @Test
    @Transactional
    public void createShadeAreaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = shadeAreaRepository.findAll().size();

        // Create the ShadeArea with an existing ID
        shadeArea.setId(1L);
        ShadeAreaDTO shadeAreaDTO = shadeAreaMapper.toDto(shadeArea);

        // An entity with an existing ID cannot be created, so this API call must fail
        restShadeAreaMockMvc.perform(post("/api/shade-areas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shadeAreaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ShadeArea in the database
        List<ShadeArea> shadeAreaList = shadeAreaRepository.findAll();
        assertThat(shadeAreaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNoOfSeedlingsIsRequired() throws Exception {
        int databaseSizeBeforeTest = shadeAreaRepository.findAll().size();
        // set the field null
        shadeArea.setNoOfSeedlings(null);

        // Create the ShadeArea, which fails.
        ShadeAreaDTO shadeAreaDTO = shadeAreaMapper.toDto(shadeArea);

        restShadeAreaMockMvc.perform(post("/api/shade-areas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shadeAreaDTO)))
            .andExpect(status().isBadRequest());

        List<ShadeArea> shadeAreaList = shadeAreaRepository.findAll();
        assertThat(shadeAreaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = shadeAreaRepository.findAll().size();
        // set the field null
        shadeArea.setDate(null);

        // Create the ShadeArea, which fails.
        ShadeAreaDTO shadeAreaDTO = shadeAreaMapper.toDto(shadeArea);

        restShadeAreaMockMvc.perform(post("/api/shade-areas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shadeAreaDTO)))
            .andExpect(status().isBadRequest());

        List<ShadeArea> shadeAreaList = shadeAreaRepository.findAll();
        assertThat(shadeAreaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllShadeAreas() throws Exception {
        // Initialize the database
        shadeAreaRepository.saveAndFlush(shadeArea);

        // Get all the shadeAreaList
        restShadeAreaMockMvc.perform(get("/api/shade-areas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(shadeArea.getId().intValue())))
            .andExpect(jsonPath("$.[*].noOfSeedlings").value(hasItem(DEFAULT_NO_OF_SEEDLINGS.intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].damage").value(hasItem(DEFAULT_DAMAGE)))
            .andExpect(jsonPath("$.[*].saplings").value(hasItem(DEFAULT_SAPLINGS)));
    }
    

    @Test
    @Transactional
    public void getShadeArea() throws Exception {
        // Initialize the database
        shadeAreaRepository.saveAndFlush(shadeArea);

        // Get the shadeArea
        restShadeAreaMockMvc.perform(get("/api/shade-areas/{id}", shadeArea.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(shadeArea.getId().intValue()))
            .andExpect(jsonPath("$.noOfSeedlings").value(DEFAULT_NO_OF_SEEDLINGS.intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.damage").value(DEFAULT_DAMAGE))
            .andExpect(jsonPath("$.saplings").value(DEFAULT_SAPLINGS));
    }

    @Test
    @Transactional
    public void getAllShadeAreasByNoOfSeedlingsIsEqualToSomething() throws Exception {
        // Initialize the database
        shadeAreaRepository.saveAndFlush(shadeArea);

        // Get all the shadeAreaList where noOfSeedlings equals to DEFAULT_NO_OF_SEEDLINGS
        defaultShadeAreaShouldBeFound("noOfSeedlings.equals=" + DEFAULT_NO_OF_SEEDLINGS);

        // Get all the shadeAreaList where noOfSeedlings equals to UPDATED_NO_OF_SEEDLINGS
        defaultShadeAreaShouldNotBeFound("noOfSeedlings.equals=" + UPDATED_NO_OF_SEEDLINGS);
    }

    @Test
    @Transactional
    public void getAllShadeAreasByNoOfSeedlingsIsInShouldWork() throws Exception {
        // Initialize the database
        shadeAreaRepository.saveAndFlush(shadeArea);

        // Get all the shadeAreaList where noOfSeedlings in DEFAULT_NO_OF_SEEDLINGS or UPDATED_NO_OF_SEEDLINGS
        defaultShadeAreaShouldBeFound("noOfSeedlings.in=" + DEFAULT_NO_OF_SEEDLINGS + "," + UPDATED_NO_OF_SEEDLINGS);

        // Get all the shadeAreaList where noOfSeedlings equals to UPDATED_NO_OF_SEEDLINGS
        defaultShadeAreaShouldNotBeFound("noOfSeedlings.in=" + UPDATED_NO_OF_SEEDLINGS);
    }

    @Test
    @Transactional
    public void getAllShadeAreasByNoOfSeedlingsIsNullOrNotNull() throws Exception {
        // Initialize the database
        shadeAreaRepository.saveAndFlush(shadeArea);

        // Get all the shadeAreaList where noOfSeedlings is not null
        defaultShadeAreaShouldBeFound("noOfSeedlings.specified=true");

        // Get all the shadeAreaList where noOfSeedlings is null
        defaultShadeAreaShouldNotBeFound("noOfSeedlings.specified=false");
    }

    @Test
    @Transactional
    public void getAllShadeAreasByNoOfSeedlingsIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        shadeAreaRepository.saveAndFlush(shadeArea);

        // Get all the shadeAreaList where noOfSeedlings greater than or equals to DEFAULT_NO_OF_SEEDLINGS
        defaultShadeAreaShouldBeFound("noOfSeedlings.greaterOrEqualThan=" + DEFAULT_NO_OF_SEEDLINGS);

        // Get all the shadeAreaList where noOfSeedlings greater than or equals to UPDATED_NO_OF_SEEDLINGS
        defaultShadeAreaShouldNotBeFound("noOfSeedlings.greaterOrEqualThan=" + UPDATED_NO_OF_SEEDLINGS);
    }

    @Test
    @Transactional
    public void getAllShadeAreasByNoOfSeedlingsIsLessThanSomething() throws Exception {
        // Initialize the database
        shadeAreaRepository.saveAndFlush(shadeArea);

        // Get all the shadeAreaList where noOfSeedlings less than or equals to DEFAULT_NO_OF_SEEDLINGS
        defaultShadeAreaShouldNotBeFound("noOfSeedlings.lessThan=" + DEFAULT_NO_OF_SEEDLINGS);

        // Get all the shadeAreaList where noOfSeedlings less than or equals to UPDATED_NO_OF_SEEDLINGS
        defaultShadeAreaShouldBeFound("noOfSeedlings.lessThan=" + UPDATED_NO_OF_SEEDLINGS);
    }


    @Test
    @Transactional
    public void getAllShadeAreasByDateIsEqualToSomething() throws Exception {
        // Initialize the database
        shadeAreaRepository.saveAndFlush(shadeArea);

        // Get all the shadeAreaList where date equals to DEFAULT_DATE
        defaultShadeAreaShouldBeFound("date.equals=" + DEFAULT_DATE);

        // Get all the shadeAreaList where date equals to UPDATED_DATE
        defaultShadeAreaShouldNotBeFound("date.equals=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    public void getAllShadeAreasByDateIsInShouldWork() throws Exception {
        // Initialize the database
        shadeAreaRepository.saveAndFlush(shadeArea);

        // Get all the shadeAreaList where date in DEFAULT_DATE or UPDATED_DATE
        defaultShadeAreaShouldBeFound("date.in=" + DEFAULT_DATE + "," + UPDATED_DATE);

        // Get all the shadeAreaList where date equals to UPDATED_DATE
        defaultShadeAreaShouldNotBeFound("date.in=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    public void getAllShadeAreasByDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        shadeAreaRepository.saveAndFlush(shadeArea);

        // Get all the shadeAreaList where date is not null
        defaultShadeAreaShouldBeFound("date.specified=true");

        // Get all the shadeAreaList where date is null
        defaultShadeAreaShouldNotBeFound("date.specified=false");
    }

    @Test
    @Transactional
    public void getAllShadeAreasByDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        shadeAreaRepository.saveAndFlush(shadeArea);

        // Get all the shadeAreaList where date greater than or equals to DEFAULT_DATE
        defaultShadeAreaShouldBeFound("date.greaterOrEqualThan=" + DEFAULT_DATE);

        // Get all the shadeAreaList where date greater than or equals to UPDATED_DATE
        defaultShadeAreaShouldNotBeFound("date.greaterOrEqualThan=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    public void getAllShadeAreasByDateIsLessThanSomething() throws Exception {
        // Initialize the database
        shadeAreaRepository.saveAndFlush(shadeArea);

        // Get all the shadeAreaList where date less than or equals to DEFAULT_DATE
        defaultShadeAreaShouldNotBeFound("date.lessThan=" + DEFAULT_DATE);

        // Get all the shadeAreaList where date less than or equals to UPDATED_DATE
        defaultShadeAreaShouldBeFound("date.lessThan=" + UPDATED_DATE);
    }


    @Test
    @Transactional
    public void getAllShadeAreasByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        shadeAreaRepository.saveAndFlush(shadeArea);

        // Get all the shadeAreaList where status equals to DEFAULT_STATUS
        defaultShadeAreaShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the shadeAreaList where status equals to UPDATED_STATUS
        defaultShadeAreaShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllShadeAreasByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        shadeAreaRepository.saveAndFlush(shadeArea);

        // Get all the shadeAreaList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultShadeAreaShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the shadeAreaList where status equals to UPDATED_STATUS
        defaultShadeAreaShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllShadeAreasByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        shadeAreaRepository.saveAndFlush(shadeArea);

        // Get all the shadeAreaList where status is not null
        defaultShadeAreaShouldBeFound("status.specified=true");

        // Get all the shadeAreaList where status is null
        defaultShadeAreaShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllShadeAreasByStatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        shadeAreaRepository.saveAndFlush(shadeArea);

        // Get all the shadeAreaList where status greater than or equals to DEFAULT_STATUS
        defaultShadeAreaShouldBeFound("status.greaterOrEqualThan=" + DEFAULT_STATUS);

        // Get all the shadeAreaList where status greater than or equals to UPDATED_STATUS
        defaultShadeAreaShouldNotBeFound("status.greaterOrEqualThan=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllShadeAreasByStatusIsLessThanSomething() throws Exception {
        // Initialize the database
        shadeAreaRepository.saveAndFlush(shadeArea);

        // Get all the shadeAreaList where status less than or equals to DEFAULT_STATUS
        defaultShadeAreaShouldNotBeFound("status.lessThan=" + DEFAULT_STATUS);

        // Get all the shadeAreaList where status less than or equals to UPDATED_STATUS
        defaultShadeAreaShouldBeFound("status.lessThan=" + UPDATED_STATUS);
    }


    @Test
    @Transactional
    public void getAllShadeAreasByDamageIsEqualToSomething() throws Exception {
        // Initialize the database
        shadeAreaRepository.saveAndFlush(shadeArea);

        // Get all the shadeAreaList where damage equals to DEFAULT_DAMAGE
        defaultShadeAreaShouldBeFound("damage.equals=" + DEFAULT_DAMAGE);

        // Get all the shadeAreaList where damage equals to UPDATED_DAMAGE
        defaultShadeAreaShouldNotBeFound("damage.equals=" + UPDATED_DAMAGE);
    }

    @Test
    @Transactional
    public void getAllShadeAreasByDamageIsInShouldWork() throws Exception {
        // Initialize the database
        shadeAreaRepository.saveAndFlush(shadeArea);

        // Get all the shadeAreaList where damage in DEFAULT_DAMAGE or UPDATED_DAMAGE
        defaultShadeAreaShouldBeFound("damage.in=" + DEFAULT_DAMAGE + "," + UPDATED_DAMAGE);

        // Get all the shadeAreaList where damage equals to UPDATED_DAMAGE
        defaultShadeAreaShouldNotBeFound("damage.in=" + UPDATED_DAMAGE);
    }

    @Test
    @Transactional
    public void getAllShadeAreasByDamageIsNullOrNotNull() throws Exception {
        // Initialize the database
        shadeAreaRepository.saveAndFlush(shadeArea);

        // Get all the shadeAreaList where damage is not null
        defaultShadeAreaShouldBeFound("damage.specified=true");

        // Get all the shadeAreaList where damage is null
        defaultShadeAreaShouldNotBeFound("damage.specified=false");
    }

    @Test
    @Transactional
    public void getAllShadeAreasByDamageIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        shadeAreaRepository.saveAndFlush(shadeArea);

        // Get all the shadeAreaList where damage greater than or equals to DEFAULT_DAMAGE
        defaultShadeAreaShouldBeFound("damage.greaterOrEqualThan=" + DEFAULT_DAMAGE);

        // Get all the shadeAreaList where damage greater than or equals to UPDATED_DAMAGE
        defaultShadeAreaShouldNotBeFound("damage.greaterOrEqualThan=" + UPDATED_DAMAGE);
    }

    @Test
    @Transactional
    public void getAllShadeAreasByDamageIsLessThanSomething() throws Exception {
        // Initialize the database
        shadeAreaRepository.saveAndFlush(shadeArea);

        // Get all the shadeAreaList where damage less than or equals to DEFAULT_DAMAGE
        defaultShadeAreaShouldNotBeFound("damage.lessThan=" + DEFAULT_DAMAGE);

        // Get all the shadeAreaList where damage less than or equals to UPDATED_DAMAGE
        defaultShadeAreaShouldBeFound("damage.lessThan=" + UPDATED_DAMAGE);
    }


    @Test
    @Transactional
    public void getAllShadeAreasBySaplingsIsEqualToSomething() throws Exception {
        // Initialize the database
        shadeAreaRepository.saveAndFlush(shadeArea);

        // Get all the shadeAreaList where saplings equals to DEFAULT_SAPLINGS
        defaultShadeAreaShouldBeFound("saplings.equals=" + DEFAULT_SAPLINGS);

        // Get all the shadeAreaList where saplings equals to UPDATED_SAPLINGS
        defaultShadeAreaShouldNotBeFound("saplings.equals=" + UPDATED_SAPLINGS);
    }

    @Test
    @Transactional
    public void getAllShadeAreasBySaplingsIsInShouldWork() throws Exception {
        // Initialize the database
        shadeAreaRepository.saveAndFlush(shadeArea);

        // Get all the shadeAreaList where saplings in DEFAULT_SAPLINGS or UPDATED_SAPLINGS
        defaultShadeAreaShouldBeFound("saplings.in=" + DEFAULT_SAPLINGS + "," + UPDATED_SAPLINGS);

        // Get all the shadeAreaList where saplings equals to UPDATED_SAPLINGS
        defaultShadeAreaShouldNotBeFound("saplings.in=" + UPDATED_SAPLINGS);
    }

    @Test
    @Transactional
    public void getAllShadeAreasBySaplingsIsNullOrNotNull() throws Exception {
        // Initialize the database
        shadeAreaRepository.saveAndFlush(shadeArea);

        // Get all the shadeAreaList where saplings is not null
        defaultShadeAreaShouldBeFound("saplings.specified=true");

        // Get all the shadeAreaList where saplings is null
        defaultShadeAreaShouldNotBeFound("saplings.specified=false");
    }

    @Test
    @Transactional
    public void getAllShadeAreasBySaplingsIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        shadeAreaRepository.saveAndFlush(shadeArea);

        // Get all the shadeAreaList where saplings greater than or equals to DEFAULT_SAPLINGS
        defaultShadeAreaShouldBeFound("saplings.greaterOrEqualThan=" + DEFAULT_SAPLINGS);

        // Get all the shadeAreaList where saplings greater than or equals to UPDATED_SAPLINGS
        defaultShadeAreaShouldNotBeFound("saplings.greaterOrEqualThan=" + UPDATED_SAPLINGS);
    }

    @Test
    @Transactional
    public void getAllShadeAreasBySaplingsIsLessThanSomething() throws Exception {
        // Initialize the database
        shadeAreaRepository.saveAndFlush(shadeArea);

        // Get all the shadeAreaList where saplings less than or equals to DEFAULT_SAPLINGS
        defaultShadeAreaShouldNotBeFound("saplings.lessThan=" + DEFAULT_SAPLINGS);

        // Get all the shadeAreaList where saplings less than or equals to UPDATED_SAPLINGS
        defaultShadeAreaShouldBeFound("saplings.lessThan=" + UPDATED_SAPLINGS);
    }


    @Test
    @Transactional
    public void getAllShadeAreasByBatchIsEqualToSomething() throws Exception {
        // Initialize the database
        Batch batch = BatchResourceIntTest.createEntity(em);
        em.persist(batch);
        em.flush();
        shadeArea.setBatch(batch);
        shadeAreaRepository.saveAndFlush(shadeArea);
        Long batchId = batch.getId();

        // Get all the shadeAreaList where batch equals to batchId
        defaultShadeAreaShouldBeFound("batchId.equals=" + batchId);

        // Get all the shadeAreaList where batch equals to batchId + 1
        defaultShadeAreaShouldNotBeFound("batchId.equals=" + (batchId + 1));
    }


    @Test
    @Transactional
    public void getAllShadeAreasByFinancialYearShadeAreaIsEqualToSomething() throws Exception {
        // Initialize the database
        FinancialYearSettings financialYearShadeArea = FinancialYearSettingsResourceIntTest.createEntity(em);
        em.persist(financialYearShadeArea);
        em.flush();
        shadeArea.setFinancialYearShadeArea(financialYearShadeArea);
        shadeAreaRepository.saveAndFlush(shadeArea);
        Long financialYearShadeAreaId = financialYearShadeArea.getId();

        // Get all the shadeAreaList where financialYearShadeArea equals to financialYearShadeAreaId
        defaultShadeAreaShouldBeFound("financialYearShadeAreaId.equals=" + financialYearShadeAreaId);

        // Get all the shadeAreaList where financialYearShadeArea equals to financialYearShadeAreaId + 1
        defaultShadeAreaShouldNotBeFound("financialYearShadeAreaId.equals=" + (financialYearShadeAreaId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultShadeAreaShouldBeFound(String filter) throws Exception {
        restShadeAreaMockMvc.perform(get("/api/shade-areas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(shadeArea.getId().intValue())))
            .andExpect(jsonPath("$.[*].noOfSeedlings").value(hasItem(DEFAULT_NO_OF_SEEDLINGS.intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].damage").value(hasItem(DEFAULT_DAMAGE)))
            .andExpect(jsonPath("$.[*].saplings").value(hasItem(DEFAULT_SAPLINGS)));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultShadeAreaShouldNotBeFound(String filter) throws Exception {
        restShadeAreaMockMvc.perform(get("/api/shade-areas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingShadeArea() throws Exception {
        // Get the shadeArea
        restShadeAreaMockMvc.perform(get("/api/shade-areas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateShadeArea() throws Exception {
        // Initialize the database
        shadeAreaRepository.saveAndFlush(shadeArea);

        int databaseSizeBeforeUpdate = shadeAreaRepository.findAll().size();

        // Update the shadeArea
        ShadeArea updatedShadeArea = shadeAreaRepository.findById(shadeArea.getId()).get();
        // Disconnect from session so that the updates on updatedShadeArea are not directly saved in db
        em.detach(updatedShadeArea);
        updatedShadeArea
            .noOfSeedlings(UPDATED_NO_OF_SEEDLINGS)
            .date(UPDATED_DATE)
            .status(UPDATED_STATUS)
            .damage(UPDATED_DAMAGE)
            .saplings(UPDATED_SAPLINGS);
        ShadeAreaDTO shadeAreaDTO = shadeAreaMapper.toDto(updatedShadeArea);

        restShadeAreaMockMvc.perform(put("/api/shade-areas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shadeAreaDTO)))
            .andExpect(status().isOk());

        // Validate the ShadeArea in the database
        List<ShadeArea> shadeAreaList = shadeAreaRepository.findAll();
        assertThat(shadeAreaList).hasSize(databaseSizeBeforeUpdate);
        ShadeArea testShadeArea = shadeAreaList.get(shadeAreaList.size() - 1);
        assertThat(testShadeArea.getNoOfSeedlings()).isEqualTo(UPDATED_NO_OF_SEEDLINGS);
        assertThat(testShadeArea.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testShadeArea.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testShadeArea.getDamage()).isEqualTo(UPDATED_DAMAGE);
        assertThat(testShadeArea.getSaplings()).isEqualTo(UPDATED_SAPLINGS);
    }

    @Test
    @Transactional
    public void updateNonExistingShadeArea() throws Exception {
        int databaseSizeBeforeUpdate = shadeAreaRepository.findAll().size();

        // Create the ShadeArea
        ShadeAreaDTO shadeAreaDTO = shadeAreaMapper.toDto(shadeArea);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restShadeAreaMockMvc.perform(put("/api/shade-areas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shadeAreaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ShadeArea in the database
        List<ShadeArea> shadeAreaList = shadeAreaRepository.findAll();
        assertThat(shadeAreaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteShadeArea() throws Exception {
        // Initialize the database
        shadeAreaRepository.saveAndFlush(shadeArea);

        int databaseSizeBeforeDelete = shadeAreaRepository.findAll().size();

        // Get the shadeArea
        restShadeAreaMockMvc.perform(delete("/api/shade-areas/{id}", shadeArea.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ShadeArea> shadeAreaList = shadeAreaRepository.findAll();
        assertThat(shadeAreaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ShadeArea.class);
        ShadeArea shadeArea1 = new ShadeArea();
        shadeArea1.setId(1L);
        ShadeArea shadeArea2 = new ShadeArea();
        shadeArea2.setId(shadeArea1.getId());
        assertThat(shadeArea1).isEqualTo(shadeArea2);
        shadeArea2.setId(2L);
        assertThat(shadeArea1).isNotEqualTo(shadeArea2);
        shadeArea1.setId(null);
        assertThat(shadeArea1).isNotEqualTo(shadeArea2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ShadeAreaDTO.class);
        ShadeAreaDTO shadeAreaDTO1 = new ShadeAreaDTO();
        shadeAreaDTO1.setId(1L);
        ShadeAreaDTO shadeAreaDTO2 = new ShadeAreaDTO();
        assertThat(shadeAreaDTO1).isNotEqualTo(shadeAreaDTO2);
        shadeAreaDTO2.setId(shadeAreaDTO1.getId());
        assertThat(shadeAreaDTO1).isEqualTo(shadeAreaDTO2);
        shadeAreaDTO2.setId(2L);
        assertThat(shadeAreaDTO1).isNotEqualTo(shadeAreaDTO2);
        shadeAreaDTO1.setId(null);
        assertThat(shadeAreaDTO1).isNotEqualTo(shadeAreaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(shadeAreaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(shadeAreaMapper.fromId(null)).isNull();
    }
}
