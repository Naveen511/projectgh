/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs DamageDTO and
                            declared the table fields, data types for Damage table.
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Damage entity.
 * 
 * DamageDTO class implements Serializable to convert instance class into series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
public class DamageDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull(message = "Quantity cannot be blank.")
    private Long noOfQuantity;

    @NotNull(message = "Date cannot be blank.")
    private LocalDate date;

    private Integer status;

    private Long batchId;

    private String batchBatchName;

    private Long descriptionId;

    private String descriptionPickListValue;

    private Long damageAreaId;

    private String damageAreaPickListValue;

    private Long financialYearDamageId;

    private String financialYearDamageBatchName;

    /**
     * To Get the Id from damage table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To Set the Id for damage table
     * 
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the noOfQuantity from damage table.
     * 
     * @return noOfQuantity
     */
    public Long getNoOfQuantity() {
        return noOfQuantity;
    }

    /**
     * To set the noOfQuantity values for the damage table.
     * 
     * @param noOfQuantity number of quantity in damage
     */
    public void setNoOfQuantity(Long noOfQuantity) {
        this.noOfQuantity = noOfQuantity;
    }

    /**
     * To Get the noOfQuantity from damage table.
     * 
     * @return date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * To set the date values for the damage table.
     * 
     * @param date damage date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * To Get the status from damage table.
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To set the status values for the damage table.
     * 
     * @param status status in damage
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the batchId from damage table.
     * 
     * @return batchId
     */
    public Long getBatchId() {
        return batchId;
    }

    /**
     * To set the batchId values for the damage table.
     * 
     * @param batchId batchId in damage
     */
    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    /**
     * To Get the batchBatchName from damage table.
     * 
     * @return batchBatchName
     */
    public String getBatchBatchName() {
        return batchBatchName;
    }

    /**
     * To set the batchBatchName values for the damage table.
     * 
     * @param batchBatchName batchBatchName in damage
     */
    public void setBatchBatchName(String batchBatchName) {
        this.batchBatchName = batchBatchName;
    }

    /**
     * To Get the descriptionId from damage table.
     * 
     * @return descriptionId
     */
    public Long getDescriptionId() {
        return descriptionId;
    }

    /**
     * To set the descriptionId values for the damage table.
     * 
     * @param descriptionId descriptionId in damage
     */
    public void setDescriptionId(Long pickListValueId) {
        this.descriptionId = pickListValueId;
    }

    /**
     * To Get the descriptionPickListValue from damage table.
     * 
     * @return descriptionPickListValue
     */
    public String getDescriptionPickListValue() {
        return descriptionPickListValue;
    }

    /**
     * To set the descriptionPickListValue values for the damage table.
     * 
     * @param descriptionPickListValue descriptionPickListValue in damage
     */
    public void setDescriptionPickListValue(String pickListValuePickListValue) {
        this.descriptionPickListValue = pickListValuePickListValue;
    }

    /**
     * To Get the descriptionPickListValue from damage table.
     * 
     * @return descriptionPickListValue
     */
    public Long getDamageAreaId() {
        return damageAreaId;
    }

    /**
     * To set the damageAreaId values for the damage table.
     * 
     * @param damageAreaId damageAreaId in damage
     */
    public void setDamageAreaId(Long pickListValueId) {
        this.damageAreaId = pickListValueId;
    }

    /**
     * To Get the damageAreaPickListValue from damage table.
     * 
     * @return damageAreaPickListValue
     */
    public String getDamageAreaPickListValue() {
        return damageAreaPickListValue;
    }

    /**
     * To set the damageAreaPickListValue values for the damage table.
     * 
     * @param damageAreaPickListValue damageAreaPickListValue in damage
     */
    public void setDamageAreaPickListValue(String pickListValuePickListValue) {
        this.damageAreaPickListValue = pickListValuePickListValue;
    }

    /**
     * To Get the financialYearDamageId from damage table.
     * 
     * @return financialYearDamageId
     */
    public Long getFinancialYearDamageId() {
        return financialYearDamageId;
    }

    /**
     * To set the financialYearDamageId values for the damage table.
     * 
     * @param financialYearDamageId financialYearDamageId in damage
     */
    public void setFinancialYearDamageId(Long financialYearSettingsId) {
        this.financialYearDamageId = financialYearSettingsId;
    }

    /**
     * To Get the damageAreaPickListValue from damage table.
     * 
     * @return damageAreaPickListValue
     */
    public String getFinancialYearDamageBatchName() {
        return financialYearDamageBatchName;
    }

    /**
     * To set the financialYearDamageBatchName values for the damage table.
     * 
     * @param financialYearDamageBatchName financialYearDamageBatchName in damage
     */
    public void setFinancialYearDamageBatchName(String financialYearSettingsBatchName) {
        this.financialYearDamageBatchName = financialYearSettingsBatchName;
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

        DamageDTO damageDTO = (DamageDTO) o;
        if (damageDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), damageDTO.getId());
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
        return "DamageDTO{" +
            "id=" + getId() +
            ", noOfQuantity=" + getNoOfQuantity() +
            ", date='" + getDate() + "'" +
            ", status=" + getStatus() +
            ", batch=" + getBatchId() +
            ", batch='" + getBatchBatchName() + "'" +
            ", description=" + getDescriptionId() +
            ", description='" + getDescriptionPickListValue() + "'" +
            ", damageArea=" + getDamageAreaId() +
            ", damageArea='" + getDamageAreaPickListValue() + "'" +
            ", financialYearDamage=" + getFinancialYearDamageId() +
            ", financialYearDamage='" + getFinancialYearDamageBatchName() + "'" +
            "}";
    }
}
