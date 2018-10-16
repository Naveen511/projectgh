/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date   : 2018/08/02
 *  Target : yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs MapSectorWithZonal  Generation 
 *                        and declared the table fields, data types for 
 *                        MapSectorWithZonal table.
 *  Relation for MapSectorWithZonal : ManyToOne Relation
 *  ManyToOne Relation : Zonal, Sector
 *
 *******************************************************************************/
package com.niche.ng.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * MapSectorWithZonal Domain Class
 * 
 * MapSectorWithZonal class implements Serializable to convert instance class into 
 * series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
@Entity
@Table(name = "map_sector_with_zonal")
public class MapSectorWithZonal extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "from_date", nullable = false)
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;

    @Column(name = "description")
    private String description;

    @Max(value = 10)
    @Column(name = "status")
    private Integer status;

    /**
     * Relation Name : ManyToOne - mapSectorWithZonals
     * Table Name    : Connects the MapSectorWithZonal Table to Zonal Table.
     * Used to point out the MapSectorWithZonal values in the Zonal table.
     */
    @ManyToOne
    @JsonIgnoreProperties("mapSectorWithZonals")
    private Zonal zonal;

    /**
     * Relation Name : ManyToOne - mapSectorWithZonals
     * Table Name    : Connects the MapSectorWithZonal Table to Sector Table.
     * Used to point out the MapSectorWithZonal values in the Sector table.
     */
    @ManyToOne
    @JsonIgnoreProperties("mapSectorWithZonals")
    private Sector sector;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    /**
     * To Get the Id from MapSectorWithZonal table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To set the id values in the MapSectorWithZonal table.
     * 
     * @param id id of the MapSectorWithZonal
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the fromDate from MapSectorWithZonal table
     * 
     * @return fromDate
     */
    public LocalDate getFromDate() {
        return fromDate;
    }

    /**
     * To set the fromDate values in the MapSectorWithZonal table.
     * 
     * @param fromDate fromDate of the MapSectorWithZonal
     * @return this
     */
    public MapSectorWithZonal fromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
        return this;
    }

    /**
     * To set the fromDate values in the MapSectorWithZonal table.
     * 
     * @param fromDate fromDate of the MapSectorWithZonal
     */
    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * To Get the toDate from MapSectorWithZonal table
     * 
     * @return toDate
     */
    public LocalDate getToDate() {
        return toDate;
    }

    /**
     * To set the toDate values in the MapSectorWithZonal table.
     * 
     * @param toDate toDate of the MapSectorWithZonal
     * @return this
     */
    public MapSectorWithZonal toDate(LocalDate toDate) {
        this.toDate = toDate;
        return this;
    }

    /**
     * To set the toDate values in the MapSectorWithZonal table.
     * 
     * @param toDate toDate of the MapSectorWithZonal
     */
    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    /**
     * To Get the description from MapSectorWithZonal table
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * To set the description values in the MapSectorWithZonal table.
     * 
     * @param description description of the MapSectorWithZonal
     * @return this
     */
    public MapSectorWithZonal description(String description) {
        this.description = description;
        return this;
    }

    /**
     * To set the description values in the MapSectorWithZonal table.
     * 
     * @param description description of the MapSectorWithZonal
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * To Get the status from MapSectorWithZonal table
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To set the status values in the MapSectorWithZonal table.
     * 
     * @param status status of the MapSectorWithZonal
     * @return this
     */
    public MapSectorWithZonal status(Integer status) {
        this.status = status;
        return this;
    }

    /**
     * To set the status values in the MapSectorWithZonal table.
     * 
     * @param status status of the MapSectorWithZonal
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the zonal from MapSectorWithZonal table
     * 
     * @return zonal
     */
    public Zonal getZonal() {
        return zonal;
    }

    /**
     * To set the zonal values in the MapSectorWithZonal table.
     * 
     * @param zonal zonal of the MapSectorWithZonal
     * @return this
     */
    public MapSectorWithZonal zonal(Zonal zonal) {
        this.zonal = zonal;
        return this;
    }

    /**
     * To set the zonal values in the MapSectorWithZonal table.
     * 
     * @param zonal zonal of the MapSectorWithZonal
     */
    public void setZonal(Zonal zonal) {
        this.zonal = zonal;
    }

    /**
     * To Get the sector from MapSectorWithZonal table
     * 
     * @return sector
     */
    public Sector getSector() {
        return sector;
    }

    /**
     * To set the sector values in the MapSectorWithZonal table.
     * 
     * @param sector sector of the MapSectorWithZonal
     * @return this
     */
    public MapSectorWithZonal sector(Sector sector) {
        this.sector = sector;
        return this;
    }

    /**
     * To set the sector values in the MapSectorWithZonal table.
     * 
     * @param sector sector of the MapSectorWithZonal
     */
    public void setSector(Sector sector) {
        this.sector = sector;
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
        MapSectorWithZonal mapSectorWithZonal = (MapSectorWithZonal) o;
        if (mapSectorWithZonal.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mapSectorWithZonal.getId());
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
        return "MapSectorWithZonal{" +
            "id=" + getId() +
            ", fromDate='" + getFromDate() + "'" +
            ", toDate='" + getToDate() + "'" +
            ", description='" + getDescription() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
