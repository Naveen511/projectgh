package com.niche.ng.web.rest;

import com.niche.ng.ProjectghApp;

import com.niche.ng.domain.CommonSettings;
import com.niche.ng.repository.CommonSettingsRepository;
import com.niche.ng.service.CommonSettingsService;
import com.niche.ng.service.dto.CommonSettingsDTO;
import com.niche.ng.service.mapper.CommonSettingsMapper;
import com.niche.ng.web.rest.errors.ExceptionTranslator;
import com.niche.ng.service.dto.CommonSettingsCriteria;
import com.niche.ng.service.CommonSettingsQueryService;

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
 * Test class for the CommonSettingsResource REST controller.
 *
 * @see CommonSettingsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectghApp.class)
public class CommonSettingsResourceIntTest {

    private static final Integer DEFAULT_DAYS_TO_CLOSE_BATCH = 1;
    private static final Integer UPDATED_DAYS_TO_CLOSE_BATCH = 2;

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    @Autowired
    private CommonSettingsRepository commonSettingsRepository;


    @Autowired
    private CommonSettingsMapper commonSettingsMapper;
    

    @Autowired
    private CommonSettingsService commonSettingsService;

    @Autowired
    private CommonSettingsQueryService commonSettingsQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCommonSettingsMockMvc;

    private CommonSettings commonSettings;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CommonSettingsResource commonSettingsResource = new CommonSettingsResource(commonSettingsService, commonSettingsQueryService);
        this.restCommonSettingsMockMvc = MockMvcBuilders.standaloneSetup(commonSettingsResource)
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
    public static CommonSettings createEntity(EntityManager em) {
        CommonSettings commonSettings = new CommonSettings()
            .daysToCloseBatch(DEFAULT_DAYS_TO_CLOSE_BATCH)
            .status(DEFAULT_STATUS);
        return commonSettings;
    }

    @Before
    public void initTest() {
        commonSettings = createEntity(em);
    }

