/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs FinancialYearSettingsDTO and
                            declared the table fields, data types for FinancialYearSettings table.
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the FinancialYearSettings entity.
 *
 * FinancialYearSettingsDTO class implements Serializable to convert instance class into 
 * series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
public class FinancialYearSettingsDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull(message = "Batch Name cannot be blank.")
    @NotBlank(message = "Batch Name cannot be blank.")
    private String batchName;

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer status;

    private Long financialYearId;

    private String financialYearPickListValue;

    /**
     * To Get the Id from FinancialYearSettings table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To Set the Id for FinancialYearSettings table
     * 
     * @param id id value
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To get the batchName from the FinancialYearSettings table.
     * 
     * @return batchName
     */
    public String getBatchName() {
        return batchName;
    }

    /**
     * To set the batchName values for the FinancialYearSettings table.
     * 
     * @param batchName name of the batch
     */
    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    /**
     * To get the startDate values for the FinancialYearSettings table.
     * 
     * @return startDate date of starting
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * To set the startDate values for the FinancialYearSettings table.
     * 
     * @param startDate date of starting
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

     /**
     * To get the endDate values for the FinancialYearSettings table.
     * 
     * @return endDate
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * To set the endDate values for the FinancialYearSettings table.
     * 
     * @param endDate date of ending
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * To get the status values for the FinancialYearSettings table.
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To set the status values for the FinancialYearSettings table.
     * 
     * @param status status in financial year setting table
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To get the financialYearId values for the FinancialYearSettings table.
     * 
     * @return financialYearId
     */
    public Long getFinancialYearId() {
        return financialYearId;
    }

    /**
     * To set the financialYearId values for the FinancialYearSettings table.
     * 
     * @param financialYearId financialYearId in financial year setting table
     */
    public void setFinancialYearId(Long pickListValueId) {
        this.financialYearId = pickListValueId;
    }

    /**
     * To get the financialYearPickListValue values for the FinancialYearSettings table.
     * 
     * @return financialYearPickListValue
     */
    public String getFinancialYearPickListValue() {
        return financialYearPickListValue;
    }

    /**
     * To set the financialYearPickListValue values for the FinancialYearSettings table.
     * 
     * @param financialYearPickListValue financialYearPickListValue in financial year setting table
     */
    public void setFinancialYearPickListValue(String pickListValuePickListValue) {
        this.financialYearPickListValue = pickListValuePickListValue;
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

        FinancialYearSettingsDTO financialYearSettingsDTO = (FinancialYearSettingsDTO) o;
        if (financialYearSettingsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), financialYearSettingsDTO.getId());
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
        return "FinancialYearSettingsDTO{" +
            "id=" + getId() +
            ", batchName='" + getBatchName() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", status=" + getStatus() +
            ", financialYear=" + getFinancialYearId() +
            ", financialYear='" + getFinancialYearPickListValue() + "'" +
            "}";
    }
}
