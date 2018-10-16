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
 *  OneToMany Relation  : Nursery, SectorIncharge, MapSectorWithZonal,
                          MapNurseryWithSector
 *  ManyToOne Relation  : Zonal, FinancialYearSettings
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
 * Sector Domain Class
 * 
 * Sector class implements Serializable to convert instance class into 
 * series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
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

    @Column(name = "status")
    private Integer status;

    /**
     * Relation Name : OneToMany - nurserys
     * Table Name    : Connects the sector Table to nursery Table
     * After Creation of the Sector, If any movement it affect in nursery Table
     */
    @OneToMany(mappedBy = "sector", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<Nursery> nurserys = new HashSet<>();

    /**
     * Relation Name : ManyToOne - zonal
     * Table Name    : Connects the Sector Table to Zonal Table
     * To get the zonal.
     */
    @ManyToOne
    @JsonIgnoreProperties("sectors")
    @JoinColumn(name="zonal_id", referencedColumnName="id")
    private Zonal zonal;

    /**
     * Relation Name : ManyToOne - financialYearSector
     * Table Name    : Connects the Sector Table to FinancialYearSettings Table
     * To get the financialYearSector.
     */
    @ManyToOne
    @JsonIgnoreProperties("sectors")
    private FinancialYearSettings financialYearSector;

    /**
     * Relation Name : OneToMany - incharges
     * Table Name    : Connects the Sector Table to SectorIncharge Table
     * After Creation of the Sector, If assign a staff it affect in SectorIncharge Table
     */
    @OneToMany(mappedBy = "sector", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<SectorIncharge> incharges = new HashSet<>();

    /**
     * Relation Name : OneToMany - mapSectorWithZonals
     * Table Name    : Connects the Sector Table to MapSectorWithZonal Table
     * After Creation of the Sector, If any movement it affect in MapSectorWithZonal Table
     */
    @OneToMany(mappedBy = "sector", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<MapSectorWithZonal> mapSectorWithZonals = new HashSet<>();

    /**
     * Relation Name : OneToMany - mapNurseryWithSectors
     * Table Name    : Connects the Sector Table to MapNurseryWithSector Table
     * After Creation of the Sector, If any movement it affect in MapNurseryWithSector Table
     */
    @OneToMany(mappedBy = "sector", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<MapNurseryWithSector> mapNurseryWithSectors = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    /**
     * To Get the Id from sector table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To Set the Id for sector table
     *
     * @param id the id in sector table
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the sectorName from sector table
     * 
     * @return sectorName
     */
    public String getSectorName() {
        return sectorName;
    }

    /**
     * To Set the sectorName for sector table
     *
     * @param sectorName the sectorName in sector table
     * @return this
     */
    public Sector sectorName(String sectorName) {
        this.sectorName = sectorName;
        return this;
    }

    /**
     * To Set the sectorName for sector table
     *
     * @param sectorName the sectorName in sector table
     */
    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    /**
     * To Get the status from sector table
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To Set the status for sector table
     *
     * @param status the status in sector table
     * @return this
     */
    public Sector status(Integer status) {
        this.status = status;
        return this;
    }

    /**
     * To Set the status for sector table
     *
     * @param status the status in sector table
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the nurserys from sector table
     * 
     * @return nurserys
     */
    public Set<Nursery> getNurserys() {
        return nurserys;
    }

    /**
     * To Set the nurserys for sector table
     *
     * @param nurseries the nurseries in sector table
     * @return this
     */
    public Sector nurserys(Set<Nursery> nurseries) {
        this.nurserys = nurseries;
        return this;
    }

    /**
     * To set the add nurserys for the sector table.
     * 
     * @param nursery the nursery in sector
     * @return this
     */
    public Sector addNurserys(Nursery nursery) {
        this.nurserys.add(nursery);
        nursery.setSector(this);
        return this;
    }

    /**
     * To set the remove nurserys for the sector table.
     * 
     * @param nursery the nursery in sector
     * @return this
     */
    public Sector removeNurserys(Nursery nursery) {
        this.nurserys.remove(nursery);
        nursery.setSector(null);
        return this;
    }

    /**
     * To Set the nurserys for sector table
     *
     * @param nurseries the nurseries in sector table
     */
    public void setNurserys(Set<Nursery> nurseries) {
        this.nurserys = nurseries;
    }

    /**
     * To Get the zonal from sector table
     * 
     * @return zonal
     */
    public Zonal getZonal() {
        return zonal;
    }

    /**
     * To Set the zonal for sector table
     *
     * @param zonal the zonal in sector table
     * @return this
     */
    public Sector zonal(Zonal zonal) {
        this.zonal = zonal;
        return this;
    }

    /**
     * To Set the zonal for sector table
     *
     * @param zonal the zonal in sector table
     */
    public void setZonal(Zonal zonal) {
        this.zonal = zonal;
    }

    /**
     * To Get the financialYearSector from sector table
     * 
     * @return financialYearSector
     */
    public FinancialYearSettings getFinancialYearSector() {
        return financialYearSector;
    }

    /**
     * To Set the financialYearSector for sector table
     *
     * @param financialYearSettings the financialYearSettings in sector table
     * @return this
     */
    public Sector financialYearSector(FinancialYearSettings financialYearSettings) {
        this.financialYearSector = financialYearSettings;
        return this;
    }

    /**
     * To Set the financialYearSector for sector table
     *
     * @param financialYearSettings the financialYearSettings in sector table
     */
    public void setFinancialYearSector(FinancialYearSettings financialYearSettings) {
        this.financialYearSector = financialYearSettings;
    }

    /**
     * To Get the incharges from sector table
     * 
     * @return incharges
     */
    public Set<SectorIncharge> getIncharges() {
        return incharges;
    }

    /**
     * To Set the incharges for sector table
     *
     * @param sectorIncharges the sectorIncharges in sector table
     * @return this
     */
    public Sector incharges(Set<SectorIncharge> sectorIncharges) {
        this.incharges = sectorIncharges;
        return this;
    }

    /**
     * To set the add incharges for the sector table.
     * 
     * @param sectorIncharge the sectorIncharge in sector
     * @return this
     */
    public Sector addIncharge(SectorIncharge sectorIncharge) {
        this.incharges.add(sectorIncharge);
        sectorIncharge.setSector(this);
        return this;
    }

    /**
     * To set the remove incharges for the sector table.
     * 
     * @param sectorIncharge the sectorIncharge in sector
     * @return this
     */
    public Sector removeIncharge(SectorIncharge sectorIncharge) {
        this.incharges.remove(sectorIncharge);
        sectorIncharge.setSector(null);
        return this;
    }

    /**
     * To Set the incharges for sector table
     *
     * @param sectorIncharges the sectorIncharges in sector table
     */
    public void setIncharges(Set<SectorIncharge> sectorIncharges) {
        this.incharges = sectorIncharges;
    }

    /**
     * To Get the mapSectorWithZonals from sector table
     * 
     * @return mapSectorWithZonals
     */
    public Set<MapSectorWithZonal> getMapSectorWithZonals() {
        return mapSectorWithZonals;
    }

    /**
     * To Set the mapSectorWithZonals for sector table
     *
     * @param mapSectorWithZonals the mapSectorWithZonals in sector table
     * @return this
     */
    public Sector mapSectorWithZonals(Set<MapSectorWithZonal> mapSectorWithZonals) {
        this.mapSectorWithZonals = mapSectorWithZonals;
        return this;
    }

    /**
     * To set the add mapSectorWithZonals for the sector table.
     * 
     * @param mapSectorWithZonal the mapSectorWithZonal in sector
     * @return this
     */
    public Sector addMapSectorWithZonal(MapSectorWithZonal mapSectorWithZonal) {
        this.mapSectorWithZonals.add(mapSectorWithZonal);
        mapSectorWithZonal.setSector(this);
        return this;
    }

    /**
     * To set the remove mapSectorWithZonals for the sector table.
     * 
     * @param mapSectorWithZonal the mapSectorWithZonal in sector
     * @return this
     */
    public Sector removeMapSectorWithZonal(MapSectorWithZonal mapSectorWithZonal) {
        this.mapSectorWithZonals.remove(mapSectorWithZonal);
        mapSectorWithZonal.setSector(null);
        return this;
    }

    /**
     * To Set the mapSectorWithZonals for sector table
     *
     * @param mapSectorWithZonals the mapSectorWithZonals in sector table
     */
    public void setMapSectorWithZonals(Set<MapSectorWithZonal> mapSectorWithZonals) {
        this.mapSectorWithZonals = mapSectorWithZonals;
    }

    /**
     * To Get the mapNurseryWithSectors from sector table
     * 
     * @return mapNurseryWithSectors
     */
    public Set<MapNurseryWithSector> getMapNurseryWithSectors() {
        return mapNurseryWithSectors;
    }

    /**
     * To Set the mapNurseryWithSectors for sector table
     *
     * @param mapNurseryWithSectors the mapNurseryWithSectors in sector table
     * @return this
     */
    public Sector mapNurseryWithSectors(Set<MapNurseryWithSector> mapNurseryWithSectors) {
        this.mapNurseryWithSectors = mapNurseryWithSectors;
        return this;
    }

    /**
     * To set the add mapNurseryWithSectors for the sector table.
     * 
     * @param mapNurseryWithSector the mapNurseryWithSector in sector
     * @return this
     */
    public Sector addMapNurseryWithSector(MapNurseryWithSector mapNurseryWithSector) {
        this.mapNurseryWithSectors.add(mapNurseryWithSector);
        mapNurseryWithSector.setSector(this);
        return this;
    }

    /**
     * To set the remove mapNurseryWithSectors for the sector table.
     * 
     * @param mapNurseryWithSector the mapNurseryWithSector in sector
     * @return this
     */
    public Sector removeMapNurseryWithSector(MapNurseryWithSector mapNurseryWithSector) {
        this.mapNurseryWithSectors.remove(mapNurseryWithSector);
        mapNurseryWithSector.setSector(null);
        return this;
    }

    /**
     * To Set the mapNurseryWithSectors for sector table
     *
     * @param mapNurseryWithSectors the mapNurseryWithSectors in sector table
     */
    public void setMapNurseryWithSectors(Set<MapNurseryWithSector> mapNurseryWithSectors) {
        this.mapNurseryWithSectors = mapNurseryWithSectors;
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
        Sector sector = (Sector) o;
        if (sector.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sector.getId());
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
        return "Sector{" +
            "id=" + getId() +
            ", sectorName='" + getSectorName() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
