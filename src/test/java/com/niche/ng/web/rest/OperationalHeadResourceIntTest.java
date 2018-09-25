package com.niche.ng.web.rest;

import com.niche.ng.ProjectghApp;

import com.niche.ng.domain.OperationalHead;
import com.niche.ng.domain.Zonal;
import com.niche.ng.domain.MapZonalWithOh;
import com.niche.ng.repository.OperationalHeadRepository;
import com.niche.ng.service.OperationalHeadService;
import com.niche.ng.service.dto.OperationalHeadDTO;
import com.niche.ng.service.mapper.OperationalHeadMapper;
import com.niche.ng.web.rest.errors.ExceptionTranslator;
import com.niche.ng.service.dto.OperationalHeadCriteria;
import com.niche.ng.service.OperationalHeadQueryService;

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
 * Test class for the OperationalHeadResource REST controller.
 *
 * @see OperationalHeadResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectghApp.class)
public class OperationalHeadResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    @Autowired
    private OperationalHeadRepository operationalHeadRepository;


    @Autowired
    private OperationalHeadMapper operationalHeadMapper;
    

    @Autowired
    private OperationalHeadService operationalHeadService;

    @Autowired
    private OperationalHeadQueryService operationalHeadQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOperationalHeadMockMvc;

    private OperationalHead operationalHead;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OperationalHeadResource operationalHeadResource = new OperationalHeadResource(operationalHeadService, operationalHeadQueryService);
        this.restOperationalHeadMockMvc = MockMvcBuilders.standaloneSetup(operationalHeadResource)
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
    public static OperationalHead createEntity(EntityManager em) {
        OperationalHead operationalHead = new OperationalHead()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .status(DEFAULT_STATUS);
        return operationalHead;
    }

    @Before
    public void initTest() {
        operationalHead = createEntity(em);
    }

    @Test
    @Transactional
    public void createOperationalHead() throws Exception {
        int databaseSizeBeforeCreate = operationalHeadRepository.findAll().size();

        // Create the OperationalHead
        OperationalHeadDTO operationalHeadDTO = operationalHeadMapper.toDto(operationalHead);
        restOperationalHeadMockMvc.perform(post("/api/operational-heads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(operationalHeadDTO)))
            .andExpect(status().isCreated());

        // Validate the OperationalHead in the database
        List<OperationalHead> operationalHeadList = operationalHeadRepository.findAll();
        assertThat(operationalHeadList).hasSize(databaseSizeBeforeCreate + 1);
        OperationalHead testOperationalHead = operationalHeadList.get(operationalHeadList.size() - 1);
        assertThat(testOperationalHead.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testOperationalHead.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testOperationalHead.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createOperationalHeadWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = operationalHeadRepository.findAll().size();

        // Create the OperationalHead with an existing ID
        operationalHead.setId(1L);
        OperationalHeadDTO operationalHeadDTO = operationalHeadMapper.toDto(operationalHead);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOperationalHeadMockMvc.perform(post("/api/operational-heads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(operationalHeadDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OperationalHead in the database
        List<OperationalHead> operationalHeadList = operationalHeadRepository.findAll();
        assertThat(operationalHeadList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = operationalHeadRepository.findAll().size();
        // set the field null
        operationalHead.setName(null);

        // Create the OperationalHead, which fails.
        OperationalHeadDTO operationalHeadDTO = operationalHeadMapper.toDto(operationalHead);

        restOperationalHeadMockMvc.perform(post("/api/operational-heads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(operationalHeadDTO)))
            .andExpect(status().isBadRequest());

        List<OperationalHead> operationalHeadList = operationalHeadRepository.findAll();
        assertThat(operationalHeadList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllOperationalHeads() throws Exception {
        // Initialize the database
        operationalHeadRepository.saveAndFlush(operationalHead);

        // Get all the operationalHeadList
        restOperationalHeadMockMvc.perform(get("/api/operational-heads?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(operationalHead.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    

    @Test
    @Transactional
    public void getOperationalHead() throws Exception {
        // Initialize the database
        operationalHeadRepository.saveAndFlush(operationalHead);

        // Get the operationalHead
        restOperationalHeadMockMvc.perform(get("/api/operational-heads/{id}", operationalHead.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(operationalHead.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getAllOperationalHeadsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        operationalHeadRepository.saveAndFlush(operationalHead);

        // Get all the operationalHeadList where name equals to DEFAULT_NAME
        defaultOperationalHeadShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the operationalHeadList where name equals to UPDATED_NAME
        defaultOperationalHeadShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllOperationalHeadsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        operationalHeadRepository.saveAndFlush(operationalHead);

        // Get all the operationalHeadList where name in DEFAULT_NAME or UPDATED_NAME
        defaultOperationalHeadShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the operationalHeadList where name equals to UPDATED_NAME
        defaultOperationalHeadShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllOperationalHeadsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        operationalHeadRepository.saveAndFlush(operationalHead);

        // Get all the operationalHeadList where name is not null
        defaultOperationalHeadShouldBeFound("name.specified=true");

        // Get all the operationalHeadList where name is null
        defaultOperationalHeadShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllOperationalHeadsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        operationalHeadRepository.saveAndFlush(operationalHead);

        // Get all the operationalHeadList where description equals to DEFAULT_DESCRIPTION
        defaultOperationalHeadShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the operationalHeadList where description equals to UPDATED_DESCRIPTION
        defaultOperationalHeadShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllOperationalHeadsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        operationalHeadRepository.saveAndFlush(operationalHead);

        // Get all the operationalHeadList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultOperationalHeadShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the operationalHeadList where description equals to UPDATED_DESCRIPTION
        defaultOperationalHeadShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllOperationalHeadsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        operationalHeadRepository.saveAndFlush(operationalHead);

        // Get all the operationalHeadList where description is not null
        defaultOperationalHeadShouldBeFound("description.specified=true");

        // Get all the operationalHeadList where description is null
        defaultOperationalHeadShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllOperationalHeadsByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        operationalHeadRepository.saveAndFlush(operationalHead);

        // Get all the operationalHeadList where status equals to DEFAULT_STATUS
        defaultOperationalHeadShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the operationalHeadList where status equals to UPDATED_STATUS
        defaultOperationalHeadShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllOperationalHeadsByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        operationalHeadRepository.saveAndFlush(operationalHead);

        // Get all the operationalHeadList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultOperationalHeadShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the operationalHeadList where status equals to UPDATED_STATUS
        defaultOperationalHeadShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllOperationalHeadsByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        operationalHeadRepository.saveAndFlush(operationalHead);

        // Get all the operationalHeadList where status is not null
        defaultOperationalHeadShouldBeFound("status.specified=true");

        // Get all the operationalHeadList where status is null
        defaultOperationalHeadShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllOperationalHeadsByStatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        operationalHeadRepository.saveAndFlush(operationalHead);

        // Get all the operationalHeadList where status greater than or equals to DEFAULT_STATUS
        defaultOperationalHeadShouldBeFound("status.greaterOrEqualThan=" + DEFAULT_STATUS);

        // Get all the operationalHeadList where status greater than or equals to UPDATED_STATUS
        defaultOperationalHeadShouldNotBeFound("status.greaterOrEqualThan=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllOperationalHeadsByStatusIsLessThanSomething() throws Exception {
        // Initialize the database
        operationalHeadRepository.saveAndFlush(operationalHead);

        // Get all the operationalHeadList where status less than or equals to DEFAULT_STATUS
        defaultOperationalHeadShouldNotBeFound("status.lessThan=" + DEFAULT_STATUS);

        // Get all the operationalHeadList where status less than or equals to UPDATED_STATUS
        defaultOperationalHeadShouldBeFound("status.lessThan=" + UPDATED_STATUS);
    }


    @Test
    @Transactional
    public void getAllOperationalHeadsByZonalIsEqualToSomething() throws Exception {
        // Initialize the database
        Zonal zonal = ZonalResourceIntTest.createEntity(em);
        em.persist(zonal);
        em.flush();
        operationalHead.addZonal(zonal);
        operationalHeadRepository.saveAndFlush(operationalHead);
        Long zonalId = zonal.getId();

        // Get all the operationalHeadList where zonal equals to zonalId
        defaultOperationalHeadShouldBeFound("zonalId.equals=" + zonalId);

        // Get all the operationalHeadList where zonal equals to zonalId + 1
        defaultOperationalHeadShouldNotBeFound("zonalId.equals=" + (zonalId + 1));
    }


    @Test
    @Transactional
    public void getAllOperationalHeadsByMapZonalWithOhIsEqualToSomething() throws Exception {
        // Initialize the database
        MapZonalWithOh mapZonalWithOh = MapZonalWithOhResourceIntTest.createEntity(em);
        em.persist(mapZonalWithOh);
        em.flush();
        operationalHead.addMapZonalWithOh(mapZonalWithOh);
        operationalHeadRepository.saveAndFlush(operationalHead);
        Long mapZonalWithOhId = mapZonalWithOh.getId();

        // Get all the operationalHeadList where mapZonalWithOh equals to mapZonalWithOhId
        defaultOperationalHeadShouldBeFound("mapZonalWithOhId.equals=" + mapZonalWithOhId);

        // Get all the operationalHeadList where mapZonalWithOh equals to mapZonalWithOhId + 1
        defaultOperationalHeadShouldNotBeFound("mapZonalWithOhId.equals=" + (mapZonalWithOhId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultOperationalHeadShouldBeFound(String filter) throws Exception {
        restOperationalHeadMockMvc.perform(get("/api/operational-heads?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(operationalHead.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultOperationalHeadShouldNotBeFound(String filter) throws Exception {
        restOperationalHeadMockMvc.perform(get("/api/operational-heads?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingOperationalHead() throws Exception {
        // Get the operationalHead
        restOperationalHeadMockMvc.perform(get("/api/operational-heads/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOperationalHead() throws Exception {
        // Initialize the database
        operationalHeadRepository.saveAndFlush(operationalHead);

        int databaseSizeBeforeUpdate = operationalHeadRepository.findAll().size();

        // Update the operationalHead
        OperationalHead updatedOperationalHead = operationalHeadRepository.findById(operationalHead.getId()).get();
        // Disconnect from session so that the updates on updatedOperationalHead are not directly saved in db
        em.detach(updatedOperationalHead);
        updatedOperationalHead
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS);
        OperationalHeadDTO operationalHeadDTO = operationalHeadMapper.toDto(updatedOperationalHead);

        restOperationalHeadMockMvc.perform(put("/api/operational-heads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(operationalHeadDTO)))
            .andExpect(status().isOk());

        // Validate the OperationalHead in the database
        List<OperationalHead> operationalHeadList = operationalHeadRepository.findAll();
        assertThat(operationalHeadList).hasSize(databaseSizeBeforeUpdate);
        OperationalHead testOperationalHead = operationalHeadList.get(operationalHeadList.size() - 1);
        assertThat(testOperationalHead.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testOperationalHead.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testOperationalHead.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingOperationalHead() throws Exception {
        int databaseSizeBeforeUpdate = operationalHeadRepository.findAll().size();

        // Create the OperationalHead
        OperationalHeadDTO operationalHeadDTO = operationalHeadMapper.toDto(operationalHead);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restOperationalHeadMockMvc.perform(put("/api/operational-heads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(operationalHeadDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OperationalHead in the database
        List<OperationalHead> operationalHeadList = operationalHeadRepository.findAll();
        assertThat(operationalHeadList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOperationalHead() throws Exception {
        // Initialize the database
        operationalHeadRepository.saveAndFlush(operationalHead);

        int databaseSizeBeforeDelete = operationalHeadRepository.findAll().size();

        // Get the operationalHead
        restOperationalHeadMockMvc.perform(delete("/api/operational-heads/{id}", operationalHead.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<OperationalHead> operationalHeadList = operationalHeadRepository.findAll();
        assertThat(operationalHeadList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OperationalHead.class);
        OperationalHead operationalHead1 = new OperationalHead();
        operationalHead1.setId(1L);
        OperationalHead operationalHead2 = new OperationalHead();
        operationalHead2.setId(operationalHead1.getId());
        assertThat(operationalHead1).isEqualTo(operationalHead2);
        operationalHead2.setId(2L);
        assertThat(operationalHead1).isNotEqualTo(operationalHead2);
        operationalHead1.setId(null);
        assertThat(operationalHead1).isNotEqualTo(operationalHead2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OperationalHeadDTO.class);
        OperationalHeadDTO operationalHeadDTO1 = new OperationalHeadDTO();
        operationalHeadDTO1.setId(1L);
        OperationalHeadDTO operationalHeadDTO2 = new OperationalHeadDTO();
        assertThat(operationalHeadDTO1).isNotEqualTo(operationalHeadDTO2);
        operationalHeadDTO2.setId(operationalHeadDTO1.getId());
        assertThat(operationalHeadDTO1).isEqualTo(operationalHeadDTO2);
        operationalHeadDTO2.setId(2L);
        assertThat(operationalHeadDTO1).isNotEqualTo(operationalHeadDTO2);
        operationalHeadDTO1.setId(null);
        assertThat(operationalHeadDTO1).isNotEqualTo(operationalHeadDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(operationalHeadMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(operationalHeadMapper.fromId(null)).isNull();
    }
}
