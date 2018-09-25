package com.niche.ng.web.rest;

import com.niche.ng.ProjectghApp;

import com.niche.ng.domain.CoverFilling;
import com.niche.ng.domain.CoverFillingDetails;
import com.niche.ng.repository.CoverFillingRepository;
import com.niche.ng.service.CoverFillingService;
import com.niche.ng.service.dto.CoverFillingDTO;
import com.niche.ng.service.mapper.CoverFillingMapper;
import com.niche.ng.web.rest.errors.ExceptionTranslator;
import com.niche.ng.service.dto.CoverFillingCriteria;
import com.niche.ng.service.CoverFillingQueryService;

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
 * Test class for the CoverFillingResource REST controller.
 *
 * @see CoverFillingResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectghApp.class)
public class CoverFillingResourceIntTest {

    private static final Integer DEFAULT_NO_OF_COVER = 1;
    private static final Integer UPDATED_NO_OF_COVER = 2;

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final Integer DEFAULT_DAMAGE_QUANTITY = 1;
    private static final Integer UPDATED_DAMAGE_QUANTITY = 2;

    @Autowired
    private CoverFillingRepository coverFillingRepository;


    @Autowired
    private CoverFillingMapper coverFillingMapper;
    

    @Autowired
    private CoverFillingService coverFillingService;

    @Autowired
    private CoverFillingQueryService coverFillingQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCoverFillingMockMvc;

    private CoverFilling coverFilling;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CoverFillingResource coverFillingResource = new CoverFillingResource(coverFillingService, coverFillingQueryService);
        this.restCoverFillingMockMvc = MockMvcBuilders.standaloneSetup(coverFillingResource)
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
    public static CoverFilling createEntity(EntityManager em) {
        CoverFilling coverFilling = new CoverFilling()
            .noOfCover(DEFAULT_NO_OF_COVER)
            .date(DEFAULT_DATE)
            .description(DEFAULT_DESCRIPTION)
            .status(DEFAULT_STATUS)
            .damageQuantity(DEFAULT_DAMAGE_QUANTITY);
        return coverFilling;
    }

    @Before
    public void initTest() {
        coverFilling = createEntity(em);
    }

