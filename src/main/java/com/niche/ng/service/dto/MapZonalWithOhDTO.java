/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs MapZonalWithOhDTO and
                            declared the table fields, data types for MapZonalWithOh table.
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import javax.validation.constraints.*;
import java.util.Objects;

/**
 * A DTO for the MapZonalWithOh entity.
 * 
 * MapZonalWithOhDTO class implements Serializable to convert instance class into series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
public class MapZonalWithOhDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull(message = "From Date cannot be blank.")
    private LocalDate fromDate;

    private LocalDate toDate;

    private String description;

    private Integer status;

    private Long zonalId;

    private String zonalZoneName;

    private Long operationalHeadId;

    private String operationalHeadName;

    /**
     * To Get the Id from MapZonalWithOh table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To set the id values for the MapZonalWithOh table.
     * 
     * @param id id value of the MapZonalWithOh
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
     * To set the fromDate values for the MapZonalWithOh table.
     * 
     * @param fromDate fromDate value of the MapZonalWithOh
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
     * To set the toDate values for the MapZonalWithOh table.
     * 
     * @param toDate toDate value of the MapZonalWithOh
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
     * To set the description values for the MapZonalWithOh table.
     * 
     * @param description description value of the MapZonalWithOh
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
     * To set the status values for the MapZonalWithOh table.
     * 
     * @param status status value of the MapZonalWithOh
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the zonalId from MapZonalWithOh table
     * 
     * @return zonalId
     */
    public Long getZonalId() {
        return zonalId;
    }

    /**
     * To set the zonalId values for the MapZonalWithOh table.
     * 
     * @param zonalId zonalId value of the MapZonalWithOh
     */
    public void setZonalId(Long zonalId) {
        this.zonalId = zonalId;
    }

    /**
     * To Get the zonalZoneName from MapZonalWithOh table
     * 
     * @return zonalZoneName
     */
    public String getZonalZoneName() {
        return zonalZoneName;
    }

    /**
     * To set the zonalZoneName values for the MapZonalWithOh table.
     * 
     * @param zonalZoneName zonalZoneName value of the MapZonalWithOh
     */
    public void setZonalZoneName(String zonalZoneName) {
        this.zonalZoneName = zonalZoneName;
    }

    /**
     * To Get the operationalHeadId from MapZonalWithOh table
     * 
     * @return operationalHeadId
     */
    public Long getOperationalHeadId() {
        return operationalHeadId;
    }

    /**
     * To set the operationalHeadId values for the MapZonalWithOh table.
     * 
     * @param operationalHeadId operationalHeadId value of the MapZonalWithOh
     */
    public void setOperationalHeadId(Long operationalHeadId) {
        this.operationalHeadId = operationalHeadId;
    }

    /**
     * To Get the operationalHeadName from MapZonalWithOh table
     * 
     * @return operationalHeadName
     */
    public String getOperationalHeadName() {
        return operationalHeadName;
    }

    /**
     * To set the operationalHeadName values for the MapZonalWithOh table.
     * 
     * @param operationalHeadName operationalHeadName value of the MapZonalWithOh
     */
    public void setOperationalHeadName(String operationalHeadName) {
        this.operationalHeadName = operationalHeadName;
    }

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

        MapZonalWithOhDTO mapZonalWithOhDTO = (MapZonalWithOhDTO) o;
        if (mapZonalWithOhDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mapZonalWithOhDTO.getId());
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
        return "MapZonalWithOhDTO{" +
            "id=" + getId() +
            ", fromDate='" + getFromDate() + "'" +
            ", toDate='" + getToDate() + "'" +
            ", description='" + getDescription() + "'" +
            ", status=" + getStatus() +
            ", zonal=" + getZonalId() +
            ", zonal='" + getZonalZoneName() + "'" +
            ", operationalHead=" + getOperationalHeadId() +
            ", operationalHead='" + getOperationalHeadName() + "'" +
            "}";
    }
}
