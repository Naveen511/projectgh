package com.niche.ng.web.rest;

import com.niche.ng.ProjectghApp;

import com.niche.ng.domain.PointOfSaleDetails;
import com.niche.ng.domain.PickListValue;
import com.niche.ng.domain.PickListValue;
import com.niche.ng.domain.NurseryStock;
import com.niche.ng.repository.PointOfSaleDetailsRepository;
import com.niche.ng.service.PointOfSaleDetailsService;
import com.niche.ng.service.dto.PointOfSaleDetailsDTO;
import com.niche.ng.service.mapper.PointOfSaleDetailsMapper;
import com.niche.ng.web.rest.errors.ExceptionTranslator;
import com.niche.ng.service.dto.PointOfSaleDetailsCriteria;
import com.niche.ng.service.PointOfSaleDetailsQueryService;

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
 * Test class for the PointOfSaleDetailsResource REST controller.
 *
 * @see PointOfSaleDetailsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectghApp.class)
public class PointOfSaleDetailsResourceIntTest {

    private static final Integer DEFAULT_QUANTITY = 1;
    private static final Integer UPDATED_QUANTITY = 2;

    private static final String DEFAULT_PURPOSE_OF_TAKING = "AAAAAAAAAA";
    private static final String UPDATED_PURPOSE_OF_TAKING = "BBBBBBBBBB";

    private static final String DEFAULT_DONOR_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DONOR_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DONOR_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_DONOR_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_NO = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_NO = "BBBBBBBBBB";

    private static final Float DEFAULT_DISCOUNT = 1F;
    private static final Float UPDATED_DISCOUNT = 2F;

    private static final Float DEFAULT_DISCOUNT_AMOUNT = 1F;
    private static final Float UPDATED_DISCOUNT_AMOUNT = 2F;

    private static final Float DEFAULT_COLLECTED_AMOUNT = 1F;
    private static final Float UPDATED_COLLECTED_AMOUNT = 2F;

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    @Autowired
    private PointOfSaleDetailsRepository pointOfSaleDetailsRepository;


    @Autowired
    private PointOfSaleDetailsMapper pointOfSaleDetailsMapper;
    

    @Autowired
    private PointOfSaleDetailsService pointOfSaleDetailsService;

    @Autowired
    private PointOfSaleDetailsQueryService pointOfSaleDetailsQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPointOfSaleDetailsMockMvc;

    private PointOfSaleDetails pointOfSaleDetails;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PointOfSaleDetailsResource pointOfSaleDetailsResource = new PointOfSaleDetailsResource(pointOfSaleDetailsService, pointOfSaleDetailsQueryService);
        this.restPointOfSaleDetailsMockMvc = MockMvcBuilders.standaloneSetup(pointOfSaleDetailsResource)
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
    public static PointOfSaleDetails createEntity(EntityManager em) {
        PointOfSaleDetails pointOfSaleDetails = new PointOfSaleDetails()
            .quantity(DEFAULT_QUANTITY)
            .purposeOfTaking(DEFAULT_PURPOSE_OF_TAKING)
            .donorName(DEFAULT_DONOR_NAME)
            .donorAddress(DEFAULT_DONOR_ADDRESS)
            .contactNo(DEFAULT_CONTACT_NO)
            .discount(DEFAULT_DISCOUNT)
            .discountAmount(DEFAULT_DISCOUNT_AMOUNT)
            .collectedAmount(DEFAULT_COLLECTED_AMOUNT)
            .date(DEFAULT_DATE)
            .status(DEFAULT_STATUS);
        return pointOfSaleDetails;
    }

