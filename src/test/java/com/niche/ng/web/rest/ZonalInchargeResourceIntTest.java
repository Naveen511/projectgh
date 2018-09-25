package com.niche.ng.web.rest;

import com.niche.ng.ProjectghApp;

import com.niche.ng.domain.ZonalIncharge;
import com.niche.ng.domain.Zonal;
import com.niche.ng.repository.ZonalInchargeRepository;
import com.niche.ng.service.ZonalInchargeService;
import com.niche.ng.service.dto.ZonalInchargeDTO;
import com.niche.ng.service.mapper.ZonalInchargeMapper;
import com.niche.ng.web.rest.errors.ExceptionTranslator;
import com.niche.ng.service.dto.ZonalInchargeCriteria;
import com.niche.ng.service.ZonalInchargeQueryService;

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
 * Test class for the ZonalInchargeResource REST controller.
 *
 * @see ZonalInchargeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectghApp.class)
public class ZonalInchargeResourceIntTest {

    private static final LocalDate DEFAULT_FROM_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FROM_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_TO_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TO_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    @Autowired
    private ZonalInchargeRepository zonalInchargeRepository;


    @Autowired
    private ZonalInchargeMapper zonalInchargeMapper;
    

    @Autowired
    private ZonalInchargeService zonalInchargeService;

    @Autowired
    private ZonalInchargeQueryService zonalInchargeQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restZonalInchargeMockMvc;

    private ZonalIncharge zonalIncharge;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ZonalInchargeResource zonalInchargeResource = new ZonalInchargeResource(zonalInchargeService, zonalInchargeQueryService);
        this.restZonalInchargeMockMvc = MockMvcBuilders.standaloneSetup(zonalInchargeResource)
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
    public static ZonalIncharge createEntity(EntityManager em) {
        ZonalIncharge zonalIncharge = new ZonalIncharge()
            .fromDate(DEFAULT_FROM_DATE)
            .toDate(DEFAULT_TO_DATE)
            .description(DEFAULT_DESCRIPTION)
            .status(DEFAULT_STATUS);
        return zonalIncharge;
    }

    @Before
    public void initTest() {
        zonalIncharge = createEntity(em);
    }

