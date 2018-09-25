/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs PickListValue Generation.
 *  Declared the table fields and data types for the PickListValue table.
 *  Defined the following Relation for the PickListValue Table :
 *  OneToMany Relation  : PickListValue, Batch, NurseryStock, GodownPurchaseDetails, 
 *         GodownStock, Nursery, NurseryInventory, NurseryInventoryDetails, 
 *         Damage, NurseryStockDetails, FinancialYearSettings
 *  ManyToOne Relation to PickList, PickListValue
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
 * A PickListValue.
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

    @OneToMany(mappedBy = "pickValue", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<PickListValue> selfIds = new HashSet<>();

    @OneToMany(mappedBy = "pickListVariety", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<Batch> varietys = new HashSet<>();

    @OneToMany(mappedBy = "pickListCategory", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<Batch> categorys = new HashSet<>();

    @OneToMany(mappedBy = "pickListVariety", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<NurseryStock> nurseryStockVarietys = new HashSet<>();

    @OneToMany(mappedBy = "pickListCategory", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<NurseryStock> nurseryStockCategorys = new HashSet<>();

    @OneToMany(mappedBy = "pickListVariety", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<GodownPurchaseDetails> godownPurchaseVarietys = new HashSet<>();

    @OneToMany(mappedBy = "pickListCategory", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<GodownPurchaseDetails> godownPurchaseCategorys = new HashSet<>();

    @OneToMany(mappedBy = "pickListQuantityType", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<GodownPurchaseDetails> godownPurchaseQuantityTypes = new HashSet<>();

    @OneToMany(mappedBy = "pickListVariety", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<GodownStock> godownStockVarietys = new HashSet<>();

    @OneToMany(mappedBy = "pickListCategory", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<GodownStock> godownStockCategorys = new HashSet<>();

    @OneToMany(mappedBy = "pickListQuantityType", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<GodownStock> godownStockQuantityTypes = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("pickListValues")
    @JoinColumn(name="pick_list_id", referencedColumnName="id")
    private PickList pickList;

    @ManyToOne
    @JsonIgnoreProperties("selfIds")
    @JoinColumn(name="pick_value_id", referencedColumnName="id")
    private PickListValue pickValue;

    @OneToMany(mappedBy = "nurseryType", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<Nursery> nurserys = new HashSet<>();

    @OneToMany(mappedBy = "quantityType", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<Batch> batchQuantityTypes = new HashSet<>();

    @OneToMany(mappedBy = "pickListVariety")
    private Set<NurseryInventory> nurseryInventoryVarietys = new HashSet<>();

    @OneToMany(mappedBy = "pickListCategory")
    private Set<NurseryInventory> nurseryInventoryCategorys = new HashSet<>();

    @OneToMany(mappedBy = "quantityType")
    private Set<NurseryInventory> nurseryInventoryQuantityTypes = new HashSet<>();

    @OneToMany(mappedBy = "damageType")
    private Set<NurseryInventoryDetails> nurseryInventoryDamageTypes = new HashSet<>();

    @OneToMany(mappedBy = "damageArea", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<Damage> pickListValueDamageAreas = new HashSet<>();

    @OneToMany(mappedBy = "saplingDamageArea", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<NurseryStockDetails> nurseryStockDamageAreas = new HashSet<>();

    @OneToMany(mappedBy = "financialYear", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<FinancialYearSettings> financialYearNames = new HashSet<>();

    @OneToMany(mappedBy = "description", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<Damage> damageDescriptions = new HashSet<>();

    @OneToMany(mappedBy = "pickListVariety", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<Quantity> quantitysVarieties = new HashSet<>();

    @OneToMany(mappedBy = "pickListCategory", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<Quantity> quantitysCategories = new HashSet<>();

    @OneToMany(mappedBy = "pickListVariety", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<PointOfSaleDetails> pointOfSaleVarietys = new HashSet<>();

    @OneToMany(mappedBy = "pickListCategory", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<PointOfSaleDetails> pointOfSaleCategorys = new HashSet<>();

    @OneToMany(mappedBy = "damageType", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<CoverFillingDetails> coverFillingDetails = new HashSet<>();

    @OneToMany(mappedBy = "inventoryDamageDescription", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<NurseryInventoryDetails> nurseryInventoryDamageDescs = new HashSet<>();

    @OneToMany(mappedBy = "coverFillingDamageDescription", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<CoverFillingDetails> coverFillingDamageDescs = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPickListValue() {
        return pickListValue;
    }

    public PickListValue pickListValue(String pickListValue) {
        this.pickListValue = pickListValue;
        return this;
    }

    public void setPickListValue(String pickListValue) {
        this.pickListValue = pickListValue;
    }

    public Integer getStatus() {
        return status;
    }

    public PickListValue status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<PickListValue> getSelfIds() {
        return selfIds;
    }

    public PickListValue selfIds(Set<PickListValue> pickListValues) {
        this.selfIds = pickListValues;
        return this;
    }

    public PickListValue addSelfIds(PickListValue pickListValue) {
        this.selfIds.add(pickListValue);
        pickListValue.setPickValue(this);
        return this;
    }

    public PickListValue removeSelfIds(PickListValue pickListValue) {
        this.selfIds.remove(pickListValue);
        pickListValue.setPickValue(null);
        return this;
    }

    public void setSelfIds(Set<PickListValue> pickListValues) {
        this.selfIds = pickListValues;
    }

    public Set<Batch> getVarietys() {
        return varietys;
    }

    public PickListValue varietys(Set<Batch> batches) {
        this.varietys = batches;
        return this;
    }

    public PickListValue addVarietys(Batch batch) {
        this.varietys.add(batch);
        batch.setPickListVariety(this);
        return this;
    }

    public PickListValue removeVarietys(Batch batch) {
        this.varietys.remove(batch);
        batch.setPickListVariety(null);
        return this;
    }

    public void setVarietys(Set<Batch> batches) {
        this.varietys = batches;
    }

    public Set<Batch> getCategorys() {
        return categorys;
    }

    public PickListValue categorys(Set<Batch> batches) {
        this.categorys = batches;
        return this;
    }

    public PickListValue addCategorys(Batch batch) {
        this.categorys.add(batch);
        batch.setPickListCategory(this);
        return this;
    }

    public PickListValue removeCategorys(Batch batch) {
        this.categorys.remove(batch);
        batch.setPickListCategory(null);
        return this;
    }

    public void setCategorys(Set<Batch> batches) {
        this.categorys = batches;
    }

    public Set<NurseryStock> getNurseryStockVarietys() {
        return nurseryStockVarietys;
    }

    public PickListValue nurseryStockVarietys(Set<NurseryStock> nurseryStocks) {
        this.nurseryStockVarietys = nurseryStocks;
        return this;
    }

    public PickListValue addNurseryStockVarietys(NurseryStock nurseryStock) {
        this.nurseryStockVarietys.add(nurseryStock);
        nurseryStock.setPickListVariety(this);
        return this;
    }

    public PickListValue removeNurseryStockVarietys(NurseryStock nurseryStock) {
        this.nurseryStockVarietys.remove(nurseryStock);
        nurseryStock.setPickListVariety(null);
        return this;
    }

    public void setNurseryStockVarietys(Set<NurseryStock> nurseryStocks) {
        this.nurseryStockVarietys = nurseryStocks;
    }

    public Set<NurseryStock> getNurseryStockCategorys() {
        return nurseryStockCategorys;
    }

    public PickListValue nurseryStockCategorys(Set<NurseryStock> nurseryStocks) {
        this.nurseryStockCategorys = nurseryStocks;
        return this;
    }

    public PickListValue addNurseryStockCategorys(NurseryStock nurseryStock) {
        this.nurseryStockCategorys.add(nurseryStock);
        nurseryStock.setPickListCategory(this);
        return this;
    }

    public PickListValue removeNurseryStockCategorys(NurseryStock nurseryStock) {
        this.nurseryStockCategorys.remove(nurseryStock);
        nurseryStock.setPickListCategory(null);
        return this;
    }

    public void setNurseryStockCategorys(Set<NurseryStock> nurseryStocks) {
        this.nurseryStockCategorys = nurseryStocks;
    }

    public Set<GodownPurchaseDetails> getGodownPurchaseVarietys() {
        return godownPurchaseVarietys;
    }

    public PickListValue godownPurchaseVarietys(Set<GodownPurchaseDetails> godownPurchaseDetails) {
        this.godownPurchaseVarietys = godownPurchaseDetails;
        return this;
    }

    public PickListValue addGodownPurchaseVarietys(GodownPurchaseDetails godownPurchaseDetails) {
        this.godownPurchaseVarietys.add(godownPurchaseDetails);
        godownPurchaseDetails.setPickListVariety(this);
        return this;
    }

    public PickListValue removeGodownPurchaseVarietys(GodownPurchaseDetails godownPurchaseDetails) {
        this.godownPurchaseVarietys.remove(godownPurchaseDetails);
        godownPurchaseDetails.setPickListVariety(null);
        return this;
    }

    public void setGodownPurchaseVarietys(Set<GodownPurchaseDetails> godownPurchaseDetails) {
        this.godownPurchaseVarietys = godownPurchaseDetails;
    }

    public Set<GodownPurchaseDetails> getGodownPurchaseCategorys() {
        return godownPurchaseCategorys;
    }

    public PickListValue godownPurchaseCategorys(Set<GodownPurchaseDetails> godownPurchaseDetails) {
        this.godownPurchaseCategorys = godownPurchaseDetails;
        return this;
    }

    public PickListValue addGodownPurchaseCategorys(GodownPurchaseDetails godownPurchaseDetails) {
        this.godownPurchaseCategorys.add(godownPurchaseDetails);
        godownPurchaseDetails.setPickListCategory(this);
        return this;
    }

    public PickListValue removeGodownPurchaseCategorys(GodownPurchaseDetails godownPurchaseDetails) {
        this.godownPurchaseCategorys.remove(godownPurchaseDetails);
        godownPurchaseDetails.setPickListCategory(null);
        return this;
    }

    public void setGodownPurchaseCategorys(Set<GodownPurchaseDetails> godownPurchaseDetails) {
        this.godownPurchaseCategorys = godownPurchaseDetails;
    }

    public Set<GodownPurchaseDetails> getGodownPurchaseQuantityTypes() {
        return godownPurchaseQuantityTypes;
    }

    public PickListValue godownPurchaseQuantityTypes(Set<GodownPurchaseDetails> godownPurchaseDetails) {
        this.godownPurchaseQuantityTypes = godownPurchaseDetails;
        return this;
    }

    public PickListValue addGodownPurchaseQuantityType(GodownPurchaseDetails godownPurchaseDetails) {
        this.godownPurchaseQuantityTypes.add(godownPurchaseDetails);
        godownPurchaseDetails.setPickListQuantityType(this);
        return this;
    }

    public PickListValue removeGodownPurchaseQuantityType(GodownPurchaseDetails godownPurchaseDetails) {
        this.godownPurchaseQuantityTypes.remove(godownPurchaseDetails);
        godownPurchaseDetails.setPickListQuantityType(null);
        return this;
    }

    public void setGodownPurchaseQuantityTypes(Set<GodownPurchaseDetails> godownPurchaseDetails) {
        this.godownPurchaseQuantityTypes = godownPurchaseDetails;
    }

    public Set<GodownStock> getGodownStockVarietys() {
        return godownStockVarietys;
    }

    public PickListValue godownStockVarietys(Set<GodownStock> godownStocks) {
        this.godownStockVarietys = godownStocks;
        return this;
    }

    public PickListValue addGodownStockVarietys(GodownStock godownStock) {
        this.godownStockVarietys.add(godownStock);
        godownStock.setPickListVariety(this);
        return this;
    }

    public PickListValue removeGodownStockVarietys(GodownStock godownStock) {
        this.godownStockVarietys.remove(godownStock);
        godownStock.setPickListVariety(null);
        return this;
    }

    public void setGodownStockVarietys(Set<GodownStock> godownStocks) {
        this.godownStockVarietys = godownStocks;
    }

    public Set<GodownStock> getGodownStockCategorys() {
        return godownStockCategorys;
    }

    public PickListValue godownStockCategorys(Set<GodownStock> godownStocks) {
        this.godownStockCategorys = godownStocks;
        return this;
    }

    public PickListValue addGodownStockCategorys(GodownStock godownStock) {
        this.godownStockCategorys.add(godownStock);
        godownStock.setPickListCategory(this);
        return this;
    }

    public PickListValue removeGodownStockCategorys(GodownStock godownStock) {
        this.godownStockCategorys.remove(godownStock);
        godownStock.setPickListCategory(null);
        return this;
    }

    public void setGodownStockCategorys(Set<GodownStock> godownStocks) {
        this.godownStockCategorys = godownStocks;
    }

    public Set<GodownStock> getGodownStockQuantityTypes() {
        return godownStockQuantityTypes;
    }

    public PickListValue godownStockQuantityTypes(Set<GodownStock> godownStocks) {
        this.godownStockQuantityTypes = godownStocks;
        return this;
    }

    public PickListValue addGodownStockQuantityTypes(GodownStock godownStock) {
        this.godownStockQuantityTypes.add(godownStock);
        godownStock.setPickListQuantityType(this);
        return this;
    }

    public PickListValue removeGodownStockQuantityTypes(GodownStock godownStock) {
        this.godownStockQuantityTypes.remove(godownStock);
        godownStock.setPickListQuantityType(null);
        return this;
    }

    public void setGodownStockQuantityTypes(Set<GodownStock> godownStocks) {
        this.godownStockQuantityTypes = godownStocks;
    }

    public PickList getPickList() {
        return pickList;
    }

    public PickListValue pickList(PickList pickList) {
        this.pickList = pickList;
        return this;
    }

    public void setPickList(PickList pickList) {
        this.pickList = pickList;
    }

    public PickListValue getPickValue() {
        return pickValue;
    }

    public PickListValue pickValue(PickListValue pickListValue) {
        this.pickValue = pickListValue;
        return this;
    }

    public void setPickValue(PickListValue pickListValue) {
        this.pickValue = pickListValue;
    }

    public Set<Quantity> getQuantitysVarieties() {
        return quantitysVarieties;
    }

    public PickListValue quantitysVarieties(Set<Quantity> quantities) {
        this.quantitysVarieties = quantities;
        return this;
    }

    public PickListValue addQuantitysVariety(Quantity quantity) {
        this.quantitysVarieties.add(quantity);
        quantity.setPickListVariety(this);
        return this;
    }

    public PickListValue removeQuantitysVariety(Quantity quantity) {
        this.quantitysVarieties.remove(quantity);
        quantity.setPickListVariety(null);
        return this;
    }

    public void setQuantitysVarieties(Set<Quantity> quantities) {
        this.quantitysVarieties = quantities;
    }

    public Set<Quantity> getQuantitysCategories() {
        return quantitysCategories;
    }

    public PickListValue quantitysCategories(Set<Quantity> quantities) {
        this.quantitysCategories = quantities;
        return this;
    }

    public PickListValue addQuantitysCategory(Quantity quantity) {
        this.quantitysCategories.add(quantity);
        quantity.setPickListCategory(this);
        return this;
    }

    public PickListValue removeQuantitysCategory(Quantity quantity) {
        this.quantitysCategories.remove(quantity);
        quantity.setPickListCategory(null);
        return this;
    }

    public void setQuantitysCategories(Set<Quantity> quantities) {
        this.quantitysCategories = quantities;
    }

    public Set<Nursery> getNurserys() {
        return nurserys;
    }

    public PickListValue nurserys(Set<Nursery> nurseries) {
        this.nurserys = nurseries;
        return this;
    }

    public PickListValue addNurserys(Nursery nursery) {
        this.nurserys.add(nursery);
        nursery.setNurseryType(this);
        return this;
    }

    public PickListValue removeNurserys(Nursery nursery) {
        this.nurserys.remove(nursery);
        nursery.setNurseryType(null);
        return this;
    }

    public void setNurserys(Set<Nursery> nurseries) {
        this.nurserys = nurseries;
    }

    public Set<Batch> getBatchQuantityTypes() {
        return batchQuantityTypes;
    }

    public PickListValue batchQuantityTypes(Set<Batch> batches) {
        this.batchQuantityTypes = batches;
        return this;
    }

    public PickListValue addBatchQuantityTypes(Batch batch) {
        this.batchQuantityTypes.add(batch);
        batch.setQuantityType(this);
        return this;
    }

    public PickListValue removeBatchQuantityTypes(Batch batch) {
        this.batchQuantityTypes.remove(batch);
        batch.setQuantityType(null);
        return this;
    }

    public void setBatchQuantityTypes(Set<Batch> batches) {
        this.batchQuantityTypes = batches;
    }

    public Set<NurseryInventory> getNurseryInventoryVarietys() {
        return nurseryInventoryVarietys;
    }

    public PickListValue nurseryInventoryVarietys(Set<NurseryInventory> nurseryInventories) {
        this.nurseryInventoryVarietys = nurseryInventories;
        return this;
    }

    public PickListValue addNurseryInventoryVarietys(NurseryInventory nurseryInventory) {
        this.nurseryInventoryVarietys.add(nurseryInventory);
        nurseryInventory.setPickListVariety(this);
        return this;
    }

    public PickListValue removeNurseryInventoryVarietys(NurseryInventory nurseryInventory) {
        this.nurseryInventoryVarietys.remove(nurseryInventory);
        nurseryInventory.setPickListVariety(null);
        return this;
    }

    public void setNurseryInventoryVarietys(Set<NurseryInventory> nurseryInventories) {
        this.nurseryInventoryVarietys = nurseryInventories;
    }

    public Set<NurseryInventory> getNurseryInventoryCategorys() {
        return nurseryInventoryCategorys;
    }

    public PickListValue nurseryInventoryCategorys(Set<NurseryInventory> nurseryInventories) {
        this.nurseryInventoryCategorys = nurseryInventories;
        return this;
    }

    public PickListValue addNurseryInventoryCategorys(NurseryInventory nurseryInventory) {
        this.nurseryInventoryCategorys.add(nurseryInventory);
        nurseryInventory.setPickListCategory(this);
        return this;
    }

    public PickListValue removeNurseryInventoryCategorys(NurseryInventory nurseryInventory) {
        this.nurseryInventoryCategorys.remove(nurseryInventory);
        nurseryInventory.setPickListCategory(null);
        return this;
    }

    public void setNurseryInventoryCategorys(Set<NurseryInventory> nurseryInventories) {
        this.nurseryInventoryCategorys = nurseryInventories;
    }

    public Set<NurseryInventory> getNurseryInventoryQuantityTypes() {
        return nurseryInventoryQuantityTypes;
    }

    public PickListValue nurseryInventoryQuantityTypes(Set<NurseryInventory> nurseryInventories) {
        this.nurseryInventoryQuantityTypes = nurseryInventories;
        return this;
    }

    public PickListValue addNurseryInventoryQuantityTypes(NurseryInventory nurseryInventory) {
        this.nurseryInventoryQuantityTypes.add(nurseryInventory);
        nurseryInventory.setQuantityType(this);
        return this;
    }

    public PickListValue removeNurseryInventoryQuantityTypes(NurseryInventory nurseryInventory) {
        this.nurseryInventoryQuantityTypes.remove(nurseryInventory);
        nurseryInventory.setQuantityType(null);
        return this;
    }

    public void setNurseryInventoryQuantityTypes(Set<NurseryInventory> nurseryInventories) {
        this.nurseryInventoryQuantityTypes = nurseryInventories;
    }

    public Set<NurseryInventoryDetails> getNurseryInventoryDamageTypes() {
        return nurseryInventoryDamageTypes;
    }

    public PickListValue nurseryInventoryDamageTypes(Set<NurseryInventoryDetails> nurseryInventoryDetails) {
        this.nurseryInventoryDamageTypes = nurseryInventoryDetails;
        return this;
    }

    public PickListValue addNurseryInventoryDamageTypes(NurseryInventoryDetails nurseryInventoryDetails) {
        this.nurseryInventoryDamageTypes.add(nurseryInventoryDetails);
        nurseryInventoryDetails.setDamageType(this);
        return this;
    }

    public PickListValue removeNurseryInventoryDamageTypes(NurseryInventoryDetails nurseryInventoryDetails) {
        this.nurseryInventoryDamageTypes.remove(nurseryInventoryDetails);
        nurseryInventoryDetails.setDamageType(null);
        return this;
    }

    public void setNurseryInventoryDamageTypes(Set<NurseryInventoryDetails> nurseryInventoryDetails) {
        this.nurseryInventoryDamageTypes = nurseryInventoryDetails;
    }

    public Set<Damage> getPickListValueDamageAreas() {
        return pickListValueDamageAreas;
    }

    public PickListValue pickListValueDamageAreas(Set<Damage> damages) {
        this.pickListValueDamageAreas = damages;
        return this;
    }

    public PickListValue addPickListValueDamageArea(Damage damage) {
        this.pickListValueDamageAreas.add(damage);
        damage.setDamageArea(this);
        return this;
    }

    public PickListValue removePickListValueDamageArea(Damage damage) {
        this.pickListValueDamageAreas.remove(damage);
        damage.setDamageArea(null);
        return this;
    }

    public void setPickListValueDamageAreas(Set<Damage> damages) {
        this.pickListValueDamageAreas = damages;
    }

    public Set<NurseryStockDetails> getNurseryStockDamageAreas() {
        return nurseryStockDamageAreas;
    }

    public PickListValue nurseryStockDamageAreas(Set<NurseryStockDetails> nurseryStockDetails) {
        this.nurseryStockDamageAreas = nurseryStockDetails;
        return this;
    }

    public PickListValue addNurseryStockDamageArea(NurseryStockDetails nurseryStockDetails) {
        this.nurseryStockDamageAreas.add(nurseryStockDetails);
        nurseryStockDetails.setSaplingDamageArea(this);
        return this;
    }

    public PickListValue removeNurseryStockDamageArea(NurseryStockDetails nurseryStockDetails) {
        this.nurseryStockDamageAreas.remove(nurseryStockDetails);
        nurseryStockDetails.setSaplingDamageArea(null);
        return this;
    }

    public void setNurseryStockDamageAreas(Set<NurseryStockDetails> nurseryStockDetails) {
        this.nurseryStockDamageAreas = nurseryStockDetails;
    }

    public Set<FinancialYearSettings> getFinancialYearNames() {
        return financialYearNames;
    }

    public PickListValue financialYearNames(Set<FinancialYearSettings> financialYearSettings) {
        this.financialYearNames = financialYearSettings;
        return this;
    }

    public PickListValue addFinancialYearName(FinancialYearSettings financialYearSettings) {
        this.financialYearNames.add(financialYearSettings);
        financialYearSettings.setFinancialYear(this);
        return this;
    }

    public PickListValue removeFinancialYearName(FinancialYearSettings financialYearSettings) {
        this.financialYearNames.remove(financialYearSettings);
        financialYearSettings.setFinancialYear(null);
        return this;
    }

    public void setFinancialYearNames(Set<FinancialYearSettings> financialYearSettings) {
        this.financialYearNames = financialYearSettings;
    }

    public Set<Damage> getDamageDescriptions() {
        return damageDescriptions;
    }

    public PickListValue damageDescriptions(Set<Damage> damages) {
        this.damageDescriptions = damages;
        return this;
    }

    public PickListValue addDamageDescription(Damage damage) {
        this.damageDescriptions.add(damage);
        damage.setDescription(this);
        return this;
    }

    public PickListValue removeDamageDescription(Damage damage) {
        this.damageDescriptions.remove(damage);
        damage.setDescription(null);
        return this;
    }

    public void setDamageDescriptions(Set<Damage> damages) {
        this.damageDescriptions = damages;
    }

    public Set<PointOfSaleDetails> getPointOfSaleVarietys() {
        return pointOfSaleVarietys;
    }

    public PickListValue pointOfSaleVarietys(Set<PointOfSaleDetails> pointOfSaleDetails) {
        this.pointOfSaleVarietys = pointOfSaleDetails;
        return this;
    }

    public PickListValue addPointOfSaleVarietys(PointOfSaleDetails pointOfSaleDetails) {
        this.pointOfSaleVarietys.add(pointOfSaleDetails);
        pointOfSaleDetails.setPickListVariety(this);
        return this;
    }

    public PickListValue removePointOfSaleVarietys(PointOfSaleDetails pointOfSaleDetails) {
        this.pointOfSaleVarietys.remove(pointOfSaleDetails);
        pointOfSaleDetails.setPickListVariety(null);
        return this;
    }

    public void setPointOfSaleVarietys(Set<PointOfSaleDetails> pointOfSaleDetails) {
        this.pointOfSaleVarietys = pointOfSaleDetails;
    }

    public Set<PointOfSaleDetails> getPointOfSaleCategorys() {
        return pointOfSaleCategorys;
    }

    public PickListValue pointOfSaleCategorys(Set<PointOfSaleDetails> pointOfSaleDetails) {
        this.pointOfSaleCategorys = pointOfSaleDetails;
        return this;
    }

    public PickListValue addPointOfSaleCategorys(PointOfSaleDetails pointOfSaleDetails) {
        this.pointOfSaleCategorys.add(pointOfSaleDetails);
        pointOfSaleDetails.setPickListCategory(this);
        return this;
    }

    public PickListValue removePointOfSaleCategorys(PointOfSaleDetails pointOfSaleDetails) {
        this.pointOfSaleCategorys.remove(pointOfSaleDetails);
        pointOfSaleDetails.setPickListCategory(null);
        return this;
    }

    public void setPointOfSaleCategorys(Set<PointOfSaleDetails> pointOfSaleDetails) {
        this.pointOfSaleCategorys = pointOfSaleDetails;
    }

    public Set<CoverFillingDetails> getCoverFillingDetails() {
        return coverFillingDetails;
    }

    public PickListValue coverFillingDetails(Set<CoverFillingDetails> coverFillingDetails) {
        this.coverFillingDetails = coverFillingDetails;
        return this;
    }

    public PickListValue addCoverFillingDetails(CoverFillingDetails coverFillingDetails) {
        this.coverFillingDetails.add(coverFillingDetails);
        coverFillingDetails.setDamageType(this);
        return this;
    }

    public PickListValue removeCoverFillingDetails(CoverFillingDetails coverFillingDetails) {
        this.coverFillingDetails.remove(coverFillingDetails);
        coverFillingDetails.setDamageType(null);
        return this;
    }

    public void setCoverFillingDetails(Set<CoverFillingDetails> coverFillingDetails) {
        this.coverFillingDetails = coverFillingDetails;
    }

    public Set<NurseryInventoryDetails> getNurseryInventoryDamageDescs() {
        return nurseryInventoryDamageDescs;
    }

    public PickListValue nurseryInventoryDamageDescs(Set<NurseryInventoryDetails> nurseryInventoryDetails) {
        this.nurseryInventoryDamageDescs = nurseryInventoryDetails;
        return this;
    }

    public PickListValue addNurseryInventoryDamageDesc(NurseryInventoryDetails nurseryInventoryDetails) {
        this.nurseryInventoryDamageDescs.add(nurseryInventoryDetails);
        nurseryInventoryDetails.setInventoryDamageDescription(this);
        return this;
    }

    public PickListValue removeNurseryInventoryDamageDesc(NurseryInventoryDetails nurseryInventoryDetails) {
        this.nurseryInventoryDamageDescs.remove(nurseryInventoryDetails);
        nurseryInventoryDetails.setInventoryDamageDescription(null);
        return this;
    }

    public void setNurseryInventoryDamageDescs(Set<NurseryInventoryDetails> nurseryInventoryDetails) {
        this.nurseryInventoryDamageDescs = nurseryInventoryDetails;
    }

    public Set<CoverFillingDetails> getCoverFillingDamageDescs() {
        return coverFillingDamageDescs;
    }

    public PickListValue coverFillingDamageDescs(Set<CoverFillingDetails> coverFillingDetails) {
        this.coverFillingDamageDescs = coverFillingDetails;
        return this;
    }

    public PickListValue addCoverFillingDamageDesc(CoverFillingDetails coverFillingDetails) {
        this.coverFillingDamageDescs.add(coverFillingDetails);
        coverFillingDetails.setCoverFillingDamageDescription(this);
        return this;
    }

    public PickListValue removeCoverFillingDamageDesc(CoverFillingDetails coverFillingDetails) {
        this.coverFillingDamageDescs.remove(coverFillingDetails);
        coverFillingDetails.setCoverFillingDamageDescription(null);
        return this;
    }

    public void setCoverFillingDamageDescs(Set<CoverFillingDetails> coverFillingDetails) {
        this.coverFillingDamageDescs = coverFillingDetails;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

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

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PickListValue{" +
            "id=" + getId() +
            ", pickListValue='" + getPickListValue() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
