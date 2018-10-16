/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/31/08
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description   : This file performs Damage Generation.
 *  Declared the table fields and data types for the damage table.
 *  Defined the following Relation for the damage table :
 *  ManyToOne Relation : PickListValue
 *  OneToMany Relation : Zonal, Sector, Batch, Damage, ShadeArea, NurseryStock, 
 *           NurseryStockDetails, Godown, GodownPurchaseDetails
 *
 *******************************************************************************/
package com.niche.ng.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A FinancialYearSettings.
 */
@Entity
@Table(name = "financial_year_settings")
public class FinancialYearSettings extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "batch_name", nullable = false)
    private String batchName;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "status")
    private Integer status;

    @ManyToOne
    @JsonIgnoreProperties("financialYearNames")
    private PickListValue financialYear;

    @OneToMany(mappedBy = "financialYear", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<Zonal> zonals = new HashSet<>();

    @OneToMany(mappedBy = "financialYearSector", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<Sector> sectors = new HashSet<>();

    @OneToMany(mappedBy = "financialYearNursery", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<Nursery> nurseries = new HashSet<>();

    @OneToMany(mappedBy = "financialYearBatch", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<Batch> batches = new HashSet<>();

    @OneToMany(mappedBy = "financialYearDamage", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<Damage> damages = new HashSet<>();

    @OneToMany(mappedBy = "financialYearShadeArea", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<ShadeArea> shadeAreas = new HashSet<>();

    @OneToMany(mappedBy = "financialYearNurseryStock", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<NurseryStock> nurseryStocks = new HashSet<>();

    @OneToMany(mappedBy = "financialYearStockDetails", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<NurseryStockDetails> nurseryStockDetails = new HashSet<>();

    @OneToMany(mappedBy = "financialYearGodown", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<Godown> godowns = new HashSet<>();

    @OneToMany(mappedBy = "financialYearGodownStock", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<GodownStock> godownStocks = new HashSet<>();

    @OneToMany(mappedBy = "financialYearGodownStockDetails", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<GodownStockDetails> godownStockDetails = new HashSet<>();

    @OneToMany(mappedBy = "financialYearGodownPurchase", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<GodownPurchaseDetails> godownPurchaseDetails = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBatchName() {
        return batchName;
    }

    public FinancialYearSettings batchName(String batchName) {
        this.batchName = batchName;
        return this;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public FinancialYearSettings startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public FinancialYearSettings endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getStatus() {
        return status;
    }

    public FinancialYearSettings status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public PickListValue getFinancialYear() {
        return financialYear;
    }

    public FinancialYearSettings financialYear(PickListValue pickListValue) {
        this.financialYear = pickListValue;
        return this;
    }

    public void setFinancialYear(PickListValue pickListValue) {
        this.financialYear = pickListValue;
    }

    public Set<Zonal> getZonals() {
        return zonals;
    }

    public FinancialYearSettings zonals(Set<Zonal> zonals) {
        this.zonals = zonals;
        return this;
    }

    public FinancialYearSettings addZonal(Zonal zonal) {
        this.zonals.add(zonal);
        zonal.setFinancialYear(this);
        return this;
    }

    public FinancialYearSettings removeZonal(Zonal zonal) {
        this.zonals.remove(zonal);
        zonal.setFinancialYear(null);
        return this;
    }

    public void setZonals(Set<Zonal> zonals) {
        this.zonals = zonals;
    }

    public Set<Sector> getSectors() {
        return sectors;
    }

    public FinancialYearSettings sectors(Set<Sector> sectors) {
        this.sectors = sectors;
        return this;
    }

    public FinancialYearSettings addSector(Sector sector) {
        this.sectors.add(sector);
        sector.setFinancialYearSector(this);
        return this;
    }

    public FinancialYearSettings removeSector(Sector sector) {
        this.sectors.remove(sector);
        sector.setFinancialYearSector(null);
        return this;
    }

    public void setSectors(Set<Sector> sectors) {
        this.sectors = sectors;
    }

    public Set<Nursery> getNurseries() {
        return nurseries;
    }

    public FinancialYearSettings nurseries(Set<Nursery> nurseries) {
        this.nurseries = nurseries;
        return this;
    }

    public FinancialYearSettings addNursery(Nursery nursery) {
        this.nurseries.add(nursery);
        nursery.setFinancialYearNursery(this);
        return this;
    }

    public FinancialYearSettings removeNursery(Nursery nursery) {
        this.nurseries.remove(nursery);
        nursery.setFinancialYearNursery(null);
        return this;
    }

    public void setNurseries(Set<Nursery> nurseries) {
        this.nurseries = nurseries;
    }

    public Set<Batch> getBatches() {
        return batches;
    }

    public FinancialYearSettings batches(Set<Batch> batches) {
        this.batches = batches;
        return this;
    }

    public FinancialYearSettings addBatch(Batch batch) {
        this.batches.add(batch);
        batch.setFinancialYearBatch(this);
        return this;
    }

    public FinancialYearSettings removeBatch(Batch batch) {
        this.batches.remove(batch);
        batch.setFinancialYearBatch(null);
        return this;
    }

    public void setBatches(Set<Batch> batches) {
        this.batches = batches;
    }

    public Set<Damage> getDamages() {
        return damages;
    }

    public FinancialYearSettings damages(Set<Damage> damages) {
        this.damages = damages;
        return this;
    }

    public FinancialYearSettings addDamage(Damage damage) {
        this.damages.add(damage);
        damage.setFinancialYearDamage(this);
        return this;
    }

    public FinancialYearSettings removeDamage(Damage damage) {
        this.damages.remove(damage);
        damage.setFinancialYearDamage(null);
        return this;
    }

    public void setDamages(Set<Damage> damages) {
        this.damages = damages;
    }

    public Set<ShadeArea> getShadeAreas() {
        return shadeAreas;
    }

    public FinancialYearSettings shadeAreas(Set<ShadeArea> shadeAreas) {
        this.shadeAreas = shadeAreas;
        return this;
    }

    public FinancialYearSettings addShadeArea(ShadeArea shadeArea) {
        this.shadeAreas.add(shadeArea);
        shadeArea.setFinancialYearShadeArea(this);
        return this;
    }

    public FinancialYearSettings removeShadeArea(ShadeArea shadeArea) {
        this.shadeAreas.remove(shadeArea);
        shadeArea.setFinancialYearShadeArea(null);
        return this;
    }

    public void setShadeAreas(Set<ShadeArea> shadeAreas) {
        this.shadeAreas = shadeAreas;
    }

    public Set<NurseryStock> getNurseryStocks() {
        return nurseryStocks;
    }

    public FinancialYearSettings nurseryStocks(Set<NurseryStock> nurseryStocks) {
        this.nurseryStocks = nurseryStocks;
        return this;
    }

    public FinancialYearSettings addNurseryStock(NurseryStock nurseryStock) {
        this.nurseryStocks.add(nurseryStock);
        nurseryStock.setFinancialYearNurseryStock(this);
        return this;
    }

    public FinancialYearSettings removeNurseryStock(NurseryStock nurseryStock) {
        this.nurseryStocks.remove(nurseryStock);
        nurseryStock.setFinancialYearNurseryStock(null);
        return this;
    }

    public void setNurseryStocks(Set<NurseryStock> nurseryStocks) {
        this.nurseryStocks = nurseryStocks;
    }

    public Set<NurseryStockDetails> getNurseryStockDetails() {
        return nurseryStockDetails;
    }

    public FinancialYearSettings nurseryStockDetails(Set<NurseryStockDetails> nurseryStockDetails) {
        this.nurseryStockDetails = nurseryStockDetails;
        return this;
    }

    public FinancialYearSettings addNurseryStockDetails(NurseryStockDetails nurseryStockDetails) {
        this.nurseryStockDetails.add(nurseryStockDetails);
        nurseryStockDetails.setFinancialYearStockDetails(this);
        return this;
    }

    public FinancialYearSettings removeNurseryStockDetails(NurseryStockDetails nurseryStockDetails) {
        this.nurseryStockDetails.remove(nurseryStockDetails);
        nurseryStockDetails.setFinancialYearStockDetails(null);
        return this;
    }

    public void setNurseryStockDetails(Set<NurseryStockDetails> nurseryStockDetails) {
        this.nurseryStockDetails = nurseryStockDetails;
    }

    public Set<Godown> getGodowns() {
        return godowns;
    }

    public FinancialYearSettings godowns(Set<Godown> godowns) {
        this.godowns = godowns;
        return this;
    }

    public FinancialYearSettings addGodown(Godown godown) {
        this.godowns.add(godown);
        godown.setFinancialYearGodown(this);
        return this;
    }

    public FinancialYearSettings removeGodown(Godown godown) {
        this.godowns.remove(godown);
        godown.setFinancialYearGodown(null);
        return this;
    }

    public void setGodowns(Set<Godown> godowns) {
        this.godowns = godowns;
    }

    public Set<GodownStock> getGodownStocks() {
        return godownStocks;
    }

    public FinancialYearSettings godownStocks(Set<GodownStock> godownStocks) {
        this.godownStocks = godownStocks;
        return this;
    }

    public FinancialYearSettings addGodownStock(GodownStock godownStock) {
        this.godownStocks.add(godownStock);
        godownStock.setFinancialYearGodownStock(this);
        return this;
    }

    public FinancialYearSettings removeGodownStock(GodownStock godownStock) {
        this.godownStocks.remove(godownStock);
        godownStock.setFinancialYearGodownStock(null);
        return this;
    }

    public void setGodownStocks(Set<GodownStock> godownStocks) {
        this.godownStocks = godownStocks;
    }

    public Set<GodownStockDetails> getGodownStockDetails() {
        return godownStockDetails;
    }

    public FinancialYearSettings godownStockDetails(Set<GodownStockDetails> godownStockDetails) {
        this.godownStockDetails = godownStockDetails;
        return this;
    }

    public FinancialYearSettings addGodownStockDetails(GodownStockDetails godownStockDetails) {
        this.godownStockDetails.add(godownStockDetails);
        godownStockDetails.setFinancialYearGodownStockDetails(this);
        return this;
    }

    public FinancialYearSettings removeGodownStockDetails(GodownStockDetails godownStockDetails) {
        this.godownStockDetails.remove(godownStockDetails);
        godownStockDetails.setFinancialYearGodownStockDetails(null);
        return this;
    }

    public void setGodownStockDetails(Set<GodownStockDetails> godownStockDetails) {
        this.godownStockDetails = godownStockDetails;
    }

    public Set<GodownPurchaseDetails> getGodownPurchaseDetails() {
        return godownPurchaseDetails;
    }

    public FinancialYearSettings godownPurchaseDetails(Set<GodownPurchaseDetails> godownPurchaseDetails) {
        this.godownPurchaseDetails = godownPurchaseDetails;
        return this;
    }

    public FinancialYearSettings addGodownPurchaseDetails(GodownPurchaseDetails godownPurchaseDetails) {
        this.godownPurchaseDetails.add(godownPurchaseDetails);
        godownPurchaseDetails.setFinancialYearGodownPurchase(this);
        return this;
    }

    public FinancialYearSettings removeGodownPurchaseDetails(GodownPurchaseDetails godownPurchaseDetails) {
        this.godownPurchaseDetails.remove(godownPurchaseDetails);
        godownPurchaseDetails.setFinancialYearGodownPurchase(null);
        return this;
    }

    public void setGodownPurchaseDetails(Set<GodownPurchaseDetails> godownPurchaseDetails) {
        this.godownPurchaseDetails = godownPurchaseDetails;
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
        FinancialYearSettings financialYearSettings = (FinancialYearSettings) o;
        if (financialYearSettings.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), financialYearSettings.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FinancialYearSettings{" +
            "id=" + getId() +
            ", batchName='" + getBatchName() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
