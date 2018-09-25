/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs Sector Generation.
 *  Declared the table fields and data types for the Sector table.
 *  Defined the following Relation for the Sector Table :
 *  OneToMany Relation to Nursery
 *  ManyToOne Relation to Zonal, FinancialYearSettings
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
 * A Sector.
 */
@Entity
@Table(name = "sector")
public class Sector extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "sector_name", nullable = false)
    private String sectorName;

    @Column(name = "sector_address")
    private String sectorAddress;

    @Column(name = "status")
    private Integer status;

    @OneToMany(mappedBy = "sector", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<Nursery> nurserys = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("sectors")
    @JoinColumn(name="zonal_id", referencedColumnName="id")
    private Zonal zonal;

    @ManyToOne
    @JsonIgnoreProperties("sectors")
    private FinancialYearSettings financialYearSector;

    @OneToMany(mappedBy = "sector", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<SectorIncharge> incharges = new HashSet<>();

    @OneToMany(mappedBy = "sector", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<MapSectorWithZonal> mapSectorWithZonals = new HashSet<>();

    @OneToMany(mappedBy = "sector", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<MapNurseryWithSector> mapNurseryWithSectors = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSectorName() {
        return sectorName;
    }

    public Sector sectorName(String sectorName) {
        this.sectorName = sectorName;
        return this;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    public String getSectorAddress() {
        return sectorAddress;
    }

    public Sector sectorAddress(String sectorAddress) {
        this.sectorAddress = sectorAddress;
        return this;
    }

    public void setSectorAddress(String sectorAddress) {
        this.sectorAddress = sectorAddress;
    }

    public Integer getStatus() {
        return status;
    }

    public Sector status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<Nursery> getNurserys() {
        return nurserys;
    }

    public Sector nurserys(Set<Nursery> nurseries) {
        this.nurserys = nurseries;
        return this;
    }

    public Sector addNurserys(Nursery nursery) {
        this.nurserys.add(nursery);
        nursery.setSector(this);
        return this;
    }

    public Sector removeNurserys(Nursery nursery) {
        this.nurserys.remove(nursery);
        nursery.setSector(null);
        return this;
    }

    public void setNurserys(Set<Nursery> nurseries) {
        this.nurserys = nurseries;
    }

    public Zonal getZonal() {
        return zonal;
    }

    public Sector zonal(Zonal zonal) {
        this.zonal = zonal;
        return this;
    }

    public void setZonal(Zonal zonal) {
        this.zonal = zonal;
    }

    public FinancialYearSettings getFinancialYearSector() {
        return financialYearSector;
    }

    public Sector financialYearSector(FinancialYearSettings financialYearSettings) {
        this.financialYearSector = financialYearSettings;
        return this;
    }

    public void setFinancialYearSector(FinancialYearSettings financialYearSettings) {
        this.financialYearSector = financialYearSettings;
    }

    public Set<SectorIncharge> getIncharges() {
        return incharges;
    }

    public Sector incharges(Set<SectorIncharge> sectorIncharges) {
        this.incharges = sectorIncharges;
        return this;
    }

    public Sector addIncharge(SectorIncharge sectorIncharge) {
        this.incharges.add(sectorIncharge);
        sectorIncharge.setSector(this);
        return this;
    }

    public Sector removeIncharge(SectorIncharge sectorIncharge) {
        this.incharges.remove(sectorIncharge);
        sectorIncharge.setSector(null);
        return this;
    }

    public void setIncharges(Set<SectorIncharge> sectorIncharges) {
        this.incharges = sectorIncharges;
    }

    public Set<MapSectorWithZonal> getMapSectorWithZonals() {
        return mapSectorWithZonals;
    }

    public Sector mapSectorWithZonals(Set<MapSectorWithZonal> mapSectorWithZonals) {
        this.mapSectorWithZonals = mapSectorWithZonals;
        return this;
    }

    public Sector addMapSectorWithZonal(MapSectorWithZonal mapSectorWithZonal) {
        this.mapSectorWithZonals.add(mapSectorWithZonal);
        mapSectorWithZonal.setSector(this);
        return this;
    }

    public Sector removeMapSectorWithZonal(MapSectorWithZonal mapSectorWithZonal) {
        this.mapSectorWithZonals.remove(mapSectorWithZonal);
        mapSectorWithZonal.setSector(null);
        return this;
    }

    public void setMapSectorWithZonals(Set<MapSectorWithZonal> mapSectorWithZonals) {
        this.mapSectorWithZonals = mapSectorWithZonals;
    }

    public Set<MapNurseryWithSector> getMapNurseryWithSectors() {
        return mapNurseryWithSectors;
    }

    public Sector mapNurseryWithSectors(Set<MapNurseryWithSector> mapNurseryWithSectors) {
        this.mapNurseryWithSectors = mapNurseryWithSectors;
        return this;
    }

    public Sector addMapNurseryWithSector(MapNurseryWithSector mapNurseryWithSector) {
        this.mapNurseryWithSectors.add(mapNurseryWithSector);
        mapNurseryWithSector.setSector(this);
        return this;
    }

    public Sector removeMapNurseryWithSector(MapNurseryWithSector mapNurseryWithSector) {
        this.mapNurseryWithSectors.remove(mapNurseryWithSector);
        mapNurseryWithSector.setSector(null);
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
        Sector sector = (Sector) o;
        if (sector.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sector.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Sector{" +
            "id=" + getId() +
            ", sectorName='" + getSectorName() + "'" +
            ", sectorAddress='" + getSectorAddress() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
