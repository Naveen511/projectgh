package com.niche.ng.web.rest;

import com.niche.ng.ProjectghApp;

import com.niche.ng.domain.Sector;
import com.niche.ng.domain.Nursery;
import com.niche.ng.domain.Zonal;
import com.niche.ng.domain.FinancialYearSettings;
import com.niche.ng.domain.SectorIncharge;
import com.niche.ng.domain.MapSectorWithZonal;
import com.niche.ng.domain.MapNurseryWithSector;
import com.niche.ng.repository.SectorRepository;
import com.niche.ng.service.SectorService;
import com.niche.ng.service.dto.SectorDTO;
import com.niche.ng.service.mapper.SectorMapper;
import com.niche.ng.web.rest.errors.ExceptionTranslator;
import com.niche.ng.service.dto.SectorCriteria;
import com.niche.ng.service.SectorQueryService;

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
 * Test class for the SectorResource REST controller.
 *
 * @see SectorResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectghApp.class)
public class SectorResourceIntTest {

    private static final String DEFAULT_SECTOR_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SECTOR_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SECTOR_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_SECTOR_ADDRESS = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    @Autowired
    private SectorRepository sectorRepository;


    @Autowired
    private SectorMapper sectorMapper;
    

    @Autowired
    private SectorService sectorService;

    @Autowired
    private SectorQueryService sectorQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSectorMockMvc;

    private Sector sector;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SectorResource sectorResource = new SectorResource(sectorService, sectorQueryService);
        this.restSectorMockMvc = MockMvcBuilders.standaloneSetup(sectorResource)
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
    public static Sector createEntity(EntityManager em) {
        Sector sector = new Sector()
            .sectorName(DEFAULT_SECTOR_NAME)
            .sectorAddress(DEFAULT_SECTOR_ADDRESS)
            .status(DEFAULT_STATUS);
        return sector;
    }

    @Before
    public void initTest() {
        sector = createEntity(em);
    }

