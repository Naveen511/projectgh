/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date   : 2018/08/02
 *  Target : yarn
 *  -----------------------------------------------------------------------------
 *  File Description   : This file performs GodownPurchaseDetails  Generation 
 *                       and declared the table fields, data types for 
 *                       GodownPurchaseDetails table.
 *  Relation for GodownPurchaseDetails : ManyToOne Relation
 *  ManyToOne Relation :  FinancialYearSettings, PickListValue, Godown
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
 * GodownPurchaseDetails Domain Class
 * 
 * GodownPurchaseDetails class implements Serializable to convert instance class into 
 * series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
@Entity
@Table(name = "godown_purchase_details")
public class GodownPurchaseDetails extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @NotNull
    @Column(name = "jhi_date", nullable = false)
    private LocalDate date;

    @Column(name = "price")
    private Float price;

    @Column(name = "owned_by")
    private String ownedBy;

    @Column(name = "vendor_name")
    private String vendorName;

    @Column(name = "vendor_address")
    private String vendorAddress;

    @Column(name = "vendor_phone")
    private Long vendorPhone;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Integer status;

    /**
     * Relation Name : ManyToOne - godownPurchaseVarietys
     * Table Name    : Connect the GodownPurchaseDetails Table to 
     *                  PickListValue Table.
     * Used to point out the GodownPurchaseDetails values in the PickListValue table.
     */
    @ManyToOne
    @JsonIgnoreProperties("godownPurchaseVarietys")
    private PickListValue pickListVariety;

    /**
     * Relation Name : ManyToOne - godownPurchaseCategorys
     * Table Name    : Connect the GodownPurchaseDetails Table to 
     *                  PickListValue Table.
     * Used to point out the GodownPurchaseDetails values in the PickListValue table.
     */
    @ManyToOne
    @JsonIgnoreProperties("godownPurchaseCategorys")
    private PickListValue pickListCategory;

    /**
     * Relation Name : ManyToOne - godownPurchaseQuantityTypes
     * Table Name    : Connect the GodownPurchaseDetails Table to 
     *                  PickListValue Table.
     * Used to point out the GodownPurchaseDetails values in the PickListValue table.
     */
    @ManyToOne
    @JsonIgnoreProperties("godownPurchaseQuantityTypes")
    private PickListValue pickListQuantityType;

    /**
     * Relation Name : ManyToOne - godownPurchaseDetails
     * Table Name    : Connect the GodownPurchaseDetails Table to 
     *                  Godown Table.
     * Used to point out the GodownPurchaseDetails values in the Godown table.
     */
    @ManyToOne
    @JsonIgnoreProperties("godownPurchaseDetails")
    private Godown godown;

    /**
     * Relation Name : ManyToOne - godownPurchaseDetails
     * Table Name    : Connect the GodownPurchaseDetails Table to 
     *                  FinancialYearSettings Table.
     * Used to point out the GodownPurchaseDetails values in the 
     * FinancialYearSettings table.
     */
    @ManyToOne
    @JsonIgnoreProperties("godownPurchaseDetails")
    private FinancialYearSettings financialYearGodownPurchase;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    /**
     * To Get the Id from GodownPurchaseDetails table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To Set the Id for GodownPurchaseDetails table
     * 
     * @param id id value
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the quantity from GodownPurchaseDetails table
     * 
     * @return quantity
     */
    public Long getQuantity() {
        return quantity;
    }

    /**
     * To set the quantity values in the GodownPurchaseDetails table.
     * 
     * @param quantity quantity of the GodownPurchaseDetails
     * @return this
     */
    public GodownPurchaseDetails quantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    /**
     * To Set the quantity for GodownPurchaseDetails table
     * 
     * @param quantity quantity value
     */
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    /**
     * To Get the date from GodownPurchaseDetails table
     * 
     * @return date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * To set the date values in the GodownPurchaseDetails table.
     * 
     * @param date date of the GodownPurchaseDetails
     * @return this
     */
    public GodownPurchaseDetails date(LocalDate date) {
        this.date = date;
        return this;
    }

    /**
     * To Set the date for GodownPurchaseDetails table
     * 
     * @param date date value
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * To Get the price from GodownPurchaseDetails table
     * 
     * @return price
     */
    public Float getPrice() {
        return price;
    }

    /**
     * To set the price values in the GodownPurchaseDetails table.
     * 
     * @param price price of the GodownPurchaseDetails
     * @return this
     */
    public GodownPurchaseDetails price(Float price) {
        this.price = price;
        return this;
    }

    /**
     * To Set the price for GodownPurchaseDetails table
     * 
     * @param price price value
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * To Get the ownedBy from GodownPurchaseDetails table
     * 
     * @return ownedBy
     */
    public String getOwnedBy() {
        return ownedBy;
    }

    /**
     * To set the ownedBy values in the GodownPurchaseDetails table.
     * 
     * @param ownedBy ownedBy of the GodownPurchaseDetails
     * @return this
     */
    public GodownPurchaseDetails ownedBy(String ownedBy) {
        this.ownedBy = ownedBy;
        return this;
    }

    /**
     * To Set the ownedBy for GodownPurchaseDetails table
     * 
     * @param ownedBy ownedBy value
     */
    public void setOwnedBy(String ownedBy) {
        this.ownedBy = ownedBy;
    }

    /**
     * To Get the vendorName from GodownPurchaseDetails table
     * 
     * @return vendorName
     */
    public String getVendorName() {
        return vendorName;
    }

    /**
     * To set the vendorName values in the GodownPurchaseDetails table.
     * 
     * @param vendorName vendorName of the GodownPurchaseDetails
     * @return this
     */
    public GodownPurchaseDetails vendorName(String vendorName) {
        this.vendorName = vendorName;
        return this;
    }

    /**
     * To Set the vendorName for GodownPurchaseDetails table
     * 
     * @param vendorName vendorName value
     */
    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    /**
     * To Get the vendorAddress from GodownPurchaseDetails table
     * 
     * @return vendorAddress
     */
    public String getVendorAddress() {
        return vendorAddress;
    }

    /**
     * To set the vendorAddress values in the GodownPurchaseDetails table.
     * 
     * @param vendorAddress vendorAddress of the GodownPurchaseDetails
     * @return this
     */
    public GodownPurchaseDetails vendorAddress(String vendorAddress) {
        this.vendorAddress = vendorAddress;
        return this;
    }

    /**
     * To Set the vendorAddress for GodownPurchaseDetails table
     * 
     * @param vendorAddress vendorAddress value
     */
    public void setVendorAddress(String vendorAddress) {
        this.vendorAddress = vendorAddress;
    }

    /**
     * To Get the vendorPhone from GodownPurchaseDetails table
     * 
     * @return vendorPhone
     */
    public Long getVendorPhone() {
        return vendorPhone;
    }

    /**
     * To set the vendorPhone values in the GodownPurchaseDetails table.
     * 
     * @param vendorPhone vendorPhone of the GodownPurchaseDetails
     * @return this
     */
    public GodownPurchaseDetails vendorPhone(Long vendorPhone) {
        this.vendorPhone = vendorPhone;
        return this;
    }

    /**
     * To Set the vendorPhone for GodownPurchaseDetails table
     * 
     * @param vendorPhone vendorPhone value
     */
    public void setVendorPhone(Long vendorPhone) {
        this.vendorPhone = vendorPhone;
    }

    /**
     * To Get the description from GodownPurchaseDetails table
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * To set the description values in the GodownPurchaseDetails table.
     * 
     * @param description description of the GodownPurchaseDetails
     * @return this
     */
    public GodownPurchaseDetails description(String description) {
        this.description = description;
        return this;
    }

    /**
     * To Set the description for GodownPurchaseDetails table
     * 
     * @param description description value
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * To Get the status from GodownPurchaseDetails table
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To set the status values in the GodownPurchaseDetails table.
     * 
     * @param status status of the GodownPurchaseDetails
     * @return this
     */
    public GodownPurchaseDetails status(Integer status) {
        this.status = status;
        return this;
    }

    /**
     * To Set the status for GodownPurchaseDetails table
     * 
     * @param status status value
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the pickListVariety from GodownPurchaseDetails table
     * 
     * @return pickListVariety
     */
    public PickListValue getPickListVariety() {
        return pickListVariety;
    }

    /**
     * To set the pickListValue values in the GodownPurchaseDetails table.
     * 
     * @param pickListValue pickListValue of the GodownPurchaseDetails
     * @return this
     */
    public GodownPurchaseDetails pickListVariety(PickListValue pickListValue) {
        this.pickListVariety = pickListValue;
        return this;
    }

    /**
     * To Set the pickListValue for GodownPurchaseDetails table
     * 
     * @param pickListValue pickListValue value
     */
    public void setPickListVariety(PickListValue pickListValue) {
        this.pickListVariety = pickListValue;
    }

    /**
     * To Get the pickListCategory from GodownPurchaseDetails table
     * 
     * @return pickListCategory
     */
    public PickListValue getPickListCategory() {
        return pickListCategory;
    }

    /**
     * To set the pickListValue values in the GodownPurchaseDetails table.
     * 
     * @param pickListValue pickListValue of the GodownPurchaseDetails
     * @return this
     */
    public GodownPurchaseDetails pickListCategory(PickListValue pickListValue) {
        this.pickListCategory = pickListValue;
        return this;
    }

    /**
     * To Set the pickListValue for GodownPurchaseDetails table
     * 
     * @param pickListValue pickListValue value
     */
    public void setPickListCategory(PickListValue pickListValue) {
        this.pickListCategory = pickListValue;
    }

    /**
     * To Get the pickListQuantityType from GodownPurchaseDetails table
     * 
     * @return pickListQuantityType
     */
    public PickListValue getPickListQuantityType() {
        return pickListQuantityType;
    }

    /**
     * To set the pickListValue values in the GodownPurchaseDetails table.
     * 
     * @param pickListValue pickListValue of the GodownPurchaseDetails
     * @return this
     */
    public GodownPurchaseDetails pickListQuantityType(PickListValue pickListValue) {
        this.pickListQuantityType = pickListValue;
        return this;
    }

    /**
     * To Set the pickListValue for GodownPurchaseDetails table
     * 
     * @param pickListValue pickListValue value
     */
    public void setPickListQuantityType(PickListValue pickListValue) {
        this.pickListQuantityType = pickListValue;
    }

    /**
     * To Get the godown from GodownPurchaseDetails table
     * 
     * @return godown
     */
    public Godown getGodown() {
        return godown;
    }

    /**
     * To set the godown values in the GodownPurchaseDetails table.
     * 
     * @param godown godown of the GodownPurchaseDetails
     * @return this
     */
    public GodownPurchaseDetails godown(Godown godown) {
        this.godown = godown;
        return this;
    }

    /**
     * To Set the godown for GodownPurchaseDetails table
     * 
     * @param godown godown value
     */
    public void setGodown(Godown godown) {
        this.godown = godown;
    }

    /**
     * To Get the financialYearGodownPurchase from GodownPurchaseDetails table
     * 
     * @return financialYearGodownPurchase
     */
    public FinancialYearSettings getFinancialYearGodownPurchase() {
        return financialYearGodownPurchase;
    }

    /**
     * To set the financialYearSettings values in the GodownPurchaseDetails table.
     * 
     * @param financialYearSettings financialYearSettings of the GodownPurchaseDetails
     * @return this
     */
    public GodownPurchaseDetails financialYearGodownPurchase(FinancialYearSettings financialYearSettings) {
        this.financialYearGodownPurchase = financialYearSettings;
        return this;
    }

    /**
     * To Set the financialYearSettings for GodownPurchaseDetails table
     * 
     * @param financialYearSettings financialYearSettings value
     */
    public void setFinancialYearGodownPurchase(FinancialYearSettings financialYearSettings) {
        this.financialYearGodownPurchase = financialYearSettings;
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
        GodownPurchaseDetails godownPurchaseDetails = (GodownPurchaseDetails) o;
        if (godownPurchaseDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), godownPurchaseDetails.getId());
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
        return "GodownPurchaseDetails{" +
            "id=" + getId() +
            ", quantity=" + getQuantity() +
            ", date='" + getDate() + "'" +
            ", price=" + getPrice() +
            ", ownedBy='" + getOwnedBy() + "'" +
            ", vendorName='" + getVendorName() + "'" +
            ", vendorAddress='" + getVendorAddress() + "'" +
            ", vendorPhone=" + getVendorPhone() +
            ", description='" + getDescription() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
