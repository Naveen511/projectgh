/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs GodownStock  Generation 
 *                       and declared the table fields, data types for 
 *                       GodownStock table.
 *  Relation for GodownStock : ManyToOne Relation, OneToMany Relation
 *  OneToMany Relation : GodownStockDetails Table
 *  ManyToOne Relation : Godown, PickList Variety, and Picklist Category,
 *                       FinancialYearSettings
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
 * GodownStock Domain Class
 * 
 * GodownStock class implements Serializable to convert instance class into 
 * series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
@Entity
@Table(name = "godown_stock")
public class GodownStock extends AbstractAuditingEntity implements Serializable {

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

    /**
     * Relation Name : OneToMany - godownStockDetails
     * Table Name    : Connect the GodownStock Table to GodownStockDetails Table
     * Used to point out the GodownStock values in the GodownStockDetails table.
     */
    @OneToMany(mappedBy = "godownStock", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<GodownStockDetails> godownStockDetails = new HashSet<>();

    /**
     * Relation Name : ManyToOne - godownStockVarietys
     * Table Name    : Connect the GodownStock Table to PickListValue Table.
     * Used to point out the GodownStock values in the PickListValue table.
     */
    @ManyToOne
    @JsonIgnoreProperties("godownStockVarietys")
    private PickListValue pickListVariety;

    /**
     * Relation Name : ManyToOne - godownStockCategorys
     * Table Name    : Connect the GodownStock Table to PickListValue Table.
     * Used to point out the GodownStock values in the PickListValue table.
     */
    @ManyToOne
    @JsonIgnoreProperties("godownStockCategorys")
    private PickListValue pickListCategory;

    /**
     * Relation Name : ManyToOne - godownStockQuantityTypes
     * Table Name    : Connect the GodownStock Table to PickListValue Table.
     * Used to point out the GodownStock values in the PickListValue table.
     */
    @ManyToOne
    @JsonIgnoreProperties("godownStockQuantityTypes")
    private PickListValue pickListQuantityType;

    /**
     * Relation Name : ManyToOne - godownStocks
     * Table Name    : Connect the GodownStock Table to Godown Table.
     * Used to point out the GodownStock values in the Godown table.
     */
    @ManyToOne
    @JsonIgnoreProperties("godownStocks")
    private Godown godown;

    /**
     * Relation Name : ManyToOne - godownStocks
     * Table Name    : Connect the GodownStock Table to FinancialYearSettings Table.
     * Used to point out the GodownStock values in the FinancialYearSettings table.
     */
    @ManyToOne
    @JsonIgnoreProperties("godownStocks")
    private FinancialYearSettings financialYearGodownStock;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    /**
     * To Get the Id from GodownStock table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To set the Id from GodownStock table
     * 
     * @return id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the currentQuantity from GodownStock table
     * 
     * @return currentQuantity 
     */
    public Long getCurrentQuantity() {
        return currentQuantity;
    }

    /**
     * To set the currentQuantity values in the GodownStock table.
     * 
     * @param currentQuantity currentQuantity of the GodownStock
     * @return this
     */
    public GodownStock currentQuantity(Long currentQuantity) {
        this.currentQuantity = currentQuantity;
        return this;
    }

    /**
     * To Set the currentQuantity for GodownStock table
     * 
     * @param currentQuantity currentQuantity value
     */
    public void setCurrentQuantity(Long currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    /**
     * To Get the addedQuantity from GodownStock table
     * 
     * @return addedQuantity 
     */
    public Long getAddedQuantity() {
        return addedQuantity;
    }

    /**
     * To set the addedQuantity values in the GodownStock table.
     * 
     * @param addedQuantity addedQuantity of the GodownStock
     * @return this
     */
    public GodownStock addedQuantity(Long addedQuantity) {
        this.addedQuantity = addedQuantity;
        return this;
    }

    /**
     * To Set the addedQuantity for GodownStock table
     * 
     * @param addedQuantity addedQuantity value
     */
    public void setAddedQuantity(Long addedQuantity) {
        this.addedQuantity = addedQuantity;
    }

    /**
     * To Get the consumedQuantity from GodownStock table
     * 
     * @return consumedQuantity 
     */
    public Long getConsumedQuantity() {
        return consumedQuantity;
    }

    /**
     * To set the consumedQuantity values in the GodownStock table.
     * 
     * @param consumedQuantity consumedQuantity of the GodownStock
     * @return this
     */
    public GodownStock consumedQuantity(Long consumedQuantity) {
        this.consumedQuantity = consumedQuantity;
        return this;
    }

    /**
     * To Set the consumedQuantity for GodownStock table
     * 
     * @param consumedQuantity consumedQuantity value
     */
    public void setConsumedQuantity(Long consumedQuantity) {
        this.consumedQuantity = consumedQuantity;
    }

    /**
     * To Get the description from GodownStock table
     * 
     * @return description 
     */
    public String getDescription() {
        return description;
    }

    /**
     * To set the description values in the GodownStock table.
     * 
     * @param description description of the GodownStock
     * @return this
     */
    public GodownStock description(String description) {
        this.description = description;
        return this;
    }

    /**
     * To Set the description for GodownStock table
     * 
     * @param description description value
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * To Get the status from GodownStock table
     * 
     * @return status 
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To set the status values in the GodownStock table.
     * 
     * @param status status of the GodownStock
     * @return this
     */
    public GodownStock status(Integer status) {
        this.status = status;
        return this;
    }

    /**
     * To Set the status for GodownStock table
     * 
     * @param status status value
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the godownStockDetails from GodownStock table
     * 
     * @return godownStockDetails 
     */
    public Set<GodownStockDetails> getGodownStockDetails() {
        return godownStockDetails;
    }

    /**
     * To set the godownStockDetails values in the GodownStock table.
     * 
     * @param godownStockDetails godownStockDetails of the GodownStock
     * @return this
     */
    public GodownStock godownStockDetails(Set<GodownStockDetails> godownStockDetails) {
        this.godownStockDetails = godownStockDetails;
        return this;
    }

    /**
     * To set the godownStockDetails values in the GodownStock table.
     * 
     * @param godownStockDetails godownStockDetails of the GodownStock
     * @return this
     */
    public GodownStock addGodownStockDetails(GodownStockDetails godownStockDetails) {
        this.godownStockDetails.add(godownStockDetails);
        godownStockDetails.setGodownStock(this);
        return this;
    }

    /**
     * To set the godownStockDetails values in the GodownStock table.
     * 
     * @param godownStockDetails godownStockDetails of the GodownStock
     * @return this
     */
    public GodownStock removeGodownStockDetails(GodownStockDetails godownStockDetails) {
        this.godownStockDetails.remove(godownStockDetails);
        godownStockDetails.setGodownStock(null);
        return this;
    }

    /**
     * To Set the godownStockDetails for GodownStock table
     * 
     * @param godownStockDetails godownStockDetails value
     */
    public void setGodownStockDetails(Set<GodownStockDetails> godownStockDetails) {
        this.godownStockDetails = godownStockDetails;
    }

    /**
     * To Get the pickListVariety from GodownStock table
     * 
     * @return pickListVariety 
     */
    public PickListValue getPickListVariety() {
        return pickListVariety;
    }

    /**
     * To set the pickListValue values in the GodownStock table.
     * 
     * @param pickListValue pickListValue of the GodownStock
     * @return this
     */
    public GodownStock pickListVariety(PickListValue pickListValue) {
        this.pickListVariety = pickListValue;
        return this;
    }

    /**
     * To Set the pickListValue for GodownStock table
     * 
     * @param pickListValue pickListValue value
     */
    public void setPickListVariety(PickListValue pickListValue) {
        this.pickListVariety = pickListValue;
    }

    /**
     * To Get the pickListCategory from GodownStock table
     * 
     * @return pickListCategory 
     */
    public PickListValue getPickListCategory() {
        return pickListCategory;
    }

    /**
     * To set the pickListValue values in the GodownStock table.
     * 
     * @param pickListValue pickListValue of the GodownStock
     * @return this
     */
    public GodownStock pickListCategory(PickListValue pickListValue) {
        this.pickListCategory = pickListValue;
        return this;
    }

    /**
     * To Set the pickListValue for GodownStock table
     * 
     * @param pickListValue pickListValue value
     */
    public void setPickListCategory(PickListValue pickListValue) {
        this.pickListCategory = pickListValue;
    }

    /**
     * To Get the pickListQuantityType from GodownStock table
     * 
     * @return pickListQuantityType 
     */
    public PickListValue getPickListQuantityType() {
        return pickListQuantityType;
    }

    /**
     * To set the pickListValue values in the GodownStock table.
     * 
     * @param pickListValue pickListValue of the GodownStock
     * @return this
     */
    public GodownStock pickListQuantityType(PickListValue pickListValue) {
        this.pickListQuantityType = pickListValue;
        return this;
    }

    /**
     * To Set the pickListValue for GodownStock table
     * 
     * @param pickListValue pickListValue value
     */
    public void setPickListQuantityType(PickListValue pickListValue) {
        this.pickListQuantityType = pickListValue;
    }

    /**
     * To Get the godown from GodownStock table
     * 
     * @return godown 
     */
    public Godown getGodown() {
        return godown;
    }

    /**
     * To set the godown values in the GodownStock table.
     * 
     * @param godown godown of the GodownStock
     * @return this
     */
    public GodownStock godown(Godown godown) {
        this.godown = godown;
        return this;
    }

    /**
     * To Set the godown for GodownStock table
     * 
     * @param godown godown value
     */
    public void setGodown(Godown godown) {
        this.godown = godown;
    }

    /**
     * To Get the financialYearGodownStock from GodownStock table
     * 
     * @return financialYearGodownStock 
     */
    public FinancialYearSettings getFinancialYearGodownStock() {
        return financialYearGodownStock;
    }

    /**
     * To set the financialYearSettings values in the GodownStock table.
     * 
     * @param financialYearSettings financialYearSettings of the GodownStock
     * @return this
     */
    public GodownStock financialYearGodownStock(FinancialYearSettings financialYearSettings) {
        this.financialYearGodownStock = financialYearSettings;
        return this;
    }

    /**
     * To Set the financialYearSettings for GodownStock table
     * 
     * @param financialYearSettings financialYearSettings value
     */
    public void setFinancialYearGodownStock(FinancialYearSettings financialYearSettings) {
        this.financialYearGodownStock = financialYearSettings;
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
        GodownStock godownStock = (GodownStock) o;
        if (godownStock.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), godownStock.getId());
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
        return "GodownStock{" +
            "id=" + getId() +
            ", currentQuantity=" + getCurrentQuantity() +
            ", addedQuantity=" + getAddedQuantity() +
            ", consumedQuantity=" + getConsumedQuantity() +
            ", description='" + getDescription() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
