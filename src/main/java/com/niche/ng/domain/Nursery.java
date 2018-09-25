/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs Nursery Generation.
 *  Declared the table fields and data types for the Nursery table.
 *  Defined the following Relation for the Nursery Table :
 *  OneToMany Relation to Batch, MotherBed, NurseryInventory, NurseryStockDetails, NurseryStock
 *  ManyToOne Relation to Sector, PickList Variety, and Picklist Category, FinancialYearSettings
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
 * A Nursery.
 */
@Entity
@Table(name = "nursery")
public class Nursery extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "nursery_name", nullable = false)
    private String nurseryName;

    @Column(name = "nursery_address")
    private String nurseryAddress;

    @Column(name = "status")
    private Integer status;

    @Column(name = "code")
    private String code;

    @OneToMany(mappedBy = "nursery", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<Batch> batchs = new HashSet<>();

    @OneToMany(mappedBy = "nursery", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<NurseryStock> nurseryStocks = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("nurserys")
    @JoinColumn(name="sector_id", referencedColumnName="id")
    private Sector sector;

    @ManyToOne
    @JsonIgnoreProperties("nurserys")
    private PickListValue nurseryType;

    @OneToMany(mappedBy = "nursery", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<MotherBed> motherBeds = new HashSet<>();

    @OneToMany(mappedBy = "nurserys", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<NurseryInventory> nurseryInventorys = new HashSet<>();

    @OneToMany(mappedBy = "itNursery", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<NurseryStockDetails> nurseryStockDetails = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("nurseries")
    private FinancialYearSettings financialYearNursery;

    @OneToMany(mappedBy = "nursery", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<NurseryIncharge> incharges = new HashSet<>();

    @OneToMany(mappedBy = "nursery", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<MapNurseryWithSector> mapNurseryWithSectors = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNurseryName() {
        return nurseryName;
    }

    public Nursery nurseryName(String nurseryName) {
        this.nurseryName = nurseryName;
        return this;
    }

    public void setNurseryName(String nurseryName) {
        this.nurseryName = nurseryName;
    }

    public String getNurseryAddress() {
        return nurseryAddress;
    }

    public Nursery nurseryAddress(String nurseryAddress) {
        this.nurseryAddress = nurseryAddress;
        return this;
    }

    public void setNurseryAddress(String nurseryAddress) {
        this.nurseryAddress = nurseryAddress;
    }

    public Integer getStatus() {
        return status;
    }

    public Nursery status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public Nursery code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<Batch> getBatchs() {
        return batchs;
    }

    public Nursery batchs(Set<Batch> batches) {
        this.batchs = batches;
        return this;
    }

    public Nursery addBatchs(Batch batch) {
        this.batchs.add(batch);
        batch.setNursery(this);
        return this;
    }

    public Nursery removeBatchs(Batch batch) {
        this.batchs.remove(batch);
        batch.setNursery(null);
        return this;
    }

    public void setBatchs(Set<Batch> batches) {
        this.batchs = batches;
    }

    public Set<NurseryStock> getNurseryStocks() {
        return nurseryStocks;
    }

    public Nursery nurseryStocks(Set<NurseryStock> nurseryStocks) {
        this.nurseryStocks = nurseryStocks;
        return this;
    }

    public Nursery addNurseryStocks(NurseryStock nurseryStock) {
        this.nurseryStocks.add(nurseryStock);
        nurseryStock.setNursery(this);
        return this;
    }

    public Nursery removeNurseryStocks(NurseryStock nurseryStock) {
        this.nurseryStocks.remove(nurseryStock);
        nurseryStock.setNursery(null);
        return this;
    }

    public void setNurseryStocks(Set<NurseryStock> nurseryStocks) {
        this.nurseryStocks = nurseryStocks;
    }

    public Sector getSector() {
        return sector;
    }

    public Nursery sector(Sector sector) {
        this.sector = sector;
        return this;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public PickListValue getNurseryType() {
        return nurseryType;
    }

    public Nursery nurseryType(PickListValue pickListValue) {
        this.nurseryType = pickListValue;
        return this;
    }

    public void setNurseryType(PickListValue pickListValue) {
        this.nurseryType = pickListValue;
    }

    public Set<MotherBed> getMotherBeds() {
        return motherBeds;
    }

    public Nursery motherBeds(Set<MotherBed> motherBeds) {
        this.motherBeds = motherBeds;
        return this;
    }

    public Nursery addMotherBeds(MotherBed motherBed) {
        this.motherBeds.add(motherBed);
        motherBed.setNursery(this);
        return this;
    }

    public Nursery removeMotherBeds(MotherBed motherBed) {
        this.motherBeds.remove(motherBed);
        motherBed.setNursery(null);
        return this;
    }

    public void setMotherBeds(Set<MotherBed> motherBeds) {
        this.motherBeds = motherBeds;
    }

    public Set<NurseryInventory> getNurseryInventorys() {
        return nurseryInventorys;
    }

    public Nursery nurseryInventorys(Set<NurseryInventory> nurseryInventories) {
        this.nurseryInventorys = nurseryInventories;
        return this;
    }

    public Nursery addNurseryInventorys(NurseryInventory nurseryInventory) {
        this.nurseryInventorys.add(nurseryInventory);
        nurseryInventory.setNurserys(this);
        return this;
    }

    public Nursery removeNurseryInventorys(NurseryInventory nurseryInventory) {
        this.nurseryInventorys.remove(nurseryInventory);
        nurseryInventory.setNurserys(null);
        return this;
    }

    public void setNurseryInventorys(Set<NurseryInventory> nurseryInventories) {
        this.nurseryInventorys = nurseryInventories;
    }

    public Set<NurseryStockDetails> getNurseryStockDetails() {
        return nurseryStockDetails;
    }

    public Nursery nurseryStockDetails(Set<NurseryStockDetails> nurseryStockDetails) {
        this.nurseryStockDetails = nurseryStockDetails;
        return this;
    }

    public Nursery addNurseryStockDetails(NurseryStockDetails nurseryStockDetails) {
        this.nurseryStockDetails.add(nurseryStockDetails);
        nurseryStockDetails.setItNursery(this);
        return this;
    }

    public Nursery removeNurseryStockDetails(NurseryStockDetails nurseryStockDetails) {
        this.nurseryStockDetails.remove(nurseryStockDetails);
        nurseryStockDetails.setItNursery(null);
        return this;
    }

    public void setNurseryStockDetails(Set<NurseryStockDetails> nurseryStockDetails) {
        this.nurseryStockDetails = nurseryStockDetails;
    }

    public FinancialYearSettings getFinancialYearNursery() {
        return financialYearNursery;
    }

    public Nursery financialYearNursery(FinancialYearSettings financialYearSettings) {
        this.financialYearNursery = financialYearSettings;
        return this;
    }

    public void setFinancialYearNursery(FinancialYearSettings financialYearSettings) {
        this.financialYearNursery = financialYearSettings;
    }

    public Set<NurseryIncharge> getIncharges() {
        return incharges;
    }

    public Nursery incharges(Set<NurseryIncharge> nurseryIncharges) {
        this.incharges = nurseryIncharges;
        return this;
    }

    public Nursery addIncharge(NurseryIncharge nurseryIncharge) {
        this.incharges.add(nurseryIncharge);
        nurseryIncharge.setNursery(this);
        return this;
    }

    public Nursery removeIncharge(NurseryIncharge nurseryIncharge) {
        this.incharges.remove(nurseryIncharge);
        nurseryIncharge.setNursery(null);
        return this;
    }

    public void setIncharges(Set<NurseryIncharge> nurseryIncharges) {
        this.incharges = nurseryIncharges;
    }

    public Set<MapNurseryWithSector> getMapNurseryWithSectors() {
        return mapNurseryWithSectors;
    }

    public Nursery mapNurseryWithSectors(Set<MapNurseryWithSector> mapNurseryWithSectors) {
        this.mapNurseryWithSectors = mapNurseryWithSectors;
        return this;
    }

    public Nursery addMapNurseryWithSector(MapNurseryWithSector mapNurseryWithSector) {
        this.mapNurseryWithSectors.add(mapNurseryWithSector);
        mapNurseryWithSector.setNursery(this);
        return this;
    }

    public Nursery removeMapNurseryWithSector(MapNurseryWithSector mapNurseryWithSector) {
        this.mapNurseryWithSectors.remove(mapNurseryWithSector);
        mapNurseryWithSector.setNursery(null);
        return this;
    }

    public void setMapNurseryWithSectors(Set<MapNurseryWithSector> mapNurseryWithSectors) {
        this.mapNurseryWithSectors = mapNurseryWithSectors;
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
        Nursery nursery = (Nursery) o;
        if (nursery.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nursery.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Nursery{" +
            "id=" + getId() +
            ", nurseryName='" + getNurseryName() + "'" +
            ", nurseryAddress='" + getNurseryAddress() + "'" +
            ", status=" + getStatus() +
            ", code='" + getCode() + "'" +
            "}";
    }
}
