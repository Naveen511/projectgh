package com.niche.ng.web.rest;

import com.niche.ng.ProjectghApp;

import com.niche.ng.domain.CoverFillingDetails;
import com.niche.ng.domain.CoverFilling;
import com.niche.ng.domain.PickListValue;
import com.niche.ng.repository.CoverFillingDetailsRepository;
import com.niche.ng.service.CoverFillingDetailsService;
import com.niche.ng.service.dto.CoverFillingDetailsDTO;
import com.niche.ng.service.mapper.CoverFillingDetailsMapper;
import com.niche.ng.web.rest.errors.ExceptionTranslator;
import com.niche.ng.service.dto.CoverFillingDetailsCriteria;
import com.niche.ng.service.CoverFillingDetailsQueryService;

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
 * Test class for the CoverFillingDetailsResource REST controller.
 *
 * @see CoverFillingDetailsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectghApp.class)
public class CoverFillingDetailsResourceIntTest {

    private static final Integer DEFAULT_QUANTITY = 1;
    private static final Integer UPDATED_QUANTITY = 2;

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private CoverFillingDetailsRepository coverFillingDetailsRepository;


    @Autowired
    private CoverFillingDetailsMapper coverFillingDetailsMapper;
    

    @Autowired
    private CoverFillingDetailsService coverFillingDetailsService;

    @Autowired
    private CoverFillingDetailsQueryService coverFillingDetailsQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCoverFillingDetailsMockMvc;

    private CoverFillingDetails coverFillingDetails;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CoverFillingDetailsResource coverFillingDetailsResource = new CoverFillingDetailsResource(coverFillingDetailsService, coverFillingDetailsQueryService);
        this.restCoverFillingDetailsMockMvc = MockMvcBuilders.standaloneSetup(coverFillingDetailsResource)
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
    public static CoverFillingDetails createEntity(EntityManager em) {
        CoverFillingDetails coverFillingDetails = new CoverFillingDetails()
            .quantity(DEFAULT_QUANTITY)
            .date(DEFAULT_DATE)
            .status(DEFAULT_STATUS)
            .description(DEFAULT_DESCRIPTION);
        return coverFillingDetails;
    }

