package com.niche.ng.web.rest;

import com.niche.ng.ProjectghApp;

import com.niche.ng.domain.GodownPurchaseDetails;
import com.niche.ng.domain.PickListValue;
import com.niche.ng.domain.PickListValue;
import com.niche.ng.domain.PickListValue;
import com.niche.ng.domain.Godown;
import com.niche.ng.domain.FinancialYearSettings;
import com.niche.ng.repository.GodownPurchaseDetailsRepository;
import com.niche.ng.service.GodownPurchaseDetailsService;
import com.niche.ng.service.dto.GodownPurchaseDetailsDTO;
import com.niche.ng.service.mapper.GodownPurchaseDetailsMapper;
import com.niche.ng.web.rest.errors.ExceptionTranslator;
import com.niche.ng.service.dto.GodownPurchaseDetailsCriteria;
import com.niche.ng.service.GodownPurchaseDetailsQueryService;

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
 * Test class for the GodownPurchaseDetailsResource REST controller.
 *
 * @see GodownPurchaseDetailsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectghApp.class)
public class GodownPurchaseDetailsResourceIntTest {

    private static final Long DEFAULT_QUANTITY = 1L;
    private static final Long UPDATED_QUANTITY = 2L;

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Float DEFAULT_PRICE = 1F;
    private static final Float UPDATED_PRICE = 2F;

    private static final String DEFAULT_OWNED_BY = "AAAAAAAAAA";
    private static final String UPDATED_OWNED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_VENDOR_NAME = "AAAAAAAAAA";
    private static final String UPDATED_VENDOR_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_VENDOR_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_VENDOR_ADDRESS = "BBBBBBBBBB";

    private static final Long DEFAULT_VENDOR_PHONE = 1L;
    private static final Long UPDATED_VENDOR_PHONE = 2L;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    @Autowired
    private GodownPurchaseDetailsRepository godownPurchaseDetailsRepository;


    @Autowired
    private GodownPurchaseDetailsMapper godownPurchaseDetailsMapper;
    

    @Autowired
    private GodownPurchaseDetailsService godownPurchaseDetailsService;

    @Autowired
    private GodownPurchaseDetailsQueryService godownPurchaseDetailsQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restGodownPurchaseDetailsMockMvc;

    private GodownPurchaseDetails godownPurchaseDetails;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GodownPurchaseDetailsResource godownPurchaseDetailsResource = new GodownPurchaseDetailsResource(godownPurchaseDetailsService, godownPurchaseDetailsQueryService);
        this.restGodownPurchaseDetailsMockMvc = MockMvcBuilders.standaloneSetup(godownPurchaseDetailsResource)
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
    public static GodownPurchaseDetails createEntity(EntityManager em) {
        GodownPurchaseDetails godownPurchaseDetails = new GodownPurchaseDetails()
            .quantity(DEFAULT_QUANTITY)
            .date(DEFAULT_DATE)
            .price(DEFAULT_PRICE)
            .ownedBy(DEFAULT_OWNED_BY)
            .vendorName(DEFAULT_VENDOR_NAME)
            .vendorAddress(DEFAULT_VENDOR_ADDRESS)
            .vendorPhone(DEFAULT_VENDOR_PHONE)
            .description(DEFAULT_DESCRIPTION)
            .status(DEFAULT_STATUS);
        return godownPurchaseDetails;
    }

