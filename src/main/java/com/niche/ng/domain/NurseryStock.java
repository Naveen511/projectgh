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
 *  OneToMany Relation to NurseryStockDetails Table
 *  ManyToOne Relation to Nursery, PickList Variety, and Picklist Category
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
 * A NurseryStock.
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

    @OneToMany(mappedBy = "nurseryStock", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<NurseryStockDetails> nurseryStockDetails = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("nurseryStocks")
    @JoinColumn(name="nursery_id", referencedColumnName="id")
    private Nursery nursery;

    @ManyToOne
    @JsonIgnoreProperties("nurseryStockVarietys")
    @JoinColumn(name="pick_list_variety_id", referencedColumnName="id")
    private PickListValue pickListVariety;

    @ManyToOne
    @JsonIgnoreProperties("nurseryStockCategorys")
    @JoinColumn(name="pick_list_category_id", referencedColumnName="id")
    private PickListValue pickListCategory;

    @ManyToOne
    @JsonIgnoreProperties("nurseryStocks")
    private FinancialYearSettings financialYearNurseryStock;

    @OneToMany(mappedBy = "nurseryStock", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<PointOfSaleDetails> pointOfSaleDetails = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCurrentQuantity() {
        return currentQuantity;
    }

    public NurseryStock currentQuantity(Long currentQuantity) {
        this.currentQuantity = currentQuantity;
        return this;
    }

    public void setCurrentQuantity(Long currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public Long getAddedQuantity() {
        return addedQuantity;
    }

    public NurseryStock addedQuantity(Long addedQuantity) {
        this.addedQuantity = addedQuantity;
        return this;
    }

    public void setAddedQuantity(Long addedQuantity) {
        this.addedQuantity = addedQuantity;
    }

    public Long getConsumedQuantity() {
        return consumedQuantity;
    }

    public NurseryStock consumedQuantity(Long consumedQuantity) {
        this.consumedQuantity = consumedQuantity;
        return this;
    }

    public void setConsumedQuantity(Long consumedQuantity) {
        this.consumedQuantity = consumedQuantity;
    }

    public String getDescription() {
        return description;
    }

    public NurseryStock description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public NurseryStock status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPosQuantity() {
        return posQuantity;
    }

    public NurseryStock posQuantity(Integer posQuantity) {
        this.posQuantity = posQuantity;
        return this;
    }

    public void setPosQuantity(Integer posQuantity) {
        this.posQuantity = posQuantity;
    }

    public Set<NurseryStockDetails> getNurseryStockDetails() {
        return nurseryStockDetails;
    }

    public NurseryStock nurseryStockDetails(Set<NurseryStockDetails> nurseryStockDetails) {
        this.nurseryStockDetails = nurseryStockDetails;
        return this;
    }

    public NurseryStock addNurseryStockDetails(NurseryStockDetails nurseryStockDetails) {
        this.nurseryStockDetails.add(nurseryStockDetails);
        nurseryStockDetails.setNurseryStock(this);
        return this;
    }

    public NurseryStock removeNurseryStockDetails(NurseryStockDetails nurseryStockDetails) {
        this.nurseryStockDetails.remove(nurseryStockDetails);
        nurseryStockDetails.setNurseryStock(null);
        return this;
    }

    public void setNurseryStockDetails(Set<NurseryStockDetails> nurseryStockDetails) {
        this.nurseryStockDetails = nurseryStockDetails;
    }

    public Nursery getNursery() {
        return nursery;
    }

    public NurseryStock nursery(Nursery nursery) {
        this.nursery = nursery;
        return this;
    }

    public void setNursery(Nursery nursery) {
        this.nursery = nursery;
    }

    public PickListValue getPickListVariety() {
        return pickListVariety;
    }

    public NurseryStock pickListVariety(PickListValue pickListValue) {
        this.pickListVariety = pickListValue;
        return this;
    }

    public void setPickListVariety(PickListValue pickListValue) {
        this.pickListVariety = pickListValue;
    }

    public PickListValue getPickListCategory() {
        return pickListCategory;
    }

    public NurseryStock pickListCategory(PickListValue pickListValue) {
        this.pickListCategory = pickListValue;
        return this;
    }

    public void setPickListCategory(PickListValue pickListValue) {
        this.pickListCategory = pickListValue;
    }

    public FinancialYearSettings getFinancialYearNurseryStock() {
        return financialYearNurseryStock;
    }

    public NurseryStock financialYearNurseryStock(FinancialYearSettings financialYearSettings) {
        this.financialYearNurseryStock = financialYearSettings;
        return this;
    }

    public void setFinancialYearNurseryStock(FinancialYearSettings financialYearSettings) {
        this.financialYearNurseryStock = financialYearSettings;
    }

    public Set<PointOfSaleDetails> getPointOfSaleDetails() {
        return pointOfSaleDetails;
    }

    public NurseryStock pointOfSaleDetails(Set<PointOfSaleDetails> pointOfSaleDetails) {
        this.pointOfSaleDetails = pointOfSaleDetails;
        return this;
    }

    public NurseryStock addPointOfSaleDetails(PointOfSaleDetails pointOfSaleDetails) {
        this.pointOfSaleDetails.add(pointOfSaleDetails);
        pointOfSaleDetails.setNurseryStock(this);
        return this;
    }

    public NurseryStock removePointOfSaleDetails(PointOfSaleDetails pointOfSaleDetails) {
        this.pointOfSaleDetails.remove(pointOfSaleDetails);
        pointOfSaleDetails.setNurseryStock(null);
        return this;
    }

    public void setPointOfSaleDetails(Set<PointOfSaleDetails> pointOfSaleDetails) {
        this.pointOfSaleDetails = pointOfSaleDetails;
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
        NurseryStock nurseryStock = (NurseryStock) o;
        if (nurseryStock.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nurseryStock.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

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
            "}";
    }
}
