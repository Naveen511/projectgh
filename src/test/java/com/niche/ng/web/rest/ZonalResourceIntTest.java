package com.niche.ng.web.rest;

import com.niche.ng.ProjectghApp;

import com.niche.ng.domain.Zonal;
import com.niche.ng.domain.Sector;
import com.niche.ng.domain.FinancialYearSettings;
import com.niche.ng.domain.OperationalHead;
import com.niche.ng.domain.MapZonalWithOh;
import com.niche.ng.domain.ZonalIncharge;
import com.niche.ng.domain.MapSectorWithZonal;
import com.niche.ng.repository.ZonalRepository;
import com.niche.ng.service.ZonalService;
import com.niche.ng.service.dto.ZonalDTO;
import com.niche.ng.service.mapper.ZonalMapper;
import com.niche.ng.web.rest.errors.ExceptionTranslator;
import com.niche.ng.service.dto.ZonalCriteria;
import com.niche.ng.service.ZonalQueryService;

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
 * Test class for the ZonalResource REST controller.
 *
 * @see ZonalResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectghApp.class)
public class ZonalResourceIntTest {

    private static final String DEFAULT_ZONE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ZONE_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    @Autowired
    private ZonalRepository zonalRepository;


    @Autowired
    private ZonalMapper zonalMapper;
    

    @Autowired
    private ZonalService zonalService;

    @Autowired
    private ZonalQueryService zonalQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restZonalMockMvc;

    private Zonal zonal;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ZonalResource zonalResource = new ZonalResource(zonalService, zonalQueryService);
        this.restZonalMockMvc = MockMvcBuilders.standaloneSetup(zonalResource)
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
    public static Zonal createEntity(EntityManager em) {
        Zonal zonal = new Zonal()
            .zoneName(DEFAULT_ZONE_NAME)
            .status(DEFAULT_STATUS);
        return zonal;
    }

    @Before
    public void initTest() {
        zonal = createEntity(em);
    }

