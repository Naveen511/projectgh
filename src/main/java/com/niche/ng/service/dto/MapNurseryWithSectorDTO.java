/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs MapNurseryWithSectorDTO and
                            declared the table fields, data types for MapNurseryWithSectorC table.
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the MapNurseryWithSector entity.
 * 
 * MapNurseryWithSectorDTO class implements Serializable to convert instance class into series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
public class MapNurseryWithSectorDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull(message = "From Date cannot be blank.")
    private LocalDate fromDate;

    private LocalDate toDate;

    private String description;

    private Integer status;

    @NotNull(message = "Nursery name cannot be blank.")
    private Long nurseryId;

    private String nurseryNurseryName;

    @NotNull(message = "Sector name cannot be blank.")
    private Long sectorId;

    private String sectorSectorName;

    /**
     * To Get the Id from MapNurseryWithSector table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }
    
    /**
     * To set the id values for the MapNurseryWithSector table.
     * 
     * @param id id value of the MapNurseryWithSector
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
     * To set the fromDate values for the MapNurseryWithSector table.
     * 
     * @param fromDate fromDate value of the MapNurseryWithSector
     */
    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * To Get the fromDate from MapNurseryWithSector table
     * 
     * @return fromDate
     */
    public LocalDate getToDate() {
        return toDate;
    }

    /**
     * To set the toDate values for the MapNurseryWithSector table.
     * 
     * @param toDate toDate value of the MapNurseryWithSector
     */
    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    /**
     * To Get the fromDate from MapNurseryWithSector table
     * 
     * @return fromDate
     */
    public String getDescription() {
        return description;
    }

    /**
     * To set the description values for the MapNurseryWithSector table.
     * 
     * @param description description value of the MapNurseryWithSector
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * To Get the fromDate from MapNurseryWithSector table
     * 
     * @return fromDate
     */
    public Integer getStatus() {
        return status;
    }
    
    /**
     * To set the status values for the MapNurseryWithSector table.
     * 
     * @param status status value of the MapNurseryWithSector
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the fromDate from MapNurseryWithSector table
     * 
     * @return fromDate
     */
    public Long getNurseryId() {
        return nurseryId;
    }

    /**
     * To set the nurseryId values for the MapNurseryWithSector table.
     * 
     * @param nurseryId nurseryId value of the MapNurseryWithSector
     */
    public void setNurseryId(Long nurseryId) {
        this.nurseryId = nurseryId;
    }

    /**
     * To Get the nurseryNurseryName from MapNurseryWithSector table
     * 
     * @return nurseryNurseryName
     */
    public String getNurseryNurseryName() {
        return nurseryNurseryName;
    }

    /**
     * To set the nurseryNurseryName values for the MapNurseryWithSector table.
     * 
     * @param nurseryNurseryName nurseryNurseryName value of the MapNurseryWithSector
     */
    public void setNurseryNurseryName(String nurseryNurseryName) {
        this.nurseryNurseryName = nurseryNurseryName;
    }

    /**
     * To Get the sectorId from MapNurseryWithSector table
     * 
     * @return sectorId
     */
    public Long getSectorId() {
        return sectorId;
    }

    /**
     * To set the sectorId values for the MapNurseryWithSector table.
     * 
     * @param sectorId sectorId value of the MapNurseryWithSector
     */
    public void setSectorId(Long sectorId) {
        this.sectorId = sectorId;
    }

    /**
     * To Get the sectorSectorName from MapNurseryWithSector table
     * 
     * @return sectorSectorName
     */
    public String getSectorSectorName() {
        return sectorSectorName;
    }

    /**
     * To set the sectorSectorName values for the MapNurseryWithSector table.
     * 
     * @param sectorSectorName sectorSectorName value of the MapNurseryWithSector
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

        MapNurseryWithSectorDTO mapNurseryWithSectorDTO = (MapNurseryWithSectorDTO) o;
        if (mapNurseryWithSectorDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mapNurseryWithSectorDTO.getId());
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
        return "MapNurseryWithSectorDTO{" +
            "id=" + getId() +
            ", fromDate='" + getFromDate() + "'" +
            ", toDate='" + getToDate() + "'" +
            ", description='" + getDescription() + "'" +
            ", status=" + getStatus() +
            ", nursery=" + getNurseryId() +
            ", nursery='" + getNurseryNurseryName() + "'" +
            ", sector=" + getSectorId() +
            ", sector='" + getSectorSectorName() + "'" +
            "}";
    }
}
