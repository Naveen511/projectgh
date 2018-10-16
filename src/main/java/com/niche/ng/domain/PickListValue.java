/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs PickListValue  Generation 
 *                        and declared the table fields, data types for 
 *                        PickListValue table.
 *  Relation for PickListValue : ManyToOne Relation, OneToMany Relation
 *  OneToMany Relation  : PickListValue, Batch, NurseryStock, GodownPurchaseDetails, 
 *                        GodownStock, Nursery, NurseryInventory, NurseryInventoryDetails, 
 *                        Damage, NurseryStockDetails, FinancialYearSettings
 *  ManyToOne Relation  : PickList, PickListValue
 *
 *******************************************************************************/
package com.niche.ng.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * PickListValue Domain Class
 * 
 * PickListValue class implements Serializable to convert instance class into 
 * series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
@Entity
@Table(name = "pick_list_value")
public class PickListValue extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "pick_list_value", nullable = false)
    private String pickListValue;

    @Column(name = "status")
    private Integer status;

     /**
     * Relation Name : OneToMany - selfIds
     * Table Name    : Connects the PickListValue Table to PickListValue Table
     * Used to point out the PickListValue values in the PickListValue table.
     */
    @OneToMany(mappedBy = "pickValue", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<PickListValue> selfIds = new HashSet<>();

    /**
     * Relation Name : OneToMany - varietys
     * Table Name    : Connects the PickListValue Table to Batch Table
     * Used to point out the PickListValue values in the Batch table.
     */
    @OneToMany(mappedBy = "pickListVariety", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<Batch> varietys = new HashSet<>();

    /**
     * Relation Name : OneToMany - categorys
     * Table Name    : Connects the PickListValue Table to Batch Table
     * Used to point out the PickListValue values in the Batch table.
     */
    @OneToMany(mappedBy = "pickListCategory", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<Batch> categorys = new HashSet<>();

    /**
     * Relation Name : OneToMany - nurseryStockVarietys
     * Table Name    : Connects the PickListValue Table to NurseryStock Table
     * Used to point out the PickListValue values in the NurseryStock table.
     */
    @OneToMany(mappedBy = "pickListVariety", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<NurseryStock> nurseryStockVarietys = new HashSet<>();

    /**
     * Relation Name : OneToMany - nurseryStockCategorys
     * Table Name    : Connects the PickListValue Table to NurseryStock Table
     * Used to point out the PickListValue values in the NurseryStock table.
     */
    @OneToMany(mappedBy = "pickListCategory", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<NurseryStock> nurseryStockCategorys = new HashSet<>();

    /**
     * Relation Name : OneToMany - godownPurchaseVarietys
     * Table Name    : Connects the PickListValue Table to GodownPurchaseDetails Table
     * Used to point out the PickListValue values in the GodownPurchaseDetails table.
     */
    @OneToMany(mappedBy = "pickListVariety", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<GodownPurchaseDetails> godownPurchaseVarietys = new HashSet<>();

    /**
     * Relation Name : OneToMany - godownPurchaseCategorys
     * Table Name    : Connects the PickListValue Table to GodownPurchaseDetails Table
     * Used to point out the PickListValue values in the GodownPurchaseDetails table.
     */
    @OneToMany(mappedBy = "pickListCategory", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<GodownPurchaseDetails> godownPurchaseCategorys = new HashSet<>();

    /**
     * Relation Name : OneToMany - godownPurchaseQuantityTypes
     * Table Name    : Connects the PickListValue Table to GodownPurchaseDetails Table
     * Used to point out the PickListValue values in the GodownPurchaseDetails table.
     */
    @OneToMany(mappedBy = "pickListQuantityType", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<GodownPurchaseDetails> godownPurchaseQuantityTypes = new HashSet<>();

    /**
     * Relation Name : OneToMany - godownStockVarietys
     * Table Name    : Connects the PickListValue Table to GodownStock Table
     * Used to point out the PickListValue values in the GodownStock table.
     */
    @OneToMany(mappedBy = "pickListVariety", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<GodownStock> godownStockVarietys = new HashSet<>();

    /**
     * Relation Name : OneToMany - godownStockCategorys
     * Table Name    : Connects the PickListValue Table to GodownStock Table
     * Used to point out the PickListValue values in the GodownStock table.
     */
    @OneToMany(mappedBy = "pickListCategory", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<GodownStock> godownStockCategorys = new HashSet<>();

    /**
     * Relation Name : OneToMany - godownStockQuantityTypes
     * Table Name    : Connects the PickListValue Table to GodownStock Table
     * Used to point out the PickListValue values in the GodownStock table.
     */
    @OneToMany(mappedBy = "pickListQuantityType", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<GodownStock> godownStockQuantityTypes = new HashSet<>();

    /**
     * Relation Name : ManyToOne - pickListValues
     * Table Name    : Connects the PickListValue Table to PickList Table.
     * Used to point out the PickListValue values in the PickList table.
     */
    @ManyToOne
    @JsonIgnoreProperties("pickListValues")
    @JoinColumn(name="pick_list_id", referencedColumnName="id")
    private PickList pickList;

    /**
     * Relation Name : ManyToOne - selfIds
     * Table Name    : Connects the PickListValue Table to PickListValue Table.
     * Used to point out the PickListValue values in the PickListValue table.
     */
    @ManyToOne
    @JsonIgnoreProperties("selfIds")
    @JoinColumn(name="pick_value_id", referencedColumnName="id")
    private PickListValue pickValue;

    /**
     * Relation Name : OneToMany - nurserys
     * Table Name    : Connects the PickListValue Table to Nursery Table
     * Used to point out the PickListValue values in the Nursery table.
     */
    @OneToMany(mappedBy = "nurseryType", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<Nursery> nurserys = new HashSet<>();

    /**
     * Relation Name : OneToMany - batchQuantityTypes
     * Table Name    : Connects the PickListValue Table to Batch Table
     * Used to point out the PickListValue values in the Batch table.
     */
    @OneToMany(mappedBy = "quantityType", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<Batch> batchQuantityTypes = new HashSet<>();

    /**
     * Relation Name : OneToMany - nurseryInventoryVarietys
     * Table Name    : Connects the PickListValue Table to NurseryInventory Table
     * Used to point out the PickListValue values in the NurseryInventory table.
     */
    @OneToMany(mappedBy = "pickListVariety", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<NurseryInventory> nurseryInventoryVarietys = new HashSet<>();

    /**
     * Relation Name : OneToMany - nurseryInventoryCategorys
     * Table Name    : Connects the PickListValue Table to NurseryInventory Table
     * Used to point out the PickListValue values in the NurseryInventory table.
     */
    @OneToMany(mappedBy = "pickListCategory", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<NurseryInventory> nurseryInventoryCategorys = new HashSet<>();

    /**
     * Relation Name : OneToMany - nurseryInventoryQuantityTypes
     * Table Name    : Connects the PickListValue Table to NurseryInventory Table
     * Used to point out the PickListValue values in the NurseryInventory table.
     */
    @OneToMany(mappedBy = "quantityType", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<NurseryInventory> nurseryInventoryQuantityTypes = new HashSet<>();

    /**
     * Relation Name : OneToMany - nurseryInventoryDamageTypes
     * Table Name    : Connects the PickListValue Table to NurseryInventoryDetails Table
     * Used to point out the PickListValue values in the NurseryInventoryDetails table.
     */
    @OneToMany(mappedBy = "damageType", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<NurseryInventoryDetails> nurseryInventoryDamageTypes = new HashSet<>();

    /**
     * Relation Name : OneToMany - pickListValueDamageAreas
     * Table Name    : Connects the PickListValue Table to Damage Table
     * Used to point out the PickListValue values in the Damage table.
     */
    @OneToMany(mappedBy = "damageArea", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<Damage> pickListValueDamageAreas = new HashSet<>();

    /**
     * Relation Name : OneToMany - nurseryStockDamageAreas
     * Table Name    : Connects the PickListValue Table to NurseryStockDetails Table
     * Used to point out the PickListValue values in the NurseryStockDetails table.
     */
    @OneToMany(mappedBy = "saplingDamageArea", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<NurseryStockDetails> nurseryStockDamageAreas = new HashSet<>();

    /**
     * Relation Name : OneToMany - financialYearNames
     * Table Name    : Connects the PickListValue Table to FinancialYearSettings Table
     * Used to point out the PickListValue values in the FinancialYearSettings table.
     */
    @OneToMany(mappedBy = "financialYear", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<FinancialYearSettings> financialYearNames = new HashSet<>();

    /**
     * Relation Name : OneToMany - damageDescriptions
     * Table Name    : Connects the PickListValue Table to Damage Table
     * Used to point out the PickListValue values in the Damage table.
     */
    @OneToMany(mappedBy = "description", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<Damage> damageDescriptions = new HashSet<>();

    /**
     * Relation Name : OneToMany - quantitysVarieties
     * Table Name    : Connects the PickListValue Table to Quantity Table
     * Used to point out the PickListValue values in the Quantity table.
     */
    @OneToMany(mappedBy = "pickListVariety", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<Quantity> quantitysVarieties = new HashSet<>();

    /**
     * Relation Name : OneToMany - quantitysCategories
     * Table Name    : Connects the PickListValue Table to Quantity Table
     * Used to point out the PickListValue values in the Quantity table.
     */
    @OneToMany(mappedBy = "pickListCategory", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<Quantity> quantitysCategories = new HashSet<>();

    /**
     * Relation Name : OneToMany - pointOfSaleVarietys
     * Table Name    : Connects the PickListValue Table to PointOfSaleDetails Table
     * Used to point out the PickListValue values in the PointOfSaleDetails table.
     */
    @OneToMany(mappedBy = "pickListVariety", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<PointOfSaleDetails> pointOfSaleVarietys = new HashSet<>();

    /**
     * Relation Name : OneToMany - pointOfSaleCategorys
     * Table Name    : Connects the PickListValue Table to PointOfSaleDetails Table
     * Used to point out the PickListValue values in the PointOfSaleDetails table.
     */
    @OneToMany(mappedBy = "pickListCategory", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<PointOfSaleDetails> pointOfSaleCategorys = new HashSet<>();

    /**
     * Relation Name : OneToMany - coverFillingDetails
     * Table Name    : Connects the PickListValue Table to CoverFillingDetails Table
     * Used to point out the PickListValue values in the CoverFillingDetails table.
     */
    @OneToMany(mappedBy = "damageType", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<CoverFillingDetails> coverFillingDetails = new HashSet<>();

    /**
     * Relation Name : OneToMany - nurseryInventoryDamageDescs
     * Table Name    : Connects the PickListValue Table to NurseryInventoryDetails Table
     * Used to point out the PickListValue values in the NurseryInventoryDetails table.
     */
    @OneToMany(mappedBy = "inventoryDamageDescription", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<NurseryInventoryDetails> nurseryInventoryDamageDescs = new HashSet<>();

    /**
     * Relation Name : OneToMany - coverFillingDamageDescs
     * Table Name    : Connects the PickListValue Table to CoverFillingDetails Table
     * Used to point out the PickListValue values in the CoverFillingDetails table.
     */
    @OneToMany(mappedBy = "coverFillingDamageDescription", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<CoverFillingDetails> coverFillingDamageDescs = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    /**
     * To Get the Id from PickListValue table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To set the id values in the PickListValue table.
     * 
     * @param id id of the PickListValue
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the pickListValue from pickListValue table
     * 
     * @return pickListValue
     */
    public String getPickListValue() {
        return pickListValue;
    }

    public PickListValue pickListValue(String pickListValue) {
        this.pickListValue = pickListValue;
        return this;
    }

     /**
     * To set the pickListValue in the pickListValue table.
     * 
     * @param pickListValue pickListValue of the pickListValue table
     */
    public void setPickListValue(String pickListValue) {
        this.pickListValue = pickListValue;
    }

    /**
     * To Get the status from pickListValue table
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To set the status values in the pickListValue table.
     * 
     * @param status status of the pickListValue
     * @return this
     */
    public PickListValue status(Integer status) {
        this.status = status;
        return this;
    }

     /**
     * To set the status in the pickListValue table.
     * 
     * @param status status of the pickListValue table
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the selfIds from pickListValue table
     * 
     * @return selfIds
     */
    public Set<PickListValue> getSelfIds() {
        return selfIds;
    }

    /**
     * To set the selfIds values in the pickListValue table.
     * 
     * @param pickListValues pickListValues of the pickListValue
     * @return this
     */
    public PickListValue selfIds(Set<PickListValue> pickListValues) {
        this.selfIds = pickListValues;
        return this;
    }

    /**
     * To set the add selfIds values in the pickListValue table.
     * 
     * @param pickListValues pickListValues of the pickListValue
     * @return this
     */
    public PickListValue addSelfIds(PickListValue pickListValue) {
        this.selfIds.add(pickListValue);
        pickListValue.setPickValue(this);
        return this;
    }

    /**
     * To set the remove selfIds values in the pickListValue table.
     * 
     * @param pickListValues pickListValues values
     * @return this
     */
    public PickListValue removeSelfIds(PickListValue pickListValue) {
        this.selfIds.remove(pickListValue);
        pickListValue.setPickValue(null);
        return this;
    }

     /**
     * To set the selfIds in the pickListValue table.
     * 
     * @param pickListValues pickListValues values
     */
    public void setSelfIds(Set<PickListValue> pickListValues) {
        this.selfIds = pickListValues;
    }

    /**
     * To Get the varietys from pickListValue table
     * 
     * @return varietys
     */
    public Set<Batch> getVarietys() {
        return varietys;
    }

    /**
     * To set the varietys values in the pickListValue table.
     * 
     * @param batches batches values
     * @return this
     */
    public PickListValue varietys(Set<Batch> batches) {
        this.varietys = batches;
        return this;
    }

    /**
     * To set the add batch values in the pickListValue table.
     * 
     * @param batch batch values
     * @return this
     */
    public PickListValue addVarietys(Batch batch) {
        this.varietys.add(batch);
        batch.setPickListVariety(this);
        return this;
    }

    /**
     * To set the remove batch values in the pickListValue table.
     * 
     * @param batch batch values
     * @return this
     */
    public PickListValue removeVarietys(Batch batch) {
        this.varietys.remove(batch);
        batch.setPickListVariety(null);
        return this;
    }

     /**
     * To set the varietys in the pickListValue table.
     * 
     * @param batches batches values
     */
    public void setVarietys(Set<Batch> batches) {
        this.varietys = batches;
    }

    /**
     * To Get the categorys from pickListValue table
     * 
     * @return categorys
     */
    public Set<Batch> getCategorys() {
        return categorys;
    }

    /**
     * To set the categorys values in the pickListValue table.
     * 
     * @param batches batches of the pickListValue
     * @return this
     */
    public PickListValue categorys(Set<Batch> batches) {
        this.categorys = batches;
        return this;
    }

    /**
     * To set the add categorys values in the pickListValue table.
     * 
     * @param batch batch of the pickListValue
     * @return this
     */
    public PickListValue addCategorys(Batch batch) {
        this.categorys.add(batch);
        batch.setPickListCategory(this);
        return this;
    }

    /**
     * To set the remove categorys values in the pickListValue table.
     * 
     * @param batch batch of the pickListValue
     * @return this
     */
    public PickListValue removeCategorys(Batch batch) {
        this.categorys.remove(batch);
        batch.setPickListCategory(null);
        return this;
    }

     /**
     * To set the categorys in the pickListValue table.
     * 
     * @param batches batches values
     */
    public void setCategorys(Set<Batch> batches) {
        this.categorys = batches;
    }

    /**
     * To Get the nurseryStockVarietys from pickListValue table
     * 
     * @return nurseryStockVarietys
     */
    public Set<NurseryStock> getNurseryStockVarietys() {
        return nurseryStockVarietys;
    }

    /**
     * To set the nurseryStockVarietys values in the pickListValue table.
     * 
     * @param nurseryStocks nurseryStocks values
     * @return this
     */
    public PickListValue nurseryStockVarietys(Set<NurseryStock> nurseryStocks) {
        this.nurseryStockVarietys = nurseryStocks;
        return this;
    }

    /**
     * To set the nadd urseryStockVarietys values in the pickListValue table.
     * 
     * @param nurseryStocks nurseryStocks values
     * @return this
     */
    public PickListValue addNurseryStockVarietys(NurseryStock nurseryStock) {
        this.nurseryStockVarietys.add(nurseryStock);
        nurseryStock.setPickListVariety(this);
        return this;
    }

    /**
     * To set the remove nurseryStockVarietys values in the pickListValue table.
     * 
     * @param nurseryStock nurseryStock values
     * @return this
     */
    public PickListValue removeNurseryStockVarietys(NurseryStock nurseryStock) {
        this.nurseryStockVarietys.remove(nurseryStock);
        nurseryStock.setPickListVariety(null);
        return this;
    }

     /**
     * To set the nurseryStockVarietys in the pickListValue table.
     * 
     * @param nurseryStock nurseryStock values
     */
    public void setNurseryStockVarietys(Set<NurseryStock> nurseryStocks) {
        this.nurseryStockVarietys = nurseryStocks;
    }

    /**
     * To Get the nurseryStockCategorys from pickListValue table
     * 
     * @return nurseryStockCategorys
     */
    public Set<NurseryStock> getNurseryStockCategorys() {
        return nurseryStockCategorys;
    }

    /**
     * To set the nurseryStockCategorys values in the pickListValue table.
     * 
     * @param nurseryStocks nurseryStocks values
     * @return this
     */
    public PickListValue nurseryStockCategorys(Set<NurseryStock> nurseryStocks) {
        this.nurseryStockCategorys = nurseryStocks;
        return this;
    }

    /**
     * To set the add nurseryStockCategorys values in the pickListValue table.
     * 
     * @param nurseryStocks nurseryStocks values
     * @return this
     */
    public PickListValue addNurseryStockCategorys(NurseryStock nurseryStock) {
        this.nurseryStockCategorys.add(nurseryStock);
        nurseryStock.setPickListCategory(this);
        return this;
    }

    /**
     * To set the remove nurseryStockCategorys values in the pickListValue table.
     * 
     * @param nurseryStocks nurseryStocks values
     * @return this
     */
    public PickListValue removeNurseryStockCategorys(NurseryStock nurseryStock) {
        this.nurseryStockCategorys.remove(nurseryStock);
        nurseryStock.setPickListCategory(null);
        return this;
    }

     /**
     * To set the nurseryStockCategorys in the pickListValue table.
     * 
     * @param nurseryStocks nurseryStocks values
     */
    public void setNurseryStockCategorys(Set<NurseryStock> nurseryStocks) {
        this.nurseryStockCategorys = nurseryStocks;
    }

    /**
     * To Get the godownPurchaseVarietys from pickListValue table
     * 
     * @return godownPurchaseVarietys
     */
    public Set<GodownPurchaseDetails> getGodownPurchaseVarietys() {
        return godownPurchaseVarietys;
    }

    /**
     * To set the godownPurchaseVarietys values in the pickListValue table.
     * 
     * @param godownPurchaseDetails godownPurchaseDetails values
     * @return this
     */
    public PickListValue godownPurchaseVarietys(Set<GodownPurchaseDetails> godownPurchaseDetails) {
        this.godownPurchaseVarietys = godownPurchaseDetails;
        return this;
    }

    /**
     * To set the add godownPurchaseVarietys values in the pickListValue table.
     * 
     * @param godownPurchaseDetails godownPurchaseDetails values
     * @return this
     */
    public PickListValue addGodownPurchaseVarietys(GodownPurchaseDetails godownPurchaseDetails) {
        this.godownPurchaseVarietys.add(godownPurchaseDetails);
        godownPurchaseDetails.setPickListVariety(this);
        return this;
    }

    /**
     * To set the remove godownPurchaseVarietys values in the pickListValue table.
     * 
     * @param godownPurchaseDetails godownPurchaseDetails values
     * @return this
     */
    public PickListValue removeGodownPurchaseVarietys(GodownPurchaseDetails godownPurchaseDetails) {
        this.godownPurchaseVarietys.remove(godownPurchaseDetails);
        godownPurchaseDetails.setPickListVariety(null);
        return this;
    }

     /**
     * To set the godownPurchaseVarietys in the pickListValue table.
     * 
     * @param godownPurchaseDetails godownPurchaseDetails values
     */
    public void setGodownPurchaseVarietys(Set<GodownPurchaseDetails> godownPurchaseDetails) {
        this.godownPurchaseVarietys = godownPurchaseDetails;
    }

    /**
     * To Get the godownPurchaseCategorys from pickListValue table
     * 
     * @return godownPurchaseCategorys
     */
    public Set<GodownPurchaseDetails> getGodownPurchaseCategorys() {
        return godownPurchaseCategorys;
    }

    /**
     * To set the godownPurchaseCategorys values in the pickListValue table.
     * 
     * @param godownPurchaseDetails godownPurchaseDetails values
     * @return this
     */
    public PickListValue godownPurchaseCategorys(Set<GodownPurchaseDetails> godownPurchaseDetails) {
        this.godownPurchaseCategorys = godownPurchaseDetails;
        return this;
    }

    /**
     * To set the add godownPurchaseCategorys values in the pickListValue table.
     * 
     * @param godownPurchaseDetails godownPurchaseDetails values
     * @return this
     */
    public PickListValue addGodownPurchaseCategorys(GodownPurchaseDetails godownPurchaseDetails) {
        this.godownPurchaseCategorys.add(godownPurchaseDetails);
        godownPurchaseDetails.setPickListCategory(this);
        return this;
    }

    /**
     * To set the remove godownPurchaseCategorys values in the pickListValue table.
     * 
     * @param godownPurchaseDetails values
     * @return this
     */
    public PickListValue removeGodownPurchaseCategorys(GodownPurchaseDetails godownPurchaseDetails) {
        this.godownPurchaseCategorys.remove(godownPurchaseDetails);
        godownPurchaseDetails.setPickListCategory(null);
        return this;
    }

     /**
     * To set the godownPurchaseCategorys in the pickListValue table.
     * 
     * @param godownPurchaseDetails godownPurchaseDetails values
     */
    public void setGodownPurchaseCategorys(Set<GodownPurchaseDetails> godownPurchaseDetails) {
        this.godownPurchaseCategorys = godownPurchaseDetails;
    }

    /**
     * To Get the godownPurchaseQuantityTypes from pickListValue table
     * 
     * @return godownPurchaseQuantityTypes
     */
    public Set<GodownPurchaseDetails> getGodownPurchaseQuantityTypes() {
        return godownPurchaseQuantityTypes;
    }

    /**
     * To set the godownPurchaseQuantityTypes values in the pickListValue table.
     * 
     * @param godownPurchaseDetails godownPurchaseDetails Value
     * @return this
     */
    public PickListValue godownPurchaseQuantityTypes(Set<GodownPurchaseDetails> godownPurchaseDetails) {
        this.godownPurchaseQuantityTypes = godownPurchaseDetails;
        return this;
    }

    /**
     * To set the add godownPurchaseQuantityTypes values in the pickListValue table.
     * 
     * @param godownPurchaseDetails godownPurchaseDetails Value
     * @return this
     */
    public PickListValue addGodownPurchaseQuantityType(GodownPurchaseDetails godownPurchaseDetails) {
        this.godownPurchaseQuantityTypes.add(godownPurchaseDetails);
        godownPurchaseDetails.setPickListQuantityType(this);
        return this;
    }

    /**
     * To set the remove godownPurchaseQuantityTypes values in the pickListValue table.
     * 
     * @param godownPurchaseDetails godownPurchaseDetails Value
     * @return this
     */
    public PickListValue removeGodownPurchaseQuantityType(GodownPurchaseDetails godownPurchaseDetails) {
        this.godownPurchaseQuantityTypes.remove(godownPurchaseDetails);
        godownPurchaseDetails.setPickListQuantityType(null);
        return this;
    }

     /**
     * To set the godownPurchaseQuantityTypes in the pickListValue table.
     * 
     * @param godownPurchaseDetails godownPurchaseDetails values
     */
    public void setGodownPurchaseQuantityTypes(Set<GodownPurchaseDetails> godownPurchaseDetails) {
        this.godownPurchaseQuantityTypes = godownPurchaseDetails;
    }

    /**
     * To Get the godownStockVarietys from pickListValue table
     * 
     * @return godownStockVarietys
     */
    public Set<GodownStock> getGodownStockVarietys() {
        return godownStockVarietys;
    }

    /**
     * To set the godownStockVarietys values in the pickListValue table.
     * 
     * @param godownStocks godownStocks Value
     * @return this
     */
    public PickListValue godownStockVarietys(Set<GodownStock> godownStocks) {
        this.godownStockVarietys = godownStocks;
        return this;
    }

    /**
     * To set the add godownStockVarietys values in the pickListValue table.
     * 
     * @param godownStocks godownStocks Value
     * @return this
     */
    public PickListValue addGodownStockVarietys(GodownStock godownStock) {
        this.godownStockVarietys.add(godownStock);
        godownStock.setPickListVariety(this);
        return this;
    }

    /**
     * To set the remove godownStockVarietys values in the pickListValue table.
     * 
     * @param godownStocks godownStocks Value
     * @return this
     */
    public PickListValue removeGodownStockVarietys(GodownStock godownStock) {
        this.godownStockVarietys.remove(godownStock);
        godownStock.setPickListVariety(null);
        return this;
    }

     /**
     * To set the godownStockVarietys in the pickListValue table.
     * 
     * @param godownStocks godownStocks values
     */
    public void setGodownStockVarietys(Set<GodownStock> godownStocks) {
        this.godownStockVarietys = godownStocks;
    }

    /**
     * To Get the godownStockCategorys from pickListValue table
     * 
     * @return godownStockCategorys
     */
    public Set<GodownStock> getGodownStockCategorys() {
        return godownStockCategorys;
    }

    /**
     * To set the godownStockCategorys values in the pickListValue table.
     * 
     * @param godownStocks godownStocks Value
     * @return this
     */
    public PickListValue godownStockCategorys(Set<GodownStock> godownStocks) {
        this.godownStockCategorys = godownStocks;
        return this;
    }

    /**
     * To set the add godownStockCategorys values in the pickListValue table.
     * 
     * @param godownStocks godownStocks Value
     * @return this
     */
    public PickListValue addGodownStockCategorys(GodownStock godownStock) {
        this.godownStockCategorys.add(godownStock);
        godownStock.setPickListCategory(this);
        return this;
    }

    /**
     * To set the remove godownStockCategorys values in the pickListValue table.
     * 
     * @param godownStocks godownStocks Value
     * @return this
     */
    public PickListValue removeGodownStockCategorys(GodownStock godownStock) {
        this.godownStockCategorys.remove(godownStock);
        godownStock.setPickListCategory(null);
        return this;
    }

     /**
     * To set the godownStockCategorys in the pickListValue table.
     * 
     * @param godownStocks godownStocks values
     */
    public void setGodownStockCategorys(Set<GodownStock> godownStocks) {
        this.godownStockCategorys = godownStocks;
    }

    /**
     * To Get the godownStockQuantityTypes from pickListValue table
     * 
     * @return godownStockQuantityTypes
     */
    public Set<GodownStock> getGodownStockQuantityTypes() {
        return godownStockQuantityTypes;
    }

    /**
     * To set the godownStockQuantityTypes values in the pickListValue table.
     * 
     * @param godownStocks godownStocks values
     * @return this
     */
    public PickListValue godownStockQuantityTypes(Set<GodownStock> godownStocks) {
        this.godownStockQuantityTypes = godownStocks;
        return this;
    }

    /**
     * To set the add godownStockQuantityTypes values in the pickListValue table.
     * 
     * @param godownStock godownStock values
     * @return this
     */
    public PickListValue addGodownStockQuantityTypes(GodownStock godownStock) {
        this.godownStockQuantityTypes.add(godownStock);
        godownStock.setPickListQuantityType(this);
        return this;
    }

    /**
     * To set the remove godownStockQuantityTypes values in the pickListValue table.
     * 
     * @param godownStock godownStock values
     * @return this
     */
    public PickListValue removeGodownStockQuantityTypes(GodownStock godownStock) {
        this.godownStockQuantityTypes.remove(godownStock);
        godownStock.setPickListQuantityType(null);
        return this;
    }

     /**
     * To set the godownStockQuantityTypes in the pickListValue table.
     * 
     * @param godownStocks godownStocks Value
     */
    public void setGodownStockQuantityTypes(Set<GodownStock> godownStocks) {
        this.godownStockQuantityTypes = godownStocks;
    }

    /**
     * To Get the pickList from pickListValue table
     * 
     * @return pickList
     */
    public PickList getPickList() {
        return pickList;
    }

    /**
     * To set the pickList values in the pickListValue table.
     * 
     * @param pickList pickList Value
     * @return this
     */
    public PickListValue pickList(PickList pickList) {
        this.pickList = pickList;
        return this;
    }

     /**
     * To set the pickList in the pickListValue table.
     * 
     * @param pickList pickList Value
     */
    public void setPickList(PickList pickList) {
        this.pickList = pickList;
    }

    /**
     * To Get the pickValue from pickListValue table
     * 
     * @return pickValue
     */
    public PickListValue getPickValue() {
        return pickValue;
    }

    /**
     * To set the pickValue values in the pickListValue table.
     * 
     * @param pickListValues pickListValues Value
     * @return this
     */
    public PickListValue pickValue(PickListValue pickListValue) {
        this.pickValue = pickListValue;
        return this;
    }

     /**
     * To set the pickValue in the pickListValue table.
     * 
     * @param pickListValue pickListValue Value
     */
    public void setPickValue(PickListValue pickListValue) {
        this.pickValue = pickListValue;
    }

    /**
     * To Get the quantitysVarieties from pickListValue table
     * 
     * @return quantitysVarieties
     */
    public Set<Quantity> getQuantitysVarieties() {
        return quantitysVarieties;
    }

    /**
     * To set the quantitysVarieties values in the pickListValue table.
     * 
     * @param quantities quantities Value
     * @return this
     */
    public PickListValue quantitysVarieties(Set<Quantity> quantities) {
        this.quantitysVarieties = quantities;
        return this;
    }

    /**
     * To set the add quantitysVarieties values in the pickListValue table.
     * 
     * @param quantity quantity Value
     * @return this
     */
    public PickListValue addQuantitysVariety(Quantity quantity) {
        this.quantitysVarieties.add(quantity);
        quantity.setPickListVariety(this);
        return this;
    }

    /**
     * To set the remove quantitysVarieties values in the pickListValue table.
     * 
     * @param quantity quantity Value
     * @return this
     */
    public PickListValue removeQuantitysVariety(Quantity quantity) {
        this.quantitysVarieties.remove(quantity);
        quantity.setPickListVariety(null);
        return this;
    }

     /**
     * To set the quantitysVarieties in the pickListValue table.
     * 
     * @param quantities quantities Value
     */
    public void setQuantitysVarieties(Set<Quantity> quantities) {
        this.quantitysVarieties = quantities;
    }

    /**
     * To Get the quantitysCategories from pickListValue table
     * 
     * @return quantitysCategories
     */
    public Set<Quantity> getQuantitysCategories() {
        return quantitysCategories;
    }

    /**
     * To set the quantitysCategories values in the pickListValue table.
     * 
     * @param quantities quantities Value
     * @return this
     */
    public PickListValue quantitysCategories(Set<Quantity> quantities) {
        this.quantitysCategories = quantities;
        return this;
    }

    /**
     * To set the add quantitysCategories values in the pickListValue table.
     * 
     * @param quantity quantity of the pickListValue
     * @return this
     */
    public PickListValue addQuantitysCategory(Quantity quantity) {
        this.quantitysCategories.add(quantity);
        quantity.setPickListCategory(this);
        return this;
    }

    /**
     * To set the remove quantitysCategories values in the pickListValue table.
     * 
     * @param quantity quantity of the pickListValue
     * @return this
     */
    public PickListValue removeQuantitysCategory(Quantity quantity) {
        this.quantitysCategories.remove(quantity);
        quantity.setPickListCategory(null);
        return this;
    }

     /**
     * To set the quantitysCategories in the pickListValue table.
     * 
     * @param quantities quantities Value
     */
    public void setQuantitysCategories(Set<Quantity> quantities) {
        this.quantitysCategories = quantities;
    }

    /**
     * To Get the nurserys from pickListValue table
     * 
     * @return nurserys
     */
    public Set<Nursery> getNurserys() {
        return nurserys;
    }

    /**
     * To set the nurserys values in the pickListValue table.
     * 
     * @param nurseries nurseries of the pickListValue
     * @return this
     */
    public PickListValue nurserys(Set<Nursery> nurseries) {
        this.nurserys = nurseries;
        return this;
    }

    /**
     * To set the add nurserys values in the pickListValue table.
     * 
     * @param nursery nursery of the pickListValue
     * @return this
     */
    public PickListValue addNurserys(Nursery nursery) {
        this.nurserys.add(nursery);
        nursery.setNurseryType(this);
        return this;
    }

    /**
     * To set the remove nurserys values in the pickListValue table.
     * 
     * @param nursery nursery of the pickListValue
     * @return this
     */
    public PickListValue removeNurserys(Nursery nursery) {
        this.nurserys.remove(nursery);
        nursery.setNurseryType(null);
        return this;
    }

     /**
     * To set the nurserys in the pickListValue table.
     * 
     * @param nurseries nurseries of the pickListValue table
     */
    public void setNurserys(Set<Nursery> nurseries) {
        this.nurserys = nurseries;
    }

    /**
     * To Get the batchQuantityTypes from pickListValue table
     * 
     * @return batchQuantityTypes
     */
    public Set<Batch> getBatchQuantityTypes() {
        return batchQuantityTypes;
    }

    /**
     * To set the batchQuantityTypes values in the pickListValue table.
     * 
     * @param batches batches of the pickListValue
     * @return this
     */
    public PickListValue batchQuantityTypes(Set<Batch> batches) {
        this.batchQuantityTypes = batches;
        return this;
    }

    /**
     * To set the add batchQuantityTypes values in the pickListValue table.
     * 
     * @param batch batch of the pickListValue
     * @return this
     */
    public PickListValue addBatchQuantityTypes(Batch batch) {
        this.batchQuantityTypes.add(batch);
        batch.setQuantityType(this);
        return this;
    }

    /**
     * To set the remove batchQuantityTypes values in the pickListValue table.
     * 
     * @param batch batch of the pickListValue
     * @return this
     */
    public PickListValue removeBatchQuantityTypes(Batch batch) {
        this.batchQuantityTypes.remove(batch);
        batch.setQuantityType(null);
        return this;
    }

     /**
     * To set the batchQuantityTypes in the pickListValue table.
     * 
     * @param batches batches of the pickListValue table
     */
    public void setBatchQuantityTypes(Set<Batch> batches) {
        this.batchQuantityTypes = batches;
    }

    /**
     * To Get the nurseryInventoryVarietys from pickListValue table
     * 
     * @return nurseryInventoryVarietys
     */
    public Set<NurseryInventory> getNurseryInventoryVarietys() {
        return nurseryInventoryVarietys;
    }

    /**
     * To set the nurseryInventoryVarietys values in the pickListValue table.
     * 
     * @param nurseryInventories nurseryInventories Value
     * @return this
     */
    public PickListValue nurseryInventoryVarietys(Set<NurseryInventory> nurseryInventories) {
        this.nurseryInventoryVarietys = nurseryInventories;
        return this;
    }

    /**
     * To set the add nurseryInventoryVarietys values in the pickListValue table.
     * 
     * @param nurseryInventory nurseryInventory Value
     * @return this
     */
    public PickListValue addNurseryInventoryVarietys(NurseryInventory nurseryInventory) {
        this.nurseryInventoryVarietys.add(nurseryInventory);
        nurseryInventory.setPickListVariety(this);
        return this;
    }

    /**
     * To set the remove nurseryInventoryVarietys values in the pickListValue table.
     * 
     * @param nurseryInventory nurseryInventory Value
     * @return this
     */
    public PickListValue removeNurseryInventoryVarietys(NurseryInventory nurseryInventory) {
        this.nurseryInventoryVarietys.remove(nurseryInventory);
        nurseryInventory.setPickListVariety(null);
        return this;
    }

     /**
     * To set the nurseryInventoryVarietys in the pickListValue table.
     * 
     * @param nurseryInventories nurseryInventories Value
     */
    public void setNurseryInventoryVarietys(Set<NurseryInventory> nurseryInventories) {
        this.nurseryInventoryVarietys = nurseryInventories;
    }

    /**
     * To Get the nurseryInventoryCategorys from pickListValue table
     * 
     * @return nurseryInventoryCategorys
     */
    public Set<NurseryInventory> getNurseryInventoryCategorys() {
        return nurseryInventoryCategorys;
    }

    /**
     * To set the nurseryInventoryCategorys values in the pickListValue table.
     * 
     * @param nurseryInventories nurseryInventories Value
     * @return this
     */
    public PickListValue nurseryInventoryCategorys(Set<NurseryInventory> nurseryInventories) {
        this.nurseryInventoryCategorys = nurseryInventories;
        return this;
    }

    /**
     * To set the add nurseryInventoryCategorys values in the pickListValue table.
     * 
     * @param nurseryInventory nurseryInventory Value
     * @return this
     */
    public PickListValue addNurseryInventoryCategorys(NurseryInventory nurseryInventory) {
        this.nurseryInventoryCategorys.add(nurseryInventory);
        nurseryInventory.setPickListCategory(this);
        return this;
    }

    /**
     * To set the remove nurseryInventoryCategorys values in the pickListValue table.
     * 
     * @param nurseryInventory nurseryInventory Value
     * @return this
     */
    public PickListValue removeNurseryInventoryCategorys(NurseryInventory nurseryInventory) {
        this.nurseryInventoryCategorys.remove(nurseryInventory);
        nurseryInventory.setPickListCategory(null);
        return this;
    }

     /**
     * To set the nurseryInventoryCategorys in the pickListValue table.
     * 
     * @param nurseryInventories nurseryInventories Value
     */
    public void setNurseryInventoryCategorys(Set<NurseryInventory> nurseryInventories) {
        this.nurseryInventoryCategorys = nurseryInventories;
    }

    /**
     * To Get the nurseryInventoryQuantityTypes from pickListValue table
     * 
     * @return nurseryInventoryQuantityTypes
     */
    public Set<NurseryInventory> getNurseryInventoryQuantityTypes() {
        return nurseryInventoryQuantityTypes;
    }

    /**
     * To set the nurseryInventoryQuantityTypes values in the pickListValue table.
     * 
     * @param nurseryInventories nurseryInventories Value
     * @return this
     */
    public PickListValue nurseryInventoryQuantityTypes(Set<NurseryInventory> nurseryInventories) {
        this.nurseryInventoryQuantityTypes = nurseryInventories;
        return this;
    }

    /**
     * To set the add nurseryInventoryQuantityTypes values in the pickListValue table.
     * 
     * @param nurseryInventory nurseryInventory Value
     * @return this
     */
    public PickListValue addNurseryInventoryQuantityTypes(NurseryInventory nurseryInventory) {
        this.nurseryInventoryQuantityTypes.add(nurseryInventory);
        nurseryInventory.setQuantityType(this);
        return this;
    }

    /**
     * To set theremove nurseryInventoryQuantityTypes values in the pickListValue table.
     * 
     * @param nurseryInventory nurseryInventory Value
     * @return this
     */
    public PickListValue removeNurseryInventoryQuantityTypes(NurseryInventory nurseryInventory) {
        this.nurseryInventoryQuantityTypes.remove(nurseryInventory);
        nurseryInventory.setQuantityType(null);
        return this;
    }

     /**
     * To set the nurseryInventoryQuantityTypes in the pickListValue table.
     * 
     * @param nurseryInventories nurseryInventories oValue
     */
    public void setNurseryInventoryQuantityTypes(Set<NurseryInventory> nurseryInventories) {
        this.nurseryInventoryQuantityTypes = nurseryInventories;
    }

    /**
     * To Get the nurseryInventoryDamageTypes from pickListValue table
     * 
     * @return nurseryInventoryDamageTypes
     */
    public Set<NurseryInventoryDetails> getNurseryInventoryDamageTypes() {
        return nurseryInventoryDamageTypes;
    }

    /**
     * To set the nurseryInventoryDamageTypes values in the pickListValue table.
     * 
     * @param nurseryInventoryDetails nurseryInventoryDetails Value
     * @return this
     */
    public PickListValue nurseryInventoryDamageTypes(Set<NurseryInventoryDetails> nurseryInventoryDetails) {
        this.nurseryInventoryDamageTypes = nurseryInventoryDetails;
        return this;
    }

    /**
     * To set the add nurseryInventoryDamageTypes values in the pickListValue table.
     * 
     * @param nurseryInventoryDetails nurseryInventoryDetails Value
     * @return this
     */
    public PickListValue addNurseryInventoryDamageTypes(NurseryInventoryDetails nurseryInventoryDetails) {
        this.nurseryInventoryDamageTypes.add(nurseryInventoryDetails);
        nurseryInventoryDetails.setDamageType(this);
        return this;
    }

    /**
     * To set the remove nurseryInventoryDamageTypes values in the pickListValue table.
     * 
     * @param nurseryInventoryDetails nurseryInventoryDetails Value
     * @return this
     */
    public PickListValue removeNurseryInventoryDamageTypes(NurseryInventoryDetails nurseryInventoryDetails) {
        this.nurseryInventoryDamageTypes.remove(nurseryInventoryDetails);
        nurseryInventoryDetails.setDamageType(null);
        return this;
    }

     /**
     * To set the nurseryInventoryDamageTypes in the pickListValue table.
     * 
     * @param nurseryInventoryDetails nurseryInventoryDetails Value
     */
    public void setNurseryInventoryDamageTypes(Set<NurseryInventoryDetails> nurseryInventoryDetails) {
        this.nurseryInventoryDamageTypes = nurseryInventoryDetails;
    }

    /**
     * To Get the pickListValueDamageAreas from pickListValue table
     * 
     * @return pickListValueDamageAreas
     */
    public Set<Damage> getPickListValueDamageAreas() {
        return pickListValueDamageAreas;
    }

    /**
     * To set the pickListValueDamageAreas values in the pickListValue table.
     * 
     * @param damages damages of the pickListValue
     * @return this
     */
    public PickListValue pickListValueDamageAreas(Set<Damage> damages) {
        this.pickListValueDamageAreas = damages;
        return this;
    }

    /**
     * To set the add pickListValueDamageAreas values in the pickListValue table.
     * 
     * @param damage damage of the pickListValue
     * @return this
     */
    public PickListValue addPickListValueDamageArea(Damage damage) {
        this.pickListValueDamageAreas.add(damage);
        damage.setDamageArea(this);
        return this;
    }

    /**
     * To set the remove pickListValueDamageAreas values in the pickListValue table.
     * 
     * @param damage damage of the pickListValue
     * @return this
     */
    public PickListValue removePickListValueDamageArea(Damage damage) {
        this.pickListValueDamageAreas.remove(damage);
        damage.setDamageArea(null);
        return this;
    }

     /**
     * To set the pickListValueDamageAreas in the pickListValue table.
     * 
     * @param damages damages of the pickListValue table
     */
    public void setPickListValueDamageAreas(Set<Damage> damages) {
        this.pickListValueDamageAreas = damages;
    }

    /**
     * To Get the nurseryStockDamageAreas from pickListValue table
     * 
     * @return nurseryStockDamageAreas
     */
    public Set<NurseryStockDetails> getNurseryStockDamageAreas() {
        return nurseryStockDamageAreas;
    }

    /**
     * To set the nurseryStockDamageAreas values in the pickListValue table.
     * 
     * @param nurseryStockDetails nurseryStockDetails value
     * @return this
     */
    public PickListValue nurseryStockDamageAreas(Set<NurseryStockDetails> nurseryStockDetails) {
        this.nurseryStockDamageAreas = nurseryStockDetails;
        return this;
    }

    /**
     * To set the add nurseryStockDamageAreas values in the pickListValue table.
     * 
     * @param nurseryStockDetails nurseryStockDetails value
     * @return this
     */
    public PickListValue addNurseryStockDamageArea(NurseryStockDetails nurseryStockDetails) {
        this.nurseryStockDamageAreas.add(nurseryStockDetails);
        nurseryStockDetails.setSaplingDamageArea(this);
        return this;
    }

    /**
     * To set the remove nurseryStockDamageAreas values in the pickListValue table.
     * 
     * @param nurseryStockDetails nurseryStockDetails value
     * @return this
     */
    public PickListValue removeNurseryStockDamageArea(NurseryStockDetails nurseryStockDetails) {
        this.nurseryStockDamageAreas.remove(nurseryStockDetails);
        nurseryStockDetails.setSaplingDamageArea(null);
        return this;
    }

     /**
     * To set the nurseryStockDamageAreas in the pickListValue table.
     * 
     * @param nurseryStockDetails nurseryStockDetails value
     */
    public void setNurseryStockDamageAreas(Set<NurseryStockDetails> nurseryStockDetails) {
        this.nurseryStockDamageAreas = nurseryStockDetails;
    }

    /**
     * To Get the financialYearNames from pickListValue table
     * 
     * @return financialYearNames
     */
    public Set<FinancialYearSettings> getFinancialYearNames() {
        return financialYearNames;
    }

    /**
     * To set the financialYearNames values in the pickListValue table.
     * 
     * @param financialYearSettings financialYearSettings Value
     * @return this
     */
    public PickListValue financialYearNames(Set<FinancialYearSettings> financialYearSettings) {
        this.financialYearNames = financialYearSettings;
        return this;
    }

    /**
     * To set the add financialYearNames values in the pickListValue table.
     * 
     * @param financialYearSettings financialYearSettings Value
     * @return this
     */
    public PickListValue addFinancialYearName(FinancialYearSettings financialYearSettings) {
        this.financialYearNames.add(financialYearSettings);
        financialYearSettings.setFinancialYear(this);
        return this;
    }

    /**
     * To set the remove financialYearNames values in the pickListValue table.
     * 
     * @param financialYearSettings financialYearSettings Value
     * @return this
     */
    public PickListValue removeFinancialYearName(FinancialYearSettings financialYearSettings) {
        this.financialYearNames.remove(financialYearSettings);
        financialYearSettings.setFinancialYear(null);
        return this;
    }

     /**
     * To set the financialYearNames in the pickListValue table.
     * 
     * @param financialYearSettings financialYearSettings Value
     */
    public void setFinancialYearNames(Set<FinancialYearSettings> financialYearSettings) {
        this.financialYearNames = financialYearSettings;
    }

    /**
     * To Get the damageDescriptions from pickListValue table
     * 
     * @return damageDescriptions
     */
    public Set<Damage> getDamageDescriptions() {
        return damageDescriptions;
    }

    /**
     * To set the damageDescriptions values in the pickListValue table.
     * 
     * @param damages damages of the pickListValue
     * @return this
     */
    public PickListValue damageDescriptions(Set<Damage> damages) {
        this.damageDescriptions = damages;
        return this;
    }

    /**
     * To set the add damageDescriptions values in the pickListValue table.
     * 
     * @param pickListValues pickListValues of the pickListValue
     * @return this
     */
    public PickListValue addDamageDescription(Damage damage) {
        this.damageDescriptions.add(damage);
        damage.setDescription(this);
        return this;
    }

    /**
     * To set the remove damageDescriptions values in the pickListValue table.
     * 
     * @param damage damage Value
     * @return this
     */
    public PickListValue removeDamageDescription(Damage damage) {
        this.damageDescriptions.remove(damage);
        damage.setDescription(null);
        return this;
    }

     /**
     * To set the damageDescriptions in the pickListValue table.
     * 
     * @param damages damages Value
     */
    public void setDamageDescriptions(Set<Damage> damages) {
        this.damageDescriptions = damages;
    }

    /**
     * To Get the pointOfSaleVarietys from pickListValue table
     * 
     * @return pointOfSaleVarietys
     */
    public Set<PointOfSaleDetails> getPointOfSaleVarietys() {
        return pointOfSaleVarietys;
    }

    /**
     * To set the pointOfSaleVarietys values in the pickListValue table.
     * 
     * @param pointOfSaleDetails pointOfSaleDetails Value
     * @return this
     */
    public PickListValue pointOfSaleVarietys(Set<PointOfSaleDetails> pointOfSaleDetails) {
        this.pointOfSaleVarietys = pointOfSaleDetails;
        return this;
    }

    /**
     * To set the add pointOfSaleVarietys values in the pickListValue table.
     * 
     * @param pointOfSaleDetails pointOfSaleDetails Value
     * @return this
     */
    public PickListValue addPointOfSaleVarietys(PointOfSaleDetails pointOfSaleDetails) {
        this.pointOfSaleVarietys.add(pointOfSaleDetails);
        pointOfSaleDetails.setPickListVariety(this);
        return this;
    }

    /**
     * To set the remove pointOfSaleVarietys values in the pickListValue table.
     * 
     * @param pointOfSaleDetails pointOfSaleDetails Value
     * @return this
     */
    public PickListValue removePointOfSaleVarietys(PointOfSaleDetails pointOfSaleDetails) {
        this.pointOfSaleVarietys.remove(pointOfSaleDetails);
        pointOfSaleDetails.setPickListVariety(null);
        return this;
    }

     /**
     * To set the pointOfSaleVarietys in the pickListValue table.
     * 
     * @param pointOfSaleDetails pointOfSaleDetails Value
     */
    public void setPointOfSaleVarietys(Set<PointOfSaleDetails> pointOfSaleDetails) {
        this.pointOfSaleVarietys = pointOfSaleDetails;
    }

    /**
     * To Get the pointOfSaleCategorys from pickListValue table
     * 
     * @return pointOfSaleCategorys
     */
    public Set<PointOfSaleDetails> getPointOfSaleCategorys() {
        return pointOfSaleCategorys;
    }

    /**
     * To set the pointOfSaleCategorys values in the pickListValue table.
     * 
     * @param pointOfSaleDetails pointOfSaleDetails Value
     * @return this
     */
    public PickListValue pointOfSaleCategorys(Set<PointOfSaleDetails> pointOfSaleDetails) {
        this.pointOfSaleCategorys = pointOfSaleDetails;
        return this;
    }

    /**
     * To set the add pointOfSaleCategorys values in the pickListValue table.
     * 
     * @param pointOfSaleDetails pointOfSaleDetails Value
     * @return this
     */
    public PickListValue addPointOfSaleCategorys(PointOfSaleDetails pointOfSaleDetails) {
        this.pointOfSaleCategorys.add(pointOfSaleDetails);
        pointOfSaleDetails.setPickListCategory(this);
        return this;
    }

    /**
     * To set the remove pointOfSaleCategorys values in the pickListValue table.
     * 
     * @param pointOfSaleDetails pointOfSaleDetails Value
     * @return this
     */
    public PickListValue removePointOfSaleCategorys(PointOfSaleDetails pointOfSaleDetails) {
        this.pointOfSaleCategorys.remove(pointOfSaleDetails);
        pointOfSaleDetails.setPickListCategory(null);
        return this;
    }

     /**
     * To set the pointOfSaleCategorys in the pickListValue table.
     * 
     * @param pointOfSaleDetails pointOfSaleDetails Value
     */
    public void setPointOfSaleCategorys(Set<PointOfSaleDetails> pointOfSaleDetails) {
        this.pointOfSaleCategorys = pointOfSaleDetails;
    }

    /**
     * To Get the coverFillingDetails from pickListValue table
     * 
     * @return coverFillingDetails
     */
    public Set<CoverFillingDetails> getCoverFillingDetails() {
        return coverFillingDetails;
    }

    /**
     * To set the coverFillingDetails values in the pickListValue table.
     * 
     * @param coverFillingDetails coverFillingDetails Value
     * @return this
     */
    public PickListValue coverFillingDetails(Set<CoverFillingDetails> coverFillingDetails) {
        this.coverFillingDetails = coverFillingDetails;
        return this;
    }

    /**
     * To set the add coverFillingDetails values in the pickListValue table.
     * 
     * @param coverFillingDetails coverFillingDetails Value
     * @return this
     */
    public PickListValue addCoverFillingDetails(CoverFillingDetails coverFillingDetails) {
        this.coverFillingDetails.add(coverFillingDetails);
        coverFillingDetails.setDamageType(this);
        return this;
    }

    /**
     * To set the remove coverFillingDetails values in the pickListValue table.
     * 
     * @param coverFillingDetails coverFillingDetails Value
     * @return this
     */
    public PickListValue removeCoverFillingDetails(CoverFillingDetails coverFillingDetails) {
        this.coverFillingDetails.remove(coverFillingDetails);
        coverFillingDetails.setDamageType(null);
        return this;
    }

     /**
     * To set the coverFillingDetails in the pickListValue table.
     * 
     * @param coverFillingDetails coverFillingDetails Value
     */
    public void setCoverFillingDetails(Set<CoverFillingDetails> coverFillingDetails) {
        this.coverFillingDetails = coverFillingDetails;
    }

    /**
     * To Get the nurseryInventoryDamageDescs from pickListValue table
     * 
     * @return nurseryInventoryDamageDescs
     */
    public Set<NurseryInventoryDetails> getNurseryInventoryDamageDescs() {
        return nurseryInventoryDamageDescs;
    }

    /**
     * To set the nurseryInventoryDamageDescs values in the pickListValue table.
     * 
     * @param nurseryInventoryDetails nurseryInventoryDetails Value
     * @return this
     */
    public PickListValue nurseryInventoryDamageDescs(Set<NurseryInventoryDetails> nurseryInventoryDetails) {
        this.nurseryInventoryDamageDescs = nurseryInventoryDetails;
        return this;
    }

    /**
     * To set the add nurseryInventoryDamageDescs values in the pickListValue table.
     * 
     * @param nurseryInventoryDetails nurseryInventoryDetails Value
     * @return this
     */
    public PickListValue addNurseryInventoryDamageDesc(NurseryInventoryDetails nurseryInventoryDetails) {
        this.nurseryInventoryDamageDescs.add(nurseryInventoryDetails);
        nurseryInventoryDetails.setInventoryDamageDescription(this);
        return this;
    }

    /**
     * To set the remove nurseryInventoryDamageDescs values in the pickListValue table.
     * 
     * @param nurseryInventoryDetails nurseryInventoryDetails Value
     * @return this
     */
    public PickListValue removeNurseryInventoryDamageDesc(NurseryInventoryDetails nurseryInventoryDetails) {
        this.nurseryInventoryDamageDescs.remove(nurseryInventoryDetails);
        nurseryInventoryDetails.setInventoryDamageDescription(null);
        return this;
    }

     /**
     * To set the nurseryInventoryDamageDescs in the pickListValue table.
     * 
     * @param nurseryInventoryDetails nurseryInventoryDetails Value
     */
    public void setNurseryInventoryDamageDescs(Set<NurseryInventoryDetails> nurseryInventoryDetails) {
        this.nurseryInventoryDamageDescs = nurseryInventoryDetails;
    }

    /**
     * To Get the coverFillingDamageDescs from pickListValue table
     * 
     * @return coverFillingDamageDescs
     */
    public Set<CoverFillingDetails> getCoverFillingDamageDescs() {
        return coverFillingDamageDescs;
    }

    /**
     * To set the coverFillingDamageDescs values in the pickListValue table.
     * 
     * @param coverFillingDetails coverFillingDetails of the pickListValue
     * @return this
     */
    public PickListValue coverFillingDamageDescs(Set<CoverFillingDetails> coverFillingDetails) {
        this.coverFillingDamageDescs = coverFillingDetails;
        return this;
    }

    /**
     * To set the add coverFillingDamageDescs values in the pickListValue table.
     * 
     * @param coverFillingDetails coverFillingDetails of the pickListValue
     * @return this
     */
    public PickListValue addCoverFillingDamageDesc(CoverFillingDetails coverFillingDetails) {
        this.coverFillingDamageDescs.add(coverFillingDetails);
        coverFillingDetails.setCoverFillingDamageDescription(this);
        return this;
    }

    /**
     * To set the remove coverFillingDamageDescs values in the pickListValue table.
     * 
     * @param coverFillingDetails coverFillingDetails of the pickListValue
     * @return this
     */
    public PickListValue removeCoverFillingDamageDesc(CoverFillingDetails coverFillingDetails) {
        this.coverFillingDamageDescs.remove(coverFillingDetails);
        coverFillingDetails.setCoverFillingDamageDescription(null);
        return this;
    }

     /**
     * To set the coverFillingDamageDescs in the pickListValue table.
     * 
     * @param coverFillingDetails coverFillingDetails of the pickListValue table
     */
    public void setCoverFillingDamageDescs(Set<CoverFillingDetails> coverFillingDetails) {
        this.coverFillingDamageDescs = coverFillingDetails;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    /**
     * To check the equals
     * 
     * @return objects
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PickListValue pickListValue = (PickListValue) o;
        if (pickListValue.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pickListValue.getId());
    }

    /**
     * Hash code for the id
     * 
     * @return objects
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    /**
     * toString to get the values
     * 
     * @return values
     */
    @Override
    public String toString() {
        return "PickListValue{" +
            "id=" + getId() +
            ", pickListValue='" + getPickListValue() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
