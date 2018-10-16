/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date   : 2018/31/08
 *  Target : yarn
 *  -----------------------------------------------------------------------------
 *  File Description   : This file performs FinancialYearSettings  Generation 
 *                       and declared the table fields, data types for FinancialYearSettings table. 
 *  Relation for FinancialYearSettings : OneToMany Relation, ManyToOne Relation
 *  ManyToOne Relation : PickListValue
 *  OneToMany Relation : Zonal, Sector, Batch, Damage, ShadeArea, NurseryStock, 
 *                       NurseryStockDetails, Godown, GodownPurchaseDetails
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
 * FinancialYearSettings Domain Class
 * 
 * FinancialYearSettings class implements Serializable to convert instance class into 
 * series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
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

    /**
     * Relation Name : ManyToOne - financialYearNames
     * Table Name    : Connects the FinancialYearSettings Table to PickListValue Table.
     * Used to get the FinancialYearSettings values from the picklist value table.
     */
    @ManyToOne
    @JsonIgnoreProperties("financialYearNames")
    private PickListValue financialYear;

    /**
     * Relation Name : OneToMany - zonals
     * Table Name    : Connects the FinancialYearSettings Table to Zonal Table
     * Used to point out the FinancialYearSettings values in the Zonal table.
     */
    @OneToMany(mappedBy = "financialYear", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<Zonal> zonals = new HashSet<>();

    /**
     * Relation Name : OneToMany - sectors
     * Table Name    : Connects the FinancialYearSettings Table to Sector Table
     * Used to point out the FinancialYearSettings values in the Sector table.
     */
    @OneToMany(mappedBy = "financialYearSector", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<Sector> sectors = new HashSet<>();

    /**
     * Relation Name : OneToMany - nurseries
     * Table Name    : Connects the FinancialYearSettings Table to Nursery Table
     * Used to point out the FinancialYearSettings values in the Nursery table.
     */
    @OneToMany(mappedBy = "financialYearNursery", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<Nursery> nurseries = new HashSet<>();

    /**
     * Relation Name : OneToMany - batches
     * Table Name    : Connects the FinancialYearSettings Table to batch Table
     * Used to point out the FinancialYearSettings values in the Batch table.
     */
    @OneToMany(mappedBy = "financialYearBatch", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<Batch> batches = new HashSet<>();

    /**
     * Relation Name : OneToMany - damages
     * Table Name    : Connects the FinancialYearSettings Table to Damage Table
     * Used to point out the FinancialYearSettings values in the Damage table.
     */
    @OneToMany(mappedBy = "financialYearDamage", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<Damage> damages = new HashSet<>();

    /**
     * Relation Name : OneToMany - shadeAreas
     * Table Name    : Connects the FinancialYearSettings Table to ShadeArea Table
     * Used to point out the FinancialYearSettings values in the ShadeArea table.
     */
    @OneToMany(mappedBy = "financialYearShadeArea", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<ShadeArea> shadeAreas = new HashSet<>();

    /**
     * Relation Name : OneToMany - nurseryStocks
     * Table Name    : Connects the FinancialYearSettings Table to NurseryStock Table
     * Used to point out the FinancialYearSettings values in the NurseryStock table.
     */
    @OneToMany(mappedBy = "financialYearNurseryStock", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<NurseryStock> nurseryStocks = new HashSet<>();

    /**
     * Relation Name : OneToMany - nurseryStockDetails
     * Table Name    : Connects the FinancialYearSettings Table to NurseryStockDetails Table
     * Used to point out the FinancialYearSettings values in the NurseryStockDetails table.
     */
    @OneToMany(mappedBy = "financialYearStockDetails", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<NurseryStockDetails> nurseryStockDetails = new HashSet<>();

    /**
     * Relation Name : OneToMany - godowns
     * Table Name    : Connects the FinancialYearSettings Table to Godown Table
     * Used to point out the FinancialYearSettings values in the Godown table.
     */
    @OneToMany(mappedBy = "financialYearGodown", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<Godown> godowns = new HashSet<>();

    /**
     * Relation Name : OneToMany - godownStocks
     * Table Name    : Connects the FinancialYearSettings Table to GodownStock Table
     * Used to point out the FinancialYearSettings values in the GodownStock table.
     */
    @OneToMany(mappedBy = "financialYearGodownStock", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<GodownStock> godownStocks = new HashSet<>();

    /**
     * Relation Name : OneToMany - godownStockDetails
     * Table Name    : Connects the FinancialYearSettings Table to GodownStockDetails Table
     * Used to point out the FinancialYearSettings values in the GodownStockDetails table.
     */
    @OneToMany(mappedBy = "financialYearGodownStockDetails", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<GodownStockDetails> godownStockDetails = new HashSet<>();

    /**
     * Relation Name : OneToMany - godownPurchaseDetails
     * Table Name    : Connects the FinancialYearSettings Table to GodownPurchaseDetails Table
     * Used to point out the FinancialYearSettings values in the GodownPurchaseDetails table.
     */
    @OneToMany(mappedBy = "financialYearGodownPurchase", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<GodownPurchaseDetails> godownPurchaseDetails = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
     /**
     * To Get the Id from FinancialYearSettings table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To Set the Id for FinancialYearSettings table
     * 
     * @param id id value
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To get the batchName from the FinancialYearSettings table.
     * 
     * @return batchName
     */
    public String getBatchName() {
        return batchName;
    }

    /**
     * To set the batchName values for the FinancialYearSettings table.
     * 
     * @param batchName name of the batch
     * @return this
     */
    public FinancialYearSettings batchName(String batchName) {
        this.batchName = batchName;
        return this;
    }

    /**
     * To set the batchName values for the FinancialYearSettings table.
     * 
     * @param batchName name of the batch
     */
    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    /**
     * To get the startDate values for the FinancialYearSettings table.
     * 
     * @return startDate date of starting
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * To set the startDate values for the FinancialYearSettings table.
     * 
     * @param startDate date of starting
     * @return this
     */
    public FinancialYearSettings startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     * To set the startDate values for the FinancialYearSettings table.
     * 
     * @param startDate date of starting
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * To get the endDate values for the FinancialYearSettings table.
     * 
     * @return endDate
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * To set the endDate values for the FinancialYearSettings table.
     * 
     * @param endDate date of ending
     * @return this
     */
    public FinancialYearSettings endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    /**
     * To set the endDate values for the FinancialYearSettings table.
     * 
     * @param endDate date of ending
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * To get the status values for the FinancialYearSettings table.
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To set the status values for the FinancialYearSettings table.
     * 
     * @param status status in financial year setting table
     * @return this
     */
    public FinancialYearSettings status(Integer status) {
        this.status = status;
        return this;
    }

    /**
     * To set the status values for the FinancialYearSettings table.
     * 
     * @param status status in financial year setting table
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To get the financialYear values for the FinancialYearSettings table.
     * 
     * @return financialYear
     */
    public PickListValue getFinancialYear() {
        return financialYear;
    }

    /**
     * To set the financialYear values for the FinancialYearSettings table.
     * 
     * @param pickListValue values in picklist
     * @return this
     */
    public FinancialYearSettings financialYear(PickListValue pickListValue) {
        this.financialYear = pickListValue;
        return this;
    }

    /**
     * To set the financialYear values for the FinancialYearSettings table.
     * 
     * @param pickListValue values in picklist
     */
    public void setFinancialYear(PickListValue pickListValue) {
        this.financialYear = pickListValue;
    }

    /**
     * To get the zonal values for the FinancialYearSettings table.
     * 
     * @return zonals
     */
    public Set<Zonal> getZonals() {
        return zonals;
    }

    /**
     * To set the zonal values for the FinancialYearSettings table.
     * 
     * @param zonals zonal values
     * @return this
     */
    public FinancialYearSettings zonals(Set<Zonal> zonals) {
        this.zonals = zonals;
        return this;
    }

    /**
     * To set the add zonal values for the FinancialYearSettings table.
     * 
     * @param zonal adding of zonal values
     * @return this
     */
    public FinancialYearSettings addZonal(Zonal zonal) {
        this.zonals.add(zonal);
        zonal.setFinancialYear(this);
        return this;
    }

    /**
     * To set the remove zonal values for the FinancialYearSettings table.
     * 
     * @param zonal removing of zonal values
     * @return this
     */
    public FinancialYearSettings removeZonal(Zonal zonal) {
        this.zonals.remove(zonal);
        zonal.setFinancialYear(null);
        return this;
    }

    /**
     * To set the zonal values for the FinancialYearSettings table.
     * 
     * @param zonals set the zonal values
     */
    public void setZonals(Set<Zonal> zonals) {
        this.zonals = zonals;
    }

    /**
     * To get the sectors values for the FinancialYearSettings table.
     * 
     * @return sectors
     */
    public Set<Sector> getSectors() {
        return sectors;
    }

    /**
     * To set the sectors values for the FinancialYearSettings table.
     * 
     * @return this
     */
    public FinancialYearSettings sectors(Set<Sector> sectors) {
        this.sectors = sectors;
        return this;
    }

    /**
     * To add the sectors values for the FinancialYearSettings table.
     * 
     * @param sector add the sector value
     * @return this
     */
    public FinancialYearSettings addSector(Sector sector) {
        this.sectors.add(sector);
        sector.setFinancialYearSector(this);
        return this;
    }

    /**
     * To remove the sectors values for the FinancialYearSettings table.
     * 
     * @param sector remove the sector value
     * @return this
     */
    public FinancialYearSettings removeSector(Sector sector) {
        this.sectors.remove(sector);
        sector.setFinancialYearSector(null);
        return this;
    }

    /**
     * To set the sectors values for the FinancialYearSettings table.
     * 
     * @param sectors set the sector values
     */
    public void setSectors(Set<Sector> sectors) {
        this.sectors = sectors;
    }

    /**
     * To get the nurseries values for the FinancialYearSettings table.
     * 
     * @return nurseries
     */
    public Set<Nursery> getNurseries() {
        return nurseries;
    }

    /**
     * To set the nurseries values for the FinancialYearSettings table.
     * 
     * @param nurseries nursery values
     * @return this
     */
    public FinancialYearSettings nurseries(Set<Nursery> nurseries) {
        this.nurseries = nurseries;
        return this;
    }

    /**
     * To add the nursery values for the FinancialYearSettings table.
     * 
     * @param nursery add the nursery value
     * @return this
     */
    public FinancialYearSettings addNursery(Nursery nursery) {
        this.nurseries.add(nursery);
        nursery.setFinancialYearNursery(this);
        return this;
    }

    /**
     * To remove the nursery values for the FinancialYearSettings table.
     * 
     * @param nursery remove the nursery value
     * @return this
     */
    public FinancialYearSettings removeNursery(Nursery nursery) {
        this.nurseries.remove(nursery);
        nursery.setFinancialYearNursery(null);
        return this;
    }

    /**
     * To set the nursery values for the FinancialYearSettings table.
     * 
     * @param nurseries nursery values
     */
    public void setNurseries(Set<Nursery> nurseries) {
        this.nurseries = nurseries;
    }

    /**
     * To get the batch values for the FinancialYearSettings table.
     * 
     * @return batches 
     */
    public Set<Batch> getBatches() {
        return batches;
    }

    /**
     * To set the batch values for the FinancialYearSettings table.
     * 
     * @param batches batch values
     * @return this
     */
    public FinancialYearSettings batches(Set<Batch> batches) {
        this.batches = batches;
        return this;
    }

    /**
     * To add the batch values for the FinancialYearSettings table.
     * 
     * @param batch add batch values
     * @return this
     */
    public FinancialYearSettings addBatch(Batch batch) {
        this.batches.add(batch);
        batch.setFinancialYearBatch(this);
        return this;
    }

    /**
     * To remove the batch values for the FinancialYearSettings table.
     * 
     * @param batch remove batch values
     * @return this
     */
    public FinancialYearSettings removeBatch(Batch batch) {
        this.batches.remove(batch);
        batch.setFinancialYearBatch(null);
        return this;
    }

    /**
     * To set the batch values for the FinancialYearSettings table.
     * 
     * @param batches batch values
     */
    public void setBatches(Set<Batch> batches) {
        this.batches = batches;
    }

    /**
     * To get the damages values for the FinancialYearSettings table.
     * 
     * @return damages
     */
    public Set<Damage> getDamages() {
        return damages;
    }

    /**
     * To set the damages values for the FinancialYearSettings table.
     * 
     * @param damages damage values
     * @return this
     */
    public FinancialYearSettings damages(Set<Damage> damages) {
        this.damages = damages;
        return this;
    }

    /**
     * To add the damages values for the FinancialYearSettings table.
     * 
     * @param damage add damage values
     * @return this
     */
    public FinancialYearSettings addDamage(Damage damage) {
        this.damages.add(damage);
        damage.setFinancialYearDamage(this);
        return this;
    }

    /**
     * To remove the damages values for the FinancialYearSettings table.
     * 
     * @param damage remove add damage values
     * @return
     */
    public FinancialYearSettings removeDamage(Damage damage) {
        this.damages.remove(damage);
        damage.setFinancialYearDamage(null);
        return this;
    }

    /**
     * To set the damages values for the FinancialYearSettings table.
     * 
     * @param damages set the damage values
     */
    public void setDamages(Set<Damage> damages) {
        this.damages = damages;
    }

    /**
     * To get the shadeAreas values for the FinancialYearSettings table.
     * 
     * @return shadeAreas
     */
    public Set<ShadeArea> getShadeAreas() {
        return shadeAreas;
    }

    /**
     * To set the shadeAreas values for the FinancialYearSettings table.
     * 
     * @param shadeAreas shade area values
     * @return this
     */
    public FinancialYearSettings shadeAreas(Set<ShadeArea> shadeAreas) {
        this.shadeAreas = shadeAreas;
        return this;
    }

    /**
     * To add the shadeAreas values for the FinancialYearSettings table.
     * 
     * @param shadeArea add shade area values
     * @return
     */
    public FinancialYearSettings addShadeArea(ShadeArea shadeArea) {
        this.shadeAreas.add(shadeArea);
        shadeArea.setFinancialYearShadeArea(this);
        return this;
    }

    /**
     * To remove the shadeAreas values for the FinancialYearSettings table.
     * 
     * @param shadeArea remove shade area values
     * @return this
     */
    public FinancialYearSettings removeShadeArea(ShadeArea shadeArea) {
        this.shadeAreas.remove(shadeArea);
        shadeArea.setFinancialYearShadeArea(null);
        return this;
    }

    /**
     * To set the shadeAreas values for the FinancialYearSettings table.
     * 
     * @param shadeAreas set the shade area values
     */
    public void setShadeAreas(Set<ShadeArea> shadeAreas) {
        this.shadeAreas = shadeAreas;
    }

    /**
     * To get the nurseryStocks values for the FinancialYearSettings table.
     * 
     * @return nurseryStocks
     */
    public Set<NurseryStock> getNurseryStocks() {
        return nurseryStocks;
    }

    /**
     * To set the nurseryStocks values for the FinancialYearSettings table.
     * 
     * @param nurseryStocks set the nursery stock values
     * @return this
     */
    public FinancialYearSettings nurseryStocks(Set<NurseryStock> nurseryStocks) {
        this.nurseryStocks = nurseryStocks;
        return this;
    }

    /**
     * To add the nurseryStocks values for the FinancialYearSettings table.
     * 
     * @param nurseryStock add the nursery stock values
     * @return this
     */
    public FinancialYearSettings addNurseryStock(NurseryStock nurseryStock) {
        this.nurseryStocks.add(nurseryStock);
        nurseryStock.setFinancialYearNurseryStock(this);
        return this;
    }

    /**
     * To remove the nurseryStocks values for the FinancialYearSettings table.
     * 
     * @param nurseryStock remove the nursery stock values
     * @return this
     */
    public FinancialYearSettings removeNurseryStock(NurseryStock nurseryStock) {
        this.nurseryStocks.remove(nurseryStock);
        nurseryStock.setFinancialYearNurseryStock(null);
        return this;
    }

    /**
     * To set the nurseryStocks values for the FinancialYearSettings table.
     * 
     * @param nurseryStocks set the nursery stock values
     */
    public void setNurseryStocks(Set<NurseryStock> nurseryStocks) {
        this.nurseryStocks = nurseryStocks;
    }

    /**
     * To get the nurseryStockDetails values for the FinancialYearSettings table.
     * 
     * @return nurseryStockDetails
     */
    public Set<NurseryStockDetails> getNurseryStockDetails() {
        return nurseryStockDetails;
    }

    /**
     * To set the nurseryStockDetails values for the FinancialYearSettings table.
     * 
     * @param nurseryStockDetails set the nursery stock detail values
     * @return this
     */
    public FinancialYearSettings nurseryStockDetails(Set<NurseryStockDetails> nurseryStockDetails) {
        this.nurseryStockDetails = nurseryStockDetails;
        return this;
    }

    /**
     * To add the nurseryStockDetails values for the FinancialYearSettings table.
     * 
     * @param nurseryStockDetails add the nursery stock detail values
     * @return this
     */
    public FinancialYearSettings addNurseryStockDetails(NurseryStockDetails nurseryStockDetails) {
        this.nurseryStockDetails.add(nurseryStockDetails);
        nurseryStockDetails.setFinancialYearStockDetails(this);
        return this;
    }

    /**
     * To remove the nurseryStockDetails values for the FinancialYearSettings table.
     * 
     * @param nurseryStockDetails remove the nursery stock detail values
     * @return this
     */
    public FinancialYearSettings removeNurseryStockDetails(NurseryStockDetails nurseryStockDetails) {
        this.nurseryStockDetails.remove(nurseryStockDetails);
        nurseryStockDetails.setFinancialYearStockDetails(null);
        return this;
    }

    /**
     * To set the nurseryStockDetails values for the FinancialYearSettings table.
     * 
     * @param nurseryStockDetails set the nursery stock detail values
     */
    public void setNurseryStockDetails(Set<NurseryStockDetails> nurseryStockDetails) {
        this.nurseryStockDetails = nurseryStockDetails;
    }

    /**
     * To get the godowns values for the FinancialYearSettings table.
     * 
     * @return godowns
     */
    public Set<Godown> getGodowns() {
        return godowns;
    }

    /**
     * To set the godowns values for the FinancialYearSettings table.
     * 
     * @param godowns set the godown values
     * @return this
     */
    public FinancialYearSettings godowns(Set<Godown> godowns) {
        this.godowns = godowns;
        return this;
    }

    /**
     * To add the godowns values for the FinancialYearSettings table.
     * 
     * @param godown add the godown values
     * @return this
     */
    public FinancialYearSettings addGodown(Godown godown) {
        this.godowns.add(godown);
        godown.setFinancialYearGodown(this);
        return this;
    }

    /**
     * To remove the godowns values for the FinancialYearSettings table.
     * 
     * @param godown remove the godown values
     * @return this
     */
    public FinancialYearSettings removeGodown(Godown godown) {
        this.godowns.remove(godown);
        godown.setFinancialYearGodown(null);
        return this;
    }

    /**
     * To set the godowns values for the FinancialYearSettings table.
     * 
     * @param godowns set the godown values
     */
    public void setGodowns(Set<Godown> godowns) {
        this.godowns = godowns;
    }

    /**
     * To get the godownStocks values for the FinancialYearSettings table.
     * 
     * @return godownStocks
     */
    public Set<GodownStock> getGodownStocks() {
        return godownStocks;
    }

    /**
     * To set the godownStocks values for the FinancialYearSettings table.
     * 
     * @param godownStocks set the godown stocks
     * @return
     */
    public FinancialYearSettings godownStocks(Set<GodownStock> godownStocks) {
        this.godownStocks = godownStocks;
        return this;
    }

    /**
     * To add the godownStocks values for the FinancialYearSettings table.
     * 
     * @param godownStock add the godown stocks
     * @return this
     */
    public FinancialYearSettings addGodownStock(GodownStock godownStock) {
        this.godownStocks.add(godownStock);
        godownStock.setFinancialYearGodownStock(this);
        return this;
    }

    /**
     * To remove the godownStocks values for the FinancialYearSettings table.
     * 
     * @param godownStock remove the godown stocks
     * @return this
     */
    public FinancialYearSettings removeGodownStock(GodownStock godownStock) {
        this.godownStocks.remove(godownStock);
        godownStock.setFinancialYearGodownStock(null);
        return this;
    }

    /**
     * To set the godownStocks values for the FinancialYearSettings table.
     * 
     * @param godownStocks set the godown stock values
     */
    public void setGodownStocks(Set<GodownStock> godownStocks) {
        this.godownStocks = godownStocks;
    }

    /**
     * To get the godownStockDetails values for the FinancialYearSettings table.
     * 
     * @return godownStockDetails
     */
    public Set<GodownStockDetails> getGodownStockDetails() {
        return godownStockDetails;
    }

    /**
     * To set the godownStockDetails values for the FinancialYearSettings table.
     * 
     * @param godownStockDetails set the godown stock detail values
     * @return this
     */
    public FinancialYearSettings godownStockDetails(Set<GodownStockDetails> godownStockDetails) {
        this.godownStockDetails = godownStockDetails;
        return this;
    }

    /**
     * To add the godownStockDetails values for the FinancialYearSettings table.
     * 
     * @param godownStockDetails add the godown stock detail values
     * @return this
     */
    public FinancialYearSettings addGodownStockDetails(GodownStockDetails godownStockDetails) {
        this.godownStockDetails.add(godownStockDetails);
        godownStockDetails.setFinancialYearGodownStockDetails(this);
        return this;
    }

    /**
     * To remove the godownStockDetails values for the FinancialYearSettings table.
     * 
     * @param godownStockDetails remove the godown stock detail values
     * @return this
     */
    public FinancialYearSettings removeGodownStockDetails(GodownStockDetails godownStockDetails) {
        this.godownStockDetails.remove(godownStockDetails);
        godownStockDetails.setFinancialYearGodownStockDetails(null);
        return this;
    }

    /**
     * To set the godownStockDetails values for the FinancialYearSettings table.
     * 
     * @param godownStockDetails set the godown stock detail values
     */
    public void setGodownStockDetails(Set<GodownStockDetails> godownStockDetails) {
        this.godownStockDetails = godownStockDetails;
    }

    /**
     * To get the godownPurchaseDetails values for the FinancialYearSettings table.
     * 
     * @return godownPurchaseDetails
     */
    public Set<GodownPurchaseDetails> getGodownPurchaseDetails() {
        return godownPurchaseDetails;
    }

    /**
     * To set the godownPurchaseDetails values for the FinancialYearSettings table.
     * 
     * @param godownPurchaseDetails set the godown purchase detail values
     * @return this
     */
    public FinancialYearSettings godownPurchaseDetails(Set<GodownPurchaseDetails> godownPurchaseDetails) {
        this.godownPurchaseDetails = godownPurchaseDetails;
        return this;
    }

    /**
     * To add the godownPurchaseDetails values for the FinancialYearSettings table.
     * 
     * @param godownPurchaseDetails add the godown purchase detail values
     * @return this
     */
    public FinancialYearSettings addGodownPurchaseDetails(GodownPurchaseDetails godownPurchaseDetails) {
        this.godownPurchaseDetails.add(godownPurchaseDetails);
        godownPurchaseDetails.setFinancialYearGodownPurchase(this);
        return this;
    }

    /**
     * To remove the godownPurchaseDetails values for the FinancialYearSettings table.
     * 
     * @param godownPurchaseDetails remove the godown purchase detail values
     * @return this
     */
    public FinancialYearSettings removeGodownPurchaseDetails(GodownPurchaseDetails godownPurchaseDetails) {
        this.godownPurchaseDetails.remove(godownPurchaseDetails);
        godownPurchaseDetails.setFinancialYearGodownPurchase(null);
        return this;
    }

    /**
     * To set the godownPurchaseDetails values for the FinancialYearSettings table.
     * 
     * @param godownPurchaseDetails set the godown purchase detail values
     */
    public void setGodownPurchaseDetails(Set<GodownPurchaseDetails> godownPurchaseDetails) {
        this.godownPurchaseDetails = godownPurchaseDetails;
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
        FinancialYearSettings financialYearSettings = (FinancialYearSettings) o;
        if (financialYearSettings.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), financialYearSettings.getId());
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
        return "FinancialYearSettings{" +
            "id=" + getId() +
            ", batchName='" + getBatchName() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
