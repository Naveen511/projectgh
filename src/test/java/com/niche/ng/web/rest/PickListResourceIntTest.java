package com.niche.ng.web.rest;

import com.niche.ng.ProjectghApp;

import com.niche.ng.domain.PickList;
import com.niche.ng.domain.PickListValue;
import com.niche.ng.repository.PickListRepository;
import com.niche.ng.service.PickListService;
import com.niche.ng.service.dto.PickListDTO;
import com.niche.ng.service.mapper.PickListMapper;
import com.niche.ng.web.rest.errors.ExceptionTranslator;
import com.niche.ng.service.dto.PickListCriteria;
import com.niche.ng.service.PickListQueryService;

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
 * Test class for the PickListResource REST controller.
 *
 * @see PickListResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectghApp.class)
public class PickListResourceIntTest {

    private static final String DEFAULT_PICK_LIST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PICK_LIST_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    @Autowired
    private PickListRepository pickListRepository;


    @Autowired
    private PickListMapper pickListMapper;
    

    @Autowired
    private PickListService pickListService;

    @Autowired
    private PickListQueryService pickListQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPickListMockMvc;

    private PickList pickList;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PickListResource pickListResource = new PickListResource(pickListService, pickListQueryService);
        this.restPickListMockMvc = MockMvcBuilders.standaloneSetup(pickListResource)
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
    public static PickList createEntity(EntityManager em) {
        PickList pickList = new PickList()
            .pickListName(DEFAULT_PICK_LIST_NAME)
            .status(DEFAULT_STATUS);
        return pickList;
    }

    @Before
    public void initTest() {
        pickList = createEntity(em);
    }

