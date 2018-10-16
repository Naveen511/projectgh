/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs Zonal Generation.
 *  Declared the table fields and data types for the Zonal table.
 *  Defined the following Relation for the Zonal Table :
 *  OneToMany Relation  : Sector, mapZonalWithOhs, zonalIncharges, mapSectorWithZonals
 *  ManyToOne Relation  : FinancialYearSettings, operationalHead
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
 * Zonal Domain Class
 * 
 * Zonal class implements Serializable to convert instance class into 
 * series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
@Entity
@Table(name = "zonal")
public class Zonal extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "zone_name", nullable = false)
    private String zoneName;

    @Column(name = "status")
    private Integer status;

    /**
     * Relation Name : OneToMany - sectors
     * Table Name    : Connects the Zonal Table to Sector Table
     * After Creation of the Zonal, If any movement it affect in sector Table
     */
    @OneToMany(mappedBy = "zonal", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<Sector> sectors = new HashSet<>();

    /**
     * Relation Name : ManyToOne - financialYear
     * Table Name    : Connects the Zonal Table to FinancialYearSettings Table
     * To get the financialYear.
     */
    @ManyToOne
    @JsonIgnoreProperties("zonals")
    private FinancialYearSettings financialYear;

    /**
     * Relation Name : ManyToOne - operationalHead
     * Table Name    : Connects the Zonal Table to OperationalHead Table
     * To get the operationalHead.
     */
    @ManyToOne
    @JsonIgnoreProperties("zonals")
    private OperationalHead operationalHead;

    /**
     * Relation Name : OneToMany - mapZonalWithOhs
     * Table Name    : Connects the Zonal Table to MapZonalWithOh Table
     * After Creation of the Zonal, If any movement it affect in MapZonalWithOh Table
     */
    @OneToMany(mappedBy = "zonal", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<MapZonalWithOh> mapZonalWithOhs = new HashSet<>();

    /**
     * Relation Name : OneToMany - zonalIncharges
     * Table Name    : Connects the Zonal Table to ZonalIncharge Table
     * After Creation of the Zonal, If assign a staff it affect in ZonalIncharge Table
     */
    @OneToMany(mappedBy = "zonal", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<ZonalIncharge> zonalIncharges = new HashSet<>();

    /**
     * Relation Name : OneToMany - mapSectorWithZonals
     * Table Name    : Connects the Zonal Table to MapSectorWithZonal Table
     * After Creation of the Zonal, If any movement it affect in MapSectorWithZonal Table
     */
    @OneToMany(mappedBy = "zonal", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<MapSectorWithZonal> mapSectorWithZonals = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    /**
     * To Get the Id from zonal table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To Set the Id for zonal table
     *
     * @param id the id in zonal table
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the zoneName from zonal table
     * 
     * @return zoneName
     */
    public String getZoneName() {
        return zoneName;
    }

    /**
     * To Set the zoneName for zonal table
     *
     * @param zoneName the zoneName in zonal table
     * @return this
     */
    public Zonal zoneName(String zoneName) {
        this.zoneName = zoneName;
        return this;
    }

    /**
     * To Set the zoneName for zonal table
     *
     * @param zoneName the zoneName in zonal table
     */
    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    /**
     * To Get the status from zonal table
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To Set the status for zonal table
     *
     * @param status the status in zonal table
     * @return this
     */
    public Zonal status(Integer status) {
        this.status = status;
        return this;
    }

    /**
     * To Set the status for zonal table
     *
     * @param status the status in zonal table
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the sectors from zonal table
     * 
     * @return sectors
     */
    public Set<Sector> getSectors() {
        return sectors;
    }

    /**
     * To Set the sectors for zonal table
     *
     * @param sectors the sectors in zonal table
     * @return this
     */
    public Zonal sectors(Set<Sector> sectors) {
        this.sectors = sectors;
        return this;
    }

    /**
     * To set the add sectors for the zonal table.
     * 
     * @param sector the sector in zonal
     * @return this
     */
    public Zonal addSectors(Sector sector) {
        this.sectors.add(sector);
        sector.setZonal(this);
        return this;
    }

    /**
     * To set the remove sectors for the zonal table.
     * 
     * @param sector the sector in zonal
     * @return this
     */
    public Zonal removeSectors(Sector sector) {
        this.sectors.remove(sector);
        sector.setZonal(null);
        return this;
    }

    /**
     * To Set the sectors for zonal table
     *
     * @param sectors the sectors in zonal table
     */
    public void setSectors(Set<Sector> sectors) {
        this.sectors = sectors;
    }

    /**
     * To Get the financialYear from zonal table
     * 
     * @return financialYear
     */
    public FinancialYearSettings getFinancialYear() {
        return financialYear;
    }

    /**
     * To Set the financialYear for zonal table
     *
     * @param financialYear the financialYear in zonal table
     * @return this
     */
    public Zonal financialYear(FinancialYearSettings financialYearSettings) {
        this.financialYear = financialYearSettings;
        return this;
    }

    /**
     * To Set the financialYear for zonal table
     *
     * @param financialYear the financialYear in zonal table
     */
    public void setFinancialYear(FinancialYearSettings financialYearSettings) {
        this.financialYear = financialYearSettings;
    }

    /**
     * To Get the operationalHead from zonal table
     * 
     * @return operationalHead
     */
    public OperationalHead getOperationalHead() {
        return operationalHead;
    }

    /**
     * To Set the operationalHead for zonal table
     *
     * @param operationalHead the operationalHead in zonal table
     * @return this
     */
    public Zonal operationalHead(OperationalHead operationalHead) {
        this.operationalHead = operationalHead;
        return this;
    }

    /**
     * To Set the operationalHead for zonal table
     *
     * @param operationalHead the operationalHead in zonal table
     */
    public void setOperationalHead(OperationalHead operationalHead) {
        this.operationalHead = operationalHead;
    }

    /**
     * To Get the mapZonalWithOhs from zonal table
     * 
     * @return mapZonalWithOhs
     */
    public Set<MapZonalWithOh> getMapZonalWithOhs() {
        return mapZonalWithOhs;
    }

    /**
     * To Set the mapZonalWithOhs for zonal table
     *
     * @param mapZonalWithOhs the mapZonalWithOhs in zonal table
     * @return this
     */
    public Zonal mapZonalWithOhs(Set<MapZonalWithOh> mapZonalWithOhs) {
        this.mapZonalWithOhs = mapZonalWithOhs;
        return this;
    }

    /**
     * To set the add mapZonalWithOhs for the zonal table.
     * 
     * @param mapZonalWithOh the mapZonalWithOh in zonal
     * @return this
     */
    public Zonal addMapZonalWithOh(MapZonalWithOh mapZonalWithOh) {
        this.mapZonalWithOhs.add(mapZonalWithOh);
        mapZonalWithOh.setZonal(this);
        return this;
    }

    /**
     * To set the remove mapZonalWithOhs for the zonal table.
     * 
     * @param mapZonalWithOh the mapZonalWithOh in zonal
     * @return this
     */
    public Zonal removeMapZonalWithOh(MapZonalWithOh mapZonalWithOh) {
        this.mapZonalWithOhs.remove(mapZonalWithOh);
        mapZonalWithOh.setZonal(null);
        return this;
    }

    /**
     * To Set the mapZonalWithOhs for zonal table
     *
     * @param mapZonalWithOhs the mapZonalWithOhs in zonal table
     */
    public void setMapZonalWithOhs(Set<MapZonalWithOh> mapZonalWithOhs) {
        this.mapZonalWithOhs = mapZonalWithOhs;
    }

    /**
     * To Get the zonalIncharges from zonal table
     * 
     * @return zonalIncharges
     */
    public Set<ZonalIncharge> getZonalIncharges() {
        return zonalIncharges;
    }

    /**
     * To Set the zonalIncharges for zonal table
     *
     * @param zonalIncharges the zonalIncharges in zonal table
     * @return this
     */
    public Zonal zonalIncharges(Set<ZonalIncharge> zonalIncharges) {
        this.zonalIncharges = zonalIncharges;
        return this;
    }

    /**
     * To set the add zonalIncharges for the zonal table.
     * 
     * @param zonalIncharge the zonalIncharge in zonal
     * @return this
     */
    public Zonal addZonalIncharge(ZonalIncharge zonalIncharge) {
        this.zonalIncharges.add(zonalIncharge);
        zonalIncharge.setZonal(this);
        return this;
    }

    /**
     * To set the remove zonalIncharges for the zonal table.
     * 
     * @param zonalIncharge the zonalIncharge in zonal
     * @return this
     */
    public Zonal removeZonalIncharge(ZonalIncharge zonalIncharge) {
        this.zonalIncharges.remove(zonalIncharge);
        zonalIncharge.setZonal(null);
        return this;
    }

    /**
     * To Set the zonalIncharges for zonal table
     *
     * @param zonalIncharges the zonalIncharges in zonal table
     */
    public void setZonalIncharges(Set<ZonalIncharge> zonalIncharges) {
        this.zonalIncharges = zonalIncharges;
    }

    /**
     * To Get the mapSectorWithZonals from zonal table
     * 
     * @return mapSectorWithZonals
     */
    public Set<MapSectorWithZonal> getMapSectorWithZonals() {
        return mapSectorWithZonals;
    }

    /**
     * To Set the mapSectorWithZonals for zonal table
     *
     * @param mapSectorWithZonals the mapSectorWithZonals in zonal table
     * @return this
     */
    public Zonal mapSectorWithZonals(Set<MapSectorWithZonal> mapSectorWithZonals) {
        this.mapSectorWithZonals = mapSectorWithZonals;
        return this;
    }

    /**
     * To set the add mapSectorWithZonals for the zonal table.
     * 
     * @param mapSectorWithZonal the mapSectorWithZonal in zonal
     * @return this
     */
    public Zonal addMapSectorWithZonal(MapSectorWithZonal mapSectorWithZonal) {
        this.mapSectorWithZonals.add(mapSectorWithZonal);
        mapSectorWithZonal.setZonal(this);
        return this;
    }

    /**
     * To set the remove mapSectorWithZonals for the zonal table.
     * 
     * @param mapSectorWithZonal the mapSectorWithZonal in zonal
     * @return this
     */
    public Zonal removeMapSectorWithZonal(MapSectorWithZonal mapSectorWithZonal) {
        this.mapSectorWithZonals.remove(mapSectorWithZonal);
        mapSectorWithZonal.setZonal(null);
        return this;
    }

    /**
     * To Set the mapSectorWithZonal for zonal table
     *
     * @param mapSectorWithZonals the mapSectorWithZonals in zonal table
     */
    public void setMapSectorWithZonals(Set<MapSectorWithZonal> mapSectorWithZonals) {
        this.mapSectorWithZonals = mapSectorWithZonals;
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
        Zonal zonal = (Zonal) o;
        if (zonal.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), zonal.getId());
    }

    /**
     * hash code for the id
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
        return "Zonal{" +
            "id=" + getId() +
            ", zoneName='" + getZoneName() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
