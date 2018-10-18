/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs Nursery  Generation 
 *                        and declared the table fields, data types for 
 *                        Nursery table.
 *  Relation for Nursery : ManyToOne Relation, OneToMany Relation
 *  OneToMany Relation : Batch, MotherBed, NurseryInventory, NurseryStockDetails, 
 *                       NurseryStock
 *  ManyToOne Relation : Sector, PickList Variety, and Picklist Category, 
 *                       FinancialYearSettings
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
 * Nursery Domain Class
 * 
 * Nursery class implements Serializable to convert instance class into 
 * series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
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

     /**
     * Relation Name : OneToMany - batchs
     * Table Name    : Connect the Nursery Table to Batch Table
     * Used to point out the Nursery values in the Batch table.
     */
    @OneToMany(mappedBy = "nursery", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<Batch> batchs = new HashSet<>();

     /**
     * Relation Name : OneToMany - nurseryStocks
     * Table Name    : Connect the Nursery Table to NurseryStock Table
     * Used to point out the Nursery values in the NurseryStock table.
     */
    @OneToMany(mappedBy = "nursery", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<NurseryStock> nurseryStocks = new HashSet<>();

    /**
     * Relation Name : ManyToOne - nurserys
     * Table Name    : Connect the Nursery Table to Sector Table.
     * Used to point out the Nursery values in the Sector table.
     */
    @ManyToOne
    @JsonIgnoreProperties("nurserys")
    @JoinColumn(name="sector_id", referencedColumnName="id")
    private Sector sector;

    /**
     * Relation Name : ManyToOne - nurserys
     * Table Name    : Connect the Nursery Table to PickListValue Table.
     * Used to point out the Nursery values in the PickListValue table.
     */
    @ManyToOne
    @JsonIgnoreProperties("nurserys")
    private PickListValue nurseryType;

     /**
     * Relation Name : OneToMany - motherBeds
     * Table Name    : Connect the Nursery Table to MotherBed Table
     * Used to point out the Nursery values in the MotherBed table.
     */
    @OneToMany(mappedBy = "nursery", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<MotherBed> motherBeds = new HashSet<>();

     /**
     * Relation Name : OneToMany - nurseryInventorys
     * Table Name    : Connect the Nursery Table to NurseryInventory Table
     * Used to point out the Nursery values in the NurseryInventory table.
     */
    @OneToMany(mappedBy = "nurserys", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<NurseryInventory> nurseryInventorys = new HashSet<>();

    /**
     * Relation Name : OneToMany - nurseryStockDetails
     * Table Name    : Connect the Nursery Table to NurseryStockDetails Table
     * Used to point out the Nursery values in the NurseryStockDetails table.
     */
    @OneToMany(mappedBy = "itNursery", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<NurseryStockDetails> nurseryStockDetails = new HashSet<>();

    /**
     * Relation Name : ManyToOne - nurseries
     * Table Name    : Connect the Nursery Table to FinancialYearSettings Table.
     * Used to point out the Nursery values in the FinancialYearSettings table.
     */
    @ManyToOne
    @JsonIgnoreProperties("nurseries")
    private FinancialYearSettings financialYearNursery;

     /**
     * Relation Name : OneToMany - incharges
     * Table Name    : Connect the Nursery Table to NurseryIncharge Table
     * Used to point out the Nursery values in the NurseryIncharge table.
     */
    @OneToMany(mappedBy = "nursery", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<NurseryIncharge> incharges = new HashSet<>();

     /**
     * Relation Name : OneToMany - mapNurseryWithSectors
     * Table Name    : Connect the Nursery Table to MapNurseryWithSector Table
     * Used to point out the Nursery values in the MapNurseryWithSector table.
     */
    @OneToMany(mappedBy = "nursery", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<MapNurseryWithSector> mapNurseryWithSectors = new HashSet<>();

    /**
     * Relation Name : OneToMany - fromNurseryStockDetails
     * Table Name    : Connect the Nursery Table to NurseryStockDetails Table
     * Used to point out the Nursery values in the NurseryStockDetails table.
     */
    @OneToMany(mappedBy = "fromNurseryStockDetails", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<NurseryStockDetails> fromNurseryStockDetails = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    /**
     * To Get the Id from Nursery table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To set the id values in the Nursery table.
     * 
     * @param id id of the Nursery
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the nurseryName from Nursery table
     * 
     * @return nurseryName
     */
    public String getNurseryName() {
        return nurseryName;
    }

     /**
     * To set the nurseryName values in the Nursery table.
     * 
     * @param nurseryName nurseryName of the Nursery
     * @return this
     */
    public Nursery nurseryName(String nurseryName) {
        this.nurseryName = nurseryName;
        return this;
    }

     /**
     * To set the nurseryName in the Nursery table.
     * 
     * @param nurseryName nurseryName of the Nursery
     */
    public void setNurseryName(String nurseryName) {
        this.nurseryName = nurseryName;
    }

    /**
     * To Get the nurseryAddress from Nursery table
     * 
     * @return nurseryAddress
     */
    public String getNurseryAddress() {
        return nurseryAddress;
    }

     /**
     * To set the nurseryAddress values in the Nursery table.
     * 
     * @param nurseryAddress nurseryAddress of the Nursery
     * @return this
     */
    public Nursery nurseryAddress(String nurseryAddress) {
        this.nurseryAddress = nurseryAddress;
        return this;
    }

     /**
     * To set the nurseryAddress in the Nursery table.
     * 
     * @param nurseryAddress nurseryAddress of the Nursery
     */
    public void setNurseryAddress(String nurseryAddress) {
        this.nurseryAddress = nurseryAddress;
    }

    /**
     * To Get the status from Nursery table
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

     /**
     * To set the status values in the Nursery table.
     * 
     * @param status status of the Nursery
     * @return this
     */
    public Nursery status(Integer status) {
        this.status = status;
        return this;
    }

     /**
     * To set the status in the Nursery table.
     * 
     * @param status status of the Nursery
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the code from Nursery table
     * 
     * @return code
     */
    public String getCode() {
        return code;
    }

     /**
     * To set the code values in the Nursery table.
     * 
     * @param code code of the Nursery
     * @return this
     */
    public Nursery code(String code) {
        this.code = code;
        return this;
    }

     /**
     * To set the code in the Nursery table.
     * 
     * @param code code of the Nursery
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * To Get the batchs from Nursery table
     * 
     * @return batchs
     */
    public Set<Batch> getBatchs() {
        return batchs;
    }

     /**
     * To set the batches values in the Nursery table.
     * 
     * @param batches batches of the Nursery
     * @return this
     */
    public Nursery batchs(Set<Batch> batches) {
        this.batchs = batches;
        return this;
    }

     /**
     * To set the batch values in the Nursery table.
     * 
     * @param batch batch of the Nursery
     * @return this
     */
    public Nursery addBatchs(Batch batch) {
        this.batchs.add(batch);
        batch.setNursery(this);
        return this;
    }

     /**
     * To set the batch values in the Nursery table.
     * 
     * @param batch batch of the Nursery
     * @return this
     */
    public Nursery removeBatchs(Batch batch) {
        this.batchs.remove(batch);
        batch.setNursery(null);
        return this;
    }

     /**
     * To set the batches in the Nursery table.
     * 
     * @param batches batches of the Nursery
     */
    public void setBatchs(Set<Batch> batches) {
        this.batchs = batches;
    }

    /**
     * To Get the nurseryStocks from Nursery table
     * 
     * @return nurseryStocks
     */
    public Set<NurseryStock> getNurseryStocks() {
        return nurseryStocks;
    }

     /**
     * To set the nurseryStocks values in the Nursery table.
     * 
     * @param nurseryStocks nurseryStocks of the Nursery
     * @return this
     */
    public Nursery nurseryStocks(Set<NurseryStock> nurseryStocks) {
        this.nurseryStocks = nurseryStocks;
        return this;
    }

     /**
     * To set the nurseryStocks values in the Nursery table.
     * 
     * @param nurseryStocks nurseryStocks of the Nursery
     * @return this
     */
    public Nursery addNurseryStocks(NurseryStock nurseryStock) {
        this.nurseryStocks.add(nurseryStock);
        nurseryStock.setNursery(this);
        return this;
    }

     /**
     * To set the nurseryStocks values in the Nursery table.
     * 
     * @param nurseryStocks nurseryStocks of the Nursery
     * @return this
     */
    public Nursery removeNurseryStocks(NurseryStock nurseryStock) {
        this.nurseryStocks.remove(nurseryStock);
        nurseryStock.setNursery(null);
        return this;
    }

     /**
     * To set the nurseryStocks in the Nursery table.
     * 
     * @param nurseryStocks nurseryStocks of the Nursery
     */
    public void setNurseryStocks(Set<NurseryStock> nurseryStocks) {
        this.nurseryStocks = nurseryStocks;
    }

    /**
     * To Get the sector from Nursery table
     * 
     * @return sector
     */
    public Sector getSector() {
        return sector;
    }

     /**
     * To set the sector values in the Nursery table.
     * 
     * @param sector sector of the Nursery
     * @return this
     */
    public Nursery sector(Sector sector) {
        this.sector = sector;
        return this;
    }

     /**
     * To set the sector in the Nursery table.
     * 
     * @param sector sector of the Nursery
     */
    public void setSector(Sector sector) {
        this.sector = sector;
    }

    /**
     * To Get the nurseryType from Nursery table
     * 
     * @return nurseryType
     */
    public PickListValue getNurseryType() {
        return nurseryType;
    }

     /**
     * To set the pickListValue values in the Nursery table.
     * 
     * @param pickListValue pickListValue of the Nursery
     * @return this
     */
    public Nursery nurseryType(PickListValue pickListValue) {
        this.nurseryType = pickListValue;
        return this;
    }

     /**
     * To set the pickListValue in the Nursery table.
     * 
     * @param pickListValue pickListValue of the Nursery
     */
    public void setNurseryType(PickListValue pickListValue) {
        this.nurseryType = pickListValue;
    }

    /**
     * To Get the motherBeds from Nursery table
     * 
     * @return motherBeds
     */
    public Set<MotherBed> getMotherBeds() {
        return motherBeds;
    }

     /**
     * To set the motherBeds values in the Nursery table.
     * 
     * @param motherBeds motherBeds of the Nursery
     * @return this
     */
    public Nursery motherBeds(Set<MotherBed> motherBeds) {
        this.motherBeds = motherBeds;
        return this;
    }

     /**
     * To set the motherBed values in the Nursery table.
     * 
     * @param motherBed motherBed of the Nursery
     * @return this
     */
    public Nursery addMotherBeds(MotherBed motherBed) {
        this.motherBeds.add(motherBed);
        motherBed.setNursery(this);
        return this;
    }

     /**
     * To set the motherBed values in the Nursery table.
     * 
     * @param motherBed motherBed of the Nursery
     * @return this
     */
    public Nursery removeMotherBeds(MotherBed motherBed) {
        this.motherBeds.remove(motherBed);
        motherBed.setNursery(null);
        return this;
    }

     /**
     * To set the motherBeds in the Nursery table.
     * 
     * @param motherBeds motherBeds of the Nursery
     */
    public void setMotherBeds(Set<MotherBed> motherBeds) {
        this.motherBeds = motherBeds;
    }

    /**
     * To Get the nurseryInventorys from Nursery table
     * 
     * @return nurseryInventorys
     */
    public Set<NurseryInventory> getNurseryInventorys() {
        return nurseryInventorys;
    }

     /**
     * To set the nurseryInventories values in the Nursery table.
     * 
     * @param nurseryInventories nurseryInventories of the Nursery
     * @return this
     */
    public Nursery nurseryInventorys(Set<NurseryInventory> nurseryInventories) {
        this.nurseryInventorys = nurseryInventories;
        return this;
    }

     /**
     * To set the nurseryInventory values in the Nursery table.
     * 
     * @param nurseryInventory nurseryInventory of the Nursery
     * @return this
     */
    public Nursery addNurseryInventorys(NurseryInventory nurseryInventory) {
        this.nurseryInventorys.add(nurseryInventory);
        nurseryInventory.setNurserys(this);
        return this;
    }

     /**
     * To set the nurseryInventory values in the Nursery table.
     * 
     * @param nurseryInventory nurseryInventory of the Nursery
     * @return this
     */
    public Nursery removeNurseryInventorys(NurseryInventory nurseryInventory) {
        this.nurseryInventorys.remove(nurseryInventory);
        nurseryInventory.setNurserys(null);
        return this;
    }

     /**
     * To set the nurseryInventories in the Nursery table.
     * 
     * @param nurseryInventories nurseryInventories of the Nursery
     */
    public void setNurseryInventorys(Set<NurseryInventory> nurseryInventories) {
        this.nurseryInventorys = nurseryInventories;
    }

    /**
     * To Get the nurseryStockDetails from Nursery table
     * 
     * @return nurseryStockDetails
     */
    public Set<NurseryStockDetails> getNurseryStockDetails() {
        return nurseryStockDetails;
    }

     /**
     * To set the nurseryStockDetails values in the Nursery table.
     * 
     * @param nurseryStockDetails nurseryStockDetails of the Nursery
     * @return this
     */
    public Nursery nurseryStockDetails(Set<NurseryStockDetails> nurseryStockDetails) {
        this.nurseryStockDetails = nurseryStockDetails;
        return this;
    }

     /**
     * To set the nurseryStockDetails values in the Nursery table.
     * 
     * @param nurseryStockDetails nurseryStockDetails of the Nursery
     * @return this
     */
    public Nursery addNurseryStockDetails(NurseryStockDetails nurseryStockDetails) {
        this.nurseryStockDetails.add(nurseryStockDetails);
        nurseryStockDetails.setItNursery(this);
        return this;
    }

     /**
     * To set the nurseryStockDetails values in the Nursery table.
     * 
     * @param nurseryStockDetails nurseryStockDetails of the Nursery
     * @return this
     */
    public Nursery removeNurseryStockDetails(NurseryStockDetails nurseryStockDetails) {
        this.nurseryStockDetails.remove(nurseryStockDetails);
        nurseryStockDetails.setItNursery(null);
        return this;
    }

     /**
     * To set the nurseryStockDetails in the Nursery table.
     * 
     * @param nurseryStockDetails nurseryStockDetails of the Nursery
     */
    public void setNurseryStockDetails(Set<NurseryStockDetails> nurseryStockDetails) {
        this.nurseryStockDetails = nurseryStockDetails;
    }

    /**
     * To Get the financialYearNursery from Nursery table
     * 
     * @return financialYearNursery
     */
    public FinancialYearSettings getFinancialYearNursery() {
        return financialYearNursery;
    }

     /**
     * To set the financialYearSettings values in the Nursery table.
     * 
     * @param financialYearSettings financialYearSettings of the Nursery
     * @return this
     */
    public Nursery financialYearNursery(FinancialYearSettings financialYearSettings) {
        this.financialYearNursery = financialYearSettings;
        return this;
    }

     /**
     * To set the financialYearSettings in the Nursery table.
     * 
     * @param financialYearSettings financialYearSettings of the Nursery
     */
    public void setFinancialYearNursery(FinancialYearSettings financialYearSettings) {
        this.financialYearNursery = financialYearSettings;
    }

    /**
     * To Get the incharges from Nursery table
     * 
     * @return incharges
     */
    public Set<NurseryIncharge> getIncharges() {
        return incharges;
    }

     /**
     * To set the nurseryIncharges values in the Nursery table.
     * 
     * @param nurseryIncharges nurseryIncharges of the Nursery
     * @return this
     */
    public Nursery incharges(Set<NurseryIncharge> nurseryIncharges) {
        this.incharges = nurseryIncharges;
        return this;
    }

     /**
     * To set the nurseryIncharge values in the Nursery table.
     * 
     * @param nurseryIncharge nurseryIncharge of the Nursery
     * @return this
     */
    public Nursery addIncharge(NurseryIncharge nurseryIncharge) {
        this.incharges.add(nurseryIncharge);
        nurseryIncharge.setNursery(this);
        return this;
    }

     /**
     * To set the nurseryIncharge values in the Nursery table.
     * 
     * @param nurseryIncharge nurseryIncharge of the Nursery
     * @return this
     */
    public Nursery removeIncharge(NurseryIncharge nurseryIncharge) {
        this.incharges.remove(nurseryIncharge);
        nurseryIncharge.setNursery(null);
        return this;
    }

     /**
     * To set the nurseryIncharge in the Nursery table.
     * 
     * @param nurseryIncharge nurseryIncharge of the Nursery
     */
    public void setIncharges(Set<NurseryIncharge> nurseryIncharges) {
        this.incharges = nurseryIncharges;
    }

    /**
     * To Get the mapNurseryWithSectors from Nursery table
     * 
     * @return mapNurseryWithSectors
     */
    public Set<MapNurseryWithSector> getMapNurseryWithSectors() {
        return mapNurseryWithSectors;
    }

     /**
     * To set the mapNurseryWithSector values in the Nursery table.
     * 
     * @param mapNurseryWithSector mapNurseryWithSector of the Nursery
     * @return this
     */
    public Nursery mapNurseryWithSectors(Set<MapNurseryWithSector> mapNurseryWithSectors) {
        this.mapNurseryWithSectors = mapNurseryWithSectors;
        return this;
    }

     /**
     * To set the mapNurseryWithSector values in the Nursery table.
     * 
     * @param mapNurseryWithSector mapNurseryWithSector of the Nursery
     * @return this
     */
    public Nursery addMapNurseryWithSector(MapNurseryWithSector mapNurseryWithSector) {
        this.mapNurseryWithSectors.add(mapNurseryWithSector);
        mapNurseryWithSector.setNursery(this);
        return this;
    }

    /**
     * To set the mapNurseryWithSector values in the Nursery table.
     * 
     * @param mapNurseryWithSector mapNurseryWithSector of the Nursery
     * @return this
     */
    public Nursery removeMapNurseryWithSector(MapNurseryWithSector mapNurseryWithSector) {
        this.mapNurseryWithSectors.remove(mapNurseryWithSector);
        mapNurseryWithSector.setNursery(null);
        return this;
    }

    /**
     * To set the mapNurseryWithSectors in the Nursery table.
     * 
     * @param mapNurseryWithSectors nurseryName of the Nursery
     */
    public void setMapNurseryWithSectors(Set<MapNurseryWithSector> mapNurseryWithSectors) {
        this.mapNurseryWithSectors = mapNurseryWithSectors;
    }

    /**
     * To Set the fromNurseryStockDetails from Nursery table
     * 
     * @return fromNurseryStockDetails
     */
    public Set<NurseryStockDetails> getFromNurseryStockDetails() {
        return fromNurseryStockDetails;
    }

    /**
     * To set the fromNurseryStockDetails values in the Nursery table.
     * 
     * @param fromNurseryStockDetails fromNurseryStockDetails of the Nursery
     * @return this
     */
    public Nursery fromNurseryStockDetails(Set<NurseryStockDetails> nurseryStockDetails) {
        this.fromNurseryStockDetails = nurseryStockDetails;
        return this;
    }

    /**
     * To add fromNurseryStockDetails values in the Nursery table.
     * 
     * @param fromNurseryStockDetails fromNurseryStockDetails of the Nursery
     * @return this
     */
    public Nursery addFromNurseryStockDetails(NurseryStockDetails nurseryStockDetails) {
        this.fromNurseryStockDetails.add(nurseryStockDetails);
        nurseryStockDetails.setFromNurseryStockDetails(this);
        return this;
    }

    /**
     * To removed fromNurseryStockDetails values in the Nursery table.
     * 
     * @param fromNurseryStockDetails fromNurseryStockDetails of the Nursery
     * @return this
     */
    public Nursery removeFromNurseryStockDetails(NurseryStockDetails nurseryStockDetails) {
        this.fromNurseryStockDetails.remove(nurseryStockDetails);
        nurseryStockDetails.setFromNurseryStockDetails(null);
        return this;
    }

    /**
     * To set the fromNurseryStockDetails in the Nursery table.
     * 
     * @param fromNurseryStockDetails fromNurseryStockDetails of the Nursery
     */
    public void setFromNurseryStockDetails(Set<NurseryStockDetails> nurseryStockDetails) {
        this.fromNurseryStockDetails = nurseryStockDetails;
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
        Nursery nursery = (Nursery) o;
        if (nursery.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nursery.getId());
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
        return "Nursery{" +
            "id=" + getId() +
            ", nurseryName='" + getNurseryName() + "'" +
            ", nurseryAddress='" + getNurseryAddress() + "'" +
            ", status=" + getStatus() +
            ", code='" + getCode() + "'" +
            "}";
    }
}
