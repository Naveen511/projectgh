/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs Godown Generation.
 *  Declared the table fields and data types for the Godown table.
 *  Defined the following Relation for the Godown table :
 *  OneToMany Relation to GodownPurchaseDetails, GodownStock
 *  ManyToOne Relation to FinancialYearSettings
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
 * A Godown.
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

    @OneToMany(mappedBy = "godown", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<GodownPurchaseDetails> godownPurchaseDetails = new HashSet<>();

    @OneToMany(mappedBy = "godown", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<GodownStock> godownStocks = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("godowns")
    private FinancialYearSettings financialYearGodown;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Godown name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public Godown address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIncharge() {
        return incharge;
    }

    public Godown incharge(String incharge) {
        this.incharge = incharge;
        return this;
    }

    public void setIncharge(String incharge) {
        this.incharge = incharge;
    }

    public Integer getStatus() {
        return status;
    }

    public Godown status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<GodownPurchaseDetails> getGodownPurchaseDetails() {
        return godownPurchaseDetails;
    }

    public Godown godownPurchaseDetails(Set<GodownPurchaseDetails> godownPurchaseDetails) {
        this.godownPurchaseDetails = godownPurchaseDetails;
        return this;
    }

    public Godown addGodownPurchaseDetails(GodownPurchaseDetails godownPurchaseDetails) {
        this.godownPurchaseDetails.add(godownPurchaseDetails);
        godownPurchaseDetails.setGodown(this);
        return this;
    }

    public Godown removeGodownPurchaseDetails(GodownPurchaseDetails godownPurchaseDetails) {
        this.godownPurchaseDetails.remove(godownPurchaseDetails);
        godownPurchaseDetails.setGodown(null);
        return this;
    }

    public void setGodownPurchaseDetails(Set<GodownPurchaseDetails> godownPurchaseDetails) {
        this.godownPurchaseDetails = godownPurchaseDetails;
    }

    public Set<GodownStock> getGodownStocks() {
        return godownStocks;
    }

    public Godown godownStocks(Set<GodownStock> godownStocks) {
        this.godownStocks = godownStocks;
        return this;
    }

    public Godown addGodownStocks(GodownStock godownStock) {
        this.godownStocks.add(godownStock);
        godownStock.setGodown(this);
        return this;
    }

    public Godown removeGodownStocks(GodownStock godownStock) {
        this.godownStocks.remove(godownStock);
        godownStock.setGodown(null);
        return this;
    }

    public void setGodownStocks(Set<GodownStock> godownStocks) {
        this.godownStocks = godownStocks;
    }

    public FinancialYearSettings getFinancialYearGodown() {
        return financialYearGodown;
    }

    public Godown financialYearGodown(FinancialYearSettings financialYearSettings) {
        this.financialYearGodown = financialYearSettings;
        return this;
    }

    public void setFinancialYearGodown(FinancialYearSettings financialYearSettings) {
        this.financialYearGodown = financialYearSettings;
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
        Godown godown = (Godown) o;
        if (godown.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), godown.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

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
