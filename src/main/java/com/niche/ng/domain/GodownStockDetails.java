/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs GodownStockDetails Generation.
 *  Declared the table fields and data types for the GodownStockDetails table.
 *  Defined the following Relation for the GodownStockDetails Table :
 *  ManyToOne Relation to GodownStock, FinancialYearSettings
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
 * A GodownStockDetails.
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

    @ManyToOne
    @JsonIgnoreProperties("godownStockDetails")
    private GodownStock godownStock;

    @ManyToOne
    @JsonIgnoreProperties("godownStockDetails")
    private FinancialYearSettings financialYearGodownStockDetails;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public GodownStockDetails date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getQuantity() {
        return quantity;
    }

    public GodownStockDetails quantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public GodownStockDetails description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public GodownStockDetails status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Float getPrice() {
        return price;
    }

    public GodownStockDetails price(Float price) {
        this.price = price;
        return this;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public GodownStock getGodownStock() {
        return godownStock;
    }

    public GodownStockDetails godownStock(GodownStock godownStock) {
        this.godownStock = godownStock;
        return this;
    }

    public void setGodownStock(GodownStock godownStock) {
        this.godownStock = godownStock;
    }

    public FinancialYearSettings getFinancialYearGodownStockDetails() {
        return financialYearGodownStockDetails;
    }

    public GodownStockDetails financialYearGodownStockDetails(FinancialYearSettings financialYearSettings) {
        this.financialYearGodownStockDetails = financialYearSettings;
        return this;
    }

    public void setFinancialYearGodownStockDetails(FinancialYearSettings financialYearSettings) {
        this.financialYearGodownStockDetails = financialYearSettings;
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
        GodownStockDetails godownStockDetails = (GodownStockDetails) o;
        if (godownStockDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), godownStockDetails.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

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
