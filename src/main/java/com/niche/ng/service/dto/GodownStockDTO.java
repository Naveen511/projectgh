/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs GodownStockDTO and
                            declared the table fields, data types for GodownStock table.
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the GodownStock entity.
 * 
 * GodownStockDTO class implements Serializable to convert instance class into series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
public class GodownStockDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    private Long currentQuantity;

    private Long addedQuantity;

    private Long consumedQuantity;

    private String description;

    private Integer status;

    @NotNull(message = "Variety cannot be blank")
    private Long pickListVarietyId;

    private String pickListVarietyPickListValue;

    @NotNull(message = "Category cannot be blank")
    private Long pickListCategoryId;

    private String pickListCategoryPickListValue;

    @NotNull(message = "Quantity Type cannot be blank")
    private Long pickListQuantityTypeId;

    private String pickListQuantityTypePickListValue;

    @NotNull(message = "Godown cannot be blank")
    private Long godownId;

    private String godownName;

    private Long financialYearGodownStockId;

    private String financialYearGodownStockBatchName;

    /**
     * To Get the Id from GodownStock table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To set the id values for the GodownStock table.
     * 
     * @param id id of the GodownStock
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the currentQuantity from GodownStock table
     * 
     * @return currentQuantity
     */
    public Long getCurrentQuantity() {
        return currentQuantity;
    }

    /**
     * To set the currentQuantity values for the GodownStock table.
     * 
     * @param currentQuantity currentQuantity of the GodownStock
     */
    public void setCurrentQuantity(Long currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    /**
     * To Get the addedQuantity from GodownStock table
     * 
     * @return addedQuantity
     */
    public Long getAddedQuantity() {
        return addedQuantity;
    }

    /**
     * To set the addedQuantity values for the GodownStock table.
     * 
     * @param addedQuantity addedQuantity of the GodownStock
     */
    public void setAddedQuantity(Long addedQuantity) {
        this.addedQuantity = addedQuantity;
    }

    /**
     * To Get the consumedQuantity from GodownStock table
     * 
     * @return consumedQuantity
     */
    public Long getConsumedQuantity() {
        return consumedQuantity;
    }

    /**
     * To set the consumedQuantity values for the GodownStock table.
     * 
     * @param consumedQuantity consumedQuantity of the GodownStock
     */
    public void setConsumedQuantity(Long consumedQuantity) {
        this.consumedQuantity = consumedQuantity;
    }

    /**
     * To Get the description from GodownStock table
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * To set the description values for the GodownStock table.
     * 
     * @param description description of the GodownStock
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * To Get the status from GodownStock table
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To set the status values for the GodownStock table.
     * 
     * @param status status of the GodownStock
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the pickListVarietyId from GodownStock table
     * 
     * @return pickListVarietyId
     */
    public Long getPickListVarietyId() {
        return pickListVarietyId;
    }

    /**
     * To set the pickListVarietyId values for the GodownStock table.
     * 
     * @param pickListVarietyId pickListVarietyId of the GodownStock
     */
    public void setPickListVarietyId(Long pickListValueId) {
        this.pickListVarietyId = pickListValueId;
    }

    /**
     * To Get the pickListVarietyPickListValue from GodownStock table
     * 
     * @return pickListVarietyPickListValue
     */
    public String getPickListVarietyPickListValue() {
        return pickListVarietyPickListValue;
    }

    /**
     * To set the pickListVarietyPickListValue values for the GodownStock table.
     * 
     * @param pickListVarietyPickListValue pickListVarietyPickListValue of the GodownStock
     */
    public void setPickListVarietyPickListValue(String pickListValuePickListValue) {
        this.pickListVarietyPickListValue = pickListValuePickListValue;
    }

    /**
     * To Get the pickListCategoryId from GodownStock table
     * 
     * @return pickListCategoryId
     */
    public Long getPickListCategoryId() {
        return pickListCategoryId;
    }

    /**
     * To set the pickListCategoryId values for the GodownStock table.
     * 
     * @param pickListCategoryId pickListCategoryId of the GodownStock
     */
    public void setPickListCategoryId(Long pickListValueId) {
        this.pickListCategoryId = pickListValueId;
    }

    /**
     * To Get the pickListCategoryPickListValue from GodownStock table
     * 
     * @return pickListCategoryPickListValue
     */
    public String getPickListCategoryPickListValue() {
        return pickListCategoryPickListValue;
    }

    /**
     * To set the pickListCategoryId values for the GodownStock table.
     * 
     * @param pickListCategoryPickListValue pickListCategoryPickListValue of the GodownStock

     */
    public void setPickListCategoryPickListValue(String pickListValuePickListValue) {
        this.pickListCategoryPickListValue = pickListValuePickListValue;
    }

    /**
     * To Get the pickListQuantityTypeId from GodownStock table
     * 
     * @return pickListQuantityTypeId
     */
    public Long getPickListQuantityTypeId() {
        return pickListQuantityTypeId;
    }

    /**
     * To set the pickListQuantityTypeId values for the GodownStock table.
     * 
     * @param pickListQuantityTypeId pickListQuantityTypeId of the GodownStock
     */
    public void setPickListQuantityTypeId(Long pickListValueId) {
        this.pickListQuantityTypeId = pickListValueId;
    }

    /**
     * To Get the pickListQuantityTypePickListValue from GodownStock table
     * 
     * @return pickListQuantityTypePickListValue
     */
    public String getPickListQuantityTypePickListValue() {
        return pickListQuantityTypePickListValue;
    }

    /**
     * To set the pickListQuantityTypePickListValue values for the GodownStock table.
     * 
     * @param pickListQuantityTypePickListValue pickListQuantityTypePickListValue of the GodownStock
     */
    public void setPickListQuantityTypePickListValue(String pickListValuePickListValue) {
        this.pickListQuantityTypePickListValue = pickListValuePickListValue;
    }

    /**
     * To Get the godownId from GodownStock table
     * 
     * @return godownId
     */
    public Long getGodownId() {
        return godownId;
    }

    /**
     * To set the godownId values for the GodownStock table.
     * 
     * @param godownId godownId of the GodownStock
     */
    public void setGodownId(Long godownId) {
        this.godownId = godownId;
    }

    /**
     * To Get the godownName from GodownStock table
     * 
     * @return godownName
     */
    public String getGodownName() {
        return godownName;
    }

    /**
     * To set the godownName values for the GodownStock table.
     * 
     * @param godownName godownName of the GodownStock
     */
    public void setGodownName(String godownName) {
        this.godownName = godownName;
    }

    /**
     * To Get the financialYearGodownStockId from GodownStock table
     * 
     * @return financialYearGodownStockId
     */
    public Long getFinancialYearGodownStockId() {
        return financialYearGodownStockId;
    }

    /**
     * To set the financialYearGodownStockId values for the GodownStock table.
     * 
     * @param financialYearGodownStockId financialYearGodownStockId of the GodownStock
     */
    public void setFinancialYearGodownStockId(Long financialYearSettingsId) {
        this.financialYearGodownStockId = financialYearSettingsId;
    }

    /**
     * To Get the financialYearGodownStockBatchName from GodownStock table
     * 
     * @return financialYearGodownStockBatchName
     */
    public String getFinancialYearGodownStockBatchName() {
        return financialYearGodownStockBatchName;
    }

    /**
     * To set the financialYearGodownStockBatchName values for the GodownStock table.
     * 
     * @param financialYearGodownStockBatchName financialYearGodownStockBatchName of the GodownStock
     */
    public void setFinancialYearGodownStockBatchName(String financialYearSettingsBatchName) {
        this.financialYearGodownStockBatchName = financialYearSettingsBatchName;
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

        GodownStockDTO godownStockDTO = (GodownStockDTO) o;
        if (godownStockDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), godownStockDTO.getId());
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
        return "GodownStockDTO{" +
            "id=" + getId() +
            ", currentQuantity=" + getCurrentQuantity() +
            ", addedQuantity=" + getAddedQuantity() +
            ", consumedQuantity=" + getConsumedQuantity() +
            ", description='" + getDescription() + "'" +
            ", status=" + getStatus() +
            ", pickListVariety=" + getPickListVarietyId() +
            ", pickListVariety='" + getPickListVarietyPickListValue() + "'" +
            ", pickListCategory=" + getPickListCategoryId() +
            ", pickListCategory='" + getPickListCategoryPickListValue() + "'" +
            ", pickListQuantityType=" + getPickListQuantityTypeId() +
            ", pickListQuantityType='" + getPickListQuantityTypePickListValue() + "'" +
            ", godown=" + getGodownId() +
            ", godown='" + getGodownName() + "'" +
            ", financialYearGodownStock=" + getFinancialYearGodownStockId() +
            ", financialYearGodownStock='" + getFinancialYearGodownStockBatchName() + "'" +
            "}";
    }
}
