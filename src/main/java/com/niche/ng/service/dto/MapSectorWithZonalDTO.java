/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs MapSectorWithZonalDTO and
                            declared the table fields, data types for MapSectorWithZonal table.
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the MapSectorWithZonal entity.
 * 
 * MapSectorWithZonalDTO class implements Serializable to convert instance class into series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
public class MapSectorWithZonalDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull(message = "From Date cannot be blank.")
    private LocalDate fromDate;

    private LocalDate toDate;

    private String description;

    @Max(value = 10)
    private Integer status;

    private Long zonalId;

    private String zonalZoneName;

    private Long sectorId;

    private String sectorSectorName;

    /**
     * To Get the Id from MapSectorWithZonal table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To set the id values for the MapSectorWithZonal table.
     * 
     * @param id id value of the MapSectorWithZonal
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
     * To set the fromDate values for the MapSectorWithZonal table.
     * 
     * @param fromDate fromDate value of the MapSectorWithZonal
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
     * To set the toDate values for the MapSectorWithZonal table.
     * 
     * @param toDate toDate value of the MapSectorWithZonal
     */
    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    /**
     * To Get the description from MapSectorWithZonal table
     * 
     * @return idescriptiond
     */
    public String getDescription() {
        return description;
    }

    /**
     * To set the description values for the MapSectorWithZonal table.
     * 
     * @param description description value of the MapSectorWithZonal
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
     * To set the status values for the MapSectorWithZonal table.
     * 
     * @param status status value of the MapSectorWithZonal
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the zonalId from MapSectorWithZonal table
     * 
     * @return zonalId
     */
    public Long getZonalId() {
        return zonalId;
    }

    /**
     * To set the zonalId values for the MapSectorWithZonal table.
     * 
     * @param zonalId zonalId value of the MapSectorWithZonal
     */
    public void setZonalId(Long zonalId) {
        this.zonalId = zonalId;
    }
    
    /**
     * To Get the zonalZoneName from MapSectorWithZonal table
     * 
     * @return zonalZoneName
     */
    public String getZonalZoneName() {
        return zonalZoneName;
    }

    /**
     * To set the zonalZoneName values for the MapSectorWithZonal table.
     * 
     * @param zonalZoneName zonalZoneName value of the MapSectorWithZonal
     */
    public void setZonalZoneName(String zonalZoneName) {
        this.zonalZoneName = zonalZoneName;
    }

    /**
     * To Get the sectorId from MapSectorWithZonal table
     * 
     * @return sectorId
     */
    public Long getSectorId() {
        return sectorId;
    }

    /**
     * To set the sectorId values for the MapSectorWithZonal table.
     * 
     * @param sectorId sectorId value of the MapSectorWithZonal
     */
    public void setSectorId(Long sectorId) {
        this.sectorId = sectorId;
    }

    /**
     * To Get the sectorSectorName from MapSectorWithZonal table
     * 
     * @return sectorSectorName
     */
    public String getSectorSectorName() {
        return sectorSectorName;
    }

    /**
     * To set the sectorSectorName values for the MapSectorWithZonal table.
     * 
     * @param sectorSectorName sectorSectorName value of the MapSectorWithZonal
     */
    public void setSectorSectorName(String sectorSectorName) {
        this.sectorSectorName = sectorSectorName;
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

        MapSectorWithZonalDTO mapSectorWithZonalDTO = (MapSectorWithZonalDTO) o;
        if (mapSectorWithZonalDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mapSectorWithZonalDTO.getId());
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
        return "MapSectorWithZonalDTO{" +
            "id=" + getId() +
            ", fromDate='" + getFromDate() + "'" +
            ", toDate='" + getToDate() + "'" +
            ", description='" + getDescription() + "'" +
            ", status=" + getStatus() +
            ", zonal=" + getZonalId() +
            ", zonal='" + getZonalZoneName() + "'" +
            ", sector=" + getSectorId() +
            ", sector='" + getSectorSectorName() + "'" +
            "}";
    }
}
