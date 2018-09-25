package com.niche.ng.web.rest;

import com.niche.ng.ProjectghApp;

import com.niche.ng.domain.Godown;
import com.niche.ng.domain.GodownPurchaseDetails;
import com.niche.ng.domain.GodownStock;
import com.niche.ng.domain.FinancialYearSettings;
import com.niche.ng.repository.GodownRepository;
import com.niche.ng.service.GodownService;
import com.niche.ng.service.dto.GodownDTO;
import com.niche.ng.service.mapper.GodownMapper;
import com.niche.ng.web.rest.errors.ExceptionTranslator;
import com.niche.ng.service.dto.GodownCriteria;
import com.niche.ng.service.GodownQueryService;

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
 * Test class for the GodownResource REST controller.
 *
 * @see GodownResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectghApp.class)
public class GodownResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_INCHARGE = "AAAAAAAAAA";
    private static final String UPDATED_INCHARGE = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    @Autowired
    private GodownRepository godownRepository;


    @Autowired
    private GodownMapper godownMapper;
    

    @Autowired
    private GodownService godownService;

    @Autowired
    private GodownQueryService godownQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restGodownMockMvc;

    private Godown godown;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GodownResource godownResource = new GodownResource(godownService, godownQueryService);
        this.restGodownMockMvc = MockMvcBuilders.standaloneSetup(godownResource)
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
    public static Godown createEntity(EntityManager em) {
        Godown godown = new Godown()
            .name(DEFAULT_NAME)
            .address(DEFAULT_ADDRESS)
            .incharge(DEFAULT_INCHARGE)
            .status(DEFAULT_STATUS);
        return godown;
    }

    @Before
    public void initTest() {
        godown = createEntity(em);
    }

    @Test
    @Transactional
    public void createGodown() throws Exception {
        int databaseSizeBeforeCreate = godownRepository.findAll().size();

        // Create the Godown
        GodownDTO godownDTO = godownMapper.toDto(godown);
        restGodownMockMvc.perform(post("/api/godowns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(godownDTO)))
            .andExpect(status().isCreated());

        // Validate the Godown in the database
        List<Godown> godownList = godownRepository.findAll();
        assertThat(godownList).hasSize(databaseSizeBeforeCreate + 1);
        Godown testGodown = godownList.get(godownList.size() - 1);
        assertThat(testGodown.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testGodown.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testGodown.getIncharge()).isEqualTo(DEFAULT_INCHARGE);
        assertThat(testGodown.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createGodownWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = godownRepository.findAll().size();

        // Create the Godown with an existing ID
        godown.setId(1L);
        GodownDTO godownDTO = godownMapper.toDto(godown);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGodownMockMvc.perform(post("/api/godowns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(godownDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Godown in the database
        List<Godown> godownList = godownRepository.findAll();
        assertThat(godownList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = godownRepository.findAll().size();
        // set the field null
        godown.setName(null);

        // Create the Godown, which fails.
        GodownDTO godownDTO = godownMapper.toDto(godown);

        restGodownMockMvc.perform(post("/api/godowns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(godownDTO)))
            .andExpect(status().isBadRequest());

        List<Godown> godownList = godownRepository.findAll();
        assertThat(godownList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllGodowns() throws Exception {
        // Initialize the database
        godownRepository.saveAndFlush(godown);

        // Get all the godownList
        restGodownMockMvc.perform(get("/api/godowns?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(godown.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].incharge").value(hasItem(DEFAULT_INCHARGE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    

    @Test
    @Transactional
    public void getGodown() throws Exception {
        // Initialize the database
        godownRepository.saveAndFlush(godown);

        // Get the godown
        restGodownMockMvc.perform(get("/api/godowns/{id}", godown.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(godown.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.incharge").value(DEFAULT_INCHARGE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getAllGodownsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        godownRepository.saveAndFlush(godown);

        // Get all the godownList where name equals to DEFAULT_NAME
        defaultGodownShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the godownList where name equals to UPDATED_NAME
        defaultGodownShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllGodownsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        godownRepository.saveAndFlush(godown);

        // Get all the godownList where name in DEFAULT_NAME or UPDATED_NAME
        defaultGodownShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the godownList where name equals to UPDATED_NAME
        defaultGodownShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllGodownsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        godownRepository.saveAndFlush(godown);

        // Get all the godownList where name is not null
        defaultGodownShouldBeFound("name.specified=true");

        // Get all the godownList where name is null
        defaultGodownShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllGodownsByAddressIsEqualToSomething() throws Exception {
        // Initialize the database
        godownRepository.saveAndFlush(godown);

        // Get all the godownList where address equals to DEFAULT_ADDRESS
        defaultGodownShouldBeFound("address.equals=" + DEFAULT_ADDRESS);

        // Get all the godownList where address equals to UPDATED_ADDRESS
        defaultGodownShouldNotBeFound("address.equals=" + UPDATED_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllGodownsByAddressIsInShouldWork() throws Exception {
        // Initialize the database
        godownRepository.saveAndFlush(godown);

        // Get all the godownList where address in DEFAULT_ADDRESS or UPDATED_ADDRESS
        defaultGodownShouldBeFound("address.in=" + DEFAULT_ADDRESS + "," + UPDATED_ADDRESS);

        // Get all the godownList where address equals to UPDATED_ADDRESS
        defaultGodownShouldNotBeFound("address.in=" + UPDATED_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllGodownsByAddressIsNullOrNotNull() throws Exception {
        // Initialize the database
        godownRepository.saveAndFlush(godown);

        // Get all the godownList where address is not null
        defaultGodownShouldBeFound("address.specified=true");

        // Get all the godownList where address is null
        defaultGodownShouldNotBeFound("address.specified=false");
    }

    @Test
    @Transactional
    public void getAllGodownsByInchargeIsEqualToSomething() throws Exception {
        // Initialize the database
        godownRepository.saveAndFlush(godown);

        // Get all the godownList where incharge equals to DEFAULT_INCHARGE
        defaultGodownShouldBeFound("incharge.equals=" + DEFAULT_INCHARGE);

        // Get all the godownList where incharge equals to UPDATED_INCHARGE
        defaultGodownShouldNotBeFound("incharge.equals=" + UPDATED_INCHARGE);
    }

    @Test
    @Transactional
    public void getAllGodownsByInchargeIsInShouldWork() throws Exception {
        // Initialize the database
        godownRepository.saveAndFlush(godown);

        // Get all the godownList where incharge in DEFAULT_INCHARGE or UPDATED_INCHARGE
        defaultGodownShouldBeFound("incharge.in=" + DEFAULT_INCHARGE + "," + UPDATED_INCHARGE);

        // Get all the godownList where incharge equals to UPDATED_INCHARGE
        defaultGodownShouldNotBeFound("incharge.in=" + UPDATED_INCHARGE);
    }

    @Test
    @Transactional
    public void getAllGodownsByInchargeIsNullOrNotNull() throws Exception {
        // Initialize the database
        godownRepository.saveAndFlush(godown);

        // Get all the godownList where incharge is not null
        defaultGodownShouldBeFound("incharge.specified=true");

        // Get all the godownList where incharge is null
        defaultGodownShouldNotBeFound("incharge.specified=false");
    }

    @Test
    @Transactional
    public void getAllGodownsByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        godownRepository.saveAndFlush(godown);

        // Get all the godownList where status equals to DEFAULT_STATUS
        defaultGodownShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the godownList where status equals to UPDATED_STATUS
        defaultGodownShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllGodownsByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        godownRepository.saveAndFlush(godown);

        // Get all the godownList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultGodownShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the godownList where status equals to UPDATED_STATUS
        defaultGodownShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllGodownsByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        godownRepository.saveAndFlush(godown);

        // Get all the godownList where status is not null
        defaultGodownShouldBeFound("status.specified=true");

        // Get all the godownList where status is null
        defaultGodownShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllGodownsByStatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        godownRepository.saveAndFlush(godown);

        // Get all the godownList where status greater than or equals to DEFAULT_STATUS
        defaultGodownShouldBeFound("status.greaterOrEqualThan=" + DEFAULT_STATUS);

        // Get all the godownList where status greater than or equals to UPDATED_STATUS
        defaultGodownShouldNotBeFound("status.greaterOrEqualThan=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllGodownsByStatusIsLessThanSomething() throws Exception {
        // Initialize the database
        godownRepository.saveAndFlush(godown);

        // Get all the godownList where status less than or equals to DEFAULT_STATUS
        defaultGodownShouldNotBeFound("status.lessThan=" + DEFAULT_STATUS);

        // Get all the godownList where status less than or equals to UPDATED_STATUS
        defaultGodownShouldBeFound("status.lessThan=" + UPDATED_STATUS);
    }


    @Test
    @Transactional
    public void getAllGodownsByGodownPurchaseDetailsIsEqualToSomething() throws Exception {
        // Initialize the database
        GodownPurchaseDetails godownPurchaseDetails = GodownPurchaseDetailsResourceIntTest.createEntity(em);
        em.persist(godownPurchaseDetails);
        em.flush();
        godown.addGodownPurchaseDetails(godownPurchaseDetails);
        godownRepository.saveAndFlush(godown);
        Long godownPurchaseDetailsId = godownPurchaseDetails.getId();

        // Get all the godownList where godownPurchaseDetails equals to godownPurchaseDetailsId
        defaultGodownShouldBeFound("godownPurchaseDetailsId.equals=" + godownPurchaseDetailsId);

        // Get all the godownList where godownPurchaseDetails equals to godownPurchaseDetailsId + 1
        defaultGodownShouldNotBeFound("godownPurchaseDetailsId.equals=" + (godownPurchaseDetailsId + 1));
    }


    @Test
    @Transactional
    public void getAllGodownsByGodownStocksIsEqualToSomething() throws Exception {
        // Initialize the database
        GodownStock godownStocks = GodownStockResourceIntTest.createEntity(em);
        em.persist(godownStocks);
        em.flush();
        godown.addGodownStocks(godownStocks);
        godownRepository.saveAndFlush(godown);
        Long godownStocksId = godownStocks.getId();

        // Get all the godownList where godownStocks equals to godownStocksId
        defaultGodownShouldBeFound("godownStocksId.equals=" + godownStocksId);

        // Get all the godownList where godownStocks equals to godownStocksId + 1
        defaultGodownShouldNotBeFound("godownStocksId.equals=" + (godownStocksId + 1));
    }


    @Test
    @Transactional
    public void getAllGodownsByFinancialYearGodownIsEqualToSomething() throws Exception {
        // Initialize the database
        FinancialYearSettings financialYearGodown = FinancialYearSettingsResourceIntTest.createEntity(em);
        em.persist(financialYearGodown);
        em.flush();
        godown.setFinancialYearGodown(financialYearGodown);
        godownRepository.saveAndFlush(godown);
        Long financialYearGodownId = financialYearGodown.getId();

        // Get all the godownList where financialYearGodown equals to financialYearGodownId
        defaultGodownShouldBeFound("financialYearGodownId.equals=" + financialYearGodownId);

        // Get all the godownList where financialYearGodown equals to financialYearGodownId + 1
        defaultGodownShouldNotBeFound("financialYearGodownId.equals=" + (financialYearGodownId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultGodownShouldBeFound(String filter) throws Exception {
        restGodownMockMvc.perform(get("/api/godowns?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(godown.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].incharge").value(hasItem(DEFAULT_INCHARGE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultGodownShouldNotBeFound(String filter) throws Exception {
        restGodownMockMvc.perform(get("/api/godowns?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingGodown() throws Exception {
        // Get the godown
        restGodownMockMvc.perform(get("/api/godowns/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGodown() throws Exception {
        // Initialize the database
        godownRepository.saveAndFlush(godown);

        int databaseSizeBeforeUpdate = godownRepository.findAll().size();

        // Update the godown
        Godown updatedGodown = godownRepository.findById(godown.getId()).get();
        // Disconnect from session so that the updates on updatedGodown are not directly saved in db
        em.detach(updatedGodown);
        updatedGodown
            .name(UPDATED_NAME)
            .address(UPDATED_ADDRESS)
            .incharge(UPDATED_INCHARGE)
            .status(UPDATED_STATUS);
        GodownDTO godownDTO = godownMapper.toDto(updatedGodown);

        restGodownMockMvc.perform(put("/api/godowns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(godownDTO)))
            .andExpect(status().isOk());

        // Validate the Godown in the database
        List<Godown> godownList = godownRepository.findAll();
        assertThat(godownList).hasSize(databaseSizeBeforeUpdate);
        Godown testGodown = godownList.get(godownList.size() - 1);
        assertThat(testGodown.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testGodown.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testGodown.getIncharge()).isEqualTo(UPDATED_INCHARGE);
        assertThat(testGodown.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingGodown() throws Exception {
        int databaseSizeBeforeUpdate = godownRepository.findAll().size();

        // Create the Godown
        GodownDTO godownDTO = godownMapper.toDto(godown);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restGodownMockMvc.perform(put("/api/godowns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(godownDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Godown in the database
        List<Godown> godownList = godownRepository.findAll();
        assertThat(godownList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGodown() throws Exception {
        // Initialize the database
        godownRepository.saveAndFlush(godown);

        int databaseSizeBeforeDelete = godownRepository.findAll().size();

        // Get the godown
        restGodownMockMvc.perform(delete("/api/godowns/{id}", godown.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Godown> godownList = godownRepository.findAll();
        assertThat(godownList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Godown.class);
        Godown godown1 = new Godown();
        godown1.setId(1L);
        Godown godown2 = new Godown();
        godown2.setId(godown1.getId());
        assertThat(godown1).isEqualTo(godown2);
        godown2.setId(2L);
        assertThat(godown1).isNotEqualTo(godown2);
        godown1.setId(null);
        assertThat(godown1).isNotEqualTo(godown2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GodownDTO.class);
        GodownDTO godownDTO1 = new GodownDTO();
        godownDTO1.setId(1L);
        GodownDTO godownDTO2 = new GodownDTO();
        assertThat(godownDTO1).isNotEqualTo(godownDTO2);
        godownDTO2.setId(godownDTO1.getId());
        assertThat(godownDTO1).isEqualTo(godownDTO2);
        godownDTO2.setId(2L);
        assertThat(godownDTO1).isNotEqualTo(godownDTO2);
        godownDTO1.setId(null);
        assertThat(godownDTO1).isNotEqualTo(godownDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(godownMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(godownMapper.fromId(null)).isNull();
    }
}
