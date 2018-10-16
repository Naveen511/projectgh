/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryStockDTO and
                            declared the table fields, data types for NurseryStockDetails table.
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the NurseryStock entity.
 * 
 * NurseryStockDTO class implements Serializable to convert instance class into series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
public class NurseryStockDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    private Long currentQuantity;

    private Long addedQuantity;

    private Long consumedQuantity;

    private String description;

    private Integer status;

    private Integer posQuantity;

    private Long damageQuantity;

    private Long nurseryId;

    private String nurseryNurseryName;

    private Long pickListVarietyId;

    private String pickListVarietyPickListValue;

    private Long pickListCategoryId;

    private String pickListCategoryPickListValue;

    private Long financialYearNurseryStockId;

    private String financialYearNurseryStockBatchName;

    /**
     * To Get the Id from NurseryStock table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To set the id values for the NurseryStock table.
     * 
     * @param id id of the NurseryStock
     */
    public void setId(Long id) {
        this.id = id;
    }

        /**
     * To Get the currentQuantity from NurseryStock table
     * 
     * @return currentQuantity
     */
    public Long getCurrentQuantity() {
        return currentQuantity;
    }

    /**
     * To set the currentQuantity values for the NurseryStock table.
     * 
     * @param currentQuantity currentQuantity of the NurseryStock
     */
    public void setCurrentQuantity(Long currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    /**
     * To Get the addedQuantity from NurseryStock table
     * 
     * @return addedQuantity
     */
    public Long getAddedQuantity() {
        return addedQuantity;
    }

    /**
     * To set the addedQuantity values for the NurseryStock table.
     * 
     * @param addedQuantity addedQuantity of the NurseryStock
     */
    public void setAddedQuantity(Long addedQuantity) {
        this.addedQuantity = addedQuantity;
    }

    /**
     * To Get the consumedQuantity from NurseryStock table
     * 
     * @return consumedQuantity
     */
    public Long getConsumedQuantity() {
        return consumedQuantity;
    }

    /**
     * To set the consumedQuantity values for the NurseryStock table.
     * 
     * @param consumedQuantity consumedQuantity of the NurseryStock
     */
    public void setConsumedQuantity(Long consumedQuantity) {
        this.consumedQuantity = consumedQuantity;
    }

    /**
     * To Get the description from NurseryStock table
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * To set the description values for the NurseryStock table.
     * 
     * @param description description of the NurseryStock
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * To Get the status from NurseryStock table
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To set the status values for the NurseryStock table.
     * 
     * @param status status of the NurseryStock
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the posQuantity from NurseryStock table
     * 
     * @return posQuantity
     */
    public Integer getPosQuantity() {
        return posQuantity;
    }

    /**
     * To set the posQuantity values for the NurseryStock table.
     * 
     * @param posQuantity posQuantity of the NurseryStock
     */
    public void setPosQuantity(Integer posQuantity) {
        this.posQuantity = posQuantity;
    }

    /**
     * To Get the damageQuantity from NurseryStock table
     * 
     * @return damageQuantity
     */
    public Long getDamageQuantity() {
        return damageQuantity;
    }

    /**
     * To set the damageQuantity values for the NurseryStock table.
     * 
     * @param damageQuantity damageQuantity of the NurseryStock
     */
    public void setDamageQuantity(Long damageQuantity) {
        this.damageQuantity = damageQuantity;
    }

    /**
     * To Get the nurseryId from NurseryStock table
     * 
     * @return nurseryId
     */
    public Long getNurseryId() {
        return nurseryId;
    }

    /**
     * To set the nurseryId values for the NurseryStock table.
     * 
     * @param nurseryId nurseryId of the NurseryStock
     */
    public void setNurseryId(Long nurseryId) {
        this.nurseryId = nurseryId;
    }

    /**
     * To Get the nurseryNurseryName from NurseryStock table
     * 
     * @return nurseryNurseryName
     */
    public String getNurseryNurseryName() {
        return nurseryNurseryName;
    }

    /**
     * To set the nurseryNurseryName values for the NurseryStock table.
     * 
     * @param nurseryNurseryName nurseryNurseryName of the NurseryStock
     */
    public void setNurseryNurseryName(String nurseryNurseryName) {
        this.nurseryNurseryName = nurseryNurseryName;
    }

    /**
     * To Get the pickListVarietyId from NurseryStock table
     * 
     * @return pickListVarietyId
     */
    public Long getPickListVarietyId() {
        return pickListVarietyId;
    }

    /**
     * To set the pickListVarietyId values for the NurseryStock table.
     * 
     * @param pickListVarietyId pickListVarietyId of the NurseryStock
     */
    public void setPickListVarietyId(Long pickListValueId) {
        this.pickListVarietyId = pickListValueId;
    }

    /**
     * To Get the pickListVarietyPickListValue from NurseryStock table
     * 
     * @return pickListVarietyPickListValue
     */
    public String getPickListVarietyPickListValue() {
        return pickListVarietyPickListValue;
    }

    /**
     * To set the pickListVarietyPickListValue values for the NurseryStock table.
     * 
     * @param pickListVarietyPickListValue pickListVarietyPickListValue of the NurseryStock
     */
    public void setPickListVarietyPickListValue(String pickListValuePickListValue) {
        this.pickListVarietyPickListValue = pickListValuePickListValue;
    }

    /**
     * To Get the pickListCategoryId from NurseryStock table
     * 
     * @return pickListCategoryId
     */
    public Long getPickListCategoryId() {
        return pickListCategoryId;
    }

    /**
     * To set the pickListCategoryId values for the NurseryStock table.
     * 
     * @param pickListCategoryId pickListCategoryId of the NurseryStock
     */
    public void setPickListCategoryId(Long pickListValueId) {
        this.pickListCategoryId = pickListValueId;
    }

    /**
     * To Get the pickListCategoryPickListValue from NurseryStock table
     * 
     * @return pickListCategoryPickListValue
     */
    public String getPickListCategoryPickListValue() {
        return pickListCategoryPickListValue;
    }

    /**
     * To set the pickListCategoryPickListValue values for the NurseryStock table.
     * 
     * @param pickListCategoryPickListValue pickListCategoryPickListValue of the NurseryStock
     */
    public void setPickListCategoryPickListValue(String pickListValuePickListValue) {
        this.pickListCategoryPickListValue = pickListValuePickListValue;
    }

    /**
     * To Get the financialYearNurseryStockId from NurseryStock table
     * 
     * @return financialYearNurseryStockId
     */
    public Long getFinancialYearNurseryStockId() {
        return financialYearNurseryStockId;
    }

    /**
     * To set the financialYearNurseryStockId values for the NurseryStock table.
     * 
     * @param financialYearNurseryStockId financialYearNurseryStockId of the NurseryStock
     */
    public void setFinancialYearNurseryStockId(Long financialYearSettingsId) {
        this.financialYearNurseryStockId = financialYearSettingsId;
    }

    /**
     * To Get the financialYearNurseryStockBatchName from NurseryStock table
     * 
     * @return financialYearNurseryStockBatchName
     */
    public String getFinancialYearNurseryStockBatchName() {
        return financialYearNurseryStockBatchName;
    }

    /**
     * To set the financialYearNurseryStockBatchName values for the NurseryStock table.
     * 
     * @param financialYearNurseryStockBatchName financialYearNurseryStockBatchName of the NurseryStock
     */
    public void setFinancialYearNurseryStockBatchName(String financialYearSettingsBatchName) {
        this.financialYearNurseryStockBatchName = financialYearSettingsBatchName;
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

        NurseryStockDTO nurseryStockDTO = (NurseryStockDTO) o;
        if (nurseryStockDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nurseryStockDTO.getId());
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
        return "NurseryStockDTO{" +
            "id=" + getId() +
            ", currentQuantity=" + getCurrentQuantity() +
            ", addedQuantity=" + getAddedQuantity() +
            ", consumedQuantity=" + getConsumedQuantity() +
            ", description='" + getDescription() + "'" +
            ", status=" + getStatus() +
            ", posQuantity=" + getPosQuantity() +
            ", damageQuantity=" + getDamageQuantity() +
            ", nursery=" + getNurseryId() +
            ", nursery='" + getNurseryNurseryName() + "'" +
            ", pickListVariety=" + getPickListVarietyId() +
            ", pickListVariety='" + getPickListVarietyPickListValue() + "'" +
            ", pickListCategory=" + getPickListCategoryId() +
            ", pickListCategory='" + getPickListCategoryPickListValue() + "'" +
            ", financialYearNurseryStock=" + getFinancialYearNurseryStockId() +
            ", financialYearNurseryStock='" + getFinancialYearNurseryStockBatchName() + "'" +
            "}";
    }
}