    @Test
    @Transactional
    public void createCoverFilling() throws Exception {
        int databaseSizeBeforeCreate = coverFillingRepository.findAll().size();

        // Create the CoverFilling
        CoverFillingDTO coverFillingDTO = coverFillingMapper.toDto(coverFilling);
        restCoverFillingMockMvc.perform(post("/api/cover-fillings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coverFillingDTO)))
            .andExpect(status().isCreated());

        // Validate the CoverFilling in the database
        List<CoverFilling> coverFillingList = coverFillingRepository.findAll();
        assertThat(coverFillingList).hasSize(databaseSizeBeforeCreate + 1);
        CoverFilling testCoverFilling = coverFillingList.get(coverFillingList.size() - 1);
        assertThat(testCoverFilling.getNoOfCover()).isEqualTo(DEFAULT_NO_OF_COVER);
        assertThat(testCoverFilling.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testCoverFilling.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testCoverFilling.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testCoverFilling.getDamageQuantity()).isEqualTo(DEFAULT_DAMAGE_QUANTITY);
    }

    @Test
    @Transactional
    public void createCoverFillingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = coverFillingRepository.findAll().size();

        // Create the CoverFilling with an existing ID
        coverFilling.setId(1L);
        CoverFillingDTO coverFillingDTO = coverFillingMapper.toDto(coverFilling);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCoverFillingMockMvc.perform(post("/api/cover-fillings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coverFillingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CoverFilling in the database
        List<CoverFilling> coverFillingList = coverFillingRepository.findAll();
        assertThat(coverFillingList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNoOfCoverIsRequired() throws Exception {
        int databaseSizeBeforeTest = coverFillingRepository.findAll().size();
        // set the field null
        coverFilling.setNoOfCover(null);

        // Create the CoverFilling, which fails.
        CoverFillingDTO coverFillingDTO = coverFillingMapper.toDto(coverFilling);

        restCoverFillingMockMvc.perform(post("/api/cover-fillings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coverFillingDTO)))
            .andExpect(status().isBadRequest());

        List<CoverFilling> coverFillingList = coverFillingRepository.findAll();
        assertThat(coverFillingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = coverFillingRepository.findAll().size();
        // set the field null
        coverFilling.setDate(null);

        // Create the CoverFilling, which fails.
        CoverFillingDTO coverFillingDTO = coverFillingMapper.toDto(coverFilling);

        restCoverFillingMockMvc.perform(post("/api/cover-fillings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coverFillingDTO)))
            .andExpect(status().isBadRequest());

        List<CoverFilling> coverFillingList = coverFillingRepository.findAll();
        assertThat(coverFillingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCoverFillings() throws Exception {
        // Initialize the database
        coverFillingRepository.saveAndFlush(coverFilling);

        // Get all the coverFillingList
        restCoverFillingMockMvc.perform(get("/api/cover-fillings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(coverFilling.getId().intValue())))
            .andExpect(jsonPath("$.[*].noOfCover").value(hasItem(DEFAULT_NO_OF_COVER)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].damageQuantity").value(hasItem(DEFAULT_DAMAGE_QUANTITY)));
    }
    

    @Test
    @Transactional
    public void getCoverFilling() throws Exception {
        // Initialize the database
        coverFillingRepository.saveAndFlush(coverFilling);

        // Get the coverFilling
        restCoverFillingMockMvc.perform(get("/api/cover-fillings/{id}", coverFilling.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(coverFilling.getId().intValue()))
            .andExpect(jsonPath("$.noOfCover").value(DEFAULT_NO_OF_COVER))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.damageQuantity").value(DEFAULT_DAMAGE_QUANTITY));
    }

    @Test
    @Transactional
    public void getAllCoverFillingsByNoOfCoverIsEqualToSomething() throws Exception {
        // Initialize the database
        coverFillingRepository.saveAndFlush(coverFilling);

        // Get all the coverFillingList where noOfCover equals to DEFAULT_NO_OF_COVER
        defaultCoverFillingShouldBeFound("noOfCover.equals=" + DEFAULT_NO_OF_COVER);

        // Get all the coverFillingList where noOfCover equals to UPDATED_NO_OF_COVER
        defaultCoverFillingShouldNotBeFound("noOfCover.equals=" + UPDATED_NO_OF_COVER);
    }

    @Test
    @Transactional
    public void getAllCoverFillingsByNoOfCoverIsInShouldWork() throws Exception {
        // Initialize the database
        coverFillingRepository.saveAndFlush(coverFilling);

        // Get all the coverFillingList where noOfCover in DEFAULT_NO_OF_COVER or UPDATED_NO_OF_COVER
        defaultCoverFillingShouldBeFound("noOfCover.in=" + DEFAULT_NO_OF_COVER + "," + UPDATED_NO_OF_COVER);

        // Get all the coverFillingList where noOfCover equals to UPDATED_NO_OF_COVER
        defaultCoverFillingShouldNotBeFound("noOfCover.in=" + UPDATED_NO_OF_COVER);
    }

    @Test
    @Transactional
    public void getAllCoverFillingsByNoOfCoverIsNullOrNotNull() throws Exception {
        // Initialize the database
        coverFillingRepository.saveAndFlush(coverFilling);

        // Get all the coverFillingList where noOfCover is not null
        defaultCoverFillingShouldBeFound("noOfCover.specified=true");

        // Get all the coverFillingList where noOfCover is null
        defaultCoverFillingShouldNotBeFound("noOfCover.specified=false");
    }

    @Test
    @Transactional
    public void getAllCoverFillingsByNoOfCoverIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        coverFillingRepository.saveAndFlush(coverFilling);

        // Get all the coverFillingList where noOfCover greater than or equals to DEFAULT_NO_OF_COVER
        defaultCoverFillingShouldBeFound("noOfCover.greaterOrEqualThan=" + DEFAULT_NO_OF_COVER);

        // Get all the coverFillingList where noOfCover greater than or equals to UPDATED_NO_OF_COVER
        defaultCoverFillingShouldNotBeFound("noOfCover.greaterOrEqualThan=" + UPDATED_NO_OF_COVER);
    }

    @Test
    @Transactional
    public void getAllCoverFillingsByNoOfCoverIsLessThanSomething() throws Exception {
        // Initialize the database
        coverFillingRepository.saveAndFlush(coverFilling);

        // Get all the coverFillingList where noOfCover less than or equals to DEFAULT_NO_OF_COVER
        defaultCoverFillingShouldNotBeFound("noOfCover.lessThan=" + DEFAULT_NO_OF_COVER);

        // Get all the coverFillingList where noOfCover less than or equals to UPDATED_NO_OF_COVER
        defaultCoverFillingShouldBeFound("noOfCover.lessThan=" + UPDATED_NO_OF_COVER);
    }


    @Test
    @Transactional
    public void getAllCoverFillingsByDateIsEqualToSomething() throws Exception {
        // Initialize the database
        coverFillingRepository.saveAndFlush(coverFilling);

        // Get all the coverFillingList where date equals to DEFAULT_DATE
        defaultCoverFillingShouldBeFound("date.equals=" + DEFAULT_DATE);

        // Get all the coverFillingList where date equals to UPDATED_DATE
        defaultCoverFillingShouldNotBeFound("date.equals=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    public void getAllCoverFillingsByDateIsInShouldWork() throws Exception {
        // Initialize the database
        coverFillingRepository.saveAndFlush(coverFilling);

        // Get all the coverFillingList where date in DEFAULT_DATE or UPDATED_DATE
        defaultCoverFillingShouldBeFound("date.in=" + DEFAULT_DATE + "," + UPDATED_DATE);

        // Get all the coverFillingList where date equals to UPDATED_DATE
        defaultCoverFillingShouldNotBeFound("date.in=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    public void getAllCoverFillingsByDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        coverFillingRepository.saveAndFlush(coverFilling);

        // Get all the coverFillingList where date is not null
        defaultCoverFillingShouldBeFound("date.specified=true");

        // Get all the coverFillingList where date is null
        defaultCoverFillingShouldNotBeFound("date.specified=false");
    }

    @Test
    @Transactional
    public void getAllCoverFillingsByDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        coverFillingRepository.saveAndFlush(coverFilling);

        // Get all the coverFillingList where date greater than or equals to DEFAULT_DATE
        defaultCoverFillingShouldBeFound("date.greaterOrEqualThan=" + DEFAULT_DATE);

        // Get all the coverFillingList where date greater than or equals to UPDATED_DATE
        defaultCoverFillingShouldNotBeFound("date.greaterOrEqualThan=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    public void getAllCoverFillingsByDateIsLessThanSomething() throws Exception {
        // Initialize the database
        coverFillingRepository.saveAndFlush(coverFilling);

        // Get all the coverFillingList where date less than or equals to DEFAULT_DATE
        defaultCoverFillingShouldNotBeFound("date.lessThan=" + DEFAULT_DATE);

        // Get all the coverFillingList where date less than or equals to UPDATED_DATE
        defaultCoverFillingShouldBeFound("date.lessThan=" + UPDATED_DATE);
    }


    @Test
    @Transactional
    public void getAllCoverFillingsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        coverFillingRepository.saveAndFlush(coverFilling);

        // Get all the coverFillingList where description equals to DEFAULT_DESCRIPTION
        defaultCoverFillingShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the coverFillingList where description equals to UPDATED_DESCRIPTION
        defaultCoverFillingShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllCoverFillingsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        coverFillingRepository.saveAndFlush(coverFilling);

        // Get all the coverFillingList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultCoverFillingShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the coverFillingList where description equals to UPDATED_DESCRIPTION
        defaultCoverFillingShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllCoverFillingsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        coverFillingRepository.saveAndFlush(coverFilling);

        // Get all the coverFillingList where description is not null
        defaultCoverFillingShouldBeFound("description.specified=true");

        // Get all the coverFillingList where description is null
        defaultCoverFillingShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllCoverFillingsByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        coverFillingRepository.saveAndFlush(coverFilling);

        // Get all the coverFillingList where status equals to DEFAULT_STATUS
        defaultCoverFillingShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the coverFillingList where status equals to UPDATED_STATUS
        defaultCoverFillingShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllCoverFillingsByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        coverFillingRepository.saveAndFlush(coverFilling);

        // Get all the coverFillingList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultCoverFillingShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the coverFillingList where status equals to UPDATED_STATUS
        defaultCoverFillingShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllCoverFillingsByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        coverFillingRepository.saveAndFlush(coverFilling);

        // Get all the coverFillingList where status is not null
        defaultCoverFillingShouldBeFound("status.specified=true");

        // Get all the coverFillingList where status is null
        defaultCoverFillingShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllCoverFillingsByStatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        coverFillingRepository.saveAndFlush(coverFilling);

        // Get all the coverFillingList where status greater than or equals to DEFAULT_STATUS
        defaultCoverFillingShouldBeFound("status.greaterOrEqualThan=" + DEFAULT_STATUS);

        // Get all the coverFillingList where status greater than or equals to UPDATED_STATUS
        defaultCoverFillingShouldNotBeFound("status.greaterOrEqualThan=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllCoverFillingsByStatusIsLessThanSomething() throws Exception {
        // Initialize the database
        coverFillingRepository.saveAndFlush(coverFilling);

        // Get all the coverFillingList where status less than or equals to DEFAULT_STATUS
        defaultCoverFillingShouldNotBeFound("status.lessThan=" + DEFAULT_STATUS);

        // Get all the coverFillingList where status less than or equals to UPDATED_STATUS
        defaultCoverFillingShouldBeFound("status.lessThan=" + UPDATED_STATUS);
    }


    @Test
    @Transactional
    public void getAllCoverFillingsByDamageQuantityIsEqualToSomething() throws Exception {
        // Initialize the database
        coverFillingRepository.saveAndFlush(coverFilling);

        // Get all the coverFillingList where damageQuantity equals to DEFAULT_DAMAGE_QUANTITY
        defaultCoverFillingShouldBeFound("damageQuantity.equals=" + DEFAULT_DAMAGE_QUANTITY);

        // Get all the coverFillingList where damageQuantity equals to UPDATED_DAMAGE_QUANTITY
        defaultCoverFillingShouldNotBeFound("damageQuantity.equals=" + UPDATED_DAMAGE_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllCoverFillingsByDamageQuantityIsInShouldWork() throws Exception {
        // Initialize the database
        coverFillingRepository.saveAndFlush(coverFilling);

        // Get all the coverFillingList where damageQuantity in DEFAULT_DAMAGE_QUANTITY or UPDATED_DAMAGE_QUANTITY
        defaultCoverFillingShouldBeFound("damageQuantity.in=" + DEFAULT_DAMAGE_QUANTITY + "," + UPDATED_DAMAGE_QUANTITY);

        // Get all the coverFillingList where damageQuantity equals to UPDATED_DAMAGE_QUANTITY
        defaultCoverFillingShouldNotBeFound("damageQuantity.in=" + UPDATED_DAMAGE_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllCoverFillingsByDamageQuantityIsNullOrNotNull() throws Exception {
        // Initialize the database
        coverFillingRepository.saveAndFlush(coverFilling);

        // Get all the coverFillingList where damageQuantity is not null
        defaultCoverFillingShouldBeFound("damageQuantity.specified=true");

        // Get all the coverFillingList where damageQuantity is null
        defaultCoverFillingShouldNotBeFound("damageQuantity.specified=false");
    }

    @Test
    @Transactional
    public void getAllCoverFillingsByDamageQuantityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        coverFillingRepository.saveAndFlush(coverFilling);

        // Get all the coverFillingList where damageQuantity greater than or equals to DEFAULT_DAMAGE_QUANTITY
        defaultCoverFillingShouldBeFound("damageQuantity.greaterOrEqualThan=" + DEFAULT_DAMAGE_QUANTITY);

        // Get all the coverFillingList where damageQuantity greater than or equals to UPDATED_DAMAGE_QUANTITY
        defaultCoverFillingShouldNotBeFound("damageQuantity.greaterOrEqualThan=" + UPDATED_DAMAGE_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllCoverFillingsByDamageQuantityIsLessThanSomething() throws Exception {
        // Initialize the database
        coverFillingRepository.saveAndFlush(coverFilling);

        // Get all the coverFillingList where damageQuantity less than or equals to DEFAULT_DAMAGE_QUANTITY
        defaultCoverFillingShouldNotBeFound("damageQuantity.lessThan=" + DEFAULT_DAMAGE_QUANTITY);

        // Get all the coverFillingList where damageQuantity less than or equals to UPDATED_DAMAGE_QUANTITY
        defaultCoverFillingShouldBeFound("damageQuantity.lessThan=" + UPDATED_DAMAGE_QUANTITY);
    }


    @Test
    @Transactional
    public void getAllCoverFillingsByCoverFillingDetailsIsEqualToSomething() throws Exception {
        // Initialize the database
        CoverFillingDetails coverFillingDetails = CoverFillingDetailsResourceIntTest.createEntity(em);
        em.persist(coverFillingDetails);
        em.flush();
        coverFilling.addCoverFillingDetails(coverFillingDetails);
        coverFillingRepository.saveAndFlush(coverFilling);
        Long coverFillingDetailsId = coverFillingDetails.getId();

        // Get all the coverFillingList where coverFillingDetails equals to coverFillingDetailsId
        defaultCoverFillingShouldBeFound("coverFillingDetailsId.equals=" + coverFillingDetailsId);

        // Get all the coverFillingList where coverFillingDetails equals to coverFillingDetailsId + 1
        defaultCoverFillingShouldNotBeFound("coverFillingDetailsId.equals=" + (coverFillingDetailsId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultCoverFillingShouldBeFound(String filter) throws Exception {
        restCoverFillingMockMvc.perform(get("/api/cover-fillings?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(coverFilling.getId().intValue())))
            .andExpect(jsonPath("$.[*].noOfCover").value(hasItem(DEFAULT_NO_OF_COVER)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].damageQuantity").value(hasItem(DEFAULT_DAMAGE_QUANTITY)));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultCoverFillingShouldNotBeFound(String filter) throws Exception {
        restCoverFillingMockMvc.perform(get("/api/cover-fillings?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingCoverFilling() throws Exception {
        // Get the coverFilling
        restCoverFillingMockMvc.perform(get("/api/cover-fillings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCoverFilling() throws Exception {
        // Initialize the database
        coverFillingRepository.saveAndFlush(coverFilling);

        int databaseSizeBeforeUpdate = coverFillingRepository.findAll().size();

        // Update the coverFilling
        CoverFilling updatedCoverFilling = coverFillingRepository.findById(coverFilling.getId()).get();
        // Disconnect from session so that the updates on updatedCoverFilling are not directly saved in db
        em.detach(updatedCoverFilling);
        updatedCoverFilling
            .noOfCover(UPDATED_NO_OF_COVER)
            .date(UPDATED_DATE)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS)
            .damageQuantity(UPDATED_DAMAGE_QUANTITY);
        CoverFillingDTO coverFillingDTO = coverFillingMapper.toDto(updatedCoverFilling);

        restCoverFillingMockMvc.perform(put("/api/cover-fillings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coverFillingDTO)))
            .andExpect(status().isOk());

        // Validate the CoverFilling in the database
        List<CoverFilling> coverFillingList = coverFillingRepository.findAll();
        assertThat(coverFillingList).hasSize(databaseSizeBeforeUpdate);
        CoverFilling testCoverFilling = coverFillingList.get(coverFillingList.size() - 1);
        assertThat(testCoverFilling.getNoOfCover()).isEqualTo(UPDATED_NO_OF_COVER);
        assertThat(testCoverFilling.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testCoverFilling.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCoverFilling.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testCoverFilling.getDamageQuantity()).isEqualTo(UPDATED_DAMAGE_QUANTITY);
    }

    @Test
    @Transactional
    public void updateNonExistingCoverFilling() throws Exception {
        int databaseSizeBeforeUpdate = coverFillingRepository.findAll().size();

        // Create the CoverFilling
        CoverFillingDTO coverFillingDTO = coverFillingMapper.toDto(coverFilling);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCoverFillingMockMvc.perform(put("/api/cover-fillings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coverFillingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CoverFilling in the database
        List<CoverFilling> coverFillingList = coverFillingRepository.findAll();
        assertThat(coverFillingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCoverFilling() throws Exception {
        // Initialize the database
        coverFillingRepository.saveAndFlush(coverFilling);

        int databaseSizeBeforeDelete = coverFillingRepository.findAll().size();

        // Get the coverFilling
        restCoverFillingMockMvc.perform(delete("/api/cover-fillings/{id}", coverFilling.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CoverFilling> coverFillingList = coverFillingRepository.findAll();
        assertThat(coverFillingList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CoverFilling.class);
        CoverFilling coverFilling1 = new CoverFilling();
        coverFilling1.setId(1L);
        CoverFilling coverFilling2 = new CoverFilling();
        coverFilling2.setId(coverFilling1.getId());
        assertThat(coverFilling1).isEqualTo(coverFilling2);
        coverFilling2.setId(2L);
        assertThat(coverFilling1).isNotEqualTo(coverFilling2);
        coverFilling1.setId(null);
        assertThat(coverFilling1).isNotEqualTo(coverFilling2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CoverFillingDTO.class);
        CoverFillingDTO coverFillingDTO1 = new CoverFillingDTO();
        coverFillingDTO1.setId(1L);
        CoverFillingDTO coverFillingDTO2 = new CoverFillingDTO();
        assertThat(coverFillingDTO1).isNotEqualTo(coverFillingDTO2);
        coverFillingDTO2.setId(coverFillingDTO1.getId());
        assertThat(coverFillingDTO1).isEqualTo(coverFillingDTO2);
        coverFillingDTO2.setId(2L);
        assertThat(coverFillingDTO1).isNotEqualTo(coverFillingDTO2);
        coverFillingDTO1.setId(null);
        assertThat(coverFillingDTO1).isNotEqualTo(coverFillingDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(coverFillingMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(coverFillingMapper.fromId(null)).isNull();
    }
}