    @Before
    public void initTest() {
        godownPurchaseDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createGodownPurchaseDetails() throws Exception {
        int databaseSizeBeforeCreate = godownPurchaseDetailsRepository.findAll().size();

        // Create the GodownPurchaseDetails
        GodownPurchaseDetailsDTO godownPurchaseDetailsDTO = godownPurchaseDetailsMapper.toDto(godownPurchaseDetails);
        restGodownPurchaseDetailsMockMvc.perform(post("/api/godown-purchase-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(godownPurchaseDetailsDTO)))
            .andExpect(status().isCreated());

        // Validate the GodownPurchaseDetails in the database
        List<GodownPurchaseDetails> godownPurchaseDetailsList = godownPurchaseDetailsRepository.findAll();
        assertThat(godownPurchaseDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        GodownPurchaseDetails testGodownPurchaseDetails = godownPurchaseDetailsList.get(godownPurchaseDetailsList.size() - 1);
        assertThat(testGodownPurchaseDetails.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testGodownPurchaseDetails.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testGodownPurchaseDetails.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testGodownPurchaseDetails.getOwnedBy()).isEqualTo(DEFAULT_OWNED_BY);
        assertThat(testGodownPurchaseDetails.getVendorName()).isEqualTo(DEFAULT_VENDOR_NAME);
        assertThat(testGodownPurchaseDetails.getVendorAddress()).isEqualTo(DEFAULT_VENDOR_ADDRESS);
        assertThat(testGodownPurchaseDetails.getVendorPhone()).isEqualTo(DEFAULT_VENDOR_PHONE);
        assertThat(testGodownPurchaseDetails.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testGodownPurchaseDetails.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createGodownPurchaseDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = godownPurchaseDetailsRepository.findAll().size();

        // Create the GodownPurchaseDetails with an existing ID
        godownPurchaseDetails.setId(1L);
        GodownPurchaseDetailsDTO godownPurchaseDetailsDTO = godownPurchaseDetailsMapper.toDto(godownPurchaseDetails);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGodownPurchaseDetailsMockMvc.perform(post("/api/godown-purchase-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(godownPurchaseDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GodownPurchaseDetails in the database
        List<GodownPurchaseDetails> godownPurchaseDetailsList = godownPurchaseDetailsRepository.findAll();
        assertThat(godownPurchaseDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkQuantityIsRequired() throws Exception {
        int databaseSizeBeforeTest = godownPurchaseDetailsRepository.findAll().size();
        // set the field null
        godownPurchaseDetails.setQuantity(null);

        // Create the GodownPurchaseDetails, which fails.
        GodownPurchaseDetailsDTO godownPurchaseDetailsDTO = godownPurchaseDetailsMapper.toDto(godownPurchaseDetails);

        restGodownPurchaseDetailsMockMvc.perform(post("/api/godown-purchase-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(godownPurchaseDetailsDTO)))
            .andExpect(status().isBadRequest());

        List<GodownPurchaseDetails> godownPurchaseDetailsList = godownPurchaseDetailsRepository.findAll();
        assertThat(godownPurchaseDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = godownPurchaseDetailsRepository.findAll().size();
        // set the field null
        godownPurchaseDetails.setDate(null);

        // Create the GodownPurchaseDetails, which fails.
        GodownPurchaseDetailsDTO godownPurchaseDetailsDTO = godownPurchaseDetailsMapper.toDto(godownPurchaseDetails);

        restGodownPurchaseDetailsMockMvc.perform(post("/api/godown-purchase-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(godownPurchaseDetailsDTO)))
            .andExpect(status().isBadRequest());

        List<GodownPurchaseDetails> godownPurchaseDetailsList = godownPurchaseDetailsRepository.findAll();
        assertThat(godownPurchaseDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllGodownPurchaseDetails() throws Exception {
        // Initialize the database
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);

        // Get all the godownPurchaseDetailsList
        restGodownPurchaseDetailsMockMvc.perform(get("/api/godown-purchase-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(godownPurchaseDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].ownedBy").value(hasItem(DEFAULT_OWNED_BY.toString())))
            .andExpect(jsonPath("$.[*].vendorName").value(hasItem(DEFAULT_VENDOR_NAME.toString())))
            .andExpect(jsonPath("$.[*].vendorAddress").value(hasItem(DEFAULT_VENDOR_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].vendorPhone").value(hasItem(DEFAULT_VENDOR_PHONE.intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    

    @Test
    @Transactional
    public void getGodownPurchaseDetails() throws Exception {
        // Initialize the database
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);

        // Get the godownPurchaseDetails
        restGodownPurchaseDetailsMockMvc.perform(get("/api/godown-purchase-details/{id}", godownPurchaseDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(godownPurchaseDetails.getId().intValue()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY.intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.ownedBy").value(DEFAULT_OWNED_BY.toString()))
            .andExpect(jsonPath("$.vendorName").value(DEFAULT_VENDOR_NAME.toString()))
            .andExpect(jsonPath("$.vendorAddress").value(DEFAULT_VENDOR_ADDRESS.toString()))
            .andExpect(jsonPath("$.vendorPhone").value(DEFAULT_VENDOR_PHONE.intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getAllGodownPurchaseDetailsByQuantityIsEqualToSomething() throws Exception {
        // Initialize the database
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);

        // Get all the godownPurchaseDetailsList where quantity equals to DEFAULT_QUANTITY
        defaultGodownPurchaseDetailsShouldBeFound("quantity.equals=" + DEFAULT_QUANTITY);

        // Get all the godownPurchaseDetailsList where quantity equals to UPDATED_QUANTITY
        defaultGodownPurchaseDetailsShouldNotBeFound("quantity.equals=" + UPDATED_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllGodownPurchaseDetailsByQuantityIsInShouldWork() throws Exception {
        // Initialize the database
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);

        // Get all the godownPurchaseDetailsList where quantity in DEFAULT_QUANTITY or UPDATED_QUANTITY
        defaultGodownPurchaseDetailsShouldBeFound("quantity.in=" + DEFAULT_QUANTITY + "," + UPDATED_QUANTITY);

        // Get all the godownPurchaseDetailsList where quantity equals to UPDATED_QUANTITY
        defaultGodownPurchaseDetailsShouldNotBeFound("quantity.in=" + UPDATED_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllGodownPurchaseDetailsByQuantityIsNullOrNotNull() throws Exception {
        // Initialize the database
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);

        // Get all the godownPurchaseDetailsList where quantity is not null
        defaultGodownPurchaseDetailsShouldBeFound("quantity.specified=true");

        // Get all the godownPurchaseDetailsList where quantity is null
        defaultGodownPurchaseDetailsShouldNotBeFound("quantity.specified=false");
    }

    @Test
    @Transactional
    public void getAllGodownPurchaseDetailsByQuantityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);

        // Get all the godownPurchaseDetailsList where quantity greater than or equals to DEFAULT_QUANTITY
        defaultGodownPurchaseDetailsShouldBeFound("quantity.greaterOrEqualThan=" + DEFAULT_QUANTITY);

        // Get all the godownPurchaseDetailsList where quantity greater than or equals to UPDATED_QUANTITY
        defaultGodownPurchaseDetailsShouldNotBeFound("quantity.greaterOrEqualThan=" + UPDATED_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllGodownPurchaseDetailsByQuantityIsLessThanSomething() throws Exception {
        // Initialize the database
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);

        // Get all the godownPurchaseDetailsList where quantity less than or equals to DEFAULT_QUANTITY
        defaultGodownPurchaseDetailsShouldNotBeFound("quantity.lessThan=" + DEFAULT_QUANTITY);

        // Get all the godownPurchaseDetailsList where quantity less than or equals to UPDATED_QUANTITY
        defaultGodownPurchaseDetailsShouldBeFound("quantity.lessThan=" + UPDATED_QUANTITY);
    }


    @Test
    @Transactional
    public void getAllGodownPurchaseDetailsByDateIsEqualToSomething() throws Exception {
        // Initialize the database
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);

        // Get all the godownPurchaseDetailsList where date equals to DEFAULT_DATE
        defaultGodownPurchaseDetailsShouldBeFound("date.equals=" + DEFAULT_DATE);

        // Get all the godownPurchaseDetailsList where date equals to UPDATED_DATE
        defaultGodownPurchaseDetailsShouldNotBeFound("date.equals=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    public void getAllGodownPurchaseDetailsByDateIsInShouldWork() throws Exception {
        // Initialize the database
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);

        // Get all the godownPurchaseDetailsList where date in DEFAULT_DATE or UPDATED_DATE
        defaultGodownPurchaseDetailsShouldBeFound("date.in=" + DEFAULT_DATE + "," + UPDATED_DATE);

        // Get all the godownPurchaseDetailsList where date equals to UPDATED_DATE
        defaultGodownPurchaseDetailsShouldNotBeFound("date.in=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    public void getAllGodownPurchaseDetailsByDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);

        // Get all the godownPurchaseDetailsList where date is not null
        defaultGodownPurchaseDetailsShouldBeFound("date.specified=true");

        // Get all the godownPurchaseDetailsList where date is null
        defaultGodownPurchaseDetailsShouldNotBeFound("date.specified=false");
    }

    @Test
    @Transactional
    public void getAllGodownPurchaseDetailsByDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);

        // Get all the godownPurchaseDetailsList where date greater than or equals to DEFAULT_DATE
        defaultGodownPurchaseDetailsShouldBeFound("date.greaterOrEqualThan=" + DEFAULT_DATE);

        // Get all the godownPurchaseDetailsList where date greater than or equals to UPDATED_DATE
        defaultGodownPurchaseDetailsShouldNotBeFound("date.greaterOrEqualThan=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    public void getAllGodownPurchaseDetailsByDateIsLessThanSomething() throws Exception {
        // Initialize the database
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);

        // Get all the godownPurchaseDetailsList where date less than or equals to DEFAULT_DATE
        defaultGodownPurchaseDetailsShouldNotBeFound("date.lessThan=" + DEFAULT_DATE);

        // Get all the godownPurchaseDetailsList where date less than or equals to UPDATED_DATE
        defaultGodownPurchaseDetailsShouldBeFound("date.lessThan=" + UPDATED_DATE);
    }


    @Test
    @Transactional
    public void getAllGodownPurchaseDetailsByPriceIsEqualToSomething() throws Exception {
        // Initialize the database
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);

        // Get all the godownPurchaseDetailsList where price equals to DEFAULT_PRICE
        defaultGodownPurchaseDetailsShouldBeFound("price.equals=" + DEFAULT_PRICE);

        // Get all the godownPurchaseDetailsList where price equals to UPDATED_PRICE
        defaultGodownPurchaseDetailsShouldNotBeFound("price.equals=" + UPDATED_PRICE);
    }

    @Test
    @Transactional
    public void getAllGodownPurchaseDetailsByPriceIsInShouldWork() throws Exception {
        // Initialize the database
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);

        // Get all the godownPurchaseDetailsList where price in DEFAULT_PRICE or UPDATED_PRICE
        defaultGodownPurchaseDetailsShouldBeFound("price.in=" + DEFAULT_PRICE + "," + UPDATED_PRICE);

        // Get all the godownPurchaseDetailsList where price equals to UPDATED_PRICE
        defaultGodownPurchaseDetailsShouldNotBeFound("price.in=" + UPDATED_PRICE);
    }

    @Test
    @Transactional
    public void getAllGodownPurchaseDetailsByPriceIsNullOrNotNull() throws Exception {
        // Initialize the database
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);

        // Get all the godownPurchaseDetailsList where price is not null
        defaultGodownPurchaseDetailsShouldBeFound("price.specified=true");

        // Get all the godownPurchaseDetailsList where price is null
        defaultGodownPurchaseDetailsShouldNotBeFound("price.specified=false");
    }

    @Test
    @Transactional
    public void getAllGodownPurchaseDetailsByPriceIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);

        // Get all the godownPurchaseDetailsList where price greater than or equals to DEFAULT_PRICE
        defaultGodownPurchaseDetailsShouldBeFound("price.greaterOrEqualThan=" + DEFAULT_PRICE);

        // Get all the godownPurchaseDetailsList where price greater than or equals to UPDATED_PRICE
        defaultGodownPurchaseDetailsShouldNotBeFound("price.greaterOrEqualThan=" + UPDATED_PRICE);
    }

    @Test
    @Transactional
    public void getAllGodownPurchaseDetailsByPriceIsLessThanSomething() throws Exception {
        // Initialize the database
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);

        // Get all the godownPurchaseDetailsList where price less than or equals to DEFAULT_PRICE
        defaultGodownPurchaseDetailsShouldNotBeFound("price.lessThan=" + DEFAULT_PRICE);

        // Get all the godownPurchaseDetailsList where price less than or equals to UPDATED_PRICE
        defaultGodownPurchaseDetailsShouldBeFound("price.lessThan=" + UPDATED_PRICE);
    }


    @Test
    @Transactional
    public void getAllGodownPurchaseDetailsByOwnedByIsEqualToSomething() throws Exception {
        // Initialize the database
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);

        // Get all the godownPurchaseDetailsList where ownedBy equals to DEFAULT_OWNED_BY
        defaultGodownPurchaseDetailsShouldBeFound("ownedBy.equals=" + DEFAULT_OWNED_BY);

        // Get all the godownPurchaseDetailsList where ownedBy equals to UPDATED_OWNED_BY
        defaultGodownPurchaseDetailsShouldNotBeFound("ownedBy.equals=" + UPDATED_OWNED_BY);
    }

    @Test
    @Transactional
    public void getAllGodownPurchaseDetailsByOwnedByIsInShouldWork() throws Exception {
        // Initialize the database
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);

        // Get all the godownPurchaseDetailsList where ownedBy in DEFAULT_OWNED_BY or UPDATED_OWNED_BY
        defaultGodownPurchaseDetailsShouldBeFound("ownedBy.in=" + DEFAULT_OWNED_BY + "," + UPDATED_OWNED_BY);

        // Get all the godownPurchaseDetailsList where ownedBy equals to UPDATED_OWNED_BY
        defaultGodownPurchaseDetailsShouldNotBeFound("ownedBy.in=" + UPDATED_OWNED_BY);
    }

    @Test
    @Transactional
    public void getAllGodownPurchaseDetailsByOwnedByIsNullOrNotNull() throws Exception {
        // Initialize the database
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);

        // Get all the godownPurchaseDetailsList where ownedBy is not null
        defaultGodownPurchaseDetailsShouldBeFound("ownedBy.specified=true");

        // Get all the godownPurchaseDetailsList where ownedBy is null
        defaultGodownPurchaseDetailsShouldNotBeFound("ownedBy.specified=false");
    }

    @Test
    @Transactional
    public void getAllGodownPurchaseDetailsByVendorNameIsEqualToSomething() throws Exception {
        // Initialize the database
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);

        // Get all the godownPurchaseDetailsList where vendorName equals to DEFAULT_VENDOR_NAME
        defaultGodownPurchaseDetailsShouldBeFound("vendorName.equals=" + DEFAULT_VENDOR_NAME);

        // Get all the godownPurchaseDetailsList where vendorName equals to UPDATED_VENDOR_NAME
        defaultGodownPurchaseDetailsShouldNotBeFound("vendorName.equals=" + UPDATED_VENDOR_NAME);
    }

    @Test
    @Transactional
    public void getAllGodownPurchaseDetailsByVendorNameIsInShouldWork() throws Exception {
        // Initialize the database
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);

        // Get all the godownPurchaseDetailsList where vendorName in DEFAULT_VENDOR_NAME or UPDATED_VENDOR_NAME
        defaultGodownPurchaseDetailsShouldBeFound("vendorName.in=" + DEFAULT_VENDOR_NAME + "," + UPDATED_VENDOR_NAME);

        // Get all the godownPurchaseDetailsList where vendorName equals to UPDATED_VENDOR_NAME
        defaultGodownPurchaseDetailsShouldNotBeFound("vendorName.in=" + UPDATED_VENDOR_NAME);
    }

    @Test
    @Transactional
    public void getAllGodownPurchaseDetailsByVendorNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);

        // Get all the godownPurchaseDetailsList where vendorName is not null
        defaultGodownPurchaseDetailsShouldBeFound("vendorName.specified=true");

        // Get all the godownPurchaseDetailsList where vendorName is null
        defaultGodownPurchaseDetailsShouldNotBeFound("vendorName.specified=false");
    }

    @Test
    @Transactional
    public void getAllGodownPurchaseDetailsByVendorAddressIsEqualToSomething() throws Exception {
        // Initialize the database
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);

        // Get all the godownPurchaseDetailsList where vendorAddress equals to DEFAULT_VENDOR_ADDRESS
        defaultGodownPurchaseDetailsShouldBeFound("vendorAddress.equals=" + DEFAULT_VENDOR_ADDRESS);

        // Get all the godownPurchaseDetailsList where vendorAddress equals to UPDATED_VENDOR_ADDRESS
        defaultGodownPurchaseDetailsShouldNotBeFound("vendorAddress.equals=" + UPDATED_VENDOR_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllGodownPurchaseDetailsByVendorAddressIsInShouldWork() throws Exception {
        // Initialize the database
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);

        // Get all the godownPurchaseDetailsList where vendorAddress in DEFAULT_VENDOR_ADDRESS or UPDATED_VENDOR_ADDRESS
        defaultGodownPurchaseDetailsShouldBeFound("vendorAddress.in=" + DEFAULT_VENDOR_ADDRESS + "," + UPDATED_VENDOR_ADDRESS);

        // Get all the godownPurchaseDetailsList where vendorAddress equals to UPDATED_VENDOR_ADDRESS
        defaultGodownPurchaseDetailsShouldNotBeFound("vendorAddress.in=" + UPDATED_VENDOR_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllGodownPurchaseDetailsByVendorAddressIsNullOrNotNull() throws Exception {
        // Initialize the database
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);

        // Get all the godownPurchaseDetailsList where vendorAddress is not null
        defaultGodownPurchaseDetailsShouldBeFound("vendorAddress.specified=true");

        // Get all the godownPurchaseDetailsList where vendorAddress is null
        defaultGodownPurchaseDetailsShouldNotBeFound("vendorAddress.specified=false");
    }

    @Test
    @Transactional
    public void getAllGodownPurchaseDetailsByVendorPhoneIsEqualToSomething() throws Exception {
        // Initialize the database
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);

        // Get all the godownPurchaseDetailsList where vendorPhone equals to DEFAULT_VENDOR_PHONE
        defaultGodownPurchaseDetailsShouldBeFound("vendorPhone.equals=" + DEFAULT_VENDOR_PHONE);

        // Get all the godownPurchaseDetailsList where vendorPhone equals to UPDATED_VENDOR_PHONE
        defaultGodownPurchaseDetailsShouldNotBeFound("vendorPhone.equals=" + UPDATED_VENDOR_PHONE);
    }

    @Test
    @Transactional
    public void getAllGodownPurchaseDetailsByVendorPhoneIsInShouldWork() throws Exception {
        // Initialize the database
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);

        // Get all the godownPurchaseDetailsList where vendorPhone in DEFAULT_VENDOR_PHONE or UPDATED_VENDOR_PHONE
        defaultGodownPurchaseDetailsShouldBeFound("vendorPhone.in=" + DEFAULT_VENDOR_PHONE + "," + UPDATED_VENDOR_PHONE);

        // Get all the godownPurchaseDetailsList where vendorPhone equals to UPDATED_VENDOR_PHONE
        defaultGodownPurchaseDetailsShouldNotBeFound("vendorPhone.in=" + UPDATED_VENDOR_PHONE);
    }

    @Test
    @Transactional
    public void getAllGodownPurchaseDetailsByVendorPhoneIsNullOrNotNull() throws Exception {
        // Initialize the database
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);

        // Get all the godownPurchaseDetailsList where vendorPhone is not null
        defaultGodownPurchaseDetailsShouldBeFound("vendorPhone.specified=true");

        // Get all the godownPurchaseDetailsList where vendorPhone is null
        defaultGodownPurchaseDetailsShouldNotBeFound("vendorPhone.specified=false");
    }

    @Test
    @Transactional
    public void getAllGodownPurchaseDetailsByVendorPhoneIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);

        // Get all the godownPurchaseDetailsList where vendorPhone greater than or equals to DEFAULT_VENDOR_PHONE
        defaultGodownPurchaseDetailsShouldBeFound("vendorPhone.greaterOrEqualThan=" + DEFAULT_VENDOR_PHONE);

        // Get all the godownPurchaseDetailsList where vendorPhone greater than or equals to UPDATED_VENDOR_PHONE
        defaultGodownPurchaseDetailsShouldNotBeFound("vendorPhone.greaterOrEqualThan=" + UPDATED_VENDOR_PHONE);
    }

    @Test
    @Transactional
    public void getAllGodownPurchaseDetailsByVendorPhoneIsLessThanSomething() throws Exception {
        // Initialize the database
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);

        // Get all the godownPurchaseDetailsList where vendorPhone less than or equals to DEFAULT_VENDOR_PHONE
        defaultGodownPurchaseDetailsShouldNotBeFound("vendorPhone.lessThan=" + DEFAULT_VENDOR_PHONE);

        // Get all the godownPurchaseDetailsList where vendorPhone less than or equals to UPDATED_VENDOR_PHONE
        defaultGodownPurchaseDetailsShouldBeFound("vendorPhone.lessThan=" + UPDATED_VENDOR_PHONE);
    }


    @Test
    @Transactional
    public void getAllGodownPurchaseDetailsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);

        // Get all the godownPurchaseDetailsList where description equals to DEFAULT_DESCRIPTION
        defaultGodownPurchaseDetailsShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the godownPurchaseDetailsList where description equals to UPDATED_DESCRIPTION
        defaultGodownPurchaseDetailsShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllGodownPurchaseDetailsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);

        // Get all the godownPurchaseDetailsList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultGodownPurchaseDetailsShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the godownPurchaseDetailsList where description equals to UPDATED_DESCRIPTION
        defaultGodownPurchaseDetailsShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllGodownPurchaseDetailsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);

