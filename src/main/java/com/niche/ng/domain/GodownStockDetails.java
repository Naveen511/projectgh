/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date   : 2018/08/02
 *  Target : yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs GodownStockDetails  Generation 
 *                       and declared the table fields, data types for 
 *                       GodownStockDetails table.
 *  Relation for GodownStockDetails : ManyToOne Relation
 *  ManyToOne Relation : GodownStock, FinancialYearSettings
 *
 *******************************************************************************/
package com.niche.ng.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * GodownStockDetails Domain Class
 * 
 * GodownStockDetails class implements Serializable to convert instance class into 
 * series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
@Entity
@Table(name = "godown_stock_details")
public class GodownStockDetails extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "jhi_date", nullable = false)
    private LocalDate date;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Integer status;

    @Column(name = "price")
    private Float price;

    /**
     * Relation Name : ManyToOne - godownStockDetails
     * Table Name    : Connects the GodownStockDetails Table to GodownStock Table.
     * Used to point out the GodownStockDetails values in the GodownStock table.
     */
    @ManyToOne
    @JsonIgnoreProperties("godownStockDetails")
    private GodownStock godownStock;

    /**
     * Relation Name : ManyToOne - godownStockDetails
     * Table Name    : Connects the GodownStockDetails Table to FinancialYearSettings Table.
     * Used to point out the GodownStockDetails values in the FinancialYearSettings table.
     */
    @ManyToOne
    @JsonIgnoreProperties("godownStockDetails")
    private FinancialYearSettings financialYearGodownStockDetails;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    /**
     * To Get the Id from GodownStockDetails table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To set the Id from GodownStockDetails table
     * 
     * @return id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the date from GodownStockDetails table
     * 
     * @return date 
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * To set the date values in the GodownStockDetails table.
     * 
     * @param date date of the GodownStockDetails
     * @return this
     */
    public GodownStockDetails date(LocalDate date) {
        this.date = date;
        return this;
    }

    /**
     * To Set the date for GodownStockDetails table
     * 
     * @param date date value
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * To Get the quantity from GodownStockDetails table
     * 
     * @return quantity 
     */
    public Long getQuantity() {
        return quantity;
    }

    /**
     * To set the quantity values in the GodownStockDetails table.
     * 
     * @param quantity quantity of the GodownStock
     * @return this
     */
    public GodownStockDetails quantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    /**
     * To Set the quantity for GodownStockDetails table
     * 
     * @param quantity quantity value
     */
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    /**
     * To Get the description from GodownStockDetails table
     * 
     * @return description 
     */
    public String getDescription() {
        return description;
    }

    /**
     * To set the description values in the GodownStockDetails table.
     * 
     * @param description description of the GodownStock
     * @return this
     */
    public GodownStockDetails description(String description) {
        this.description = description;
        return this;
    }

    /**
     * To Set the description for GodownStockDetails table
     * 
     * @param description description value
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * To Get the status from GodownStockDetails table
     * 
     * @return status 
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To set the status values in the GodownStockDetails table.
     * 
     * @param status status of the GodownStock
     * @return this
     */
    public GodownStockDetails status(Integer status) {
        this.status = status;
        return this;
    }

    /**
     * To Set the status for GodownStockDetails table
     * 
     * @param status status value
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the price from GodownStockDetails table
     * 
     * @return price 
     */
    public Float getPrice() {
        return price;
    }

    /**
     * To set the price values in the GodownStockDetails table.
     * 
     * @param price price of the GodownStockDetails
     * @return this
     */
    public GodownStockDetails price(Float price) {
        this.price = price;
        return this;
    }

    /**
     * To Set the price for GodownStockDetails table
     * 
     * @param price price value
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * To Get the godownStock from GodownStockDetails table
     * 
     * @return godownStock 
     */
    public GodownStock getGodownStock() {
        return godownStock;
    }

    /**
     * To set the godownStock values in the GodownStockDetails table.
     * 
     * @param godownStock godownStock of the GodownStockDetails
     * @return this
     */
    public GodownStockDetails godownStock(GodownStock godownStock) {
        this.godownStock = godownStock;
        return this;
    }

    /**
     * To Set the godownStock for GodownStockDetails table
     * 
     * @param godownStock godownStock value
     */
    public void setGodownStock(GodownStock godownStock) {
        this.godownStock = godownStock;
    }

    /**
     * To Get the financialYearGodownStockDetails from GodownStockDetails table
     * 
     * @return financialYearGodownStockDetails 
     */
    public FinancialYearSettings getFinancialYearGodownStockDetails() {
        return financialYearGodownStockDetails;
    }

    /**
     * To set the financialYearSettings values in the GodownStockDetails table.
     * 
     * @param financialYearSettings financialYearSettings of the GodownStockDetails
     * @return this
     */
    public GodownStockDetails financialYearGodownStockDetails(FinancialYearSettings financialYearSettings) {
        this.financialYearGodownStockDetails = financialYearSettings;
        return this;
    }

    /**
     * To Set the financialYearSettings for GodownStockDetails table
     * 
     * @param financialYearSettings financialYearSettings value
     */
    public void setFinancialYearGodownStockDetails(FinancialYearSettings financialYearSettings) {
        this.financialYearGodownStockDetails = financialYearSettings;
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
        GodownStockDetails godownStockDetails = (GodownStockDetails) o;
        if (godownStockDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), godownStockDetails.getId());
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
        return "GodownStockDetails{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", quantity=" + getQuantity() +
            ", description='" + getDescription() + "'" +
            ", status=" + getStatus() +
            ", price=" + getPrice() +
            "}";
    }
}