    @Before
    public void initTest() {
        pointOfSaleDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createPointOfSaleDetails() throws Exception {
        int databaseSizeBeforeCreate = pointOfSaleDetailsRepository.findAll().size();

        // Create the PointOfSaleDetails
        PointOfSaleDetailsDTO pointOfSaleDetailsDTO = pointOfSaleDetailsMapper.toDto(pointOfSaleDetails);
        restPointOfSaleDetailsMockMvc.perform(post("/api/point-of-sale-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pointOfSaleDetailsDTO)))
            .andExpect(status().isCreated());

        // Validate the PointOfSaleDetails in the database
        List<PointOfSaleDetails> pointOfSaleDetailsList = pointOfSaleDetailsRepository.findAll();
        assertThat(pointOfSaleDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        PointOfSaleDetails testPointOfSaleDetails = pointOfSaleDetailsList.get(pointOfSaleDetailsList.size() - 1);
        assertThat(testPointOfSaleDetails.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testPointOfSaleDetails.getPurposeOfTaking()).isEqualTo(DEFAULT_PURPOSE_OF_TAKING);
        assertThat(testPointOfSaleDetails.getDonorName()).isEqualTo(DEFAULT_DONOR_NAME);
        assertThat(testPointOfSaleDetails.getDonorAddress()).isEqualTo(DEFAULT_DONOR_ADDRESS);
        assertThat(testPointOfSaleDetails.getContactNo()).isEqualTo(DEFAULT_CONTACT_NO);
        assertThat(testPointOfSaleDetails.getDiscount()).isEqualTo(DEFAULT_DISCOUNT);
        assertThat(testPointOfSaleDetails.getDiscountAmount()).isEqualTo(DEFAULT_DISCOUNT_AMOUNT);
        assertThat(testPointOfSaleDetails.getCollectedAmount()).isEqualTo(DEFAULT_COLLECTED_AMOUNT);
        assertThat(testPointOfSaleDetails.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testPointOfSaleDetails.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createPointOfSaleDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pointOfSaleDetailsRepository.findAll().size();

        // Create the PointOfSaleDetails with an existing ID
        pointOfSaleDetails.setId(1L);
        PointOfSaleDetailsDTO pointOfSaleDetailsDTO = pointOfSaleDetailsMapper.toDto(pointOfSaleDetails);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPointOfSaleDetailsMockMvc.perform(post("/api/point-of-sale-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pointOfSaleDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PointOfSaleDetails in the database
        List<PointOfSaleDetails> pointOfSaleDetailsList = pointOfSaleDetailsRepository.findAll();
        assertThat(pointOfSaleDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPointOfSaleDetails() throws Exception {
        // Initialize the database
        pointOfSaleDetailsRepository.saveAndFlush(pointOfSaleDetails);

        // Get all the pointOfSaleDetailsList
        restPointOfSaleDetailsMockMvc.perform(get("/api/point-of-sale-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pointOfSaleDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)))
            .andExpect(jsonPath("$.[*].purposeOfTaking").value(hasItem(DEFAULT_PURPOSE_OF_TAKING.toString())))
            .andExpect(jsonPath("$.[*].donorName").value(hasItem(DEFAULT_DONOR_NAME.toString())))
            .andExpect(jsonPath("$.[*].donorAddress").value(hasItem(DEFAULT_DONOR_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].contactNo").value(hasItem(DEFAULT_CONTACT_NO.toString())))
            .andExpect(jsonPath("$.[*].discount").value(hasItem(DEFAULT_DISCOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].discountAmount").value(hasItem(DEFAULT_DISCOUNT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].collectedAmount").value(hasItem(DEFAULT_COLLECTED_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    

    @Test
    @Transactional
    public void getPointOfSaleDetails() throws Exception {
        // Initialize the database
        pointOfSaleDetailsRepository.saveAndFlush(pointOfSaleDetails);

        // Get the pointOfSaleDetails
        restPointOfSaleDetailsMockMvc.perform(get("/api/point-of-sale-details/{id}", pointOfSaleDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pointOfSaleDetails.getId().intValue()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY))
            .andExpect(jsonPath("$.purposeOfTaking").value(DEFAULT_PURPOSE_OF_TAKING.toString()))
            .andExpect(jsonPath("$.donorName").value(DEFAULT_DONOR_NAME.toString()))
            .andExpect(jsonPath("$.donorAddress").value(DEFAULT_DONOR_ADDRESS.toString()))
            .andExpect(jsonPath("$.contactNo").value(DEFAULT_CONTACT_NO.toString()))
            .andExpect(jsonPath("$.discount").value(DEFAULT_DISCOUNT.doubleValue()))
            .andExpect(jsonPath("$.discountAmount").value(DEFAULT_DISCOUNT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.collectedAmount").value(DEFAULT_COLLECTED_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getAllPointOfSaleDetailsByQuantityIsEqualToSomething() throws Exception {
        // Initialize the database
        pointOfSaleDetailsRepository.saveAndFlush(pointOfSaleDetails);

        // Get all the pointOfSaleDetailsList where quantity equals to DEFAULT_QUANTITY
        defaultPointOfSaleDetailsShouldBeFound("quantity.equals=" + DEFAULT_QUANTITY);

        // Get all the pointOfSaleDetailsList where quantity equals to UPDATED_QUANTITY
        defaultPointOfSaleDetailsShouldNotBeFound("quantity.equals=" + UPDATED_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllPointOfSaleDetailsByQuantityIsInShouldWork() throws Exception {
        // Initialize the database
        pointOfSaleDetailsRepository.saveAndFlush(pointOfSaleDetails);

        // Get all the pointOfSaleDetailsList where quantity in DEFAULT_QUANTITY or UPDATED_QUANTITY
        defaultPointOfSaleDetailsShouldBeFound("quantity.in=" + DEFAULT_QUANTITY + "," + UPDATED_QUANTITY);

        // Get all the pointOfSaleDetailsList where quantity equals to UPDATED_QUANTITY
        defaultPointOfSaleDetailsShouldNotBeFound("quantity.in=" + UPDATED_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllPointOfSaleDetailsByQuantityIsNullOrNotNull() throws Exception {
        // Initialize the database
        pointOfSaleDetailsRepository.saveAndFlush(pointOfSaleDetails);

        // Get all the pointOfSaleDetailsList where quantity is not null
        defaultPointOfSaleDetailsShouldBeFound("quantity.specified=true");

        // Get all the pointOfSaleDetailsList where quantity is null
        defaultPointOfSaleDetailsShouldNotBeFound("quantity.specified=false");
    }

    @Test
    @Transactional
    public void getAllPointOfSaleDetailsByQuantityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pointOfSaleDetailsRepository.saveAndFlush(pointOfSaleDetails);

        // Get all the pointOfSaleDetailsList where quantity greater than or equals to DEFAULT_QUANTITY
        defaultPointOfSaleDetailsShouldBeFound("quantity.greaterOrEqualThan=" + DEFAULT_QUANTITY);

        // Get all the pointOfSaleDetailsList where quantity greater than or equals to UPDATED_QUANTITY
        defaultPointOfSaleDetailsShouldNotBeFound("quantity.greaterOrEqualThan=" + UPDATED_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllPointOfSaleDetailsByQuantityIsLessThanSomething() throws Exception {
        // Initialize the database
        pointOfSaleDetailsRepository.saveAndFlush(pointOfSaleDetails);

        // Get all the pointOfSaleDetailsList where quantity less than or equals to DEFAULT_QUANTITY
        defaultPointOfSaleDetailsShouldNotBeFound("quantity.lessThan=" + DEFAULT_QUANTITY);

        // Get all the pointOfSaleDetailsList where quantity less than or equals to UPDATED_QUANTITY
        defaultPointOfSaleDetailsShouldBeFound("quantity.lessThan=" + UPDATED_QUANTITY);
    }


    @Test
    @Transactional
    public void getAllPointOfSaleDetailsByPurposeOfTakingIsEqualToSomething() throws Exception {
        // Initialize the database
        pointOfSaleDetailsRepository.saveAndFlush(pointOfSaleDetails);

        // Get all the pointOfSaleDetailsList where purposeOfTaking equals to DEFAULT_PURPOSE_OF_TAKING
        defaultPointOfSaleDetailsShouldBeFound("purposeOfTaking.equals=" + DEFAULT_PURPOSE_OF_TAKING);

        // Get all the pointOfSaleDetailsList where purposeOfTaking equals to UPDATED_PURPOSE_OF_TAKING
        defaultPointOfSaleDetailsShouldNotBeFound("purposeOfTaking.equals=" + UPDATED_PURPOSE_OF_TAKING);
    }

    @Test
    @Transactional
    public void getAllPointOfSaleDetailsByPurposeOfTakingIsInShouldWork() throws Exception {
        // Initialize the database
        pointOfSaleDetailsRepository.saveAndFlush(pointOfSaleDetails);

        // Get all the pointOfSaleDetailsList where purposeOfTaking in DEFAULT_PURPOSE_OF_TAKING or UPDATED_PURPOSE_OF_TAKING
        defaultPointOfSaleDetailsShouldBeFound("purposeOfTaking.in=" + DEFAULT_PURPOSE_OF_TAKING + "," + UPDATED_PURPOSE_OF_TAKING);

        // Get all the pointOfSaleDetailsList where purposeOfTaking equals to UPDATED_PURPOSE_OF_TAKING
        defaultPointOfSaleDetailsShouldNotBeFound("purposeOfTaking.in=" + UPDATED_PURPOSE_OF_TAKING);
    }

    @Test
    @Transactional
    public void getAllPointOfSaleDetailsByPurposeOfTakingIsNullOrNotNull() throws Exception {
        // Initialize the database
        pointOfSaleDetailsRepository.saveAndFlush(pointOfSaleDetails);

        // Get all the pointOfSaleDetailsList where purposeOfTaking is not null
        defaultPointOfSaleDetailsShouldBeFound("purposeOfTaking.specified=true");

        // Get all the pointOfSaleDetailsList where purposeOfTaking is null
        defaultPointOfSaleDetailsShouldNotBeFound("purposeOfTaking.specified=false");
    }

    @Test
    @Transactional
    public void getAllPointOfSaleDetailsByDonorNameIsEqualToSomething() throws Exception {
        // Initialize the database
        pointOfSaleDetailsRepository.saveAndFlush(pointOfSaleDetails);

        // Get all the pointOfSaleDetailsList where donorName equals to DEFAULT_DONOR_NAME
        defaultPointOfSaleDetailsShouldBeFound("donorName.equals=" + DEFAULT_DONOR_NAME);

        // Get all the pointOfSaleDetailsList where donorName equals to UPDATED_DONOR_NAME
        defaultPointOfSaleDetailsShouldNotBeFound("donorName.equals=" + UPDATED_DONOR_NAME);
    }

    @Test
    @Transactional
    public void getAllPointOfSaleDetailsByDonorNameIsInShouldWork() throws Exception {
        // Initialize the database
        pointOfSaleDetailsRepository.saveAndFlush(pointOfSaleDetails);

        // Get all the pointOfSaleDetailsList where donorName in DEFAULT_DONOR_NAME or UPDATED_DONOR_NAME
        defaultPointOfSaleDetailsShouldBeFound("donorName.in=" + DEFAULT_DONOR_NAME + "," + UPDATED_DONOR_NAME);

        // Get all the pointOfSaleDetailsList where donorName equals to UPDATED_DONOR_NAME
        defaultPointOfSaleDetailsShouldNotBeFound("donorName.in=" + UPDATED_DONOR_NAME);
    }

    @Test
    @Transactional
    public void getAllPointOfSaleDetailsByDonorNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        pointOfSaleDetailsRepository.saveAndFlush(pointOfSaleDetails);

        // Get all the pointOfSaleDetailsList where donorName is not null
        defaultPointOfSaleDetailsShouldBeFound("donorName.specified=true");

        // Get all the pointOfSaleDetailsList where donorName is null
        defaultPointOfSaleDetailsShouldNotBeFound("donorName.specified=false");
    }

    @Test
    @Transactional
    public void getAllPointOfSaleDetailsByDonorAddressIsEqualToSomething() throws Exception {
        // Initialize the database
        pointOfSaleDetailsRepository.saveAndFlush(pointOfSaleDetails);

        // Get all the pointOfSaleDetailsList where donorAddress equals to DEFAULT_DONOR_ADDRESS
        defaultPointOfSaleDetailsShouldBeFound("donorAddress.equals=" + DEFAULT_DONOR_ADDRESS);

        // Get all the pointOfSaleDetailsList where donorAddress equals to UPDATED_DONOR_ADDRESS
        defaultPointOfSaleDetailsShouldNotBeFound("donorAddress.equals=" + UPDATED_DONOR_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllPointOfSaleDetailsByDonorAddressIsInShouldWork() throws Exception {
        // Initialize the database
        pointOfSaleDetailsRepository.saveAndFlush(pointOfSaleDetails);

        // Get all the pointOfSaleDetailsList where donorAddress in DEFAULT_DONOR_ADDRESS or UPDATED_DONOR_ADDRESS
        defaultPointOfSaleDetailsShouldBeFound("donorAddress.in=" + DEFAULT_DONOR_ADDRESS + "," + UPDATED_DONOR_ADDRESS);

        // Get all the pointOfSaleDetailsList where donorAddress equals to UPDATED_DONOR_ADDRESS
        defaultPointOfSaleDetailsShouldNotBeFound("donorAddress.in=" + UPDATED_DONOR_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllPointOfSaleDetailsByDonorAddressIsNullOrNotNull() throws Exception {
        // Initialize the database
        pointOfSaleDetailsRepository.saveAndFlush(pointOfSaleDetails);

        // Get all the pointOfSaleDetailsList where donorAddress is not null
        defaultPointOfSaleDetailsShouldBeFound("donorAddress.specified=true");

        // Get all the pointOfSaleDetailsList where donorAddress is null
        defaultPointOfSaleDetailsShouldNotBeFound("donorAddress.specified=false");
    }

    @Test
    @Transactional
    public void getAllPointOfSaleDetailsByContactNoIsEqualToSomething() throws Exception {
        // Initialize the database
        pointOfSaleDetailsRepository.saveAndFlush(pointOfSaleDetails);

        // Get all the pointOfSaleDetailsList where contactNo equals to DEFAULT_CONTACT_NO
        defaultPointOfSaleDetailsShouldBeFound("contactNo.equals=" + DEFAULT_CONTACT_NO);

        // Get all the pointOfSaleDetailsList where contactNo equals to UPDATED_CONTACT_NO
        defaultPointOfSaleDetailsShouldNotBeFound("contactNo.equals=" + UPDATED_CONTACT_NO);
    }

    @Test
    @Transactional
    public void getAllPointOfSaleDetailsByContactNoIsInShouldWork() throws Exception {
        // Initialize the database
        pointOfSaleDetailsRepository.saveAndFlush(pointOfSaleDetails);

        // Get all the pointOfSaleDetailsList where contactNo in DEFAULT_CONTACT_NO or UPDATED_CONTACT_NO
        defaultPointOfSaleDetailsShouldBeFound("contactNo.in=" + DEFAULT_CONTACT_NO + "," + UPDATED_CONTACT_NO);

        // Get all the pointOfSaleDetailsList where contactNo equals to UPDATED_CONTACT_NO
        defaultPointOfSaleDetailsShouldNotBeFound("contactNo.in=" + UPDATED_CONTACT_NO);
    }

    @Test
    @Transactional
    public void getAllPointOfSaleDetailsByContactNoIsNullOrNotNull() throws Exception {
        // Initialize the database
        pointOfSaleDetailsRepository.saveAndFlush(pointOfSaleDetails);

        // Get all the pointOfSaleDetailsList where contactNo is not null
        defaultPointOfSaleDetailsShouldBeFound("contactNo.specified=true");

        // Get all the pointOfSaleDetailsList where contactNo is null
        defaultPointOfSaleDetailsShouldNotBeFound("contactNo.specified=false");
    }

    @Test
    @Transactional
    public void getAllPointOfSaleDetailsByDiscountIsEqualToSomething() throws Exception {
        // Initialize the database
        pointOfSaleDetailsRepository.saveAndFlush(pointOfSaleDetails);

        // Get all the pointOfSaleDetailsList where discount equals to DEFAULT_DISCOUNT
        defaultPointOfSaleDetailsShouldBeFound("discount.equals=" + DEFAULT_DISCOUNT);

        // Get all the pointOfSaleDetailsList where discount equals to UPDATED_DISCOUNT
        defaultPointOfSaleDetailsShouldNotBeFound("discount.equals=" + UPDATED_DISCOUNT);
    }

    @Test
    @Transactional
    public void getAllPointOfSaleDetailsByDiscountIsInShouldWork() throws Exception {
        // Initialize the database
        pointOfSaleDetailsRepository.saveAndFlush(pointOfSaleDetails);

        // Get all the pointOfSaleDetailsList where discount in DEFAULT_DISCOUNT or UPDATED_DISCOUNT
        defaultPointOfSaleDetailsShouldBeFound("discount.in=" + DEFAULT_DISCOUNT + "," + UPDATED_DISCOUNT);

        // Get all the pointOfSaleDetailsList where discount equals to UPDATED_DISCOUNT
        defaultPointOfSaleDetailsShouldNotBeFound("discount.in=" + UPDATED_DISCOUNT);
    }

    @Test
    @Transactional
    public void getAllPointOfSaleDetailsByDiscountIsNullOrNotNull() throws Exception {
        // Initialize the database
        pointOfSaleDetailsRepository.saveAndFlush(pointOfSaleDetails);

        // Get all the pointOfSaleDetailsList where discount is not null
        defaultPointOfSaleDetailsShouldBeFound("discount.specified=true");

        // Get all the pointOfSaleDetailsList where discount is null
        defaultPointOfSaleDetailsShouldNotBeFound("discount.specified=false");
    }

    @Test
    @Transactional
    public void getAllPointOfSaleDetailsByDiscountAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        pointOfSaleDetailsRepository.saveAndFlush(pointOfSaleDetails);

        // Get all the pointOfSaleDetailsList where discountAmount equals to DEFAULT_DISCOUNT_AMOUNT
        defaultPointOfSaleDetailsShouldBeFound("discountAmount.equals=" + DEFAULT_DISCOUNT_AMOUNT);

        // Get all the pointOfSaleDetailsList where discountAmount equals to UPDATED_DISCOUNT_AMOUNT
        defaultPointOfSaleDetailsShouldNotBeFound("discountAmount.equals=" + UPDATED_DISCOUNT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllPointOfSaleDetailsByDiscountAmountIsInShouldWork() throws Exception {
        // Initialize the database
        pointOfSaleDetailsRepository.saveAndFlush(pointOfSaleDetails);

        // Get all the pointOfSaleDetailsList where discountAmount in DEFAULT_DISCOUNT_AMOUNT or UPDATED_DISCOUNT_AMOUNT
        defaultPointOfSaleDetailsShouldBeFound("discountAmount.in=" + DEFAULT_DISCOUNT_AMOUNT + "," + UPDATED_DISCOUNT_AMOUNT);

        // Get all the pointOfSaleDetailsList where discountAmount equals to UPDATED_DISCOUNT_AMOUNT
        defaultPointOfSaleDetailsShouldNotBeFound("discountAmount.in=" + UPDATED_DISCOUNT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllPointOfSaleDetailsByDiscountAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        pointOfSaleDetailsRepository.saveAndFlush(pointOfSaleDetails);

        // Get all the pointOfSaleDetailsList where discountAmount is not null
        defaultPointOfSaleDetailsShouldBeFound("discountAmount.specified=true");

        // Get all the pointOfSaleDetailsList where discountAmount is null
        defaultPointOfSaleDetailsShouldNotBeFound("discountAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllPointOfSaleDetailsByCollectedAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        pointOfSaleDetailsRepository.saveAndFlush(pointOfSaleDetails);

        // Get all the pointOfSaleDetailsList where collectedAmount equals to DEFAULT_COLLECTED_AMOUNT
        defaultPointOfSaleDetailsShouldBeFound("collectedAmount.equals=" + DEFAULT_COLLECTED_AMOUNT);

        // Get all the pointOfSaleDetailsList where collectedAmount equals to UPDATED_COLLECTED_AMOUNT
        defaultPointOfSaleDetailsShouldNotBeFound("collectedAmount.equals=" + UPDATED_COLLECTED_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllPointOfSaleDetailsByCollectedAmountIsInShouldWork() throws Exception {
        // Initialize the database
        pointOfSaleDetailsRepository.saveAndFlush(pointOfSaleDetails);

        // Get all the pointOfSaleDetailsList where collectedAmount in DEFAULT_COLLECTED_AMOUNT or UPDATED_COLLECTED_AMOUNT
        defaultPointOfSaleDetailsShouldBeFound("collectedAmount.in=" + DEFAULT_COLLECTED_AMOUNT + "," + UPDATED_COLLECTED_AMOUNT);

        // Get all the pointOfSaleDetailsList where collectedAmount equals to UPDATED_COLLECTED_AMOUNT
        defaultPointOfSaleDetailsShouldNotBeFound("collectedAmount.in=" + UPDATED_COLLECTED_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllPointOfSaleDetailsByCollectedAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        pointOfSaleDetailsRepository.saveAndFlush(pointOfSaleDetails);

        // Get all the pointOfSaleDetailsList where collectedAmount is not null
        defaultPointOfSaleDetailsShouldBeFound("collectedAmount.specified=true");

        // Get all the pointOfSaleDetailsList where collectedAmount is null
        defaultPointOfSaleDetailsShouldNotBeFound("collectedAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllPointOfSaleDetailsByDateIsEqualToSomething() throws Exception {
        // Initialize the database
        pointOfSaleDetailsRepository.saveAndFlush(pointOfSaleDetails);

        // Get all the pointOfSaleDetailsList where date equals to DEFAULT_DATE
        defaultPointOfSaleDetailsShouldBeFound("date.equals=" + DEFAULT_DATE);

        // Get all the pointOfSaleDetailsList where date equals to UPDATED_DATE
        defaultPointOfSaleDetailsShouldNotBeFound("date.equals=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    public void getAllPointOfSaleDetailsByDateIsInShouldWork() throws Exception {
        // Initialize the database
        pointOfSaleDetailsRepository.saveAndFlush(pointOfSaleDetails);

        // Get all the pointOfSaleDetailsList where date in DEFAULT_DATE or UPDATED_DATE
        defaultPointOfSaleDetailsShouldBeFound("date.in=" + DEFAULT_DATE + "," + UPDATED_DATE);

        // Get all the pointOfSaleDetailsList where date equals to UPDATED_DATE
        defaultPointOfSaleDetailsShouldNotBeFound("date.in=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    public void getAllPointOfSaleDetailsByDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        pointOfSaleDetailsRepository.saveAndFlush(pointOfSaleDetails);

        // Get all the pointOfSaleDetailsList where date is not null
        defaultPointOfSaleDetailsShouldBeFound("date.specified=true");

        // Get all the pointOfSaleDetailsList where date is null
        defaultPointOfSaleDetailsShouldNotBeFound("date.specified=false");
    }

    @Test
    @Transactional
    public void getAllPointOfSaleDetailsByDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pointOfSaleDetailsRepository.saveAndFlush(pointOfSaleDetails);

        // Get all the pointOfSaleDetailsList where date greater than or equals to DEFAULT_DATE
        defaultPointOfSaleDetailsShouldBeFound("date.greaterOrEqualThan=" + DEFAULT_DATE);

        // Get all the pointOfSaleDetailsList where date greater than or equals to UPDATED_DATE
        defaultPointOfSaleDetailsShouldNotBeFound("date.greaterOrEqualThan=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    public void getAllPointOfSaleDetailsByDateIsLessThanSomething() throws Exception {
        // Initialize the database
        pointOfSaleDetailsRepository.saveAndFlush(pointOfSaleDetails);

        // Get all the pointOfSaleDetailsList where date less than or equals to DEFAULT_DATE
        defaultPointOfSaleDetailsShouldNotBeFound("date.lessThan=" + DEFAULT_DATE);

        // Get all the pointOfSaleDetailsList where date less than or equals to UPDATED_DATE
        defaultPointOfSaleDetailsShouldBeFound("date.lessThan=" + UPDATED_DATE);
    }


    @Test
    @Transactional
    public void getAllPointOfSaleDetailsByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        pointOfSaleDetailsRepository.saveAndFlush(pointOfSaleDetails);

        // Get all the pointOfSaleDetailsList where status equals to DEFAULT_STATUS
        defaultPointOfSaleDetailsShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the pointOfSaleDetailsList where status equals to UPDATED_STATUS
        defaultPointOfSaleDetailsShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllPointOfSaleDetailsByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        pointOfSaleDetailsRepository.saveAndFlush(pointOfSaleDetails);

        // Get all the pointOfSaleDetailsList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultPointOfSaleDetailsShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the pointOfSaleDetailsList where status equals to UPDATED_STATUS
        defaultPointOfSaleDetailsShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllPointOfSaleDetailsByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        pointOfSaleDetailsRepository.saveAndFlush(pointOfSaleDetails);

        // Get all the pointOfSaleDetailsList where status is not null
        defaultPointOfSaleDetailsShouldBeFound("status.specified=true");

        // Get all the pointOfSaleDetailsList where status is null
        defaultPointOfSaleDetailsShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllPointOfSaleDetailsByStatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pointOfSaleDetailsRepository.saveAndFlush(pointOfSaleDetails);

        // Get all the pointOfSaleDetailsList where status greater than or equals to DEFAULT_STATUS
        defaultPointOfSaleDetailsShouldBeFound("status.greaterOrEqualThan=" + DEFAULT_STATUS);

        // Get all the pointOfSaleDetailsList where status greater than or equals to UPDATED_STATUS
        defaultPointOfSaleDetailsShouldNotBeFound("status.greaterOrEqualThan=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllPointOfSaleDetailsByStatusIsLessThanSomething() throws Exception {
        // Initialize the database
        pointOfSaleDetailsRepository.saveAndFlush(pointOfSaleDetails);

        // Get all the pointOfSaleDetailsList where status less than or equals to DEFAULT_STATUS
        defaultPointOfSaleDetailsShouldNotBeFound("status.lessThan=" + DEFAULT_STATUS);

        // Get all the pointOfSaleDetailsList where status less than or equals to UPDATED_STATUS
        defaultPointOfSaleDetailsShouldBeFound("status.lessThan=" + UPDATED_STATUS);
    }


    @Test
    @Transactional
    public void getAllPointOfSaleDetailsByPickListVarietyIsEqualToSomething() throws Exception {
        // Initialize the database
        PickListValue pickListVariety = PickListValueResourceIntTest.createEntity(em);
        em.persist(pickListVariety);
        em.flush();
        pointOfSaleDetails.setPickListVariety(pickListVariety);
        pointOfSaleDetailsRepository.saveAndFlush(pointOfSaleDetails);
        Long pickListVarietyId = pickListVariety.getId();

        // Get all the pointOfSaleDetailsList where pickListVariety equals to pickListVarietyId
        defaultPointOfSaleDetailsShouldBeFound("pickListVarietyId.equals=" + pickListVarietyId);

        // Get all the pointOfSaleDetailsList where pickListVariety equals to pickListVarietyId + 1
        defaultPointOfSaleDetailsShouldNotBeFound("pickListVarietyId.equals=" + (pickListVarietyId + 1));
    }


    @Test
    @Transactional
    public void getAllPointOfSaleDetailsByPickListCategoryIsEqualToSomething() throws Exception {
        // Initialize the database
        PickListValue pickListCategory = PickListValueResourceIntTest.createEntity(em);
        em.persist(pickListCategory);
        em.flush();
        pointOfSaleDetails.setPickListCategory(pickListCategory);
        pointOfSaleDetailsRepository.saveAndFlush(pointOfSaleDetails);
        Long pickListCategoryId = pickListCategory.getId();

        // Get all the pointOfSaleDetailsList where pickListCategory equals to pickListCategoryId
        defaultPointOfSaleDetailsShouldBeFound("pickListCategoryId.equals=" + pickListCategoryId);

        // Get all the pointOfSaleDetailsList where pickListCategory equals to pickListCategoryId + 1
        defaultPointOfSaleDetailsShouldNotBeFound("pickListCategoryId.equals=" + (pickListCategoryId + 1));
    }


    @Test
    @Transactional
    public void getAllPointOfSaleDetailsByNurseryStockIsEqualToSomething() throws Exception {
        // Initialize the database
        NurseryStock nurseryStock = NurseryStockResourceIntTest.createEntity(em);
        em.persist(nurseryStock);
        em.flush();
        pointOfSaleDetails.setNurseryStock(nurseryStock);
        pointOfSaleDetailsRepository.saveAndFlush(pointOfSaleDetails);
        Long nurseryStockId = nurseryStock.getId();

        // Get all the pointOfSaleDetailsList where nurseryStock equals to nurseryStockId
        defaultPointOfSaleDetailsShouldBeFound("nurseryStockId.equals=" + nurseryStockId);

        // Get all the pointOfSaleDetailsList where nurseryStock equals to nurseryStockId + 1
        defaultPointOfSaleDetailsShouldNotBeFound("nurseryStockId.equals=" + (nurseryStockId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultPointOfSaleDetailsShouldBeFound(String filter) throws Exception {
        restPointOfSaleDetailsMockMvc.perform(get("/api/point-of-sale-details?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pointOfSaleDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)))
            .andExpect(jsonPath("$.[*].purposeOfTaking").value(hasItem(DEFAULT_PURPOSE_OF_TAKING.toString())))
            .andExpect(jsonPath("$.[*].donorName").value(hasItem(DEFAULT_DONOR_NAME.toString())))
            .andExpect(jsonPath("$.[*].donorAddress").value(hasItem(DEFAULT_DONOR_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].contactNo").value(hasItem(DEFAULT_CONTACT_NO.toString())))
            .andExpect(jsonPath("$.[*].discount").value(hasItem(DEFAULT_DISCOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].discountAmount").value(hasItem(DEFAULT_DISCOUNT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].collectedAmount").value(hasItem(DEFAULT_COLLECTED_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultPointOfSaleDetailsShouldNotBeFound(String filter) throws Exception {
        restPointOfSaleDetailsMockMvc.perform(get("/api/point-of-sale-details?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingPointOfSaleDetails() throws Exception {
        // Get the pointOfSaleDetails
        restPointOfSaleDetailsMockMvc.perform(get("/api/point-of-sale-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePointOfSaleDetails() throws Exception {
        // Initialize the database
        pointOfSaleDetailsRepository.saveAndFlush(pointOfSaleDetails);

        int databaseSizeBeforeUpdate = pointOfSaleDetailsRepository.findAll().size();

        // Update the pointOfSaleDetails
        PointOfSaleDetails updatedPointOfSaleDetails = pointOfSaleDetailsRepository.findById(pointOfSaleDetails.getId()).get();
        // Disconnect from session so that the updates on updatedPointOfSaleDetails are not directly saved in db
        em.detach(updatedPointOfSaleDetails);
        updatedPointOfSaleDetails
            .quantity(UPDATED_QUANTITY)
            .purposeOfTaking(UPDATED_PURPOSE_OF_TAKING)
            .donorName(UPDATED_DONOR_NAME)
            .donorAddress(UPDATED_DONOR_ADDRESS)
            .contactNo(UPDATED_CONTACT_NO)
            .discount(UPDATED_DISCOUNT)
            .discountAmount(UPDATED_DISCOUNT_AMOUNT)
            .collectedAmount(UPDATED_COLLECTED_AMOUNT)
            .date(UPDATED_DATE)
            .status(UPDATED_STATUS);
        PointOfSaleDetailsDTO pointOfSaleDetailsDTO = pointOfSaleDetailsMapper.toDto(updatedPointOfSaleDetails);

        restPointOfSaleDetailsMockMvc.perform(put("/api/point-of-sale-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pointOfSaleDetailsDTO)))
            .andExpect(status().isOk());

        // Validate the PointOfSaleDetails in the database
        List<PointOfSaleDetails> pointOfSaleDetailsList = pointOfSaleDetailsRepository.findAll();
        assertThat(pointOfSaleDetailsList).hasSize(databaseSizeBeforeUpdate);
        PointOfSaleDetails testPointOfSaleDetails = pointOfSaleDetailsList.get(pointOfSaleDetailsList.size() - 1);
        assertThat(testPointOfSaleDetails.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testPointOfSaleDetails.getPurposeOfTaking()).isEqualTo(UPDATED_PURPOSE_OF_TAKING);
        assertThat(testPointOfSaleDetails.getDonorName()).isEqualTo(UPDATED_DONOR_NAME);
        assertThat(testPointOfSaleDetails.getDonorAddress()).isEqualTo(UPDATED_DONOR_ADDRESS);
        assertThat(testPointOfSaleDetails.getContactNo()).isEqualTo(UPDATED_CONTACT_NO);
        assertThat(testPointOfSaleDetails.getDiscount()).isEqualTo(UPDATED_DISCOUNT);
        assertThat(testPointOfSaleDetails.getDiscountAmount()).isEqualTo(UPDATED_DISCOUNT_AMOUNT);
        assertThat(testPointOfSaleDetails.getCollectedAmount()).isEqualTo(UPDATED_COLLECTED_AMOUNT);
        assertThat(testPointOfSaleDetails.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testPointOfSaleDetails.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingPointOfSaleDetails() throws Exception {
        int databaseSizeBeforeUpdate = pointOfSaleDetailsRepository.findAll().size();

        // Create the PointOfSaleDetails
        PointOfSaleDetailsDTO pointOfSaleDetailsDTO = pointOfSaleDetailsMapper.toDto(pointOfSaleDetails);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPointOfSaleDetailsMockMvc.perform(put("/api/point-of-sale-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pointOfSaleDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PointOfSaleDetails in the database
        List<PointOfSaleDetails> pointOfSaleDetailsList = pointOfSaleDetailsRepository.findAll();
        assertThat(pointOfSaleDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePointOfSaleDetails() throws Exception {
        // Initialize the database
        pointOfSaleDetailsRepository.saveAndFlush(pointOfSaleDetails);

        int databaseSizeBeforeDelete = pointOfSaleDetailsRepository.findAll().size();

        // Get the pointOfSaleDetails
        restPointOfSaleDetailsMockMvc.perform(delete("/api/point-of-sale-details/{id}", pointOfSaleDetails.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PointOfSaleDetails> pointOfSaleDetailsList = pointOfSaleDetailsRepository.findAll();
        assertThat(pointOfSaleDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PointOfSaleDetails.class);
        PointOfSaleDetails pointOfSaleDetails1 = new PointOfSaleDetails();
        pointOfSaleDetails1.setId(1L);
        PointOfSaleDetails pointOfSaleDetails2 = new PointOfSaleDetails();
        pointOfSaleDetails2.setId(pointOfSaleDetails1.getId());
        assertThat(pointOfSaleDetails1).isEqualTo(pointOfSaleDetails2);
        pointOfSaleDetails2.setId(2L);
        assertThat(pointOfSaleDetails1).isNotEqualTo(pointOfSaleDetails2);
        pointOfSaleDetails1.setId(null);
        assertThat(pointOfSaleDetails1).isNotEqualTo(pointOfSaleDetails2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PointOfSaleDetailsDTO.class);
        PointOfSaleDetailsDTO pointOfSaleDetailsDTO1 = new PointOfSaleDetailsDTO();
        pointOfSaleDetailsDTO1.setId(1L);
        PointOfSaleDetailsDTO pointOfSaleDetailsDTO2 = new PointOfSaleDetailsDTO();
        assertThat(pointOfSaleDetailsDTO1).isNotEqualTo(pointOfSaleDetailsDTO2);
        pointOfSaleDetailsDTO2.setId(pointOfSaleDetailsDTO1.getId());
        assertThat(pointOfSaleDetailsDTO1).isEqualTo(pointOfSaleDetailsDTO2);
        pointOfSaleDetailsDTO2.setId(2L);
        assertThat(pointOfSaleDetailsDTO1).isNotEqualTo(pointOfSaleDetailsDTO2);
        pointOfSaleDetailsDTO1.setId(null);
        assertThat(pointOfSaleDetailsDTO1).isNotEqualTo(pointOfSaleDetailsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(pointOfSaleDetailsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(pointOfSaleDetailsMapper.fromId(null)).isNull();
    }
}
