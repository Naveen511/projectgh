/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date   : 2018/08/02
 *  Target : yarn
 *  -----------------------------------------------------------------------------
 *  File Description   : This file performs Godown  Generation 
 *                       and declared the table fields, data types for Godown table.
 *  Relation for Godown : OneToMany Relation, ManyToOne Relation
 *  OneToMany Relation : GodownPurchaseDetails, GodownStock
 *  ManyToOne Relation : FinancialYearSettings
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
 * Godown Domain Class
 * 
 * Godown class implements Serializable to convert instance class into 
 * series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
@Entity
@Table(name = "godown")
public class Godown extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "incharge")
    private String incharge;

    @Column(name = "status")
    private Integer status;

    /**
     * Relation Name : OneToMany - godownPurchaseDetails
     * Table Name    : Connects the Godown Table to GodownPurchaseDetails Table
     * Used to point out the Godown values in the GodownPurchaseDetails table.
     */
    @OneToMany(mappedBy = "godown", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<GodownPurchaseDetails> godownPurchaseDetails = new HashSet<>();

    /**
     * Relation Name : OneToMany - godownStocks
     * Table Name    : Connects the Godown Table to GodownStock Table
     * Used to point out the Godown values in the GodownStock table.
     */
    @OneToMany(mappedBy = "godown", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<GodownStock> godownStocks = new HashSet<>();

    /**
     * Relation Name : ManyToOne - godowns
     * Table Name    : Connects the Godown Table to FinancialYearSettings Table.
     * Used to point out the Godown values in the FinancialYearSettings table.
     */
    @ManyToOne
    @JsonIgnoreProperties("godowns")
    private FinancialYearSettings financialYearGodown;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
     /**
     * To Get the Id from Godown table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To Set the Id for Godown table
     * 
     * @param id id value
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To get the name values in the Godown table.
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * To set the name values in the Godown table.
     * 
     * @param name name of the godown
     * @return this
     */
    public Godown name(String name) {
        this.name = name;
        return this;
    }

    /**
     * To set the name values in the Godown table.
     * 
     * @param name name of the godown
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * To get the address values in the Godown table.
     * 
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * To set the address values in the Godown table.
     * 
     * @param address address of the godown
     * @return this
     */
    public Godown address(String address) {
        this.address = address;
        return this;
    }

    /**
     * To set the address values in the Godown table.
     * 
     * @param address address of the godown
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * To get the incharge values in the Godown table.]
     * 
     * @return incharge
     */
    public String getIncharge() {
        return incharge;
    }

    /**
     * To set the incharge values in the Godown table.
     * 
     * @param incharge godown incharge
     * @return this
     */
    public Godown incharge(String incharge) {
        this.incharge = incharge;
        return this;
    }

    /**
     * To set the incharge values in the Godown table.
     * 
     * @param incharge set the incharge
     */
    public void setIncharge(String incharge) {
        this.incharge = incharge;
    }

    /**
     * To get the status values in the Godown table.
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To set the status values in the Godown table.
     * 
     * @param status status in godown
     * @return this
     */
    public Godown status(Integer status) {
        this.status = status;
        return this;
    }

    /**
     * To set the status values in the Godown table.
     * 
     * @param status set the status in godown
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To get the adding ofgodownPurchaseDetails values in the Godown table.
     * 
     * @return godownPurchaseDetails
     */
    public Set<GodownPurchaseDetails> getGodownPurchaseDetails() {
        return godownPurchaseDetails;
    }

    /**
     * To set the godownPurchaseDetails values in the Godown table.
     * 
     * @param godownPurchaseDetails purchase details
     * @return this
     */
    public Godown godownPurchaseDetails(Set<GodownPurchaseDetails> godownPurchaseDetails) {
        this.godownPurchaseDetails = godownPurchaseDetails;
        return this;
    }

    /**
     * To set the adding ofgodownPurchaseDetails values in the Godown table.
     * 
     * @param godownPurchaseDetails add the purchase details
     * @return this
     */
    public Godown addGodownPurchaseDetails(GodownPurchaseDetails godownPurchaseDetails) {
        this.godownPurchaseDetails.add(godownPurchaseDetails);
        godownPurchaseDetails.setGodown(this);
        return this;
    }

    /**
     * To set the removing of godownPurchaseDetails values in the Godown table.
     * 
     * @param godownPurchaseDetails remove the purchase details
     * @return this
     */
    public Godown removeGodownPurchaseDetails(GodownPurchaseDetails godownPurchaseDetails) {
        this.godownPurchaseDetails.remove(godownPurchaseDetails);
        godownPurchaseDetails.setGodown(null);
        return this;
    }

    /**
     * To set the godownPurchaseDetails values in the Godown table.
     * 
     * @param godownPurchaseDetails set the the purchase details
     */
    public void setGodownPurchaseDetails(Set<GodownPurchaseDetails> godownPurchaseDetails) {
        this.godownPurchaseDetails = godownPurchaseDetails;
    }

    /**
     * To set the godownStocks values in the Godown table.
     * 
     * @return godownStocks
     */
    public Set<GodownStock> getGodownStocks() {
        return godownStocks;
    }

    /**
     * To set the godownStocks values in the Godown table.
     * 
     * @param godownStocks stocks in godown
     * @return this
     */
    public Godown godownStocks(Set<GodownStock> godownStocks) {
        this.godownStocks = godownStocks;
        return this;
    }

    /**
     * To add the godownStocks values in the Godown table.
     * 
     * @param godownStock add stocks in godown
     * @return this
     */
    public Godown addGodownStocks(GodownStock godownStock) {
        this.godownStocks.add(godownStock);
        godownStock.setGodown(this);
        return this;
    }

    /**
     * To set the remove godownStocks values in the Godown table.
     * 
     * @param godownStock remove stocks in godown
     * @return this
     */
    public Godown removeGodownStocks(GodownStock godownStock) {
        this.godownStocks.remove(godownStock);
        godownStock.setGodown(null);
        return this;
    }

    /**
     * To set the godownStocks values in the Godown table.
     * 
     * @param godownStocks set godown stocks
     */
    public void setGodownStocks(Set<GodownStock> godownStocks) {
        this.godownStocks = godownStocks;
    }

    /**
     * To get the financialYearGodown values in the Godown table.
     * 
     * @return financialYearGodown
     */
    public FinancialYearSettings getFinancialYearGodown() {
        return financialYearGodown;
    }

    /**
     * To set the financialYearGodown values in the Godown table.
     * 
     * @param financialYearSettings year settings
     * @return this
     */
    public Godown financialYearGodown(FinancialYearSettings financialYearSettings) {
        this.financialYearGodown = financialYearSettings;
        return this;
    }

    /**
     * To set the financialYearGodown values in the Godown table.
     * 
     * @param financialYearSettings set the year 
     */
    public void setFinancialYearGodown(FinancialYearSettings financialYearSettings) {
        this.financialYearGodown = financialYearSettings;
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
        Godown godown = (Godown) o;
        if (godown.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), godown.getId());
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
        return "Godown{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", address='" + getAddress() + "'" +
            ", incharge='" + getIncharge() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