    @Test
    @Transactional
    public void createSector() throws Exception {
        int databaseSizeBeforeCreate = sectorRepository.findAll().size();

        // Create the Sector
        SectorDTO sectorDTO = sectorMapper.toDto(sector);
        restSectorMockMvc.perform(post("/api/sectors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sectorDTO)))
            .andExpect(status().isCreated());

        // Validate the Sector in the database
        List<Sector> sectorList = sectorRepository.findAll();
        assertThat(sectorList).hasSize(databaseSizeBeforeCreate + 1);
        Sector testSector = sectorList.get(sectorList.size() - 1);
        assertThat(testSector.getSectorName()).isEqualTo(DEFAULT_SECTOR_NAME);
        assertThat(testSector.getSectorAddress()).isEqualTo(DEFAULT_SECTOR_ADDRESS);
        assertThat(testSector.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createSectorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sectorRepository.findAll().size();

        // Create the Sector with an existing ID
        sector.setId(1L);
        SectorDTO sectorDTO = sectorMapper.toDto(sector);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSectorMockMvc.perform(post("/api/sectors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sectorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Sector in the database
        List<Sector> sectorList = sectorRepository.findAll();
        assertThat(sectorList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkSectorNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = sectorRepository.findAll().size();
        // set the field null
        sector.setSectorName(null);

        // Create the Sector, which fails.
        SectorDTO sectorDTO = sectorMapper.toDto(sector);

        restSectorMockMvc.perform(post("/api/sectors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sectorDTO)))
            .andExpect(status().isBadRequest());

        List<Sector> sectorList = sectorRepository.findAll();
        assertThat(sectorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSectors() throws Exception {
        // Initialize the database
        sectorRepository.saveAndFlush(sector);

        // Get all the sectorList
        restSectorMockMvc.perform(get("/api/sectors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sector.getId().intValue())))
            .andExpect(jsonPath("$.[*].sectorName").value(hasItem(DEFAULT_SECTOR_NAME.toString())))
            .andExpect(jsonPath("$.[*].sectorAddress").value(hasItem(DEFAULT_SECTOR_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    

    @Test
    @Transactional
    public void getSector() throws Exception {
        // Initialize the database
        sectorRepository.saveAndFlush(sector);

        // Get the sector
        restSectorMockMvc.perform(get("/api/sectors/{id}", sector.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sector.getId().intValue()))
            .andExpect(jsonPath("$.sectorName").value(DEFAULT_SECTOR_NAME.toString()))
            .andExpect(jsonPath("$.sectorAddress").value(DEFAULT_SECTOR_ADDRESS.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getAllSectorsBySectorNameIsEqualToSomething() throws Exception {
        // Initialize the database
        sectorRepository.saveAndFlush(sector);

        // Get all the sectorList where sectorName equals to DEFAULT_SECTOR_NAME
        defaultSectorShouldBeFound("sectorName.equals=" + DEFAULT_SECTOR_NAME);

        // Get all the sectorList where sectorName equals to UPDATED_SECTOR_NAME
        defaultSectorShouldNotBeFound("sectorName.equals=" + UPDATED_SECTOR_NAME);
    }

    @Test
    @Transactional
    public void getAllSectorsBySectorNameIsInShouldWork() throws Exception {
        // Initialize the database
        sectorRepository.saveAndFlush(sector);

        // Get all the sectorList where sectorName in DEFAULT_SECTOR_NAME or UPDATED_SECTOR_NAME
        defaultSectorShouldBeFound("sectorName.in=" + DEFAULT_SECTOR_NAME + "," + UPDATED_SECTOR_NAME);

        // Get all the sectorList where sectorName equals to UPDATED_SECTOR_NAME
        defaultSectorShouldNotBeFound("sectorName.in=" + UPDATED_SECTOR_NAME);
    }

    @Test
    @Transactional
    public void getAllSectorsBySectorNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        sectorRepository.saveAndFlush(sector);

        // Get all the sectorList where sectorName is not null
        defaultSectorShouldBeFound("sectorName.specified=true");

        // Get all the sectorList where sectorName is null
        defaultSectorShouldNotBeFound("sectorName.specified=false");
    }

    @Test
    @Transactional
    public void getAllSectorsBySectorAddressIsEqualToSomething() throws Exception {
        // Initialize the database
        sectorRepository.saveAndFlush(sector);

        // Get all the sectorList where sectorAddress equals to DEFAULT_SECTOR_ADDRESS
        defaultSectorShouldBeFound("sectorAddress.equals=" + DEFAULT_SECTOR_ADDRESS);

        // Get all the sectorList where sectorAddress equals to UPDATED_SECTOR_ADDRESS
        defaultSectorShouldNotBeFound("sectorAddress.equals=" + UPDATED_SECTOR_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllSectorsBySectorAddressIsInShouldWork() throws Exception {
        // Initialize the database
        sectorRepository.saveAndFlush(sector);

        // Get all the sectorList where sectorAddress in DEFAULT_SECTOR_ADDRESS or UPDATED_SECTOR_ADDRESS
        defaultSectorShouldBeFound("sectorAddress.in=" + DEFAULT_SECTOR_ADDRESS + "," + UPDATED_SECTOR_ADDRESS);

        // Get all the sectorList where sectorAddress equals to UPDATED_SECTOR_ADDRESS
        defaultSectorShouldNotBeFound("sectorAddress.in=" + UPDATED_SECTOR_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllSectorsBySectorAddressIsNullOrNotNull() throws Exception {
        // Initialize the database
        sectorRepository.saveAndFlush(sector);

        // Get all the sectorList where sectorAddress is not null
        defaultSectorShouldBeFound("sectorAddress.specified=true");

        // Get all the sectorList where sectorAddress is null
        defaultSectorShouldNotBeFound("sectorAddress.specified=false");
    }

    @Test
    @Transactional
    public void getAllSectorsByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        sectorRepository.saveAndFlush(sector);

        // Get all the sectorList where status equals to DEFAULT_STATUS
        defaultSectorShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the sectorList where status equals to UPDATED_STATUS
        defaultSectorShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllSectorsByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        sectorRepository.saveAndFlush(sector);

        // Get all the sectorList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultSectorShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the sectorList where status equals to UPDATED_STATUS
        defaultSectorShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllSectorsByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        sectorRepository.saveAndFlush(sector);

        // Get all the sectorList where status is not null
        defaultSectorShouldBeFound("status.specified=true");

        // Get all the sectorList where status is null
        defaultSectorShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllSectorsByStatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sectorRepository.saveAndFlush(sector);

        // Get all the sectorList where status greater than or equals to DEFAULT_STATUS
        defaultSectorShouldBeFound("status.greaterOrEqualThan=" + DEFAULT_STATUS);

        // Get all the sectorList where status greater than or equals to UPDATED_STATUS
        defaultSectorShouldNotBeFound("status.greaterOrEqualThan=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllSectorsByStatusIsLessThanSomething() throws Exception {
        // Initialize the database
        sectorRepository.saveAndFlush(sector);

        // Get all the sectorList where status less than or equals to DEFAULT_STATUS
        defaultSectorShouldNotBeFound("status.lessThan=" + DEFAULT_STATUS);

        // Get all the sectorList where status less than or equals to UPDATED_STATUS
        defaultSectorShouldBeFound("status.lessThan=" + UPDATED_STATUS);
    }


    @Test
    @Transactional
    public void getAllSectorsByNurserysIsEqualToSomething() throws Exception {
        // Initialize the database
        Nursery nurserys = NurseryResourceIntTest.createEntity(em);
        em.persist(nurserys);
        em.flush();
        sector.addNurserys(nurserys);
        sectorRepository.saveAndFlush(sector);
        Long nurserysId = nurserys.getId();

        // Get all the sectorList where nurserys equals to nurserysId
        defaultSectorShouldBeFound("nurserysId.equals=" + nurserysId);

        // Get all the sectorList where nurserys equals to nurserysId + 1
        defaultSectorShouldNotBeFound("nurserysId.equals=" + (nurserysId + 1));
    }


    @Test
    @Transactional
    public void getAllSectorsByZonalIsEqualToSomething() throws Exception {
        // Initialize the database
        Zonal zonal = ZonalResourceIntTest.createEntity(em);
        em.persist(zonal);
        em.flush();
        sector.setZonal(zonal);
        sectorRepository.saveAndFlush(sector);
        Long zonalId = zonal.getId();

        // Get all the sectorList where zonal equals to zonalId
        defaultSectorShouldBeFound("zonalId.equals=" + zonalId);

        // Get all the sectorList where zonal equals to zonalId + 1
        defaultSectorShouldNotBeFound("zonalId.equals=" + (zonalId + 1));
    }


    @Test
    @Transactional
    public void getAllSectorsByFinancialYearSectorIsEqualToSomething() throws Exception {
        // Initialize the database
        FinancialYearSettings financialYearSector = FinancialYearSettingsResourceIntTest.createEntity(em);
        em.persist(financialYearSector);
        em.flush();
        sector.setFinancialYearSector(financialYearSector);
        sectorRepository.saveAndFlush(sector);
        Long financialYearSectorId = financialYearSector.getId();

        // Get all the sectorList where financialYearSector equals to financialYearSectorId
        defaultSectorShouldBeFound("financialYearSectorId.equals=" + financialYearSectorId);

        // Get all the sectorList where financialYearSector equals to financialYearSectorId + 1
        defaultSectorShouldNotBeFound("financialYearSectorId.equals=" + (financialYearSectorId + 1));
    }


    @Test
    @Transactional
    public void getAllSectorsByInchargeIsEqualToSomething() throws Exception {
        // Initialize the database
        SectorIncharge incharge = SectorInchargeResourceIntTest.createEntity(em);
        em.persist(incharge);
        em.flush();
        sector.addIncharge(incharge);
        sectorRepository.saveAndFlush(sector);
        Long inchargeId = incharge.getId();

        // Get all the sectorList where incharge equals to inchargeId
        defaultSectorShouldBeFound("inchargeId.equals=" + inchargeId);

        // Get all the sectorList where incharge equals to inchargeId + 1
        defaultSectorShouldNotBeFound("inchargeId.equals=" + (inchargeId + 1));
    }


    @Test
    @Transactional
    public void getAllSectorsByMapSectorWithZonalIsEqualToSomething() throws Exception {
        // Initialize the database
        MapSectorWithZonal mapSectorWithZonal = MapSectorWithZonalResourceIntTest.createEntity(em);
        em.persist(mapSectorWithZonal);
        em.flush();
        sector.addMapSectorWithZonal(mapSectorWithZonal);
        sectorRepository.saveAndFlush(sector);
        Long mapSectorWithZonalId = mapSectorWithZonal.getId();

        // Get all the sectorList where mapSectorWithZonal equals to mapSectorWithZonalId
        defaultSectorShouldBeFound("mapSectorWithZonalId.equals=" + mapSectorWithZonalId);

        // Get all the sectorList where mapSectorWithZonal equals to mapSectorWithZonalId + 1
        defaultSectorShouldNotBeFound("mapSectorWithZonalId.equals=" + (mapSectorWithZonalId + 1));
    }


    @Test
    @Transactional
    public void getAllSectorsByMapNurseryWithSectorIsEqualToSomething() throws Exception {
        // Initialize the database
        MapNurseryWithSector mapNurseryWithSector = MapNurseryWithSectorResourceIntTest.createEntity(em);
        em.persist(mapNurseryWithSector);
        em.flush();
        sector.addMapNurseryWithSector(mapNurseryWithSector);
        sectorRepository.saveAndFlush(sector);
        Long mapNurseryWithSectorId = mapNurseryWithSector.getId();

        // Get all the sectorList where mapNurseryWithSector equals to mapNurseryWithSectorId
        defaultSectorShouldBeFound("mapNurseryWithSectorId.equals=" + mapNurseryWithSectorId);

        // Get all the sectorList where mapNurseryWithSector equals to mapNurseryWithSectorId + 1
        defaultSectorShouldNotBeFound("mapNurseryWithSectorId.equals=" + (mapNurseryWithSectorId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultSectorShouldBeFound(String filter) throws Exception {
        restSectorMockMvc.perform(get("/api/sectors?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sector.getId().intValue())))
            .andExpect(jsonPath("$.[*].sectorName").value(hasItem(DEFAULT_SECTOR_NAME.toString())))
            .andExpect(jsonPath("$.[*].sectorAddress").value(hasItem(DEFAULT_SECTOR_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultSectorShouldNotBeFound(String filter) throws Exception {
        restSectorMockMvc.perform(get("/api/sectors?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingSector() throws Exception {
        // Get the sector
        restSectorMockMvc.perform(get("/api/sectors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSector() throws Exception {
        // Initialize the database
        sectorRepository.saveAndFlush(sector);

        int databaseSizeBeforeUpdate = sectorRepository.findAll().size();

        // Update the sector
        Sector updatedSector = sectorRepository.findById(sector.getId()).get();
        // Disconnect from session so that the updates on updatedSector are not directly saved in db
        em.detach(updatedSector);
        updatedSector
            .sectorName(UPDATED_SECTOR_NAME)
            .sectorAddress(UPDATED_SECTOR_ADDRESS)
            .status(UPDATED_STATUS);
        SectorDTO sectorDTO = sectorMapper.toDto(updatedSector);

        restSectorMockMvc.perform(put("/api/sectors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sectorDTO)))
            .andExpect(status().isOk());

        // Validate the Sector in the database
        List<Sector> sectorList = sectorRepository.findAll();
        assertThat(sectorList).hasSize(databaseSizeBeforeUpdate);
        Sector testSector = sectorList.get(sectorList.size() - 1);
        assertThat(testSector.getSectorName()).isEqualTo(UPDATED_SECTOR_NAME);
        assertThat(testSector.getSectorAddress()).isEqualTo(UPDATED_SECTOR_ADDRESS);
        assertThat(testSector.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingSector() throws Exception {
        int databaseSizeBeforeUpdate = sectorRepository.findAll().size();

        // Create the Sector
        SectorDTO sectorDTO = sectorMapper.toDto(sector);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSectorMockMvc.perform(put("/api/sectors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sectorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Sector in the database
        List<Sector> sectorList = sectorRepository.findAll();
        assertThat(sectorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSector() throws Exception {
        // Initialize the database
        sectorRepository.saveAndFlush(sector);

        int databaseSizeBeforeDelete = sectorRepository.findAll().size();

        // Get the sector
        restSectorMockMvc.perform(delete("/api/sectors/{id}", sector.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Sector> sectorList = sectorRepository.findAll();
        assertThat(sectorList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Sector.class);
        Sector sector1 = new Sector();
        sector1.setId(1L);
        Sector sector2 = new Sector();
        sector2.setId(sector1.getId());
        assertThat(sector1).isEqualTo(sector2);
        sector2.setId(2L);
        assertThat(sector1).isNotEqualTo(sector2);
        sector1.setId(null);
        assertThat(sector1).isNotEqualTo(sector2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SectorDTO.class);
        SectorDTO sectorDTO1 = new SectorDTO();
        sectorDTO1.setId(1L);
        SectorDTO sectorDTO2 = new SectorDTO();
        assertThat(sectorDTO1).isNotEqualTo(sectorDTO2);
        sectorDTO2.setId(sectorDTO1.getId());
        assertThat(sectorDTO1).isEqualTo(sectorDTO2);
        sectorDTO2.setId(2L);
        assertThat(sectorDTO1).isNotEqualTo(sectorDTO2);
        sectorDTO1.setId(null);
        assertThat(sectorDTO1).isNotEqualTo(sectorDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(sectorMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(sectorMapper.fromId(null)).isNull();
    }
}
