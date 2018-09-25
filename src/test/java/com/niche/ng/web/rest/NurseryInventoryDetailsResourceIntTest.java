package com.niche.ng.web.rest;

import com.niche.ng.ProjectghApp;

import com.niche.ng.domain.NurseryInventoryDetails;
import com.niche.ng.domain.NurseryInventory;
import com.niche.ng.domain.PickListValue;
import com.niche.ng.repository.NurseryInventoryDetailsRepository;
import com.niche.ng.service.NurseryInventoryDetailsService;
import com.niche.ng.service.dto.NurseryInventoryDetailsDTO;
import com.niche.ng.service.mapper.NurseryInventoryDetailsMapper;
import com.niche.ng.web.rest.errors.ExceptionTranslator;
import com.niche.ng.service.dto.NurseryInventoryDetailsCriteria;
import com.niche.ng.service.NurseryInventoryDetailsQueryService;

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
 * Test class for the NurseryInventoryDetailsResource REST controller.
 *
 * @see NurseryInventoryDetailsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectghApp.class)
public class NurseryInventoryDetailsResourceIntTest {

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_QUANTITY = 1;
    private static final Integer UPDATED_QUANTITY = 2;

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private NurseryInventoryDetailsRepository nurseryInventoryDetailsRepository;


    @Autowired
    private NurseryInventoryDetailsMapper nurseryInventoryDetailsMapper;
    

    @Autowired
    private NurseryInventoryDetailsService nurseryInventoryDetailsService;

    @Autowired
    private NurseryInventoryDetailsQueryService nurseryInventoryDetailsQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restNurseryInventoryDetailsMockMvc;

    private NurseryInventoryDetails nurseryInventoryDetails;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NurseryInventoryDetailsResource nurseryInventoryDetailsResource = new NurseryInventoryDetailsResource(nurseryInventoryDetailsService, nurseryInventoryDetailsQueryService);
        this.restNurseryInventoryDetailsMockMvc = MockMvcBuilders.standaloneSetup(nurseryInventoryDetailsResource)
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
    public static NurseryInventoryDetails createEntity(EntityManager em) {
        NurseryInventoryDetails nurseryInventoryDetails = new NurseryInventoryDetails()
            .date(DEFAULT_DATE)
            .quantity(DEFAULT_QUANTITY)
            .status(DEFAULT_STATUS)
            .description(DEFAULT_DESCRIPTION);
        return nurseryInventoryDetails;
    }