    @Test
    @Transactional
    public void createPickList() throws Exception {
        int databaseSizeBeforeCreate = pickListRepository.findAll().size();

        // Create the PickList
        PickListDTO pickListDTO = pickListMapper.toDto(pickList);
        restPickListMockMvc.perform(post("/api/pick-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pickListDTO)))
            .andExpect(status().isCreated());

        // Validate the PickList in the database
        List<PickList> pickListList = pickListRepository.findAll();
        assertThat(pickListList).hasSize(databaseSizeBeforeCreate + 1);
        PickList testPickList = pickListList.get(pickListList.size() - 1);
        assertThat(testPickList.getPickListName()).isEqualTo(DEFAULT_PICK_LIST_NAME);
        assertThat(testPickList.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createPickListWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pickListRepository.findAll().size();

        // Create the PickList with an existing ID
        pickList.setId(1L);
        PickListDTO pickListDTO = pickListMapper.toDto(pickList);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPickListMockMvc.perform(post("/api/pick-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pickListDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PickList in the database
        List<PickList> pickListList = pickListRepository.findAll();
        assertThat(pickListList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkPickListNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = pickListRepository.findAll().size();
        // set the field null
        pickList.setPickListName(null);

        // Create the PickList, which fails.
        PickListDTO pickListDTO = pickListMapper.toDto(pickList);

        restPickListMockMvc.perform(post("/api/pick-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pickListDTO)))
            .andExpect(status().isBadRequest());

        List<PickList> pickListList = pickListRepository.findAll();
        assertThat(pickListList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPickLists() throws Exception {
        // Initialize the database
        pickListRepository.saveAndFlush(pickList);

        // Get all the pickListList
        restPickListMockMvc.perform(get("/api/pick-lists?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pickList.getId().intValue())))
            .andExpect(jsonPath("$.[*].pickListName").value(hasItem(DEFAULT_PICK_LIST_NAME.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    

    @Test
    @Transactional
    public void getPickList() throws Exception {
        // Initialize the database
        pickListRepository.saveAndFlush(pickList);

        // Get the pickList
        restPickListMockMvc.perform(get("/api/pick-lists/{id}", pickList.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pickList.getId().intValue()))
            .andExpect(jsonPath("$.pickListName").value(DEFAULT_PICK_LIST_NAME.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getAllPickListsByPickListNameIsEqualToSomething() throws Exception {
        // Initialize the database
        pickListRepository.saveAndFlush(pickList);

        // Get all the pickListList where pickListName equals to DEFAULT_PICK_LIST_NAME
        defaultPickListShouldBeFound("pickListName.equals=" + DEFAULT_PICK_LIST_NAME);

        // Get all the pickListList where pickListName equals to UPDATED_PICK_LIST_NAME
        defaultPickListShouldNotBeFound("pickListName.equals=" + UPDATED_PICK_LIST_NAME);
    }

    @Test
    @Transactional
    public void getAllPickListsByPickListNameIsInShouldWork() throws Exception {
        // Initialize the database
        pickListRepository.saveAndFlush(pickList);

        // Get all the pickListList where pickListName in DEFAULT_PICK_LIST_NAME or UPDATED_PICK_LIST_NAME
        defaultPickListShouldBeFound("pickListName.in=" + DEFAULT_PICK_LIST_NAME + "," + UPDATED_PICK_LIST_NAME);

        // Get all the pickListList where pickListName equals to UPDATED_PICK_LIST_NAME
        defaultPickListShouldNotBeFound("pickListName.in=" + UPDATED_PICK_LIST_NAME);
    }

    @Test
    @Transactional
    public void getAllPickListsByPickListNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        pickListRepository.saveAndFlush(pickList);

        // Get all the pickListList where pickListName is not null
        defaultPickListShouldBeFound("pickListName.specified=true");

        // Get all the pickListList where pickListName is null
        defaultPickListShouldNotBeFound("pickListName.specified=false");
    }

    @Test
    @Transactional
    public void getAllPickListsByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        pickListRepository.saveAndFlush(pickList);

        // Get all the pickListList where status equals to DEFAULT_STATUS
        defaultPickListShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the pickListList where status equals to UPDATED_STATUS
        defaultPickListShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllPickListsByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        pickListRepository.saveAndFlush(pickList);

        // Get all the pickListList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultPickListShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the pickListList where status equals to UPDATED_STATUS
        defaultPickListShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllPickListsByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        pickListRepository.saveAndFlush(pickList);

        // Get all the pickListList where status is not null
        defaultPickListShouldBeFound("status.specified=true");

        // Get all the pickListList where status is null
        defaultPickListShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllPickListsByStatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pickListRepository.saveAndFlush(pickList);

        // Get all the pickListList where status greater than or equals to DEFAULT_STATUS
        defaultPickListShouldBeFound("status.greaterOrEqualThan=" + DEFAULT_STATUS);

        // Get all the pickListList where status greater than or equals to UPDATED_STATUS
        defaultPickListShouldNotBeFound("status.greaterOrEqualThan=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllPickListsByStatusIsLessThanSomething() throws Exception {
        // Initialize the database
        pickListRepository.saveAndFlush(pickList);

        // Get all the pickListList where status less than or equals to DEFAULT_STATUS
        defaultPickListShouldNotBeFound("status.lessThan=" + DEFAULT_STATUS);

        // Get all the pickListList where status less than or equals to UPDATED_STATUS
        defaultPickListShouldBeFound("status.lessThan=" + UPDATED_STATUS);
    }


    @Test
    @Transactional
    public void getAllPickListsByPickListValuesIsEqualToSomething() throws Exception {
        // Initialize the database
        PickListValue pickListValues = PickListValueResourceIntTest.createEntity(em);
        em.persist(pickListValues);
        em.flush();
        pickList.addPickListValues(pickListValues);
        pickListRepository.saveAndFlush(pickList);
        Long pickListValuesId = pickListValues.getId();

        // Get all the pickListList where pickListValues equals to pickListValuesId
        defaultPickListShouldBeFound("pickListValuesId.equals=" + pickListValuesId);

        // Get all the pickListList where pickListValues equals to pickListValuesId + 1
        defaultPickListShouldNotBeFound("pickListValuesId.equals=" + (pickListValuesId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultPickListShouldBeFound(String filter) throws Exception {
        restPickListMockMvc.perform(get("/api/pick-lists?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pickList.getId().intValue())))
            .andExpect(jsonPath("$.[*].pickListName").value(hasItem(DEFAULT_PICK_LIST_NAME.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultPickListShouldNotBeFound(String filter) throws Exception {
        restPickListMockMvc.perform(get("/api/pick-lists?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingPickList() throws Exception {
        // Get the pickList
        restPickListMockMvc.perform(get("/api/pick-lists/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePickList() throws Exception {
        // Initialize the database
        pickListRepository.saveAndFlush(pickList);

        int databaseSizeBeforeUpdate = pickListRepository.findAll().size();

        // Update the pickList
        PickList updatedPickList = pickListRepository.findById(pickList.getId()).get();
        // Disconnect from session so that the updates on updatedPickList are not directly saved in db
        em.detach(updatedPickList);
        updatedPickList
            .pickListName(UPDATED_PICK_LIST_NAME)
            .status(UPDATED_STATUS);
        PickListDTO pickListDTO = pickListMapper.toDto(updatedPickList);

        restPickListMockMvc.perform(put("/api/pick-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pickListDTO)))
            .andExpect(status().isOk());

        // Validate the PickList in the database
        List<PickList> pickListList = pickListRepository.findAll();
        assertThat(pickListList).hasSize(databaseSizeBeforeUpdate);
        PickList testPickList = pickListList.get(pickListList.size() - 1);
        assertThat(testPickList.getPickListName()).isEqualTo(UPDATED_PICK_LIST_NAME);
        assertThat(testPickList.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingPickList() throws Exception {
        int databaseSizeBeforeUpdate = pickListRepository.findAll().size();

        // Create the PickList
        PickListDTO pickListDTO = pickListMapper.toDto(pickList);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPickListMockMvc.perform(put("/api/pick-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pickListDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PickList in the database
        List<PickList> pickListList = pickListRepository.findAll();
        assertThat(pickListList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePickList() throws Exception {
        // Initialize the database
        pickListRepository.saveAndFlush(pickList);

        int databaseSizeBeforeDelete = pickListRepository.findAll().size();

        // Get the pickList
        restPickListMockMvc.perform(delete("/api/pick-lists/{id}", pickList.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PickList> pickListList = pickListRepository.findAll();
        assertThat(pickListList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PickList.class);
        PickList pickList1 = new PickList();
        pickList1.setId(1L);
        PickList pickList2 = new PickList();
        pickList2.setId(pickList1.getId());
        assertThat(pickList1).isEqualTo(pickList2);
        pickList2.setId(2L);
        assertThat(pickList1).isNotEqualTo(pickList2);
        pickList1.setId(null);
        assertThat(pickList1).isNotEqualTo(pickList2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PickListDTO.class);
        PickListDTO pickListDTO1 = new PickListDTO();
        pickListDTO1.setId(1L);
        PickListDTO pickListDTO2 = new PickListDTO();
        assertThat(pickListDTO1).isNotEqualTo(pickListDTO2);
        pickListDTO2.setId(pickListDTO1.getId());
        assertThat(pickListDTO1).isEqualTo(pickListDTO2);
        pickListDTO2.setId(2L);
        assertThat(pickListDTO1).isNotEqualTo(pickListDTO2);
        pickListDTO1.setId(null);
        assertThat(pickListDTO1).isNotEqualTo(pickListDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(pickListMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(pickListMapper.fromId(null)).isNull();
    }
}
