/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date   : 2018/08/02
 *  Target : yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs MapZonalWithOh  Generation 
 *                        and declared the table fields, data types for 
 *                        MapZonalWithOh table.
 *  Relation for MapZonalWithOh : ManyToOne Relation
 *  ManyToOne Relation : Zonal, operationalHead
 *
 *******************************************************************************/
package com.niche.ng.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * MapZonalWithOh Domain Class
 * 
 * MapZonalWithOh class implements Serializable to convert instance class into 
 * series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
@Entity
@Table(name = "map_zonal_with_oh")
public class MapZonalWithOh extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "from_date")
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Integer status;

    /**
     * Relation Name : ManyToOne - mapZonalWithOhs
     * Table Name    : Connect the MapZonalWithOh Table to Zonal Table.
     * Used to point out the MapZonalWithOh values in the Zonal table.
     */
    @ManyToOne
    @JsonIgnoreProperties("mapZonalWithOhs")
    private Zonal zonal;

    /**
     * Relation Name : ManyToOne - mapZonalWithOhs
     * Table Name    : Connect the MapZonalWithOh Table to OperationalHead Table.
     * Used to point out the MapZonalWithOh values in the OperationalHead table.
     */
    @ManyToOne
    @JsonIgnoreProperties("mapZonalWithOhs")
    private OperationalHead operationalHead;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    /**
     * To Get the Id from MapZonalWithOh table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To set the id values in the MapZonalWithOh table.
     * 
     * @param id id of the MapZonalWithOh
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the fromDate from MapZonalWithOh table
     * 
     * @return fromDate
     */
    public LocalDate getFromDate() {
        return fromDate;
    }

    /**
     * To set the fromDate values in the MapZonalWithOh table.
     * 
     * @param fromDate fromDate of the MapZonalWithOh
     * @return this
     */
    public MapZonalWithOh fromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
        return this;
    }

    /**
     * To set the fromDate values in the MapZonalWithOh table.
     * 
     * @param fromDate fromDate of the MapZonalWithOh
     */
    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * To Get the toDate from MapZonalWithOh table
     * 
     * @return toDate
     */
    public LocalDate getToDate() {
        return toDate;
    }

    /**
     * To set the toDate values in the MapZonalWithOh table.
     * 
     * @param toDate toDate of the MapZonalWithOh
     * @return this
     */
    public MapZonalWithOh toDate(LocalDate toDate) {
        this.toDate = toDate;
        return this;
    }

     /**
     * To set the toDate values in the MapZonalWithOh table.
     * 
     * @param toDate toDate of the MapZonalWithOh
     */
    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    /**
     * To Get the description from MapZonalWithOh table
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * To set the description values in the MapZonalWithOh table.
     * 
     * @param description description of the MapZonalWithOh
     * @return this
     */
    public MapZonalWithOh description(String description) {
        this.description = description;
        return this;
    }

     /**
     * To set the description values in the MapZonalWithOh table.
     * 
     * @param description description of the MapZonalWithOh
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * To Get the status from MapZonalWithOh table
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To set the status values in the MapZonalWithOh table.
     * 
     * @param status status of the MapZonalWithOh
     * @return this
     */
    public MapZonalWithOh status(Integer status) {
        this.status = status;
        return this;
    }

     /**
     * To set the status values in the MapZonalWithOh table.
     * 
     * @param status status of the MapZonalWithOh
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the zonal from MapZonalWithOh table
     * 
     * @return zonal
     */
    public Zonal getZonal() {
        return zonal;
    }

    /**
     * To set the zonal values in the MapZonalWithOh table.
     * 
     * @param zonal zonal of the MapZonalWithOh
     * @return this
     */
    public MapZonalWithOh zonal(Zonal zonal) {
        this.zonal = zonal;
        return this;
    }

     /**
     * To set the zonal values in the MapZonalWithOh table.
     * 
     * @param zonal zonal of the MapZonalWithOh
     */
    public void setZonal(Zonal zonal) {
        this.zonal = zonal;
    }

    /**
     * To Get the operationalHead from MapZonalWithOh table
     * 
     * @return operationalHead
     */
    public OperationalHead getOperationalHead() {
        return operationalHead;
    }

    /**
     * To set the operationalHead values in the MapZonalWithOh table.
     * 
     * @param operationalHead operationalHead of the MapZonalWithOh
     * @return this
     */
    public MapZonalWithOh operationalHead(OperationalHead operationalHead) {
        this.operationalHead = operationalHead;
        return this;
    }

     /**
     * To set the operationalHead values in the MapZonalWithOh table.
     * 
     * @param operationalHead operationalHead of the MapZonalWithOh
     */
    public void setOperationalHead(OperationalHead operationalHead) {
        this.operationalHead = operationalHead;
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
        MapZonalWithOh mapZonalWithOh = (MapZonalWithOh) o;
        if (mapZonalWithOh.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mapZonalWithOh.getId());
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
        return "MapZonalWithOh{" +
            "id=" + getId() +
            ", fromDate='" + getFromDate() + "'" +
            ", toDate='" + getToDate() + "'" +
            ", description='" + getDescription() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