    @Test
    @Transactional
    public void createZonal() throws Exception {
        int databaseSizeBeforeCreate = zonalRepository.findAll().size();

        // Create the Zonal
        ZonalDTO zonalDTO = zonalMapper.toDto(zonal);
        restZonalMockMvc.perform(post("/api/zonals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(zonalDTO)))
            .andExpect(status().isCreated());

        // Validate the Zonal in the database
        List<Zonal> zonalList = zonalRepository.findAll();
        assertThat(zonalList).hasSize(databaseSizeBeforeCreate + 1);
        Zonal testZonal = zonalList.get(zonalList.size() - 1);
        assertThat(testZonal.getZoneName()).isEqualTo(DEFAULT_ZONE_NAME);
        assertThat(testZonal.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createZonalWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = zonalRepository.findAll().size();

        // Create the Zonal with an existing ID
        zonal.setId(1L);
        ZonalDTO zonalDTO = zonalMapper.toDto(zonal);

        // An entity with an existing ID cannot be created, so this API call must fail
        restZonalMockMvc.perform(post("/api/zonals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(zonalDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Zonal in the database
        List<Zonal> zonalList = zonalRepository.findAll();
        assertThat(zonalList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkZoneNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = zonalRepository.findAll().size();
        // set the field null
        zonal.setZoneName(null);

        // Create the Zonal, which fails.
        ZonalDTO zonalDTO = zonalMapper.toDto(zonal);

        restZonalMockMvc.perform(post("/api/zonals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(zonalDTO)))
            .andExpect(status().isBadRequest());

        List<Zonal> zonalList = zonalRepository.findAll();
        assertThat(zonalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllZonals() throws Exception {
        // Initialize the database
        zonalRepository.saveAndFlush(zonal);

        // Get all the zonalList
        restZonalMockMvc.perform(get("/api/zonals?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(zonal.getId().intValue())))
            .andExpect(jsonPath("$.[*].zoneName").value(hasItem(DEFAULT_ZONE_NAME.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    

    @Test
    @Transactional
    public void getZonal() throws Exception {
        // Initialize the database
        zonalRepository.saveAndFlush(zonal);

        // Get the zonal
        restZonalMockMvc.perform(get("/api/zonals/{id}", zonal.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(zonal.getId().intValue()))
            .andExpect(jsonPath("$.zoneName").value(DEFAULT_ZONE_NAME.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getAllZonalsByZoneNameIsEqualToSomething() throws Exception {
        // Initialize the database
        zonalRepository.saveAndFlush(zonal);

        // Get all the zonalList where zoneName equals to DEFAULT_ZONE_NAME
        defaultZonalShouldBeFound("zoneName.equals=" + DEFAULT_ZONE_NAME);

        // Get all the zonalList where zoneName equals to UPDATED_ZONE_NAME
        defaultZonalShouldNotBeFound("zoneName.equals=" + UPDATED_ZONE_NAME);
    }

    @Test
    @Transactional
    public void getAllZonalsByZoneNameIsInShouldWork() throws Exception {
        // Initialize the database
        zonalRepository.saveAndFlush(zonal);

        // Get all the zonalList where zoneName in DEFAULT_ZONE_NAME or UPDATED_ZONE_NAME
        defaultZonalShouldBeFound("zoneName.in=" + DEFAULT_ZONE_NAME + "," + UPDATED_ZONE_NAME);

        // Get all the zonalList where zoneName equals to UPDATED_ZONE_NAME
        defaultZonalShouldNotBeFound("zoneName.in=" + UPDATED_ZONE_NAME);
    }

    @Test
    @Transactional
    public void getAllZonalsByZoneNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        zonalRepository.saveAndFlush(zonal);

        // Get all the zonalList where zoneName is not null
        defaultZonalShouldBeFound("zoneName.specified=true");

        // Get all the zonalList where zoneName is null
        defaultZonalShouldNotBeFound("zoneName.specified=false");
    }

    @Test
    @Transactional
    public void getAllZonalsByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        zonalRepository.saveAndFlush(zonal);

        // Get all the zonalList where status equals to DEFAULT_STATUS
        defaultZonalShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the zonalList where status equals to UPDATED_STATUS
        defaultZonalShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllZonalsByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        zonalRepository.saveAndFlush(zonal);

        // Get all the zonalList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultZonalShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the zonalList where status equals to UPDATED_STATUS
        defaultZonalShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllZonalsByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        zonalRepository.saveAndFlush(zonal);

        // Get all the zonalList where status is not null
        defaultZonalShouldBeFound("status.specified=true");

        // Get all the zonalList where status is null
        defaultZonalShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllZonalsByStatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        zonalRepository.saveAndFlush(zonal);

        // Get all the zonalList where status greater than or equals to DEFAULT_STATUS
        defaultZonalShouldBeFound("status.greaterOrEqualThan=" + DEFAULT_STATUS);

        // Get all the zonalList where status greater than or equals to UPDATED_STATUS
        defaultZonalShouldNotBeFound("status.greaterOrEqualThan=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllZonalsByStatusIsLessThanSomething() throws Exception {
        // Initialize the database
        zonalRepository.saveAndFlush(zonal);

        // Get all the zonalList where status less than or equals to DEFAULT_STATUS
        defaultZonalShouldNotBeFound("status.lessThan=" + DEFAULT_STATUS);

        // Get all the zonalList where status less than or equals to UPDATED_STATUS
        defaultZonalShouldBeFound("status.lessThan=" + UPDATED_STATUS);
    }


    @Test
    @Transactional
    public void getAllZonalsBySectorsIsEqualToSomething() throws Exception {
        // Initialize the database
        Sector sectors = SectorResourceIntTest.createEntity(em);
        em.persist(sectors);
        em.flush();
        zonal.addSectors(sectors);
        zonalRepository.saveAndFlush(zonal);
        Long sectorsId = sectors.getId();

        // Get all the zonalList where sectors equals to sectorsId
        defaultZonalShouldBeFound("sectorsId.equals=" + sectorsId);

        // Get all the zonalList where sectors equals to sectorsId + 1
        defaultZonalShouldNotBeFound("sectorsId.equals=" + (sectorsId + 1));
    }


    @Test
    @Transactional
    public void getAllZonalsByFinancialYearIsEqualToSomething() throws Exception {
        // Initialize the database
        FinancialYearSettings financialYear = FinancialYearSettingsResourceIntTest.createEntity(em);
        em.persist(financialYear);
        em.flush();
        zonal.setFinancialYear(financialYear);
        zonalRepository.saveAndFlush(zonal);
        Long financialYearId = financialYear.getId();

        // Get all the zonalList where financialYear equals to financialYearId
        defaultZonalShouldBeFound("financialYearId.equals=" + financialYearId);

        // Get all the zonalList where financialYear equals to financialYearId + 1
        defaultZonalShouldNotBeFound("financialYearId.equals=" + (financialYearId + 1));
    }


    @Test
    @Transactional
    public void getAllZonalsByOperationalHeadIsEqualToSomething() throws Exception {
        // Initialize the database
        OperationalHead operationalHead = OperationalHeadResourceIntTest.createEntity(em);
        em.persist(operationalHead);
        em.flush();
        zonal.setOperationalHead(operationalHead);
        zonalRepository.saveAndFlush(zonal);
        Long operationalHeadId = operationalHead.getId();

        // Get all the zonalList where operationalHead equals to operationalHeadId
        defaultZonalShouldBeFound("operationalHeadId.equals=" + operationalHeadId);

        // Get all the zonalList where operationalHead equals to operationalHeadId + 1
        defaultZonalShouldNotBeFound("operationalHeadId.equals=" + (operationalHeadId + 1));
    }


    @Test
    @Transactional
    public void getAllZonalsByMapZonalWithOhIsEqualToSomething() throws Exception {
        // Initialize the database
        MapZonalWithOh mapZonalWithOh = MapZonalWithOhResourceIntTest.createEntity(em);
        em.persist(mapZonalWithOh);
        em.flush();
        zonal.addMapZonalWithOh(mapZonalWithOh);
        zonalRepository.saveAndFlush(zonal);
        Long mapZonalWithOhId = mapZonalWithOh.getId();

        // Get all the zonalList where mapZonalWithOh equals to mapZonalWithOhId
        defaultZonalShouldBeFound("mapZonalWithOhId.equals=" + mapZonalWithOhId);

        // Get all the zonalList where mapZonalWithOh equals to mapZonalWithOhId + 1
        defaultZonalShouldNotBeFound("mapZonalWithOhId.equals=" + (mapZonalWithOhId + 1));
    }


    @Test
    @Transactional
    public void getAllZonalsByZonalInchargeIsEqualToSomething() throws Exception {
        // Initialize the database
        ZonalIncharge zonalIncharge = ZonalInchargeResourceIntTest.createEntity(em);
        em.persist(zonalIncharge);
        em.flush();
        zonal.addZonalIncharge(zonalIncharge);
        zonalRepository.saveAndFlush(zonal);
        Long zonalInchargeId = zonalIncharge.getId();

        // Get all the zonalList where zonalIncharge equals to zonalInchargeId
        defaultZonalShouldBeFound("zonalInchargeId.equals=" + zonalInchargeId);

        // Get all the zonalList where zonalIncharge equals to zonalInchargeId + 1
        defaultZonalShouldNotBeFound("zonalInchargeId.equals=" + (zonalInchargeId + 1));
    }


    @Test
    @Transactional
    public void getAllZonalsByMapSectorWithZonalIsEqualToSomething() throws Exception {
        // Initialize the database
        MapSectorWithZonal mapSectorWithZonal = MapSectorWithZonalResourceIntTest.createEntity(em);
        em.persist(mapSectorWithZonal);
        em.flush();
        zonal.addMapSectorWithZonal(mapSectorWithZonal);
        zonalRepository.saveAndFlush(zonal);
        Long mapSectorWithZonalId = mapSectorWithZonal.getId();

        // Get all the zonalList where mapSectorWithZonal equals to mapSectorWithZonalId
        defaultZonalShouldBeFound("mapSectorWithZonalId.equals=" + mapSectorWithZonalId);

        // Get all the zonalList where mapSectorWithZonal equals to mapSectorWithZonalId + 1
        defaultZonalShouldNotBeFound("mapSectorWithZonalId.equals=" + (mapSectorWithZonalId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultZonalShouldBeFound(String filter) throws Exception {
        restZonalMockMvc.perform(get("/api/zonals?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(zonal.getId().intValue())))
            .andExpect(jsonPath("$.[*].zoneName").value(hasItem(DEFAULT_ZONE_NAME.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultZonalShouldNotBeFound(String filter) throws Exception {
        restZonalMockMvc.perform(get("/api/zonals?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingZonal() throws Exception {
        // Get the zonal
        restZonalMockMvc.perform(get("/api/zonals/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateZonal() throws Exception {
        // Initialize the database
        zonalRepository.saveAndFlush(zonal);

        int databaseSizeBeforeUpdate = zonalRepository.findAll().size();

        // Update the zonal
        Zonal updatedZonal = zonalRepository.findById(zonal.getId()).get();
        // Disconnect from session so that the updates on updatedZonal are not directly saved in db
        em.detach(updatedZonal);
        updatedZonal
            .zoneName(UPDATED_ZONE_NAME)
            .status(UPDATED_STATUS);
        ZonalDTO zonalDTO = zonalMapper.toDto(updatedZonal);

        restZonalMockMvc.perform(put("/api/zonals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(zonalDTO)))
            .andExpect(status().isOk());

        // Validate the Zonal in the database
        List<Zonal> zonalList = zonalRepository.findAll();
        assertThat(zonalList).hasSize(databaseSizeBeforeUpdate);
        Zonal testZonal = zonalList.get(zonalList.size() - 1);
        assertThat(testZonal.getZoneName()).isEqualTo(UPDATED_ZONE_NAME);
        assertThat(testZonal.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingZonal() throws Exception {
        int databaseSizeBeforeUpdate = zonalRepository.findAll().size();

        // Create the Zonal
        ZonalDTO zonalDTO = zonalMapper.toDto(zonal);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restZonalMockMvc.perform(put("/api/zonals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(zonalDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Zonal in the database
        List<Zonal> zonalList = zonalRepository.findAll();
        assertThat(zonalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteZonal() throws Exception {
        // Initialize the database
        zonalRepository.saveAndFlush(zonal);

        int databaseSizeBeforeDelete = zonalRepository.findAll().size();

        // Get the zonal
        restZonalMockMvc.perform(delete("/api/zonals/{id}", zonal.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Zonal> zonalList = zonalRepository.findAll();
        assertThat(zonalList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Zonal.class);
        Zonal zonal1 = new Zonal();
        zonal1.setId(1L);
        Zonal zonal2 = new Zonal();
        zonal2.setId(zonal1.getId());
        assertThat(zonal1).isEqualTo(zonal2);
        zonal2.setId(2L);
        assertThat(zonal1).isNotEqualTo(zonal2);
        zonal1.setId(null);
        assertThat(zonal1).isNotEqualTo(zonal2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ZonalDTO.class);
        ZonalDTO zonalDTO1 = new ZonalDTO();
        zonalDTO1.setId(1L);
        ZonalDTO zonalDTO2 = new ZonalDTO();
        assertThat(zonalDTO1).isNotEqualTo(zonalDTO2);
        zonalDTO2.setId(zonalDTO1.getId());
        assertThat(zonalDTO1).isEqualTo(zonalDTO2);
        zonalDTO2.setId(2L);
        assertThat(zonalDTO1).isNotEqualTo(zonalDTO2);
        zonalDTO1.setId(null);
        assertThat(zonalDTO1).isNotEqualTo(zonalDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(zonalMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(zonalMapper.fromId(null)).isNull();
    }
}
