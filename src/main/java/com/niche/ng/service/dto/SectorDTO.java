/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs SectorDTO and
                            declared the table fields, data types for Sector table.
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Sector entity.
 * 
 * SectorDTO class implements Serializable to convert instance class into series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
public class SectorDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull(message = "Sector name cannot be blank.")
    @NotBlank(message = "Sector name cannot be blank.")
    private String sectorName;

    private String sectorAddress;

    private Integer status;

    @NotNull(message = "Zonal name cannot be blank.")
    private Long zonalId;

    private String zonalZoneName;

    private Long financialYearSectorId;

    private String financialYearSectorBatchName;

    /**
     * To Get the Id from Sector table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To set the id values for the Sector table.
     * 
     * @param id id of the Sector
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the sectorName from Sector table
     * 
     * @return sectorName
     */
    public String getSectorName() {
        return sectorName;
    }

    /**
     * To set the sectorName values for the Sector table.
     * 
     * @param sectorName sectorName of the Sector
     */
    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    /**
     * To Get the sectorAddress from Sector table
     * 
     * @return sectorAddress
     */
    public String getSectorAddress() {
        return sectorAddress;
    }

    /**
     * To set the sectorAddress values for the Sector table.
     * 
     * @param sectorAddress sectorAddress of the Sector
     */
    public void setSectorAddress(String sectorAddress) {
        this.sectorAddress = sectorAddress;
    }

    /**
     * To Get the status from Sector table
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To set the status values for the Sector table.
     * 
     * @param status status of the Sector
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the zonalId from Sector table
     * 
     * @return zonalId
     */
    public Long getZonalId() {
        return zonalId;
    }

    /**
     * To set the zonalId values for the Sector table.
     * 
     * @param zonalId zonalId of the Sector
     */
    public void setZonalId(Long zonalId) {
        this.zonalId = zonalId;
    }

    /**
     * To Get the zonalZoneName from Sector table
     * 
     * @return zonalZoneName
     */
    public String getZonalZoneName() {
        return zonalZoneName;
    }

    /**
     * To set the zonalZoneName values for the Sector table.
     * 
     * @param zonalZoneName zonalZoneName of the Sector
     */
    public void setZonalZoneName(String zonalZoneName) {
        this.zonalZoneName = zonalZoneName;
    }

    /**
     * To Get the financialYearSectorId from Sector table
     * 
     * @return financialYearSectorId
     */
    public Long getFinancialYearSectorId() {
        return financialYearSectorId;
    }

    /**
     * To set the financialYearSectorId values for the Sector table.
     * 
     * @param financialYearSectorId financialYearSectorId of the Sector
     */
    public void setFinancialYearSectorId(Long financialYearSettingsId) {
        this.financialYearSectorId = financialYearSettingsId;
    }

    /**
     * To Get the financialYearSectorBatchName from Sector table
     * 
     * @return financialYearSectorBatchName
     */
    public String getFinancialYearSectorBatchName() {
        return financialYearSectorBatchName;
    }

    /**
     * To set the financialYearSectorBatchName values for the Sector table.
     * 
     * @param financialYearSectorBatchName financialYearSectorBatchName of the Sector
     */
    public void setFinancialYearSectorBatchName(String financialYearSettingsBatchName) {
        this.financialYearSectorBatchName = financialYearSettingsBatchName;
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

        SectorDTO sectorDTO = (SectorDTO) o;
        if (sectorDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sectorDTO.getId());
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
        return "SectorDTO{" +
            "id=" + getId() +
            ", sectorName='" + getSectorName() + "'" +
            ", sectorAddress='" + getSectorAddress() + "'" +
            ", status=" + getStatus() +
            ", zonal=" + getZonalId() +
            ", zonal='" + getZonalZoneName() + "'" +
            ", financialYearSector=" + getFinancialYearSectorId() +
            ", financialYearSector='" + getFinancialYearSectorBatchName() + "'" +
            "}";
    }
}
