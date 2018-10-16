/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description   : This file performs GodownPurchaseDetails Generation.
 *  Declared the table fields and data types for the GodownPurchaseDetails table.
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
 * A GodownPurchaseDetails.
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
    private Long price;

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

    @ManyToOne
    @JsonIgnoreProperties("godownPurchaseVarietys")
    private PickListValue pickListVariety;

    @ManyToOne
    @JsonIgnoreProperties("godownPurchaseCategorys")
    private PickListValue pickListCategory;

    @ManyToOne
    @JsonIgnoreProperties("godownPurchaseQuantityTypes")
    private PickListValue pickListQuantityType;

    @ManyToOne
    @JsonIgnoreProperties("godownPurchaseDetails")
    private Godown godown;

    @ManyToOne
    @JsonIgnoreProperties("godownPurchaseDetails")
    private FinancialYearSettings financialYearGodownPurchase;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public GodownPurchaseDetails quantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDate() {
        return date;
    }

    public GodownPurchaseDetails date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getPrice() {
        return price;
    }

    public GodownPurchaseDetails price(Long price) {
        this.price = price;
        return this;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getOwnedBy() {
        return ownedBy;
    }

    public GodownPurchaseDetails ownedBy(String ownedBy) {
        this.ownedBy = ownedBy;
        return this;
    }

    public void setOwnedBy(String ownedBy) {
        this.ownedBy = ownedBy;
    }

    public String getVendorName() {
        return vendorName;
    }

    public GodownPurchaseDetails vendorName(String vendorName) {
        this.vendorName = vendorName;
        return this;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorAddress() {
        return vendorAddress;
    }

    public GodownPurchaseDetails vendorAddress(String vendorAddress) {
        this.vendorAddress = vendorAddress;
        return this;
    }

    public void setVendorAddress(String vendorAddress) {
        this.vendorAddress = vendorAddress;
    }

    public Long getVendorPhone() {
        return vendorPhone;
    }

    public GodownPurchaseDetails vendorPhone(Long vendorPhone) {
        this.vendorPhone = vendorPhone;
        return this;
    }

    public void setVendorPhone(Long vendorPhone) {
        this.vendorPhone = vendorPhone;
    }

    public String getDescription() {
        return description;
    }

    public GodownPurchaseDetails description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public GodownPurchaseDetails status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public PickListValue getPickListVariety() {
        return pickListVariety;
    }

    public GodownPurchaseDetails pickListVariety(PickListValue pickListValue) {
        this.pickListVariety = pickListValue;
        return this;
    }

    public void setPickListVariety(PickListValue pickListValue) {
        this.pickListVariety = pickListValue;
    }

    public PickListValue getPickListCategory() {
        return pickListCategory;
    }

    public GodownPurchaseDetails pickListCategory(PickListValue pickListValue) {
        this.pickListCategory = pickListValue;
        return this;
    }

    public void setPickListCategory(PickListValue pickListValue) {
        this.pickListCategory = pickListValue;
    }

    public PickListValue getPickListQuantityType() {
        return pickListQuantityType;
    }

    public GodownPurchaseDetails pickListQuantityType(PickListValue pickListValue) {
        this.pickListQuantityType = pickListValue;
        return this;
    }

    public void setPickListQuantityType(PickListValue pickListValue) {
        this.pickListQuantityType = pickListValue;
    }

    public Godown getGodown() {
        return godown;
    }

    public GodownPurchaseDetails godown(Godown godown) {
        this.godown = godown;
        return this;
    }

    public void setGodown(Godown godown) {
        this.godown = godown;
    }

    public FinancialYearSettings getFinancialYearGodownPurchase() {
        return financialYearGodownPurchase;
    }

    public GodownPurchaseDetails financialYearGodownPurchase(FinancialYearSettings financialYearSettings) {
        this.financialYearGodownPurchase = financialYearSettings;
        return this;
    }

    public void setFinancialYearGodownPurchase(FinancialYearSettings financialYearSettings) {
        this.financialYearGodownPurchase = financialYearSettings;
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
        GodownPurchaseDetails godownPurchaseDetails = (GodownPurchaseDetails) o;
        if (godownPurchaseDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), godownPurchaseDetails.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

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
