/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs Zonal Generation.
 *  Declared the table fields and data types for the Zonal table.
 *  Defined the following Relation for the Zonal Table :
 *  OneToMany Relation  :Sector Table
 *  ManyToOne Relation  : FinancialYearSettings
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
 * A Zonal.
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

    @Column(name = "zone_address")
    private String zoneAddress;

    @Column(name = "status")
    private Integer status;

    @OneToMany(mappedBy = "zonal", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<Sector> sectors = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("zonals")
    private FinancialYearSettings financialYear;

    @ManyToOne
    @JsonIgnoreProperties("zonals")
    private OperationalHead operationalHead;

    @OneToMany(mappedBy = "zonal", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<MapZonalWithOh> mapZonalWithOhs = new HashSet<>();

    @OneToMany(mappedBy = "zonal", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<ZonalIncharge> zonalIncharges = new HashSet<>();

    @OneToMany(mappedBy = "zonal", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<MapSectorWithZonal> mapSectorWithZonals = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZoneName() {
        return zoneName;
    }

    public Zonal zoneName(String zoneName) {
        this.zoneName = zoneName;
        return this;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getZoneAddress() {
        return zoneAddress;
    }

    public Zonal zoneAddress(String zoneAddress) {
        this.zoneAddress = zoneAddress;
        return this;
    }

    public void setZoneAddress(String zoneAddress) {
        this.zoneAddress = zoneAddress;
    }

    public Integer getStatus() {
        return status;
    }

    public Zonal status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<Sector> getSectors() {
        return sectors;
    }

    public Zonal sectors(Set<Sector> sectors) {
        this.sectors = sectors;
        return this;
    }

    public Zonal addSectors(Sector sector) {
        this.sectors.add(sector);
        sector.setZonal(this);
        return this;
    }

    public Zonal removeSectors(Sector sector) {
        this.sectors.remove(sector);
        sector.setZonal(null);
        return this;
    }

    public void setSectors(Set<Sector> sectors) {
        this.sectors = sectors;
    }

    public FinancialYearSettings getFinancialYear() {
        return financialYear;
    }

    public Zonal financialYear(FinancialYearSettings financialYearSettings) {
        this.financialYear = financialYearSettings;
        return this;
    }

    public void setFinancialYear(FinancialYearSettings financialYearSettings) {
        this.financialYear = financialYearSettings;
    }

    public OperationalHead getOperationalHead() {
        return operationalHead;
    }

    public Zonal operationalHead(OperationalHead operationalHead) {
        this.operationalHead = operationalHead;
        return this;
    }

    public void setOperationalHead(OperationalHead operationalHead) {
        this.operationalHead = operationalHead;
    }

    public Set<MapZonalWithOh> getMapZonalWithOhs() {
        return mapZonalWithOhs;
    }

    public Zonal mapZonalWithOhs(Set<MapZonalWithOh> mapZonalWithOhs) {
        this.mapZonalWithOhs = mapZonalWithOhs;
        return this;
    }

    public Zonal addMapZonalWithOh(MapZonalWithOh mapZonalWithOh) {
        this.mapZonalWithOhs.add(mapZonalWithOh);
        mapZonalWithOh.setZonal(this);
        return this;
    }

    public Zonal removeMapZonalWithOh(MapZonalWithOh mapZonalWithOh) {
        this.mapZonalWithOhs.remove(mapZonalWithOh);
        mapZonalWithOh.setZonal(null);
        return this;
    }

    public void setMapZonalWithOhs(Set<MapZonalWithOh> mapZonalWithOhs) {
        this.mapZonalWithOhs = mapZonalWithOhs;
    }

    public Set<ZonalIncharge> getZonalIncharges() {
        return zonalIncharges;
    }

    public Zonal zonalIncharges(Set<ZonalIncharge> zonalIncharges) {
        this.zonalIncharges = zonalIncharges;
        return this;
    }

    public Zonal addZonalIncharge(ZonalIncharge zonalIncharge) {
        this.zonalIncharges.add(zonalIncharge);
        zonalIncharge.setZonal(this);
        return this;
    }

    public Zonal removeZonalIncharge(ZonalIncharge zonalIncharge) {
        this.zonalIncharges.remove(zonalIncharge);
        zonalIncharge.setZonal(null);
        return this;
    }

    public void setZonalIncharges(Set<ZonalIncharge> zonalIncharges) {
        this.zonalIncharges = zonalIncharges;
    }

    public Set<MapSectorWithZonal> getMapSectorWithZonals() {
        return mapSectorWithZonals;
    }

    public Zonal mapSectorWithZonals(Set<MapSectorWithZonal> mapSectorWithZonals) {
        this.mapSectorWithZonals = mapSectorWithZonals;
        return this;
    }

    public Zonal addMapSectorWithZonal(MapSectorWithZonal mapSectorWithZonal) {
        this.mapSectorWithZonals.add(mapSectorWithZonal);
        mapSectorWithZonal.setZonal(this);
        return this;
    }

    public Zonal removeMapSectorWithZonal(MapSectorWithZonal mapSectorWithZonal) {
        this.mapSectorWithZonals.remove(mapSectorWithZonal);
        mapSectorWithZonal.setZonal(null);
        return this;
    }

    public void setMapSectorWithZonals(Set<MapSectorWithZonal> mapSectorWithZonals) {
        this.mapSectorWithZonals = mapSectorWithZonals;
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
        Zonal zonal = (Zonal) o;
        if (zonal.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), zonal.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Zonal{" +
            "id=" + getId() +
            ", zoneName='" + getZoneName() + "'" +
            ", zoneAddress='" + getZoneAddress() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