    @Before
    public void initTest() {
        coverFillingDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createCoverFillingDetails() throws Exception {
        int databaseSizeBeforeCreate = coverFillingDetailsRepository.findAll().size();

        // Create the CoverFillingDetails
        CoverFillingDetailsDTO coverFillingDetailsDTO = coverFillingDetailsMapper.toDto(coverFillingDetails);
        restCoverFillingDetailsMockMvc.perform(post("/api/cover-filling-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coverFillingDetailsDTO)))
            .andExpect(status().isCreated());

        // Validate the CoverFillingDetails in the database
        List<CoverFillingDetails> coverFillingDetailsList = coverFillingDetailsRepository.findAll();
        assertThat(coverFillingDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        CoverFillingDetails testCoverFillingDetails = coverFillingDetailsList.get(coverFillingDetailsList.size() - 1);
        assertThat(testCoverFillingDetails.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testCoverFillingDetails.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testCoverFillingDetails.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testCoverFillingDetails.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createCoverFillingDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = coverFillingDetailsRepository.findAll().size();

        // Create the CoverFillingDetails with an existing ID
        coverFillingDetails.setId(1L);
        CoverFillingDetailsDTO coverFillingDetailsDTO = coverFillingDetailsMapper.toDto(coverFillingDetails);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCoverFillingDetailsMockMvc.perform(post("/api/cover-filling-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coverFillingDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CoverFillingDetails in the database
        List<CoverFillingDetails> coverFillingDetailsList = coverFillingDetailsRepository.findAll();
        assertThat(coverFillingDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkQuantityIsRequired() throws Exception {
        int databaseSizeBeforeTest = coverFillingDetailsRepository.findAll().size();
        // set the field null
        coverFillingDetails.setQuantity(null);

        // Create the CoverFillingDetails, which fails.
        CoverFillingDetailsDTO coverFillingDetailsDTO = coverFillingDetailsMapper.toDto(coverFillingDetails);

        restCoverFillingDetailsMockMvc.perform(post("/api/cover-filling-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coverFillingDetailsDTO)))
            .andExpect(status().isBadRequest());

        List<CoverFillingDetails> coverFillingDetailsList = coverFillingDetailsRepository.findAll();
        assertThat(coverFillingDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = coverFillingDetailsRepository.findAll().size();
        // set the field null
        coverFillingDetails.setDate(null);

        // Create the CoverFillingDetails, which fails.
        CoverFillingDetailsDTO coverFillingDetailsDTO = coverFillingDetailsMapper.toDto(coverFillingDetails);

        restCoverFillingDetailsMockMvc.perform(post("/api/cover-filling-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coverFillingDetailsDTO)))
            .andExpect(status().isBadRequest());

        List<CoverFillingDetails> coverFillingDetailsList = coverFillingDetailsRepository.findAll();
        assertThat(coverFillingDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCoverFillingDetails() throws Exception {
        // Initialize the database
        coverFillingDetailsRepository.saveAndFlush(coverFillingDetails);

        // Get all the coverFillingDetailsList
        restCoverFillingDetailsMockMvc.perform(get("/api/cover-filling-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(coverFillingDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }
    

    @Test
    @Transactional
    public void getCoverFillingDetails() throws Exception {
        // Initialize the database
        coverFillingDetailsRepository.saveAndFlush(coverFillingDetails);

        // Get the coverFillingDetails
        restCoverFillingDetailsMockMvc.perform(get("/api/cover-filling-details/{id}", coverFillingDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(coverFillingDetails.getId().intValue()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getAllCoverFillingDetailsByQuantityIsEqualToSomething() throws Exception {
        // Initialize the database
        coverFillingDetailsRepository.saveAndFlush(coverFillingDetails);

        // Get all the coverFillingDetailsList where quantity equals to DEFAULT_QUANTITY
        defaultCoverFillingDetailsShouldBeFound("quantity.equals=" + DEFAULT_QUANTITY);

        // Get all the coverFillingDetailsList where quantity equals to UPDATED_QUANTITY
        defaultCoverFillingDetailsShouldNotBeFound("quantity.equals=" + UPDATED_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllCoverFillingDetailsByQuantityIsInShouldWork() throws Exception {
        // Initialize the database
        coverFillingDetailsRepository.saveAndFlush(coverFillingDetails);

        // Get all the coverFillingDetailsList where quantity in DEFAULT_QUANTITY or UPDATED_QUANTITY
        defaultCoverFillingDetailsShouldBeFound("quantity.in=" + DEFAULT_QUANTITY + "," + UPDATED_QUANTITY);

        // Get all the coverFillingDetailsList where quantity equals to UPDATED_QUANTITY
        defaultCoverFillingDetailsShouldNotBeFound("quantity.in=" + UPDATED_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllCoverFillingDetailsByQuantityIsNullOrNotNull() throws Exception {
        // Initialize the database
        coverFillingDetailsRepository.saveAndFlush(coverFillingDetails);

        // Get all the coverFillingDetailsList where quantity is not null
        defaultCoverFillingDetailsShouldBeFound("quantity.specified=true");

        // Get all the coverFillingDetailsList where quantity is null
        defaultCoverFillingDetailsShouldNotBeFound("quantity.specified=false");
    }

    @Test
    @Transactional
    public void getAllCoverFillingDetailsByQuantityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        coverFillingDetailsRepository.saveAndFlush(coverFillingDetails);

        // Get all the coverFillingDetailsList where quantity greater than or equals to DEFAULT_QUANTITY
        defaultCoverFillingDetailsShouldBeFound("quantity.greaterOrEqualThan=" + DEFAULT_QUANTITY);

        // Get all the coverFillingDetailsList where quantity greater than or equals to UPDATED_QUANTITY
        defaultCoverFillingDetailsShouldNotBeFound("quantity.greaterOrEqualThan=" + UPDATED_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllCoverFillingDetailsByQuantityIsLessThanSomething() throws Exception {
        // Initialize the database
        coverFillingDetailsRepository.saveAndFlush(coverFillingDetails);

        // Get all the coverFillingDetailsList where quantity less than or equals to DEFAULT_QUANTITY
        defaultCoverFillingDetailsShouldNotBeFound("quantity.lessThan=" + DEFAULT_QUANTITY);

        // Get all the coverFillingDetailsList where quantity less than or equals to UPDATED_QUANTITY
        defaultCoverFillingDetailsShouldBeFound("quantity.lessThan=" + UPDATED_QUANTITY);
    }


    @Test
    @Transactional
    public void getAllCoverFillingDetailsByDateIsEqualToSomething() throws Exception {
        // Initialize the database
        coverFillingDetailsRepository.saveAndFlush(coverFillingDetails);

        // Get all the coverFillingDetailsList where date equals to DEFAULT_DATE
        defaultCoverFillingDetailsShouldBeFound("date.equals=" + DEFAULT_DATE);

        // Get all the coverFillingDetailsList where date equals to UPDATED_DATE
        defaultCoverFillingDetailsShouldNotBeFound("date.equals=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    public void getAllCoverFillingDetailsByDateIsInShouldWork() throws Exception {
        // Initialize the database
        coverFillingDetailsRepository.saveAndFlush(coverFillingDetails);

        // Get all the coverFillingDetailsList where date in DEFAULT_DATE or UPDATED_DATE
        defaultCoverFillingDetailsShouldBeFound("date.in=" + DEFAULT_DATE + "," + UPDATED_DATE);

        // Get all the coverFillingDetailsList where date equals to UPDATED_DATE
        defaultCoverFillingDetailsShouldNotBeFound("date.in=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    public void getAllCoverFillingDetailsByDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        coverFillingDetailsRepository.saveAndFlush(coverFillingDetails);

        // Get all the coverFillingDetailsList where date is not null
        defaultCoverFillingDetailsShouldBeFound("date.specified=true");

        // Get all the coverFillingDetailsList where date is null
        defaultCoverFillingDetailsShouldNotBeFound("date.specified=false");
    }

    @Test
    @Transactional
    public void getAllCoverFillingDetailsByDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        coverFillingDetailsRepository.saveAndFlush(coverFillingDetails);

        // Get all the coverFillingDetailsList where date greater than or equals to DEFAULT_DATE
        defaultCoverFillingDetailsShouldBeFound("date.greaterOrEqualThan=" + DEFAULT_DATE);

        // Get all the coverFillingDetailsList where date greater than or equals to UPDATED_DATE
        defaultCoverFillingDetailsShouldNotBeFound("date.greaterOrEqualThan=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    public void getAllCoverFillingDetailsByDateIsLessThanSomething() throws Exception {
        // Initialize the database
        coverFillingDetailsRepository.saveAndFlush(coverFillingDetails);

        // Get all the coverFillingDetailsList where date less than or equals to DEFAULT_DATE
        defaultCoverFillingDetailsShouldNotBeFound("date.lessThan=" + DEFAULT_DATE);

        // Get all the coverFillingDetailsList where date less than or equals to UPDATED_DATE
        defaultCoverFillingDetailsShouldBeFound("date.lessThan=" + UPDATED_DATE);
    }


    @Test
    @Transactional
    public void getAllCoverFillingDetailsByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        coverFillingDetailsRepository.saveAndFlush(coverFillingDetails);

        // Get all the coverFillingDetailsList where status equals to DEFAULT_STATUS
        defaultCoverFillingDetailsShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the coverFillingDetailsList where status equals to UPDATED_STATUS
        defaultCoverFillingDetailsShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllCoverFillingDetailsByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        coverFillingDetailsRepository.saveAndFlush(coverFillingDetails);

        // Get all the coverFillingDetailsList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultCoverFillingDetailsShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the coverFillingDetailsList where status equals to UPDATED_STATUS
        defaultCoverFillingDetailsShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllCoverFillingDetailsByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        coverFillingDetailsRepository.saveAndFlush(coverFillingDetails);

        // Get all the coverFillingDetailsList where status is not null
        defaultCoverFillingDetailsShouldBeFound("status.specified=true");

        // Get all the coverFillingDetailsList where status is null
        defaultCoverFillingDetailsShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllCoverFillingDetailsByStatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        coverFillingDetailsRepository.saveAndFlush(coverFillingDetails);

        // Get all the coverFillingDetailsList where status greater than or equals to DEFAULT_STATUS
        defaultCoverFillingDetailsShouldBeFound("status.greaterOrEqualThan=" + DEFAULT_STATUS);

        // Get all the coverFillingDetailsList where status greater than or equals to UPDATED_STATUS
        defaultCoverFillingDetailsShouldNotBeFound("status.greaterOrEqualThan=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllCoverFillingDetailsByStatusIsLessThanSomething() throws Exception {
        // Initialize the database
        coverFillingDetailsRepository.saveAndFlush(coverFillingDetails);

        // Get all the coverFillingDetailsList where status less than or equals to DEFAULT_STATUS
        defaultCoverFillingDetailsShouldNotBeFound("status.lessThan=" + DEFAULT_STATUS);

        // Get all the coverFillingDetailsList where status less than or equals to UPDATED_STATUS
        defaultCoverFillingDetailsShouldBeFound("status.lessThan=" + UPDATED_STATUS);
    }


    @Test
    @Transactional
    public void getAllCoverFillingDetailsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        coverFillingDetailsRepository.saveAndFlush(coverFillingDetails);

        // Get all the coverFillingDetailsList where description equals to DEFAULT_DESCRIPTION
        defaultCoverFillingDetailsShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the coverFillingDetailsList where description equals to UPDATED_DESCRIPTION
        defaultCoverFillingDetailsShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllCoverFillingDetailsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        coverFillingDetailsRepository.saveAndFlush(coverFillingDetails);

        // Get all the coverFillingDetailsList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultCoverFillingDetailsShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the coverFillingDetailsList where description equals to UPDATED_DESCRIPTION
        defaultCoverFillingDetailsShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllCoverFillingDetailsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        coverFillingDetailsRepository.saveAndFlush(coverFillingDetails);

        // Get all the coverFillingDetailsList where description is not null
        defaultCoverFillingDetailsShouldBeFound("description.specified=true");

        // Get all the coverFillingDetailsList where description is null
        defaultCoverFillingDetailsShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllCoverFillingDetailsByCoverFillingIsEqualToSomething() throws Exception {
        // Initialize the database
        CoverFilling coverFilling = CoverFillingResourceIntTest.createEntity(em);
        em.persist(coverFilling);
        em.flush();
        coverFillingDetails.setCoverFilling(coverFilling);
        coverFillingDetailsRepository.saveAndFlush(coverFillingDetails);
        Long coverFillingId = coverFilling.getId();

        // Get all the coverFillingDetailsList where coverFilling equals to coverFillingId
        defaultCoverFillingDetailsShouldBeFound("coverFillingId.equals=" + coverFillingId);

        // Get all the coverFillingDetailsList where coverFilling equals to coverFillingId + 1
        defaultCoverFillingDetailsShouldNotBeFound("coverFillingId.equals=" + (coverFillingId + 1));
    }


    @Test
    @Transactional
    public void getAllCoverFillingDetailsByDamageTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        PickListValue damageType = PickListValueResourceIntTest.createEntity(em);
        em.persist(damageType);
        em.flush();
        coverFillingDetails.setDamageType(damageType);
        coverFillingDetailsRepository.saveAndFlush(coverFillingDetails);
        Long damageTypeId = damageType.getId();

        // Get all the coverFillingDetailsList where damageType equals to damageTypeId
        defaultCoverFillingDetailsShouldBeFound("damageTypeId.equals=" + damageTypeId);

        // Get all the coverFillingDetailsList where damageType equals to damageTypeId + 1
        defaultCoverFillingDetailsShouldNotBeFound("damageTypeId.equals=" + (damageTypeId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultCoverFillingDetailsShouldBeFound(String filter) throws Exception {
        restCoverFillingDetailsMockMvc.perform(get("/api/cover-filling-details?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(coverFillingDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultCoverFillingDetailsShouldNotBeFound(String filter) throws Exception {
        restCoverFillingDetailsMockMvc.perform(get("/api/cover-filling-details?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingCoverFillingDetails() throws Exception {
        // Get the coverFillingDetails
        restCoverFillingDetailsMockMvc.perform(get("/api/cover-filling-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCoverFillingDetails() throws Exception {
        // Initialize the database
        coverFillingDetailsRepository.saveAndFlush(coverFillingDetails);

        int databaseSizeBeforeUpdate = coverFillingDetailsRepository.findAll().size();

        // Update the coverFillingDetails
        CoverFillingDetails updatedCoverFillingDetails = coverFillingDetailsRepository.findById(coverFillingDetails.getId()).get();
        // Disconnect from session so that the updates on updatedCoverFillingDetails are not directly saved in db
        em.detach(updatedCoverFillingDetails);
        updatedCoverFillingDetails
            .quantity(UPDATED_QUANTITY)
            .date(UPDATED_DATE)
            .status(UPDATED_STATUS)
            .description(UPDATED_DESCRIPTION);
        CoverFillingDetailsDTO coverFillingDetailsDTO = coverFillingDetailsMapper.toDto(updatedCoverFillingDetails);

        restCoverFillingDetailsMockMvc.perform(put("/api/cover-filling-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coverFillingDetailsDTO)))
            .andExpect(status().isOk());

        // Validate the CoverFillingDetails in the database
        List<CoverFillingDetails> coverFillingDetailsList = coverFillingDetailsRepository.findAll();
        assertThat(coverFillingDetailsList).hasSize(databaseSizeBeforeUpdate);
        CoverFillingDetails testCoverFillingDetails = coverFillingDetailsList.get(coverFillingDetailsList.size() - 1);
        assertThat(testCoverFillingDetails.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testCoverFillingDetails.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testCoverFillingDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testCoverFillingDetails.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingCoverFillingDetails() throws Exception {
        int databaseSizeBeforeUpdate = coverFillingDetailsRepository.findAll().size();

        // Create the CoverFillingDetails
        CoverFillingDetailsDTO coverFillingDetailsDTO = coverFillingDetailsMapper.toDto(coverFillingDetails);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCoverFillingDetailsMockMvc.perform(put("/api/cover-filling-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coverFillingDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CoverFillingDetails in the database
        List<CoverFillingDetails> coverFillingDetailsList = coverFillingDetailsRepository.findAll();
        assertThat(coverFillingDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCoverFillingDetails() throws Exception {
        // Initialize the database
        coverFillingDetailsRepository.saveAndFlush(coverFillingDetails);

        int databaseSizeBeforeDelete = coverFillingDetailsRepository.findAll().size();

        // Get the coverFillingDetails
        restCoverFillingDetailsMockMvc.perform(delete("/api/cover-filling-details/{id}", coverFillingDetails.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CoverFillingDetails> coverFillingDetailsList = coverFillingDetailsRepository.findAll();
        assertThat(coverFillingDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CoverFillingDetails.class);
        CoverFillingDetails coverFillingDetails1 = new CoverFillingDetails();
        coverFillingDetails1.setId(1L);
        CoverFillingDetails coverFillingDetails2 = new CoverFillingDetails();
        coverFillingDetails2.setId(coverFillingDetails1.getId());
        assertThat(coverFillingDetails1).isEqualTo(coverFillingDetails2);
        coverFillingDetails2.setId(2L);
        assertThat(coverFillingDetails1).isNotEqualTo(coverFillingDetails2);
        coverFillingDetails1.setId(null);
        assertThat(coverFillingDetails1).isNotEqualTo(coverFillingDetails2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CoverFillingDetailsDTO.class);
        CoverFillingDetailsDTO coverFillingDetailsDTO1 = new CoverFillingDetailsDTO();
        coverFillingDetailsDTO1.setId(1L);
        CoverFillingDetailsDTO coverFillingDetailsDTO2 = new CoverFillingDetailsDTO();
        assertThat(coverFillingDetailsDTO1).isNotEqualTo(coverFillingDetailsDTO2);
        coverFillingDetailsDTO2.setId(coverFillingDetailsDTO1.getId());
        assertThat(coverFillingDetailsDTO1).isEqualTo(coverFillingDetailsDTO2);
        coverFillingDetailsDTO2.setId(2L);
        assertThat(coverFillingDetailsDTO1).isNotEqualTo(coverFillingDetailsDTO2);
        coverFillingDetailsDTO1.setId(null);
        assertThat(coverFillingDetailsDTO1).isNotEqualTo(coverFillingDetailsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(coverFillingDetailsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(coverFillingDetailsMapper.fromId(null)).isNull();
    }
}