    @Test
    @Transactional
    public void createCommonSettings() throws Exception {
        int databaseSizeBeforeCreate = commonSettingsRepository.findAll().size();

        // Create the CommonSettings
        CommonSettingsDTO commonSettingsDTO = commonSettingsMapper.toDto(commonSettings);
        restCommonSettingsMockMvc.perform(post("/api/common-settings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(commonSettingsDTO)))
            .andExpect(status().isCreated());

        // Validate the CommonSettings in the database
        List<CommonSettings> commonSettingsList = commonSettingsRepository.findAll();
        assertThat(commonSettingsList).hasSize(databaseSizeBeforeCreate + 1);
        CommonSettings testCommonSettings = commonSettingsList.get(commonSettingsList.size() - 1);
        assertThat(testCommonSettings.getDaysToCloseBatch()).isEqualTo(DEFAULT_DAYS_TO_CLOSE_BATCH);
        assertThat(testCommonSettings.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createCommonSettingsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = commonSettingsRepository.findAll().size();

        // Create the CommonSettings with an existing ID
        commonSettings.setId(1L);
        CommonSettingsDTO commonSettingsDTO = commonSettingsMapper.toDto(commonSettings);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCommonSettingsMockMvc.perform(post("/api/common-settings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(commonSettingsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CommonSettings in the database
        List<CommonSettings> commonSettingsList = commonSettingsRepository.findAll();
        assertThat(commonSettingsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCommonSettings() throws Exception {
        // Initialize the database
        commonSettingsRepository.saveAndFlush(commonSettings);

        // Get all the commonSettingsList
        restCommonSettingsMockMvc.perform(get("/api/common-settings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(commonSettings.getId().intValue())))
            .andExpect(jsonPath("$.[*].daysToCloseBatch").value(hasItem(DEFAULT_DAYS_TO_CLOSE_BATCH)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    

    @Test
    @Transactional
    public void getCommonSettings() throws Exception {
        // Initialize the database
        commonSettingsRepository.saveAndFlush(commonSettings);

        // Get the commonSettings
        restCommonSettingsMockMvc.perform(get("/api/common-settings/{id}", commonSettings.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(commonSettings.getId().intValue()))
            .andExpect(jsonPath("$.daysToCloseBatch").value(DEFAULT_DAYS_TO_CLOSE_BATCH))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getAllCommonSettingsByDaysToCloseBatchIsEqualToSomething() throws Exception {
        // Initialize the database
        commonSettingsRepository.saveAndFlush(commonSettings);

        // Get all the commonSettingsList where daysToCloseBatch equals to DEFAULT_DAYS_TO_CLOSE_BATCH
        defaultCommonSettingsShouldBeFound("daysToCloseBatch.equals=" + DEFAULT_DAYS_TO_CLOSE_BATCH);

        // Get all the commonSettingsList where daysToCloseBatch equals to UPDATED_DAYS_TO_CLOSE_BATCH
        defaultCommonSettingsShouldNotBeFound("daysToCloseBatch.equals=" + UPDATED_DAYS_TO_CLOSE_BATCH);
    }

    @Test
    @Transactional
    public void getAllCommonSettingsByDaysToCloseBatchIsInShouldWork() throws Exception {
        // Initialize the database
        commonSettingsRepository.saveAndFlush(commonSettings);

        // Get all the commonSettingsList where daysToCloseBatch in DEFAULT_DAYS_TO_CLOSE_BATCH or UPDATED_DAYS_TO_CLOSE_BATCH
        defaultCommonSettingsShouldBeFound("daysToCloseBatch.in=" + DEFAULT_DAYS_TO_CLOSE_BATCH + "," + UPDATED_DAYS_TO_CLOSE_BATCH);

        // Get all the commonSettingsList where daysToCloseBatch equals to UPDATED_DAYS_TO_CLOSE_BATCH
        defaultCommonSettingsShouldNotBeFound("daysToCloseBatch.in=" + UPDATED_DAYS_TO_CLOSE_BATCH);
    }

    @Test
    @Transactional
    public void getAllCommonSettingsByDaysToCloseBatchIsNullOrNotNull() throws Exception {
        // Initialize the database
        commonSettingsRepository.saveAndFlush(commonSettings);

        // Get all the commonSettingsList where daysToCloseBatch is not null
        defaultCommonSettingsShouldBeFound("daysToCloseBatch.specified=true");

        // Get all the commonSettingsList where daysToCloseBatch is null
        defaultCommonSettingsShouldNotBeFound("daysToCloseBatch.specified=false");
    }

    @Test
    @Transactional
    public void getAllCommonSettingsByDaysToCloseBatchIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        commonSettingsRepository.saveAndFlush(commonSettings);

        // Get all the commonSettingsList where daysToCloseBatch greater than or equals to DEFAULT_DAYS_TO_CLOSE_BATCH
        defaultCommonSettingsShouldBeFound("daysToCloseBatch.greaterOrEqualThan=" + DEFAULT_DAYS_TO_CLOSE_BATCH);

        // Get all the commonSettingsList where daysToCloseBatch greater than or equals to UPDATED_DAYS_TO_CLOSE_BATCH
        defaultCommonSettingsShouldNotBeFound("daysToCloseBatch.greaterOrEqualThan=" + UPDATED_DAYS_TO_CLOSE_BATCH);
    }

    @Test
    @Transactional
    public void getAllCommonSettingsByDaysToCloseBatchIsLessThanSomething() throws Exception {
        // Initialize the database
        commonSettingsRepository.saveAndFlush(commonSettings);

        // Get all the commonSettingsList where daysToCloseBatch less than or equals to DEFAULT_DAYS_TO_CLOSE_BATCH
        defaultCommonSettingsShouldNotBeFound("daysToCloseBatch.lessThan=" + DEFAULT_DAYS_TO_CLOSE_BATCH);

        // Get all the commonSettingsList where daysToCloseBatch less than or equals to UPDATED_DAYS_TO_CLOSE_BATCH
        defaultCommonSettingsShouldBeFound("daysToCloseBatch.lessThan=" + UPDATED_DAYS_TO_CLOSE_BATCH);
    }


    @Test
    @Transactional
    public void getAllCommonSettingsByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        commonSettingsRepository.saveAndFlush(commonSettings);

        // Get all the commonSettingsList where status equals to DEFAULT_STATUS
        defaultCommonSettingsShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the commonSettingsList where status equals to UPDATED_STATUS
        defaultCommonSettingsShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllCommonSettingsByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        commonSettingsRepository.saveAndFlush(commonSettings);

        // Get all the commonSettingsList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultCommonSettingsShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the commonSettingsList where status equals to UPDATED_STATUS
        defaultCommonSettingsShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllCommonSettingsByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        commonSettingsRepository.saveAndFlush(commonSettings);

        // Get all the commonSettingsList where status is not null
        defaultCommonSettingsShouldBeFound("status.specified=true");

        // Get all the commonSettingsList where status is null
        defaultCommonSettingsShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllCommonSettingsByStatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        commonSettingsRepository.saveAndFlush(commonSettings);

        // Get all the commonSettingsList where status greater than or equals to DEFAULT_STATUS
        defaultCommonSettingsShouldBeFound("status.greaterOrEqualThan=" + DEFAULT_STATUS);

        // Get all the commonSettingsList where status greater than or equals to UPDATED_STATUS
        defaultCommonSettingsShouldNotBeFound("status.greaterOrEqualThan=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllCommonSettingsByStatusIsLessThanSomething() throws Exception {
        // Initialize the database
        commonSettingsRepository.saveAndFlush(commonSettings);

        // Get all the commonSettingsList where status less than or equals to DEFAULT_STATUS
        defaultCommonSettingsShouldNotBeFound("status.lessThan=" + DEFAULT_STATUS);

        // Get all the commonSettingsList where status less than or equals to UPDATED_STATUS
        defaultCommonSettingsShouldBeFound("status.lessThan=" + UPDATED_STATUS);
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultCommonSettingsShouldBeFound(String filter) throws Exception {
        restCommonSettingsMockMvc.perform(get("/api/common-settings?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(commonSettings.getId().intValue())))
            .andExpect(jsonPath("$.[*].daysToCloseBatch").value(hasItem(DEFAULT_DAYS_TO_CLOSE_BATCH)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultCommonSettingsShouldNotBeFound(String filter) throws Exception {
        restCommonSettingsMockMvc.perform(get("/api/common-settings?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingCommonSettings() throws Exception {
        // Get the commonSettings
        restCommonSettingsMockMvc.perform(get("/api/common-settings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCommonSettings() throws Exception {
        // Initialize the database
        commonSettingsRepository.saveAndFlush(commonSettings);

        int databaseSizeBeforeUpdate = commonSettingsRepository.findAll().size();

        // Update the commonSettings
        CommonSettings updatedCommonSettings = commonSettingsRepository.findById(commonSettings.getId()).get();
        // Disconnect from session so that the updates on updatedCommonSettings are not directly saved in db
        em.detach(updatedCommonSettings);
        updatedCommonSettings
            .daysToCloseBatch(UPDATED_DAYS_TO_CLOSE_BATCH)
            .status(UPDATED_STATUS);
        CommonSettingsDTO commonSettingsDTO = commonSettingsMapper.toDto(updatedCommonSettings);

        restCommonSettingsMockMvc.perform(put("/api/common-settings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(commonSettingsDTO)))
            .andExpect(status().isOk());

        // Validate the CommonSettings in the database
        List<CommonSettings> commonSettingsList = commonSettingsRepository.findAll();
        assertThat(commonSettingsList).hasSize(databaseSizeBeforeUpdate);
        CommonSettings testCommonSettings = commonSettingsList.get(commonSettingsList.size() - 1);
        assertThat(testCommonSettings.getDaysToCloseBatch()).isEqualTo(UPDATED_DAYS_TO_CLOSE_BATCH);
        assertThat(testCommonSettings.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingCommonSettings() throws Exception {
        int databaseSizeBeforeUpdate = commonSettingsRepository.findAll().size();

        // Create the CommonSettings
        CommonSettingsDTO commonSettingsDTO = commonSettingsMapper.toDto(commonSettings);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCommonSettingsMockMvc.perform(put("/api/common-settings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(commonSettingsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CommonSettings in the database
        List<CommonSettings> commonSettingsList = commonSettingsRepository.findAll();
        assertThat(commonSettingsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCommonSettings() throws Exception {
        // Initialize the database
        commonSettingsRepository.saveAndFlush(commonSettings);

        int databaseSizeBeforeDelete = commonSettingsRepository.findAll().size();

        // Get the commonSettings
        restCommonSettingsMockMvc.perform(delete("/api/common-settings/{id}", commonSettings.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CommonSettings> commonSettingsList = commonSettingsRepository.findAll();
        assertThat(commonSettingsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommonSettings.class);
        CommonSettings commonSettings1 = new CommonSettings();
        commonSettings1.setId(1L);
        CommonSettings commonSettings2 = new CommonSettings();
        commonSettings2.setId(commonSettings1.getId());
        assertThat(commonSettings1).isEqualTo(commonSettings2);
        commonSettings2.setId(2L);
        assertThat(commonSettings1).isNotEqualTo(commonSettings2);
        commonSettings1.setId(null);
        assertThat(commonSettings1).isNotEqualTo(commonSettings2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommonSettingsDTO.class);
        CommonSettingsDTO commonSettingsDTO1 = new CommonSettingsDTO();
        commonSettingsDTO1.setId(1L);
        CommonSettingsDTO commonSettingsDTO2 = new CommonSettingsDTO();
        assertThat(commonSettingsDTO1).isNotEqualTo(commonSettingsDTO2);
        commonSettingsDTO2.setId(commonSettingsDTO1.getId());
        assertThat(commonSettingsDTO1).isEqualTo(commonSettingsDTO2);
        commonSettingsDTO2.setId(2L);
        assertThat(commonSettingsDTO1).isNotEqualTo(commonSettingsDTO2);
        commonSettingsDTO1.setId(null);
        assertThat(commonSettingsDTO1).isNotEqualTo(commonSettingsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(commonSettingsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(commonSettingsMapper.fromId(null)).isNull();
    }
}
