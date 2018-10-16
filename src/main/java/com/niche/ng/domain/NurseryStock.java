/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryStock Generation.
 *  Declared the table fields and data types for the NurseryStock table.
 *  Defined the following Relation for the NurseryStock Table :
 *  OneToMany Relation  : NurseryStockDetails, PointOfSaleDetails Table
 *  ManyToOne Relation  : Nursery, PickListValue and FinancialYearSettings
 *
 *******************************************************************************/
package com.niche.ng.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

 /**
 * NurseryStock Domain Class
 * 
 * NurseryStock class implements Serializable to convert instance class into 
 * series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
@Entity
@Table(name = "nursery_stock")
public class NurseryStock extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "current_quantity")
    private Long currentQuantity;

    @Column(name = "added_quantity")
    private Long addedQuantity;

    @Column(name = "consumed_quantity")
    private Long consumedQuantity;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Integer status;

    @Column(name = "pos_quantity")
    private Integer posQuantity;

    @Column(name = "damage_quantity")
    private Long damageQuantity;

    /**
     * Relation Name : OneToMany - nurseryStockDetails
     * Table Name    : Connects the NurseryStock Table to NurseryStockDetails Table
     * After Creation of the NurseryStock, If any movement it affect in NurseryStockDetails Table
     */
    @OneToMany(mappedBy = "nurseryStock", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<NurseryStockDetails> nurseryStockDetails = new HashSet<>();

    /**
     * Relation Name : ManyToOne - nursery
     * Table Name    : Connects the NurseryStock Table to Nursery Table
     * To get the nursery.
     */
    @ManyToOne
    @JsonIgnoreProperties("nurseryStocks")
    @JoinColumn(name="nursery_id", referencedColumnName="id")
    private Nursery nursery;

    /**
     * Relation Name : ManyToOne - pickListVariety
     * Table Name    : Connects the NurseryStock Table to PickListValue Table
     * To get the pickListVariety.
     */
    @ManyToOne
    @JsonIgnoreProperties("nurseryStockVarietys")
    @JoinColumn(name="pick_list_variety_id", referencedColumnName="id")
    private PickListValue pickListVariety;

    /**
     * Relation Name : ManyToOne - pickListCategory
     * Table Name    : Connects the NurseryStock Table to PickListValue Table
     * To get the pickListCategory.
     */
    @ManyToOne
    @JsonIgnoreProperties("nurseryStockCategorys")
    @JoinColumn(name="pick_list_category_id", referencedColumnName="id")
    private PickListValue pickListCategory;

    /**
     * Relation Name : ManyToOne - financialYearNurseryStock
     * Table Name    : Connects the NurseryStock Table to FinancialYearSettings Table
     * To get the financialYearNurseryStock.
     */
    @ManyToOne
    @JsonIgnoreProperties("nurseryStocks")
    private FinancialYearSettings financialYearNurseryStock;

    /**
     * Relation Name : OneToMany - pointOfSaleDetails
     * Table Name    : Connects the NurseryStock Table to PointOfSaleDetails Table
     * After Creation of the NurseryStock, If any movement it affect in PointOfSaleDetails Table
     */
    @OneToMany(mappedBy = "nurseryStock", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<PointOfSaleDetails> pointOfSaleDetails = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    /**
     * To Get the Id from nurseryStocks table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To Set the Id for nurseryStocks table
     *
     * @param id the id in nurseryStocks table
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the currentQuantity from nurseryStocks table
     * 
     * @return currentQuantity
     */
    public Long getCurrentQuantity() {
        return currentQuantity;
    }

    /**
     * To Set the currentQuantity for nurseryStocks table
     *
     * @param currentQuantity the currentQuantity in nurseryStocks table
     * @return this
     */
    public NurseryStock currentQuantity(Long currentQuantity) {
        this.currentQuantity = currentQuantity;
        return this;
    }

    /**
     * To Set the currentQuantity for nurseryStocks table
     *
     * @param currentQuantity the currentQuantity in nurseryStocks table
     */
    public void setCurrentQuantity(Long currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    /**
     * To Get the addedQuantity from nurseryStocks table
     * 
     * @return addedQuantity
     */
    public Long getAddedQuantity() {
        return addedQuantity;
    }

    /**
     * To Set the addedQuantity for nurseryStocks table
     *
     * @param addedQuantity the addedQuantity in nurseryStocks table
     * @return this
     */
    public NurseryStock addedQuantity(Long addedQuantity) {
        this.addedQuantity = addedQuantity;
        return this;
    }

    /**
     * To Set the addedQuantity for nurseryStocks table
     *
     * @param addedQuantity the addedQuantity in nurseryStocks table
     */
    public void setAddedQuantity(Long addedQuantity) {
        this.addedQuantity = addedQuantity;
    }

    /**
     * To Get the consumedQuantity from nurseryStocks table
     * 
     * @return consumedQuantity
     */
    public Long getConsumedQuantity() {
        return consumedQuantity;
    }

    /**
     * To Set the consumedQuantity for nurseryStocks table
     *
     * @param consumedQuantity the consumedQuantity in nurseryStocks table
     * @return this
     */
    public NurseryStock consumedQuantity(Long consumedQuantity) {
        this.consumedQuantity = consumedQuantity;
        return this;
    }

    /**
     * To Set the consumedQuantity for nurseryStocks table
     *
     * @param consumedQuantity the consumedQuantity in nurseryStocks table
     */
    public void setConsumedQuantity(Long consumedQuantity) {
        this.consumedQuantity = consumedQuantity;
    }

    /**
     * To Get the description from nurseryStocks table
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * To Set the description for nurseryStocks table
     *
     * @param description the description in nurseryStocks table
     * @return this
     */
    public NurseryStock description(String description) {
        this.description = description;
        return this;
    }

    /**
     * To Set the description for nurseryStocks table
     *
     * @param description the description in nurseryStocks table
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * To Get the status from nurseryStocks table
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To Set the status for nurseryStocks table
     *
     * @param status the status in nurseryStocks table
     * @return this
     */
    public NurseryStock status(Integer status) {
        this.status = status;
        return this;
    }

    /**
     * To Set the status for nurseryStocks table
     *
     * @param status the status in nurseryStocks table
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the posQuantity from nurseryStocks table
     * 
     * @return posQuantity
     */
    public Integer getPosQuantity() {
        return posQuantity;
    }

    /**
     * To Set the posQuantity for nurseryStocks table
     *
     * @param posQuantity the posQuantity in nurseryStocks table
     * @return this
     */
    public NurseryStock posQuantity(Integer posQuantity) {
        this.posQuantity = posQuantity;
        return this;
    }

    /**
     * To Set the posQuantity for nurseryStocks table
     *
     * @param posQuantity the posQuantity in nurseryStocks table
     */
    public void setPosQuantity(Integer posQuantity) {
        this.posQuantity = posQuantity;
    }

    /**
     * To Get the damageQuantity from nurseryStocks table
     * 
     * @return damageQuantity
     */
    public Long getDamageQuantity() {
        return damageQuantity;
    }

    /**
     * To Set the damageQuantity for nurseryStocks table
     *
     * @param damageQuantity the damageQuantity in nurseryStocks table
     * @return this
     */
    public NurseryStock damageQuantity(Long damageQuantity) {
        this.damageQuantity = damageQuantity;
        return this;
    }

    /**
     * To Set the damageQuantity for nurseryStocks table
     *
     * @param damageQuantity the damageQuantity in nurseryStocks table
     */
    public void setDamageQuantity(Long damageQuantity) {
        this.damageQuantity = damageQuantity;
    }


    /**
     * To Get the nurseryStockDetails from nurseryStocks table
     * 
     * @return nurseryStockDetails
     */
    public Set<NurseryStockDetails> getNurseryStockDetails() {
        return nurseryStockDetails;
    }

    /**
     * To Set the nurseryStockDetails for nurseryStocks table
     *
     * @param nurseryStockDetails the nurseryStockDetails in nurseryStocks table
     * @return this
     */
    public NurseryStock nurseryStockDetails(Set<NurseryStockDetails> nurseryStockDetails) {
        this.nurseryStockDetails = nurseryStockDetails;
        return this;
    }

    /**
     * To set the add nurseryStockDetails for the nurseryStocks table.
     * 
     * @param nurseryStockDetails the nurseryStockDetails in nurseryStocks
     * @return this
     */
    public NurseryStock addNurseryStockDetails(NurseryStockDetails nurseryStockDetails) {
        this.nurseryStockDetails.add(nurseryStockDetails);
        nurseryStockDetails.setNurseryStock(this);
        return this;
    }

    /**
     * To set the remove nurseryStockDetails for the nurseryStocks table.
     * 
     * @param nurseryStockDetails the nurseryStockDetails in nurseryStocks
     * @return this
     */
    public NurseryStock removeNurseryStockDetails(NurseryStockDetails nurseryStockDetails) {
        this.nurseryStockDetails.remove(nurseryStockDetails);
        nurseryStockDetails.setNurseryStock(null);
        return this;
    }

    /**
     * To Set the nurseryStockDetails for nurseryStocks table
     *
     * @param nurseryStockDetails the nurseryStockDetails in nurseryStocks table
     */
    public void setNurseryStockDetails(Set<NurseryStockDetails> nurseryStockDetails) {
        this.nurseryStockDetails = nurseryStockDetails;
    }

    /**
     * To Get the nursery from nurseryStocks table
     * 
     * @return nursery
     */
    public Nursery getNursery() {
        return nursery;
    }

    /**
     * To Set the nursery for nurseryStocks table
     *
     * @param nursery the nursery in nurseryStocks table
     * @return this
     */
    public NurseryStock nursery(Nursery nursery) {
        this.nursery = nursery;
        return this;
    }

    /**
     * To Set the nursery for nurseryStocks table
     *
     * @param nursery the nursery in nurseryStocks table
     */
    public void setNursery(Nursery nursery) {
        this.nursery = nursery;
    }

    /**
     * To Get the pickListVariety from nurseryStocks table
     * 
     * @return pickListVariety
     */
    public PickListValue getPickListVariety() {
        return pickListVariety;
    }

    /**
     * To Set the pickListVariety for nurseryStocks table
     *
     * @param pickListValue the pickListValue in nurseryStocks table
     * @return this
     */
    public NurseryStock pickListVariety(PickListValue pickListValue) {
        this.pickListVariety = pickListValue;
        return this;
    }

    /**
     * To Set the pickListVariety for nurseryStocks table
     *
     * @param pickListValue the pickListValue in nurseryStocks table
     */
    public void setPickListVariety(PickListValue pickListValue) {
        this.pickListVariety = pickListValue;
    }

    /**
     * To Get the pickListCategory from nurseryStocks table
     * 
     * @return pickListCategory
     */
    public PickListValue getPickListCategory() {
        return pickListCategory;
    }

    /**
     * To Set the pickListCategory for nurseryStocks table
     *
     * @param pickListValue the pickListValue in nurseryStocks table
     * @return this
     */
    public NurseryStock pickListCategory(PickListValue pickListValue) {
        this.pickListCategory = pickListValue;
        return this;
    }

    /**
     * To Set the pickListCategory for nurseryStocks table
     *
     * @param pickListValue the pickListValue in nurseryStocks table
     */
    public void setPickListCategory(PickListValue pickListValue) {
        this.pickListCategory = pickListValue;
    }

    /**
     * To Get the financialYearNurseryStock from nurseryStocks table
     * 
     * @return financialYearNurseryStock
     */
    public FinancialYearSettings getFinancialYearNurseryStock() {
        return financialYearNurseryStock;
    }

    /**
     * To Set the financialYearNurseryStock for nurseryStocks table
     *
     * @param financialYearSettings the financialYearSettings in nurseryStocks table
     * @return this
     */
    public NurseryStock financialYearNurseryStock(FinancialYearSettings financialYearSettings) {
        this.financialYearNurseryStock = financialYearSettings;
        return this;
    }

    /**
     * To Set the financialYearNurseryStock for nurseryStocks table
     *
     * @param financialYearSettings the financialYearSettings in nurseryStocks table
     */
    public void setFinancialYearNurseryStock(FinancialYearSettings financialYearSettings) {
        this.financialYearNurseryStock = financialYearSettings;
    }

    /**
     * To Get the pointOfSaleDetails from nurseryStocks table
     * 
     * @return pointOfSaleDetails
     */
    public Set<PointOfSaleDetails> getPointOfSaleDetails() {
        return pointOfSaleDetails;
    }

    /**
     * To Set the pointOfSaleDetails for nurseryStocks table
     *
     * @param pointOfSaleDetails the pointOfSaleDetails in nurseryStocks table
     * @return this
     */
    public NurseryStock pointOfSaleDetails(Set<PointOfSaleDetails> pointOfSaleDetails) {
        this.pointOfSaleDetails = pointOfSaleDetails;
        return this;
    }

    /**
     * To set the add pointOfSaleDetails for the nurseryStocks table.
     * 
     * @param pointOfSaleDetails the pointOfSaleDetails in nurseryStocks
     * @return this
     */
    public NurseryStock addPointOfSaleDetails(PointOfSaleDetails pointOfSaleDetails) {
        this.pointOfSaleDetails.add(pointOfSaleDetails);
        pointOfSaleDetails.setNurseryStock(this);
        return this;
    }

    /**
     * To set the remove pointOfSaleDetails for the nurseryStocks table.
     * 
     * @param pointOfSaleDetails the pointOfSaleDetails in nurseryStocks
     * @return this
     */
    public NurseryStock removePointOfSaleDetails(PointOfSaleDetails pointOfSaleDetails) {
        this.pointOfSaleDetails.remove(pointOfSaleDetails);
        pointOfSaleDetails.setNurseryStock(null);
        return this;
    }

    /**
     * To Set the pointOfSaleDetails for nurseryStocks table
     *
     * @param pointOfSaleDetails the pointOfSaleDetails in nurseryStocks table
     */
    public void setPointOfSaleDetails(Set<PointOfSaleDetails> pointOfSaleDetails) {
        this.pointOfSaleDetails = pointOfSaleDetails;
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
        NurseryStock nurseryStock = (NurseryStock) o;
        if (nurseryStock.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nurseryStock.getId());
    }

    /**
     * hash code for the id
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
        return "NurseryStock{" +
            "id=" + getId() +
            ", currentQuantity=" + getCurrentQuantity() +
            ", addedQuantity=" + getAddedQuantity() +
            ", consumedQuantity=" + getConsumedQuantity() +
            ", description='" + getDescription() + "'" +
            ", status=" + getStatus() +
            ", posQuantity=" + getPosQuantity() +
            ", damageQuantity=" + getDamageQuantity() +
            "}";
    }
}
