/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs ShadeAreaDTO and
                            declared the table fields, data types for ShadeArea table.
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ShadeArea entity.
 * 
 * ShadeAreaDTO class implements Serializable to convert instance class into series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
public class ShadeAreaDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull(message = "Quantity cannot be blank.")
    private Long noOfSeedlings;

    @NotNull(message = "Date cannot be blank.")
    private LocalDate date;

    private Integer status;

    private Integer damage;

    private Integer saplings;

    private Integer round;

    private Long batchId;

    private String batchBatchName;

    private Long financialYearShadeAreaId;

    private String financialYearShadeAreaBatchName;

    /**
     * To Get the Id from ShadeArea table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To set the id values for the ShadeArea table.
     * 
     * @param id id of the ShadeArea
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the noOfSeedlings from ShadeArea table
     * 
     * @return noOfSeedlings
     */
    public Long getNoOfSeedlings() {
        return noOfSeedlings;
    }

    /**
     * To set the noOfSeedlings values for the ShadeArea table.
     * 
     * @param noOfSeedlings noOfSeedlings of the ShadeArea
     */
    public void setNoOfSeedlings(Long noOfSeedlings) {
        this.noOfSeedlings = noOfSeedlings;
    }

    /**
     * To Get the date from ShadeArea table
     * 
     * @return date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * To set the date values for the ShadeArea table.
     * 
     * @param date date of the ShadeArea
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * To Get the status from ShadeArea table
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To set the status values for the ShadeArea table.
     * 
     * @param status status of the ShadeArea
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the damage from ShadeArea table
     * 
     * @return damage
     */
    public Integer getDamage() {
        return damage;
    }

    /**
     * To set the damage values for the ShadeArea table.
     * 
     * @param damage damage of the ShadeArea
     */
    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    /**
     * To Get the saplings from ShadeArea table
     * 
     * @return idsaplings
     */
    public Integer getSaplings() {
        return saplings;
    }

    /**
     * To set the saplings values for the ShadeArea table.
     * 
     * @param saplings saplings of the ShadeArea
     */
    public void setSaplings(Integer saplings) {
        this.saplings = saplings;
    }

    /**
     * To Get the round from ShadeArea table
     * 
     * @return round
     */
    public Integer getRound() {
        return round;
    }

    /**
     * To set the round values for the ShadeArea table.
     * 
     * @param round round of the ShadeArea
     */
    public void setRound(Integer round) {
        this.round = round;
    }

    /**
     * To Get the batchId from ShadeArea table
     * 
     * @return batchId
     */
    public Long getBatchId() {
        return batchId;
    }

    /**
     * To set the batchId values for the ShadeArea table.
     * 
     * @param batchId batchId of the ShadeArea
     */
    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    /**
     * To Get the batchBatchName from ShadeArea table
     * 
     * @return batchBatchName
     */
    public String getBatchBatchName() {
        return batchBatchName;
    }

    /**
     * To set the batchBatchName values for the ShadeArea table.
     * 
     * @param batchBatchName batchBatchName of the ShadeArea
     */
    public void setBatchBatchName(String batchBatchName) {
        this.batchBatchName = batchBatchName;
    }

    /**
     * To Get the financialYearShadeAreaId from ShadeArea table
     * 
     * @return financialYearShadeAreaId
     */
    public Long getFinancialYearShadeAreaId() {
        return financialYearShadeAreaId;
    }

    /**
     * To set the financialYearShadeAreaId values for the ShadeArea table.
     * 
     * @param financialYearShadeAreaId financialYearShadeAreaId of the ShadeArea
     */
    public void setFinancialYearShadeAreaId(Long financialYearSettingsId) {
        this.financialYearShadeAreaId = financialYearSettingsId;
    }

    /**
     * To Get the financialYearShadeAreaBatchName from ShadeArea table
     * 
     * @return financialYearShadeAreaBatchName
     */
    public String getFinancialYearShadeAreaBatchName() {
        return financialYearShadeAreaBatchName;
    }

    /**
     * To set the financialYearShadeAreaBatchName values for the ShadeArea table.
     * 
     * @param financialYearShadeAreaBatchName financialYearShadeAreaBatchName of the ShadeArea
     */
    public void setFinancialYearShadeAreaBatchName(String financialYearSettingsBatchName) {
        this.financialYearShadeAreaBatchName = financialYearSettingsBatchName;
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

        ShadeAreaDTO shadeAreaDTO = (ShadeAreaDTO) o;
        if (shadeAreaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), shadeAreaDTO.getId());
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
        return "ShadeAreaDTO{" +
            "id=" + getId() +
            ", noOfSeedlings=" + getNoOfSeedlings() +
            ", date='" + getDate() + "'" +
            ", status=" + getStatus() +
            ", damage=" + getDamage() +
            ", saplings=" + getSaplings() +
            ", round=" + getRound() +
            ", batch=" + getBatchId() +
            ", batch='" + getBatchBatchName() + "'" +
            ", financialYearShadeArea=" + getFinancialYearShadeAreaId() +
            ", financialYearShadeArea='" + getFinancialYearShadeAreaBatchName() + "'" +
            "}";
    }
}