    @Before
    public void initTest() {
        nurseryInventoryDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createNurseryInventoryDetails() throws Exception {
        int databaseSizeBeforeCreate = nurseryInventoryDetailsRepository.findAll().size();

        // Create the NurseryInventoryDetails
        NurseryInventoryDetailsDTO nurseryInventoryDetailsDTO = nurseryInventoryDetailsMapper.toDto(nurseryInventoryDetails);
        restNurseryInventoryDetailsMockMvc.perform(post("/api/nursery-inventory-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryInventoryDetailsDTO)))
            .andExpect(status().isCreated());

        // Validate the NurseryInventoryDetails in the database
        List<NurseryInventoryDetails> nurseryInventoryDetailsList = nurseryInventoryDetailsRepository.findAll();
        assertThat(nurseryInventoryDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        NurseryInventoryDetails testNurseryInventoryDetails = nurseryInventoryDetailsList.get(nurseryInventoryDetailsList.size() - 1);
        assertThat(testNurseryInventoryDetails.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testNurseryInventoryDetails.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testNurseryInventoryDetails.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testNurseryInventoryDetails.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createNurseryInventoryDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nurseryInventoryDetailsRepository.findAll().size();

        // Create the NurseryInventoryDetails with an existing ID
        nurseryInventoryDetails.setId(1L);
        NurseryInventoryDetailsDTO nurseryInventoryDetailsDTO = nurseryInventoryDetailsMapper.toDto(nurseryInventoryDetails);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNurseryInventoryDetailsMockMvc.perform(post("/api/nursery-inventory-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryInventoryDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NurseryInventoryDetails in the database
        List<NurseryInventoryDetails> nurseryInventoryDetailsList = nurseryInventoryDetailsRepository.findAll();
        assertThat(nurseryInventoryDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllNurseryInventoryDetails() throws Exception {
        // Initialize the database
        nurseryInventoryDetailsRepository.saveAndFlush(nurseryInventoryDetails);

        // Get all the nurseryInventoryDetailsList
        restNurseryInventoryDetailsMockMvc.perform(get("/api/nursery-inventory-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nurseryInventoryDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }
    

    @Test
    @Transactional
    public void getNurseryInventoryDetails() throws Exception {
        // Initialize the database
        nurseryInventoryDetailsRepository.saveAndFlush(nurseryInventoryDetails);

        // Get the nurseryInventoryDetails
        restNurseryInventoryDetailsMockMvc.perform(get("/api/nursery-inventory-details/{id}", nurseryInventoryDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(nurseryInventoryDetails.getId().intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getAllNurseryInventoryDetailsByDateIsEqualToSomething() throws Exception {
        // Initialize the database
        nurseryInventoryDetailsRepository.saveAndFlush(nurseryInventoryDetails);

        // Get all the nurseryInventoryDetailsList where date equals to DEFAULT_DATE
        defaultNurseryInventoryDetailsShouldBeFound("date.equals=" + DEFAULT_DATE);

        // Get all the nurseryInventoryDetailsList where date equals to UPDATED_DATE
        defaultNurseryInventoryDetailsShouldNotBeFound("date.equals=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    public void getAllNurseryInventoryDetailsByDateIsInShouldWork() throws Exception {
        // Initialize the database
        nurseryInventoryDetailsRepository.saveAndFlush(nurseryInventoryDetails);

        // Get all the nurseryInventoryDetailsList where date in DEFAULT_DATE or UPDATED_DATE
        defaultNurseryInventoryDetailsShouldBeFound("date.in=" + DEFAULT_DATE + "," + UPDATED_DATE);

        // Get all the nurseryInventoryDetailsList where date equals to UPDATED_DATE
        defaultNurseryInventoryDetailsShouldNotBeFound("date.in=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    public void getAllNurseryInventoryDetailsByDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        nurseryInventoryDetailsRepository.saveAndFlush(nurseryInventoryDetails);

        // Get all the nurseryInventoryDetailsList where date is not null
        defaultNurseryInventoryDetailsShouldBeFound("date.specified=true");

        // Get all the nurseryInventoryDetailsList where date is null
        defaultNurseryInventoryDetailsShouldNotBeFound("date.specified=false");
    }

    @Test
    @Transactional
    public void getAllNurseryInventoryDetailsByDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        nurseryInventoryDetailsRepository.saveAndFlush(nurseryInventoryDetails);

        // Get all the nurseryInventoryDetailsList where date greater than or equals to DEFAULT_DATE
        defaultNurseryInventoryDetailsShouldBeFound("date.greaterOrEqualThan=" + DEFAULT_DATE);

        // Get all the nurseryInventoryDetailsList where date greater than or equals to UPDATED_DATE
        defaultNurseryInventoryDetailsShouldNotBeFound("date.greaterOrEqualThan=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    public void getAllNurseryInventoryDetailsByDateIsLessThanSomething() throws Exception {
        // Initialize the database
        nurseryInventoryDetailsRepository.saveAndFlush(nurseryInventoryDetails);

        // Get all the nurseryInventoryDetailsList where date less than or equals to DEFAULT_DATE
        defaultNurseryInventoryDetailsShouldNotBeFound("date.lessThan=" + DEFAULT_DATE);

        // Get all the nurseryInventoryDetailsList where date less than or equals to UPDATED_DATE
        defaultNurseryInventoryDetailsShouldBeFound("date.lessThan=" + UPDATED_DATE);
    }


    @Test
    @Transactional
    public void getAllNurseryInventoryDetailsByQuantityIsEqualToSomething() throws Exception {
        // Initialize the database
        nurseryInventoryDetailsRepository.saveAndFlush(nurseryInventoryDetails);

        // Get all the nurseryInventoryDetailsList where quantity equals to DEFAULT_QUANTITY
        defaultNurseryInventoryDetailsShouldBeFound("quantity.equals=" + DEFAULT_QUANTITY);

        // Get all the nurseryInventoryDetailsList where quantity equals to UPDATED_QUANTITY
        defaultNurseryInventoryDetailsShouldNotBeFound("quantity.equals=" + UPDATED_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllNurseryInventoryDetailsByQuantityIsInShouldWork() throws Exception {
        // Initialize the database
        nurseryInventoryDetailsRepository.saveAndFlush(nurseryInventoryDetails);

        // Get all the nurseryInventoryDetailsList where quantity in DEFAULT_QUANTITY or UPDATED_QUANTITY
        defaultNurseryInventoryDetailsShouldBeFound("quantity.in=" + DEFAULT_QUANTITY + "," + UPDATED_QUANTITY);

        // Get all the nurseryInventoryDetailsList where quantity equals to UPDATED_QUANTITY
        defaultNurseryInventoryDetailsShouldNotBeFound("quantity.in=" + UPDATED_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllNurseryInventoryDetailsByQuantityIsNullOrNotNull() throws Exception {
        // Initialize the database
        nurseryInventoryDetailsRepository.saveAndFlush(nurseryInventoryDetails);

        // Get all the nurseryInventoryDetailsList where quantity is not null
        defaultNurseryInventoryDetailsShouldBeFound("quantity.specified=true");

        // Get all the nurseryInventoryDetailsList where quantity is null
        defaultNurseryInventoryDetailsShouldNotBeFound("quantity.specified=false");
    }

    @Test
    @Transactional
    public void getAllNurseryInventoryDetailsByQuantityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        nurseryInventoryDetailsRepository.saveAndFlush(nurseryInventoryDetails);

        // Get all the nurseryInventoryDetailsList where quantity greater than or equals to DEFAULT_QUANTITY
        defaultNurseryInventoryDetailsShouldBeFound("quantity.greaterOrEqualThan=" + DEFAULT_QUANTITY);

        // Get all the nurseryInventoryDetailsList where quantity greater than or equals to UPDATED_QUANTITY
        defaultNurseryInventoryDetailsShouldNotBeFound("quantity.greaterOrEqualThan=" + UPDATED_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllNurseryInventoryDetailsByQuantityIsLessThanSomething() throws Exception {
        // Initialize the database
        nurseryInventoryDetailsRepository.saveAndFlush(nurseryInventoryDetails);

        // Get all the nurseryInventoryDetailsList where quantity less than or equals to DEFAULT_QUANTITY
        defaultNurseryInventoryDetailsShouldNotBeFound("quantity.lessThan=" + DEFAULT_QUANTITY);

        // Get all the nurseryInventoryDetailsList where quantity less than or equals to UPDATED_QUANTITY
        defaultNurseryInventoryDetailsShouldBeFound("quantity.lessThan=" + UPDATED_QUANTITY);
    }


    @Test
    @Transactional
    public void getAllNurseryInventoryDetailsByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        nurseryInventoryDetailsRepository.saveAndFlush(nurseryInventoryDetails);

        // Get all the nurseryInventoryDetailsList where status equals to DEFAULT_STATUS
        defaultNurseryInventoryDetailsShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the nurseryInventoryDetailsList where status equals to UPDATED_STATUS
        defaultNurseryInventoryDetailsShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllNurseryInventoryDetailsByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        nurseryInventoryDetailsRepository.saveAndFlush(nurseryInventoryDetails);

        // Get all the nurseryInventoryDetailsList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultNurseryInventoryDetailsShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the nurseryInventoryDetailsList where status equals to UPDATED_STATUS
        defaultNurseryInventoryDetailsShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllNurseryInventoryDetailsByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        nurseryInventoryDetailsRepository.saveAndFlush(nurseryInventoryDetails);

        // Get all the nurseryInventoryDetailsList where status is not null
        defaultNurseryInventoryDetailsShouldBeFound("status.specified=true");

        // Get all the nurseryInventoryDetailsList where status is null
        defaultNurseryInventoryDetailsShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllNurseryInventoryDetailsByStatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        nurseryInventoryDetailsRepository.saveAndFlush(nurseryInventoryDetails);

        // Get all the nurseryInventoryDetailsList where status greater than or equals to DEFAULT_STATUS
        defaultNurseryInventoryDetailsShouldBeFound("status.greaterOrEqualThan=" + DEFAULT_STATUS);

        // Get all the nurseryInventoryDetailsList where status greater than or equals to UPDATED_STATUS
        defaultNurseryInventoryDetailsShouldNotBeFound("status.greaterOrEqualThan=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllNurseryInventoryDetailsByStatusIsLessThanSomething() throws Exception {
        // Initialize the database
        nurseryInventoryDetailsRepository.saveAndFlush(nurseryInventoryDetails);

        // Get all the nurseryInventoryDetailsList where status less than or equals to DEFAULT_STATUS
        defaultNurseryInventoryDetailsShouldNotBeFound("status.lessThan=" + DEFAULT_STATUS);

        // Get all the nurseryInventoryDetailsList where status less than or equals to UPDATED_STATUS
        defaultNurseryInventoryDetailsShouldBeFound("status.lessThan=" + UPDATED_STATUS);
    }


    @Test
    @Transactional
    public void getAllNurseryInventoryDetailsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        nurseryInventoryDetailsRepository.saveAndFlush(nurseryInventoryDetails);

        // Get all the nurseryInventoryDetailsList where description equals to DEFAULT_DESCRIPTION
        defaultNurseryInventoryDetailsShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the nurseryInventoryDetailsList where description equals to UPDATED_DESCRIPTION
        defaultNurseryInventoryDetailsShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllNurseryInventoryDetailsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        nurseryInventoryDetailsRepository.saveAndFlush(nurseryInventoryDetails);

        // Get all the nurseryInventoryDetailsList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultNurseryInventoryDetailsShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the nurseryInventoryDetailsList where description equals to UPDATED_DESCRIPTION
        defaultNurseryInventoryDetailsShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllNurseryInventoryDetailsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        nurseryInventoryDetailsRepository.saveAndFlush(nurseryInventoryDetails);

        // Get all the nurseryInventoryDetailsList where description is not null
        defaultNurseryInventoryDetailsShouldBeFound("description.specified=true");

        // Get all the nurseryInventoryDetailsList where description is null
        defaultNurseryInventoryDetailsShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllNurseryInventoryDetailsByNurseryInventoryIsEqualToSomething() throws Exception {
        // Initialize the database
        NurseryInventory nurseryInventory = NurseryInventoryResourceIntTest.createEntity(em);
        em.persist(nurseryInventory);
        em.flush();
        nurseryInventoryDetails.setNurseryInventory(nurseryInventory);
        nurseryInventoryDetailsRepository.saveAndFlush(nurseryInventoryDetails);
        Long nurseryInventoryId = nurseryInventory.getId();

        // Get all the nurseryInventoryDetailsList where nurseryInventory equals to nurseryInventoryId
        defaultNurseryInventoryDetailsShouldBeFound("nurseryInventoryId.equals=" + nurseryInventoryId);

        // Get all the nurseryInventoryDetailsList where nurseryInventory equals to nurseryInventoryId + 1
        defaultNurseryInventoryDetailsShouldNotBeFound("nurseryInventoryId.equals=" + (nurseryInventoryId + 1));
    }


    @Test
    @Transactional
    public void getAllNurseryInventoryDetailsByDamageTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        PickListValue damageType = PickListValueResourceIntTest.createEntity(em);
        em.persist(damageType);
        em.flush();
        nurseryInventoryDetails.setDamageType(damageType);
        nurseryInventoryDetailsRepository.saveAndFlush(nurseryInventoryDetails);
        Long damageTypeId = damageType.getId();

        // Get all the nurseryInventoryDetailsList where damageType equals to damageTypeId
        defaultNurseryInventoryDetailsShouldBeFound("damageTypeId.equals=" + damageTypeId);

        // Get all the nurseryInventoryDetailsList where damageType equals to damageTypeId + 1
        defaultNurseryInventoryDetailsShouldNotBeFound("damageTypeId.equals=" + (damageTypeId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultNurseryInventoryDetailsShouldBeFound(String filter) throws Exception {
        restNurseryInventoryDetailsMockMvc.perform(get("/api/nursery-inventory-details?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nurseryInventoryDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultNurseryInventoryDetailsShouldNotBeFound(String filter) throws Exception {
        restNurseryInventoryDetailsMockMvc.perform(get("/api/nursery-inventory-details?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingNurseryInventoryDetails() throws Exception {
        // Get the nurseryInventoryDetails
        restNurseryInventoryDetailsMockMvc.perform(get("/api/nursery-inventory-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNurseryInventoryDetails() throws Exception {
        // Initialize the database
        nurseryInventoryDetailsRepository.saveAndFlush(nurseryInventoryDetails);

        int databaseSizeBeforeUpdate = nurseryInventoryDetailsRepository.findAll().size();

        // Update the nurseryInventoryDetails
        NurseryInventoryDetails updatedNurseryInventoryDetails = nurseryInventoryDetailsRepository.findById(nurseryInventoryDetails.getId()).get();
        // Disconnect from session so that the updates on updatedNurseryInventoryDetails are not directly saved in db
        em.detach(updatedNurseryInventoryDetails);
        updatedNurseryInventoryDetails
            .date(UPDATED_DATE)
            .quantity(UPDATED_QUANTITY)
            .status(UPDATED_STATUS)
            .description(UPDATED_DESCRIPTION);
        NurseryInventoryDetailsDTO nurseryInventoryDetailsDTO = nurseryInventoryDetailsMapper.toDto(updatedNurseryInventoryDetails);

        restNurseryInventoryDetailsMockMvc.perform(put("/api/nursery-inventory-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryInventoryDetailsDTO)))
            .andExpect(status().isOk());

        // Validate the NurseryInventoryDetails in the database
        List<NurseryInventoryDetails> nurseryInventoryDetailsList = nurseryInventoryDetailsRepository.findAll();
        assertThat(nurseryInventoryDetailsList).hasSize(databaseSizeBeforeUpdate);
        NurseryInventoryDetails testNurseryInventoryDetails = nurseryInventoryDetailsList.get(nurseryInventoryDetailsList.size() - 1);
        assertThat(testNurseryInventoryDetails.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testNurseryInventoryDetails.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testNurseryInventoryDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testNurseryInventoryDetails.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingNurseryInventoryDetails() throws Exception {
        int databaseSizeBeforeUpdate = nurseryInventoryDetailsRepository.findAll().size();

        // Create the NurseryInventoryDetails
        NurseryInventoryDetailsDTO nurseryInventoryDetailsDTO = nurseryInventoryDetailsMapper.toDto(nurseryInventoryDetails);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restNurseryInventoryDetailsMockMvc.perform(put("/api/nursery-inventory-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nurseryInventoryDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NurseryInventoryDetails in the database
        List<NurseryInventoryDetails> nurseryInventoryDetailsList = nurseryInventoryDetailsRepository.findAll();
        assertThat(nurseryInventoryDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNurseryInventoryDetails() throws Exception {
        // Initialize the database
        nurseryInventoryDetailsRepository.saveAndFlush(nurseryInventoryDetails);

        int databaseSizeBeforeDelete = nurseryInventoryDetailsRepository.findAll().size();

        // Get the nurseryInventoryDetails
        restNurseryInventoryDetailsMockMvc.perform(delete("/api/nursery-inventory-details/{id}", nurseryInventoryDetails.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<NurseryInventoryDetails> nurseryInventoryDetailsList = nurseryInventoryDetailsRepository.findAll();
        assertThat(nurseryInventoryDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NurseryInventoryDetails.class);
        NurseryInventoryDetails nurseryInventoryDetails1 = new NurseryInventoryDetails();
        nurseryInventoryDetails1.setId(1L);
        NurseryInventoryDetails nurseryInventoryDetails2 = new NurseryInventoryDetails();
        nurseryInventoryDetails2.setId(nurseryInventoryDetails1.getId());
        assertThat(nurseryInventoryDetails1).isEqualTo(nurseryInventoryDetails2);
        nurseryInventoryDetails2.setId(2L);
        assertThat(nurseryInventoryDetails1).isNotEqualTo(nurseryInventoryDetails2);
        nurseryInventoryDetails1.setId(null);
        assertThat(nurseryInventoryDetails1).isNotEqualTo(nurseryInventoryDetails2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NurseryInventoryDetailsDTO.class);
        NurseryInventoryDetailsDTO nurseryInventoryDetailsDTO1 = new NurseryInventoryDetailsDTO();
        nurseryInventoryDetailsDTO1.setId(1L);
        NurseryInventoryDetailsDTO nurseryInventoryDetailsDTO2 = new NurseryInventoryDetailsDTO();
        assertThat(nurseryInventoryDetailsDTO1).isNotEqualTo(nurseryInventoryDetailsDTO2);
        nurseryInventoryDetailsDTO2.setId(nurseryInventoryDetailsDTO1.getId());
        assertThat(nurseryInventoryDetailsDTO1).isEqualTo(nurseryInventoryDetailsDTO2);
        nurseryInventoryDetailsDTO2.setId(2L);
        assertThat(nurseryInventoryDetailsDTO1).isNotEqualTo(nurseryInventoryDetailsDTO2);
        nurseryInventoryDetailsDTO1.setId(null);
        assertThat(nurseryInventoryDetailsDTO1).isNotEqualTo(nurseryInventoryDetailsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(nurseryInventoryDetailsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(nurseryInventoryDetailsMapper.fromId(null)).isNull();
    }
}
