/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/09/12
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs OperationalHead Generation.
 *  Declared the table fields and data types for the OperationalHead table.
 *  Defined the following Relation for the OperationalHead Table :
 *  OneToMany Relation  : Zonal, MapZonalWithOh
 *
 *******************************************************************************/
package com.niche.ng.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * OperationalHead Domain Class
 * 
 * OperationalHead class implements Serializable to convert instance class into 
 * series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
@Entity
@Table(name = "operational_head")
// @Table(
//     name = "operational_head",
//     uniqueConstraints = {
//             @UniqueConstraint(columnNames = "name"),
//             @UniqueConstraint(columnNames = "description")
//     }
// )
public class OperationalHead extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 64, message = "Name should not exist 64 character.")
    @Column(name = "name", length = 64, nullable = false, unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Integer status;

    /**
     * Relation Name : OneToMany - zonals
     * Table Name    : Connect the OperationalHead Table to Zonal Table
     * After Creation of the OperationalHead, If any movement it affect in Zonal Table
     */
    @OneToMany(mappedBy = "operationalHead", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<Zonal> zonals = new HashSet<>();

    /**
     * Relation Name : OneToMany - mapZonalWithOhs
     * Table Name    : Connect the OperationalHead Table to MapZonalWithOh Table
     * After Creation of the OperationalHead, If any movement it affect in MapZonalWithOh Table
     */
    @OneToMany(mappedBy = "operationalHead", cascade = javax.persistence.CascadeType.REMOVE)
    private Set<MapZonalWithOh> mapZonalWithOhs = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    
    /**
     * To Get the Id from operationalHead table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To Set the Id for operationalHead table
     *
     * @param id the id in operationalHead table
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the name from operationalHead table
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * To Set the name for operationalHead table
     *
     * @param name the name in operationalHead table
     * @return this
     */
    public OperationalHead name(String name) {
        this.name = name;
        return this;
    }

    /**
     * To Set the name for operationalHead table
     *
     * @param name the name in operationalHead table
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * To Get the description from operationalHead table
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * To Set the description for operationalHead table
     *
     * @param description the description in operationalHead table
     * @return this
     */
    public OperationalHead description(String description) {
        this.description = description;
        return this;
    }

    /**
     * To Set the description for operationalHead table
     *
     * @param description the description in operationalHead table
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * To Get the status from operationalHead table
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To Set the status for operationalHead table
     *
     * @param status the status in operationalHead table
     * @return this
     */
    public OperationalHead status(Integer status) {
        this.status = status;
        return this;
    }

    /**
     * To Set the status for operationalHead table
     *
     * @param status the status in operationalHead table
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the zonals from operationalHead table
     * 
     * @return zonals
     */
    public Set<Zonal> getZonals() {
        return zonals;
    }

    /**
     * To Set the zonals for operationalHead table
     *
     * @param zonals the zonals in operationalHead table
     * @return this
     */
    public OperationalHead zonals(Set<Zonal> zonals) {
        this.zonals = zonals;
        return this;
    }

    /**
     * To set the add zonals for the operationalHead table.
     * 
     * @param zonal the zonal in operationalHead
     * @return this
     */
    public OperationalHead addZonal(Zonal zonal) {
        this.zonals.add(zonal);
        zonal.setOperationalHead(this);
        return this;
    }

    /**
     * To set the remove zonals for the operationalHead table.
     * 
     * @param zonal the zonal in operationalHead
     * @return this
     */
    public OperationalHead removeZonal(Zonal zonal) {
        this.zonals.remove(zonal);
        zonal.setOperationalHead(null);
        return this;
    }

    /**
     * To Set the zonals for operationalHead table
     *
     * @param zonals the zonals in operationalHead table
     */
    public void setZonals(Set<Zonal> zonals) {
        this.zonals = zonals;
    }

    /**
     * To Get the mapZonalWithOhs from operationalHead table
     * 
     * @return mapZonalWithOhs
     */
    public Set<MapZonalWithOh> getMapZonalWithOhs() {
        return mapZonalWithOhs;
    }

    /**
     * To Set the mapZonalWithOhs for operationalHead table
     *
     * @param mapZonalWithOhs the mapZonalWithOhs in operationalHead table
     * @return this
     */
    public OperationalHead mapZonalWithOhs(Set<MapZonalWithOh> mapZonalWithOhs) {
        this.mapZonalWithOhs = mapZonalWithOhs;
        return this;
    }

    /**
     * To set the add mapZonalWithOhs for the operationalHead table.
     * 
     * @param mapZonalWithOh the mapZonalWithOh in operationalHead
     * @return this
     */
    public OperationalHead addMapZonalWithOh(MapZonalWithOh mapZonalWithOh) {
        this.mapZonalWithOhs.add(mapZonalWithOh);
        mapZonalWithOh.setOperationalHead(this);
        return this;
    }

    /**
     * To set the remove mapZonalWithOhs for the operationalHead table.
     * 
     * @param mapZonalWithOh the mapZonalWithOh in operationalHead
     * @return this
     */
    public OperationalHead removeMapZonalWithOh(MapZonalWithOh mapZonalWithOh) {
        this.mapZonalWithOhs.remove(mapZonalWithOh);
        mapZonalWithOh.setOperationalHead(null);
        return this;
    }

    /**
     * To Set the mapZonalWithOhs for operationalHead table
     *
     * @param mapZonalWithOhs the mapZonalWithOhs in operationalHead table
     */
    public void setMapZonalWithOhs(Set<MapZonalWithOh> mapZonalWithOhs) {
        this.mapZonalWithOhs = mapZonalWithOhs;
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
        OperationalHead operationalHead = (OperationalHead) o;
        if (operationalHead.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), operationalHead.getId());
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
        return "OperationalHead{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