    @Test
    @Transactional
    public void createZonalIncharge() throws Exception {
        int databaseSizeBeforeCreate = zonalInchargeRepository.findAll().size();

        // Create the ZonalIncharge
        ZonalInchargeDTO zonalInchargeDTO = zonalInchargeMapper.toDto(zonalIncharge);
        restZonalInchargeMockMvc.perform(post("/api/zonal-incharges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(zonalInchargeDTO)))
            .andExpect(status().isCreated());

        // Validate the ZonalIncharge in the database
        List<ZonalIncharge> zonalInchargeList = zonalInchargeRepository.findAll();
        assertThat(zonalInchargeList).hasSize(databaseSizeBeforeCreate + 1);
        ZonalIncharge testZonalIncharge = zonalInchargeList.get(zonalInchargeList.size() - 1);
        assertThat(testZonalIncharge.getFromDate()).isEqualTo(DEFAULT_FROM_DATE);
        assertThat(testZonalIncharge.getToDate()).isEqualTo(DEFAULT_TO_DATE);
        assertThat(testZonalIncharge.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testZonalIncharge.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createZonalInchargeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = zonalInchargeRepository.findAll().size();

        // Create the ZonalIncharge with an existing ID
        zonalIncharge.setId(1L);
        ZonalInchargeDTO zonalInchargeDTO = zonalInchargeMapper.toDto(zonalIncharge);

        // An entity with an existing ID cannot be created, so this API call must fail
        restZonalInchargeMockMvc.perform(post("/api/zonal-incharges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(zonalInchargeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ZonalIncharge in the database
        List<ZonalIncharge> zonalInchargeList = zonalInchargeRepository.findAll();
        assertThat(zonalInchargeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllZonalIncharges() throws Exception {
        // Initialize the database
        zonalInchargeRepository.saveAndFlush(zonalIncharge);

        // Get all the zonalInchargeList
        restZonalInchargeMockMvc.perform(get("/api/zonal-incharges?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(zonalIncharge.getId().intValue())))
            .andExpect(jsonPath("$.[*].fromDate").value(hasItem(DEFAULT_FROM_DATE.toString())))
            .andExpect(jsonPath("$.[*].toDate").value(hasItem(DEFAULT_TO_DATE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    

    @Test
    @Transactional
    public void getZonalIncharge() throws Exception {
        // Initialize the database
        zonalInchargeRepository.saveAndFlush(zonalIncharge);

        // Get the zonalIncharge
        restZonalInchargeMockMvc.perform(get("/api/zonal-incharges/{id}", zonalIncharge.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(zonalIncharge.getId().intValue()))
            .andExpect(jsonPath("$.fromDate").value(DEFAULT_FROM_DATE.toString()))
            .andExpect(jsonPath("$.toDate").value(DEFAULT_TO_DATE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getAllZonalInchargesByFromDateIsEqualToSomething() throws Exception {
        // Initialize the database
        zonalInchargeRepository.saveAndFlush(zonalIncharge);

        // Get all the zonalInchargeList where fromDate equals to DEFAULT_FROM_DATE
        defaultZonalInchargeShouldBeFound("fromDate.equals=" + DEFAULT_FROM_DATE);

        // Get all the zonalInchargeList where fromDate equals to UPDATED_FROM_DATE
        defaultZonalInchargeShouldNotBeFound("fromDate.equals=" + UPDATED_FROM_DATE);
    }

    @Test
    @Transactional
    public void getAllZonalInchargesByFromDateIsInShouldWork() throws Exception {
        // Initialize the database
        zonalInchargeRepository.saveAndFlush(zonalIncharge);

        // Get all the zonalInchargeList where fromDate in DEFAULT_FROM_DATE or UPDATED_FROM_DATE
        defaultZonalInchargeShouldBeFound("fromDate.in=" + DEFAULT_FROM_DATE + "," + UPDATED_FROM_DATE);

        // Get all the zonalInchargeList where fromDate equals to UPDATED_FROM_DATE
        defaultZonalInchargeShouldNotBeFound("fromDate.in=" + UPDATED_FROM_DATE);
    }

    @Test
    @Transactional
    public void getAllZonalInchargesByFromDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        zonalInchargeRepository.saveAndFlush(zonalIncharge);

        // Get all the zonalInchargeList where fromDate is not null
        defaultZonalInchargeShouldBeFound("fromDate.specified=true");

        // Get all the zonalInchargeList where fromDate is null
        defaultZonalInchargeShouldNotBeFound("fromDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllZonalInchargesByFromDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        zonalInchargeRepository.saveAndFlush(zonalIncharge);

        // Get all the zonalInchargeList where fromDate greater than or equals to DEFAULT_FROM_DATE
        defaultZonalInchargeShouldBeFound("fromDate.greaterOrEqualThan=" + DEFAULT_FROM_DATE);

        // Get all the zonalInchargeList where fromDate greater than or equals to UPDATED_FROM_DATE
        defaultZonalInchargeShouldNotBeFound("fromDate.greaterOrEqualThan=" + UPDATED_FROM_DATE);
    }

    @Test
    @Transactional
    public void getAllZonalInchargesByFromDateIsLessThanSomething() throws Exception {
        // Initialize the database
        zonalInchargeRepository.saveAndFlush(zonalIncharge);

        // Get all the zonalInchargeList where fromDate less than or equals to DEFAULT_FROM_DATE
        defaultZonalInchargeShouldNotBeFound("fromDate.lessThan=" + DEFAULT_FROM_DATE);

        // Get all the zonalInchargeList where fromDate less than or equals to UPDATED_FROM_DATE
        defaultZonalInchargeShouldBeFound("fromDate.lessThan=" + UPDATED_FROM_DATE);
    }


    @Test
    @Transactional
    public void getAllZonalInchargesByToDateIsEqualToSomething() throws Exception {
        // Initialize the database
        zonalInchargeRepository.saveAndFlush(zonalIncharge);

        // Get all the zonalInchargeList where toDate equals to DEFAULT_TO_DATE
        defaultZonalInchargeShouldBeFound("toDate.equals=" + DEFAULT_TO_DATE);

        // Get all the zonalInchargeList where toDate equals to UPDATED_TO_DATE
        defaultZonalInchargeShouldNotBeFound("toDate.equals=" + UPDATED_TO_DATE);
    }

    @Test
    @Transactional
    public void getAllZonalInchargesByToDateIsInShouldWork() throws Exception {
        // Initialize the database
        zonalInchargeRepository.saveAndFlush(zonalIncharge);

        // Get all the zonalInchargeList where toDate in DEFAULT_TO_DATE or UPDATED_TO_DATE
        defaultZonalInchargeShouldBeFound("toDate.in=" + DEFAULT_TO_DATE + "," + UPDATED_TO_DATE);

        // Get all the zonalInchargeList where toDate equals to UPDATED_TO_DATE
        defaultZonalInchargeShouldNotBeFound("toDate.in=" + UPDATED_TO_DATE);
    }

    @Test
    @Transactional
    public void getAllZonalInchargesByToDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        zonalInchargeRepository.saveAndFlush(zonalIncharge);

        // Get all the zonalInchargeList where toDate is not null
        defaultZonalInchargeShouldBeFound("toDate.specified=true");

        // Get all the zonalInchargeList where toDate is null
        defaultZonalInchargeShouldNotBeFound("toDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllZonalInchargesByToDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        zonalInchargeRepository.saveAndFlush(zonalIncharge);

        // Get all the zonalInchargeList where toDate greater than or equals to DEFAULT_TO_DATE
        defaultZonalInchargeShouldBeFound("toDate.greaterOrEqualThan=" + DEFAULT_TO_DATE);

        // Get all the zonalInchargeList where toDate greater than or equals to UPDATED_TO_DATE
        defaultZonalInchargeShouldNotBeFound("toDate.greaterOrEqualThan=" + UPDATED_TO_DATE);
    }

    @Test
    @Transactional
    public void getAllZonalInchargesByToDateIsLessThanSomething() throws Exception {
        // Initialize the database
        zonalInchargeRepository.saveAndFlush(zonalIncharge);

        // Get all the zonalInchargeList where toDate less than or equals to DEFAULT_TO_DATE
        defaultZonalInchargeShouldNotBeFound("toDate.lessThan=" + DEFAULT_TO_DATE);

        // Get all the zonalInchargeList where toDate less than or equals to UPDATED_TO_DATE
        defaultZonalInchargeShouldBeFound("toDate.lessThan=" + UPDATED_TO_DATE);
    }


    @Test
    @Transactional
    public void getAllZonalInchargesByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        zonalInchargeRepository.saveAndFlush(zonalIncharge);

        // Get all the zonalInchargeList where description equals to DEFAULT_DESCRIPTION
        defaultZonalInchargeShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the zonalInchargeList where description equals to UPDATED_DESCRIPTION
        defaultZonalInchargeShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllZonalInchargesByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        zonalInchargeRepository.saveAndFlush(zonalIncharge);

        // Get all the zonalInchargeList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultZonalInchargeShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the zonalInchargeList where description equals to UPDATED_DESCRIPTION
        defaultZonalInchargeShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllZonalInchargesByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        zonalInchargeRepository.saveAndFlush(zonalIncharge);

        // Get all the zonalInchargeList where description is not null
        defaultZonalInchargeShouldBeFound("description.specified=true");

        // Get all the zonalInchargeList where description is null
        defaultZonalInchargeShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllZonalInchargesByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        zonalInchargeRepository.saveAndFlush(zonalIncharge);

        // Get all the zonalInchargeList where status equals to DEFAULT_STATUS
        defaultZonalInchargeShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the zonalInchargeList where status equals to UPDATED_STATUS
        defaultZonalInchargeShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllZonalInchargesByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        zonalInchargeRepository.saveAndFlush(zonalIncharge);

        // Get all the zonalInchargeList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultZonalInchargeShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the zonalInchargeList where status equals to UPDATED_STATUS
        defaultZonalInchargeShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllZonalInchargesByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        zonalInchargeRepository.saveAndFlush(zonalIncharge);

        // Get all the zonalInchargeList where status is not null
        defaultZonalInchargeShouldBeFound("status.specified=true");

        // Get all the zonalInchargeList where status is null
        defaultZonalInchargeShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllZonalInchargesByStatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        zonalInchargeRepository.saveAndFlush(zonalIncharge);

        // Get all the zonalInchargeList where status greater than or equals to DEFAULT_STATUS
        defaultZonalInchargeShouldBeFound("status.greaterOrEqualThan=" + DEFAULT_STATUS);

        // Get all the zonalInchargeList where status greater than or equals to UPDATED_STATUS
        defaultZonalInchargeShouldNotBeFound("status.greaterOrEqualThan=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllZonalInchargesByStatusIsLessThanSomething() throws Exception {
        // Initialize the database
        zonalInchargeRepository.saveAndFlush(zonalIncharge);

        // Get all the zonalInchargeList where status less than or equals to DEFAULT_STATUS
        defaultZonalInchargeShouldNotBeFound("status.lessThan=" + DEFAULT_STATUS);

        // Get all the zonalInchargeList where status less than or equals to UPDATED_STATUS
        defaultZonalInchargeShouldBeFound("status.lessThan=" + UPDATED_STATUS);
    }


    @Test
    @Transactional
    public void getAllZonalInchargesByZonalIsEqualToSomething() throws Exception {
        // Initialize the database
        Zonal zonal = ZonalResourceIntTest.createEntity(em);
        em.persist(zonal);
        em.flush();
        zonalIncharge.setZonal(zonal);
        zonalInchargeRepository.saveAndFlush(zonalIncharge);
        Long zonalId = zonal.getId();

        // Get all the zonalInchargeList where zonal equals to zonalId
        defaultZonalInchargeShouldBeFound("zonalId.equals=" + zonalId);

        // Get all the zonalInchargeList where zonal equals to zonalId + 1
        defaultZonalInchargeShouldNotBeFound("zonalId.equals=" + (zonalId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultZonalInchargeShouldBeFound(String filter) throws Exception {
        restZonalInchargeMockMvc.perform(get("/api/zonal-incharges?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(zonalIncharge.getId().intValue())))
            .andExpect(jsonPath("$.[*].fromDate").value(hasItem(DEFAULT_FROM_DATE.toString())))
            .andExpect(jsonPath("$.[*].toDate").value(hasItem(DEFAULT_TO_DATE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultZonalInchargeShouldNotBeFound(String filter) throws Exception {
        restZonalInchargeMockMvc.perform(get("/api/zonal-incharges?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingZonalIncharge() throws Exception {
        // Get the zonalIncharge
        restZonalInchargeMockMvc.perform(get("/api/zonal-incharges/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateZonalIncharge() throws Exception {
        // Initialize the database
        zonalInchargeRepository.saveAndFlush(zonalIncharge);

        int databaseSizeBeforeUpdate = zonalInchargeRepository.findAll().size();

        // Update the zonalIncharge
        ZonalIncharge updatedZonalIncharge = zonalInchargeRepository.findById(zonalIncharge.getId()).get();
        // Disconnect from session so that the updates on updatedZonalIncharge are not directly saved in db
        em.detach(updatedZonalIncharge);
        updatedZonalIncharge
            .fromDate(UPDATED_FROM_DATE)
            .toDate(UPDATED_TO_DATE)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS);
        ZonalInchargeDTO zonalInchargeDTO = zonalInchargeMapper.toDto(updatedZonalIncharge);

        restZonalInchargeMockMvc.perform(put("/api/zonal-incharges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(zonalInchargeDTO)))
            .andExpect(status().isOk());

        // Validate the ZonalIncharge in the database
        List<ZonalIncharge> zonalInchargeList = zonalInchargeRepository.findAll();
        assertThat(zonalInchargeList).hasSize(databaseSizeBeforeUpdate);
        ZonalIncharge testZonalIncharge = zonalInchargeList.get(zonalInchargeList.size() - 1);
        assertThat(testZonalIncharge.getFromDate()).isEqualTo(UPDATED_FROM_DATE);
        assertThat(testZonalIncharge.getToDate()).isEqualTo(UPDATED_TO_DATE);
        assertThat(testZonalIncharge.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testZonalIncharge.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingZonalIncharge() throws Exception {
        int databaseSizeBeforeUpdate = zonalInchargeRepository.findAll().size();

        // Create the ZonalIncharge
        ZonalInchargeDTO zonalInchargeDTO = zonalInchargeMapper.toDto(zonalIncharge);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restZonalInchargeMockMvc.perform(put("/api/zonal-incharges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(zonalInchargeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ZonalIncharge in the database
        List<ZonalIncharge> zonalInchargeList = zonalInchargeRepository.findAll();
        assertThat(zonalInchargeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteZonalIncharge() throws Exception {
        // Initialize the database
        zonalInchargeRepository.saveAndFlush(zonalIncharge);

        int databaseSizeBeforeDelete = zonalInchargeRepository.findAll().size();

        // Get the zonalIncharge
        restZonalInchargeMockMvc.perform(delete("/api/zonal-incharges/{id}", zonalIncharge.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ZonalIncharge> zonalInchargeList = zonalInchargeRepository.findAll();
        assertThat(zonalInchargeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ZonalIncharge.class);
        ZonalIncharge zonalIncharge1 = new ZonalIncharge();
        zonalIncharge1.setId(1L);
        ZonalIncharge zonalIncharge2 = new ZonalIncharge();
        zonalIncharge2.setId(zonalIncharge1.getId());
        assertThat(zonalIncharge1).isEqualTo(zonalIncharge2);
        zonalIncharge2.setId(2L);
        assertThat(zonalIncharge1).isNotEqualTo(zonalIncharge2);
        zonalIncharge1.setId(null);
        assertThat(zonalIncharge1).isNotEqualTo(zonalIncharge2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ZonalInchargeDTO.class);
        ZonalInchargeDTO zonalInchargeDTO1 = new ZonalInchargeDTO();
        zonalInchargeDTO1.setId(1L);
        ZonalInchargeDTO zonalInchargeDTO2 = new ZonalInchargeDTO();
        assertThat(zonalInchargeDTO1).isNotEqualTo(zonalInchargeDTO2);
        zonalInchargeDTO2.setId(zonalInchargeDTO1.getId());
        assertThat(zonalInchargeDTO1).isEqualTo(zonalInchargeDTO2);
        zonalInchargeDTO2.setId(2L);
        assertThat(zonalInchargeDTO1).isNotEqualTo(zonalInchargeDTO2);
        zonalInchargeDTO1.setId(null);
        assertThat(zonalInchargeDTO1).isNotEqualTo(zonalInchargeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(zonalInchargeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(zonalInchargeMapper.fromId(null)).isNull();
    }
}