        // Get all the godownPurchaseDetailsList where description is not null
        defaultGodownPurchaseDetailsShouldBeFound("description.specified=true");

        // Get all the godownPurchaseDetailsList where description is null
        defaultGodownPurchaseDetailsShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllGodownPurchaseDetailsByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);

        // Get all the godownPurchaseDetailsList where status equals to DEFAULT_STATUS
        defaultGodownPurchaseDetailsShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the godownPurchaseDetailsList where status equals to UPDATED_STATUS
        defaultGodownPurchaseDetailsShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllGodownPurchaseDetailsByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);

        // Get all the godownPurchaseDetailsList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultGodownPurchaseDetailsShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the godownPurchaseDetailsList where status equals to UPDATED_STATUS
        defaultGodownPurchaseDetailsShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllGodownPurchaseDetailsByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);

        // Get all the godownPurchaseDetailsList where status is not null
        defaultGodownPurchaseDetailsShouldBeFound("status.specified=true");

        // Get all the godownPurchaseDetailsList where status is null
        defaultGodownPurchaseDetailsShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllGodownPurchaseDetailsByStatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);

        // Get all the godownPurchaseDetailsList where status greater than or equals to DEFAULT_STATUS
        defaultGodownPurchaseDetailsShouldBeFound("status.greaterOrEqualThan=" + DEFAULT_STATUS);

        // Get all the godownPurchaseDetailsList where status greater than or equals to UPDATED_STATUS
        defaultGodownPurchaseDetailsShouldNotBeFound("status.greaterOrEqualThan=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllGodownPurchaseDetailsByStatusIsLessThanSomething() throws Exception {
        // Initialize the database
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);

        // Get all the godownPurchaseDetailsList where status less than or equals to DEFAULT_STATUS
        defaultGodownPurchaseDetailsShouldNotBeFound("status.lessThan=" + DEFAULT_STATUS);

        // Get all the godownPurchaseDetailsList where status less than or equals to UPDATED_STATUS
        defaultGodownPurchaseDetailsShouldBeFound("status.lessThan=" + UPDATED_STATUS);
    }


    @Test
    @Transactional
    public void getAllGodownPurchaseDetailsByPickListVarietyIsEqualToSomething() throws Exception {
        // Initialize the database
        PickListValue pickListVariety = PickListValueResourceIntTest.createEntity(em);
        em.persist(pickListVariety);
        em.flush();
        godownPurchaseDetails.setPickListVariety(pickListVariety);
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);
        Long pickListVarietyId = pickListVariety.getId();

        // Get all the godownPurchaseDetailsList where pickListVariety equals to pickListVarietyId
        defaultGodownPurchaseDetailsShouldBeFound("pickListVarietyId.equals=" + pickListVarietyId);

        // Get all the godownPurchaseDetailsList where pickListVariety equals to pickListVarietyId + 1
        defaultGodownPurchaseDetailsShouldNotBeFound("pickListVarietyId.equals=" + (pickListVarietyId + 1));
    }


    @Test
    @Transactional
    public void getAllGodownPurchaseDetailsByPickListCategoryIsEqualToSomething() throws Exception {
        // Initialize the database
        PickListValue pickListCategory = PickListValueResourceIntTest.createEntity(em);
        em.persist(pickListCategory);
        em.flush();
        godownPurchaseDetails.setPickListCategory(pickListCategory);
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);
        Long pickListCategoryId = pickListCategory.getId();

        // Get all the godownPurchaseDetailsList where pickListCategory equals to pickListCategoryId
        defaultGodownPurchaseDetailsShouldBeFound("pickListCategoryId.equals=" + pickListCategoryId);

        // Get all the godownPurchaseDetailsList where pickListCategory equals to pickListCategoryId + 1
        defaultGodownPurchaseDetailsShouldNotBeFound("pickListCategoryId.equals=" + (pickListCategoryId + 1));
    }


    @Test
    @Transactional
    public void getAllGodownPurchaseDetailsByPickListQuantityTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        PickListValue pickListQuantityType = PickListValueResourceIntTest.createEntity(em);
        em.persist(pickListQuantityType);
        em.flush();
        godownPurchaseDetails.setPickListQuantityType(pickListQuantityType);
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);
        Long pickListQuantityTypeId = pickListQuantityType.getId();

        // Get all the godownPurchaseDetailsList where pickListQuantityType equals to pickListQuantityTypeId
        defaultGodownPurchaseDetailsShouldBeFound("pickListQuantityTypeId.equals=" + pickListQuantityTypeId);

        // Get all the godownPurchaseDetailsList where pickListQuantityType equals to pickListQuantityTypeId + 1
        defaultGodownPurchaseDetailsShouldNotBeFound("pickListQuantityTypeId.equals=" + (pickListQuantityTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllGodownPurchaseDetailsByGodownIsEqualToSomething() throws Exception {
        // Initialize the database
        Godown godown = GodownResourceIntTest.createEntity(em);
        em.persist(godown);
        em.flush();
        godownPurchaseDetails.setGodown(godown);
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);
        Long godownId = godown.getId();

        // Get all the godownPurchaseDetailsList where godown equals to godownId
        defaultGodownPurchaseDetailsShouldBeFound("godownId.equals=" + godownId);

        // Get all the godownPurchaseDetailsList where godown equals to godownId + 1
        defaultGodownPurchaseDetailsShouldNotBeFound("godownId.equals=" + (godownId + 1));
    }


    @Test
    @Transactional
    public void getAllGodownPurchaseDetailsByFinancialYearGodownPurchaseIsEqualToSomething() throws Exception {
        // Initialize the database
        FinancialYearSettings financialYearGodownPurchase = FinancialYearSettingsResourceIntTest.createEntity(em);
        em.persist(financialYearGodownPurchase);
        em.flush();
        godownPurchaseDetails.setFinancialYearGodownPurchase(financialYearGodownPurchase);
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);
        Long financialYearGodownPurchaseId = financialYearGodownPurchase.getId();

        // Get all the godownPurchaseDetailsList where financialYearGodownPurchase equals to financialYearGodownPurchaseId
        defaultGodownPurchaseDetailsShouldBeFound("financialYearGodownPurchaseId.equals=" + financialYearGodownPurchaseId);

        // Get all the godownPurchaseDetailsList where financialYearGodownPurchase equals to financialYearGodownPurchaseId + 1
        defaultGodownPurchaseDetailsShouldNotBeFound("financialYearGodownPurchaseId.equals=" + (financialYearGodownPurchaseId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultGodownPurchaseDetailsShouldBeFound(String filter) throws Exception {
        restGodownPurchaseDetailsMockMvc.perform(get("/api/godown-purchase-details?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(godownPurchaseDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].ownedBy").value(hasItem(DEFAULT_OWNED_BY.toString())))
            .andExpect(jsonPath("$.[*].vendorName").value(hasItem(DEFAULT_VENDOR_NAME.toString())))
            .andExpect(jsonPath("$.[*].vendorAddress").value(hasItem(DEFAULT_VENDOR_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].vendorPhone").value(hasItem(DEFAULT_VENDOR_PHONE.intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultGodownPurchaseDetailsShouldNotBeFound(String filter) throws Exception {
        restGodownPurchaseDetailsMockMvc.perform(get("/api/godown-purchase-details?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingGodownPurchaseDetails() throws Exception {
        // Get the godownPurchaseDetails
        restGodownPurchaseDetailsMockMvc.perform(get("/api/godown-purchase-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGodownPurchaseDetails() throws Exception {
        // Initialize the database
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);

        int databaseSizeBeforeUpdate = godownPurchaseDetailsRepository.findAll().size();

        // Update the godownPurchaseDetails
        GodownPurchaseDetails updatedGodownPurchaseDetails = godownPurchaseDetailsRepository.findById(godownPurchaseDetails.getId()).get();
        // Disconnect from session so that the updates on updatedGodownPurchaseDetails are not directly saved in db
        em.detach(updatedGodownPurchaseDetails);
        updatedGodownPurchaseDetails
            .quantity(UPDATED_QUANTITY)
            .date(UPDATED_DATE)
            .price(UPDATED_PRICE)
            .ownedBy(UPDATED_OWNED_BY)
            .vendorName(UPDATED_VENDOR_NAME)
            .vendorAddress(UPDATED_VENDOR_ADDRESS)
            .vendorPhone(UPDATED_VENDOR_PHONE)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS);
        GodownPurchaseDetailsDTO godownPurchaseDetailsDTO = godownPurchaseDetailsMapper.toDto(updatedGodownPurchaseDetails);

        restGodownPurchaseDetailsMockMvc.perform(put("/api/godown-purchase-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(godownPurchaseDetailsDTO)))
            .andExpect(status().isOk());

        // Validate the GodownPurchaseDetails in the database
        List<GodownPurchaseDetails> godownPurchaseDetailsList = godownPurchaseDetailsRepository.findAll();
        assertThat(godownPurchaseDetailsList).hasSize(databaseSizeBeforeUpdate);
        GodownPurchaseDetails testGodownPurchaseDetails = godownPurchaseDetailsList.get(godownPurchaseDetailsList.size() - 1);
        assertThat(testGodownPurchaseDetails.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testGodownPurchaseDetails.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testGodownPurchaseDetails.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testGodownPurchaseDetails.getOwnedBy()).isEqualTo(UPDATED_OWNED_BY);
        assertThat(testGodownPurchaseDetails.getVendorName()).isEqualTo(UPDATED_VENDOR_NAME);
        assertThat(testGodownPurchaseDetails.getVendorAddress()).isEqualTo(UPDATED_VENDOR_ADDRESS);
        assertThat(testGodownPurchaseDetails.getVendorPhone()).isEqualTo(UPDATED_VENDOR_PHONE);
        assertThat(testGodownPurchaseDetails.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testGodownPurchaseDetails.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingGodownPurchaseDetails() throws Exception {
        int databaseSizeBeforeUpdate = godownPurchaseDetailsRepository.findAll().size();

        // Create the GodownPurchaseDetails
        GodownPurchaseDetailsDTO godownPurchaseDetailsDTO = godownPurchaseDetailsMapper.toDto(godownPurchaseDetails);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restGodownPurchaseDetailsMockMvc.perform(put("/api/godown-purchase-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(godownPurchaseDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GodownPurchaseDetails in the database
        List<GodownPurchaseDetails> godownPurchaseDetailsList = godownPurchaseDetailsRepository.findAll();
        assertThat(godownPurchaseDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGodownPurchaseDetails() throws Exception {
        // Initialize the database
        godownPurchaseDetailsRepository.saveAndFlush(godownPurchaseDetails);

        int databaseSizeBeforeDelete = godownPurchaseDetailsRepository.findAll().size();

        // Get the godownPurchaseDetails
        restGodownPurchaseDetailsMockMvc.perform(delete("/api/godown-purchase-details/{id}", godownPurchaseDetails.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<GodownPurchaseDetails> godownPurchaseDetailsList = godownPurchaseDetailsRepository.findAll();
        assertThat(godownPurchaseDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GodownPurchaseDetails.class);
        GodownPurchaseDetails godownPurchaseDetails1 = new GodownPurchaseDetails();
        godownPurchaseDetails1.setId(1L);
        GodownPurchaseDetails godownPurchaseDetails2 = new GodownPurchaseDetails();
        godownPurchaseDetails2.setId(godownPurchaseDetails1.getId());
        assertThat(godownPurchaseDetails1).isEqualTo(godownPurchaseDetails2);
        godownPurchaseDetails2.setId(2L);
        assertThat(godownPurchaseDetails1).isNotEqualTo(godownPurchaseDetails2);
        godownPurchaseDetails1.setId(null);
        assertThat(godownPurchaseDetails1).isNotEqualTo(godownPurchaseDetails2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GodownPurchaseDetailsDTO.class);
        GodownPurchaseDetailsDTO godownPurchaseDetailsDTO1 = new GodownPurchaseDetailsDTO();
        godownPurchaseDetailsDTO1.setId(1L);
        GodownPurchaseDetailsDTO godownPurchaseDetailsDTO2 = new GodownPurchaseDetailsDTO();
        assertThat(godownPurchaseDetailsDTO1).isNotEqualTo(godownPurchaseDetailsDTO2);
        godownPurchaseDetailsDTO2.setId(godownPurchaseDetailsDTO1.getId());
        assertThat(godownPurchaseDetailsDTO1).isEqualTo(godownPurchaseDetailsDTO2);
        godownPurchaseDetailsDTO2.setId(2L);
        assertThat(godownPurchaseDetailsDTO1).isNotEqualTo(godownPurchaseDetailsDTO2);
        godownPurchaseDetailsDTO1.setId(null);
        assertThat(godownPurchaseDetailsDTO1).isNotEqualTo(godownPurchaseDetailsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(godownPurchaseDetailsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(godownPurchaseDetailsMapper.fromId(null)).isNull();
    }
}
