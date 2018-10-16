/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date   : 2018/08/02
 *  Target : yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs MapNurseryWithSector  Generation 
 *                        and declared the table fields, data types for 
 *                        MapNurseryWithSector table.
 *  Relation for MapNurseryWithSector : ManyToOne Relation
 *  ManyToOne Relation : Nursery, Sector
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
 * MapNurseryWithSector Domain Class
 * 
 * MapNurseryWithSector class implements Serializable to convert instance class into 
 * series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
@Entity
@Table(name = "map_nursery_with_sector")
public class MapNurseryWithSector extends AbstractAuditingEntity implements Serializable {

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

    @Column(name = "status")
    private Integer status;

    /**
     * Relation Name : ManyToOne - mapNurseryWithSectors
     * Table Name    : Connects the MapNurseryWithSector Table to Nursery Table.
     * Used to point out the MapNurseryWithSector values in the Nursery table.
     */
    @ManyToOne
    @JsonIgnoreProperties("mapNurseryWithSectors")
    private Nursery nursery;

    /**
     * Relation Name : ManyToOne - mapNurseryWithSectors
     * Table Name    : Connects the MapNurseryWithSector Table to Sector Table.
     * Used to point out the MapNurseryWithSector values in the Sector table.
     */
    @ManyToOne
    @JsonIgnoreProperties("mapNurseryWithSectors")
    private Sector sector;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    /**
     * To Get the Id from MapNurseryWithSector table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To set the id values in the MapNurseryWithSector table.
     * 
     * @param id id of the MapNurseryWithSector
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the fromDate from MapNurseryWithSector table
     * 
     * @return fromDate
     */
    public LocalDate getFromDate() {
        return fromDate;
    }

    /**
     * To set the fromDate values in the MapNurseryWithSector table.
     * 
     * @param fromDate fromDate of the MapNurseryWithSector
     * @return this
     */
    public MapNurseryWithSector fromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
        return this;
    }

    /**
     * To set the fromDate values in the MapNurseryWithSector table.
     * 
     * @param fromDate fromDate of the MapNurseryWithSector
     */
    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * To Get the toDate from MapNurseryWithSector table
     * 
     * @return toDate
     */
    public LocalDate getToDate() {
        return toDate;
    }

    /**
     * To set the toDate values in the MapNurseryWithSector table.
     * 
     * @param toDate toDate of the MapNurseryWithSector
     * @return this
     */
    public MapNurseryWithSector toDate(LocalDate toDate) {
        this.toDate = toDate;
        return this;
    }

    /**
     * To set the toDate values in the MapNurseryWithSector table.
     * 
     * @param toDate toDate of the MapNurseryWithSector
     */
    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    /**
     * To Get the description from MapNurseryWithSector table
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * To set the description values in the MapNurseryWithSector table.
     * 
     * @param description description of the MapNurseryWithSector
     * @return this
     */
    public MapNurseryWithSector description(String description) {
        this.description = description;
        return this;
    }

    /**
     * To set the description values in the MapNurseryWithSector table.
     * 
     * @param description description of the MapNurseryWithSector
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * To Get the status from MapNurseryWithSector table
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To set the status values in the MapNurseryWithSector table.
     * 
     * @param status status of the MapNurseryWithSector
     * @return this
     */
    public MapNurseryWithSector status(Integer status) {
        this.status = status;
        return this;
    }

    /**
     * To set the status values in the MapNurseryWithSector table.
     * 
     * @param status status of the MapNurseryWithSector
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the nursery from MapNurseryWithSector table
     * 
     * @return nursery
     */
    public Nursery getNursery() {
        return nursery;
    }

    /**
     * To set the nursery values in the MapNurseryWithSector table.
     * 
     * @param nursery nursery of the MapNurseryWithSector
     * @return this
     */
    public MapNurseryWithSector nursery(Nursery nursery) {
        this.nursery = nursery;
        return this;
    }

    /**
     * To set the nursery values in the MapNurseryWithSector table.
     * 
     * @param nursery nursery of the MapNurseryWithSector
     */
    public void setNursery(Nursery nursery) {
        this.nursery = nursery;
    }

    /**
     * To Get the sector from MapNurseryWithSector table
     * 
     * @return sector
     */
    public Sector getSector() {
        return sector;
    }

    /**
     * To set the sector values in the MapNurseryWithSector table.
     * 
     * @param sector sector of the MapNurseryWithSector
     * @return this
     */
    public MapNurseryWithSector sector(Sector sector) {
        this.sector = sector;
        return this;
    }

    /**
     * To set the sector values in the MapNurseryWithSector table.
     * 
     * @param sector sector of the MapNurseryWithSector
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
        MapNurseryWithSector mapNurseryWithSector = (MapNurseryWithSector) o;
        if (mapNurseryWithSector.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mapNurseryWithSector.getId());
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
        return "MapNurseryWithSector{" +
            "id=" + getId() +
            ", fromDate='" + getFromDate() + "'" +
            ", toDate='" + getToDate() + "'" +
            ", description='" + getDescription() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
